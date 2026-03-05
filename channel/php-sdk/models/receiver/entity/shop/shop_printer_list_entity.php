<?php

namespace MytChannel\Models\Receiver\Entity\Shop;

/**
 * 打印机设备信息
 * 单个打印机设备的详细配置
 */
class PrinterItem {
    /**
     * @var string 平台方渠道 ID
     */
    public $shop_id;
    
    /**
     * @var string 打印机品牌
     */
    public $brand;
    
    /**
     * @var string 打印机名称
     */
    public $name;
    
    /**
     * @var string 打印机设备编号
     */
    public $machine_code;
    
    /**
     * @var string 打印机设备密钥
     */
    public $machine_sign;
    
    /**
     * @var int 打印份数
     */
    public $print_number;
    
    /**
     * @var string 打印机纸张宽度
     */
    public $width;
}

/**
 * 查询门店打印机列表请求参数（三方接收麦芽田请求）
 * 麦芽田向三方查询打印机列表时的请求参数
 * 
 * 使用场景：
 *   麦芽田查询三方门店绑定的打印机设备
 *
 * 注意事项：
 *   - 门店必须先在打印机平台后台绑定打印机
 */
class ShopPrinterListReq {
    /**
     * @var string 平台方渠道 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var int 页码（从 1 开始）
     */
    public $page;
    
    /**
     * @var int 每页数量（建议值：10-50）
     */
    public $page_size;
    
    /**
     * @var string 游标 ID
     */
    public $scroll_id;
}

/**
 * 查询门店打印机列表响应数据（三方返回给麦芽田）
 * 三方返回麦芽田的打印机设备列表
 */
class ShopPrinterListResp {
    /**
     * @var PrinterItem[] 打印机设备列表
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
     * @var int 打印机总数
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
