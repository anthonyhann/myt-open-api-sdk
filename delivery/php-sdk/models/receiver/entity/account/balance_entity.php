<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/account
 * @ClassName: balance_entity
 * @Description: 麦芽田配送开放平台SDK - 查询当前账号余额实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\account;

/**
 * BalanceReq 查询账号余额请求参数
 *
 * command: balance
 * 必接: 是
 * 说明: 麦芽田平台调用三方配送服务查询账号余额
 */
class BalanceReq
{
    /**
     * 平台方渠道ID
     * 三方平台账号ID
     * @var string
     */
    public $shop_id;

    /**
     * 麦芽田商户token
     * 用于验证身份的访问令牌，可选
     * @var string
     */
    public $token;
}

/**
 * BalanceResp 查询账号余额响应参数
 */
class BalanceResp
{
    /**
     * 余额金额
     * 单位：分
     * @var int
     */
    public $balance;

    /**
     * 查询时间
     * Unix时间戳（秒）
     * @var int
     */
    public $at_time;

    /**
     * BalanceResp constructor.
     */
    public function __construct()
    {
        $this->at_time = time();
    }
}
