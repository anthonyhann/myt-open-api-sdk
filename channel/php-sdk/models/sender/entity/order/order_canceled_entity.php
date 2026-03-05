<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单取消推送请求参数
 * 三方服务推送订单取消状态给麦芽田
 * 
 * 使用场景：
 *   订单被取消后，三方推送取消状态给麦芽田
 *
 * 注意事项：
 *   - 订单取消后不可恢复
 *   - 需要记录取消原因和原因码
 */
class OrderCanceledReq
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
     * 取消原因描述
     * @var string
     */
    public $reason;

    /**
     * 取消原因码：
     * 1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
     * @var int
     */
    public $reason_code;

    /**
     * 取消时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * OrderCanceledReq 构造函数
     */
    public function __construct()
    {
        $this->update_time = time();
    }
}
