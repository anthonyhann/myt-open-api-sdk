// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// TokenUnbindReq 解绑授权令牌请求参数（麦芽田推送给三方）
// 麦芽田通知三方解绑授权令牌
//
// 使用场景：
//
//	麦芽田通知三方解除授权绑定
//
// 业务流程：
//
//	麦芽田 → 推送解绑请求 → 三方处理解绑逻辑 → 三方清理本地 token
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
//   - 三方收到解绑通知后应清理本地存储的 token
type TokenUnbindReq struct {
	ShopId string `json:"shop_id" validate:"required"` // 平台方渠道 ID（三方平台的门店标识）
	Token  string `json:"token,optional"`              // 要解绑的访问令牌
}
