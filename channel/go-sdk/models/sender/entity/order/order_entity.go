// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// OrderBaseData 订单基础数据结构
// 包含订单主体信息、顾客信息和更新时间
// 用于三方推送新订单和订单修改给麦芽田
type OrderBaseData struct {
	Order         OrderInfo         `json:"order"`          // 订单主体信息（商品、费用、配送等详细信息）
	OrderCustomer OrderCustomerInfo `json:"order_customer"` // 顾客信息（收货人、电话、地址等）
	UpdateTime    int64             `json:"update_time"`    // 订单更新时间（Unix 时间戳，单位：秒）
}

// CreateOrderReq 新订单推送请求参数
// 三方服务推送新订单给麦芽田时使用
//
// 使用场景：
//
//	用户在三方平台下单并支付成功后，三方推送完整订单信息给麦芽田
//
// 注意事项：
//   - 这是必接接口，三方必须实现
//   - 包含订单的完整信息（商品、费用、顾客、配送等）
type CreateOrderReq struct {
	OrderBaseData
}

// UpdateOrderReq 订单修改推送请求参数
// 三方服务推送订单修改信息给麦芽田时使用
//
// 使用场景：
//
//	用户在订单确认前修改订单信息，三方推送修改内容给麦芽田
//
// 注意事项：
//   - 此接口仅订单确认前可调用
//   - 修改内容仅推送修改部分的数据
type UpdateOrderReq struct {
	OrderBaseData
}

// OrderInfo 订单主体信息
// 包含订单的核心信息：订单号、门店、商品、费用、配送等
type OrderInfo struct {
	OrderId       string           `json:"order_id" validate:"required"`     // 平台方订单唯一 ID（第三方平台的订单 ID）
	OrderSn       int              `json:"order_sn" validate:"max=99999999"` // 平台方流水号（数字型订单号，最大 99999999）
	ShopId        string           `json:"shop_id" validate:"required"`      // 平台方渠道 ID（门店唯一标识）
	ShopName      string           `json:"shop_name"`                        // 门店名称
	OriginOrderSn string           `json:"origin_order_sn,optional"`         // 原始渠道流水号（如美团、饿了么的原始订单号）
	OriginTag     string           `json:"origin_tag,optional"`              // 原始订单来源渠道标识（如：meituan、eleme、tiktok 等）
	Category      string           `json:"category"`                         // 订单分类，麦芽田枚举值（如：shaokao、canyin 等）
	IsPreOrder    bool             `json:"is_pre_order"`                     // 是否预约单（true：预约单，false：即时单）
	TotalPrice    int              `json:"total_price"`                      // 订单原价总金额（单位：分，折扣前）
	BalancePrice  int              `json:"balance_price"`                    // 实付金额（单位：分，折扣后用户实际支付）
	CreateTime    int64            `json:"create_time"`                      // 下单时间（Unix 时间戳，单位：秒）
	DeliveryTime  int64            `json:"delivery_time"`                    // 期望送达时间（开始时间，Unix 时间戳，单位：秒）
	DeliveryEnd   int64            `json:"delivery_end,optional"`            // 期望送达时间（结束时间，Unix 时间戳，单位：秒）
	DeliveryType  int64            `json:"delivery_type"`                    // 配送类型：0-麦芽田自动适配，1-同城配送，2-全国快递
	IsPicker      bool             `json:"is_picker"`                        // 是否自提（true：顾客到店自提，false：配送到家）
	PickTime      int64            `json:"pick_time,optional"`               // 自提时间（Unix 时间戳，单位：秒，仅自提单有效）
	UserRemark    string           `json:"user_remark"`                      // 用户备注（顾客下单时填写的备注信息）
	Greeting      string           `json:"greeting,optional"`                // 祝福语（如生日蛋糕的祝福语）
	PickupCode    string           `json:"pickup_code,optional"`             // 取货码（自提单的取货验证码）
	OrderFee      OrderFeeInfo     `json:"order_fee"`                        // 订单费用详情（配送费、餐盒费、优惠等）
	OrderGoods    []OrderGoodsInfo `json:"order_goods"`                      // 订单商品列表（包含商品详情、规格、价格等）
	TimeReminders TimeReminder     `json:"time_reminders,optional"`          // 订单相关提醒时间
}

