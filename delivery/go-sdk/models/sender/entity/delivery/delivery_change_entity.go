/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: delivery_change_entity
 * @Description: 配送状态同步实体定义
 * @Version 1.0
 */

package entity

// DeliveryChangeReq 配送状态同步请求参数
// command: delivery_change
// 必接: 是
// 说明: 同步订单配送状态操作，三方配送服务商主动推送订单配送状态到麦芽田平台
type DeliveryChangeReq struct {
	// OrderNo 配送单号
	// 三方配送平台的订单号
	OrderNo string `json:"order_no" validate:"required"`

	// SourceOrderNo 三方订单号
	// 麦芽田侧的订单号
	SourceOrderNo string `json:"source_order_no" validate:"required"`

	// ShopId 三方商户ID、门店ID
	// 标识订单所属的商户或门店
	ShopId string `json:"shop_id" validate:"required"`

	// Status 配送状态
	// 可选值:
	// - GRABBED: 已分配骑手
	// - PICKUP: 待取货
	// - DELIVERING: 配送中
	// - DONE: 已完成
	// - CANCEL: 已取消
	// - RIDER_CANCEL: 骑手取消
	// - TRANSFER: 骑手转单
	// - EXPECT: 配送异常
	Status string `json:"status" validate:"required"`

	// Tag 标签信息
	// 可选字段，用于业务标识
	Tag string `json:"tag,optional"`

	// RiderName 骑手姓名
	// 配送员姓名
	RiderName string `json:"rider_name,optional"`

	// RiderPhone 骑手电话
	// 配送员联系电话，格式示例: "13888888888_1234"
	RiderPhone string `json:"rider_phone,optional"`

	// Longitude 经度
	// 当前位置经度，国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude,optional"`

	// Latitude 纬度
	// 当前位置纬度，国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude,optional"`

	// PickupCode 取货码
	// 商家取货验证码
	PickupCode string `json:"pickup_code,optional"`

	// Distance 配送距离
	// 单位：米
	Distance int64 `json:"distance,optional"`

	// DeliveryFee 配送费
	// 单位：分
	DeliveryFee int64 `json:"delivery_fee,optional"`

	// IsTransship 是否接驳单
	// true: 是接驳单; false: 不是接驳单
	IsTransship bool `json:"is_transship,optional"`

	// CancelType 取消类型
	// 1: 用户取消
	// 2: 商户取消
	// 3: 客服取消
	// 4: 系统取消
	// 5: 其他原因
	// Status为CANCEL时必填
	CancelType int `json:"cancel_type,optional"`

	// CancelReason 取消原因
	// 取消订单的具体原因描述
	CancelReason string `json:"cancel_reason,optional"`

	// CancelDeditAmount 取消订单违约金
	// 单位：分
	CancelDeditAmount int64 `json:"cancel_dedit_amount,optional"`

	// VehicleInfo 车辆信息
	// 配送车辆的详细信息（可选）
	VehicleInfo VehicleInfo `json:"vehicle_info,optional"`

	// DeliveryPhotoUrls 配送照片链接列表
	// 配送过程中的照片URL列表（可选）
	DeliveryPhotoUrls []string `json:"delivery_photo_urls,optional"`

	// IsCallback 是否回调
	// 标识是否需要麦芽田平台回调
	IsCallback bool `json:"is_callback,optional"`

	// UpdateTime 更新时间
	// Unix时间戳（秒）
	UpdateTime int64 `json:"update_time"`
}

// VehicleInfo 车辆信息
type VehicleInfo struct {
	// VehicleName 车辆名称
	// 示例: "雪铁龙C5"
	VehicleName string `json:"vehicle_name,optional"`

	// VehicleColor 车辆颜色
	// 示例: "蓝色"
	VehicleColor string `json:"vehicle_color,optional"`

	// VehicleNo 车牌号
	// 示例: "冀E4WE32"
	VehicleNo string `json:"vehicle_no,optional"`
}
