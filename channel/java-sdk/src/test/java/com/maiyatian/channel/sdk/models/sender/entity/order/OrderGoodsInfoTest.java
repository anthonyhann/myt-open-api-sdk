/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderGoodsInfo 单元测试
 * 
 * @author Test Generator
 * @version 1.0.0
 */
@DisplayName("OrderGoodsInfo 测试")
public class OrderGoodsInfoTest {

    private ObjectMapper objectMapper;
    private OrderGoodsInfo orderGoodsInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderGoodsInfo = new OrderGoodsInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            // When
            OrderGoodsInfo goodsInfo = new OrderGoodsInfo();
            
            // Then
            assertNotNull(goodsInfo);
            assertNull(goodsInfo.getGoodsId());
            assertNull(goodsInfo.getGoodsName());
            assertNull(goodsInfo.getGoodsCode());
            assertNull(goodsInfo.getThumb());
            assertNull(goodsInfo.getSkuId());
            assertNull(goodsInfo.getSkuName());
            assertNull(goodsInfo.getSkuCode());
            assertNull(goodsInfo.getFoodProperty());
            assertNull(goodsInfo.getSkuAttributes());
            assertNull(goodsInfo.getCommodities());
            assertNull(goodsInfo.getUnit());
            assertNull(goodsInfo.getWeight());
            assertNull(goodsInfo.getUpc());
            assertNull(goodsInfo.getShelfNo());
            assertNull(goodsInfo.getNumber());
            assertNull(goodsInfo.getGoodsPrice());
            assertNull(goodsInfo.getGoodsTotalFee());
            assertNull(goodsInfo.getPackageNumber());
            assertNull(goodsInfo.getPackagePrice());
            assertNull(goodsInfo.getPackageTotalFee());
            assertNull(goodsInfo.getReduceFee());
            assertNull(goodsInfo.getDiscountFee());
            assertNull(goodsInfo.getDiscountPlatformFee());
            assertNull(goodsInfo.getDiscountMerchantFee());
            assertNull(goodsInfo.getDiscountAgentFee());
            assertNull(goodsInfo.getDiscountLogisticsFee());
            assertNull(goodsInfo.getTotalFee());
        }
    }

    @Nested
    @DisplayName("Getter 和 Setter 测试")
    class GetterSetterTests {

        @Test
        @DisplayName("商品基本信息测试")
        void testBasicGoodsInfo() {
            // Given
            String goodsId = "goods_001";
            String goodsName = "汉堡套餐";
            String goodsCode = "BURGER_SET";
            String thumb = "https://example.com/burger.jpg";
            
            // When
            orderGoodsInfo.setGoodsId(goodsId);
            orderGoodsInfo.setGoodsName(goodsName);
            orderGoodsInfo.setGoodsCode(goodsCode);
            orderGoodsInfo.setThumb(thumb);
            
            // Then
            assertEquals(goodsId, orderGoodsInfo.getGoodsId());
            assertEquals(goodsName, orderGoodsInfo.getGoodsName());
            assertEquals(goodsCode, orderGoodsInfo.getGoodsCode());
            assertEquals(thumb, orderGoodsInfo.getThumb());
        }

        @Test
        @DisplayName("SKU信息测试")
        void testSkuInfo() {
            // Given
            String skuId = "sku_001";
            String skuName = "中杯加辣";
            String skuCode = "MEDIUM_SPICY";
            
            // When
            orderGoodsInfo.setSkuId(skuId);
            orderGoodsInfo.setSkuName(skuName);
            orderGoodsInfo.setSkuCode(skuCode);
            
            // Then
            assertEquals(skuId, orderGoodsInfo.getSkuId());
            assertEquals(skuName, orderGoodsInfo.getSkuName());
            assertEquals(skuCode, orderGoodsInfo.getSkuCode());
        }

        @Test
        @DisplayName("商品属性测试")
        void testGoodsProperties() {
            // Given
            List<String> foodProperty = Arrays.asList("6英寸", "原味");
            String unit = "份";
            Integer weight = 500;
            String upc = "1234567890123";
            String shelfNo = "A-001";
            
            // When
            orderGoodsInfo.setFoodProperty(foodProperty);
            orderGoodsInfo.setUnit(unit);
            orderGoodsInfo.setWeight(weight);
            orderGoodsInfo.setUpc(upc);
            orderGoodsInfo.setShelfNo(shelfNo);
            
            // Then
            assertEquals(foodProperty, orderGoodsInfo.getFoodProperty());
            assertEquals(unit, orderGoodsInfo.getUnit());
            assertEquals(weight, orderGoodsInfo.getWeight());
            assertEquals(upc, orderGoodsInfo.getUpc());
            assertEquals(shelfNo, orderGoodsInfo.getShelfNo());
        }

        @Test
        @DisplayName("SKU属性列表测试")
        void testSkuAttributes() {
            // Given
            List<OrderGoodsSkuAttribute> skuAttributes = Arrays.asList(
                new OrderGoodsSkuAttribute("烧鸡200g", 1, "份"),
                new OrderGoodsSkuAttribute("大杯可乐", 1, "杯")
            );
            
            // When
            orderGoodsInfo.setSkuAttributes(skuAttributes);
            
            // Then
            assertEquals(skuAttributes, orderGoodsInfo.getSkuAttributes());
            assertEquals(2, orderGoodsInfo.getSkuAttributes().size());
        }

        @Test
        @DisplayName("商品分组测试")
        void testCommodities() {
            // Given
            List<OrderGoodsSkuAttributeGroup> commodities = Arrays.asList(
                new OrderGoodsSkuAttributeGroup("主食", Arrays.asList(new OrderGoodsSkuAttribute("汉堡", 1, "个"))),
                new OrderGoodsSkuAttributeGroup("饮料", Arrays.asList(new OrderGoodsSkuAttribute("可乐", 1, "杯")))
            );
            
            // When
            orderGoodsInfo.setCommodities(commodities);
            
            // Then
            assertEquals(commodities, orderGoodsInfo.getCommodities());
            assertEquals(2, orderGoodsInfo.getCommodities().size());
        }

        @Test
        @DisplayName("数量和价格信息测试")
        void testQuantityAndPriceInfo() {
            // Given
            Integer number = 2;
            Integer goodsPrice = 2000;
            Integer goodsTotalFee = 4000;
            Integer packageNumber = 1;
            Integer packagePrice = 200;
            Integer packageTotalFee = 200;
            
            // When
            orderGoodsInfo.setNumber(number);
            orderGoodsInfo.setGoodsPrice(goodsPrice);
            orderGoodsInfo.setGoodsTotalFee(goodsTotalFee);
            orderGoodsInfo.setPackageNumber(packageNumber);
            orderGoodsInfo.setPackagePrice(packagePrice);
            orderGoodsInfo.setPackageTotalFee(packageTotalFee);
            
            // Then
            assertEquals(number, orderGoodsInfo.getNumber());
            assertEquals(goodsPrice, orderGoodsInfo.getGoodsPrice());
            assertEquals(goodsTotalFee, orderGoodsInfo.getGoodsTotalFee());
            assertEquals(packageNumber, orderGoodsInfo.getPackageNumber());
            assertEquals(packagePrice, orderGoodsInfo.getPackagePrice());
            assertEquals(packageTotalFee, orderGoodsInfo.getPackageTotalFee());
        }

        @Test
        @DisplayName("优惠信息测试")
        void testDiscountInfo() {
            // Given
            Integer reduceFee = 1800;
            Integer discountFee = 400;
            Integer discountPlatformFee = 200;
            Integer discountMerchantFee = 100;
            Integer discountAgentFee = 50;
            Integer discountLogisticsFee = 50;
            Integer totalFee = 3800;
            
            // When
            orderGoodsInfo.setReduceFee(reduceFee);
            orderGoodsInfo.setDiscountFee(discountFee);
            orderGoodsInfo.setDiscountPlatformFee(discountPlatformFee);
            orderGoodsInfo.setDiscountMerchantFee(discountMerchantFee);
            orderGoodsInfo.setDiscountAgentFee(discountAgentFee);
            orderGoodsInfo.setDiscountLogisticsFee(discountLogisticsFee);
            orderGoodsInfo.setTotalFee(totalFee);
            
            // Then
            assertEquals(reduceFee, orderGoodsInfo.getReduceFee());
            assertEquals(discountFee, orderGoodsInfo.getDiscountFee());
            assertEquals(discountPlatformFee, orderGoodsInfo.getDiscountPlatformFee());
            assertEquals(discountMerchantFee, orderGoodsInfo.getDiscountMerchantFee());
            assertEquals(discountAgentFee, orderGoodsInfo.getDiscountAgentFee());
            assertEquals(discountLogisticsFee, orderGoodsInfo.getDiscountLogisticsFee());
            assertEquals(totalFee, orderGoodsInfo.getTotalFee());
        }
    }

    @Nested
    @DisplayName("equals 和 hashCode 测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象 equals 测试")
        void testEqualsWithSameInstance() {
            // Then
            assertEquals(orderGoodsInfo, orderGoodsInfo);
            assertEquals(orderGoodsInfo.hashCode(), orderGoodsInfo.hashCode());
        }

        @Test
        @DisplayName("null 对象 equals 测试")
        void testEqualsWithNull() {
            // Then
            assertNotEquals(null, orderGoodsInfo);
        }

        @Test
        @DisplayName("不同类型对象 equals 测试")
        void testEqualsWithDifferentType() {
            // Given
            String differentType = "different";
            
            // Then
            assertNotEquals(orderGoodsInfo, differentType);
        }

        @Test
        @DisplayName("相同属性对象 equals 测试")
        void testEqualsWithSameProperties() {
            // Given
            OrderGoodsInfo goodsInfo1 = createFullOrderGoodsInfo();
            OrderGoodsInfo goodsInfo2 = createFullOrderGoodsInfo();
            
            // Then
            assertEquals(goodsInfo1, goodsInfo2);
            assertEquals(goodsInfo1.hashCode(), goodsInfo2.hashCode());
        }

        @Test
        @DisplayName("不同属性对象 equals 测试")
        void testEqualsWithDifferentProperties() {
            // Given
            OrderGoodsInfo goodsInfo1 = createFullOrderGoodsInfo();
            OrderGoodsInfo goodsInfo2 = createFullOrderGoodsInfo();
            goodsInfo2.setGoodsId("different_id");
            
            // Then
            assertNotEquals(goodsInfo1, goodsInfo2);
        }

        @Test
        @DisplayName("不同商品ID equals 测试")
        void testEqualsWithDifferentGoodsId() {
            // Given
            orderGoodsInfo.setGoodsId("goods_001");
            OrderGoodsInfo other = new OrderGoodsInfo();
            other.setGoodsId("goods_002");
            
            // Then
            assertNotEquals(orderGoodsInfo, other);
        }

        @Test
        @DisplayName("不同SKU属性 equals 测试")
        void testEqualsWithDifferentSkuAttributes() {
            // Given
            orderGoodsInfo.setSkuAttributes(Arrays.asList(new OrderGoodsSkuAttribute("test", 1, "个")));
            OrderGoodsInfo other = new OrderGoodsInfo();
            other.setSkuAttributes(Collections.emptyList());
            
            // Then
            assertNotEquals(orderGoodsInfo, other);
        }

        @Test
        @DisplayName("不同价格 equals 测试")
        void testEqualsWithDifferentPrice() {
            // Given
            orderGoodsInfo.setGoodsPrice(1000);
            OrderGoodsInfo other = new OrderGoodsInfo();
            other.setGoodsPrice(2000);
            
            // Then
            assertNotEquals(orderGoodsInfo, other);
        }
    }

    @Nested
    @DisplayName("toString 测试")
    class ToStringTests {

        @Test
        @DisplayName("空对象 toString 测试")
        void testToStringWithEmptyObject() {
            // When
            String result = orderGoodsInfo.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderGoodsInfo"));
            assertTrue(result.contains("goodsId='null'"));
            assertTrue(result.contains("goodsName='null'"));
            assertTrue(result.contains("skuId='null'"));
        }

        @Test
        @DisplayName("完整对象 toString 测试")
        void testToStringWithFullObject() {
            // Given
            OrderGoodsInfo goodsInfo = createFullOrderGoodsInfo();
            
            // When
            String result = goodsInfo.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderGoodsInfo"));
            assertTrue(result.contains("goodsId='goods_001'"));
            assertTrue(result.contains("goodsName='汉堡套餐'"));
            assertTrue(result.contains("skuId='sku_001'"));
            assertTrue(result.contains("number=2"));
            assertTrue(result.contains("goodsPrice=2000"));
        }

        @Test
        @DisplayName("包含列表的 toString 测试")
        void testToStringWithLists() {
            // Given
            orderGoodsInfo.setFoodProperty(Arrays.asList("6英寸", "原味"));
            orderGoodsInfo.setSkuAttributes(Arrays.asList(new OrderGoodsSkuAttribute("test", 1, "个")));
            
            // When
            String result = orderGoodsInfo.toString();
            
            // Then
            assertTrue(result.contains("foodProperty=[6英寸, 原味]"));
            assertTrue(result.contains("skuAttributes="));
        }
    }

    @Nested
    @DisplayName("JSON 序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("空对象序列化测试")
        void testSerializeEmptyObject() throws JsonProcessingException {
            // When
            String json = objectMapper.writeValueAsString(orderGoodsInfo);
            
            // Then
            assertNotNull(json);
            assertEquals("{}", json);
        }

        @Test
        @DisplayName("基本信息序列化测试")
        void testSerializeBasicInfo() throws JsonProcessingException {
            // Given
            orderGoodsInfo.setGoodsId("goods_001");
            orderGoodsInfo.setGoodsName("汉堡套餐");
            orderGoodsInfo.setSkuId("sku_001");
            orderGoodsInfo.setNumber(2);
            orderGoodsInfo.setGoodsPrice(2000);
            
            // When
            String json = objectMapper.writeValueAsString(orderGoodsInfo);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"goods_id\":\"goods_001\""));
            assertTrue(json.contains("\"goods_name\":\"汉堡套餐\""));
            assertTrue(json.contains("\"sku_id\":\"sku_001\""));
            assertTrue(json.contains("\"number\":2"));
            assertTrue(json.contains("\"goods_price\":2000"));
        }

        @Test
        @DisplayName("反序列化测试")
        void testDeserialize() throws JsonProcessingException {
            // Given
            String json = "{\"goods_id\":\"goods_001\",\"goods_name\":\"汉堡套餐\",\"sku_id\":\"sku_001\",\"number\":2,\"goods_price\":2000,\"total_fee\":3800}";
            
            // When
            OrderGoodsInfo goodsInfo = objectMapper.readValue(json, OrderGoodsInfo.class);
            
            // Then
            assertNotNull(goodsInfo);
            assertEquals("goods_001", goodsInfo.getGoodsId());
            assertEquals("汉堡套餐", goodsInfo.getGoodsName());
            assertEquals("sku_001", goodsInfo.getSkuId());
            assertEquals(Integer.valueOf(2), goodsInfo.getNumber());
            assertEquals(Integer.valueOf(2000), goodsInfo.getGoodsPrice());
            assertEquals(Integer.valueOf(3800), goodsInfo.getTotalFee());
        }

        @Test
        @DisplayName("序列化后反序列化一致性测试")
        void testSerializeDeserializeConsistency() throws JsonProcessingException {
            // Given
            OrderGoodsInfo original = createFullOrderGoodsInfo();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderGoodsInfo deserialized = objectMapper.readValue(json, OrderGoodsInfo.class);
            
            // Then
            assertEquals(original.getGoodsId(), deserialized.getGoodsId());
            assertEquals(original.getGoodsName(), deserialized.getGoodsName());
            assertEquals(original.getSkuId(), deserialized.getSkuId());
            assertEquals(original.getNumber(), deserialized.getNumber());
            assertEquals(original.getGoodsPrice(), deserialized.getGoodsPrice());
            assertEquals(original.getTotalFee(), deserialized.getTotalFee());
        }

        @Test
        @DisplayName("包含数组的序列化测试")
        void testSerializeWithArrays() throws JsonProcessingException {
            // Given
            orderGoodsInfo.setFoodProperty(Arrays.asList("6英寸", "原味"));
            
            // When
            String json = objectMapper.writeValueAsString(orderGoodsInfo);
            
            // Then
            assertTrue(json.contains("\"food_property\":[\"6英寸\",\"原味\"]"));
        }

        @Test
        @DisplayName("空数组序列化测试")
        void testSerializeWithEmptyArrays() throws JsonProcessingException {
            // Given
            orderGoodsInfo.setFoodProperty(Collections.emptyList());
            orderGoodsInfo.setSkuAttributes(Collections.emptyList());
            
            // When
            String json = objectMapper.writeValueAsString(orderGoodsInfo);
            
            // Then
            assertTrue(json.contains("\"food_property\":[]"));
            assertTrue(json.contains("\"sku_attributes\":[]"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("最大整数值测试")
        void testMaxIntegerValues() {
            // Given
            orderGoodsInfo.setNumber(Integer.MAX_VALUE);
            orderGoodsInfo.setGoodsPrice(Integer.MAX_VALUE);
            orderGoodsInfo.setWeight(Integer.MAX_VALUE);
            
            // Then
            assertEquals(Integer.MAX_VALUE, orderGoodsInfo.getNumber());
            assertEquals(Integer.MAX_VALUE, orderGoodsInfo.getGoodsPrice());
            assertEquals(Integer.MAX_VALUE, orderGoodsInfo.getWeight());
        }

        @Test
        @DisplayName("最小整数值测试")
        void testMinIntegerValues() {
            // Given
            orderGoodsInfo.setNumber(Integer.MIN_VALUE);
            orderGoodsInfo.setGoodsPrice(Integer.MIN_VALUE);
            orderGoodsInfo.setWeight(Integer.MIN_VALUE);
            
            // Then
            assertEquals(Integer.MIN_VALUE, orderGoodsInfo.getNumber());
            assertEquals(Integer.MIN_VALUE, orderGoodsInfo.getGoodsPrice());
            assertEquals(Integer.MIN_VALUE, orderGoodsInfo.getWeight());
        }

        @Test
        @DisplayName("零值测试")
        void testZeroValues() {
            // Given
            orderGoodsInfo.setNumber(0);
            orderGoodsInfo.setGoodsPrice(0);
            orderGoodsInfo.setWeight(0);
            orderGoodsInfo.setTotalFee(0);
            
            // Then
            assertEquals(Integer.valueOf(0), orderGoodsInfo.getNumber());
            assertEquals(Integer.valueOf(0), orderGoodsInfo.getGoodsPrice());
            assertEquals(Integer.valueOf(0), orderGoodsInfo.getWeight());
            assertEquals(Integer.valueOf(0), orderGoodsInfo.getTotalFee());
        }

        @Test
        @DisplayName("负数优惠测试")
        void testNegativeDiscountValues() {
            // Given
            orderGoodsInfo.setDiscountFee(-500);
            orderGoodsInfo.setDiscountPlatformFee(-200);
            orderGoodsInfo.setDiscountMerchantFee(-300);
            
            // Then
            assertEquals(Integer.valueOf(-500), orderGoodsInfo.getDiscountFee());
            assertEquals(Integer.valueOf(-200), orderGoodsInfo.getDiscountPlatformFee());
            assertEquals(Integer.valueOf(-300), orderGoodsInfo.getDiscountMerchantFee());
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            // Given
            orderGoodsInfo.setGoodsId("");
            orderGoodsInfo.setGoodsName("");
            orderGoodsInfo.setUnit("");
            
            // Then
            assertEquals("", orderGoodsInfo.getGoodsId());
            assertEquals("", orderGoodsInfo.getGoodsName());
            assertEquals("", orderGoodsInfo.getUnit());
        }

        @Test
        @DisplayName("长字符串测试")
        void testLongStrings() {
            // Given
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            orderGoodsInfo.setGoodsName(longString);
            orderGoodsInfo.setThumb(longString);
            
            // Then
            assertEquals(longString, orderGoodsInfo.getGoodsName());
            assertEquals(longString, orderGoodsInfo.getThumb());
            assertEquals(1000, orderGoodsInfo.getGoodsName().length());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            // Given
            String specialName = "汉堡套餐@#$%^&*()_+中文测试";
            String specialCode = "GOODS_CODE_123!@#";
            
            // When
            orderGoodsInfo.setGoodsName(specialName);
            orderGoodsInfo.setGoodsCode(specialCode);
            
            // Then
            assertEquals(specialName, orderGoodsInfo.getGoodsName());
            assertEquals(specialCode, orderGoodsInfo.getGoodsCode());
        }
    }

    /**
     * 创建完整的OrderGoodsInfo对象用于测试
     */
    private OrderGoodsInfo createFullOrderGoodsInfo() {
        OrderGoodsInfo goodsInfo = new OrderGoodsInfo();
        
        // 基本信息
        goodsInfo.setGoodsId("goods_001");
        goodsInfo.setGoodsName("汉堡套餐");
        goodsInfo.setGoodsCode("BURGER_SET");
        goodsInfo.setThumb("https://example.com/burger.jpg");
        
        // SKU信息
        goodsInfo.setSkuId("sku_001");
        goodsInfo.setSkuName("中杯加辣");
        goodsInfo.setSkuCode("MEDIUM_SPICY");
        
        // 属性信息
        goodsInfo.setFoodProperty(Arrays.asList("6英寸", "原味"));
        goodsInfo.setSkuAttributes(Arrays.asList(new OrderGoodsSkuAttribute("test", 1, "个")));
        goodsInfo.setCommodities(Arrays.asList(new OrderGoodsSkuAttributeGroup("主食", Arrays.asList(new OrderGoodsSkuAttribute("汉堡", 1, "个")))));
        goodsInfo.setUnit("份");
        goodsInfo.setWeight(500);
        goodsInfo.setUpc("1234567890123");
        goodsInfo.setShelfNo("A-001");
        
        // 数量和价格
        goodsInfo.setNumber(2);
        goodsInfo.setGoodsPrice(2000);
        goodsInfo.setGoodsTotalFee(4000);
        goodsInfo.setPackageNumber(1);
        goodsInfo.setPackagePrice(200);
        goodsInfo.setPackageTotalFee(200);
        
        // 优惠信息
        goodsInfo.setReduceFee(1800);
        goodsInfo.setDiscountFee(400);
        goodsInfo.setDiscountPlatformFee(200);
        goodsInfo.setDiscountMerchantFee(100);
        goodsInfo.setDiscountAgentFee(50);
        goodsInfo.setDiscountLogisticsFee(50);
        goodsInfo.setTotalFee(3800);
        
        return goodsInfo;
    }
}