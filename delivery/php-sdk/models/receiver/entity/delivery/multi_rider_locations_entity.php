<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: multi_rider_locations_entity
 * @Description: 批量获取骑手当前位置实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * RiderLocationReq 单个订单骑手位置查询参数
 */
class RiderLocationReq
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
}

/**
 * RiderLocationResp 骑手位置信息
 */
class RiderLocationResp
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
     * 位置采集时间
     * Unix时间戳（秒）
     * @var int
     */
    public $at_time;

    /**
     * RiderLocationResp constructor.
     */
    public function __construct()
    {
        $this->at_time = time();
    }
}

/**
 * MultiRiderLocationsReq 批量查询骑手位置请求参数
 *
 * command: multi_rider_locations
 * 必接: 是
 * 说明: 批量查询多个订单的骑手当前位置信息
 */
class MultiRiderLocationsReq
{
    /**
     * 订单列表
     * 需要查询骑手位置的订单列表
     * @var array|RiderLocationReq[]
     */
    public $orders;
}

/**
 * MultiRiderLocationsResp 批量查询骑手位置响应参数
 */
class MultiRiderLocationsResp
{
    /**
     * 骑手位置列表
     * 返回每个订单对应的骑手位置信息
     * @var array|RiderLocationResp[]
     */
    public $data;
}
