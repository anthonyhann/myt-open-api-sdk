/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import com.maiyatian.delivery.sdk.consts.Constants;
import com.maiyatian.delivery.sdk.vars.Variables;

import java.time.Duration;

/**
 * HTTP客户端配置构建器
 * <p>
 * 使用Builder模式构建HttpClientConfig，提供流式API和默认值自动填充
 * 对应Go SDK中的ConfigBuilder功能
 * </p>
 * 
 * <h3>功能特性：</h3>
 * <ul>
 * <li><strong>流式API</strong>：支持链式调用，代码简洁易读</li>
 * <li><strong>默认值</strong>：自动填充合理的默认配置</li>
 * <li><strong>验证机制</strong>：构建时自动验证必填项</li>
 * <li><strong>类型安全</strong>：编译时检查参数类型</li>
 * </ul>
 * 
 * <h3>对应关系：</h3>
 * <ul>
 * <li>Go: {@code client.NewConfigBuilder()...Build()} → 
 *     Java: {@code HttpClientConfig.builder()...build()}</li>
 * <li>Go: {@code BaseURL("xxx")} → Java: {@code baseUrl("xxx")}</li>
 * </ul>
 * 
 * <h3>使用示例：</h3>
 * <pre>{@code
 * // 基本配置
 * HttpClientConfig config = HttpClientConfig.builder()
 *     .baseUrl("https://open-api-test.maiyatian.com")
 *     .apiKey("your_app_key")
 *     .apiSecret("your_app_secret")
 *     .build();
 * 
 * // 完整配置
 * HttpClientConfig config = HttpClientConfig.builder()
 *     .baseUrl("https://open-api.maiyatian.com")
 *     .apiKey("your_app_key")
 *     .apiSecret("your_app_secret")
 *     .maxConnections(100)
 *     .requestTimeout(Duration.ofSeconds(30))
 *     .retryMaxAttempts(5)
 *     .enableLogging(true)
 *     .build();
 * }</pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigBuilder {
    
    // ==================== 基础配置 ====================
    
    /**
     * 麦芽田开放平台 API 基础地址
     */
    String baseUrl = Constants.PROD_BASE_URL;
    
    /**
     * 应用密钥（app_key）
     */
    String apiKey;
    
    /**
     * 应用密钥（app_secret）
     */
    String apiSecret;
    
    /**
     * SDK 版本号
     */
    String sdkVersion = Variables.getVersion();
    
    // ==================== 连接池配置 ====================
    
    /**
     * 最大连接数
     */
    int maxConnections = 50;
    
    /**
     * 每个主机的最大连接数
     */
    int maxConnectionsPerHost = 10;
    
    /**
     * 连接保活超时时间
     */
    Duration keepAliveTimeout = Duration.ofSeconds(30);
    
    // ==================== 超时配置 ====================
    
    /**
     * 请求总超时时间
     */
    Duration requestTimeout = Duration.ofSeconds(60);
    
    /**
     * 连接建立超时时间
     */
    Duration connectionTimeout = Duration.ofSeconds(30);
    
    /**
     * 读取响应超时时间
     */
    Duration readTimeout = Duration.ofSeconds(60);
    
    // ==================== 重试配置 ====================
    
    /**
     * 最大重试次数
     */
    int retryMaxAttempts = 3;
    
    /**
     * 重试基础延迟时间
     */
    Duration retryBaseDelay = Duration.ofSeconds(1);
    
    /**
     * 重试最大延迟时间
     */
    Duration retryMaxDelay = Duration.ofSeconds(30);
    
    // ==================== 日志配置 ====================
    
    /**
     * 是否启用日志记录
     */
    boolean enableLogging = true;
    
    /**
     * 是否记录请求体
     */
    boolean logRequestBody = false;
    
    /**
     * 是否记录响应体
     */
    boolean logResponseBody = false;
    
    // ==================== 构造函数 ====================
    
    /**
     * 公共构造函数
     * <p>
     * 初始化默认配置值
     * </p>
     */
    public ConfigBuilder() {
        // 默认值已在字段声明时设置
    }
    
    // ==================== 基础配置方法 ====================
    
    /**
     * 设置API服务基础URL
     * <p>
     * 对应Go SDK中的BaseURL()方法
     * </p>
     * 
     * <h3>环境地址：</h3>
     * <ul>
     * <li>测试环境: {@value Constants#TEST_BASE_URL}</li>
     * <li>正式环境: {@value Constants#PROD_BASE_URL}</li>
     * </ul>
     * 
     * @param url API服务地址
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果URL为null或空字符串
     */
    public ConfigBuilder baseUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("BaseURL不能为空");
        }
        this.baseUrl = url.trim();
        return this;
    }
    
    /**
     * 设置应用密钥（app_key）
     * <p>
     * 对应Go SDK中的APIKey()方法
     * </p>
     * 
     * @param key 从麦芽田开放平台申请获得的AppKey
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果key为null或空字符串
     */
    public ConfigBuilder apiKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("APIKey不能为空");
        }
        this.apiKey = key.trim();
        return this;
    }
    
    /**
     * 设置应用密钥（app_secret）
     * <p>
     * 对应Go SDK中的APISecret()方法
     * </p>
     * 
     * @param secret 从麦芽田开放平台申请获得的AppSecret
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果secret为null或空字符串
     */
    public ConfigBuilder apiSecret(String secret) {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalArgumentException("APISecret不能为空");
        }
        this.apiSecret = secret.trim();
        return this;
    }
    
    /**
     * 设置SDK版本号
     * <p>
     * 通常不需要手动设置，会自动使用Variables.getVersion()
     * </p>
     * 
     * @param version SDK版本号
     * @return ConfigBuilder实例，支持链式调用
     */
    public ConfigBuilder sdkVersion(String version) {
        this.sdkVersion = version;
        return this;
    }
    
    // ==================== 连接池配置方法 ====================
    
    /**
     * 设置最大连接数
     * <p>
     * 对应Go SDK中的MaxConnections()方法
     * </p>
     * 
     * <h3>建议配置：</h3>
     * <ul>
     * <li>低并发（&lt;10 QPS）: 20-50</li>
     * <li>中并发（10-100 QPS）: 50-100</li>
     * <li>高并发（&gt;100 QPS）: 100-200</li>
     * </ul>
     * 
     * @param max 整个客户端的最大TCP连接数
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果max小于等于0
     */
    public ConfigBuilder maxConnections(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("最大连接数必须大于0");
        }
        this.maxConnections = max;
        return this;
    }
    
    /**
     * 设置每个主机的最大连接数
     * 
     * @param max 每个主机的最大连接数
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果max小于等于0
     */
    public ConfigBuilder maxConnectionsPerHost(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("每主机最大连接数必须大于0");
        }
        this.maxConnectionsPerHost = max;
        return this;
    }
    
    /**
     * 设置连接保活超时时间
     * 
     * @param timeout 连接保活时间
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果timeout为null或非正数
     */
    public ConfigBuilder keepAliveTimeout(Duration timeout) {
        if (timeout == null || timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("连接保活时间必须为正数");
        }
        this.keepAliveTimeout = timeout;
        return this;
    }
    
    // ==================== 超时配置方法 ====================
    
    /**
     * 设置请求超时时间
     * <p>
     * 对应Go SDK中的RequestTimeout()方法
     * </p>
     * 
     * <h3>建议配置：</h3>
     * <ul>
     * <li>快速接口（查询类）: 10-30秒</li>
     * <li>一般接口: 30-60秒</li>
     * <li>慢速接口（处理类）: 60-120秒</li>
     * </ul>
     * 
     * @param timeout 整个HTTP请求的总超时时间
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果timeout为null或非正数
     */
    public ConfigBuilder requestTimeout(Duration timeout) {
        if (timeout == null || timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("请求超时时间必须为正数");
        }
        this.requestTimeout = timeout;
        return this;
    }
    
    /**
     * 设置连接建立超时时间
     * 
     * @param timeout 连接建立超时时间
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果timeout为null或非正数
     */
    public ConfigBuilder connectionTimeout(Duration timeout) {
        if (timeout == null || timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("连接超时时间必须为正数");
        }
        this.connectionTimeout = timeout;
        return this;
    }
    
    /**
     * 设置读取响应超时时间
     * 
     * @param timeout 读取响应超时时间
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果timeout为null或非正数
     */
    public ConfigBuilder readTimeout(Duration timeout) {
        if (timeout == null || timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("读取超时时间必须为正数");
        }
        this.readTimeout = timeout;
        return this;
    }
    
    // ==================== 重试配置方法 ====================
    
    /**
     * 设置最大重试次数
     * <p>
     * 对应Go SDK中的RetryMaxAttempts()方法
     * </p>
     * 
     * <h3>重试场景：</h3>
     * <ul>
     * <li>网络错误（连接失败、超时等）</li>
     * <li>HTTP 5xx错误</li>
     * <li>业务状态码5xx错误</li>
     * </ul>
     * 
     * @param attempts 失败时的最大重试次数
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果attempts为负数
     */
    public ConfigBuilder retryMaxAttempts(int attempts) {
        if (attempts < 0) {
            throw new IllegalArgumentException("最大重试次数不能为负数");
        }
        this.retryMaxAttempts = attempts;
        return this;
    }
    
    /**
     * 设置重试基础延迟时间
     * 
     * @param delay 第一次重试前的等待时间
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果delay为null或负数
     */
    public ConfigBuilder retryBaseDelay(Duration delay) {
        if (delay == null || delay.isNegative()) {
            throw new IllegalArgumentException("重试基础延迟不能为负数");
        }
        this.retryBaseDelay = delay;
        return this;
    }
    
    /**
     * 设置重试最大延迟时间
     * 
     * @param delay 重试等待时间的上限
     * @return ConfigBuilder实例，支持链式调用
     * @throws IllegalArgumentException 如果delay为null或负数
     */
    public ConfigBuilder retryMaxDelay(Duration delay) {
        if (delay == null || delay.isNegative()) {
            throw new IllegalArgumentException("重试最大延迟不能为负数");
        }
        this.retryMaxDelay = delay;
        return this;
    }
    
    // ==================== 日志配置方法 ====================
    
    /**
     * 启用日志记录
     * <p>
     * 对应Go SDK中的EnableLogging()方法
     * </p>
     * 
     * <h3>日志内容：</h3>
     * <ul>
     * <li>请求方法、URL、时间戳</li>
     * <li>响应状态码、响应时间</li>
     * <li>错误信息（如果有）</li>
     * </ul>
     * 
     * @param enable true启用，false禁用
     * @return ConfigBuilder实例，支持链式调用
     */
    public ConfigBuilder enableLogging(boolean enable) {
        this.enableLogging = enable;
        return this;
    }
    
    /**
     * 启用请求体日志记录
     * 
     * @param enable true启用，false禁用
     * @return ConfigBuilder实例，支持链式调用
     */
    public ConfigBuilder logRequestBody(boolean enable) {
        this.logRequestBody = enable;
        return this;
    }
    
    /**
     * 启用响应体日志记录
     * 
     * @param enable true启用，false禁用
     * @return ConfigBuilder实例，支持链式调用
     */
    public ConfigBuilder logResponseBody(boolean enable) {
        this.logResponseBody = enable;
        return this;
    }
    
    // ==================== 构建方法 ====================
    
    /**
     * 构建最终配置
     * <p>
     * 对应Go SDK中的Build()方法
     * 完成配置构建并返回配置对象，自动验证必填项
     * </p>
     * 
     * <h3>验证规则：</h3>
     * <ul>
     * <li>BaseURL: 必填，不能为空</li>
     * <li>APIKey: 必填，不能为空</li>
     * <li>APISecret: 必填，不能为空</li>
     * </ul>
     * 
     * @return 构建完成的配置对象
     * @throws IllegalStateException 如果必填项未设置
     */
    public HttpClientConfig build() {
        // 验证必填字段
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalStateException("BaseURL不能为空，请使用baseUrl()方法设置API基础地址");
        }
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("APIKey不能为空，请使用apiKey()方法设置应用密钥");
        }
        if (apiSecret == null || apiSecret.trim().isEmpty()) {
            throw new IllegalStateException("APISecret不能为空，请使用apiSecret()方法设置应用密钥");
        }
        
        // 创建配置对象（手动设置属性）
        HttpClientConfig config = new HttpClientConfig();
        config.setBaseUrl(baseUrl);
        config.setApiKey(apiKey);
        config.setApiSecret(apiSecret);
        config.setSdkVersion(sdkVersion);
        config.setMaxConnections(maxConnections);
        config.setMaxConnectionsPerHost(maxConnectionsPerHost);
        config.setKeepAliveTimeout(keepAliveTimeout);
        config.setRequestTimeout(requestTimeout);
        config.setConnectionTimeout(connectionTimeout);
        config.setReadTimeout(readTimeout);
        config.setRetryMaxAttempts(retryMaxAttempts);
        config.setRetryBaseDelay(retryBaseDelay);
        config.setRetryMaxDelay(retryMaxDelay);
        config.setEnableLogging(enableLogging);
        config.setLogRequestBody(logRequestBody);
        config.setLogResponseBody(logResponseBody);
        
        return config;
    }
}