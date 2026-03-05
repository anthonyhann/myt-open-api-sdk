<?php

namespace Maiyatian\Delivery\PhpSdk\Client;

use Maiyatian\Delivery\PhpSdk\Consts\Consts;

/**
 * 类型安全的API响应包装器
 * 对应 Go SDK 中的 ApiResponse[T any] 泛型
 * PHP中使用模板注释和运行时类型检查实现类似功能
 * 
 * @template T
 * @author SDK Generator  
 * @version 1.0.0
 * @since 1.0.0
 */
class ApiResponse
{
    /**
     * 业务状态码：200 表示成功，其他表示失败
     * @var int
     */
    private $code;
    
    /**
     * 返回消息：成功时为 "ok"，失败时为错误描述
     * @var string
     */
    private $message;
    
    /**
     * 业务数据，已解析为具体的PHP对象或数组
     * @var T|null
     */
    private $data;
    
    /**
     * 原始响应对象
     * @var HTTPResponse
     */
    private $rawResponse;
    
    /**
     * 响应数据的类型信息
     * @var string|null
     */
    private $dataType;
    
    // ==================== 构造函数 ====================
    
    /**
     * ApiResponse 构造函数
     * 
     * @param int $code 状态码
     * @param string $message 响应消息
     * @param T|null $data 业务数据
     * @param HTTPResponse|null $rawResponse 原始响应
     */
    public function __construct(int $code, string $message, $data = null, ?HTTPResponse $rawResponse = null)
    {
        $this->code = $code;
        $this->message = $message;
        $this->data = $data;
        $this->rawResponse = $rawResponse;
        $this->dataType = is_object($data) ? get_class($data) : gettype($data);
    }
    
    // ==================== 静态工厂方法 ====================
    
    /**
     * 创建成功响应
     * 对应 Go SDK 的成功响应创建
     * 
     * @template U
     * @param U $data 响应数据
     * @param string $message 成功消息
     * @return ApiResponse<U>
     */
    public static function success($data, string $message = 'ok'): self
    {
        return new self(Consts::SUCCESS_CODE, $message, $data);
    }
    
    /**
     * 创建错误响应
     * 对应 Go SDK 的错误响应创建
     * 
     * @param int $code 错误码
     * @param string $message 错误消息
     * @return ApiResponse<null>
     */
    public static function error(int $code, string $message): self
    {
        return new self($code, $message, null);
    }
    
    /**
     * 从HTTPResponse转换创建ApiResponse
     * 对应 Go SDK 中 RequestWithApiClient 的转换逻辑
     * 
     * @template U
     * @param HTTPResponse $response HTTP响应对象
     * @param string|callable|null $dataClass 数据类型或转换函数
     * @return ApiResponse<U>
     * @throws \InvalidArgumentException
     */
    public static function from(HTTPResponse $response, $dataClass = null): self
    {
        $data = null;
        
        // 解析响应数据
        if (!empty($response->Data)) {
            try {
                if ($dataClass === null) {
                    // 不指定类型，直接解析为数组
                    $data = json_decode($response->Data, true);
                    if (json_last_error() !== JSON_ERROR_NONE) {
                        throw new \InvalidArgumentException('JSON解析失败: ' . json_last_error_msg());
                    }
                } elseif (is_string($dataClass)) {
                    // 指定具体类，尝试实例化
                    $arrayData = json_decode($response->Data, true);
                    if (json_last_error() !== JSON_ERROR_NONE) {
                        throw new \InvalidArgumentException('JSON解析失败: ' . json_last_error_msg());
                    }
                    
                    if (class_exists($dataClass)) {
                        $data = self::instantiateFromArray($dataClass, $arrayData);
                    } else {
                        $data = $arrayData;
                    }
                } elseif (is_callable($dataClass)) {
                    // 使用自定义转换函数
                    $arrayData = json_decode($response->Data, true);
                    if (json_last_error() !== JSON_ERROR_NONE) {
                        throw new \InvalidArgumentException('JSON解析失败: ' . json_last_error_msg());
                    }
                    $data = $dataClass($arrayData);
                } else {
                    throw new \InvalidArgumentException('无效的数据类型参数');
                }
            } catch (\Exception $e) {
                throw new \InvalidArgumentException('数据转换失败: ' . $e->getMessage(), 0, $e);
            }
        }
        
        return new self($response->Code, $response->Message, $data, $response);
    }
    
    // ==================== Getter 方法 ====================
    
    /**
     * 获取状态码
     * 
     * @return int
     */
    public function getCode(): int
    {
        return $this->code;
    }
    
    /**
     * 获取响应消息
     * 
     * @return string
     */
    public function getMessage(): string
    {
        return $this->message;
    }
    
    /**
     * 获取响应数据
     * 
     * @return T|null
     */
    public function getData()
    {
        return $this->data;
    }
    
    /**
     * 获取原始响应对象
     * 
     * @return HTTPResponse|null
     */
    public function getRawResponse(): ?HTTPResponse
    {
        return $this->rawResponse;
    }
    
    /**
     * 获取数据类型信息
     * 
     * @return string|null
     */
    public function getDataType(): ?string
    {
        return $this->dataType;
    }
    
    // ==================== 状态检查方法 ====================
    
