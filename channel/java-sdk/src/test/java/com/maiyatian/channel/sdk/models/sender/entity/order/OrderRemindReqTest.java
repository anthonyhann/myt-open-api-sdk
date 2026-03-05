/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderRemindReq 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderRemindReq 单元测试")
class OrderRemindReqTest {

    private ObjectMapper objectMapper;
    private Validator validator;
    
    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            // When
            OrderRemindReq req = new OrderRemindReq();
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getRemindId());
            assertNull(req.getReason());
            assertNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有属性")
        void fullArgsConstructor_ShouldSetAllProperties() {
            // Given
            String orderId = "order123";
            String shopId = "shop123";
            Integer remindId = 456;
            String reason = "客户催单";
            Long updateTime = 1640995200L;
            
            // When
            OrderRemindReq req = new OrderRemindReq(orderId, shopId, remindId, reason, updateTime);
            
            // Then
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(remindId, req.getRemindId());
            assertEquals(reason, req.getReason());
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void fullArgsConstructor_ShouldAcceptNullValues() {
            // When
            OrderRemindReq req = new OrderRemindReq(null, null, null, null, null);
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getRemindId());
            assertNull(req.getReason());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        private OrderRemindReq req;

        @BeforeEach
        void setUp() {
            req = new OrderRemindReq();
        }

        @Test
        @DisplayName("orderId的getter和setter应该正常工作")
        void orderId_GetterSetter_ShouldWork() {
            // Given
            String orderId = "order123";
            
            // When
            req.setOrderId(orderId);
            
            // Then
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("shopId的getter和setter应该正常工作")
        void shopId_GetterSetter_ShouldWork() {
            // Given
            String shopId = "shop123";
            
            // When
            req.setShopId(shopId);
            
            // Then
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("remindId的getter和setter应该正常工作")
        void remindId_GetterSetter_ShouldWork() {
            // Given
            Integer remindId = 456;
            
            // When
            req.setRemindId(remindId);
            
            // Then
            assertEquals(remindId, req.getRemindId());
        }

        @Test
        @DisplayName("reason的getter和setter应该正常工作")
        void reason_GetterSetter_ShouldWork() {
            // Given
            String reason = "客户催单";
            
            // When
            req.setReason(reason);
            
            // Then
            assertEquals(reason, req.getReason());
        }

        @Test
        @DisplayName("updateTime的getter和setter应该正常工作")
        void updateTime_GetterSetter_ShouldWork() {
            // Given
            Long updateTime = System.currentTimeMillis() / 1000;
            
            // When
            req.setUpdateTime(updateTime);
            
            // Then
            assertEquals(updateTime, req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("验证注解测试")
    class ValidationTests {

        @Test
        @DisplayName("orderId不能为空")
        void orderId_ShouldNotBeBlank() {
            // Given
            OrderRemindReq req = createValidReq();
            req.setOrderId(null);
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "订单ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("orderId不能为空字符串")
        void orderId_ShouldNotBeEmptyString() {
            // Given
            OrderRemindReq req = createValidReq();
            req.setOrderId("");
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "订单ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("orderId不能为仅包含空格的字符串")
        void orderId_ShouldNotBeBlankString() {
            // Given
            OrderRemindReq req = createValidReq();
            req.setOrderId("   ");
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "订单ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为空")
        void shopId_ShouldNotBeBlank() {
            // Given
            OrderRemindReq req = createValidReq();
            req.setShopId(null);
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为空字符串")
        void shopId_ShouldNotBeEmptyString() {
            // Given
            OrderRemindReq req = createValidReq();
            req.setShopId("");
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为仅包含空格的字符串")
        void shopId_ShouldNotBeBlankString() {
            // Given
            OrderRemindReq req = createValidReq();
            req.setShopId("   ");
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("有效对象应该通过验证")
        void validObject_ShouldPassValidation() {
            // Given
            OrderRemindReq req = createValidReq();
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("可选字段为null应该通过验证")
        void optionalFieldsNull_ShouldPassValidation() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setOrderId("order123");
            req.setShopId("shop123");
            req.setRemindId(null);
            req.setReason(null);
            req.setUpdateTime(null);
            
            // When
            Set<ConstraintViolation<OrderRemindReq>> violations = validator.validate(req);
            
            // Then
            assertTrue(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When & Then
            assertEquals(req, req);
        }

        @Test
        @DisplayName("具有相同属性的对象应该相等")
        void sameProperties_ShouldBeEqual() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            
            // When & Then
            assertEquals(req1, req2);
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When & Then
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            // Given
            OrderRemindReq req = createTestReq();
            String other = "other";
            
            // When & Then
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("orderId不同时应该不相等")
        void differentOrderId_ShouldNotBeEqual() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            req2.setOrderId("different");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("shopId不同时应该不相等")
        void differentShopId_ShouldNotBeEqual() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            req2.setShopId("different");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("remindId不同时应该不相等")
        void differentRemindId_ShouldNotBeEqual() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            req2.setRemindId(999);
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("reason不同时应该不相等")
        void differentReason_ShouldNotBeEqual() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            req2.setReason("different");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("updateTime不同时应该不相等")
        void differentUpdateTime_ShouldNotBeEqual() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            req2.setUpdateTime(999L);
            
            // When & Then
            assertNotEquals(req1, req2);
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相等对象应该有相同的hashCode")
        void equalObjects_ShouldHaveSameHashCode() {
            // Given
            OrderRemindReq req1 = createTestReq();
            OrderRemindReq req2 = createTestReq();
            
            // When & Then
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("多次调用应该返回相同的hashCode")
        void multipleInvocations_ShouldReturnSameHashCode() {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When
            int hashCode1 = req.hashCode();
            int hashCode2 = req.hashCode();
            
            // Then
            assertEquals(hashCode1, hashCode2);
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含类名")
        void toString_ShouldContainClassName() {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("OrderRemindReq"));
        }

        @Test
        @DisplayName("toString应该包含所有属性")
        void toString_ShouldContainAllProperties() {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("orderId"));
            assertTrue(result.contains("shopId"));
            assertTrue(result.contains("remindId"));
            assertTrue(result.contains("reason"));
            assertTrue(result.contains("updateTime"));
        }

        @Test
        @DisplayName("toString不应该返回null")
        void toString_ShouldNotReturnNull() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertNotNull(result);
        }

        @Test
        @DisplayName("toString应该包含属性值")
        void toString_ShouldContainPropertyValues() {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("order123"));
            assertTrue(result.contains("shop123"));
            assertTrue(result.contains("456"));
            assertTrue(result.contains("客户催单"));
            assertTrue(result.contains("1640995200"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应该能够序列化为JSON")
        void object_ShouldSerializeToJson() throws Exception {
            // Given
            OrderRemindReq req = createTestReq();
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"order123\""));
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"remind_id\":456"));
            assertTrue(json.contains("\"reason\":\"客户催单\""));
            assertTrue(json.contains("\"update_time\":1640995200"));
        }

        @Test
        @DisplayName("JSON应该能够反序列化为对象")
        void json_ShouldDeserializeToObject() throws Exception {
            // Given
            String json = "{\"order_id\":\"order123\",\"shop_id\":\"shop123\"," +
                         "\"remind_id\":456,\"reason\":\"客户催单\",\"update_time\":1640995200}";
            
            // When
            OrderRemindReq req = objectMapper.readValue(json, OrderRemindReq.class);
            
            // Then
            assertNotNull(req);
            assertEquals("order123", req.getOrderId());
            assertEquals("shop123", req.getShopId());
            assertEquals(Integer.valueOf(456), req.getRemindId());
            assertEquals("客户催单", req.getReason());
            assertEquals(Long.valueOf(1640995200L), req.getUpdateTime());
        }

        @Test
        @DisplayName("序列化后再反序列化应该得到相等的对象")
        void serializeAndDeserialize_ShouldGetEqualObject() throws Exception {
            // Given
            OrderRemindReq original = createTestReq();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderRemindReq deserialized = objectMapper.readValue(json, OrderRemindReq.class);
            
            // Then
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应该能够序列化")
        void objectWithNullFields_ShouldSerialize() throws Exception {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setOrderId("order123");
            req.setShopId("shop123");
            req.setRemindId(null);
            req.setReason(null);
            req.setUpdateTime(null);
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"order123\""));
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
        }

        @Test
        @DisplayName("包含null字段的JSON应该能够反序列化")
        void jsonWithNullFields_ShouldDeserialize() throws Exception {
            // Given
            String json = "{\"order_id\":\"order123\",\"shop_id\":\"shop123\"," +
                         "\"remind_id\":null,\"reason\":null,\"update_time\":null}";
            
            // When
            OrderRemindReq req = objectMapper.readValue(json, OrderRemindReq.class);
            
            // Then
            assertNotNull(req);
            assertEquals("order123", req.getOrderId());
            assertEquals("shop123", req.getShopId());
            assertNull(req.getRemindId());
            assertNull(req.getReason());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("所有字段为null时应该正常工作")
        void allFieldsNull_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            
            // When & Then
            assertDoesNotThrow(() -> {
                req.toString();
                req.hashCode();
                req.equals(new OrderRemindReq());
            });
        }

        @Test
        @DisplayName("remindId为0时应该正常工作")
        void remindIdZero_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setRemindId(0);
            
            // When & Then
            assertEquals(Integer.valueOf(0), req.getRemindId());
            assertDoesNotThrow(() -> {
                req.toString();
                req.hashCode();
            });
        }

        @Test
        @DisplayName("remindId为负数时应该正常工作")
        void remindIdNegative_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setRemindId(-1);
            
            // When & Then
            assertEquals(Integer.valueOf(-1), req.getRemindId());
        }

        @Test
        @DisplayName("updateTime为0时应该正常工作")
        void updateTimeZero_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setUpdateTime(0L);
            
            // When & Then
            assertEquals(Long.valueOf(0L), req.getUpdateTime());
        }

        @Test
        @DisplayName("updateTime为负数时应该正常工作")
        void updateTimeNegative_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setUpdateTime(-1L);
            
            // When & Then
            assertEquals(Long.valueOf(-1L), req.getUpdateTime());
        }

        @Test
        @DisplayName("字符串字段为空字符串时应该正常工作")
        void stringFieldsEmpty_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setOrderId("");
            req.setShopId("");
            req.setReason("");
            
            // When & Then
            assertEquals("", req.getOrderId());
            assertEquals("", req.getShopId());
            assertEquals("", req.getReason());
        }

        @Test
        @DisplayName("字符串字段包含特殊字符时应该正常工作")
        void stringFieldsWithSpecialCharacters_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            String specialText = "测试@#$%^&*()_+{}|:<>?[]\\;'\",./-=`~";
            req.setOrderId(specialText);
            req.setShopId(specialText);
            req.setReason(specialText);
            
            // When & Then
            assertEquals(specialText, req.getOrderId());
            assertEquals(specialText, req.getShopId());
            assertEquals(specialText, req.getReason());
        }

        @Test
        @DisplayName("数字字段为最大值时应该正常工作")
        void numericFieldsMaxValue_ShouldWork() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            req.setRemindId(Integer.MAX_VALUE);
            req.setUpdateTime(Long.MAX_VALUE);
            
            // When & Then
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), req.getRemindId());
            assertEquals(Long.valueOf(Long.MAX_VALUE), req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("催单时间应该是合理的时间戳")
        void updateTime_ShouldBeReasonableTimestamp() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            Long currentTime = System.currentTimeMillis() / 1000;
            
            // When
            req.setUpdateTime(currentTime);
            
            // Then
            assertEquals(currentTime, req.getUpdateTime());
            assertTrue(req.getUpdateTime() > 0);
        }

        @Test
        @DisplayName("催单原因应该支持中文")
        void reason_ShouldSupportChinese() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            String chineseReason = "客户等餐时间过长，要求尽快配送";
            
            // When
            req.setReason(chineseReason);
            
            // Then
            assertEquals(chineseReason, req.getReason());
        }

        @Test
        @DisplayName("remindId应该支持正整数")
        void remindId_ShouldSupportPositiveIntegers() {
            // Given
            OrderRemindReq req = new OrderRemindReq();
            Integer remindId = 12345;
            
            // When
            req.setRemindId(remindId);
            
            // Then
            assertEquals(remindId, req.getRemindId());
        }
    }

    /**
     * 创建测试用的OrderRemindReq对象
     */
    private OrderRemindReq createTestReq() {
        OrderRemindReq req = new OrderRemindReq();
        req.setOrderId("order123");
        req.setShopId("shop123");
        req.setRemindId(456);
        req.setReason("客户催单");
        req.setUpdateTime(1640995200L);
        return req;
    }

    /**
     * 创建有效的OrderRemindReq对象（通过验证）
     */
    private OrderRemindReq createValidReq() {
        OrderRemindReq req = new OrderRemindReq();
        req.setOrderId("order123");
        req.setShopId("shop123");
        req.setRemindId(456);
        req.setReason("客户催单");
        req.setUpdateTime(1640995200L);
        return req;
    }
}