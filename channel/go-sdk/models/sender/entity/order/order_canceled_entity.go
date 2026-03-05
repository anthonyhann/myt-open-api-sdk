// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// OrderCanceledReq 订单取消推送请求参数
// 三方服务推送订单取消状态给麦芽田
//
// 使用场景：
//
//	订单被取消后，三方推送取消状态给麦芽田
//
// 注意事项：
//   - 订单取消后不可恢复
//   - 需要记录取消原因和原因码
type OrderCanceledReq struct {
	OrderId    string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId     string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	Reason     string `json:"reason"`                       // 取消原因描述
	ReasonCode int    `json:"reason_code"`                  // 取消原因码：1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
	UpdateTime int64  `json:"update_time"`                  // 取消时间（Unix 时间戳，单位：秒）
}
