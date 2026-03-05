<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: models/receiver/entity/delivery
 * @ClassName: service_pkg_list_entity
 * @Description: 获取服务包列表实体定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\receiver\entity\delivery;

/**
 * ServicePkg 服务包信息
 */
class ServicePkg
{
    /**
     * 服务包名称
     * 示例: "滴滴跑腿直送"、"滴滴跑腿特惠"
     * @var string
     */
    public $name;

    /**
     * 服务包标识
     * 开放平台对外服务包:
     * - base: 经济配送
     * - direct: 专人直送
     * 详见服务包枚举
     * @var string
     */
    public $service_pkg;
}

/**
 * ServicePkgListReq 获取服务包列表请求参数
 *
 * command: service_pkg_list
 * 必接: 否（建议对接）
 * 说明: 麦芽田平台调用三方配送服务获取可用的服务包列表
 */
class ServicePkgListReq
{
    /**
     * 三方平台账号ID
     * 商户或门店的唯一标识
     * @var string
     */
    public $shop_id;

    /**
     * 城市code
     * 标准城市行政编码，示例: "10100"
     * @var string
     */
    public $city;
}

/**
 * ServicePkgListResp 获取服务包列表响应参数
 */
class ServicePkgListResp
{
    /**
     * 服务包列表
     * 可用的服务包信息列表
     * @var array|ServicePkg[]
     */
    public $data;
}
