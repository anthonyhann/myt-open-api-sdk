<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 拒绝退款请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送拒绝退款请求
 * 
 * 使用场景：
 *   麦芽田通知三方拒绝订单退款
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的拒绝退款接口
 */
class OrderRejectRefundReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 退款单号
     */
    public $refund_id;
    
    /**
     * @var string 拒绝退款原因
     */
    public $refuse_reason;
}

/**
 * 拒绝退款响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的拒绝退款结果
 */
class OrderRejectRefundResp {
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
