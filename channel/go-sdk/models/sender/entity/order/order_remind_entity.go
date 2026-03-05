// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// OrderRemindReq 订单催单推送请求参数
// 三方服务推送催单信息给麦芽田
//
// 使用场景：
//
//	用户在三方平台催单，三方推送催单信息给麦芽田
//
// 注意事项：
//   - 三方应及时推送催单信息
type OrderRemindReq struct {
	OrderId    string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId     string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	RemindId   int    `json:"remind_id,optional"`           // 催单 ID
	Reason     string `json:"reason"`                       // 催单原因
	UpdateTime int64  `json:"update_time"`                  // 催单时间（Unix 时间戳，单位：秒）
}
