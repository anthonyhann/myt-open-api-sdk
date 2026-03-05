<?php

namespace MytChannel\Models\Receiver\Entity\Shop;

/**
 * 门店信息项
 * 门店列表中的单个门店信息
 */
class ShopItem {
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
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

/**
 * 查询门店列表请求参数（三方接收麦芽田请求）
 * 麦芽田向三方查询门店列表时的请求参数
 * 
 * 使用场景：
 *   麦芽田查询三方商户名下的所有门店
 *
 * 分页方式：
 *   - 普通分页：使用 Page 和 PageSize
 *   - 游标分页：使用 ScrollId
 */
class ShopListReq {
    /**
     * @var int 页码（从 1 开始）
     */
    public $page;
    
    /**
     * @var int 每页数量（建议值：10-100）
     */
    public $page_size;
    
    /**
     * @var string 游标 ID
     */
    public $scroll_id;
}

/**
 * 查询门店列表响应数据（三方返回给麦芽田）
 * 三方返回给麦芽田的分页门店列表
 */
class ShopListResp {
    /**
     * @var ShopItem[] 门店数据列表
     */
    public $data = [];
    
    /**
     * @var int 当前页码
     */
    public $page;
    
    /**
     * @var int 总页数
     */
    public $total_page;
    
    /**
     * @var int 门店总数
     */
    public $total;
    
    /**
     * @var bool 是否最后一页
     */
    public $is_last;
    
    /**
     * @var string 游标 ID
     */
    public $scroll_id;
}
