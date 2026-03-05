/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * UpdateOrderReq 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("UpdateOrderReq 单元测试")
class UpdateOrderReqTest {

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
            UpdateOrderReq req = new UpdateOrderReq();
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrder());
            assertNull(req.getOrderCustomer());
            assertNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有属性")
        void fullArgsConstructor_ShouldSetAllProperties() {
            // Given
            OrderInfo order = createTestOrderInfo();
            OrderCustomerInfo orderCustomer = createTestOrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            // When
            UpdateOrderReq req = new UpdateOrderReq(order, orderCustomer, updateTime);
            
            // Then
            assertNotNull(req);
            assertEquals(order, req.getOrder());
            assertEquals(orderCustomer, req.getOrderCustomer());
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void fullArgsConstructor_ShouldAcceptNullValues() {
            // When
            UpdateOrderReq req = new UpdateOrderReq(null, null, null);
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrder());
            assertNull(req.getOrderCustomer());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("继承方法测试")
    class InheritanceTests {

        @Test
        @DisplayName("应该继承OrderBaseData的所有getter和setter方法")
        void shouldInheritOrderBaseDataMethods() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            OrderInfo order = createTestOrderInfo();
            OrderCustomerInfo orderCustomer = createTestOrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            // When
            req.setOrder(order);
            req.setOrderCustomer(orderCustomer);
            req.setUpdateTime(updateTime);
            
            // Then
            assertEquals(order, req.getOrder());
            assertEquals(orderCustomer, req.getOrderCustomer());
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("应该继承OrderBaseData的equals方法")
        void shouldInheritOrderBaseDataEquals() {
            // Given
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
            
            // When & Then
            assertEquals(req1, req2);
        }

        @Test
        @DisplayName("应该继承OrderBaseData的hashCode方法")
        void shouldInheritOrderBaseDataHashCode() {
            // Given
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
            
            // When & Then
            assertEquals(req1.hashCode(), req2.hashCode());
        }
    }

    @Nested
    @DisplayName("验证注解测试")
    class ValidationTests {

        @Test
        @DisplayName("order不能为空")
        void order_ShouldNotBeNull() {
            // Given
            UpdateOrderReq req = createValidUpdateOrderReq();
            req.setOrder(null);
            
            // When
            Set<ConstraintViolation<UpdateOrderReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "订单信息不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("orderCustomer不能为空")
        void orderCustomer_ShouldNotBeNull() {
            // Given
            UpdateOrderReq req = createValidUpdateOrderReq();
            req.setOrderCustomer(null);
            
            // When
            Set<ConstraintViolation<UpdateOrderReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "顾客信息不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("有效对象应该通过验证")
        void validObject_ShouldPassValidation() {
            // Given
            UpdateOrderReq req = createValidUpdateOrderReq();
            
            // When
            Set<ConstraintViolation<UpdateOrderReq>> violations = validator.validate(req);
            
            // Then
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("updateTime可以为null")
        void updateTime_CanBeNull() {
            // Given
            UpdateOrderReq req = createValidUpdateOrderReq();
            req.setUpdateTime(null);
            
            // When
            Set<ConstraintViolation<UpdateOrderReq>> violations = validator.validate(req);
            
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
            UpdateOrderReq req = createTestUpdateOrderReq();
            
            // When & Then
            assertEquals(req, req);
        }

        @Test
        @DisplayName("具有相同属性的对象应该相等")
        void sameProperties_ShouldBeEqual() {
            // Given
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
            
            // When & Then
            assertEquals(req1, req2);
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            
            // When & Then
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            String other = "other";
            
            // When & Then
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("与OrderBaseData对象比较应该基于类型判断")
        void compareWithOrderBaseData_ShouldCheckType() {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            OrderBaseData baseData = new OrderBaseData(req.getOrder(), req.getOrderCustomer(), req.getUpdateTime());
            
            // When & Then
            assertNotEquals(req, baseData);
        }

        @Test
        @DisplayName("order不同时应该不相等")
        void differentOrder_ShouldNotBeEqual() {
            // Given
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
            req2.setOrder(createDifferentOrderInfo());
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("orderCustomer不同时应该不相等")
        void differentOrderCustomer_ShouldNotBeEqual() {
            // Given
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
            req2.setOrderCustomer(createDifferentOrderCustomerInfo());
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("updateTime不同时应该不相等")
        void differentUpdateTime_ShouldNotBeEqual() {
            // Given
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
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
            UpdateOrderReq req1 = createTestUpdateOrderReq();
            UpdateOrderReq req2 = createTestUpdateOrderReq();
            
            // When & Then
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("多次调用应该返回相同的hashCode")
        void multipleInvocations_ShouldReturnSameHashCode() {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            
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
            UpdateOrderReq req = createTestUpdateOrderReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("UpdateOrderReq"));
        }

        @Test
        @DisplayName("toString应该包含所有属性")
        void toString_ShouldContainAllProperties() {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("order"));
            assertTrue(result.contains("orderCustomer"));
            assertTrue(result.contains("updateTime"));
        }

        @Test
        @DisplayName("toString不应该返回null")
        void toString_ShouldNotReturnNull() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertNotNull(result);
        }

        @Test
        @DisplayName("toString应该覆盖父类方法")
        void toString_ShouldOverrideParentMethod() {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            OrderBaseData baseData = new OrderBaseData(req.getOrder(), req.getOrderCustomer(), req.getUpdateTime());
            
            // When
            String reqString = req.toString();
            String baseString = baseData.toString();
            
            // Then
            assertNotEquals(reqString, baseString);
            assertTrue(reqString.contains("UpdateOrderReq"));
            assertTrue(baseString.contains("OrderBaseData"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应该能够序列化为JSON")
        void object_ShouldSerializeToJson() throws Exception {
            // Given
            UpdateOrderReq req = createTestUpdateOrderReq();
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order\":{"));
            assertTrue(json.contains("\"order_customer\":{"));
            assertTrue(json.contains("\"update_time\":1640995200"));
        }

        @Test
        @DisplayName("JSON应该能够反序列化为对象")
        void json_ShouldDeserializeToObject() throws Exception {
            // Given
            String json = createTestJson();
            
            // When
            UpdateOrderReq req = objectMapper.readValue(json, UpdateOrderReq.class);
            
            // Then
            assertNotNull(req);
            assertNotNull(req.getOrder());
            assertNotNull(req.getOrderCustomer());
            assertEquals(Long.valueOf(1640995200L), req.getUpdateTime());
        }

        @Test
        @DisplayName("序列化后再反序列化应该得到相等的对象")
        void serializeAndDeserialize_ShouldGetEqualObject() throws Exception {
            // Given
            UpdateOrderReq original = createTestUpdateOrderReq();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            UpdateOrderReq deserialized = objectMapper.readValue(json, UpdateOrderReq.class);
            
            // Then
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应该能够序列化")
        void objectWithNullFields_ShouldSerialize() throws Exception {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            req.setOrder(createTestOrderInfo());
            req.setOrderCustomer(createTestOrderCustomerInfo());
            req.setUpdateTime(null);
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order\":{"));
            assertTrue(json.contains("\"order_customer\":{"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("所有字段为null时应该正常工作")
        void allFieldsNull_ShouldWork() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            
            // When & Then
            assertDoesNotThrow(() -> {
                req.toString();
                req.hashCode();
                req.equals(new UpdateOrderReq());
            });
        }

        @Test
        @DisplayName("updateTime为0时应该正常工作")
        void updateTimeZero_ShouldWork() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            req.setUpdateTime(0L);
            
            // When & Then
            assertEquals(Long.valueOf(0L), req.getUpdateTime());
        }

        @Test
        @DisplayName("updateTime为负数时应该正常工作")
        void updateTimeNegative_ShouldWork() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            req.setUpdateTime(-1L);
            
            // When & Then
            assertEquals(Long.valueOf(-1L), req.getUpdateTime());
        }

        @Test
        @DisplayName("updateTime为最大值时应该正常工作")
        void updateTimeMaxValue_ShouldWork() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            req.setUpdateTime(Long.MAX_VALUE);
            
            // When & Then
            assertEquals(Long.valueOf(Long.MAX_VALUE), req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("应该支持订单修改场景")
        @JsonIgnore
        void shouldSupportOrderUpdateScenario() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            OrderInfo originalOrder = createTestOrderInfo();
            OrderCustomerInfo originalCustomer = createTestOrderCustomerInfo();
            
            // When - 模拟订单修改
            req.setOrder(originalOrder);
            req.setOrderCustomer(originalCustomer);
            req.setUpdateTime(System.currentTimeMillis() / 1000);
            
            // Then
            assertNotNull(req.getOrder());
            assertNotNull(req.getOrderCustomer());
            assertNotNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("应该支持部分字段修改")
        @JsonIgnore
        void shouldSupportPartialUpdate() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            OrderInfo modifiedOrder = createTestOrderInfo();
            
            // When - 仅修改订单信息，保持客户信息不变
            req.setOrder(modifiedOrder);
            req.setOrderCustomer(createTestOrderCustomerInfo());
            req.setUpdateTime(System.currentTimeMillis() / 1000);
            
            // Then
            assertNotNull(req.getOrder());
            assertNotNull(req.getOrderCustomer());
            assertTrue(req.getUpdateTime() > 0);
        }

        @Test
        @DisplayName("应该能够追踪修改时间")
        @JsonIgnore
        void shouldTrackUpdateTime() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            Long updateTime = System.currentTimeMillis() / 1000;
            
            // When
            req.setOrder(createTestOrderInfo());
            req.setOrderCustomer(createTestOrderCustomerInfo());
            req.setUpdateTime(updateTime);
            
            // Then
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("应该继承OrderBaseData的所有功能")
        @JsonIgnore
        void shouldInheritAllOrderBaseDataFeatures() {
            // Given
            UpdateOrderReq req = new UpdateOrderReq();
            
            // When
            req.setOrder(createTestOrderInfo());
            req.setOrderCustomer(createTestOrderCustomerInfo());
            req.setUpdateTime(1640995200L);
            
            // Then
            assertTrue(req instanceof OrderBaseData);
            assertNotNull(req.getOrder());
            assertNotNull(req.getOrderCustomer());
            assertNotNull(req.getUpdateTime());
        }
    }

    /**
     * 创建测试用的UpdateOrderReq对象
     */
    private UpdateOrderReq createTestUpdateOrderReq() {
        UpdateOrderReq req = new UpdateOrderReq();
        req.setOrder(createTestOrderInfo());
        req.setOrderCustomer(createTestOrderCustomerInfo());
        req.setUpdateTime(1640995200L);
        return req;
    }

    /**
     * 创建有效的UpdateOrderReq对象（通过验证）
     */
    private UpdateOrderReq createValidUpdateOrderReq() {
        UpdateOrderReq req = new UpdateOrderReq();
        req.setOrder(createTestOrderInfo());
        req.setOrderCustomer(createTestOrderCustomerInfo());
        req.setUpdateTime(1640995200L);
        return req;
    }

    /**
     * 创建测试用的OrderInfo对象
     */
    private OrderInfo createTestOrderInfo() {
        OrderInfo order = new OrderInfo();
        order.setOrderId("order123");
        order.setShopId("shop123");
        order.setShopName("测试门店");
        order.setCategory("美食");
        return order;
    }

    /**
     * 创建不同的OrderInfo对象
     */
    private OrderInfo createDifferentOrderInfo() {
        OrderInfo order = new OrderInfo();
        order.setOrderId("different_order");
        order.setShopId("different_shop");
        order.setShopName("不同门店");
        order.setCategory("饮品");
        return order;
    }

    /**
     * 创建测试用的OrderCustomerInfo对象
     */
    private OrderCustomerInfo createTestOrderCustomerInfo() {
        OrderCustomerInfo customer = new OrderCustomerInfo();
        customer.setRealName("测试客户");
        customer.setPhone("13800138000");
        customer.setAddress("测试地址");
        return customer;
    }

    /**
     * 创建不同的OrderCustomerInfo对象
     */
    private OrderCustomerInfo createDifferentOrderCustomerInfo() {
        OrderCustomerInfo customer = new OrderCustomerInfo();
        customer.setRealName("不同客户");
        customer.setPhone("13900139000");
        customer.setAddress("不同地址");
        return customer;
    }

    /**
     * 创建测试用的JSON字符串
     */
    private String createTestJson() {
        return "{" +
                "\"order\":{" +
                    "\"order_id\":\"order123\"," +
                    "\"shop_id\":\"shop123\"," +
                    "\"shop_name\":\"测试门店\"," +
                    "\"category\":\"美食\"" +
                "}," +
                "\"order_customer\":{" +
                    "\"real_name\":\"测试客户\"," +
                    "\"phone\":\"13800138000\"," +
                    "\"address\":\"测试地址\"" +
                "}," +
                "\"update_time\":1640995200" +
                "}";
    }
}