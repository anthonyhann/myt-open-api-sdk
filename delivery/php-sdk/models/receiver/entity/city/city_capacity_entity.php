<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/receiver/entity/city
 * @ClassName: city_capacity_entity
 * @Description: 麦芽田配送开放平台SDK - 获取城市运力实体
 * 提供获取城市运力的请求和响应实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\city;

/**
 * CityCapacityResponse 获取城市运力响应实体
 */
class CityCapacityResponse
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

    /**
     * 城市列表
     * @var array<CityInfo>
     */
    public $data;
}

/**
 * CityInfo 城市信息实体
 */
class CityInfo
{
    /**
     * 城市名称
     * @var string
     */
    public $city_name;

    /**
     * 城市编码
     * @var string
     */
    public $city_code;

    /**
     * CityInfo constructor.
     *
     * @param string $cityName 城市名称
     * @param string $cityCode 城市编码
     */
    public function __construct(string $cityName = '', string $cityCode = '')
    {
        $this->city_name = $cityName;
        $this->city_code = $cityCode;
    }
}
