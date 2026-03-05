/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 订单基础数据结构
 * <p>
 * 包含订单主体信息、顾客信息和更新时间
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用于三方推送新订单和订单修改给麦芽田
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderBaseData {

    /**
     * 订单主体信息（商品、费用、配送等详细信息）
     */
    @NotNull(message = "订单信息不能为空")
    @Valid
    @JsonProperty("order")
    private OrderInfo order;

    /**
     * 顾客信息（收货人、电话、地址等）
     */
    @NotNull(message = "顾客信息不能为空")
    @Valid
    @JsonProperty("order_customer")
    private OrderCustomerInfo orderCustomer;

    /**
     * 订单更新时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("update_time")
    private Long updateTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderBaseData() {
    }

    /**
     * 全参构造函数
     */
    public OrderBaseData(OrderInfo order, OrderCustomerInfo orderCustomer, Long updateTime) {
        this.order = order;
        this.orderCustomer = orderCustomer;
        this.updateTime = updateTime;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }

    public OrderCustomerInfo getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomerInfo orderCustomer) {
        this.orderCustomer = orderCustomer;
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
        OrderBaseData that = (OrderBaseData) o;
        return Objects.equals(order, that.order) &&
               Objects.equals(orderCustomer, that.orderCustomer) &&
               Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, orderCustomer, updateTime);
    }

    @Override
    public String toString() {
        return "OrderBaseData{" +
                "order=" + order +
                ", orderCustomer=" + orderCustomer +
                ", updateTime=" + updateTime +
                '}';
    }
}