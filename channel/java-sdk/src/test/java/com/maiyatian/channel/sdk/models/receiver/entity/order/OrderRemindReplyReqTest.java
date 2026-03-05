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
 * OrderRemindReplyReq 催单回复请求参数单元测试
 */
@DisplayName("OrderRemindReplyReq 催单回复请求参数测试")
public class OrderRemindReplyReqTest {

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
            OrderRemindReplyReq req = new OrderRemindReplyReq();
            
            assertNotNull(req);
            assertNull(req.getShopId());
            assertNull(req.getRemindId());
            assertNull(req.getReplyType());
            assertNull(req.getReplyContent());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String shopId = "SHOP_456";
            Integer remindId = 123;
            Integer replyType = 1;
            String replyContent = "正在备货中，预计10分钟完成";
            
            OrderRemindReplyReq req = new OrderRemindReplyReq(shopId, remindId, replyType, replyContent);
            
            assertNotNull(req);
            assertEquals(shopId, req.getShopId());
            assertEquals(remindId, req.getRemindId());
            assertEquals(replyType, req.getReplyType());
            assertEquals(replyContent, req.getReplyContent());
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid方法测试 - 有效数据")
        void testIsValid_ValidData() {
            OrderRemindReplyReq req = new OrderRemindReplyReq("SHOP_456", 123, 1, "content");
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("isValid方法测试 - 无效数据")
        void testIsValid_InvalidData() {
            assertFalse(new OrderRemindReplyReq(null, 123, 1, "content").isValid());
            assertFalse(new OrderRemindReplyReq("", 123, 1, "content").isValid());
            assertFalse(new OrderRemindReplyReq("SHOP_456", null, 1, "content").isValid());
        }

        @Test
        @DisplayName("hasReplyType方法测试")
        void testHasReplyType() {
            OrderRemindReplyReq req1 = new OrderRemindReplyReq("SHOP_456", 123, 1, "content");
            assertTrue(req1.hasReplyType());
            
            OrderRemindReplyReq req2 = new OrderRemindReplyReq("SHOP_456", 123, null, "content");
            assertFalse(req2.hasReplyType());
        }

        @Test
        @DisplayName("hasReplyContent方法测试")
        void testHasReplyContent() {
            OrderRemindReplyReq req1 = new OrderRemindReplyReq("SHOP_456", 123, 1, "content");
            assertTrue(req1.hasReplyContent());
            
            OrderRemindReplyReq req2 = new OrderRemindReplyReq("SHOP_456", 123, 1, null);
            assertFalse(req2.hasReplyContent());
            
            OrderRemindReplyReq req3 = new OrderRemindReplyReq("SHOP_456", 123, 1, "");
            assertFalse(req3.hasReplyContent());
        }

        @Test
        @DisplayName("getReplyTypeDescription方法测试")
        void testGetReplyTypeDescription() {
            OrderRemindReplyReq req1 = new OrderRemindReplyReq("SHOP_456", 123, 1, "content");
            assertEquals("备货中", req1.getReplyTypeDescription());
            
            OrderRemindReplyReq req2 = new OrderRemindReplyReq("SHOP_456", 123, 2, "content");
            assertEquals("已送出", req2.getReplyTypeDescription());
            
            OrderRemindReplyReq req3 = new OrderRemindReplyReq("SHOP_456", 123, null, "content");
            assertEquals("未知类型", req3.getReplyTypeDescription());
            
            OrderRemindReplyReq req4 = new OrderRemindReplyReq("SHOP_456", 123, 99, "content");
            assertEquals("未知类型(99)", req4.getReplyTypeDescription());
        }

        @Test
        @DisplayName("getFormattedReply方法测试")
        void testGetFormattedReply() {
            OrderRemindReplyReq req1 = new OrderRemindReplyReq("SHOP_456", 123, 1, "正在准备中");
            String result1 = req1.getFormattedReply();
            assertTrue(result1.contains("回复类型：备货中"));
            assertTrue(result1.contains("回复内容：正在准备中"));
            
            OrderRemindReplyReq req2 = new OrderRemindReplyReq("SHOP_456", 123, 1, null);
            String result2 = req2.getFormattedReply();
            assertTrue(result2.contains("回复类型：备货中"));
            assertFalse(result2.contains("回复内容："));
        }
    }

    @Nested
    @DisplayName("枚举业务逻辑测试")
    class ReplyTypeEnumTests {

