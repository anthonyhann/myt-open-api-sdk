/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShopDetailReq 实体类的 JUnit 5 单元测试
 */
@DisplayName("ShopDetailReq Tests")
class ShopDetailReqTest {

    private ShopDetailReq shopDetailReq;
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        shopDetailReq = new ShopDetailReq();
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void testDefaultConstructor() {
            ShopDetailReq req = new ShopDetailReq();
            
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("全参构造函数应正确设置参数")
        void testFullConstructor() {
            String shopId = "shop123";
            
            ShopDetailReq req = new ShopDetailReq(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("构造函数应能处理null参数")
        void testConstructorWithNullParameter() {
            ShopDetailReq req = new ShopDetailReq(null);
            
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("构造函数应能处理空字符串参数")
        void testConstructorWithEmptyParameter() {
            ShopDetailReq req = new ShopDetailReq("");
            
            assertEquals("", req.getShopId());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId 的 getter 和 setter 应正常工作")
        void testShopIdGetterSetter() {
            String shopId = "shop123";
            
            shopDetailReq.setShopId(shopId);
            
            assertEquals(shopId, shopDetailReq.getShopId());
        }

        @Test
        @DisplayName("设置null值应正常工作")
        void testSetNullValue() {
            shopDetailReq.setShopId(null);
            
            assertNull(shopDetailReq.getShopId());
        }

        @Test
        @DisplayName("设置空字符串应正常工作")
        void testSetEmptyString() {
            shopDetailReq.setShopId("");
            
            assertEquals("", shopDetailReq.getShopId());
        }

        @Test
        @DisplayName("设置包含空格的字符串应正常工作")
        void testSetStringWithWhitespace() {
            String shopId = "  shop123  ";
            
            shopDetailReq.setShopId(shopId);
            
            assertEquals(shopId, shopDetailReq.getShopId());
        }

        @Test
        @DisplayName("设置特殊字符应正常工作")
        void testSetStringWithSpecialCharacters() {
            String shopId = "shop_123-test@example.com";
            
            shopDetailReq.setShopId(shopId);
            
            assertEquals(shopId, shopDetailReq.getShopId());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            ShopDetailReq req1 = new ShopDetailReq("shop123");
            ShopDetailReq req2 = new ShopDetailReq("shop123");
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            ShopDetailReq req1 = new ShopDetailReq("shop123");
            ShopDetailReq req2 = new ShopDetailReq("shop456");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            
            assertEquals(req, req);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            String other = "different type";
            
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("两个都为null的对象应相等")
        void testEqualsWithBothNull() {
            ShopDetailReq req1 = new ShopDetailReq(null);
            ShopDetailReq req2 = new ShopDetailReq(null);
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("一个为null一个不为null的对象应不相等")
        void testEqualsWithOneNull() {
            ShopDetailReq req1 = new ShopDetailReq(null);
            ShopDetailReq req2 = new ShopDetailReq("shop123");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("两个空字符串对象应相等")
        void testEqualsWithBothEmpty() {
            ShopDetailReq req1 = new ShopDetailReq("");
            ShopDetailReq req2 = new ShopDetailReq("");
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("大小写不同的对象应不相等")
        void testEqualsWithDifferentCase() {
            ShopDetailReq req1 = new ShopDetailReq("shop123");
            ShopDetailReq req2 = new ShopDetailReq("SHOP123");
            
            assertNotEquals(req1, req2);
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含类名和字段信息")
        void testToString() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopDetailReq"));
            assertTrue(result.contains("shopId='shop123'"));
        }

        @Test
        @DisplayName("null值的toString应正确处理")
        void testToStringWithNullValue() {
            ShopDetailReq req = new ShopDetailReq(null);
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopDetailReq"));
            assertTrue(result.contains("shopId=null"));
        }

        @Test
        @DisplayName("空字符串的toString应正确处理")
        void testToStringWithEmptyValue() {
            ShopDetailReq req = new ShopDetailReq("");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopDetailReq"));
            assertTrue(result.contains("shopId=''"));
        }

        @Test
        @DisplayName("包含特殊字符的toString应正确处理")
        void testToStringWithSpecialCharacters() {
            ShopDetailReq req = new ShopDetailReq("shop_123-test@example.com");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopDetailReq"));
            assertTrue(result.contains("shopId='shop_123-test@example.com'"));
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            ShopDetailReq req = new ShopDetailReq("shop123");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
        }

        @Test
        @DisplayName("null值应能正确序列化")
        void testSerializationWithNull() throws IOException {
            ShopDetailReq req = new ShopDetailReq(null);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":null"));
        }

        @Test
        @DisplayName("空字符串应能正确序列化")
        void testSerializationWithEmpty() throws IOException {
            ShopDetailReq req = new ShopDetailReq("");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"\""));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"shop_id\":\"shop123\"}";
            
            ShopDetailReq req = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertEquals("shop123", req.getShopId());
        }

        @Test
        @DisplayName("null值的JSON应能正确反序列化")
        void testDeserializationWithNull() throws IOException {
            String json = "{\"shop_id\":null}";
            
            ShopDetailReq req = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("空字符串的JSON应能正确反序列化")
        void testDeserializationWithEmpty() throws IOException {
            String json = "{\"shop_id\":\"\"}";
            
            ShopDetailReq req = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertEquals("", req.getShopId());
        }

        @Test
        @DisplayName("缺少字段的JSON应能正确反序列化")
        void testDeserializationWithMissingField() throws IOException {
            String json = "{}";
            
            ShopDetailReq req = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("包含额外字段的JSON应能正确反序列化")
        void testDeserializationWithExtraFields() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"extra_field\":\"extra_value\"}";
            
            ShopDetailReq req = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertEquals("shop123", req.getShopId());
        }

        @Test
        @DisplayName("序列化和反序列化应保持数据一致性")
        void testSerializationDeseriializationConsistency() throws IOException {
            ShopDetailReq original = new ShopDetailReq("shop123");
            
            String json = objectMapper.writeValueAsString(original);
            ShopDetailReq deserialized = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的请求对象应通过验证")
        void testValidRequest() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            
            Set<ConstraintViolation<ShopDetailReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("shopId为null不应有验证错误（因为没有@NotNull注解）")
        void testNullShopId() {
            ShopDetailReq req = new ShopDetailReq(null);
            
            Set<ConstraintViolation<ShopDetailReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("shopId为空字符串不应有验证错误（因为没有@NotBlank注解）")
        void testEmptyShopId() {
            ShopDetailReq req = new ShopDetailReq("");
            
            Set<ConstraintViolation<ShopDetailReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("shopId为只包含空格的字符串不应有验证错误")
        void testWhitespaceOnlyShopId() {
            ShopDetailReq req = new ShopDetailReq("   ");
            
            Set<ConstraintViolation<ShopDetailReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("非常长的shopId应正常处理")
        void testVeryLongShopId() {
            StringBuilder longShopId = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longShopId.append("shop");
            }
            
            ShopDetailReq req = new ShopDetailReq(longShopId.toString());
            
            assertEquals(longShopId.toString(), req.getShopId());
        }

        @Test
        @DisplayName("包含Unicode字符的shopId应正常处理")
        void testUnicodeShopId() {
            String unicodeShopId = "门店_123_测试";
            
            ShopDetailReq req = new ShopDetailReq(unicodeShopId);
            
            assertEquals(unicodeShopId, req.getShopId());
        }

        @Test
        @DisplayName("包含换行符的shopId应正常处理")
        void testShopIdWithNewline() {
            String shopIdWithNewline = "shop\n123";
            
            ShopDetailReq req = new ShopDetailReq(shopIdWithNewline);
            
            assertEquals(shopIdWithNewline, req.getShopId());
        }

        @Test
        @DisplayName("包含制表符的shopId应正常处理")
        void testShopIdWithTab() {
            String shopIdWithTab = "shop\t123";
            
            ShopDetailReq req = new ShopDetailReq(shopIdWithTab);
            
            assertEquals(shopIdWithTab, req.getShopId());
        }

        @Test
        @DisplayName("包含JSON特殊字符的shopId应正确序列化和反序列化")
        void testShopIdWithJsonSpecialCharacters() throws IOException {
            String shopIdWithSpecialChars = "shop\"123\\456/789\b\f\n\r\t";
            ShopDetailReq original = new ShopDetailReq(shopIdWithSpecialChars);
            
            String json = objectMapper.writeValueAsString(original);
            ShopDetailReq deserialized = objectMapper.readValue(json, ShopDetailReq.class);
            
            assertEquals(original, deserialized);
            assertEquals(shopIdWithSpecialChars, deserialized.getShopId());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("空构造函数创建的对象应可以后续设置值")
        void testSetValueAfterDefaultConstruction() {
            ShopDetailReq req = new ShopDetailReq();
            assertNull(req.getShopId());
            
            req.setShopId("shop123");
            assertEquals("shop123", req.getShopId());
        }

        @Test
        @DisplayName("可以重复设置shopId值")
        void testRepeatedSetting() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            assertEquals("shop123", req.getShopId());
            
            req.setShopId("shop456");
            assertEquals("shop456", req.getShopId());
            
            req.setShopId(null);
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("对象创建后修改不影响equals比较")
        void testEqualsAfterModification() {
            ShopDetailReq req1 = new ShopDetailReq("shop123");
            ShopDetailReq req2 = new ShopDetailReq("shop123");
            
            assertEquals(req1, req2);
            
            req1.setShopId("modified");
            assertNotEquals(req1, req2);
            
            req1.setShopId("shop123");
            assertEquals(req1, req2);
        }
    }

    @Nested
    @DisplayName("Memory and Performance Tests")
    class MemoryPerformanceTests {

        @Test
        @DisplayName("大量对象创建不应导致内存溢出")
        void testMassObjectCreation() {
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 10000; i++) {
                    ShopDetailReq req = new ShopDetailReq("shop" + i);
                    assertEquals("shop" + i, req.getShopId());
                }
            });
        }

        @Test
        @DisplayName("toString方法应高效执行")
        void testToStringPerformance() {
            ShopDetailReq req = new ShopDetailReq("shop123");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    String result = req.toString();
                    assertNotNull(result);
                }
            });
        }

        @Test
        @DisplayName("equals和hashCode方法应高效执行")
        void testEqualsHashCodePerformance() {
            ShopDetailReq req1 = new ShopDetailReq("shop123");
            ShopDetailReq req2 = new ShopDetailReq("shop123");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    boolean result = req1.equals(req2);
                    assertTrue(result);
                    int hash1 = req1.hashCode();
                    int hash2 = req2.hashCode();
                    assertEquals(hash1, hash2);
                }
            });
        }
    }
}