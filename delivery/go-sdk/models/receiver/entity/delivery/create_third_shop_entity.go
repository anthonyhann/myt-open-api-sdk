/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: create_third_shop_entity
 * @Description: 创建三方侧门店实体定义
 * @Version 1.0
 */

package entity

// CreateThirdShopReq 创建三方门店请求参数
// command: create_third_shop
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务同步门店信息
// 注意: 如需修改门店信息，也调用该接口
type CreateThirdShopReq struct {
	// ShopId 麦芽田方门店ID
	// 麦芽田平台的门店唯一标识
	ShopId int64 `json:"shop_id" validate:"required"`

	// ShopName 门店名称
	// 门店的商业名称
	ShopName string `json:"name" validate:"required"`

	// Phone 联系电话
	// 门店联系电话
	Phone string `json:"phone" validate:"required"`

	// Province 省
	// 省级行政区名称，示例: "陕西省"
	Province string `json:"province"`

	// ProvinceCode 省码
	// 省级行政区编码
	ProvinceCode int64 `json:"province_code"`

	// City 市
	// 市级行政区名称，示例: "西安市"
	City string `json:"city"`

	// CityCode 市码
	// 市级行政区编码
	CityCode int64 `json:"city_code"`

	// District 区
	// 区县级行政区名称，示例: "莲湖区"
	District string `json:"district"`

	// DistrictCode 区码
	// 区县级行政区编码
	DistrictCode int64 `json:"district_code"`

	// Address 详细地址
	// 门店的详细地址信息
	Address string `json:"address"`

	// MapType 坐标类型
	// 1: 高德地图; 2: 百度地图
	MapType string `json:"map_type"`

	// Category 店铺分类
	// 麦芽田枚举，如: xiaochi, dangao等
	// 详见店铺分类枚举
	Category string `json:"category"`

	// Longitude 经度
	// 门店位置经度，国测局02标准（如高德地图坐标系）
	Longitude string `json:"longitude" validate:"required"`

	// Latitude 纬度
	// 门店位置纬度，国测局02标准（如高德地图坐标系）
	Latitude string `json:"latitude" validate:"required"`
}

// CreateThirdShopResp 创建三方门店响应参数
type CreateThirdShopResp struct {
	// StoreId 门店ID
	// 三方配送服务商系统中的门店唯一标识
	StoreId string `json:"store_id"`
}
