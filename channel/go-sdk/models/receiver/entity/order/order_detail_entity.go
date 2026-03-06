// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

import orderBase "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"

// OrderDetailReq 查询订单详情请求参数（三方接收麦芽田请求）
// 麦芽田向三方查询订单详情时的请求参数
//
// 使用场景：
//
//	麦芽田查询三方订单详细信息
//
// 注意事项：
//   - 此结构用于麦芽田调用三方的查询接口
type OrderDetailReq struct {
	OrderId string `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId  string `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
}

// OrderDetailResp 查询订单详情响应数据（三方返回给麦芽田）
// 三方返回麦芽田的订单完整信息
type OrderDetailResp struct {
	orderBase.OrderBaseData // 继承订单基础数据结构
}
