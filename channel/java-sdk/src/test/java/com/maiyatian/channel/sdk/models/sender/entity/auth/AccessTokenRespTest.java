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
 * AccessTokenResp 单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 */
@DisplayName("AccessTokenResp 单元测试")
class AccessTokenRespTest {

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
            AccessTokenResp resp = new AccessTokenResp();
            
            assertNull(resp.getShopId());
            assertNull(resp.getToken());
            assertNull(resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("核心参数构造函数应正确设置必需参数")
        void testCoreParametersConstructor() {
            String shopId = "shop_123";
            String token = "access_token_456";
            String refreshToken = "refresh_token_789";
            
            AccessTokenResp resp = new AccessTokenResp(shopId, token, refreshToken);
            
            assertEquals(shopId, resp.getShopId());
            assertEquals(token, resp.getToken());
            assertEquals(refreshToken, resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullParametersConstructor() {
            String shopId = "shop_123";
            String token = "access_token_456";
            String refreshToken = "refresh_token_789";
            Long expireTime = CURRENT_TIME + 3600L; // 1小时后过期
            Long refreshExpireTime = CURRENT_TIME + 7200L; // 2小时后过期
            
            AccessTokenResp resp = new AccessTokenResp(shopId, token, refreshToken, expireTime, refreshExpireTime);
            
            assertEquals(shopId, resp.getShopId());
            assertEquals(token, resp.getToken());
            assertEquals(refreshToken, resp.getRefreshToken());
            assertEquals(expireTime, resp.getExpireTime());
            assertEquals(refreshExpireTime, resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("构造函数应接受null值")
        void testConstructorWithNullValues() {
            AccessTokenResp resp = new AccessTokenResp(null, null, null);
            
            assertNull(resp.getShopId());
            assertNull(resp.getToken());
            assertNull(resp.getRefreshToken());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId的getter和setter应正常工作")
        void testShopIdGetterSetter() {
            AccessTokenResp resp = new AccessTokenResp();
            String shopId = "shop_test_456";
            
            resp.setShopId(shopId);
            assertEquals(shopId, resp.getShopId());
            
            resp.setShopId(null);
            assertNull(resp.getShopId());
        }

        @Test
        @DisplayName("token的getter和setter应正常工作")
        void testTokenGetterSetter() {
            AccessTokenResp resp = new AccessTokenResp();
            String token = "access_token_123456789";
            
            resp.setToken(token);
            assertEquals(token, resp.getToken());
            
            resp.setToken(null);
            assertNull(resp.getToken());
        }

        @Test
        @DisplayName("refreshToken的getter和setter应正常工作")
        void testRefreshTokenGetterSetter() {
            AccessTokenResp resp = new AccessTokenResp();
            String refreshToken = "refresh_token_987654321";
            
            resp.setRefreshToken(refreshToken);
            assertEquals(refreshToken, resp.getRefreshToken());
            
            resp.setRefreshToken(null);
            assertNull(resp.getRefreshToken());
        }

        @Test
        @DisplayName("expireTime的getter和setter应正常工作")
        void testExpireTimeGetterSetter() {
            AccessTokenResp resp = new AccessTokenResp();
            Long expireTime = CURRENT_TIME + 3600L;
            
            resp.setExpireTime(expireTime);
            assertEquals(expireTime, resp.getExpireTime());
            
            resp.setExpireTime(null);
            assertNull(resp.getExpireTime());
        }

        @Test
        @DisplayName("refreshExpireTime的getter和setter应正常工作")
        void testRefreshExpireTimeGetterSetter() {
            AccessTokenResp resp = new AccessTokenResp();
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            
            resp.setRefreshExpireTime(refreshExpireTime);
            assertEquals(refreshExpireTime, resp.getRefreshExpireTime());
            
            resp.setRefreshExpireTime(null);
            assertNull(resp.getRefreshExpireTime());
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("isTokenExpired应正确判断令牌是否过期")
        void testIsTokenExpired() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回false
            assertFalse(resp.isTokenExpired());
            
            // 过期时间为0，应返回false
            resp.setExpireTime(0L);
            assertFalse(resp.isTokenExpired());
            
            // 过期时间为null，应返回false
            resp.setExpireTime(null);
            assertFalse(resp.isTokenExpired());
            
            // 已过期，应返回true
            resp.setExpireTime(CURRENT_TIME - 3600L);
            assertTrue(resp.isTokenExpired());
            
            // 未过期，应返回false
            resp.setExpireTime(CURRENT_TIME + 3600L);
            assertFalse(resp.isTokenExpired());
        }

        @Test
        @DisplayName("isRefreshTokenExpired应正确判断刷新令牌是否过期")
        void testIsRefreshTokenExpired() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回false
            assertFalse(resp.isRefreshTokenExpired());
            
            // 过期时间为0，应返回false
            resp.setRefreshExpireTime(0L);
            assertFalse(resp.isRefreshTokenExpired());
            
            // 过期时间为null，应返回false
            resp.setRefreshExpireTime(null);
            assertFalse(resp.isRefreshTokenExpired());
            
            // 已过期，应返回true
            resp.setRefreshExpireTime(CURRENT_TIME - 3600L);
            assertTrue(resp.isRefreshTokenExpired());
            
            // 未过期，应返回false
            resp.setRefreshExpireTime(CURRENT_TIME + 3600L);
            assertFalse(resp.isRefreshTokenExpired());
        }

        @Test
        @DisplayName("needsRefresh带参数应正确判断是否需要刷新")
        void testNeedsRefreshWithParameter() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回false
            assertFalse(resp.needsRefresh(3600L));
            
            // 过期时间为0，应返回false
            resp.setExpireTime(0L);
            assertFalse(resp.needsRefresh(3600L));
            
            // 过期时间为null，应返回false
            resp.setExpireTime(null);
            assertFalse(resp.needsRefresh(3600L));
            
            // 距离过期时间小于指定秒数，应返回true
            resp.setExpireTime(CURRENT_TIME + 1800L); // 30分钟后过期
            assertTrue(resp.needsRefresh(3600L)); // 提前1小时刷新
            
            // 距离过期时间大于指定秒数，应返回false
            resp.setExpireTime(CURRENT_TIME + 7200L); // 2小时后过期
            assertFalse(resp.needsRefresh(3600L)); // 提前1小时刷新
        }

        @Test
        @DisplayName("needsRefresh无参应使用默认7天提前刷新")
        void testNeedsRefreshWithoutParameter() {
            AccessTokenResp resp = new AccessTokenResp();
            long sevenDays = 7 * 24 * 3600L;
            
            // 未设置过期时间，应返回false
            assertFalse(resp.needsRefresh());
            
            // 距离过期时间小于7天，应返回true
            resp.setExpireTime(CURRENT_TIME + sevenDays - 3600L); // 6天23小时后过期
            assertTrue(resp.needsRefresh());
            
            // 距离过期时间大于7天，应返回false
            resp.setExpireTime(CURRENT_TIME + sevenDays + 3600L); // 7天1小时后过期
            assertFalse(resp.needsRefresh());
        }

        @Test
        @DisplayName("getExpireTimeAsInstant应正确返回Instant对象")
        void testGetExpireTimeAsInstant() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回null
            assertNull(resp.getExpireTimeAsInstant());
            
            // 过期时间为0，应返回null
            resp.setExpireTime(0L);
            assertNull(resp.getExpireTimeAsInstant());
            
            // 正常过期时间，应返回对应的Instant
            Long expireTime = CURRENT_TIME + 3600L;
            resp.setExpireTime(expireTime);
            Instant instant = resp.getExpireTimeAsInstant();
            assertNotNull(instant);
            assertEquals(expireTime.longValue(), instant.getEpochSecond());
        }

        @Test
        @DisplayName("getRefreshExpireTimeAsInstant应正确返回Instant对象")
        void testGetRefreshExpireTimeAsInstant() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回null
            assertNull(resp.getRefreshExpireTimeAsInstant());
            
            // 过期时间为0，应返回null
            resp.setRefreshExpireTime(0L);
            assertNull(resp.getRefreshExpireTimeAsInstant());
            
            // 正常过期时间，应返回对应的Instant
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            resp.setRefreshExpireTime(refreshExpireTime);
            Instant instant = resp.getRefreshExpireTimeAsInstant();
            assertNotNull(instant);
            assertEquals(refreshExpireTime.longValue(), instant.getEpochSecond());
        }

        @Test
        @DisplayName("getFormattedExpireTime应正确格式化过期时间")
        void testGetFormattedExpireTime() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回"永久有效"
            assertEquals("永久有效", resp.getFormattedExpireTime());
            
            // 过期时间为0，应返回"永久有效"
            resp.setExpireTime(0L);
            assertEquals("永久有效", resp.getFormattedExpireTime());
            
            // 正常过期时间，应返回格式化字符串
            resp.setExpireTime(1640995200L); // 2022-01-01 00:00:00 UTC
            String formatted = resp.getFormattedExpireTime();
            assertNotNull(formatted);
            assertTrue(formatted.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
        }

        @Test
        @DisplayName("getFormattedRefreshExpireTime应正确格式化刷新令牌过期时间")
        void testGetFormattedRefreshExpireTime() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回"永久有效"
            assertEquals("永久有效", resp.getFormattedRefreshExpireTime());
            
            // 过期时间为0，应返回"永久有效"
            resp.setRefreshExpireTime(0L);
            assertEquals("永久有效", resp.getFormattedRefreshExpireTime());
            
            // 正常过期时间，应返回格式化字符串
            resp.setRefreshExpireTime(1640995200L); // 2022-01-01 00:00:00 UTC
            String formatted = resp.getFormattedRefreshExpireTime();
            assertNotNull(formatted);
            assertTrue(formatted.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
        }

        @Test
        @DisplayName("getRemainingSeconds应正确计算剩余时间")
        void testGetRemainingSeconds() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 未设置过期时间，应返回Long.MAX_VALUE
            assertEquals(Long.MAX_VALUE, resp.getRemainingSeconds());
            
            // 过期时间为0，应返回Long.MAX_VALUE
            resp.setExpireTime(0L);
            assertEquals(Long.MAX_VALUE, resp.getRemainingSeconds());
            
            // 已过期，应返回0
            resp.setExpireTime(CURRENT_TIME - 3600L);
            assertEquals(0L, resp.getRemainingSeconds());
            
            // 未过期，应返回正确的剩余秒数
            resp.setExpireTime(CURRENT_TIME + 3600L);
            long remaining = resp.getRemainingSeconds();
            assertTrue(remaining > 3500L && remaining <= 3600L); // 考虑测试执行时间
        }
    }

