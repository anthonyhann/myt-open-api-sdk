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
 * 查询订单详情请求参数（三方接收麦芽田请求）
 * <p>
 * 麦芽田向三方查询订单详情时的请求参数
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田查询三方订单详细信息
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>此结构用于麦芽田调用三方的查询接口</li>
 * <li>三方需要根据请求参数返回完整的订单详情</li>
 * <li>订单ID和门店ID必须准确匹配</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderDetailReq {

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
    public OrderDetailReq() {
    }

    /**
     * 全参构造函数
     * 
     * @param orderId 平台方订单唯一ID
     * @param shopId 平台方渠道ID
     */
    public OrderDetailReq(String orderId, String shopId) {
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
        OrderDetailReq that = (OrderDetailReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId);
    }

    @Override
    public String toString() {
        return "OrderDetailReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查请求参数是否完整
     * 
     * @return 如果订单ID和门店ID都不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return orderId != null && !orderId.trim().isEmpty() &&
               shopId != null && !shopId.trim().isEmpty();
    }

    /**
     * 创建请求参数的便捷工厂方法
     * 
     * @param orderId 订单ID
     * @param shopId 门店ID
     * @return 订单详情查询请求对象
     */
    public static OrderDetailReq of(String orderId, String shopId) {
        return new OrderDetailReq(orderId, shopId);
    }
}