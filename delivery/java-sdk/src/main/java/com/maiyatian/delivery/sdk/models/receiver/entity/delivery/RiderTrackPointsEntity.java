/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

import java.util.List;

/**
 * 查询当前订单配送骑手位置实体
 * <p>
 * 用于麦芽田请求三方服务获取骑手轨迹点
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RiderTrackPointsEntity {
    
    /**
     * 查询骑手轨迹点请求参数
     * <p>
     * command: rider_track_points
     * 必接: 是
     * 说明: 查询当前订单配送骑手的位置轨迹信息，支持时间范围查询
     */
    public static class Req {
        
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
        
        /**
         * 查询开始时间
         * <p>Unix时间戳（秒），可选字段
         */
        private long startTime;
        
        /**
         * 查询结束时间
         * <p>Unix时间戳（秒），可选字段
         */
        private long endTime;
        
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
        
        public long getStartTime() {
            return startTime;
        }
        
        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
        
        public long getEndTime() {
            return endTime;
        }
        
        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }
    }
    
    /**
     * 查询骑手轨迹点响应参数
     */
    public static class Resp {
        
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
         * 骑手经纬度列表
         * <p>按时间顺序排列的骑手位置轨迹点
         */
        private List<RiderTrackPoint> riderTrackPoints;
        
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
        
        public List<RiderTrackPoint> getRiderTrackPoints() {
            return riderTrackPoints;
        }
        
        public void setRiderTrackPoints(List<RiderTrackPoint> riderTrackPoints) {
            this.riderTrackPoints = riderTrackPoints;
        }
    }
    
    /**
     * 骑手轨迹点信息
     */
    public static class RiderTrackPoint {
        
        /**
         * 骑手姓名
         * <p>配送员姓名
         */
        private String riderName;
        
        /**
         * 骑手手机号
         * <p>配送员联系电话
         */
        private String riderPhone;
        
        /**
         * 经度
         * <p>位置经度，国测局02标准（如高德地图坐标系）
         */
        private String longitude;
        
        /**
         * 纬度
         * <p>位置纬度，国测局02标准（如高德地图坐标系）
         */
        private String latitude;
        
        /**
         * 经纬度采集时间
         * <p>Unix时间戳（秒）
         */
        private long atTime;
        
        // ==================== Getter 和 Setter 方法 ====================
        
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