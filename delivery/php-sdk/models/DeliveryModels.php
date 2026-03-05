<?php

namespace Maiyatian\Delivery\PhpSdk\Models;

use Maiyatian\Delivery\PhpSdk\Consts\Consts;

/**
 * 配送业务模型集合
 * 对应 Go SDK 中的 models/sender/entity 包
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */

/**
 * 配送订单实体
 * 对应 Go SDK 中的 OrderEntity
 */
class DeliveryOrder
{
    /**
     * 订单ID
     * @var string
     */
    public $orderId;
    
    /**
     * 商户订单号
     * @var string
     */
    public $merchantOrderId;
    
    /**
     * 配送单号
     * @var string|null
     */
    public $deliveryId;
    
    /**
     * 配送状态
     * @var int
     */
    public $status = Consts::DELIVERY_STATUS_PENDING;
    
    /**
     * 订单创建时间
     * @var \DateTime
     */
    public $createdAt;
    
    /**
     * 订单更新时间
     * @var \DateTime
     */
    public $updatedAt;
    
    /**
     * 发货地址
     * @var Address
     */
    public $fromAddress;
    
    /**
     * 收货地址
     * @var Address
     */
    public $toAddress;
    
    /**
     * 商品信息
     * @var OrderItem[]
     */
    public $items = [];
    
    /**
     * 配送费用
     * @var float
     */
    public $deliveryFee = 0.0;
    
    /**
     * 订单备注
     * @var string|null
     */
    public $remark;
    
    /**
     * 期望配送时间
     * @var \DateTime|null
     */
    public $expectedDeliveryTime;
    
    /**
     * 实际配送时间
     * @var \DateTime|null
     */
    public $actualDeliveryTime;
    
    /**
     * 配送员信息
     * @var DeliveryDriver|null
     */
    public $driver;
    
    /**
     * 检查订单是否已完成
     * 
     * @return bool
     */
    public function isCompleted(): bool
    {
        return $this->status === Consts::DELIVERY_STATUS_DELIVERED;
    }
    
    /**
     * 检查订单是否已取消
     * 
     * @return bool
     */
    public function isCanceled(): bool
    {
        return $this->status === Consts::DELIVERY_STATUS_CANCELED;
    }
    
    /**
     * 检查订单是否正在配送
     * 
     * @return bool
     */
    public function isDelivering(): bool
    {
        return $this->status === Consts::DELIVERY_STATUS_DELIVERING;
    }
    
    /**
     * 获取状态描述
     * 
     * @return string
     */
    public function getStatusDescription(): string
    {
        $statusMap = [
            Consts::DELIVERY_STATUS_PENDING => '待分配',
            Consts::DELIVERY_STATUS_DELIVERING => '配送中',
            Consts::DELIVERY_STATUS_DELIVERED => '已送达',
            Consts::DELIVERY_STATUS_CANCELED => '已取消'
        ];
        
        return $statusMap[$this->status] ?? '未知状态';
    }
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'order_id' => $this->orderId,
            'merchant_order_id' => $this->merchantOrderId,
            'delivery_id' => $this->deliveryId,
            'status' => $this->status,
            'status_description' => $this->getStatusDescription(),
            'created_at' => $this->createdAt?->format('Y-m-d H:i:s'),
            'updated_at' => $this->updatedAt?->format('Y-m-d H:i:s'),
            'from_address' => $this->fromAddress?->toArray(),
            'to_address' => $this->toAddress?->toArray(),
            'items' => array_map(fn($item) => $item->toArray(), $this->items),
            'delivery_fee' => $this->deliveryFee,
            'remark' => $this->remark,
            'expected_delivery_time' => $this->expectedDeliveryTime?->format('Y-m-d H:i:s'),
            'actual_delivery_time' => $this->actualDeliveryTime?->format('Y-m-d H:i:s'),
            'driver' => $this->driver?->toArray()
        ];
    }
}

/**
 * 地址信息
 */
class Address
{
    /**
     * 详细地址
     * @var string
     */
    public $detail;
    
    /**
     * 联系人姓名
     * @var string
     */
    public $contactName;
    
