/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: token_unbind_entity
 * @Description: 授权解绑实体定义
 * @Version 1.0
 */

package entity

// TokenUnbindReq 授权解绑请求参数
// command: token_unbind
// 必接: 是
// 说明: 当用户解绑授权时，麦芽田平台调用此接口通知三方配送服务
type TokenUnbindReq struct {
	// ShopId 平台方渠道ID
	// 需要解绑的商户ID
	ShopId string `json:"shop_id" validate:"required"`

	// Token 麦芽田商户token
	// 需要解绑的访问令牌
	Token string `json:"token,optional"`
}

// TokenUnbindResp 授权解绑响应参数
type TokenUnbindResp struct {
	// ShopId 平台方渠道ID
	// 已解绑的商户ID
	ShopId string `json:"shop_id"`

	// Token 麦芽田商户token
	// 已解绑的访问令牌
	Token string `json:"token"`
}
