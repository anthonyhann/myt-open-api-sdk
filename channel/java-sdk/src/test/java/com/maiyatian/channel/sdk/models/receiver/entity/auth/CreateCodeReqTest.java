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
 * CreateCodeReq 获取授权码请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. JSON 序列化/反序列化测试
 * 5. 默认值测试
 * 6. 边界条件测试
 */
@DisplayName("CreateCodeReq 获取授权码请求参数测试")
public class CreateCodeReqTest {

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
            CreateCodeReq req = new CreateCodeReq();
            
            assertNotNull(req);
            assertNull(req.getAppKey());
            assertEquals("code", req.getResponseType()); // 默认值
            assertEquals("web", req.getView()); // 默认值
            assertNull(req.getRedirectUri());
            assertNull(req.getShopId());
            assertNull(req.getState());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            CreateCodeReq req = new CreateCodeReq(
                "test_app_key", "code", "h5", 
                "https://example.com/callback", "SHOP_123", "test_state"
            );
            
            assertNotNull(req);
            assertEquals("test_app_key", req.getAppKey());
            assertEquals("code", req.getResponseType());
            assertEquals("h5", req.getView());
            assertEquals("https://example.com/callback", req.getRedirectUri());
            assertEquals("SHOP_123", req.getShopId());
            assertEquals("test_state", req.getState());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("AppKey 设置和获取")
        void testAppKeyGetterSetter() {
            CreateCodeReq req = new CreateCodeReq();
            String appKey = "test_app_key_123";
            
            req.setAppKey(appKey);
            
            assertEquals(appKey, req.getAppKey());
        }

        @Test
        @DisplayName("ResponseType 设置和获取")
        void testResponseTypeGetterSetter() {
            CreateCodeReq req = new CreateCodeReq();
            String responseType = "token";
            
            req.setResponseType(responseType);
            
            assertEquals(responseType, req.getResponseType());
        }

        @Test
        @DisplayName("View 设置和获取")
        void testViewGetterSetter() {
            CreateCodeReq req = new CreateCodeReq();
            String view = "mobile";
            
            req.setView(view);
            
            assertEquals(view, req.getView());
        }

