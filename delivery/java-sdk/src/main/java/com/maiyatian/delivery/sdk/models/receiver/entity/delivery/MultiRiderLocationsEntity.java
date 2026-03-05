/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

import java.util.List;

/**
 * 批量获取骑手当前位置实体
 * <p>
 * 用于麦芽田请求三方服务批量获取骑手当前位置，command值为multi_rider_locations，是必接接口
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class MultiRiderLocationsEntity {
    
    /**
     * 批量查询骑手位置请求参数
     * <p>
     * command: multi_rider_locations
     * 必接: 是
     * 说明: 批量查询多个订单的骑手当前位置信息
     */
    public static class Req {
        
        /**
         * 订单列表
         * <p>需要查询骑手位置的订单列表
         */
        private List<RiderLocationReq> orders;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public List<RiderLocationReq> getOrders() {
            return orders;
        }
        
        public void setOrders(List<RiderLocationReq> orders) {
            this.orders = orders;
        }
    }
    
    /**
     * 批量查询骑手位置响应参数
     */
    public static class Resp {
        
        /**
         * 骑手位置列表
         * <p>返回每个订单对应的骑手位置信息
         */
        private List<RiderLocationResp> data;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public List<RiderLocationResp> getData() {
            return data;
        }
        
        public void setData(List<RiderLocationResp> data) {
            this.data = data;
        }
    }
    
    /**
     * 单个订单骑手位置查询参数
     */
    public static class RiderLocationReq {
        
        /**
         * 配送单号
         * <p>三方配送平台的订单号
         */
        private String orderNo;
        
        /**
         * 麦芽田侧运单号
         * <p>麦芽田平台的订单号
         */
        private String sourceOrderNo;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getOrderNo() {
            return orderNo;
        }
        
        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        
        public String getSourceOrderNo() {
            return sourceOrderNo;
        }
        
        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }
    }
    
    /**
     * 骑手位置信息
     */
    public static class RiderLocationResp {
        
        /**
         * 麦芽田订单号
         * <p>麦芽田平台的订单号
         */
        private String sourceOrderNo;
        
        /**
         * 三方运单号
         * <p>配送服务商的订单号
         */
        private String orderNo;
        
        /**
         * 骑手姓名
         * <p>配送员姓名
         */
        private String riderName;
        
        /**
         * 骑手电话
         * <p>配送员联系电话
         */
        private String riderPhone;
        
        /**
         * 经度
         * <p>当前位置经度，国测局02标准（如高德地图坐标系）
         */
        private String longitude;
        
        /**
         * 纬度
         * <p>当前位置纬度，国测局02标准（如高德地图坐标系）
         */
        private String latitude;
        
        /**
         * 位置采集时间
         * <p>Unix时间戳（秒）
         */
        private long atTime;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getSourceOrderNo() {
            return sourceOrderNo;
        }
        
        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }
        
        public String getOrderNo() {
            return orderNo;
        }
        
        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        
        public String getRiderName() {
            return riderName;
        }
        
        public void setRiderName(String riderName) {
            this.riderName = riderName;
        }
        
        public String getRiderPhone() {
            return riderPhone;
        }
        
        public void setRiderPhone(String riderPhone) {
            this.riderPhone = riderPhone;
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
        
        public long getAtTime() {
            return atTime;
        }
        
        public void setAtTime(long atTime) {
            this.atTime = atTime;
        }
    }
}