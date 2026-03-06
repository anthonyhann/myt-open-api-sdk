// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/shop"
)

const (
	// ShopInfo 查询麦芽田门店信息接口路径
	ShopInfo = "v1/channel/shop_info"
)

// ShopInfo 查询麦芽田门店信息接口实现
// 三方服务主动查询门店在麦芽田平台的基本信息和营业状态
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 商户授权令牌
//	data: 查询请求参数（门店 ID）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：查询成功，Data 中包含门店详细信息
//	  - Code≠200：查询失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 三方需要查询门店信息
//  2. 调用此接口向麦芽田查询
//  3. 麦芽田返回门店的基本信息和营业状态
//
// 返回数据包含：
//   - 门店基本信息：门店 ID、名称、商户名称、联系电话
//   - 营业状态：1-营业中，2-休息中，3-已打烊
//
// 使用场景：
//   - 验证门店信息是否正确
//   - 同步门店营业状态
//   - 门店信息展示
func (o *channelSender) ShopInfo(ctx context.Context, token string, data *entity.ShopInfoReq) (*client.ApiResponse[*entity.ShopInfoResp], error) {
	return client.RequestWithApiClient[*entity.ShopInfoResp](ctx, o.apiClient, http.MethodPost, ShopInfo, data, token, nil)
}
