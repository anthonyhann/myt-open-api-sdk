// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	entity "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

const (
	// OrderRefunded 订单退款结果推送接口路径
	OrderRefunded = "v1/channel/order_refunded"
)

// OrderRefunded 订单退款结果推送接口实现
// 三方服务在处理退款申请后，主动推送退款结果给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 退款结果信息（订单 ID、退款单 ID、退款状态、实际退款金额）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 三方处理退款申请（同意或拒绝）
//  2. 三方系统更新退款状态
//  3. 调用此接口推送退款结果给麦芽田
//  4. 麦芽田更新退款状态，退款成功时原路退款给顾客
//
// 注意事项：
//   - 退款状态：1-退款成功，2-退款失败，3-退款处理中
//   - 退款成功后金额会原路退回给顾客
func (o *channelSender) OrderRefunded(ctx context.Context, token string, data *entity.OrderRefundedReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderRefunded, data, token, nil)
}
