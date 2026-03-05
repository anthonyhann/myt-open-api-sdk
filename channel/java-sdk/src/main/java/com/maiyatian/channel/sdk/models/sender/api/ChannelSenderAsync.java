/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.channel.sdk.client.ConfigValidationException;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
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
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 麦芽田渠道开放平台异步 Sender API 客户端
 * <p>
 * 提供基于 CompletableFuture 的异步调用支持，适用于高并发场景
 * </p>
 * 
 * <h3>主要特性：</h3>
 * <ul>
 * <li>✅ 完全异步的API调用</li>
 * <li>✅ 基于CompletableFuture的响应式编程支持</li>
 * <li>✅ 自定义线程池管理</li>
 * <li>✅ 异常处理和链式调用</li>
 * <li>✅ 资源自动管理和清理</li>
 * </ul>
 * 
 * <h3>使用场景：</h3>
 * <ul>
 * <li>高并发订单推送</li>
 * <li>批量API调用</li>
 * <li>非阻塞业务流程</li>
 * <li>响应式编程架构</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ChannelSenderAsync implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(ChannelSenderAsync.class);

    private final ChannelSender syncClient;
    private final ExecutorService executorService;
    private volatile boolean closed = false;

    /**
     * 构造函数
     * 
     * @param config HTTP 客户端配置
     * @throws ConfigValidationException 如果配置验证失败
     */
    public ChannelSenderAsync(HttpClientConfig config) throws ConfigValidationException {
        this.syncClient = new ChannelSender(config);
        this.executorService = createExecutorService(config);
        
        logger.info("ChannelSenderAsync 异步客户端初始化完成");
    }

    /**
     * 创建自定义线程池
     */
    private ExecutorService createExecutorService(HttpClientConfig config) {
        // 根据配置决定线程池大小
        int threadPoolSize = Math.max(4, Runtime.getRuntime().availableProcessors());
        
        return Executors.newFixedThreadPool(threadPoolSize, new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "ChannelSender-async-" + threadNumber.getAndIncrement());
                t.setDaemon(true);  // 守护线程，JVM退出时自动清理
                t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        });
    }

    // ==================== 订单推送接口（异步版本） ====================

    /**
     * 新订单推送接口【必接】（异步版本）
     */
    public CompletableFuture<Response> orderCreatedAsync(String token, CreateOrderReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderCreated(token, request, headers));
    }

    /**
     * 新订单推送接口【必接】（异步简化版本）
     */
    public CompletableFuture<Response> orderCreatedAsync(String token, CreateOrderReq request) {
        return orderCreatedAsync(token, request, null);
    }

    /**
     * 订单修改推送接口（异步版本）
     */
    public CompletableFuture<Response> orderModifiedAsync(String token, UpdateOrderReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderModified(token, request, headers));
    }

    /**
     * 订单修改推送接口（异步简化版本）
     */
    public CompletableFuture<Response> orderModifiedAsync(String token, UpdateOrderReq request) {
        return orderModifiedAsync(token, request, null);
    }

    /**
     * 订单确认推送接口【必接】（异步版本）
     */
    public CompletableFuture<Response> orderConfirmedAsync(String token, OrderConfirmedReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderConfirmed(token, request, headers));
    }

    /**
     * 订单确认推送接口【必接】（异步简化版本）
     */
    public CompletableFuture<Response> orderConfirmedAsync(String token, OrderConfirmedReq request) {
        return orderConfirmedAsync(token, request, null);
    }

    /**
     * 订单催单推送接口（异步版本）
     */
    public CompletableFuture<Response> orderRemindAsync(String token, OrderRemindReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderRemind(token, request, headers));
    }

    /**
     * 订单催单推送接口（异步简化版本）
     */
    public CompletableFuture<Response> orderRemindAsync(String token, OrderRemindReq request) {
        return orderRemindAsync(token, request, null);
    }

    /**
     * 订单取消推送接口（异步版本）
     */
    public CompletableFuture<Response> orderCanceledAsync(String token, OrderCanceledReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderCanceled(token, request, headers));
    }

    /**
     * 订单取消推送接口（异步简化版本）
     */
    public CompletableFuture<Response> orderCanceledAsync(String token, OrderCanceledReq request) {
        return orderCanceledAsync(token, request, null);
    }

    /**
     * 订单完成推送接口【必接】（异步版本）
     */
    public CompletableFuture<Response> orderDoneAsync(String token, OrderDoneReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderDone(token, request, headers));
    }

    /**
     * 订单完成推送接口【必接】（异步简化版本）
     */
    public CompletableFuture<Response> orderDoneAsync(String token, OrderDoneReq request) {
        return orderDoneAsync(token, request, null);
    }

    // ==================== 退款推送接口（异步版本） ====================

    /**
     * 订单申请退款推送接口（异步版本）
     */
    public CompletableFuture<Response> orderApplyRefundAsync(String token, OrderApplyRefundReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderApplyRefund(token, request, headers));
    }

    /**
     * 订单申请退款推送接口（异步简化版本）
     */
    public CompletableFuture<Response> orderApplyRefundAsync(String token, OrderApplyRefundReq request) {
        return orderApplyRefundAsync(token, request, null);
    }

    /**
     * 订单退款结果推送接口（异步版本）
     */
    public CompletableFuture<Response> orderRefundedAsync(String token, OrderRefundedReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.orderRefunded(token, request, headers));
    }

    /**
     * 订单退款结果推送接口（异步简化版本）
     */
    public CompletableFuture<Response> orderRefundedAsync(String token, OrderRefundedReq request) {
        return orderRefundedAsync(token, request, null);
    }

    // ==================== 配送推送接口（异步版本） ====================

    /**
     * 自配送状态变更推送接口（异步版本）
     */
    public CompletableFuture<Response> selfDeliveryChangeAsync(String token, SelfDeliveryChangeReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.selfDeliveryChange(token, request, headers));
    }

    /**
     * 自配送状态变更推送接口（异步简化版本）
     */
    public CompletableFuture<Response> selfDeliveryChangeAsync(String token, SelfDeliveryChangeReq request) {
        return selfDeliveryChangeAsync(token, request, null);
    }

    // ==================== 授权管理接口（异步版本） ====================

    /**
     * 获取访问令牌接口【必接】（异步版本）
     */
    public CompletableFuture<AccessTokenResp> accessTokenAsync(String token, AccessTokenReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.accessToken(token, request, headers));
    }

    /**
     * 获取访问令牌接口【必接】（异步简化版本）
     */
    public CompletableFuture<AccessTokenResp> accessTokenAsync(String token, AccessTokenReq request) {
        return accessTokenAsync(token, request, null);
    }

    /**
     * 刷新访问令牌接口（异步版本）
     */
    public CompletableFuture<RefreshTokenResp> refreshTokenAsync(String token, RefreshTokenReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.refreshToken(token, request, headers));
    }

    /**
     * 刷新访问令牌接口（异步简化版本）
     */
    public CompletableFuture<RefreshTokenResp> refreshTokenAsync(String token, RefreshTokenReq request) {
        return refreshTokenAsync(token, request, null);
    }

    // ==================== 门店查询接口（异步版本） ====================

    /**
     * 查询麦芽田门店信息接口（异步版本）
     */
    public CompletableFuture<ShopInfoResp> shopInfoAsync(String token, ShopInfoReq request, Map<String, String> headers) {
        return executeAsync(() -> syncClient.shopInfo(token, request, headers));
    }

    /**
     * 查询麦芽田门店信息接口（异步简化版本）
     */
    public CompletableFuture<ShopInfoResp> shopInfoAsync(String token, ShopInfoReq request) {
        return shopInfoAsync(token, request, null);
    }

    // ==================== 工具方法 ====================

    /**
     * 执行原始HTTP请求（异步版本）
     */
    public CompletableFuture<Response> requestAsync(String method, String path, Object data, String token, Map<String, String> headers) {
        return executeAsync(() -> syncClient.request(method, path, data, token, headers));
    }

    /**
     * 执行异步操作的通用方法
     */
    private <T> CompletableFuture<T> executeAsync(ThrowingSupplier<T> supplier) {
        if (closed) {
            // Java 8 兼容的方式创建失败的 Future
            CompletableFuture<T> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(new IllegalStateException("ChannelSenderAsync 已关闭"));
            return failedFuture;
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.get();
            } catch (JsonProcessingException e) {
                logger.error("JSON处理失败", e);
                throw new RuntimeException("JSON处理失败: " + e.getMessage(), e);
            } catch (IOException e) {
                logger.error("网络请求失败", e);
                throw new RuntimeException("网络请求失败: " + e.getMessage(), e);
            } catch (Exception e) {
                logger.error("API调用失败", e);
                throw new RuntimeException("API调用失败: " + e.getMessage(), e);
            }
        }, executorService);
    }

    /**
     * 获取同步客户端实例
     */
    public ChannelSender getSyncClient() {
        return syncClient;
    }

    /**
     * 检查客户端是否已关闭
     */
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        if (!closed) {
            closed = true;
            
            // 优雅关闭线程池
            executorService.shutdown();
            
            // 关闭同步客户端
            syncClient.close();
            
            logger.info("ChannelSenderAsync 异步客户端已关闭");
        }
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建新的异步客户端实例
     */
    public static ChannelSenderAsync create(HttpClientConfig config) throws ConfigValidationException {
        return new ChannelSenderAsync(config);
    }

    // ==================== 函数式接口 ====================

    /**
     * 支持抛出异常的Supplier接口
     */
    @FunctionalInterface
    private interface ThrowingSupplier<T> {
        T get() throws Exception;
    }

    // ==================== 批量操作增强方法 ====================

    /**
     * 批量推送订单
     */
    public CompletableFuture<java.util.List<Response>> batchOrderCreated(String token, java.util.List<CreateOrderReq> orders) {
        if (orders == null || orders.isEmpty()) {
            return CompletableFuture.completedFuture(java.util.Collections.emptyList());
        }

        java.util.List<CompletableFuture<Response>> futures = orders.stream()
                .map(order -> orderCreatedAsync(token, order))
                .collect(java.util.stream.Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(java.util.stream.Collectors.toList()));
    }

    /**
     * 批量推送订单确认
     */
    public CompletableFuture<java.util.List<Response>> batchOrderConfirmed(String token, java.util.List<OrderConfirmedReq> confirmations) {
        if (confirmations == null || confirmations.isEmpty()) {
            return CompletableFuture.completedFuture(java.util.Collections.emptyList());
        }

        java.util.List<CompletableFuture<Response>> futures = confirmations.stream()
                .map(confirmation -> orderConfirmedAsync(token, confirmation))
                .collect(java.util.stream.Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(java.util.stream.Collectors.toList()));
    }

    /**
     * 批量推送订单完成
     */
    public CompletableFuture<java.util.List<Response>> batchOrderDone(String token, java.util.List<OrderDoneReq> completions) {
        if (completions == null || completions.isEmpty()) {
            return CompletableFuture.completedFuture(java.util.Collections.emptyList());
        }

        java.util.List<CompletableFuture<Response>> futures = completions.stream()
                .map(completion -> orderDoneAsync(token, completion))
                .collect(java.util.stream.Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(java.util.stream.Collectors.toList()));
    }
}