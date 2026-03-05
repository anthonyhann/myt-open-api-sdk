/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import java.time.Duration;
import java.util.Objects;

/**
 * 企业级 HTTP 客户端配置
 * <p>
 * 提供完整的连接池、超时、重试、日志等企业级特性配置
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpClientConfig {
    
    // ==================== 基础配置 ====================
    
    /**
     * 麦芽田开放平台 API 基础地址
     * <ul>
     * <li>测试环境：https://open-api-test.maiyatian.com</li>
     * <li>正式环境：https://open-api.maiyatian.com</li>
     * </ul>
     */
    private String baseUrl = "https://open-api.maiyatian.com";
    
    /**
     * 应用密钥（app_key）
     * <p>由麦芽田开放平台分配，用于标识应用身份</p>
     */
    private String apiKey;
    
    /**
     * 应用密钥（app_secret）
     * <p>由麦芽田开放平台分配，用于请求签名，请妥善保管</p>
     */
    private String apiSecret;
    
    /**
     * SDK 版本号
     * <p>默认值：1.0.0</p>
     */
    private String sdkVersion = "1.0.0";

    // ==================== 连接池配置 ====================
    
    /**
     * 最大连接数
     * <p>控制整体连接池大小，建议值：50-100<br>
     * 默认值：50</p>
     */
    private int maxConnections = 50;
    
    /**
     * 每个主机的最大连接数
     * <p>控制单个主机的并发连接数，建议值：10-20<br>
     * 默认值：10</p>
     */
    private int maxConnectionsPerHost = 10;
    
    /**
     * 连接保活超时时间
     * <p>空闲连接在连接池中的保持时间<br>
     * 默认值：30秒</p>
     */
    private Duration keepAliveTimeout = Duration.ofSeconds(30);

    // ==================== 超时配置 ====================
    
    /**
     * 请求总超时时间
     * <p>包含连接建立、请求发送、响应接收的总时间<br>
     * 建议值：30-60秒，默认值：60秒</p>
     */
    private Duration requestTimeout = Duration.ofSeconds(60);
    
    /**
     * 连接建立超时时间
     * <p>TCP 连接建立的最大等待时间<br>
     * 默认值：30秒</p>
     */
    private Duration connectionTimeout = Duration.ofSeconds(30);
    
    /**
     * 读取响应超时时间
     * <p>从服务器读取响应数据的最大等待时间<br>
     * 默认值：60秒</p>
     */
    private Duration readTimeout = Duration.ofSeconds(60);

    // ==================== 重试配置 ====================
    
    /**
     * 最大重试次数
     * <p>发生网络错误或 5xx 服务器错误时的重试次数<br>
     * 建议值：2-3次，默认值：3</p>
     */
    private int retryMaxAttempts = 3;
    
    /**
     * 重试基础延迟时间
     * <p>第一次重试前的等待时间<br>
     * 默认值：1秒</p>
     */
    private Duration retryBaseDelay = Duration.ofSeconds(1);
    
    /**
     * 重试最大延迟时间
     * <p>重试等待时间的上限（使用指数退避策略）<br>
     * 默认值：30秒</p>
     */
    private Duration retryMaxDelay = Duration.ofSeconds(30);

    // ==================== 日志配置 ====================
    
    /**
     * 是否启用日志记录
     * <p>开启后会记录请求和响应的详细信息<br>
     * 默认值：true</p>
     */
    private boolean enableLogging = true;
    
    /**
     * 是否记录请求体
     * <p>开启后会在日志中输出完整的请求体（注意：可能包含敏感信息）<br>
     * 默认值：false</p>
     */
    private boolean logRequestBody = false;
    
    /**
     * 是否记录响应体
     * <p>开启后会在日志中输出完整的响应体<br>
     * 默认值：false</p>
     */
    private boolean logResponseBody = false;

    // ==================== 构造函数 ====================
    
    /**
     * 默认构造函数
     */
    public HttpClientConfig() {
    }

    /**
     * 创建配置构建器
     * 
     * @return ConfigBuilder实例
     */
    public static ConfigBuilder builder() {
        return new ConfigBuilder();
    }

    /**
     * 通过 Builder 创建配置（包级可见）
     * 
     * @param builder 配置构建器
     */
    HttpClientConfig(ConfigBuilder builder) {
        this.baseUrl = builder.baseUrl;
        this.apiKey = builder.apiKey;
        this.apiSecret = builder.apiSecret;
        this.sdkVersion = builder.sdkVersion;
        this.maxConnections = builder.maxConnections;
        this.maxConnectionsPerHost = builder.maxConnectionsPerHost;
        this.keepAliveTimeout = builder.keepAliveTimeout;
        this.requestTimeout = builder.requestTimeout;
        this.connectionTimeout = builder.connectionTimeout;
        this.readTimeout = builder.readTimeout;
        this.retryMaxAttempts = builder.retryMaxAttempts;
        this.retryBaseDelay = builder.retryBaseDelay;
        this.retryMaxDelay = builder.retryMaxDelay;
        this.enableLogging = builder.enableLogging;
        this.logRequestBody = builder.logRequestBody;
        this.logResponseBody = builder.logResponseBody;
    }

    // ==================== 验证方法 ====================
    
    /**
     * 验证配置有效性
     * <p>检查必填字段和配置值的合理性</p>
     * 
     * @throws ConfigValidationException 如果配置验证失败
     */
    public void validate() throws ConfigValidationException {
        // 验证基础配置
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new ConfigValidationException("BaseURL", "API 基础地址不能为空");
        }
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new ConfigValidationException("APIKey", "应用密钥（app_key）不能为空");
        }
        if (apiSecret == null || apiSecret.trim().isEmpty()) {
            throw new ConfigValidationException("APISecret", "应用密钥（app_secret）不能为空");
        }
        
        // 验证连接池配置
        if (maxConnections <= 0) {
            throw new ConfigValidationException("MaxConnections", "最大连接数必须大于 0");
        }
        
        // 验证超时配置
        if (requestTimeout == null || requestTimeout.isZero() || requestTimeout.isNegative()) {
            throw new ConfigValidationException("RequestTimeout", "请求超时时间必须大于 0");
        }
        
        // 验证重试配置
        if (retryMaxAttempts < 0) {
            throw new ConfigValidationException("RetryMaxAttempts", "最大重试次数不能为负数");
        }
    }

    // ==================== Getter 方法 ====================
    
    public String getBaseUrl() { return baseUrl; }
    public String getApiKey() { return apiKey; }
    public String getApiSecret() { return apiSecret; }
    public String getSdkVersion() { return sdkVersion; }
    public int getMaxConnections() { return maxConnections; }
    public int getMaxConnectionsPerHost() { return maxConnectionsPerHost; }
    public Duration getKeepAliveTimeout() { return keepAliveTimeout; }
    public Duration getRequestTimeout() { return requestTimeout; }
    public Duration getConnectionTimeout() { return connectionTimeout; }
    public Duration getReadTimeout() { return readTimeout; }
    public int getRetryMaxAttempts() { return retryMaxAttempts; }
    public Duration getRetryBaseDelay() { return retryBaseDelay; }
    public Duration getRetryMaxDelay() { return retryMaxDelay; }
    public boolean isEnableLogging() { return enableLogging; }
    public boolean isLogRequestBody() { return logRequestBody; }
    public boolean isLogResponseBody() { return logResponseBody; }

    // ==================== Setter 方法 ====================
    
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }
    public void setApiSecret(String apiSecret) { this.apiSecret = apiSecret; }
    public void setSdkVersion(String sdkVersion) { this.sdkVersion = sdkVersion; }
    public void setMaxConnections(int maxConnections) { this.maxConnections = maxConnections; }
    public void setMaxConnectionsPerHost(int maxConnectionsPerHost) { this.maxConnectionsPerHost = maxConnectionsPerHost; }
    public void setKeepAliveTimeout(Duration keepAliveTimeout) { this.keepAliveTimeout = keepAliveTimeout; }
    public void setRequestTimeout(Duration requestTimeout) { this.requestTimeout = requestTimeout; }
    public void setConnectionTimeout(Duration connectionTimeout) { this.connectionTimeout = connectionTimeout; }
    public void setReadTimeout(Duration readTimeout) { this.readTimeout = readTimeout; }
    public void setRetryMaxAttempts(int retryMaxAttempts) { this.retryMaxAttempts = retryMaxAttempts; }
    public void setRetryBaseDelay(Duration retryBaseDelay) { this.retryBaseDelay = retryBaseDelay; }
    public void setRetryMaxDelay(Duration retryMaxDelay) { this.retryMaxDelay = retryMaxDelay; }
    public void setEnableLogging(boolean enableLogging) { this.enableLogging = enableLogging; }
    public void setLogRequestBody(boolean logRequestBody) { this.logRequestBody = logRequestBody; }
    public void setLogResponseBody(boolean logResponseBody) { this.logResponseBody = logResponseBody; }

    // ==================== Object 方法 ====================
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpClientConfig that = (HttpClientConfig) o;
        return maxConnections == that.maxConnections &&
               maxConnectionsPerHost == that.maxConnectionsPerHost &&
               retryMaxAttempts == that.retryMaxAttempts &&
               enableLogging == that.enableLogging &&
               logRequestBody == that.logRequestBody &&
               logResponseBody == that.logResponseBody &&
               Objects.equals(baseUrl, that.baseUrl) &&
               Objects.equals(apiKey, that.apiKey) &&
               Objects.equals(apiSecret, that.apiSecret) &&
               Objects.equals(sdkVersion, that.sdkVersion) &&
               Objects.equals(keepAliveTimeout, that.keepAliveTimeout) &&
               Objects.equals(requestTimeout, that.requestTimeout) &&
               Objects.equals(connectionTimeout, that.connectionTimeout) &&
               Objects.equals(readTimeout, that.readTimeout) &&
               Objects.equals(retryBaseDelay, that.retryBaseDelay) &&
               Objects.equals(retryMaxDelay, that.retryMaxDelay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseUrl, apiKey, apiSecret, sdkVersion, maxConnections, 
                          maxConnectionsPerHost, keepAliveTimeout, requestTimeout, 
                          connectionTimeout, readTimeout, retryMaxAttempts, 
                          retryBaseDelay, retryMaxDelay, enableLogging, 
                          logRequestBody, logResponseBody);
    }

    @Override
    public String toString() {
        return "HttpClientConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                ", apiKey='" + (apiKey != null ? apiKey.substring(0, Math.min(apiKey.length(), 8)) + "***" : "null") + '\'' +
                ", apiSecret='***'" +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", maxConnections=" + maxConnections +
                ", maxConnectionsPerHost=" + maxConnectionsPerHost +
                ", keepAliveTimeout=" + keepAliveTimeout +
                ", requestTimeout=" + requestTimeout +
                ", connectionTimeout=" + connectionTimeout +
                ", readTimeout=" + readTimeout +
                ", retryMaxAttempts=" + retryMaxAttempts +
                ", retryBaseDelay=" + retryBaseDelay +
                ", retryMaxDelay=" + retryMaxDelay +
                ", enableLogging=" + enableLogging +
                ", logRequestBody=" + logRequestBody +
                ", logResponseBody=" + logResponseBody +
                '}';
    }

    // ==================== 静态工厂方法 ====================
    
    /**
     * 创建默认配置
     * <p>返回一个包含合理默认值的配置对象，适用于大多数场景<br>
     * 使用前需要设置 APIKey 和 APISecret</p>
     * 
     * @return 默认配置实例
     */
    public static HttpClientConfig createDefault() {
        return new HttpClientConfig();
    }
    
    /**
     * 创建新的配置构建器
     * <p>初始化时使用默认配置，然后通过链式调用修改需要的配置项</p>
     * 
     * @return 配置构建器实例
     */
    public static ConfigBuilder newBuilder() {
        return new ConfigBuilder();
    }
    
    /**
     * 配置构建器
     * <p>使用 Builder 模式创建 {@link HttpClientConfig} 实例</p>
     */
    public static class ConfigBuilder {
        
        private String baseUrl;
        private String apiKey;
        private String apiSecret;
        private String sdkVersion = "1.0.0";
        private int maxConnections = 50;
        private int maxConnectionsPerHost = 10;
        private Duration keepAliveTimeout = Duration.ofSeconds(30);
        private Duration requestTimeout = Duration.ofSeconds(60);
        private Duration connectionTimeout = Duration.ofSeconds(30);
        private Duration readTimeout = Duration.ofSeconds(60);
        private int retryMaxAttempts = 3;
        private Duration retryBaseDelay = Duration.ofSeconds(1);
        private Duration retryMaxDelay = Duration.ofSeconds(30);
        private boolean enableLogging = true;
        private boolean logRequestBody = false;
        private boolean logResponseBody = false;
        
        /**
         * 构造函数
         */
        public ConfigBuilder() {
        }
        
        /**
         * 设置 API 基础地址
         * 
         * @param baseUrl API 基础地址
         * @return 当前构建器实例
         */
        public ConfigBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
        
        /**
         * 设置应用密钥（app_key）
         * 
         * @param apiKey 应用密钥
         * @return 当前构建器实例
         */
        public ConfigBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }
        
        /**
         * 设置应用密钥（app_secret）
         * 
         * @param apiSecret 应用密钥
         * @return 当前构建器实例
         */
        public ConfigBuilder apiSecret(String apiSecret) {
            this.apiSecret = apiSecret;
            return this;
        }
        
        /**
         * 设置 SDK 版本号
         * 
         * @param sdkVersion SDK 版本号
         * @return 当前构建器实例
         */
        public ConfigBuilder sdkVersion(String sdkVersion) {
            this.sdkVersion = sdkVersion;
            return this;
        }
        
        /**
         * 设置最大连接数
         * 
         * @param maxConnections 最大连接数
         * @return 当前构建器实例
         */
        public ConfigBuilder maxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
            return this;
        }
        
        /**
         * 设置每个主机的最大连接数
         * 
         * @param maxConnectionsPerHost 每个主机的最大连接数
         * @return 当前构建器实例
         */
        public ConfigBuilder maxConnectionsPerHost(int maxConnectionsPerHost) {
            this.maxConnectionsPerHost = maxConnectionsPerHost;
            return this;
        }
        
        /**
         * 设置连接保活超时时间
         * 
         * @param keepAliveTimeout 连接保活超时时间
         * @return 当前构建器实例
         */
        public ConfigBuilder keepAliveTimeout(Duration keepAliveTimeout) {
            this.keepAliveTimeout = keepAliveTimeout;
            return this;
        }
        
        /**
         * 设置请求总超时时间
         * 
         * @param requestTimeout 请求总超时时间
         * @return 当前构建器实例
         */
        public ConfigBuilder requestTimeout(Duration requestTimeout) {
            this.requestTimeout = requestTimeout;
            return this;
        }
        
        /**
         * 设置连接建立超时时间
         * 
         * @param connectionTimeout 连接建立超时时间
         * @return 当前构建器实例
         */
        public ConfigBuilder connectionTimeout(Duration connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }
        
        /**
         * 设置读取响应超时时间
         * 
         * @param readTimeout 读取响应超时时间
         * @return 当前构建器实例
         */
        public ConfigBuilder readTimeout(Duration readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }
        
        /**
         * 设置最大重试次数
         * 
         * @param retryMaxAttempts 最大重试次数
         * @return 当前构建器实例
         */
        public ConfigBuilder retryMaxAttempts(int retryMaxAttempts) {
            this.retryMaxAttempts = retryMaxAttempts;
            return this;
        }
        
        /**
         * 设置重试基础延迟时间
         * 
         * @param retryBaseDelay 重试基础延迟时间
         * @return 当前构建器实例
         */
        public ConfigBuilder retryBaseDelay(Duration retryBaseDelay) {
            this.retryBaseDelay = retryBaseDelay;
            return this;
        }
        
        /**
         * 设置重试最大延迟时间
         * 
         * @param retryMaxDelay 重试最大延迟时间
         * @return 当前构建器实例
         */
        public ConfigBuilder retryMaxDelay(Duration retryMaxDelay) {
            this.retryMaxDelay = retryMaxDelay;
            return this;
        }
        
        /**
         * 设置是否启用日志记录
         * 
         * @param enableLogging 是否启用日志记录
         * @return 当前构建器实例
         */
        public ConfigBuilder enableLogging(boolean enableLogging) {
            this.enableLogging = enableLogging;
            return this;
        }
        
        /**
         * 设置是否记录请求体
         * 
         * @param logRequestBody 是否记录请求体
         * @return 当前构建器实例
         */
        public ConfigBuilder logRequestBody(boolean logRequestBody) {
            this.logRequestBody = logRequestBody;
            return this;
        }
        
        /**
         * 设置是否记录响应体
         * 
         * @param logResponseBody 是否记录响应体
         * @return 当前构建器实例
         */
        public ConfigBuilder logResponseBody(boolean logResponseBody) {
            this.logResponseBody = logResponseBody;
            return this;
        }
        
        /**
         * 构建配置对象
         * 
         * @return 配置对象
         * @throws ConfigValidationException 如果配置验证失败
         */
        public HttpClientConfig build() throws ConfigValidationException {
            HttpClientConfig config = new HttpClientConfig(this);
            config.validate();
            return config;
        }
    }
}