/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.examples;

import com.maiyatian.channel.sdk.MytChannelSDK;
import com.maiyatian.channel.sdk.models.sender.api.IChannelSenderV2;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.*;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoReq;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoResp;
import com.maiyatian.channel.sdk.models.types.ApiResponse;
import com.maiyatian.channel.sdk.models.types.Money;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 麦芽田渠道开放平台 Java SDK V2 现代化示例
 * <p>
 * 展示如何使用新的泛型API和现代Java特性，对应Go SDK的使用方式
 * </p>
 * 
 * <h3>主要特性：</h3>
 * <ul>
 * <li>类型安全的泛型API调用</li>
 * <li>链式配置构建</li>
 * <li>异步编程支持</li>
 * <li>现代Java最佳实践</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public class ModernExampleV2 {

    private static final String TEST_BASE_URL = "https://open-api-test.maiyatian.com";
    private static final String PROD_BASE_URL = "https://open-api.maiyatian.com";

    public static void main(String[] args) {
        System.out.println("🚀 麦芽田渠道开放平台 Java SDK V2 现代化示例");
        System.out.println("📅 示例运行时间: " + Instant.now());
        System.out.println("🔧 SDK版本: " + MytChannelSDK.VERSION);
        System.out.println();

        try {
            // 运行所有示例
            runBasicUsageExample();
            runAuthorizationExample();
            runOrderLifecycleExample();
            runAsyncExample();
            runErrorHandlingExample();
            
            System.out.println("🎉 所有示例运行完成！");
            
        } catch (Exception e) {
            System.err.println("❌ 示例运行失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 基础使用示例
     * <p>
     * 对应 Go SDK 的基础配置和客户端创建
     * </p>
     */
    public static void runBasicUsageExample() {
        System.out.println("=== 基础使用示例 ===");

        // 1. 使用 Builder 模式创建客户端（推荐方式）
        try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                .baseUrl(TEST_BASE_URL)                           // 测试环境
                .apiKey("your_app_key")                           // 替换为你的 AppKey
                .apiSecret("your_app_secret")                     // 替换为你的 AppSecret  
                .requestTimeout(Duration.ofSeconds(30))           // 请求超时 30 秒
                .retryMaxAttempts(3)                             // 最大重试 3 次
                .enableLogging(true)                             // 启用日志记录
                .build()) {

            System.out.println("✅ 渠道发送客户端创建成功");
            System.out.println("📍 API 基础地址: " + TEST_BASE_URL);
            System.out.println("🔑 使用配置: 超时30秒, 重试3次, 启用日志");

        } catch (Exception e) {
            System.err.println("❌ 客户端创建失败: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * 授权流程示例
     * <p>
     * 对应 Go SDK 的授权流程：获取令牌 → 使用令牌
     * </p>
     */
    public static void runAuthorizationExample() {
        System.out.println("=== 授权流程示例 ===");

        try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                .baseUrl(TEST_BASE_URL)
                .apiKey("your_app_key")
                .apiSecret("your_app_secret")
                .enableLogging(true)
                .build()) {

            // 1. 获取访问令牌
            AccessTokenReq tokenReq = new AccessTokenReq();
            tokenReq.setGrantType("1");                          // 1:门店 2:商户 3:三方门店直连
            tokenReq.setCode("mock_auth_code_from_callback");     // 从 OAuth2 授权流程获取
            // 注意：其他字段根据实际实体类定义进行设置

            System.out.println("🔄 正在获取访问令牌...");
            ApiResponse<AccessTokenResp> tokenResp = sender.accessToken("", tokenReq);

            if (tokenResp.isSuccess()) {
                AccessTokenResp tokenData = tokenResp.getData();
                System.out.println("✅ 获取访问令牌成功!");
                System.out.println("📄 令牌信息: Token=" + tokenData.getToken().substring(0, 20) + "...");
                // 注意：过期时间字段根据实际实体类定义进行获取
                System.out.println("⏰ 令牌获取成功");
                
                // 保存令牌供后续使用
                String accessToken = tokenData.getToken();
                String refreshToken = tokenData.getRefreshToken();
                
                System.out.println("💾 令牌已保存，可用于后续API调用");
                
            } else {
                System.err.println("❌ 获取访问令牌失败: [" + tokenResp.getCode() + "] " + tokenResp.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ 授权流程异常: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * 订单生命周期示例
     * <p>
     * 对应 Go SDK 的订单推送流程：创建 → 确认 → 完成
     * </p>
     */
    public static void runOrderLifecycleExample() {
        System.out.println("=== 订单生命周期示例 ===");

        try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                .baseUrl(TEST_BASE_URL)
                .apiKey("your_app_key")
                .apiSecret("your_app_secret")
                .enableLogging(true)
                .build()) {

            String token = "mock_access_token"; // 从授权流程获取
            String orderNo = "order_" + System.currentTimeMillis();
            System.out.println("📦 订单号: " + orderNo);

            // 1. 推送新订单【必接】
            System.out.println("\n📤 步骤1: 推送新订单给麦芽田...");
            CreateOrderReq createOrderReq = buildSampleOrder(orderNo);
            
            ApiResponse<Void> createResp = sender.orderCreated(token, createOrderReq);
            if (createResp.isSuccess()) {
                System.out.println("✅ 新订单推送成功");
            } else {
                System.err.println("❌ 新订单推送失败: [" + createResp.getCode() + "] " + createResp.getMessage());
                return;
            }

            // 2. 订单确认【必接】
            System.out.println("\n📋 步骤2: 商户确认订单...");
            OrderConfirmedReq confirmReq = new OrderConfirmedReq();
            // 注意：根据实际实体类定义设置字段
            // confirmReq.setOrderNo(orderNo);
            // confirmReq.setEstimateTime(1800); // 预计30分钟出餐
            
            ApiResponse<Void> confirmResp = sender.orderConfirmed(token, confirmReq);
            if (confirmResp.isSuccess()) {
                System.out.println("✅ 订单确认成功");
            } else {
                System.err.println("❌ 订单确认失败: [" + confirmResp.getCode() + "] " + confirmResp.getMessage());
            }

            // 3. 订单完成【必接】
            System.out.println("\n🎯 步骤3: 订单配送完成...");
            OrderDoneReq doneReq = new OrderDoneReq();
            // 注意：根据实际实体类定义设置字段
            // doneReq.setOrderNo(orderNo);
            // doneReq.setFinishTime(Instant.now().getEpochSecond());
            
            ApiResponse<Void> doneResp = sender.orderDone(token, doneReq);
            if (doneResp.isSuccess()) {
                System.out.println("✅ 订单完成推送成功");
                System.out.println("🎉 订单 " + orderNo + " 全流程处理完成!");
            } else {
                System.err.println("❌ 订单完成推送失败: [" + doneResp.getCode() + "] " + doneResp.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ 订单流程异常: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * 异步编程示例
     * <p>
     * 展示如何在异步环境中使用SDK，对应Go SDK的并发处理
     * </p>
     */
    public static void runAsyncExample() {
        System.out.println("=== 异步编程示例 ===");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                .baseUrl(TEST_BASE_URL)
                .apiKey("your_app_key")
                .apiSecret("your_app_secret")
                .maxConnections(50)  // 增加连接池大小支持并发
                .enableLogging(false) // 关闭日志避免并发时输出混乱
                .build()) {

            String token = "mock_access_token";
            
            System.out.println("⚡ 开始异步推送多个订单...");
            
            // 创建多个异步任务
            List<CompletableFuture<String>> futures = Arrays.asList(
                // 异步推送订单1
                CompletableFuture.supplyAsync(() -> {
                    try {
                        String orderNo = "async_order_1_" + System.currentTimeMillis();
                        CreateOrderReq orderReq = buildSampleOrder(orderNo);
                        ApiResponse<Void> resp = sender.orderCreated(token, orderReq);
                        return resp.isSuccess() ? "✅ 订单1推送成功" : "❌ 订单1推送失败";
                    } catch (Exception e) {
                        return "❌ 订单1异常: " + e.getMessage();
                    }
                }, executor),
                
                // 异步推送订单2
                CompletableFuture.supplyAsync(() -> {
                    try {
                        String orderNo = "async_order_2_" + System.currentTimeMillis();
                        CreateOrderReq orderReq = buildSampleOrder(orderNo);
                        ApiResponse<Void> resp = sender.orderCreated(token, orderReq);
                        return resp.isSuccess() ? "✅ 订单2推送成功" : "❌ 订单2推送失败";
                    } catch (Exception e) {
                        return "❌ 订单2异常: " + e.getMessage();
                    }
                }, executor),
                
                // 异步查询门店信息
                CompletableFuture.supplyAsync(() -> {
                    try {
                        ShopInfoReq shopReq = new ShopInfoReq();
                        // 注意：根据实际实体类定义设置字段
                        // shopReq.setStoreId("store_123");
                        ApiResponse<ShopInfoResp> resp = sender.shopInfo(token, shopReq);
                        return resp.isSuccess() ? "✅ 门店信息查询成功" : "❌ 门店信息查询失败";
                    } catch (Exception e) {
                        return "❌ 门店查询异常: " + e.getMessage();
                    }
                }, executor)
            );

            // 等待所有任务完成并收集结果
            CompletableFuture<Void> allTasks = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
            );
            
            allTasks.thenRun(() -> {
                System.out.println("📊 异步任务执行结果:");
                futures.forEach(future -> {
                    try {
                        System.out.println("  " + future.get());
                    } catch (Exception e) {
                        System.err.println("  ❌ 任务异常: " + e.getMessage());
                    }
                });
            }).get(); // 阻塞等待完成

            System.out.println("🎉 异步示例完成！");

        } catch (Exception e) {
            System.err.println("❌ 异步示例异常: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
        
        System.out.println();
    }

    /**
     * 错误处理示例
     * <p>
     * 展示如何优雅地处理各种错误情况，对应Go SDK的错误处理方式
     * </p>
     */
    public static void runErrorHandlingExample() {
        System.out.println("=== 错误处理示例 ===");

        // 使用错误配置创建客户端来演示错误处理
        try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                .baseUrl("https://invalid-api.example.com") // 无效的API地址
                .apiKey("invalid_app_key")
                .apiSecret("invalid_app_secret")
                .requestTimeout(Duration.ofSeconds(5))        // 较短的超时时间
                .retryMaxAttempts(2)                         // 减少重试次数加快演示
                .enableLogging(false)
                .build()) {

            System.out.println("🧪 测试错误处理机制...");
            
            try {
                // 尝试推送订单（预期失败）
                CreateOrderReq orderReq = buildSampleOrder("error_test_order");
                ApiResponse<Void> response = sender.orderCreated("invalid_token", orderReq);
                
                // 错误处理的最佳实践
                if (response.isError()) {
                    handleBusinessError(response.getCode(), response.getMessage());
                } else {
                    System.out.println("✅ 请求成功（意外）");
                }
                
            } catch (java.net.UnknownHostException e) {
                System.out.println("🌐 网络错误: 无法连接到服务器 - " + e.getMessage());
                System.out.println("💡 建议：检查网络连接和API地址配置");
                
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("⏰ 超时错误: 请求超时 - " + e.getMessage());
                System.out.println("💡 建议：增加超时时间或检查网络状况");
                
            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                System.out.println("📄 JSON错误: 数据序列化失败 - " + e.getMessage());
                System.out.println("💡 建议：检查请求数据格式");
                
            } catch (RuntimeException e) {
                System.out.println("💥 运行时错误: " + e.getMessage());
                System.out.println("💡 建议：检查SDK配置和使用方式");
                
            } catch (Exception e) {
                System.out.println("❌ 未知错误: " + e.getMessage());
                System.out.println("💡 建议：查看详细错误信息，联系技术支持");
            }

            System.out.println("🔧 错误处理演示完成");

        } catch (Exception e) {
            System.err.println("❌ 错误处理示例异常: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * 处理业务错误的辅助方法
     */
    private static void handleBusinessError(int code, String message) {
        System.out.println("📋 业务错误处理:");
        System.out.println("   错误码: " + code);
        System.out.println("   错误信息: " + message);
        
        // 根据不同错误码进行相应处理
        switch (code) {
            case 401:
                System.out.println("💡 建议：检查 token 是否有效，可能需要重新授权或刷新令牌");
                break;
            case 403:
                System.out.println("💡 建议：检查 app_key 和 app_secret 是否正确");
                break;
            case 404:
                System.out.println("💡 建议：检查请求的资源是否存在（如订单号、商户ID）");
                break;
            case 422:
                System.out.println("💡 建议：检查请求参数格式，如订单状态、时间格式等");
                break;
            case 429:
                System.out.println("💡 建议：请求过于频繁，请稍后重试");
                break;
            default:
                if (code >= 500 && code < 600) {
                    System.out.println("💡 建议：服务器暂时异常，SDK会自动重试");
                } else {
                    System.out.println("💡 建议：查看详细错误信息，联系技术支持");
                }
                break;
        }
    }

    /**
     * 构建示例订单数据
     * <p>
     * 对应 Go SDK examples 中的订单数据构建
     * </p>
     */
    private static CreateOrderReq buildSampleOrder(String orderNo) {
        CreateOrderReq orderReq = new CreateOrderReq();
        
        // 注意：以下代码根据实际的实体类定义进行调整
        // 基础订单信息示例：
        // orderReq.setOrderNo(orderNo);
        // orderReq.setStoreId("store_123");
        
        System.out.println("📝 构建示例订单: " + orderNo);
        System.out.println("💡 提示：请根据实际的实体类定义设置订单字段");

        return orderReq;
    }
}