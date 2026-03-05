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
 * 订单取消推送请求参数
 * <p>
 * 三方服务推送订单取消状态给麦芽田
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 订单被取消后，三方推送取消状态给麦芽田
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>订单取消后不可恢复</li>
 * <li>需要记录取消原因和原因码</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderCanceledReq {

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
     * 取消原因描述
     */
    @JsonProperty("reason")
    private String reason;

    /**
     * 取消原因码：1-用户取消，2-商户取消，3-客服取消，4-系统取消，5-其他
     */
    @JsonProperty("reason_code")
    private Integer reasonCode;

    /**
     * 取消时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderCanceledReq() {
    }

    /**
     * 全参构造函数
     */
    public OrderCanceledReq(String orderId, String shopId, String reason, Integer reasonCode, Long updateTime) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.reason = reason;
        this.reasonCode = reasonCode;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(Integer reasonCode) {
        this.reasonCode = reasonCode;
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
        OrderCanceledReq that = (OrderCanceledReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(reason, that.reason) &&
               Objects.equals(reasonCode, that.reasonCode) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, reason, reasonCode, updateTime);
    }

    @Override
    public String toString() {
        return "OrderCanceledReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", reason='" + reason + '\'' +
                ", reasonCode=" + reasonCode +
                ", updateTime=" + updateTime +
                '}';
    }
}