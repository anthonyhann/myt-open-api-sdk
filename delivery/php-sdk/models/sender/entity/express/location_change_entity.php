<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/entity/express
 * @ClassName: location_change_entity
 * @Description: 麦芽田配送开放平台SDK - 快递轨迹回传实体
 * 提供快递轨迹回传的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\entity\express;

/**
 * LocationPoint 轨迹点实体
 */
class LocationPoint
{
    /**
     * 描述
     * @var string
     */
    public $description;

    /**
     * 城市
     * @var string
     */
    public $city;

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
     * 状态
     * @var string
     */
    public $status;

    /**
     * 备注
     * @var string
     */
    public $remark;

    /**
     * 更新时间
     * @var int
     */
    public $update_time;

    /**
     * LocationPoint constructor.
     *
     * @param string $description 描述
     * @param string $status 状态
     */
    public function __construct(string $description, string $status = '')
    {
        $this->description = $description;
        $this->status = $status;
        $this->update_time = time();
    }
}

/**
 * LocationChangeRequest 快递轨迹回传请求实体
 */
class LocationChangeRequest
{
    /**
     * 配送单号
     * @var string
     */
    public $order_no;

    /**
     * 麦芽田侧单号（下单接口提供的单号）
     * @var string
     */
    public $source_order_no;

    /**
     * 三方商户ID、门店ID
     * @var string
     */
    public $shop_id;

    /**
     * 标签
     * @var string
     */
    public $tag;

    /**
     * 轨迹列表
     * @var array<LocationPoint>
     */
    public $locations;

    /**
     * LocationChangeRequest constructor.
     *
     * @param string $orderNo 配送单号
     * @param string $sourceOrderNo 麦芽田侧单号
     * @param string $shopId 三方商户ID、门店ID
     */
    public function __construct(string $orderNo, string $sourceOrderNo, string $shopId)
    {
        $this->order_no = $orderNo;
        $this->source_order_no = $sourceOrderNo;
        $this->shop_id = $shopId;
        $this->locations = [];
    }

    /**
     * 添加轨迹点
     *
     * @param LocationPoint $locationPoint 轨迹点
     */
    public function addLocationPoint(LocationPoint $locationPoint): void
    {
        $this->locations[] = $locationPoint;
    }

    /**
     * 批量添加轨迹点
     *
     * @param array<LocationPoint> $locationPoints 轨迹点列表
     */
    public function addLocationPoints(array $locationPoints): void
    {
        foreach ($locationPoints as $locationPoint) {
            $this->addLocationPoint($locationPoint);
        }
    }
}

/**
 * LocationChangeResponse 快递轨迹回传响应实体
 */
class LocationChangeResponse
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
