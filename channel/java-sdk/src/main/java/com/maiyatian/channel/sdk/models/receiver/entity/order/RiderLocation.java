/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 骑手位置信息
 * <p>
 * 单个订单的骑手位置和配送状态
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用于三方系统接收和更新订单的骑手位置信息
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>位置坐标采用国测局 GCJ-02 标准</li>
 * <li>距离单位为米</li>
 * <li>时间戳单位为秒</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RiderLocation {

    /**
     * 平台方订单唯一 ID（三方平台的订单号）
     */
    @NotBlank(message = "订单ID不能为空")
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 配送状态
     */
    @NotBlank(message = "配送状态不能为空")
    @JsonProperty("status")
    private String status;

    /**
     * 骑手姓名
     */
    @NotBlank(message = "骑手姓名不能为空")
    @JsonProperty("rider_name")
    private String riderName;

    /**
     * 骑手电话
     */
    @NotBlank(message = "骑手电话不能为空")
    @JsonProperty("rider_phone")
    private String riderPhone;

    /**
     * 物流单号
     */
    @NotBlank(message = "物流单号不能为空")
    @JsonProperty("logistic_no")
    private String logisticNo;

    /**
     * 物流标识
     */
    @NotBlank(message = "物流标识不能为空")
    @JsonProperty("logistic_tag")
    private String logisticTag;

    /**
     * 骑手当前经度（国测局 GCJ-02 标准）
     */
    @JsonProperty("longitude")
    private String longitude;

    /**
     * 骑手当前纬度（国测局 GCJ-02 标准）
     */
    @JsonProperty("latitude")
    private String latitude;

    /**
     * 骑手距离顾客的距离（单位：米）
     */
    @JsonProperty("distance")
    private Integer distance;

    /**
     * 是否快递配送
     */
    @JsonProperty("is_express")
    private Boolean isExpress;

    /**
     * 位置更新时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public RiderLocation() {
    }

    /**
     * 全参构造函数
     */
    public RiderLocation(String orderId, String shopId, String status, String riderName, 
                        String riderPhone, String logisticNo, String logisticTag, 
                        String longitude, String latitude, Integer distance, 
                        Boolean isExpress, Long updateTime) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.status = status;
        this.riderName = riderName;
        this.riderPhone = riderPhone;
        this.logisticNo = logisticNo;
        this.logisticTag = logisticTag;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.isExpress = isExpress;
        this.updateTime = updateTime;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getLogisticTag() {
        return logisticTag;
    }

    public void setLogisticTag(String logisticTag) {
        this.logisticTag = logisticTag;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Boolean getIsExpress() {
        return isExpress;
    }

    public void setIsExpress(Boolean isExpress) {
        this.isExpress = isExpress;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiderLocation that = (RiderLocation) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(status, that.status) &&
               Objects.equals(riderName, that.riderName) &&
               Objects.equals(riderPhone, that.riderPhone) &&
               Objects.equals(logisticNo, that.logisticNo) &&
               Objects.equals(logisticTag, that.logisticTag) &&
               Objects.equals(longitude, that.longitude) &&
               Objects.equals(latitude, that.latitude) &&
               Objects.equals(distance, that.distance) &&
               Objects.equals(isExpress, that.isExpress) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, status, riderName, riderPhone, 
                           logisticNo, logisticTag, longitude, latitude, 
                           distance, isExpress, updateTime);
    }

    @Override
    public String toString() {
        return "RiderLocation{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", status='" + status + '\'' +
                ", riderName='" + riderName + '\'' +
                ", riderPhone='" + riderPhone + '\'' +
                ", logisticNo='" + logisticNo + '\'' +
                ", logisticTag='" + logisticTag + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", distance=" + distance +
                ", isExpress=" + isExpress +
                ", updateTime=" + updateTime +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查必要字段是否完整
     * 
     * @return 如果必要字段都不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return orderId != null && !orderId.trim().isEmpty() &&
               shopId != null && !shopId.trim().isEmpty() &&
               status != null && !status.trim().isEmpty() &&
               riderName != null && !riderName.trim().isEmpty() &&
               riderPhone != null && !riderPhone.trim().isEmpty() &&
               logisticNo != null && !logisticNo.trim().isEmpty() &&
               logisticTag != null && !logisticTag.trim().isEmpty();
    }

    /**
     * 检查是否包含位置坐标
     * 
     * @return 如果包含经纬度坐标返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasLocation() {
        return longitude != null && !longitude.trim().isEmpty() &&
               latitude != null && !latitude.trim().isEmpty();
    }

    /**
     * 检查是否包含距离信息
     * 
     * @return 如果包含距离信息返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasDistance() {
        return distance != null && distance >= 0;
    }

    /**
     * 获取格式化的位置信息
     * 
     * @return 格式化的位置信息字符串
     */
    @JsonIgnore
    public String getFormattedLocation() {
        if (hasLocation()) {
            return String.format("经度: %s, 纬度: %s", longitude, latitude);
        }
        return "位置信息不可用";
    }

    /**
     * 获取格式化的距离信息
     * 
     * @return 格式化的距离信息字符串
     */
    @JsonIgnore
    public String getFormattedDistance() {
        if (hasDistance()) {
            if (distance < 1000) {
                return distance + "米";
            } else {
                return String.format("%.1f公里", distance / 1000.0);
            }
        }
        return "距离不可用";
    }

    /**
     * 检查是否为快递配送
     * 
     * @return 如果是快递配送返回true，否则返回false
     */
    @JsonIgnore
    public boolean isExpressDelivery() {
        return isExpress != null && isExpress;
    }

    /**
     * 获取配送类型描述
     * 
     * @return 配送类型的中文描述
     */
    @JsonIgnore
    public String getDeliveryTypeDescription() {
        return isExpressDelivery() ? "快递配送" : "同城配送";
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建完整骑手位置信息的便捷工厂方法
     */
    public static RiderLocation of(String orderId, String shopId, String status, String riderName, 
                                  String riderPhone, String logisticNo, String logisticTag, 
                                  String longitude, String latitude, Integer distance, 
                                  Boolean isExpress, Long updateTime) {
        return new RiderLocation(orderId, shopId, status, riderName, riderPhone, 
                               logisticNo, logisticTag, longitude, latitude, 
                               distance, isExpress, updateTime);
    }

    /**
     * 创建基本骑手位置信息（必要字段）
     */
    public static RiderLocation basic(String orderId, String shopId, String status, 
                                     String riderName, String riderPhone, 
                                     String logisticNo, String logisticTag) {
        return new RiderLocation(orderId, shopId, status, riderName, riderPhone, 
                               logisticNo, logisticTag, null, null, null, null, null);
    }

    /**
     * 创建带位置信息的骑手信息
     */
    public static RiderLocation withLocation(String orderId, String shopId, String status, 
                                           String riderName, String riderPhone, 
                                           String logisticNo, String logisticTag,
                                           String longitude, String latitude, Long updateTime) {
        return new RiderLocation(orderId, shopId, status, riderName, riderPhone, 
                               logisticNo, logisticTag, longitude, latitude, 
                               null, null, updateTime);
    }
}