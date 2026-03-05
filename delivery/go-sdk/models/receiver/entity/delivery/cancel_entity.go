/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: cancel_entity
 * @Description: 取消配送实体定义
 * @Version 1.0
 */

package entity

// CancelReq 取消配送请求参数
// command: cancel
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务取消配送订单
type CancelReq struct {
	// SourceOrderNo 麦芽田侧运单号
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
	// 详见取消类型枚举
	CancelReasonType int `json:"cancel_reason_type"`

	// CancelReason 取消原因
	// 取消订单的具体原因描述
	CancelReason string `json:"cancel_reason"`
}

// CancelResp 取消配送响应参数
type CancelResp struct {
	// Status 处理状态
	// 1: 成功; 0: 失败
	Status int `json:"status,default=1"`

	// ErrorCode 错误码
	// 取消失败时的错误码
	ErrorCode string `json:"error_code,optional"`

	// Reason 失败原因
	// 取消失败时的原因描述
	Reason string `json:"reason,optional"`

	// CancelAmount 取消违约金
	// 单位：分
	CancelAmount int64 `json:"cancel_amount,optional"`
}
