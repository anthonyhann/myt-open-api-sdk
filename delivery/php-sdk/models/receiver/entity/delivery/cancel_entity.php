<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: cancel_entity
 * @Description: 麦芽田配送开放平台SDK - 取消配送接口实体
 * 提供取消配送接口的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * CancelRequest 取消配送请求实体
 */
class CancelRequest
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
     * 取消类型
     * @var int
     */
    public $type;

    /**
     * CancelRequest constructor.
     *
     * @param string $sourceOrderNo 麦芽田侧运单号
     */
    public function __construct(string $sourceOrderNo)
    {
        $this->source_order_no = $sourceOrderNo;
    }
}

/**
 * CancelResponse 取消配送响应实体
 */
class CancelResponse
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
