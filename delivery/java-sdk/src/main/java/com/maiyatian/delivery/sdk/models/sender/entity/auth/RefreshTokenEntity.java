/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.auth;

/**
 * 刷新访问令牌Token实体
 * <p>
 * 当token过期时，使用(有效期内的)refresh_token重新获取新的token
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RefreshTokenEntity {
    
    /**
     * 刷新访问令牌请求参数
     * <p>
     * command: refresh_token
     * 必接: 是
     * 说明: 当token过期时，使用(有效期内的)refresh_token重新获取新的token
     * 注意: 该接口刷新得到新的token和refresh_token，旧的token随即在5分钟内失效
     */
    public static class Req {
        
        /**
         * 访问令牌
         * <p>需要刷新的访问令牌
         */
        private String token;
        
        /**
         * 刷新授权token
         * <p>用于获取新的访问令牌
         */
        private String refreshToken;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getToken() {
            return token;
        }
        
        public void setToken(String token) {
            this.token = token;
        }
        
        public String getRefreshToken() {
            return refreshToken;
        }
        
        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
    
    /**
     * 刷新访问令牌响应参数
     */
    public static class Resp {
        
        /**
         * 新的麦芽田授权token
         * <p>用于后续API调用认证
         */
        private String token;
        
        /**
         * 新的刷新授权token
         * <p>用于下次token过期后刷新
         */
        private String refreshToken;
        
        /**
         * 新token过期时间
         * <p>Unix时间戳，默认30天
         */
        private long expireTime;
        
        /**
         * 新refresh_token过期时间
         * <p>Unix时间戳，默认30天
         * <p>注意: 需要在时效内用此接口再换取新的refresh_token才不会出现用户授权频繁失效的情况
         */
        private long refreshExpireTime;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getToken() {
            return token;
        }
        
        public void setToken(String token) {
            this.token = token;
        }
        
        public String getRefreshToken() {
            return refreshToken;
        }
        
        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
        
        public long getExpireTime() {
            return expireTime;
        }
        
        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }
        
        public long getRefreshExpireTime() {
            return refreshExpireTime;
        }
        
        public void setRefreshExpireTime(long refreshExpireTime) {
            this.refreshExpireTime = refreshExpireTime;
        }
    }
}