<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单完成推送请求参数
 * 三方服务推送订单完成状态给麦芽田
 * 
 * 使用场景：
 *   订单配送完成后，三方推送完成状态给麦芽田
 *
 * 注意事项：
 *   - 这是必接接口，三方必须实现
 *   - 订单完成后麦芽田进入结算流程
 */
class OrderDoneReq
{
    /**
     * 平台方订单唯一 ID（三方平台的订单号）
     * @var string
     */
    public $order_id;

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     * @var string
     */
    public $shop_id;

    /**
     * 完成时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * OrderDoneReq 构造函数
     */
    public function __construct()
    {
        $this->update_time = time();
    }
}
