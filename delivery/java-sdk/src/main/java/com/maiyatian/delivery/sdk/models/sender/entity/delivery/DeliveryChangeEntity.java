/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.delivery;

import java.util.List;

/**
 * 配送状态同步实体
 * <p>
 * 同步订单配送状态操作，三方配送服务商主动推送订单配送状态到麦芽田平台
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeliveryChangeEntity {
    
    /**
     * 配送状态同步请求参数
     * <p>
     * command: delivery_change
     * 必接: 是
     * 说明: 同步订单配送状态操作，三方配送服务商主动推送订单配送状态到麦芽田平台
     */
    public static class Req {
        
        /**
         * 配送单号
         * <p>三方配送平台的订单号
         */
        private String orderNo;
        
        /**
         * 三方订单号
         * <p>麦芽田侧的订单号
         */
        private String sourceOrderNo;
        
        /**
         * 三方商户ID、门店ID
         * <p>标识订单所属的商户或门店
         */
        private String shopId;
        
        /**
         * 配送状态
         * <p>可选值:
         * <ul>
         * <li>GRABBED: 已分配骑手</li>
         * <li>PICKUP: 待取货</li>
         * <li>DELIVERING: 配送中</li>
         * <li>DONE: 已完成</li>
         * <li>CANCEL: 已取消</li>
         * <li>RIDER_CANCEL: 骑手取消</li>
         * <li>TRANSFER: 骑手转单</li>
         * <li>EXPECT: 配送异常</li>
         * </ul>
         */
        private String status;
        
        /**
         * 标签信息
         * <p>可选字段，用于业务标识
         */
        private String tag;
        
        /**
         * 骑手姓名
         * <p>配送员姓名
         */
        private String riderName;
        
        /**
         * 骑手电话
         * <p>配送员联系电话，格式示例: "13888888888_1234"
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
         * 取货码
         * <p>商家取货验证码
         */
        private String pickupCode;
        
        /**
         * 配送距离
         * <p>单位：米
         */
        private long distance;
        
        /**
         * 配送费
         * <p>单位：分
         */
        private long deliveryFee;
        
        /**
         * 是否接驳单
         * <p>true: 是接驳单; false: 不是接驳单
         */
        private boolean isTransship;
        
        /**
         * 取消类型
         * <ul>
         * <li>1: 用户取消</li>
         * <li>2: 商户取消</li>
         * <li>3: 客服取消</li>
         * <li>4: 系统取消</li>
         * <li>5: 其他原因</li>
         * </ul>
         * <p>Status为CANCEL时必填
         */
        private int cancelType;
        
        /**
         * 取消原因
         * <p>取消订单的具体原因描述
         */
        private String cancelReason;
        
        /**
         * 取消订单违约金
         * <p>单位：分
         */
        private long cancelDeditAmount;
        
        /**
         * 车辆信息
         * <p>配送车辆的详细信息（可选）
         */
        private VehicleInfo vehicleInfo;
        
        /**
         * 配送照片链接列表
         * <p>配送过程中的照片URL列表（可选）
         */
        private List<String> deliveryPhotoUrls;
        
        /**
         * 是否回调
         * <p>标识是否需要麦芽田平台回调
         */
        private boolean isCallback;
        
        /**
         * 更新时间
         * <p>Unix时间戳（秒）
         */
        private long updateTime;
        
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
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getTag() {
            return tag;
        }
        
        public void setTag(String tag) {
            this.tag = tag;
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
        
        public String getPickupCode() {
            return pickupCode;
        }
        
        public void setPickupCode(String pickupCode) {
            this.pickupCode = pickupCode;
        }
        
        public long getDistance() {
            return distance;
        }
        
        public void setDistance(long distance) {
            this.distance = distance;
        }
        
        public long getDeliveryFee() {
            return deliveryFee;
        }
        
        public void setDeliveryFee(long deliveryFee) {
            this.deliveryFee = deliveryFee;
        }
        
        public boolean isTransship() {
            return isTransship;
        }
        
        public void setTransship(boolean isTransship) {
            this.isTransship = isTransship;
        }
        
        public int getCancelType() {
            return cancelType;
        }
        
        public void setCancelType(int cancelType) {
            this.cancelType = cancelType;
        }
        
        public String getCancelReason() {
            return cancelReason;
        }
        
        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }
        
        public long getCancelDeditAmount() {
            return cancelDeditAmount;
        }
        
        public void setCancelDeditAmount(long cancelDeditAmount) {
            this.cancelDeditAmount = cancelDeditAmount;
        }
        
        public VehicleInfo getVehicleInfo() {
            return vehicleInfo;
        }
        
        public void setVehicleInfo(VehicleInfo vehicleInfo) {
            this.vehicleInfo = vehicleInfo;
        }
        
        public List<String> getDeliveryPhotoUrls() {
            return deliveryPhotoUrls;
        }
        
        public void setDeliveryPhotoUrls(List<String> deliveryPhotoUrls) {
            this.deliveryPhotoUrls = deliveryPhotoUrls;
        }
        
        public boolean isCallback() {
            return isCallback;
        }
        
        public void setCallback(boolean isCallback) {
            this.isCallback = isCallback;
        }
        
        public long getUpdateTime() {
            return updateTime;
        }
        
        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
        
        /**
         * 车辆信息
         */
        public static class VehicleInfo {
            
            /**
             * 车辆名称
             * <p>示例: "雪铁龙C5"
             */
            private String vehicleName;
            
            /**
             * 车辆颜色
             * <p>示例: "蓝色"
             */
            private String vehicleColor;
            
            /**
             * 车牌号
             * <p>示例: "冀E4WE32"
             */
            private String vehicleNo;
            
            // ==================== Getter 和 Setter 方法 ====================
            
            public String getVehicleName() {
                return vehicleName;
            }
            
            public void setVehicleName(String vehicleName) {
                this.vehicleName = vehicleName;
            }
            
            public String getVehicleColor() {
                return vehicleColor;
            }
            
            public void setVehicleColor(String vehicleColor) {
                this.vehicleColor = vehicleColor;
            }
            
            public String getVehicleNo() {
                return vehicleNo;
            }
            
            public void setVehicleNo(String vehicleNo) {
                this.vehicleNo = vehicleNo;
            }
        }
    }
}