    @Nested
    @DisplayName("equals和hashCode方法测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象应相等")
        void testSameObjectEquals() {
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789");
            
            assertTrue(resp.equals(resp));
            assertEquals(resp.hashCode(), resp.hashCode());
        }

        @Test
        @DisplayName("具有相同属性的不同对象应相等")
        void testEqualObjectsWithSameProperties() {
            Long expireTime = CURRENT_TIME + 3600L;
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            
            AccessTokenResp resp1 = new AccessTokenResp("shop123", "token456", "refresh789", expireTime, refreshExpireTime);
            AccessTokenResp resp2 = new AccessTokenResp("shop123", "token456", "refresh789", expireTime, refreshExpireTime);
            
            assertTrue(resp1.equals(resp2));
            assertTrue(resp2.equals(resp1));
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("具有不同属性的对象应不相等")
        void testUnequalObjectsWithDifferentProperties() {
            AccessTokenResp resp1 = new AccessTokenResp("shop123", "token456", "refresh789");
            AccessTokenResp resp2 = new AccessTokenResp("shop123", "token999", "refresh789");
            
            assertFalse(resp1.equals(resp2));
            assertFalse(resp2.equals(resp1));
        }

        @Test
        @DisplayName("与null比较应返回false")
        void testEqualsWithNull() {
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789");
            
            assertFalse(resp.equals(null));
        }

        @Test
        @DisplayName("与不同类型对象比较应返回false")
        void testEqualsWithDifferentType() {
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789");
            String str = "not an AccessTokenResp";
            
            assertFalse(resp.equals(str));
        }

        @Test
        @DisplayName("包含null字段的对象应正确比较")
        void testEqualsWithNullFields() {
            AccessTokenResp resp1 = new AccessTokenResp(null, null, null);
            AccessTokenResp resp2 = new AccessTokenResp(null, null, null);
            AccessTokenResp resp3 = new AccessTokenResp("shop123", null, null);
            
            assertTrue(resp1.equals(resp2));
            assertFalse(resp1.equals(resp3));
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段并脱敏敏感信息")
        void testToStringContainsAllFields() {
            Long expireTime = 1640995200L;
            Long refreshExpireTime = 1641081600L;
            AccessTokenResp resp = new AccessTokenResp("shop123", "access_token_123456789", "refresh_token_987654321", expireTime, refreshExpireTime);
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("AccessTokenResp"));
            assertTrue(result.contains("shopId='shop123'"));
            assertTrue(result.contains("token='access_t***'")); // 脱敏
            assertTrue(result.contains("refreshToken='refresh_***'")); // 脱敏
            assertTrue(result.contains("expireTime=" + expireTime));
            assertTrue(result.contains("refreshExpireTime=" + refreshExpireTime));
            assertTrue(result.contains("isTokenExpired="));
            assertTrue(result.contains("needsRefresh="));
        }

        @Test
        @DisplayName("toString应正确处理null值")
        void testToStringWithNullValues() {
            AccessTokenResp resp = new AccessTokenResp();
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("AccessTokenResp"));
            assertTrue(result.contains("shopId='null'"));
            assertTrue(result.contains("token='null'"));
            assertTrue(result.contains("refreshToken='null'"));
        }

