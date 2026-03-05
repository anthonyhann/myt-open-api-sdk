/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 门店信息项
 * <p>
 * 门店列表中的单个门店信息
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用于门店列表查询的响应数据，包含门店的基本信息和位置坐标
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>位置坐标采用国测局 GCJ-02 标准</li>
 * <li>shopId是唯一标识，必须保证唯一性</li>
 * <li>经纬度坐标为字符串格式</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopItem {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空")
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
    public ShopItem() {
    }

    /**
     * 基本信息构造函数
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     */
    public ShopItem(String shopId, String name) {
        this.shopId = shopId;
        this.name = name;
    }

    /**
     * 包含地址信息的构造函数
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     * @param phone 联系电话
     * @param province 省份
     * @param city 城市
     * @param address 详细地址
     */
    public ShopItem(String shopId, String name, String phone, String province, String city, String address) {
        this.shopId = shopId;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.address = address;
    }

    /**
     * 全参构造函数
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     * @param phone 联系电话
     * @param province 省份
     * @param city 城市
     * @param address 详细地址
     * @param latitude 纬度
     * @param longitude 经度
     */
    public ShopItem(String shopId, String name, String phone, String province, String city, 
                   String address, String latitude, String longitude) {
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
        ShopItem shopItem = (ShopItem) o;
        return Objects.equals(shopId, shopItem.shopId) &&
               Objects.equals(name, shopItem.name) &&
               Objects.equals(phone, shopItem.phone) &&
               Objects.equals(province, shopItem.province) &&
               Objects.equals(city, shopItem.city) &&
               Objects.equals(address, shopItem.address) &&
               Objects.equals(latitude, shopItem.latitude) &&
               Objects.equals(longitude, shopItem.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, name, phone, province, city, address, latitude, longitude);
    }

    @Override
    public String toString() {
        return "ShopItem{" +
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

    // ==================== 便捷方法 ====================

    /**
     * 检查必要信息是否完整
     * 
     * @return 如果门店ID和名称都不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return shopId != null && !shopId.trim().isEmpty() &&
               name != null && !name.trim().isEmpty();
    }

    /**
     * 检查是否包含联系电话
     * 
     * @return 如果包含联系电话返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasPhone() {
        return phone != null && !phone.trim().isEmpty();
    }

    /**
     * 检查是否包含地址信息
     * 
     * @return 如果包含地址信息返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasAddress() {
        return address != null && !address.trim().isEmpty();
    }

    /**
     * 检查是否包含位置坐标
     * 
     * @return 如果包含经纬度坐标返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasCoordinates() {
        return longitude != null && !longitude.trim().isEmpty() &&
               latitude != null && !latitude.trim().isEmpty();
    }

    /**
     * 获取完整地址
     * 
     * @return 格式化的完整地址字符串
     */
    @JsonIgnore
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (province != null && !province.trim().isEmpty()) {
            sb.append(province.trim());
        }
        if (city != null && !city.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(city.trim());
        }
        if (address != null && !address.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(address.trim());
        }
        return sb.toString();
    }

    /**
     * 获取格式化的位置坐标
     * 
     * @return 格式化的位置坐标字符串
     */
    @JsonIgnore
    public String getFormattedCoordinates() {
        if (hasCoordinates()) {
            return String.format("经度: %s, 纬度: %s", longitude, latitude);
        }
        return "位置坐标不可用";
    }

    /**
     * 获取门店显示名称（包含地区信息）
     * 
     * @return 带地区信息的门店名称
     */
    @JsonIgnore
    public String getDisplayName() {
        if (city != null && !city.trim().isEmpty()) {
            return String.format("[%s] %s", city.trim(), name != null ? name : "");
        }
        return name != null ? name : "";
    }

    /**
     * 检查是否在指定城市
     * 
     * @param cityName 城市名称
     * @return 如果在指定城市返回true，否则返回false
     */
    @JsonIgnore
    public boolean isInCity(String cityName) {
        return cityName != null && cityName.equals(this.city);
    }

    /**
     * 检查是否在指定省份
     * 
     * @param provinceName 省份名称
     * @return 如果在指定省份返回true，否则返回false
     */
    @JsonIgnore
    public boolean isInProvince(String provinceName) {
        return provinceName != null && provinceName.equals(this.province);
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建基本门店信息
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     * @return 门店信息对象
     */
    public static ShopItem of(String shopId, String name) {
        return new ShopItem(shopId, name);
    }

    /**
     * 创建包含地址的门店信息
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     * @param phone 联系电话
     * @param province 省份
     * @param city 城市
     * @param address 详细地址
     * @return 门店信息对象
     */
    public static ShopItem withAddress(String shopId, String name, String phone, 
                                      String province, String city, String address) {
        return new ShopItem(shopId, name, phone, province, city, address);
    }

    /**
     * 创建包含坐标的门店信息
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     * @param longitude 经度
     * @param latitude 纬度
     * @return 门店信息对象
     */
    public static ShopItem withCoordinates(String shopId, String name, String longitude, String latitude) {
        return new ShopItem(shopId, name, null, null, null, null, latitude, longitude);
    }

    /**
     * 创建完整的门店信息
     * 
     * @param shopId 门店ID
     * @param name 门店名称
     * @param phone 联系电话
     * @param province 省份
     * @param city 城市
     * @param address 详细地址
     * @param longitude 经度
     * @param latitude 纬度
     * @return 门店信息对象
     */
    public static ShopItem complete(String shopId, String name, String phone, String province, 
                                  String city, String address, String longitude, String latitude) {
        return new ShopItem(shopId, name, phone, province, city, address, latitude, longitude);
    }

    /**
     * 构建器模式创建门店信息
     * 
     * @return 门店信息构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 门店信息构建器
     */
    public static class Builder {
        private String shopId;
        private String name;
        private String phone;
        private String province;
        private String city;
        private String address;
        private String latitude;
        private String longitude;

        /**
         * 设置门店ID
         * 
         * @param shopId 门店ID
         * @return 构建器实例
         */
        public Builder shopId(String shopId) {
            this.shopId = shopId;
            return this;
        }

        /**
         * 设置门店名称
         * 
         * @param name 门店名称
         * @return 构建器实例
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * 设置联系电话
         * 
         * @param phone 联系电话
         * @return 构建器实例
         */
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * 设置省份
         * 
         * @param province 省份
         * @return 构建器实例
         */
        public Builder province(String province) {
            this.province = province;
            return this;
        }

        /**
         * 设置城市
         * 
         * @param city 城市
         * @return 构建器实例
         */
        public Builder city(String city) {
            this.city = city;
            return this;
        }

        /**
         * 设置详细地址
         * 
         * @param address 详细地址
         * @return 构建器实例
         */
        public Builder address(String address) {
            this.address = address;
            return this;
        }

        /**
         * 设置纬度
         * 
         * @param latitude 纬度
         * @return 构建器实例
         */
        public Builder latitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        /**
         * 设置经度
         * 
         * @param longitude 经度
         * @return 构建器实例
         */
        public Builder longitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        /**
         * 设置坐标
         * 
         * @param longitude 经度
         * @param latitude 纬度
         * @return 构建器实例
         */
        public Builder coordinates(String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
            return this;
        }

        /**
         * 构建门店信息对象
         * 
         * @return 门店信息对象
         */
        public ShopItem build() {
            return new ShopItem(shopId, name, phone, province, city, address, latitude, longitude);
        }
    }
}