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
 * 拒绝退款请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方拒绝退款
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方拒绝退款申请
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送拒绝退款请求 → 三方处理拒绝逻辑 → 三方回复处理结果
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>三方应及时处理拒绝退款通知，更新订单状态</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderRejectRefundReq {

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
     * 拒绝退款原因
     */
    @JsonProperty("reason")
    private String reason;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderRejectRefundReq() {
    }

    /**
     * 全参构造函数
     * 
     * @param orderId 平台方订单唯一ID
     * @param shopId 平台方渠道ID
     * @param refundId 退款单ID
     * @param reason 拒绝退款原因
     */
    public OrderRejectRefundReq(String orderId, String shopId, String refundId, String reason) {
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
        OrderRejectRefundReq that = (OrderRejectRefundReq) o;
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
        return "OrderRejectRefundReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", refundId='" + refundId + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查请求参数是否有效
     * 
     * @return 如果订单ID和门店ID都不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return orderId != null && !orderId.trim().isEmpty() &&
               shopId != null && !shopId.trim().isEmpty();
    }

    /**
     * 检查是否包含退款单ID
     * 
     * @return 如果包含退款单ID返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasRefundId() {
        return refundId != null && !refundId.trim().isEmpty();
    }

    /**
     * 检查是否包含拒绝原因
     * 
     * @return 如果包含拒绝原因返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasReason() {
        return reason != null && !reason.trim().isEmpty();
    }

    /**
     * 获取格式化的拒绝原因信息
     * 
     * @return 格式化的拒绝原因信息
     */
    @JsonIgnore
    public String getFormattedReason() {
        if (hasReason()) {
            return "拒绝退款原因：" + reason;
        }
        return "拒绝退款原因：未提供";
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建拒绝退款请求的便捷工厂方法
     * 
     * @param orderId 订单ID
     * @param shopId 门店ID
     * @param refundId 退款单ID
     * @param reason 拒绝原因
     * @return 拒绝退款请求对象
     */
    public static OrderRejectRefundReq of(String orderId, String shopId, String refundId, String reason) {
        return new OrderRejectRefundReq(orderId, shopId, refundId, reason);
    }

    /**
     * 创建基本拒绝退款请求（无退款单ID）
     * 
     * @param orderId 订单ID
     * @param shopId 门店ID
     * @param reason 拒绝原因
     * @return 拒绝退款请求对象
     */
    public static OrderRejectRefundReq basic(String orderId, String shopId, String reason) {
        return new OrderRejectRefundReq(orderId, shopId, null, reason);
    }

    /**
     * 创建最小拒绝退款请求（仅必要字段）
     * 
     * @param orderId 订单ID
     * @param shopId 门店ID
     * @return 拒绝退款请求对象
     */
    public static OrderRejectRefundReq minimal(String orderId, String shopId) {
        return new OrderRejectRefundReq(orderId, shopId, null, null);
    }
}