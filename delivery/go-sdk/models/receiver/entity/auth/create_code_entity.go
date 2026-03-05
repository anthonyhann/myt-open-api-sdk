// Package entity 麦芽田配送开放平台实体定义
//
// @Author Hanqiang
// @Date 2025/12/8 17:23
// @PackageName: entity
// @ClassName: create_code_entity
// @Description: OAuth2授权码实体定义，用于授权码模式获取访问令牌的授权流程
// @Version 1.0
package entity

// CreateCodeReq OAuth2授权码请求实体
// 用于麦芽田平台OAuth2授权流程中的授权码请求，遵循OAuth2.0规范
// 主要用于商户绑定配送账号的授权场景
type CreateCodeReq struct {
	// ResponseType 授权响应类型，固定为"code"表示使用授权码模式
	// 遵循OAuth2.0授权码流程规范
	ResponseType string `json:"response_type,default=code"`

	// View 授权页面展示类型，默认为"web"网页版
	// 支持不同终端的授权页面适配
	View string `json:"view,default=web"`

	// RedirectUri 授权回调地址
	// 授权成功后重定向的URI，必须与应用注册时的回调地址一致
	// 格式：https://your-domain.com/callback
	RedirectUri string `json:"redirect_uri"`

	// Code 授权码
	// OAuth2授权流程中获得的临时授权码，有效期5分钟
	// 用于换取长期访问令牌（access_token）
	Code string `json:"code"`

	// State 参数（可选）
	// 防CSRF攻击的状态码，授权服务器会原样返回
	State string `json:"state,optional"`
}
