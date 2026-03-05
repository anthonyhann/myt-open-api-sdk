<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: send_entity
 * @Description: 麦芽田配送开放平台SDK - 下单接口实体
 * 提供配送下单接口的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * SendRequest 配送下单请求实体
 */
class SendRequest
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
     * 期望送达时间（单位：unix 时间戳，精确到秒）
     * @var int
     */
    public $delay_delivery_time;

    /**
     * 期望送达时间（单位：unix 时间）
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
     * 发票类型 1-公司 ；2-个人
     * @var string
     */
    public $exp_type;

    /**
     * 快递预约上门揽件开始时间
     * @var int
     */
    public $pickup_start_time;

    /**
     * 快递预约上门揽件结束时间
     * @var int
     */
    public $pickup_end_time;

    /**
     * 门店ID
     * @var string
     */
    public $custid;

    /**
     * 运费付款方式，0-寄付现结；1-寄付月结；2-收方付；3-第三方付
     * @var int
     */
    public $pay_mode;

    /**
     * 发货人信息
     * @var SendSenderInfo
     */
    public $sender;

    /**
     * 收货人信息
     * @var SendReceiverInfo
     */
    public $receiver;

    /**
     * 订单信息
     * @var SendOrderInfo
     */
    public $order_info;

    /**
     * 订单业务标识
     * @var array<string>
     */
    public $tags;

    /**
     * SendRequest constructor.
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
        $this->sender = new SendSenderInfo();
        $this->receiver = new SendReceiverInfo();
        $this->order_info = new SendOrderInfo();
        $this->tags = [];
    }
}

/**
 * SendSenderInfo 发货人信息实体（下单接口专用）
 */
class SendSenderInfo
{
    /**
     * 门店名称
     * @var string
     */
    public $name;

    /**
     * 获取验证码手机号
     * @var string
     */
    public $phone;

    /**
     * 客户详细地址
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
     * 行政区编码（省）
     * @var string
     */
    public $province_code;

    /**
     * 行政区编码（城市）
     * @var string
     */
    public $city_code;

    /**
     * 行政区编码（县区）
     * @var string
     */
    public $district_code;

    /**
     * SendSenderInfo constructor.
     */
    public function __construct()
    {
    }
}

/**
 * SendReceiverInfo 收货人信息实体（下单接口专用）
 */
class SendReceiverInfo
{
    /**
     * 收货人
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
     * 收货人行政区编码（省）
     * @var string
     */
    public $province_code;

    /**
     * 收货人行政区编码（城市）
     * @var string
     */
    public $city_code;

    /**
     * 收货人行政区编码（县区）
     * @var string
     */
    public $district_code;

    /**
     * SendReceiverInfo constructor.
     */
    public function __construct()
    {
    }
}

/**
 * SendOrderInfo 订单信息实体（下单接口专用）
 */
class SendOrderInfo
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
     * 订单渠道tag
     * @var string
     */
    public $channel_tag;

    /**
     * 门店名称
     * @var string
     */
    public $channel_name;

    /**
     * 渠道订单号
     * @var string
     */
    public $source_no;

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
     * 商品列表
     * @var array<SendGoodsInfo>
     */
    public $goods_list;

    /**
     * SendOrderInfo constructor.
     */
    public function __construct()
    {
        $this->goods_list = [];
    }

    /**
     * 添加商品信息
     *
     * @param SendGoodsInfo $goodsInfo 商品信息
     */
    public function addGoodsInfo(SendGoodsInfo $goodsInfo): void
    {
        $this->goods_list[] = $goodsInfo;
    }

    /**
     * 批量添加商品信息
     *
     * @param array<SendGoodsInfo> $goodsInfos 商品信息列表
     */
    public function addGoodsInfos(array $goodsInfos): void
    {
        foreach ($goodsInfos as $goodsInfo) {
            $this->addGoodsInfo($goodsInfo);
        }
    }
}

/**
 * SendGoodsInfo 商品信息实体（下单接口专用）
 */
class SendGoodsInfo
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
     * SendGoodsInfo constructor.
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
 * SendResponse 配送下单响应实体
 */
class SendResponse
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
     * @var SendResponseData
     */
    public $data;
}

/**
 * SendResponseData 配送下单响应数据实体
 */
class SendResponseData
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
     * 加费(分)
     * @var int
     */
    public $tips;

    /**
     * 计费时间
     * @var int
     */
    public $at_time;

    /**
     * 期望送达时间,如遇没有可传0
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
