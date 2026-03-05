<?php

namespace Maiyatian\Channel\PhpSdk\Client;

use Maiyatian\Channel\PhpSdk\Utils\Tools;
use Maiyatian\Channel\PhpSdk\Consts\Consts;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

/**
 * 企业级 HTTP 客户端管理器
 * 封装了麦芽田开放平台 API 调用的所有功能：
 * - 连接池管理：复用 HTTP 连接，提升性能
 * - 自动重试：网络错误和 5xx 错误自动重试
 * - 签名生成：自动生成符合麦芽田规范的请求签名
 * - 日志记录：记录请求和响应的详细信息，便于调试
 * - 超时控制：连接、请求、读取的多层超时保护
 */
class HTTPClientManager
{
    /**
     * 客户端配置
     * @var HTTPClientConfig
     */
    private $config;

    /**
     * Curl 句柄资源
     * @var resource
     */
    private $curlHandle;

    /**
     * 日志记录器
     * @var \Monolog\Logger|null
     */
    private $logger;

    /**
     * HTTPClientManager 构造函数
     * @param HTTPClientConfig $config
     * @throws ConfigValidationError
     */
    public function __construct(HTTPClientConfig $config)
    {
        // 验证配置有效性
        $config->Validate();
        $this->config = $config;

        // 初始化 curl 句柄
        $this->curlHandle = curl_init();

        // 设置基础 curl 选项
        curl_setopt($this->curlHandle, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($this->curlHandle, CURLOPT_HEADER, false);
        curl_setopt($this->curlHandle, CURLOPT_ENCODING, 'gzip, deflate');
        curl_setopt($this->curlHandle, CURLOPT_TIMEOUT, $this->config->RequestTimeout);
        curl_setopt($this->curlHandle, CURLOPT_CONNECTTIMEOUT, $this->config->ConnectionTimeout);
        curl_setopt($this->curlHandle, CURLOPT_USERAGENT, Variables::getUserAgent());
    }

    /**
     * 创建新的 HTTP 客户端管理器
     * @param HTTPClientConfig $config
     * @return HTTPClientManager
     * @throws ConfigValidationError
     */
    public static function NewHTTPClientManager(HTTPClientConfig $config)
    {
        return new self($config);
    }

    /**
     * 执行 HTTP 请求
     * @param string $method HTTP 方法（GET/POST）
     * @param string $path API 路径，如 "v1/channel/order_created"
     * @param mixed $data 业务参数，会被序列化为 JSON 字符串放入 data 字段
     * @param string $token 商户授权令牌
     * @param array $headers 额外的 HTTP 请求头（可选）
     * @return HTTPResponse
     * @throws \Exception
     */
    public function Request($method, $path, $data, $token = '', array $headers = [])
    {
        // 构建完整的请求 URL
        $url = $this->buildURL($path);

        // 验证业务参数
        if (empty($data)) {
            throw new \Exception("业务参数 data 不能为空");
        }

        // 序列化业务参数为 JSON 字符串
        $dataString = json_encode($data, JSON_UNESCAPED_UNICODE);
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \Exception(sprintf("序列化业务参数失败: %s", json_last_error_msg()));
        }

        // 构建请求数据结构
        $request = new HTTPRequest();
        $request->AppKey = $this->config->APIKey;
        $request->RequestId = $this->generateRequestID();
        $request->Token = $token;
        $request->Timestamp = time();
        $request->Data = $dataString;
        $request->Signature = '';

        // 生成请求签名
        $request->Signature = $this->GenSign($request, $this->config->APISecret);

        // 序列化请求数据
        $requestString = json_encode($request, JSON_UNESCAPED_UNICODE);
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \Exception(sprintf("序列化请求数据失败: %s", json_last_error_msg()));
        }

        // 设置 curl 选项
        curl_setopt($this->curlHandle, CURLOPT_URL, $url);
        curl_setopt($this->curlHandle, CURLOPT_CUSTOMREQUEST, $method);

        // 设置请求头
        $defaultHeaders = [
            'Content-Type: ' . Consts::CONTENT_TYPE_JSON_UTF8,
            'Accept: ' . Consts::CONTENT_TYPE_JSON,
            sprintf('Content-Length: %d', strlen($requestString)),
        ];

        if (!empty($headers)) {
            foreach ($headers as $key => $value) {
                $defaultHeaders[] = sprintf('%s: %s', $key, $value);
            }
        }

        curl_setopt($this->curlHandle, CURLOPT_HTTPHEADER, $defaultHeaders);

        // 设置请求体（仅 POST 请求）
        if (strtoupper($method) === 'POST') {
            curl_setopt($this->curlHandle, CURLOPT_POSTFIELDS, $requestString);
        }

        // 记录请求日志
        $this->logRequest($method, $url, $request, $headers);

