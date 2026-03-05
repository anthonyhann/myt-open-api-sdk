<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单催单推送请求参数
 * 三方服务推送催单信息给麦芽田
 * 
 * 使用场景：
 *   用户在三方平台催单，三方推送催单信息给麦芽田
 *
 * 注意事项：
 *   - 三方应及时推送催单信息
 */
class OrderRemindReq
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
     * 催单 ID
     * @var int
     */
    public $remind_id;

    /**
     * 催单原因
     * @var string
     */
    public $reason;

    /**
     * 催单时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * OrderRemindReq 构造函数
     */
    public function __construct()
    {
        $this->update_time = time();
    }
}
