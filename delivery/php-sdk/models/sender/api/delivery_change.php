<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/api
 * @ClassName: delivery_change
 * @Description: 麦芽田配送开放平台SDK - 配送状态同步接口
 * 提供同步订单配送状态操作的功能
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\api;

use Maiyatian\Delivery\SDK\client\HTTPClientManager;
use Maiyatian\Delivery\SDK\models\types\Constants;

class DeliveryChange extends Base
{
    /**
     * 同步订单配送状态
     *
     * @param string $orderNo 配送单号
     * @param string $sourceOrderNo 三方订单号
     * @param string $shopId 三方商户ID、门店ID
     * @param string $status 配送状态
     * @param string $riderName 骑手名称
     * @param string $riderPhone 骑手电话
     * @param string $longitude 经度
     * @param string $latitude 纬度
     * @param string $pickupCode 取货码
     * @param int $distance 配送距离（单位：米）
     * @param int $deliveryFee 配送费（单位：分）
     * @param int $cancelType 取消类型
     * @param string $cancelReason 取消原因
     * @param int $cancelDeditAmount 取消订单违约金（单位：分）
     * @param array $vehicleInfo 车辆信息
     * @param int $updateTime 更新时间
     * @param bool $isTransship 是否接驳单
     * @return HTTPClientManager\HTTPResponse
     */
    public function syncDeliveryStatus(
        string $orderNo,
        string $sourceOrderNo,
        string $shopId,
        string $status,
        string $riderName = '',
        string $riderPhone = '',
        string $longitude = '',
        string $latitude = '',
        string $pickupCode = '',
        int $distance = 0,
        int $deliveryFee = 0,
        int $cancelType = 0,
        string $cancelReason = '',
        int $cancelDeditAmount = 0,
        array $vehicleInfo = [],
        int $updateTime = 0,
        bool $isTransship = false
    ): HTTPClientManager\HTTPResponse {
        if ($updateTime === 0) {
            $updateTime = time();
        }

        $data = [
            'order_no' => $orderNo,
            'source_order_no' => $sourceOrderNo,
            'shop_id' => $shopId,
            'status' => $status,
            'rider_name' => $riderName,
            'rider_phone' => $riderPhone,
            'longitude' => $longitude,
            'latitude' => $latitude,
            'pickup_code' => $pickupCode,
            'distance' => $distance,
            'delivery_fee' => $deliveryFee,
            'cancel_type' => $cancelType,
            'cancel_reason' => $cancelReason,
            'cancel_dedit_amount' => $cancelDeditAmount,
            'vehicle_info' => $vehicleInfo,
            'update_time' => $updateTime,
            'is_transship' => $isTransship,
        ];

        return $this->post(Constants::COMMAND_DELIVERY_CHANGE, $data);
    }
}
