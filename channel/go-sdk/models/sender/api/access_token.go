// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/auth"
)

const (
	// AccessToken 获取访问令牌接口路径
	AccessToken = "v1/channel/access_token"
)

// AccessToken 获取访问令牌接口实现【必接】
// 三方服务使用授权码 code 向麦芽田换取访问令牌
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	data: 授权码换取令牌请求参数（包含 code、门店信息等）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：换取成功，Data 中包含 token、refresh_token 和过期时间
//	  - Code≠200：换取失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 商户在授权页面完成授权
//  2. 授权成功后，三方从回调地址获取 code
//  3. 调用此接口向麦芽田换取访问令牌
//  4. 麦芽田返回 token 和 refresh_token
//  5. 三方保存令牌，用于后续 API 调用
//
// 注意事项：
//   - 这是必接接口，授权流程必需
//   - 此接口的公共参数 token 必须传空字符串
//   - 授权码 code 有效期 5 分钟，只能使用一次
//   - token 有效期 30 天，refresh_token 有效期 30 天
func (o *channelSender) AccessToken(ctx context.Context, data *entity.AccessTokenReq) (*client.ApiResponse[*entity.AccessTokenResp], error) {
	return client.RequestWithApiClient[*entity.AccessTokenResp](ctx, o.apiClient, http.MethodPost, AccessToken, data, "", nil)
}
