/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderRejectRefundReq 拒绝退款请求参数单元测试
 */
@DisplayName("OrderRejectRefundReq 拒绝退款请求参数测试")
public class OrderRejectRefundReqTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            OrderRejectRefundReq req = new OrderRejectRefundReq();
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getReason());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String orderId = "ORDER_123";
            String shopId = "SHOP_456";
            String refundId = "REFUND_789";
            String reason = "商品已发货无法退款";
            
            OrderRejectRefundReq req = new OrderRejectRefundReq(orderId, shopId, refundId, reason);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(refundId, req.getRefundId());
            assertEquals(reason, req.getReason());
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid方法测试 - 有效数据")
        void testIsValid_ValidData() {
            OrderRejectRefundReq req = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "reason");
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("isValid方法测试 - 无效数据")
        void testIsValid_InvalidData() {
            assertFalse(new OrderRejectRefundReq(null, "SHOP_456", "REFUND_789", "reason").isValid());
            assertFalse(new OrderRejectRefundReq("", "SHOP_456", "REFUND_789", "reason").isValid());
            assertFalse(new OrderRejectRefundReq("ORDER_123", null, "REFUND_789", "reason").isValid());
            assertFalse(new OrderRejectRefundReq("ORDER_123", "", "REFUND_789", "reason").isValid());
        }

        @Test
        @DisplayName("hasRefundId方法测试")
        void testHasRefundId() {
            OrderRejectRefundReq req1 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "reason");
            assertTrue(req1.hasRefundId());
            
            OrderRejectRefundReq req2 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", null, "reason");
            assertFalse(req2.hasRefundId());
            
            OrderRejectRefundReq req3 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "", "reason");
            assertFalse(req3.hasRefundId());
        }

        @Test
        @DisplayName("hasReason方法测试")
        void testHasReason() {
            OrderRejectRefundReq req1 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "reason");
            assertTrue(req1.hasReason());
            
            OrderRejectRefundReq req2 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", null);
            assertFalse(req2.hasReason());
            
            OrderRejectRefundReq req3 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "");
            assertFalse(req3.hasReason());
        }

        @Test
        @DisplayName("getFormattedReason方法测试")
        void testGetFormattedReason() {
            OrderRejectRefundReq req1 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "商品质量问题");
            assertEquals("拒绝退款原因：商品质量问题", req1.getFormattedReason());
            
            OrderRejectRefundReq req2 = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", null);
            assertEquals("拒绝退款原因：未提供", req2.getFormattedReason());
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试")
        void testOfFactoryMethod() {
            OrderRejectRefundReq req = OrderRejectRefundReq.of("ORDER_123", "SHOP_456", "REFUND_789", "reason");
            
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals("REFUND_789", req.getRefundId());
            assertEquals("reason", req.getReason());
        }

        @Test
        @DisplayName("basic静态工厂方法测试")
        void testBasicFactoryMethod() {
            OrderRejectRefundReq req = OrderRejectRefundReq.basic("ORDER_123", "SHOP_456", "reason");
            
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getRefundId());
            assertEquals("reason", req.getReason());
        }

        @Test
        @DisplayName("minimal静态工厂方法测试")
        void testMinimalFactoryMethod() {
            OrderRejectRefundReq req = OrderRejectRefundReq.minimal("ORDER_123", "SHOP_456");
            
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getReason());
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderRejectRefundReq original = new OrderRejectRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "商品质量问题");
            
            String json = objectMapper.writeValueAsString(original);
            OrderRejectRefundReq deserialized = objectMapper.readValue(json, OrderRejectRefundReq.class);
            
            assertEquals(original, deserialized);
        }
    }
}