/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.account;

/**
 * 获取充值链接实体
 * <p>
 * 用于麦芽田请求三方服务获取充值链接
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RechargeUrlEntity {
    
    /**
     * 获取充值链接请求参数
     * <p>
     * command: recharge_url
     * 必接: 是
     * 说明: 麦芽田平台调用三方配送服务获取在线充值链接
     */
    public static class Req {
        
        /**
         * 平台方渠道ID
         * <p>三方平台账号ID
         */
        private String shopId;
        

        // ==================== Getter 和 Setter 方法 ====================
        
        public String getShopId() {
            return shopId;
        }
        
        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
        
    }
    
    /**
     * 获取充值链接响应参数
     */
    public static class Resp {
        
        /**
         * 三方充值链接
         * <p>用于跳转到第三方充值页面的URL
         */
        private String rechargeUrl;
        
        /**
         * 生成时间
         * <p>Unix时间戳（秒）
         */
        private long atTime;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getRechargeUrl() {
            return rechargeUrl;
        }
        
        public void setRechargeUrl(String rechargeUrl) {
            this.rechargeUrl = rechargeUrl;
        }
        
        public long getAtTime() {
            return atTime;
        }
        
        public void setAtTime(long atTime) {
            this.atTime = atTime;
        }
    }
}