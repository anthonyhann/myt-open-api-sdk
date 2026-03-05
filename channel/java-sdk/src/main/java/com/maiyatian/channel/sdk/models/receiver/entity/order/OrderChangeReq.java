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
 * 订单状态变更请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方订单状态已变更
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方订单状态变化
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送订单状态变更 → 三方接收并处理 → 三方同步订单状态
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>订单状态需要及时同步到三方平台</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderChangeReq {

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
     * 商户联系电话
     */
    @JsonProperty("merchant_phone")
    private String merchantPhone;

    /**
     * 订单状态
     */
    @NotBlank(message = "订单状态不能为空")
    @JsonProperty("status")
    private String status;

    /**
     * 状态更新时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderChangeReq() {
    }

    /**
     * 全参构造函数
     * 
     * @param orderId 平台方订单唯一ID
     * @param shopId 平台方渠道ID
     * @param merchantPhone 商户联系电话
     * @param status 订单状态
     * @param updateTime 状态更新时间
     */
    public OrderChangeReq(String orderId, String shopId, String merchantPhone, 
                         String status, Long updateTime) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.merchantPhone = merchantPhone;
        this.status = status;
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

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        OrderChangeReq that = (OrderChangeReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(merchantPhone, that.merchantPhone) &&
               Objects.equals(status, that.status) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, merchantPhone, status, updateTime);
    }

    @Override
    public String toString() {
        return "OrderChangeReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", merchantPhone='" + merchantPhone + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查订单状态是否有效（非空非空白）
     * 
     * @return 如果状态有效返回true，否则返回false
     */
    public boolean hasValidStatus() {
        return status != null && !status.trim().isEmpty();
    }

    /**
     * 检查是否包含商户联系电话
     * 
     * @return 如果包含商户电话返回true，否则返回false
     */
    public boolean hasMerchantPhone() {
        return merchantPhone != null && !merchantPhone.trim().isEmpty();
    }

    /**
     * 检查更新时间是否有效（大于0）
     * 
     * @return 如果更新时间有效返回true，否则返回false
     */
    public boolean hasValidUpdateTime() {
        return updateTime != null && updateTime > 0;
    }
}