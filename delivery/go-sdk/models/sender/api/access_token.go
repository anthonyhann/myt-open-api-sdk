/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: api
 * @ClassName: access_token
 * @Description: 授权码换取访问令牌API
 * @Version 1.0
 */

package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/auth"
)

const (
	// AccessToken 授权码换取访问令牌接口路径
	// command: access_token
	// 必接: 是
	AccessToken = "v1/delivery/access_token"
)

// AccessToken 授权码code换取访问令牌Token
//
// 功能说明:
//   - 用授权码code换取长时令牌refreshToken以及访问令牌accessToken
//   - 此接口为后端接口，三方服务商主动调用麦芽田平台服务
//
// 参数说明:
//   - ctx: 上下文对象，用于控制请求的超时和取消
//   - token: 麦芽田授权token（此接口可为空，因为是获取token的接口）
//   - data: 请求参数，包含grant_type、code、store_id等信息
//
// 返回值:
//   - *client.HTTPResponse: 包含shop_id、token、refresh_token、expire_time等信息
//   - error: 错误信息，成功时为nil
//
// 注意事项:
//   - code有效期5分钟，过期需重新授权
//   - 多次使用code换取token返回结果相同
//   - grant_type为1和2时，code字段必填
//
// 使用示例:
//
//	req := &entity.AccessTokenReq{
//	    GrantType: "1",      // 门店级授权
//	    Code:      "xxxxx",  // 授权码
//	    StoreId:   "123456", // 门店ID
//	    Mobile:    "13800138000",
//	    City:      "北京市",
//	    CityCode:  "110100",
//	}
//	resp, err := sender.AccessToken(ctx, "", req)
//	if err != nil {
//	    log.Fatal(err)
//	}
//	// 解析响应中的data字段获取token信息
func (o *deliverySender) AccessToken(ctx context.Context, data *entity.AccessTokenReq) (*client.ApiResponse[*entity.AccessTokenResp], error) {
	return client.RequestWithApiClient[*entity.AccessTokenResp](ctx, o.apiClient, http.MethodPost, AccessToken, data, "", nil)
}
