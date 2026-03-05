<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order;

/**
 * 订单基础数据结构
 * 包含订单主体信息、顾客信息和更新时间
 * 用于三方推送新订单和订单修改给麦芽田
 */
class OrderBaseData
{
    /**
     * 订单主体信息（商品、费用、配送等详细信息）
     * @var OrderInfo
     */
    public $Order;

    /**
     * 顾客信息（收货人、电话、地址等）
     * @var OrderCustomerInfo
     */
    public $OrderCustomer;

    /**
     * 订单更新时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $UpdateTime;

    /**
     * OrderBaseData 构造函数
     */
    public function __construct()
    {
        $this->Order = new OrderInfo();
        $this->OrderCustomer = new OrderCustomerInfo();
        $this->UpdateTime = time();
    }
}

/**
 * 新订单推送请求参数
 * 三方服务推送新订单给麦芽田时使用
 * 
 * 使用场景：
 *   用户在三方平台下单并支付成功后，三方推送完整订单信息给麦芽田
 *
 * 注意事项：
 *   - 这是必接接口，三方必须实现
 *   - 包含订单的完整信息（商品、费用、顾客、配送等）
 */
class CreateOrderReq extends OrderBaseData
{
    // 继承 OrderBaseData 的所有字段
}

/**
 * 订单修改推送请求参数
 * 三方服务推送订单修改信息给麦芽田时使用
 * 
 * 使用场景：
 *   用户在订单确认前修改订单信息，三方推送修改内容给麦芽田
 *
 * 注意事项：
 *   - 此接口仅订单确认前可调用
 *   - 修改内容仅推送修改部分的数据
 */
class UpdateOrderReq extends OrderBaseData
{
    // 继承 OrderBaseData 的所有字段
}

/**
 * 订单主体信息
 * 包含订单的核心信息：订单号、门店、商品、费用、配送等
 */
class OrderInfo
{
    /**
     * 平台方订单唯一 ID（第三方平台的订单 ID）
     * @var string
     */
    public $OrderId;

    /**
     * 平台方流水号（数字型订单号，最大 99999999）
     * @var int
     */
    public $OrderSn;

    /**
     * 平台方渠道 ID（门店唯一标识）
     * @var string
     */
    public $ShopId;

    /**
     * 门店名称
     * @var string
     */
    public $ShopName;

    /**
     * 原始渠道流水号（如美团、饿了么的原始订单号）
     * @var string
     */
    public $OriginOrderSn;

    /**
     * 原始订单来源渠道标识（如：meituan、eleme、tiktok 等）
     * @var string
     */
    public $OriginTag;

    /**
     * 订单分类，麦芽田枚举值（如：shaokao、canyin 等）
     * @var string
     */
    public $Category;

    /**
     * 是否预约单（true：预约单，false：即时单）
     * @var bool
     */
    public $IsPreOrder = false;

    /**
     * 订单原价总金额（单位：分，折扣前）
     * @var int
     */
    public $TotalPrice;

    /**
     * 实付金额（单位：分，折扣后用户实际支付）
     * @var int
     */
    public $BalancePrice;

    /**
     * 下单时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $CreateTime;

    /**
     * 期望送达时间（开始时间，Unix 时间戳，单位：秒）
     * @var int
     */
    public $DeliveryTime;

    /**
     * 期望送达时间（结束时间，Unix 时间戳，单位：秒）
     * @var int
     */
    public $DeliveryEnd;

    /**
     * 配送类型：0-麦芽田自动适配，1-同城配送，2-全国快递
     * @var int
     */
    public $DeliveryType;

    /**
     * 是否自提（true：顾客到店自提，false：配送到家）
     * @var bool
     */
    public $IsPicker = false;

    /**
     * 自提时间（Unix 时间戳，单位：秒，仅自提单有效）
     * @var int
     */
    public $PickTime;

    /**
     * 用户备注（顾客下单时填写的备注信息）
     * @var string
     */
    public $UserRemark;

    /**
     * 祝福语（如生日蛋糕的祝福语）
     * @var string
     */
    public $Greeting;

    /**
     * 取货码（自提单的取货验证码）
     * @var string
     */
    public $PickupCode;

    /**
     * 订单费用详情（配送费、餐盒费、优惠等）
     * @var OrderFeeInfo
     */
    public $OrderFee;

    /**
     * 订单商品列表（包含商品详情、规格、价格等）
     * @var array|OrderGoodsInfo[]
     */
    public $OrderGoods = [];

    /**
     * 订单相关提醒时间
     * @var TimeReminder
     */
    public $TimeReminders;

    /**
     * OrderInfo 构造函数
     */
    public function __construct()
    {
        $this->OrderFee = new OrderFeeInfo();
        $this->OrderGoods = [];
        $this->TimeReminders = new TimeReminder();
    }

    /**
     * 添加商品到订单
     * 
     * @param OrderGoodsInfo $goods 商品信息
     */
    public function AddGoods(OrderGoodsInfo $goods)
    {
        $this->OrderGoods[] = $goods;
    }
}

