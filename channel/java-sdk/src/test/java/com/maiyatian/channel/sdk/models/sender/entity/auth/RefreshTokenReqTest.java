/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RefreshTokenReq 单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 */
@DisplayName("RefreshTokenReq 单元测试")
class RefreshTokenReqTest {

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
            RefreshTokenReq req = new RefreshTokenReq();
            
            assertNull(req.getToken());
            assertNull(req.getRefreshToken());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullParametersConstructor() {
            String token = "access_token_123";
            String refreshToken = "refresh_token_456";
            
            RefreshTokenReq req = new RefreshTokenReq(token, refreshToken);
            
            assertEquals(token, req.getToken());
            assertEquals(refreshToken, req.getRefreshToken());
        }

        @Test
        @DisplayName("构造函数应接受null值")
        void testConstructorWithNullValues() {
            RefreshTokenReq req = new RefreshTokenReq(null, null);
            
            assertNull(req.getToken());
            assertNull(req.getRefreshToken());
        }

        @Test
        @DisplayName("构造函数应接受空字符串")
        void testConstructorWithEmptyStrings() {
            RefreshTokenReq req = new RefreshTokenReq("", "");
            
            assertEquals("", req.getToken());
            assertEquals("", req.getRefreshToken());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("token的getter和setter应正常工作")
        void testTokenGetterSetter() {
            RefreshTokenReq req = new RefreshTokenReq();
            String token = "access_token_123456789";
            
            req.setToken(token);
            assertEquals(token, req.getToken());
            
            req.setToken(null);
            assertNull(req.getToken());
        }

        @Test
        @DisplayName("refreshToken的getter和setter应正常工作")
        void testRefreshTokenGetterSetter() {
            RefreshTokenReq req = new RefreshTokenReq();
            String refreshToken = "refresh_token_987654321";
            
            req.setRefreshToken(refreshToken);
            assertEquals(refreshToken, req.getRefreshToken());
            
            req.setRefreshToken(null);
            assertNull(req.getRefreshToken());
        }

        @Test
        @DisplayName("setter应接受各种类型的字符串")
        void testSetterWithVariousStrings() {
            RefreshTokenReq req = new RefreshTokenReq();
            
            // 测试空字符串
            req.setToken("");
            req.setRefreshToken("");
            assertEquals("", req.getToken());
            assertEquals("", req.getRefreshToken());
            
            // 测试包含特殊字符的字符串
            String specialToken = "token_!@#$%^&*()_+测试中文";
            req.setToken(specialToken);
            assertEquals(specialToken, req.getToken());
            
            // 测试长字符串
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            req.setRefreshToken(longString);
            assertEquals(longString, req.getRefreshToken());
        }
    }

    @Nested
    @DisplayName("equals和hashCode方法测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象应相等")
        void testSameObjectEquals() {
            RefreshTokenReq req = new RefreshTokenReq("token123", "refresh456");
            
            assertTrue(req.equals(req));
            assertEquals(req.hashCode(), req.hashCode());
        }

