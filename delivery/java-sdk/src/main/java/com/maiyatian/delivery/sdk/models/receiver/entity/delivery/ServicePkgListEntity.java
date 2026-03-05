/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

import java.util.List;

/**
 * 获取服务包列表实体
 * <p>
 * 用于麦芽田请求三方服务获取服务包列表
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServicePkgListEntity {
    
    /**
     * 获取服务包列表请求参数
     * <p>
     * command: service_pkg_list
     * 必接: 否（建议对接）
     * 说明: 麦芽田平台调用三方配送服务获取可用的服务包列表
     */
    public static class Req {
        
        /**
         * 三方平台账号ID
         * <p>商户或门店的唯一标识
         */
        private String shopId;
        
        /**
         * 城市code
         * <p>标准城市行政编码，示例: "10100"
         */
        private String city;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getShopId() {
            return shopId;
        }
        
        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
    }
    
    /**
     * 获取服务包列表响应参数
     */
    public static class Resp {
        
        /**
         * 服务包列表
         * <p>可用的服务包信息列表
         */
        private List<ServicePkg> data;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public List<ServicePkg> getData() {
            return data;
        }
        
        public void setData(List<ServicePkg> data) {
            this.data = data;
        }
    }
    
    /**
     * 服务包信息
     */
    public static class ServicePkg {
        
        /**
         * 服务包名称
         * <p>示例: "滴滴跑腿直送"、"滴滴跑腿特惠"
         */
        private String name;
        
        /**
         * 服务包标识
         * <p>开放平台对外服务包:
         * <ul>
         * <li>base: 经济配送</li>
         * <li>direct: 专人直送</li>
         * </ul>
         * 详见服务包枚举
         */
        private String servicePkg;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getServicePkg() {
            return servicePkg;
        }
        
        public void setServicePkg(String servicePkg) {
            this.servicePkg = servicePkg;
        }
    }
}