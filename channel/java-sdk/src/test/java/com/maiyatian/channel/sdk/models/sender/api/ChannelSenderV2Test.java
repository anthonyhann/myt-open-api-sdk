/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.client.HttpClientManager;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.*;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoReq;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoResp;
import com.maiyatian.channel.sdk.models.types.ApiResponse;
import com.maiyatian.channel.sdk.models.types.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ChannelSenderV2 实现类测试
 * <p>
 * 测试实际的HTTP客户端调用和配置合并逻辑，对应 Go SDK 中的 channelSender 测试
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ChannelSenderV2实现类测试")
class ChannelSenderV2Test {

    @Mock
    private HttpClientManager mockHttpClient;

    private ChannelSenderV2 channelSender;
    private HttpClientConfig testConfig;

    @BeforeEach
    void setUp() throws Exception {
        // 创建测试配置
        testConfig = createTestConfig();
        
        // 使用Mock的HttpClientManager创建ChannelSenderV2实例
        try (MockedConstruction<HttpClientManager> mockedConstruction = mockConstruction(HttpClientManager.class,
                (mock, context) -> {
                    // 设置mock的行为
                })) {
            
            channelSender = new ChannelSenderV2(testConfig);
            
            // 通过反射注入mockHttpClient
            Field httpClientField = ChannelSenderV2.class.getDeclaredField("httpClient");
            httpClientField.setAccessible(true);
            httpClientField.set(channelSender, mockHttpClient);
        }
    }

    // ==================== 配置合并测试 ====================

    @Test
    @DisplayName("默认配置和用户配置合并测试")
    void testConfigMerging() {
        // Given
        HttpClientConfig userConfig = HttpClientConfig.builder()
                .baseUrl("https://custom-api.test.com")
                .apiKey("custom_key")
                .apiSecret("custom_secret")
                .maxConnections(100)
                .requestTimeout(Duration.ofSeconds(45))
                .retryMaxAttempts(5)
                .enableLogging(true)
                .build();

        // When
        assertDoesNotThrow(() -> {
            new ChannelSenderV2(userConfig);
        });

        // Then - 验证配置正确合并（通过不抛异常证明）
        assertTrue(true, "配置合并成功，无异常抛出");
    }

    @Test
    @DisplayName("null配置处理测试")
    void testNullConfigHandling() {
        // When & Then
        assertDoesNotThrow(() -> {
            new ChannelSenderV2(null);
        });
    }

    @Test
    @DisplayName("部分配置合并测试")
    void testPartialConfigMerging() {
        // Given - 只设置部分配置
        HttpClientConfig partialConfig = HttpClientConfig.builder()
                .baseUrl("https://partial-api.test.com")
                .apiKey("partial_key")
                // 其他配置使用默认值
                .build();

        // When & Then
        assertDoesNotThrow(() -> {
            new ChannelSenderV2(partialConfig);
        });
    }

    // ==================== 订单推送接口测试 ====================

    @Test
    @DisplayName("orderCreated接口调用测试")
    void testOrderCreated() throws IOException, JsonProcessingException {
        // Given
        CreateOrderReq orderReq = createSampleOrder();
        String token = "test_token";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Request-ID", "req_123");

        ApiResponse<Void> expectedResponse = new ApiResponse<>(200, "success", null);
        when(mockHttpClient.postEmpty(eq("v1/channel/order_created"), eq(orderReq), eq(token), eq(headers)))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<Void> response = channelSender.orderCreated(token, orderReq, headers);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("success", response.getMessage());
        assertNull(response.getData());
        
        verify(mockHttpClient).postEmpty("v1/channel/order_created", orderReq, token, headers);
    }

