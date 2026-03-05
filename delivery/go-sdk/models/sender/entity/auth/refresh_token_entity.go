/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: refresh_token_entity
 * @Description: 刷新访问令牌Token实体定义
 * @Version 1.0
 */

package entity

// RefreshTokenReq 刷新访问令牌请求参数
// command: refresh_token
// 必接: 是
// 说明: 当token过期时，使用(有效期内的)refresh_token重新获取新的token
// 注意: 该接口刷新得到新的token和refresh_token，旧的token随即在5分钟内失效
type RefreshTokenReq struct {
	// Token 访问令牌
	// 需要刷新的访问令牌
	Token string `json:"token" validate:"required"`

	// RefreshToken 刷新授权token
	// 用于获取新的访问令牌
	RefreshToken string `json:"refresh_token" validate:"required"`
}

// RefreshTokenResp 刷新访问令牌响应参数
type RefreshTokenResp struct {
	// Token 新的麦芽田授权token
	// 用于后续API调用认证
	Token string `json:"token" validate:"required"`

	// RefreshToken 新的刷新授权token
	// 用于下次token过期后刷新
	RefreshToken string `json:"refresh_token" validate:"required"`

	// ExpireTime 新token过期时间
	// Unix时间戳，默认30天
	ExpireTime int64 `json:"expire_time"`

	// RefreshExpireTime 新refresh_token过期时间
	// Unix时间戳，默认30天
	// 注意: 需要在时效内用此接口再换取新的refresh_token才不会出现用户授权频繁失效的情况
	RefreshExpireTime int64 `json:"refresh_expire_time"`
}
