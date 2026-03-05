/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: precancel_entity
 * @Description: 预取消配送实体定义
 * @Version 1.0
 */

package entity

// PrecancelReq 预取消配送请求参数
// command: precancel
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务预查询取消订单的违约金等信息
// 注意: 此接口仅查询取消信息，不会实际取消订单
type PrecancelReq struct {
	// SourceOrderNo 麦芽田侧订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// SourceDeliveryNo 三方配送单号
	// 配送服务商的订单号
	SourceDeliveryNo string `json:"source_delivery_no"`

	// CancelReasonType 取消类型
	// 1: 用户取消
	// 2: 商户取消
	// 3: 客服取消
	// 4: 系统取消
	// 5: 其他原因
	// 可选字段，详见取消类型枚举
	CancelReasonType int `json:"cancel_reason_type"`

	// CancelReason 取消原因
	// 取消订单的具体原因描述，可选
	CancelReason string `json:"cancel_reason"`
}

// PrecancelResp 预取消配送响应参数
type PrecancelResp struct {
	// Status 预取消接口处理状态
	// 1: 成功; 0: 失败
	Status int `json:"status"`

	// ErrorCode 错误码
	// 处理失败时的错误码
	ErrorCode string `json:"error_code,optional"`

	// CancelAmount 取消违约金
	// 单位：分
	// 表示如果取消此订单需要支付的违约金
	CancelAmount int `json:"cancel_amount"`
}
