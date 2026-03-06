// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
)

const (
	// OrderRemind 订单催单推送接口路径
	OrderRemind = "v1/channel/order_remind"
)

// OrderRemind 订单催单推送接口实现
// 三方服务在用户催单后，主动推送催单信息给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 催单信息（订单 ID、门店 ID、催单原因、催单时间）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 用户在三方平台催单
//  2. 三方调用此接口推送催单信息给麦芽田
//  3. 麦芽田记录催单并通知商家
//
// 注意事项：
//   - 催单信息会通知到商家
//   - 商家应及时回复催单
func (o *channelSender) OrderRemind(ctx context.Context, token string, data *entity.OrderRemindReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderRemind, data, token, nil)
}
