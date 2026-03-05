/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.types;

/**
 * 标准请求结构
 * <p>
 * 包含应用密钥、请求ID、令牌、时间戳、业务数据和签名
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class Request {
    
    /**
     * 应用密钥，用于标识调用方
     */
    private String appKey;
    
    /**
     * 请求唯一标识，用于追踪请求链路
     */
    private String requestId;
    
    /**
     * 授权令牌，用于身份验证
     */
    private String token;
    
    /**
     * 请求时间戳，Unix秒级时间戳
     */
    private long timestamp;
    
    /**
     * 业务数据，JSON字符串格式
     */
    private String data;
    
    /**
     * 请求签名，HmacSHA256签名值
     */
    private String signature;
    
    // ==================== 构造函数 ====================
    
    /**
     * 默认构造函数
     */
    public Request() {
    }
    
    /**
     * 完整构造函数
     * 
     * @param appKey 应用密钥
     * @param requestId 请求唯一标识
     * @param token 授权令牌
     * @param timestamp 请求时间戳
     * @param data 业务数据
     * @param signature 请求签名
     */
    public Request(String appKey, String requestId, String token, long timestamp, String data, String signature) {
        this.appKey = appKey;
        this.requestId = requestId;
        this.token = token;
        this.timestamp = timestamp;
        this.data = data;
        this.signature = signature;
    }
    
    // ==================== Getter 和 Setter 方法 ====================
    
    public String getAppKey() {
        return appKey;
    }
    
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getSignature() {
        return signature;
    }
    
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    // ==================== Object 方法 ====================
    
    @Override
    public String toString() {
        return "Request{" +
                "appKey='" + appKey + '\'' +
                ", requestId='" + requestId + '\'' +
                ", token='" + (token != null ? token.substring(0, Math.min(token.length(), 8)) + "***" : "null") + '\'' +
                ", timestamp=" + timestamp +
                ", data='" + data + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}