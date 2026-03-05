/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.*;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoReq;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoResp;
import com.maiyatian.channel.sdk.models.types.ApiResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 渠道发送接口 V2 - 泛型版本
 * <p>
 * 对应 Go SDK 中的 IChannelSender 接口，提供类型安全的 API 调用
 * 所有方法返回 ApiResponse&lt;T&gt; 类型，匹配 Go SDK 的 ApiResponse[T] 设计
 * </p>
 * 
 * <h3>接口说明：</h3>
 * 这些接口由三方服务商主动调用，向麦芽田开放平台推送订单数据、状态变更等信息
 * 
 * <h3>必接接口（标注【必接】）：</h3>
 * <ul>
 * <li><strong>orderCreated</strong>：新订单推送（三方必须推送新订单给麦芽田）</li>
 * <li><strong>orderConfirmed</strong>：订单确认推送（三方确认接单后推送给麦芽田）</li>
 * <li><strong>orderDone</strong>：订单完成推送（三方订单完成后推送给麦芽田）</li>
 * <li><strong>accessToken</strong>：获取访问令牌（授权流程必需）</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IChannelSenderV2 extends AutoCloseable {

    // ==================== 订单推送接口（对应 Go SDK ApiResponseEmptyData）====================

    /**
     * 新订单推送接口【必接】
     * <p>
     * 对应 Go SDK: OrderCreated(ctx context.Context, token string, data *entityOrder.CreateOrderReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     * 
     * @param token 商户授权令牌
     * @param data 订单详细信息
     * @param headers 自定义请求头（可选）
     * @return 空数据的API响应
     */
    ApiResponse<Void> orderCreated(String token, CreateOrderReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 新订单推送接口【必接】（简化版本）
     */
    default ApiResponse<Void> orderCreated(String token, CreateOrderReq data) 
            throws IOException, JsonProcessingException {
        return orderCreated(token, data, null);
    }

    /**
     * 订单修改推送接口
     * <p>
     * 对应 Go SDK: OrderModified(ctx context.Context, token string, data *entityOrder.UpdateOrderReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderModified(String token, UpdateOrderReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderModified(String token, UpdateOrderReq data) 
            throws IOException, JsonProcessingException {
        return orderModified(token, data, null);
    }

    /**
     * 订单确认推送接口【必接】
     * <p>
     * 对应 Go SDK: OrderConfirmed(ctx context.Context, token string, data *entityOrder.OrderConfirmedReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderConfirmed(String token, OrderConfirmedReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderConfirmed(String token, OrderConfirmedReq data) 
            throws IOException, JsonProcessingException {
        return orderConfirmed(token, data, null);
    }

    /**
     * 订单催单推送接口
     * <p>
     * 对应 Go SDK: OrderRemind(ctx context.Context, token string, data *entityOrder.OrderRemindReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderRemind(String token, OrderRemindReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderRemind(String token, OrderRemindReq data) 
            throws IOException, JsonProcessingException {
        return orderRemind(token, data, null);
    }

    /**
     * 订单取消推送接口
     * <p>
     * 对应 Go SDK: OrderCanceled(ctx context.Context, token string, data *entityOrder.OrderCanceledReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderCanceled(String token, OrderCanceledReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderCanceled(String token, OrderCanceledReq data) 
            throws IOException, JsonProcessingException {
        return orderCanceled(token, data, null);
    }

    /**
     * 订单完成推送接口【必接】
     * <p>
     * 对应 Go SDK: OrderDone(ctx context.Context, token string, data *entityOrder.OrderDoneReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderDone(String token, OrderDoneReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderDone(String token, OrderDoneReq data) 
            throws IOException, JsonProcessingException {
        return orderDone(token, data, null);
    }

    // ==================== 退款推送接口 ====================

    /**
     * 订单申请退款推送接口
     * <p>
     * 对应 Go SDK: OrderApplyRefund(ctx context.Context, token string, data *entityOrder.OrderApplyRefundReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderApplyRefund(String token, OrderApplyRefundReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderApplyRefund(String token, OrderApplyRefundReq data) 
            throws IOException, JsonProcessingException {
        return orderApplyRefund(token, data, null);
    }

    /**
     * 订单退款结果推送接口
     * <p>
     * 对应 Go SDK: OrderRefunded(ctx context.Context, token string, data *entityOrder.OrderRefundedReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> orderRefunded(String token, OrderRefundedReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> orderRefunded(String token, OrderRefundedReq data) 
            throws IOException, JsonProcessingException {
        return orderRefunded(token, data, null);
    }

    // ==================== 配送推送接口 ====================

    /**
     * 自配送状态变更推送接口
     * <p>
     * 对应 Go SDK: SelfDeliveryChange(ctx context.Context, token string, data *entityOrder.SelfDeliveryChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error)
     * </p>
     */
    ApiResponse<Void> selfDeliveryChange(String token, SelfDeliveryChangeReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<Void> selfDeliveryChange(String token, SelfDeliveryChangeReq data) 
            throws IOException, JsonProcessingException {
        return selfDeliveryChange(token, data, null);
    }

    // ==================== 授权管理接口（返回具体类型）====================

    /**
     * 获取访问令牌接口【必接】
     * <p>
     * 对应 Go SDK: AccessToken(ctx context.Context, data *entity.AccessTokenReq) (*client.ApiResponse[*entity.AccessTokenResp], error)
     * </p>
     * 
     * @param token 商户授权令牌（此接口传空字符串）
     * @param data 授权请求参数
     * @param headers 自定义请求头（可选）
     * @return 包含访问令牌的API响应
     */
    ApiResponse<AccessTokenResp> accessToken(String token, AccessTokenReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<AccessTokenResp> accessToken(String token, AccessTokenReq data) 
            throws IOException, JsonProcessingException {
        return accessToken(token, data, null);
    }

    /**
     * 刷新访问令牌接口
     * <p>
     * 对应 Go SDK: RefreshToken(ctx context.Context, token string, data *entity.RefreshTokenReq) (*client.ApiResponse[*entity.RefreshTokenResp], error)
     * </p>
     */
    ApiResponse<RefreshTokenResp> refreshToken(String token, RefreshTokenReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<RefreshTokenResp> refreshToken(String token, RefreshTokenReq data) 
            throws IOException, JsonProcessingException {
        return refreshToken(token, data, null);
    }

    // ==================== 门店查询接口 ====================

    /**
     * 查询麦芽田门店信息接口
     * <p>
     * 对应 Go SDK: ShopInfo(ctx context.Context, token string, data *entityShop.ShopInfoReq) (*client.ApiResponse[*entityShop.ShopInfoResp], error)
     * </p>
     */
    ApiResponse<ShopInfoResp> shopInfo(String token, ShopInfoReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    default ApiResponse<ShopInfoResp> shopInfo(String token, ShopInfoReq data) 
            throws IOException, JsonProcessingException {
        return shopInfo(token, data, null);
    }
}