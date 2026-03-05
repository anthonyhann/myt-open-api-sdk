/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

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
 * ShopDetailResp 实体类的 JUnit 5 单元测试
 */
@DisplayName("ShopDetailResp Tests")
class ShopDetailRespTest {

    private ShopDetailResp shopDetailResp;
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        shopDetailResp = new ShopDetailResp();
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void testDefaultConstructor() {
            ShopDetailResp resp = new ShopDetailResp();
            
            assertNull(resp.getShopId());
            assertNull(resp.getName());
            assertNull(resp.getPhone());
            assertNull(resp.getProvince());
            assertNull(resp.getCity());
            assertNull(resp.getAddress());
            assertNull(resp.getLatitude());
            assertNull(resp.getLongitude());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullConstructor() {
            String shopId = "shop123";
            String name = "测试门店";
            String phone = "13800138000";
            String province = "北京市";
            String city = "北京市";
            String address = "朝阳区某某街道123号";
            String latitude = "39.9042";
            String longitude = "116.4074";
            
            ShopDetailResp resp = new ShopDetailResp(shopId, name, phone, province, city, address, latitude, longitude);
            
            assertEquals(shopId, resp.getShopId());
            assertEquals(name, resp.getName());
            assertEquals(phone, resp.getPhone());
            assertEquals(province, resp.getProvince());
            assertEquals(city, resp.getCity());
            assertEquals(address, resp.getAddress());
            assertEquals(latitude, resp.getLatitude());
            assertEquals(longitude, resp.getLongitude());
        }

        @Test
        @DisplayName("构造函数应能处理null参数")
        void testConstructorWithNullParameters() {
            ShopDetailResp resp = new ShopDetailResp(null, null, null, null, null, null, null, null);
            
            assertNull(resp.getShopId());
            assertNull(resp.getName());
            assertNull(resp.getPhone());
            assertNull(resp.getProvince());
            assertNull(resp.getCity());
            assertNull(resp.getAddress());
            assertNull(resp.getLatitude());
            assertNull(resp.getLongitude());
        }

        @Test
        @DisplayName("构造函数应能处理空字符串参数")
        void testConstructorWithEmptyParameters() {
            ShopDetailResp resp = new ShopDetailResp("", "", "", "", "", "", "", "");
            
            assertEquals("", resp.getShopId());
            assertEquals("", resp.getName());
            assertEquals("", resp.getPhone());
            assertEquals("", resp.getProvince());
            assertEquals("", resp.getCity());
            assertEquals("", resp.getAddress());
            assertEquals("", resp.getLatitude());
            assertEquals("", resp.getLongitude());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId 的 getter 和 setter 应正常工作")
        void testShopIdGetterSetter() {
            String shopId = "shop123";
            shopDetailResp.setShopId(shopId);
            assertEquals(shopId, shopDetailResp.getShopId());
        }

        @Test
        @DisplayName("name 的 getter 和 setter 应正常工作")
        void testNameGetterSetter() {
            String name = "测试门店";
            shopDetailResp.setName(name);
            assertEquals(name, shopDetailResp.getName());
        }

        @Test
        @DisplayName("phone 的 getter 和 setter 应正常工作")
        void testPhoneGetterSetter() {
            String phone = "13800138000";
            shopDetailResp.setPhone(phone);
            assertEquals(phone, shopDetailResp.getPhone());
        }

        @Test
        @DisplayName("province 的 getter 和 setter 应正常工作")
        void testProvinceGetterSetter() {
            String province = "北京市";
            shopDetailResp.setProvince(province);
            assertEquals(province, shopDetailResp.getProvince());
        }

        @Test
        @DisplayName("city 的 getter 和 setter 应正常工作")
        void testCityGetterSetter() {
            String city = "北京市";
            shopDetailResp.setCity(city);
            assertEquals(city, shopDetailResp.getCity());
        }

        @Test
        @DisplayName("address 的 getter 和 setter 应正常工作")
        void testAddressGetterSetter() {
            String address = "朝阳区某某街道123号";
            shopDetailResp.setAddress(address);
            assertEquals(address, shopDetailResp.getAddress());
        }

        @Test
        @DisplayName("latitude 的 getter 和 setter 应正常工作")
        void testLatitudeGetterSetter() {
            String latitude = "39.9042";
            shopDetailResp.setLatitude(latitude);
            assertEquals(latitude, shopDetailResp.getLatitude());
        }

        @Test
        @DisplayName("longitude 的 getter 和 setter 应正常工作")
        void testLongitudeGetterSetter() {
            String longitude = "116.4074";
            shopDetailResp.setLongitude(longitude);
            assertEquals(longitude, shopDetailResp.getLongitude());
        }

        @Test
        @DisplayName("所有字段设置null值应正常工作")
        void testSetNullValues() {
            shopDetailResp.setShopId(null);
            shopDetailResp.setName(null);
            shopDetailResp.setPhone(null);
            shopDetailResp.setProvince(null);
            shopDetailResp.setCity(null);
            shopDetailResp.setAddress(null);
            shopDetailResp.setLatitude(null);
            shopDetailResp.setLongitude(null);
            
            assertNull(shopDetailResp.getShopId());
            assertNull(shopDetailResp.getName());
            assertNull(shopDetailResp.getPhone());
            assertNull(shopDetailResp.getProvince());
            assertNull(shopDetailResp.getCity());
            assertNull(shopDetailResp.getAddress());
            assertNull(shopDetailResp.getLatitude());
            assertNull(shopDetailResp.getLongitude());
        }

        @Test
        @DisplayName("所有字段设置空字符串应正常工作")
        void testSetEmptyStrings() {
            shopDetailResp.setShopId("");
            shopDetailResp.setName("");
            shopDetailResp.setPhone("");
            shopDetailResp.setProvince("");
            shopDetailResp.setCity("");
            shopDetailResp.setAddress("");
            shopDetailResp.setLatitude("");
            shopDetailResp.setLongitude("");
            
            assertEquals("", shopDetailResp.getShopId());
            assertEquals("", shopDetailResp.getName());
            assertEquals("", shopDetailResp.getPhone());
            assertEquals("", shopDetailResp.getProvince());
            assertEquals("", shopDetailResp.getCity());
            assertEquals("", shopDetailResp.getAddress());
            assertEquals("", shopDetailResp.getLatitude());
            assertEquals("", shopDetailResp.getLongitude());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            ShopDetailResp resp1 = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            ShopDetailResp resp2 = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertEquals(resp1, resp2);
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            ShopDetailResp resp1 = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            ShopDetailResp resp2 = new ShopDetailResp("shop456", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertNotEquals(resp1, resp2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertEquals(resp, resp);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertNotEquals(resp, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            String other = "different type";
            
            assertNotEquals(resp, other);
        }

        @Test
        @DisplayName("所有字段都为null的对象应相等")
        void testEqualsWithAllNull() {
            ShopDetailResp resp1 = new ShopDetailResp(null, null, null, null, null, null, null, null);
            ShopDetailResp resp2 = new ShopDetailResp(null, null, null, null, null, null, null, null);
            
            assertEquals(resp1, resp2);
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("部分字段不同的对象应不相等")
        void testEqualsWithPartialDifference() {
            ShopDetailResp resp1 = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            ShopDetailResp resp2 = new ShopDetailResp("shop123", "测试门店", "13800138001", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertNotEquals(resp1, resp2);
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段信息")
        void testToString() {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopDetailResp"));
            assertTrue(result.contains("shopId='shop123'"));
            assertTrue(result.contains("name='测试门店'"));
            assertTrue(result.contains("phone='13800138000'"));
            assertTrue(result.contains("province='北京市'"));
            assertTrue(result.contains("city='北京市'"));
            assertTrue(result.contains("address='朝阳区某某街道123号'"));
            assertTrue(result.contains("latitude='39.9042'"));
            assertTrue(result.contains("longitude='116.4074'"));
        }

        @Test
        @DisplayName("空对象的toString应正确处理null值")
        void testToStringWithNullValues() {
            ShopDetailResp resp = new ShopDetailResp();
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopDetailResp"));
            assertTrue(result.contains("shopId=null"));
            assertTrue(result.contains("name=null"));
            assertTrue(result.contains("phone=null"));
            assertTrue(result.contains("province=null"));
            assertTrue(result.contains("city=null"));
            assertTrue(result.contains("address=null"));
            assertTrue(result.contains("latitude=null"));
            assertTrue(result.contains("longitude=null"));
        }

        @Test
        @DisplayName("空字符串字段的toString应正确处理")
        void testToStringWithEmptyValues() {
            ShopDetailResp resp = new ShopDetailResp("", "", "", "", "", "", "", "");
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopDetailResp"));
            assertTrue(result.contains("shopId=''"));
            assertTrue(result.contains("name=''"));
            assertTrue(result.contains("phone=''"));
            assertTrue(result.contains("province=''"));
            assertTrue(result.contains("city=''"));
            assertTrue(result.contains("address=''"));
            assertTrue(result.contains("latitude=''"));
            assertTrue(result.contains("longitude=''"));
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"name\":\"测试门店\""));
            assertTrue(json.contains("\"phone\":\"13800138000\""));
            assertTrue(json.contains("\"province\":\"北京市\""));
            assertTrue(json.contains("\"city\":\"北京市\""));
            assertTrue(json.contains("\"address\":\"朝阳区某某街道123号\""));
            assertTrue(json.contains("\"latitude\":\"39.9042\""));
            assertTrue(json.contains("\"longitude\":\"116.4074\""));
        }

        @Test
        @DisplayName("包含null值的对象应能正确序列化")
        void testSerializationWithNullValues() throws IOException {
            ShopDetailResp resp = new ShopDetailResp("shop123", null, null, null, null, null, null, null);
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"name\":null"));
            assertTrue(json.contains("\"phone\":null"));
            assertTrue(json.contains("\"province\":null"));
            assertTrue(json.contains("\"city\":null"));
            assertTrue(json.contains("\"address\":null"));
            assertTrue(json.contains("\"latitude\":null"));
            assertTrue(json.contains("\"longitude\":null"));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"name\":\"测试门店\",\"phone\":\"13800138000\",\"province\":\"北京市\",\"city\":\"北京市\",\"address\":\"朝阳区某某街道123号\",\"latitude\":\"39.9042\",\"longitude\":\"116.4074\"}";
            
            ShopDetailResp resp = objectMapper.readValue(json, ShopDetailResp.class);
            
            assertEquals("shop123", resp.getShopId());
            assertEquals("测试门店", resp.getName());
            assertEquals("13800138000", resp.getPhone());
            assertEquals("北京市", resp.getProvince());
            assertEquals("北京市", resp.getCity());
            assertEquals("朝阳区某某街道123号", resp.getAddress());
            assertEquals("39.9042", resp.getLatitude());
            assertEquals("116.4074", resp.getLongitude());
        }

        @Test
        @DisplayName("部分字段缺失的JSON应能正确反序列化")
        void testDeserializationWithMissingFields() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"name\":\"测试门店\"}";
            
            ShopDetailResp resp = objectMapper.readValue(json, ShopDetailResp.class);
            
            assertEquals("shop123", resp.getShopId());
            assertEquals("测试门店", resp.getName());
            assertNull(resp.getPhone());
            assertNull(resp.getProvince());
            assertNull(resp.getCity());
            assertNull(resp.getAddress());
            assertNull(resp.getLatitude());
            assertNull(resp.getLongitude());
        }

        @Test
        @DisplayName("包含null值的JSON应能正确反序列化")
        void testDeserializationWithNullValues() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"name\":null,\"phone\":null,\"province\":null,\"city\":null,\"address\":null,\"latitude\":null,\"longitude\":null}";
            
            ShopDetailResp resp = objectMapper.readValue(json, ShopDetailResp.class);
            
            assertEquals("shop123", resp.getShopId());
            assertNull(resp.getName());
            assertNull(resp.getPhone());
            assertNull(resp.getProvince());
            assertNull(resp.getCity());
            assertNull(resp.getAddress());
            assertNull(resp.getLatitude());
            assertNull(resp.getLongitude());
        }

        @Test
        @DisplayName("序列化和反序列化应保持数据一致性")
        void testSerializationDeserializationConsistency() throws IOException {
            ShopDetailResp original = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            String json = objectMapper.writeValueAsString(original);
            ShopDetailResp deserialized = objectMapper.readValue(json, ShopDetailResp.class);
            
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的响应对象应通过验证")
        void testValidResponse() {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            Set<ConstraintViolation<ShopDetailResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("所有字段为null不应有验证错误（因为没有验证注解）")
        void testAllNullFields() {
            ShopDetailResp resp = new ShopDetailResp(null, null, null, null, null, null, null, null);
            
            Set<ConstraintViolation<ShopDetailResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("所有字段为空字符串不应有验证错误")
        void testAllEmptyFields() {
            ShopDetailResp resp = new ShopDetailResp("", "", "", "", "", "", "", "");
            
            Set<ConstraintViolation<ShopDetailResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("门店信息应能表示完整的地址信息")
        void testCompleteAddressInfo() {
            ShopDetailResp resp = new ShopDetailResp();
            resp.setProvince("北京市");
            resp.setCity("北京市");
            resp.setAddress("朝阳区某某街道123号");
            
            assertEquals("北京市", resp.getProvince());
            assertEquals("北京市", resp.getCity());
            assertEquals("朝阳区某某街道123号", resp.getAddress());
        }

        @Test
        @DisplayName("门店信息应能表示完整的坐标信息")
        void testCompleteLocationInfo() {
            ShopDetailResp resp = new ShopDetailResp();
            resp.setLatitude("39.9042");
            resp.setLongitude("116.4074");
            
            assertEquals("39.9042", resp.getLatitude());
            assertEquals("116.4074", resp.getLongitude());
        }

        @Test
        @DisplayName("门店信息应能表示完整的联系信息")
        void testCompleteContactInfo() {
            ShopDetailResp resp = new ShopDetailResp();
            resp.setName("测试门店");
            resp.setPhone("13800138000");
            
            assertEquals("测试门店", resp.getName());
            assertEquals("13800138000", resp.getPhone());
        }

        @Test
        @DisplayName("可以独立设置和获取每个字段")
        void testIndependentFieldOperations() {
            ShopDetailResp resp = new ShopDetailResp();
            
            // 逐个设置字段
            resp.setShopId("shop123");
            assertEquals("shop123", resp.getShopId());
            assertNull(resp.getName());
            
            resp.setName("测试门店");
            assertEquals("测试门店", resp.getName());
            assertNull(resp.getPhone());
            
            resp.setPhone("13800138000");
            assertEquals("13800138000", resp.getPhone());
            assertNull(resp.getProvince());
            
            // 验证之前设置的字段仍然存在
            assertEquals("shop123", resp.getShopId());
            assertEquals("测试门店", resp.getName());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("所有字符串字段为空字符串的处理")
        void testAllEmptyStringFields() {
            ShopDetailResp resp = new ShopDetailResp("", "", "", "", "", "", "", "");
            
            assertEquals("", resp.getShopId());
            assertEquals("", resp.getName());
            assertEquals("", resp.getPhone());
            assertEquals("", resp.getProvince());
            assertEquals("", resp.getCity());
            assertEquals("", resp.getAddress());
            assertEquals("", resp.getLatitude());
            assertEquals("", resp.getLongitude());
        }

        @Test
        @DisplayName("字符串字段包含空格的处理")
        void testWhitespaceStringFields() {
            ShopDetailResp resp = new ShopDetailResp("  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ");
            
            assertEquals("  ", resp.getShopId());
            assertEquals("  ", resp.getName());
            assertEquals("  ", resp.getPhone());
            assertEquals("  ", resp.getProvince());
            assertEquals("  ", resp.getCity());
            assertEquals("  ", resp.getAddress());
            assertEquals("  ", resp.getLatitude());
            assertEquals("  ", resp.getLongitude());
        }

        @Test
        @DisplayName("包含特殊字符的字段处理")
        void testSpecialCharactersInFields() {
            String specialChars = "shop_123-test@example.com";
            ShopDetailResp resp = new ShopDetailResp();
            resp.setShopId(specialChars);
            resp.setName(specialChars);
            resp.setPhone(specialChars);
            
            assertEquals(specialChars, resp.getShopId());
            assertEquals(specialChars, resp.getName());
            assertEquals(specialChars, resp.getPhone());
        }

        @Test
        @DisplayName("非常长的字符串字段处理")
        void testVeryLongStringFields() {
            StringBuilder longString = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longString.append("长");
            }
            
            ShopDetailResp resp = new ShopDetailResp();
            resp.setName(longString.toString());
            resp.setAddress(longString.toString());
            
            assertEquals(longString.toString(), resp.getName());
            assertEquals(longString.toString(), resp.getAddress());
        }

        @Test
        @DisplayName("坐标字段的边界值处理")
        void testCoordinateBoundaryValues() {
            ShopDetailResp resp = new ShopDetailResp();
            
            // 最大纬度值
            resp.setLatitude("90.0");
            assertEquals("90.0", resp.getLatitude());
            
            // 最小纬度值
            resp.setLatitude("-90.0");
            assertEquals("-90.0", resp.getLatitude());
            
            // 最大经度值
            resp.setLongitude("180.0");
            assertEquals("180.0", resp.getLongitude());
            
            // 最小经度值
            resp.setLongitude("-180.0");
            assertEquals("-180.0", resp.getLongitude());
        }

        @Test
        @DisplayName("手机号格式的多样性处理")
        void testPhoneNumberFormats() {
            ShopDetailResp resp = new ShopDetailResp();
            
            String[] phoneFormats = {
                "13800138000",
                "138-0013-8000",
                "+86-138-0013-8000",
                "(138) 0013-8000",
                "138 0013 8000"
            };
            
            for (String phone : phoneFormats) {
                resp.setPhone(phone);
                assertEquals(phone, resp.getPhone());
            }
        }

        @Test
        @DisplayName("包含Unicode字符的处理")
        void testUnicodeCharacters() {
            ShopDetailResp resp = new ShopDetailResp();
            
            String unicodeName = "麦芽田测试门店🏪";
            String unicodeAddress = "北京市朝阳区某某街道123号📍";
            
            resp.setName(unicodeName);
            resp.setAddress(unicodeAddress);
            
            assertEquals(unicodeName, resp.getName());
            assertEquals(unicodeAddress, resp.getAddress());
        }

        @Test
        @DisplayName("JSON特殊字符的序列化和反序列化")
        void testJsonSpecialCharactersSerializationDeserialization() throws IOException {
            String nameWithSpecialChars = "测试\"门店\"\\换行\n制表\t";
            ShopDetailResp original = new ShopDetailResp();
            original.setShopId("shop123");
            original.setName(nameWithSpecialChars);
            
            String json = objectMapper.writeValueAsString(original);
            ShopDetailResp deserialized = objectMapper.readValue(json, ShopDetailResp.class);
            
            assertEquals(original, deserialized);
            assertEquals(nameWithSpecialChars, deserialized.getName());
        }
    }

    @Nested
    @DisplayName("Performance Tests")
    class PerformanceTests {

        @Test
        @DisplayName("大量对象创建不应导致内存溢出")
        void testMassObjectCreation() {
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 5000; i++) {
                    ShopDetailResp resp = new ShopDetailResp("shop" + i, "门店" + i, "1380013" + String.format("%04d", i), "北京市", "北京市", "地址" + i, "39.90" + (i % 100), "116.40" + (i % 100));
                    assertEquals("shop" + i, resp.getShopId());
                }
            });
        }

        @Test
        @DisplayName("toString方法应高效执行")
        void testToStringPerformance() {
            ShopDetailResp resp = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    String result = resp.toString();
                    assertNotNull(result);
                    assertTrue(result.length() > 0);
                }
            });
        }

        @Test
        @DisplayName("equals和hashCode方法应高效执行")
        void testEqualsHashCodePerformance() {
            ShopDetailResp resp1 = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            ShopDetailResp resp2 = new ShopDetailResp("shop123", "测试门店", "13800138000", "北京市", "北京市", "朝阳区某某街道123号", "39.9042", "116.4074");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    boolean result = resp1.equals(resp2);
                    assertTrue(result);
                    int hash1 = resp1.hashCode();
                    int hash2 = resp2.hashCode();
                    assertEquals(hash1, hash2);
                }
            });
        }
    }
}