/**
 * 订单费用信息
 * 包含订单的各项费用明细：原价、配送费、优惠、佣金等
 */
class OrderFeeInfo
{
    /**
     * 订单原价总金额（单位：分，折扣前的商品总价）
     * @var int
     */
    public $TotalFee;

    /**
     * 配送费（单位：分）
     * @var int
     */
    public $SendFee;

    /**
     * 餐盒费（单位：分）
     * @var int
     */
    public $PackageFee;

    /**
     * 优惠总金额（单位：分，所有优惠的总和）
     * @var int
     */
    public $DiscountFee;

    /**
     * 商户应收金额（单位：分，扣除平台佣金后商户实际收入）
     * @var int
     */
    public $ShopFee;

    /**
     * 用户实付金额（单位：分，用户最终支付的金额）
     * @var int
     */
    public $UserFee;

    /**
     * 付款类型：1-货到付款，2-在线支付
     * @var int
     */
    public $PayType;

    /**
     * 是否需要发票
     * @var bool
     */
    public $NeedInvoice = false;

    /**
     * 发票信息（仅当需要发票时有值）
     * @var InvoiceInfo
     */
    public $Invoice;

    /**
     * 订单活动信息列表（满减、折扣等营销活动）
     * @var array|ActivityInfo[]
     */
    public $Activity = [];

    /**
     * 平台佣金（单位：分）
     * @var int
     */
    public $Commission;

    /**
     * 是否首次购买（用于首单优惠等营销场景）
     * @var bool
     */
    public $IsFirst = false;

    /**
     * 是否收藏用户（用于会员营销）
     * @var bool
     */
    public $IsFavorite = false;

    /**
     * OrderFeeInfo 构造函数
     */
    public function __construct()
    {
        $this->Invoice = new InvoiceInfo();
        $this->Activity = [];
    }

    /**
     * 添加活动到订单
     * 
     * @param ActivityInfo $activity 活动信息
     */
    public function AddActivity(ActivityInfo $activity)
    {
        $this->Activity[] = $activity;
    }
}

/**
 * 发票信息
 * 包含发票抬头、税号、类型等开票所需信息
 */
class InvoiceInfo
{
    /**
     * 发票抬头类型：1-公司（企业），2-个人
     * @var int
     */
    public $Type;

    /**
     * 发票抬头（公司名称或个人姓名）
     * @var string
     */
    public $Title;

    /**
     * 纳税人识别号（企业税号，个人可为空）
     * @var string
     */
    public $TaxerId;

    /**
     * 发票接收邮箱（如：example@qq.com）
     * @var string
     */
    public $Email;

    /**
     * 发票形式：1-纸质发票，2-电子发票
     * @var int
     */
    public $FormType;

    /**
     * 电子发票二维码（电子发票专用）
     * @var string
     */
    public $EQrcode;
}

/**
 * 营销活动信息
 * 记录订单参与的各类营销活动及优惠金额分担
 */
class ActivityInfo
{
    /**
     * 活动类型：1-满减活动，2-折扣活动，3-其他
     * @var int
     */
    public $Type;

    /**
     * 活动说明（如："满30减10"）
     * @var string
     */
    public $Title;

    /**
     * 商户承担优惠金额（单位：分）
     * @var int
     */
    public $Merchant;

    /**
     * 活动减免总金额（单位：分）
     * @var int
     */
    public $Reduce;
}

/**
 * 订单商品信息
 * 包含商品的详细信息：名称、规格、价格、数量、优惠等
 */
class OrderGoodsInfo
{
    /**
     * 平台方商品 ID（第三方平台的商品 ID）
     * @var string
     */
    public $GoodsId;

    /**
     * 商品名称
     * @var string
     */
    public $GoodsName;

    /**
     * 商户自定义商品编码（系统外部编码，可选）
     * @var string
     */
    public $GoodsCode;

    /**
     * 商品图片 URL
     * @var string
     */
    public $Thumb;

    /**
     * 平台方规格 ID（商品 SKU ID）
     * @var string
     */
    public $SkuId;

    /**
     * 商品 SKU 名称（如："中杯"、"加辣"）
     * @var string
     */
    public $SkuName;

    /**
     * 商户自定义 SKU 编码（系统外部编码，可选）
     * @var string
     */
    public $SkuCode;

    /**
     * 商品规格属性列表（如：["6英寸", "原味"]）
     * @var array|string[]
     */
    public $FoodProperty = [];

    /**
     * 商品套餐属性列表（单层结构）
     * @var array|OrderGoodsSkuAttribute[]
     */
    public $SkuAttributes = [];

    /**
     * 商品套餐分组列表（分组结构，如：主食、小料）
     * @var array|OrderGoodsSkuAttributeGroup[]
     */
    public $Commodities = [];

    /**
     * 商品单位（如："份"、"个"、"杯"）
     * @var string
     */
    public $Unit;

    /**
     * 单个商品重量（单位：克）
     * @var int
     */
    public $Weight;

    /**
     * 商品统一编码（UPC 条形码）
     * @var string
     */
    public $Upc;

