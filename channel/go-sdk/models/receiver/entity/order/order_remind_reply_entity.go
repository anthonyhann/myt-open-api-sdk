// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// OrderRemindReplyReq 催单回复请求参数（麦芽田推送给三方）
// 麦芽田通知三方催单的回复消息
//
// 使用场景：
//
//	麦芽田通知三方商家回复顾客催单的消息
//
// 业务流程：
//
//	麦芽田 → 推送催单回复请求 → 三方处理回复逻辑 → 三方回复处理结果
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
type OrderRemindReplyReq struct {
	ShopId       string `json:"shop_id" validate:"required"`   // 平台方渠道 ID（三方平台的门店标识）
	RemindId     int    `json:"remind_id" validate:"required"` // 催单 ID
	ReplyType    int    `json:"reply_type"`                    // 回复类型：1-备货中，2-已送出，3-天气原因，4-人手不足，5-其他
	ReplyContent string `json:"reply_content"`                 // 回复内容
}
