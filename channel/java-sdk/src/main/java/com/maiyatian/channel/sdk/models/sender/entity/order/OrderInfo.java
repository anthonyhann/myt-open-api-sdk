/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * 订单主体信息
 * <p>
 * 包含订单的核心信息：订单号、门店、商品、费用、配送等
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderInfo {

    /**
     * 平台方订单唯一 ID（第三方平台的订单 ID）
     */
    @NotBlank(message = "订单ID不能为空")
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 平台方流水号（数字型订单号，最大 99999999）
     */
    @Max(value = 99999999, message = "流水号不能超过99999999")
    @JsonProperty("order_sn")
    private Integer orderSn;

    /**
     * 平台方渠道 ID（门店唯一标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 门店名称
     */
    @JsonProperty("shop_name")
    private String shopName;

    /**
     * 原始渠道流水号（如美团、饿了么的原始订单号）
     */
    @JsonProperty("origin_order_sn")
    private String originOrderSn;

    /**
     * 原始订单来源渠道标识（如：meituan、eleme、tiktok 等）
     */
    @JsonProperty("origin_tag")
    private String originTag;

    /**
     * 订单分类，麦芽田枚举值（如：shaokao、canyin 等）
     */
    @JsonProperty("category")
    private String category;

    /**
     * 是否预约单（true：预约单，false：即时单）
     */
    @JsonProperty("is_pre_order")
    private Boolean isPreOrder;

    /**
     * 订单原价总金额（单位：分，折扣前）
     */
    @JsonProperty("total_price")
    private Integer totalPrice;

    /**
     * 实付金额（单位：分，折扣后用户实际支付）
     */
    @JsonProperty("balance_price")
    private Integer balancePrice;

    /**
     * 下单时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("create_time")
    private Long createTime;

    /**
     * 期望送达时间（开始时间，Unix 时间戳，单位：秒）
     */
    @JsonProperty("delivery_time")
    private Long deliveryTime;

    /**
     * 期望送达时间（结束时间，Unix 时间戳，单位：秒）
     */
    @JsonProperty("delivery_end")
    private Long deliveryEnd;

    /**
     * 配送类型：0-麦芽田自动适配，1-同城配送，2-全国快递
     */
    @JsonProperty("delivery_type")
    private Long deliveryType;

    /**
     * 是否自提（true：顾客到店自提，false：配送到家）
     */
    @JsonProperty("is_picker")
    private Boolean isPicker;

    /**
     * 自提时间（Unix 时间戳，单位：秒，仅自提单有效）
     */
    @JsonProperty("pick_time")
    private Long pickTime;

    /**
     * 用户备注（顾客下单时填写的备注信息）
     */
    @JsonProperty("user_remark")
    private String userRemark;

    /**
     * 祝福语（如生日蛋糕的祝福语）
     */
    @JsonProperty("greeting")
    private String greeting;

    /**
     * 取货码（自提单的取货验证码）
     */
    @JsonProperty("pickup_code")
    private String pickupCode;

    /**
     * 订单费用详情（配送费、餐盒费、优惠等）
     */
    @NotNull(message = "订单费用信息不能为空")
    @Valid
    @JsonProperty("order_fee")
    private OrderFeeInfo orderFee;

    /**
     * 订单商品列表（包含商品详情、规格、价格等）
     */
    @NotNull(message = "订单商品列表不能为空")
    @Valid
    @JsonProperty("order_goods")
    private List<OrderGoodsInfo> orderGoods;

    /**
     * 订单相关提醒时间
     */
    @Valid
    @JsonProperty("time_reminders")
    private TimeReminder timeReminders;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderInfo() {
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(Integer orderSn) {
        this.orderSn = orderSn;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOriginOrderSn() {
        return originOrderSn;
    }

    public void setOriginOrderSn(String originOrderSn) {
        this.originOrderSn = originOrderSn;
    }

    public String getOriginTag() {
        return originTag;
    }

    public void setOriginTag(String originTag) {
        this.originTag = originTag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsPreOrder() {
        return isPreOrder;
    }

    public void setIsPreOrder(Boolean isPreOrder) {
        this.isPreOrder = isPreOrder;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getBalancePrice() {
        return balancePrice;
    }

    public void setBalancePrice(Integer balancePrice) {
        this.balancePrice = balancePrice;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getDeliveryEnd() {
        return deliveryEnd;
    }

    public void setDeliveryEnd(Long deliveryEnd) {
        this.deliveryEnd = deliveryEnd;
    }

    public Long getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Long deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Boolean getIsPicker() {
        return isPicker;
    }

    public void setIsPicker(Boolean isPicker) {
        this.isPicker = isPicker;
    }

    public Long getPickTime() {
        return pickTime;
    }

    public void setPickTime(Long pickTime) {
        this.pickTime = pickTime;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public OrderFeeInfo getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(OrderFeeInfo orderFee) {
        this.orderFee = orderFee;
    }

    public List<OrderGoodsInfo> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoodsInfo> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public TimeReminder getTimeReminders() {
        return timeReminders;
    }

    public void setTimeReminders(TimeReminder timeReminders) {
        this.timeReminders = timeReminders;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(orderId, orderInfo.orderId) &&
               Objects.equals(orderSn, orderInfo.orderSn) &&
               Objects.equals(shopId, orderInfo.shopId) &&
               Objects.equals(shopName, orderInfo.shopName) &&
               Objects.equals(originOrderSn, orderInfo.originOrderSn) &&
               Objects.equals(originTag, orderInfo.originTag) &&
               Objects.equals(category, orderInfo.category) &&
               Objects.equals(isPreOrder, orderInfo.isPreOrder) &&
               Objects.equals(totalPrice, orderInfo.totalPrice) &&
               Objects.equals(balancePrice, orderInfo.balancePrice) &&
               Objects.equals(createTime, orderInfo.createTime) &&
               Objects.equals(deliveryTime, orderInfo.deliveryTime) &&
               Objects.equals(deliveryEnd, orderInfo.deliveryEnd) &&
               Objects.equals(deliveryType, orderInfo.deliveryType) &&
               Objects.equals(isPicker, orderInfo.isPicker) &&
               Objects.equals(pickTime, orderInfo.pickTime) &&
               Objects.equals(userRemark, orderInfo.userRemark) &&
               Objects.equals(greeting, orderInfo.greeting) &&
               Objects.equals(pickupCode, orderInfo.pickupCode) &&
               Objects.equals(orderFee, orderInfo.orderFee) &&
               Objects.equals(orderGoods, orderInfo.orderGoods) &&
               Objects.equals(timeReminders, orderInfo.timeReminders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderSn, shopId, shopName, originOrderSn, originTag,
                           category, isPreOrder, totalPrice, balancePrice, createTime,
                           deliveryTime, deliveryEnd, deliveryType, isPicker, pickTime,
                           userRemark, greeting, pickupCode, orderFee, orderGoods, timeReminders);
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", orderSn=" + orderSn +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", originOrderSn='" + originOrderSn + '\'' +
                ", originTag='" + originTag + '\'' +
                ", category='" + category + '\'' +
                ", isPreOrder=" + isPreOrder +
                ", totalPrice=" + totalPrice +
                ", balancePrice=" + balancePrice +
                ", createTime=" + createTime +
                ", deliveryTime=" + deliveryTime +
                ", deliveryEnd=" + deliveryEnd +
                ", deliveryType=" + deliveryType +
                ", isPicker=" + isPicker +
                ", pickTime=" + pickTime +
                ", userRemark='" + userRemark + '\'' +
                ", greeting='" + greeting + '\'' +
                ", pickupCode='" + pickupCode + '\'' +
                ", orderFee=" + orderFee +
                ", orderGoods=" + orderGoods +
                ", timeReminders=" + timeReminders +
                '}';
    }
}