        @Test
        @DisplayName("枚举值测试")
        void testEnumValues() {
            assertEquals(1, OrderRemindReplyReq.ReplyType.PREPARING.getCode());
            assertEquals("备货中", OrderRemindReplyReq.ReplyType.PREPARING.getDescription());
            
            assertEquals(2, OrderRemindReplyReq.ReplyType.DISPATCHED.getCode());
            assertEquals("已送出", OrderRemindReplyReq.ReplyType.DISPATCHED.getDescription());
            
            assertEquals(3, OrderRemindReplyReq.ReplyType.WEATHER_ISSUE.getCode());
            assertEquals("天气原因", OrderRemindReplyReq.ReplyType.WEATHER_ISSUE.getDescription());
            
            assertEquals(4, OrderRemindReplyReq.ReplyType.STAFF_SHORTAGE.getCode());
            assertEquals("人手不足", OrderRemindReplyReq.ReplyType.STAFF_SHORTAGE.getDescription());
            
            assertEquals(5, OrderRemindReplyReq.ReplyType.OTHER.getCode());
            assertEquals("其他", OrderRemindReplyReq.ReplyType.OTHER.getDescription());
        }

        @Test
        @DisplayName("fromCode方法测试 - 有效代码")
        void testFromCode_ValidCodes() {
            assertEquals(OrderRemindReplyReq.ReplyType.PREPARING, 
                    OrderRemindReplyReq.ReplyType.fromCode(1));
            assertEquals(OrderRemindReplyReq.ReplyType.DISPATCHED, 
                    OrderRemindReplyReq.ReplyType.fromCode(2));
            assertEquals(OrderRemindReplyReq.ReplyType.WEATHER_ISSUE, 
                    OrderRemindReplyReq.ReplyType.fromCode(3));
            assertEquals(OrderRemindReplyReq.ReplyType.STAFF_SHORTAGE, 
                    OrderRemindReplyReq.ReplyType.fromCode(4));
            assertEquals(OrderRemindReplyReq.ReplyType.OTHER, 
                    OrderRemindReplyReq.ReplyType.fromCode(5));
        }

