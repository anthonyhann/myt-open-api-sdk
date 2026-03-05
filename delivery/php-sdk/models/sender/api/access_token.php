<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/api
 * @ClassName: access_token
 * @Description: 麦芽田配送开放平台SDK - 授权码获取access_token接口
 * 提供通过授权码获取access_token和refresh_token的功能
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\api;

use Maiyatian\Delivery\SDK\client\HTTPClientManager;
use Maiyatian\Delivery\SDK\models\types\Constants;

class AccessToken extends Base
{
    /**
     * 使用授权码获取access_token和refresh_token
     *
     * @param string $code 授权码
     * @param string $grantType 授权级别 1:门店 2:商户 3:三方门店直联授权
     * @param string $mobile 三方平台账号注册手机号
     * @param string $storeId 三方平台最小维度的唯一ID
     * @param string $city 城市名称
     * @param string $cityCode 城市编码
     * @param string $sourceKey 三方平台最小维度的唯一ID对应密钥
     * @param string $platform 快递平台唯一标识
     * @return HTTPClientManager\HTTPResponse
     */
    public function getAccessToken(
        string $code,
        string $grantType = Constants::GRANT_TYPE_STORE,
        string $mobile = '',
        string $storeId = '',
        string $city = '',
        string $cityCode = '',
        string $sourceKey = '',
        string $platform = ''
    ): HTTPClientManager\HTTPResponse {
        $data = [
            'grant_type' => $grantType,
            'code' => $code,
            'mobile' => $mobile,
            'store_id' => $storeId,
            'city' => $city,
            'city_code' => $cityCode,
            'source_key' => $sourceKey,
            'platform' => $platform,
        ];

        return $this->post(Constants::COMMAND_ACCESS_TOKEN, $data);
    }
}
