// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// ShopListReq 查询门店列表请求参数（三方接收麦芽田请求）
// 麦芽田向三方查询门店列表时的请求参数
//
// 使用场景：
//
//	麦芽田查询三方商户名下的所有门店
//
// 分页方式：
//   - 普通分页：使用 Page 和 PageSize
//   - 游标分页：使用 ScrollId
type ShopListReq struct {
	Page     int    `json:"page"`               // 页码（从 1 开始）
	PageSize int    `json:"page_size"`          // 每页数量（建议值：10-100）
	ScrollId string `json:"scroll_id,optional"` // 游标 ID
}

// ShopListResp 查询门店列表响应数据（三方返回给麦芽田）
// 三方返回给麦芽田的分页门店列表
type ShopListResp struct {
	Data      []ShopItem `json:"data"`                // 门店数据列表
	Page      int        `json:"page,optional"`       // 当前页码
	TotalPage int        `json:"total_page,optional"` // 总页数
	Total     int        `json:"total,optional"`      // 门店总数
	IsLast    bool       `json:"is_last,optional"`    // 是否最后一页
	ScrollId  string     `json:"scroll_id,optional"`  // 游标 ID
}

// ShopItem 门店信息项
// 门店列表中的单个门店信息
type ShopItem struct {
	ShopId    string `json:"shop_id"`   // 平台方渠道 ID（三方平台的门店标识）
	Name      string `json:"name"`      // 门店名称
	Phone     string `json:"phone"`     // 门店联系电话
	Province  string `json:"province"`  // 省份
	City      string `json:"city"`      // 城市
	Address   string `json:"address"`   // 详细地址
	Latitude  string `json:"latitude"`  // 门店纬度（国测局 GCJ-02 标准）
	Longitude string `json:"longitude"` // 门店经度（国测局 GCJ-02 标准）
}
