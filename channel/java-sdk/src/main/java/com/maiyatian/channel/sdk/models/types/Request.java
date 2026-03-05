/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 麦芽田 API 基础请求结构
 * <p>
 * 包含所有 API 请求的公共字段
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class Request {

    /**
     * 应用密钥，由麦芽田开放平台分配
     */
    @JsonProperty("app_key")
    private String appKey;

    /**
     * 商户授权令牌
     */
    @JsonProperty("token")
    private String token;

    /**
     * 请求唯一标识（UUID），每次请求需生成新值
     */
    @JsonProperty("request_id")
    private String requestId;

    /**
     * 请求签名，用于验证请求合法性
     */
    @JsonProperty("signature")
    private String signature;

    /**
     * 请求时间戳（Unix 秒），需与实际时间误差不超过 10 分钟
     */
    @JsonProperty("timestamp")
    private Long timestamp;

    /**
     * 业务参数，序列化为 JSON 格式的字符串
     */
    @JsonProperty("data")
    private String data;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public Request() {
    }

    /**
     * 全参构造函数
     */
    public Request(String appKey, String token, String requestId, String signature, Long timestamp, String data) {
        this.appKey = appKey;
        this.token = token;
        this.requestId = requestId;
        this.signature = signature;
        this.timestamp = timestamp;
        this.data = data;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(appKey, request.appKey) &&
               Objects.equals(token, request.token) &&
               Objects.equals(requestId, request.requestId) &&
               Objects.equals(signature, request.signature) &&
               Objects.equals(timestamp, request.timestamp) &&
               Objects.equals(data, request.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appKey, token, requestId, signature, timestamp, data);
    }

    @Override
    public String toString() {
        return "Request{" +
                "appKey='" + (appKey != null ? appKey.substring(0, Math.min(appKey.length(), 8)) + "***" : "null") + '\'' +
                ", token='" + (token != null ? token.substring(0, Math.min(token.length(), 8)) + "***" : "null") + '\'' +
                ", requestId='" + requestId + '\'' +
                ", signature='***'" +
                ", timestamp=" + timestamp +
                ", data='" + (data != null ? data.substring(0, Math.min(data.length(), 50)) + (data.length() > 50 ? "..." : "") : "null") + '\'' +
                '}';
    }
}