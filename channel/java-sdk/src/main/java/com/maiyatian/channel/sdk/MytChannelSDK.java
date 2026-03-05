/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk;

import com.maiyatian.channel.sdk.client.ConfigBuilder;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.models.sender.api.ChannelSenderV2;
import com.maiyatian.channel.sdk.models.sender.api.IChannelSenderV2;

/**
 * 麦芽田渠道开放平台 Java SDK 主入口类
 * <p>
 * 对应 Go SDK 中的 NewChannelSender 函数，提供 SDK 实例的创建和配置
 * 采用工厂模式和Builder模式，提供类型安全和便捷的SDK使用体验
 * </p>
 * 
 * <h3>使用示例：</h3>
 * <pre>{@code
 * // 基础使用
 * IChannelSenderV2 sender = MytChannelSDK.newBuilder()
 *     .baseUrl("https://open-api-test.maiyatian.com")
 *     .apiKey("your_app_key")
 *     .apiSecret("your_app_secret")
 *     .build();
 * 
 * // 高级配置
 * IChannelSenderV2 sender = MytChannelSDK.newBuilder()
 *     .baseUrl("https://open-api.maiyatian.com")
 *     .apiKey("your_app_key")
 *     .apiSecret("your_app_secret")
 *     .maxConnections(100)
 *     .requestTimeout(Duration.ofSeconds(30))
 *     .retryMaxAttempts(5)
 *     .enableLogging(true)
 *     .build();
 * 
 * // 使用自定义配置
 * HttpClientConfig config = new ConfigBuilder()
 *     .baseUrl("https://open-api.maiyatian.com")
 *     .apiKey("your_app_key")
 *     .apiSecret("your_app_secret")
 *     .build();
 * IChannelSenderV2 sender = MytChannelSDK.create(config);
 * }</pre>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public final class MytChannelSDK {

    /**
     * SDK 版本号
     */
    public static final String VERSION = "2.0.0";

    // 私有构造函数，防止实例化
    private MytChannelSDK() {
        throw new UnsupportedOperationException("这是一个工具类，不应该被实例化");
    }

    /**
     * 创建新的 SDK 构建器
     * <p>
     * 对应 Go SDK 中的 client.NewConfigBuilder() 函数
     * </p>
     * 
     * @return SDK 构建器实例
     */
    public static SDKBuilder newBuilder() {
        return new SDKBuilder();
    }

    /**
     * 使用默认配置创建渠道发送客户端
     * <p>
     * 注意：必须在创建后设置必要的配置项（baseUrl、apiKey、apiSecret）
     * </p>
     * 
     * @return 渠道发送客户端实例
     * @throws RuntimeException 如果配置验证失败
     */
    public static IChannelSenderV2 createDefault() {
        HttpClientConfig defaultConfig = new ConfigBuilder().build();
        return new ChannelSenderV2(defaultConfig);
    }

    /**
     * 使用自定义配置创建渠道发送客户端
     * <p>
     * 对应 Go SDK 中的 NewChannelSender(config) 函数
     * </p>
     * 
     * @param config HTTP 客户端配置
     * @return 渠道发送客户端实例
     * @throws RuntimeException 如果配置验证失败
     */
    public static IChannelSenderV2 create(HttpClientConfig config) {
        return new ChannelSenderV2(config);
    }

    /**
     * SDK 构建器类
     * <p>
     * 提供链式调用的配置构建方式，对应 Go SDK 中的 ConfigBuilder
     * </p>
     */
    public static class SDKBuilder {
        private final ConfigBuilder configBuilder;

        private SDKBuilder() {
            this.configBuilder = new ConfigBuilder();
        }

        /**
         * 设置 API 基础地址
         * 
         * @param baseUrl API 基础地址，如 https://open-api.maiyatian.com
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder baseUrl(String baseUrl) {
            configBuilder.baseUrl(baseUrl);
            return this;
        }

        /**
         * 设置应用密钥（app_key）
         * 
         * @param apiKey 由麦芽田开放平台分配的应用标识
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder apiKey(String apiKey) {
            configBuilder.apiKey(apiKey);
            return this;
        }

        /**
         * 设置应用密钥（app_secret）
         * 
         * @param apiSecret 由麦芽田开放平台分配的应用密钥，用于签名
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder apiSecret(String apiSecret) {
            configBuilder.apiSecret(apiSecret);
            return this;
        }

        /**
         * 设置最大连接数
         * 
         * @param maxConnections 连接池最大连接数，建议值 50-100
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder maxConnections(int maxConnections) {
            configBuilder.maxConnections(maxConnections);
            return this;
        }

        /**
         * 设置请求超时时间
         * 
         * @param requestTimeout 请求总超时时间
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder requestTimeout(java.time.Duration requestTimeout) {
            configBuilder.requestTimeout(requestTimeout);
            return this;
        }

        /**
         * 设置最大重试次数
         * 
         * @param retryMaxAttempts 网络错误或 5xx 错误时的重试次数，建议值 2-3 次
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder retryMaxAttempts(int retryMaxAttempts) {
            configBuilder.retryMaxAttempts(retryMaxAttempts);
            return this;
        }

        /**
         * 设置是否启用日志记录
         * 
         * @param enableLogging true 启用日志，false 禁用日志
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder enableLogging(boolean enableLogging) {
            configBuilder.enableLogging(enableLogging);
            return this;
        }

        /**
         * 设置是否记录请求体
         * 
         * @param logRequestBody true 记录请求体，false 不记录
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder logRequestBody(boolean logRequestBody) {
            configBuilder.logRequestBody(logRequestBody);
            return this;
        }

        /**
         * 设置是否记录响应体
         * 
         * @param logResponseBody true 记录响应体，false 不记录
         * @return 构建器实例，支持链式调用
         */
        public SDKBuilder logResponseBody(boolean logResponseBody) {
            configBuilder.logResponseBody(logResponseBody);
            return this;
        }

        /**
         * 构建渠道发送客户端实例
         * 
         * @return 渠道发送客户端实例
         * @throws RuntimeException 如果必填字段缺失或配置验证失败
         */
        public IChannelSenderV2 build() {
            HttpClientConfig config = configBuilder.build();
            return new ChannelSenderV2(config);
        }

        /**
         * 构建配置对象（不创建客户端实例）
         * 
         * @return HTTP 客户端配置对象
         * @throws RuntimeException 如果必填字段缺失或配置验证失败
         */
        public HttpClientConfig buildConfig() {
            return configBuilder.build();
        }
    }
}