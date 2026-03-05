// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	entity "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

const (
	// SelfDeliveryChange 自配送状态变更推送接口路径
	SelfDeliveryChange = "v1/channel/self_delivery_change"
)

// SelfDeliveryChange 自配送状态变更推送接口实现
// 三方服务在使用自配送时，主动推送配送状态变更给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 自配送状态信息（订单号、配送状态、骑手信息、位置信息）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 三方自配送订单状态变化（骑手接单、到店、配送中等）
//  2. 调用此接口推送状态变更给麦芽田
//  3. 麦芽田同步配送状态，用户可追踪订单
//
// 注意事项：
//   - 仅适用于三方自配送订单
//   - 需要及时推送状态，让用户可以实时追踪订单
//   - 配送状态：PENDING、GRABBED、ATSHOP、PICKUP、DELIVERING、DONE、CANCEL、EXPECT
func (o *channelSender) SelfDeliveryChange(ctx context.Context, token string, data *entity.SelfDeliveryChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, SelfDeliveryChange, data, token, nil)
}
