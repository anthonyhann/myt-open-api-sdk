<?php

namespace MytChannel\Models\Receiver\Entity\Shop;

/**
 * 查询门店详情请求参数（三方接收麦芽田请求）
 * 麦芽田向三方查询门店详情时的请求参数
 * 
 * 使用场景：
 *   查询三方门店基本信息
 *
 * 注意事项：
 *   - 此结构用于麦芽田调用三方的查询接口
 */
class ShopDetailReq {
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
}

/**
 * 查询门店详情响应数据（三方返回给麦芽田）
 * 三方返回给麦芽田的门店完整信息
 */
class ShopDetailResp {
    /**
     * @var string 平台方渠道 ID
     */
    public $shop_id;
    
    /**
     * @var string 门店名称
     */
    public $name;
    
    /**
     * @var string 门店联系电话
     */
    public $phone;
    
    /**
     * @var string 省份
     */
    public $province;
    
    /**
     * @var string 城市
     */
    public $city;
    
    /**
     * @var string 详细地址
     */
    public $address;
    
    /**
     * @var string 门店纬度（国测局 GCJ-02 标准）
     */
    public $latitude;
    
    /**
     * @var string 门店经度（国测局 GCJ-02 标准）
     */
    public $longitude;
}
