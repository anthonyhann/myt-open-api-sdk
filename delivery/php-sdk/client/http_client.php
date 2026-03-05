<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: client
 * @ClassName: HTTPClientManager
 * @Description: 麦芽田配送开放平台SDK - HTTP客户端实现
 * 提供企业级HTTP客户端，支持连接池、重试、日志等特性
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\client;

use GuzzleHttp\Client;
use GuzzleHttp\HandlerStack;
use GuzzleHttp\MessageFormatter;
use GuzzleHttp\Middleware;
use GuzzleHttp\RetryMiddleware;
use GuzzleHttp\Exception\GuzzleException;
use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use InvalidArgumentException;
use Throwable;

class HTTPClientManager
{
    /**
     * @var Config
     */
    private $config;

    /**
     * @var Client
     */
    private $client;

    /**
     * @var Logger|null
     */
    private $logger;

    /**
     * HTTPRequest 标准请求结构
     *
     * 字段说明:
     *   - app_key: 应用密钥，用于标识调用方
     *   - request_id: 请求唯一标识，用于追踪请求链路
     *   - token: 授权令牌，用于身份验证
     *   - timestamp: 请求时间戳，Unix秒级时间戳
     *   - data: 业务数据，JSON字符串格式
     *   - signature: 请求签名，HmacSHA256签名值
     */
    class HTTPRequest
    {
        public $app_key;
        public $request_id;
        public $token;
        public $timestamp;
        public $data;
        public $signature;
    }

    /**
     * HTTPResponse 标准化HTTP响应结构
     *
     * 字段说明:
     *   - code: 业务状态码，200表示成功
     *   - message: 响应消息，成功时为"ok"
     *   - data: 业务数据，JSON字符串格式（需要反序列化）
     */
    class HTTPResponse
    {
        public $code;
        public $message;
        public $data;
    }

    /**
     * 创建新的HTTP客户端管理器
     *
     * 功能说明:
     *   - 创建并初始化企业级HTTP客户端
     *   - 配置连接池、重试策略、日志等特性
     *   - 设置请求签名中间件
     *
     * @param Config $config HTTP客户端配置
     * @throws InvalidArgumentException
     */
    public function __construct(Config $config)
    {
        $config->validate();
        $this->config = $config;
        $this->client = $this->createHttpClient();
        $this->logger = $this->createLogger();
    }

    /**
     * 创建HTTP客户端
     *
     * @return Client
     */
    private function createHttpClient(): Client
    {
        $stack = HandlerStack::create();

        // 添加重试中间件
        $stack->push(Middleware::retry(function (
            $retries, 
            \Psr\Http\Message\RequestInterface $request, 
            \Psr\Http\Message\ResponseInterface $response = null, 
            Throwable $exception = null
        ) {
            // 重试条件：
            // 1. 网络错误或5xx服务器错误
            // 2. 重试次数未超过配置值
            if ($retries >= $this->config->retryMaxAttempts) {
                return false;
            }

            // 网络错误
            if ($exception !== null) {
                return true;
            }

            // HTTP 5xx错误
            if ($response->getStatusCode() >= 500) {
                return true;
            }

            // 业务层5xx错误
            $body = (string)$response->getBody();
            $responseData = json_decode($body, true);
            if (is_array($responseData) && isset($responseData['code']) && $responseData['code'] >= 500) {
                return true;
            }

            return false;
        }, function ($retries) {
            // 指数退避策略
            $delay = $this->config->retryBaseDelay * pow(2, $retries - 1);
            return min($delay, $this->config->retryMaxDelay);
        }));

        // 添加日志中间件
        if ($this->config->enableLogging) {
            $formatter = new MessageFormatter(MessageFormatter::DEBUG);
            $stack->push(Middleware::log($this->logger, $formatter));
        }

        $options = [
            'base_uri' => $this->config->baseURL,
            'handler' => $stack,
            'timeout' => $this->config->requestTimeout,
            'connect_timeout' => $this->config->connectionTimeout,
            'http_errors' => false, // 禁用自动抛出HTTP错误，我们自己处理
            'headers' => [
                'Content-Type' => 'application/json;charset=utf-8',
                'User-Agent' => 'Maiyatian-PHP-SDK/' . $this->config->sdkVersion,
                'Accept' => 'application/json',
                'Accept-Encoding' => 'gzip, deflate',
            ],
            // 连接池设置
            'curl' => [
                CURLOPT_TCP_KEEPALIVE => 1,
                CURLOPT_TCP_KEEPIDLE => $this->config->keepAliveTimeout,
            ],
        ];

        return new Client($options);
    }

    /**
     * 创建日志记录器
     *
     * @return Logger|null
     */
    private function createLogger(): ?Logger
    {
        if (!$this->config->enableLogging) {
            return null;
        }

        $logger = new Logger('maiyatian-php-sdk');
        $handler = new StreamHandler('php://stderr', Logger::DEBUG);
        $logger->pushHandler($handler);

        return $logger;
    }

