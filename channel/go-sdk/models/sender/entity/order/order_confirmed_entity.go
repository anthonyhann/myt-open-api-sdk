// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// OrderConfirmedReq 订单确认推送请求参数
// 三方服务推送订单确认状态给麦芽田
//
// 使用场景：
//
//	三方商户确认接单后，推送确认状态给麦芽田
//
// 注意事项：
//   - 这是必接接口，三方必须实现
//   - 确认后麦芽田订单状态变为"已确认"
type OrderConfirmedReq struct {
	OrderId    string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId     string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	UpdateTime int64  `json:"update_time"`                  // 确认时间（Unix 时间戳，单位：秒）
}
