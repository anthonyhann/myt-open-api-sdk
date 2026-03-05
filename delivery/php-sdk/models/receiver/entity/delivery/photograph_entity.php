<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/26 16:20
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: photograph_entity
 * @Description: 麦芽田配送开放平台SDK - 拍照接口实体
 * 提供拍照接口的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * PhotographRequest 拍照请求实体
 */
class PhotographRequest
{
    /**
     * 订单号
     * @var string
     */
    public $order_no;

    /**
     * 源订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 类型，默认值为0，可选参数
     * @var int
     */
    public $type;

    /**
     * PhotographRequest constructor.
     *
     * @param string $orderNo 订单号
     * @param string $sourceOrderNo 源订单号
     * @param int|null $type 类型，默认值为0，可选参数
     */
    public function __construct(string $orderNo, string $sourceOrderNo, ?int $type = 0)
    {
        $this->order_no = $orderNo;
        $this->source_order_no = $sourceOrderNo;
        $this->type = $type;
    }
}

/**
 * PhotographResponse 拍照响应实体
 */
class PhotographResponse
{
    /**
     * 订单号
     * @var string
     */
    public $order_no;

    /**
     * 源订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 骑手姓名
     * @var string
     */
    public $rider_name;

    /**
     * 骑手电话
     * @var string
     */
    public $rider_phone;

    /**
     * 取货照片列表
     * @var array
     */
    public $pickup_photos;

    /**
     * 配送照片列表
     * @var array
     */
    public $delivery_photos;

    /**
     * PhotographResponse constructor.
     *
     * @param string $orderNo 订单号
     * @param string $sourceOrderNo 源订单号
     * @param string $riderName 骑手姓名
     * @param string $riderPhone 骑手电话
     * @param array $pickupPhotos 取货照片列表
     * @param array $deliveryPhotos 配送照片列表
     */
    public function __construct(
        string $orderNo,
        string $sourceOrderNo,
        string $riderName,
        string $riderPhone,
        array $pickupPhotos = [],
        array $deliveryPhotos = []
    ) {
        $this->order_no = $orderNo;
        $this->source_order_no = $sourceOrderNo;
        $this->rider_name = $riderName;
        $this->rider_phone = $riderPhone;
        $this->pickup_photos = $pickupPhotos;
        $this->delivery_photos = $deliveryPhotos;
    }
}

