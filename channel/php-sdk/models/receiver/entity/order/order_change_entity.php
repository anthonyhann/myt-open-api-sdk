<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 订单变更请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送订单变更请求
 * 
 * 使用场景：
 *   麦芽田通知三方订单信息发生变更
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的订单变更接口
 */
class OrderChangeReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 变更类型
     */
    public $change_type;
    
    /**
     * @var array 变更内容
     */
    public $change_content;
    
    /**
     * @var int 变更时间（Unix 时间戳，单位：秒）
     */
    public $change_time;
}

/**
 * 订单变更响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的订单变更结果
 */
class OrderChangeResp {
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
