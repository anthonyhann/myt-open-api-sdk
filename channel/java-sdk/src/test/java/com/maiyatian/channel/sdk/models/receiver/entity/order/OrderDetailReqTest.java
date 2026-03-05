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
 * OrderDetailReq 查询订单详情请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. 业务逻辑方法测试
 * 5. JSON 序列化/反序列化测试
 * 6. 数据验证测试
 * 7. 边界条件测试
 */
@DisplayName("OrderDetailReq 订单详情请求参数测试")
public class OrderDetailReqTest {

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
            OrderDetailReq req = new OrderDetailReq();
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String orderId = "ORDER_123";
            String shopId = "SHOP_456";
            
            OrderDetailReq req = new OrderDetailReq(orderId, shopId);
            
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
            OrderDetailReq req = new OrderDetailReq();
            String orderId = "TEST_ORDER_789";
            
            req.setOrderId(orderId);
            
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            OrderDetailReq req = new OrderDetailReq();
            String shopId = "TEST_SHOP_123";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            OrderDetailReq req = new OrderDetailReq("order", "shop");
            
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
            OrderDetailReq req1 = new OrderDetailReq("ORDER_123", "SHOP_456");
            OrderDetailReq req2 = new OrderDetailReq("ORDER_123", "SHOP_456");
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            OrderDetailReq req1 = new OrderDetailReq("ORDER_123", "SHOP_456");
            OrderDetailReq req2 = new OrderDetailReq("ORDER_456", "SHOP_456");
            OrderDetailReq req3 = new OrderDetailReq("ORDER_123", "SHOP_789");
            OrderDetailReq req4 = null;
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, req4);
            assertNotEquals(req1, "not an OrderDetailReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            OrderDetailReq req1 = new OrderDetailReq("ORDER_123", "SHOP_456");
            OrderDetailReq req2 = new OrderDetailReq("ORDER_123", "SHOP_456");
            OrderDetailReq req3 = new OrderDetailReq("ORDER_456", "SHOP_456");
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            OrderDetailReq req = new OrderDetailReq("ORDER_123", "SHOP_456");
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_123"));
            assertTrue(result.contains("SHOP_456"));
            assertTrue(result.contains("OrderDetailReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            OrderDetailReq req = new OrderDetailReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("OrderDetailReq"));
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid方法测试 - 有效数据")
        void testIsValid_ValidData() {
            OrderDetailReq req = new OrderDetailReq("ORDER_123", "SHOP_456");
            
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("isValid方法测试 - 无效数据")
        void testIsValid_InvalidData() {
            // 测试null值
            assertFalse(new OrderDetailReq(null, "SHOP_456").isValid());
            assertFalse(new OrderDetailReq("ORDER_123", null).isValid());
            assertFalse(new OrderDetailReq(null, null).isValid());
            
            // 测试空字符串
            assertFalse(new OrderDetailReq("", "SHOP_456").isValid());
            assertFalse(new OrderDetailReq("ORDER_123", "").isValid());
            assertFalse(new OrderDetailReq("", "").isValid());
            
            // 测试只包含空白字符
            assertFalse(new OrderDetailReq("   ", "SHOP_456").isValid());
            assertFalse(new OrderDetailReq("ORDER_123", "   ").isValid());
            assertFalse(new OrderDetailReq("   ", "   ").isValid());
        }

        @Test
        @DisplayName("静态工厂方法of测试")
        void testOfFactoryMethod() {
            String orderId = "FACTORY_ORDER_123";
            String shopId = "FACTORY_SHOP_456";
            
            OrderDetailReq req = OrderDetailReq.of(orderId, shopId);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("静态工厂方法of测试 - null值")
        void testOfFactoryMethod_WithNulls() {
            OrderDetailReq req = OrderDetailReq.of(null, null);
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            OrderDetailReq req = new OrderDetailReq("ORDER_123", "SHOP_456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_123\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\"}";
            
            OrderDetailReq req = objectMapper.readValue(json, OrderDetailReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderDetailReq original = new OrderDetailReq("ORDER_789", "SHOP_123");
            
            String json = objectMapper.writeValueAsString(original);
            OrderDetailReq deserialized = objectMapper.readValue(json, OrderDetailReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            OrderDetailReq req = new OrderDetailReq();
            
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
            OrderDetailReq req = new OrderDetailReq(longString, longString);
            
            assertEquals(longString, req.getOrderId());
            assertEquals(longString, req.getShopId());
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialOrderId = "ORDER_测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            String specialShopId = "SHOP_特殊字符🚀🎉";
            
            OrderDetailReq req = new OrderDetailReq(specialOrderId, specialShopId);
            
            assertEquals(specialOrderId, req.getOrderId());
            assertEquals(specialShopId, req.getShopId());
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            String unicodeOrderId = "ORDER_汉字_العربية_русский";
            String unicodeShopId = "SHOP_🌟⭐✨";
            
            OrderDetailReq req = new OrderDetailReq(unicodeOrderId, unicodeShopId);
            
            assertEquals(unicodeOrderId, req.getOrderId());
            assertEquals(unicodeShopId, req.getShopId());
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("空字符串和空白字符混合测试")
        void testEmptyAndWhitespaceMix() {
            // 测试各种空白字符组合
            String[] invalidValues = {"", " ", "  ", "\t", "\n", "\r", "\r\n", " \t\n "};
            
            for (String invalidValue : invalidValues) {
                OrderDetailReq req1 = new OrderDetailReq(invalidValue, "SHOP_456");
                OrderDetailReq req2 = new OrderDetailReq("ORDER_123", invalidValue);
                
                assertFalse(req1.isValid(), "Should be invalid with orderId: '" + invalidValue + "'");
                assertFalse(req2.isValid(), "Should be invalid with shopId: '" + invalidValue + "'");
            }
        }
    }
}