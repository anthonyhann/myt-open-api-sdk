/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 配送状态变更请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方配送状态已变更
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方订单配送状态变化
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送配送状态变更 → 三方接收并同步状态
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeliveryChangeReq {

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
    @JsonProperty("rider_name")
    private String riderName;

    /**
     * 骑手电话
     */
    @JsonProperty("rider_phone")
    private String riderPhone;

    /**
     * 物流单号
     */
    @JsonProperty("logistic_no")
    private String logisticNo;

    /**
     * 物流标识
     */
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
     * 是否转运
     */
    @JsonProperty("is_transship")
    private Boolean isTransship = false;

    /**
     * 是否快递配送
     */
    @JsonProperty("is_express")
    private Boolean isExpress = false;

    /**
     * 配送费（单位：分）
     */
    @JsonProperty("delivery_fee")
    private Long deliveryFee = 0L;

    /**
     * 状态更新时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public DeliveryChangeReq() {
    }

    /**
     * 全参构造函数
     */
    public DeliveryChangeReq(String orderId, String shopId, String status, String riderName,
                           String riderPhone, String logisticNo, String logisticTag,
                           String longitude, String latitude, Boolean isTransship,
                           Boolean isExpress, Long deliveryFee, Long updateTime) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.status = status;
        this.riderName = riderName;
        this.riderPhone = riderPhone;
        this.logisticNo = logisticNo;
        this.logisticTag = logisticTag;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isTransship = isTransship;
        this.isExpress = isExpress;
        this.deliveryFee = deliveryFee;
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

    public Boolean getIsTransship() {
        return isTransship;
    }

    public void setIsTransship(Boolean isTransship) {
        this.isTransship = isTransship;
    }

    public Boolean getIsExpress() {
        return isExpress;
    }

    public void setIsExpress(Boolean isExpress) {
        this.isExpress = isExpress;
    }

    public Long getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Long deliveryFee) {
        this.deliveryFee = deliveryFee;
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
        DeliveryChangeReq that = (DeliveryChangeReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(status, that.status) &&
               Objects.equals(riderName, that.riderName) &&
               Objects.equals(riderPhone, that.riderPhone) &&
               Objects.equals(logisticNo, that.logisticNo) &&
               Objects.equals(logisticTag, that.logisticTag) &&
               Objects.equals(longitude, that.longitude) &&
               Objects.equals(latitude, that.latitude) &&
               Objects.equals(isTransship, that.isTransship) &&
               Objects.equals(isExpress, that.isExpress) &&
               Objects.equals(deliveryFee, that.deliveryFee) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, status, riderName, riderPhone,
                           logisticNo, logisticTag, longitude, latitude,
                           isTransship, isExpress, deliveryFee, updateTime);
    }

    @Override
    public String toString() {
        return "DeliveryChangeReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", status='" + status + '\'' +
                ", riderName='" + riderName + '\'' +
                ", riderPhone='" + riderPhone + '\'' +
                ", logisticNo='" + logisticNo + '\'' +
                ", logisticTag='" + logisticTag + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", isTransship=" + isTransship +
                ", isExpress=" + isExpress +
                ", deliveryFee=" + deliveryFee +
                ", updateTime=" + updateTime +
                '}';
    }
}