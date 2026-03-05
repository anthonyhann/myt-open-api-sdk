<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: tips_entity
 * @Description: 麦芽田配送开放平台SDK - 添加小费接口实体
 * 提供添加小费接口的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * TipsRequest 添加小费请求实体
 */
class TipsRequest
{
    /**
     * 麦芽田侧运单号
     * @var string
     */
    public $source_order_no;

    /**
     * 小费金额（分）
     * @var int
     */
    public $tips;

    /**
     * TipsRequest constructor.
     *
     * @param string $sourceOrderNo 麦芽田侧运单号
     * @param int $tips 小费金额（分）
     */
    public function __construct(string $sourceOrderNo, int $tips)
    {
        $this->source_order_no = $sourceOrderNo;
        $this->tips = $tips;
    }
}

/**
 * TipsResponse 添加小费响应实体
 */
class TipsResponse
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