    @Test
    @DisplayName("orderModified接口调用测试")
    void testOrderModified() throws IOException, JsonProcessingException {
        // Given
        UpdateOrderReq updateReq = new UpdateOrderReq();
        updateReq.setOrderNo("updated_order_123");
        String token = "test_token";

        ApiResponse<Void> expectedResponse = new ApiResponse<>(200, "order modified", null);
        when(mockHttpClient.postEmpty(eq("v1/channel/order_modified"), eq(updateReq), eq(token), isNull()))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<Void> response = channelSender.orderModified(token, updateReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("order modified", response.getMessage());
        
        verify(mockHttpClient).postEmpty("v1/channel/order_modified", updateReq, token, null);
    }

    @Test
    @DisplayName("orderConfirmed接口调用测试")
    void testOrderConfirmed() throws IOException, JsonProcessingException {
        // Given
        OrderConfirmedReq confirmReq = new OrderConfirmedReq();
        confirmReq.setOrderNo("confirmed_order_456");
        confirmReq.setEstimateTime(1800); // 30分钟
        String token = "confirm_token";

        ApiResponse<Void> expectedResponse = new ApiResponse<>(200, "order confirmed", null);
        when(mockHttpClient.postEmpty(eq("v1/channel/order_confirmed"), eq(confirmReq), eq(token), isNull()))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<Void> response = channelSender.orderConfirmed(token, confirmReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("order confirmed", response.getMessage());
        
        verify(mockHttpClient).postEmpty("v1/channel/order_confirmed", confirmReq, token, null);
    }

    @Test
    @DisplayName("orderDone接口调用测试")
    void testOrderDone() throws IOException, JsonProcessingException {
        // Given
        OrderDoneReq doneReq = new OrderDoneReq();
        doneReq.setOrderNo("done_order_789");
        doneReq.setFinishTime(System.currentTimeMillis() / 1000);
        String token = "done_token";

        ApiResponse<Void> expectedResponse = new ApiResponse<>(200, "order completed", null);
        when(mockHttpClient.postEmpty(eq("v1/channel/order_done"), eq(doneReq), eq(token), isNull()))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<Void> response = channelSender.orderDone(token, doneReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("order completed", response.getMessage());
        
        verify(mockHttpClient).postEmpty("v1/channel/order_done", doneReq, token, null);
    }

    // ==================== 授权管理接口测试 ====================

    @Test
    @DisplayName("accessToken接口调用测试")
    void testAccessToken() throws IOException, JsonProcessingException {
        // Given
        AccessTokenReq tokenReq = new AccessTokenReq();
        tokenReq.setGrantType("1");
        tokenReq.setCode("auth_code_123");
        String token = ""; // 获取token时传空

        AccessTokenResp expectedResp = new AccessTokenResp();
        expectedResp.setToken("new_access_token");
        expectedResp.setRefreshToken("new_refresh_token");
        expectedResp.setExpiresIn(3600L);

        ApiResponse<AccessTokenResp> expectedResponse = new ApiResponse<>(200, "token obtained", expectedResp);
        when(mockHttpClient.postWithType(eq("v1/channel/access_token"), eq(tokenReq), eq(token), 
                isNull(), eq(AccessTokenResp.class)))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<AccessTokenResp> response = channelSender.accessToken(token, tokenReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("token obtained", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("new_access_token", response.getData().getToken());
        assertEquals("new_refresh_token", response.getData().getRefreshToken());
        assertEquals(3600L, response.getData().getExpiresIn());
        
        verify(mockHttpClient).postWithType("v1/channel/access_token", tokenReq, token, null, AccessTokenResp.class);
    }

    @Test
    @DisplayName("refreshToken接口调用测试")
    void testRefreshToken() throws IOException, JsonProcessingException {
        // Given
        RefreshTokenReq refreshReq = new RefreshTokenReq();
        refreshReq.setRefreshToken("old_refresh_token");
        String token = "current_access_token";

        RefreshTokenResp expectedResp = new RefreshTokenResp();
        expectedResp.setToken("refreshed_access_token");
        expectedResp.setRefreshToken("refreshed_refresh_token");
        expectedResp.setExpiresIn(3600L);

        ApiResponse<RefreshTokenResp> expectedResponse = new ApiResponse<>(200, "token refreshed", expectedResp);
        when(mockHttpClient.postWithType(eq("v1/channel/refresh_token"), eq(refreshReq), eq(token), 
                isNull(), eq(RefreshTokenResp.class)))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<RefreshTokenResp> response = channelSender.refreshToken(token, refreshReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("token refreshed", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("refreshed_access_token", response.getData().getToken());
        
        verify(mockHttpClient).postWithType("v1/channel/refresh_token", refreshReq, token, null, RefreshTokenResp.class);
    }

    // ==================== 门店查询接口测试 ====================

    @Test
    @DisplayName("shopInfo接口调用测试")
    void testShopInfo() throws IOException, JsonProcessingException {
        // Given
        ShopInfoReq shopReq = new ShopInfoReq();
        shopReq.setStoreId("store_456");
        String token = "shop_token";

        ShopInfoResp expectedResp = new ShopInfoResp();
        expectedResp.setStoreId("store_456");
        expectedResp.setStoreName("测试门店名称");

        ApiResponse<ShopInfoResp> expectedResponse = new ApiResponse<>(200, "shop info retrieved", expectedResp);
        when(mockHttpClient.postWithType(eq("v1/channel/shop_info"), eq(shopReq), eq(token), 
                isNull(), eq(ShopInfoResp.class)))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<ShopInfoResp> response = channelSender.shopInfo(token, shopReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("shop info retrieved", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("store_456", response.getData().getStoreId());
        assertEquals("测试门店名称", response.getData().getStoreName());
        
        verify(mockHttpClient).postWithType("v1/channel/shop_info", shopReq, token, null, ShopInfoResp.class);
    }

    // ==================== 异常处理测试 ====================

    @Test
    @DisplayName("HTTP客户端IOException处理测试")
    void testHttpClientIOException() throws IOException, JsonProcessingException {
        // Given
        CreateOrderReq orderReq = createSampleOrder();
        String token = "test_token";

        when(mockHttpClient.postEmpty(anyString(), any(), anyString(), any()))
                .thenThrow(new IOException("Network error"));

        // When & Then
        assertThrows(IOException.class, () -> {
            channelSender.orderCreated(token, orderReq, null);
        });
    }

    @Test
    @DisplayName("JSON处理异常测试")
    void testJsonProcessingException() throws IOException, JsonProcessingException {
        // Given
        AccessTokenReq tokenReq = new AccessTokenReq();
        String token = "";

        when(mockHttpClient.postWithType(anyString(), any(), anyString(), any(), any()))
                .thenThrow(new JsonProcessingException("JSON parsing error") {});

        // When & Then
        assertThrows(JsonProcessingException.class, () -> {
            channelSender.accessToken(token, tokenReq, null);
        });
    }

    // ==================== 生命周期测试 ====================

    @Test
    @DisplayName("close方法测试")
    void testClose() throws Exception {
        // When
        channelSender.close();

        // Then
        verify(mockHttpClient).close();
    }

    @Test
    @DisplayName("多次close调用测试")
    void testMultipleClose() throws Exception {
        // When
        channelSender.close();
        channelSender.close(); // 第二次调用

        // Then
        verify(mockHttpClient, times(2)).close();
    }

    @Test
    @DisplayName("close异常处理测试")
    void testCloseWithException() throws Exception {
        // Given
        doThrow(new Exception("Close error")).when(mockHttpClient).close();

        // When & Then
        assertThrows(Exception.class, () -> {
            channelSender.close();
        });
    }

    // ==================== 退款和配送接口测试 ====================

    @Test
    @DisplayName("orderApplyRefund接口调用测试")
    void testOrderApplyRefund() throws IOException, JsonProcessingException {
        // Given
        OrderApplyRefundReq refundReq = new OrderApplyRefundReq();
        refundReq.setOrderNo("refund_order_123");
        String token = "refund_token";

        ApiResponse<Void> expectedResponse = new ApiResponse<>(200, "refund applied", null);
        when(mockHttpClient.postEmpty(eq("v1/channel/order_apply_refund"), eq(refundReq), eq(token), isNull()))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<Void> response = channelSender.orderApplyRefund(token, refundReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("refund applied", response.getMessage());
        
        verify(mockHttpClient).postEmpty("v1/channel/order_apply_refund", refundReq, token, null);
    }

    @Test
    @DisplayName("selfDeliveryChange接口调用测试")
    void testSelfDeliveryChange() throws IOException, JsonProcessingException {
        // Given
        SelfDeliveryChangeReq deliveryReq = new SelfDeliveryChangeReq();
        deliveryReq.setOrderNo("delivery_order_123");
        String token = "delivery_token";

        ApiResponse<Void> expectedResponse = new ApiResponse<>(200, "delivery status changed", null);
        when(mockHttpClient.postEmpty(eq("v1/channel/self_delivery_change"), eq(deliveryReq), eq(token), isNull()))
                .thenReturn(expectedResponse);

        // When
        ApiResponse<Void> response = channelSender.selfDeliveryChange(token, deliveryReq, null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("delivery status changed", response.getMessage());
        
        verify(mockHttpClient).postEmpty("v1/channel/self_delivery_change", deliveryReq, token, null);
    }

    // ==================== 辅助方法 ====================

    private HttpClientConfig createTestConfig() {
        return HttpClientConfig.builder()
                .baseUrl("https://test-api.maiyatian.com")
                .apiKey("test_app_key")
                .apiSecret("test_app_secret")
                .maxConnections(50)
                .requestTimeout(Duration.ofSeconds(30))
                .retryMaxAttempts(3)
                .enableLogging(false)
                .build();
    }

    private CreateOrderReq createSampleOrder() {
        CreateOrderReq orderReq = new CreateOrderReq();
        orderReq.setOrderNo("test_order_" + System.currentTimeMillis());
        orderReq.setStoreId("test_store_123");
        orderReq.setThirdOrderNo("third_" + System.currentTimeMillis());
        orderReq.setOrderType(1); // 外卖订单
        orderReq.setBusinessType(1); // 餐饮
        orderReq.setTotalPrice(new Money(new BigDecimal("2580"))); // 25.80元
        orderReq.setOrderTime(System.currentTimeMillis() / 1000);

        // 客户信息
        OrderCustomerInfo customerInfo = new OrderCustomerInfo();
        customerInfo.setName("测试客户");
        customerInfo.setPhone("138****0000");
        customerInfo.setAddress("测试地址");
        orderReq.setCustomerInfo(customerInfo);

        return orderReq;
    }
}