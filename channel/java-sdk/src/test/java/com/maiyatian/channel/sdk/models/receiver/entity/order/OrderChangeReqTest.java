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
 * OrderChangeReq 订单状态变更请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. 业务逻辑方法测试
 * 5. JSON 序列化/反序列化测试
 * 6. 边界条件测试
 */
@DisplayName("OrderChangeReq 订单状态变更请求参数测试")
public class OrderChangeReqTest {

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
            OrderChangeReq req = new OrderChangeReq();
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getMerchantPhone());
            assertNull(req.getStatus());
            assertNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String orderId = "ORDER_123";
            String shopId = "SHOP_456";
            String merchantPhone = "13812345678";
            String status = "CONFIRMED";
            Long updateTime = System.currentTimeMillis() / 1000;
            
            OrderChangeReq req = new OrderChangeReq(orderId, shopId, merchantPhone, status, updateTime);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(merchantPhone, req.getMerchantPhone());
            assertEquals(status, req.getStatus());
            assertEquals(updateTime, req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("OrderId 设置和获取")
        void testOrderIdGetterSetter() {
            OrderChangeReq req = new OrderChangeReq();
            String orderId = "TEST_ORDER_789";
            
            req.setOrderId(orderId);
            
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            OrderChangeReq req = new OrderChangeReq();
            String shopId = "TEST_SHOP_123";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("MerchantPhone 设置和获取")
        void testMerchantPhoneGetterSetter() {
            OrderChangeReq req = new OrderChangeReq();
            String merchantPhone = "13987654321";
            
            req.setMerchantPhone(merchantPhone);
            
            assertEquals(merchantPhone, req.getMerchantPhone());
        }

        @Test
        @DisplayName("Status 设置和获取")
        void testStatusGetterSetter() {
            OrderChangeReq req = new OrderChangeReq();
            String status = "CANCELLED";
            
            req.setStatus(status);
            
            assertEquals(status, req.getStatus());
        }

        @Test
        @DisplayName("UpdateTime 设置和获取")
        void testUpdateTimeGetterSetter() {
            OrderChangeReq req = new OrderChangeReq();
            Long updateTime = 1704067200L; // 2024-01-01 00:00:00
            
            req.setUpdateTime(updateTime);
            
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            OrderChangeReq req = new OrderChangeReq("order", "shop", "phone", "status", 1000L);
            
            req.setOrderId(null);
            req.setShopId(null);
            req.setMerchantPhone(null);
            req.setStatus(null);
            req.setUpdateTime(null);
            
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getMerchantPhone());
            assertNull(req.getStatus());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            OrderChangeReq req1 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            OrderChangeReq req2 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            OrderChangeReq req1 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            OrderChangeReq req2 = new OrderChangeReq("ORDER_456", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            OrderChangeReq req3 = new OrderChangeReq("ORDER_123", "SHOP_789", "13812345678", "CONFIRMED", 1704067200L);
            OrderChangeReq req4 = new OrderChangeReq("ORDER_123", "SHOP_456", "13987654321", "CONFIRMED", 1704067200L);
            OrderChangeReq req5 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CANCELLED", 1704067200L);
            OrderChangeReq req6 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704153600L);
            OrderChangeReq req7 = null;
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, req4);
            assertNotEquals(req1, req5);
            assertNotEquals(req1, req6);
            assertNotEquals(req1, req7);
            assertNotEquals(req1, "not an OrderChangeReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            OrderChangeReq req1 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            OrderChangeReq req2 = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            OrderChangeReq req3 = new OrderChangeReq("ORDER_456", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            OrderChangeReq req = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_123"));
            assertTrue(result.contains("SHOP_456"));
            assertTrue(result.contains("13812345678"));
            assertTrue(result.contains("CONFIRMED"));
            assertTrue(result.contains("1704067200"));
            assertTrue(result.contains("OrderChangeReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            OrderChangeReq req = new OrderChangeReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("OrderChangeReq"));
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("hasValidStatus方法测试 - 有效状态")
        void testHasValidStatus_ValidStatus() {
            OrderChangeReq req1 = new OrderChangeReq();
            req1.setStatus("CONFIRMED");
            assertTrue(req1.hasValidStatus());
            
            OrderChangeReq req2 = new OrderChangeReq();
            req2.setStatus("CANCELLED");
            assertTrue(req2.hasValidStatus());
            
            OrderChangeReq req3 = new OrderChangeReq();
            req3.setStatus("DELIVERED");
            assertTrue(req3.hasValidStatus());
        }

        @Test
        @DisplayName("hasValidStatus方法测试 - 无效状态")
        void testHasValidStatus_InvalidStatus() {
            OrderChangeReq req1 = new OrderChangeReq();
            req1.setStatus(null);
            assertFalse(req1.hasValidStatus());
            
            OrderChangeReq req2 = new OrderChangeReq();
            req2.setStatus("");
            assertFalse(req2.hasValidStatus());
            
            OrderChangeReq req3 = new OrderChangeReq();
            req3.setStatus("   ");
            assertFalse(req3.hasValidStatus());
            
            OrderChangeReq req4 = new OrderChangeReq();
            req4.setStatus("\t\n");
            assertFalse(req4.hasValidStatus());
        }

        @Test
        @DisplayName("hasMerchantPhone方法测试 - 有电话")
        void testHasMerchantPhone_HasPhone() {
            OrderChangeReq req1 = new OrderChangeReq();
            req1.setMerchantPhone("13812345678");
            assertTrue(req1.hasMerchantPhone());
            
            OrderChangeReq req2 = new OrderChangeReq();
            req2.setMerchantPhone("400-123-4567");
            assertTrue(req2.hasMerchantPhone());
            
            OrderChangeReq req3 = new OrderChangeReq();
            req3.setMerchantPhone("+86-138-1234-5678");
            assertTrue(req3.hasMerchantPhone());
        }

        @Test
        @DisplayName("hasMerchantPhone方法测试 - 无电话")
        void testHasMerchantPhone_NoPhone() {
            OrderChangeReq req1 = new OrderChangeReq();
            req1.setMerchantPhone(null);
            assertFalse(req1.hasMerchantPhone());
            
            OrderChangeReq req2 = new OrderChangeReq();
            req2.setMerchantPhone("");
            assertFalse(req2.hasMerchantPhone());
            
            OrderChangeReq req3 = new OrderChangeReq();
            req3.setMerchantPhone("   ");
            assertFalse(req3.hasMerchantPhone());
            
            OrderChangeReq req4 = new OrderChangeReq();
            req4.setMerchantPhone("\t\n");
            assertFalse(req4.hasMerchantPhone());
        }

        @Test
        @DisplayName("hasValidUpdateTime方法测试 - 有效时间")
        void testHasValidUpdateTime_ValidTime() {
            OrderChangeReq req1 = new OrderChangeReq();
            req1.setUpdateTime(1L);
            assertTrue(req1.hasValidUpdateTime());
            
            OrderChangeReq req2 = new OrderChangeReq();
            req2.setUpdateTime(1704067200L); // 2024-01-01
            assertTrue(req2.hasValidUpdateTime());
            
            OrderChangeReq req3 = new OrderChangeReq();
            req3.setUpdateTime(Long.MAX_VALUE);
            assertTrue(req3.hasValidUpdateTime());
        }

        @Test
        @DisplayName("hasValidUpdateTime方法测试 - 无效时间")
        void testHasValidUpdateTime_InvalidTime() {
            OrderChangeReq req1 = new OrderChangeReq();
            req1.setUpdateTime(null);
            assertFalse(req1.hasValidUpdateTime());
            
            OrderChangeReq req2 = new OrderChangeReq();
            req2.setUpdateTime(0L);
            assertFalse(req2.hasValidUpdateTime());
            
            OrderChangeReq req3 = new OrderChangeReq();
            req3.setUpdateTime(-1L);
            assertFalse(req3.hasValidUpdateTime());
            
            OrderChangeReq req4 = new OrderChangeReq();
            req4.setUpdateTime(-1704067200L);
            assertFalse(req4.hasValidUpdateTime());
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            OrderChangeReq req = new OrderChangeReq("ORDER_123", "SHOP_456", "13812345678", "CONFIRMED", 1704067200L);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_123\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
            assertTrue(json.contains("\"merchant_phone\":\"13812345678\""));
            assertTrue(json.contains("\"status\":\"CONFIRMED\""));
            assertTrue(json.contains("\"update_time\":1704067200"));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"merchant_phone\":\"13812345678\"," +
                    "\"status\":\"CONFIRMED\",\"update_time\":1704067200}";
            
            OrderChangeReq req = objectMapper.readValue(json, OrderChangeReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals("13812345678", req.getMerchantPhone());
            assertEquals("CONFIRMED", req.getStatus());
            assertEquals(1704067200L, req.getUpdateTime());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderChangeReq original = new OrderChangeReq("ORDER_789", "SHOP_123", "13987654321", "CANCELLED", 1704153600L);
            
            String json = objectMapper.writeValueAsString(original);
            OrderChangeReq deserialized = objectMapper.readValue(json, OrderChangeReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            OrderChangeReq req = new OrderChangeReq();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            // Jackson默认不序列化null值，除非特别配置
        }

        @Test
        @DisplayName("JSON反序列化测试 - 部分字段")
        void testJsonDeserialization_PartialFields() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"status\":\"CONFIRMED\"}";
            
            OrderChangeReq req = objectMapper.readValue(json, OrderChangeReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getMerchantPhone());
            assertEquals("CONFIRMED", req.getStatus());
            assertNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("JSON反序列化测试 - 仅必要字段")
        void testJsonDeserialization_RequiredFieldsOnly() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"status\":\"CONFIRMED\"}";
            
            OrderChangeReq req = objectMapper.readValue(json, OrderChangeReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals("CONFIRMED", req.getStatus());
            assertNull(req.getMerchantPhone());
            assertNull(req.getUpdateTime());
            
            // 验证业务逻辑方法
            assertTrue(req.hasValidStatus());
            assertFalse(req.hasMerchantPhone());
            assertFalse(req.hasValidUpdateTime());
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
            
            OrderChangeReq req = new OrderChangeReq(longString, longString, longString, longString, 1000L);
            
            assertEquals(longString, req.getOrderId());
            assertEquals(longString, req.getShopId());
            assertEquals(longString, req.getMerchantPhone());
            assertEquals(longString, req.getStatus());
            assertEquals(1000L, req.getUpdateTime());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialOrderId = "ORDER_测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            String specialShopId = "SHOP_特殊字符🚀🎉";
            String specialMerchantPhone = "+86-138-1234-5678#123";
            String specialStatus = "STATUS_特殊状态@测试";
            
            OrderChangeReq req = new OrderChangeReq(specialOrderId, specialShopId, specialMerchantPhone, specialStatus, 1000L);
            
            assertEquals(specialOrderId, req.getOrderId());
            assertEquals(specialShopId, req.getShopId());
            assertEquals(specialMerchantPhone, req.getMerchantPhone());
            assertEquals(specialStatus, req.getStatus());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            String unicodeOrderId = "ORDER_汉字_العربية_русский";
            String unicodeShopId = "SHOP_🌟⭐✨";
            String unicodeMerchantPhone = "电话：138-1234-5678";
            String unicodeStatus = "状态_Japanese_日本語_Korean_한국어";
            
            OrderChangeReq req = new OrderChangeReq(unicodeOrderId, unicodeShopId, unicodeMerchantPhone, unicodeStatus, 1000L);
            
            assertEquals(unicodeOrderId, req.getOrderId());
            assertEquals(unicodeShopId, req.getShopId());
            assertEquals(unicodeMerchantPhone, req.getMerchantPhone());
            assertEquals(unicodeStatus, req.getStatus());
        }

        @Test
        @DisplayName("空字符串和空白字符测试")
        void testEmptyAndWhitespaceStrings() {
            String[] testStrings = {"", " ", "  ", "\t", "\n", "\r", "\r\n", " \t\n "};
            
            for (String testString : testStrings) {
                OrderChangeReq req = new OrderChangeReq(testString, testString, testString, testString, 1000L);
                
                assertEquals(testString, req.getOrderId());
                assertEquals(testString, req.getShopId());
                assertEquals(testString, req.getMerchantPhone());
                assertEquals(testString, req.getStatus());
                
                // 验证业务逻辑
                if (testString.trim().isEmpty()) {
                    assertFalse(req.hasValidStatus(), "Status should be invalid for: '" + testString + "'");
                    assertFalse(req.hasMerchantPhone(), "MerchantPhone should be invalid for: '" + testString + "'");
                } else {
                    assertTrue(req.hasValidStatus(), "Status should be valid for: '" + testString + "'");
                    assertTrue(req.hasMerchantPhone(), "MerchantPhone should be valid for: '" + testString + "'");
                }
            }
        }

        @Test
        @DisplayName("数值边界测试")
        void testNumericalBoundaries() {
            OrderChangeReq req = new OrderChangeReq();
            
            // 测试极大值
            Long maxLong = Long.MAX_VALUE;
            req.setUpdateTime(maxLong);
            assertEquals(maxLong, req.getUpdateTime());
            assertTrue(req.hasValidUpdateTime());
            
            // 测试零值
            req.setUpdateTime(0L);
            assertEquals(0L, req.getUpdateTime());
            assertFalse(req.hasValidUpdateTime()); // 零值被认为是无效的
            
            // 测试正值
            req.setUpdateTime(1L);
            assertEquals(1L, req.getUpdateTime());
            assertTrue(req.hasValidUpdateTime());
            
            // 测试负值
            Long negativeValue = -1L;
            req.setUpdateTime(negativeValue);
            assertEquals(negativeValue, req.getUpdateTime());
            assertFalse(req.hasValidUpdateTime()); // 负值被认为是无效的
        }

        @Test
        @DisplayName("混合边界条件测试")
        void testMixedEdgeCases() {
            // 测试各种边界条件的组合
            OrderChangeReq req1 = new OrderChangeReq(null, "", "13812345678", "   ", 1000L);
            OrderChangeReq req2 = new OrderChangeReq("ORDER_123", null, "", "CONFIRMED", 0L);
            OrderChangeReq req3 = new OrderChangeReq("", "SHOP_456", null, "", null);
            
            // req1 验证
            assertNull(req1.getOrderId());
            assertEquals("", req1.getShopId());
            assertEquals("13812345678", req1.getMerchantPhone());
            assertEquals("   ", req1.getStatus());
            assertEquals(1000L, req1.getUpdateTime());
            assertFalse(req1.hasValidStatus());
            assertTrue(req1.hasMerchantPhone());
            assertTrue(req1.hasValidUpdateTime());
            
            // req2 验证
            assertEquals("ORDER_123", req2.getOrderId());
            assertNull(req2.getShopId());
            assertEquals("", req2.getMerchantPhone());
            assertEquals("CONFIRMED", req2.getStatus());
            assertEquals(0L, req2.getUpdateTime());
            assertTrue(req2.hasValidStatus());
            assertFalse(req2.hasMerchantPhone());
            assertFalse(req2.hasValidUpdateTime());
            
            // req3 验证
            assertEquals("", req3.getOrderId());
            assertEquals("SHOP_456", req3.getShopId());
            assertNull(req3.getMerchantPhone());
            assertEquals("", req3.getStatus());
            assertNull(req3.getUpdateTime());
            assertFalse(req3.hasValidStatus());
            assertFalse(req3.hasMerchantPhone());
            assertFalse(req3.hasValidUpdateTime());
        }
    }

    @Nested
    @DisplayName("业务场景测试")
    class BusinessScenarioTests {

        @Test
        @DisplayName("订单确认场景测试")
        void testOrderConfirmationScenario() {
            OrderChangeReq req = new OrderChangeReq("ORDER_20240101_001", "SHOP_BEIJING_001", 
                    "400-123-4567", "CONFIRMED", 1704067200L);
            
            assertTrue(req.hasValidStatus());
            assertTrue(req.hasMerchantPhone());
            assertTrue(req.hasValidUpdateTime());
        }

        @Test
        @DisplayName("订单取消场景测试")
        void testOrderCancellationScenario() {
            OrderChangeReq req = new OrderChangeReq("ORDER_20240101_002", "SHOP_SHANGHAI_002", 
                    null, "CANCELLED", 1704067800L);
            
            assertTrue(req.hasValidStatus());
            assertFalse(req.hasMerchantPhone());
            assertTrue(req.hasValidUpdateTime());
        }

        @Test
        @DisplayName("订单配送场景测试")
        void testOrderDeliveryScenario() {
            OrderChangeReq req = new OrderChangeReq("ORDER_20240101_003", "SHOP_GUANGZHOU_003", 
                    "13812345678", "DELIVERING", System.currentTimeMillis() / 1000);
            
            assertTrue(req.hasValidStatus());
            assertTrue(req.hasMerchantPhone());
            assertTrue(req.hasValidUpdateTime());
        }

        @Test
        @DisplayName("最简化订单状态变更场景测试")
        void testMinimalOrderChangeScenario() {
            // 仅包含必要字段的场景
            OrderChangeReq req = new OrderChangeReq("ORDER_123", "SHOP_456", null, "PROCESSING", null);
            
            assertTrue(req.hasValidStatus());
            assertFalse(req.hasMerchantPhone());
            assertFalse(req.hasValidUpdateTime());
        }
    }
}