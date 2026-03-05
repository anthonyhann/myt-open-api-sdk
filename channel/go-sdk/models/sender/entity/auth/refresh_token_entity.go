// Package entity 提供三方服务主动调用麦芽田授权接口的请求参数定义
package entity

// RefreshTokenReq 刷新访问令牌请求参数
// 三方服务使用刷新令牌向麦芽田换取新的访问令牌
//
// 使用场景：
//
//	当 token 过期时，三方使用 refresh_token 向麦芽田换取新的 token
//
// 注意事项：
//   - 刷新得到新的 token 和 refresh_token
//   - 旧的 token 随即在 5 分钟内失效
type RefreshTokenReq struct {
	Token        string `json:"token" validate:"required"`         // 当前访问令牌
	RefreshToken string `json:"refresh_token" validate:"required"` // 刷新令牌
}

// RefreshTokenResp 刷新访问令牌响应数据
// 麦芽田返回的新访问令牌和刷新令牌
type RefreshTokenResp struct {
	Token             string `json:"token" validate:"required"`         // 新的访问令牌，有效期 30 天
	RefreshToken      string `json:"refresh_token" validate:"required"` // 新的刷新令牌，有效期 30 天
	ExpireTime        int64  `json:"expire_time"`                       // 新访问令牌过期时间（Unix 时间戳，单位：秒）
	RefreshExpireTime int64  `json:"refresh_expire_time"`               // 新刷新令牌过期时间（Unix 时间戳，单位：秒）
}
