<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Shop;

/**
 * 查询麦芽田门店信息请求参数
 * 三方服务向麦芽田查询门店信息
 * 
 * 使用场景：
 *   三方查询门店在麦芽田平台的基本信息和状态
 */
class ShopInfoReq
{
    /**
     * 平台方渠道 ID（三方平台的门店标识）
     * @var string
     */
    public $shop_id;
}

/**
 * 查询麦芽田门店信息响应数据
 * 麦芽田返回的门店基本信息和营业状态
 */
class ShopInfoResp
{
    /**
     * 平台方渠道 ID
     * @var string
     */
    public $shop_id;

    /**
     * 门店名称
     * @var string
     */
    public $shop_name;

    /**
     * 商户名称
     * @var string
     */
    public $merchant_name;

    /**
     * 门店联系电话
     * @var string
     */
    public $phone;

    /**
     * 门店状态：
     * 1-营业中，2-休息中，3-已打烊
     * @var int
     */
    public $status;
}