        @Test
        @DisplayName("RedirectUri 设置和获取")
        void testRedirectUriGetterSetter() {
            CreateCodeReq req = new CreateCodeReq();
            String redirectUri = "https://myapp.com/oauth/callback";
            
            req.setRedirectUri(redirectUri);
            
            assertEquals(redirectUri, req.getRedirectUri());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            CreateCodeReq req = new CreateCodeReq();
            String shopId = "SHOP_789";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("State 设置和获取")
        void testStateGetterSetter() {
            CreateCodeReq req = new CreateCodeReq();
            String state = "random_csrf_token";
            
            req.setState(state);
            
            assertEquals(state, req.getState());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            CreateCodeReq req = createValidRequest();
            
            req.setAppKey(null);
            req.setRedirectUri(null);
            req.setShopId(null);
            req.setState(null);
            
            assertNull(req.getAppKey());
            assertNull(req.getRedirectUri());
            assertNull(req.getShopId());
            assertNull(req.getState());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            CreateCodeReq req1 = createValidRequest();
            CreateCodeReq req2 = createValidRequest();
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            CreateCodeReq req1 = createValidRequest();
            CreateCodeReq req2 = createValidRequest();
            req2.setAppKey("different_app_key");
            
            CreateCodeReq req3 = createValidRequest();
            req3.setView("h5");
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, null);
            assertNotEquals(req1, "not a CreateCodeReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            CreateCodeReq req1 = createValidRequest();
            CreateCodeReq req2 = createValidRequest();
            CreateCodeReq req3 = createValidRequest();
            req3.setAppKey("different_app_key");
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            CreateCodeReq req = createValidRequest();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("test_app_key"));
            assertTrue(result.contains("web"));
            assertTrue(result.contains("CreateCodeReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            CreateCodeReq req = new CreateCodeReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("CreateCodeReq"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            CreateCodeReq req = createValidRequest();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"app_key\":\"test_app_key\""));
            assertTrue(json.contains("\"response_type\":\"code\""));
            assertTrue(json.contains("\"view\":\"web\""));
            assertTrue(json.contains("\"redirect_uri\":\"https://example.com/callback\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_123\""));
            assertTrue(json.contains("\"state\":\"test_state\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{" +
                "\"app_key\":\"test_app_key\"," +
                "\"response_type\":\"code\"," +
                "\"view\":\"h5\"," +
                "\"redirect_uri\":\"https://example.com/callback\"," +
                "\"shop_id\":\"SHOP_123\"," +
                "\"state\":\"test_state\"" +
                "}";
            
            CreateCodeReq req = objectMapper.readValue(json, CreateCodeReq.class);
            
            assertNotNull(req);
            assertEquals("test_app_key", req.getAppKey());
            assertEquals("code", req.getResponseType());
            assertEquals("h5", req.getView());
            assertEquals("https://example.com/callback", req.getRedirectUri());
            assertEquals("SHOP_123", req.getShopId());
            assertEquals("test_state", req.getState());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            CreateCodeReq original = createValidRequest();
            
            String json = objectMapper.writeValueAsString(original);
            CreateCodeReq deserialized = objectMapper.readValue(json, CreateCodeReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            CreateCodeReq req = new CreateCodeReq();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            // Jackson默认会序列化默认值
            assertTrue(json.contains("\"response_type\":\"code\""));
            assertTrue(json.contains("\"view\":\"web\""));
        }
    }

    @Nested
    @DisplayName("默认值测试")
    class DefaultValueTests {

        @Test
        @DisplayName("ResponseType默认值测试")
        void testResponseTypeDefaultValue() {
            CreateCodeReq req = new CreateCodeReq();
            
            assertEquals("code", req.getResponseType());
            
            // 测试通过setter修改
            req.setResponseType("token");
            assertEquals("token", req.getResponseType());
        }

        @Test
        @DisplayName("View默认值测试")
        void testViewDefaultValue() {
            CreateCodeReq req = new CreateCodeReq();
            
            assertEquals("web", req.getView());
            
            // 测试通过setter修改
            req.setView("h5");
            assertEquals("h5", req.getView());
        }

        @Test
        @DisplayName("全参构造函数保持默认值")
        void testFullConstructorWithDefaultValues() {
            CreateCodeReq req = new CreateCodeReq(
                "test_app", "code", "web", 
                "https://test.com", "SHOP_1", "state"
            );
            
            assertEquals("code", req.getResponseType());
            assertEquals("web", req.getView());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("超长字符串测试")
        void testVeryLongStrings() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
            String longString = sb.toString();
            CreateCodeReq req = new CreateCodeReq();
            
            req.setAppKey(longString);
            req.setRedirectUri("https://" + longString + ".com");
            req.setShopId(longString);
            req.setState(longString);
            
            assertEquals(longString, req.getAppKey());
            assertTrue(req.getRedirectUri().contains(longString));
            assertEquals(longString, req.getShopId());
            assertEquals(longString, req.getState());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            CreateCodeReq req = new CreateCodeReq();
            
            String specialChars = "测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            req.setAppKey(specialChars);
            req.setShopId(specialChars);
            req.setState(specialChars);
            
            assertEquals(specialChars, req.getAppKey());
            assertEquals(specialChars, req.getShopId());
            assertEquals(specialChars, req.getState());
        }

        @Test
        @DisplayName("URL格式测试")
        void testUrlFormats() {
            CreateCodeReq req = new CreateCodeReq();
            String[] validUrls = {
                "https://example.com/callback",
                "http://localhost:8080/oauth/callback",
                "https://app.domain.com:9000/auth?param=value",
                "https://测试域名.com/回调",
                "https://example.com/path/with%20spaces"
            };
            
            for (String url : validUrls) {
                req.setRedirectUri(url);
                assertEquals(url, req.getRedirectUri());
            }
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            CreateCodeReq req = new CreateCodeReq();
            
            req.setAppKey("");
            req.setResponseType("");
            req.setView("");
            req.setRedirectUri("");
            req.setShopId("");
            req.setState("");
            
            assertEquals("", req.getAppKey());
            assertEquals("", req.getResponseType());
            assertEquals("", req.getView());
            assertEquals("", req.getRedirectUri());
            assertEquals("", req.getShopId());
            assertEquals("", req.getState());
        }

        @Test
        @DisplayName("View类型测试")
        void testViewTypes() {
            CreateCodeReq req = new CreateCodeReq();
            String[] viewTypes = {"web", "h5", "mobile", "desktop", "app"};
            
            for (String viewType : viewTypes) {
                req.setView(viewType);
                assertEquals(viewType, req.getView());
            }
        }

        @Test
        @DisplayName("ResponseType类型测试")
        void testResponseTypes() {
            CreateCodeReq req = new CreateCodeReq();
            String[] responseTypes = {"code", "token", "id_token"};
            
            for (String responseType : responseTypes) {
                req.setResponseType(responseType);
                assertEquals(responseType, req.getResponseType());
            }
        }
    }

    // 辅助方法
    private CreateCodeReq createValidRequest() {
        return new CreateCodeReq(
            "test_app_key", "code", "web",
            "https://example.com/callback", "SHOP_123", "test_state"
        );
    }
}