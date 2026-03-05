// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	entity "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

const (
	// OrderCreated 新订单推送接口路径
	OrderCreated = "v1/channel/order_created"
)

// OrderCreated 新订单推送接口实现【必接】
// 三方服务在用户下单并付款成功后，主动推送新订单给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 新订单完整信息（包含订单主体、商品列表、顾客信息等）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 用户在三方平台下单并支付
//  2. 三方系统创建订单
//  3. 调用此接口推送订单数据给麦芽田
//  4. 麦芽田接收订单并开始处理
//
// 注意事项：
//   - 这是必接接口，三方必须实现
//   - 订单数据必须完整，包含所有必填字段
//   - 推送失败时应进行重试（SDK 已自动处理）
func (o *channelSender) OrderCreated(ctx context.Context, token string, data *entity.CreateOrderReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderCreated, data, token, nil)
}
