<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单确认推送请求参数
 * 三方服务推送订单确认状态给麦芽田
 * 
 * 使用场景：
 *   三方商户确认接单后，推送确认状态给麦芽田
 *
 * 注意事项：
 *   - 这是必接接口，三方必须实现
 *   - 确认后麦芽田订单状态变为"已确认"
 */
class OrderConfirmedReq
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
     * 确认时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * OrderConfirmedReq 构造函数
     */
    public function __construct()
    {
        $this->update_time = time();
    }
}
