// Package entity 麦芽田配送开放平台Sender接口实体定义
// 包含用于三方配送服务商主动调用麦芽田平台接口的数据结构
package entity

// AccessTokenReq 获取访问令牌请求实体
// 用于OAuth2授权码换取访问令牌，支持门店、商户和三方门店直联三种授权模式
// 对应接口：access_token (command: access_token)
type AccessTokenReq struct {
	// GrantType 授权级别类型，必填
	// 1: 门店授权  2: 商户授权
	// 不同授权级别决定了后续可访问的API范围和权限
	GrantType string `json:"grant_type" validate:"required"`

	// Code 授权码，当GrantType为1或2时必填
	// OAuth2流程中获取的临时授权码，有效期5分钟
	// 多次使用同一个code获取的token是相同的
	Code string `json:"code" validate:"required_if=GrantType 1,required_if=GrantType 2"`

	// StoreId 三方平台最小维度的唯一ID，必填
	// 如果平台方是门店维度则填写门店ID
	// 用于标识具体的业务实体
	StoreId string `json:"store_id" validate:"required"`

	// Mobile 三方平台账号注册手机号，必填
	// 用于身份验证和联系方式确认
	Mobile string `json:"mobile"`

	// City 城市名称，必填
	// 格式示例："北京市"，用于确定服务区域
	City string `json:"city"`

	// CityCode 标准城市行政编码，必填
	// 格式示例："110100"，对应国家标准的城市编码
	CityCode string `json:"city_code"`

	// SourceKey 三方平台最小维度的唯一ID对应密钥，可选
	// 用于增强安全性的额外验证参数
	SourceKey string `json:"source_key,optional"`

	// Platform 快递平台唯一标识，可选
	// 格式示例："JD"（京东）
	// 用于标识具体的快递服务提供商
	Platform string `json:"platform,optional"`
}

// AccessTokenResp 获取访问令牌响应实体
// 包含访问令牌、刷新令牌以及相关的过期时间信息
type AccessTokenResp struct {
	// ShopId 麦芽田平台方渠道ID
	// 用于标识在麦芽田平台中的渠道身份
	ShopId string `json:"shop_id"`

	// Token 麦芽田授权访问令牌，必填
	// 用于后续API调用的身份认证
	// 有效期30天，过期后需要使用RefreshToken刷新
	Token string `json:"token" validate:"required"`

	// RefreshToken 刷新授权令牌，必填
	// 用于在Token过期时获取新的访问令牌
	// 有效期30天，建议在有效期内及时刷新
	RefreshToken string `json:"refresh_token" validate:"required"`

	// ExpireTime Token过期时间
	// Unix时间戳格式（精确到秒）
	// 0表示永久不过期，大于0表示具体的过期时间
	ExpireTime int64 `json:"expire_time"`

	// RefreshExpireTime RefreshToken过期时间
	// Unix时间戳格式（精确到秒）
	// RefreshToken的有效期，过期后需要重新授权
	RefreshExpireTime int64 `json:"refresh_expire_time"`
}
