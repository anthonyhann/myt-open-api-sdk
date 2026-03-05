<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/api
 * @ClassName: DeliverySender
 * @Description: 麦芽田配送开放平台SDK - 配送服务发送端接口和实现
 * Sender代表三方配送服务商主动调用麦芽田开放平台的接口
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\api;

use Maiyatian\Delivery\SDK\client\Config;
use Maiyatian\Delivery\SDK\client\HTTPClientManager;
use Maiyatian\Delivery\SDK\models\sender\entity\auth\AccessTokenRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\auth\RefreshTokenRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\delivery\DeliveryChangeRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\express\LocationChangeRequest;
use Maiyatian\Delivery\SDK\models\types\Constants;

/**
 * IDeliverySender 配送服务发送端接口定义
 *
 * 接口说明:
 *   - Sender接口用于三方配送服务商主动向麦芽田平台推送数据和请求服务
 *   - 包括：配送状态同步、快递轨迹回传、授权相关接口
 *
 * 使用场景:
 *   - 三方配送服务商需要向麦芽田平台推送配送状态时
 *   - 三方配送服务商需要推送快递轨迹信息时
 *   - 三方配送服务商需要获取或刷新访问令牌时
 */
interface IDeliverySender
{
    /**
     * 配送状态同步(必接)
     *
     * 功能: 同步订单配送状态操作
     * 说明: 三方配送服务商主动推送订单配送状态到麦芽田平台
     * command: delivery_change
     * 必接: 是
     *
     * @param string $token 授权令牌
     * @param DeliveryChangeRequest $data 配送状态同步请求数据
     * @return HTTPClientManager\HTTPResponse
     */
    public function deliveryChange(string $token, DeliveryChangeRequest $data);

    /**
     * 快递轨迹回传(必接)
     *
     * 功能: 推送快递轨迹信息
     * 说明: 三方配送服务商主动推送快递轨迹信息到麦芽田平台
     * command: location_change
     * 必接: 是
     *
     * @param string $token 授权令牌
     * @param LocationChangeRequest $data 快递轨迹回传请求数据
     * @return HTTPClientManager\HTTPResponse
     */
    public function locationChange(string $token, LocationChangeRequest $data);

    /**
     * 授权码code换取访问令牌Token(必接)
     *
     * 功能: 用授权码换取访问令牌
     * 说明: 用授权码code换取长时令牌refreshToken以及访问令牌accessToken
     * command: access_token
     * 必接: 是
     *
     * @param AccessTokenRequest $data 授权码获取access_token请求数据
     * @return HTTPClientManager\HTTPResponse
     */
    public function accessToken(AccessTokenRequest $data);

    /**
     * 刷新访问令牌Token(必接)
     *
     * 功能: 刷新过期的访问令牌
     * 说明: 当token过期时，使用refresh_token重新获取新的token
     * command: refresh_token
     * 必接: 是
     *
     * @param string $token 授权令牌
     * @param RefreshTokenRequest $data 刷新访问令牌请求数据
     * @return HTTPClientManager\HTTPResponse
     */
    public function refreshToken(string $token, RefreshTokenRequest $data);
}

/**
 * DeliverySender 配送服务发送端实现
 */
class DeliverySender implements IDeliverySender
{
    /**
     * @var HTTPClientManager
     */
    protected $clientManager;

    /**
     * DeliverySender constructor.
     *
     * @param HTTPClientManager $clientManager
     */
    public function __construct(HTTPClientManager $clientManager)
    {
        $this->clientManager = $clientManager;
    }

    /**
     * 获取HTTP客户端管理器
     *
     * @return HTTPClientManager
     */
    public function getClientManager(): HTTPClientManager
    {
        return $this->clientManager;
    }

    /**
     * 设置HTTP客户端管理器
     *
     * @param HTTPClientManager $clientManager
     */
    public function setClientManager(HTTPClientManager $clientManager): void
    {
        $this->clientManager = $clientManager;
    }

    /**
     * {@inheritdoc}
     */
    public function deliveryChange(string $token, DeliveryChangeRequest $data)
    {
        return $this->clientManager->post(
            Constants::COMMAND_DELIVERY_CHANGE,
            $data,
            $token
        );
    }

    /**
     * {@inheritdoc}
     */
    public function locationChange(string $token, LocationChangeRequest $data)
    {
        return $this->clientManager->post(
            Constants::COMMAND_LOCATION_CHANGE,
            $data,
            $token
        );
    }

    /**
     * {@inheritdoc}
     */
    public function accessToken(AccessTokenRequest $data)
    {
        return $this->clientManager->post(
            Constants::COMMAND_ACCESS_TOKEN,
            $data
        );
    }

    /**
     * {@inheritdoc}
     */
    public function refreshToken(string $token, RefreshTokenRequest $data)
    {
        return $this->clientManager->post(
            Constants::COMMAND_REFRESH_TOKEN,
            $data,
            $token
        );
    }
}

/**
 * DeliverySenderFactory 配送服务发送端工厂类
 */
class DeliverySenderFactory
{
    /**
     * 创建配送服务发送端实例
     *
     * 功能说明:
     *   - 创建用于三方配送服务商主动调用麦芽田平台的客户端实例
     *   - 初始化HTTP客户端管理器
     *
     * @param Config $config HTTP客户端配置
     * @return IDeliverySender
     */
    public static function create(Config $config): IDeliverySender
    {
        $clientManager = new HTTPClientManager($config);
        return new DeliverySender($clientManager);
    }
}
