/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: api
 * @ClassName: refresh_token
 * @Description: 刷新访问令牌API
 * @Version 1.0
 */

package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/delivery/myt-go-sdk/client"
	entity "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/auth"
)

const (
	// RefreshToken 刷新访问令牌接口路径
	// command: refresh_token
	// 必接: 是
	RefreshToken = "v1/delivery/refresh_token"
)

// RefreshToken 刷新访问令牌Token
//
// 功能说明:
//   - 当token过期时，使用(有效期内的)refresh_token重新获取新的token
//   - 该接口刷新得到新的token和refresh_token，旧的token随即在5分钟内失效
//   - 此接口为后端接口，三方服务商主动调用麦芽田平台服务
//
// 参数说明:
//   - ctx: 上下文对象，用于控制请求的超时和取消
//   - token: 需要刷新的麦芽田授权token
//   - data: 请求参数，包含token和refresh_token
//
// 返回值:
//   - *client.HTTPResponse: 包含新的token、refresh_token、expire_time等信息
//   - error: 错误信息，成功时为nil
//
// 注意事项:
//   - refresh_token也有过期时间，过期后需重新授权
//   - 建议在refresh_token时效内用此接口换取新的refresh_token
//   - 避免用户授权频繁失效的情况
//
// 使用示例:
//
//	req := &entity.RefreshTokenReq{
//	    Token:        "old_token_xxx",
//	    RefreshToken: "refresh_token_xxx",
//	}
//	resp, err := sender.RefreshToken(ctx, "old_token_xxx", req)
//	if err != nil {
//	    log.Fatal(err)
//	}
//	// 解析响应获取新的token和refresh_token
func (o *deliverySender) RefreshToken(ctx context.Context, token string, data *entity.RefreshTokenReq) (*client.ApiResponse[*entity.RefreshTokenResp], error) {
	return client.RequestWithApiClient[*entity.RefreshTokenResp](ctx, o.apiClient, http.MethodPost, RefreshToken, data, token, nil)
}
