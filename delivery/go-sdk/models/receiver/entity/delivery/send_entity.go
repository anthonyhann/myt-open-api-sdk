/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: send_entity
 * @Description: 配送下单实体定义
 * @Version 1.0
 */

package entity

// SendReq 配送下单请求参数
// command: send
// 必接: 是
// 说明: 麦芽田平台主动调用三方配送服务创建配送订单
type SendReq struct {
	// ShopId 麦芽田方门店ID
	// 麦芽田平台的门店唯一标识
	ShopId string `json:"shop_id"`

	// IsPreOrder 是否预约单
	// true: 预约单; false: 即时单
	IsPreOrder bool `json:"is_pre_order"`

	// IsTransship 是否接驳单
	// true: 是接驳单; false: 不是接驳单
	IsTransship bool `json:"is_transship,optional"`

	// DelayDeliveryTime 期望送达时间
	// Unix时间戳（秒）
	DelayDeliveryTime int64 `json:"delay_delivery_time"`

	// ExpectStartTime 期望开始时间
	// Unix时间戳（秒），可选
	ExpectStartTime int64 `json:"expect_start_time,optional"`

	// ExpectFinishTime 期望完成时间
	// Unix时间戳（秒）
	ExpectFinishTime int64 `json:"expect_finish_time"`

	// Tips 小费
	// 单位：分
	Tips int64 `json:"tips"`

	// SourceOrderNo 订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no,optional"`

	// Remark 用户备注
	// 订单备注信息
	Remark string `json:"remark"`

	// Sender 发货人信息
	// 发货门店的详细信息
	Sender SenderInfo `json:"sender"`

	// Receiver 收货人信息
	// 收货客户的详细信息
	Receiver ReceiverInfo `json:"receiver"`

	// OrderInfo 订单信息
	// 订单商品等详细信息
	OrderInfo OrderInfo `json:"order_info"`

	// ExpType 快递类型
	// 1: 非地图类快递; 2: 地图类快递
	ExpType string `json:"exp_type,optional"`

	// PickupStartTime 快递预约上门揽件开始时间
	// Unix时间戳（秒）
	PickupStartTime int64 `json:"pickup_start_time,optional"`

	// PickupEndTime 快递预约上门揽件结束时间
	// Unix时间戳（秒）
	PickupEndTime int64 `json:"pickup_end_time,optional"`

	// Custid 门店ID
	// 客户门店标识
	Custid string `json:"custid,optional"`

	// PayMode 运费付款方式
	// 0: 寄付现结; 1: 寄付月结; 2: 收方付; 3: 第三方付
	PayMode int `json:"pay_mode,optional"`

	// Tags 订单业务标识
	// []string类型，用于标记订单的特殊属性
	Tags []string `json:"tags,optional"`

	// BillingNo 计价单号
	// 从计费接口返回的计价唯一标识，用于锁定计费
	BillingNo string `json:"billing_no,optional"`
}

// SenderInfo 发货人信息
type SenderInfo struct {
	// Name 发货门店名称
	Name string `json:"name"`

	// Phone 发货门店电话
	Phone string `json:"phone"`

	// Address 发货门店地址
	Address string `json:"address"`

	// AddressDetail 发货门店地址详情
	AddressDetail string `json:"address_detail"`

	// Longitude 经度
	// 国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude"`

	// Latitude 纬度
	// 国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude"`

	// ProvinceCode 省级行政区编码
	ProvinceCode string `json:"province_code,optional"`

	// CityCode 市级行政区编码
	CityCode string `json:"city_code,optional"`

	// DistrictCode 区县级行政区编码
	DistrictCode string `json:"district_code,optional"`
}

