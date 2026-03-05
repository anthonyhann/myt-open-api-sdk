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
 * 确认订单请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方需要确认订单
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方商户确认接单
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送确认订单请求 → 三方处理确认逻辑 → 三方回复确认结果
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>三方确认后需调用 sender.OrderConfirmed 推送确认状态给麦芽田</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderConfirmReq {

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

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderConfirmReq() {
    }

    /**
     * 全参构造函数
     */
    public OrderConfirmReq(String orderId, String shopId) {
        this.orderId = orderId;
        this.shopId = shopId;
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

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderConfirmReq that = (OrderConfirmReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId);
    }

    @Override
    public String toString() {
        return "OrderConfirmReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}