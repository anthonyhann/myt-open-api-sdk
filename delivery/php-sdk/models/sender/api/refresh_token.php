<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/api
 * @ClassName: refresh_token
 * @Description: 麦芽田配送开放平台SDK - refreshToken刷新accessToken接口
 * 提供使用refreshToken刷新accessToken的功能
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\api;

use Maiyatian\Delivery\SDK\client\HTTPClientManager;
use Maiyatian\Delivery\SDK\models\types\Constants;

class RefreshToken extends Base
{
    /**
     * 使用refreshToken刷新accessToken
     *
     * @param string $token 访问令牌
     * @param string $refreshToken 刷新授权token
     * @return HTTPClientManager\HTTPResponse
     */
    public function refreshToken(
        string $token,
        string $refreshToken
    ): HTTPClientManager\HTTPResponse {
        $data = [
            'token' => $token,
            'refresh_token' => $refreshToken,
        ];

        return $this->post(Constants::COMMAND_REFRESH_TOKEN, $data);
    }
}
