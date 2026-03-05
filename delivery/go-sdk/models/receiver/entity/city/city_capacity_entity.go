/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: city_capacity_entity
 * @Description: 获取城市运力实体定义
 * @Version 1.0
 */

package entity

// CityResp 城市运力响应参数
// command: city_capacity
// 必接: 否（建议对接）
// 说明: 麦芽田平台调用三方配送服务获取支持配送的城市列表
type CityResp struct {
	// Data 城市列表
	// 配送服务商支持配送的所有城市信息
	Data []*CityInfo `json:"data"`
}

// CityInfo 城市信息
type CityInfo struct {
	// CityName 城市名称
	// 示例: "北京市"、"上海"
	CityName string `json:"city_name"`

	// CityCode 城市编码
	// 标准城市行政编码，示例: "100010"
	CityCode string `json:"city_code"`
}
