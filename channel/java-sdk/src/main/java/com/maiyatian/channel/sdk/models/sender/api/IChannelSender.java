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
import com.maiyatian.channel.sdk.models.types.Response;
import com.maiyatian.channel.sdk.models.types.ApiResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 渠道发送接口
 * <p>
 * 定义三方服务主动推送数据到麦芽田开放平台的所有接口
 * </p>
 * 
 * <h3>接口说明：</h3>
 * 这些接口由三方服务商主动调用，向麦芽田开放平台推送订单数据、状态变更等信息
 * 
 * <h3>接口分类：</h3>
 * <ul>
 * <li><strong>订单推送</strong>：新订单推送、订单修改推送、订单确认推送、订单完成推送、订单取消推送、催单推送</li>
 * <li><strong>退款推送</strong>：退款申请推送、退款结果推送</li>
 * <li><strong>配送推送</strong>：自配送状态变更推送</li>
 * <li><strong>授权管理</strong>：获取访问令牌、刷新令牌</li>
 * <li><strong>门店查询</strong>：查询门店信息</li>
 * </ul>
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
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IChannelSender extends AutoCloseable {

    // ==================== 订单推送接口 ====================

    /**
     * 新订单推送接口【必接】
     * <p>
     * 三方服务在用户下单并付款成功后，主动推送新订单给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 用户在三方平台下单 → 三方创建订单 → 调用此接口推送给麦芽田 → 麦芽田接收并处理订单
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_created
     * 
     * @param token 商户授权令牌
     * @param data 订单详细信息（包含商品、费用、顾客、配送等完整数据）
     * @param headers 自定义请求头（可选）
     * @return API响应（code=200表示成功）
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化失败时
     */
    Response orderCreated(String token, CreateOrderReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 新订单推送接口【必接】（简化版本）
     */
    Response orderCreated(String token, CreateOrderReq data) 
            throws IOException, JsonProcessingException;

    /**
     * 订单修改推送接口
     * <p>
     * 三方服务在用户修改订单后，主动推送订单变更信息给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 用户在三方平台修改订单 → 三方更新订单 → 调用此接口推送给麦芽田 → 麦芽田更新订单信息
     * 
     * <h3>注意事项：</h3>
     * <ul>
     * <li>仅订单确认前可调用</li>
     * <li>修改内容仅推送修改部分的数据</li>
     * </ul>
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_modified
     */
    Response orderModified(String token, UpdateOrderReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单修改推送接口（简化版本）
     */
    Response orderModified(String token, UpdateOrderReq data) 
            throws IOException, JsonProcessingException;

    /**
     * 订单确认推送接口【必接】
     * <p>
     * 三方服务在商户确认订单后，主动推送确认状态给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 三方商户确认接单 → 调用此接口推送给麦芽田 → 麦芽田更新订单状态为"已确认"
     * 
     * <h3>注意事项：</h3>
     * <ul>
     * <li>订单确认后不可撤销</li>
     * <li>确认后开始计算出餐时间</li>
     * </ul>
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_confirmed
     */
    Response orderConfirmed(String token, OrderConfirmedReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单确认推送接口【必接】（简化版本）
     */
    Response orderConfirmed(String token, OrderConfirmedReq data) 
            throws IOException, JsonProcessingException;

    /**
     * 订单催单推送接口
     * <p>
     * 三方服务在用户催单后，主动推送催单信息给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 用户在三方平台催单 → 三方调用此接口推送给麦芽田 → 麦芽田记录催单
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_remind
     */
    Response orderRemind(String token, OrderRemindReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单催单推送接口（简化版本）
     */
    Response orderRemind(String token, OrderRemindReq data) 
            throws IOException, JsonProcessingException;

    /**
     * 订单取消推送接口
     * <p>
     * 三方服务在订单被取消后，主动推送取消状态给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 用户/商家取消订单 → 三方调用此接口推送给麦芽田 → 麦芽田更新订单状态
     * 
     * <h3>注意事项：</h3>
     * <ul>
     * <li>订单取消后不可恢复</li>
     * </ul>
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_canceled
     */
    Response orderCanceled(String token, OrderCanceledReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单取消推送接口（简化版本）
     */
    Response orderCanceled(String token, OrderCanceledReq data) 
            throws IOException, JsonProcessingException;

    /**
     * 订单完成推送接口【必接】
     * <p>
     * 三方服务在订单配送完成后，主动推送完成状态给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 订单配送完成 → 三方调用此接口推送给麦芽田 → 麦芽田更新订单状态并进入结算
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_done
     */
    Response orderDone(String token, OrderDoneReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单完成推送接口【必接】（简化版本）
     */
    Response orderDone(String token, OrderDoneReq data) 
            throws IOException, JsonProcessingException;

    // ==================== 退款推送接口 ====================

    /**
     * 订单申请退款推送接口
     * <p>
     * 三方服务在顾客或商家发起退款申请后，主动推送退款申请给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 用户/商家在三方平台申请退款 → 调用此接口推送给麦芽田 → 麦芽田处理退款流程
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_apply_refund
     */
    Response orderApplyRefund(String token, OrderApplyRefundReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单申请退款推送接口（简化版本）
     */
    Response orderApplyRefund(String token, OrderApplyRefundReq data) 
            throws IOException, JsonProcessingException;

    /**
     * 订单退款结果推送接口
     * <p>
     * 三方服务在处理退款申请后，主动推送退款结果给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 三方处理退款 → 调用此接口推送退款结果给麦芽田 → 麦芽田更新退款状态
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/order_refunded
     */
    Response orderRefunded(String token, OrderRefundedReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 订单退款结果推送接口（简化版本）
     */
    Response orderRefunded(String token, OrderRefundedReq data) 
            throws IOException, JsonProcessingException;

    // ==================== 配送推送接口 ====================

    /**
     * 自配送状态变更推送接口
     * <p>
     * 三方服务在使用自配送时，主动推送配送状态变更给麦芽田
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 三方自配送状态变化（骑手接单、到店、配送中等）→ 调用此接口推送给麦芽田 → 麦芽田同步配送状态
     * 
     * <h3>注意事项：</h3>
     * <ul>
     * <li>仅适用于三方自配送订单</li>
     * <li>需要及时推送状态，让用户可以追踪订单</li>
     * </ul>
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/self_delivery_change
     */
    Response selfDeliveryChange(String token, SelfDeliveryChangeReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 自配送状态变更推送接口（简化版本）
     */
    Response selfDeliveryChange(String token, SelfDeliveryChangeReq data) 
            throws IOException, JsonProcessingException;

    // ==================== 授权管理接口 ====================

    /**
     * 获取访问令牌接口【必接】
     * <p>
     * 三方服务使用授权码code向麦芽田换取访问令牌
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 商户授权完成 → 三方获得code → 调用此接口向麦芽田换取token → 麦芽田返回访问令牌
     * 
     * <h3>注意事项：</h3>
     * <ul>
     * <li>此接口的公共参数token传空字符串</li>
     * <li>授权码code有效期5分钟，只能使用一次</li>
     * </ul>
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/access_token
     */
    AccessTokenResp accessToken(String token, AccessTokenReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 获取访问令牌接口【必接】（简化版本）
     */
    AccessTokenResp accessToken(String token, AccessTokenReq data) 
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
     * <h3>注意事项：</h3>
     * <ul>
     * <li>刷新得到新的token和refresh_token</li>
     * <li>旧的token随即在5分钟内失效</li>
     * <li>需要在refresh_token时效内换取新的，避免授权频繁失效</li>
     * </ul>
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/refresh_token
     */
    RefreshTokenResp refreshToken(String token, RefreshTokenReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 刷新访问令牌接口（简化版本）
     */
    RefreshTokenResp refreshToken(String token, RefreshTokenReq data) 
            throws IOException, JsonProcessingException;

    // ==================== 门店查询接口 ====================

    /**
     * 查询麦芽田门店信息接口
     * <p>
     * 三方服务主动查询门店在麦芽田平台的基本信息和营业状态
     * </p>
     * 
     * <h3>业务流程：</h3>
     * 三方需要门店信息 → 调用此接口查询麦芽田 → 麦芽田返回门店信息
     * 
     * <h3>接口路径：</h3>
     * POST /v1/channel/shop_info
     */
    ShopInfoResp shopInfo(String token, ShopInfoReq data, Map<String, String> headers) 
            throws IOException, JsonProcessingException;

    /**
     * 查询麦芽田门店信息接口（简化版本）
     */
    ShopInfoResp shopInfo(String token, ShopInfoReq data) 
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