<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: query_info_entity
 * @Description: 查询配送详情实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * QueryInfoReq 查询配送详情请求参数
 *
 * command: query_info
 * 必接: 是
 * 说明: 麦芽田平台调用三方配送服务查询订单配送详情
 */
class QueryInfoReq
{
    /**
     * 配送单号
     * 三方配送平台的订单号
     * @var string
     */
    public $order_no;

    /**
     * 麦芽田侧订单号
     * 麦芽田平台的订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 手机号码
     * 用于查询的手机号
     * @var string
     */
    public $mobile;

    /**
     * 付款类型
     * 业务类型标识
     * @var int
     */
    public $type;

    /**
     * 配送方编码
     * 配送服务商的编码标识
     * @var string
     */
    public $shipper_code;
}

/**
 * QueryInfoResp 查询配送详情响应参数
 */
class QueryInfoResp
{
    /**
     * 三方运单号
     * 配送服务商的订单号
     * @var string
     */
    public $order_no;

    /**
     * 麦芽田订单号
     * 麦芽田平台的订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 配送状态
     * 可选值: GRABBED, PICKUP, DELIVERING, DONE, CANCEL, RIDER_CANCEL, TRANSFER, EXPECT
     * 详见配送状态枚举
     * @var string
     */
    public $status;

    /**
     * 状态名称
     * 状态的中文描述，如"待接单"
     * @var string
     */
    public $status_name;

    /**
     * 运费
     * 单位：分
     * @var int
     */
    public $pay_amount;

    /**
     * 优惠费用
     * 单位：分，如遇没有可传0
     * @var int
     */
    public $coupon;

    /**
     * 溢价
     * 单位：分，如遇没有可传0
     * @var int
     */
    public $premium;

    /**
     * 加费
     * 单位：分，如遇没有可传0
     * @var int
     */
    public $tips;

    /**
     * 配送距离
     * 单位：米
     * @var int
     */
    public $distance;

    /**
     * 下单时间
     * Unix时间戳（秒）
     * @var int
     */
    public $create_time;

    /**
     * 接单时间
     * Unix时间戳（秒），如遇没有可传0
     * @var int
     */
    public $accept_time;

    /**
     * 取货时间
     * Unix时间戳（秒），如遇没有可传0
     * @var int
     */
    public $fetch_time;

    /**
     * 完成时间
     * Unix时间戳（秒），如遇没有可传0
     * @var int
     */
    public $finish_time;

    /**
     * 取消时间
     * Unix时间戳（秒），如遇没有可传0
     * @var int
     */
    public $cancel_time;

    /**
     * 骑手姓名
     * 配送员姓名
     * @var string
     */
    public $rider_name;

    /**
     * 骑手电话
     * 配送员联系电话
     * @var string
     */
    public $rider_phone;

    /**
     * 经度
     * 当前位置经度，国测局02标准（如高德地图坐标系）
     * @var string
     */
    public $longitude;

    /**
     * 纬度
     * 当前位置纬度，国测局02标准（如高德地图坐标系）
     * @var string
     */
    public $latitude;

    /**
     * 是否转单
     * true: 是转单; false: 不是转单
     * @var bool
     */
    public $is_transship;
}
