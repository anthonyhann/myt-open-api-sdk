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
 * 订单顾客信息
 * <p>
 * 包含收货人信息、联系方式和收货地址
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderCustomerInfo {

    /**
     * 收货人真实姓名（可能经过脱敏处理，如："张*生"）
     */
    @NotBlank(message = "收货人姓名不能为空")
    @JsonProperty("real_name")
    private String realName;

    /**
     * 收货人真实电话（可能是虚拟号，如："15525426477_0067"）
     */
    @NotBlank(message = "收货人电话不能为空")
    @JsonProperty("phone")
    private String phone;

    /**
     * 收货人隐私号（脱敏显示，如："157****8884"）
     */
    @JsonProperty("secret_phone")
    private String secretPhone;

    /**
     * 预订人手机号（下单人电话，可能与收货人不同）
     */
    @JsonProperty("order_phone")
    private String orderPhone;

    /**
     * 备用联系电话（可能是虚拟号）
     */
    @JsonProperty("reserve_phone")
    private String reservePhone;

    /**
     * 收货详细地址（如："朔二区-18号楼 (18号楼一单元202)"）
     */
    @JsonProperty("address")
    private String address;

    /**
     * 收货地址经度（国测局 GCJ-02 标准，如高德地图坐标）
     */
    @JsonProperty("longitude")
    private String longitude;

    /**
     * 收货地址纬度（国测局 GCJ-02 标准，如高德地图坐标）
     */
    @JsonProperty("latitude")
    private String latitude;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderCustomerInfo() {
    }

    /**
     * 全参构造函数
     */
    public OrderCustomerInfo(String realName, String phone, String secretPhone, String orderPhone,
                           String reservePhone, String address, String longitude, String latitude) {
        this.realName = realName;
        this.phone = phone;
        this.secretPhone = secretPhone;
        this.orderPhone = orderPhone;
        this.reservePhone = reservePhone;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecretPhone() {
        return secretPhone;
    }

    public void setSecretPhone(String secretPhone) {
        this.secretPhone = secretPhone;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getReservePhone() {
        return reservePhone;
    }

    public void setReservePhone(String reservePhone) {
        this.reservePhone = reservePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCustomerInfo that = (OrderCustomerInfo) o;
        return Objects.equals(realName, that.realName) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(secretPhone, that.secretPhone) &&
               Objects.equals(orderPhone, that.orderPhone) &&
               Objects.equals(reservePhone, that.reservePhone) &&
               Objects.equals(address, that.address) &&
               Objects.equals(longitude, that.longitude) &&
               Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realName, phone, secretPhone, orderPhone,
                           reservePhone, address, longitude, latitude);
    }

    @Override
    public String toString() {
        return "OrderCustomerInfo{" +
                "realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                ", secretPhone='" + secretPhone + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", reservePhone='" + reservePhone + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}