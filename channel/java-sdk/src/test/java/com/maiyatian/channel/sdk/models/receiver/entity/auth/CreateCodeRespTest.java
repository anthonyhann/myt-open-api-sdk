/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CreateCodeResp 获取授权码响应数据单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. JSON 序列化/反序列化测试
 * 5. 边界条件测试
 * 6. 业务逻辑测试
 */
@DisplayName("CreateCodeResp 获取授权码响应数据测试")
public class CreateCodeRespTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            CreateCodeResp resp = new CreateCodeResp();
            
            assertNotNull(resp);
            assertNull(resp.getCode());
            assertNull(resp.getState());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            CreateCodeResp resp = new CreateCodeResp(
                "AUTH_CODE_123456", "csrf_token_state"
            );
            
            assertNotNull(resp);
            assertEquals("AUTH_CODE_123456", resp.getCode());
            assertEquals("csrf_token_state", resp.getState());
        }

        @Test
        @DisplayName("全参构造函数 - null值")
        void testFullConstructorWithNulls() {
            CreateCodeResp resp = new CreateCodeResp(null, null);
            
            assertNotNull(resp);
            assertNull(resp.getCode());
            assertNull(resp.getState());
        }

        @Test
        @DisplayName("全参构造函数 - 空字符串")
        void testFullConstructorWithEmptyStrings() {
            CreateCodeResp resp = new CreateCodeResp("", "");
            
            assertNotNull(resp);
            assertEquals("", resp.getCode());
            assertEquals("", resp.getState());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("Code 设置和获取")
        void testCodeGetterSetter() {
            CreateCodeResp resp = new CreateCodeResp();
            String code = "AUTH_CODE_ABCDEF123456";
            
            resp.setCode(code);
            
            assertEquals(code, resp.getCode());
        }

        @Test
        @DisplayName("State 设置和获取")
        void testStateGetterSetter() {
            CreateCodeResp resp = new CreateCodeResp();
            String state = "random_csrf_protection_token";
            
            resp.setState(state);
            
            assertEquals(state, resp.getState());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            CreateCodeResp resp = createValidResponse();
            
            resp.setCode(null);
            resp.setState(null);
            
            assertNull(resp.getCode());
            assertNull(resp.getState());
        }

        @Test
        @DisplayName("设置空字符串")
        void testSetEmptyValues() {
            CreateCodeResp resp = createValidResponse();
            
            resp.setCode("");
            resp.setState("");
            
            assertEquals("", resp.getCode());
            assertEquals("", resp.getState());
        }

        @Test
        @DisplayName("Code字段链式设置")
        void testCodeChaining() {
            CreateCodeResp resp = new CreateCodeResp();
            String originalCode = "ORIGINAL_CODE";
            String newCode = "NEW_CODE";
            
            resp.setCode(originalCode);
            assertEquals(originalCode, resp.getCode());
            
            resp.setCode(newCode);
            assertEquals(newCode, resp.getCode());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            CreateCodeResp resp1 = createValidResponse();
            CreateCodeResp resp2 = createValidResponse();
            
            assertEquals(resp1, resp2);
            assertEquals(resp1, resp1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            CreateCodeResp resp1 = createValidResponse();
            CreateCodeResp resp2 = createValidResponse();
            resp2.setCode("DIFFERENT_CODE");
            
            CreateCodeResp resp3 = createValidResponse();
            resp3.setState("DIFFERENT_STATE");
            
            CreateCodeResp resp4 = new CreateCodeResp(null, "test_state");
            CreateCodeResp resp5 = new CreateCodeResp("test_code", null);
            
            assertNotEquals(resp1, resp2);
            assertNotEquals(resp1, resp3);
            assertNotEquals(resp1, resp4);
            assertNotEquals(resp1, resp5);
            assertNotEquals(resp1, null);
            assertNotEquals(resp1, "not a CreateCodeResp");
        }

        @Test
        @DisplayName("equals方法测试 - null字段")
        void testEquals_WithNullFields() {
            CreateCodeResp resp1 = new CreateCodeResp(null, null);
            CreateCodeResp resp2 = new CreateCodeResp(null, null);
            CreateCodeResp resp3 = new CreateCodeResp("code", null);
            CreateCodeResp resp4 = new CreateCodeResp(null, "state");
            
            assertEquals(resp1, resp2);
            assertNotEquals(resp1, resp3);
            assertNotEquals(resp1, resp4);
            assertNotEquals(resp3, resp4);
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            CreateCodeResp resp1 = createValidResponse();
            CreateCodeResp resp2 = createValidResponse();
            CreateCodeResp resp3 = createValidResponse();
            resp3.setCode("DIFFERENT_CODE");
            
            assertEquals(resp1.hashCode(), resp2.hashCode());
            assertNotEquals(resp1.hashCode(), resp3.hashCode());
        }

        @Test
        @DisplayName("hashCode方法测试 - null字段")
        void testHashCode_WithNullFields() {
            CreateCodeResp resp1 = new CreateCodeResp(null, null);
            CreateCodeResp resp2 = new CreateCodeResp(null, null);
            CreateCodeResp resp3 = new CreateCodeResp("code", null);
            
            assertEquals(resp1.hashCode(), resp2.hashCode());
            assertNotEquals(resp1.hashCode(), resp3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            CreateCodeResp resp = createValidResponse();
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("AUTH_CODE_123456"));
            assertTrue(result.contains("test_state"));
            assertTrue(result.contains("CreateCodeResp"));
            assertTrue(result.contains("code="));
            assertTrue(result.contains("state="));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("CreateCodeResp"));
            assertTrue(result.contains("code='null'"));
            assertTrue(result.contains("state='null'"));
        }

        @Test
        @DisplayName("toString方法测试 - 空字符串")
        void testToString_WithEmptyValues() {
            CreateCodeResp resp = new CreateCodeResp("", "");
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("CreateCodeResp"));
            assertTrue(result.contains("code=''"));
            assertTrue(result.contains("state=''"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            CreateCodeResp resp = createValidResponse();
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertNotNull(json);
            assertTrue(json.contains("\"code\":\"AUTH_CODE_123456\""));
            assertTrue(json.contains("\"state\":\"test_state\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{" +
                "\"code\":\"AUTH_CODE_ABCDEF\"," +
                "\"state\":\"csrf_protection_token\"" +
                "}";
            
            CreateCodeResp resp = objectMapper.readValue(json, CreateCodeResp.class);
            
            assertNotNull(resp);
            assertEquals("AUTH_CODE_ABCDEF", resp.getCode());
            assertEquals("csrf_protection_token", resp.getState());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            CreateCodeResp original = createValidResponse();
            
            String json = objectMapper.writeValueAsString(original);
            CreateCodeResp deserialized = objectMapper.readValue(json, CreateCodeResp.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            CreateCodeResp resp = new CreateCodeResp();
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertNotNull(json);
            // Jackson默认会序列化null值为null
            assertTrue(json.contains("\"code\":null") || json.contains("\"code\": null"));
            assertTrue(json.contains("\"state\":null") || json.contains("\"state\": null"));
        }

        @Test
        @DisplayName("JSON反序列化测试 - 缺失字段")
        void testJsonDeserialization_MissingFields() throws JsonProcessingException {
            String json = "{\"code\":\"AUTH_CODE_ONLY\"}";
            
            CreateCodeResp resp = objectMapper.readValue(json, CreateCodeResp.class);
            
            assertNotNull(resp);
            assertEquals("AUTH_CODE_ONLY", resp.getCode());
            assertNull(resp.getState());
        }

        @Test
        @DisplayName("JSON反序列化测试 - 额外字段")
        void testJsonDeserialization_ExtraFields() throws JsonProcessingException {
            // 配置ObjectMapper忽略未知属性来测试
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            String json = "{" +
                "\"code\":\"AUTH_CODE_123\"," +
                "\"state\":\"test_state\"," +
                "\"extra_field\":\"should_be_ignored\"" +
                "}";
            
            CreateCodeResp resp = mapper.readValue(json, CreateCodeResp.class);
            
            assertNotNull(resp);
            assertEquals("AUTH_CODE_123", resp.getCode());
            assertEquals("test_state", resp.getState());
        }

        @Test
        @DisplayName("JSON反序列化测试 - 空对象")
        void testJsonDeserialization_EmptyObject() throws JsonProcessingException {
            String json = "{}";
            
            CreateCodeResp resp = objectMapper.readValue(json, CreateCodeResp.class);
            
            assertNotNull(resp);
            assertNull(resp.getCode());
            assertNull(resp.getState());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("授权码格式测试")
        void testAuthCodeFormats() {
            CreateCodeResp resp = new CreateCodeResp();
            
            // 测试典型的授权码格式
            String[] validCodes = {
                "AUTH_CODE_123456",
                "abc123def456",
                "AUTH-CODE-789",
                "auth_code_xyz_123",
                "1234567890abcdef",
                "CODE123456789012345678901234567890" // 32位
            };
            
            for (String code : validCodes) {
                resp.setCode(code);
                assertEquals(code, resp.getCode());
            }
        }

        @Test
        @DisplayName("State字段CSRF防护测试")
        void testStateCSRFProtection() {
            CreateCodeResp resp = new CreateCodeResp();
            
            // 测试各种CSRF token格式
            String[] validStates = {
                "csrf_token_123456",
                "random_state_value",
                "abc123def456ghi789",
                "state-with-dashes",
                "state_with_underscores",
                "MixedCaseState123",
                "12345678901234567890123456789012" // 32位随机字符串
            };
            
            for (String state : validStates) {
                resp.setState(state);
                assertEquals(state, resp.getState());
            }
        }

        @Test
        @DisplayName("回调URL参数格式测试")
        void testCallbackUrlParameterFormat() {
            // 模拟从回调URL解析出的参数
            CreateCodeResp resp1 = new CreateCodeResp("AUTH123", "state456");
            CreateCodeResp resp2 = new CreateCodeResp("CODE789", "csrf_token");
            
            // 验证参数正确设置
            assertEquals("AUTH123", resp1.getCode());
            assertEquals("state456", resp1.getState());
            assertEquals("CODE789", resp2.getCode());
            assertEquals("csrf_token", resp2.getState());
        }

        @Test
        @DisplayName("授权失败场景测试")
        void testAuthorizationFailureScenarios() {
            // 测试可能的失败场景（code为null或空）
            CreateCodeResp failedResp1 = new CreateCodeResp(null, "test_state");
            CreateCodeResp failedResp2 = new CreateCodeResp("", "test_state");
            
            assertNull(failedResp1.getCode());
            assertEquals("", failedResp2.getCode());
            assertEquals("test_state", failedResp1.getState());
            assertEquals("test_state", failedResp2.getState());
        }

        @Test
        @DisplayName("State值对比测试")
        void testStateValueComparison() {
            String originalState = "original_csrf_token";
            CreateCodeResp resp = new CreateCodeResp("CODE123", originalState);
            
            // 模拟验证state值
            assertEquals(originalState, resp.getState());
            
            // 测试state不匹配的情况
            String differentState = "different_csrf_token";
            assertNotEquals(differentState, resp.getState());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("超长字符串测试")
        void testVeryLongStrings() {
            // 创建10000字符的超长字符串（避免使用String.repeat，保持Java 8兼容性）
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
            String longString = sb.toString();
            
            CreateCodeResp resp = new CreateCodeResp();
            
            resp.setCode(longString);
            resp.setState(longString);
            
            assertEquals(longString, resp.getCode());
            assertEquals(longString, resp.getState());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String specialChars = "测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            resp.setCode(specialChars);
            resp.setState(specialChars);
            
            assertEquals(specialChars, resp.getCode());
            assertEquals(specialChars, resp.getState());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String unicodeString = "授权码🔐状态🔄测试💯";
            resp.setCode(unicodeString);
            resp.setState(unicodeString);
            
            assertEquals(unicodeString, resp.getCode());
            assertEquals(unicodeString, resp.getState());
        }

        @Test
        @DisplayName("Base64编码字符测试")
        void testBase64Characters() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String base64String = "QVVUSF9DT0RFXzEyMzQ1Ng==";
            resp.setCode(base64String);
            resp.setState(base64String);
            
            assertEquals(base64String, resp.getCode());
            assertEquals(base64String, resp.getState());
        }

        @Test
        @DisplayName("URL编码字符测试")
        void testUrlEncodedCharacters() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String urlEncodedString = "auth%20code%20with%20spaces";
            resp.setCode(urlEncodedString);
            resp.setState(urlEncodedString);
            
            assertEquals(urlEncodedString, resp.getCode());
            assertEquals(urlEncodedString, resp.getState());
        }

        @Test
        @DisplayName("换行符和制表符测试")
        void testNewlineAndTabCharacters() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String stringWithWhitespace = "code\nwith\ttabs\rand\nlines";
            resp.setCode(stringWithWhitespace);
            resp.setState(stringWithWhitespace);
            
            assertEquals(stringWithWhitespace, resp.getCode());
            assertEquals(stringWithWhitespace, resp.getState());
        }

        @Test
        @DisplayName("HTML编码字符测试")
        void testHtmlEncodedCharacters() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String htmlString = "&lt;code&gt;&amp;&quot;test&quot;&lt;/code&gt;";
            resp.setCode(htmlString);
            resp.setState(htmlString);
            
            assertEquals(htmlString, resp.getCode());
            assertEquals(htmlString, resp.getState());
        }

        @Test
        @DisplayName("数字字符串测试")
        void testNumericStrings() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String numericCode = "1234567890";
            String numericState = "9876543210";
            
            resp.setCode(numericCode);
            resp.setState(numericState);
            
            assertEquals(numericCode, resp.getCode());
            assertEquals(numericState, resp.getState());
        }

        @Test
        @DisplayName("混合大小写测试")
        void testMixedCaseStrings() {
            CreateCodeResp resp = new CreateCodeResp();
            
            String mixedCaseCode = "AuTh_CoDe_123_XyZ";
            String mixedCaseState = "StAtE_ToKeN_456_AbC";
            
            resp.setCode(mixedCaseCode);
            resp.setState(mixedCaseState);
            
            assertEquals(mixedCaseCode, resp.getCode());
            assertEquals(mixedCaseState, resp.getState());
        }
    }

    // 辅助方法
    private CreateCodeResp createValidResponse() {
        return new CreateCodeResp("AUTH_CODE_123456", "test_state");
    }
}