        // 执行请求（带重试机制）
        $retryAttempts = 0;
        $responseBody = '';
        $httpCode = 0;

        while ($retryAttempts <= $this->config->RetryMaxAttempts) {
            // 执行请求
            $responseBody = curl_exec($this->curlHandle);
            $httpCode = curl_getinfo($this->curlHandle, CURLINFO_HTTP_CODE);
            $error = curl_error($this->curlHandle);

            // 检查 curl 错误
            if (!empty($error)) {
                $retryAttempts++;
                if ($retryAttempts > $this->config->RetryMaxAttempts) {
                    throw new \Exception(sprintf("HTTP 请求失败: %s", $error));
                }
                $this->logRetry($method, $url, $retryAttempts, $error);
                $this->delayRetry($retryAttempts);
                continue;
            }

            // 检查 HTTP 状态码（5xx 错误重试）
            if ($httpCode >= Consts::SERVER_ERROR_START && $httpCode < Consts::STATUS_EXCEPTION_SERVER_ERROR) {
                $retryAttempts++;
                if ($retryAttempts > $this->config->RetryMaxAttempts) {
                    break;
                }
                $this->logRetry($method, $url, $retryAttempts, sprintf("HTTP %d 错误", $httpCode));
                $this->delayRetry($retryAttempts);
                continue;
            }

            // 请求成功，跳出循环
            break;
        }

        // 记录响应日志
        $this->logResponse($httpCode, $responseBody);

        // 解析响应
        $response = $this->parseResponse($responseBody);
        if ($response->Code !== Consts::SUCCESS_CODE) {
            throw new \Exception(sprintf("API 错误 [%d]: %s", $response->Code, $response->Message));
        }

