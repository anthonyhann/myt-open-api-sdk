// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
)

const (
	// OrderConfirmed 订单确认推送接口路径
	OrderConfirmed = "v1/channel/order_confirmed"
)

// OrderConfirmed 订单确认推送接口实现【必接】
// 三方服务在商户确认订单后，主动推送确认状态给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 订单确认信息（订单 ID、门店 ID、确认时间）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 三方商户确认接单
//  2. 调用此接口推送确认状态给麦芽田
//  3. 麦芽田更新订单状态为"已确认"
//  4. 开始计算出餐时间
//
// 注意事项：
//   - 这是必接接口，三方必须实现
//   - 订单确认后不可撤销
//   - 应在订单创建后及时确认，避免超时
func (o *channelSender) OrderConfirmed(ctx context.Context, token string, data *entity.OrderConfirmedReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderConfirmed, data, token, nil)
}
