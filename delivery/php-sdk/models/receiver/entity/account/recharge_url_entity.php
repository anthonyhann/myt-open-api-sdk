<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/account
 * @ClassName: recharge_url_entity
 * @Description: 在线充值链接实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\account;

/**
 * RechargeUrlReq 获取充值链接请求参数
 *
 * command: recharge_url
 * 必接: 是
 * 说明: 麦芽田平台调用三方配送服务获取在线充值链接
 */
class RechargeUrlReq
{
    /**
     * 平台方渠道ID
     * 三方平台账号ID
     * @var string
     */
    public $shop_id;
}

/**
 * RechargeUrlResp 获取充值链接响应参数
 */
class RechargeUrlResp
{
    /**
     * 三方充值链接
     * 用于跳转到第三方充值页面的URL
     * @var string
     */
    public $recharge_url;

    /**
     * 生成时间
     * Unix时间戳（秒）
     * @var int
     */
    public $at_time;

    /**
     * RechargeUrlResp constructor.
     */
    public function __construct()
    {
        $this->at_time = time();
    }
}