        @Test
        @DisplayName("具有相同属性的不同对象应相等")
        void testEqualObjectsWithSameProperties() {
            RefreshTokenReq req1 = new RefreshTokenReq("token123", "refresh456");
            RefreshTokenReq req2 = new RefreshTokenReq("token123", "refresh456");
            
            assertTrue(req1.equals(req2));
            assertTrue(req2.equals(req1));
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("具有不同token的对象应不相等")
        void testUnequalObjectsWithDifferentToken() {
            RefreshTokenReq req1 = new RefreshTokenReq("token123", "refresh456");
            RefreshTokenReq req2 = new RefreshTokenReq("token999", "refresh456");
            
            assertFalse(req1.equals(req2));
            assertFalse(req2.equals(req1));
        }

        @Test
        @DisplayName("具有不同refreshToken的对象应不相等")
        void testUnequalObjectsWithDifferentRefreshToken() {
            RefreshTokenReq req1 = new RefreshTokenReq("token123", "refresh456");
            RefreshTokenReq req2 = new RefreshTokenReq("token123", "refresh999");
            
            assertFalse(req1.equals(req2));
            assertFalse(req2.equals(req1));
        }

        @Test
        @DisplayName("与null比较应返回false")
        void testEqualsWithNull() {
            RefreshTokenReq req = new RefreshTokenReq("token123", "refresh456");
            
            assertFalse(req.equals(null));
        }

        @Test
        @DisplayName("与不同类型对象比较应返回false")
        void testEqualsWithDifferentType() {
            RefreshTokenReq req = new RefreshTokenReq("token123", "refresh456");
            String str = "not a RefreshTokenReq";
            
            assertFalse(req.equals(str));
        }

        @Test
        @DisplayName("包含null字段的对象应正确比较")
        void testEqualsWithNullFields() {
            RefreshTokenReq req1 = new RefreshTokenReq(null, null);
            RefreshTokenReq req2 = new RefreshTokenReq(null, null);
            RefreshTokenReq req3 = new RefreshTokenReq("token123", null);
            RefreshTokenReq req4 = new RefreshTokenReq(null, "refresh456");
            
            assertTrue(req1.equals(req2));
            assertFalse(req1.equals(req3));
            assertFalse(req1.equals(req4));
            assertFalse(req3.equals(req4));
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("空字符串和null应被视为不同")
        void testEmptyStringVsNull() {
            RefreshTokenReq req1 = new RefreshTokenReq("", "");
            RefreshTokenReq req2 = new RefreshTokenReq(null, null);
            
            assertFalse(req1.equals(req2));
            assertFalse(req2.equals(req1));
        }

        @Test
        @DisplayName("大小写敏感比较")
        void testCaseSensitiveComparison() {
            RefreshTokenReq req1 = new RefreshTokenReq("Token123", "Refresh456");
            RefreshTokenReq req2 = new RefreshTokenReq("token123", "refresh456");
            
            assertFalse(req1.equals(req2));
            assertFalse(req2.equals(req1));
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段")
        void testToStringContainsAllFields() {
            RefreshTokenReq req = new RefreshTokenReq("access_token_123456", "refresh_token_789123");
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("RefreshTokenReq"));
            assertTrue(result.contains("token='access_token_123456'"));
            assertTrue(result.contains("refreshToken='refresh_token_789123'"));
        }

        @Test
        @DisplayName("toString应正确处理null值")
        void testToStringWithNullValues() {
            RefreshTokenReq req = new RefreshTokenReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("RefreshTokenReq"));
            assertTrue(result.contains("token='null'"));
            assertTrue(result.contains("refreshToken='null'"));
        }

        @Test
        @DisplayName("toString应正确处理空字符串")
        void testToStringWithEmptyStrings() {
            RefreshTokenReq req = new RefreshTokenReq("", "");
            
            String result = req.toString();
            
            assertTrue(result.contains("token=''"));
            assertTrue(result.contains("refreshToken=''"));
        }

        @Test
        @DisplayName("toString应正确处理包含特殊字符的字符串")
        void testToStringWithSpecialCharacters() {
            RefreshTokenReq req = new RefreshTokenReq("token'with\"quotes", "refresh\nwith\ttabs");
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("token='token'with\"quotes'"));
            assertTrue(result.contains("refreshToken='refresh\nwith\ttabs'"));
        }

        @Test
        @DisplayName("toString应格式正确")
        void testToStringFormat() {
            RefreshTokenReq req = new RefreshTokenReq("token123", "refresh456");
            
            String result = req.toString();
            
            assertEquals("RefreshTokenReq{token='token123', refreshToken='refresh456'}", result);
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("完整对象应正确序列化为JSON")
        void testJsonSerialization() throws JsonProcessingException {
            RefreshTokenReq req = new RefreshTokenReq("access_token_123", "refresh_token_456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"token\":\"access_token_123\""));
            assertTrue(json.contains("\"refresh_token\":\"refresh_token_456\""));
        }

        @Test
        @DisplayName("JSON应正确反序列化为对象")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"token\":\"access_token_123\",\"refresh_token\":\"refresh_token_456\"}";
            
