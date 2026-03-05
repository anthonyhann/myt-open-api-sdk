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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IChannelSenderV2 接口测试
 * <p>
 * 测试接口的默认方法实现和基本契约，对应 Go SDK 中的 IChannelSender 接口测试
 * </p>
 */
@DisplayName("IChannelSenderV2接口测试")
class IChannelSenderV2Test {

    private TestableChannelSender channelSender;

    @BeforeEach
    void setUp() {
        channelSender = new TestableChannelSender();
    }

    // ==================== 订单推送接口默认方法测试 ====================

    @Test
    @DisplayName("orderCreated默认方法调用测试")
    void testOrderCreatedDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        CreateOrderReq orderReq = new CreateOrderReq();
        orderReq.setOrderNo("test_order_123");
        String token = "test_token";

        // When
        ApiResponse<Void> response = channelSender.orderCreated(token, orderReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderCreated called with token: " + token, response.getMessage());
        assertNull(response.getData());
        
        // 验证参数传递
        assertEquals(token, channelSender.lastToken);
        assertEquals(orderReq, channelSender.lastCreateOrderReq);
        assertNull(channelSender.lastHeaders);
    }

    @Test
    @DisplayName("orderModified默认方法调用测试")
    void testOrderModifiedDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        UpdateOrderReq updateReq = new UpdateOrderReq();
        updateReq.setOrderNo("test_order_456");
        String token = "test_token_2";

        // When
        ApiResponse<Void> response = channelSender.orderModified(token, updateReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderModified called with token: " + token, response.getMessage());
    }

    @Test
    @DisplayName("orderConfirmed默认方法调用测试")
    void testOrderConfirmedDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        OrderConfirmedReq confirmReq = new OrderConfirmedReq();
        confirmReq.setOrderNo("test_order_789");
        String token = "test_token_3";

        // When
        ApiResponse<Void> response = channelSender.orderConfirmed(token, confirmReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderConfirmed called with token: " + token, response.getMessage());
    }

    @Test
    @DisplayName("orderRemind默认方法调用测试")
    void testOrderRemindDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        OrderRemindReq remindReq = new OrderRemindReq();
        remindReq.setOrderNo("test_order_remind");
        String token = "test_token_remind";

        // When
        ApiResponse<Void> response = channelSender.orderRemind(token, remindReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderRemind called with token: " + token, response.getMessage());
    }

    @Test
    @DisplayName("orderCanceled默认方法调用测试")
    void testOrderCanceledDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        OrderCanceledReq cancelReq = new OrderCanceledReq();
        cancelReq.setOrderNo("test_order_cancel");
        String token = "test_token_cancel";

        // When
        ApiResponse<Void> response = channelSender.orderCanceled(token, cancelReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderCanceled called with token: " + token, response.getMessage());
    }

    @Test
    @DisplayName("orderDone默认方法调用测试")
    void testOrderDoneDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        OrderDoneReq doneReq = new OrderDoneReq();
        doneReq.setOrderNo("test_order_done");
        String token = "test_token_done";

        // When
        ApiResponse<Void> response = channelSender.orderDone(token, doneReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderDone called with token: " + token, response.getMessage());
    }

    // ==================== 退款推送接口默认方法测试 ====================

    @Test
    @DisplayName("orderApplyRefund默认方法调用测试")
    void testOrderApplyRefundDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        OrderApplyRefundReq refundReq = new OrderApplyRefundReq();
        refundReq.setOrderNo("test_order_refund");
        String token = "test_token_refund";

        // When
        ApiResponse<Void> response = channelSender.orderApplyRefund(token, refundReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderApplyRefund called with token: " + token, response.getMessage());
    }

    @Test
    @DisplayName("orderRefunded默认方法调用测试")
    void testOrderRefundedDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        OrderRefundedReq refundedReq = new OrderRefundedReq();
        refundedReq.setOrderNo("test_order_refunded");
        String token = "test_token_refunded";

        // When
        ApiResponse<Void> response = channelSender.orderRefunded(token, refundedReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("orderRefunded called with token: " + token, response.getMessage());
    }

    // ==================== 配送推送接口默认方法测试 ====================

    @Test
    @DisplayName("selfDeliveryChange默认方法调用测试")
    void testSelfDeliveryChangeDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        SelfDeliveryChangeReq deliveryReq = new SelfDeliveryChangeReq();
        deliveryReq.setOrderNo("test_order_delivery");
        String token = "test_token_delivery";

        // When
        ApiResponse<Void> response = channelSender.selfDeliveryChange(token, deliveryReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("selfDeliveryChange called with token: " + token, response.getMessage());
    }

    // ==================== 授权管理接口默认方法测试 ====================

    @Test
    @DisplayName("accessToken默认方法调用测试")
    void testAccessTokenDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        AccessTokenReq tokenReq = new AccessTokenReq();
        tokenReq.setGrantType("1");
        String token = "";

        // When
        ApiResponse<AccessTokenResp> response = channelSender.accessToken(token, tokenReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("accessToken called with token: " + token, response.getMessage());
        assertNotNull(response.getData());
        assertEquals("mock_access_token", response.getData().getToken());
    }

    @Test
    @DisplayName("refreshToken默认方法调用测试")
    void testRefreshTokenDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        RefreshTokenReq refreshReq = new RefreshTokenReq();
        refreshReq.setRefreshToken("mock_refresh_token");
        String token = "existing_token";

