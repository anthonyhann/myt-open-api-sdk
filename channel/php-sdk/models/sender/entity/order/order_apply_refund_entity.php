<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单申请退款推送请求参数
 * 三方服务推送退款申请给麦芽田
 * 
 * 使用场景：
 *   用户或商家发起退款申请，三方推送退款申请给麦芽田
 *
 * 注意事项：
 *   - 支持部分退款（按商品退款）
 */
class OrderApplyRefundReq
{
    /**
     * 平台方订单唯一 ID（三方平台的订单号）
     * @var string
     */
    public $order_id;

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     * @var string
     */
    public $shop_id;

    /**
     * 退款单 ID
     * @var string
     */
    public $refund_id;

    /**
     * 退款类型：
     * 1-仅退款，2-退货退款
     * @var int
     */
    public $type;

    /**
     * 退款原因描述
     * @var string
     */
    public $reason;

    /**
     * 申请退款总金额（单位：分）
     * @var int
     */
    public $total_price;

    /**
     * 退款商品总数量
     * @var int
     */
    public $count;

    /**
     * 退款商品列表
     * @var array|OrderApplyRefundGoodsInfo[]
     */
    public $goods = [];

    /**
     * 申请时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * OrderApplyRefundReq 构造函数
     */
    public function __construct()
    {
        $this->goods = [];
        $this->update_time = time();
    }

    /**
     * 添加退款商品
     * 
     * @param OrderApplyRefundGoodsInfo $goods 退款商品信息
     */
    public function AddGoods(OrderApplyRefundGoodsInfo $goods)
    {
        $this->goods[] = $goods;
    }
}

/**
 * 退款商品信息
 * 包含退款商品的详细信息和退款金额
 */
class OrderApplyRefundGoodsInfo
{
    /**
     * 平台方商品 ID
     * @var string
     */
    public $goods_id;

    /**
     * 商品名称
     * @var string
     */
    public $goods_name;

    /**
     * 平台方规格 ID
     * @var string
     */
    public $sku_id;

    /**
     * 商品统一编码（UPC 条形码）
     * @var string
     */
    public $upc;

    /**
     * 货架号
     * @var string
     */
    public $shelf_no;

    /**
     * 退款数量
     * @var int
     */
    public $number;

    /**
     * 商品总价（单位：分）
     * @var int
     */
    public $goods_total_fee;

    /**
     * 包装盒数量
     * @var int
     */
    public $package_number;

    /**
     * 包装盒费用（单位：分）
     * @var int
     */
    public $package_fee;

    /**
     * 退款金额（单位：分）
     * @var int
     */
    public $refund_fee;
}
