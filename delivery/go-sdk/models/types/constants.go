/**
* @Author Hanqiang
* @Date 2025/12/9
* @PackageName: types
* @ClassName: constants
* @Description: 麦芽田配送开放平台 - 枚举常量定义
* @Version 1.0
 */

package types

// ==================== 配送状态枚举 ====================

// DeliveryStatus 配送状态
type DeliveryStatus string

const (
	// DeliveryStatusPending 待接单
	DeliveryStatusPending DeliveryStatus = "PENDING"

	// DeliveryStatusGrabbed 已分配骑手
	DeliveryStatusGrabbed DeliveryStatus = "GRABBED"
	// DeliveryStatusAtShop 已到店
	DeliveryStatusAtShop DeliveryStatus = "ATSHOP"
	// DeliveryStatusCollected 已取货
	DeliveryStatusCollected DeliveryStatus = "COLLECTED"
	// DeliveryStatusPickup 待取货/取货中
	DeliveryStatusPickup DeliveryStatus = "PICKUP"
	// DeliveryStatusDelivering 配送中
	DeliveryStatusDelivering DeliveryStatus = "DELIVERING"
	// DeliveryStatusDone 已完成
	DeliveryStatusDone DeliveryStatus = "DONE"
	// DeliveryStatusCancel 已取消
	DeliveryStatusCancel DeliveryStatus = "CANCEL"
	// DeliveryStatusBack 已退回
	DeliveryStatusBack DeliveryStatus = "BACK"
	// DeliveryStatusTransfer 骑手转单
	DeliveryStatusTransfer DeliveryStatus = "TRANSFER"
	// DeliveryStatusRiderCancel 骑手取消
	DeliveryStatusRiderCancel DeliveryStatus = "RIDER_CANCEL"
	// DeliveryStatusCreateFailed 配送异常
	DeliveryStatusCreateFailed DeliveryStatus = "CREATE_FAILED"
)

// String 返回状态的字符串表示
func (s DeliveryStatus) String() string {
	return string(s)
}

// GetStatusName 获取状态的中文名称
func (s DeliveryStatus) GetStatusName() string {
	statusNames := map[DeliveryStatus]string{
		DeliveryStatusPending:      "待接单",
		DeliveryStatusGrabbed:      "已分配骑手",
		DeliveryStatusAtShop:       "已到店",
		DeliveryStatusCollected:    "已取货",
		DeliveryStatusPickup:       "待取货",
		DeliveryStatusDelivering:   "配送中",
		DeliveryStatusDone:         "已完成",
		DeliveryStatusCancel:       "已取消",
		DeliveryStatusBack:         "已退回",
		DeliveryStatusTransfer:     "骑手转单",
		DeliveryStatusRiderCancel:  "骑手取消",
		DeliveryStatusCreateFailed: "配送异常",
	}
	return statusNames[s]
}

// ==================== 取消类型枚举 ====================

// CancelType 订单取消类型
type CancelType int

const (
	// CancelTypeUser 用户取消
	CancelTypeUser CancelType = 1
	// CancelTypeMerchant 商户取消
	CancelTypeMerchant CancelType = 2
	// CancelTypeCustomerService 客服取消
	CancelTypeCustomerService CancelType = 3
	// CancelTypeSystem 系统取消
	CancelTypeSystem CancelType = 4
	// CancelTypeOther 其他原因取消
	CancelTypeOther CancelType = 5
)

// GetCancelTypeName 获取取消类型的中文名称
func GetCancelTypeName(t CancelType) string {
	cancelTypeNames := map[CancelType]string{
		CancelTypeUser:            "用户取消",
		CancelTypeMerchant:        "商户取消",
		CancelTypeCustomerService: "客服取消",
		CancelTypeSystem:          "系统取消",
		CancelTypeOther:           "其他原因",
	}
	return cancelTypeNames[t]
}

// ==================== 订单来源枚举 ====================

// OrderSource 订单来源标识
type OrderSource string

const (
	// OrderSourceMeituan 美团外卖
	OrderSourceMeituan OrderSource = "meituan"
	// OrderSourceEleme 饿了么
	OrderSourceEleme OrderSource = "eleme"
	// OrderSourceDouyin 抖音
	OrderSourceDouyin OrderSource = "douyin"
	// OrderSourceKuaishou 快手
	OrderSourceKuaishou OrderSource = "kuaishou"
	// OrderSourceWechat 微信小程序
	OrderSourceWechat OrderSource = "wechat"
	// OrderSourceAlipay 支付宝小程序
	OrderSourceAlipay OrderSource = "alipay"
	// OrderSourceJD 京东
	OrderSourceJD OrderSource = "jd"
	// OrderSourceOther 其他渠道
	OrderSourceOther OrderSource = "other"
)

// String 返回订单来源的字符串表示
func (s OrderSource) String() string {
	return string(s)
}

// ==================== 店铺分类枚举 ====================

// ShopCategory 店铺分类(麦芽田枚举)
type ShopCategory string

const (
	// ShopCategoryXiaochi 小吃快餐
	ShopCategoryXiaochi ShopCategory = "xiaochi"
	// ShopCategoryDangao 蛋糕烘焙
	ShopCategoryDangao ShopCategory = "dangao"
	// ShopCategoryShuiguo 水果生鲜
	ShopCategoryShuiguo ShopCategory = "shuiguo"
	// ShopCategoryNaicha 奶茶饮品
	ShopCategoryNaicha ShopCategory = "naicha"
	// ShopCategoryHuoguo 火锅烧烤
	ShopCategoryHuoguo ShopCategory = "huoguo"
	// ShopCategoryChaocai 炒菜简餐
	ShopCategoryChaocai ShopCategory = "chaocai"
	// ShopCategoryRihanliaoli 日韩料理
	ShopCategoryRihanliaoli ShopCategory = "rihanliaoli"
	// ShopCategoryXican 西餐披萨
	ShopCategoryXican ShopCategory = "xican"
	// ShopCategoryOther 其他分类
	ShopCategoryOther ShopCategory = "other"
)

