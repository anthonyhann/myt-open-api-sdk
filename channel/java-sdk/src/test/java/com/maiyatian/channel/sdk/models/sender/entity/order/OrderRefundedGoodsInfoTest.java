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
 * OrderRefundedGoodsInfo 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderRefundedGoodsInfo 单元测试")
class OrderRefundedGoodsInfoTest {

    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            // When
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            
            // Then
            assertNotNull(info);
            assertNull(info.getGoodsId());
            assertNull(info.getGoodsName());
            assertNull(info.getSkuId());
            assertNull(info.getUpc());
            assertNull(info.getShelfNo());
            assertNull(info.getNumber());
            assertNull(info.getGoodsTotalFee());
            assertNull(info.getPackageNumber());
            assertNull(info.getPackageFee());
            assertNull(info.getRefundFee());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        private OrderRefundedGoodsInfo info;

        @BeforeEach
        void setUp() {
            info = new OrderRefundedGoodsInfo();
        }

        @Test
        @DisplayName("goodsId的getter和setter应该正常工作")
        void goodsId_GetterSetter_ShouldWork() {
            // Given
            String goodsId = "goods123";
            
            // When
            info.setGoodsId(goodsId);
            
            // Then
            assertEquals(goodsId, info.getGoodsId());
        }

        @Test
        @DisplayName("goodsName的getter和setter应该正常工作")
        void goodsName_GetterSetter_ShouldWork() {
            // Given
            String goodsName = "测试商品";
            
            // When
            info.setGoodsName(goodsName);
            
            // Then
            assertEquals(goodsName, info.getGoodsName());
        }

        @Test
        @DisplayName("skuId的getter和setter应该正常工作")
        void skuId_GetterSetter_ShouldWork() {
            // Given
            String skuId = "sku123";
            
            // When
            info.setSkuId(skuId);
            
            // Then
            assertEquals(skuId, info.getSkuId());
        }

        @Test
        @DisplayName("upc的getter和setter应该正常工作")
        void upc_GetterSetter_ShouldWork() {
            // Given
            String upc = "123456789012";
            
            // When
            info.setUpc(upc);
            
            // Then
            assertEquals(upc, info.getUpc());
        }

        @Test
        @DisplayName("shelfNo的getter和setter应该正常工作")
        void shelfNo_GetterSetter_ShouldWork() {
            // Given
            String shelfNo = "A01-B02";
            
            // When
            info.setShelfNo(shelfNo);
            
            // Then
            assertEquals(shelfNo, info.getShelfNo());
        }

        @Test
        @DisplayName("number的getter和setter应该正常工作")
        void number_GetterSetter_ShouldWork() {
            // Given
            Integer number = 5;
            
            // When
            info.setNumber(number);
            
            // Then
            assertEquals(number, info.getNumber());
        }

        @Test
        @DisplayName("goodsTotalFee的getter和setter应该正常工作")
        void goodsTotalFee_GetterSetter_ShouldWork() {
            // Given
            Integer goodsTotalFee = 2500;
            
            // When
            info.setGoodsTotalFee(goodsTotalFee);
            
            // Then
            assertEquals(goodsTotalFee, info.getGoodsTotalFee());
        }

        @Test
        @DisplayName("packageNumber的getter和setter应该正常工作")
        void packageNumber_GetterSetter_ShouldWork() {
            // Given
            Integer packageNumber = 2;
            
            // When
            info.setPackageNumber(packageNumber);
            
            // Then
            assertEquals(packageNumber, info.getPackageNumber());
        }

        @Test
        @DisplayName("packageFee的getter和setter应该正常工作")
        void packageFee_GetterSetter_ShouldWork() {
            // Given
            Integer packageFee = 100;
            
            // When
            info.setPackageFee(packageFee);
            
            // Then
            assertEquals(packageFee, info.getPackageFee());
        }

        @Test
        @DisplayName("refundFee的getter和setter应该正常工作")
        void refundFee_GetterSetter_ShouldWork() {
            // Given
            Integer refundFee = 2600;
            
            // When
            info.setRefundFee(refundFee);
            
            // Then
            assertEquals(refundFee, info.getRefundFee());
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            // Given
            OrderRefundedGoodsInfo info = createTestInfo();
            
            // When & Then
            assertEquals(info, info);
        }

