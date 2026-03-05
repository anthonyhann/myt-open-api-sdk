// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// MealPickingReq 订单出餐状态更新请求参数（麦芽田推送给三方）
// 麦芽田通知三方更新出餐状态
//
// 使用场景：
//
//	麦芽田通知三方订单出餐状态变化
//
// 业务流程：
//
//	麦芽田 → 推送出餐状态请求 → 三方处理出餐逻辑 → 三方回复处理结果
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
//   - Type 取值：0-等待拣货，1-拣货中，2-拣货完成
type MealPickingReq struct {
	OrderId string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId  string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	Type    int64  `json:"type,optional"`                // 出餐类型：0-等待拣货，1-拣货中，2-拣货完成
}
