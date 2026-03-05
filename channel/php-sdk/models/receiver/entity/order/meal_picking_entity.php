<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 取餐码更新请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送取餐码更新请求
 * 
 * 使用场景：
 *   麦芽田通知三方更新订单的取餐码信息
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的取餐码更新接口
 */
class MealPickingReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 取餐码
     */
    public $picking_code;
    
    /**
     * @var int 更新时间（Unix 时间戳，单位：秒）
     */
    public $update_time;
}

/**
 * 取餐码更新响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的取餐码更新结果
 */
class MealPickingResp {
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
