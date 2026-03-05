// Package entity 提供三方服务主动推送给麦芽田的订单数据结构定义
package entity

// OrderApplyRefundReq 订单申请退款推送请求参数
// 三方服务推送退款申请给麦芽田
//
// 使用场景：
//
//	用户或商家发起退款申请，三方推送退款申请给麦芽田
//
// 注意事项：
//   - 支持部分退款（按商品退款）
type OrderApplyRefundReq struct {
	OrderId    string                      `json:"order_id" validate:"required"` // 平台方订单唯一 ID（三方平台的订单号）
	ShopId     string                      `json:"shop_id" validate:"required"`  // 平台方渠道 ID（三方平台的门店标识）
	RefundId   string                      `json:"refund_id,optional"`           // 退款单 ID
	Type       int                         `json:"type"`                         // 退款类型：1-仅退款，2-退货退款
	Reason     string                      `json:"reason"`                       // 退款原因描述
	TotalPrice int                         `json:"total_price"`                  // 申请退款总金额（单位：分）
	Count      int                         `json:"count"`                        // 退款商品总数量
	Goods      []OrderApplyRefundGoodsInfo `json:"goods"`                        // 退款商品列表
	UpdateTime int64                       `json:"update_time"`                  // 申请时间（Unix 时间戳，单位：秒）
}

// OrderApplyRefundGoodsInfo 退款商品信息
// 包含退款商品的详细信息和退款金额
type OrderApplyRefundGoodsInfo struct {
	GoodsId       string `json:"goods_id"`        // 平台方商品 ID
	GoodsName     string `json:"goods_name"`      // 商品名称
	SkuId         string `json:"sku_id"`          // 平台方规格 ID
	Upc           string `json:"upc"`             // 商品统一编码（UPC 条形码）
	ShelfNo       string `json:"shelf_no"`        // 货架号
	Number        int    `json:"number"`          // 退款数量
	GoodsTotalFee int    `json:"goods_total_fee"` // 商品总价（单位：分）
	PackageNumber int    `json:"package_number"`  // 包装盒数量
	PackageFee    int    `json:"package_fee"`     // 包装盒费用（单位：分）
	RefundFee     int    `json:"refund_fee"`      // 退款金额（单位：分）
}
