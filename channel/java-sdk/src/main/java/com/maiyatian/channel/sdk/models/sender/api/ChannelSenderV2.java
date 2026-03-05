/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.channel.sdk.client.ApiClientUtils;
import com.maiyatian.channel.sdk.client.ConfigBuilder;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.client.HttpClientManager;
import com.maiyatian.channel.sdk.consts.Constants;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.*;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoReq;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoResp;
import com.maiyatian.channel.sdk.models.types.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * 渠道发送接口实现 V2 - 泛型版本
 * <p>
 * 封装 HTTP 客户端，提供三方服务主动调用麦芽田 API 的便捷方法
 * 对应 Go SDK 中的 channelSender 实现，使用泛型提供类型安全的 API 调用
 * </p>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public class ChannelSenderV2 implements IChannelSenderV2 {
    
    private static final Logger logger = LoggerFactory.getLogger(ChannelSenderV2.class);
    
    private final HttpClientManager httpClient;

    /**
     * 创建渠道发送接口实例
     * 
     * @param config HTTP 客户端配置，包含 API 地址、密钥、超时、重试等配置
     * @throws RuntimeException 如果配置验证失败
     */
    public ChannelSenderV2(HttpClientConfig config) {
        try {
            // 获取默认配置
            HttpClientConfig defaultConfig = new ConfigBuilder().build();
            
            // 合并用户配置（用户配置优先）
            HttpClientConfig mergedConfig = mergeConfig(defaultConfig, config);
            
            // 创建 HTTP 客户端管理器
            this.httpClient = new HttpClientManager(mergedConfig);
            
            if (mergedConfig.isEnableLogging()) {
                logger.info("渠道发送客户端V2初始化完成，基础地址: {}", mergedConfig.getBaseUrl());
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化渠道发送客户端失败", e);
        }
    }

    // ==================== 订单推送接口实现 ====================

    @Override
    public ApiResponse<Void> orderCreated(String token, CreateOrderReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_CREATED, data, token, headers);
    }

    @Override
    public ApiResponse<Void> orderModified(String token, UpdateOrderReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_MODIFIED, data, token, headers);
    }

    @Override
    public ApiResponse<Void> orderConfirmed(String token, OrderConfirmedReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_CONFIRMED, data, token, headers);
    }

    @Override
    public ApiResponse<Void> orderRemind(String token, OrderRemindReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_REMIND, data, token, headers);
    }

    @Override
    public ApiResponse<Void> orderCanceled(String token, OrderCanceledReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_CANCELED, data, token, headers);
    }

    @Override
    public ApiResponse<Void> orderDone(String token, OrderDoneReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_DONE, data, token, headers);
    }

    // ==================== 退款推送接口实现 ====================

    @Override
    public ApiResponse<Void> orderApplyRefund(String token, OrderApplyRefundReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_APPLY_REFUND, data, token, headers);
    }

    @Override
    public ApiResponse<Void> orderRefunded(String token, OrderRefundedReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.ORDER_REFUNDED, data, token, headers);
    }

    // ==================== 配送推送接口实现 ====================

    @Override
    public ApiResponse<Void> selfDeliveryChange(String token, SelfDeliveryChangeReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.postEmpty(httpClient, Constants.ApiPath.SELF_DELIVERY_CHANGE, data, token, headers);
    }

    // ==================== 授权管理接口实现 ====================

    @Override
    public ApiResponse<AccessTokenResp> accessToken(String token, AccessTokenReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.post(httpClient, Constants.ApiPath.ACCESS_TOKEN, data, token, headers, AccessTokenResp.class);
    }

    @Override
    public ApiResponse<RefreshTokenResp> refreshToken(String token, RefreshTokenReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.post(httpClient, Constants.ApiPath.REFRESH_TOKEN, data, token, headers, RefreshTokenResp.class);
    }

    // ==================== 门店查询接口实现 ====================

    @Override
    public ApiResponse<ShopInfoResp> shopInfo(String token, ShopInfoReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return ApiClientUtils.post(httpClient, Constants.ApiPath.SHOP_INFO, data, token, headers, ShopInfoResp.class);
    }

    // ==================== 生命周期管理 ====================

    @Override
    public void close() throws Exception {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    // ==================== 私有方法 ====================

    /**
     * 合并用户配置到默认配置
     * 对应 Go SDK 中的 mergeConfig 函数
     * 
     * @param defaultConfig 默认配置
     * @param userConfig 用户配置
     * @return 合并后的配置
     */
    private HttpClientConfig mergeConfig(HttpClientConfig defaultConfig, HttpClientConfig userConfig) {
        if (userConfig == null) {
            return defaultConfig;
        }

        // 创建 ConfigBuilder 来构建合并后的配置
        ConfigBuilder builder = new ConfigBuilder();

        // 基础配置
        String baseUrl = userConfig.getBaseUrl() != null ? userConfig.getBaseUrl() : defaultConfig.getBaseUrl();
        String apiKey = userConfig.getApiKey() != null ? userConfig.getApiKey() : defaultConfig.getApiKey();
        String apiSecret = userConfig.getApiSecret() != null ? userConfig.getApiSecret() : defaultConfig.getApiSecret();

        builder.baseUrl(baseUrl)
               .apiKey(apiKey)
               .apiSecret(apiSecret);

        // 连接池配置
        if (userConfig.getMaxConnections() > 0) {
            builder.maxConnections(userConfig.getMaxConnections());
        } else {
            builder.maxConnections(defaultConfig.getMaxConnections());
        }

        // 超时配置
        if (userConfig.getRequestTimeout() != null) {
            builder.requestTimeout(userConfig.getRequestTimeout());
        } else {
            builder.requestTimeout(defaultConfig.getRequestTimeout());
        }

        if (userConfig.getConnectionTimeout() != null) {
            builder.connectionTimeout(userConfig.getConnectionTimeout());
        } else {
            builder.connectionTimeout(defaultConfig.getConnectionTimeout());
        }

        if (userConfig.getReadTimeout() != null) {
            builder.readTimeout(userConfig.getReadTimeout());
        } else {
            builder.readTimeout(defaultConfig.getReadTimeout());
        }

        // 重试配置
        if (userConfig.getRetryMaxAttempts() > 0) {
            builder.retryMaxAttempts(userConfig.getRetryMaxAttempts());
        } else {
            builder.retryMaxAttempts(defaultConfig.getRetryMaxAttempts());
        }

        if (userConfig.getRetryBaseDelay() != null) {
            builder.retryBaseDelay(userConfig.getRetryBaseDelay());
        } else {
            builder.retryBaseDelay(defaultConfig.getRetryBaseDelay());
        }

        if (userConfig.getRetryMaxDelay() != null) {
            builder.retryMaxDelay(userConfig.getRetryMaxDelay());
        } else {
            builder.retryMaxDelay(defaultConfig.getRetryMaxDelay());
        }

        // 日志配置
        builder.enableLogging(userConfig.isEnableLogging())
               .logRequestBody(userConfig.isLogRequestBody())
               .logResponseBody(userConfig.isLogResponseBody());

        return builder.build();
    }
}