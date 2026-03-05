<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单退款结果推送请求参数
 * 三方服务推送退款处理结果给麦芽田
 * 
 * 使用场景：
 *   三方处理退款申请后，推送退款结果给麦芽田
 *
 * 注意事项：
 *   - Status 字段标识退款状态
 *   - 退款成功后，麦芽田会处理退款流程
 */
class OrderRefundedReq
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
     * 退款状态：
     * 1-退款成功，2-退款失败，3-退款处理中
     * @var int
     */
    public $status;

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
     * 实际退款总金额（单位：分）
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
     * @var array|OrderRefundedGoodsInfo[]
     */
    public $goods = [];

    /**
     * 退款处理时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $update_time;

    /**
     * OrderRefundedReq 构造函数
     */
    public function __construct()
    {
        $this->goods = [];
        $this->update_time = time();
    }

    /**
     * 添加已退款商品
     * 
     * @param OrderRefundedGoodsInfo $goods 已退款商品信息
     */
    public function AddGoods(OrderRefundedGoodsInfo $goods)
    {
        $this->goods[] = $goods;
    }
}

/**
 * 已退款商品信息
 * 包含已退款商品的详细信息和退款金额
 */
class OrderRefundedGoodsInfo
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
     * 实际退款金额（单位：分）
     * @var int
     */
    public $refund_fee;
}
