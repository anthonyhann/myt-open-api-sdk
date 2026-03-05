<?php

namespace Maiyatian\Delivery\PhpSdk\Client;

use Maiyatian\Delivery\PhpSdk\Consts\Consts;
use Maiyatian\Delivery\PhpSdk\Vars\Variables;

/**
 * API客户端工具类
 * 对应 Go SDK 中的 RequestWithApiClient[T any] 功能
 * 提供类型安全的API调用和响应处理
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class ApiClientUtils
{
    // ==================== 核心API调用方法 ====================
    
    /**
     * 类型安全的API调用方法
     * 对应 Go SDK 中的 RequestWithApiClient[T any] 函数
     * 
     * @template T
     * @param HTTPClient $client HTTP客户端实例
     * @param string $method HTTP方法 (GET, POST, PUT, DELETE)
     * @param string $endpoint API端点路径
     * @param array|null $data 请求数据
     * @param string|callable|null $responseType 响应数据类型或转换函数
     * @param array $headers 额外的HTTP头
     * @return ApiResponse<T>
     * @throws \InvalidArgumentException
     * @throws \Exception
     */
    public static function requestWithApiClient(
        HTTPClient $client,
        string $method,
        string $endpoint,
        ?array $data = null,
        $responseType = null,
        array $headers = []
    ): ApiResponse {
        // 参数验证
        self::validateRequestParams($method, $endpoint);
        
        // 调试日志
        if (Variables::isDebugging()) {
            error_log(sprintf(
                "[%s] API调用开始 - %s %s",
                Variables::getSdkType(),
                $method,
                $endpoint
            ));
        }
        
        try {
            // 准备请求
            $request = self::prepareRequest($method, $endpoint, $data, $headers);
            
            // 执行HTTP请求
            $response = $client->request($request);
            
            // 转换为ApiResponse
            $apiResponse = ApiResponse::from($response, $responseType);
            
            // 调试日志
            if (Variables::isDebugging()) {
                error_log(sprintf(
                    "[%s] API调用完成 - 状态码: %d, 消息: %s",
                    Variables::getSdkType(),
                    $apiResponse->getCode(),
                    $apiResponse->getMessage()
                ));
            }
            
            return $apiResponse;
            
        } catch (\Exception $e) {
            // 错误日志
            if (Variables::isDebugging()) {
                error_log(sprintf(
                    "[%s] API调用失败 - %s: %s",
                    Variables::getSdkType(),
                    get_class($e),
                    $e->getMessage()
                ));
            }
            
            // 返回错误响应
            return ApiResponse::error(500, 'API调用异常: ' . $e->getMessage());
        }
    }
    
    // ==================== 便利方法 ====================
    
    /**
     * GET请求
     * 
     * @template T
     * @param HTTPClient $client HTTP客户端
     * @param string $endpoint API端点
     * @param array $query 查询参数
     * @param string|callable|null $responseType 响应类型
     * @return ApiResponse<T>
     */
    public static function get(
        HTTPClient $client,
        string $endpoint,
        array $query = [],
        $responseType = null
    ): ApiResponse {
        // 构建查询字符串
        if (!empty($query)) {
            $queryString = http_build_query($query);
            $endpoint .= (strpos($endpoint, '?') === false ? '?' : '&') . $queryString;
        }
        
        return self::requestWithApiClient($client, 'GET', $endpoint, null, $responseType);
    }
    
    /**
     * POST请求
     * 
     * @template T
     * @param HTTPClient $client HTTP客户端
     * @param string $endpoint API端点
     * @param array $data 请求数据
     * @param string|callable|null $responseType 响应类型
     * @return ApiResponse<T>
     */
    public static function post(
        HTTPClient $client,
        string $endpoint,
        array $data,
        $responseType = null
    ): ApiResponse {
        return self::requestWithApiClient($client, 'POST', $endpoint, $data, $responseType);
    }
    
    /**
     * PUT请求
     * 
     * @template T
     * @param HTTPClient $client HTTP客户端
     * @param string $endpoint API端点
     * @param array $data 请求数据
     * @param string|callable|null $responseType 响应类型
     * @return ApiResponse<T>
     */
    public static function put(
        HTTPClient $client,
        string $endpoint,
        array $data,
        $responseType = null
    ): ApiResponse {
        return self::requestWithApiClient($client, 'PUT', $endpoint, $data, $responseType);
    }
    
    /**
     * DELETE请求
     * 
     * @template T
     * @param HTTPClient $client HTTP客户端
     * @param string $endpoint API端点
     * @param string|callable|null $responseType 响应类型
     * @return ApiResponse<T>
     */
    public static function delete(
        HTTPClient $client,
        string $endpoint,
        $responseType = null
    ): ApiResponse {
        return self::requestWithApiClient($client, 'DELETE', $endpoint, null, $responseType);
    }
    
    // ==================== 配送业务特定方法 ====================
    
    /**
     * 获取访问令牌
     * 
     * @param HTTPClient $client HTTP客户端
     * @param string $grantType 授权类型
     * @return ApiResponse<array>
     */
    public static function getAccessToken(
        HTTPClient $client,
        string $grantType = Consts::GRANT_TYPE_STORE
    ): ApiResponse {
        return self::post(
            $client,
            sprintf('%s/access_token', Consts::DELIVERY_PATH_PREFIX),
            ['grant_type' => $grantType],
            'array'
        );
    }
    
    /**
     * 刷新访问令牌
     * 
     * @param HTTPClient $client HTTP客户端
     * @param string $refreshToken 刷新令牌
     * @return ApiResponse<array>
     */
    public static function refreshAccessToken(
        HTTPClient $client,
        string $refreshToken
    ): ApiResponse {
        return self::post(
            $client,
            sprintf('%s/refresh_token', Consts::DELIVERY_PATH_PREFIX),
            ['refresh_token' => $refreshToken],
            'array'
        );
    }
    
    /**
     * 查询配送状态
     * 
     * @param HTTPClient $client HTTP客户端
     * @param string $deliveryId 配送单号
     * @return ApiResponse<array>
     */
    public static function getDeliveryStatus(
        HTTPClient $client,
        string $deliveryId
    ): ApiResponse {
        return self::get(
            $client,
            sprintf('%s/delivery/%s/status', Consts::DELIVERY_PATH_PREFIX, $deliveryId),
            [],
            'array'
        );
    }
    
    /**
     * 创建配送订单
     * 
     * @param HTTPClient $client HTTP客户端
     * @param array $orderData 订单数据
     * @return ApiResponse<array>
     */
    public static function createDeliveryOrder(
        HTTPClient $client,
        array $orderData
    ): ApiResponse {
        return self::post(
            $client,
            sprintf('%s/orders', Consts::DELIVERY_PATH_PREFIX),
            $orderData,
            'array'
        );
    }
    
    /**
     * 取消配送订单
     * 
     * @param HTTPClient $client HTTP客户端
     * @param string $orderId 订单ID
     * @param string $reason 取消原因
     * @return ApiResponse<array>
     */
    public static function cancelDeliveryOrder(
        HTTPClient $client,
        string $orderId,
        string $reason
    ): ApiResponse {
        return self::put(
            $client,
            sprintf('%s/orders/%s/cancel', Consts::DELIVERY_PATH_PREFIX, $orderId),
            ['reason' => $reason],
            'array'
        );
    }
    
    /**
     * 获取配送轨迹
     * 
     * @param HTTPClient $client HTTP客户端
     * @param string $deliveryId 配送单号
     * @return ApiResponse<array>
     */
    public static function getDeliveryTrack(
        HTTPClient $client,
        string $deliveryId
    ): ApiResponse {
        return self::get(
            $client,
            sprintf('%s/delivery/%s/track', Consts::DELIVERY_PATH_PREFIX, $deliveryId),
            [],
            'array'
        );
    }
    
    // ==================== 快递业务特定方法 ====================
    
    /**
     * 查询快递服务可用性
     * 
     * @param HTTPClient $client HTTP客户端
     * @param array $location 位置信息
     * @param string $expressType 快递类型
     * @return ApiResponse<array>
     */
    public static function checkExpressAvailability(
        HTTPClient $client,
        array $location,
        string $expressType = Consts::EXPRESS_TYPE_INSTANT
    ): ApiResponse {
        return self::post(
            $client,
            sprintf('%s/availability', Consts::EXPRESS_PATH_PREFIX),
            [
                'location' => $location,
                'express_type' => $expressType
            ],
            'array'
        );
    }
    
    /**
     * 创建快递订单
     * 
     * @param HTTPClient $client HTTP客户端
     * @param array $orderData 订单数据
     * @return ApiResponse<array>
     */
    public static function createExpressOrder(
        HTTPClient $client,
        array $orderData
    ): ApiResponse {
        return self::post(
            $client,
            sprintf('%s/orders', Consts::EXPRESS_PATH_PREFIX),
            $orderData,
            'array'
        );
    }
    
    // ==================== 批量操作方法 ====================
    
    /**
     * 批量API调用
     * 支持并发执行多个API请求
     * 
     * @param HTTPClient $client HTTP客户端
     * @param array $requests 请求列表 [['method' => 'GET', 'endpoint' => '/path', 'data' => [], 'responseType' => 'array'], ...]
     * @return array ApiResponse数组
     */
    public static function batchRequest(
        HTTPClient $client,
        array $requests
    ): array {
        $responses = [];
        
        foreach ($requests as $index => $request) {
            $method = $request['method'] ?? 'GET';
            $endpoint = $request['endpoint'] ?? '';
            $data = $request['data'] ?? null;
            $responseType = $request['responseType'] ?? null;
            
            try {
                $response = self::requestWithApiClient($client, $method, $endpoint, $data, $responseType);
                $responses[$index] = $response;
            } catch (\Exception $e) {
                $responses[$index] = ApiResponse::error(500, '批量请求失败: ' . $e->getMessage());
            }
        }
        
        return $responses;
    }
    
    // ==================== 私有辅助方法 ====================
    
    /**
     * 验证请求参数
     * 
     * @param string $method HTTP方法
     * @param string $endpoint API端点
     * @throws \InvalidArgumentException
     */
    private static function validateRequestParams(string $method, string $endpoint): void
    {
        $allowedMethods = ['GET', 'POST', 'PUT', 'DELETE', 'PATCH'];
        
        if (empty($method)) {
            throw new \InvalidArgumentException('HTTP方法不能为空');
        }
        
        if (!in_array(strtoupper($method), $allowedMethods, true)) {
            throw new \InvalidArgumentException(sprintf(
                '不支持的HTTP方法: %s，支持的方法: %s',
                $method,
                implode(', ', $allowedMethods)
            ));
        }
        
        if (empty($endpoint)) {
            throw new \InvalidArgumentException('API端点不能为空');
        }
        
        if (!is_string($endpoint)) {
            throw new \InvalidArgumentException('API端点必须是字符串');
        }
    }
    
    /**
     * 准备HTTP请求
     * 
     * @param string $method HTTP方法
     * @param string $endpoint API端点
     * @param array|null $data 请求数据
     * @param array $headers 额外头部
     * @return HTTPRequest
     */
    private static function prepareRequest(
        string $method,
        string $endpoint,
        ?array $data,
        array $headers
    ): HTTPRequest {
        // 创建请求对象
        $request = new HTTPRequest();
        $request->Method = strtoupper($method);
        $request->Path = $endpoint;
        
        // 设置请求头
        $defaultHeaders = [
            'User-Agent' => Variables::getUserAgent(),
            'Content-Type' => Consts::CONTENT_TYPE_JSON_UTF8,
            'Accept' => Consts::CONTENT_TYPE_JSON,
            'X-SDK-Version' => Variables::getVersion(),
            'X-SDK-Type' => Variables::getSdkType()
        ];
        
        $request->Headers = array_merge($defaultHeaders, $headers);
        
        // 设置请求体
        if ($data !== null && in_array($request->Method, ['POST', 'PUT', 'PATCH'], true)) {
            $request->Data = json_encode($data, JSON_UNESCAPED_UNICODE);
            if (json_last_error() !== JSON_ERROR_NONE) {
                throw new \InvalidArgumentException('请求数据JSON编码失败: ' . json_last_error_msg());
            }
        }
        
        return $request;
    }
}

/**
 * HTTP请求数据结构
 * 对应 Go SDK 中的请求结构
 */
class HTTPRequest
{
    /**
     * HTTP方法
     * @var string
     */
    public $Method;
    
    /**
     * 请求路径
     * @var string
     */
    public $Path;
    
    /**
     * 请求头
     * @var array
     */
    public $Headers = [];
    
    /**
     * 请求数据
     * @var string|null
     */
    public $Data;
    
    /**
     * 查询参数
     * @var array
     */
    public $Query = [];
}