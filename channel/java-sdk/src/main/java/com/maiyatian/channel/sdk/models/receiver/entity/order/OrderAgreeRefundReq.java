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
 * 同意退款请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方同意退款
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方同意退款申请
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送同意退款请求 → 三方处理退款逻辑 → 三方回复处理结果
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
public class OrderAgreeRefundReq {

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
     * 退款单 ID
     */
    @JsonProperty("refund_id")
    private String refundId;

    /**
     * 同意退款原因
     */
    @JsonProperty("reason")
    private String reason;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderAgreeRefundReq() {
    }

    /**
     * 全参构造函数
     */
    public OrderAgreeRefundReq(String orderId, String shopId, String refundId, String reason) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.refundId = refundId;
        this.reason = reason;
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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAgreeRefundReq that = (OrderAgreeRefundReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(refundId, that.refundId) &&
               Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, refundId, reason);
    }

    @Override
    public String toString() {
        return "OrderAgreeRefundReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", refundId='" + refundId + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}