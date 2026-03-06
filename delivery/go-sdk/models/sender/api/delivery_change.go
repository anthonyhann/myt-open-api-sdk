/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: api
 * @ClassName: delivery_change
 * @Description: 配送状态同步API
 * @Version 1.0
 */

package api

import (
	"context"
	"net/http"

	"github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/delivery"
)

const (
	// DeliveryChange 配送状态同步接口路径
	// command: delivery_change
	// 必接: 是
	DeliveryChange = "v1/delivery/delivery_change"
)

// DeliveryChange 配送状态同步
//
// 功能说明:
//   - 同步订单配送状态操作
//   - 三方配送服务商主动推送订单配送状态到麦芽田平台
//   - 支持配送全流程状态更新（接单、取货、配送中、完成、取消等）
//
// 参数说明:
//   - ctx: 上下文对象，用于控制请求的超时和取消
//   - token: 麦芽田授权token，用于身份验证
//   - data: 请求参数，包含订单号、状态、骑手信息、位置等
//
// 返回值:
//   - *client.HTTPResponse: 标准响应，code为200表示成功
//   - error: 错误信息，成功时为nil
//
// 状态说明:
//   - GRABBED: 已分配骑手
//   - PICKUP: 待取货
//   - DELIVERING: 配送中
//   - DONE: 已完成
//   - CANCEL: 已取消
//   - RIDER_CANCEL: 骑手取消
//   - TRANSFER: 骑手转单
//   - EXPECT: 配送异常
//
// 注意事项:
//   - 订单状态变更时必须及时推送
//   - 取消状态时需提供cancel_type和cancel_reason
//   - 骑手信息（姓名、电话、位置）建议实时更新
//
// 使用示例:
//
//	req := &entity.DeliveryChangeReq{
//	    OrderNo:       "delivery_order_123",
//	    SourceOrderNo: "myt_order_456",
//	    ShopId:        "shop_789",
//	    Status:        "DELIVERING",
//	    RiderName:     "张骑手",
//	    RiderPhone:    "13800138000",
//	    Longitude:     "116.397128",
//	    Latitude:      "39.916527",
//	    UpdateTime:    time.Now().Unix(),
//	}
//	resp, err := sender.DeliveryChange(ctx, token, req)
//	if err != nil {
//	    log.Fatal(err)
//	}
func (o *deliverySender) DeliveryChange(ctx context.Context, token string, data *entity.DeliveryChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, DeliveryChange, data, token, nil)
}