// String 返回店铺分类的字符串表示
func (s ShopCategory) String() string {
	return string(s)
}

// ==================== 服务包枚举 ====================

// ServicePackage 麦芽田对外开放服务包
type ServicePackage string

const (
	// ServicePackageBase 经济配送
	ServicePackageBase ServicePackage = "base"
	// ServicePackageDirect 专人直送
	ServicePackageDirect ServicePackage = "direct"
)

// String 返回服务包的字符串表示
func (s ServicePackage) String() string {
	return string(s)
}

// GetServicePackageName 获取服务包的中文名称
func (s ServicePackage) GetServicePackageName() string {
	servicePackageNames := map[ServicePackage]string{
		ServicePackageBase:   "经济配送",
		ServicePackageDirect: "专人直送",
	}
	return servicePackageNames[s]
}

// ==================== 快递类型枚举 ====================

// ExpressType 快递类型
type ExpressType string

const (
	// ExpressTypeNormal 非地图类快递
	ExpressTypeNormal ExpressType = "1"
	// ExpressTypeMap 地图类快递
	ExpressTypeMap ExpressType = "2"
)

// String 返回快递类型的字符串表示
func (s ExpressType) String() string {
	return string(s)
}

// ==================== 运费付款方式枚举 ====================

// PayMode 运费付款方式
type PayMode int

const (
	// PayModeSenderCash 寄付现结
	PayModeSenderCash PayMode = 0
	// PayModeSenderMonthly 寄付月结
	PayModeSenderMonthly PayMode = 1
	// PayModeReceiver 收方付
	PayModeReceiver PayMode = 2
	// PayModeThirdParty 第三方付
	PayModeThirdParty PayMode = 3
)

// GetPayModeName 获取付款方式的中文名称
func GetPayModeName(m PayMode) string {
	payModeNames := map[PayMode]string{
		PayModeSenderCash:    "寄付现结",
		PayModeSenderMonthly: "寄付月结",
		PayModeReceiver:      "收方付",
		PayModeThirdParty:    "第三方付",
	}
	return payModeNames[m]
}

// ==================== 地图类型枚举 ====================

// MapType 坐标类型
type MapType string

const (
	// MapTypeGaode 高德地图坐标系（国测局02标准）
	MapTypeGaode MapType = "1"
	// MapTypeBaidu 百度地图坐标系
	MapTypeBaidu MapType = "2"
)

// String 返回地图类型的字符串表示
func (m MapType) String() string {
	return string(m)
}

// GetMapTypeName 获取地图类型的中文名称
func (m MapType) GetMapTypeName() string {
	mapTypeNames := map[MapType]string{
		MapTypeGaode: "高德地图",
		MapTypeBaidu: "百度地图",
	}
	return mapTypeNames[m]
}

// ==================== 授权级别枚举 ====================

// GrantType 授权级别
type GrantType string

const (
	// GrantTypeStore 门店级别授权
	GrantTypeStore GrantType = "1"
	// GrantTypeMerchant 商户级别授权
	GrantTypeMerchant GrantType = "2"
)

// String 返回授权级别的字符串表示
func (g GrantType) String() string {
	return string(g)
}

// GetGrantTypeName 获取授权级别的中文名称
func (g GrantType) GetGrantTypeName() string {
	grantTypeNames := map[GrantType]string{
		GrantTypeStore:    "门店授权",
		GrantTypeMerchant: "商户授权",
	}
	return grantTypeNames[g]
}

// ==================== 照片类型枚举 ====================

// PhotoType 骑手照片类型
type PhotoType int

const (
	// PhotoTypeAll 所有照片
	PhotoTypeAll PhotoType = 0
	// PhotoTypePickup 取货照片
	PhotoTypePickup PhotoType = 1
	// PhotoTypeComplete 完成照片
	PhotoTypeComplete PhotoType = 2
)

// GetPhotoTypeName 获取照片类型的中文名称
func GetPhotoTypeName(t PhotoType) string {
	photoTypeNames := map[PhotoType]string{
		PhotoTypeAll:      "所有照片",
		PhotoTypePickup:   "取货照片",
		PhotoTypeComplete: "完成照片",
	}
	return photoTypeNames[t]
}

// ==================== 错误码常量 ====================

const (
	// CodeSuccess 成功
	CodeSuccess = 200
	// CodeUnauthorized 接口未授权
	CodeUnauthorized = 401
	// CodeNotFound 资源不存在
	CodeNotFound = 404
	// CodeParamError 参数错误
	CodeParamError = 422
	// CodeInternalError 服务器内部错误
	CodeInternalError = 500
	// CodeServiceUnavailable 服务不可用
	CodeServiceUnavailable = 503
)

// GetErrorMessage 根据错误码获取错误描述
func GetErrorMessage(code int) string {
	errorMessages := map[int]string{
		CodeSuccess:            "成功",
		CodeUnauthorized:       "接口未授权",
		CodeNotFound:           "资源不存在",
		CodeParamError:         "参数错误",
		CodeInternalError:      "服务器内部错误",
		CodeServiceUnavailable: "服务不可用",
	}
	if msg, ok := errorMessages[code]; ok {
		return msg
	}
	return "未知错误"
}
