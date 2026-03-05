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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderRefundedReq 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderRefundedReq 单元测试")
class OrderRefundedReqTest {

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
            OrderRefundedReq req = new OrderRefundedReq();
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getRefundId());
            assertNull(req.getStatus());
            assertNull(req.getType());
            assertNull(req.getReason());
            assertNull(req.getTotalPrice());
            assertNull(req.getCount());
            assertNull(req.getGoods());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        private OrderRefundedReq req;

        @BeforeEach
        void setUp() {
            req = new OrderRefundedReq();
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
        @DisplayName("refundId的getter和setter应该正常工作")
        void refundId_GetterSetter_ShouldWork() {
            // Given
            String refundId = "refund123";
            
            // When
            req.setRefundId(refundId);
            
            // Then
            assertEquals(refundId, req.getRefundId());
        }

        @Test
        @DisplayName("status的getter和setter应该正常工作")
        void status_GetterSetter_ShouldWork() {
            // Given
            Integer status = 1;
            
            // When
            req.setStatus(status);
            
            // Then
            assertEquals(status, req.getStatus());
        }

        @Test
        @DisplayName("type的getter和setter应该正常工作")
        void type_GetterSetter_ShouldWork() {
            // Given
            Integer type = 2;
            
            // When
            req.setType(type);
            
            // Then
            assertEquals(type, req.getType());
        }

        @Test
        @DisplayName("reason的getter和setter应该正常工作")
        void reason_GetterSetter_ShouldWork() {
            // Given
            String reason = "商品质量问题";
            
            // When
            req.setReason(reason);
            
            // Then
            assertEquals(reason, req.getReason());
        }

        @Test
        @DisplayName("totalPrice的getter和setter应该正常工作")
        void totalPrice_GetterSetter_ShouldWork() {
            // Given
            Integer totalPrice = 5000;
            
            // When
            req.setTotalPrice(totalPrice);
            
            // Then
            assertEquals(totalPrice, req.getTotalPrice());
        }

        @Test
        @DisplayName("count的getter和setter应该正常工作")
        void count_GetterSetter_ShouldWork() {
            // Given
            Integer count = 3;
            
            // When
            req.setCount(count);
            
            // Then
            assertEquals(count, req.getCount());
        }

        @Test
        @DisplayName("goods的getter和setter应该正常工作")
        void goods_GetterSetter_ShouldWork() {
            // Given
            List<OrderRefundedGoodsInfo> goods = createTestGoodsList();
            
            // When
            req.setGoods(goods);
            
            // Then
            assertEquals(goods, req.getGoods());
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
            OrderRefundedReq req = createValidReq();
            req.setOrderId(null);
            
            // When
            Set<ConstraintViolation<OrderRefundedReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "订单ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("orderId不能为空字符串")
        void orderId_ShouldNotBeEmptyString() {
            // Given
            OrderRefundedReq req = createValidReq();
            req.setOrderId("");
            
            // When
            Set<ConstraintViolation<OrderRefundedReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "订单ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为空")
        void shopId_ShouldNotBeBlank() {
            // Given
            OrderRefundedReq req = createValidReq();
            req.setShopId(null);
            
            // When
            Set<ConstraintViolation<OrderRefundedReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为空字符串")
        void shopId_ShouldNotBeEmptyString() {
            // Given
            OrderRefundedReq req = createValidReq();
            req.setShopId("");
            
            // When
            Set<ConstraintViolation<OrderRefundedReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("有效对象应该通过验证")
        void validObject_ShouldPassValidation() {
            // Given
            OrderRefundedReq req = createValidReq();
            
            // When
            Set<ConstraintViolation<OrderRefundedReq>> violations = validator.validate(req);
            
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
            OrderRefundedReq req = createTestReq();
            
            // When & Then
            assertEquals(req, req);
        }

        @Test
        @DisplayName("具有相同属性的对象应该相等")
        void sameProperties_ShouldBeEqual() {
            // Given
            OrderRefundedReq req1 = createTestReq();
            OrderRefundedReq req2 = createTestReq();
            
            // When & Then
            assertEquals(req1, req2);
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            // Given
            OrderRefundedReq req = createTestReq();
            
            // When & Then
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            // Given
            OrderRefundedReq req = createTestReq();
            String other = "other";
            
            // When & Then
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("orderId不同时应该不相等")
        void differentOrderId_ShouldNotBeEqual() {
            // Given
            OrderRefundedReq req1 = createTestReq();
            OrderRefundedReq req2 = createTestReq();
            req2.setOrderId("different");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("shopId不同时应该不相等")
        void differentShopId_ShouldNotBeEqual() {
            // Given
            OrderRefundedReq req1 = createTestReq();
            OrderRefundedReq req2 = createTestReq();
            req2.setShopId("different");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("status不同时应该不相等")
        void differentStatus_ShouldNotBeEqual() {
            // Given
            OrderRefundedReq req1 = createTestReq();
            OrderRefundedReq req2 = createTestReq();
            req2.setStatus(999);
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("goods不同时应该不相等")
        void differentGoods_ShouldNotBeEqual() {
            // Given
            OrderRefundedReq req1 = createTestReq();
            OrderRefundedReq req2 = createTestReq();
            req2.setGoods(new ArrayList<>());
            
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
            OrderRefundedReq req1 = createTestReq();
            OrderRefundedReq req2 = createTestReq();
            
            // When & Then
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("多次调用应该返回相同的hashCode")
        void multipleInvocations_ShouldReturnSameHashCode() {
            // Given
            OrderRefundedReq req = createTestReq();
            
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
            OrderRefundedReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("OrderRefundedReq"));
        }

        @Test
        @DisplayName("toString应该包含所有属性")
        void toString_ShouldContainAllProperties() {
            // Given
            OrderRefundedReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("orderId"));
            assertTrue(result.contains("shopId"));
            assertTrue(result.contains("refundId"));
            assertTrue(result.contains("status"));
            assertTrue(result.contains("type"));
            assertTrue(result.contains("reason"));
            assertTrue(result.contains("totalPrice"));
            assertTrue(result.contains("count"));
            assertTrue(result.contains("goods"));
            assertTrue(result.contains("updateTime"));
        }

        @Test
        @DisplayName("toString不应该返回null")
        void toString_ShouldNotReturnNull() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应该能够序列化为JSON")
        void object_ShouldSerializeToJson() throws Exception {
            // Given
            OrderRefundedReq req = createTestReq();
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"order123\""));
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"refund_id\":\"refund123\""));
            assertTrue(json.contains("\"status\":1"));
            assertTrue(json.contains("\"type\":2"));
            assertTrue(json.contains("\"reason\":\"商品质量问题\""));
            assertTrue(json.contains("\"total_price\":5000"));
            assertTrue(json.contains("\"count\":3"));
            assertTrue(json.contains("\"goods\":["));
            assertTrue(json.contains("\"update_time\""));
        }

        @Test
        @DisplayName("JSON应该能够反序列化为对象")
        void json_ShouldDeserializeToObject() throws Exception {
            // Given
            String json = "{\"order_id\":\"order123\",\"shop_id\":\"shop123\"," +
                         "\"refund_id\":\"refund123\",\"status\":1,\"type\":2," +
                         "\"reason\":\"商品质量问题\",\"total_price\":5000,\"count\":3," +
                         "\"goods\":[],\"update_time\":1640995200}";
            
            // When
            OrderRefundedReq req = objectMapper.readValue(json, OrderRefundedReq.class);
            
            // Then
            assertNotNull(req);
            assertEquals("order123", req.getOrderId());
            assertEquals("shop123", req.getShopId());
            assertEquals("refund123", req.getRefundId());
            assertEquals(Integer.valueOf(1), req.getStatus());
            assertEquals(Integer.valueOf(2), req.getType());
            assertEquals("商品质量问题", req.getReason());
            assertEquals(Integer.valueOf(5000), req.getTotalPrice());
            assertEquals(Integer.valueOf(3), req.getCount());
            assertNotNull(req.getGoods());
            assertEquals(Long.valueOf(1640995200L), req.getUpdateTime());
        }

        @Test
        @DisplayName("序列化后再反序列化应该得到相等的对象")
        void serializeAndDeserialize_ShouldGetEqualObject() throws Exception {
            // Given
            OrderRefundedReq original = createTestReq();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderRefundedReq deserialized = objectMapper.readValue(json, OrderRefundedReq.class);
            
            // Then
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("含有复杂goods列表的对象应该能够序列化")
        void objectWithComplexGoods_ShouldSerialize() throws Exception {
            // Given
            OrderRefundedReq req = createTestReq();
            req.setGoods(createComplexGoodsList());
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"goods\":["));
        }

        @Test
        @DisplayName("空的goods列表应该能够序列化")
        void objectWithEmptyGoods_ShouldSerialize() throws Exception {
            // Given
            OrderRefundedReq req = createTestReq();
            req.setGoods(new ArrayList<>());
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"goods\":[]"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("所有字段为null时应该正常工作")
        void allFieldsNull_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            
            // When & Then
            assertDoesNotThrow(() -> {
                req.toString();
                req.hashCode();
                req.equals(new OrderRefundedReq());
            });
        }

        @Test
        @DisplayName("数字字段为0时应该正常工作")
        void numericFieldsZero_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            req.setStatus(0);
            req.setType(0);
            req.setTotalPrice(0);
            req.setCount(0);
            req.setUpdateTime(0L);
            
            // When & Then
            assertDoesNotThrow(() -> {
                assertEquals(Integer.valueOf(0), req.getStatus());
                assertEquals(Integer.valueOf(0), req.getType());
                assertEquals(Integer.valueOf(0), req.getTotalPrice());
                assertEquals(Integer.valueOf(0), req.getCount());
                assertEquals(Long.valueOf(0L), req.getUpdateTime());
            });
        }

        @Test
        @DisplayName("数字字段为负数时应该正常工作")
        void numericFieldsNegative_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            req.setStatus(-1);
            req.setType(-1);
            req.setTotalPrice(-100);
            req.setCount(-1);
            req.setUpdateTime(-1L);
            
            // When & Then
            assertDoesNotThrow(() -> {
                assertEquals(Integer.valueOf(-1), req.getStatus());
                assertEquals(Integer.valueOf(-1), req.getType());
                assertEquals(Integer.valueOf(-100), req.getTotalPrice());
                assertEquals(Integer.valueOf(-1), req.getCount());
                assertEquals(Long.valueOf(-1L), req.getUpdateTime());
            });
        }

        @Test
        @DisplayName("字符串字段为空字符串时应该正常工作")
        void stringFieldsEmpty_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            req.setOrderId("");
            req.setShopId("");
            req.setRefundId("");
            req.setReason("");
            
            // When & Then
            assertEquals("", req.getOrderId());
            assertEquals("", req.getShopId());
            assertEquals("", req.getRefundId());
            assertEquals("", req.getReason());
        }

        @Test
        @DisplayName("字符串字段包含特殊字符时应该正常工作")
        void stringFieldsWithSpecialCharacters_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            String specialText = "测试@#$%^&*()_+{}|:<>?[]\\;'\",./-=`~";
            req.setOrderId(specialText);
            req.setShopId(specialText);
            req.setRefundId(specialText);
            req.setReason(specialText);
            
            // When & Then
            assertEquals(specialText, req.getOrderId());
            assertEquals(specialText, req.getShopId());
            assertEquals(specialText, req.getRefundId());
            assertEquals(specialText, req.getReason());
        }

        @Test
        @DisplayName("数字字段为最大值时应该正常工作")
        void numericFieldsMaxValue_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            req.setStatus(Integer.MAX_VALUE);
            req.setType(Integer.MAX_VALUE);
            req.setTotalPrice(Integer.MAX_VALUE);
            req.setCount(Integer.MAX_VALUE);
            req.setUpdateTime(Long.MAX_VALUE);
            
            // When & Then
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), req.getStatus());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), req.getType());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), req.getTotalPrice());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), req.getCount());
            assertEquals(Long.valueOf(Long.MAX_VALUE), req.getUpdateTime());
        }

        @Test
        @DisplayName("goods列表为null时应该正常工作")
        void goodsListNull_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            req.setGoods(null);
            
            // When & Then
            assertNull(req.getGoods());
            assertDoesNotThrow(() -> {
                req.toString();
                req.hashCode();
            });
        }

        @Test
        @DisplayName("goods列表为空时应该正常工作")
        void goodsListEmpty_ShouldWork() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            req.setGoods(new ArrayList<>());
            
            // When & Then
            assertNotNull(req.getGoods());
            assertTrue(req.getGoods().isEmpty());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("退款状态应该支持有效值")
        void refundStatus_ShouldSupportValidValues() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            
            // When & Then - 退款成功
            req.setStatus(1);
            assertEquals(Integer.valueOf(1), req.getStatus());
            
            // 退款失败
            req.setStatus(2);
            assertEquals(Integer.valueOf(2), req.getStatus());
            
            // 退款处理中
            req.setStatus(3);
            assertEquals(Integer.valueOf(3), req.getStatus());
        }

        @Test
        @DisplayName("退款类型应该支持有效值")
        void refundType_ShouldSupportValidValues() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            
            // When & Then - 仅退款
            req.setType(1);
            assertEquals(Integer.valueOf(1), req.getType());
            
            // 退货退款
            req.setType(2);
            assertEquals(Integer.valueOf(2), req.getType());
        }

        @Test
        @DisplayName("退款商品列表应该能够正确管理")
        void goodsList_ShouldBeProperlyManaged() {
            // Given
            OrderRefundedReq req = new OrderRefundedReq();
            List<OrderRefundedGoodsInfo> goods = createTestGoodsList();
            
            // When
            req.setGoods(goods);
            
            // Then
            assertEquals(goods.size(), req.getGoods().size());
            assertEquals(goods.get(0), req.getGoods().get(0));
        }
    }

    /**
     * 创建测试用的OrderRefundedReq对象
     */
    private OrderRefundedReq createTestReq() {
        OrderRefundedReq req = new OrderRefundedReq();
        req.setOrderId("order123");
        req.setShopId("shop123");
        req.setRefundId("refund123");
        req.setStatus(1);
        req.setType(2);
        req.setReason("商品质量问题");
        req.setTotalPrice(5000);
        req.setCount(3);
        req.setGoods(createTestGoodsList());
        req.setUpdateTime(1640995200L);
        return req;
    }

    /**
     * 创建有效的OrderRefundedReq对象（通过验证）
     */
    private OrderRefundedReq createValidReq() {
        OrderRefundedReq req = new OrderRefundedReq();
        req.setOrderId("order123");
        req.setShopId("shop123");
        req.setRefundId("refund123");
        req.setStatus(1);
        req.setType(2);
        req.setReason("商品质量问题");
        req.setTotalPrice(5000);
        req.setCount(3);
        req.setGoods(createTestGoodsList());
        req.setUpdateTime(1640995200L);
        return req;
    }

    /**
     * 创建测试用的商品列表
     */
    private List<OrderRefundedGoodsInfo> createTestGoodsList() {
        OrderRefundedGoodsInfo goods = new OrderRefundedGoodsInfo();
        goods.setGoodsId("goods123");
        goods.setGoodsName("测试商品");
        goods.setSkuId("sku123");
        goods.setUpc("123456789012");
        goods.setShelfNo("A01-B02");
        goods.setNumber(2);
        goods.setGoodsTotalFee(2500);
        goods.setPackageNumber(1);
        goods.setPackageFee(50);
        goods.setRefundFee(2550);
        
        return Arrays.asList(goods);
    }

    /**
     * 创建复杂的测试商品列表
     */
    private List<OrderRefundedGoodsInfo> createComplexGoodsList() {
        List<OrderRefundedGoodsInfo> goodsList = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            OrderRefundedGoodsInfo goods = new OrderRefundedGoodsInfo();
            goods.setGoodsId("goods" + i);
            goods.setGoodsName("测试商品" + i);
            goods.setSkuId("sku" + i);
            goods.setUpc("123456789" + String.format("%03d", i));
            goods.setShelfNo("A" + String.format("%02d", i) + "-B01");
            goods.setNumber(i + 1);
            goods.setGoodsTotalFee(1000 * (i + 1));
            goods.setPackageNumber(1);
            goods.setPackageFee(50);
            goods.setRefundFee(1050 * (i + 1));
            goodsList.add(goods);
        }
        
        return goodsList;
    }
}