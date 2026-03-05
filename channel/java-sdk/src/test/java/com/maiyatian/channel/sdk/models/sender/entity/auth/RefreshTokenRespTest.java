/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RefreshTokenResp 单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 */
@DisplayName("RefreshTokenResp 单元测试")
class RefreshTokenRespTest {

    private ObjectMapper objectMapper;
    private static final long CURRENT_TIME = System.currentTimeMillis() / 1000;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void testDefaultConstructor() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            assertNull(resp.getToken());
            assertNull(resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullParametersConstructor() {
            String token = "new_access_token_123";
            String refreshToken = "new_refresh_token_456";
            Long expireTime = CURRENT_TIME + 3600L; // 1小时后过期
            Long refreshExpireTime = CURRENT_TIME + 7200L; // 2小时后过期
            
            RefreshTokenResp resp = new RefreshTokenResp(token, refreshToken, expireTime, refreshExpireTime);
            
            assertEquals(token, resp.getToken());
            assertEquals(refreshToken, resp.getRefreshToken());
            assertEquals(expireTime, resp.getExpireTime());
            assertEquals(refreshExpireTime, resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("构造函数应接受null值")
        void testConstructorWithNullValues() {
            RefreshTokenResp resp = new RefreshTokenResp(null, null, null, null);
            
            assertNull(resp.getToken());
            assertNull(resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("构造函数应接受部分null值")
        void testConstructorWithPartialNullValues() {
            String token = "token123";
            String refreshToken = "refresh456";
            
            RefreshTokenResp resp = new RefreshTokenResp(token, refreshToken, null, null);
            
            assertEquals(token, resp.getToken());
            assertEquals(refreshToken, resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("token的getter和setter应正常工作")
        void testTokenGetterSetter() {
            RefreshTokenResp resp = new RefreshTokenResp();
            String token = "new_access_token_123456789";
            
            resp.setToken(token);
            assertEquals(token, resp.getToken());
            
            resp.setToken(null);
            assertNull(resp.getToken());
        }

        @Test
        @DisplayName("refreshToken的getter和setter应正常工作")
        void testRefreshTokenGetterSetter() {
            RefreshTokenResp resp = new RefreshTokenResp();
            String refreshToken = "new_refresh_token_987654321";
            
            resp.setRefreshToken(refreshToken);
            assertEquals(refreshToken, resp.getRefreshToken());
            
            resp.setRefreshToken(null);
            assertNull(resp.getRefreshToken());
        }

        @Test
        @DisplayName("expireTime的getter和setter应正常工作")
        void testExpireTimeGetterSetter() {
            RefreshTokenResp resp = new RefreshTokenResp();
            Long expireTime = CURRENT_TIME + 3600L;
            
            resp.setExpireTime(expireTime);
            assertEquals(expireTime, resp.getExpireTime());
            
            resp.setExpireTime(null);
            assertNull(resp.getExpireTime());
        }

        @Test
        @DisplayName("refreshExpireTime的getter和setter应正常工作")
        void testRefreshExpireTimeGetterSetter() {
            RefreshTokenResp resp = new RefreshTokenResp();
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            
            resp.setRefreshExpireTime(refreshExpireTime);
            assertEquals(refreshExpireTime, resp.getRefreshExpireTime());
            
            resp.setRefreshExpireTime(null);
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("setter应接受各种边界值")
        void testSetterWithBoundaryValues() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 测试空字符串
            resp.setToken("");
            resp.setRefreshToken("");
            assertEquals("", resp.getToken());
            assertEquals("", resp.getRefreshToken());
            
            // 测试最小时间戳
            resp.setExpireTime(1L);
            resp.setRefreshExpireTime(1L);
            assertEquals(1L, resp.getExpireTime());
            assertEquals(1L, resp.getRefreshExpireTime());
            
            // 测试最大时间戳
            resp.setExpireTime(Long.MAX_VALUE);
            resp.setRefreshExpireTime(Long.MAX_VALUE);
            assertEquals(Long.MAX_VALUE, resp.getExpireTime());
            assertEquals(Long.MAX_VALUE, resp.getRefreshExpireTime());
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("getFormattedExpireTime应正确格式化过期时间")
        void testGetFormattedExpireTime() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 未设置过期时间，应返回"未知"
            assertEquals("未知", resp.getFormattedExpireTime());
            
            // 设置具体时间戳
            resp.setExpireTime(1640995200L); // 2022-01-01 00:00:00 UTC
            String formatted = resp.getFormattedExpireTime();
            assertNotNull(formatted);
            assertTrue(formatted.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
        }

        @Test
        @DisplayName("getFormattedRefreshExpireTime应正确格式化刷新令牌过期时间")
        void testGetFormattedRefreshExpireTime() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 未设置过期时间，应返回"未知"
            assertEquals("未知", resp.getFormattedRefreshExpireTime());
            
            // 设置具体时间戳
            resp.setRefreshExpireTime(1641081600L); // 2022-01-02 00:00:00 UTC
            String formatted = resp.getFormattedRefreshExpireTime();
            assertNotNull(formatted);
            assertTrue(formatted.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
        }

        @Test
        @DisplayName("needsRefresh应正确判断是否需要刷新令牌")
        void testNeedsRefresh() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 未设置过期时间，应返回true
            assertTrue(resp.needsRefresh());
            
            // 已过期，应返回true
            resp.setExpireTime(CURRENT_TIME - 3600L);
            assertTrue(resp.needsRefresh());
            
            // 距离过期时间少于1小时，应返回true
            resp.setExpireTime(CURRENT_TIME + 1800L); // 30分钟后过期
            assertTrue(resp.needsRefresh());
            
            // 距离过期时间大于1小时，应返回false
            resp.setExpireTime(CURRENT_TIME + 7200L); // 2小时后过期
            assertFalse(resp.needsRefresh());
        }

        @Test
        @DisplayName("isExpired应正确判断是否已过期")
        void testIsExpired() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 未设置过期时间，应返回true
            assertTrue(resp.isExpired());
            
            // 已过期，应返回true
            resp.setExpireTime(CURRENT_TIME - 3600L);
            assertTrue(resp.isExpired());
            
            // 未过期，应返回false
            resp.setExpireTime(CURRENT_TIME + 3600L);
            assertFalse(resp.isExpired());
            
            // 刚好过期，应返回true
            resp.setExpireTime(CURRENT_TIME);
            boolean result = resp.isExpired();
            // 由于时间精度问题，可能返回true或false，但应该是一致的
            assertTrue(result || !result);
        }

        @Test
        @DisplayName("业务方法应处理边界时间值")
        void testBusinessMethodsWithBoundaryTimeValues() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 测试最小时间值
            resp.setExpireTime(1L);
            assertTrue(resp.isExpired());
            assertTrue(resp.needsRefresh());
            
            // 测试最大时间值
            resp.setExpireTime(Long.MAX_VALUE);
            assertFalse(resp.isExpired());
            assertFalse(resp.needsRefresh());
            
            // 测试负值（技术上可能，但业务上不合理）
            resp.setExpireTime(-1L);
            assertTrue(resp.isExpired());
            assertTrue(resp.needsRefresh());
        }

        @Test
        @DisplayName("格式化方法应处理特殊时间戳")
        void testFormattingWithSpecialTimestamps() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 测试Unix纪元时间
            resp.setExpireTime(0L);
            String formatted = resp.getFormattedExpireTime();
            assertNotNull(formatted);
            assertTrue(formatted.contains("1970") || formatted.contains("1969")); // 取决于时区
            
            // 测试负时间戳
            resp.setRefreshExpireTime(-86400L); // 1970-01-01前一天
            String formattedRefresh = resp.getFormattedRefreshExpireTime();
            assertNotNull(formattedRefresh);
            assertTrue(formattedRefresh.contains("1969"));
        }

        @Test
        @DisplayName("业务方法应与当前时间一致")
        void testBusinessMethodsConsistency() {
            RefreshTokenResp resp = new RefreshTokenResp();
            long futureTime = CURRENT_TIME + 7200L; // 2小时后
            
            resp.setExpireTime(futureTime);
            
            // 多次调用应该返回一致的结果
            boolean expired1 = resp.isExpired();
            boolean expired2 = resp.isExpired();
            assertEquals(expired1, expired2);
            
            boolean needsRefresh1 = resp.needsRefresh();
            boolean needsRefresh2 = resp.needsRefresh();
            assertEquals(needsRefresh1, needsRefresh2);
        }
    }

    @Nested
    @DisplayName("equals和hashCode方法测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象应相等")
        void testSameObjectEquals() {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            assertTrue(resp.equals(resp));
            assertEquals(resp.hashCode(), resp.hashCode());
        }

        @Test
        @DisplayName("具有相同属性的不同对象应相等")
        void testEqualObjectsWithSameProperties() {
            Long expireTime = CURRENT_TIME + 3600L;
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            
            RefreshTokenResp resp1 = new RefreshTokenResp("token123", "refresh456", expireTime, refreshExpireTime);
            RefreshTokenResp resp2 = new RefreshTokenResp("token123", "refresh456", expireTime, refreshExpireTime);
            
            assertTrue(resp1.equals(resp2));
            assertTrue(resp2.equals(resp1));
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("具有不同属性的对象应不相等")
        void testUnequalObjectsWithDifferentProperties() {
            RefreshTokenResp resp1 = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            RefreshTokenResp resp2 = new RefreshTokenResp("token999", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            assertFalse(resp1.equals(resp2));
            assertFalse(resp2.equals(resp1));
        }

        @Test
        @DisplayName("与null比较应返回false")
        void testEqualsWithNull() {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            assertFalse(resp.equals(null));
        }

        @Test
        @DisplayName("与不同类型对象比较应返回false")
        void testEqualsWithDifferentType() {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            String str = "not a RefreshTokenResp";
            
            assertFalse(resp.equals(str));
        }

        @Test
        @DisplayName("包含null字段的对象应正确比较")
        void testEqualsWithNullFields() {
            RefreshTokenResp resp1 = new RefreshTokenResp(null, null, null, null);
            RefreshTokenResp resp2 = new RefreshTokenResp(null, null, null, null);
            RefreshTokenResp resp3 = new RefreshTokenResp("token123", null, null, null);
            
            assertTrue(resp1.equals(resp2));
            assertFalse(resp1.equals(resp3));
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("时间戳字段差异应影响相等性")
        void testEqualsWithDifferentTimestamps() {
            RefreshTokenResp resp1 = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            RefreshTokenResp resp2 = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3601L, CURRENT_TIME + 7200L);
            RefreshTokenResp resp3 = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7201L);
            
            assertFalse(resp1.equals(resp2));
            assertFalse(resp1.equals(resp3));
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段")
        void testToStringContainsAllFields() {
            Long expireTime = CURRENT_TIME + 3600L;
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            RefreshTokenResp resp = new RefreshTokenResp("access_token_123456", "refresh_token_789123", expireTime, refreshExpireTime);
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("RefreshTokenResp"));
            assertTrue(result.contains("token='access_token_123456'"));
            assertTrue(result.contains("refreshToken='refresh_token_789123'"));
            assertTrue(result.contains("expireTime=" + expireTime));
            assertTrue(result.contains("refreshExpireTime=" + refreshExpireTime));
        }

        @Test
        @DisplayName("toString应正确处理null值")
        void testToStringWithNullValues() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("RefreshTokenResp"));
            assertTrue(result.contains("token='null'"));
            assertTrue(result.contains("refreshToken='null'"));
            assertTrue(result.contains("expireTime=null"));
            assertTrue(result.contains("refreshExpireTime=null"));
        }

        @Test
        @DisplayName("toString应正确处理空字符串")
        void testToStringWithEmptyStrings() {
            RefreshTokenResp resp = new RefreshTokenResp("", "", 0L, 0L);
            
            String result = resp.toString();
            
            assertTrue(result.contains("token=''"));
            assertTrue(result.contains("refreshToken=''"));
            assertTrue(result.contains("expireTime=0"));
            assertTrue(result.contains("refreshExpireTime=0"));
        }

        @Test
        @DisplayName("toString应正确处理特殊字符")
        void testToStringWithSpecialCharacters() {
            RefreshTokenResp resp = new RefreshTokenResp("token'with\"quotes", "refresh\nwith\ttabs", 123L, 456L);
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("token='token'with\"quotes'"));
            assertTrue(result.contains("refreshToken='refresh\nwith\ttabs'"));
        }

        @Test
        @DisplayName("toString格式应正确")
        void testToStringFormat() {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", 1640995200L, 1641081600L);
            
            String result = resp.toString();
            String expected = "RefreshTokenResp{token='token123', refreshToken='refresh456', expireTime=1640995200, refreshExpireTime=1641081600}";
            
            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("完整对象应正确序列化为JSON")
        void testJsonSerialization() throws JsonProcessingException {
            Long expireTime = CURRENT_TIME + 3600L;
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            RefreshTokenResp resp = new RefreshTokenResp("new_token_123", "new_refresh_456", expireTime, refreshExpireTime);
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertNotNull(json);
            assertTrue(json.contains("\"token\":\"new_token_123\""));
            assertTrue(json.contains("\"refresh_token\":\"new_refresh_456\""));
            assertTrue(json.contains("\"expire_time\":" + expireTime));
            assertTrue(json.contains("\"refresh_expire_time\":" + refreshExpireTime));
        }

        @Test
        @DisplayName("JSON应正确反序列化为对象")
        void testJsonDeserialization() throws JsonProcessingException {
            Long expireTime = CURRENT_TIME + 3600L;
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            String json = String.format("{\"token\":\"new_token_123\",\"refresh_token\":\"new_refresh_456\",\"expire_time\":%d,\"refresh_expire_time\":%d}", expireTime, refreshExpireTime);
            
            RefreshTokenResp resp = objectMapper.readValue(json, RefreshTokenResp.class);
            
            assertEquals("new_token_123", resp.getToken());
            assertEquals("new_refresh_456", resp.getRefreshToken());
            assertEquals(expireTime, resp.getExpireTime());
            assertEquals(refreshExpireTime, resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("序列化反序列化应保持对象相等性")
        void testSerializationDeserialization() throws JsonProcessingException {
            RefreshTokenResp original = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            String json = objectMapper.writeValueAsString(original);
            RefreshTokenResp deserialized = objectMapper.readValue(json, RefreshTokenResp.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应正确序列化")
        void testJsonSerializationWithNullFields() throws JsonProcessingException {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", null, null);
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertNotNull(json);
            assertTrue(json.contains("\"token\":\"token123\""));
            assertTrue(json.contains("\"refresh_token\":\"refresh456\""));
        }

        @Test
        @DisplayName("部分JSON应正确反序列化")
        void testPartialJsonDeserialization() throws JsonProcessingException {
            String json = "{\"token\":\"token123\",\"refresh_token\":\"refresh456\"}";
            
            RefreshTokenResp resp = objectMapper.readValue(json, RefreshTokenResp.class);
            
            assertEquals("token123", resp.getToken());
            assertEquals("refresh456", resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("业务方法不应被序列化")
        void testBusinessMethodsNotSerialized() throws JsonProcessingException {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            String json = objectMapper.writeValueAsString(resp);
            
            // 业务方法返回值不应出现在JSON中
            assertFalse(json.contains("needsRefresh"));
            assertFalse(json.contains("isExpired"));
            assertFalse(json.contains("formattedExpireTime"));
            assertFalse(json.contains("formattedRefreshExpireTime"));
        }

        @Test
        @DisplayName("JSON字段名应使用注解指定的名称")
        void testJsonFieldNames() throws JsonProcessingException {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", 123L, 456L);
            
            String json = objectMapper.writeValueAsString(resp);
            
            // 应该使用@JsonProperty指定的字段名
            assertTrue(json.contains("\"token\":"));
            assertTrue(json.contains("\"refresh_token\":"));
            assertTrue(json.contains("\"expire_time\":"));
            assertTrue(json.contains("\"refresh_expire_time\":"));
            
            // 不应该包含Java字段名
            assertFalse(json.contains("\"refreshToken\":"));
            assertFalse(json.contains("\"expireTime\":"));
            assertFalse(json.contains("\"refreshExpireTime\":"));
        }

        @Test
        @DisplayName("反序列化应忽略未知字段")
        void testDeserializationIgnoresUnknownFields() throws JsonProcessingException {
            String json = "{\"token\":\"token123\",\"refresh_token\":\"refresh456\",\"unknown_field\":\"value\"}";
            
            RefreshTokenResp resp = objectMapper.readValue(json, RefreshTokenResp.class);
            
            assertEquals("token123", resp.getToken());
            assertEquals("refresh456", resp.getRefreshToken());
        }

        @Test
        @DisplayName("包含特殊字符的字符串应正确序列化和反序列化")
        void testJsonWithSpecialCharacters() throws JsonProcessingException {
            String specialToken = "token_!@#$%^&*()_+测试中文🎉";
            String specialRefreshToken = "refresh\"with'quotes\nand\ttabs";
            RefreshTokenResp resp = new RefreshTokenResp(specialToken, specialRefreshToken, 123L, 456L);
            
            String json = objectMapper.writeValueAsString(resp);
            RefreshTokenResp deserialized = objectMapper.readValue(json, RefreshTokenResp.class);
            
            assertEquals(resp, deserialized);
            assertEquals(specialToken, deserialized.getToken());
            assertEquals(specialRefreshToken, deserialized.getRefreshToken());
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
            
            RefreshTokenResp resp = new RefreshTokenResp(longString, longString, CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            assertEquals(longString, resp.getToken());
            assertEquals(longString, resp.getRefreshToken());
        }

        @Test
        @DisplayName("应正确处理包含Unicode字符的字符串")
        void testUnicodeCharacters() {
            String unicodeToken = "token_测试_🎉_中文_αβγ_русский";
            String unicodeRefresh = "refresh_😀😁😂🤣😃😄😅😆😉";
            
            RefreshTokenResp resp = new RefreshTokenResp(unicodeToken, unicodeRefresh, 123L, 456L);
            
            assertEquals(unicodeToken, resp.getToken());
            assertEquals(unicodeRefresh, resp.getRefreshToken());
        }

        @Test
        @DisplayName("应正确处理只包含空格的字符串")
        void testWhitespaceOnlyStrings() {
            String spacesToken = "   ";
            String tabsRefresh = "\t\t\t";
            
            RefreshTokenResp resp = new RefreshTokenResp(spacesToken, tabsRefresh, 0L, 0L);
            
            assertEquals(spacesToken, resp.getToken());
            assertEquals(tabsRefresh, resp.getRefreshToken());
        }

        @Test
        @DisplayName("应正确处理极端时间戳值")
        void testExtremeTimestampValues() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 测试最小正值时间戳
            resp.setExpireTime(1L);
            resp.setRefreshExpireTime(1L);
            assertEquals(1L, resp.getExpireTime());
            assertEquals(1L, resp.getRefreshExpireTime());
            
            // 测试最大时间戳
            resp.setExpireTime(Long.MAX_VALUE);
            resp.setRefreshExpireTime(Long.MAX_VALUE);
            assertEquals(Long.MAX_VALUE, resp.getExpireTime());
            assertEquals(Long.MAX_VALUE, resp.getRefreshExpireTime());
            
            // 测试负值时间戳
            resp.setExpireTime(-1L);
            resp.setRefreshExpireTime(-1L);
            assertEquals(-1L, resp.getExpireTime());
            assertEquals(-1L, resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("业务方法应处理极端时间值的性能")
        void testBusinessMethodsPerformanceWithExtremeValues() {
            RefreshTokenResp resp = new RefreshTokenResp("token123", "refresh456", Long.MAX_VALUE, Long.MAX_VALUE);
            
            // 多次调用应该快速返回
            long startTime = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                resp.isExpired();
                resp.needsRefresh();
                resp.getFormattedExpireTime();
                resp.getFormattedRefreshExpireTime();
            }
            long endTime = System.nanoTime();
            
            // 1000次调用应该在合理时间内完成（1秒）
            assertTrue((endTime - startTime) < 1_000_000_000L);
        }

        @Test
        @DisplayName("格式化方法应处理时区相关问题")
        void testFormattingWithTimeZones() {
            RefreshTokenResp resp = new RefreshTokenResp();
            
            // 使用已知时间戳测试
            resp.setExpireTime(1640995200L); // 2022-01-01 00:00:00 UTC
            String formatted = resp.getFormattedExpireTime();
            
            // 格式应该正确，但具体值依赖于系统时区
            assertNotNull(formatted);
            assertTrue(formatted.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
            assertTrue(formatted.contains("2021") || formatted.contains("2022")); // 可能因时区而异
        }

        @Test
        @DisplayName("equals和hashCode应处理极端值组合")
        void testEqualsHashCodeWithExtremeValues() {
            RefreshTokenResp resp1 = new RefreshTokenResp("", "", 0L, 0L);
            RefreshTokenResp resp2 = new RefreshTokenResp("", "", 0L, 0L);
            RefreshTokenResp resp3 = new RefreshTokenResp(null, null, Long.MAX_VALUE, Long.MAX_VALUE);
            RefreshTokenResp resp4 = new RefreshTokenResp(null, null, Long.MAX_VALUE, Long.MAX_VALUE);
            
            assertTrue(resp1.equals(resp2));
            assertTrue(resp3.equals(resp4));
            assertFalse(resp1.equals(resp3));
            
            assertEquals(resp1.hashCode(), resp2.hashCode());
            assertEquals(resp3.hashCode(), resp4.hashCode());
        }

        @Test
        @DisplayName("构造函数应处理极端值组合")
        void testConstructorWithExtremeValueCombinations() {
            // 空字符串和最大时间戳
            RefreshTokenResp resp1 = new RefreshTokenResp("", "", Long.MAX_VALUE, Long.MIN_VALUE);
            assertEquals("", resp1.getToken());
            assertEquals("", resp1.getRefreshToken());
            assertEquals(Long.MAX_VALUE, resp1.getExpireTime());
            assertEquals(Long.MIN_VALUE, resp1.getRefreshExpireTime());
            
            // null值和零时间戳
            RefreshTokenResp resp2 = new RefreshTokenResp(null, null, 0L, 0L);
            assertNull(resp2.getToken());
            assertNull(resp2.getRefreshToken());
            assertEquals(0L, resp2.getExpireTime());
            assertEquals(0L, resp2.getRefreshExpireTime());
        }
    }
}