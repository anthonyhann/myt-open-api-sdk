/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 查询门店详情响应数据（三方返回给麦芽田）
 * <p>
 * 三方返回给麦芽田的门店完整信息
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopDetailResp {

    /**
     * 平台方渠道 ID
     */
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 门店名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 门店联系电话
     */
    @JsonProperty("phone")
    private String phone;

    /**
     * 省份
     */
    @JsonProperty("province")
    private String province;

    /**
     * 城市
     */
    @JsonProperty("city")
    private String city;

    /**
     * 详细地址
     */
    @JsonProperty("address")
    private String address;

    /**
     * 门店纬度（国测局 GCJ-02 标准）
     */
    @JsonProperty("latitude")
    private String latitude;

    /**
     * 门店经度（国测局 GCJ-02 标准）
     */
    @JsonProperty("longitude")
    private String longitude;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ShopDetailResp() {
    }

    /**
     * 全参构造函数
     */
    public ShopDetailResp(String shopId, String name, String phone, String province,
                         String city, String address, String latitude, String longitude) {
        this.shopId = shopId;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDetailResp that = (ShopDetailResp) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(name, that.name) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(province, that.province) &&
               Objects.equals(city, that.city) &&
               Objects.equals(address, that.address) &&
               Objects.equals(latitude, that.latitude) &&
               Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, name, phone, province, city, address, latitude, longitude);
    }

    @Override
    public String toString() {
        return "ShopDetailResp{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}