    /**
     * 联系电话
     * @var string
     */
    public $contactPhone;
    
    /**
     * 经度
     * @var float
     */
    public $longitude;
    
    /**
     * 纬度
     * @var float
     */
    public $latitude;
    
    /**
     * 省份
     * @var string|null
     */
    public $province;
    
    /**
     * 城市
     * @var string|null
     */
    public $city;
    
    /**
     * 区县
     * @var string|null
     */
    public $district;
    
    /**
     * 构造函数
     * 
     * @param string $detail 详细地址
     * @param string $contactName 联系人姓名
     * @param string $contactPhone 联系电话
     * @param float $longitude 经度
     * @param float $latitude 纬度
     */
    public function __construct(
        string $detail,
        string $contactName,
        string $contactPhone,
        float $longitude,
        float $latitude
    ) {
        $this->detail = $detail;
        $this->contactName = $contactName;
        $this->contactPhone = $contactPhone;
        $this->longitude = $longitude;
        $this->latitude = $latitude;
    }
    
    /**
     * 验证地址信息完整性
     * 
     * @return bool
     */
    public function isValid(): bool
    {
        return !empty($this->detail) 
            && !empty($this->contactName) 
            && !empty($this->contactPhone)
            && $this->longitude !== 0.0
            && $this->latitude !== 0.0;
    }
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'detail' => $this->detail,
            'contact_name' => $this->contactName,
            'contact_phone' => $this->contactPhone,
            'longitude' => $this->longitude,
            'latitude' => $this->latitude,
            'province' => $this->province,
            'city' => $this->city,
            'district' => $this->district
        ];
    }
}

/**
 * 订单商品
 */
class OrderItem
{
    /**
     * 商品名称
     * @var string
     */
    public $name;
    
    /**
     * 商品数量
     * @var int
     */
    public $quantity;
    
    /**
     * 商品单价
     * @var float
     */
    public $price;
    
    /**
     * 商品重量(克)
     * @var float|null
     */
    public $weight;
    
    /**
     * 商品尺寸描述
     * @var string|null
     */
    public $size;
    
    /**
     * 商品备注
     * @var string|null
     */
    public $remark;
    
    /**
     * 构造函数
     * 
     * @param string $name 商品名称
     * @param int $quantity 数量
     * @param float $price 单价
     */
    public function __construct(string $name, int $quantity, float $price)
    {
        $this->name = $name;
        $this->quantity = $quantity;
        $this->price = $price;
    }
    
    /**
     * 获取商品总价
     * 
     * @return float
     */
    public function getTotalPrice(): float
    {
        return $this->price * $this->quantity;
    }
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'name' => $this->name,
            'quantity' => $this->quantity,
            'price' => $this->price,
            'total_price' => $this->getTotalPrice(),
            'weight' => $this->weight,
            'size' => $this->size,
            'remark' => $this->remark
        ];
    }
}

/**
 * 配送员信息
 */
class DeliveryDriver
{
    /**
     * 配送员ID
     * @var string
     */
    public $driverId;
    
    /**
     * 配送员姓名
     * @var string
     */
    public $name;
    
    /**
     * 配送员电话
     * @var string
     */
    public $phone;
    
    /**
     * 当前位置
     * @var Location|null
     */
    public $currentLocation;
    
    /**
     * 配送员状态
     * @var string
     */
    public $status;
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'driver_id' => $this->driverId,
            'name' => $this->name,
            'phone' => $this->phone,
            'current_location' => $this->currentLocation?->toArray(),
            'status' => $this->status
        ];
    }
}

/**
 * 位置信息
 * 对应 Go SDK 中的位置变更数据
 */
class Location
{
    /**
     * 经度
     * @var float
     */
    public $longitude;
    
    /**
     * 纬度
     * @var float
     */
    public $latitude;
    
    /**
     * 位置描述
     * @var string|null
     */
    public $description;
    
    /**
     * 位置更新时间
     * @var \DateTime
     */
    public $timestamp;
    
    /**
     * 位置类型
     * @var string
     */
    public $type = Consts::LOCATION_TYPE_REAL_TIME;
    
