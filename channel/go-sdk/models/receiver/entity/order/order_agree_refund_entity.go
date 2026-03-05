// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// OrderAgreeRefundReq 同意退款请求参数（麦芽田推送给三方）
// 麦芽田通知三方同意退款
//
// 使用场景：
//
//	麦芽田通知三方同意退款申请
//
// 业务流程：
//
//	麦芽田 → 推送同意退款请求 → 三方处理退款逻辑 → 三方回复处理结果
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
type OrderAgreeRefundReq struct {
	OrderId  string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId   string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	RefundId string `json:"refund_id"`                    // 退款单 ID
	Reason   string `json:"reason"`                       // 同意退款原因
}
