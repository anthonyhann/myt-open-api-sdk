<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Api;

use Maiyatian\Channel\PhpSdk\Client\HTTPClientConfig;
use Maiyatian\Channel\PhpSdk\Client\HTTPClientManager;

/**
 * 渠道发送接口实现类
 * 封装 HTTP 客户端，提供三方服务主动调用麦芽田 API 的便捷方法
 */
class ChannelSender implements IChannelSender
{
    /**
     * HTTP 客户端管理器
     * @var HTTPClientManager
     */
    private $apiClient;

    /**
     * ChannelSender 构造函数
     * 
     * @param HTTPClientConfig $config HTTP 客户端配置
     */
    public function __construct(HTTPClientConfig $config)
    {
        // 合并默认配置（用户配置优先）
        $defaultConfig = HTTPClientConfig::NewDefaultConfig();
        $mergedConfig = $this->mergeConfig($defaultConfig, $config);
        
        // 创建 HTTP 客户端管理器
        $this->apiClient = HTTPClientManager::NewHTTPClientManager($mergedConfig);
    }

    /**
     * 创建渠道发送接口实例
     * 
     * @param HTTPClientConfig $config HTTP 客户端配置
     * @return IChannelSender 渠道发送接口实例
     */
    public static function NewChannelSender(HTTPClientConfig $config)
    {
        return new self($config);
    }

    /**
     * 合并配置
     * 
     * @param HTTPClientConfig $defaultConfig 默认配置
     * @param HTTPClientConfig $userConfig 用户配置
     * @return HTTPClientConfig 合并后的配置
     */
    private function mergeConfig(HTTPClientConfig $defaultConfig, HTTPClientConfig $userConfig)
    {
        $mergedConfig = clone $defaultConfig;
        
        // 获取用户配置的所有属性
        $reflection = new \ReflectionObject($userConfig);
        $properties = $reflection->getProperties();
        
        foreach ($properties as $property) {
            $property->setAccessible(true);
            $value = $property->getValue($userConfig);
            
            // 如果用户配置了该属性，则覆盖默认值
            if ($value !== null) {
                $mergedConfig->{$property->getName()} = $value;
            }
        }
        
        return $mergedConfig;
    }

    /**
     * {@inheritdoc}
     */
    public function OrderCreated($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_created', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderModified($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_modified', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderConfirmed($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_confirmed', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderRemind($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_remind', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderApplyRefund($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_apply_refund', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderRefunded($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_refunded', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderCanceled($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_canceled', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function OrderDone($token, $data)
    {
        return $this->apiClient->Post('v1/channel/order_done', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function SelfDeliveryChange($token, $data)
    {
        return $this->apiClient->Post('v1/channel/self_delivery_change', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function ShopInfo($token, $data)
    {
        return $this->apiClient->Post('v1/channel/shop_info', $data, $token);
    }

    /**
     * {@inheritdoc}
     */
    public function AccessToken($data)
    {
        // 注意：此接口的公共参数 token 传空字符串
        return $this->apiClient->Post('v1/channel/access_token', $data, '');
    }

    /**
     * {@inheritdoc}
     */
    public function RefreshToken($token, $data)
    {
        return $this->apiClient->Post('v1/channel/refresh_token', $data, $token);
    }

    /**
     * 获取 HTTP 客户端管理器
     * 
     * @return HTTPClientManager
     */
    public function GetAPIClient()
    {
        return $this->apiClient;
    }
}
