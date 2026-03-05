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
 * TokenUnbindReq 实体类的 JUnit 5 单元测试
 */
@DisplayName("TokenUnbindReq Tests")
class TokenUnbindReqTest {

    private TokenUnbindReq tokenUnbindReq;
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        tokenUnbindReq = new TokenUnbindReq();
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
            TokenUnbindReq req = new TokenUnbindReq();
            
            assertNull(req.getShopId());
            assertNull(req.getToken());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullConstructor() {
            String shopId = "shop123";
            String token = "token456";
            
            TokenUnbindReq req = new TokenUnbindReq(shopId, token);
            
            assertEquals(shopId, req.getShopId());
            assertEquals(token, req.getToken());
        }

        @Test
        @DisplayName("构造函数应能处理null参数")
        void testConstructorWithNullParameters() {
            TokenUnbindReq req = new TokenUnbindReq(null, null);
            
            assertNull(req.getShopId());
            assertNull(req.getToken());
        }

        @Test
        @DisplayName("构造函数应能处理空字符串参数")
        void testConstructorWithEmptyParameters() {
            TokenUnbindReq req = new TokenUnbindReq("", "");
            
            assertEquals("", req.getShopId());
            assertEquals("", req.getToken());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId 的 getter 和 setter 应正常工作")
        void testShopIdGetterSetter() {
            String shopId = "shop123";
            tokenUnbindReq.setShopId(shopId);
            assertEquals(shopId, tokenUnbindReq.getShopId());
        }

        @Test
        @DisplayName("token 的 getter 和 setter 应正常工作")
        void testTokenGetterSetter() {
            String token = "token456";
            tokenUnbindReq.setToken(token);
            assertEquals(token, tokenUnbindReq.getToken());
        }

        @Test
        @DisplayName("所有字段设置null值应正常工作")
        void testSetNullValues() {
            tokenUnbindReq.setShopId(null);
            tokenUnbindReq.setToken(null);
            
            assertNull(tokenUnbindReq.getShopId());
            assertNull(tokenUnbindReq.getToken());
        }

        @Test
        @DisplayName("所有字段设置空字符串应正常工作")
        void testSetEmptyStrings() {
            tokenUnbindReq.setShopId("");
            tokenUnbindReq.setToken("");
            
            assertEquals("", tokenUnbindReq.getShopId());
            assertEquals("", tokenUnbindReq.getToken());
        }

        @Test
        @DisplayName("字段设置空格字符串应正常工作")
        void testSetWhitespaceStrings() {
            tokenUnbindReq.setShopId("  ");
            tokenUnbindReq.setToken("  ");
            
            assertEquals("  ", tokenUnbindReq.getShopId());
            assertEquals("  ", tokenUnbindReq.getToken());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq req2 = new TokenUnbindReq("shop123", "token456");
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq req2 = new TokenUnbindReq("shop456", "token456");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            assertEquals(req, req);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            String other = "different type";
            
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("所有字段都为null的对象应相等")
        void testEqualsWithAllNull() {
            TokenUnbindReq req1 = new TokenUnbindReq(null, null);
            TokenUnbindReq req2 = new TokenUnbindReq(null, null);
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("部分字段不同的对象应不相等")
        void testEqualsWithPartialDifference() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq req2 = new TokenUnbindReq("shop123", "token789");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("shopId相同但token不同的对象应不相等")
        void testEqualsWithSameShopIdDifferentToken() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq req2 = new TokenUnbindReq("shop123", "token789");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("shopId不同但token相同的对象应不相等")
        void testEqualsWithDifferentShopIdSameToken() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq req2 = new TokenUnbindReq("shop789", "token456");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("空字符串字段的对象应相等")
        void testEqualsWithEmptyStrings() {
            TokenUnbindReq req1 = new TokenUnbindReq("", "");
            TokenUnbindReq req2 = new TokenUnbindReq("", "");
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("混合null和非null字段的对象应不相等")
        void testEqualsWithMixedNullAndNonNull() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", null);
            TokenUnbindReq req2 = new TokenUnbindReq("shop123", "token456");
            
            assertNotEquals(req1, req2);
            
            TokenUnbindReq req3 = new TokenUnbindReq(null, "token456");
            TokenUnbindReq req4 = new TokenUnbindReq("shop123", "token456");
            
            assertNotEquals(req3, req4);
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段信息")
        void testToString() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            String result = req.toString();
            
            assertTrue(result.contains("TokenUnbindReq"));
            assertTrue(result.contains("shopId='shop123'"));
            assertTrue(result.contains("token='token456'"));
        }

        @Test
        @DisplayName("空对象的toString应正确处理null值")
        void testToStringWithNullValues() {
            TokenUnbindReq req = new TokenUnbindReq();
            
            String result = req.toString();
            
            assertTrue(result.contains("TokenUnbindReq"));
            assertTrue(result.contains("shopId=null"));
            assertTrue(result.contains("token=null"));
        }

        @Test
        @DisplayName("包含空字符串的toString应正确处理")
        void testToStringWithEmptyValues() {
            TokenUnbindReq req = new TokenUnbindReq("", "");
            
            String result = req.toString();
            
            assertTrue(result.contains("TokenUnbindReq"));
            assertTrue(result.contains("shopId=''"));
            assertTrue(result.contains("token=''"));
        }

        @Test
        @DisplayName("部分null字段的toString应正确处理")
        void testToStringWithPartialNullValues() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", null);
            String result1 = req1.toString();
            assertTrue(result1.contains("shopId='shop123'"));
            assertTrue(result1.contains("token=null"));
            
            TokenUnbindReq req2 = new TokenUnbindReq(null, "token456");
            String result2 = req2.toString();
            assertTrue(result2.contains("shopId=null"));
            assertTrue(result2.contains("token='token456'"));
        }

        @Test
        @DisplayName("toString应该是一致的")
        void testToStringConsistency() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            String result1 = req.toString();
            String result2 = req.toString();
            
            assertEquals(result1, result2);
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"token\":\"token456\""));
        }

        @Test
        @DisplayName("包含null值的对象应能正确序列化")
        void testSerializationWithNullValues() throws IOException {
            TokenUnbindReq req = new TokenUnbindReq("shop123", null);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"token\":null"));
        }

        @Test
        @DisplayName("全null对象应能正确序列化")
        void testSerializationWithAllNullValues() throws IOException {
            TokenUnbindReq req = new TokenUnbindReq(null, null);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":null"));
            assertTrue(json.contains("\"token\":null"));
        }

        @Test
        @DisplayName("空字符串对象应能正确序列化")
        void testSerializationWithEmptyValues() throws IOException {
            TokenUnbindReq req = new TokenUnbindReq("", "");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"\""));
            assertTrue(json.contains("\"token\":\"\""));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"token\":\"token456\"}";
            
            TokenUnbindReq req = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertEquals("shop123", req.getShopId());
            assertEquals("token456", req.getToken());
        }

        @Test
        @DisplayName("部分字段缺失的JSON应能正确反序列化")
        void testDeserializationWithMissingFields() throws IOException {
            String json1 = "{\"shop_id\":\"shop123\"}";
            TokenUnbindReq req1 = objectMapper.readValue(json1, TokenUnbindReq.class);
            assertEquals("shop123", req1.getShopId());
            assertNull(req1.getToken());
            
            String json2 = "{\"token\":\"token456\"}";
            TokenUnbindReq req2 = objectMapper.readValue(json2, TokenUnbindReq.class);
            assertNull(req2.getShopId());
            assertEquals("token456", req2.getToken());
        }

        @Test
        @DisplayName("包含null值的JSON应能正确反序列化")
        void testDeserializationWithNullValues() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"token\":null}";
            
            TokenUnbindReq req = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertEquals("shop123", req.getShopId());
            assertNull(req.getToken());
        }

        @Test
        @DisplayName("空JSON对象应能正确反序列化")
        void testDeserializationWithEmptyJson() throws IOException {
            String json = "{}";
            
            TokenUnbindReq req = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertNull(req.getShopId());
            assertNull(req.getToken());
        }

        @Test
        @DisplayName("序列化和反序列化应保持数据一致性")
        void testSerializationDeserializationConsistency() throws IOException {
            TokenUnbindReq original = new TokenUnbindReq("shop123", "token456");
            
            String json = objectMapper.writeValueAsString(original);
            TokenUnbindReq deserialized = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null值的对象序列化反序列化应保持一致性")
        void testSerializationDeserializationConsistencyWithNulls() throws IOException {
            TokenUnbindReq original = new TokenUnbindReq("shop123", null);
            
            String json = objectMapper.writeValueAsString(original);
            TokenUnbindReq deserialized = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含特殊字符的对象应能正确序列化和反序列化")
        void testSerializationDeserializationWithSpecialCharacters() throws IOException {
            String shopIdWithSpecialChars = "shop_123-test@example.com";
            String tokenWithSpecialChars = "token_abc-xyz!@#$%^&*()";
            
            TokenUnbindReq original = new TokenUnbindReq(shopIdWithSpecialChars, tokenWithSpecialChars);
            
            String json = objectMapper.writeValueAsString(original);
            TokenUnbindReq deserialized = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertEquals(original, deserialized);
            assertEquals(shopIdWithSpecialChars, deserialized.getShopId());
            assertEquals(tokenWithSpecialChars, deserialized.getToken());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的请求对象应通过验证")
        void testValidRequest() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("空的shopId应触发验证错误")
        void testEmptyShopId() {
            TokenUnbindReq req = new TokenUnbindReq("", "token456");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("shopId")));
        }

        @Test
        @DisplayName("null的shopId应触发验证错误")
        void testNullShopId() {
            TokenUnbindReq req = new TokenUnbindReq(null, "token456");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("shopId")));
        }

        @Test
        @DisplayName("空格字符串shopId应触发验证错误")
        void testWhitespaceOnlyShopId() {
            TokenUnbindReq req = new TokenUnbindReq("   ", "token456");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("shopId")));
        }

        @Test
        @DisplayName("token字段为null应通过验证（没有验证注解）")
        void testNullToken() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", null);
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            // 应该只有shopId相关的验证，token没有验证注解
            violations.stream()
                    .forEach(v -> assertEquals("shopId", v.getPropertyPath().toString()));
        }

        @Test
        @DisplayName("token字段为空字符串应通过验证（没有验证注解）")
        void testEmptyToken() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("验证错误消息应准确")
        void testValidationErrorMessage() {
            TokenUnbindReq req = new TokenUnbindReq("", "token456");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            ConstraintViolation<TokenUnbindReq> violation = violations.iterator().next();
            assertEquals("门店ID不能为空", violation.getMessage());
        }

        @Test
        @DisplayName("多个验证错误应同时报告")
        void testMultipleValidationErrors() {
            TokenUnbindReq req = new TokenUnbindReq(null, "token456");
            
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(req);
            
            assertEquals(1, violations.size()); // 只有shopId有验证注解
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("解绑请求应包含必要的业务信息")
        void testUnbindRequestBusinessData() {
            String shopId = "shop123";
            String token = "access_token_abc123";
            
            TokenUnbindReq req = new TokenUnbindReq(shopId, token);
            
            assertEquals(shopId, req.getShopId());
            assertEquals(token, req.getToken());
            
            // 验证对象包含解绑所需的完整信息
            assertNotNull(req.getShopId());
            assertNotNull(req.getToken());
        }

        @Test
        @DisplayName("解绑请求应支持只有shopId的场景")
        void testUnbindRequestWithOnlyShopId() {
            String shopId = "shop123";
            
            TokenUnbindReq req = new TokenUnbindReq(shopId, null);
            
            assertEquals(shopId, req.getShopId());
            assertNull(req.getToken());
            
            // 在某些解绑场景下，可能只需要shopId
            assertNotNull(req.getShopId());
        }

        @Test
        @DisplayName("解绑请求应能处理各种token格式")
        void testUnbindRequestWithDifferentTokenFormats() {
            String shopId = "shop123";
            String[] tokenFormats = {
                "abc123",
                "access_token_abc123",
                "Bearer abc123",
                "JWT.eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9",
                "refresh_token_xyz789",
                "session_token_12345"
            };
            
            for (String token : tokenFormats) {
                TokenUnbindReq req = new TokenUnbindReq(shopId, token);
                assertEquals(shopId, req.getShopId());
                assertEquals(token, req.getToken());
            }
        }

        @Test
        @DisplayName("解绑请求应能处理不同门店ID格式")
        void testUnbindRequestWithDifferentShopIdFormats() {
            String token = "token123";
            String[] shopIdFormats = {
                "shop123",
                "SHOP_123",
                "shop-123",
                "shop.123",
                "123",
                "shop_abc_123"
            };
            
            for (String shopId : shopIdFormats) {
                TokenUnbindReq req = new TokenUnbindReq(shopId, token);
                assertEquals(shopId, req.getShopId());
                assertEquals(token, req.getToken());
            }
        }

        @Test
        @DisplayName("解绑请求应能正确复制和克隆")
        void testUnbindRequestCopyAndClone() {
            TokenUnbindReq original = new TokenUnbindReq("shop123", "token456");
            
            // 通过构造函数复制
            TokenUnbindReq copy = new TokenUnbindReq(original.getShopId(), original.getToken());
            
            assertEquals(original, copy);
            assertNotSame(original, copy);
            
            // 修改副本不应影响原对象
            copy.setShopId("shop789");
            assertNotEquals(original.getShopId(), copy.getShopId());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("处理特殊字符的shopId")
        void testSpecialCharactersInShopId() {
            String specialShopId = "shop_123-test@example.com#$%^&*()";
            String token = "token123";
            
            TokenUnbindReq req = new TokenUnbindReq(specialShopId, token);
            
            assertEquals(specialShopId, req.getShopId());
            assertEquals(token, req.getToken());
        }

        @Test
        @DisplayName("处理特殊字符的token")
        void testSpecialCharactersInToken() {
            String shopId = "shop123";
            String specialToken = "token_123-abc@example.com#$%^&*()[]{}|\\:;\"'<>?,./";
            
            TokenUnbindReq req = new TokenUnbindReq(shopId, specialToken);
            
            assertEquals(shopId, req.getShopId());
            assertEquals(specialToken, req.getToken());
        }

        @Test
        @DisplayName("处理很长的字符串")
        void testVeryLongStrings() {
            StringBuilder longShopId = new StringBuilder();
            StringBuilder longToken = new StringBuilder();
            
            for (int i = 0; i < 1000; i++) {
                longShopId.append("s");
                longToken.append("t");
            }
            
            TokenUnbindReq req = new TokenUnbindReq(longShopId.toString(), longToken.toString());
            
            assertEquals(longShopId.toString(), req.getShopId());
            assertEquals(longToken.toString(), req.getToken());
            assertEquals(1000, req.getShopId().length());
            assertEquals(1000, req.getToken().length());
        }

        @Test
        @DisplayName("处理Unicode字符")
        void testUnicodeCharacters() {
            String unicodeShopId = "门店123";
            String unicodeToken = "令牌456";
            
            TokenUnbindReq req = new TokenUnbindReq(unicodeShopId, unicodeToken);
            
            assertEquals(unicodeShopId, req.getShopId());
            assertEquals(unicodeToken, req.getToken());
        }

        @Test
        @DisplayName("处理包含换行符和制表符的字符串")
        void testStringWithControlCharacters() {
            String shopIdWithControls = "shop\n123\t";
            String tokenWithControls = "token\r456\b";
            
            TokenUnbindReq req = new TokenUnbindReq(shopIdWithControls, tokenWithControls);
            
            assertEquals(shopIdWithControls, req.getShopId());
            assertEquals(tokenWithControls, req.getToken());
        }

        @Test
        @DisplayName("处理JSON特殊字符")
        void testJsonSpecialCharacters() throws IOException {
            String shopIdWithJsonChars = "shop\"123\\test";
            String tokenWithJsonChars = "token\"456\\test";
            
            TokenUnbindReq req = new TokenUnbindReq(shopIdWithJsonChars, tokenWithJsonChars);
            
            // 测试序列化和反序列化
            String json = objectMapper.writeValueAsString(req);
            TokenUnbindReq deserialized = objectMapper.readValue(json, TokenUnbindReq.class);
            
            assertEquals(req, deserialized);
            assertEquals(shopIdWithJsonChars, deserialized.getShopId());
            assertEquals(tokenWithJsonChars, deserialized.getToken());
        }

        @Test
        @DisplayName("处理极端边界情况组合")
        void testExtremeBoundaryCombinations() {
            // 测试各种null和非null的组合
            assertDoesNotThrow(() -> {
                new TokenUnbindReq(null, null);
                new TokenUnbindReq("", "");
                new TokenUnbindReq("shop", null);
                new TokenUnbindReq(null, "token");
                new TokenUnbindReq("", "token");
                new TokenUnbindReq("shop", "");
                new TokenUnbindReq("  ", "  ");
            });
        }
    }

    @Nested
    @DisplayName("Performance Tests")
    class PerformanceTests {

        @Test
        @DisplayName("大量对象创建不应导致性能问题")
        void testMassObjectCreation() {
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 10000; i++) {
                    TokenUnbindReq req = new TokenUnbindReq("shop" + i, "token" + i);
                    assertEquals("shop" + i, req.getShopId());
                    assertEquals("token" + i, req.getToken());
                }
            });
        }

        @Test
        @DisplayName("toString方法应高效执行")
        void testToStringPerformance() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 5000; i++) {
                    String result = req.toString();
                    assertNotNull(result);
                    assertTrue(result.length() > 0);
                }
            });
        }

        @Test
        @DisplayName("equals和hashCode方法应高效执行")
        void testEqualsHashCodePerformance() {
            TokenUnbindReq req1 = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq req2 = new TokenUnbindReq("shop123", "token456");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 5000; i++) {
                    boolean result = req1.equals(req2);
                    assertTrue(result);
                    int hash1 = req1.hashCode();
                    int hash2 = req2.hashCode();
                    assertEquals(hash1, hash2);
                }
            });
        }

        @Test
        @DisplayName("JSON序列化和反序列化应高效执行")
        void testJsonPerformance() {
            TokenUnbindReq req = new TokenUnbindReq("shop123", "token456");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    String json = objectMapper.writeValueAsString(req);
                    TokenUnbindReq deserialized = objectMapper.readValue(json, TokenUnbindReq.class);
                    assertEquals(req, deserialized);
                }
            });
        }

        @Test
        @DisplayName("验证应高效执行")
        void testValidationPerformance() {
            TokenUnbindReq validReq = new TokenUnbindReq("shop123", "token456");
            TokenUnbindReq invalidReq = new TokenUnbindReq("", "token456");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    Set<ConstraintViolation<TokenUnbindReq>> validViolations = validator.validate(validReq);
                    assertTrue(validViolations.isEmpty());
                    
                    Set<ConstraintViolation<TokenUnbindReq>> invalidViolations = validator.validate(invalidReq);
                    assertFalse(invalidViolations.isEmpty());
                }
            });
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("完整的解绑流程模拟")
        void testCompleteUnbindFlow() {
            // 模拟一个完整的解绑流程
            String shopId = "shop123";
            String token = "access_token_abc123";
            
            // 1. 创建解绑请求
            TokenUnbindReq unbindReq = new TokenUnbindReq(shopId, token);
            assertNotNull(unbindReq);
            
            // 2. 验证请求参数
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(unbindReq);
            assertTrue(violations.isEmpty(), "解绑请求应通过验证");
            
            // 3. 序列化为JSON（模拟网络传输）
            assertDoesNotThrow(() -> {
                String json = objectMapper.writeValueAsString(unbindReq);
                assertNotNull(json);
                assertTrue(json.length() > 0);
                
                // 4. 反序列化（模拟接收方处理）
                TokenUnbindReq receivedReq = objectMapper.readValue(json, TokenUnbindReq.class);
                assertEquals(unbindReq, receivedReq);
                
                // 5. 验证接收到的请求
                assertEquals(shopId, receivedReq.getShopId());
                assertEquals(token, receivedReq.getToken());
            });
        }

        @Test
        @DisplayName("异常场景的解绑流程")
        void testErrorCaseUnbindFlow() {
            // 测试异常场景下的处理
            TokenUnbindReq invalidReq = new TokenUnbindReq("", "token123");
            
            // 1. 验证应该失败
            Set<ConstraintViolation<TokenUnbindReq>> violations = validator.validate(invalidReq);
            assertFalse(violations.isEmpty());
            
            // 2. 但序列化应该成功
            assertDoesNotThrow(() -> {
                String json = objectMapper.writeValueAsString(invalidReq);
                TokenUnbindReq deserialized = objectMapper.readValue(json, TokenUnbindReq.class);
                assertEquals(invalidReq, deserialized);
            });
        }

        @Test
        @DisplayName("跨系统兼容性测试")
        void testCrossSystemCompatibility() throws IOException {
            // 测试不同系统间的数据交换兼容性
            TokenUnbindReq originalReq = new TokenUnbindReq("shop123", "token456");
            
            // 序列化为标准JSON格式
            String json = objectMapper.writeValueAsString(originalReq);
            
            // 模拟不同系统的JSON格式
            String[] compatibleJsonFormats = {
                json, // 标准格式
                "{\"shop_id\":\"shop123\",\"token\":\"token456\"}", // 手动构造
                "{\"token\":\"token456\",\"shop_id\":\"shop123\"}", // 字段顺序不同
                "{\n  \"shop_id\" : \"shop123\",\n  \"token\" : \"token456\"\n}", // 带格式化
            };
            
            for (String compatibleJson : compatibleJsonFormats) {
                TokenUnbindReq parsed = objectMapper.readValue(compatibleJson, TokenUnbindReq.class);
                assertEquals(originalReq, parsed);
            }
        }
    }
}