    /**
     * 构造函数
     * 
     * @param float $longitude 经度
     * @param float $latitude 纬度
     * @param string $type 位置类型
     */
    public function __construct(float $longitude, float $latitude, string $type = Consts::LOCATION_TYPE_REAL_TIME)
    {
        $this->longitude = $longitude;
        $this->latitude = $latitude;
        $this->type = $type;
        $this->timestamp = new \DateTime();
    }
    
    /**
     * 检查是否是实时位置
     * 
     * @return bool
     */
    public function isRealTime(): bool
    {
        return $this->type === Consts::LOCATION_TYPE_REAL_TIME;
    }
    
    /**
     * 检查是否是轨迹点
     * 
     * @return bool
     */
    public function isTrackPoint(): bool
    {
        return $this->type === Consts::LOCATION_TYPE_TRACK;
    }
    
    /**
     * 检查是否是关键节点
     * 
     * @return bool
     */
    public function isMilestone(): bool
    {
        return $this->type === Consts::LOCATION_TYPE_MILESTONE;
    }
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'longitude' => $this->longitude,
            'latitude' => $this->latitude,
            'description' => $this->description,
            'timestamp' => $this->timestamp->format('Y-m-d H:i:s'),
            'type' => $this->type
        ];
    }
}

/**
 * 访问令牌响应
 * 对应 Go SDK 中的 AccessToken 结构
 */
class AccessTokenResponse
{
    /**
     * 访问令牌
     * @var string
     */
    public $accessToken;
    
    /**
     * 刷新令牌
     * @var string
     */
    public $refreshToken;
    
    /**
     * 令牌类型
     * @var string
     */
    public $tokenType = 'Bearer';
    
    /**
     * 过期时间（秒）
     * @var int
     */
    public $expiresIn;
    
    /**
     * 授权范围
     * @var array
     */
    public $scope = [];
    
    /**
     * 令牌创建时间
     * @var \DateTime
     */
    public $createdAt;
    
    /**
     * 构造函数
     */
    public function __construct()
    {
        $this->createdAt = new \DateTime();
    }
    
    /**
     * 检查令牌是否过期
     * 
     * @return bool
     */
    public function isExpired(): bool
    {
        $expireTime = clone $this->createdAt;
        $expireTime->add(new \DateInterval(sprintf('PT%dS', $this->expiresIn)));
        
        return new \DateTime() > $expireTime;
    }
    
    /**
     * 获取过期时间
     * 
     * @return \DateTime
     */
    public function getExpireTime(): \DateTime
    {
        $expireTime = clone $this->createdAt;
        $expireTime->add(new \DateInterval(sprintf('PT%dS', $this->expiresIn)));
        
        return $expireTime;
    }
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return [
            'access_token' => $this->accessToken,
            'refresh_token' => $this->refreshToken,
            'token_type' => $this->tokenType,
            'expires_in' => $this->expiresIn,
            'scope' => $this->scope,
            'created_at' => $this->createdAt->format('Y-m-d H:i:s'),
            'expire_time' => $this->getExpireTime()->format('Y-m-d H:i:s'),
            'is_expired' => $this->isExpired()
        ];
    }
}

/**
 * 模型工厂类
 * 用于创建和转换模型实例
 */
