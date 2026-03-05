/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.client;

import java.time.Duration;

/**
 * 配置构建器（Builder 模式）
 * <p>
 * 提供链式调用的配置构建方式，使用更加灵活和直观
 * </p>
 * 
 * <h3>使用示例：</h3>
 * <pre>{@code
 * HttpClientConfig config = HttpClientConfig.newBuilder()
 *     .baseUrl("https://open-api-test.maiyatian.com")
 *     .apiKey("your_app_key")
 *     .apiSecret("your_app_secret")
 *     .requestTimeout(Duration.ofSeconds(30))
 *     .build();
 * }</pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigBuilder {
    
    // 基础配置
    String baseUrl = "https://open-api.maiyatian.com";
    String apiKey;
    String apiSecret;
    String sdkVersion = "1.0.0";
    
    // 连接池配置
    int maxConnections = 50;
    int maxConnectionsPerHost = 10;
    Duration keepAliveTimeout = Duration.ofSeconds(30);
    
    // 超时配置
    Duration requestTimeout = Duration.ofSeconds(60);
    Duration connectionTimeout = Duration.ofSeconds(30);
    Duration readTimeout = Duration.ofSeconds(60);
    
    // 重试配置
    int retryMaxAttempts = 3;
    Duration retryBaseDelay = Duration.ofSeconds(1);
    Duration retryMaxDelay = Duration.ofSeconds(30);
    
    // 日志配置
    boolean enableLogging = true;
    boolean logRequestBody = false;
    boolean logResponseBody = false;

    /**
     * 公共构造函数，允许外部创建Builder实例
     */
    public ConfigBuilder() {
    }

    /**
     * 设置 API 基础地址
     * 
     * @param url API 基础地址，如 https://open-api.maiyatian.com
     *           <ul>
     *           <li>测试环境：https://open-api-test.maiyatian.com</li>
     *           <li>正式环境：https://open-api.maiyatian.com</li>
     *           </ul>
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder baseUrl(String url) {
        this.baseUrl = url;
        return this;
    }

    /**
     * 设置应用密钥（app_key）
     * 
     * @param key 由麦芽田开放平台分配的应用标识
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder apiKey(String key) {
        this.apiKey = key;
        return this;
    }

    /**
     * 设置应用密钥（app_secret）
     * 
     * @param secret 由麦芽田开放平台分配的应用密钥，用于签名，请妥善保管
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder apiSecret(String secret) {
        this.apiSecret = secret;
        return this;
    }

    /**
     * 设置 SDK 版本号
     * 
     * @param version SDK 版本号
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder sdkVersion(String version) {
        this.sdkVersion = version;
        return this;
    }

    /**
     * 设置最大连接数
     * 
     * @param max 连接池最大连接数，建议值 50-100
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder maxConnections(int max) {
        this.maxConnections = max;
        return this;
    }

    /**
     * 设置每个主机的最大连接数
     * 
     * @param max 每个主机的最大连接数，建议值 10-20
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder maxConnectionsPerHost(int max) {
        this.maxConnectionsPerHost = max;
        return this;
    }

    /**
     * 设置连接保活超时时间
     * 
     * @param timeout 连接保活时间
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder keepAliveTimeout(Duration timeout) {
        this.keepAliveTimeout = timeout;
        return this;
    }

    /**
     * 设置请求超时时间
     * 
     * @param timeout 请求总超时时间，建议值 30-60 秒
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder requestTimeout(Duration timeout) {
        this.requestTimeout = timeout;
        return this;
    }

    /**
     * 设置连接建立超时时间
     * 
     * @param timeout 连接建立超时时间
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder connectionTimeout(Duration timeout) {
        this.connectionTimeout = timeout;
        return this;
    }

    /**
     * 设置读取响应超时时间
     * 
     * @param timeout 读取响应超时时间
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder readTimeout(Duration timeout) {
        this.readTimeout = timeout;
        return this;
    }

    /**
     * 设置最大重试次数
     * 
     * @param attempts 网络错误或 5xx 错误时的重试次数，建议值 2-3 次
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder retryMaxAttempts(int attempts) {
        this.retryMaxAttempts = attempts;
        return this;
    }

    /**
     * 设置重试基础延迟时间
     * 
     * @param delay 重试基础延迟时间
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder retryBaseDelay(Duration delay) {
        this.retryBaseDelay = delay;
        return this;
    }

    /**
     * 设置重试最大延迟时间
     * 
     * @param delay 重试最大延迟时间
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder retryMaxDelay(Duration delay) {
        this.retryMaxDelay = delay;
        return this;
    }

    /**
     * 设置是否启用日志记录
     * 
     * @param enable true 启用日志，false 禁用日志
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder enableLogging(boolean enable) {
        this.enableLogging = enable;
        return this;
    }

    /**
     * 设置是否记录请求体
     * 
     * @param log true 记录请求体，false 不记录（注意：可能包含敏感信息）
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder logRequestBody(boolean log) {
        this.logRequestBody = log;
        return this;
    }

    /**
     * 设置是否记录响应体
     * 
     * @param log true 记录响应体，false 不记录
     * @return 构建器实例，支持链式调用
     */
    public ConfigBuilder logResponseBody(boolean log) {
        this.logResponseBody = log;
        return this;
    }

    /**
     * 构建最终配置
     * <p>验证必填字段后返回配置对象</p>
     * 
     * @return HTTP 客户端配置实例
     * @throws IllegalArgumentException 如果必填字段缺失
     */
    public HttpClientConfig build() {
        // 验证必填字段
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("BaseURL 不能为空，请使用 baseUrl() 方法设置 API 基础地址");
        }
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("APIKey 不能为空，请使用 apiKey() 方法设置应用密钥");
        }
        if (apiSecret == null || apiSecret.trim().isEmpty()) {
            throw new IllegalArgumentException("APISecret 不能为空，请使用 apiSecret() 方法设置应用密钥");
        }

        // 创建并返回配置实例
        return new HttpClientConfig(this);
    }
}