<?php

namespace MytChannel\Models\Receiver\Entity\Order;

/**
 * 骑手位置更新请求参数（三方接收麦芽田请求）
 * 麦芽田向三方发送骑手位置更新请求
 * 
 * 使用场景：
 *   麦芽田通知三方骑手位置发生变化
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的骑手位置更新接口
 */
class RiderLocationReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 骑手 ID
     */
    public $rider_id;
    
    /**
     * @var string 骑手姓名
     */
    public $rider_name;
    
    /**
     * @var string 骑手电话
     */
    public $rider_phone;
    
    /**
     * @var float 骑手纬度
     */
    public $latitude;
    
    /**
     * @var float 骑手经度
     */
    public $longitude;
    
    /**
     * @var int 位置更新时间（Unix 时间戳，单位：秒）
     */
    public $update_time;
}

/**
 * 骑手位置更新响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的骑手位置更新结果
 */
class RiderLocationResp {
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
