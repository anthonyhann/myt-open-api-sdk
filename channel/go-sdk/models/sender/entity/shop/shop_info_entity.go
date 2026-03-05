// Package entity 提供三方服务主动调用麦芽田门店接口的请求参数定义
package entity

// ShopInfoReq 查询麦芽田门店信息请求参数
// 三方服务向麦芽田查询门店信息
//
// 使用场景：
//
//	三方查询门店在麦芽田平台的基本信息和状态
type ShopInfoReq struct {
	ShopId string `json:"shop_id" validate:"required"` // 平台方渠道 ID（三方平台的门店标识）
}

// ShopInfoResp 查询麦芽田门店信息响应数据
// 麦芽田返回的门店基本信息和营业状态
type ShopInfoResp struct {
	ShopId       string `json:"shop_id"`       // 平台方渠道 ID
	ShopName     string `json:"shop_name"`     // 门店名称
	MerchantName string `json:"merchant_name"` // 商户名称
	Phone        string `json:"phone"`         // 门店联系电话
	Status       int64  `json:"status"`        // 门店状态：1-营业中，2-休息中，3-已打烊
}
