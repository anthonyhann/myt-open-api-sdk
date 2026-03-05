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
 * OrderConfirmedReq单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderConfirmedReq 测试")
class OrderConfirmedReqTest {

    private ObjectMapper objectMapper;
    private OrderConfirmedReq orderConfirmedReq;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderConfirmedReq = new OrderConfirmedReq();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            OrderConfirmedReq req = new OrderConfirmedReq();
            
            assertAll("默认构造函数验证",
                () -> assertNull(req.getOrderId(), "orderId应该为null"),
                () -> assertNull(req.getShopId(), "shopId应该为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void allArgsConstructor_ShouldSetAllFields() {
            // 准备测试数据
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            Long updateTime = 1640995200L; // 2022-01-01 00:00:00 UTC
            
            // 测试构造函数
            OrderConfirmedReq req = new OrderConfirmedReq(orderId, shopId, updateTime);
            
            assertAll("全参构造函数验证",
                () -> assertEquals(orderId, req.getOrderId(), "orderId应该被正确设置"),
                () -> assertEquals(shopId, req.getShopId(), "shopId应该被正确设置"),
                () -> assertEquals(updateTime, req.getUpdateTime(), "updateTime应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void allArgsConstructor_ShouldAcceptNullValues() {
            OrderConfirmedReq req = new OrderConfirmedReq(null, null, null);
            
            assertAll("null值构造函数验证",
                () -> assertNull(req.getOrderId(), "orderId可以为null"),
                () -> assertNull(req.getShopId(), "shopId可以为null"),
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
            
            orderConfirmedReq.setOrderId(orderId);
            assertEquals(orderId, orderConfirmedReq.getOrderId(), "getter应该返回setter设置的值");
            
            orderConfirmedReq.setOrderId(null);
            assertNull(orderConfirmedReq.getOrderId(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("shopId的getter和setter应该正常工作")
        void shopIdGetterSetter_ShouldWorkCorrectly() {
            String shopId = "SHOP456";
            
            orderConfirmedReq.setShopId(shopId);
            assertEquals(shopId, orderConfirmedReq.getShopId(), "getter应该返回setter设置的值");
            
            orderConfirmedReq.setShopId(null);
            assertNull(orderConfirmedReq.getShopId(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("updateTime的getter和setter应该正常工作")
        void updateTimeGetterSetter_ShouldWorkCorrectly() {
            Long updateTime = 1640995200L;
            
            orderConfirmedReq.setUpdateTime(updateTime);
            assertEquals(updateTime, orderConfirmedReq.getUpdateTime(), "getter应该返回setter设置的值");
            
            orderConfirmedReq.setUpdateTime(null);
            assertNull(orderConfirmedReq.getUpdateTime(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("链式调用测试")
        void chainedSetterCalls_ShouldWork() {
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            Long updateTime = 1640995200L;
            
            orderConfirmedReq.setOrderId(orderId);
            orderConfirmedReq.setShopId(shopId);
            orderConfirmedReq.setUpdateTime(updateTime);
            
            assertAll("链式调用验证",
                () -> assertEquals(orderId, orderConfirmedReq.getOrderId()),
                () -> assertEquals(shopId, orderConfirmedReq.getShopId()),
                () -> assertEquals(updateTime, orderConfirmedReq.getUpdateTime())
            );
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertEquals(orderConfirmedReq, orderConfirmedReq, "对象应该与自身相等");
        }

        @Test
        @DisplayName("相同内容的不同对象应该相等")
        void differentObjectsWithSameContent_ShouldBeEqual() {
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            Long updateTime = 1640995200L;
            
            OrderConfirmedReq req1 = new OrderConfirmedReq(orderId, shopId, updateTime);
            OrderConfirmedReq req2 = new OrderConfirmedReq(orderId, shopId, updateTime);
            
            assertEquals(req1, req2, "内容相同的对象应该相等");
        }

        @Test
        @DisplayName("不同orderId的对象不应该相等")
        void differentOrderId_ShouldNotBeEqual() {
            OrderConfirmedReq req1 = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            OrderConfirmedReq req2 = new OrderConfirmedReq("ORDER789", "SHOP456", 1640995200L);
            
            assertNotEquals(req1, req2, "不同orderId的对象不应该相等");
        }

        @Test
        @DisplayName("不同shopId的对象不应该相等")
        void differentShopId_ShouldNotBeEqual() {
            OrderConfirmedReq req1 = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            OrderConfirmedReq req2 = new OrderConfirmedReq("ORDER123", "SHOP789", 1640995200L);
            
            assertNotEquals(req1, req2, "不同shopId的对象不应该相等");
        }

        @Test
        @DisplayName("不同updateTime的对象不应该相等")
        void differentUpdateTime_ShouldNotBeEqual() {
            OrderConfirmedReq req1 = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            OrderConfirmedReq req2 = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995300L);
            
            assertNotEquals(req1, req2, "不同updateTime的对象不应该相等");
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            assertNotEquals(orderConfirmedReq, null, "对象与null比较应该返回false");
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            assertNotEquals(orderConfirmedReq, "string", "与不同类型对象比较应该返回false");
        }

        @Test
        @DisplayName("null字段的对象比较")
        void objectsWithNullFields_ShouldBeComparedCorrectly() {
            OrderConfirmedReq req1 = new OrderConfirmedReq(null, null, null);
            OrderConfirmedReq req2 = new OrderConfirmedReq(null, null, null);
            
            assertEquals(req1, req2, "都是null字段的对象应该相等");
        }

        @Test
        @DisplayName("部分字段为null的对象比较")
        void objectsWithPartialNullFields_ShouldBeComparedCorrectly() {
            OrderConfirmedReq req1 = new OrderConfirmedReq("ORDER123", null, null);
            OrderConfirmedReq req2 = new OrderConfirmedReq(null, null, null);
            
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
            Long updateTime = 1640995200L;
            
            OrderConfirmedReq req1 = new OrderConfirmedReq(orderId, shopId, updateTime);
            OrderConfirmedReq req2 = new OrderConfirmedReq(orderId, shopId, updateTime);
            
            assertEquals(req1.hashCode(), req2.hashCode(), "相同对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致性")
        void hashCode_ShouldBeConsistent() {
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            int firstHashCode = req.hashCode();
            int secondHashCode = req.hashCode();
            
            assertEquals(firstHashCode, secondHashCode, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段不应该导致hashCode异常")
        void nullFields_ShouldNotCauseHashCodeException() {
            OrderConfirmedReq req = new OrderConfirmedReq(null, null, null);
            
            assertDoesNotThrow(() -> req.hashCode(), "null字段不应该导致hashCode异常");
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含类名")
        void toString_ShouldContainClassName() {
            String result = orderConfirmedReq.toString();
            assertTrue(result.contains("OrderConfirmedReq"), "toString应该包含类名");
        }

        @Test
        @DisplayName("toString应该包含所有字段")
        void toString_ShouldContainAllFields() {
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            String result = req.toString();
            
            assertAll("toString字段验证",
                () -> assertTrue(result.contains("orderId="), "应该包含orderId字段"),
                () -> assertTrue(result.contains("shopId="), "应该包含shopId字段"),
                () -> assertTrue(result.contains("updateTime="), "应该包含updateTime字段")
            );
        }

        @Test
        @DisplayName("toString应该包含具体的值")
        void toString_ShouldContainSpecificValues() {
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            String result = req.toString();
            
            assertAll("toString值验证",
                () -> assertTrue(result.contains("ORDER123"), "应该包含orderId的值"),
                () -> assertTrue(result.contains("SHOP456"), "应该包含shopId的值"),
                () -> assertTrue(result.contains("1640995200"), "应该包含updateTime的值")
            );
        }

        @Test
        @DisplayName("toString应该处理null值")
        void toString_ShouldHandleNullValues() {
            OrderConfirmedReq req = new OrderConfirmedReq(null, null, null);
            String result = req.toString();
            
            assertAll("toString null值验证",
                () -> assertTrue(result.contains("orderId=null") || result.contains("orderId='null'"), "应该正确显示null的orderId"),
                () -> assertTrue(result.contains("shopId=null") || result.contains("shopId='null'"), "应该正确显示null的shopId"),
                () -> assertTrue(result.contains("updateTime=null"), "应该正确显示null的updateTime")
            );
        }

        @Test
        @DisplayName("toString不应该抛出异常")
        void toString_ShouldNotThrowException() {
            assertDoesNotThrow(() -> orderConfirmedReq.toString(), "toString不应该抛出异常");
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该能够序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            
            assertDoesNotThrow(() -> objectMapper.writeValueAsString(req), 
                "应该能够序列化为JSON");
        }

        @Test
        @DisplayName("应该能够从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"order_id\":\"ORDER123\",\"shop_id\":\"SHOP456\",\"update_time\":1640995200}";
            
            OrderConfirmedReq req = objectMapper.readValue(json, OrderConfirmedReq.class);
            
            assertAll("JSON反序列化验证",
                () -> assertNotNull(req, "反序列化结果不应该为null"),
                () -> assertEquals("ORDER123", req.getOrderId(), "orderId应该正确反序列化"),
                () -> assertEquals("SHOP456", req.getShopId(), "shopId应该正确反序列化"),
                () -> assertEquals(1640995200L, req.getUpdateTime(), "updateTime应该正确反序列化")
            );
        }

        @Test
        @DisplayName("JSON字段映射应该正确")
        void jsonFieldMapping_ShouldBeCorrect() throws Exception {
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            String json = objectMapper.writeValueAsString(req);
            
            assertAll("JSON字段映射验证",
                () -> assertTrue(json.contains("\"order_id\""), "应该包含order_id字段"),
                () -> assertTrue(json.contains("\"shop_id\""), "应该包含shop_id字段"),
                () -> assertTrue(json.contains("\"update_time\""), "应该包含update_time字段")
            );
        }

        @Test
        @DisplayName("空对象应该能够序列化和反序列化")
        void emptyObject_ShouldSerializeAndDeserialize() throws Exception {
            OrderConfirmedReq original = new OrderConfirmedReq();
            
            String json = objectMapper.writeValueAsString(original);
            OrderConfirmedReq deserialized = objectMapper.readValue(json, OrderConfirmedReq.class);
            
            assertAll("空对象序列化验证",
                () -> assertNotNull(json, "JSON不应该为null"),
                () -> assertNotNull(deserialized, "反序列化对象不应该为null"),
                () -> assertNull(deserialized.getOrderId(), "反序列化后orderId应该为null"),
                () -> assertNull(deserialized.getShopId(), "反序列化后shopId应该为null"),
                () -> assertNull(deserialized.getUpdateTime(), "反序列化后updateTime应该为null")
            );
        }

        @Test
        @DisplayName("序列化后反序列化应该保持数据一致性")
        void serializeDeserialize_ShouldMaintainDataIntegrity() throws Exception {
            OrderConfirmedReq original = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            
            String json = objectMapper.writeValueAsString(original);
            OrderConfirmedReq deserialized = objectMapper.readValue(json, OrderConfirmedReq.class);
            
            assertEquals(original, deserialized, "序列化后反序列化应该保持对象相等性");
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("验证确认时间的合理性")
        @JsonIgnore
        void validateConfirmationTime() {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long pastTime = currentTime - 3600; // 1小时前
            Long futureTime = currentTime + 300; // 5分钟后（允许少量时间偏差）
            
            assertAll("确认时间验证",
                () -> {
                    orderConfirmedReq.setUpdateTime(currentTime);
                    assertEquals(currentTime, orderConfirmedReq.getUpdateTime(), "当前时间应该有效");
                },
                () -> {
                    orderConfirmedReq.setUpdateTime(pastTime);
                    assertEquals(pastTime, orderConfirmedReq.getUpdateTime(), "过去时间应该有效");
                },
                () -> {
                    orderConfirmedReq.setUpdateTime(futureTime);
                    assertEquals(futureTime, orderConfirmedReq.getUpdateTime(), "近期未来时间应该能设置");
                }
            );
        }

        @Test
        @DisplayName("验证订单确认的必要字段")
        @JsonIgnore
        void validateRequiredFieldsForConfirmation() {
            // 模拟业务场景：订单确认必须包含orderId和shopId
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", System.currentTimeMillis() / 1000);
            
            assertAll("必要字段验证",
                () -> assertNotNull(req.getOrderId(), "确认订单必须有orderId"),
                () -> assertFalse(req.getOrderId().trim().isEmpty(), "orderId不能为空字符串"),
                () -> assertNotNull(req.getShopId(), "确认订单必须有shopId"),
                () -> assertFalse(req.getShopId().trim().isEmpty(), "shopId不能为空字符串")
            );
        }

        @Test
        @DisplayName("验证订单ID格式合法性")
        @JsonIgnore
        void validateOrderIdFormat() {
            assertAll("订单ID格式验证",
                () -> {
                    String validId = "ORDER_2022_001";
                    orderConfirmedReq.setOrderId(validId);
                    assertEquals(validId, orderConfirmedReq.getOrderId(), "包含下划线的订单ID应该有效");
                },
                () -> {
                    String numericId = "123456789";
                    orderConfirmedReq.setOrderId(numericId);
                    assertEquals(numericId, orderConfirmedReq.getOrderId(), "纯数字订单ID应该有效");
                },
                () -> {
                    String mixedId = "ORD123ABC";
                    orderConfirmedReq.setOrderId(mixedId);
                    assertEquals(mixedId, orderConfirmedReq.getOrderId(), "字母数字混合订单ID应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证门店ID格式合法性")
        @JsonIgnore
        void validateShopIdFormat() {
            assertAll("门店ID格式验证",
                () -> {
                    String validShopId = "SHOP_001";
                    orderConfirmedReq.setShopId(validShopId);
                    assertEquals(validShopId, orderConfirmedReq.getShopId(), "包含下划线的门店ID应该有效");
                },
                () -> {
                    String numericShopId = "1001";
                    orderConfirmedReq.setShopId(numericShopId);
                    assertEquals(numericShopId, orderConfirmedReq.getShopId(), "纯数字门店ID应该有效");
                },
                () -> {
                    String mixedShopId = "STORE123";
                    orderConfirmedReq.setShopId(mixedShopId);
                    assertEquals(mixedShopId, orderConfirmedReq.getShopId(), "字母数字混合门店ID应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证订单确认状态转换的合法性")
        @JsonIgnore
        void validateOrderConfirmationStateTransition() {
            // 模拟订单状态：未确认 -> 已确认
            OrderConfirmedReq req = new OrderConfirmedReq("ORDER123", "SHOP456", System.currentTimeMillis() / 1000);
            
            assertAll("状态转换验证",
                () -> assertNotNull(req.getOrderId(), "确认时订单ID必须存在"),
                () -> assertNotNull(req.getShopId(), "确认时门店ID必须存在"),
                () -> assertNotNull(req.getUpdateTime(), "确认时间必须记录"),
                () -> assertTrue(req.getUpdateTime() > 0, "确认时间必须是有效的时间戳")
            );
        }

        @Test
        @DisplayName("验证重复确认的处理")
        @JsonIgnore
        void validateDuplicateConfirmationHandling() {
            // 测试相同订单的重复确认请求
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            Long firstConfirmTime = System.currentTimeMillis() / 1000;
            Long secondConfirmTime = firstConfirmTime + 300; // 5分钟后
            
            OrderConfirmedReq firstConfirm = new OrderConfirmedReq(orderId, shopId, firstConfirmTime);
            OrderConfirmedReq secondConfirm = new OrderConfirmedReq(orderId, shopId, secondConfirmTime);
            
            assertAll("重复确认验证",
                () -> assertEquals(orderId, firstConfirm.getOrderId(), "第一次确认的订单ID应该正确"),
                () -> assertEquals(orderId, secondConfirm.getOrderId(), "第二次确认的订单ID应该相同"),
                () -> assertEquals(shopId, firstConfirm.getShopId(), "第一次确认的门店ID应该正确"),
                () -> assertEquals(shopId, secondConfirm.getShopId(), "第二次确认的门店ID应该相同"),
                () -> assertNotEquals(firstConfirm, secondConfirm, "不同时间的确认请求应该被视为不同对象")
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
                    orderConfirmedReq.setUpdateTime(0L);
                    assertEquals(0L, orderConfirmedReq.getUpdateTime(), "updateTime设置为0应该有效");
                },
                () -> {
                    orderConfirmedReq.setUpdateTime(Long.MAX_VALUE);
                    assertEquals(Long.MAX_VALUE, orderConfirmedReq.getUpdateTime(), "updateTime设置为Long.MAX_VALUE应该有效");
                },
                () -> {
                    orderConfirmedReq.setUpdateTime(Long.MIN_VALUE);
                    assertEquals(Long.MIN_VALUE, orderConfirmedReq.getUpdateTime(), "updateTime设置为Long.MIN_VALUE应该有效");
                }
            );
        }

        @Test
        @DisplayName("特殊字符处理测试")
        void specialCharacterHandling() {
            assertAll("特殊字符处理测试",
                () -> {
                    String specialOrderId = "ORDER@#$%^&*()";
                    orderConfirmedReq.setOrderId(specialOrderId);
                    assertEquals(specialOrderId, orderConfirmedReq.getOrderId(), "包含特殊字符的订单ID应该能设置");
                },
                () -> {
                    String unicodeShopId = "店铺_001";
                    orderConfirmedReq.setShopId(unicodeShopId);
                    assertEquals(unicodeShopId, orderConfirmedReq.getShopId(), "包含中文的门店ID应该能设置");
                },
                () -> {
                    String htmlShopId = "<shop>001</shop>";
                    orderConfirmedReq.setShopId(htmlShopId);
                    assertEquals(htmlShopId, orderConfirmedReq.getShopId(), "包含HTML的门店ID应该能设置");
                }
            );
        }

        @Test
        @DisplayName("长字符串处理测试")
        void longStringHandling() {
            // 测试超长订单ID和门店ID
            StringBuilder longOrderId = new StringBuilder();
            StringBuilder longShopId = new StringBuilder();
            
            for (int i = 0; i < 1000; i++) {
                longOrderId.append("ORDER");
                longShopId.append("SHOP");
            }
            
            assertDoesNotThrow(() -> {
                orderConfirmedReq.setOrderId(longOrderId.toString());
                orderConfirmedReq.setShopId(longShopId.toString());
                
                assertEquals(longOrderId.toString(), orderConfirmedReq.getOrderId(), "长订单ID应该能正确设置");
                assertEquals(longShopId.toString(), orderConfirmedReq.getShopId(), "长门店ID应该能正确设置");
            }, "处理长字符串时不应该抛出异常");
        }

        @Test
        @DisplayName("内存压力测试")
        void memoryPressureTest() {
            // 创建大量OrderConfirmedReq对象测试内存使用
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 10000; i++) {
                    OrderConfirmedReq req = new OrderConfirmedReq("ORDER" + i, "SHOP" + i, (long) i);
                    // 验证对象创建正常
                    assertNotNull(req.getOrderId(), "订单ID应该不为null");
                    assertNotNull(req.getShopId(), "门店ID应该不为null");
                    assertNotNull(req.getUpdateTime(), "更新时间应该不为null");
                }
            }, "创建大量对象时不应该抛出异常");
        }

        @Test
        @DisplayName("并发访问测试")
        void concurrentAccessTest() throws InterruptedException {
            final OrderConfirmedReq sharedReq = new OrderConfirmedReq("ORDER123", "SHOP456", 1640995200L);
            final boolean[] testPassed = {true};
            
            // 创建多个线程同时访问对象
            Thread[] threads = new Thread[10];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            // 读取操作
                            String orderId = sharedReq.getOrderId();
                            String shopId = sharedReq.getShopId();
                            Long updateTime = sharedReq.getUpdateTime();
                            
                            // 验证数据一致性
                            if (!"ORDER123".equals(orderId) || !"SHOP456".equals(shopId) || 
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

        @Test
        @DisplayName("对象状态不变性测试")
        void objectImmutabilityAfterCreation() {
            String orderId = "ORDER123";
            String shopId = "SHOP456";
            Long updateTime = 1640995200L;
            
            OrderConfirmedReq req = new OrderConfirmedReq(orderId, shopId, updateTime);
            
            // 保存原始值
            String originalOrderId = req.getOrderId();
            String originalShopId = req.getShopId();
            Long originalUpdateTime = req.getUpdateTime();
            
            // 多次访问getter
            req.getOrderId();
            req.getShopId();
            req.getUpdateTime();
            
            assertAll("对象状态不变性验证",
                () -> assertEquals(originalOrderId, req.getOrderId(), "多次调用getter后orderId应该保持不变"),
                () -> assertEquals(originalShopId, req.getShopId(), "多次调用getter后shopId应该保持不变"),
                () -> assertEquals(originalUpdateTime, req.getUpdateTime(), "多次调用getter后updateTime应该保持不变")
            );
        }
    }
}