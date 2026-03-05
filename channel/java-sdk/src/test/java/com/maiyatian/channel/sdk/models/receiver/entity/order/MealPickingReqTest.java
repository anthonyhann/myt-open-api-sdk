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
 * MealPickingReq 订单出餐状态更新请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. 业务逻辑方法测试（枚举相关）
 * 5. JSON 序列化/反序列化测试
 * 6. 静态工厂方法测试
 * 7. 边界条件测试
 */
@DisplayName("MealPickingReq 订单出餐状态更新请求参数测试")
public class MealPickingReqTest {

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
            MealPickingReq req = new MealPickingReq();
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getType());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String orderId = "ORDER_123";
            String shopId = "SHOP_456";
            Long type = 1L;
            
            MealPickingReq req = new MealPickingReq(orderId, shopId, type);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(type, req.getType());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("OrderId 设置和获取")
        void testOrderIdGetterSetter() {
            MealPickingReq req = new MealPickingReq();
            String orderId = "TEST_ORDER_789";
            
            req.setOrderId(orderId);
            
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            MealPickingReq req = new MealPickingReq();
            String shopId = "TEST_SHOP_123";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("Type 设置和获取")
        void testTypeGetterSetter() {
            MealPickingReq req = new MealPickingReq();
            Long type = 2L;
            
            req.setType(type);
            
            assertEquals(type, req.getType());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            MealPickingReq req = new MealPickingReq("order", "shop", 1L);
            
            req.setOrderId(null);
            req.setShopId(null);
            req.setType(null);
            
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getType());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            MealPickingReq req1 = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            MealPickingReq req2 = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            MealPickingReq req1 = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            MealPickingReq req2 = new MealPickingReq("ORDER_456", "SHOP_456", 1L);
            MealPickingReq req3 = new MealPickingReq("ORDER_123", "SHOP_789", 1L);
            MealPickingReq req4 = new MealPickingReq("ORDER_123", "SHOP_456", 2L);
            MealPickingReq req5 = null;
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, req4);
            assertNotEquals(req1, req5);
            assertNotEquals(req1, "not a MealPickingReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            MealPickingReq req1 = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            MealPickingReq req2 = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            MealPickingReq req3 = new MealPickingReq("ORDER_456", "SHOP_456", 1L);
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            MealPickingReq req = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_123"));
            assertTrue(result.contains("SHOP_456"));
            assertTrue(result.contains("1"));
            assertTrue(result.contains("MealPickingReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            MealPickingReq req = new MealPickingReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("MealPickingReq"));
        }
    }

    @Nested
    @DisplayName("枚举业务逻辑测试")
    class MealPickingTypeEnumTests {

        @Test
        @DisplayName("枚举值测试")
        void testEnumValues() {
            assertEquals(0L, MealPickingReq.MealPickingType.WAITING.getCode());
            assertEquals("等待拣货", MealPickingReq.MealPickingType.WAITING.getDescription());
            
            assertEquals(1L, MealPickingReq.MealPickingType.PICKING.getCode());
            assertEquals("拣货中", MealPickingReq.MealPickingType.PICKING.getDescription());
            
            assertEquals(2L, MealPickingReq.MealPickingType.COMPLETED.getCode());
            assertEquals("拣货完成", MealPickingReq.MealPickingType.COMPLETED.getDescription());
        }

        @Test
        @DisplayName("fromCode方法测试 - 有效代码")
        void testFromCode_ValidCodes() {
            assertEquals(MealPickingReq.MealPickingType.WAITING, 
                    MealPickingReq.MealPickingType.fromCode(0L));
            assertEquals(MealPickingReq.MealPickingType.PICKING, 
                    MealPickingReq.MealPickingType.fromCode(1L));
            assertEquals(MealPickingReq.MealPickingType.COMPLETED, 
                    MealPickingReq.MealPickingType.fromCode(2L));
        }

        @Test
        @DisplayName("fromCode方法测试 - 无效代码")
        void testFromCode_InvalidCodes() {
            assertNull(MealPickingReq.MealPickingType.fromCode(null));
            assertNull(MealPickingReq.MealPickingType.fromCode(-1L));
            assertNull(MealPickingReq.MealPickingType.fromCode(3L));
            assertNull(MealPickingReq.MealPickingType.fromCode(100L));
        }

        @Test
        @DisplayName("枚举完整性测试")
        void testEnumCompleteness() {
            MealPickingReq.MealPickingType[] types = MealPickingReq.MealPickingType.values();
            assertEquals(3, types.length);
            
            // 确保所有枚举值都有对应的fromCode结果
            for (MealPickingReq.MealPickingType type : types) {
                assertEquals(type, MealPickingReq.MealPickingType.fromCode(type.getCode()));
            }
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            MealPickingReq req = new MealPickingReq("ORDER_123", "SHOP_456", 1L);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_123\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
            assertTrue(json.contains("\"type\":1"));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"type\":1}";
            
            MealPickingReq req = objectMapper.readValue(json, MealPickingReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(1L, req.getType());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            MealPickingReq original = new MealPickingReq("ORDER_789", "SHOP_123", 2L);
            
            String json = objectMapper.writeValueAsString(original);
            MealPickingReq deserialized = objectMapper.readValue(json, MealPickingReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            MealPickingReq req = new MealPickingReq();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            // Jackson默认不序列化null值，除非特别配置
        }

        @Test
        @DisplayName("JSON反序列化测试 - 部分字段")
        void testJsonDeserialization_PartialFields() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\"}";
            
            MealPickingReq req = objectMapper.readValue(json, MealPickingReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertNull(req.getType());
        }

        @Test
        @DisplayName("JSON序列化测试 - 所有枚举类型")
        void testJsonSerialization_AllEnumTypes() throws JsonProcessingException {
            // 测试所有枚举类型的序列化
            MealPickingReq waitingReq = new MealPickingReq("ORDER_1", "SHOP_1", 0L);
            MealPickingReq pickingReq = new MealPickingReq("ORDER_2", "SHOP_2", 1L);
            MealPickingReq completedReq = new MealPickingReq("ORDER_3", "SHOP_3", 2L);
            
            String waitingJson = objectMapper.writeValueAsString(waitingReq);
            String pickingJson = objectMapper.writeValueAsString(pickingReq);
            String completedJson = objectMapper.writeValueAsString(completedReq);
            
            assertTrue(waitingJson.contains("\"type\":0"));
            assertTrue(pickingJson.contains("\"type\":1"));
            assertTrue(completedJson.contains("\"type\":2"));
            
            // 测试反序列化
            assertEquals(waitingReq, objectMapper.readValue(waitingJson, MealPickingReq.class));
            assertEquals(pickingReq, objectMapper.readValue(pickingJson, MealPickingReq.class));
            assertEquals(completedReq, objectMapper.readValue(completedJson, MealPickingReq.class));
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
            
            MealPickingReq req = new MealPickingReq(longString, longString, 1L);
            
            assertEquals(longString, req.getOrderId());
            assertEquals(longString, req.getShopId());
            assertEquals(1L, req.getType());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialOrderId = "ORDER_测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            String specialShopId = "SHOP_特殊字符🚀🎉";
            
            MealPickingReq req = new MealPickingReq(specialOrderId, specialShopId, 1L);
            
            assertEquals(specialOrderId, req.getOrderId());
            assertEquals(specialShopId, req.getShopId());
            assertEquals(1L, req.getType());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            String unicodeOrderId = "ORDER_汉字_العربية_русский";
            String unicodeShopId = "SHOP_🌟⭐✨";
            
            MealPickingReq req = new MealPickingReq(unicodeOrderId, unicodeShopId, 2L);
            
            assertEquals(unicodeOrderId, req.getOrderId());
            assertEquals(unicodeShopId, req.getShopId());
            assertEquals(2L, req.getType());
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            MealPickingReq req = new MealPickingReq("", "", 0L);
            
            assertEquals("", req.getOrderId());
            assertEquals("", req.getShopId());
            assertEquals(0L, req.getType());
        }

        @Test
        @DisplayName("数值边界测试")
        void testNumericalBoundaries() {
            MealPickingReq req = new MealPickingReq();
            
            // 测试极大值
            Long maxLong = Long.MAX_VALUE;
            req.setType(maxLong);
            assertEquals(maxLong, req.getType());
            
            // 测试零值
            req.setType(0L);
            assertEquals(0L, req.getType());
            
            // 测试负值
            Long negativeValue = -1L;
            req.setType(negativeValue);
            assertEquals(negativeValue, req.getType());
        }

        @Test
        @DisplayName("枚举边界测试")
        void testEnumBoundaries() {
            // 测试枚举定义的最小值和最大值
            assertEquals(0L, MealPickingReq.MealPickingType.WAITING.getCode());
            assertEquals(2L, MealPickingReq.MealPickingType.COMPLETED.getCode());
            
            // 测试超出范围的值
            assertNull(MealPickingReq.MealPickingType.fromCode(-1L));
            assertNull(MealPickingReq.MealPickingType.fromCode(3L));
            
            // 测试实际使用中的边界值
            MealPickingReq req1 = new MealPickingReq("ORDER_1", "SHOP_1", -1L);
            MealPickingReq req2 = new MealPickingReq("ORDER_2", "SHOP_2", 99L);
            
            assertEquals(-1L, req1.getType());
            assertEquals(99L, req2.getType());
        }

        @Test
        @DisplayName("混合边界条件测试")
        void testMixedEdgeCases() {
            // 测试各种边界条件的组合
            MealPickingReq req1 = new MealPickingReq(null, "", Long.MAX_VALUE);
            MealPickingReq req2 = new MealPickingReq("", null, Long.MIN_VALUE);
            MealPickingReq req3 = new MealPickingReq("ORDER", "SHOP", null);
            
            assertNull(req1.getOrderId());
            assertEquals("", req1.getShopId());
            assertEquals(Long.MAX_VALUE, req1.getType());
            
            assertEquals("", req2.getOrderId());
            assertNull(req2.getShopId());
            assertEquals(Long.MIN_VALUE, req2.getType());
            
            assertEquals("ORDER", req3.getOrderId());
            assertEquals("SHOP", req3.getShopId());
            assertNull(req3.getType());
        }
    }

    @Nested
    @DisplayName("枚举描述和业务逻辑测试")
    class EnumDescriptionTests {

        @Test
        @DisplayName("枚举描述正确性测试")
        void testEnumDescriptions() {
            assertEquals("等待拣货", MealPickingReq.MealPickingType.WAITING.getDescription());
            assertEquals("拣货中", MealPickingReq.MealPickingType.PICKING.getDescription());
            assertEquals("拣货完成", MealPickingReq.MealPickingType.COMPLETED.getDescription());
        }

        @Test
        @DisplayName("枚举代码唯一性测试")
        void testEnumCodeUniqueness() {
            MealPickingReq.MealPickingType[] types = MealPickingReq.MealPickingType.values();
            
            // 确保所有代码都是唯一的
            for (int i = 0; i < types.length; i++) {
                for (int j = i + 1; j < types.length; j++) {
                    assertNotEquals(types[i].getCode(), types[j].getCode(),
                            "枚举代码不应该重复: " + types[i] + " 和 " + types[j]);
                }
            }
        }

        @Test
        @DisplayName("枚举业务流程测试")
        void testEnumBusinessFlow() {
            // 测试出餐状态的业务流程：等待拣货 -> 拣货中 -> 拣货完成
            MealPickingReq.MealPickingType waiting = MealPickingReq.MealPickingType.WAITING;
            MealPickingReq.MealPickingType picking = MealPickingReq.MealPickingType.PICKING;
            MealPickingReq.MealPickingType completed = MealPickingReq.MealPickingType.COMPLETED;
            
            // 验证流程顺序
            assertTrue(waiting.getCode() < picking.getCode());
            assertTrue(picking.getCode() < completed.getCode());
            
            // 验证状态转换
            assertEquals(0L, waiting.getCode());
            assertEquals(1L, picking.getCode());
            assertEquals(2L, completed.getCode());
        }
    }
}