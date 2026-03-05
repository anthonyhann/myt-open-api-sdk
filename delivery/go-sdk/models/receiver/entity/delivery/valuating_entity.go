/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: valuating_entity
 * @Description: 订单计费接口实体定义
 * @Version 1.0
 */

package entity

// ValuatingReq 订单计费请求参数
// command: valuating
// 必接: 是
// 说明: 查询订单运费，麦芽田平台调用三方配送服务计算订单运费
// 注意: 请求参数与下单接口SendReq完全相同
type ValuatingReq struct {
	SendReq
}

// ValuatingResp 订单计费响应参数
// 注意: 响应参数基本继承自SendResp，额外增加计价单号字段
type ValuatingResp struct {
	SendResp

	// BillingNo 计价三方唯一计费号
	// 用于锁定计费，在下单时传入此号码以确保计费一致性
	BillingNo string `json:"billing_no,optional"`
}
