// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// ConfirmOrderReq 确认订单请求参数（麦芽田推送给三方）
// 麦芽田通知三方需要确认订单
//
// 使用场景：
//
//	麦芽田通知三方商户确认接单
//
// 业务流程：
//
//	麦芽田 → 推送确认订单请求 → 三方处理确认逻辑 → 三方回复确认结果
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
//   - 三方确认后需调用 sender.OrderConfirmed 推送确认状态给麦芽田
type ConfirmOrderReq struct {
	OrderId string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId  string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
}
