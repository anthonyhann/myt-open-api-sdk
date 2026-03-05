/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderConfirmReq 确认订单请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. JSON 序列化/反序列化测试
 * 5. 边界条件测试
 */
@DisplayName("OrderConfirmReq 确认订单请求参数测试")
public class OrderConfirmReqTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            OrderConfirmReq req = new OrderConfirmReq();
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String orderId = "ORDER_123";
            String shopId = "SHOP_456";
            
            OrderConfirmReq req = new OrderConfirmReq(orderId, shopId);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("OrderId 设置和获取")
        void testOrderIdGetterSetter() {
            OrderConfirmReq req = new OrderConfirmReq();
            String orderId = "TEST_ORDER_789";
            
            req.setOrderId(orderId);
            
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            OrderConfirmReq req = new OrderConfirmReq();
            String shopId = "TEST_SHOP_123";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            OrderConfirmReq req = new OrderConfirmReq("order", "shop");
            
            req.setOrderId(null);
            req.setShopId(null);
            
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            OrderConfirmReq req1 = new OrderConfirmReq("ORDER_123", "SHOP_456");
            OrderConfirmReq req2 = new OrderConfirmReq("ORDER_123", "SHOP_456");
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            OrderConfirmReq req1 = new OrderConfirmReq("ORDER_123", "SHOP_456");
            OrderConfirmReq req2 = new OrderConfirmReq("ORDER_456", "SHOP_456");
            OrderConfirmReq req3 = new OrderConfirmReq("ORDER_123", "SHOP_789");
            OrderConfirmReq req4 = null;
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, req4);
            assertNotEquals(req1, "not an OrderConfirmReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            OrderConfirmReq req1 = new OrderConfirmReq("ORDER_123", "SHOP_456");
            OrderConfirmReq req2 = new OrderConfirmReq("ORDER_123", "SHOP_456");
            OrderConfirmReq req3 = new OrderConfirmReq("ORDER_456", "SHOP_456");
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            OrderConfirmReq req = new OrderConfirmReq("ORDER_123", "SHOP_456");
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_123"));
            assertTrue(result.contains("SHOP_456"));
            assertTrue(result.contains("OrderConfirmReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            OrderConfirmReq req = new OrderConfirmReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("OrderConfirmReq"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            OrderConfirmReq req = new OrderConfirmReq("ORDER_123", "SHOP_456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_123\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\"}";
            
            OrderConfirmReq req = objectMapper.readValue(json, OrderConfirmReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderConfirmReq original = new OrderConfirmReq("ORDER_789", "SHOP_123");
            
            String json = objectMapper.writeValueAsString(original);
            OrderConfirmReq deserialized = objectMapper.readValue(json, OrderConfirmReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            OrderConfirmReq req = new OrderConfirmReq();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            // Jackson默认不序列化null值，除非特别配置
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("超长字符串测试")
        void testVeryLongStrings() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
            String longString = sb.toString();
            OrderConfirmReq req = new OrderConfirmReq(longString, longString);
            
            assertEquals(longString, req.getOrderId());
            assertEquals(longString, req.getShopId());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialOrderId = "ORDER_测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            String specialShopId = "SHOP_特殊字符🚀🎉";
            
            OrderConfirmReq req = new OrderConfirmReq(specialOrderId, specialShopId);
            
            assertEquals(specialOrderId, req.getOrderId());
            assertEquals(specialShopId, req.getShopId());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            String unicodeOrderId = "ORDER_汉字_العربية_русский";
            String unicodeShopId = "SHOP_🌟⭐✨";
            
            OrderConfirmReq req = new OrderConfirmReq(unicodeOrderId, unicodeShopId);
            
            assertEquals(unicodeOrderId, req.getOrderId());
            assertEquals(unicodeShopId, req.getShopId());
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            OrderConfirmReq req = new OrderConfirmReq("", "");
            
            assertEquals("", req.getOrderId());
            assertEquals("", req.getShopId());
        }

        @Test
        @DisplayName("只包含空白字符测试")
        void testWhitespaceOnlyStrings() {
            String[] whitespaceStrings = {" ", "  ", "\t", "\n", "\r", "\r\n", " \t\n "};
            
            for (String whitespace : whitespaceStrings) {
                OrderConfirmReq req = new OrderConfirmReq(whitespace, whitespace);
                
                assertEquals(whitespace, req.getOrderId());
                assertEquals(whitespace, req.getShopId());
            }
        }

        @Test
        @DisplayName("混合边界条件测试")
        void testMixedEdgeCases() {
            // 测试各种边界条件的组合
            OrderConfirmReq req1 = new OrderConfirmReq(null, "");
            OrderConfirmReq req2 = new OrderConfirmReq("", null);
            OrderConfirmReq req3 = new OrderConfirmReq("ORDER", "   ");
            
            assertNull(req1.getOrderId());
            assertEquals("", req1.getShopId());
            
            assertEquals("", req2.getOrderId());
            assertNull(req2.getShopId());
            
            assertEquals("ORDER", req3.getOrderId());
            assertEquals("   ", req3.getShopId());
        }
    }

    @Nested
    @DisplayName("业务场景测试")
    class BusinessScenarioTests {

        @Test
        @DisplayName("订单确认成功场景测试")
        void testOrderConfirmationSuccessScenario() {
            OrderConfirmReq req = new OrderConfirmReq("ORDER_20240101_001", "SHOP_BEIJING_001");
            
            assertEquals("ORDER_20240101_001", req.getOrderId());
            assertEquals("SHOP_BEIJING_001", req.getShopId());
            
            // 验证对象完整性
            assertNotNull(req);
            assertTrue(req.toString().contains("ORDER_20240101_001"));
            assertTrue(req.toString().contains("SHOP_BEIJING_001"));
        }

        @Test
        @DisplayName("多店铺订单确认场景测试")
        void testMultiShopOrderConfirmationScenario() {
            String[] shopIds = {
                "SHOP_BEIJING_001",
                "SHOP_SHANGHAI_002", 
                "SHOP_GUANGZHOU_003",
                "SHOP_SHENZHEN_004"
            };
            
            for (int i = 0; i < shopIds.length; i++) {
                String orderId = "ORDER_2024010" + (i + 1) + "_00" + (i + 1);
                OrderConfirmReq req = new OrderConfirmReq(orderId, shopIds[i]);
                
                assertEquals(orderId, req.getOrderId());
                assertEquals(shopIds[i], req.getShopId());
            }
        }
    }
}