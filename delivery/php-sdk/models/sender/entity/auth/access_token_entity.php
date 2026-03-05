<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/entity/auth
 * @ClassName: access_token_entity
 * @Description: 麦芽田配送开放平台SDK - 授权码获取access_token实体
 * 提供授权码获取access_token的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\entity\auth;

/**
 * AccessTokenRequest 授权码获取access_token请求实体
 */
class AccessTokenRequest
{
    /**
     * 授权级别 1:门店 2:商户 3:三方门店直联授权
     * @var string
     */
    public $grant_type;

    /**
     * 授权码
     * @var string
     */
    public $code;

    /**
     * 三方平台账号注册手机号
     * @var string
     */
    public $mobile;

    /**
     * 三方平台最小维度的唯一ID
     * @var string
     */
    public $store_id;

    /**
     * 城市名称
     * @var string
     */
    public $city;

    /**
     * 城市编码
     * @var string
     */
    public $city_code;

    /**
     * 三方平台最小维度的唯一ID对应密钥
     * @var string
     */
    public $source_key;

    /**
     * 快递平台唯一标识
     * @var string
     */
    public $platform;

    /**
     * AccessTokenRequest constructor.
     *
     * @param string $code 授权码
     * @param string $grantType 授权级别
     */
    public function __construct(string $code, string $grantType = '1')
    {
        $this->code = $code;
        $this->grant_type = $grantType;
    }
}

/**
 * AccessTokenResponse 授权码获取access_token响应实体
 */
class AccessTokenResponse
{
    /**
     * 平台方渠道ID
     * @var string
     */
    public $shop_id;

    /**
     * 麦芽田授权token
     * @var string
     */
    public $token;

    /**
     * 刷新授权token
     * @var string
     */
    public $refresh_token;

    /**
     * token过期时间(30天)
     * @var int
     */
    public $expire_time;

    /**
     * refresh_token过期时间(30天)
     * @var int
     */
    public $refresh_expire_time;
}
