<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: precancel_entity
 * @Description: 麦芽田配送开放平台SDK - 预取消配送接口实体
 * 提供预取消配送接口的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * PrecancelRequest 预取消配送请求实体
 */
class PrecancelRequest
{
    /**
     * 麦芽田侧运单号
     * @var string
     */
    public $source_order_no;

    /**
     * 取消原因
     * @var string
     */
    public $reason;

    /**
     * PrecancelRequest constructor.
     *
     * @param string $sourceOrderNo 麦芽田侧运单号
     */
    public function __construct(string $sourceOrderNo)
    {
        $this->source_order_no = $sourceOrderNo;
    }
}

/**
 * PrecancelResponse 预取消配送响应实体
 */
class PrecancelResponse
{
    /**
     * 状态码
     * @var int
     */
    public $code;

    /**
     * 响应消息
     * @var string
     */
    public $message;
}
