<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/entity/delivery
 * @ClassName: delivery_change_entity
 * @Description: 麦芽田配送开放平台SDK - 配送状态同步实体
 * 提供同步订单配送状态操作的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\entity\delivery;

/**
 * VehicleInfo 车辆信息实体
 */
class VehicleInfo
{
    /**
     * 车辆名称
     * @var string
     */
    public $vehicle_name;

    /**
     * 车辆颜色
     * @var string
     */
    public $vehicle_color;

    /**
     * 车牌号
     * @var string
     */
    public $vehicle_no;

    /**
     * VehicleInfo constructor.
     *
     * @param string $vehicleName 车辆名称
     * @param string $vehicleColor 车辆颜色
     * @param string $vehicleNo 车牌号
     */
    public function __construct(string $vehicleName = '', string $vehicleColor = '', string $vehicleNo = '')
    {
        $this->vehicle_name = $vehicleName;
        $this->vehicle_color = $vehicleColor;
        $this->vehicle_no = $vehicleNo;
    }
}

/**
 * DeliveryChangeRequest 配送状态同步请求实体
 */
class DeliveryChangeRequest
{
    /**
     * 配送单号
     * @var string
     */
    public $order_no;

    /**
     * 三方订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 三方商户ID、门店ID
     * @var string
     */
    public $shop_id;

    /**
     * 配送状态
     * @var string
     */
    public $status;

    /**
     * 骑手名称
     * @var string
     */
    public $rider_name;

    /**
     * 骑手电话
     * @var string
     */
    public $rider_phone;

    /**
     * 经度
     * @var string
     */
    public $longitude;

    /**
     * 纬度
     * @var string
     */
    public $latitude;

    /**
     * 取货码
     * @var string
     */
    public $pickup_code;

    /**
     * 配送距离（单位：米）
     * @var int
     */
    public $distance;

    /**
     * 配送费（单位：分）
     * @var int
     */
    public $delivery_fee;

    /**
     * 取消类型
     * @var int
     */
    public $cancel_type;

    /**
     * 取消原因
     * @var string
     */
    public $cancel_reason;

    /**
     * 取消订单违约金（单位：分）
     * @var int
     */
    public $cancel_dedit_amount;

    /**
     * 车辆信息
     * @var VehicleInfo
     */
    public $vehicle_info;

    /**
     * 更新时间
     * @var int
     */
    public $update_time;

    /**
     * 是否接驳单
     * @var bool
     */
    public $is_transship;

    /**
     * DeliveryChangeRequest constructor.
     *
     * @param string $orderNo 配送单号
     * @param string $sourceOrderNo 三方订单号
     * @param string $shopId 三方商户ID、门店ID
     * @param string $status 配送状态
     */
    public function __construct(string $orderNo, string $sourceOrderNo, string $shopId, string $status)
    {
        $this->order_no = $orderNo;
        $this->source_order_no = $sourceOrderNo;
        $this->shop_id = $shopId;
        $this->status = $status;
        $this->update_time = time();
        $this->is_transship = false;
    }
}

/**
 * DeliveryChangeResponse 配送状态同步响应实体
 */
class DeliveryChangeResponse
{
    /**
     * 状态码
     * @var int
     */
    public $code;

    /**
     * 响应消息
     * @var string
     */
    public $message;
}
