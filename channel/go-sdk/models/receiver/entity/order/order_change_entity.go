// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// OrderChangeReq 订单状态变更请求参数（麦芽田推送给三方）
// 麦芽田通知三方订单状态已变更
//
// 使用场景：
//
//	麦芽田通知三方订单状态变化
//
// 业务流程：
//
//	麦芽田 → 推送订单状态变更 → 三方接收并处理 → 三方同步订单状态
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
type OrderChangeReq struct {
	OrderId       string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId        string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	MerchantPhone string `json:"merchant_phone"`               // 商户联系电话
	Status        string `json:"status" validate:"required"`   // 订单状态
	UpdateTime    int64  `json:"update_time"`                  // 状态更新时间（Unix 时间戳，单位：秒）
}
