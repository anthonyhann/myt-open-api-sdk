/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

/**
 * 查询配送详情实体
 * <p>
 * 用于麦芽田请求三方服务查询配送详情，command值为query_info，是必接接口
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class QueryInfoEntity {
    
    /**
     * 查询配送详情请求参数
     * <p>
     * command: query_info
     * 必接: 是
     * 说明: 麦芽田平台调用三方配送服务查询订单配送详情
     */
    public static class Req {
        
        /**
         * 配送单号
         * <p>三方配送平台的订单号
         */
        private String orderNo;
        
        /**
         * 麦芽田侧订单号
         * <p>麦芽田平台的订单号
         */
        private String sourceOrderNo;
        
        /**
         * 手机号码
         * <p>用于查询的手机号
         */
        private String mobile;
        
        /**
         * 付款类型
         * <p>业务类型标识
         */
        private long type;
        
        /**
         * 配送方编码
         * <p>配送服务商的编码标识
         */
        private String shipperCode;
        
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
        
        public String getMobile() {
            return mobile;
        }
        
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        
        public long getType() {
            return type;
        }
        
        public void setType(long type) {
            this.type = type;
        }
        
        public String getShipperCode() {
            return shipperCode;
        }
        
        public void setShipperCode(String shipperCode) {
            this.shipperCode = shipperCode;
        }
    }
    
    /**
     * 查询配送详情响应参数
     */
    public static class Resp {
        
        /**
         * 三方运单号
         * <p>配送服务商的订单号
         */
        private String orderNo;
        
        /**
         * 麦芽田订单号
         * <p>麦芽田平台的订单号
         */
        private String sourceOrderNo;
        
        /**
         * 配送状态
         * <p>可选值: GRABBED, PICKUP, DELIVERING, DONE, CANCEL, RIDER_CANCEL, TRANSFER, EXPECT
         * 详见配送状态枚举
         */
        private String status;
        
        /**
         * 状态名称
         * <p>状态的中文描述，如"待接单"
         */
        private String statusName;
        
        /**
         * 运费（单位：分）
         */
        private long payAmount;
        
        /**
         * 优惠费用（单位：分，如遇没有可传0）
         */
        private long coupon;
        
        /**
         * 溢价（单位：分，如遇没有可传0）
         */
        private long premium;
        
        /**
         * 加费（单位：分，如遇没有可传0）
         */
        private long tips;
        
        /**
         * 配送距离（单位：米）
         */
        private long distance;
        
        /**
         * 下单时间
         * <p>Unix时间戳（秒）
         */
        private long createTime;
        
        /**
         * 接单时间
         * <p>Unix时间戳（秒），如遇没有可传0
         */
        private long acceptTime;
        
        /**
         * 取货时间
         * <p>Unix时间戳（秒），如遇没有可传0
         */
        private long fetchTime;
        
        /**
         * 完成时间
         * <p>Unix时间戳（秒），如遇没有可传0
         */
        private long finishTime;
        
        /**
         * 取消时间
         * <p>Unix时间戳（秒），如遇没有可传0
         */
        private long cancelTime;
        
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
         * 是否转单
         * <p>true: 是转单; false: 不是转单
         */
        private boolean isTransship;
        
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
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getStatusName() {
            return statusName;
        }
        
        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
        
        public long getPayAmount() {
            return payAmount;
        }
        
        public void setPayAmount(long payAmount) {
            this.payAmount = payAmount;
        }
        
        public long getCoupon() {
            return coupon;
        }
        
        public void setCoupon(long coupon) {
            this.coupon = coupon;
        }
        
        public long getPremium() {
            return premium;
        }
        
        public void setPremium(long premium) {
            this.premium = premium;
        }
        
        public long getTips() {
            return tips;
        }
        
        public void setTips(long tips) {
            this.tips = tips;
        }
        
        public long getDistance() {
            return distance;
        }
        
        public void setDistance(long distance) {
            this.distance = distance;
        }
        
        public long getCreateTime() {
            return createTime;
        }
        
        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
        
        public long getAcceptTime() {
            return acceptTime;
        }
        
        public void setAcceptTime(long acceptTime) {
            this.acceptTime = acceptTime;
        }
        
        public long getFetchTime() {
            return fetchTime;
        }
        
        public void setFetchTime(long fetchTime) {
            this.fetchTime = fetchTime;
        }
        
        public long getFinishTime() {
            return finishTime;
        }
        
        public void setFinishTime(long finishTime) {
            this.finishTime = finishTime;
        }
        
        public long getCancelTime() {
            return cancelTime;
        }
        
        public void setCancelTime(long cancelTime) {
            this.cancelTime = cancelTime;
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
        
        public boolean isTransship() {
            return isTransship;
        }
        
        public void setTransship(boolean isTransship) {
            this.isTransship = isTransship;
        }
    }
}