<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/account
 * @ClassName: token_unbind_entity
 * @Description: 授权解绑实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\account;

/**
 * TokenUnbindReq 授权解绑请求参数
 *
 * command: token_unbind
 * 必接: 是
 * 说明: 当用户解绑授权时，麦芽田平台调用此接口通知三方配送服务
 */
class TokenUnbindReq
{
    /**
     * 平台方渠道ID
     * 需要解绑的商户ID
     * @var string
     */
    public $shop_id;

    /**
     * 麦芽田商户token
     * 需要解绑的访问令牌
     * @var string
     */
    public $token;
}

/**
 * TokenUnbindResp 授权解绑响应参数
 */
class TokenUnbindResp
{
    /**
     * 平台方渠道ID
     * 已解绑的商户ID
     * @var string
     */
    public $shop_id;

    /**
     * 麦芽田商户token
     * 已解绑的访问令牌
     * @var string
     */
    public $token;
}
