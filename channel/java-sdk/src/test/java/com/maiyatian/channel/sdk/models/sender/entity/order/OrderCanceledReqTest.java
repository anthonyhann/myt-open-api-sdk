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

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderCanceledReq单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderCanceledReq 测试")
class OrderCanceledReqTest {

    private ObjectMapper objectMapper;
    private OrderCanceledReq orderCanceledReq;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderCanceledReq = new OrderCanceledReq();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            OrderCanceledReq req = new OrderCanceledReq();
            
            assertAll("默认构造函数验证",
                () -> assertNull(req.getOrderId(), "orderId应该为null"),
                () -> assertNull(req.getShopId(), "shopId应该为null"),
                () -> assertNull(req.getReason(), "reason应该为null"),
                () -> assertNull(req.getReasonCode(), "reasonCode应该为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void allArgsConstructor_ShouldSetAllFields() {
            // 准备测试数据
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            String reason = "用户主动取消";
            Integer reasonCode = 1;
            Long updateTime = 1640995200L; // 2022-01-01 00:00:00 UTC
            
            // 测试构造函数
            OrderCanceledReq req = new OrderCanceledReq(orderId, shopId, reason, reasonCode, updateTime);
            
            assertAll("全参构造函数验证",
                () -> assertEquals(orderId, req.getOrderId(), "orderId应该被正确设置"),
                () -> assertEquals(shopId, req.getShopId(), "shopId应该被正确设置"),
                () -> assertEquals(reason, req.getReason(), "reason应该被正确设置"),
                () -> assertEquals(reasonCode, req.getReasonCode(), "reasonCode应该被正确设置"),
                () -> assertEquals(updateTime, req.getUpdateTime(), "updateTime应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void allArgsConstructor_ShouldAcceptNullValues() {
            OrderCanceledReq req = new OrderCanceledReq(null, null, null, null, null);
            
            assertAll("null值构造函数验证",
                () -> assertNull(req.getOrderId(), "orderId可以为null"),
                () -> assertNull(req.getShopId(), "shopId可以为null"),
                () -> assertNull(req.getReason(), "reason可以为null"),
                () -> assertNull(req.getReasonCode(), "reasonCode可以为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime可以为null")
            );
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("orderId的getter和setter应该正常工作")
        void orderIdGetterSetter_ShouldWorkCorrectly() {
            String orderId = "ORDER123";
            
            orderCanceledReq.setOrderId(orderId);
            assertEquals(orderId, orderCanceledReq.getOrderId(), "getter应该返回setter设置的值");
            
            orderCanceledReq.setOrderId(null);
            assertNull(orderCanceledReq.getOrderId(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("shopId的getter和setter应该正常工作")
        void shopIdGetterSetter_ShouldWorkCorrectly() {
            String shopId = "SHOP456";
            
            orderCanceledReq.setShopId(shopId);
            assertEquals(shopId, orderCanceledReq.getShopId(), "getter应该返回setter设置的值");
            
            orderCanceledReq.setShopId(null);
            assertNull(orderCanceledReq.getShopId(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("reason的getter和setter应该正常工作")
        void reasonGetterSetter_ShouldWorkCorrectly() {
            String reason = "用户主动取消";
            
            orderCanceledReq.setReason(reason);
            assertEquals(reason, orderCanceledReq.getReason(), "getter应该返回setter设置的值");
            
            orderCanceledReq.setReason(null);
            assertNull(orderCanceledReq.getReason(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("reasonCode的getter和setter应该正常工作")
        void reasonCodeGetterSetter_ShouldWorkCorrectly() {
            Integer reasonCode = 1;
            
            orderCanceledReq.setReasonCode(reasonCode);
            assertEquals(reasonCode, orderCanceledReq.getReasonCode(), "getter应该返回setter设置的值");
            
            orderCanceledReq.setReasonCode(null);
            assertNull(orderCanceledReq.getReasonCode(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("updateTime的getter和setter应该正常工作")
        void updateTimeGetterSetter_ShouldWorkCorrectly() {
            Long updateTime = 1640995200L;
            
            orderCanceledReq.setUpdateTime(updateTime);
            assertEquals(updateTime, orderCanceledReq.getUpdateTime(), "getter应该返回setter设置的值");
            
            orderCanceledReq.setUpdateTime(null);
            assertNull(orderCanceledReq.getUpdateTime(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("链式调用测试")
        void chainedSetterCalls_ShouldWork() {
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            String reason = "用户主动取消";
            Integer reasonCode = 1;
            Long updateTime = 1640995200L;
            
            orderCanceledReq.setOrderId(orderId);
            orderCanceledReq.setShopId(shopId);
            orderCanceledReq.setReason(reason);
            orderCanceledReq.setReasonCode(reasonCode);
            orderCanceledReq.setUpdateTime(updateTime);
            
            assertAll("链式调用验证",
                () -> assertEquals(orderId, orderCanceledReq.getOrderId()),
                () -> assertEquals(shopId, orderCanceledReq.getShopId()),
                () -> assertEquals(reason, orderCanceledReq.getReason()),
                () -> assertEquals(reasonCode, orderCanceledReq.getReasonCode()),
                () -> assertEquals(updateTime, orderCanceledReq.getUpdateTime())
            );
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertEquals(orderCanceledReq, orderCanceledReq, "对象应该与自身相等");
        }

        @Test
        @DisplayName("相同内容的不同对象应该相等")
        void differentObjectsWithSameContent_ShouldBeEqual() {
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            String reason = "用户主动取消";
            Integer reasonCode = 1;
            Long updateTime = 1640995200L;
            
            OrderCanceledReq req1 = new OrderCanceledReq(orderId, shopId, reason, reasonCode, updateTime);
            OrderCanceledReq req2 = new OrderCanceledReq(orderId, shopId, reason, reasonCode, updateTime);
            
            assertEquals(req1, req2, "内容相同的对象应该相等");
        }

        @Test
        @DisplayName("不同orderId的对象不应该相等")
        void differentOrderId_ShouldNotBeEqual() {
            OrderCanceledReq req1 = new OrderCanceledReq("ORDER123", "SHOP456", "原因", 1, 1640995200L);
            OrderCanceledReq req2 = new OrderCanceledReq("ORDER789", "SHOP456", "原因", 1, 1640995200L);
            
            assertNotEquals(req1, req2, "不同orderId的对象不应该相等");
        }

        @Test
        @DisplayName("不同reasonCode的对象不应该相等")
        void differentReasonCode_ShouldNotBeEqual() {
            OrderCanceledReq req1 = new OrderCanceledReq("ORDER123", "SHOP456", "原因", 1, 1640995200L);
            OrderCanceledReq req2 = new OrderCanceledReq("ORDER123", "SHOP456", "原因", 2, 1640995200L);
            
            assertNotEquals(req1, req2, "不同reasonCode的对象不应该相等");
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            assertNotEquals(orderCanceledReq, null, "对象与null比较应该返回false");
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            assertNotEquals(orderCanceledReq, "string", "与不同类型对象比较应该返回false");
        }

        @Test
        @DisplayName("null字段的对象比较")
        void objectsWithNullFields_ShouldBeComparedCorrectly() {
            OrderCanceledReq req1 = new OrderCanceledReq(null, null, null, null, null);
            OrderCanceledReq req2 = new OrderCanceledReq(null, null, null, null, null);
            
            assertEquals(req1, req2, "都是null字段的对象应该相等");
        }

        @Test
        @DisplayName("部分字段为null的对象比较")
        void objectsWithPartialNullFields_ShouldBeComparedCorrectly() {
            OrderCanceledReq req1 = new OrderCanceledReq("ORDER123", null, null, null, null);
            OrderCanceledReq req2 = new OrderCanceledReq(null, null, null, null, null);
            
            assertNotEquals(req1, req2, "部分字段不同的对象应该不相等");
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相同对象应该有相同的hashCode")
        void sameObjects_ShouldHaveSameHashCode() {
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            String reason = "用户主动取消";
            Integer reasonCode = 1;
            Long updateTime = 1640995200L;
            
            OrderCanceledReq req1 = new OrderCanceledReq(orderId, shopId, reason, reasonCode, updateTime);
            OrderCanceledReq req2 = new OrderCanceledReq(orderId, shopId, reason, reasonCode, updateTime);
            
            assertEquals(req1.hashCode(), req2.hashCode(), "相同对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致性")
        void hashCode_ShouldBeConsistent() {
            OrderCanceledReq req = new OrderCanceledReq("ORDER123", "SHOP456", "原因", 1, 1640995200L);
            int firstHashCode = req.hashCode();
            int secondHashCode = req.hashCode();
            
            assertEquals(firstHashCode, secondHashCode, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段不应该导致hashCode异常")
        void nullFields_ShouldNotCauseHashCodeException() {
            OrderCanceledReq req = new OrderCanceledReq(null, null, null, null, null);
            
            assertDoesNotThrow(() -> req.hashCode(), "null字段不应该导致hashCode异常");
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含类名")
        void toString_ShouldContainClassName() {
            String result = orderCanceledReq.toString();
            assertTrue(result.contains("OrderCanceledReq"), "toString应该包含类名");
        }

        @Test
        @DisplayName("toString应该包含所有字段")
        void toString_ShouldContainAllFields() {
            OrderCanceledReq req = new OrderCanceledReq("ORDER123", "SHOP456", "用户主动取消", 1, 1640995200L);
            String result = req.toString();
            
            assertAll("toString字段验证",
                () -> assertTrue(result.contains("orderId="), "应该包含orderId字段"),
                () -> assertTrue(result.contains("shopId="), "应该包含shopId字段"),
                () -> assertTrue(result.contains("reason="), "应该包含reason字段"),
                () -> assertTrue(result.contains("reasonCode="), "应该包含reasonCode字段"),
                () -> assertTrue(result.contains("updateTime="), "应该包含updateTime字段")
            );
        }

        @Test
        @DisplayName("toString应该包含具体的值")
        void toString_ShouldContainSpecificValues() {
            OrderCanceledReq req = new OrderCanceledReq("ORDER123", "SHOP456", "用户主动取消", 1, 1640995200L);
            String result = req.toString();
            
            assertAll("toString值验证",
                () -> assertTrue(result.contains("ORDER123"), "应该包含orderId的值"),
                () -> assertTrue(result.contains("SHOP456"), "应该包含shopId的值"),
                () -> assertTrue(result.contains("用户主动取消"), "应该包含reason的值"),
                () -> assertTrue(result.contains("1"), "应该包含reasonCode的值"),
                () -> assertTrue(result.contains("1640995200"), "应该包含updateTime的值")
            );
        }

        @Test
        @DisplayName("toString应该处理null值")
        void toString_ShouldHandleNullValues() {
            OrderCanceledReq req = new OrderCanceledReq(null, null, null, null, null);
            String result = req.toString();
            
            assertAll("toString null值验证",
                () -> assertTrue(result.contains("orderId=null") || result.contains("orderId='null'"), "应该正确显示null的orderId"),
                () -> assertTrue(result.contains("shopId=null") || result.contains("shopId='null'"), "应该正确显示null的shopId"),
                () -> assertTrue(result.contains("reason=null") || result.contains("reason='null'"), "应该正确显示null的reason"),
                () -> assertTrue(result.contains("reasonCode=null"), "应该正确显示null的reasonCode"),
                () -> assertTrue(result.contains("updateTime=null"), "应该正确显示null的updateTime")
            );
        }

        @Test
        @DisplayName("toString不应该抛出异常")
        void toString_ShouldNotThrowException() {
            assertDoesNotThrow(() -> orderCanceledReq.toString(), "toString不应该抛出异常");
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该能够序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            OrderCanceledReq req = new OrderCanceledReq("ORDER123", "SHOP456", "用户主动取消", 1, 1640995200L);
            
            assertDoesNotThrow(() -> objectMapper.writeValueAsString(req), 
                "应该能够序列化为JSON");
        }

        @Test
        @DisplayName("应该能够从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"order_id\":\"ORDER123\",\"shop_id\":\"SHOP456\",\"reason\":\"用户主动取消\",\"reason_code\":1,\"update_time\":1640995200}";
            
            OrderCanceledReq req = objectMapper.readValue(json, OrderCanceledReq.class);
            
            assertAll("JSON反序列化验证",
                () -> assertNotNull(req, "反序列化结果不应该为null"),
                () -> assertEquals("ORDER123", req.getOrderId(), "orderId应该正确反序列化"),
                () -> assertEquals("SHOP456", req.getShopId(), "shopId应该正确反序列化"),
                () -> assertEquals("用户主动取消", req.getReason(), "reason应该正确反序列化"),
                () -> assertEquals(1, req.getReasonCode(), "reasonCode应该正确反序列化"),
                () -> assertEquals(1640995200L, req.getUpdateTime(), "updateTime应该正确反序列化")
            );
        }

        @Test
        @DisplayName("JSON字段映射应该正确")
        void jsonFieldMapping_ShouldBeCorrect() throws Exception {
            OrderCanceledReq req = new OrderCanceledReq("ORDER123", "SHOP456", "用户主动取消", 1, 1640995200L);
            String json = objectMapper.writeValueAsString(req);
            
            assertAll("JSON字段映射验证",
                () -> assertTrue(json.contains("\"order_id\""), "应该包含order_id字段"),
                () -> assertTrue(json.contains("\"shop_id\""), "应该包含shop_id字段"),
                () -> assertTrue(json.contains("\"reason\""), "应该包含reason字段"),
                () -> assertTrue(json.contains("\"reason_code\""), "应该包含reason_code字段"),
                () -> assertTrue(json.contains("\"update_time\""), "应该包含update_time字段")
            );
        }

        @Test
        @DisplayName("空对象应该能够序列化和反序列化")
        void emptyObject_ShouldSerializeAndDeserialize() throws Exception {
            OrderCanceledReq original = new OrderCanceledReq();
            
            String json = objectMapper.writeValueAsString(original);
            OrderCanceledReq deserialized = objectMapper.readValue(json, OrderCanceledReq.class);
            
            assertAll("空对象序列化验证",
                () -> assertNotNull(json, "JSON不应该为null"),
                () -> assertNotNull(deserialized, "反序列化对象不应该为null"),
                () -> assertNull(deserialized.getOrderId(), "反序列化后orderId应该为null"),
                () -> assertNull(deserialized.getShopId(), "反序列化后shopId应该为null"),
                () -> assertNull(deserialized.getReason(), "反序列化后reason应该为null"),
                () -> assertNull(deserialized.getReasonCode(), "反序列化后reasonCode应该为null"),
                () -> assertNull(deserialized.getUpdateTime(), "反序列化后updateTime应该为null")
            );
        }

        @Test
        @DisplayName("序列化后反序列化应该保持数据一致性")
        void serializeDeserialize_ShouldMaintainDataIntegrity() throws Exception {
            OrderCanceledReq original = new OrderCanceledReq("ORDER123", "SHOP456", "用户主动取消", 1, 1640995200L);
            
            String json = objectMapper.writeValueAsString(original);
            OrderCanceledReq deserialized = objectMapper.readValue(json, OrderCanceledReq.class);
            
            assertEquals(original, deserialized, "序列化后反序列化应该保持对象相等性");
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("验证reasonCode的有效值范围")
        @JsonIgnore
        void validateReasonCodeValues() {
            assertAll("reasonCode有效值验证",
                () -> {
                    orderCanceledReq.setReasonCode(1);
                    assertEquals(1, orderCanceledReq.getReasonCode(), "reasonCode=1(用户取消)应该有效");
                },
                () -> {
                    orderCanceledReq.setReasonCode(2);
                    assertEquals(2, orderCanceledReq.getReasonCode(), "reasonCode=2(商户取消)应该有效");
                },
                () -> {
                    orderCanceledReq.setReasonCode(3);
                    assertEquals(3, orderCanceledReq.getReasonCode(), "reasonCode=3(客服取消)应该有效");
                },
                () -> {
                    orderCanceledReq.setReasonCode(4);
                    assertEquals(4, orderCanceledReq.getReasonCode(), "reasonCode=4(系统取消)应该有效");
                },
                () -> {
                    orderCanceledReq.setReasonCode(5);
                    assertEquals(5, orderCanceledReq.getReasonCode(), "reasonCode=5(其他)应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证reasonCode超出范围的情况")
        @JsonIgnore
        void handleInvalidReasonCode() {
            // 注意：这里只是测试setter/getter，实际验证应该在业务逻辑层
            assertAll("reasonCode超范围值测试",
                () -> {
                    orderCanceledReq.setReasonCode(0);
                    assertEquals(0, orderCanceledReq.getReasonCode(), "reasonCode=0应该能设置（但可能在业务验证中无效）");
                },
                () -> {
                    orderCanceledReq.setReasonCode(6);
                    assertEquals(6, orderCanceledReq.getReasonCode(), "reasonCode=6应该能设置（但可能在业务验证中无效）");
                },
                () -> {
                    orderCanceledReq.setReasonCode(-1);
                    assertEquals(-1, orderCanceledReq.getReasonCode(), "reasonCode=-1应该能设置（但可能在业务验证中无效）");
                }
            );
        }

        @Test
        @DisplayName("验证取消时间的合理性")
        @JsonIgnore
        void validateUpdateTime() {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long futureTime = currentTime + 3600; // 1小时后
            Long pastTime = currentTime - 3600; // 1小时前
            
            assertAll("updateTime时间验证",
                () -> {
                    orderCanceledReq.setUpdateTime(currentTime);
                    assertEquals(currentTime, orderCanceledReq.getUpdateTime(), "当前时间应该有效");
                },
                () -> {
                    orderCanceledReq.setUpdateTime(pastTime);
                    assertEquals(pastTime, orderCanceledReq.getUpdateTime(), "过去时间应该有效");
                },
                () -> {
                    orderCanceledReq.setUpdateTime(futureTime);
                    assertEquals(futureTime, orderCanceledReq.getUpdateTime(), "未来时间应该能设置（但可能在业务验证中无效）");
                }
            );
        }

        @Test
        @DisplayName("验证订单ID格式")
        @JsonIgnore
        void validateOrderIdFormat() {
            assertAll("orderId格式验证",
                () -> {
                    String validId = "ORDER_2022_001";
                    orderCanceledReq.setOrderId(validId);
                    assertEquals(validId, orderCanceledReq.getOrderId(), "包含下划线的订单ID应该有效");
                },
                () -> {
                    String numericId = "123456789";
                    orderCanceledReq.setOrderId(numericId);
                    assertEquals(numericId, orderCanceledReq.getOrderId(), "纯数字订单ID应该有效");
                },
                () -> {
                    String mixedId = "ORD123ABC";
                    orderCanceledReq.setOrderId(mixedId);
                    assertEquals(mixedId, orderCanceledReq.getOrderId(), "字母数字混合订单ID应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证取消原因的长度")
        @JsonIgnore
        void validateReasonLength() {
            assertAll("reason长度验证",
                () -> {
                    String shortReason = "取消";
                    orderCanceledReq.setReason(shortReason);
                    assertEquals(shortReason, orderCanceledReq.getReason(), "短原因描述应该有效");
                },
                () -> {
                    String longReason = "用户因为个人原因需要取消此订单，已经与客服沟通确认，无需退款处理，谢谢合作。";
                    orderCanceledReq.setReason(longReason);
                    assertEquals(longReason, orderCanceledReq.getReason(), "长原因描述应该有效");
                },
                () -> {
                    String emptyReason = "";
                    orderCanceledReq.setReason(emptyReason);
                    assertEquals(emptyReason, orderCanceledReq.getReason(), "空原因描述应该能设置");
                }
            );
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("极值测试")
        void extremeValueTests() {
            assertAll("极值测试",
                () -> {
                    orderCanceledReq.setUpdateTime(0L);
                    assertEquals(0L, orderCanceledReq.getUpdateTime(), "updateTime设置为0应该有效");
                },
                () -> {
                    orderCanceledReq.setUpdateTime(Long.MAX_VALUE);
                    assertEquals(Long.MAX_VALUE, orderCanceledReq.getUpdateTime(), "updateTime设置为Long.MAX_VALUE应该有效");
                },
                () -> {
                    orderCanceledReq.setReasonCode(Integer.MAX_VALUE);
                    assertEquals(Integer.MAX_VALUE, orderCanceledReq.getReasonCode(), "reasonCode设置为Integer.MAX_VALUE应该有效");
                },
                () -> {
                    orderCanceledReq.setReasonCode(Integer.MIN_VALUE);
                    assertEquals(Integer.MIN_VALUE, orderCanceledReq.getReasonCode(), "reasonCode设置为Integer.MIN_VALUE应该有效");
                }
            );
        }

        @Test
        @DisplayName("特殊字符处理测试")
        void specialCharacterHandling() {
            assertAll("特殊字符处理测试",
                () -> {
                    String specialOrderId = "ORDER@#$%^&*()";
                    orderCanceledReq.setOrderId(specialOrderId);
                    assertEquals(specialOrderId, orderCanceledReq.getOrderId(), "包含特殊字符的订单ID应该能设置");
                },
                () -> {
                    String unicodeReason = "用户取消订单🚫❌";
                    orderCanceledReq.setReason(unicodeReason);
                    assertEquals(unicodeReason, orderCanceledReq.getReason(), "包含Unicode字符的原因应该能设置");
                },
                () -> {
                    String htmlReason = "<script>alert('test')</script>";
                    orderCanceledReq.setReason(htmlReason);
                    assertEquals(htmlReason, orderCanceledReq.getReason(), "包含HTML的原因应该能设置");
                }
            );
        }

        @Test
        @DisplayName("内存和性能测试")
        void memoryAndPerformanceTests() {
            // 测试大量数据处理
            StringBuilder largeReason = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                largeReason.append("这是一个很长的取消原因描述");
            }
            
            assertDoesNotThrow(() -> {
                orderCanceledReq.setReason(largeReason.toString());
                String result = orderCanceledReq.getReason();
                assertNotNull(result, "大量数据应该能正确处理");
                assertEquals(largeReason.toString(), result, "大量数据应该保持一致性");
            }, "处理大量数据时不应该抛出异常");
        }

        @Test
        @DisplayName("并发访问测试")
        void concurrentAccessTest() throws InterruptedException {
            final OrderCanceledReq sharedReq = new OrderCanceledReq("ORDER123", "SHOP456", "原因", 1, 1640995200L);
            final boolean[] testPassed = {true};
            
            // 创建多个线程同时访问对象
            Thread[] threads = new Thread[10];
            for (int i = 0; i < threads.length; i++) {
                final int threadIndex = i;
                threads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            // 读取操作
                            String orderId = sharedReq.getOrderId();
                            String shopId = sharedReq.getShopId();
                            String reason = sharedReq.getReason();
                            Integer reasonCode = sharedReq.getReasonCode();
                            Long updateTime = sharedReq.getUpdateTime();
                            
                            // 验证数据一致性
                            if (!"ORDER123".equals(orderId) || !"SHOP456".equals(shopId) || 
                                !"原因".equals(reason) || !Integer.valueOf(1).equals(reasonCode) ||
                                !Long.valueOf(1640995200L).equals(updateTime)) {
                                testPassed[0] = false;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        testPassed[0] = false;
                    }
                });
                threads[i].start();
            }
            
            // 等待所有线程完成
            for (Thread thread : threads) {
                thread.join();
            }
            
            assertTrue(testPassed[0], "并发访问应该保持数据一致性");
        }
    }
}