        @Test
        @DisplayName("fromCode方法测试 - 无效代码")
        void testFromCode_InvalidCodes() {
            assertNull(OrderRemindReplyReq.ReplyType.fromCode(null));
            assertNull(OrderRemindReplyReq.ReplyType.fromCode(0));
            assertNull(OrderRemindReplyReq.ReplyType.fromCode(6));
            assertNull(OrderRemindReplyReq.ReplyType.fromCode(-1));
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试 - Integer版本")
        void testOfFactoryMethod_Integer() {
            OrderRemindReplyReq req = OrderRemindReplyReq.of("SHOP_456", 123, 1, "content");
            
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(123, req.getRemindId());
            assertEquals(1, req.getReplyType());
            assertEquals("content", req.getReplyContent());
        }

        @Test
        @DisplayName("of静态工厂方法测试 - 枚举版本")
        void testOfFactoryMethod_Enum() {
            OrderRemindReplyReq req = OrderRemindReplyReq.of("SHOP_456", 123, 
                    OrderRemindReplyReq.ReplyType.PREPARING, "content");
            
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(123, req.getRemindId());
            assertEquals(1, req.getReplyType());
            assertEquals("content", req.getReplyContent());
        }

        @Test
        @DisplayName("basic静态工厂方法测试")
        void testBasicFactoryMethod() {
            OrderRemindReplyReq req = OrderRemindReplyReq.basic("SHOP_456", 123);
            
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(123, req.getRemindId());
            assertNull(req.getReplyType());
            assertNull(req.getReplyContent());
        }

        @Test
        @DisplayName("withType静态工厂方法测试")
        void testWithTypeFactoryMethod() {
            OrderRemindReplyReq req = OrderRemindReplyReq.withType("SHOP_456", 123, 
                    OrderRemindReplyReq.ReplyType.DISPATCHED);
            
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(123, req.getRemindId());
            assertEquals(2, req.getReplyType());
            assertNull(req.getReplyContent());
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            OrderRemindReplyReq req = new OrderRemindReplyReq("SHOP_456", 123, 1, "正在备货中");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
            assertTrue(json.contains("\"remind_id\":123"));
            assertTrue(json.contains("\"reply_type\":1"));
            assertTrue(json.contains("\"reply_content\":\"正在备货中\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"shop_id\":\"SHOP_456\",\"remind_id\":123,\"reply_type\":1,\"reply_content\":\"正在备货中\"}";
            
            OrderRemindReplyReq req = objectMapper.readValue(json, OrderRemindReplyReq.class);
            
            assertNotNull(req);
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(123, req.getRemindId());
            assertEquals(1, req.getReplyType());
            assertEquals("正在备货中", req.getReplyContent());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderRemindReplyReq original = new OrderRemindReplyReq("SHOP_456", 123, 1, "正在备货中");
            
            String json = objectMapper.writeValueAsString(original);
            OrderRemindReplyReq deserialized = objectMapper.readValue(json, OrderRemindReplyReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - 所有枚举类型")
        void testJsonSerialization_AllEnumTypes() throws JsonProcessingException {
            for (OrderRemindReplyReq.ReplyType type : OrderRemindReplyReq.ReplyType.values()) {
                OrderRemindReplyReq req = OrderRemindReplyReq.of("SHOP_456", 123, type, "测试内容");
                
                String json = objectMapper.writeValueAsString(req);
                OrderRemindReplyReq deserialized = objectMapper.readValue(json, OrderRemindReplyReq.class);
                
                assertEquals(req, deserialized);
                assertEquals(type.getCode(), deserialized.getReplyType());
                assertEquals(type.getDescription(), deserialized.getReplyTypeDescription());
            }
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialShopId = "SHOP_特殊字符🚀🎉";
            String specialContent = "回复内容包含特殊字符：@#$%^&*()，以及emoji 🍕🎯";
            
            OrderRemindReplyReq req = new OrderRemindReplyReq(specialShopId, 123, 1, specialContent);
            
            assertEquals(specialShopId, req.getShopId());
            assertEquals(specialContent, req.getReplyContent());
            assertTrue(req.hasReplyContent());
        }

        @Test
        @DisplayName("数值边界测试")
        void testNumericalBoundaries() {
            OrderRemindReplyReq req = new OrderRemindReplyReq();
            
            // 测试极大值
            req.setRemindId(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, req.getRemindId());
            
            // 测试零值
            req.setRemindId(0);
            assertEquals(0, req.getRemindId());
            
            // 测试负值
            req.setRemindId(-1);
            assertEquals(-1, req.getRemindId());
        }

        @Test
        @DisplayName("混合边界条件测试")
        void testMixedEdgeCases() {
            OrderRemindReplyReq req1 = new OrderRemindReplyReq(null, 123, 1, "content");
            OrderRemindReplyReq req2 = new OrderRemindReplyReq("SHOP_456", null, 1, "content");
            OrderRemindReplyReq req3 = new OrderRemindReplyReq("SHOP_456", 123, null, null);
            
            assertFalse(req1.isValid());
            assertFalse(req2.isValid());
            assertTrue(req3.isValid());
            
            assertFalse(req3.hasReplyType());
            assertFalse(req3.hasReplyContent());
            assertEquals("未知类型", req3.getReplyTypeDescription());
        }
    }

    @Nested
    @DisplayName("业务场景测试")
    class BusinessScenarioTests {

        @Test
        @DisplayName("完整催单回复场景测试")
        void testCompleteRemindReplyScenario() {
            // 模拟备货中回复
            OrderRemindReplyReq req1 = OrderRemindReplyReq.of("SHOP_BEIJING_001", 12345, 
                    OrderRemindReplyReq.ReplyType.PREPARING, "您的订单正在备货中，预计5分钟完成");
            
            assertTrue(req1.isValid());
            assertTrue(req1.hasReplyType());
            assertTrue(req1.hasReplyContent());
            assertEquals("备货中", req1.getReplyTypeDescription());
            
            // 模拟已送出回复
            OrderRemindReplyReq req2 = OrderRemindReplyReq.of("SHOP_SHANGHAI_002", 12346, 
                    OrderRemindReplyReq.ReplyType.DISPATCHED, "您的订单已送出，骑手正在配送中");
            
            assertEquals("已送出", req2.getReplyTypeDescription());
            assertTrue(req2.getFormattedReply().contains("已送出"));
        }

        @Test
        @DisplayName("天气原因延迟场景测试")
        void testWeatherDelayScenario() {
            OrderRemindReplyReq req = OrderRemindReplyReq.of("SHOP_GUANGZHOU_003", 12347, 
                    OrderRemindReplyReq.ReplyType.WEATHER_ISSUE, "由于雨天天气，配送可能延迟，请您谅解");
            
            assertEquals("天气原因", req.getReplyTypeDescription());
            assertTrue(req.hasReplyContent());
            assertTrue(req.getReplyContent().contains("雨天"));
        }

        @Test
        @DisplayName("最简化回复场景测试")
        void testMinimalReplyScenario() {
            OrderRemindReplyReq req = OrderRemindReplyReq.basic("SHOP_456", 123);
            
            assertTrue(req.isValid());
            assertFalse(req.hasReplyType());
            assertFalse(req.hasReplyContent());
            assertEquals("未知类型", req.getReplyTypeDescription());
        }
    }
}