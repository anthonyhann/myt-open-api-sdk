/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.auth;

/**
 * 获取访问令牌实体
 * <p>
 * 用于OAuth2授权码换取访问令牌，支持门店、商户和三方门店直联三种授权模式
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class AccessTokenEntity {
    
    /**
     * 获取访问令牌请求实体
     * <p>
     * 用于OAuth2授权码换取访问令牌，支持门店、商户和三方门店直联三种授权模式
     * 对应接口：access_token (command: access_token)
     */
    public static class Req {
        
        /**
         * 授权级别类型，必填
         * <ul>
         * <li>1: 门店授权</li>
         * <li>2: 商户授权</li>
         * </ul>
         * 不同授权级别决定了后续可访问的API范围和权限
         */
        private String grantType;
        
        /**
         * 授权码，当GrantType为1或2时必填
         * <p>OAuth2流程中获取的临时授权码，有效期5分钟
         * <p>多次使用同一个code获取的token是相同的
         */
        private String code;
        
        /**
         * 三方平台最小维度的唯一ID，必填
         * <p>如果平台方是门店维度则填写门店ID
         * <p>用于标识具体的业务实体
         */
        private String storeId;
        
        /**
         * 三方平台账号注册手机号，必填
         * <p>用于身份验证和联系方式确认
         */
        private String mobile;
        
        /**
         * 城市名称，必填
         * <p>格式示例："北京市"，用于确定服务区域
         */
        private String city;
        
        /**
         * 标准城市行政编码，必填
         * <p>格式示例："110100"，对应国家标准的城市编码
         */
        private String cityCode;
        
        /**
         * 三方平台最小维度的唯一ID对应密钥，可选
         * <p>用于增强安全性的额外验证参数
         */
        private String sourceKey;
        
        /**
         * 快递平台唯一标识，可选
         * <p>格式示例："JD"（京东）
         * <p>用于标识具体的快递服务提供商
         */
        private String platform;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getGrantType() {
            return grantType;
        }
        
        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }
        
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public String getStoreId() {
            return storeId;
        }
        
        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
        
        public String getMobile() {
            return mobile;
        }
        
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public String getCityCode() {
            return cityCode;
        }
        
        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
        
        public String getSourceKey() {
            return sourceKey;
        }
        
        public void setSourceKey(String sourceKey) {
            this.sourceKey = sourceKey;
        }
        
        public String getPlatform() {
            return platform;
        }
        
        public void setPlatform(String platform) {
            this.platform = platform;
        }
    }
    
    /**
     * 获取访问令牌响应实体
     * <p>
     * 包含访问令牌、刷新令牌以及相关的过期时间信息
     */
    public static class Resp {
        
        /**
         * 麦芽田平台方渠道ID
         * <p>用于标识在麦芽田平台中的渠道身份
         */
        private String shopId;
        
        /**
         * 麦芽田授权访问令牌，必填
         * <p>用于后续API调用的身份认证
         * <p>有效期30天，过期后需要使用RefreshToken刷新
         */
        private String token;
        
        /**
         * 刷新授权令牌，必填
         * <p>用于在Token过期时获取新的访问令牌
         * <p>有效期30天，建议在有效期内及时刷新
         */
        private String refreshToken;
        
        /**
         * Token过期时间
         * <p>Unix时间戳格式（精确到秒）
         * <p>0表示永久不过期，大于0表示具体的过期时间
         */
        private long expireTime;
        
        /**
         * RefreshToken过期时间
         * <p>Unix时间戳格式（精确到秒）
         * <p>RefreshToken的有效期，过期后需要重新授权
         */
        private long refreshExpireTime;
        
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