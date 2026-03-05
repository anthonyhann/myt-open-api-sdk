/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.city;

import java.util.List;

/**
 * 获取城市运力实体
 * <p>
 * 用于麦芽田请求三方服务获取支持配送的城市列表
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CityCapacityEntity {
    
    /**
     * 城市运力响应参数
     * <p>
     * command: city_capacity
     * 必接: 否（建议对接）
     * 说明: 麦芽田平台调用三方配送服务获取支持配送的城市列表
     */
    public static class Resp {
        
        /**
         * 城市列表
         * <p>配送服务商支持配送的所有城市信息
         */
        private List<CityInfo> data;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public List<CityInfo> getData() {
            return data;
        }
        
        public void setData(List<CityInfo> data) {
            this.data = data;
        }
    }
    
    /**
     * 城市信息
     */
    public static class CityInfo {
        
        /**
         * 城市名称
         * <p>示例: "北京市"、"上海"
         */
        private String cityName;
        
        /**
         * 城市编码
         * <p>标准城市行政编码，示例: "100010"
         */
        private String cityCode;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getCityName() {
            return cityName;
        }
        
        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
        
        public String getCityCode() {
            return cityCode;
        }
        
        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
    }
}
