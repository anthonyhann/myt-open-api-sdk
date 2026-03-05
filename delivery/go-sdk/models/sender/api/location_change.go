/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: api
 * @ClassName: location_change
 * @Description: 快递轨迹回传API
 * @Version 1.0
 */

package api

import (
	"context"
	"net/http"

	"github.com/maiyatian/delivery/myt-go-sdk/client"
	entity "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/express"
)

const (
	// LocationChange 快递轨迹回传接口路径
	// command: location_change
	// 必接: 否
	LocationChange = "v1/delivery/location_change"
)

// LocationChange 快递轨迹回传
//
// 功能说明:
//   - 三方配送服务商主动推送快递轨迹信息到麦芽田平台
//   - 用于快递订单的轨迹追踪和状态更新
//   - 支持多个轨迹节点批量推送
//
// 参数说明:
//   - ctx: 上下文对象，用于控制请求的超时和取消
//   - token: 麦芽田授权token，用于身份验证
//   - data: 请求参数，包含订单号、轨迹列表等信息
//
// 返回值:
//   - *client.HTTPResponse: 标准响应，code为200表示成功
//   - error: 错误信息，成功时为nil
//
// 轨迹状态说明:
//   - UNPROGRESS: 未处理
//   - CREATED: 待确认
//   - CONFIRM: 已确认
//   - DELIVERY: 待抢单
//   - PICKUP: 待取货
//   - DELIVERING: 配送中
//   - DONE: 已完成
//   - CANCEL: 已取消
//   - DELETE: 已删除
//   - EXPECT: 配送异常
//
// 注意事项:
//   - 轨迹节点应按时间顺序排列
//   - 每个轨迹节点必须包含描述和时间
//   - 位置信息（经纬度）使用国测局02标准（高德坐标系）
//   - 轨迹更新应及时推送，确保信息实时性
//
// 使用示例:
//
//	req := &entity.LocationChangeReq{
//	    OrderNo:       "express_order_123",
//	    SourceOrderNo: "myt_order_456",
//	    ShopId:        "shop_789",
//	    Locations: []entity.Location{
//	        {
//	            Description: "[北京市]快件已揽收",
//	            City:        "北京市",
//	            Longitude:   "116.397128",
//	            Latitude:    "39.916527",
//	            Status:      "PICKUP",
//	            UpdateTime:  time.Now().Unix(),
//	        },
//	        {
//	            Description: "[北京市]快件正在配送中",
//	            City:        "北京市",
//	            Longitude:   "116.407128",
//	            Latitude:    "39.906527",
//	            Status:      "DELIVERING",
//	            UpdateTime:  time.Now().Unix(),
//	        },
//	    },
//	}
//	resp, err := sender.LocationChange(ctx, token, req)
//	if err != nil {
//	    log.Fatal(err)
//	}
func (o *deliverySender) LocationChange(ctx context.Context, token string, data *entity.LocationChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error) {
	return client.RequestWithApiClient[ApiResponseEmptyData](ctx, o.apiClient, http.MethodPost, LocationChange, data, token, nil)
}
