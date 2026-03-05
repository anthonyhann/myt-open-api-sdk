<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 订单催单回复请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送订单催单回复请求
 * 
 * 使用场景：
 *   麦芽田通知三方处理催单请求
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的催单回复接口
 */
class OrderRemindReplyReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 催单内容
     */
    public $remind_content;
    
    /**
     * @var int 催单时间（Unix 时间戳，单位：秒）
     */
    public $remind_time;
}

/**
 * 订单催单回复响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的催单回复结果
 */
class OrderRemindReplyResp {
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
