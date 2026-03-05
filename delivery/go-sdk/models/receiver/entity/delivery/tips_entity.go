/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: entity
 * @ClassName: tips_entity
 * @Description: 添加小费实体定义
 * @Version 1.0
 */

package entity

// TipsReq 添加小费请求参数
// command: tips
// 必接: 是
// 说明: 麦芽田平台调用三方配送服务为订单添加小费
type TipsReq struct {
	// SourceOrderNo 麦芽田侧运单号
	// 麦芽田平台的订单号
	SourceOrderNo string `json:"source_order_no"`

	// Tips 小费金额
	// 单位：分
	// 注意：采用累加方式，每次调用会在原有基础上增加
	Tips int64 `json:"tips"`
}
