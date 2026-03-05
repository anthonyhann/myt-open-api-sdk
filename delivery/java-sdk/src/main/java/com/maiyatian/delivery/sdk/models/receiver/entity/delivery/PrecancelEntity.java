/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

/**
 * 预取消配送实体
 * <p>
 * 用于麦芽田请求三方服务预查询取消订单的违约金等信息
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrecancelEntity {
    
    /**
     * 预取消配送请求参数
     * <p>
     * command: precancel
     * 必接: 是
     * 说明: 麦芽田平台调用三方配送服务预查询取消订单的违约金等信息
     * 注意: 此接口仅查询取消信息，不会实际取消订单
     */
    public static class Req {
        
        /**
         * 麦芽田侧订单号
         * <p>麦芽田平台的订单号
         */
        private String sourceOrderNo;
        
        /**
         * 三方配送单号
         * <p>配送服务商的订单号
         */
        private String sourceDeliveryNo;
        
        /**
         * 取消类型
         * <ul>
         * <li>1: 用户取消</li>
         * <li>2: 商户取消</li>
         * <li>3: 客服取消</li>
         * <li>4: 系统取消</li>
         * <li>5: 其他原因</li>
         * </ul>
         * 可选字段，详见取消类型枚举
         */
        private int cancelReasonType;
        
        /**
         * 取消原因
         * <p>取消订单的具体原因描述，可选
         */
        private String cancelReason;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getSourceOrderNo() {
            return sourceOrderNo;
        }
        
        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }
        
        public String getSourceDeliveryNo() {
            return sourceDeliveryNo;
        }
        
        public void setSourceDeliveryNo(String sourceDeliveryNo) {
            this.sourceDeliveryNo = sourceDeliveryNo;
        }
        
        public int getCancelReasonType() {
            return cancelReasonType;
        }
        
        public void setCancelReasonType(int cancelReasonType) {
            this.cancelReasonType = cancelReasonType;
        }
        
        public String getCancelReason() {
            return cancelReason;
        }
        
        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }
    }
    
    /**
     * 预取消配送响应参数
     */
    public static class Resp {
        
        /**
         * 预取消接口处理状态
         * <p>1: 成功; 0: 失败
         */
        private int status;
        
        /**
         * 错误码
         * <p>处理失败时的错误码
         */
        private String errorCode;
        
        /**
         * 取消违约金
         * <p>单位：分
         * 表示如果取消此订单需要支付的违约金
         */
        private int cancelAmount;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public int getStatus() {
            return status;
        }
        
        public void setStatus(int status) {
            this.status = status;
        }
        
        public String getErrorCode() {
            return errorCode;
        }
        
        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
        
        public int getCancelAmount() {
            return cancelAmount;
        }
        
        public void setCancelAmount(int cancelAmount) {
            this.cancelAmount = cancelAmount;
        }
    }
}