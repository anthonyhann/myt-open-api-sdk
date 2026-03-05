/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.channel.sdk.client.ConfigValidationException;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.models.sender.api.ChannelSender;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;

import java.io.IOException;
import java.time.Duration;

/**
 * 麦芽田渠道开放平台 Java SDK 快速开始示例
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class QuickStartExample {

    public static void main(String[] args) {
        // 配置参数
        String baseUrl = "https://open-api-test.maiyatian.com"; // 测试环境地址
        String apiKey = "your_app_key";      // 替换为你的应用密钥
        String apiSecret = "your_app_secret"; // 替换为你的应用密钥
        
        try {
            // 1. 创建配置
            HttpClientConfig config = createConfig(baseUrl, apiKey, apiSecret);
            
            // 2. 创建 Sender 客户端
            try (ChannelSender sender = new ChannelSender(config)) {
                System.out.println("✅ ChannelSender 客户端创建成功！");
                
                // 3. 演示获取访问令牌
                demonstrateAccessToken(sender);
                
            } // try-with-resources 会自动关闭客户端
            
        } catch (ConfigValidationException e) {
            System.err.println("❌ 配置验证失败: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ 运行出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建 HTTP 客户端配置
     */
    private static HttpClientConfig createConfig(String baseUrl, String apiKey, String apiSecret) 
            throws ConfigValidationException {
        return HttpClientConfig.newBuilder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                // 连接池配置
                .maxConnections(50)
                .maxConnectionsPerHost(10)
                // 超时配置
                .requestTimeout(Duration.ofSeconds(30))
                .connectionTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                // 重试配置
                .retryMaxAttempts(3)
                .retryBaseDelay(Duration.ofSeconds(1))
                .retryMaxDelay(Duration.ofSeconds(30))
                // 日志配置
                .enableLogging(true)
                .logRequestBody(false) // 生产环境建议设为 false
                .logResponseBody(false) // 生产环境建议设为 false
                .build();
    }

    /**
     * 演示获取访问令牌
     */
    private static void demonstrateAccessToken(ChannelSender sender) {
        System.out.println("\n🔑 演示获取访问令牌:");
        
        try {
            // 创建获取访问令牌请求
            AccessTokenReq request = AccessTokenReq.createShopAuth("AUTH_CODE_FROM_CALLBACK", "shop_123")
                    .withCategory("canyin")
                    .withName("测试门店")
                    .withType("waimai")
                    .withLocation("116.397428", "39.90923");
            
            System.out.println("📤 发送请求: " + request);
            
            // 注意：这里会因为 AUTH_CODE 不正确而失败，仅用于演示 API 调用方式
            // 在实际使用中，需要从 OAuth 回调中获取真实的授权码
            AccessTokenResp response = sender.accessToken("", request);
            
            System.out.println("📥 响应成功:");
            System.out.println("   Shop ID: " + response.getShopId());
            System.out.println("   Token: " + response.getToken().substring(0, 8) + "***");
            System.out.println("   过期时间: " + response.getFormattedExpireTime());
            System.out.println("   是否需要刷新: " + response.needsRefresh());
            
        } catch (JsonProcessingException e) {
            System.err.println("❌ JSON 处理失败: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("❌ 网络请求失败: " + e.getMessage());
        } catch (RuntimeException e) {
            // 这里通常是业务错误，比如授权码无效
            System.err.println("⚠️  业务错误（预期的，因为使用了测试授权码）: " + e.getMessage());
            System.out.println("💡 提示：请使用真实的 OAuth 授权码进行测试");
        }
    }
}