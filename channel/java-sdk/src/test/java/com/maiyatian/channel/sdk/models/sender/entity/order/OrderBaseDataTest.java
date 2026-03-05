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

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderBaseData单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderBaseData 测试")
class OrderBaseDataTest {

    private ObjectMapper objectMapper;
    private OrderBaseData orderBaseData;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderBaseData = new OrderBaseData();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            OrderBaseData data = new OrderBaseData();
            
            assertAll("默认构造函数验证",
                () -> assertNull(data.getOrder(), "order应该为null"),
                () -> assertNull(data.getOrderCustomer(), "orderCustomer应该为null"),
                () -> assertNull(data.getUpdateTime(), "updateTime应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void allArgsConstructor_ShouldSetAllFields() {
            // 准备测试数据
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L; // 2022-01-01 00:00:00 UTC
            
            // 测试构造函数
            OrderBaseData data = new OrderBaseData(order, customer, updateTime);
            
            assertAll("全参构造函数验证",
                () -> assertSame(order, data.getOrder(), "order应该被正确设置"),
                () -> assertSame(customer, data.getOrderCustomer(), "orderCustomer应该被正确设置"),
                () -> assertEquals(updateTime, data.getUpdateTime(), "updateTime应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void allArgsConstructor_ShouldAcceptNullValues() {
            OrderBaseData data = new OrderBaseData(null, null, null);
            
            assertAll("null值构造函数验证",
                () -> assertNull(data.getOrder(), "order可以为null"),
                () -> assertNull(data.getOrderCustomer(), "orderCustomer可以为null"),
                () -> assertNull(data.getUpdateTime(), "updateTime可以为null")
            );
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("order的getter和setter应该正常工作")
        void orderGetterSetter_ShouldWorkCorrectly() {
            OrderInfo order = new OrderInfo();
            
            orderBaseData.setOrder(order);
            assertSame(order, orderBaseData.getOrder(), "getter应该返回setter设置的值");
            
            orderBaseData.setOrder(null);
            assertNull(orderBaseData.getOrder(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("orderCustomer的getter和setter应该正常工作")
        void orderCustomerGetterSetter_ShouldWorkCorrectly() {
            OrderCustomerInfo customer = new OrderCustomerInfo();
            
            orderBaseData.setOrderCustomer(customer);
            assertSame(customer, orderBaseData.getOrderCustomer(), "getter应该返回setter设置的值");
            
            orderBaseData.setOrderCustomer(null);
            assertNull(orderBaseData.getOrderCustomer(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("updateTime的getter和setter应该正常工作")
        void updateTimeGetterSetter_ShouldWorkCorrectly() {
            Long updateTime = 1640995200L;
            
            orderBaseData.setUpdateTime(updateTime);
            assertEquals(updateTime, orderBaseData.getUpdateTime(), "getter应该返回setter设置的值");
            
            orderBaseData.setUpdateTime(null);
            assertNull(orderBaseData.getUpdateTime(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("链式调用测试")
        void chainedSetterCalls_ShouldWork() {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            orderBaseData.setOrder(order);
            orderBaseData.setOrderCustomer(customer);
            orderBaseData.setUpdateTime(updateTime);
            
            assertAll("链式调用验证",
                () -> assertSame(order, orderBaseData.getOrder()),
                () -> assertSame(customer, orderBaseData.getOrderCustomer()),
                () -> assertEquals(updateTime, orderBaseData.getUpdateTime())
            );
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertEquals(orderBaseData, orderBaseData, "对象应该与自身相等");
        }

        @Test
        @DisplayName("相同内容的不同对象应该相等")
        void differentObjectsWithSameContent_ShouldBeEqual() {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data1 = new OrderBaseData(order, customer, updateTime);
            OrderBaseData data2 = new OrderBaseData(order, customer, updateTime);
            
            assertEquals(data1, data2, "内容相同的对象应该相等");
        }

        @Test
        @DisplayName("不同内容的对象不应该相等")
        void differentContent_ShouldNotBeEqual() {
            OrderInfo order1 = new OrderInfo();
            order1.setOrderId("ORDER1"); // 设置不同的内容
            OrderInfo order2 = new OrderInfo();
            order2.setOrderId("ORDER2"); // 设置不同的内容
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data1 = new OrderBaseData(order1, customer, updateTime);
            OrderBaseData data2 = new OrderBaseData(order2, customer, updateTime);
            
            assertNotEquals(data1, data2, "不同内容的OrderBaseData应该不相等");
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            assertNotEquals(orderBaseData, null, "对象与null比较应该返回false");
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            assertNotEquals(orderBaseData, "string", "与不同类型对象比较应该返回false");
        }

        @Test
        @DisplayName("null字段的对象比较")
        void objectsWithNullFields_ShouldBeComparedCorrectly() {
            OrderBaseData data1 = new OrderBaseData(null, null, null);
            OrderBaseData data2 = new OrderBaseData(null, null, null);
            
            assertEquals(data1, data2, "都是null字段的对象应该相等");
        }

        @Test
        @DisplayName("部分字段为null的对象比较")
        void objectsWithPartialNullFields_ShouldBeComparedCorrectly() {
            OrderInfo order = new OrderInfo();
            
            OrderBaseData data1 = new OrderBaseData(order, null, null);
            OrderBaseData data2 = new OrderBaseData(null, null, null);
            
            assertNotEquals(data1, data2, "部分字段不同的对象应该不相等");
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相同对象应该有相同的hashCode")
        void sameObjects_ShouldHaveSameHashCode() {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data1 = new OrderBaseData(order, customer, updateTime);
            OrderBaseData data2 = new OrderBaseData(order, customer, updateTime);
            
            assertEquals(data1.hashCode(), data2.hashCode(), "相同对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致性")
        void hashCode_ShouldBeConsistent() {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data = new OrderBaseData(order, customer, updateTime);
            int firstHashCode = data.hashCode();
            int secondHashCode = data.hashCode();
            
            assertEquals(firstHashCode, secondHashCode, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段不应该导致hashCode异常")
        void nullFields_ShouldNotCauseHashCodeException() {
            OrderBaseData data = new OrderBaseData(null, null, null);
            
            assertDoesNotThrow(() -> data.hashCode(), "null字段不应该导致hashCode异常");
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含类名")
        void toString_ShouldContainClassName() {
            String result = orderBaseData.toString();
            assertTrue(result.contains("OrderBaseData"), "toString应该包含类名");
        }

        @Test
        @DisplayName("toString应该包含所有字段")
        void toString_ShouldContainAllFields() {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data = new OrderBaseData(order, customer, updateTime);
            String result = data.toString();
            
            assertAll("toString字段验证",
                () -> assertTrue(result.contains("order="), "应该包含order字段"),
                () -> assertTrue(result.contains("orderCustomer="), "应该包含orderCustomer字段"),
                () -> assertTrue(result.contains("updateTime="), "应该包含updateTime字段")
            );
        }

        @Test
        @DisplayName("toString应该处理null值")
        void toString_ShouldHandleNullValues() {
            OrderBaseData data = new OrderBaseData(null, null, null);
            String result = data.toString();
            
            assertAll("toString null值验证",
                () -> assertTrue(result.contains("order=null"), "应该正确显示null的order"),
                () -> assertTrue(result.contains("orderCustomer=null"), "应该正确显示null的orderCustomer"),
                () -> assertTrue(result.contains("updateTime=null"), "应该正确显示null的updateTime")
            );
        }

        @Test
        @DisplayName("toString不应该抛出异常")
        void toString_ShouldNotThrowException() {
            assertDoesNotThrow(() -> orderBaseData.toString(), "toString不应该抛出异常");
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该能够序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data = new OrderBaseData(order, customer, updateTime);
            
            assertDoesNotThrow(() -> objectMapper.writeValueAsString(data), 
                "应该能够序列化为JSON");
        }

        @Test
        @DisplayName("应该能够从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"order\":null,\"order_customer\":null,\"update_time\":1640995200}";
            
            OrderBaseData data = objectMapper.readValue(json, OrderBaseData.class);
            
            assertAll("JSON反序列化验证",
                () -> assertNotNull(data, "反序列化结果不应该为null"),
                () -> assertNull(data.getOrder(), "order应该为null"),
                () -> assertNull(data.getOrderCustomer(), "orderCustomer应该为null"),
                () -> assertEquals(1640995200L, data.getUpdateTime(), "updateTime应该正确反序列化")
            );
        }

        @Test
        @DisplayName("JSON字段映射应该正确")
        void jsonFieldMapping_ShouldBeCorrect() throws Exception {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            OrderBaseData data = new OrderBaseData(order, customer, 1640995200L);
            String json = objectMapper.writeValueAsString(data);
            
            assertAll("JSON字段映射验证",
                () -> assertTrue(json.contains("\"order\""), "应该包含order字段"),
                () -> assertTrue(json.contains("\"order_customer\""), "应该包含order_customer字段"),
                () -> assertTrue(json.contains("\"update_time\""), "应该包含update_time字段"),
                () -> assertTrue(json.contains("1640995200"), "应该包含updateTime的值")
            );
        }

        @Test
        @DisplayName("空对象应该能够序列化和反序列化")
        void emptyObject_ShouldSerializeAndDeserialize() throws Exception {
            OrderBaseData original = new OrderBaseData();
            
            String json = objectMapper.writeValueAsString(original);
            OrderBaseData deserialized = objectMapper.readValue(json, OrderBaseData.class);
            
            assertAll("空对象序列化验证",
                () -> assertNotNull(json, "JSON不应该为null"),
                () -> assertNotNull(deserialized, "反序列化对象不应该为null"),
                () -> assertNull(deserialized.getOrder(), "反序列化后order应该为null"),
                () -> assertNull(deserialized.getOrderCustomer(), "反序列化后orderCustomer应该为null"),
                () -> assertNull(deserialized.getUpdateTime(), "反序列化后updateTime应该为null")
            );
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("updateTime边界值测试")
        void updateTime_BoundaryValues() {
            assertAll("updateTime边界值测试",
                () -> {
                    orderBaseData.setUpdateTime(0L);
                    assertEquals(0L, orderBaseData.getUpdateTime(), "应该能设置updateTime为0");
                },
                () -> {
                    orderBaseData.setUpdateTime(Long.MAX_VALUE);
                    assertEquals(Long.MAX_VALUE, orderBaseData.getUpdateTime(), "应该能设置updateTime为Long.MAX_VALUE");
                },
                () -> {
                    orderBaseData.setUpdateTime(Long.MIN_VALUE);
                    assertEquals(Long.MIN_VALUE, orderBaseData.getUpdateTime(), "应该能设置updateTime为Long.MIN_VALUE");
                }
            );
        }

        @Test
        @DisplayName("极大数据量测试")
        void largeDataHandling_ShouldWork() {
            // 创建复杂的测试对象
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            customer.setRealName("测试用户名字很长很长很长很长很长很长很长很长很长");
            customer.setPhone("13800138000");
            customer.setAddress("非常详细的地址信息，包含很多文字描述，用来测试系统对长文本的处理能力");
            
            OrderBaseData data = new OrderBaseData(order, customer, System.currentTimeMillis());
            
            assertAll("大数据量处理验证",
                () -> assertNotNull(data.toString(), "toString应该能处理复杂数据"),
                () -> assertEquals(customer.getRealName(), data.getOrderCustomer().getRealName(), "长文本应该被正确保存"),
                () -> assertDoesNotThrow(() -> data.hashCode(), "hashCode应该能处理复杂数据")
            );
        }

        @Test
        @DisplayName("对象状态不变性测试")
        void objectImmutabilityAfterCreation() {
            OrderInfo order = new OrderInfo();
            OrderCustomerInfo customer = new OrderCustomerInfo();
            Long updateTime = 1640995200L;
            
            OrderBaseData data = new OrderBaseData(order, customer, updateTime);
            
            // 保存原始引用
            OrderInfo originalOrder = data.getOrder();
            OrderCustomerInfo originalCustomer = data.getOrderCustomer();
            Long originalUpdateTime = data.getUpdateTime();
            
            // 多次访问getter
            data.getOrder();
            data.getOrderCustomer();
            data.getUpdateTime();
            
            assertAll("对象状态不变性验证",
                () -> assertSame(originalOrder, data.getOrder(), "多次调用getter后order引用应该保持不变"),
                () -> assertSame(originalCustomer, data.getOrderCustomer(), "多次调用getter后orderCustomer引用应该保持不变"),
                () -> assertEquals(originalUpdateTime, data.getUpdateTime(), "多次调用getter后updateTime应该保持不变")
            );
        }
    }
}