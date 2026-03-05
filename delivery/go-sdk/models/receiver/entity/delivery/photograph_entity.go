/**
* @Author Hanqiang
* @Date 2025/12/26 16:20
* @PackageName:
* @ClassName: photograph_entity
* @Description: TODO
* @Version 1.0
 */

package entity

type PhotographReq struct {
	OrderNo       string `json:"order_no"`
	SourceOrderNo string `json:"source_order_no"`
	Type          int64  `json:"type,default=0,optional"`
}

type PhotographResp struct {
	OrderNo        string   `json:"order_no"`
	SourceOrderNo  string   `json:"source_order_no"`
	RiderName      string   `json:"rider_name"`
	RiderPhone     string   `json:"rider_phone"`
	PickupPhotos   []string `json:"pickup_photos"`
	DeliveryPhotos []string `json:"delivery_photos"`
}
