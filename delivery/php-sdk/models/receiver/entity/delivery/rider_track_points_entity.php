<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: rider_track_points_entity
 * @Description: 查询当前订单配送骑手位置实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * RiderTrackPoint 骑手轨迹点信息
 */
class RiderTrackPoint
{
    /**
     * 骑手姓名
     * 配送员姓名
     * @var string
     */
    public $rider_name;

    /**
     * 骑手手机号
     * 配送员联系电话
     * @var string
     */
    public $rider_phone;

    /**
     * 经度
     * 位置经度，国测局02标准（如高德地图坐标系）
     * @var string
     */
    public $longitude;

    /**
     * 纬度
     * 位置纬度，国测局02标准（如高德地图坐标系）
     * @var string
     */
    public $latitude;

    /**
     * 经纬度采集时间
     * Unix时间戳（秒）
     * @var int
     */
    public $at_time;

    /**
     * RiderTrackPoint constructor.
     */
    public function __construct()
    {
        $this->at_time = time();
    }
}

/**
 * RiderTrackPointsReq 查询骑手轨迹点请求参数
 *
 * command: rider_track_points
 * 必接: 是
 * 说明: 查询当前订单配送骑手的位置轨迹信息，支持时间范围查询
 */
class RiderTrackPointsReq
{
    /**
     * 配送单号
     * 三方配送平台的订单号
     * @var string
     */
    public $order_no;

    /**
     * 麦芽田侧运单号
     * 麦芽田平台的订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 查询开始时间
     * Unix时间戳（秒），可选字段
     * @var int
     */
    public $start_time;

    /**
     * 查询结束时间
     * Unix时间戳（秒），可选字段
     * @var int
     */
    public $end_time;
}

/**
 * RiderTrackPointsResp 查询骑手轨迹点响应参数
 */
class RiderTrackPointsResp
{
    /**
     * 麦芽田订单号
     * 麦芽田平台的订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 三方运单号
     * 配送服务商的订单号
     * @var string
     */
    public $order_no;

    /**
     * 骑手经纬度列表
     * 按时间顺序排列的骑手位置轨迹点
     * @var array|RiderTrackPoint[]
     */
    public $rider_track_points;
}