        @Test
        @DisplayName("toString应正确脱敏短token")
        void testToStringWithShortToken() {
            AccessTokenResp resp = new AccessTokenResp("shop123", "abc", "xyz");
            
            String result = resp.toString();
            
            assertTrue(result.contains("token='abc***'"));
            assertTrue(result.contains("refreshToken='xyz***'"));
        }

        @Test
        @DisplayName("toString应包含业务状态信息")
        void testToStringIncludesBusinessStatus() {
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            String result = resp.toString();
            
            assertTrue(result.contains("isTokenExpired=false"));
            assertTrue(result.contains("needsRefresh="));
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
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789", expireTime, refreshExpireTime);
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"token\":\"token456\""));
            assertTrue(json.contains("\"refresh_token\":\"refresh789\""));
            assertTrue(json.contains("\"expire_time\":" + expireTime));
            assertTrue(json.contains("\"refresh_expire_time\":" + refreshExpireTime));
        }

        @Test
        @DisplayName("JSON应正确反序列化为对象")
        void testJsonDeserialization() throws JsonProcessingException {
            Long expireTime = CURRENT_TIME + 3600L;
            Long refreshExpireTime = CURRENT_TIME + 7200L;
            String json = String.format("{\"shop_id\":\"shop123\",\"token\":\"token456\",\"refresh_token\":\"refresh789\",\"expire_time\":%d,\"refresh_expire_time\":%d}", expireTime, refreshExpireTime);
            
            AccessTokenResp resp = objectMapper.readValue(json, AccessTokenResp.class);
            
            assertEquals("shop123", resp.getShopId());
            assertEquals("token456", resp.getToken());
            assertEquals("refresh789", resp.getRefreshToken());
            assertEquals(expireTime, resp.getExpireTime());
            assertEquals(refreshExpireTime, resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("序列化反序列化应保持对象相等性")
        void testSerializationDeserialization() throws JsonProcessingException {
            AccessTokenResp original = new AccessTokenResp("shop123", "token456", "refresh789", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            String json = objectMapper.writeValueAsString(original);
            AccessTokenResp deserialized = objectMapper.readValue(json, AccessTokenResp.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应正确序列化")
        void testJsonSerializationWithNullFields() throws JsonProcessingException {
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789");
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"token\":\"token456\""));
            assertTrue(json.contains("\"refresh_token\":\"refresh789\""));
        }

        @Test
        @DisplayName("部分JSON应正确反序列化")
        void testPartialJsonDeserialization() throws JsonProcessingException {
            String json = "{\"shop_id\":\"shop123\",\"token\":\"token456\",\"refresh_token\":\"refresh789\"}";
            
            AccessTokenResp resp = objectMapper.readValue(json, AccessTokenResp.class);
            
            assertEquals("shop123", resp.getShopId());
            assertEquals("token456", resp.getToken());
            assertEquals("refresh789", resp.getRefreshToken());
            assertNull(resp.getExpireTime());
            assertNull(resp.getRefreshExpireTime());
        }

        @Test
        @DisplayName("业务方法不应被序列化")
        void testBusinessMethodsNotSerialized() throws JsonProcessingException {
            AccessTokenResp resp = new AccessTokenResp("shop123", "token456", "refresh789", CURRENT_TIME + 3600L, CURRENT_TIME + 7200L);
            
            String json = objectMapper.writeValueAsString(resp);
            
            // 业务方法返回值不应出现在JSON中
            assertFalse(json.contains("isTokenExpired"));
            assertFalse(json.contains("needsRefresh"));
            assertFalse(json.contains("formattedExpireTime"));
            assertFalse(json.contains("remainingSeconds"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应正确处理空字符串")
        void testEmptyStrings() {
            AccessTokenResp resp = new AccessTokenResp("", "", "");
            
            assertEquals("", resp.getShopId());
            assertEquals("", resp.getToken());
            assertEquals("", resp.getRefreshToken());
        }

        @Test
        @DisplayName("应正确处理极长字符串")
        void testVeryLongStrings() {
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            
            AccessTokenResp resp = new AccessTokenResp();
            resp.setToken(longString);
            
            assertEquals(longString, resp.getToken());
        }

        @Test
        @DisplayName("应正确处理边界时间戳值")
        void testBoundaryTimestampValues() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 测试最小时间戳
            resp.setExpireTime(1L);
            assertEquals(1L, resp.getExpireTime());
            
            // 测试最大时间戳
            resp.setExpireTime(Long.MAX_VALUE);
            assertEquals(Long.MAX_VALUE, resp.getExpireTime());
            
            // 测试负值时间戳（虽然业务上不合理，但技术上应该支持）
            resp.setExpireTime(-1L);
            assertEquals(-1L, resp.getExpireTime());
        }

        @Test
        @DisplayName("toString应处理极长token的脱敏")
        void testToStringWithVeryLongTokens() {
            StringBuilder longTokenBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                longTokenBuilder.append("a");
            }
            String longToken = longTokenBuilder.toString();
            
            AccessTokenResp resp = new AccessTokenResp("shop123", longToken, longToken);
            String result = resp.toString();
            
            // 应该只显示前8个字符加***
            String expected = longToken.substring(0, 8) + "***";
            assertTrue(result.contains("token='" + expected + "'"));
            assertTrue(result.contains("refreshToken='" + expected + "'"));
        }

        @Test
        @DisplayName("业务方法应处理极端时间值")
        void testBusinessMethodsWithExtremeTimeValues() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 测试极大时间值
            resp.setExpireTime(Long.MAX_VALUE);
            assertFalse(resp.isTokenExpired());
            assertFalse(resp.needsRefresh());
            assertEquals(Long.MAX_VALUE, resp.getRemainingSeconds());
            
            // 测试极小时间值
            resp.setExpireTime(1L);
            assertTrue(resp.isTokenExpired());
            assertTrue(resp.needsRefresh(3600L));
            assertEquals(0L, resp.getRemainingSeconds());
        }

        @Test
        @DisplayName("格式化方法应处理特殊时间戳")
        void testFormattingMethodsWithSpecialTimestamps() {
            AccessTokenResp resp = new AccessTokenResp();
            
            // 测试Unix纪元时间
            resp.setExpireTime(0L);
            assertEquals("永久有效", resp.getFormattedExpireTime());
            
            // 测试负时间戳
            resp.setExpireTime(-1L);
            String formatted = resp.getFormattedExpireTime();
            assertNotNull(formatted);
            assertTrue(formatted.contains("1969") || formatted.contains("1970")); // 取决于时区
        }
    }
}