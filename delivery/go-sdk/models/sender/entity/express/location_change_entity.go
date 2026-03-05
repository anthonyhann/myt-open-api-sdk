/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: location_change_entity
 * @Description: 快递轨迹回传实体定义
 * @Version 1.0
 */

package entity

// LocationChangeReq 快递轨迹回传请求参数
// command: location_change
// 必接: 是
// 说明: 三方配送服务商主动推送快递轨迹信息到麦芽田平台
// 用于快递订单的轨迹追踪和状态更新
type LocationChangeReq struct {
	// OrderNo 配送单号
	// 三方配送平台的订单号
	OrderNo string `json:"order_no" validate:"required"`

	// SourceOrderNo 麦芽田侧单号
	// 下单接口提供的单号
	SourceOrderNo string `json:"source_order_no" validate:"required"`

	// ShopId 三方商户ID、门店ID
	// 标识订单所属的商户或门店
	ShopId string `json:"shop_id" validate:"required"`

	// Tag 标签信息
	// 可选字段，用于业务标识
	Tag string `json:"tag,optional"`

	// Locations 轨迹列表
	// 按时间顺序排列的物流轨迹信息
	Locations []Location `json:"locations,optional"`
}

// Location 轨迹节点信息
type Location struct {
	// Description 轨迹描述
	// 示例: "[合肥市]【安徽合肥沐涵公司】的西瓜揽收已揽收"
	Description string `json:"description,optional"`

	// City 所在城市
	// 示例: "合肥市"
	City string `json:"city,optional"`

	// Longitude 经度
	// 国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude,optional"`

	// Latitude 纬度
	// 国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude,optional"`

	// Status 当前状态
	// 可选值:
	// - UNPROGRESS: 未处理
	// - CREATED: 待确认
	// - CONFIRM: 已确认
	// - DELIVERY: 待抢单
	// - PICKUP: 待取货
	// - DELIVERING: 配送中
	// - DONE: 已完成
	// - CANCEL: 已取消
	// - DELETE: 已删除
	// - EXPECT: 配送异常
	Status string `json:"status,optional"`

	// Remark 备注信息
	// 可选字段
	Remark string `json:"remark,optional"`

	// UpdateTime 更新时间
	// Unix时间戳（秒）
	UpdateTime int64 `json:"update_time"`
}
