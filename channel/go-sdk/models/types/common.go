// Package types 提供麦芽田开放平台的核心数据模型
// 包含枚举类型、金额处理、时间范围等通用业务类型
package types

import (
	"time"
)

// Money 金额类型（单位：分）
// 使用整数表示金额，避免浮点数精度问题
//
// 示例：
//
//	money := types.Money(12345) // 表示 123.45 元
//	yuan := money.ToYuan()      // 转换为 123.45
type Money int64

// ToYuan 转换为元（浮点数）
//
// 返回：
//
//	float64: 以元为单位的金额（保留 2 位小数）
//
// 示例：
//
//	money := types.Money(12345)
//	yuan := money.ToYuan() // 返回 123.45
func (m Money) ToYuan() float64 {
	return float64(m) / 100.0
}

// FromYuan 从元转换为分
//
// 参数：
//
//	yuan: 以元为单位的金额
//
// 返回：
//
//	Money: 以分为单位的金额
//
// 示例：
//
//	money := types.FromYuan(123.45) // 返回 12345 分
func FromYuan(yuan float64) Money {
	return Money(yuan * 100)
}

// TimeRange 时间范围
// 用于表示订单的送达时间窗口等
type TimeRange struct {
	StartTime int64 `json:"start_time"` // 开始时间（Unix 时间戳，单位：秒）
	EndTime   int64 `json:"end_time"`   // 结束时间（Unix 时间戳，单位：秒）
}

// ToTime 转换为 time.Time 对象
//
// 返回：
//
//	start: 开始时间
//	end: 结束时间
//
// 示例：
//
//	tr := &TimeRange{StartTime: 1657098625, EndTime: 1657099625}
//	start, end := tr.ToTime()
func (tr *TimeRange) ToTime() (start, end time.Time) {
	start = time.Unix(tr.StartTime, 0)
	end = time.Unix(tr.EndTime, 0)
	return start, end
}

// OrderStatus 订单状态枚举
// 表示订单在整个生命周期中的不同状态
type OrderStatus string

// 订单状态枚举常量
const (
	StatusUnprogress OrderStatus = "UNPROGRESS" // 未处理：订单刚创建，尚未被商家处理
	StatusCreated    OrderStatus = "CREATED"    // 待确认：订单等待商家确认接单
	StatusConfirm    OrderStatus = "CONFIRM"    // 已确认：商家已确认接单，准备出餐
	StatusDelivery   OrderStatus = "DELIVERY"   // 待抢单：订单已发配送，等待骑手抢单
	StatusPickup     OrderStatus = "PICKUP"     // 待取货：骑手已抢单，等待到店取货
	StatusDelivering OrderStatus = "DELIVERING" // 配送中：骑手正在配送中
	StatusDone       OrderStatus = "DONE"       // 已完成：订单已完成配送
	StatusCancel     OrderStatus = "CANCEL"     // 已取消：订单已取消
	StatusDelete     OrderStatus = "DELETE"     // 已删除：订单已删除
	StatusExpect     OrderStatus = "EXPECT"     // 配送异常：配送过程中出现异常
)

// Category 订单分类枚举
// 表示店铺的经营品类，用于商品分类和配送匹配
type Category string

// 订单分类枚举常量（麦芽田平台标准分类）
const (
	CategoryXiaochi  Category = "xiaochi"  // 小吃美食：小吃、快餐、地方特色小吃等
	CategoryCanyin   Category = "canyin"   // 正餐快餐：中餐、快餐、便当等
	CategoryShaokao  Category = "shaokao"  // 龙虾烧烤：烧烤、龙虾、海鲜烧烤等
	CategoryDangao   Category = "dangao"   // 烘焙蛋糕：蛋糕、面包、烘焙甜点等
	CategoryTianpin  Category = "tianpin"  // 甜品奶茶：奶茶、甜品、饮料等
	CategoryLiaoli   Category = "liaoli"   // 西餐料理：西餐、日料、韩料等
	CategoryHuoguo   Category = "huoguo"   // 火锅串串：火锅、麻辣烫、串串香等
	CategoryXianhua  Category = "xianhua"  // 浪漫鲜花：鲜花、花束、绿植等
	CategoryShuiguo  Category = "shuiguo"  // 生鲜果蔬：水果、蔬菜、生鲜产品等
	CategoryYinpin   Category = "yinpin"   // 酒水零售：酒水、饮料、零食等
	CategoryChaoshi  Category = "chaoshi"  // 超市百货：日用品、零食、百货等
	CategoryChengren Category = "chengren" // 医药成人：药品、成人用品、保健品等
)

// OriginTag 订单来源渠道标识
// 标识订单的原始来源平台，用于数据统计和业务分析
type OriginTag string

