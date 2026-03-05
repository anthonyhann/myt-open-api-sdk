<?php

namespace Maiyatian\Channel\PhpSdk;

use Maiyatian\Channel\PhpSdk\Client\HTTPClientManager;
use Maiyatian\Channel\PhpSdk\Client\HTTPClientConfig;
use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Client\ApiClientUtils;
use Maiyatian\Channel\PhpSdk\Client\ApiResponse;
use Maiyatian\Channel\PhpSdk\Consts\Consts;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

/**
 * 麦芽田渠道 PHP SDK 主入口类
 * 对应 Go SDK 的整体架构设计，提供统一的 SDK 访问接口
 * 
 * 核心功能：
 * - 客户端配置和初始化
 * - 类型安全的 API 调用
 * - 统一的错误处理
 * - 环境变量配置支持
 * - 调试模式控制
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class ChannelSDK
{
    /**
     * HTTP 客户端实例
     * @var HTTPClientManager
     */
    private $client;
    
    /**
     * 客户端配置
     * @var HTTPClientConfig
     */
    private $config;
    
    /**
     * ChannelSDK 构造函数
     * 
     * @param HTTPClientConfig $config 客户端配置
     * @throws \Exception 配置验证失败
     */
    public function __construct(HTTPClientConfig $config)
    {
        $this->config = $config;
        $this->client = HTTPClientManager::NewHTTPClientManager($config);
    }
    
    // ==================== 静态工厂方法 ====================
    
    /**
     * 使用默认配置创建 SDK 实例
     * 
     * @param string $apiKey 应用密钥 (app_key)
     * @param string $apiSecret 应用密钥 (app_secret)
     * @param string $baseURL API基础地址，默认使用正式环境
     * @return ChannelSDK SDK 实例
     * @throws \Exception 配置验证失败
     */
    public static function newDefault(string $apiKey, string $apiSecret, string $baseURL = ''): self
    {
        $config = HTTPClientConfig::NewDefaultConfig();
        $config->APIKey = $apiKey;
        $config->APISecret = $apiSecret;
        
        if (!empty($baseURL)) {
            $config->BaseURL = $baseURL;
        }
        
        return new self($config);
    }
    
    /**
     * 使用测试环境创建 SDK 实例
     * 
     * @param string $apiKey 应用密钥 (app_key)
     * @param string $apiSecret 应用密钥 (app_secret)
     * @return ChannelSDK SDK 实例
     * @throws \Exception 配置验证失败
     */
    public static function newForTest(string $apiKey, string $apiSecret): self
    {
        $config = HTTPClientConfig::NewDefaultConfig();
        $config->APIKey = $apiKey;
        $config->APISecret = $apiSecret;
        $config->BaseURL = Consts::TEST_BASE_URL;
        $config->EnableLogging = true; // 测试环境启用详细日志
        $config->LogRequestBody = Variables::isDebugging();
        $config->LogResponseBody = Variables::isDebugging();
        
        return new self($config);
    }
    
    /**
     * 使用构建器模式创建 SDK 实例
     * 
     * 使用示例：
     *   $sdk = ChannelSDK::newBuilder()
     *       ->apiKey('your-app-key')
     *       ->apiSecret('your-app-secret')
     *       ->baseURL('https://custom-api.example.com')
     *       ->requestTimeout(30)
     *       ->enableLogging(true)
     *       ->build();
     * 
     * @return ConfigBuilder 配置构建器
     */
    public static function newBuilder(): ConfigBuilder
    {
        return ConfigBuilder::NewConfigBuilder();
    }
    
    /**
     * 从环境变量创建 SDK 实例
     * 
     * 需要的环境变量：
     *   - MAIYATIAN_APP_KEY: 应用密钥
     *   - MAIYATIAN_APP_SECRET: 应用密钥
     *   - MAIYATIAN_API_BASE_URL: API基础地址（可选，默认正式环境）
     * 
     * @return ChannelSDK SDK 实例
     * @throws \Exception 环境变量缺失或配置验证失败
     */
    public static function newFromEnv(): self
    {
        $apiKey = getenv('MAIYATIAN_APP_KEY');
        $apiSecret = getenv('MAIYATIAN_APP_SECRET');
        $baseURL = getenv('MAIYATIAN_API_BASE_URL');
        
        if (!$apiKey) {
            throw new \Exception('环境变量 MAIYATIAN_APP_KEY 未设置');
        }
        
        if (!$apiSecret) {
            throw new \Exception('环境变量 MAIYATIAN_APP_SECRET 未设置');
        }
        
        return self::newDefault($apiKey, $apiSecret, $baseURL ?: '');
    }
    
    // ==================== 类型安全的 API 调用方法 ====================
    
    /**
     * 通用类型安全API调用
     * 对应 Go SDK 的 RequestWithApiClient[T any] 泛型函数
     * 
     * @template T
     * @param string $method HTTP方法
     * @param string $path API路径
     * @param mixed $data 请求数据
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应
     */
    public function request(
        string $method,
        string $path,
        $data,
        string $token = '',
        $dataClass = null,
        array $headers = []
    ): ApiResponse {
        return ApiClientUtils::requestWithApiClient(
            $this->client,
            $method,
            $path,
            $data,
            $token,
            $dataClass,
            $headers
        );
    }
    
    /**
     * 执行 GET 请求
     * 
     * @template T
     * @param string $path API路径
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应
     */
    public function get(string $path, string $token = '', $dataClass = null, array $headers = []): ApiResponse
    {
        return ApiClientUtils::get($this->client, $path, $token, $dataClass, $headers);
    }
    
    /**
     * 执行 POST 请求
     * 
     * @template T
     * @param string $path API路径
     * @param mixed $data 请求数据
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应
     */
    public function post(string $path, $data, string $token = '', $dataClass = null, array $headers = []): ApiResponse
    {
        return ApiClientUtils::post($this->client, $path, $data, $token, $dataClass, $headers);
    }
    
    /**
     * 执行带重试机制的 API 请求
     * 
     * @template T
     * @param string $method HTTP方法
     * @param string $path API路径
     * @param mixed $data 请求数据
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $retryConfig 重试配置
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应
     */
    public function requestWithRetry(
        string $method,
        string $path,
        $data,
        string $token = '',
        $dataClass = null,
        array $retryConfig = [],
        array $headers = []
    ): ApiResponse {
        return ApiClientUtils::requestWithRetry(
            $this->client,
            $method,
            $path,
            $data,
            $token,
            $dataClass,
            $retryConfig,
            $headers
        );
    }
    
    /**
     * 批量 API 请求
     * 
     * @param array $requests 请求数组
     * @return ApiResponse[] 响应数组
     */
    public function batchRequest(array $requests): array
    {
        return ApiClientUtils::batchRequest($this->client, $requests);
    }
    
    // ==================== 业务接口方法（渠道相关） ====================
    
    /**
     * 获取授权令牌
     * 对应 Go SDK 中的 access_token.go
     * 
     * @param array $authData 授权数据
     * @return ApiResponse 授权响应
     */
    public function getAccessToken(array $authData): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/access_token',
            $authData
        );
    }
    
    /**
     * 刷新授权令牌
     * 对应 Go SDK 中的 refresh_token.go
     * 
     * @param string $refreshToken 刷新令牌
     * @return ApiResponse 刷新响应
     */
    public function refreshToken(string $refreshToken): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/refresh_token',
            ['refresh_token' => $refreshToken]
        );
    }
    
    /**
     * 获取门店信息
     * 对应 Go SDK 中的 shop_info.go
     * 
     * @param string $token 授权令牌
     * @return ApiResponse 门店信息响应
     */
    public function getShopInfo(string $token): ApiResponse
    {
        return $this->get(
            Consts::CHANNEL_PATH_PREFIX . '/shop_info',
            $token
        );
    }
    
    /**
     * 订单创建通知
     * 对应 Go SDK 中的 order_created.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 订单创建响应
     */
    public function orderCreated(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_created',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单确认通知
     * 对应 Go SDK 中的 order_confirmed.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 订单确认响应
     */
    public function orderConfirmed(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_confirmed',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单完成通知
     * 对应 Go SDK 中的 order_done.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 订单完成响应
     */
    public function orderDone(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_done',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单取消通知
     * 对应 Go SDK 中的 order_canceled.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 订单取消响应
     */
    public function orderCanceled(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_canceled',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单退款申请通知
     * 对应 Go SDK 中的 order_apply_refund.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 退款申请响应
     */
    public function orderApplyRefund(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_apply_refund',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单退款完成通知
     * 对应 Go SDK 中的 order_refunded.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 退款完成响应
     */
    public function orderRefunded(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_refunded',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单修改通知
     * 对应 Go SDK 中的 order_modified.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 订单修改响应
     */
    public function orderModified(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_modified',
            $orderData,
            $token
        );
    }
    
    /**
     * 订单催单通知
     * 对应 Go SDK 中的 order_remind.go
     * 
     * @param array $orderData 订单数据
     * @param string $token 授权令牌
     * @return ApiResponse 催单响应
     */
    public function orderRemind(array $orderData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/order_remind',
            $orderData,
            $token
        );
    }
    
    /**
     * 自配送状态变更通知
     * 对应 Go SDK 中的 self_delivery_change.go
     * 
     * @param array $deliveryData 配送数据
     * @param string $token 授权令牌
     * @return ApiResponse 配送状态变更响应
     */
    public function selfDeliveryChange(array $deliveryData, string $token): ApiResponse
    {
        return $this->post(
            Consts::CHANNEL_PATH_PREFIX . '/self_delivery_change',
            $deliveryData,
            $token
        );
    }
    
    // ==================== 工具方法 ====================
    
    /**
     * 获取 SDK 版本信息
     * 对应 Go SDK 的 vars.VERSION
     * 
     * @return string SDK 版本号
     */
    public function getVersion(): string
    {
        return Variables::getVersion();
    }
    
    /**
     * 检查是否处于调试模式
     * 对应 Go SDK 的 vars.IsDebugging
     * 
     * @return bool 是否调试模式
     */
    public function isDebugging(): bool
    {
        return Variables::isDebugging();
    }
    
    /**
     * 获取客户端配置
     * 
     * @return HTTPClientConfig 客户端配置
     */
    public function getConfig(): HTTPClientConfig
    {
        return $this->config;
    }
    
    /**
     * 获取 HTTP 客户端实例
     * 
     * @return HTTPClientManager HTTP 客户端
     */
    public function getClient(): HTTPClientManager
    {
        return $this->client;
    }
    
    /**
     * 关闭 SDK，释放资源
     */
    public function close(): void
    {
        $this->client->Close();
    }
    
    /**
     * 析构函数，自动释放资源
     */
    public function __destruct()
    {
        $this->close();
    }
}