// OrderFeeInfo 订单费用信息
// 包含订单的各项费用明细：原价、配送费、优惠、佣金等
type OrderFeeInfo struct {
	TotalFee    int            `json:"total_fee"`             // 订单原价总金额（单位：分，折扣前的商品总价）
	SendFee     int            `json:"send_fee"`              // 配送费（单位：分）
	PackageFee  int            `json:"package_fee"`           // 餐盒费（单位：分）
	DiscountFee int            `json:"discount_fee"`          // 优惠总金额（单位：分，所有优惠的总和）
	ShopFee     int            `json:"shop_fee"`              // 商户应收金额（单位：分，扣除平台佣金后商户实际收入）
	UserFee     int            `json:"user_fee"`              // 用户实付金额（单位：分，用户最终支付的金额）
	PayType     int            `json:"pay_type"`              // 付款类型：1-货到付款，2-在线支付
	NeedInvoice bool           `json:"need_invoice,optional"` // 是否需要发票
	Invoice     InvoiceInfo    `json:"invoice,optional"`      // 发票信息（仅当需要发票时有值）
	Activity    []ActivityInfo `json:"activity,optional"`     // 订单活动信息列表（满减、折扣等营销活动）
	Commission  int            `json:"commission,optional"`   // 平台佣金（单位：分）
	IsFirst     bool           `json:"is_first,optional"`     // 是否首次购买（用于首单优惠等营销场景）
	IsFavorite  bool           `json:"is_favorite,optional"`  // 是否收藏用户（用于会员营销）
}

// InvoiceInfo 发票信息
// 包含发票抬头、税号、类型等开票所需信息
type InvoiceInfo struct {
	Type     int    `json:"type"`               // 发票抬头类型：1-公司（企业），2-个人
	Title    string `json:"title"`              // 发票抬头（公司名称或个人姓名）
	TaxerId  string `json:"taxer_id"`           // 纳税人识别号（企业税号，个人可为空）
	Email    string `json:"email,optional"`     // 发票接收邮箱（如：example@qq.com）
	FormType int    `json:"form_type,optional"` // 发票形式：1-纸质发票，2-电子发票
	EQrcode  string `json:"e_qrcode,optional"`  // 电子发票二维码（电子发票专用）
}

// ActivityInfo 营销活动信息
// 记录订单参与的各类营销活动及优惠金额分担
type ActivityInfo struct {
	Type     int    `json:"type"`     // 活动类型：1-满减活动，2-折扣活动，3-其他
	Title    string `json:"title"`    // 活动说明（如："满30减10"）
	Merchant int    `json:"merchant"` // 商户承担优惠金额（单位：分）
	Reduce   int    `json:"reduce"`   // 活动减免总金额（单位：分）
}

