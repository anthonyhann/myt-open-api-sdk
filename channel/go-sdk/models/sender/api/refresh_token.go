// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/auth"
)

const (
	// RefreshToken 刷新访问令牌接口路径
	RefreshToken = "v1/channel/refresh_token"
)

// RefreshToken 刷新访问令牌接口实现
// 三方服务使用刷新令牌向麦芽田重新获取新的访问令牌
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	token: 当前访问令牌（即将过期或已过期的 token）
//	data: 刷新令牌请求参数（包含当前 token 和 refresh_token）
//
// 返回：
//
//	*client.HTTPResponse: API 响应
//	  - Code=200：刷新成功，Data 中包含新的 token 和 refresh_token
//	  - Code≠200：刷新失败，查看 Message 了解失败原因
//	error: 网络错误或请求失败时返回
//
// 业务流程：
//  1. 三方检测到 token 即将过期
//  2. 调用此接口向麦芽田请求刷新
//  3. 麦芽田验证 refresh_token 有效性
//  4. 返回新的 token 和 refresh_token
//  5. 三方保存新的令牌，旧 token 在 5 分钟内失效
//
// 注意事项：
//   - refresh_token 有效期 30 天
//   - 刷新后得到新的 token 和 refresh_token
//   - 旧的 token 随即在 5 分钟内失效
//   - 如果 refresh_token 也过期了，需要重新授权
//   - 建议在 token 过期前提前刷新，避免授权中断
func (o *channelSender) RefreshToken(ctx context.Context, token string, data *entity.RefreshTokenReq) (*client.ApiResponse[*entity.RefreshTokenResp], error) {
	return client.RequestWithApiClient[*entity.RefreshTokenResp](ctx, o.apiClient, http.MethodPost, RefreshToken, data, token, nil)
}
