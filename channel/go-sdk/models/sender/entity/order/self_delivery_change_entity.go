// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// SelfDeliveryChangeReq 自配送状态变更推送请求参数
// 三方服务推送自配送状态变更给麦芽田
//
// 使用场景：
//
//	三方自配送订单状态变化，推送状态给麦芽田
//
// 注意事项：
//   - 仅适用于三方自配送订单
//   - 需要及时推送配送状态
type SelfDeliveryChangeReq struct {
	OrderNo       string `json:"order_no" validate:"required"`        // 麦芽田订单号
	SourceOrderNo string `json:"source_order_no" validate:"required"` // 原始订单号（三方平台的订单号）
	ShopId        string `json:"shop_id" validate:"required"`         // 平台方渠道 ID（三方平台的门店标识）
	Status        string `json:"status" validate:"required"`          // 配送状态
	Tag           string `json:"tag,optional"`                        // 配送标签
	RiderName     string `json:"rider_name,optional"`                 // 骑手姓名
	RiderPhone    string `json:"rider_phone,optional"`                // 骑手电话
	Longitude     string `json:"longitude,optional"`                  // 骑手当前经度（国测局 GCJ-02 标准）
	Latitude      string `json:"latitude,optional"`                   // 骑手当前纬度（国测局 GCJ-02 标准）
	PickupCode    string `json:"pickup_code,optional"`                // 取货码
	CancelType    int    `json:"cancel_type,optional"`                // 取消类型：1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
	CancelReason  string `json:"cancel_reason,optional"`              // 取消原因描述
	UpdateTime    int64  `json:"update_time"`                         // 状态更新时间（Unix 时间戳，单位：秒）
}
