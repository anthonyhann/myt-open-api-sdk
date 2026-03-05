/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: balance_entity
 * @Description: 查询当前账号余额实体定义
 * @Version 1.0
 */

package entity

// BalanceReq 查询账号余额请求参数
// command: balance
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务查询账号余额
type BalanceReq struct {
	// ShopId 平台方渠道ID
	// 三方平台账号ID
	ShopId string `json:"shop_id" validate:"required"`

	// Token 麦芽田商户token
	// 用于验证身份的访问令牌，可选
	Token string `json:"token,optional"`
}

// BalanceResp 查询账号余额响应参数
type BalanceResp struct {
	// Balance 余额金额
	// 单位：分
	Balance int64 `json:"balance"`

	// AtTime 查询时间
	// Unix时间戳（秒）
	AtTime int64 `json:"at_time"`
}
