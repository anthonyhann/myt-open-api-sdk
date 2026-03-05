/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

/**
 * 取消配送实体
 * <p>
 * 用于麦芽田请求三方服务取消配送，command值为cancel，是必接接口
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CancelEntity {
    
    /**
     * 取消配送请求参数
     * <p>
     * command: cancel
     * 必接: 是
     * 说明: 麦芽田平台调用三方配送服务取消配送订单
     */
    public static class Req {
        
        /**
         * 麦芽田侧运单号
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
         * 详见取消类型枚举
         */
        private int cancelReasonType;
        
        /**
         * 取消原因
         * <p>取消订单的具体原因描述
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
     * 取消配送响应参数
     */
    public static class Resp {
        
        /**
         * 处理状态
         * <p>1: 成功; 0: 失败
         */
        private int status = 1;
        
        /**
         * 错误码
         * <p>取消失败时的错误码
         */
        private String errorCode;
        
        /**
         * 失败原因
         * <p>取消失败时的原因描述
         */
        private String reason;
        
        /**
         * 取消违约金
         * <p>单位：分
         */
        private long cancelAmount;
        
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
        
        public String getReason() {
            return reason;
        }
        
        public void setReason(String reason) {
            this.reason = reason;
        }
        
        public long getCancelAmount() {
            return cancelAmount;
        }
        
        public void setCancelAmount(long cancelAmount) {
            this.cancelAmount = cancelAmount;
        }
    }
}