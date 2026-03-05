/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: multi_rider_locations_entity
 * @Description: 批量获取骑手当前位置实体定义
 * @Version 1.0
 */

package entity

// MultiRiderLocationsReq 批量查询骑手位置请求参数
// command: multi_rider_locations
// 必接: 是
// 说明: 批量查询多个订单的骑手当前位置信息
type MultiRiderLocationsReq struct {
	// Orders 订单列表
	// 需要查询骑手位置的订单列表
	Orders []*RiderLocationReq `json:"orders" validate:"required"`
}

// MultiRiderLocationsResp 批量查询骑手位置响应参数
type MultiRiderLocationsResp struct {
	// Data 骑手位置列表
	// 返回每个订单对应的骑手位置信息
	Data []*RiderLocationResp `json:"data"`
}

// RiderLocationReq 单个订单骑手位置查询参数
type RiderLocationReq struct {
	// OrderNo 配送单号
	// 三方配送平台的订单号
	OrderNo string `json:"order_no"`

	// SourceOrderNo 麦芽田侧运单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`
}

// RiderLocationResp 骑手位置信息
type RiderLocationResp struct {
	// SourceOrderNo 麦芽田订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// OrderNo 三方运单号
	// 配送服务商的订单号
	OrderNo string `json:"order_no"`

	// RiderName 骑手姓名
	// 配送员姓名
	RiderName string `json:"rider_name"`

	// RiderPhone 骑手电话
	// 配送员联系电话
	RiderPhone string `json:"rider_phone"`

	// Longitude 经度
	// 当前位置经度，国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude"`

	// Latitude 纬度
	// 当前位置纬度，国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude"`

	// AtTime 位置采集时间
	// Unix时间戳（秒）
	AtTime int64 `json:"at_time"`
}
