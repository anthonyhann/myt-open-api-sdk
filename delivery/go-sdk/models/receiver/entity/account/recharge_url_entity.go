/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: recharge_url_entity
 * @Description: 在线充值链接实体定义
 * @Version 1.0
 */

package entity

// RechargeUrlReq 获取充值链接请求参数
// command: recharge_url
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务获取在线充值链接
type RechargeUrlReq struct {
	// ShopId 平台方渠道ID
	// 三方平台账号ID
	ShopId string `json:"shop_id" validate:"required"`
}

// RechargeUrlResp 获取充值链接响应参数
type RechargeUrlResp struct {
	// RechargeUrl 三方充值链接
	// 用于跳转到第三方充值页面的URL
	RechargeUrl string `json:"recharge_url"`

	// AtTime 生成时间
	// Unix时间戳（秒）
	AtTime int64 `json:"at_time"`
}
