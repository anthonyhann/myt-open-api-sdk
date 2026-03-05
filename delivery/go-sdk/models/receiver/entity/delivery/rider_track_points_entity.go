/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: rider_track_points_entity
 * @Description: 查询当前订单配送骑手位置实体定义
 * @Version 1.0
 */

package entity

// RiderTrackPointsReq 查询骑手轨迹点请求参数
// command: rider_track_points
// 必接: 是
// 说明: 查询当前订单配送骑手的位置轨迹信息，支持时间范围查询
type RiderTrackPointsReq struct {
	// OrderNo 配送单号
	// 三方配送平台的订单号
	OrderNo string `json:"order_no" validate:"required"`

	// SourceOrderNo 麦芽田侧运单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no" validate:"required"`

	// StartTime 查询开始时间
	// Unix时间戳（秒），可选字段
	StartTime int64 `json:"start_time,optional"`

	// EndTime 查询结束时间
	// Unix时间戳（秒），可选字段
	EndTime int64 `json:"end_time,optional"`
}

// RiderTrackPointsResp 查询骑手轨迹点响应参数
type RiderTrackPointsResp struct {
	// SourceOrderNo 麦芽田订单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// OrderNo 三方运单号
	// 配送服务商的订单号
	OrderNo string `json:"order_no"`

	// RiderTrackPoints 骑手经纬度列表
	// 按时间顺序排列的骑手位置轨迹点
	RiderTrackPoints []*RiderTrackPoint `json:"rider_track_points"`
}

// RiderTrackPoint 骑手轨迹点信息
type RiderTrackPoint struct {
	// RiderName 骑手姓名
	// 配送员姓名
	RiderName string `json:"rider_name"`

	// RiderPhone 骑手手机号
	// 配送员联系电话
	RiderPhone string `json:"rider_phone"`

	// Longitude 经度
	// 位置经度，国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude"`

	// Latitude 纬度
	// 位置纬度，国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude"`

	// AtTime 经纬度采集时间
	// Unix时间戳（秒）
	AtTime int64 `json:"at_time"`
}
