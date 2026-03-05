/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.auth;

/**
 * OAuth2授权码实体定义
 * <p>
 * 用于授权码模式获取访问令牌的授权流程
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CreateCodeEntity {
    
    /**
     * OAuth2授权码请求实体
     * <p>
     * 用于麦芽田平台OAuth2授权流程中的授权码请求，遵循OAuth2.0规范
     * 主要用于商户绑定配送账号的授权场景
     */
    public static class Req {
        
        /**
         * 授权响应类型，固定为"code"表示使用授权码模式
         * <p>遵循OAuth2.0授权码流程规范
         */
        private String responseType = "code";
        
        /**
         * 授权页面展示类型，默认为"web"网页版
         * <p>支持不同终端的授权页面适配
         */
        private String view = "web";
        
        /**
         * 授权回调地址
         * <p>授权成功后重定向的URI，必须与应用注册时的回调地址一致
         * 格式：https://your-domain.com/callback
         */
        private String redirectUri;
        
        /**
         * 授权码
         * <p>OAuth2授权流程中获得的临时授权码，有效期5分钟
         * 用于换取长期访问令牌（access_token）
         */
        private String code;
        
        /**
         * 防CSRF攻击的状态码，授权服务器会原样返回
         */
        private String state;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getResponseType() {
            return responseType;
        }
        
        public void setResponseType(String responseType) {
            this.responseType = responseType;
        }
        
        public String getView() {
            return view;
        }
        
        public void setView(String view) {
            this.view = view;
        }
        
        public String getRedirectUri() {
            return redirectUri;
        }
        
        public void setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
        }
        
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public String getState() {
            return state;
        }
        
        public void setState(String state) {
            this.state = state;
        }
    }
}