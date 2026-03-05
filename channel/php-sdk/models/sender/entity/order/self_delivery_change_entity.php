<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 自配送状态变更推送请求参数
 * 三方服务推送自配送状态变更给麦芽田
 * 
 * 使用场景：
 *   三方自配送订单状态变化，推送状态给麦芽田
 *
 * 注意事项：
 *   - 仅适用于三方自配送订单
 *   - 需要及时推送配送状态
 */
class SelfDeliveryChangeReq
{
    /**
     * 麦芽田订单号
     * @var string
     */
    public $order_no;

    /**
     * 原始订单号（三方平台的订单号）
     * @var string
     */
    public $source_order_no;

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     * @var string
     */
    public $shop_id;

    /**
     * 配送状态
     * @var string
     */
    public $status;

    /**
     * 配送标签
     * @var string
     */
    public $tag;

    /**
     * 骑手姓名
     * @var string
     */
    public $rider_name;

    /**
     * 骑手电话
     * @var string
     */
    public $rider_phone;

    /**
     * 骑手当前经度（国测局 GCJ-02 标准）
     * @var string
     */
    public $longitude;

    /**
     * 骑手当前纬度（国测局 GCJ-02 标准）
     * @var string
     */
    public $latitude;

    /**
     * 取货码
     * @var string
     */
    public $pickup_code;

    /**
     * 取消类型：
     * 1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
     * @var int
     */
    public $cancel_type;

    /**
     * 取消原因描述
     * @var string
     */
    public $cancel_reason;

    /**
     * 状态更新时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * SelfDeliveryChangeReq 构造函数
     */
    public function __construct()
    {
        $this->update_time = time();
    }
}