        // When
        ApiResponse<RefreshTokenResp> response = channelSender.refreshToken(token, refreshReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("refreshToken called with token: " + token, response.getMessage());
        assertNotNull(response.getData());
        assertEquals("new_access_token", response.getData().getToken());
    }

    // ==================== 门店查询接口默认方法测试 ====================

    @Test
    @DisplayName("shopInfo默认方法调用测试")
    void testShopInfoDefaultMethod() throws IOException, JsonProcessingException {
        // Given
        ShopInfoReq shopReq = new ShopInfoReq();
        shopReq.setStoreId("store_123");
        String token = "shop_token";

        // When
        ApiResponse<ShopInfoResp> response = channelSender.shopInfo(token, shopReq);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("shopInfo called with token: " + token, response.getMessage());
        assertNotNull(response.getData());
        assertEquals("测试门店", response.getData().getStoreName());
    }

    // ==================== 带自定义headers的方法测试 ====================

    @Test
    @DisplayName("带自定义headers的orderCreated方法测试")
    void testOrderCreatedWithCustomHeaders() throws IOException, JsonProcessingException {
        // Given
        CreateOrderReq orderReq = new CreateOrderReq();
        orderReq.setOrderNo("test_order_headers");
        String token = "test_token_headers";
        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("X-Custom-Header", "custom_value");
        customHeaders.put("X-Request-ID", "req_123");

        // When
        ApiResponse<Void> response = channelSender.orderCreated(token, orderReq, customHeaders);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertTrue(response.getMessage().contains("with headers"));
        
        // 验证headers传递
        assertEquals(customHeaders, channelSender.lastHeaders);
    }

    @Test
    @DisplayName("accessToken带自定义headers测试")
    void testAccessTokenWithCustomHeaders() throws IOException, JsonProcessingException {
        // Given
        AccessTokenReq tokenReq = new AccessTokenReq();
        tokenReq.setGrantType("1");
        String token = "";
        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("X-Client-Version", "1.0.0");

        // When
        ApiResponse<AccessTokenResp> response = channelSender.accessToken(token, tokenReq, customHeaders);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertTrue(response.getMessage().contains("with headers"));
        assertNotNull(response.getData());
    }

    // ==================== 接口契约测试 ====================

    @Test
    @DisplayName("AutoCloseable接口实现测试")
    void testAutoCloseableInterface() {
        // Given & When & Then
        assertTrue(channelSender instanceof AutoCloseable, "IChannelSenderV2应该继承AutoCloseable");
        
        // 测试close方法可以被调用（通过try-with-resources）
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = channelSender) {
                // 使用sender
                assertNotNull(sender);
            }
        });
    }

    // ==================== 测试用的模拟实现类 ====================

    /**
     * 可测试的ChannelSender实现类，用于测试接口的默认方法
     */
    private static class TestableChannelSender implements IChannelSenderV2 {
        
        // 记录最后一次调用的参数，用于验证
        public String lastToken;
        public CreateOrderReq lastCreateOrderReq;
        public Map<String, String> lastHeaders;

        @Override
        public ApiResponse<Void> orderCreated(String token, CreateOrderReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            this.lastToken = token;
            this.lastCreateOrderReq = data;
            this.lastHeaders = headers;
            
            String message = "orderCreated called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderModified(String token, UpdateOrderReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderModified called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderConfirmed(String token, OrderConfirmedReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderConfirmed called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderRemind(String token, OrderRemindReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderRemind called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderCanceled(String token, OrderCanceledReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderCanceled called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderDone(String token, OrderDoneReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderDone called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderApplyRefund(String token, OrderApplyRefundReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderApplyRefund called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> orderRefunded(String token, OrderRefundedReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "orderRefunded called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<Void> selfDeliveryChange(String token, SelfDeliveryChangeReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "selfDeliveryChange called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            return new ApiResponse<>(200, message, null);
        }

        @Override
        public ApiResponse<AccessTokenResp> accessToken(String token, AccessTokenReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "accessToken called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            
            AccessTokenResp resp = new AccessTokenResp();
            resp.setToken("mock_access_token");
            resp.setRefreshToken("mock_refresh_token");
            resp.setExpiresIn(3600L);
            
            return new ApiResponse<>(200, message, resp);
        }

        @Override
        public ApiResponse<RefreshTokenResp> refreshToken(String token, RefreshTokenReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "refreshToken called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            
            RefreshTokenResp resp = new RefreshTokenResp();
            resp.setToken("new_access_token");
            resp.setRefreshToken("new_refresh_token");
            resp.setExpiresIn(3600L);
            
            return new ApiResponse<>(200, message, resp);
        }

        @Override
        public ApiResponse<ShopInfoResp> shopInfo(String token, ShopInfoReq data, Map<String, String> headers) 
                throws IOException, JsonProcessingException {
            String message = "shopInfo called with token: " + token;
            if (headers != null && !headers.isEmpty()) {
                message += " with headers";
            }
            
            ShopInfoResp resp = new ShopInfoResp();
            resp.setStoreName("测试门店");
            resp.setStoreId("store_123");
            
            return new ApiResponse<>(200, message, resp);
        }

        @Override
        public void close() throws Exception {
            // 模拟close操作
        }
    }
}