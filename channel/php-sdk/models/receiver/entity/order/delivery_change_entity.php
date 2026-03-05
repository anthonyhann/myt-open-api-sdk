<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 配送状态变更请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送配送状态变更请求
 * 
 * 使用场景：
 *   麦芽田通知三方订单配送状态发生变化
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的配送状态变更接口
 */
class DeliveryChangeReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 配送状态
     */
    public $delivery_status;
    
    /**
     * @var string 配送状态描述
     */
    public $status_desc;
    
    /**
     * @var int 状态变更时间（Unix 时间戳，单位：秒）
     */
    public $change_time;
}

/**
 * 配送状态变更响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的配送状态变更结果
 */
class DeliveryChangeResp {
    /**
     * @var bool 是否处理成功
     */
    public $success;
    
    /**
     * @var string 处理结果描述
     */
    public $message;
    
    /**
     * @var string 平台方订单唯一 ID
     */
    public $order_id;
}
