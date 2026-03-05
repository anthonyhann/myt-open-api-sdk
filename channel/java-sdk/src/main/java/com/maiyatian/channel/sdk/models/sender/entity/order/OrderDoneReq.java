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
 * 订单完成推送请求参数
 * <p>
 * 三方服务推送订单完成状态给麦芽田
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 订单配送完成后，三方推送完成状态给麦芽田
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是必接接口，三方必须实现</li>
 * <li>订单完成后麦芽田进入结算流程</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderDoneReq {

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
     * 完成时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderDoneReq() {
    }

    /**
     * 全参构造函数
     */
    public OrderDoneReq(String orderId, String shopId, Long updateTime) {
        this.orderId = orderId;
        this.shopId = shopId;
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
        OrderDoneReq that = (OrderDoneReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, updateTime);
    }

    @Override
    public String toString() {
        return "OrderDoneReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}