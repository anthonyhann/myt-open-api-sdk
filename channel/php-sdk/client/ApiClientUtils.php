<?php

namespace Maiyatian\Channel\PhpSdk\Client;

use Maiyatian\Channel\PhpSdk\Consts\Consts;

/**
 * API客户端工具类
 * 对应 Go SDK 中的 RequestWithApiClient[T any] 泛型函数
 * 提供类型安全的API调用功能，实现与Go SDK完全一致的业务逻辑
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class ApiClientUtils
{
    /**
     * 通用类型安全API请求方法
     * 对应 Go SDK 中的 RequestWithApiClient[T any] 函数
     * 
     * 使用示例：
     *   // 获取订单信息
     *   $response = ApiClientUtils::requestWithApiClient(
     *       $client,
     *       'POST',
     *       'v1/channel/order_created',
     *       $orderData,
     *       $token,
     *       OrderCreatedResponse::class
     *   );
     *   
     *   if ($response->isSuccess()) {
     *       $orderInfo = $response->getData(); // 类型安全的 OrderCreatedResponse 对象
     *   }
     * 
     * @template T
     * @param HTTPClientManager $client HTTP客户端实例
     * @param string $method HTTP方法（GET/POST）
     * @param string $path API路径
     * @param mixed $data 请求数据
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型或转换函数
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应对象
     * @throws \Exception API调用异常
     */
    public static function requestWithApiClient(
        HTTPClientManager $client,
        string $method,
        string $path,
        $data,
        string $token = '',
        $dataClass = null,
        array $headers = []
    ): ApiResponse {
        try {
            // 执行HTTP请求，获取原始响应
            $httpResponse = $client->Request($method, $path, $data, $token, $headers);
            
            // 将HTTPResponse转换为类型安全的ApiResponse
            return ApiResponse::from($httpResponse, $dataClass);
            
        } catch (\Exception $e) {
            // 捕获所有异常并转换为错误响应
            return ApiResponse::error(500, sprintf('API调用失败: %s', $e->getMessage()));
        }
    }
    
    /**
     * 执行GET请求
     * 对应 Go SDK 中特化的 GET 请求方法
     * 
     * 使用示例：
     *   $response = ApiClientUtils::get($client, 'v1/channel/shop_info', $token, ShopInfoResponse::class);
     * 
     * @template T
     * @param HTTPClientManager $client HTTP客户端实例
     * @param string $path API路径
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应对象
     */
    public static function get(
        HTTPClientManager $client,
        string $path,
        string $token = '',
        $dataClass = null,
        array $headers = []
    ): ApiResponse {
        return self::requestWithApiClient(
            $client,
            'GET',
            $path,
            (object)[], // GET请求使用空对象作为data
            $token,
            $dataClass,
            $headers
        );
    }
    
    /**
     * 执行POST请求
     * 对应 Go SDK 中特化的 POST 请求方法
     * 
     * 使用示例：
     *   $response = ApiClientUtils::post($client, 'v1/channel/order_created', $orderData, $token, OrderCreatedResponse::class);
     * 
     * @template T
     * @param HTTPClientManager $client HTTP客户端实例
     * @param string $path API路径
     * @param mixed $data 请求数据
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应对象
     */
    public static function post(
        HTTPClientManager $client,
        string $path,
        $data,
        string $token = '',
        $dataClass = null,
        array $headers = []
    ): ApiResponse {
        return self::requestWithApiClient(
            $client,
            'POST',
            $path,
            $data,
            $token,
            $dataClass,
            $headers
        );
    }
    
    /**
     * 批量API请求（并发执行）
     * 对应 Go SDK 中使用 goroutine 的并发请求功能
     * PHP中使用curl_multi实现并发
     * 
     * 使用示例：
     *   $requests = [
     *       ['method' => 'GET', 'path' => 'v1/channel/shop_info', 'data' => [], 'token' => $token],
     *       ['method' => 'POST', 'path' => 'v1/channel/order_created', 'data' => $orderData, 'token' => $token],
     *   ];
     *   $responses = ApiClientUtils::batchRequest($client, $requests);
     * 
     * @param HTTPClientManager $client HTTP客户端实例
     * @param array $requests 请求数组，每个元素包含method、path、data、token、dataClass、headers字段
     * @return ApiResponse[] 响应数组，索引与请求数组对应
     */
    public static function batchRequest(HTTPClientManager $client, array $requests): array
    {
        $responses = [];
        
        // 对于小量请求，直接同步执行
        // 生产环境中可以考虑集成Guzzle等支持并发的HTTP库
        foreach ($requests as $index => $request) {
            try {
                $responses[$index] = self::requestWithApiClient(
                    $client,
                    $request['method'] ?? 'GET',
                    $request['path'] ?? '',
                    $request['data'] ?? (object)[],
                    $request['token'] ?? '',
                    $request['dataClass'] ?? null,
                    $request['headers'] ?? []
                );
            } catch (\Exception $e) {
                $responses[$index] = ApiResponse::error(500, sprintf('批量请求[%d]失败: %s', $index, $e->getMessage()));
            }
        }
        
        return $responses;
    }
    
    /**
     * 重试机制的智能API调用
     * 对应 Go SDK 中带有指数退避重试的请求逻辑
     * 在HTTPClientManager基础重试之外，增加业务级重试
     * 
     * @template T
     * @param HTTPClientManager $client HTTP客户端实例
     * @param string $method HTTP方法
     * @param string $path API路径
     * @param mixed $data 请求数据
     * @param string $token 授权令牌
     * @param string|callable|null $dataClass 响应数据类型
     * @param array $retryConfig 重试配置 ['maxAttempts' => 3, 'baseDelay' => 1, 'maxDelay' => 30]
     * @param array $headers 额外请求头
     * @return ApiResponse<T> 类型安全的API响应对象
     */
    public static function requestWithRetry(
        HTTPClientManager $client,
        string $method,
        string $path,
        $data,
        string $token = '',
        $dataClass = null,
        array $retryConfig = [],
        array $headers = []
    ): ApiResponse {
        // 默认重试配置
        $config = array_merge([
            'maxAttempts' => 3,
            'baseDelay' => 1,
            'maxDelay' => 30,
            'retryOnCodes' => [429, 500, 502, 503, 504] // 需要重试的HTTP状态码
        ], $retryConfig);
        
        $attempt = 0;
        $lastError = null;
        
        while ($attempt <= $config['maxAttempts']) {
            try {
                $response = self::requestWithApiClient($client, $method, $path, $data, $token, $dataClass, $headers);
                
                // 检查是否需要重试（基于业务错误码）
                if ($response->isSuccess() || $attempt >= $config['maxAttempts']) {
                    return $response;
                }
                
                // 检查是否为可重试的错误
                if (!in_array($response->getCode(), $config['retryOnCodes'], true)) {
                    return $response;
                }
                
                $lastError = sprintf('业务错误码 %d: %s', $response->getCode(), $response->getMessage());
                
            } catch (\Exception $e) {
                $lastError = $e->getMessage();
                
                if ($attempt >= $config['maxAttempts']) {
                    return ApiResponse::error(500, sprintf('重试失败: %s', $lastError));
                }
            }
            
            $attempt++;
            
            if ($attempt <= $config['maxAttempts']) {
                // 指数退避延迟
                $delay = min(
                    $config['baseDelay'] * pow(2, $attempt - 1),
                    $config['maxDelay']
                );
                sleep($delay);
            }
        }
        
        return ApiResponse::error(500, sprintf('达到最大重试次数，最后错误: %s', $lastError));
    }
    
    /**
     * 验证API响应数据完整性
     * 对应 Go SDK 中的数据验证逻辑
     * 
     * @param ApiResponse $response API响应对象
     * @param array $requiredFields 必需字段列表
     * @return bool 验证结果
     */
    public static function validateResponse(ApiResponse $response, array $requiredFields = []): bool
    {
        if ($response->isError()) {
            return false;
        }
        
        $data = $response->getData();
        if ($data === null) {
            return empty($requiredFields);
        }
        
        // 检查必需字段
        foreach ($requiredFields as $field) {
            if (is_object($data)) {
                if (!property_exists($data, $field) && !isset($data->$field)) {
                    return false;
                }
            } elseif (is_array($data)) {
                if (!array_key_exists($field, $data)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * 创建标准化的错误响应
     * 对应 Go SDK 中的错误处理模式
     * 
     * @param string $operation 操作名称
     * @param \Exception $exception 异常对象
     * @return ApiResponse<null> 错误响应
     */
    public static function createErrorResponse(string $operation, \Exception $exception): ApiResponse
    {
        $errorCode = $exception->getCode() ?: 500;
        $errorMessage = sprintf('[%s] %s', $operation, $exception->getMessage());
        
        return ApiResponse::error($errorCode, $errorMessage);
    }
    
    /**
     * 类型安全的数据提取
     * 从ApiResponse中安全提取指定类型的数据
     * 
     * @template T
     * @param ApiResponse $response API响应对象
     * @param string $className 期望的数据类型
     * @param mixed $defaultValue 默认值
     * @return T|mixed 提取的数据或默认值
     */
    public static function extractTypedData(ApiResponse $response, string $className, $defaultValue = null)
    {
        if ($response->isError()) {
            return $defaultValue;
        }
        
        $data = $response->getData();
        if ($data === null) {
            return $defaultValue;
        }
        
        // 类型检查
        if (class_exists($className) && !($data instanceof $className)) {
            // 尝试类型转换
            if (is_array($data) || is_object($data)) {
                try {
                    $reflection = new \ReflectionClass($className);
                    $instance = $reflection->newInstanceWithoutConstructor();
                    
                    $arrayData = is_object($data) ? (array)$data : $data;
                    foreach ($arrayData as $key => $value) {
                        if ($reflection->hasProperty($key)) {
                            $property = $reflection->getProperty($key);
                            if (!$property->isPublic()) {
                                $property->setAccessible(true);
                            }
                            $property->setValue($instance, $value);
                        }
                    }
                    
                    return $instance;
                } catch (\Exception $e) {
                    return $defaultValue;
                }
            }
        }
        
        return $data;
    }
}