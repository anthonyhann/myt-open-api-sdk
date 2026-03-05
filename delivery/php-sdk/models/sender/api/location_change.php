<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/api
 * @ClassName: location_change
 * @Description: 麦芽田配送开放平台SDK - 快递轨迹回传接口
 * 提供快递轨迹回传功能
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\api;

use Maiyatian\Delivery\SDK\client\HTTPClientManager;
use Maiyatian\Delivery\SDK\models\types\Constants;

class LocationChange extends Base
{
    /**
     * 回传快递轨迹
     *
     * @param string $orderNo 配送单号
     * @param string $sourceOrderNo 麦芽田侧单号
     * @param string $shopId 三方商户ID、门店ID
     * @param string $tag 标签
     * @param array $locations 轨迹列表
     * @return HTTPClientManager\HTTPResponse
     */
    public function reportLocation(
        string $orderNo,
        string $sourceOrderNo,
        string $shopId,
        array $locations,
        string $tag = ''
    ): HTTPClientManager\HTTPResponse {
        $data = [
            'order_no' => $orderNo,
            'source_order_no' => $sourceOrderNo,
            'shop_id' => $shopId,
            'tag' => $tag,
            'locations' => $locations,
        ];

        return $this->post(Constants::COMMAND_LOCATION_CHANGE, $data);
    }

    /**
     * 创建轨迹点
     *
     * @param string $description 描述
     * @param string $city 城市
     * @param string $longitude 经度
     * @param string $latitude 纬度
     * @param string $status 状态
     * @param string $remark 备注
     * @param int $updateTime 更新时间
     * @return array
     */
    public function createLocationPoint(
        string $description,
        string $city = '',
        string $longitude = '',
        string $latitude = '',
        string $status = Constants::TRACK_STATUS_DELIVERING,
        string $remark = '',
        int $updateTime = 0
    ): array {
        if ($updateTime === 0) {
            $updateTime = time();
        }

        return [
            'description' => $description,
            'city' => $city,
            'longitude' => $longitude,
            'latitude' => $latitude,
            'status' => $status,
            'remark' => $remark,
            'update_time' => $updateTime,
        ];
    }
}
