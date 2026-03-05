<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Api;

/**
 * 渠道发送接口定义
 * 定义三方服务主动推送数据到麦芽田开放平台的所有接口
 * 
 * 接口分类：
 * - 订单推送：新订单推送、订单修改推送、订单确认推送、订单完成推送、订单取消推送、催单推送
 * - 退款推送：退款申请推送、退款结果推送
 * - 配送推送：自配送状态变更推送
 * - 授权管理：获取访问令牌、刷新令牌
 * - 门店查询：查询门店信息
 *
 * 必接接口（标注【必接】）：
 * - OrderCreated：新订单推送（三方必须推送新订单给麦芽田）
 * - OrderConfirmed：订单确认推送（三方确认接单后推送给麦芽田）
 * - OrderDone：订单完成推送（三方订单完成后推送给麦芽田）
 * - AccessToken：获取访问令牌（授权流程必需）
 */
interface IChannelSender
{
    /**
     * 新订单推送接口【必接】
     * 三方服务在用户下单并付款成功后，主动推送新订单给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\CreateOrderReq $data 订单详细信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderCreated($token, $data);

    /**
     * 订单修改推送接口
     * 三方服务在用户修改订单后，主动推送订单变更信息给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\UpdateOrderReq $data 订单修改信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderModified($token, $data);

    /**
     * 订单确认推送接口【必接】
     * 三方服务在商户确认订单后，主动推送确认状态给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderConfirmedReq $data 订单确认信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderConfirmed($token, $data);

    /**
     * 订单催单推送接口
     * 三方服务在用户催单后，主动推送催单信息给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderRemindReq $data 催单信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderRemind($token, $data);

    /**
     * 订单申请退款推送接口
     * 三方服务在顾客或商家发起退款申请后，主动推送退款申请给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderApplyRefundReq $data 退款申请信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderApplyRefund($token, $data);

    /**
     * 订单退款结果推送接口
     * 三方服务在处理退款申请后，主动推送退款结果给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderRefundedReq $data 退款结果信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderRefunded($token, $data);

    /**
     * 订单取消推送接口
     * 三方服务在订单被取消后，主动推送取消状态给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderCanceledReq $data 订单取消信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderCanceled($token, $data);

    /**
     * 订单完成推送接口【必接】
     * 三方服务在订单配送完成后，主动推送完成状态给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderDoneReq $data 订单完成信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function OrderDone($token, $data);

    /**
     * 自配送状态变更推送接口
     * 三方服务在使用自配送时，主动推送配送状态变更给麦芽田
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\SelfDeliveryChangeReq $data 自配送状态变更信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function SelfDeliveryChange($token, $data);

    /**
     * 查询麦芽田门店信息接口
     * 三方服务主动查询门店在麦芽田平台的基本信息和营业状态
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Shop\ShopInfoReq $data 门店查询信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function ShopInfo($token, $data);

    /**
     * 获取访问令牌接口【必接】
     * 三方服务使用授权码 code 向麦芽田换取访问令牌
     * 
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Auth\AccessTokenReq $data 授权信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function AccessToken($data);

    /**
     * 刷新访问令牌接口
     * 三方服务使用刷新令牌向麦芽田重新获取新的访问令牌
     * 
     * @param string $token 商户授权令牌
     * @param \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Auth\RefreshTokenReq $data 刷新令牌信息
     * @return \Maiyatian\Channel\PhpSdk\Client\HTTPResponse API响应
     * @throws \Exception 请求失败或业务错误时抛出异常
     */
    public function RefreshToken($token, $data);
}
