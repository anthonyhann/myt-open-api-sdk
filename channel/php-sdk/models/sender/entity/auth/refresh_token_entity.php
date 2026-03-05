<?php

namespace Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Auth;

/**
 * 刷新访问令牌请求实体
 * 三方服务使用刷新令牌向麦芽田重新获取新的访问令牌
 */
class RefreshTokenReq
{
    /**
     * 访问令牌
     * 原有的访问令牌，即将过期或已过期
     * @var string
     */
    public $token;

    /**
     * 刷新授权token
     * 由麦芽田授权服务器返回，用于刷新访问令牌
     * @var string
     */
    public $refresh_token;
}

/**
 * 刷新访问令牌响应实体
 */
class RefreshTokenResp
{
    /**
     * 新的访问令牌
     * 有效期 30 天
     * @var string
     */
    public $token;

    /**
     * 新的刷新授权token
     * 用于再次刷新访问令牌，有效期 30 天
     * @var string
     */
    public $refresh_token;

    /**
     * 新token的过期时间
     * Unix时间戳，单位：秒
     * @var int
     */
    public $expire_time;

    /**
     * 新refresh_token的过期时间
     * Unix时间戳，单位：秒
     * @var int
     */
    public $refresh_expire_time;
}
