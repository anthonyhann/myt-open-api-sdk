<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 确认订单请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送订单确认请求
 * 
 * 使用场景：
 *   麦芽田通知三方确认订单信息
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的确认订单接口
 */
class OrderConfirmReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 麦芽田订单 ID
     */
    public $myt_order_id;
}

/**
 * 确认订单响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的订单确认结果
 */
class OrderConfirmResp {
    /**
     * @var bool 是否确认成功
     */
    public $success;
    
    /**
     * @var string 确认结果描述
     */
    public $message;
    
    /**
     * @var string 平台方订单唯一 ID
     */
    public $order_id;
}
