<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/entity/auth
 * @ClassName: refresh_token_entity
 * @Description: 麦芽田配送开放平台SDK - refreshToken刷新accessToken实体
 * 提供refreshToken刷新accessToken的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\entity\auth;

/**
 * RefreshTokenRequest refreshToken刷新accessToken请求实体
 */
class RefreshTokenRequest
{
    /**
     * 访问令牌
     * @var string
     */
    public $token;

    /**
     * 刷新授权token
     * @var string
     */
    public $refresh_token;

    /**
     * RefreshTokenRequest constructor.
     *
     * @param string $token 访问令牌
     * @param string $refreshToken 刷新授权token
     */
    public function __construct(string $token, string $refreshToken)
    {
        $this->token = $token;
        $this->refresh_token = $refreshToken;
    }
}

/**
 * RefreshTokenResponse refreshToken刷新accessToken响应实体
 */
class RefreshTokenResponse
{
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
