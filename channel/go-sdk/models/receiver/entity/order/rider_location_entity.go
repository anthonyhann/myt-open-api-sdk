// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// MultiRiderLocationReq 批量更新骑手位置请求参数（麦芽田推送给三方）
// 麦芽田批量请求给三方骑手位置信息
//
// 使用场景：
//
//	麦芽田批量请求三方获取订单的骑手位置
//
// 业务流程：
//
//	麦芽田 → 批量请求骑手位置 → 三方接收并同步位置信息
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
type MultiRiderLocationReq struct {
	Locations []*RiderLocation `json:"locations" validate:"required"` // 骑手位置列表
}

// RiderLocation 骑手位置信息
// 单个订单的骑手位置和配送状态
type RiderLocation struct {
	OrderId     string `json:"order_id" validate:"required"`      // 平台方订单唯一 ID（三方平台的订单号）
	ShopId      string `json:"shop_id" validate:"required"`       // 平台方渠道 ID（三方平台的门店标识）
	Status      string `json:"status" validate:"required"`        // 配送状态
	RiderName   string `json:"rider_name" validate:"required"`    // 骑手姓名
	RiderPhone  string `json:"rider_phone" validate:"required"`   // 骑手电话
	LogisticNo  string `json:"logistic_no" validate:"required"`   // 物流单号
	LogisticTag string `json:"logistic_tag" validate:"required"`  // 物流标识
	Longitude   string `json:"longitude"`                         // 骑手当前经度（国测局 GCJ-02 标准）
	Latitude    string `json:"latitude"`                          // 骑手当前纬度（国测局 GCJ-02 标准）
	Distance    int    `json:"distance,optional"`                 // 骑手距离顾客的距离（单位：米）
	IsExpress   bool   `json:"is_express,default=false,optional"` // 是否快递配送
	UpdateTime  int64  `json:"update_time"`                       // 位置更新时间（Unix 时间戳，单位：秒）
}