    /**
     * 货架号（仓库管理用）
     * @var string
     */
    public $ShelfNo;

    /**
     * 购买数量
     * @var int
     */
    public $Number;

    /**
     * 商品单价（单位：分，折扣前）
     * @var int
     */
    public $GoodsPrice;

    /**
     * 商品总价（单位：分，折扣前 = 单价 × 数量）
     * @var int
     */
    public $GoodsTotalFee;

    /**
     * 包装盒数量
     * @var int
     */
    public $PackageNumber;

    /**
     * 包装盒单价（单位：分）
     * @var int
     */
    public $PackagePrice;

    /**
     * 包装盒总价（单位：分）
     * @var int
     */
    public $PackageTotalFee;

    /**
     * 商品折扣单价（单位：分，折扣后的单价）
     * @var int
     */
    public $ReduceFee;

    /**
     * 商品总优惠金额（单位：分，此商品享受的所有优惠）
     * @var int
     */
    public $DiscountFee;

    /**
     * 平台渠道承担的优惠金额（单位：分）
     * @var int
     */
    public $DiscountPlatformFee;

    /**
     * 商户承担的优惠金额（单位：分）
     * @var int
     */
    public $DiscountMerchantFee;

    /**
     * 代理商承担的优惠金额（单位：分）
     * @var int
     */
    public $DiscountAgentFee;

    /**
     * 物流承担的优惠金额（单位：分）
     * @var int
     */
    public $DiscountLogisticsFee;

    /**
     * 商品合计费用（单位：分，折扣后实付）
     * @var int
     */
    public $TotalFee;
}

/**
 * 商品套餐分组
 * 用于表示套餐类商品的分组结构（如：主食、小料、饮料）
 * 
 * 示例：
 *   套餐A（汉堡套餐）
 *     - 主食组：汉堡×1
 *     - 小食组：薯条×1
 *     - 饮料组：可乐×1
 */
class OrderGoodsSkuAttributeGroup
{
    /**
     * 分组名称（如："主食"、"小料"、"饮料"）
     * @var string
     */
    public $GroupName;

    /**
     * 该分组下的商品列表
     * @var array|OrderGoodsSkuAttribute[]
     */
    public $Items = [];

    /**
     * OrderGoodsSkuAttributeGroup 构造函数
     * 
     * @param string $groupName 分组名称
     */
    public function __construct($groupName = '')
    {
        $this->GroupName = $groupName;
        $this->Items = [];
    }

    /**
     * 添加商品到分组
     * 
     * @param OrderGoodsSkuAttribute $item 商品项
     */
    public function AddItem(OrderGoodsSkuAttribute $item)
    {
        $this->Items[] = $item;
    }
}

/**
 * 商品套餐属性
 * 表示套餐中的单个商品项
 */
class OrderGoodsSkuAttribute
{
    /**
     * 属性名称（如："烧鸡200g"、"大杯可乐"）
     * @var string
     */
    public $Name;

    /**
     * 数量（可选，默认为 1）
     * @var int
     */
    public $Number = 1;

    /**
     * 单位（可选，如："份"、"个"）
     * @var string
     */
    public $Unit;
}

/**
 * 订单相关提醒时间
 * 包含订单处理过程中的关键时间节点提醒
 */
class TimeReminder
{
    /**
     * 建议最晚呼叫骑手时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $EstimatedCallRiderTime;

    /**
     * 建议最晚出餐时间（Unix 时间戳，单位：秒）
     * @var int
     */
    public $EstimatedFinishCookingTime;

    /**
     * 最晚开始备餐时间（Unix 时间戳，单位：秒，仅预订单会返回该值）
     * @var int
     */
    public $LatestStartCookingTime;

    /**
     * TimeReminder 构造函数
     */
    public function __construct()
    {
        $this->EstimatedCallRiderTime = 0;
        $this->EstimatedFinishCookingTime = 0;
        $this->LatestStartCookingTime = 0;
    }
}

/**
 * 订单顾客信息
 * 包含收货人信息、联系方式和收货地址
 */
class OrderCustomerInfo
{
    /**
     * 收货人真实姓名（可能经过脱敏处理，如："张*生"）
     * @var string
     */
    public $RealName;

    /**
     * 收货人真实电话（可能是虚拟号，如："15525426477_0067"）
     * @var string
     */
    public $Phone;

    /**
     * 收货人隐私号（脱敏显示，如："157****8884"）
     * @var string
     */
    public $SecretPhone;

    /**
     * 预订人手机号（下单人电话，可能与收货人不同）
     * @var string
     */
    public $OrderPhone;

    /**
     * 备用联系电话（可能是虚拟号）
     * @var string
     */
    public $ReservePhone;

    /**
     * 收货详细地址（如："朔二区-18号楼 (18号楼一单元202)"）
     * @var string
     */
    public $Address;

    /**
     * 收货地址经度（国测局 GCJ-02 标准，如高德地图坐标）
     * @var string
     */
    public $Longitude;

    /**
     * 收货地址纬度（国测局 GCJ-02 标准，如高德地图坐标）
     * @var string
     */
    public $Latitude;
}
