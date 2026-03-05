/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.account;

/**
 * 查询余额实体
 * <p>
 * 用于麦芽田请求三方服务查询余额
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class BalanceEntity {
    
    /**
     * 查询账号余额请求参数
     * <p>
     * command: balance
     * 必接: 是
     * 说明: 麦芽田平台调用三方配送服务查询账号余额
     */
    public static class Req {
        
        /**
         * 平台方渠道ID
         * <p>三方平台账号ID
         */
        private String shopId;
        
        /**
         * 麦芽田商户token
         * <p>用于验证身份的访问令牌，可选
         */
        private String token;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getShopId() {
            return shopId;
        }
        
        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
        
        public String getToken() {
            return token;
        }
        
        public void setToken(String token) {
            this.token = token;
        }
    }
    
    /**
     * 查询账号余额响应参数
     */
    public static class Resp {
        
        /**
         * 余额金额
         * <p>单位：分
         */
        private long balance;
        
        /**
         * 查询时间
         * <p>Unix时间戳（秒）
         */
        private long atTime;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public long getBalance() {
            return balance;
        }
        
        public void setBalance(long balance) {
            this.balance = balance;
        }
        
        public long getAtTime() {
            return atTime;
        }
        
        public void setAtTime(long atTime) {
            this.atTime = atTime;
        }
    }
}