// 订单来源渠道枚举常量
const (
	OriginMeituan   OriginTag = "meituan"   // 美团外卖：美团外卖平台订单
	OriginShangou   OriginTag = "shangou"   // 美团闪购：美团闪购平台订单
	OriginMeituanka OriginTag = "meituanka" // 美团品牌：美团品牌订单
	OriginEleme     OriginTag = "eleme"     // 饿了么：饿了么外卖平台订单
	OriginEbai      OriginTag = "ebai"      // 饿百：饿了么超市订单
	OriginTiktok    OriginTag = "tiktok"    // 抖音外卖：抖音本地生活外卖订单
	OriginDoudian   OriginTag = "doudian"   // 抖店小时达：抖音电商小时达订单
	OriginKuaishou  OriginTag = "kuaishou"  // 快手：快手平台订单
	OriginDaojia    OriginTag = "daojia"    // 京东：京东到家订单
	OriginWeimob    OriginTag = "weimob"    // 微盟：微盟 SaaS 平台订单
	OriginYouzan2   OriginTag = "youzan2"   // 有赞：有赞 SaaS 平台订单
	OriginWeixin    OriginTag = "weixin"    // 微信：微信小程序/公众号订单
	OriginAlipay    OriginTag = "alipay"    // 支付宝：支付宝小程序订单
	OriginApplet    OriginTag = "applet"    // 小程序类：其他小程序平台订单
	OriginOther     OriginTag = "other"     // 其他：其他未分类的订单来源
)

// DeliveryStatus 配送状态枚举
// 表示订单在配送过程中的不同状态
type DeliveryStatus string

// 配送状态枚举常量
const (
	DeliveryStatusPending     DeliveryStatus = "PENDING"      // 待处理：配送订单已创建，等待分配骑手
	DeliveryStatusGrabbed     DeliveryStatus = "GRABBED"      // 已分配骑手：骑手已接单
	DeliveryStatusAtShop      DeliveryStatus = "ATSHOP"       // 已到店：骑手已到达商家
	DeliveryStatusPickup      DeliveryStatus = "PICKUP"       // 待取货：等待商家出餐，骑手取货
	DeliveryStatusDelivering  DeliveryStatus = "DELIVERING"   // 配送中：骑手正在配送途中
	DeliveryStatusDone        DeliveryStatus = "DONE"         // 已完成：配送已完成
	DeliveryStatusCancel      DeliveryStatus = "CANCEL"       // 已取消：配送已取消
	DeliveryStatusExpect      DeliveryStatus = "EXPECT"       // 配送异常：配送过程中出现异常
	DeliveryStatusTransfer    DeliveryStatus = "TRANSFER"     // 骑手转单：骑手将订单转给其他骑手
	DeliveryStatusRiderCancel DeliveryStatus = "RIDER_CANCEL" // 骑手取消：骑手主动取消订单
)

// CancelType 订单取消类型枚举
// 标识订单取消的发起方
type CancelType int

// 取消类型枚举常量
const (
	CancelTypeUser     CancelType = 1 // 用户取消：顾客主动取消订单
	CancelTypeMerchant CancelType = 2 // 商户取消：商家主动取消订单
	CancelTypeService  CancelType = 3 // 客服取消：客服介入取消订单
	CancelTypeSystem   CancelType = 4 // 系统取消：系统自动取消订单（如超时未支付）
	CancelTypeOther    CancelType = 5 // 其他取消：其他原因取消
)

// MealPickingType 订单出餐类型枚举
// 表示商家备货和出餐的状态
type MealPickingType int

// 出餐类型枚举常量
const (
	MealPickingTypeWait  MealPickingType = 0 // 等待拣货：订单等待商家备货
	MealPickingTypeDoing MealPickingType = 1 // 拣货中：商家正在备货、出餐
	MealPickingTypeDone  MealPickingType = 2 // 拣货完成：商家已完成出餐，等待骑手取货
)

// OrderRemindReplyType 订单催单回复类型枚举
// 商家对顾客催单的回复原因
type OrderRemindReplyType int

// 催单回复类型枚举常量
const (
	OrderRemindReplyTypePreparing  OrderRemindReplyType = 1 // 备货中，正在烹饪：商家正在制作餐品
	OrderRemindReplyTypeDelivering OrderRemindReplyType = 2 // 已送出：订单已交给骑手配送
	OrderRemindReplyTypeWeather    OrderRemindReplyType = 3 // 天气原因：因天气原因导致延迟
	OrderRemindReplyTypeShorthand  OrderRemindReplyType = 4 // 人手不足：因人手不足导致延迟
	OrderRemindReplyTypeOther      OrderRemindReplyType = 5 // 其他：其他原因导致延迟
)
