// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
)

const (
	// OrderCanceled 订单取消推送接口路径
	OrderCanceled = "v1/channel/order_canceled"
)

// OrderCanceled 订单取消推送接口实现
// 三方服务在订单被取消后，主动推送取消状态给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 订单取消信息（订单 ID、门店 ID、取消原因、取消时间）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 用户/商家在三方平台取消订单
//  2. 三方系统处理取消逻辑
//  3. 调用此接口推送取消状态给麦芽田
//  4. 麦芽田更新订单状态为"已取消"
//
// 注意事项：
//   - 订单取消后不可恢复
//   - 需要记录取消原因和原因码
//   - 取消类型：1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
func (o *channelSender) OrderCanceled(ctx context.Context, token string, data *entity.OrderCanceledReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderCanceled, data, token, nil)
}
