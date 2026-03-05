// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// ShopDetailReq 查询门店详情请求参数（三方接收麦芽田请求）
// 麦芽田向三方查询门店详情时的请求参数
//
// 使用场景：
//
//	查询三方门店基本信息
//
// 注意事项：
//   - 此结构用于麦芽田调用三方的查询接口
type ShopDetailReq struct {
	ShopId string `json:"shop_id"` // 平台方渠道 ID（三方平台的门店标识）
}

// ShopDetailResp 查询门店详情响应数据（三方返回给麦芽田）
// 三方返回给麦芽田的门店完整信息
type ShopDetailResp struct {
	ShopId    string `json:"shop_id"`   // 平台方渠道 ID
	Name      string `json:"name"`      // 门店名称
	Phone     string `json:"phone"`     // 门店联系电话
	Province  string `json:"province"`  // 省份
	City      string `json:"city"`      // 城市
	Address   string `json:"address"`   // 详细地址
	Latitude  string `json:"latitude"`  // 门店纬度（国测局 GCJ-02 标准）
	Longitude string `json:"longitude"` // 门店经度（国测局 GCJ-02 标准）
}
