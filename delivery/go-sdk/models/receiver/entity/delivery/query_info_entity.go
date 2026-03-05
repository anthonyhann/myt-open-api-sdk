/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: query_info_entity
 * @Description: 查询配送详情实体定义
 * @Version 1.0
 */

package entity

// QueryInfoReq 查询配送详情请求参数
// command: query_info
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务查询订单配送详情
type QueryInfoReq struct {
	// OrderNo 配送单号
	// 三方配送平台的订单号
	OrderNo string `json:"order_no"`

	// SourceOrderNo 麦芽田侧订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// Mobile 手机号码
	// 用于查询的手机号
	Mobile string `json:"mobile,optional"`

	// Type 付款类型
	// 业务类型标识
	Type int64 `json:"type,optional"`

	// ShipperCode 配送方编码
	// 配送服务商的编码标识
	ShipperCode string `json:"shipper_code,optional"`
}

// QueryInfoResp 查询配送详情响应参数
type QueryInfoResp struct {
	// OrderNo 三方运单号
	// 配送服务商的订单号
	OrderNo string `json:"order_no"`

	// SourceOrderNo 麦芽田订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// Status 配送状态
	// 可选值: GRABBED, PICKUP, DELIVERING, DONE, CANCEL, RIDER_CANCEL, TRANSFER, EXPECT
	// 详见配送状态枚举
	Status string `json:"status"`

	// StatusName 状态名称
	// 状态的中文描述，如"待接单"
	StatusName string `json:"status_name"`

	// PayAmount 运费
	// 单位：分
	PayAmount int64 `json:"pay_amount"`

	// Coupon 优惠费用
	// 单位：分，如遇没有可传0
	Coupon int64 `json:"coupon"`

	// Premium 溢价
	// 单位：分，如遇没有可传0
	Premium int64 `json:"premium"`

	// Tips 加费
	// 单位：分，如遇没有可传0
	Tips int64 `json:"tips"`

	// Distance 配送距离
	// 单位：米
	Distance int64 `json:"distance"`

	// CreateTime 下单时间
	// Unix时间戳（秒）
	CreateTime int64 `json:"create_time"`

	// AcceptTime 接单时间
	// Unix时间戳（秒），如遇没有可传0
	AcceptTime int64 `json:"accept_time"`

	// FetchTime 取货时间
	// Unix时间戳（秒），如遇没有可传0
	FetchTime int64 `json:"fetch_time"`

	// FinishTime 完成时间
	// Unix时间戳（秒），如遇没有可传0
	FinishTime int64 `json:"finish_time"`

	// CancelTime 取消时间
	// Unix时间戳（秒），如遇没有可传0
	CancelTime int64 `json:"cancel_time"`

	// RiderName 骑手姓名
	// 配送员姓名
	RiderName string `json:"rider_name"`

	// RiderPhone 骑手电话
	// 配送员联系电话
	RiderPhone string `json:"rider_phone"`

	// Longitude 经度
	// 当前位置经度，国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude"`

	// Latitude 纬度
	// 当前位置纬度，国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude"`

	// IsTransship 是否转单
	// true: 是转单; false: 不是转单
	IsTransship bool `json:"is_transship"`
}
