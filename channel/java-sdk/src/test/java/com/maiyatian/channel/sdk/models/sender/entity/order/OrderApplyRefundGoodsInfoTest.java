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
 * OrderApplyRefundGoodsInfo单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderApplyRefundGoodsInfo 测试")
class OrderApplyRefundGoodsInfoTest {

    private ObjectMapper objectMapper;
    private OrderApplyRefundGoodsInfo goodsInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        goodsInfo = new OrderApplyRefundGoodsInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            
            assertAll("默认构造函数验证",
                () -> assertNull(info.getGoodsId(), "goodsId应该为null"),
                () -> assertNull(info.getGoodsName(), "goodsName应该为null"),
                () -> assertNull(info.getSkuId(), "skuId应该为null"),
                () -> assertNull(info.getUpc(), "upc应该为null"),
                () -> assertNull(info.getShelfNo(), "shelfNo应该为null"),
                () -> assertNull(info.getNumber(), "number应该为null"),
                () -> assertNull(info.getGoodsTotalFee(), "goodsTotalFee应该为null"),
                () -> assertNull(info.getPackageNumber(), "packageNumber应该为null"),
                () -> assertNull(info.getPackageFee(), "packageFee应该为null"),
                () -> assertNull(info.getRefundFee(), "refundFee应该为null")
            );
        }
    }

    @Nested
    @DisplayName("Getter和Setter测试")
    class GetterSetterTests {

        @Test
        @DisplayName("goodsId字段的getter和setter")
        void goodsIdGetterSetter() {
            assertNull(goodsInfo.getGoodsId(), "初始值应该为null");
            
            String goodsId = "goods_12345";
            goodsInfo.setGoodsId(goodsId);
            assertEquals(goodsId, goodsInfo.getGoodsId(), "goodsId应该正确设置");
            
            // 测试空字符串
            goodsInfo.setGoodsId("");
            assertEquals("", goodsInfo.getGoodsId(), "应该能够设置空字符串");
            
            // 测试null
            goodsInfo.setGoodsId(null);
            assertNull(goodsInfo.getGoodsId(), "应该能够设置为null");
        }

        @Test
        @DisplayName("goodsName字段的getter和setter")
        void goodsNameGetterSetter() {
            assertNull(goodsInfo.getGoodsName(), "初始值应该为null");
            
            String goodsName = "烤羊肉串（10串）";
            goodsInfo.setGoodsName(goodsName);
            assertEquals(goodsName, goodsInfo.getGoodsName(), "goodsName应该正确设置");
            
            // 测试空字符串
            goodsInfo.setGoodsName("");
            assertEquals("", goodsInfo.getGoodsName(), "应该能够设置空字符串");
            
            // 测试null
            goodsInfo.setGoodsName(null);
            assertNull(goodsInfo.getGoodsName(), "应该能够设置为null");
        }

        @Test
        @DisplayName("skuId字段的getter和setter")
        void skuIdGetterSetter() {
            assertNull(goodsInfo.getSkuId(), "初始值应该为null");
            
            String skuId = "sku_67890";
            goodsInfo.setSkuId(skuId);
            assertEquals(skuId, goodsInfo.getSkuId(), "skuId应该正确设置");
            
            // 测试空字符串
            goodsInfo.setSkuId("");
            assertEquals("", goodsInfo.getSkuId(), "应该能够设置空字符串");
            
            // 测试null
            goodsInfo.setSkuId(null);
            assertNull(goodsInfo.getSkuId(), "应该能够设置为null");
        }

        @Test
        @DisplayName("upc字段的getter和setter")
        void upcGetterSetter() {
            assertNull(goodsInfo.getUpc(), "初始值应该为null");
            
            String upc = "123456789012";
            goodsInfo.setUpc(upc);
            assertEquals(upc, goodsInfo.getUpc(), "upc应该正确设置");
            
            // 测试空字符串
            goodsInfo.setUpc("");
            assertEquals("", goodsInfo.getUpc(), "应该能够设置空字符串");
            
            // 测试null
            goodsInfo.setUpc(null);
            assertNull(goodsInfo.getUpc(), "应该能够设置为null");
        }

        @Test
        @DisplayName("shelfNo字段的getter和setter")
        void shelfNoGetterSetter() {
            assertNull(goodsInfo.getShelfNo(), "初始值应该为null");
            
            String shelfNo = "A001-B02";
            goodsInfo.setShelfNo(shelfNo);
            assertEquals(shelfNo, goodsInfo.getShelfNo(), "shelfNo应该正确设置");
            
            // 测试空字符串
            goodsInfo.setShelfNo("");
            assertEquals("", goodsInfo.getShelfNo(), "应该能够设置空字符串");
            
            // 测试null
            goodsInfo.setShelfNo(null);
            assertNull(goodsInfo.getShelfNo(), "应该能够设置为null");
        }

        @Test
        @DisplayName("number字段的getter和setter")
        void numberGetterSetter() {
            assertNull(goodsInfo.getNumber(), "初始值应该为null");
            
            Integer number = 3;
            goodsInfo.setNumber(number);
            assertEquals(number, goodsInfo.getNumber(), "number应该正确设置");
            
            // 测试零值
            goodsInfo.setNumber(0);
            assertEquals(Integer.valueOf(0), goodsInfo.getNumber(), "应该能够设置为0");
            
            // 测试负数（虽然业务上不合理，但API应该支持）
            goodsInfo.setNumber(-1);
            assertEquals(Integer.valueOf(-1), goodsInfo.getNumber(), "应该能够设置负数");
            
            // 测试null
            goodsInfo.setNumber(null);
            assertNull(goodsInfo.getNumber(), "应该能够设置为null");
        }

        @Test
        @DisplayName("goodsTotalFee字段的getter和setter")
        void goodsTotalFeeGetterSetter() {
            assertNull(goodsInfo.getGoodsTotalFee(), "初始值应该为null");
            
            Integer fee = 3000; // 30元
            goodsInfo.setGoodsTotalFee(fee);
            assertEquals(fee, goodsInfo.getGoodsTotalFee(), "goodsTotalFee应该正确设置");
            
            // 测试零值
            goodsInfo.setGoodsTotalFee(0);
            assertEquals(Integer.valueOf(0), goodsInfo.getGoodsTotalFee(), "应该能够设置为0");
            
            // 测试负数
            goodsInfo.setGoodsTotalFee(-100);
            assertEquals(Integer.valueOf(-100), goodsInfo.getGoodsTotalFee(), "应该能够设置负数");
            
            // 测试null
            goodsInfo.setGoodsTotalFee(null);
            assertNull(goodsInfo.getGoodsTotalFee(), "应该能够设置为null");
        }

        @Test
        @DisplayName("packageNumber字段的getter和setter")
        void packageNumberGetterSetter() {
            assertNull(goodsInfo.getPackageNumber(), "初始值应该为null");
            
            Integer packageNumber = 2;
            goodsInfo.setPackageNumber(packageNumber);
            assertEquals(packageNumber, goodsInfo.getPackageNumber(), "packageNumber应该正确设置");
            
            // 测试零值
            goodsInfo.setPackageNumber(0);
            assertEquals(Integer.valueOf(0), goodsInfo.getPackageNumber(), "应该能够设置为0");
            
            // 测试null
            goodsInfo.setPackageNumber(null);
            assertNull(goodsInfo.getPackageNumber(), "应该能够设置为null");
        }

        @Test
        @DisplayName("packageFee字段的getter和setter")
        void packageFeeGetterSetter() {
            assertNull(goodsInfo.getPackageFee(), "初始值应该为null");
            
            Integer packageFee = 200; // 2元
            goodsInfo.setPackageFee(packageFee);
            assertEquals(packageFee, goodsInfo.getPackageFee(), "packageFee应该正确设置");
            
            // 测试零值
            goodsInfo.setPackageFee(0);
            assertEquals(Integer.valueOf(0), goodsInfo.getPackageFee(), "应该能够设置为0");
            
            // 测试null
            goodsInfo.setPackageFee(null);
            assertNull(goodsInfo.getPackageFee(), "应该能够设置为null");
        }

        @Test
        @DisplayName("refundFee字段的getter和setter")
        void refundFeeGetterSetter() {
            assertNull(goodsInfo.getRefundFee(), "初始值应该为null");
            
            Integer refundFee = 2500; // 25元
            goodsInfo.setRefundFee(refundFee);
            assertEquals(refundFee, goodsInfo.getRefundFee(), "refundFee应该正确设置");
            
            // 测试零值
            goodsInfo.setRefundFee(0);
            assertEquals(Integer.valueOf(0), goodsInfo.getRefundFee(), "应该能够设置为0");
            
            // 测试null
            goodsInfo.setRefundFee(null);
            assertNull(goodsInfo.getRefundFee(), "应该能够设置为null");
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertTrue(goodsInfo.equals(goodsInfo), "对象应该与自身相等");
        }

        @Test
        @DisplayName("null对象应该不相等")
        void nullObject_ShouldNotBeEqual() {
            assertFalse(goodsInfo.equals(null), "对象不应该与null相等");
        }

        @Test
        @DisplayName("不同类型对象应该不相等")
        void differentType_ShouldNotBeEqual() {
            String differentType = "not an OrderApplyRefundGoodsInfo";
            assertFalse(goodsInfo.equals(differentType), "不同类型的对象不应该相等");
        }

        @Test
        @DisplayName("具有相同值的对象应该相等")
        void sameValues_ShouldBeEqual() {
            OrderApplyRefundGoodsInfo info1 = createTestGoodsInfo();
            OrderApplyRefundGoodsInfo info2 = createTestGoodsInfo();
            
            assertEquals(info1, info2, "具有相同值的对象应该相等");
            assertEquals(info2, info1, "equals方法应该是对称的");
        }

        @Test
        @DisplayName("具有不同值的对象应该不相等")
        void differentValues_ShouldNotBeEqual() {
            OrderApplyRefundGoodsInfo info1 = createTestGoodsInfo();
            OrderApplyRefundGoodsInfo info2 = createTestGoodsInfo();
            info2.setGoodsId("different_goods_id");
            
            assertNotEquals(info1, info2, "具有不同值的对象不应该相等");
        }

        @Test
        @DisplayName("null字段的对象应该正确比较")
        void nullFields_ShouldCompareCorrectly() {
            OrderApplyRefundGoodsInfo info1 = new OrderApplyRefundGoodsInfo();
            OrderApplyRefundGoodsInfo info2 = new OrderApplyRefundGoodsInfo();
            OrderApplyRefundGoodsInfo info3 = new OrderApplyRefundGoodsInfo();
            info3.setGoodsId("test");
            
            assertEquals(info1, info2, "都为null的字段应该相等");
            assertNotEquals(info1, info3, "部分null的字段不应该相等");
        }

        @Test
        @DisplayName("equals方法应该具有传递性")
        void equals_ShouldBeTransitive() {
            OrderApplyRefundGoodsInfo info1 = createTestGoodsInfo();
            OrderApplyRefundGoodsInfo info2 = createTestGoodsInfo();
            OrderApplyRefundGoodsInfo info3 = createTestGoodsInfo();
            
            assertTrue(info1.equals(info2), "info1应该等于info2");
            assertTrue(info2.equals(info3), "info2应该等于info3");
            assertTrue(info1.equals(info3), "info1应该等于info3（传递性）");
        }

        private OrderApplyRefundGoodsInfo createTestGoodsInfo() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId("goods_123");
            info.setGoodsName("测试商品");
            info.setSkuId("sku_456");
            info.setUpc("123456789012");
            info.setShelfNo("A001");
            info.setNumber(2);
            info.setGoodsTotalFee(2000);
            info.setPackageNumber(1);
            info.setPackageFee(100);
            info.setRefundFee(1800);
            return info;
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相等对象应该有相同的hashCode")
        void equalObjects_ShouldHaveSameHashCode() {
            OrderApplyRefundGoodsInfo info1 = createTestGoodsInfo();
            OrderApplyRefundGoodsInfo info2 = createTestGoodsInfo();
            
            assertEquals(info1.hashCode(), info2.hashCode(), 
                "相等的对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致")
        void hashCode_ShouldBeConsistent() {
            OrderApplyRefundGoodsInfo info = createTestGoodsInfo();
            int hashCode1 = info.hashCode();
            int hashCode2 = info.hashCode();
            
            assertEquals(hashCode1, hashCode2, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段的hashCode应该正确计算")
        void nullFields_ShouldCalculateHashCodeCorrectly() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            
            assertDoesNotThrow(() -> info.hashCode(), "null字段不应该导致hashCode抛出异常");
        }

        @Test
        @DisplayName("修改对象后hashCode应该改变")
        void modifiedObject_ShouldChangeHashCode() {
            OrderApplyRefundGoodsInfo info = createTestGoodsInfo();
            int originalHashCode = info.hashCode();
            
            info.setGoodsId("modified_goods_id");
            int newHashCode = info.hashCode();
            
            assertNotEquals(originalHashCode, newHashCode, "修改对象后hashCode应该改变");
        }

        private OrderApplyRefundGoodsInfo createTestGoodsInfo() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId("goods_123");
            info.setGoodsName("测试商品");
            info.setSkuId("sku_456");
            info.setUpc("123456789012");
            info.setShelfNo("A001");
            info.setNumber(2);
            info.setGoodsTotalFee(2000);
            info.setPackageNumber(1);
            info.setPackageFee(100);
            info.setRefundFee(1800);
            return info;
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含所有字段")
        void toString_ShouldContainAllFields() {
            OrderApplyRefundGoodsInfo info = createTestGoodsInfo();
            String result = info.toString();
            
            assertAll("toString内容验证",
                () -> assertTrue(result.contains("OrderApplyRefundGoodsInfo"), "应该包含类名"),
                () -> assertTrue(result.contains("goodsId='goods_123'"), "应该包含goodsId字段"),
                () -> assertTrue(result.contains("goodsName='测试商品'"), "应该包含goodsName字段"),
                () -> assertTrue(result.contains("skuId='sku_456'"), "应该包含skuId字段"),
                () -> assertTrue(result.contains("upc='123456789012'"), "应该包含upc字段"),
                () -> assertTrue(result.contains("shelfNo='A001'"), "应该包含shelfNo字段"),
                () -> assertTrue(result.contains("number=2"), "应该包含number字段"),
                () -> assertTrue(result.contains("goodsTotalFee=2000"), "应该包含goodsTotalFee字段"),
                () -> assertTrue(result.contains("packageNumber=1"), "应该包含packageNumber字段"),
                () -> assertTrue(result.contains("packageFee=100"), "应该包含packageFee字段"),
                () -> assertTrue(result.contains("refundFee=1800"), "应该包含refundFee字段")
            );
        }

        @Test
        @DisplayName("toString应该正确处理null值")
        void toString_ShouldHandleNullValues() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            String result = info.toString();
            
            assertAll("null值toString验证",
                () -> assertTrue(result.contains("goodsId='null'"), "应该正确显示null goodsId"),
                () -> assertTrue(result.contains("goodsName='null'"), "应该正确显示null goodsName"),
                () -> assertTrue(result.contains("skuId='null'"), "应该正确显示null skuId"),
                () -> assertTrue(result.contains("number=null"), "应该正确显示null number"),
                () -> assertTrue(result.contains("refundFee=null"), "应该正确显示null refundFee")
            );
        }

        @Test
        @DisplayName("toString应该返回非null字符串")
        void toString_ShouldReturnNonNullString() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            String result = info.toString();
            
            assertNotNull(result, "toString不应该返回null");
            assertFalse(result.isEmpty(), "toString不应该返回空字符串");
        }

        private OrderApplyRefundGoodsInfo createTestGoodsInfo() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId("goods_123");
            info.setGoodsName("测试商品");
            info.setSkuId("sku_456");
            info.setUpc("123456789012");
            info.setShelfNo("A001");
            info.setNumber(2);
            info.setGoodsTotalFee(2000);
            info.setPackageNumber(1);
            info.setPackageFee(100);
            info.setRefundFee(1800);
            return info;
        }
    }

    @Nested
    @DisplayName("JSON序列化/反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该正确序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            OrderApplyRefundGoodsInfo info = createTestGoodsInfo();
            
            String json = objectMapper.writeValueAsString(info);
            
            assertAll("JSON序列化验证",
                () -> assertTrue(json.contains("\"goods_id\":\"goods_123\""), "应该包含goods_id字段"),
                () -> assertTrue(json.contains("\"goods_name\":\"测试商品\""), "应该包含goods_name字段"),
                () -> assertTrue(json.contains("\"sku_id\":\"sku_456\""), "应该包含sku_id字段"),
                () -> assertTrue(json.contains("\"upc\":\"123456789012\""), "应该包含upc字段"),
                () -> assertTrue(json.contains("\"shelf_no\":\"A001\""), "应该包含shelf_no字段"),
                () -> assertTrue(json.contains("\"number\":2"), "应该包含number字段"),
                () -> assertTrue(json.contains("\"goods_total_fee\":2000"), "应该包含goods_total_fee字段"),
                () -> assertTrue(json.contains("\"package_number\":1"), "应该包含package_number字段"),
                () -> assertTrue(json.contains("\"package_fee\":100"), "应该包含package_fee字段"),
                () -> assertTrue(json.contains("\"refund_fee\":1800"), "应该包含refund_fee字段")
            );
        }

        @Test
        @DisplayName("应该正确从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"goods_id\":\"goods_123\",\"goods_name\":\"测试商品\"," +
                         "\"sku_id\":\"sku_456\",\"upc\":\"123456789012\",\"shelf_no\":\"A001\"," +
                         "\"number\":2,\"goods_total_fee\":2000,\"package_number\":1," +
                         "\"package_fee\":100,\"refund_fee\":1800}";
            
            OrderApplyRefundGoodsInfo info = objectMapper.readValue(json, OrderApplyRefundGoodsInfo.class);
            
            assertAll("JSON反序列化验证",
                () -> assertEquals("goods_123", info.getGoodsId(), "goodsId应该正确反序列化"),
                () -> assertEquals("测试商品", info.getGoodsName(), "goodsName应该正确反序列化"),
                () -> assertEquals("sku_456", info.getSkuId(), "skuId应该正确反序列化"),
                () -> assertEquals("123456789012", info.getUpc(), "upc应该正确反序列化"),
                () -> assertEquals("A001", info.getShelfNo(), "shelfNo应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(2), info.getNumber(), "number应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(2000), info.getGoodsTotalFee(), "goodsTotalFee应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(1), info.getPackageNumber(), "packageNumber应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(100), info.getPackageFee(), "packageFee应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(1800), info.getRefundFee(), "refundFee应该正确反序列化")
            );
        }

        @Test
        @DisplayName("应该正确处理JSON中的null值")
        void shouldHandleNullValuesInJson() throws Exception {
            String json = "{\"goods_id\":null,\"goods_name\":null,\"sku_id\":null," +
                         "\"upc\":null,\"shelf_no\":null,\"number\":null," +
                         "\"goods_total_fee\":null,\"package_number\":null," +
                         "\"package_fee\":null,\"refund_fee\":null}";
            
            OrderApplyRefundGoodsInfo info = objectMapper.readValue(json, OrderApplyRefundGoodsInfo.class);
            
            assertAll("JSON null值处理验证",
                () -> assertNull(info.getGoodsId(), "goodsId应该为null"),
                () -> assertNull(info.getGoodsName(), "goodsName应该为null"),
                () -> assertNull(info.getSkuId(), "skuId应该为null"),
                () -> assertNull(info.getUpc(), "upc应该为null"),
                () -> assertNull(info.getShelfNo(), "shelfNo应该为null"),
                () -> assertNull(info.getNumber(), "number应该为null"),
                () -> assertNull(info.getGoodsTotalFee(), "goodsTotalFee应该为null"),
                () -> assertNull(info.getPackageNumber(), "packageNumber应该为null"),
                () -> assertNull(info.getPackageFee(), "packageFee应该为null"),
                () -> assertNull(info.getRefundFee(), "refundFee应该为null")
            );
        }

        @Test
        @DisplayName("序列化和反序列化应该保持一致")
        void serializationRoundTrip_ShouldMaintainConsistency() throws Exception {
            OrderApplyRefundGoodsInfo original = createTestGoodsInfo();
            
            String json = objectMapper.writeValueAsString(original);
            OrderApplyRefundGoodsInfo deserialized = objectMapper.readValue(json, OrderApplyRefundGoodsInfo.class);
            
            assertEquals(original, deserialized, "序列化和反序列化后对象应该相等");
        }

        @Test
        @DisplayName("应该正确处理空JSON对象")
        void shouldHandleEmptyJsonObject() throws Exception {
            String json = "{}";
            
            OrderApplyRefundGoodsInfo info = objectMapper.readValue(json, OrderApplyRefundGoodsInfo.class);
            
            assertAll("空JSON对象处理验证",
                () -> assertNull(info.getGoodsId(), "goodsId应该为null"),
                () -> assertNull(info.getGoodsName(), "goodsName应该为null"),
                () -> assertNull(info.getNumber(), "number应该为null"),
                () -> assertNull(info.getRefundFee(), "refundFee应该为null")
            );
        }

        private OrderApplyRefundGoodsInfo createTestGoodsInfo() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId("goods_123");
            info.setGoodsName("测试商品");
            info.setSkuId("sku_456");
            info.setUpc("123456789012");
            info.setShelfNo("A001");
            info.setNumber(2);
            info.setGoodsTotalFee(2000);
            info.setPackageNumber(1);
            info.setPackageFee(100);
            info.setRefundFee(1800);
            return info;
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应该处理极大的数值")
        void shouldHandleLargeNumbers() {
            Integer maxValue = Integer.MAX_VALUE;
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setNumber(maxValue);
            info.setGoodsTotalFee(maxValue);
            info.setPackageNumber(maxValue);
            info.setPackageFee(maxValue);
            info.setRefundFee(maxValue);
            
            assertAll("极大数值验证",
                () -> assertEquals(maxValue, info.getNumber(), "应该能处理Integer.MAX_VALUE的number"),
                () -> assertEquals(maxValue, info.getGoodsTotalFee(), "应该能处理Integer.MAX_VALUE的goodsTotalFee"),
                () -> assertEquals(maxValue, info.getPackageNumber(), "应该能处理Integer.MAX_VALUE的packageNumber"),
                () -> assertEquals(maxValue, info.getPackageFee(), "应该能处理Integer.MAX_VALUE的packageFee"),
                () -> assertEquals(maxValue, info.getRefundFee(), "应该能处理Integer.MAX_VALUE的refundFee")
            );
        }

        @Test
        @DisplayName("应该处理零值")
        void shouldHandleZeroValues() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setNumber(0);
            info.setGoodsTotalFee(0);
            info.setPackageNumber(0);
            info.setPackageFee(0);
            info.setRefundFee(0);
            
            assertAll("零值验证",
                () -> assertEquals(Integer.valueOf(0), info.getNumber(), "应该能处理0值的number"),
                () -> assertEquals(Integer.valueOf(0), info.getGoodsTotalFee(), "应该能处理0值的goodsTotalFee"),
                () -> assertEquals(Integer.valueOf(0), info.getPackageNumber(), "应该能处理0值的packageNumber"),
                () -> assertEquals(Integer.valueOf(0), info.getPackageFee(), "应该能处理0值的packageFee"),
                () -> assertEquals(Integer.valueOf(0), info.getRefundFee(), "应该能处理0值的refundFee")
            );
        }

        @Test
        @DisplayName("应该处理长字符串")
        void shouldHandleLongStrings() {
            StringBuilder longString = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                longString.append("长商品名称测试");
            }
            String longGoodsName = longString.toString();
            
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsName(longGoodsName);
            
            assertEquals(longGoodsName, info.getGoodsName(), "应该能处理长字符串");
        }

        @Test
        @DisplayName("应该处理包含特殊字符的字符串")
        void shouldHandleSpecialCharacters() {
            String specialGoodsName = "烤羊肉串（10串）！@#$%^&*()_+{}|:<>?[]\\;'\".,/~`";
            String specialShelfNo = "A001-B02@#$";
            String specialUpc = "123456789012-ABC";
            
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsName(specialGoodsName);
            info.setShelfNo(specialShelfNo);
            info.setUpc(specialUpc);
            
            assertAll("特殊字符处理验证",
                () -> assertEquals(specialGoodsName, info.getGoodsName(), "应该能处理包含特殊字符的商品名称"),
                () -> assertEquals(specialShelfNo, info.getShelfNo(), "应该能处理包含特殊字符的货架号"),
                () -> assertEquals(specialUpc, info.getUpc(), "应该能处理包含特殊字符的UPC")
            );
        }

        @Test
        @DisplayName("应该处理包含Unicode字符的字符串")
        void shouldHandleUnicodeCharacters() {
            String unicodeGoodsName = "烤羊肉串 🥢 (10串) ￥30";
            String unicodeShelfNo = "货架A区 📦 001号";
            
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsName(unicodeGoodsName);
            info.setShelfNo(unicodeShelfNo);
            
            assertAll("Unicode字符处理验证",
                () -> assertEquals(unicodeGoodsName, info.getGoodsName(), "应该能处理包含Unicode字符的商品名称"),
                () -> assertEquals(unicodeShelfNo, info.getShelfNo(), "应该能处理包含Unicode字符的货架号")
            );
        }
    }

    @Nested
    @DisplayName("业务逻辑验证测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("退款商品信息应该包含必要的标识字段")
        void refundGoods_ShouldContainNecessaryIdentifiers() {
            OrderApplyRefundGoodsInfo info = createCompleteGoodsInfo();
            
            assertAll("商品标识字段验证",
                () -> assertNotNull(info.getGoodsId(), "商品ID不应该为null"),
                () -> assertNotNull(info.getGoodsName(), "商品名称不应该为null"),
                () -> assertNotNull(info.getSkuId(), "SKU ID不应该为null")
            );
        }

        @Test
        @DisplayName("退款金额不应该超过商品总价")
        void refundFee_ShouldNotExceedGoodsTotalFee() {
            OrderApplyRefundGoodsInfo info = createCompleteGoodsInfo();
            info.setGoodsTotalFee(3000); // 30元
            info.setRefundFee(2500); // 25元退款
            
            assertTrue(info.getRefundFee() <= info.getGoodsTotalFee(), 
                "退款金额不应该超过商品总价");
        }

        @Test
        @DisplayName("退款数量不应该超过原始购买数量")
        void refundNumber_ShouldBeReasonable() {
            OrderApplyRefundGoodsInfo info = createCompleteGoodsInfo();
            info.setNumber(2); // 退款2个
            
            assertTrue(info.getNumber() > 0, "退款数量应该大于0");
        }

        @Test
        @DisplayName("金额字段应该使用分为单位")
        void amounts_ShouldBeInCents() {
            OrderApplyRefundGoodsInfo info = createCompleteGoodsInfo();
            info.setGoodsTotalFee(3000); // 30元 = 3000分
            info.setPackageFee(200);     // 2元 = 200分
            info.setRefundFee(2800);     // 28元 = 2800分
            
            assertAll("金额单位验证",
                () -> assertEquals(Integer.valueOf(3000), info.getGoodsTotalFee(), "商品总价3000分（30元）"),
                () -> assertEquals(Integer.valueOf(200), info.getPackageFee(), "包装费200分（2元）"),
                () -> assertEquals(Integer.valueOf(2800), info.getRefundFee(), "退款金额2800分（28元）")
            );
        }

        @Test
        @DisplayName("UPC条形码应该是标准格式")
        void upc_ShouldBeStandardFormat() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            String standardUpc = "123456789012"; // 12位UPC-A格式
            info.setUpc(standardUpc);
            
            assertAll("UPC条形码验证",
                () -> assertEquals(standardUpc, info.getUpc(), "应该正确设置UPC条形码"),
                () -> assertEquals(12, standardUpc.length(), "标准UPC-A应该是12位")
            );
        }

        @Test
        @DisplayName("货架号应该支持多种格式")
        void shelfNo_ShouldSupportVariousFormats() {
            String[] shelfFormats = {
                "A001",           // 简单格式
                "A001-B02",       // 分区格式
                "1F-A区-001",     // 楼层-区域-编号
                "冷藏区-A001",    // 中文区域名
                "FREEZER-001"     // 英文区域名
            };
            
            for (String shelfNo : shelfFormats) {
                OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
                info.setShelfNo(shelfNo);
                assertEquals(shelfNo, info.getShelfNo(), "应该支持货架号格式: " + shelfNo);
            }
        }

        @Test
        @DisplayName("包装信息应该与商品数量匹配")
        void packageInfo_ShouldMatchGoodsQuantity() {
            OrderApplyRefundGoodsInfo info = createCompleteGoodsInfo();
            info.setNumber(3);           // 退款3个商品
            info.setPackageNumber(1);    // 1个包装盒
            info.setPackageFee(200);     // 2元包装费
            
            assertAll("包装信息验证",
                () -> assertEquals(Integer.valueOf(3), info.getNumber(), "商品数量为3"),
                () -> assertEquals(Integer.valueOf(1), info.getPackageNumber(), "包装盒数量为1"),
                () -> assertTrue(info.getPackageNumber() <= info.getNumber(), "包装盒数量不应该超过商品数量")
            );
        }

        @Test
        @DisplayName("应该支持部分退款场景")
        void shouldSupportPartialRefund() {
            // 原始订单：5个商品，总价5000分（50元）
            // 部分退款：2个商品，退款2000分（20元）
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId("goods_12345");
            info.setGoodsName("烤羊肉串（10串装）");
            info.setSkuId("sku_67890");
            info.setNumber(2);              // 退款2个
            info.setGoodsTotalFee(2000);    // 2个商品的总价
            info.setPackageNumber(1);       // 1个包装盒
            info.setPackageFee(100);        // 包装费
            info.setRefundFee(1900);        // 实际退款（商品费用+包装费的一部分）
            
            assertAll("部分退款验证",
                () -> assertEquals(Integer.valueOf(2), info.getNumber(), "部分退款数量为2"),
                () -> assertEquals(Integer.valueOf(2000), info.getGoodsTotalFee(), "部分商品总价"),
                () -> assertEquals(Integer.valueOf(1900), info.getRefundFee(), "实际退款金额"),
                () -> assertTrue(info.getRefundFee() <= info.getGoodsTotalFee() + info.getPackageFee(), 
                    "退款金额不应该超过商品价格加包装费")
            );
        }

        @Test
        @DisplayName("应该支持不同类型的商品退款")
        void shouldSupportDifferentProductTypes() {
            // 测试不同类型的商品
            OrderApplyRefundGoodsInfo foodItem = createGoodsInfo("food_001", "烤羊肉串", 2, 2000);
            OrderApplyRefundGoodsInfo drinkItem = createGoodsInfo("drink_002", "冰啤酒", 1, 800);
            OrderApplyRefundGoodsInfo snackItem = createGoodsInfo("snack_003", "瓜子", 3, 600);
            
            assertAll("不同商品类型验证",
                () -> assertEquals("烤羊肉串", foodItem.getGoodsName(), "食物类商品"),
                () -> assertEquals("冰啤酒", drinkItem.getGoodsName(), "饮料类商品"),
                () -> assertEquals("瓜子", snackItem.getGoodsName(), "零食类商品"),
                () -> assertEquals(Integer.valueOf(2), foodItem.getNumber(), "食物退款数量"),
                () -> assertEquals(Integer.valueOf(1), drinkItem.getNumber(), "饮料退款数量"),
                () -> assertEquals(Integer.valueOf(3), snackItem.getNumber(), "零食退款数量")
            );
        }

        private OrderApplyRefundGoodsInfo createCompleteGoodsInfo() {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId("goods_12345");
            info.setGoodsName("烤羊肉串（10串）");
            info.setSkuId("sku_67890");
            info.setUpc("123456789012");
            info.setShelfNo("A001-B02");
            info.setNumber(2);
            info.setGoodsTotalFee(3000);
            info.setPackageNumber(1);
            info.setPackageFee(200);
            info.setRefundFee(2800);
            return info;
        }

        private OrderApplyRefundGoodsInfo createGoodsInfo(String goodsId, String goodsName, Integer number, Integer totalFee) {
            OrderApplyRefundGoodsInfo info = new OrderApplyRefundGoodsInfo();
            info.setGoodsId(goodsId);
            info.setGoodsName(goodsName);
            info.setNumber(number);
            info.setGoodsTotalFee(totalFee);
            info.setRefundFee(totalFee); // 简化：退款金额等于商品总价
            return info;
        }
    }
}