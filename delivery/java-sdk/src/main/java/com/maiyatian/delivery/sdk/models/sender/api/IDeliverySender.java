/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import com.maiyatian.delivery.sdk.models.types.Response;

import java.io.IOException;
import java.util.Map;

/**
 * 配送发送接口
 * <p>
 * 定义三方服务主动推送数据到麦芽田开放平台的所有接口
 * </p>
 * 
 * <h3>接口说明：</h3>
 * 这些接口由三方服务商主动调用，向麦芽田开放平台推送配送状态变更、位置变更等信息
 * 
 * <h3>接口分类：</h3>
 * <ul>
 * <li><strong>授权管理</strong>：获取访问令牌、刷新令牌</li>
 * <li><strong>配送变更</strong>：配送状态变更推送</li>
 * <li><strong>位置变更</strong>：骑手位置变更推送</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IDeliverySender extends AutoCloseable {
    
    /**
     * 关闭资源
     * 
     * @throws IOException 如果关闭资源时发生错误
     */
    @Override
    void close() throws IOException;

    // ==================== 授权管理接口 ====================

    /**
     * 获取访问令牌接口
     * <p>
     * 三方服务使用授权码code向麦芽田换取访问令牌
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 商户授权完成 → 三方获得code → 调用此接口向麦芽田换取token → 麦芽田返回访问令牌
     * 
     * <h3>接口路径：</h3>
     * POST /v1/delivery/access_token
     * 
     * @param token 商户授权令牌
     * @param data 授权请求数据
     * @param headers 自定义请求头（可选）
     * @return 访问令牌响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化失败时
     */
    AccessTokenEntity.Resp accessToken(String token, AccessTokenEntity.Req data, Map<String, String> headers)
            throws IOException, JsonProcessingException;

    /**
     * 获取访问令牌接口（简化版本）
     */
    AccessTokenEntity.Resp accessToken(String token, AccessTokenEntity.Req data)
            throws IOException, JsonProcessingException;

    /**
     * 刷新访问令牌接口
     * <p>
     * 三方服务使用刷新令牌向麦芽田重新获取新的访问令牌
     * </p>
     * 
     * <h3>业务流程：</h3>
     * token即将过期 → 调用此接口向麦芽田刷新 → 麦芽田返回新的token和refresh_token
     * 
     * <h3>接口路径：</h3>
     * POST /v1/delivery/refresh_token
     * 
     * @param token 商户授权令牌
     * @param data 刷新请求数据
     * @param headers 自定义请求头（可选）
     * @return 刷新令牌响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化失败时
     */
    RefreshTokenEntity.Resp refreshToken(String token, RefreshTokenEntity.Req data, Map<String, String> headers)
            throws IOException, JsonProcessingException;

    /**
     * 刷新访问令牌接口（简化版本）
     */
    RefreshTokenEntity.Resp refreshToken(String token, RefreshTokenEntity.Req data)
            throws IOException, JsonProcessingException;

    // ==================== 配送变更接口 ====================

    /**
     * 配送状态变更推送接口
     * <p>
     * 三方服务在配送状态发生变更时，主动推送变更信息给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 配送状态变更 → 三方调用此接口推送给麦芽田 → 麦芽田更新配送状态
     * 
     * <h3>接口路径：</h3>
     * POST /v1/delivery/delivery_change
     * 
     * @param token 商户授权令牌
     * @param data 配送变更数据
     * @param headers 自定义请求头（可选）
     * @return API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化失败时
     */
    Response deliveryChange(String token, DeliveryChangeEntity.Req data, Map<String, String> headers)
            throws IOException, JsonProcessingException;

    /**
     * 配送状态变更推送接口（简化版本）
     */
    Response deliveryChange(String token, DeliveryChangeEntity.Req data)
            throws IOException, JsonProcessingException;

    // ==================== 位置变更接口 ====================

    /**
     * 骑手位置变更推送接口
     * <p>
     * 三方服务在骑手位置发生变更时，主动推送位置信息给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 骑手位置变更 → 三方调用此接口推送给麦芽田 → 麦芽田更新骑手位置
     * 
     * <h3>接口路径：</h3>
     * POST /v1/delivery/location_change
     * 
     * @param token 商户授权令牌
     * @param data 位置变更数据
     * @param headers 自定义请求头（可选）
     * @return API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化失败时
     */
    Response locationChange(String token, LocationChangeEntity.Req data, Map<String, String> headers)
            throws IOException, JsonProcessingException;

    /**
     * 骑手位置变更推送接口（简化版本）
     */
    Response locationChange(String token, LocationChangeEntity.Req data)
            throws IOException, JsonProcessingException;

    // ==================== 工具方法 ====================

    /**
     * 执行原始HTTP请求
     * 
     * @param method HTTP方法
     * @param path API路径
     * @param data 请求数据
     * @param token 授权令牌
     * @param headers 请求头
     * @return API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON处理失败时
     */
    Response request(String method, String path, Object data, String token, Map<String, String> headers)
            throws IOException, JsonProcessingException;
}