    /**
     * 执行HTTP请求
     *
     * 功能说明:
     *   - 执行HTTP请求并自动处理签名、重试等逻辑
     *   - 自动生成请求ID和时间戳
     *   - 自动计算HmacSHA256签名
     *   - 自动处理业务错误码
     *
     * @param string $method HTTP方法，如'GET'、'POST'
     * @param string $path 请求路径，可以是相对路径或完整URL
     * @param mixed $data 业务数据，会被序列化为JSON
     * @param string $token 授权令牌
     * @param array|null $headers 额外的HTTP请求头
     * @return HTTPResponse
     * @throws GuzzleException
     * @throws InvalidArgumentException
     */
    public function request(string $method, string $path, $data, string $token = '', array $headers = null): HTTPResponse
    {
        if ($data === null) {
            throw new InvalidArgumentException('data is required');
        }

        $requestBody = $this->buildRequestBody($data, $token);

        $options = [
            'json' => $requestBody,
        ];

        if ($headers !== null) {
            $options['headers'] = $headers;
        }

        $response = $this->client->request($method, $path, $options);
        $statusCode = $response->getStatusCode();
        $responseBody = (string)$response->getBody();

        $httpResponse = new HTTPResponse();
        $responseData = json_decode($responseBody, true);

        if (is_array($responseData)) {
            $httpResponse->code = $responseData['code'] ?? $statusCode;
            $httpResponse->message = $responseData['message'] ?? '';
            $httpResponse->data = $responseData['data'] ?? '';
        } else {
            $httpResponse->code = $statusCode;
            $httpResponse->message = 'Invalid JSON response';
            $httpResponse->data = $responseBody;
        }

        return $httpResponse;
    }

    /**
     * 构建请求体并生成签名
     *
     * @param mixed $data 业务数据
     * @param string $token 授权令牌
     * @return HTTPRequest
     */
    private function buildRequestBody($data, string $token): HTTPRequest
    {
        $dataString = json_encode($data, JSON_UNESCAPED_UNICODE);
        if ($dataString === false) {
            throw new InvalidArgumentException('Failed to encode data to JSON');
        }

        $request = new HTTPRequest();
        $request->app_key = $this->config->apiKey;
        $request->request_id = $this->generateRequestID();
        $request->token = $token;
        $request->timestamp = time();
        $request->data = $dataString;
        $request->signature = $this->generateSign($request);

        return $request;
    }

    /**
     * 生成请求签名
     *
     * 功能说明:
     *   - 根据麦芽田开放平台签名规范生成HmacSHA256签名
     *   - 签名步骤：提取字段 -> 排序 -> 拼接 -> HmacSHA256 -> Base64编码
     *
     * 签名规则:
     *   1. 提取请求体中的app_key、token、timestamp、data字段
     *   2. 按字母顺序排序（ksort）
     *   3. 用半角逗号连接：key1=value1,key2=value2,...
     *   4. 使用appSecret计算HmacSHA256值
     *   5. 对结果进行URL安全的Base64编码
     *
     * @param HTTPRequest $request HTTP请求结构体
     * @return string 签名字符串
     */
    private function generateSign(HTTPRequest $request): string
    {
        // 提取签名字段
        $signFields = [
            'app_key' => $request->app_key,
            'token' => $request->token,
            'timestamp' => $request->timestamp,
            'data' => $request->data,
        ];

        // 按字母顺序排序
        ksort($signFields);

        // 拼接字符串
        $dataToSign = '';
        foreach ($signFields as $key => $value) {
            $dataToSign .= "{$key}={$value},";
        }
        $dataToSign = rtrim($dataToSign, ',');

        // 计算HmacSHA256签名
        $hmac = hash_hmac('sha256', $dataToSign, $this->config->apiSecret, true);
        $signature = base64_encode($hmac);

        // URL安全的Base64编码
        $signature = str_replace(['+', '/', '='], ['-', '_', ''], $signature);

        return $signature;
    }

    /**
     * 生成请求ID
     *
     * @return string
     */
    private function generateRequestID(): string
    {
        $timestamp = substr((string)time(), -6);
        $random = bin2hex(random_bytes(8));
        return "{$timestamp}-{$random}";
    }

    /**
     * 执行GET请求
     *
     * @param string $path 请求路径
     * @param string $token 授权令牌
     * @param array|null $headers 额外的HTTP请求头
     * @return HTTPResponse
     * @throws GuzzleException
     * @throws InvalidArgumentException
     */
    public function get(string $path, string $token = '', array $headers = null): HTTPResponse
    {
        return $this->request('GET', $path, [], $token, $headers);
    }

    /**
     * 执行POST请求
     *
     * @param string $path 请求路径
     * @param mixed $data 业务数据
     * @param string $token 授权令牌
     * @param array|null $headers 额外的HTTP请求头
     * @return HTTPResponse
     * @throws GuzzleException
     * @throws InvalidArgumentException
     */
    public function post(string $path, $data, string $token = '', array $headers = null): HTTPResponse
    {
        return $this->request('POST', $path, $data, $token, $headers);
    }

    /**
     * 关闭HTTP客户端
     *
     * @return void
     */
    public function close(): void
    {
        // Guzzle客户端自动管理连接池，无需显式关闭
        if ($this->logger !== null) {
            $this->logger->info('HTTP client closed');
        }
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
     * 动态更新超时设置
     *
     * @param int $timeout 超时时间（秒）
     * @return void
     */
    public function updateTimeout(int $timeout): void
    {
        $this->config->requestTimeout = $timeout;
        $this->client = $this->createHttpClient();
    }

    /**
     * 动态更新重试设置
     *
     * @param int $maxAttempts 最大重试次数
     * @param int $baseDelay 重试基础延迟（毫秒）
     * @param int $maxDelay 重试最大延迟（毫秒）
     * @return void
     */
    public function updateRetrySettings(int $maxAttempts, int $baseDelay, int $maxDelay): void
    {
        $this->config->retryMaxAttempts = $maxAttempts;
        $this->config->retryBaseDelay = $baseDelay;
        $this->config->retryMaxDelay = $maxDelay;
        $this->client = $this->createHttpClient();
    }
}