        return $response;
    }

    /**
     * 执行 GET 请求
     * @param string $path API 路径
     * @param string $token 商户授权令牌
     * @param array $headers 额外的请求头（可选）
     * @return HTTPResponse
     * @throws \Exception
     */
    public function Get($path, $token = '', array $headers = [])
    {
        return $this->Request('GET', $path, (object)[], $token, $headers);
    }

    /**
     * 执行 POST 请求
     * @param string $path API 路径
     * @param mixed $data 业务参数
     * @param string $token 商户授权令牌
     * @param array $headers 额外的请求头（可选）
     * @return HTTPResponse
     * @throws \Exception
     */
    public function Post($path, $data, $token = '', array $headers = [])
    {
        return $this->Request('POST', $path, $data, $token, $headers);
    }

    /**
     * 关闭 HTTP 客户端，释放资源
     */
    public function Close()
    {
        if (is_resource($this->curlHandle)) {
            curl_close($this->curlHandle);
        }
        if ($this->logger) {
            $this->logger->info('HTTP 客户端已关闭');
        }
    }

    /**
     * 生成请求签名
     * @param HTTPRequest $request 请求对象
     * @param string $secretKey 应用密钥
     * @return string
     */
    public function GenSign(HTTPRequest $request, $secretKey)
    {
        // 将对象转换为数组
        $data = Tools::ToMap($request);

        // 提取所有键并排序
        ksort($data);

        // 构建待签名字符串
        $dataToSign = '';
        foreach ($data as $key => $value) {
            $dataToSign .= sprintf('%s=%s,', $key, $value);
        }
        // 移除最后一个逗号
        $dataToSign = rtrim($dataToSign, ',');

        // 计算 HmacSHA256 签名
        $signature = hash_hmac('sha256', $dataToSign, $secretKey, true);

        // 使用 URL 安全的 Base64 编码
        $urlSafeSignature = str_replace(['+', '/', '='], ['-', '_', ''], base64_encode($signature));

        return $urlSafeSignature;
    }

    /**
     * 获取客户端配置
     * @return HTTPClientConfig
     */
    public function GetConfig()
    {
        return $this->config;
    }

    /**
     * 构建完整的请求 URL
     * @param string $path API 路径
     * @return string
     */
    private function buildURL($path)
    {
        if (filter_var($path, FILTER_VALIDATE_URL)) {
            return $path;
        }
        return rtrim($this->config->BaseURL, '/') . '/' . ltrim($path, '/');
    }

    /**
     * 生成请求唯一标识（UUID 格式）
     * @return string
     */
    private function generateRequestID()
    {
        $data = random_bytes(16);
        $data[6] = chr(ord($data[6]) & 0x0f | 0x40); // Version 4
        $data[8] = chr(ord($data[8]) & 0x3f | 0x80); // Variant 10
        return vsprintf('%s-%s-%s-%s-%s', str_split(bin2hex($data), 4));
    }

    /**
     * 解析响应
     * @param string $responseBody 响应体
     * @return HTTPResponse
     * @throws \Exception
     */
    private function parseResponse($responseBody)
    {
        $response = json_decode($responseBody);
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \Exception(sprintf("解析响应失败: %s", json_last_error_msg()));
        }

        // 验证响应结构
        if (!isset($response->Code) || !isset($response->Message)) {
            throw new \Exception(sprintf("响应格式错误: %s", $responseBody));
        }

        $httpResponse = new HTTPResponse();
        $httpResponse->Code = $response->Code;
        $httpResponse->Message = $response->Message;
        $httpResponse->Data = $response->Data ?? '';

        return $httpResponse;
    }

    /**
     * 日志记录请求信息
     * @param string $method HTTP 方法
     * @param string $url 请求 URL
     * @param HTTPRequest $request 请求对象
     * @param array $headers 请求头
     */
    private function logRequest($method, $url, $request, $headers)
    {
        if (!$this->config->EnableLogging && !Variables::isDebugging()) {
            return;
        }

        $logData = [
            'method' => $method,
            'url' => $url,
            'headers' => $headers,
            'timestamp' => date('Y-m-d H:i:s'),
        ];

        if ($this->config->LogRequestBody || Variables::isDebugging()) {
            $logData['body'] = $request;
        }

        $this->outputLog('请求', $logData);
    }

    /**
     * 日志记录响应信息
     * @param int $httpCode HTTP 状态码
     * @param string $responseBody 响应体
     */
    private function logResponse($httpCode, $responseBody)
    {
        if (!$this->config->EnableLogging && !Variables::isDebugging()) {
            return;
        }

        $logData = [
            'status_code' => $httpCode,
            'response_time_ms' => curl_getinfo($this->curlHandle, CURLINFO_TOTAL_TIME) * 1000,
            'timestamp' => date('Y-m-d H:i:s'),
        ];

        if ($this->config->LogResponseBody || Variables::isDebugging()) {
            $logData['body'] = $responseBody;
        }

        $this->outputLog('响应', $logData);
    }

    /**
     * 日志记录重试信息
     * @param string $method HTTP 方法
     * @param string $url 请求 URL
     * @param int $attempt 重试次数
     * @param string $reason 重试原因
     */
    private function logRetry($method, $url, $attempt, $reason)
    {
        if (!$this->config->EnableLogging) {
            return;
        }

        $logData = [
            'method' => $method,
            'url' => $url,
            'attempt' => $attempt,
            'reason' => $reason,
            'timestamp' => date('Y-m-d H:i:s'),
        ];

        $this->outputLog('重试', $logData);
    }

    /**
     * 输出日志
     * @param string $type 日志类型
     * @param array $data 日志数据
     */
    private function outputLog($type, $data)
    {
        if ($this->logger) {
            // 如果有日志记录器，使用日志记录器
            $this->logger->info($type, $data);
        } else {
            // 否则输出到标准错误
            fwrite(STDERR, sprintf("[%s] %s: %s\n", date('Y-m-d H:i:s'), $type, json_encode($data, JSON_UNESCAPED_UNICODE)));
        }
    }

    /**
     * 延迟重试
     * @param int $attempt 重试次数
     */
    private function delayRetry($attempt)
    {
        // 使用指数退避策略计算延迟时间
        $delay = min(
            $this->config->RetryBaseDelay * pow(2, $attempt - 1),
            $this->config->RetryMaxDelay
        );
        sleep($delay);
    }

    /**
     * 析构函数
     */
    public function __destruct()
    {
        $this->Close();
    }
}

/**
 * 麦芽田 API 标准请求结构
 * 所有接口请求都需要包含这些公共参数
 */
class HTTPRequest
{
    /**
     * 应用密钥，由麦芽田开放平台分配
     * @var string
     */
    public $AppKey;

    /**
     * 请求唯一标识（UUID），每次请求需生成新值
     * @var string
     */
    public $RequestId;

    /**
     * 商户授权令牌
     * @var string
     */
    public $Token;

    /**
     * 请求时间戳（Unix 秒），需与实际时间误差不超过 10 分钟
     * @var int
     */
    public $Timestamp;

    /**
     * 业务参数，序列化为 JSON 字符串
     * @var string
     */
    public $Data;

    /**
     * 请求签名，用于验证请求合法性
     * @var string
     */
    public $Signature;
}

/**
 * 麦芽田 API 标准响应结构
 * 所有接口响应都遵循统一的格式
 */
class HTTPResponse
{
    /**
     * 状态码：200 表示成功，其他表示失败
     * @var int
     */
    public $Code;

    /**
     * 返回消息：成功时为 "ok"，失败时为错误描述
     * @var string
     */
    public $Message;

    /**
     * 业务数据，序列化为 JSON 字符串（可选）
     * @var string
     */
    public $Data;
}
