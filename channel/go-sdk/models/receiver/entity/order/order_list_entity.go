// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

import orderBase "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"

// OrderListReq 查询订单列表请求参数（三方接收麦芽田请求）
// 麦芽田向三方查询订单列表时的请求参数
//
// 使用场景：
//
//	麦芽田查询三方某个时间段的所有订单
//
// 注意事项：
//   - 时间范围不建议超过 31 天
type OrderListReq struct {
	ShopId    string `json:"shop_id" validate:"required"` // 平台方渠道 ID（三方平台的门店标识）
	StartTime int64  `json:"start_time"`                  // 查询开始时间（Unix 时间戳，单位：秒）
	EndTime   int64  `json:"end_time"`                    // 查询结束时间（Unix 时间戳，单位：秒）
	Page      uint64 `json:"page"`                        // 页码（从 1 开始）
	PageSize  uint64 `json:"page_size"`                   // 每页数量（建议值：10-100）
}

// OrderListResp 查询订单列表响应数据（三方返回给麦芽田）
// 三方返回麦芽田的分页订单列表
type OrderListResp struct {
	Data      []orderBase.OrderBaseData `json:"data"`             // 订单数据列表
	Page      uint64                    `json:"page"`             // 当前页码
	Total     uint64                    `json:"total"`            // 订单总数
	TotalPage uint64                    `json:"total_page"`       // 总页数
	IsLast    bool                      `json:"is_last,optional"` // 是否最后一页
}
