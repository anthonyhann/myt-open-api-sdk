/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 自配送状态变更推送请求参数
 * <p>
 * 三方服务推送自配送状态变更给麦芽田
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 三方自配送订单状态变化，推送状态给麦芽田
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>仅适用于三方自配送订单</li>
 * <li>需要及时推送配送状态</li>
 * <li>配送状态：PENDING、GRABBED、ATSHOP、PICKUP、DELIVERING、DONE、CANCEL、EXPECT</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class SelfDeliveryChangeReq {

    /**
     * 麦芽田订单号
     */
    @NotBlank(message = "麦芽田订单号不能为空")
    @JsonProperty("order_no")
    private String orderNo;

    /**
     * 原始订单号（三方平台的订单号）
     */
    @NotBlank(message = "原始订单号不能为空")
    @JsonProperty("source_order_no")
    private String sourceOrderNo;

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
     * 配送标签
     */
    @JsonProperty("tag")
    private String tag;

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
     * 取货码
     */
    @JsonProperty("pickup_code")
    private String pickupCode;

    /**
     * 取消类型：1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
     */
    @JsonProperty("cancel_type")
    private Integer cancelType;

    /**
     * 取消原因描述
     */
    @JsonProperty("cancel_reason")
    private String cancelReason;

    /**
     * 状态更新时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public SelfDeliveryChangeReq() {
    }

    /**
     * 全参构造函数
     */
    public SelfDeliveryChangeReq(String orderNo, String sourceOrderNo, String shopId, String status, 
                                String tag, String riderName, String riderPhone, String longitude, 
                                String latitude, String pickupCode, Integer cancelType, 
                                String cancelReason, Long updateTime) {
        this.orderNo = orderNo;
        this.sourceOrderNo = sourceOrderNo;
        this.shopId = shopId;
        this.status = status;
        this.tag = tag;
        this.riderName = riderName;
        this.riderPhone = riderPhone;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pickupCode = pickupCode;
        this.cancelType = cancelType;
        this.cancelReason = cancelReason;
        this.updateTime = updateTime;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSourceOrderNo() {
        return sourceOrderNo;
    }

    public void setSourceOrderNo(String sourceOrderNo) {
        this.sourceOrderNo = sourceOrderNo;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
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
        SelfDeliveryChangeReq that = (SelfDeliveryChangeReq) o;
        return Objects.equals(orderNo, that.orderNo) &&
               Objects.equals(sourceOrderNo, that.sourceOrderNo) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(status, that.status) &&
               Objects.equals(tag, that.tag) &&
               Objects.equals(riderName, that.riderName) &&
               Objects.equals(riderPhone, that.riderPhone) &&
               Objects.equals(longitude, that.longitude) &&
               Objects.equals(latitude, that.latitude) &&
               Objects.equals(pickupCode, that.pickupCode) &&
               Objects.equals(cancelType, that.cancelType) &&
               Objects.equals(cancelReason, that.cancelReason) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNo, sourceOrderNo, shopId, status, tag, riderName, 
                           riderPhone, longitude, latitude, pickupCode, cancelType, 
                           cancelReason, updateTime);
    }

    @Override
    public String toString() {
        return "SelfDeliveryChangeReq{" +
                "orderNo='" + orderNo + '\'' +
                ", sourceOrderNo='" + sourceOrderNo + '\'' +
                ", shopId='" + shopId + '\'' +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                ", riderName='" + riderName + '\'' +
                ", riderPhone='" + riderPhone + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", pickupCode='" + pickupCode + '\'' +
                ", cancelType=" + cancelType +
                ", cancelReason='" + cancelReason + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}