    /**
     * 检查是否是成功响应
     * 对应 Go SDK 的成功状态检查
     * 
     * @return bool
     */
    public function isSuccess(): bool
    {
        return $this->code === Consts::SUCCESS_CODE;
    }
    
    /**
     * 检查是否是错误响应
     * 
     * @return bool
     */
    public function isError(): bool
    {
        return !$this->isSuccess();
    }
    
    /**
     * 检查是否有数据
     * 
     * @return bool
     */
    public function hasData(): bool
    {
        return $this->isSuccess() && $this->data !== null;
    }
    
    // ==================== 函数式编程方法（类似Go泛型） ====================
    
    /**
     * 转换数据类型（map操作）
     * 对应 Go SDK 泛型的类型转换功能
     * 
     * @template U
     * @param callable $mapper 转换函数
     * @return ApiResponse<U>
     */
    public function map(callable $mapper): self
    {
        if ($this->isError() || $this->data === null) {
            return new self($this->code, $this->message, null, $this->rawResponse);
        }
        
        try {
            $mappedData = $mapper($this->data);
            return new self($this->code, $this->message, $mappedData, $this->rawResponse);
        } catch (\Exception $e) {
            return self::error(500, '数据转换失败: ' . $e->getMessage());
        }
    }
    
    /**
     * 过滤响应（filter操作）
     * 
     * @param callable $predicate 过滤条件
     * @return ApiResponse<T>
     */
    public function filter(callable $predicate): self
    {
        if ($this->isError()) {
            return $this;
        }
        
        try {
            if ($predicate($this)) {
                return $this;
            } else {
                return self::error(400, '响应不满足过滤条件');
            }
        } catch (\Exception $e) {
            return self::error(500, '过滤条件检查失败: ' . $e->getMessage());
        }
    }
    
    /**
     * 获取数据或返回默认值
     * 对应 Go SDK 的安全数据获取
     * 
     * @param T $defaultValue 默认值
     * @return T
     */
    public function getDataOrDefault($defaultValue)
    {
        if ($this->isSuccess() && $this->data !== null) {
            return $this->data;
        }
        return $defaultValue;
    }
    
    /**
     * 获取数据或抛出异常
     * 
     * @return T
     * @throws ApiException
     */
    public function getDataOrThrow()
    {
        if ($this->isSuccess() && $this->data !== null) {
            return $this->data;
        }
        
        throw new ApiException(
            sprintf('API错误 [%d]: %s', $this->code, $this->message),
            $this->code
        );
    }
    
    // ==================== 配送业务特定方法 ====================
    
    /**
     * 检查是否是配送业务成功响应
     * 
     * @return bool
     */
    public function isDeliverySuccess(): bool
    {
        return $this->isSuccess() && $this->data !== null;
    }
    
    /**
     * 获取配送状态
     * 专门用于配送业务的状态获取
     * 
     * @return int|null
     */
    public function getDeliveryStatus(): ?int
    {
        if ($this->isError() || !is_array($this->data)) {
            return null;
        }
        
        return $this->data['status'] ?? $this->data['delivery_status'] ?? null;
    }
    
    /**
     * 检查是否是位置变更响应
     * 
     * @return bool
     */
    public function isLocationChange(): bool
    {
        if ($this->isError() || !is_array($this->data)) {
            return false;
        }
        
        return isset($this->data['latitude']) && isset($this->data['longitude']);
    }
    
    // ==================== 私有辅助方法 ====================
    
    /**
     * 从数组实例化对象
     * 
     * @param string $className 类名
     * @param array $data 数据数组
     * @return object
     */
    private static function instantiateFromArray(string $className, array $data)
    {
        $reflection = new \ReflectionClass($className);
        $instance = $reflection->newInstanceWithoutConstructor();
        
        foreach ($data as $key => $value) {
            $property = null;
            
            // 尝试直接访问属性
            if ($reflection->hasProperty($key)) {
                $property = $reflection->getProperty($key);
                if (!$property->isPublic()) {
                    $property->setAccessible(true);
                }
                $property->setValue($instance, $value);
            } elseif (method_exists($instance, 'set' . ucfirst($key))) {
                // 尝试setter方法
                $instance->{'set' . ucfirst($key)}($value);
            } elseif (property_exists($instance, $key)) {
                // 动态属性
                $instance->$key = $value;
            }
        }
        
        return $instance;
    }
    
    // ==================== 魔术方法 ====================
    
    /**
     * 字符串表示
     * 
     * @return string
     */
    public function __toString(): string
    {
        return sprintf(
            'ApiResponse{code=%d, message="%s", hasData=%s, dataType=%s}',
            $this->code,
            $this->message,
            $this->hasData() ? 'true' : 'false',
            $this->dataType ?? 'null'
        );
    }
    
    /**
     * 序列化为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'code' => $this->code,
            'message' => $this->message,
            'data' => $this->data,
            'hasData' => $this->hasData(),
            'isSuccess' => $this->isSuccess(),
            'isDeliverySuccess' => $this->isDeliverySuccess(),
            'deliveryStatus' => $this->getDeliveryStatus(),
            'isLocationChange' => $this->isLocationChange()
        ];
    }
}

/**
 * API异常类
 */
class ApiException extends \Exception
{
    public function __construct(string $message, int $code = 0, ?\Throwable $previous = null)
    {
        parent::__construct($message, $code, $previous);
    }
}