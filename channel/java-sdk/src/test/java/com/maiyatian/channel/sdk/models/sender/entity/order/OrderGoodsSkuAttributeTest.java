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

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderGoodsSkuAttribute 单元测试
 * 
 * @author Test Generator
 * @version 1.0.0
 */
@DisplayName("OrderGoodsSkuAttribute 测试")
public class OrderGoodsSkuAttributeTest {

    private ObjectMapper objectMapper;
    private OrderGoodsSkuAttribute skuAttribute;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        skuAttribute = new OrderGoodsSkuAttribute();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute();
            
            // Then
            assertNotNull(attribute);
            assertNull(attribute.getName());
            assertNull(attribute.getNumber());
            assertNull(attribute.getUnit());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            // Given
            String name = "烧鸡200g";
            Integer number = 1;
            String unit = "份";
            
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute(name, number, unit);
            
            // Then
            assertNotNull(attribute);
            assertEquals(name, attribute.getName());
            assertEquals(number, attribute.getNumber());
            assertEquals(unit, attribute.getUnit());
        }

        @Test
        @DisplayName("全参构造函数 - null值测试")
        void testFullConstructorWithNullValues() {
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute(null, null, null);
            
            // Then
            assertNotNull(attribute);
            assertNull(attribute.getName());
            assertNull(attribute.getNumber());
            assertNull(attribute.getUnit());
        }

        @Test
        @DisplayName("全参构造函数 - 部分null值测试")
        void testFullConstructorWithPartialNullValues() {
            // Given
            String name = "大杯可乐";
            
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute(name, null, null);
            
            // Then
            assertNotNull(attribute);
            assertEquals(name, attribute.getName());
            assertNull(attribute.getNumber());
            assertNull(attribute.getUnit());
        }
    }

    @Nested
    @DisplayName("Getter 和 Setter 测试")
    class GetterSetterTests {

        @Test
        @DisplayName("名称测试")
        void testName() {
            // Given
            String name = "烧鸡200g";
            
            // When
            skuAttribute.setName(name);
            
            // Then
            assertEquals(name, skuAttribute.getName());
        }

        @Test
        @DisplayName("名称 - null值测试")
        void testNameWithNull() {
            // When
            skuAttribute.setName(null);
            
            // Then
            assertNull(skuAttribute.getName());
        }

        @Test
        @DisplayName("名称 - 空字符串测试")
        void testNameWithEmptyString() {
            // When
            skuAttribute.setName("");
            
            // Then
            assertEquals("", skuAttribute.getName());
        }

        @Test
        @DisplayName("数量测试")
        void testNumber() {
            // Given
            Integer number = 2;
            
            // When
            skuAttribute.setNumber(number);
            
            // Then
            assertEquals(number, skuAttribute.getNumber());
        }

        @Test
        @DisplayName("数量 - null值测试")
        void testNumberWithNull() {
            // When
            skuAttribute.setNumber(null);
            
            // Then
            assertNull(skuAttribute.getNumber());
        }

        @Test
        @DisplayName("数量 - 零值测试")
        void testNumberWithZero() {
            // When
            skuAttribute.setNumber(0);
            
            // Then
            assertEquals(Integer.valueOf(0), skuAttribute.getNumber());
        }

        @Test
        @DisplayName("单位测试")
        void testUnit() {
            // Given
            String unit = "份";
            
            // When
            skuAttribute.setUnit(unit);
            
            // Then
            assertEquals(unit, skuAttribute.getUnit());
        }

        @Test
        @DisplayName("单位 - null值测试")
        void testUnitWithNull() {
            // When
            skuAttribute.setUnit(null);
            
            // Then
            assertNull(skuAttribute.getUnit());
        }

        @Test
        @DisplayName("单位 - 空字符串测试")
        void testUnitWithEmptyString() {
            // When
            skuAttribute.setUnit("");
            
            // Then
            assertEquals("", skuAttribute.getUnit());
        }
    }

    @Nested
    @DisplayName("equals 和 hashCode 测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象 equals 测试")
        void testEqualsWithSameInstance() {
            // Then
            assertEquals(skuAttribute, skuAttribute);
            assertEquals(skuAttribute.hashCode(), skuAttribute.hashCode());
        }

        @Test
        @DisplayName("null 对象 equals 测试")
        void testEqualsWithNull() {
            // Then
            assertNotEquals(null, skuAttribute);
        }

        @Test
        @DisplayName("不同类型对象 equals 测试")
        void testEqualsWithDifferentType() {
            // Given
            String differentType = "different";
            
            // Then
            assertNotEquals(skuAttribute, differentType);
        }

        @Test
        @DisplayName("相同属性对象 equals 测试")
        void testEqualsWithSameProperties() {
            // Given
            OrderGoodsSkuAttribute attribute1 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            OrderGoodsSkuAttribute attribute2 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            
            // Then
            assertEquals(attribute1, attribute2);
            assertEquals(attribute1.hashCode(), attribute2.hashCode());
        }

        @Test
        @DisplayName("不同名称 equals 测试")
        void testEqualsWithDifferentName() {
            // Given
            OrderGoodsSkuAttribute attribute1 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            OrderGoodsSkuAttribute attribute2 = new OrderGoodsSkuAttribute("大杯可乐", 1, "份");
            
            // Then
            assertNotEquals(attribute1, attribute2);
        }

        @Test
        @DisplayName("不同数量 equals 测试")
        void testEqualsWithDifferentNumber() {
            // Given
            OrderGoodsSkuAttribute attribute1 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            OrderGoodsSkuAttribute attribute2 = new OrderGoodsSkuAttribute("烧鸡200g", 2, "份");
            
            // Then
            assertNotEquals(attribute1, attribute2);
        }

        @Test
        @DisplayName("不同单位 equals 测试")
        void testEqualsWithDifferentUnit() {
            // Given
            OrderGoodsSkuAttribute attribute1 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            OrderGoodsSkuAttribute attribute2 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "个");
            
            // Then
            assertNotEquals(attribute1, attribute2);
        }

        @Test
        @DisplayName("null值属性 equals 测试")
        void testEqualsWithNullProperties() {
            // Given
            OrderGoodsSkuAttribute attribute1 = new OrderGoodsSkuAttribute(null, null, null);
            OrderGoodsSkuAttribute attribute2 = new OrderGoodsSkuAttribute(null, null, null);
            
            // Then
            assertEquals(attribute1, attribute2);
            assertEquals(attribute1.hashCode(), attribute2.hashCode());
        }

        @Test
        @DisplayName("部分null值属性 equals 测试")
        void testEqualsWithPartialNullProperties() {
            // Given
            OrderGoodsSkuAttribute attribute1 = new OrderGoodsSkuAttribute("烧鸡200g", null, "份");
            OrderGoodsSkuAttribute attribute2 = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            
            // Then
            assertNotEquals(attribute1, attribute2);
        }
    }

    @Nested
    @DisplayName("toString 测试")
    class ToStringTests {

        @Test
        @DisplayName("空对象 toString 测试")
        void testToStringWithEmptyObject() {
            // When
            String result = skuAttribute.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderGoodsSkuAttribute"));
            assertTrue(result.contains("name=null"));
            assertTrue(result.contains("number=null"));
            assertTrue(result.contains("unit=null"));
        }

        @Test
        @DisplayName("完整对象 toString 测试")
        void testToStringWithFullObject() {
            // Given
            skuAttribute.setName("烧鸡200g");
            skuAttribute.setNumber(1);
            skuAttribute.setUnit("份");
            
            // When
            String result = skuAttribute.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderGoodsSkuAttribute"));
            assertTrue(result.contains("name='烧鸡200g'"));
            assertTrue(result.contains("number=1"));
            assertTrue(result.contains("unit='份'"));
        }

        @Test
        @DisplayName("部分属性 toString 测试")
        void testToStringWithPartialProperties() {
            // Given
            skuAttribute.setName("大杯可乐");
            skuAttribute.setNumber(null);
            skuAttribute.setUnit("杯");
            
            // When
            String result = skuAttribute.toString();
            
            // Then
            assertTrue(result.contains("name='大杯可乐'"));
            assertTrue(result.contains("number=null"));
            assertTrue(result.contains("unit='杯'"));
        }

        @Test
        @DisplayName("特殊字符 toString 测试")
        void testToStringWithSpecialCharacters() {
            // Given
            skuAttribute.setName("麻辣烫@#$%");
            skuAttribute.setNumber(5);
            skuAttribute.setUnit("碗/份");
            
            // When
            String result = skuAttribute.toString();
            
            // Then
            assertTrue(result.contains("name='麻辣烫@#$%'"));
            assertTrue(result.contains("number=5"));
            assertTrue(result.contains("unit='碗/份'"));
        }
    }

    @Nested
    @DisplayName("JSON 序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("空对象序列化测试")
        void testSerializeEmptyObject() throws JsonProcessingException {
            // When
            String json = objectMapper.writeValueAsString(skuAttribute);
            
            // Then
            assertNotNull(json);
            assertEquals("{}", json);
        }

        @Test
        @DisplayName("完整对象序列化测试")
        void testSerializeFullObject() throws JsonProcessingException {
            // Given
            skuAttribute.setName("烧鸡200g");
            skuAttribute.setNumber(1);
            skuAttribute.setUnit("份");
            
            // When
            String json = objectMapper.writeValueAsString(skuAttribute);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"name\":\"烧鸡200g\""));
            assertTrue(json.contains("\"number\":1"));
            assertTrue(json.contains("\"unit\":\"份\""));
        }

        @Test
        @DisplayName("部分属性序列化测试")
        void testSerializePartialProperties() throws JsonProcessingException {
            // Given
            skuAttribute.setName("大杯可乐");
            skuAttribute.setUnit("杯");
            
            // When
            String json = objectMapper.writeValueAsString(skuAttribute);
            
            // Then
            assertTrue(json.contains("\"name\":\"大杯可乐\""));
            assertTrue(json.contains("\"unit\":\"杯\""));
            assertFalse(json.contains("\"number\":"));
        }

        @Test
        @DisplayName("反序列化测试")
        void testDeserialize() throws JsonProcessingException {
            // Given
            String json = "{\"name\":\"烧鸡200g\",\"number\":1,\"unit\":\"份\"}";
            
            // When
            OrderGoodsSkuAttribute attribute = objectMapper.readValue(json, OrderGoodsSkuAttribute.class);
            
            // Then
            assertNotNull(attribute);
            assertEquals("烧鸡200g", attribute.getName());
            assertEquals(Integer.valueOf(1), attribute.getNumber());
            assertEquals("份", attribute.getUnit());
        }

        @Test
        @DisplayName("序列化后反序列化一致性测试")
        void testSerializeDeserializeConsistency() throws JsonProcessingException {
            // Given
            OrderGoodsSkuAttribute original = new OrderGoodsSkuAttribute("烧鸡200g", 1, "份");
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderGoodsSkuAttribute deserialized = objectMapper.readValue(json, OrderGoodsSkuAttribute.class);
            
            // Then
            assertEquals(original.getName(), deserialized.getName());
            assertEquals(original.getNumber(), deserialized.getNumber());
            assertEquals(original.getUnit(), deserialized.getUnit());
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("中文字符序列化测试")
        void testSerializeChineseCharacters() throws JsonProcessingException {
            // Given
            skuAttribute.setName("麻辣烫套餐");
            skuAttribute.setNumber(2);
            skuAttribute.setUnit("碗");
            
            // When
            String json = objectMapper.writeValueAsString(skuAttribute);
            OrderGoodsSkuAttribute deserialized = objectMapper.readValue(json, OrderGoodsSkuAttribute.class);
            
            // Then
            assertEquals("麻辣烫套餐", deserialized.getName());
            assertEquals(Integer.valueOf(2), deserialized.getNumber());
            assertEquals("碗", deserialized.getUnit());
        }

        @Test
        @DisplayName("空字符串序列化测试")
        void testSerializeEmptyStrings() throws JsonProcessingException {
            // Given
            skuAttribute.setName("");
            skuAttribute.setNumber(1);
            skuAttribute.setUnit("");
            
            // When
            String json = objectMapper.writeValueAsString(skuAttribute);
            
            // Then
            assertTrue(json.contains("\"name\":\"\""));
            assertTrue(json.contains("\"number\":1"));
            assertTrue(json.contains("\"unit\":\"\""));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("最大整数值测试")
        void testMaxIntegerValue() {
            // When
            skuAttribute.setNumber(Integer.MAX_VALUE);
            
            // Then
            assertEquals(Integer.MAX_VALUE, skuAttribute.getNumber());
        }

        @Test
        @DisplayName("最小整数值测试")
        void testMinIntegerValue() {
            // When
            skuAttribute.setNumber(Integer.MIN_VALUE);
            
            // Then
            assertEquals(Integer.MIN_VALUE, skuAttribute.getNumber());
        }

        @Test
        @DisplayName("零值测试")
        void testZeroValue() {
            // When
            skuAttribute.setNumber(0);
            
            // Then
            assertEquals(Integer.valueOf(0), skuAttribute.getNumber());
        }

        @Test
        @DisplayName("负数测试")
        void testNegativeValue() {
            // When
            skuAttribute.setNumber(-5);
            
            // Then
            assertEquals(Integer.valueOf(-5), skuAttribute.getNumber());
        }

        @Test
        @DisplayName("长字符串测试")
        void testLongString() {
            // Given
            StringBuilder longNameBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longNameBuilder.append("a");
            }
            String longName = longNameBuilder.toString();
            
            StringBuilder longUnitBuilder = new StringBuilder();
            for (int i = 0; i < 500; i++) {
                longUnitBuilder.append("u");
            }
            String longUnit = longUnitBuilder.toString();
            
            // When
            skuAttribute.setName(longName);
            skuAttribute.setUnit(longUnit);
            
            // Then
            assertEquals(longName, skuAttribute.getName());
            assertEquals(longUnit, skuAttribute.getUnit());
            assertEquals(1000, skuAttribute.getName().length());
            assertEquals(500, skuAttribute.getUnit().length());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            // Given
            String specialName = "商品@#$%^&*()_+{}|:<>?";
            String specialUnit = "单位!@#$%^&*()_+";
            
            // When
            skuAttribute.setName(specialName);
            skuAttribute.setUnit(specialUnit);
            
            // Then
            assertEquals(specialName, skuAttribute.getName());
            assertEquals(specialUnit, skuAttribute.getUnit());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            // Given
            String unicodeName = "🍔汉堡套餐🍟";
            String unicodeUnit = "份📦";
            
            // When
            skuAttribute.setName(unicodeName);
            skuAttribute.setUnit(unicodeUnit);
            
            // Then
            assertEquals(unicodeName, skuAttribute.getName());
            assertEquals(unicodeUnit, skuAttribute.getUnit());
        }

        @Test
        @DisplayName("空格字符测试")
        void testWhitespaceCharacters() {
            // Given
            String nameWithSpaces = "  烧鸡 200g  ";
            String unitWithSpaces = "  份  ";
            
            // When
            skuAttribute.setName(nameWithSpaces);
            skuAttribute.setUnit(unitWithSpaces);
            
            // Then
            assertEquals(nameWithSpaces, skuAttribute.getName());
            assertEquals(unitWithSpaces, skuAttribute.getUnit());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("有效商品属性创建测试")
        void testValidAttributeCreation() {
            // Given
            String name = "烧鸡200g";
            Integer number = 1;
            String unit = "份";
            
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute(name, number, unit);
            
            // Then
            assertTrue(isValidAttribute(attribute));
        }

        @Test
        @DisplayName("默认数量属性创建测试")
        void testAttributeWithDefaultNumber() {
            // Given
            String name = "大杯可乐";
            String unit = "杯";
            
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute(name, null, unit);
            
            // Then
            assertEquals(name, attribute.getName());
            assertNull(attribute.getNumber()); // 数量可以为空，表示使用默认值1
            assertEquals(unit, attribute.getUnit());
        }

        @Test
        @DisplayName("可选单位属性创建测试")
        void testAttributeWithOptionalUnit() {
            // Given
            String name = "薯条";
            Integer number = 1;
            
            // When
            OrderGoodsSkuAttribute attribute = new OrderGoodsSkuAttribute(name, number, null);
            
            // Then
            assertEquals(name, attribute.getName());
            assertEquals(number, attribute.getNumber());
            assertNull(attribute.getUnit()); // 单位可以为空
        }

        /**
         * 简单的业务逻辑验证：属性名称不能为空
         */
        private boolean isValidAttribute(OrderGoodsSkuAttribute attribute) {
            return attribute != null && 
                   attribute.getName() != null && 
                   !attribute.getName().trim().isEmpty();
        }
    }
}