            RefreshTokenReq req = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals("access_token_123", req.getToken());
            assertEquals("refresh_token_456", req.getRefreshToken());
        }

        @Test
        @DisplayName("序列化反序列化应保持对象相等性")
        void testSerializationDeserialization() throws JsonProcessingException {
            RefreshTokenReq original = new RefreshTokenReq("access_token_123", "refresh_token_456");
            
            String json = objectMapper.writeValueAsString(original);
            RefreshTokenReq deserialized = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应正确序列化")
        void testJsonSerializationWithNullFields() throws JsonProcessingException {
            RefreshTokenReq req = new RefreshTokenReq(null, "refresh_token_456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"refresh_token\":\"refresh_token_456\""));
            // null值的处理取决于ObjectMapper配置
        }

        @Test
        @DisplayName("空字符串应正确序列化和反序列化")
        void testJsonWithEmptyStrings() throws JsonProcessingException {
            RefreshTokenReq req = new RefreshTokenReq("", "");
            
            String json = objectMapper.writeValueAsString(req);
            RefreshTokenReq deserialized = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals(req, deserialized);
            assertEquals("", deserialized.getToken());
            assertEquals("", deserialized.getRefreshToken());
        }

        @Test
        @DisplayName("包含特殊字符的字符串应正确序列化和反序列化")
        void testJsonWithSpecialCharacters() throws JsonProcessingException {
            String specialToken = "token_!@#$%^&*()_+测试中文🎉";
            String specialRefreshToken = "refresh\"with'quotes\nand\ttabs";
            RefreshTokenReq req = new RefreshTokenReq(specialToken, specialRefreshToken);
            
            String json = objectMapper.writeValueAsString(req);
            RefreshTokenReq deserialized = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals(req, deserialized);
            assertEquals(specialToken, deserialized.getToken());
            assertEquals(specialRefreshToken, deserialized.getRefreshToken());
        }

        @Test
        @DisplayName("部分JSON应正确反序列化")
        void testPartialJsonDeserialization() throws JsonProcessingException {
            String json = "{\"token\":\"access_token_123\"}";
            
            RefreshTokenReq req = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals("access_token_123", req.getToken());
            assertNull(req.getRefreshToken());
        }

        @Test
        @DisplayName("JSON字段名应使用注解指定的名称")
        void testJsonFieldNames() throws JsonProcessingException {
            RefreshTokenReq req = new RefreshTokenReq("token123", "refresh456");
            
            String json = objectMapper.writeValueAsString(req);
            
            // 应该使用@JsonProperty指定的字段名
            assertTrue(json.contains("\"token\":"));
            assertTrue(json.contains("\"refresh_token\":"));
            // 不应该包含Java字段名
            assertFalse(json.contains("\"refreshToken\":"));
        }

        @Test
        @DisplayName("反序列化应忽略未知字段")
        void testDeserializationIgnoresUnknownFields() throws JsonProcessingException {
            String json = "{\"token\":\"token123\",\"refresh_token\":\"refresh456\",\"unknown_field\":\"value\"}";
            
            // 默认情况下，Jackson会忽略未知字段（除非配置为失败）
            RefreshTokenReq req = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals("token123", req.getToken());
            assertEquals("refresh456", req.getRefreshToken());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应正确处理极长字符串")
        void testVeryLongStrings() {
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            
            RefreshTokenReq req = new RefreshTokenReq(longString, longString);
            
            assertEquals(longString, req.getToken());
            assertEquals(longString, req.getRefreshToken());
            
            // toString应该能处理长字符串
            String result = req.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("应正确处理包含Unicode字符的字符串")
        void testUnicodeCharacters() {
            String unicodeToken = "token_测试_🎉_中文_αβγ_русский";
            String unicodeRefresh = "refresh_😀😁😂🤣😃😄😅😆😉";
            
            RefreshTokenReq req = new RefreshTokenReq(unicodeToken, unicodeRefresh);
            
            assertEquals(unicodeToken, req.getToken());
            assertEquals(unicodeRefresh, req.getRefreshToken());
        }

        @Test
        @DisplayName("应正确处理只包含空格的字符串")
        void testWhitespaceOnlyStrings() {
            String spacesToken = "   ";
            String tabsRefresh = "\t\t\t";
            
            RefreshTokenReq req = new RefreshTokenReq(spacesToken, tabsRefresh);
            
            assertEquals(spacesToken, req.getToken());
            assertEquals(tabsRefresh, req.getRefreshToken());
        }

        @Test
        @DisplayName("应正确处理包含换行符的字符串")
        void testStringsWithNewlines() {
            String tokenWithNewlines = "token\nwith\nmultiple\nlines";
            String refreshWithNewlines = "refresh\r\nwith\r\nCRLF";
            
            RefreshTokenReq req = new RefreshTokenReq(tokenWithNewlines, refreshWithNewlines);
            
            assertEquals(tokenWithNewlines, req.getToken());
            assertEquals(refreshWithNewlines, req.getRefreshToken());
        }

        @Test
        @DisplayName("equals和hashCode应处理极端情况")
        void testEqualsHashCodeExtremeValues() {
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("x");
            }
            String longString = longStringBuilder.toString();
            
            RefreshTokenReq req1 = new RefreshTokenReq(longString, longString);
            RefreshTokenReq req2 = new RefreshTokenReq(longString, longString);
            
            assertTrue(req1.equals(req2));
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("JSON序列化应处理最大长度字符串")
        void testJsonWithMaxLengthStrings() throws JsonProcessingException {
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 5000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            
            RefreshTokenReq req = new RefreshTokenReq(longString, longString);
            
            String json = objectMapper.writeValueAsString(req);
            RefreshTokenReq deserialized = objectMapper.readValue(json, RefreshTokenReq.class);
            
            assertEquals(req, deserialized);
        }

        @Test
        @DisplayName("构造函数应处理混合null和非null值")
        void testConstructorWithMixedNullValues() {
            RefreshTokenReq req1 = new RefreshTokenReq("token123", null);
            RefreshTokenReq req2 = new RefreshTokenReq(null, "refresh456");
            
            assertEquals("token123", req1.getToken());
            assertNull(req1.getRefreshToken());
            
            assertNull(req2.getToken());
            assertEquals("refresh456", req2.getRefreshToken());
        }

        @Test
        @DisplayName("setter链式调用测试")
        void testSetterChaining() {
            RefreshTokenReq req = new RefreshTokenReq();
            
            // 虽然没有返回this的setter，但应该能连续调用
            req.setToken("token123");
            req.setRefreshToken("refresh456");
            
            assertEquals("token123", req.getToken());
            assertEquals("refresh456", req.getRefreshToken());
        }
    }
}