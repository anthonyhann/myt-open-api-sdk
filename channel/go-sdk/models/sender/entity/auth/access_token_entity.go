// Package entity 提供三方服务主动调用麦芽田授权接口的请求参数定义
package entity

// AccessTokenReq 获取访问令牌请求参数
// 三方服务使用授权码向麦芽田换取访问令牌
//
// 使用场景：
//
//	商户完成 OAuth2 授权后，三方使用授权码向麦芽田换取访问令牌
//
// 注意事项：
//   - 此接口的公共参数 token 传空字符串
//   - 授权码 code 有效期 5 分钟
type AccessTokenReq struct {
	GrantType string `json:"grant_type" validate:"required"` // 授权类型，固定值："shop"
	Code      string `json:"code" validate:"required"`       // 授权码，有效期 5 分钟
	ShopId    string `json:"shop_id" validate:"required"`    // 平台方渠道 ID（三方平台的门店标识）
	Category  string `json:"category"`                       // 订单分类，麦芽田枚举值
	Name      string `json:"name"`                           // 门店名称
	Type      string `json:"type"`                           // 店铺类型：["waimai", "shop", "other"]
	Longitude string `json:"longitude"`                      // 门店经度（国测局 GCJ-02 标准）
	Latitude  string `json:"latitude"`                       // 门店纬度（国测局 GCJ-02 标准）
}

// AccessTokenResp 获取访问令牌响应数据
// 麦芽田返回的访问令牌和刷新令牌
type AccessTokenResp struct {
	ShopId            string `json:"shop_id"`                           // 平台方渠道 ID
	Token             string `json:"token" validate:"required"`         // 访问令牌，有效期 30 天
	RefreshToken      string `json:"refresh_token" validate:"required"` // 刷新令牌，有效期 30 天
	ExpireTime        int64  `json:"expire_time"`                       // 访问令牌过期时间（Unix 时间戳，单位：秒）
	RefreshExpireTime int64  `json:"refresh_expire_time"`               // 刷新令牌过期时间（Unix 时间戳，单位：秒）
	ExpiresIn         int64
}
