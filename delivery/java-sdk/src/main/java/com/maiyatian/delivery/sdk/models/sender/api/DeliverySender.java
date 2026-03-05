/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.models.types.Response;
import com.maiyatian.delivery.sdk.client.HttpClientConfig;
import com.maiyatian.delivery.sdk.client.HttpClientManager;
import com.maiyatian.delivery.sdk.client.ConfigValidationException;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import com.maiyatian.delivery.sdk.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * 配送发送接口实现类
 * <p>
 * 提供同步方式调用麦芽田开放平台配送相关接口
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeliverySender implements IDeliverySender {

    private static final Logger logger = LoggerFactory.getLogger(DeliverySender.class);
    private final HttpClientManager httpClientManager;

    // ==================== 构造函数 ====================

    /**
     * 创建新的配送发送者实例
     * 
     * @param config HTTP客户端配置
     * @throws ConfigValidationException 如果配置验证失败
     */
    public DeliverySender(HttpClientConfig config) throws ConfigValidationException {
        this.httpClientManager = new HttpClientManager(config);
        logger.info("配送发送者初始化完成");
    }

    /**
     * 通过配置构建器创建新的配送发送者实例
     * 
     * @param configBuilder 配置构建器
     * @throws ConfigValidationException 如果配置验证失败
     */
    public DeliverySender(HttpClientConfig.ConfigBuilder configBuilder) throws ConfigValidationException {
        this(configBuilder.build());
    }

    // ==================== 授权管理接口 ====================

    @Override
    public AccessTokenEntity.Resp accessToken(String token, AccessTokenEntity.Req data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        Response response = httpClientManager.post("access_token", data, token);
        return JsonUtils.fromJson(JsonUtils.toJson(response.getData()), AccessTokenEntity.Resp.class);
    }

    @Override
    public AccessTokenEntity.Resp accessToken(String token, AccessTokenEntity.Req data) 
            throws IOException, JsonProcessingException {
        return accessToken(token, data, Collections.emptyMap());
    }

    @Override
    public RefreshTokenEntity.Resp refreshToken(String token, RefreshTokenEntity.Req data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        Response response = httpClientManager.post("refresh_token", data, token);
        return JsonUtils.fromJson(JsonUtils.toJson(response.getData()), RefreshTokenEntity.Resp.class);
    }

    @Override
    public RefreshTokenEntity.Resp refreshToken(String token, RefreshTokenEntity.Req data) 
            throws IOException, JsonProcessingException {
        return refreshToken(token, data, Collections.emptyMap());
    }

    // ==================== 配送变更接口 ====================

    @Override
    public Response deliveryChange(String token, DeliveryChangeEntity.Req data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return httpClientManager.post("delivery_change", data, token);
    }

    @Override
    public Response deliveryChange(String token, DeliveryChangeEntity.Req data) 
            throws IOException, JsonProcessingException {
        return deliveryChange(token, data, Collections.emptyMap());
    }

    // ==================== 位置变更接口 ====================

    @Override
    public Response locationChange(String token, LocationChangeEntity.Req data, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return httpClientManager.post("location_change", data, token);
    }

    @Override
    public Response locationChange(String token, LocationChangeEntity.Req data) 
            throws IOException, JsonProcessingException {
        return locationChange(token, data, Collections.emptyMap());
    }

    // ==================== 工具方法 ====================

    @Override
    public Response request(String method, String path, Object data, String token, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return httpClientManager.request(method, path, data, token);
    }

    // ==================== 资源管理 ====================

    @Override
    public void close() {
        try {
            httpClientManager.close();
            logger.info("配送发送者已关闭");
        } catch (Exception e) {
            logger.error("关闭配送发送者时发生错误: {}", e.getMessage(), e);
        }
    }
}