// ReceiverInfo 收货人信息
type ReceiverInfo struct {
	// Name 收货人姓名
	Name string `json:"name"`

	// Phone 收货人电话
	Phone string `json:"phone"`

	// Address 收货人地址
	Address string `json:"address"`

	// AddressDetail 收货人地址详情
	AddressDetail string `json:"address_detail"`

	// Longitude 经度
	// 国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude"`

	// Latitude 纬度
	// 国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude"`

	// ProvinceCode 省级行政区编码
	ProvinceCode string `json:"province_code,optional"`

	// CityCode 市级行政区编码
	CityCode string `json:"city_code,optional"`

	// DistrictCode 区县级行政区编码
	DistrictCode string `json:"district_code,optional"`
}

// OrderInfo 订单信息
type OrderInfo struct {
	// Sn 订单流水号
	// 纯数字编号
	Sn int64 `json:"sn"`

	// FullSn 完整订单流水号
	// 包含日期标识的完整流水号，示例: "101_1230"
	FullSn string `json:"full_sn"`

	// Source 订单来源标识
	// 可选值: meituan, eleme, douyin, kuaishou, wechat, alipay等
	// 详见订单来源枚举
	Source string `json:"source"`

	// ChannelTag 订单渠道tag
	// 渠道标识
	ChannelTag string `json:"channel_tag"`

	// ChannelName 渠道名称
	// 订单店铺名称
	ChannelName string `json:"channel_name"`

	// SourceNo 渠道订单号
	// 第三方渠道的原始订单号
	SourceNo string `json:"source_no"`

	// Category 店铺分类
	// 可选值: xiaochi, dangao, shuiguo, naicha等
	// 详见店铺分类枚举
	Category string `json:"category"`

	// Weight 商品重量
	// 单位：千克
	Weight int `json:"weight"`

	// TotalFee 合计费用（折扣后）
	// 单位：分
	TotalFee int64 `json:"total_fee"`

	// PaidFee 实付金额
	// 单位：分
	PaidFee int64 `json:"paid_fee"`

	// IsFromDoor 是否上门取件
	// 0: 不上门取件; 1: 上门取件
	IsFromDoor int `json:"is_from_door,optional"`

	// IsToDoor 是否送件上门
	// 0: 不送件上门; 1: 送件上门
	IsToDoor int `json:"is_to_door,optional"`

	// GoodsList 商品列表
	// 订单中的所有商品信息
	GoodsList []*GoodsInfo `json:"goods_list"`
}

// GoodsInfo 商品信息
type GoodsInfo struct {
	// Name 商品名称
	Name string `json:"name"`

	// Number 商品数量
	Number int `json:"number"`

	// Price 商品价格
	// 单位：分
	Price int64 `json:"price"`

	// Size 商品规格
	// 如: "大杯"、"中杯"等
	Size string `json:"size"`
}

// SendResp 配送下单响应参数
type SendResp struct {
	// OrderNo 三方运单号
	// 配送服务商的订单号
	OrderNo string `json:"order_no"`

	// SourceOrderNo 麦芽田订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// PayAmount 运费
	// 单位：分
	PayAmount int64 `json:"pay_amount"`

	// Coupon 优惠费用
	// 单位：分，如遇没有可传0
	Coupon int64 `json:"coupon"`

	// Premium 当前溢价
	// 单位：分，如遇没有可传0
	Premium int64 `json:"premium"`

	// Tips 加费
	// 单位：分，如遇没有可传0
	Tips int64 `json:"tips"`

	// Distance 配送距离
	// 单位：米
	Distance int64 `json:"distance"`

	// Weight 重量
	// 单位：克
	Weight int `json:"weight"`

	// Overweight 超重
	// 单位：克
	Overweight int `json:"overweight"`

	// AtTime 计费时间
	// Unix时间戳（秒）
	AtTime int64 `json:"at_time"`

	// ExpectTime 期望送达时间
	// Unix时间戳（秒），如遇没有可传0
	ExpectTime int64 `json:"expect_time"`

	// ServicePkg 服务包内容
	// 示例: "xxx"
	ServicePkg string `json:"service_pkg,optional"`

	// ServiceTag 服务标签
	// 示例: "专人直送"
	ServiceTag string `json:"service_tag,optional"`
}