// OrderGoodsInfo 订单商品信息
// 包含商品的详细信息：名称、规格、价格、数量、优惠等
type OrderGoodsInfo struct {
	GoodsId              string                        `json:"goods_id"`                        // 平台方商品 ID（第三方平台的商品 ID）
	GoodsName            string                        `json:"goods_name"`                      // 商品名称
	GoodsCode            string                        `json:"goods_code,optional"`             // 商户自定义商品编码（系统外部编码，可选）
	Thumb                string                        `json:"thumb"`                           // 商品图片 URL
	SkuId                string                        `json:"sku_id"`                          // 平台方规格 ID（商品 SKU ID）
	SkuName              string                        `json:"sku_name"`                        // 商品 SKU 名称（如："中杯"、"加辣"）
	SkuCode              string                        `json:"sku_code,optional"`               // 商户自定义 SKU 编码（系统外部编码，可选）
	FoodProperty         []string                      `json:"food_property,optional"`          // 商品规格属性列表（如：["6英寸", "原味"]）
	SkuAttributes        []OrderGoodsSkuAttribute      `json:"sku_attributes,optional"`         // 商品套餐属性列表（单层结构）
	Commodities          []OrderGoodsSkuAttributeGroup `json:"commodities,optional"`            // 商品套餐分组列表（分组结构，如：主食、小料）
	Unit                 string                        `json:"unit"`                            // 商品单位（如："份"、"个"、"杯"）
	Weight               int                           `json:"weight"`                          // 单个商品重量（单位：克）
	Upc                  string                        `json:"upc,optional"`                    // 商品统一编码（UPC 条形码）
	ShelfNo              string                        `json:"shelf_no,optional"`               // 货架号（仓库管理用）
	Number               int                           `json:"number"`                          // 购买数量
	GoodsPrice           int                           `json:"goods_price"`                     // 商品单价（单位：分，折扣前）
	GoodsTotalFee        int                           `json:"goods_total_fee"`                 // 商品总价（单位：分，折扣前 = 单价 × 数量）
	PackageNumber        int                           `json:"package_number,optional"`         // 包装盒数量
	PackagePrice         int                           `json:"package_price,optional"`          // 包装盒单价（单位：分）
	PackageTotalFee      int                           `json:"package_total_fee,optional"`      // 包装盒总价（单位：分）
	ReduceFee            int                           `json:"reduce_fee"`                      // 商品折扣单价（单位：分，折扣后的单价）
	DiscountFee          int                           `json:"discount_fee"`                    // 商品总优惠金额（单位：分，此商品享受的所有优惠）
	DiscountPlatformFee  int                           `json:"discount_platform_fee,optional"`  // 平台渠道承担的优惠金额（单位：分）
	DiscountMerchantFee  int                           `json:"discount_merchant_fee,optional"`  // 商户承担的优惠金额（单位：分）
	DiscountAgentFee     int                           `json:"discount_agent_fee,optional"`     // 代理商承担的优惠金额（单位：分）
	DiscountLogisticsFee int                           `json:"discount_logistics_fee,optional"` // 物流承担的优惠金额（单位：分）
	TotalFee             int                           `json:"total_fee"`                       // 商品合计费用（单位：分，折扣后实付）
}

// OrderGoodsSkuAttributeGroup 商品套餐分组
// 用于表示套餐类商品的分组结构（如：主食、小料、饮料）
//
// 示例：
//
//	套餐A（汉堡套餐）
//	  - 主食组：汉堡×1
//	  - 小食组：薯条×1
//	  - 饮料组：可乐×1
type OrderGoodsSkuAttributeGroup struct {
	GroupName string                   `json:"group_name"` // 分组名称（如："主食"、"小料"、"饮料"）
	Items     []OrderGoodsSkuAttribute `json:"items"`      // 该分组下的商品列表
}

// OrderGoodsSkuAttribute 商品套餐属性
// 表示套餐中的单个商品项
type OrderGoodsSkuAttribute struct {
	Name   string `json:"name"`            // 属性名称（如："烧鸡200g"、"大杯可乐"）
	Number int    `json:"number,optional"` // 数量（可选，默认为 1）
	Unit   string `json:"unit,optional"`   // 单位（可选，如："份"、"个"）
}

// TimeReminder 订单相关提醒时间
type TimeReminder struct {
	EstimatedCallRiderTime     int64 `json:"estimated_call_rider_time,optional"`     // 建议最晚呼叫骑手时间
	EstimatedFinishCookingTime int64 `json:"estimated_finish_cooking_time,optional"` // 建议最晚出餐时间
	LatestStartCookingTime     int64 `json:"latest_start_cooking_time,optional"`     // 最晚开始备餐时间,仅预订单会返回该值
}

// OrderCustomerInfo 订单顾客信息
// 包含收货人信息、联系方式和收货地址
type OrderCustomerInfo struct {
	RealName     string `json:"real_name" validate:"required"` // 收货人真实姓名（可能经过脱敏处理，如："张*生"）
	Phone        string `json:"phone" validate:"required"`     // 收货人真实电话（可能是虚拟号，如："15525426477_0067"）
	SecretPhone  string `json:"secret_phone"`                  // 收货人隐私号（脱敏显示，如："157****8884"）
	OrderPhone   string `json:"order_phone,optional"`          // 预订人手机号（下单人电话，可能与收货人不同）
	ReservePhone string `json:"reserve_phone,optional"`        // 备用联系电话（可能是虚拟号）
	Address      string `json:"address"`                       // 收货详细地址（如："朔二区-18号楼 (18号楼一单元202)"）
	Longitude    string `json:"longitude"`                     // 收货地址经度（国测局 GCJ-02 标准，如高德地图坐标）
	Latitude     string `json:"latitude"`                      // 收货地址纬度（国测局 GCJ-02 标准，如高德地图坐标）
}
