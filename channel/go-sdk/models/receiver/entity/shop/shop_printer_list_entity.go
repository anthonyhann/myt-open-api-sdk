// Package entity 提供麦芽田主动推送给三方服务的数据结构定义
package entity

// ShopPrinterListReq 查询门店打印机列表请求参数（三方接收麦芽田请求）
// 麦芽田向三方查询打印机列表时的请求参数
//
// 使用场景：
//
//	麦芽田查询三方门店绑定的打印机设备
//
// 注意事项：
//   - 门店必须先在打印机平台后台绑定打印机
type ShopPrinterListReq struct {
	ShopId   string `json:"shop_id"`            // 平台方渠道 ID（三方平台的门店标识）
	Page     int    `json:"page"`               // 页码（从 1 开始）
	PageSize int    `json:"page_size"`          // 每页数量（建议值：10-50）
	ScrollId string `json:"scroll_id,optional"` // 游标 ID
}

// ShopPrinterListResp 查询门店打印机列表响应数据（三方返回给麦芽田）
// 三方返回麦芽田的打印机设备列表
type ShopPrinterListResp struct {
	Data      []PrinterItem `json:"data"`                // 打印机设备列表
	Page      int           `json:"page,optional"`       // 当前页码
	TotalPage int           `json:"total_page,optional"` // 总页数
	Total     int           `json:"total,optional"`      // 打印机总数
	IsLast    bool          `json:"isLast,optional"`     // 是否最后一页
	ScrollId  string        `json:"scroll_id,optional"`  // 游标 ID
}

// PrinterItem 打印机设备信息
// 单个打印机设备的详细配置
type PrinterItem struct {
	ShopId      string `json:"shop_id"`      // 平台方渠道 ID
	Brand       string `json:"brand"`        // 打印机品牌
	Name        string `json:"name"`         // 打印机名称
	MachineCode string `json:"machine_code"` // 打印机设备编号
	MachineSign string `json:"machine_sign"` // 打印机设备密钥
	PrintNumber int    `json:"print_number"` // 打印份数
	Width       string `json:"width"`        // 打印机纸张宽度
}
