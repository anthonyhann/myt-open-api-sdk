// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	entity "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

const (
	// OrderModified 订单修改推送接口路径
	OrderModified = "v1/channel/order_modified"
)

// OrderModified 订单修改推送接口实现
// 三方服务在用户修改订单后，主动推送订单变更信息给麦芽田
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 订单修改信息（修改后的订单数据，仅包含修改部分）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：推送成功
//	  - Code≠200：推送失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 用户在三方平台修改订单（修改商品、地址等）
//  2. 三方系统更新订单信息
//  3. 调用此接口推送变更给麦芽田
//  4. 麦芽田同步更新订单信息
//
// 注意事项：
//   - 仅订单确认前可调用
//   - 修改内容仅推送修改部分的数据
//   - 订单确认后无法修改
func (o *channelSender) OrderModified(ctx context.Context, token string, data *entity.UpdateOrderReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, OrderModified, data, token, nil)
}
