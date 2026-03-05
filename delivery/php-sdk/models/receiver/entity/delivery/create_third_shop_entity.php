<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: create_third_shop_entity
 * @Description: 创建三方侧门店实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * CreateThirdShopReq 创建三方门店请求参数
 *
 * command: create_third_shop
 * 必接: 是
 * 说明: 麦芽田平台调用三方配送服务同步门店信息
 * 注意: 如需修改门店信息，也调用该接口
 */
class CreateThirdShopReq
{
    /**
     * 麦芽田方门店ID
     * 麦芽田平台的门店唯一标识
     * @var int
     */
    public $shop_id;

    /**
     * 门店名称
     * 门店的商业名称
     * @var string
     */
    public $name;

    /**
     * 联系电话
     * 门店联系电话
     * @var string
     */
    public $phone;

    /**
     * 省
     * 省级行政区名称，示例: "陕西省"
     * @var string
     */
    public $province;

    /**
     * 省码
     * 省级行政区编码
     * @var int
     */
    public $province_code;

    /**
     * 市
     * 市级行政区名称，示例: "西安市"
     * @var string
     */
    public $city;

    /**
     * 市码
     * 市级行政区编码
     * @var int
     */
    public $city_code;

    /**
     * 区
     * 区县级行政区名称，示例: "莲湖区"
     * @var string
     */
    public $district;

    /**
     * 区码
     * 区县级行政区编码
     * @var int
     */
    public $district_code;

    /**
     * 详细地址
     * 门店的详细地址信息
     * @var string
     */
    public $address;

    /**
     * 坐标类型
     * 1: 高德地图; 2: 百度地图
     * @var string
     */
    public $map_type;

    /**
     * 店铺分类
     * 麦芽田枚举，如: xiaochi, dangao等
     * 详见店铺分类枚举
     * @var string
     */
    public $category;

    /**
     * 经度
     * 门店位置经度，国测局02标准（如高德地图坐标系）
     * @var string
     */
    public $longitude;

    /**
     * 纬度
     * 门店位置纬度，国测局02标准（如高德地图坐标系）
     * @var string
     */
    public $latitude;
}

/**
 * CreateThirdShopResp 创建三方门店响应参数
 */
class CreateThirdShopResp
{
    /**
     * 门店ID
     * 三方配送服务商系统中的门店唯一标识
     * @var string
     */
    public $store_id;
}
