/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.express;

import java.util.List;

/**
 * 快递轨迹回传实体
 * <p>
 * 三方配送服务商主动推送快递轨迹信息到麦芽田平台
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class LocationChangeEntity {
    
    /**
     * 快递轨迹回传请求参数
     * <p>
     * command: location_change
     * 必接: 是
     * 说明: 三方配送服务商主动推送快递轨迹信息到麦芽田平台
     * 用于快递订单的轨迹追踪和状态更新
     */
    public static class Req {
        
        /**
         * 配送单号
         * <p>三方配送平台的订单号
         */
        private String orderNo;
        
        /**
         * 麦芽田侧单号
         * <p>下单接口提供的单号
         */
        private String sourceOrderNo;
        
        /**
         * 三方商户ID、门店ID
         * <p>标识订单所属的商户或门店
         */
        private String shopId;
        
        /**
         * 标签信息
         * <p>可选字段，用于业务标识
         */
        private String tag;
        
        /**
         * 轨迹列表
         * <p>按时间顺序排列的物流轨迹信息
         */
        private List<Location> locations;
        
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
        
        public String getShopId() {
            return shopId;
        }
        
        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
        
        public String getTag() {
            return tag;
        }
        
        public void setTag(String tag) {
            this.tag = tag;
        }
        
        public List<Location> getLocations() {
            return locations;
        }
        
        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }
    }
    
    /**
     * 轨迹节点信息
     */
    public static class Location {
        
        /**
         * 轨迹描述
         * <p>示例: "[合肥市]【安徽合肥沐涵公司】的西瓜揽收已揽收"
         */
        private String description;
        
        /**
         * 所在城市
         * <p>示例: "合肥市"
         */
        private String city;
        
        /**
         * 经度
         * <p>国测局02标准（如高德地图坐标系）
         */
        private String longitude;
        
        /**
         * 纬度
         * <p>国测局02标准（如高德地图坐标系）
         */
        private String latitude;
        
        /**
         * 当前状态
         * <p>可选值:
         * <ul>
         * <li>UNPROGRESS: 未处理</li>
         * <li>CREATED: 待确认</li>
         * <li>CONFIRM: 已确认</li>
         * <li>DELIVERY: 待抢单</li>
         * <li>PICKUP: 待取货</li>
         * <li>DELIVERING: 配送中</li>
         * <li>DONE: 已完成</li>
         * <li>CANCEL: 已取消</li>
         * <li>DELETE: 已删除</li>
         * <li>EXPECT: 配送异常</li>
         * </ul>
         */
        private String status;
        
        /**
         * 备注信息
         * <p>可选字段
         */
        private String remark;
        
        /**
         * 更新时间
         * <p>Unix时间戳（秒）
         */
        private long updateTime;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
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
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getRemark() {
            return remark;
        }
        
        public void setRemark(String remark) {
            this.remark = remark;
        }
        
        public long getUpdateTime() {
            return updateTime;
        }
        
        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}