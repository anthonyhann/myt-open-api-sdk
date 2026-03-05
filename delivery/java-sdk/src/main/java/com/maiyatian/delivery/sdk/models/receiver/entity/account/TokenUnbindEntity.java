/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.account;

/**
 * 授权解绑实体
 * <p>
 * 当用户解绑授权时，麦芽田平台调用此接口通知三方配送服务
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class TokenUnbindEntity {
    
    /**
     * 授权解绑请求参数
     * <p>
     * command: token_unbind
     * 必接: 是
     * 说明: 当用户解绑授权时，麦芽田平台调用此接口通知三方配送服务
     */
    public static class Req {
        
        /**
         * 平台方渠道ID
         * <p>需要解绑的商户ID
         */
        private String shopId;
        
        /**
         * 麦芽田商户token
         * <p>需要解绑的访问令牌
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
     * 授权解绑响应参数
     */
    public static class Resp {
        
        /**
         * 平台方渠道ID
         * <p>已解绑的商户ID
         */
        private String shopId;
        
        /**
         * 麦芽田商户token
         * <p>已解绑的访问令牌
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
}