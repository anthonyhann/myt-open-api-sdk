/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AccessTokenReq 单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 */
@DisplayName("AccessTokenReq 单元测试")
class AccessTokenReqTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void testDefaultConstructor() {
            AccessTokenReq req = new AccessTokenReq();
            
            assertNull(req.getGrantType());
            assertNull(req.getCode());
            assertNull(req.getShopId());
            assertNull(req.getCategory());
            assertNull(req.getName());
            assertNull(req.getType());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
        }

        @Test
        @DisplayName("核心参数构造函数应正确设置必需参数")
        void testCoreParametersConstructor() {
            String grantType = "shop";
            String code = "test_code_123";
            String shopId = "shop_456";
            
            AccessTokenReq req = new AccessTokenReq(grantType, code, shopId);
            
            assertEquals(grantType, req.getGrantType());
            assertEquals(code, req.getCode());
            assertEquals(shopId, req.getShopId());
            assertNull(req.getCategory());
            assertNull(req.getName());
            assertNull(req.getType());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullParametersConstructor() {
            String grantType = "shop";
            String code = "test_code_123";
            String shopId = "shop_456";
            String category = "food";
            String name = "测试餐厅";
            String type = "waimai";
            String longitude = "116.404";
            String latitude = "39.915";
            
            AccessTokenReq req = new AccessTokenReq(grantType, code, shopId, category, name, type, longitude, latitude);
            
            assertEquals(grantType, req.getGrantType());
            assertEquals(code, req.getCode());
            assertEquals(shopId, req.getShopId());
            assertEquals(category, req.getCategory());
            assertEquals(name, req.getName());
            assertEquals(type, req.getType());
            assertEquals(longitude, req.getLongitude());
            assertEquals(latitude, req.getLatitude());
        }

        @Test
        @DisplayName("构造函数应接受null值")
        void testConstructorWithNullValues() {
            AccessTokenReq req = new AccessTokenReq(null, null, null);
            
            assertNull(req.getGrantType());
            assertNull(req.getCode());
            assertNull(req.getShopId());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("grantType的getter和setter应正常工作")
        void testGrantTypeGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String grantType = "shop";
            
            req.setGrantType(grantType);
            assertEquals(grantType, req.getGrantType());
            
            req.setGrantType(null);
            assertNull(req.getGrantType());
        }

        @Test
        @DisplayName("code的getter和setter应正常工作")
        void testCodeGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String code = "test_auth_code_12345";
            
            req.setCode(code);
            assertEquals(code, req.getCode());
            
            req.setCode(null);
            assertNull(req.getCode());
        }

        @Test
        @DisplayName("shopId的getter和setter应正常工作")
        void testShopIdGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String shopId = "shop_test_789";
            
            req.setShopId(shopId);
            assertEquals(shopId, req.getShopId());
            
            req.setShopId(null);
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("category的getter和setter应正常工作")
        void testCategoryGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String category = "food";
            
            req.setCategory(category);
            assertEquals(category, req.getCategory());
            
            req.setCategory(null);
            assertNull(req.getCategory());
        }

        @Test
        @DisplayName("name的getter和setter应正常工作")
        void testNameGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String name = "测试门店名称";
            
            req.setName(name);
            assertEquals(name, req.getName());
            
            req.setName(null);
            assertNull(req.getName());
        }

        @Test
        @DisplayName("type的getter和setter应正常工作")
        void testTypeGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String type = "waimai";
            
            req.setType(type);
            assertEquals(type, req.getType());
            
            req.setType(null);
            assertNull(req.getType());
        }

        @Test
        @DisplayName("longitude的getter和setter应正常工作")
        void testLongitudeGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String longitude = "116.404";
            
            req.setLongitude(longitude);
            assertEquals(longitude, req.getLongitude());
            
            req.setLongitude(null);
            assertNull(req.getLongitude());
        }

        @Test
        @DisplayName("latitude的getter和setter应正常工作")
        void testLatitudeGetterSetter() {
            AccessTokenReq req = new AccessTokenReq();
            String latitude = "39.915";
            
            req.setLatitude(latitude);
            assertEquals(latitude, req.getLatitude());
            
            req.setLatitude(null);
            assertNull(req.getLatitude());
        }
    }

    @Nested
    @DisplayName("链式设置方法测试")
    class FluentSetterTests {

        @Test
        @DisplayName("withCategory应正确设置category并返回自身")
        void testWithCategory() {
            AccessTokenReq req = new AccessTokenReq();
            String category = "retail";
            
            AccessTokenReq result = req.withCategory(category);
            
            assertSame(req, result);
            assertEquals(category, req.getCategory());
        }

        @Test
        @DisplayName("withName应正确设置name并返回自身")
        void testWithName() {
            AccessTokenReq req = new AccessTokenReq();
            String name = "连锁便利店";
            
            AccessTokenReq result = req.withName(name);
            
            assertSame(req, result);
            assertEquals(name, req.getName());
        }

        @Test
        @DisplayName("withType应正确设置type并返回自身")
        void testWithType() {
            AccessTokenReq req = new AccessTokenReq();
            String type = "shop";
            
            AccessTokenReq result = req.withType(type);
            
            assertSame(req, result);
            assertEquals(type, req.getType());
        }

        @Test
        @DisplayName("withLocation应正确设置经纬度并返回自身")
        void testWithLocation() {
            AccessTokenReq req = new AccessTokenReq();
            String longitude = "121.473";
            String latitude = "31.230";
            
            AccessTokenReq result = req.withLocation(longitude, latitude);
            
            assertSame(req, result);
            assertEquals(longitude, req.getLongitude());
            assertEquals(latitude, req.getLatitude());
        }

        @Test
        @DisplayName("链式调用应正常工作")
        void testFluentChaining() {
            AccessTokenReq req = new AccessTokenReq("shop", "code123", "shop456");
            
            AccessTokenReq result = req.withCategory("food")
                    .withName("美味餐厅")
                    .withType("waimai")
                    .withLocation("116.404", "39.915");
            
            assertSame(req, result);
            assertEquals("food", req.getCategory());
            assertEquals("美味餐厅", req.getName());
            assertEquals("waimai", req.getType());
            assertEquals("116.404", req.getLongitude());
            assertEquals("39.915", req.getLatitude());
        }

        @Test
        @DisplayName("链式设置方法应接受null值")
        void testFluentSettersWithNull() {
            AccessTokenReq req = new AccessTokenReq();
            
            req.withCategory(null)
                    .withName(null)
                    .withType(null)
                    .withLocation(null, null);
            
            assertNull(req.getCategory());
            assertNull(req.getName());
            assertNull(req.getType());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("createShopAuth应创建正确的门店授权请求")
        void testCreateShopAuth() {
            String code = "auth_code_789";
            String shopId = "shop_123";
            
            AccessTokenReq req = AccessTokenReq.createShopAuth(code, shopId);
            
            assertEquals("shop", req.getGrantType());
            assertEquals(code, req.getCode());
            assertEquals(shopId, req.getShopId());
            assertNull(req.getCategory());
            assertNull(req.getName());
            assertNull(req.getType());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
        }

        @Test
        @DisplayName("createShopAuth应接受null值")
        void testCreateShopAuthWithNullValues() {
            AccessTokenReq req = AccessTokenReq.createShopAuth(null, null);
            
            assertEquals("shop", req.getGrantType());
            assertNull(req.getCode());
            assertNull(req.getShopId());
        }

        @Test
        @DisplayName("createShopAuth创建的对象应支持链式调用")
        void testCreateShopAuthWithFluentChaining() {
            AccessTokenReq req = AccessTokenReq.createShopAuth("code123", "shop456")
                    .withCategory("food")
                    .withName("测试餐厅")
                    .withLocation("116.404", "39.915");
            
            assertEquals("shop", req.getGrantType());
            assertEquals("code123", req.getCode());
            assertEquals("shop456", req.getShopId());
            assertEquals("food", req.getCategory());
            assertEquals("测试餐厅", req.getName());
            assertEquals("116.404", req.getLongitude());
            assertEquals("39.915", req.getLatitude());
        }
    }

    @Nested
    @DisplayName("equals和hashCode方法测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象应相等")
        void testSameObjectEquals() {
            AccessTokenReq req = new AccessTokenReq("shop", "code123", "shop456");
            
            assertTrue(req.equals(req));
            assertEquals(req.hashCode(), req.hashCode());
        }

        @Test
        @DisplayName("具有相同属性的不同对象应相等")
        void testEqualObjectsWithSameProperties() {
            AccessTokenReq req1 = new AccessTokenReq("shop", "code123", "shop456", "food", "餐厅", "waimai", "116.404", "39.915");
            AccessTokenReq req2 = new AccessTokenReq("shop", "code123", "shop456", "food", "餐厅", "waimai", "116.404", "39.915");
            
            assertTrue(req1.equals(req2));
            assertTrue(req2.equals(req1));
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("具有不同属性的对象应不相等")
        void testUnequalObjectsWithDifferentProperties() {
            AccessTokenReq req1 = new AccessTokenReq("shop", "code123", "shop456");
            AccessTokenReq req2 = new AccessTokenReq("shop", "code456", "shop456");
            
            assertFalse(req1.equals(req2));
            assertFalse(req2.equals(req1));
            // hashCode可能相同，但不是必须不同
        }

        @Test
        @DisplayName("与null比较应返回false")
        void testEqualsWithNull() {
            AccessTokenReq req = new AccessTokenReq("shop", "code123", "shop456");
            
            assertFalse(req.equals(null));
        }

        @Test
        @DisplayName("与不同类型对象比较应返回false")
        void testEqualsWithDifferentType() {
            AccessTokenReq req = new AccessTokenReq("shop", "code123", "shop456");
            String str = "not an AccessTokenReq";
            
            assertFalse(req.equals(str));
        }

        @Test
        @DisplayName("包含null字段的对象应正确比较")
        void testEqualsWithNullFields() {
            AccessTokenReq req1 = new AccessTokenReq(null, null, null);
            AccessTokenReq req2 = new AccessTokenReq(null, null, null);
            AccessTokenReq req3 = new AccessTokenReq("shop", null, null);
            
            assertTrue(req1.equals(req2));
            assertFalse(req1.equals(req3));
            assertEquals(req1.hashCode(), req2.hashCode());
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段")
        void testToStringContainsAllFields() {
            AccessTokenReq req = new AccessTokenReq("shop", "code123456789", "shop456", "food", "餐厅", "waimai", "116.404", "39.915");
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("AccessTokenReq"));
            assertTrue(result.contains("grantType='shop'"));
            assertTrue(result.contains("code123***")); // 敏感信息应被脱敏
            assertTrue(result.contains("shopId='shop456'"));
            assertTrue(result.contains("category='food'"));
            assertTrue(result.contains("name='餐厅'"));
            assertTrue(result.contains("type='waimai'"));
            assertTrue(result.contains("longitude='116.404'"));
            assertTrue(result.contains("latitude='39.915'"));
        }

        @Test
        @DisplayName("toString应正确处理null值")
        void testToStringWithNullValues() {
            AccessTokenReq req = new AccessTokenReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("AccessTokenReq"));
            assertTrue(result.contains("grantType='null'"));
            assertTrue(result.contains("code='null'"));
            assertTrue(result.contains("shopId='null'"));
        }

        @Test
        @DisplayName("toString应正确脱敏短code")
        void testToStringWithShortCode() {
            AccessTokenReq req = new AccessTokenReq("shop", "abc", "shop456");
            
            String result = req.toString();
            
            assertTrue(result.contains("code='abc***'"));
        }

        @Test
        @DisplayName("toString应正确脱敏长code")
        void testToStringWithLongCode() {
            String longCode = "verylongauthcode123456789";
            AccessTokenReq req = new AccessTokenReq("shop", longCode, "shop456");
            
            String result = req.toString();
            
            assertTrue(result.contains("code='verylong***'"));
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("完整对象应正确序列化为JSON")
        void testJsonSerialization() throws JsonProcessingException {
            AccessTokenReq req = new AccessTokenReq("shop", "code123", "shop456", "food", "餐厅", "waimai", "116.404", "39.915");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"grant_type\":\"shop\""));
            assertTrue(json.contains("\"code\":\"code123\""));
            assertTrue(json.contains("\"shop_id\":\"shop456\""));
            assertTrue(json.contains("\"category\":\"food\""));
            assertTrue(json.contains("\"name\":\"餐厅\""));
            assertTrue(json.contains("\"type\":\"waimai\""));
            assertTrue(json.contains("\"longitude\":\"116.404\""));
            assertTrue(json.contains("\"latitude\":\"39.915\""));
        }

        @Test
        @DisplayName("JSON应正确反序列化为对象")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"grant_type\":\"shop\",\"code\":\"code123\",\"shop_id\":\"shop456\",\"category\":\"food\",\"name\":\"餐厅\",\"type\":\"waimai\",\"longitude\":\"116.404\",\"latitude\":\"39.915\"}";
            
            AccessTokenReq req = objectMapper.readValue(json, AccessTokenReq.class);
            
            assertEquals("shop", req.getGrantType());
            assertEquals("code123", req.getCode());
            assertEquals("shop456", req.getShopId());
            assertEquals("food", req.getCategory());
            assertEquals("餐厅", req.getName());
            assertEquals("waimai", req.getType());
            assertEquals("116.404", req.getLongitude());
            assertEquals("39.915", req.getLatitude());
        }

        @Test
        @DisplayName("序列化反序列化应保持对象相等性")
        void testSerializationDeserialization() throws JsonProcessingException {
            AccessTokenReq original = new AccessTokenReq("shop", "code123", "shop456", "food", "餐厅", "waimai", "116.404", "39.915");
            
            String json = objectMapper.writeValueAsString(original);
            AccessTokenReq deserialized = objectMapper.readValue(json, AccessTokenReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应正确序列化")
        void testJsonSerializationWithNullFields() throws JsonProcessingException {
            AccessTokenReq req = new AccessTokenReq("shop", "code123", "shop456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"grant_type\":\"shop\""));
            assertTrue(json.contains("\"code\":\"code123\""));
            assertTrue(json.contains("\"shop_id\":\"shop456\""));
            // null字段可能不出现在JSON中，这取决于ObjectMapper配置
        }

        @Test
        @DisplayName("部分JSON应正确反序列化")
        void testPartialJsonDeserialization() throws JsonProcessingException {
            String json = "{\"grant_type\":\"shop\",\"code\":\"code123\",\"shop_id\":\"shop456\"}";
            
            AccessTokenReq req = objectMapper.readValue(json, AccessTokenReq.class);
            
            assertEquals("shop", req.getGrantType());
            assertEquals("code123", req.getCode());
            assertEquals("shop456", req.getShopId());
            assertNull(req.getCategory());
            assertNull(req.getName());
            assertNull(req.getType());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应正确处理空字符串")
        void testEmptyStrings() {
            AccessTokenReq req = new AccessTokenReq("", "", "");
            
            assertEquals("", req.getGrantType());
            assertEquals("", req.getCode());
            assertEquals("", req.getShopId());
        }

        @Test
        @DisplayName("应正确处理包含特殊字符的字符串")
        void testSpecialCharacters() {
            String specialChars = "!@#$%^&*()测试中文🎉";
            AccessTokenReq req = new AccessTokenReq();
            req.setName(specialChars);
            
            assertEquals(specialChars, req.getName());
        }

        @Test
        @DisplayName("应正确处理极长字符串")
        void testVeryLongStrings() {
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            
            AccessTokenReq req = new AccessTokenReq();
            req.setCode(longString);
            
            assertEquals(longString, req.getCode());
        }

        @Test
        @DisplayName("withLocation应正确处理边界经纬度值")
        void testLocationBoundaryValues() {
            AccessTokenReq req = new AccessTokenReq();
            
            // 测试极限经纬度
            req.withLocation("180.0", "90.0");
            assertEquals("180.0", req.getLongitude());
            assertEquals("90.0", req.getLatitude());
            
            req.withLocation("-180.0", "-90.0");
            assertEquals("-180.0", req.getLongitude());
            assertEquals("-90.0", req.getLatitude());
        }

        @Test
        @DisplayName("toString应处理极长code的脱敏")
        void testToStringWithVeryLongCode() {
            StringBuilder longCodeBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                longCodeBuilder.append("a");
            }
            String longCode = longCodeBuilder.toString();
            
            AccessTokenReq req = new AccessTokenReq("shop", longCode, "shop456");
            String result = req.toString();
            
            // 应该只显示前8个字符加***
            String expected = longCode.substring(0, 8) + "***";
            assertTrue(result.contains("code='" + expected + "'"));
        }
    }
}