<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: valuating_entity
 * @Description: 麦芽田配送开放平台SDK - 订单计费接口实体
 * 提供查询订单运费的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * GoodsInfo 商品信息实体
 */
class GoodsInfo
{
    /**
     * 商品名称
     * @var string
     */
    public $name;

    /**
     * 商品数量
     * @var int
     */
    public $number;

    /**
     * 商品价格
     * @var int
     */
    public $price;

    /**
     * GoodsInfo constructor.
     *
     * @param string $name 商品名称
     * @param int $number 商品数量
     * @param int $price 商品价格
     */
    public function __construct(string $name, int $number, int $price)
    {
        $this->name = $name;
        $this->number = $number;
        $this->price = $price;
    }
}

/**
 * OrderInfo 订单信息实体
 */
class OrderInfo
{
    /**
     * 订单流水号
     * @var int
     */
    public $sn;

    /**
     * 完整订单流水号
     * @var string
     */
    public $full_sn;

    /**
     * 订单来源标识
     * @var string
     */
    public $source;

    /**
     * 渠道名称
     * @var string
     */
    public $channel_name;

    /**
     * 店铺分类
     * @var string
     */
    public $category;

    /**
     * 商品重量（单位：千克）
     * @var int
     */
    public $weight;

    /**
     * 合计费用（折扣后）（单位：分）
     * @var int
     */
    public $total_fee;

    /**
     * 实付金额(分)
     * @var int
     */
    public $paid_fee;

    /**
     * 是否上门取件,0 不上门取件 1 上门取件
     * @var int
     */
    public $is_from_door;

    /**
     * 是否送件上门,0 不送件上门 1 送件上门
     * @var int
     */
    public $is_to_door;

    /**
     * 商品列表
     * @var array<GoodsInfo>
     */
    public $goods_list;

    /**
     * OrderInfo constructor.
     */
    public function __construct()
    {
        $this->goods_list = [];
    }

    /**
     * 添加商品信息
     *
     * @param GoodsInfo $goodsInfo 商品信息
     */
    public function addGoodsInfo(GoodsInfo $goodsInfo): void
    {
        $this->goods_list[] = $goodsInfo;
    }

    /**
     * 批量添加商品信息
     *
     * @param array<GoodsInfo> $goodsInfos 商品信息列表
     */
    public function addGoodsInfos(array $goodsInfos): void
    {
        foreach ($goodsInfos as $goodsInfo) {
            $this->addGoodsInfo($goodsInfo);
        }
    }
}

/**
 * SenderInfo 发货人信息实体
 */
class SenderInfo
{
    /**
     * 发货门店名称
     * @var string
     */
    public $name;

    /**
     * 发货门店电话
     * @var string
     */
    public $phone;

    /**
     * 发货门店地址
     * @var string
     */
    public $address;

    /**
     * 发货门店地址详情
     * @var string
     */
    public $address_detail;

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
     * 标准城市行政编码
     * @var string
     */
    public $city_code;

    /**
     * SenderInfo constructor.
     */
    public function __construct()
    {
        // 默认值设置
        $this->is_from_door = 1;
    }
}

/**
 * ReceiverInfo 收货人信息实体
 */
class ReceiverInfo
{
    /**
     * 收货人姓名
     * @var string
     */
    public $name;

    /**
     * 收货人电话
     * @var string
     */
    public $phone;

    /**
     * 收货人地址
     * @var string
     */
    public $address;

    /**
     * 收货人地址详情
     * @var string
     */
    public $address_detail;

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
     * 标准城市行政编码
     * @var string
     */
    public $city_code;

    /**
     * ReceiverInfo constructor.
     */
    public function __construct()
    {
        // 默认值设置
        $this->is_to_door = 1;
    }
}

/**
 * ValuatingRequest 订单计费请求实体
 */
class ValuatingRequest
{
    /**
     * 麦芽田方门店ID
     * @var string
     */
    public $shop_id;

    /**
     * 是否预约单
     * @var bool
     */
    public $is_pre_order;

    /**
     * 是否接驳单
     * @var bool
     */
    public $is_transship;

    /**
     * 预约单期望送达时间（单位：unix 时间戳，精确到秒）
     * @var int
     */
    public $delay_delivery_time;

    /**
     * 期望送达时间（单位：unix 时间戳）
     * @var int
     */
    public $expect_finish_time;

    /**
     * 小费
     * @var int
     */
    public $tips;

    /**
     * 订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 用户备注
     * @var string
     */
    public $remark;

    /**
     * 发货人信息
     * @var SenderInfo
     */
    public $sender;

    /**
     * 收货人信息
     * @var ReceiverInfo
     */
    public $receiver;

    /**
     * 订单信息
     * @var OrderInfo
     */
    public $order_info;

    /**
     * 订单业务标识
     * @var array<string>
     */
    public $tags;

    /**
     * ValuatingRequest constructor.
     *
     * @param string $shopId 麦芽田方门店ID
     * @param string $sourceOrderNo 订单号
     */
    public function __construct(string $shopId, string $sourceOrderNo)
    {
        $this->shop_id = $shopId;
        $this->source_order_no = $sourceOrderNo;
        $this->is_pre_order = false;
        $this->is_transship = false;
        $this->sender = new SenderInfo();
        $this->receiver = new ReceiverInfo();
        $this->order_info = new OrderInfo();
        $this->tags = [];
    }
}

/**
 * ValuatingResponse 订单计费响应实体
 */
class ValuatingResponse
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

    /**
     * 业务数据
     * @var ValuatingData
     */
    public $data;
}

/**
 * ValuatingData 订单计费业务数据实体
 */
class ValuatingData
{
    /**
     * 运费（分）
     * @var int
     */
    public $pay_amount;

    /**
     * 优惠费用(分)
     * @var int
     */
    public $coupon;

    /**
     * 配送距离（米）
     * @var int
     */
    public $distance;

    /**
     * 当前溢价(分)
     * @var int
     */
    public $premium;

    /**
     * 重量（克）
     * @var int
     */
    public $weight;

    /**
     * 超重（克）
     * @var int
     */
    public $overweight;

    /**
     * 小费(分)
     * @var int
     */
    public $tips;

    /**
     * 计费时间
     * @var int
     */
    public $at_time;

    /**
     * 期望送达时间
     * @var int
     */
    public $expect_time;

    /**
     * 三方运单号
     * @var string
     */
    public $order_no;

    /**
     * 麦芽田订单号
     * @var string
     */
    public $source_order_no;

    /**
     * 计价三方唯一计费号(锁定计费)
     * @var string
     */
    public $billing_no;

    /**
     * 服务包
     * @var string
     */
    public $service_pkg;

    /**
     * 服务标签
     * @var string
     */
    public $service_tag;
}
