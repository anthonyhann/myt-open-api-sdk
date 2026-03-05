/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.client.ConfigBuilder;
import com.maiyatian.channel.sdk.client.ConfigValidationException;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.client.HttpClientManager;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.*;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoReq;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoResp;
import com.maiyatian.channel.sdk.models.types.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

/**
 * 麦芽田渠道开放平台 Sender API 客户端
 * <p>
 * 用于三方服务主动调用麦芽田平台接口
 * </p>
 * 
 * <h3>主要功能：</h3>
 * <ul>
 * <li>授权管理：获取访问令牌、刷新令牌</li>
 * <li>订单推送：推送新订单、订单状态变更</li>
 * <li>配送管理：推送配送状态、骑手位置</li>
 * <li>退款管理：推送退款申请、退款结果</li>
 * <li>门店管理：查询门店信息和状态</li>
 * </ul>
 * 
 * <h3>特性增强：</h3>
 * <ul>
 * <li>✅ 完整的接口规范实现</li>
 * <li>✅ 增强的错误处理和重试机制</li>
 * <li>✅ 配置合并与验证</li>
 * <li>✅ 详细的日志记录</li>
 * <li>✅ 资源自动管理</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ChannelSender implements IChannelSender {

    private static final Logger logger = LoggerFactory.getLogger(ChannelSender.class);

    private final HttpClientManager httpClient;
    private final ObjectMapper objectMapper;

    /**
     * 构造函数
     * 
     * @param config HTTP 客户端配置
     * @throws ConfigValidationException 如果配置验证失败
     */
    public ChannelSender(HttpClientConfig config) throws ConfigValidationException {
        // 应用配置优化和合并
        HttpClientConfig optimizedConfig = applyConfigOptimizations(config);
        
        this.httpClient = new HttpClientManager(optimizedConfig);
        this.objectMapper = new ObjectMapper();
        
        if (optimizedConfig.isEnableLogging()) {
            logger.info("ChannelSender 客户端初始化完成 - BaseURL: {}, 连接超时: {}ms, 读取超时: {}ms", 
                    optimizedConfig.getBaseUrl(), 
                    optimizedConfig.getConnectionTimeout(), 
                    optimizedConfig.getReadTimeout());
        }
    }

    /**
     * 应用配置优化和合并默认配置
     * 
     * @param userConfig 用户配置
     * @return 优化后的配置
     */
    private HttpClientConfig applyConfigOptimizations(HttpClientConfig userConfig) {
        // 获取默认配置
        HttpClientConfig defaultConfig = getDefaultConfig();
        
        // 合并配置（用户配置优先）
        HttpClientConfig mergedConfig = mergeConfigs(defaultConfig, userConfig);
        
        // 应用性能优化
        return applyPerformanceOptimizations(mergedConfig);
    }

    /**
     * 获取默认配置（不包含必填字段）
     */
    private HttpClientConfig getDefaultConfig() {
        HttpClientConfig defaultConfig = new HttpClientConfig();
        defaultConfig.setBaseUrl("https://open-api.maiyatian.com");
        defaultConfig.setConnectionTimeout(Duration.ofSeconds(30));
        defaultConfig.setReadTimeout(Duration.ofSeconds(60));
        defaultConfig.setRetryMaxAttempts(3);
        defaultConfig.setEnableLogging(false);
        defaultConfig.setSdkVersion("1.0.0");
        defaultConfig.setMaxConnections(50);
        defaultConfig.setMaxConnectionsPerHost(10);
        defaultConfig.setKeepAliveTimeout(Duration.ofSeconds(30));
        defaultConfig.setRequestTimeout(Duration.ofSeconds(60));
        defaultConfig.setRetryBaseDelay(Duration.ofSeconds(1));
        defaultConfig.setRetryMaxDelay(Duration.ofSeconds(30));
        defaultConfig.setLogRequestBody(false);
        defaultConfig.setLogResponseBody(false);
        return defaultConfig;
    }

    /**
     * 合并配置（用户配置优先）
     */
    private HttpClientConfig mergeConfigs(HttpClientConfig defaultConfig, HttpClientConfig userConfig) {
        // 创建新配置，从用户配置开始
        HttpClientConfig merged = new HttpClientConfig();
        
        // 基础URL
        merged.setBaseUrl(userConfig.getBaseUrl() != null ? userConfig.getBaseUrl() : defaultConfig.getBaseUrl());
        
        // API密钥（必须由用户提供，如果用户没有提供则保持null）
        merged.setApiKey(userConfig.getApiKey() != null ? userConfig.getApiKey() : null);
        merged.setApiSecret(userConfig.getApiSecret() != null ? userConfig.getApiSecret() : null);
        
        // 超时配置 - 使用Duration类型
        merged.setConnectionTimeout(userConfig.getConnectionTimeout() != null ? 
                userConfig.getConnectionTimeout() : defaultConfig.getConnectionTimeout());
        merged.setReadTimeout(userConfig.getReadTimeout() != null ? 
                userConfig.getReadTimeout() : defaultConfig.getReadTimeout());
        
        // 重试配置
        merged.setRetryMaxAttempts(userConfig.getRetryMaxAttempts() >= 0 ? 
                userConfig.getRetryMaxAttempts() : defaultConfig.getRetryMaxAttempts());
        
        // 日志配置
        merged.setEnableLogging(userConfig.isEnableLogging());
        
        // 其他配置使用默认值
        merged.setSdkVersion(defaultConfig.getSdkVersion());
        merged.setMaxConnections(defaultConfig.getMaxConnections());
        merged.setMaxConnectionsPerHost(defaultConfig.getMaxConnectionsPerHost());
        merged.setKeepAliveTimeout(defaultConfig.getKeepAliveTimeout());
        merged.setRequestTimeout(defaultConfig.getRequestTimeout());
        merged.setRetryBaseDelay(defaultConfig.getRetryBaseDelay());
        merged.setRetryMaxDelay(defaultConfig.getRetryMaxDelay());
        merged.setLogRequestBody(defaultConfig.isLogRequestBody());
        merged.setLogResponseBody(defaultConfig.isLogResponseBody());
        
        return merged;
    }

    /**
     * 应用性能优化
     */
    private HttpClientConfig applyPerformanceOptimizations(HttpClientConfig config) {
        HttpClientConfig optimized = new HttpClientConfig();
        
        // 复制所有配置
        copyConfigFields(config, optimized);
        
        // 确保最小超时时间
        if (config.getConnectionTimeout().toMillis() < 5000) {
            optimized.setConnectionTimeout(Duration.ofSeconds(5));
            logger.warn("连接超时时间过短，已调整为5秒");
        }
        
        if (config.getReadTimeout().toMillis() < 10000) {
            optimized.setReadTimeout(Duration.ofSeconds(10));
            logger.warn("读取超时时间过短，已调整为10秒");
        }
        
        // 确保合理的重试次数
        if (config.getRetryMaxAttempts() > 5) {
            optimized.setRetryMaxAttempts(5);
            logger.warn("重试次数过多，已调整为5次");
        }
        
        return optimized;
    }

    /**
     * 复制配置字段
     */
    private void copyConfigFields(HttpClientConfig source, HttpClientConfig target) {
        target.setBaseUrl(source.getBaseUrl());
        target.setApiKey(source.getApiKey());
        target.setApiSecret(source.getApiSecret());
        target.setSdkVersion(source.getSdkVersion());
        target.setMaxConnections(source.getMaxConnections());
        target.setMaxConnectionsPerHost(source.getMaxConnectionsPerHost());
        target.setKeepAliveTimeout(source.getKeepAliveTimeout());
        target.setRequestTimeout(source.getRequestTimeout());
        target.setConnectionTimeout(source.getConnectionTimeout());
        target.setReadTimeout(source.getReadTimeout());
        target.setRetryMaxAttempts(source.getRetryMaxAttempts());
        target.setRetryBaseDelay(source.getRetryBaseDelay());
        target.setRetryMaxDelay(source.getRetryMaxDelay());
        target.setEnableLogging(source.isEnableLogging());
        target.setLogRequestBody(source.isLogRequestBody());
        target.setLogResponseBody(source.isLogResponseBody());
    }

    // ==================== 授权管理接口 ====================

    /**
     * 获取访问令牌
     * <p>
     * 使用授权码向麦芽田换取访问令牌
     * </p>
     * 
     * @param token 公共参数中的 token（传空字符串）
     * @param request 获取访问令牌请求参数
     * @param headers 额外的请求头（可选）
     * @return 访问令牌响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public AccessTokenResp accessToken(String token, AccessTokenReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        // 执行 HTTP 请求
        Response response = httpClient.post("v1/channel/access_token", request, token, headers);
        
        // 解析响应数据
        if (response.getData() != null && !response.getData().trim().isEmpty()) {
            return objectMapper.readValue(response.getData(), AccessTokenResp.class);
        }
        
        throw new RuntimeException("响应数据为空");
    }

    /**
     * 获取访问令牌（简化版本）
     * 
     * @param token 公共参数中的 token（传空字符串）
     * @param request 获取访问令牌请求参数
     * @return 访问令牌响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public AccessTokenResp accessToken(String token, AccessTokenReq request) 
            throws IOException, JsonProcessingException {
        return accessToken(token, request, null);
    }

    // ==================== 订单管理 API ====================

    /**
     * 新订单推送接口【必接】
     * <p>
     * 三方服务在用户下单并付款成功后，主动推送新订单给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 新订单完整信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderCreated(String token, CreateOrderReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_created", request, token, headers);
    }

    /**
     * 新订单推送接口（简化版本）
     */
    public Response orderCreated(String token, CreateOrderReq request) 
            throws IOException, JsonProcessingException {
        return orderCreated(token, request, null);
    }

    /**
     * 订单修改推送接口
     * <p>
     * 三方服务在用户修改订单后，主动推送订单变更信息给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 订单修改信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderModified(String token, UpdateOrderReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_modified", request, token, headers);
    }

    /**
     * 订单修改推送接口（简化版本）
     */
    public Response orderModified(String token, UpdateOrderReq request) 
            throws IOException, JsonProcessingException {
        return orderModified(token, request, null);
    }

    /**
     * 订单确认推送接口【必接】
     * <p>
     * 三方服务在商户确认订单后，主动推送确认状态给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 订单确认信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderConfirmed(String token, OrderConfirmedReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_confirmed", request, token, headers);
    }

    /**
     * 订单确认推送接口（简化版本）
     */
    public Response orderConfirmed(String token, OrderConfirmedReq request) 
            throws IOException, JsonProcessingException {
        return orderConfirmed(token, request, null);
    }

    /**
     * 订单催单推送接口
     * <p>
     * 三方服务在用户催单后，主动推送催单信息给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 催单信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderRemind(String token, OrderRemindReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_remind", request, token, headers);
    }

    /**
     * 订单催单推送接口（简化版本）
     */
    public Response orderRemind(String token, OrderRemindReq request) 
            throws IOException, JsonProcessingException {
        return orderRemind(token, request, null);
    }

    /**
     * 订单申请退款推送接口
     * <p>
     * 三方服务在顾客或商家发起退款申请后，主动推送退款申请给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 退款申请信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderApplyRefund(String token, OrderApplyRefundReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_apply_refund", request, token, headers);
    }

    /**
     * 订单申请退款推送接口（简化版本）
     */
    public Response orderApplyRefund(String token, OrderApplyRefundReq request) 
            throws IOException, JsonProcessingException {
        return orderApplyRefund(token, request, null);
    }

    /**
     * 订单退款结果推送接口
     * <p>
     * 三方服务在处理退款申请后，主动推送退款结果给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 退款结果信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderRefunded(String token, OrderRefundedReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_refunded", request, token, headers);
    }

    /**
     * 订单退款结果推送接口（简化版本）
     */
    public Response orderRefunded(String token, OrderRefundedReq request) 
            throws IOException, JsonProcessingException {
        return orderRefunded(token, request, null);
    }

    /**
     * 订单取消推送接口
     * <p>
     * 三方服务在订单被取消后，主动推送取消状态给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 订单取消信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderCanceled(String token, OrderCanceledReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_canceled", request, token, headers);
    }

    /**
     * 订单取消推送接口（简化版本）
     */
    public Response orderCanceled(String token, OrderCanceledReq request) 
            throws IOException, JsonProcessingException {
        return orderCanceled(token, request, null);
    }

    /**
     * 订单完成推送接口【必接】
     * <p>
     * 三方服务在订单配送完成后，主动推送完成状态给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 订单完成信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response orderDone(String token, OrderDoneReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/order_done", request, token, headers);
    }

    /**
     * 订单完成推送接口（简化版本）
     */
    public Response orderDone(String token, OrderDoneReq request) 
            throws IOException, JsonProcessingException {
        return orderDone(token, request, null);
    }

    // ==================== 门店管理 API ====================

    /**
     * 查询麦芽田门店信息接口
     * <p>
     * 三方服务主动查询门店在麦芽田平台的基本信息和营业状态
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 查询门店信息请求
     * @param headers 自定义请求头
     * @return 门店信息响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public ShopInfoResp shopInfo(String token, ShopInfoReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        Response response = httpClient.post("v1/channel/shop_info", request, token, headers);
        
        if (response.getData() != null && !response.getData().trim().isEmpty()) {
            return objectMapper.readValue(response.getData(), ShopInfoResp.class);
        }
        
        throw new RuntimeException("响应数据为空");
    }

    /**
     * 查询麦芽田门店信息接口（简化版本）
     */
    public ShopInfoResp shopInfo(String token, ShopInfoReq request) 
            throws IOException, JsonProcessingException {
        return shopInfo(token, request, null);
    }

    // ==================== 自配送管理 API ====================

    /**
     * 自配送状态变更推送接口
     * <p>
     * 三方服务在使用自配送时，主动推送配送状态变更给麦芽田
     * </p>
     * 
     * @param token 商户授权令牌
     * @param request 自配送状态变更信息
     * @param headers 自定义请求头
     * @return 响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public Response selfDeliveryChange(String token, SelfDeliveryChangeReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        return httpClient.post("v1/channel/self_delivery_change", request, token, headers);
    }

    /**
     * 自配送状态变更推送接口（简化版本）
     */
    public Response selfDeliveryChange(String token, SelfDeliveryChangeReq request) 
            throws IOException, JsonProcessingException {
        return selfDeliveryChange(token, request, null);
    }

    // ==================== 授权管理 API ====================

    /**
     * 刷新访问令牌接口
     * <p>
     * 三方服务使用刷新令牌向麦芽田重新获取新的访问令牌
     * </p>
     * 
     * @param token 当前访问令牌
     * @param request 刷新令牌请求参数
     * @param headers 自定义请求头
     * @return 新的访问令牌响应数据
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 序列化失败时
     */
    public RefreshTokenResp refreshToken(String token, RefreshTokenReq request, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        if (request == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        Response response = httpClient.post("v1/channel/refresh_token", request, token, headers);
        
        if (response.getData() != null && !response.getData().trim().isEmpty()) {
            return objectMapper.readValue(response.getData(), RefreshTokenResp.class);
        }
        
        throw new RuntimeException("响应数据为空");
    }

    /**
     * 刷新访问令牌接口（简化版本）
     */
    public RefreshTokenResp refreshToken(String token, RefreshTokenReq request) 
            throws IOException, JsonProcessingException {
        return refreshToken(token, request, null);
    }

    // ==================== 工具方法 ====================

    /**
     * 获取 HTTP 客户端管理器
     * 
     * @return HTTP 客户端管理器
     */
    public HttpClientManager getHttpClient() {
        return httpClient;
    }

    /**
     * 获取 JSON 序列化器
     * 
     * @return ObjectMapper 实例
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 执行原始 HTTP 请求
     * 
     * @param method HTTP 方法
     * @param path API 路径
     * @param data 请求数据
     * @param token 授权令牌
     * @param headers 请求头
     * @return API 响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON 处理失败时
     */
    public Response request(String method, String path, Object data, String token, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return httpClient.request(method, path, data, token, headers);
    }

    @Override
    public void close() {
        if (httpClient != null) {
            httpClient.close();
        }
        logger.info("ChannelSender 客户端已关闭");
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建新的 ChannelSender 实例
     * 
     * @param config HTTP 客户端配置
     * @return ChannelSender 实例
     * @throws ConfigValidationException 如果配置验证失败
     */
    public static ChannelSender create(HttpClientConfig config) throws ConfigValidationException {
        return new ChannelSender(config);
    }

    /**
     * 创建新的 ChannelSender 实例（使用 Builder 配置）
     * 
     * @param configBuilder 配置构建器
     * @return ChannelSender 实例
     * @throws ConfigValidationException 如果配置验证失败
     */
    public static ChannelSender create(ConfigBuilder configBuilder) throws ConfigValidationException {
        return new ChannelSender(configBuilder.build());
    }
}