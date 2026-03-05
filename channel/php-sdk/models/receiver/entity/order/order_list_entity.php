<?php

namespace MytChannel\Models\Receiver\Entity\Order;

use MytChannel\Models\Sender\Entity\Order\OrderBaseData;

/**
 * 查询订单列表请求参数（三方接收麦芽田请求）
 * 麦芽田向三方查询订单列表时的请求参数
 * 
 * 使用场景：
 *   麦芽田查询三方某个时间段的所有订单
 *
 * 注意事项：
 *   - 时间范围不建议超过 31 天
 */
class OrderListReq {
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var int 查询开始时间（Unix 时间戳，单位：秒）
     */
    public $start_time;
    
    /**
     * @var int 查询结束时间（Unix 时间戳，单位：秒）
     */
    public $end_time;
    
    /**
     * @var int 页码（从 1 开始）
     */
    public $page;
    
    /**
     * @var int 每页数量（建议值：10-100）
     */
    public $page_size;
}

/**
 * 查询订单列表响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的分页订单列表
 */
class OrderListResp {
    /**
     * @var OrderBaseData[] 订单数据列表
     */
    public $data = [];
    
    /**
     * @var int 当前页码
     */
    public $page;
    
    /**
     * @var int 订单总数
     */
    public $total;
    
    /**
     * @var int 总页数
     */
    public $total_page;
    
    /**
     * @var bool 是否最后一页
     */
    public $is_last;
}
