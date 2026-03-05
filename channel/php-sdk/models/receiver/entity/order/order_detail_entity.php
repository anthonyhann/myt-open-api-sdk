<?php

namespace MytChannel\Models\Receiver\Entity\Order;

use MytChannel\Models\Sender\Entity\Order\OrderBaseData;

/**
 * 查询订单详情请求参数（三方接收麦芽田请求）
 * 麦芽田向三方查询订单详情时的请求参数
 * 
 * 使用场景：
 *   麦芽田查询三方订单详细信息
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的查询接口
 */
class OrderDetailReq {
    /**
     * @var string 平台方订单唯一 ID（三方平台的订单号）
     */
    public $order_id;
    
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
}

/**
 * 查询订单详情响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的订单完整信息
 */
class OrderDetailResp extends OrderBaseData {
    // 继承 OrderBaseData 的所有字段
}
