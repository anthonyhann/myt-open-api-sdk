// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// DeliveryChangeReq 配送状态变更请求参数（麦芽田推送给三方）
// 麦芽田通知三方配送状态已变更
//
// 使用场景：
//
//	麦芽田通知三方订单配送状态变化
//
// 业务流程：
//
//	麦芽田 → 推送配送状态变更 → 三方接收并同步状态
//
// 注意事项：
//   - 这是麦芽田推送给三方的回调接口
//   - 三方需要实现此接口的回调处理逻辑
type DeliveryChangeReq struct {
	OrderId     string `json:"order_id" validate:"required"`        // 平台方订单唯一 ID（三方平台的订单号）
	ShopId      string `json:"shop_id" validate:"required"`         // 平台方渠道 ID（三方平台的门店标识）
	Status      string `json:"status" validate:"required"`          // 配送状态
	RiderName   string `json:"rider_name"`                          // 骑手姓名
	RiderPhone  string `json:"rider_phone"`                         // 骑手电话
	LogisticNo  string `json:"logistic_no"`                         // 物流单号
	LogisticTag string `json:"logistic_tag"`                        // 物流标识
	Longitude   string `json:"longitude"`                           // 骑手当前经度（国测局 GCJ-02 标准）
	Latitude    string `json:"latitude"`                            // 骑手当前纬度（国测局 GCJ-02 标准）
	IsTransship bool   `json:"is_transship,default=false,optional"` // 是否转运
	IsExpress   bool   `json:"is_express,default=false,optional"`   // 是否快递配送
	DeliveryFee int64  `json:"delivery_fee,default=0,optional"`     // 配送费（单位：分）
	UpdateTime  int64  `json:"update_time"`                         // 状态更新时间（Unix 时间戳，单位：秒）
}
