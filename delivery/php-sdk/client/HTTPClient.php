<?php

namespace Maiyatian\Delivery\PhpSdk\Client;

use Maiyatian\Delivery\PhpSdk\Consts\Consts;
use Maiyatian\Delivery\PhpSdk\Vars\Variables;

/**
 * 企业级HTTP客户端
 * 对应 Go SDK 中的 HTTPClientManager
 * 提供连接池、重试、签名、日志等企业级功能
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class HTTPClient
{
    /**
     * 客户端配置
     * @var Config
     */
    private $config;
    
    /**
     * cURL句柄池
     * @var array
     */
    private $curlPool = [];
    
    /**
     * 请求计数器（用于统计）
     * @var int
     */
    private $requestCounter = 0;
    
    /**
     * 构造函数
     * 
     * @param Config $config HTTP客户端配置
     * @throws \InvalidArgumentException
     */
    public function __construct(Config $config)
    {
        // 验证配置
        $config->validate();
        
        $this->config = $config;
        
        // 初始化cURL池
        $this->initializeCurlPool();
        
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] HTTP客户端已初始化 - BaseURL: %s",
                Variables::getSdkType(),
                $this->config->baseURL
            ));
        }
    }
    
    /**
     * 执行HTTP请求（核心方法）
     * 对应 Go SDK 中的 Request 方法
     * 
     * @param HTTPRequest $request 请求对象
     * @return HTTPResponse
     * @throws \Exception
     */
    public function request(HTTPRequest $request): HTTPResponse
    {
        $this->requestCounter++;
        
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] 开始请求 #%d - %s %s",
                Variables::getSdkType(),
                $this->requestCounter,
                $request->Method,
                $request->Path
            ));
        }
        
        $attempts = 0;
        $maxAttempts = $this->config->retryMaxAttempts + 1; // 包含首次请求
        $lastError = null;
        
        while ($attempts < $maxAttempts) {
            try {
                $response = $this->executeSingleRequest($request, $attempts);
                
                // 检查是否需要重试
                if (!$this->shouldRetry($response, null, $attempts)) {
                    return $response;
                }
                
                $lastError = new \Exception(sprintf(
                    'HTTP %d错误，状态码：%d，消息：%s',
                    $response->StatusCode ?? 0,
                    $response->Code,
                    $response->Message
                ));
                
            } catch (\Exception $e) {
                $lastError = $e;
                
                // 网络错误检查是否重试
                if (!$this->shouldRetry(null, $e, $attempts)) {
                    throw $e;
                }
                
                if (Variables::isDebugging()) {
                    error_log(sprintf(
                        "[%s] 请求失败，尝试第 %d 次重试: %s",
                        Variables::getSdkType(),
                        $attempts + 1,
                        $e->getMessage()
                    ));
                }
            }
            
            $attempts++;
            
            // 计算重试延迟（指数退避）
            if ($attempts < $maxAttempts) {
                $delay = $this->calculateRetryDelay($attempts);
                usleep($delay * 1000); // 转换为微秒
            }
        }
        
        // 所有重试都失败了
        throw new \Exception(sprintf(
            '请求失败，已重试 %d 次: %s',
            $maxAttempts - 1,
            $lastError ? $lastError->getMessage() : '未知错误'
        ));
    }
    
    /**
     * GET请求
     * 
     * @param string $path API路径
     * @param string $token 授权令牌
     * @param array $headers 额外头部
     * @return HTTPResponse
     */
    public function get(string $path, string $token, array $headers = []): HTTPResponse
    {
        $request = new HTTPRequest();
        $request->Method = 'GET';
        $request->Path = $path;
        $request->Headers = array_merge($this->getDefaultHeaders(), $headers);
        
        // GET请求不传data，使用空对象
        $request->Data = json_encode(new \stdClass());
        
        return $this->executeSignedRequest($request, $token);
    }
    
    /**
     * POST请求
     * 
     * @param string $path API路径
     * @param array $data 请求数据
     * @param string $token 授权令牌
     * @param array $headers 额外头部
     * @return HTTPResponse
     */
    public function post(string $path, array $data, string $token, array $headers = []): HTTPResponse
    {
        $request = new HTTPRequest();
        $request->Method = 'POST';
        $request->Path = $path;
        $request->Headers = array_merge($this->getDefaultHeaders(), $headers);
        $request->Data = json_encode($data, JSON_UNESCAPED_UNICODE);
        
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \InvalidArgumentException('请求数据JSON编码失败: ' . json_last_error_msg());
        }
        
        return $this->executeSignedRequest($request, $token);
    }
    
    /**
     * PUT请求
     * 
     * @param string $path API路径
     * @param array $data 请求数据
     * @param string $token 授权令牌
     * @param array $headers 额外头部
     * @return HTTPResponse
     */
    public function put(string $path, array $data, string $token, array $headers = []): HTTPResponse
    {
        $request = new HTTPRequest();
        $request->Method = 'PUT';
        $request->Path = $path;
        $request->Headers = array_merge($this->getDefaultHeaders(), $headers);
        $request->Data = json_encode($data, JSON_UNESCAPED_UNICODE);
        
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \InvalidArgumentException('请求数据JSON编码失败: ' . json_last_error_msg());
        }
        
        return $this->executeSignedRequest($request, $token);
    }
    
    /**
     * DELETE请求
     * 
     * @param string $path API路径
     * @param string $token 授权令牌
     * @param array $headers 额外头部
     * @return HTTPResponse
     */
    public function delete(string $path, string $token, array $headers = []): HTTPResponse
    {
        $request = new HTTPRequest();
        $request->Method = 'DELETE';
        $request->Path = $path;
        $request->Headers = array_merge($this->getDefaultHeaders(), $headers);
        $request->Data = json_encode(new \stdClass());
        
        return $this->executeSignedRequest($request, $token);
    }
    
    /**
     * 执行带签名的请求
     * 对应 Go SDK 的签名和请求逻辑
     * 
     * @param HTTPRequest $request 请求对象
     * @param string $token 授权令牌
     * @return HTTPResponse
     */
    private function executeSignedRequest(HTTPRequest $request, string $token): HTTPResponse
    {
        // 构建标准请求结构（对应Go SDK的HTTPRequest）
        $requestData = [
            'app_key' => $this->config->apiKey,
            'request_id' => $this->generateRequestId(),
            'token' => $token,
            'timestamp' => time(),
            'data' => $request->Data,
            'signature' => '' // 稍后生成
        ];
        
        // 生成签名
        $requestData['signature'] = $this->generateSignature($requestData);
        
        // 更新请求体为标准格式
        $request->Data = json_encode($requestData, JSON_UNESCAPED_UNICODE);
        
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \Exception('请求数据序列化失败: ' . json_last_error_msg());
        }
        
        return $this->request($request);
    }
    
    /**
     * 执行单次请求
     * 
     * @param HTTPRequest $request 请求对象
     * @param int $attemptNumber 当前尝试次数
     * @return HTTPResponse
     * @throws \Exception
     */
    private function executeSingleRequest(HTTPRequest $request, int $attemptNumber): HTTPResponse
    {
        $startTime = microtime(true);
        
        // 获取或创建cURL句柄
        $ch = $this->getCurlHandle();
        
        // 构建完整URL
        $url = $this->buildUrl($request->Path);
        
        // 设置cURL选项
        $this->configureCurl($ch, $url, $request);
        
        // 执行请求
        $responseBody = curl_exec($ch);
        $httpStatusCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        $error = curl_error($ch);
        $responseTime = (microtime(true) - $startTime) * 1000; // 毫秒
        
        // 归还cURL句柄到池中
        $this->returnCurlHandle($ch);
        
        // 检查cURL错误
        if ($responseBody === false || !empty($error)) {
            throw new \Exception(sprintf('cURL错误: %s', $error ?: '未知错误'));
        }
        
        // 记录响应日志
        if (Variables::isDebugging()) {
            $this->logResponse($url, $httpStatusCode, $responseTime, $responseBody);
        }
        
        // 解析响应
        $response = $this->parseResponse($responseBody, $httpStatusCode);
        
        return $response;
    }
    
    /**
     * 配置cURL选项
     * 
     * @param resource $ch cURL句柄
     * @param string $url 请求URL
     * @param HTTPRequest $request 请求对象
     */
    private function configureCurl($ch, string $url, HTTPRequest $request): void
    {
        // 基础设置
        curl_setopt_array($ch, [
            CURLOPT_URL => $url,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_TIMEOUT => $this->config->requestTimeout,
            CURLOPT_CONNECTTIMEOUT => $this->config->connectionTimeout,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_MAXREDIRS => 3,
            CURLOPT_SSL_VERIFYPEER => true,
            CURLOPT_SSL_VERIFYHOST => 2,
            CURLOPT_USERAGENT => Variables::getUserAgent(),
            CURLOPT_ENCODING => 'gzip, deflate',
        ]);
        
        // 设置HTTP方法
        switch (strtoupper($request->Method)) {
            case 'GET':
                curl_setopt($ch, CURLOPT_HTTPGET, true);
                break;
            case 'POST':
                curl_setopt($ch, CURLOPT_POST, true);
                curl_setopt($ch, CURLOPT_POSTFIELDS, $request->Data);
                break;
            case 'PUT':
                curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
                curl_setopt($ch, CURLOPT_POSTFIELDS, $request->Data);
                break;
            case 'DELETE':
                curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
                break;
        }
        
        // 设置请求头
        $headers = [];
        foreach ($request->Headers as $key => $value) {
            $headers[] = sprintf('%s: %s', $key, $value);
        }
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        
        // 记录请求日志
        if (Variables::isDebugging()) {
            $this->logRequest($url, $request);
        }
    }
    
    /**
     * 解析HTTP响应
     * 
     * @param string $responseBody 响应体
     * @param int $httpStatusCode HTTP状态码
     * @return HTTPResponse
     * @throws \Exception
     */
    private function parseResponse(string $responseBody, int $httpStatusCode): HTTPResponse
    {
        $response = new HTTPResponse();
        $response->StatusCode = $httpStatusCode;
        
        // 解析JSON响应
        $jsonData = json_decode($responseBody, true);
        
        if (json_last_error() !== JSON_ERROR_NONE) {
            // JSON解析失败，构造错误响应
            $response->Code = 500;
            $response->Message = 'JSON解析失败: ' . json_last_error_msg();
            $response->Data = '';
            return $response;
        }
        
        // 填充响应字段
        $response->Code = $jsonData['code'] ?? 500;
        $response->Message = $jsonData['message'] ?? 'Unknown error';
        $response->Data = isset($jsonData['data']) ? json_encode($jsonData['data'], JSON_UNESCAPED_UNICODE) : '';
        
        return $response;
    }
    
    /**
     * 检查是否需要重试
     * 
     * @param HTTPResponse|null $response 响应对象
     * @param \Exception|null $exception 异常对象
     * @param int $attempt 当前尝试次数
     * @return bool
     */
    private function shouldRetry(?HTTPResponse $response, ?\Exception $exception, int $attempt): bool
    {
        // 已达到最大重试次数
        if ($attempt >= $this->config->retryMaxAttempts) {
            return false;
        }
        
        // 网络异常，需要重试
        if ($exception !== null) {
            return true;
        }
        
        // HTTP状态码检查
        if ($response !== null) {
            // 5xx服务器错误重试
            if ($response->StatusCode >= 500) {
                return true;
            }
            
            // 业务层5xx错误重试（但排除严重异常）
            if ($response->StatusCode === 200 && 
                $response->Code >= 500 && 
                $response->Code < Consts::STATUS_EXCEPTION_SERVER_ERROR) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 计算重试延迟（指数退避）
     * 
     * @param int $attempt 当前尝试次数
     * @return int 延迟毫秒数
     */
    private function calculateRetryDelay(int $attempt): int
    {
        $baseDelay = $this->config->retryBaseDelay;
        $maxDelay = $this->config->retryMaxDelay;
        
        // 指数退避：base * 2^(attempt-1)
        $delay = $baseDelay * pow(2, $attempt - 1);
        
        // 限制最大延迟
        return min($delay, $maxDelay);
    }
    
    /**
     * 生成请求签名
     * 对应 Go SDK 中的 GenSign 方法
     * 
     * @param array $requestData 请求数据
     * @return string 签名值
     * @throws \Exception
     */
    private function generateSignature(array $requestData): string
    {
        // 移除signature字段（不参与签名）
        $dataToSign = $requestData;
        unset($dataToSign['signature']);
        
        // 按key排序（ksort）
        ksort($dataToSign);
        
        // 构建待签名字符串
        $signString = '';
        foreach ($dataToSign as $key => $value) {
            $signString .= sprintf('%s=%s,', $key, $value);
        }
        $signString = rtrim($signString, ','); // 移除最后的逗号
        
        // HMAC-SHA256签名
        $signature = hash_hmac('sha256', $signString, $this->config->apiSecret, true);
        
        // Base64 URL安全编码
        return rtrim(strtr(base64_encode($signature), '+/', '-_'), '=');
    }
    
    /**
     * 生成请求ID
     * 对应 Go SDK 中的 generateRequestID 方法
     * 
     * @return string UUID格式的请求ID
     */
    private function generateRequestId(): string
    {
        // 生成16字节随机数
        $data = random_bytes(16);
        
        // 设置版本和变体位
        $data[6] = chr(ord($data[6]) & 0x0f | 0x40); // 版本4
        $data[8] = chr(ord($data[8]) & 0x3f | 0x80); // 变体位
        
        // 格式化为UUID
        return sprintf('%s-%s-%s-%s-%s',
            bin2hex(substr($data, 0, 4)),
            bin2hex(substr($data, 4, 2)),
            bin2hex(substr($data, 6, 2)),
            bin2hex(substr($data, 8, 2)),
            bin2hex(substr($data, 10, 6))
        );
    }
    
    /**
     * 构建完整URL
     * 
     * @param string $path API路径
     * @return string 完整URL
     */
    private function buildUrl(string $path): string
    {
        // 检查是否为绝对URL
        if (strpos($path, 'http://') === 0 || strpos($path, 'https://') === 0) {
            return $path;
        }
        
        return rtrim($this->config->baseURL, '/') . '/' . ltrim($path, '/');
    }
    
    /**
     * 获取默认HTTP头
     * 
     * @return array
     */
    private function getDefaultHeaders(): array
    {
        return [
            'Content-Type' => Consts::CONTENT_TYPE_JSON_UTF8,
            'Accept' => Consts::CONTENT_TYPE_JSON,
            'User-Agent' => Variables::getUserAgent(),
            'X-SDK-Version' => Variables::getVersion(),
            'X-SDK-Type' => Variables::getSdkType(),
            'Accept-Encoding' => 'gzip, deflate'
        ];
    }
    
    /**
     * 初始化cURL连接池
     */
    private function initializeCurlPool(): void
    {
        $poolSize = min($this->config->maxConnections, 10); // 限制池大小
        
        for ($i = 0; $i < $poolSize; $i++) {
            $this->curlPool[] = curl_init();
        }
        
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] cURL连接池已初始化，大小: %d",
                Variables::getSdkType(),
                $poolSize
            ));
        }
    }
    
    /**
     * 获取cURL句柄
     * 
     * @return resource
     */
    private function getCurlHandle()
    {
        if (!empty($this->curlPool)) {
            return array_pop($this->curlPool);
        }
        
        // 池中没有可用句柄，创建新的
        return curl_init();
    }
    
    /**
     * 归还cURL句柄到池中
     * 
     * @param resource $ch cURL句柄
     */
    private function returnCurlHandle($ch): void
    {
        // 重置句柄状态
        curl_reset($ch);
        
        // 如果池未满，归还到池中
        if (count($this->curlPool) < $this->config->maxConnections) {
            $this->curlPool[] = $ch;
        } else {
            curl_close($ch);
        }
    }
    
    /**
     * 记录请求日志
     * 
     * @param string $url 请求URL
     * @param HTTPRequest $request 请求对象
     */
    private function logRequest(string $url, HTTPRequest $request): void
    {
        $logData = [
            'method' => $request->Method,
            'url' => $url,
            'has_body' => !empty($request->Data),
            'timestamp' => date('Y-m-d H:i:s')
        ];
        
        if ($this->config->logRequestBody && !empty($request->Data)) {
            $bodyData = json_decode($request->Data, true);
            if (json_last_error() === JSON_ERROR_NONE) {
                $logData['body'] = $bodyData;
            }
        }
        
        error_log(sprintf(
            "[%s] 请求: %s",
            Variables::getSdkType(),
            json_encode($logData, JSON_UNESCAPED_UNICODE)
        ));
    }
    
    /**
     * 记录响应日志
     * 
     * @param string $url 请求URL
     * @param int $statusCode HTTP状态码
     * @param float $responseTime 响应时间（毫秒）
     * @param string $responseBody 响应体
     */
    private function logResponse(string $url, int $statusCode, float $responseTime, string $responseBody): void
    {
        $logData = [
            'url' => $url,
            'status_code' => $statusCode,
            'response_time_ms' => round($responseTime, 2),
            'response_size' => strlen($responseBody),
            'timestamp' => date('Y-m-d H:i:s')
        ];
        
        if ($this->config->logResponseBody && !empty($responseBody)) {
            $responseData = json_decode($responseBody, true);
            if (json_last_error() === JSON_ERROR_NONE) {
                $logData['body'] = $responseData;
            }
        }
        
        error_log(sprintf(
            "[%s] 响应: %s",
            Variables::getSdkType(),
            json_encode($logData, JSON_UNESCAPED_UNICODE)
        ));
    }
    
    /**
     * 获取客户端配置
     * 
     * @return Config
     */
    public function getConfig(): Config
    {
        return $this->config;
    }
    
    /**
     * 更新请求超时设置
     * 
     * @param int $timeout 超时时间（秒）
     */
    public function updateTimeout(int $timeout): void
    {
        $this->config->requestTimeout = $timeout;
        
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] 请求超时已更新为: %d秒",
                Variables::getSdkType(),
                $timeout
            ));
        }
    }
    
    /**
     * 更新重试设置
     * 
     * @param int $maxAttempts 最大重试次数
     * @param int $baseDelay 基础延迟（毫秒）
     * @param int $maxDelay 最大延迟（毫秒）
     */
    public function updateRetrySettings(int $maxAttempts, int $baseDelay, int $maxDelay): void
    {
        $this->config->retryMaxAttempts = $maxAttempts;
        $this->config->retryBaseDelay = $baseDelay;
        $this->config->retryMaxDelay = $maxDelay;
        
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] 重试设置已更新 - 最大次数: %d, 基础延迟: %dms, 最大延迟: %dms",
                Variables::getSdkType(),
                $maxAttempts,
                $baseDelay,
                $maxDelay
            ));
        }
    }
    
    /**
     * 关闭客户端并清理资源
     */
    public function close(): void
    {
        // 关闭连接池中的所有cURL句柄
        foreach ($this->curlPool as $ch) {
            curl_close($ch);
        }
        $this->curlPool = [];
        
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] HTTP客户端已关闭，处理了 %d 个请求",
                Variables::getSdkType(),
                $this->requestCounter
            ));
        }
    }
    
    /**
     * 析构函数
     */
    public function __destruct()
    {
        $this->close();
    }
}

/**
 * HTTP响应数据结构
 * 对应 Go SDK 中的 HTTPResponse
 */
class HTTPResponse
{
    /**
     * HTTP状态码
     * @var int
     */
    public $StatusCode;
    
    /**
     * 业务状态码
     * @var int
     */
    public $Code;
    
    /**
     * 响应消息
     * @var string
     */
    public $Message;
    
    /**
     * 业务数据（JSON字符串）
     * @var string
     */
    public $Data;
    
    /**
     * 反序列化数据到指定格式
     * 对应 Go SDK 中的 UnmarshalData 方法
     * 
     * @param mixed $formatData 目标数据引用
     * @return bool 是否成功
     */
    public function unmarshalData(&$formatData): bool
    {
        if (empty($this->Data)) {
            $formatData = null;
            return true;
        }
        
        $formatData = json_decode($this->Data, true);
        return json_last_error() === JSON_ERROR_NONE;
    }
}