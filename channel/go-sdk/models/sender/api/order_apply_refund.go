// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	entity "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

const (
	// OrderApplyRefund 订单申请退款推送接口路径
	OrderApplyRefund = "v1/channel/order_apply_refund"
)

// OrderApplyRefund 订单申请退款推送接口实现
// 三方服务在顾客或商家发起退款申请后，主动推送退款申请给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 退款申请信息（订单 ID、退款类型、退款原因、退款商品列表、退款金额）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 用户/商家在三方平台发起退款申请
//  2. 三方系统创建退款申请单
//  3. 调用此接口推送退款申请给麦芽田
//  4. 麦芽田处理退款流程
//
// 注意事项：
//   - 支持部分退款（按商品退款）
//   - 退款类型：1-仅退款，2-退货退款
//   - 退款金额需要商家审核确认
func (o *channelSender) OrderApplyRefund(ctx context.Context, token string, data *entity.OrderApplyRefundReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderApplyRefund, data, token, nil)
}
