/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderBaseData;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderInfo;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderCustomerInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 查询订单详情响应数据（三方返回给麦芽田）
 * <p>
 * 三方返回麦芽田的订单完整信息
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田查询订单详情时，三方返回完整的订单数据
 * 
 * <h3>数据结构：</h3>
 * 包含订单基础数据结构，包括：
 * <ul>
 * <li>订单主体信息（商品、费用、配送等详细信息）</li>
 * <li>顾客信息（收货人、电话、地址等）</li>
 * <li>订单更新时间</li>
 * </ul>
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>返回的订单信息必须完整准确</li>
 * <li>所有必填字段都必须有值</li>
 * <li>顾客隐私信息需要按规范处理</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderDetailResp {

    /**
     * 订单基础数据（包含订单信息、顾客信息和更新时间）
     */
    @NotNull(message = "订单基础数据不能为空")
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
    public OrderDetailResp() {
    }

    /**
     * 全参构造函数
     * 
     * @param order 订单主体信息
     * @param orderCustomer 顾客信息
     * @param updateTime 订单更新时间
     */
    public OrderDetailResp(OrderInfo order, OrderCustomerInfo orderCustomer, Long updateTime) {
        this.order = order;
        this.orderCustomer = orderCustomer;
        this.updateTime = updateTime;
    }

    /**
     * 基于OrderBaseData构造
     * 
     * @param orderBaseData 订单基础数据
     */
    public OrderDetailResp(OrderBaseData orderBaseData) {
        if (orderBaseData != null) {
            this.order = orderBaseData.getOrder();
            this.orderCustomer = orderBaseData.getOrderCustomer();
            this.updateTime = orderBaseData.getUpdateTime();
        }
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

    // ==================== 便捷方法 ====================

    /**
     * 检查订单详情响应是否完整
     * 
     * @return 如果包含必要的订单信息和顾客信息返回true，否则返回false
     */
    public boolean isComplete() {
        return order != null && orderCustomer != null;
    }

    /**
     * 获取订单ID（便捷方法）
     * 
     * @return 订单ID，如果订单信息为空则返回null
     */
    public String getOrderId() {
        return order != null ? order.getOrderId() : null;
    }

    /**
     * 获取门店ID（便捷方法）
     * 
     * @return 门店ID，如果订单信息为空则返回null
     */
    public String getShopId() {
        return order != null ? order.getShopId() : null;
    }

    /**
     * 获取顾客姓名（便捷方法）
     * 
     * @return 顾客姓名，如果顾客信息为空则返回null
     */
    public String getCustomerName() {
        return orderCustomer != null ? orderCustomer.getRealName() : null;
    }

    /**
     * 获取顾客电话（便捷方法）
     * 
     * @return 顾客电话，如果顾客信息为空则返回null
     */
    public String getCustomerPhone() {
        return orderCustomer != null ? orderCustomer.getPhone() : null;
    }

    /**
     * 转换为OrderBaseData
     * 
     * @return OrderBaseData对象
     */
    public OrderBaseData toOrderBaseData() {
        return new OrderBaseData(order, orderCustomer, updateTime);
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailResp that = (OrderDetailResp) o;
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
        return "OrderDetailResp{" +
                "order=" + order +
                ", orderCustomer=" + orderCustomer +
                ", updateTime=" + updateTime +
                '}';
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建订单详情响应的便捷工厂方法
     * 
     * @param order 订单信息
     * @param orderCustomer 顾客信息
     * @param updateTime 更新时间
     * @return 订单详情响应对象
     */
    public static OrderDetailResp of(OrderInfo order, OrderCustomerInfo orderCustomer, Long updateTime) {
        return new OrderDetailResp(order, orderCustomer, updateTime);
    }

    /**
     * 从OrderBaseData创建响应对象的便捷工厂方法
     * 
     * @param orderBaseData 订单基础数据
     * @return 订单详情响应对象
     */
    public static OrderDetailResp from(OrderBaseData orderBaseData) {
        return new OrderDetailResp(orderBaseData);
    }
}