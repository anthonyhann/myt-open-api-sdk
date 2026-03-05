/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.client.ApiClientUtils;
import com.maiyatian.delivery.sdk.client.HttpClientConfig;
import com.maiyatian.delivery.sdk.client.HttpClientManager;
import com.maiyatian.delivery.sdk.consts.Constants;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import com.maiyatian.delivery.sdk.models.types.ApiResponse;
import com.maiyatian.delivery.sdk.vars.Variables;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 高级特性示例
 * <p>
 * 展示SDK的高级功能，包括：
 * <ul>
 * <li>Builder模式配置</li>
 * <li>泛型API调用</li>
 * <li>错误处理</li>
 * <li>自定义请求头</li>
 * <li>调试模式</li>
 * </ul>
 * 
 * <h3>对应Go SDK示例：</h3>
 * <pre>{@code
 * // Go SDK usage
 * config := client.NewConfigBuilder().
 *     BaseURL("https://open-api-test.maiyatian.com").
 *     APIKey("your_app_key").
 *     APISecret("your_app_secret").
 *     Build()
 * 
 * sender := api.NewDeliverySender(config)
 * response, err := client.RequestWithApiClient[*entity.AccessTokenResp](
 *     ctx, sender.apiClient, "POST", "v1/delivery/access_token", data, "", nil
 * )
 * }</pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class AdvancedExample {
    
    /**
     * 主演示方法
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 检查调试模式
        if (Variables.isDebugging()) {
            System.out.println("[DEBUG] 调试模式已启用，SDK版本: " + Variables.getVersion());
            System.out.println("[DEBUG] User-Agent: " + Variables.getUserAgent());
        }
        
        try {
            // 演示1：使用Builder模式创建配置
            demonstrateBuilderPattern();
            
            // 演示2：类型安全的泛型API调用
            demonstrateGenericApiCalls();
            
            // 演示3：错误处理和重试机制
            demonstrateErrorHandling();
            
            // 演示4：自定义请求头和超时设置
            demonstrateCustomHeaders();
            
        } catch (Exception e) {
            System.err.println("演示过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 演示Builder模式配置
     * <p>
     * 对应Go SDK的ConfigBuilder功能
     * </p>
     */
    private static void demonstrateBuilderPattern() throws Exception {
        System.out.println("=== 演示1：Builder模式配置 ===\n");
        
        try {
            // 基础配置
            System.out.println("1. 基础配置:");
            HttpClientConfig basicConfig = HttpClientConfig.builder()
                .baseUrl(Constants.TEST_BASE_URL)
                .apiKey("your_app_key")
                .apiSecret("your_app_secret")
                .build();
            
            System.out.println("   基础地址: " + basicConfig.getBaseUrl());
            System.out.println("   SDK版本: " + basicConfig.getSdkVersion());
            
            // 高级配置
            System.out.println("\n2. 高级配置:");
            HttpClientConfig advancedConfig = HttpClientConfig.builder()
                .baseUrl(Constants.PROD_BASE_URL)
                .apiKey("prod_app_key")
                .apiSecret("prod_app_secret")
                .maxConnections(100)                    // 提高并发连接数
                .maxConnectionsPerHost(20)              // 每个主机20个连接
                .requestTimeout(Duration.ofSeconds(30)) // 30秒超时
                .retryMaxAttempts(5)                    // 最多重试5次
                .retryBaseDelay(Duration.ofMillis(500)) // 500ms基础延迟
                .enableLogging(true)                    // 启用日志
                .logRequestBody(false)                  // 不记录请求体（安全考虑）
                .logResponseBody(false)                 // 不记录响应体（性能考虑）
                .build();
            
            System.out.println("   最大连接数: " + advancedConfig.getMaxConnections());
            System.out.println("   请求超时: " + advancedConfig.getRequestTimeout().getSeconds() + "秒");
            System.out.println("   最大重试次数: " + advancedConfig.getRetryMaxAttempts());
            
        } catch (Exception e) {
            System.err.println("配置构建失败: " + e.getMessage());
        }
    }
    
    /**
     * 演示类型安全的泛型API调用
     * <p>
     * 对应Go SDK的RequestWithApiClient[T any]功能
     * </p>
     */
    private static void demonstrateGenericApiCalls() throws Exception {
        System.out.println("\n=== 演示2：类型安全的泛型API调用 ===\n");
        
        // 创建配置
        HttpClientConfig config = HttpClientConfig.builder()
            .baseUrl(Constants.TEST_BASE_URL)
            .apiKey("demo_app_key")
            .apiSecret("demo_app_secret")
            .build();
        
        try (HttpClientManager httpClient = new HttpClientManager(config)) {
            
            // 1. 调用返回具体类型的API（类型安全）
            System.out.println("1. 获取访问令牌 (ApiResponse<AccessTokenEntity.Resp>):");
            
            AccessTokenEntity.Req tokenRequest = new AccessTokenEntity.Req();
            tokenRequest.setGrantType(Constants.GRANT_TYPE_STORE);
            tokenRequest.setCode("demo_auth_code");
            tokenRequest.setMobile("13800138000");
            tokenRequest.setStoreId("demo_store_001");
            tokenRequest.setCity("北京市");
            tokenRequest.setCityCode("10001");
            
            // 使用泛型工具类进行类型安全的API调用
            ApiResponse<AccessTokenEntity.Resp> tokenResponse = ApiClientUtils.requestWithApiClient(
                httpClient,
                "POST",
                Constants.ACCESS_TOKEN_PATH,
                tokenRequest,
                "", // 首次获取不需要token
                null,
                AccessTokenEntity.Resp.class
            );
            
            if (tokenResponse.isSuccess()) {
                AccessTokenEntity.Resp tokenData = tokenResponse.getDataOrThrow();
                System.out.println("   成功获取令牌: " + tokenData.getToken());
                System.out.println("   刷新令牌: " + tokenData.getRefreshToken());
                System.out.println("   过期时间: " + tokenData.getExpireTime());
            } else {
                System.err.println("   获取令牌失败: " + tokenResponse.getMessage());
            }
            
            // 2. 调用无返回数据的API（Void类型）
            System.out.println("\n2. 配送状态变更 (ApiResponse<Void>):");
            
            DeliveryChangeEntity.Req deliveryRequest = new DeliveryChangeEntity.Req();
            deliveryRequest.setOrderNo("ORDER_" + System.currentTimeMillis());
            deliveryRequest.setSourceOrderNo("SOURCE_" + System.currentTimeMillis());
            deliveryRequest.setShopId("demo_shop_001");
            deliveryRequest.setStatus(Constants.DELIVERY_STATUS_PICKUP);
            deliveryRequest.setRiderName("张骑手");
            deliveryRequest.setRiderPhone("13999999999");
            deliveryRequest.setLongitude("116.397128");
            deliveryRequest.setLatitude("39.916527");
            deliveryRequest.setUpdateTime(System.currentTimeMillis() / 1000);
            
            ApiResponse<Void> deliveryResponse = ApiClientUtils.requestWithEmptyResponse(
                httpClient,
                "POST", 
                Constants.DELIVERY_CHANGE_PATH,
                deliveryRequest,
                "demo_token_12345",
                null
            );
            
            if (deliveryResponse.isSuccess()) {
                System.out.println("   配送状态变更成功");
            } else {
                System.err.println("   配送状态变更失败: " + deliveryResponse.getMessage());
            }
            
            // 3. 链式操作演示
            System.out.println("\n3. 链式操作示例:");
            
            String tokenFromChain = ApiClientUtils.requestWithApiClient(
                    httpClient, "POST", Constants.ACCESS_TOKEN_PATH,
                    tokenRequest, "", null, AccessTokenEntity.Resp.class
                )
                .filter(ApiResponse::isSuccess)                           // 过滤成功响应
                .map(tokenResp -> tokenResp != null ? tokenResp.getToken() : "")  // 提取令牌
                .getDataOrDefault("fallback_token");                      // 获取数据或默认值
            
            System.out.println("   链式操作获取的令牌: " + tokenFromChain);
        }
    }
    
    /**
     * 演示错误处理和重试机制
     */
    private static void demonstrateErrorHandling() throws Exception {
        System.out.println("\n=== 演示3：错误处理和重试机制 ===\n");
        
        // 配置重试参数
        HttpClientConfig config = HttpClientConfig.builder()
            .baseUrl(Constants.TEST_BASE_URL)
            .apiKey("test_app_key")
            .apiSecret("test_app_secret")
            .retryMaxAttempts(3)                    // 最多重试3次
            .retryBaseDelay(Duration.ofMillis(100)) // 100ms基础延迟
            .retryMaxDelay(Duration.ofSeconds(2))   // 最大2秒延迟
            .requestTimeout(Duration.ofSeconds(5))  // 5秒超时
            .build();
        
        try (HttpClientManager httpClient = new HttpClientManager(config)) {
            
            // 1. 业务错误处理
            System.out.println("1. 业务错误处理:");
            
            AccessTokenEntity.Req invalidRequest = new AccessTokenEntity.Req();
            invalidRequest.setGrantType("invalid_grant_type"); // 故意使用无效的grant_type
            
            try {
                ApiResponse<AccessTokenEntity.Resp> response = ApiClientUtils.requestWithApiClient(
                    httpClient, "POST", Constants.ACCESS_TOKEN_PATH,
                    invalidRequest, "", null, AccessTokenEntity.Resp.class
                );
                
                if (response.isError()) {
                    System.out.println("   检测到业务错误:");
                    System.out.println("   错误代码: " + response.getCode());
                    System.out.println("   错误消息: " + response.getMessage());
                }
                
            } catch (Exception e) {
                System.out.println("   捕获异常: " + e.getClass().getSimpleName());
                System.out.println("   异常消息: " + e.getMessage());
            }
            
            // 2. 网络错误处理（模拟）
            System.out.println("\n2. 超时和重试演示:");
            
            HttpClientConfig timeoutConfig = HttpClientConfig.builder()
                .baseUrl("http://httpbin.org/delay/10") // 模拟10秒延迟的服务
                .apiKey("timeout_test_key")
                .apiSecret("timeout_test_secret")
                .requestTimeout(Duration.ofSeconds(2))   // 2秒超时
                .retryMaxAttempts(2)                     // 重试2次
                .build();
            
            try (HttpClientManager timeoutClient = new HttpClientManager(timeoutConfig)) {
                long startTime = System.currentTimeMillis();
                
                try {
                    ApiResponse<Void> response = ApiClientUtils.requestWithEmptyResponse(
                        timeoutClient, "POST", "/delay/10",
                        new HashMap<>(), "test_token", null
                    );
                    
                } catch (Exception e) {
                    long duration = System.currentTimeMillis() - startTime;
                    System.out.println("   请求失败，耗时: " + duration + "ms");
                    System.out.println("   异常类型: " + e.getClass().getSimpleName());
                    System.out.println("   (预期行为：超时并重试)");
                }
            } catch (Exception e) {
                System.out.println("   配置创建失败: " + e.getMessage());
            }
        }
    }
    
    /**
     * 演示自定义请求头和超时设置
     */
    private static void demonstrateCustomHeaders() throws Exception {
        System.out.println("\n=== 演示4：自定义请求头和超时设置 ===\n");
        
        HttpClientConfig config = HttpClientConfig.builder()
            .baseUrl(Constants.TEST_BASE_URL)
            .apiKey("custom_app_key")
            .apiSecret("custom_app_secret")
            .build();
        
        try (HttpClientManager httpClient = new HttpClientManager(config)) {
            
            // 1. 自定义请求头
            System.out.println("1. 使用自定义请求头:");
            
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("X-Request-ID", "REQ_" + System.currentTimeMillis());
            customHeaders.put("X-Client-Version", Variables.getVersion());
            customHeaders.put("X-Environment", "demo");
            customHeaders.put("X-Trace-ID", "TRACE_" + System.nanoTime());
            
            System.out.println("   自定义请求头:");
            customHeaders.forEach((key, value) -> 
                System.out.println("     " + key + ": " + value)
            );
            
            RefreshTokenEntity.Req refreshRequest = new RefreshTokenEntity.Req();
            refreshRequest.setToken("demo_access_token");
            refreshRequest.setRefreshToken("demo_refresh_token");
            
            try {
                ApiResponse<RefreshTokenEntity.Resp> response = ApiClientUtils.requestWithApiClient(
                    httpClient, "POST", Constants.REFRESH_TOKEN_PATH,
                    refreshRequest, "demo_token", customHeaders, RefreshTokenEntity.Resp.class
                );
                
                System.out.println("   请求状态: " + (response.isSuccess() ? "成功" : "失败"));
                System.out.println("   响应代码: " + response.getCode());
                
            } catch (Exception e) {
                System.out.println("   请求异常: " + e.getMessage());
            }
            
            // 2. 快递轨迹回传示例
            System.out.println("\n2. 快递轨迹回传示例:");
            
            LocationChangeEntity.Req locationRequest = new LocationChangeEntity.Req();
            locationRequest.setOrderNo("TRACE_ORDER_001");
            locationRequest.setSourceOrderNo("SOURCE_ORDER_001");
            locationRequest.setShopId("demo_shop_001");
            locationRequest.setTag("快递轨迹");
            
            // 构建轨迹列表
            LocationChangeEntity.Location location1 = new LocationChangeEntity.Location();
            location1.setDescription("[北京市]快件已从【北京分拣中心】发往【上海分拣中心】");
            location1.setCity("北京市");
            location1.setLongitude("116.397128");
            location1.setLatitude("39.916527");
            location1.setStatus(Constants.LOCATION_STATUS_DELIVERING);
            location1.setUpdateTime(System.currentTimeMillis() / 1000 - 3600); // 1小时前
            
            LocationChangeEntity.Location location2 = new LocationChangeEntity.Location();
            location2.setDescription("[上海市]快件已到达【上海分拣中心】");
            location2.setCity("上海市");
            location2.setLongitude("121.473701");
            location2.setLatitude("31.230416");
            location2.setStatus(Constants.LOCATION_STATUS_PICKUP);
            location2.setUpdateTime(System.currentTimeMillis() / 1000); // 现在
            
            locationRequest.setLocations(Arrays.asList(location1, location2));
            
            try {
                ApiResponse<Void> response = ApiClientUtils.requestWithEmptyResponse(
                    httpClient, "POST", Constants.LOCATION_CHANGE_PATH,
                    locationRequest, "demo_token", null
                );
                
                if (response.isSuccess()) {
                    System.out.println("   轨迹回传成功，包含 " + locationRequest.getLocations().size() + " 个轨迹点");
                } else {
                    System.err.println("   轨迹回传失败: " + response.getMessage());
                }
                
            } catch (Exception e) {
                System.err.println("   轨迹回传异常: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== 演示完成 ===");
        System.out.println("更多用法请参考API文档和其他示例文件");
    }
}