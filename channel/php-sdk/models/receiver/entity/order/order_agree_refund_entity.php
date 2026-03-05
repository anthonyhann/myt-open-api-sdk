<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 同意退款请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送同意退款请求
 * 
 * 使用场景：
 *   麦芽田通知三方同意订单退款
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的同意退款接口
 */
class OrderAgreeRefundReq {
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
     * @var float 退款金额
     */
    public $refund_amount;
}

/**
 * 同意退款响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的同意退款结果
 */
class OrderAgreeRefundResp {
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
