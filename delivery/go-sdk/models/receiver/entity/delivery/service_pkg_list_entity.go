/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: service_pkg_list_entity
 * @Description: 获取服务包列表实体定义
 * @Version 1.0
 */

package entity

// ServicePkgListReq 获取服务包列表请求参数
// command: service_pkg_list
// 必接: 否（建议对接）
// 说明: 麦芽田平台调用三方配送服务获取可用的服务包列表
type ServicePkgListReq struct {
	// ShopId 三方平台账号ID
	// 商户或门店的唯一标识
	ShopId string `json:"shop_id"`

	// City 城市code
	// 标准城市行政编码，示例: "10100"
	City string `json:"city"`
}

// ServicePkgListResp 获取服务包列表响应参数
type ServicePkgListResp struct {
	// Data 服务包列表
	// 可用的服务包信息列表
	Data []*ServicePkg `json:"data"`
}

// ServicePkg 服务包信息
type ServicePkg struct {
	// Name 服务包名称
	// 示例: "滴滴跑腿直送"、"滴滴跑腿特惠"
	Name string `json:"name"`

	// ServicePkg 服务包标识
	// 开放平台对外服务包:
	// - base: 经济配送
	// - direct: 专人直送
	// 详见服务包枚举
	ServicePkg string `json:"service_pkg"`
}
