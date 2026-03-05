// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	entity "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

const (
	// OrderDone 订单完成推送接口路径
	OrderDone = "v1/channel/order_done"
)

// OrderDone 订单完成推送接口实现【必接】
// 三方服务在订单配送完成后，主动推送完成状态给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 订单完成信息（订单 ID、门店 ID、完成时间）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 订单配送完成（骑手送达或用户自提）
//  2. 三方系统标记订单完成
//  3. 调用此接口推送完成状态给麦芽田
//  4. 麦芽田更新订单状态并进入结算流程
//
// 注意事项：
//   - 这是必接接口，三方必须实现
//   - 订单完成后进入结算流程
//   - 应在订单实际完成时及时推送
func (o *channelSender) OrderDone(ctx context.Context, token string, data *entity.OrderDoneReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderDone, data, token, nil)
}
