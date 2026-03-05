<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Auth;

/**
 * 获取访问令牌请求实体
 * 三方服务使用授权码 code 向麦芽田换取访问令牌
 */
class AccessTokenReq
{
    /**
     * 授权类型
     * 默认值: shop
     * @var string
     */
    public $grant_type = 'shop';

    /**
     * 授权码
     * 由麦芽田授权服务器返回，有效期 5 分钟，只能使用一次
     * @var string
     */
    public $code;

    /**
     * 平台方渠道ID
     * 三方平台的店铺唯一标识
     * @var string
     */
    public $shop_id;

    /**
     * 订单分类
     * 麦芽田枚举值，如 yinpin（饮品）、shaokao（烧烤）等
     * @var string
     */
    public $category;

    /**
     * 门店名称
     * 店铺的中文名称
     * @var string
     */
    public $name;

    /**
     * 店铺类型
     * 可选值：waimai（外卖）、shop（堂食）、other（其他）
     * @var string
     */
    public $type;

    /**
     * 经度
     * 国测局02标准，如高德地图坐标系
     * @var string
     */
    public $longitude;

    /**
     * 纬度
     * 国测局02标准，如高德地图坐标系
     * @var string
     */
    public $latitude;
}

/**
 * 获取访问令牌响应实体
 */
class AccessTokenResp
{
    /**
     * 平台方渠道ID
     * @var string
     */
    public $shop_id;

    /**
     * 麦芽田授权token
     * 访问麦芽田API的凭证，有效期 30 天
     * @var string
     */
    public $token;

    /**
     * 刷新授权token
     * 用于刷新访问令牌，有效期 30 天
     * @var string
     */
    public $refresh_token;

    /**
     * token过期时间
     * Unix时间戳，单位：秒
     * @var int
     */
    public $expire_time;

    /**
     * refresh_token过期时间
     * Unix时间戳，单位：秒
     * @var int
     */
    public $refresh_expire_time;
}
