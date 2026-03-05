/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * 订单申请退款推送请求参数
 * <p>
 * 三方服务推送退款申请给麦芽田
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用户或商家发起退款申请，三方推送退款申请给麦芽田
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>支持部分退款（按商品退款）</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderApplyRefundReq {

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
     * 退款类型：1-仅退款，2-退货退款
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 退款原因描述
     */
    @JsonProperty("reason")
    private String reason;

    /**
     * 申请退款总金额（单位：分）
     */
    @JsonProperty("total_price")
    private Integer totalPrice;

    /**
     * 退款商品总数量
     */
    @JsonProperty("count")
    private Integer count;

    /**
     * 退款商品列表
     */
    @Valid
    @JsonProperty("goods")
    private List<OrderApplyRefundGoodsInfo> goods;

    /**
     * 申请时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    public OrderApplyRefundReq() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<OrderApplyRefundGoodsInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderApplyRefundGoodsInfo> goods) {
        this.goods = goods;
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
        OrderApplyRefundReq that = (OrderApplyRefundReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(refundId, that.refundId) &&
               Objects.equals(type, that.type) &&
               Objects.equals(reason, that.reason) &&
               Objects.equals(totalPrice, that.totalPrice) &&
               Objects.equals(count, that.count) &&
               Objects.equals(goods, that.goods) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, refundId, type, reason,
                           totalPrice, count, goods, updateTime);
    }

    @Override
    public String toString() {
        return "OrderApplyRefundReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", refundId='" + refundId + '\'' +
                ", type=" + type +
                ", reason='" + reason + '\'' +
                ", totalPrice=" + totalPrice +
                ", count=" + count +
                ", goods=" + goods +
                ", updateTime=" + updateTime +
                '}';
    }
}