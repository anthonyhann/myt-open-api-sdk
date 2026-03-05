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
 * OrderAgreeRefundReq 同意退款请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. JSON 序列化/反序列化测试
 * 5. 边界条件测试
 */
@DisplayName("OrderAgreeRefundReq 同意退款请求参数测试")
public class OrderAgreeRefundReqTest {

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
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            
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
            String reason = "顾客要求退款";
            
            OrderAgreeRefundReq req = new OrderAgreeRefundReq(orderId, shopId, refundId, reason);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(refundId, req.getRefundId());
            assertEquals(reason, req.getReason());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("OrderId 设置和获取")
        void testOrderIdGetterSetter() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            String orderId = "TEST_ORDER_789";
            
            req.setOrderId(orderId);
            
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            String shopId = "TEST_SHOP_123";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("RefundId 设置和获取")
        void testRefundIdGetterSetter() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            String refundId = "REFUND_456";
            
            req.setRefundId(refundId);
            
            assertEquals(refundId, req.getRefundId());
        }

        @Test
        @DisplayName("Reason 设置和获取")
        void testReasonGetterSetter() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            String reason = "商品质量问题";
            
            req.setReason(reason);
            
            assertEquals(reason, req.getReason());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("order", "shop", "refund", "reason");
            
            req.setOrderId(null);
            req.setShopId(null);
            req.setRefundId(null);
            req.setReason(null);
            
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getReason());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            OrderAgreeRefundReq req1 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            OrderAgreeRefundReq req2 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            OrderAgreeRefundReq req1 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            OrderAgreeRefundReq req2 = new OrderAgreeRefundReq("ORDER_456", "SHOP_456", "REFUND_789", "顾客要求退款");
            OrderAgreeRefundReq req3 = new OrderAgreeRefundReq("ORDER_123", "SHOP_789", "REFUND_789", "顾客要求退款");
            OrderAgreeRefundReq req4 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_456", "顾客要求退款");
            OrderAgreeRefundReq req5 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "商品质量问题");
            OrderAgreeRefundReq req6 = null;
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, req4);
            assertNotEquals(req1, req5);
            assertNotEquals(req1, req6);
            assertNotEquals(req1, "not an OrderAgreeRefundReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            OrderAgreeRefundReq req1 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            OrderAgreeRefundReq req2 = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            OrderAgreeRefundReq req3 = new OrderAgreeRefundReq("ORDER_456", "SHOP_456", "REFUND_789", "顾客要求退款");
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_123"));
            assertTrue(result.contains("SHOP_456"));
            assertTrue(result.contains("REFUND_789"));
            assertTrue(result.contains("顾客要求退款"));
            assertTrue(result.contains("OrderAgreeRefundReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("OrderAgreeRefundReq"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", "顾客要求退款");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_123\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
            assertTrue(json.contains("\"refund_id\":\"REFUND_789\""));
            assertTrue(json.contains("\"reason\":\"顾客要求退款\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"refund_id\":\"REFUND_789\",\"reason\":\"顾客要求退款\"}";
            
            OrderAgreeRefundReq req = objectMapper.readValue(json, OrderAgreeRefundReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals("REFUND_789", req.getRefundId());
            assertEquals("顾客要求退款", req.getReason());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderAgreeRefundReq original = new OrderAgreeRefundReq("ORDER_789", "SHOP_123", "REFUND_456", "商品质量问题");
            
            String json = objectMapper.writeValueAsString(original);
            OrderAgreeRefundReq deserialized = objectMapper.readValue(json, OrderAgreeRefundReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            // Jackson默认不序列化null值，除非特别配置
        }

        @Test
        @DisplayName("JSON反序列化测试 - 部分字段")
        void testJsonDeserialization_PartialFields() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\"}";
            
            OrderAgreeRefundReq req = objectMapper.readValue(json, OrderAgreeRefundReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getReason());
        }

        @Test
        @DisplayName("JSON反序列化测试 - 仅必要字段")
        void testJsonDeserialization_RequiredFieldsOnly() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\"}";
            
            OrderAgreeRefundReq req = objectMapper.readValue(json, OrderAgreeRefundReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getReason());
        }

        @Test
        @DisplayName("JSON序列化测试 - 带有特殊字符的退款原因")
        void testJsonSerialization_SpecialCharactersInReason() throws JsonProcessingException {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", 
                    "退款原因：商品质量问题，包含特殊字符 @#$%^&*() 和emoji 🎉🚀");
            
            String json = objectMapper.writeValueAsString(req);
            OrderAgreeRefundReq deserialized = objectMapper.readValue(json, OrderAgreeRefundReq.class);
            
            assertEquals(req, deserialized);
            assertTrue(deserialized.getReason().contains("🎉🚀"));
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
            
            OrderAgreeRefundReq req = new OrderAgreeRefundReq(longString, longString, longString, longString);
            
            assertEquals(longString, req.getOrderId());
            assertEquals(longString, req.getShopId());
            assertEquals(longString, req.getRefundId());
            assertEquals(longString, req.getReason());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialOrderId = "ORDER_测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            String specialShopId = "SHOP_特殊字符🚀🎉";
            String specialRefundId = "REFUND_退款单号#123";
            String specialReason = "退款原因：包含特殊字符 & HTML <script>alert('test')</script>";
            
            OrderAgreeRefundReq req = new OrderAgreeRefundReq(specialOrderId, specialShopId, specialRefundId, specialReason);
            
            assertEquals(specialOrderId, req.getOrderId());
            assertEquals(specialShopId, req.getShopId());
            assertEquals(specialRefundId, req.getRefundId());
            assertEquals(specialReason, req.getReason());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            String unicodeOrderId = "ORDER_汉字_العربية_русский";
            String unicodeShopId = "SHOP_🌟⭐✨";
            String unicodeRefundId = "REFUND_日本語_한국어";
            String unicodeReason = "退款原因：多语言测试 Arabic العربية Russian русский Japanese 日本語 Korean 한국어";
            
            OrderAgreeRefundReq req = new OrderAgreeRefundReq(unicodeOrderId, unicodeShopId, unicodeRefundId, unicodeReason);
            
            assertEquals(unicodeOrderId, req.getOrderId());
            assertEquals(unicodeShopId, req.getShopId());
            assertEquals(unicodeRefundId, req.getRefundId());
            assertEquals(unicodeReason, req.getReason());
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("", "", "", "");
            
            assertEquals("", req.getOrderId());
            assertEquals("", req.getShopId());
            assertEquals("", req.getRefundId());
            assertEquals("", req.getReason());
        }

        @Test
        @DisplayName("只包含空白字符测试")
        void testWhitespaceOnlyStrings() {
            String[] whitespaceStrings = {" ", "  ", "\t", "\n", "\r", "\r\n", " \t\n "};
            
            for (String whitespace : whitespaceStrings) {
                OrderAgreeRefundReq req = new OrderAgreeRefundReq(whitespace, whitespace, whitespace, whitespace);
                
                assertEquals(whitespace, req.getOrderId());
                assertEquals(whitespace, req.getShopId());
                assertEquals(whitespace, req.getRefundId());
                assertEquals(whitespace, req.getReason());
            }
        }

        @Test
        @DisplayName("混合边界条件测试")
        void testMixedEdgeCases() {
            // 测试各种边界条件的组合
            OrderAgreeRefundReq req1 = new OrderAgreeRefundReq(null, "", "REFUND_123", "正常退款原因");
            OrderAgreeRefundReq req2 = new OrderAgreeRefundReq("ORDER_123", null, "", "");
            OrderAgreeRefundReq req3 = new OrderAgreeRefundReq("", "SHOP_456", null, "   ");
            
            assertNull(req1.getOrderId());
            assertEquals("", req1.getShopId());
            assertEquals("REFUND_123", req1.getRefundId());
            assertEquals("正常退款原因", req1.getReason());
            
            assertEquals("ORDER_123", req2.getOrderId());
            assertNull(req2.getShopId());
            assertEquals("", req2.getRefundId());
            assertEquals("", req2.getReason());
            
            assertEquals("", req3.getOrderId());
            assertEquals("SHOP_456", req3.getShopId());
            assertNull(req3.getRefundId());
            assertEquals("   ", req3.getReason());
        }

        @Test
        @DisplayName("退款原因内容测试")
        void testRefundReasonContent() {
            // 测试各种常见的退款原因
            String[] reasons = {
                "顾客要求退款",
                "商品质量问题",
                "配送延迟",
                "商品不符合描述",
                "顾客重复下单",
                "商家缺货",
                "价格调整",
                "系统错误",
                "",
                null,
                "包含emoji的退款原因 🎉🚀💰",
                "English refund reason",
                "المبرر العربي للاسترداد",
                "Причина возврата на русском"
            };
            
            for (String reason : reasons) {
                OrderAgreeRefundReq req = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", "REFUND_789", reason);
                assertEquals(reason, req.getReason());
            }
        }
    }

    @Nested
    @DisplayName("业务场景测试")
    class BusinessScenarioTests {

        @Test
        @DisplayName("完整退款流程测试")
        void testCompleteRefundScenario() {
            // 模拟完整的退款同意场景
            String orderId = "ORDER_20240101_123456";
            String shopId = "SHOP_BEIJING_001";
            String refundId = "REFUND_20240101_789";
            String reason = "商品质量不符合标准，顾客要求全额退款";
            
            OrderAgreeRefundReq req = new OrderAgreeRefundReq(orderId, shopId, refundId, reason);
            
            // 验证所有字段都正确设置
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(refundId, req.getRefundId());
            assertEquals(reason, req.getReason());
            
            // 验证对象完整性
            assertNotNull(req);
            assertTrue(req.toString().contains(orderId));
            assertTrue(req.toString().contains(shopId));
            assertTrue(req.toString().contains(refundId));
            assertTrue(req.toString().contains(reason));
        }

        @Test
        @DisplayName("部分退款场景测试")
        void testPartialRefundScenario() {
            // 模拟部分退款场景（没有退款单ID的情况）
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", null, "部分商品退款");
            
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getRefundId());
            assertEquals("部分商品退款", req.getReason());
        }

        @Test
        @DisplayName("简化退款场景测试")
        void testSimplifiedRefundScenario() {
            // 模拟最简化的退款同意场景（仅必要字段）
            OrderAgreeRefundReq req = new OrderAgreeRefundReq("ORDER_123", "SHOP_456", null, null);
            
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getReason());
        }
    }
}