/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

/**
 * 创建三方侧门店实体
 * <p>
 * 用于麦芽田请求三方服务同步门店信息
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CreateThirdShopEntity {
    
    /**
     * 创建三方门店请求参数
     * <p>
     * command: create_third_shop
     * 必接: 是
     * 说明: 麦芽田平台调用三方配送服务同步门店信息
     * 注意: 如需修改门店信息，也调用该接口
     */
    public static class Req {
        
        /**
         * 麦芽田方门店ID
         * <p>麦芽田平台的门店唯一标识
         */
        private long shopId;
        
        /**
         * 门店名称
         * <p>门店的商业名称
         */
        private String shopName;
        
        /**
         * 联系电话
         * <p>门店联系电话
         */
        private String phone;
        
        /**
         * 省
         * <p>省级行政区名称，示例: "陕西省"
         */
        private String province;
        
        /**
         * 省码
         * <p>省级行政区编码
         */
        private long provinceCode;
        
        /**
         * 市
         * <p>市级行政区名称，示例: "西安市"
         */
        private String city;
        
        /**
         * 市码
         * <p>市级行政区编码
         */
        private long cityCode;
        
        /**
         * 区
         * <p>区县级行政区名称，示例: "莲湖区"
         */
        private String district;
        
        /**
         * 区码
         * <p>区县级行政区编码
         */
        private long districtCode;
        
        /**
         * 详细地址
         * <p>门店的详细地址信息
         */
        private String address;
        
        /**
         * 坐标类型
         * <p>1: 高德地图; 2: 百度地图
         */
        private String mapType;
        
        /**
         * 店铺分类
         * <p>麦芽田枚举，如: xiaochi, dangao等
         * 详见店铺分类枚举
         */
        private String category;
        
        /**
         * 经度
         * <p>门店位置经度，国测局02标准（如高德地图坐标系）
         */
        private String longitude;
        
        /**
         * 纬度
         * <p>门店位置纬度，国测局02标准（如高德地图坐标系）
         */
        private String latitude;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public long getShopId() {
            return shopId;
        }
        
        public void setShopId(long shopId) {
            this.shopId = shopId;
        }
        
        public String getShopName() {
            return shopName;
        }
        
        public void setShopName(String shopName) {
            this.shopName = shopName;
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
        
        public long getProvinceCode() {
            return provinceCode;
        }
        
        public void setProvinceCode(long provinceCode) {
            this.provinceCode = provinceCode;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public long getCityCode() {
            return cityCode;
        }
        
        public void setCityCode(long cityCode) {
            this.cityCode = cityCode;
        }
        
        public String getDistrict() {
            return district;
        }
        
        public void setDistrict(String district) {
            this.district = district;
        }
        
        public long getDistrictCode() {
            return districtCode;
        }
        
        public void setDistrictCode(long districtCode) {
            this.districtCode = districtCode;
        }
        
        public String getAddress() {
            return address;
        }
        
        public void setAddress(String address) {
            this.address = address;
        }
        
        public String getMapType() {
            return mapType;
        }
        
        public void setMapType(String mapType) {
            this.mapType = mapType;
        }
        
        public String getCategory() {
            return category;
        }
        
        public void setCategory(String category) {
            this.category = category;
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
    }
    
    /**
     * 创建三方门店响应参数
     */
    public static class Resp {
        
        /**
         * 门店ID
         * <p>三方配送服务商系统中的门店唯一标识
         */
        private String storeId;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getStoreId() {
            return storeId;
        }
        
        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
    }
}