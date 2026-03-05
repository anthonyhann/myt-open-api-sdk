// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// OrderDoneReq 订单完成推送请求参数
// 三方服务推送订单完成状态给麦芽田
//
// 使用场景：
//
//	订单配送完成后，三方推送完成状态给麦芽田
//
// 注意事项：
//   - 这是必接接口，三方必须实现
//   - 订单完成后麦芽田进入结算流程
type OrderDoneReq struct {
	OrderId    string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId     string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	UpdateTime int64  `json:"update_time"`                  // 完成时间（Unix 时间戳，单位：秒）
}