class ModelFactory
{
    /**
     * 从数组创建DeliveryOrder实例
     * 
     * @param array $data 订单数据
     * @return DeliveryOrder
     */
    public static function createDeliveryOrderFromArray(array $data): DeliveryOrder
    {
        $order = new DeliveryOrder();
        $order->orderId = $data['order_id'] ?? '';
        $order->merchantOrderId = $data['merchant_order_id'] ?? '';
        $order->deliveryId = $data['delivery_id'] ?? null;
        $order->status = $data['status'] ?? Consts::DELIVERY_STATUS_PENDING;
        $order->deliveryFee = $data['delivery_fee'] ?? 0.0;
        $order->remark = $data['remark'] ?? null;
        
        // 时间字段
        if (isset($data['created_at'])) {
            $order->createdAt = new \DateTime($data['created_at']);
        }
        if (isset($data['updated_at'])) {
            $order->updatedAt = new \DateTime($data['updated_at']);
        }
        if (isset($data['expected_delivery_time'])) {
            $order->expectedDeliveryTime = new \DateTime($data['expected_delivery_time']);
        }
        if (isset($data['actual_delivery_time'])) {
            $order->actualDeliveryTime = new \DateTime($data['actual_delivery_time']);
        }
        
        // 地址信息
        if (isset($data['from_address'])) {
            $order->fromAddress = self::createAddressFromArray($data['from_address']);
        }
        if (isset($data['to_address'])) {
            $order->toAddress = self::createAddressFromArray($data['to_address']);
        }
        
        // 商品列表
        if (isset($data['items']) && is_array($data['items'])) {
            $order->items = array_map([self::class, 'createOrderItemFromArray'], $data['items']);
        }
        
        // 配送员信息
        if (isset($data['driver'])) {
            $order->driver = self::createDeliveryDriverFromArray($data['driver']);
        }
        
        return $order;
    }
    
    /**
     * 从数组创建Address实例
     * 
     * @param array $data 地址数据
     * @return Address
     */
    public static function createAddressFromArray(array $data): Address
    {
        $address = new Address(
            $data['detail'] ?? '',
            $data['contact_name'] ?? '',
            $data['contact_phone'] ?? '',
            $data['longitude'] ?? 0.0,
            $data['latitude'] ?? 0.0
        );
        
        $address->province = $data['province'] ?? null;
        $address->city = $data['city'] ?? null;
        $address->district = $data['district'] ?? null;
        
        return $address;
    }
    
    /**
     * 从数组创建OrderItem实例
     * 
     * @param array $data 商品数据
     * @return OrderItem
     */
    public static function createOrderItemFromArray(array $data): OrderItem
    {
        $item = new OrderItem(
            $data['name'] ?? '',
            $data['quantity'] ?? 1,
            $data['price'] ?? 0.0
        );
        
        $item->weight = $data['weight'] ?? null;
        $item->size = $data['size'] ?? null;
        $item->remark = $data['remark'] ?? null;
        
        return $item;
    }
    
    /**
     * 从数组创建DeliveryDriver实例
     * 
     * @param array $data 配送员数据
     * @return DeliveryDriver
     */
    public static function createDeliveryDriverFromArray(array $data): DeliveryDriver
    {
        $driver = new DeliveryDriver();
        $driver->driverId = $data['driver_id'] ?? '';
        $driver->name = $data['name'] ?? '';
        $driver->phone = $data['phone'] ?? '';
        $driver->status = $data['status'] ?? '';
        
        if (isset($data['current_location'])) {
            $driver->currentLocation = self::createLocationFromArray($data['current_location']);
        }
        
        return $driver;
    }
    
    /**
     * 从数组创建Location实例
     * 
     * @param array $data 位置数据
     * @return Location
     */
    public static function createLocationFromArray(array $data): Location
    {
        $location = new Location(
            $data['longitude'] ?? 0.0,
            $data['latitude'] ?? 0.0,
            $data['type'] ?? Consts::LOCATION_TYPE_REAL_TIME
        );
        
        $location->description = $data['description'] ?? null;
        
        if (isset($data['timestamp'])) {
            $location->timestamp = new \DateTime($data['timestamp']);
        }
        
        return $location;
    }
    
    /**
     * 从数组创建AccessTokenResponse实例
     * 
     * @param array $data 令牌数据
     * @return AccessTokenResponse
     */
    public static function createAccessTokenResponseFromArray(array $data): AccessTokenResponse
    {
        $token = new AccessTokenResponse();
        $token->accessToken = $data['access_token'] ?? '';
        $token->refreshToken = $data['refresh_token'] ?? '';
        $token->tokenType = $data['token_type'] ?? 'Bearer';
        $token->expiresIn = $data['expires_in'] ?? 3600;
        $token->scope = $data['scope'] ?? [];
        
        if (isset($data['created_at'])) {
            $token->createdAt = new \DateTime($data['created_at']);
        }
        
        return $token;
    }
}