        @Test
        @DisplayName("具有相同属性的对象应该相等")
        void sameProperties_ShouldBeEqual() {
            // Given
            OrderRefundedGoodsInfo info1 = createTestInfo();
            OrderRefundedGoodsInfo info2 = createTestInfo();
            
            // When & Then
            assertEquals(info1, info2);
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            // Given
            OrderRefundedGoodsInfo info = createTestInfo();
            
            // When & Then
            assertNotEquals(info, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            // Given
            OrderRefundedGoodsInfo info = createTestInfo();
            String other = "other";
            
            // When & Then
            assertNotEquals(info, other);
        }

        @Test
        @DisplayName("goodsId不同时应该不相等")
        void differentGoodsId_ShouldNotBeEqual() {
            // Given
            OrderRefundedGoodsInfo info1 = createTestInfo();
            OrderRefundedGoodsInfo info2 = createTestInfo();
            info2.setGoodsId("different");
            
            // When & Then
            assertNotEquals(info1, info2);
        }

        @Test
        @DisplayName("goodsName不同时应该不相等")
        void differentGoodsName_ShouldNotBeEqual() {
            // Given
            OrderRefundedGoodsInfo info1 = createTestInfo();
            OrderRefundedGoodsInfo info2 = createTestInfo();
            info2.setGoodsName("different");
            
            // When & Then
            assertNotEquals(info1, info2);
        }

        @Test
        @DisplayName("number不同时应该不相等")
        void differentNumber_ShouldNotBeEqual() {
            // Given
            OrderRefundedGoodsInfo info1 = createTestInfo();
            OrderRefundedGoodsInfo info2 = createTestInfo();
            info2.setNumber(999);
            
            // When & Then
            assertNotEquals(info1, info2);
        }

        @Test
        @DisplayName("refundFee不同时应该不相等")
        void differentRefundFee_ShouldNotBeEqual() {
            // Given
            OrderRefundedGoodsInfo info1 = createTestInfo();
            OrderRefundedGoodsInfo info2 = createTestInfo();
            info2.setRefundFee(999);
            
            // When & Then
            assertNotEquals(info1, info2);
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相等对象应该有相同的hashCode")
        void equalObjects_ShouldHaveSameHashCode() {
            // Given
            OrderRefundedGoodsInfo info1 = createTestInfo();
            OrderRefundedGoodsInfo info2 = createTestInfo();
            
            // When & Then
            assertEquals(info1.hashCode(), info2.hashCode());
        }

        @Test
        @DisplayName("多次调用应该返回相同的hashCode")
        void multipleInvocations_ShouldReturnSameHashCode() {
            // Given
            OrderRefundedGoodsInfo info = createTestInfo();
            
            // When
            int hashCode1 = info.hashCode();
            int hashCode2 = info.hashCode();
            
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
            OrderRefundedGoodsInfo info = createTestInfo();
            
            // When
            String result = info.toString();
            
            // Then
            assertTrue(result.contains("OrderRefundedGoodsInfo"));
        }

        @Test
        @DisplayName("toString应该包含所有属性")
        void toString_ShouldContainAllProperties() {
            // Given
            OrderRefundedGoodsInfo info = createTestInfo();
            
            // When
            String result = info.toString();
            
            // Then
            assertTrue(result.contains("goodsId"));
            assertTrue(result.contains("goodsName"));
            assertTrue(result.contains("skuId"));
            assertTrue(result.contains("upc"));
            assertTrue(result.contains("shelfNo"));
            assertTrue(result.contains("number"));
            assertTrue(result.contains("goodsTotalFee"));
            assertTrue(result.contains("packageNumber"));
            assertTrue(result.contains("packageFee"));
            assertTrue(result.contains("refundFee"));
        }

        @Test
        @DisplayName("toString不应该返回null")
        void toString_ShouldNotReturnNull() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            
            // When
            String result = info.toString();
            
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
            OrderRefundedGoodsInfo info = createTestInfo();
            
            // When
            String json = objectMapper.writeValueAsString(info);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"goods_id\":\"goods123\""));
            assertTrue(json.contains("\"goods_name\":\"测试商品\""));
            assertTrue(json.contains("\"sku_id\":\"sku123\""));
            assertTrue(json.contains("\"upc\":\"123456789012\""));
            assertTrue(json.contains("\"shelf_no\":\"A01-B02\""));
            assertTrue(json.contains("\"number\":5"));
            assertTrue(json.contains("\"goods_total_fee\":2500"));
            assertTrue(json.contains("\"package_number\":2"));
            assertTrue(json.contains("\"package_fee\":100"));
            assertTrue(json.contains("\"refund_fee\":2600"));
        }

        @Test
        @DisplayName("JSON应该能够反序列化为对象")
        void json_ShouldDeserializeToObject() throws Exception {
            // Given
            String json = "{\"goods_id\":\"goods123\",\"goods_name\":\"测试商品\"," +
                         "\"sku_id\":\"sku123\",\"upc\":\"123456789012\"," +
                         "\"shelf_no\":\"A01-B02\",\"number\":5," +
                         "\"goods_total_fee\":2500,\"package_number\":2," +
                         "\"package_fee\":100,\"refund_fee\":2600}";
            
            // When
            OrderRefundedGoodsInfo info = objectMapper.readValue(json, OrderRefundedGoodsInfo.class);
            
            // Then
            assertNotNull(info);
            assertEquals("goods123", info.getGoodsId());
            assertEquals("测试商品", info.getGoodsName());
            assertEquals("sku123", info.getSkuId());
            assertEquals("123456789012", info.getUpc());
            assertEquals("A01-B02", info.getShelfNo());
            assertEquals(Integer.valueOf(5), info.getNumber());
            assertEquals(Integer.valueOf(2500), info.getGoodsTotalFee());
            assertEquals(Integer.valueOf(2), info.getPackageNumber());
            assertEquals(Integer.valueOf(100), info.getPackageFee());
            assertEquals(Integer.valueOf(2600), info.getRefundFee());
        }

        @Test
        @DisplayName("序列化后再反序列化应该得到相等的对象")
        void serializeAndDeserialize_ShouldGetEqualObject() throws Exception {
            // Given
            OrderRefundedGoodsInfo original = createTestInfo();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderRefundedGoodsInfo deserialized = objectMapper.readValue(json, OrderRefundedGoodsInfo.class);
            
            // Then
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("所有字段为null时应该正常工作")
        void allFieldsNull_ShouldWork() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            
            // When & Then
            assertDoesNotThrow(() -> {
                info.toString();
                info.hashCode();
                info.equals(new OrderRefundedGoodsInfo());
            });
        }

        @Test
        @DisplayName("数字字段为0时应该正常工作")
        void numericFieldsZero_ShouldWork() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            info.setNumber(0);
            info.setGoodsTotalFee(0);
            info.setPackageNumber(0);
            info.setPackageFee(0);
            info.setRefundFee(0);
            
            // When & Then
            assertDoesNotThrow(() -> {
                assertEquals(Integer.valueOf(0), info.getNumber());
                assertEquals(Integer.valueOf(0), info.getGoodsTotalFee());
                assertEquals(Integer.valueOf(0), info.getPackageNumber());
                assertEquals(Integer.valueOf(0), info.getPackageFee());
                assertEquals(Integer.valueOf(0), info.getRefundFee());
            });
        }

        @Test
        @DisplayName("数字字段为负数时应该正常工作")
        void numericFieldsNegative_ShouldWork() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            info.setNumber(-1);
            info.setGoodsTotalFee(-100);
            info.setPackageNumber(-1);
            info.setPackageFee(-50);
            info.setRefundFee(-200);
            
            // When & Then
            assertDoesNotThrow(() -> {
                assertEquals(Integer.valueOf(-1), info.getNumber());
                assertEquals(Integer.valueOf(-100), info.getGoodsTotalFee());
                assertEquals(Integer.valueOf(-1), info.getPackageNumber());
                assertEquals(Integer.valueOf(-50), info.getPackageFee());
                assertEquals(Integer.valueOf(-200), info.getRefundFee());
            });
        }

        @Test
        @DisplayName("字符串字段为空字符串时应该正常工作")
        void stringFieldsEmpty_ShouldWork() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            info.setGoodsId("");
            info.setGoodsName("");
            info.setSkuId("");
            info.setUpc("");
            info.setShelfNo("");
            
            // When & Then
            assertEquals("", info.getGoodsId());
            assertEquals("", info.getGoodsName());
            assertEquals("", info.getSkuId());
            assertEquals("", info.getUpc());
            assertEquals("", info.getShelfNo());
        }

        @Test
        @DisplayName("字符串字段包含特殊字符时应该正常工作")
        void stringFieldsWithSpecialCharacters_ShouldWork() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            String specialText = "测试@#$%^&*()_+{}|:<>?[]\\;'\",./-=`~";
            info.setGoodsId(specialText);
            info.setGoodsName(specialText);
            info.setSkuId(specialText);
            info.setUpc(specialText);
            info.setShelfNo(specialText);
            
            // When & Then
            assertEquals(specialText, info.getGoodsId());
            assertEquals(specialText, info.getGoodsName());
            assertEquals(specialText, info.getSkuId());
            assertEquals(specialText, info.getUpc());
            assertEquals(specialText, info.getShelfNo());
        }

        @Test
        @DisplayName("数字字段为最大整数值时应该正常工作")
        void numericFieldsMaxValue_ShouldWork() {
            // Given
            OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
            info.setNumber(Integer.MAX_VALUE);
            info.setGoodsTotalFee(Integer.MAX_VALUE);
            info.setPackageNumber(Integer.MAX_VALUE);
            info.setPackageFee(Integer.MAX_VALUE);
            info.setRefundFee(Integer.MAX_VALUE);
            
            // When & Then
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), info.getNumber());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), info.getGoodsTotalFee());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), info.getPackageNumber());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), info.getPackageFee());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), info.getRefundFee());
        }
    }

    /**
     * 创建测试用的OrderRefundedGoodsInfo对象
     */
    private OrderRefundedGoodsInfo createTestInfo() {
        OrderRefundedGoodsInfo info = new OrderRefundedGoodsInfo();
        info.setGoodsId("goods123");
        info.setGoodsName("测试商品");
        info.setSkuId("sku123");
        info.setUpc("123456789012");
        info.setShelfNo("A01-B02");
        info.setNumber(5);
        info.setGoodsTotalFee(2500);
        info.setPackageNumber(2);
        info.setPackageFee(100);
        info.setRefundFee(2600);
        return info;
    }
}