/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ApiResponse 泛型响应类测试
 * <p>
 * 对应 Go SDK 中的 ApiResponse[T] 测试，验证类型安全和响应处理功能
 * </p>
 */
@DisplayName("ApiResponse泛型响应类测试")
class ApiResponseTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    /**
     * 测试数据类，用于测试泛型功能
     */
    public static class TestData {
        private String id;
        private String name;
        private Integer value;

        public TestData() {}

        public TestData(String id, String name, Integer value) {
            this.id = id;
            this.name = name;
            this.value = value;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Integer getValue() { return value; }
        public void setValue(Integer value) { this.value = value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestData testData = (TestData) o;
            return java.util.Objects.equals(id, testData.id) &&
                   java.util.Objects.equals(name, testData.name) &&
                   java.util.Objects.equals(value, testData.value);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(id, name, value);
        }
    }

    @Test
    @DisplayName("基础构造函数和getter/setter测试")
    void testBasicConstructorAndAccessors() {
        // Given
        TestData testData = new TestData("123", "test", 456);
        
        // When
        ApiResponse<TestData> response = new ApiResponse<>(200, "success", testData);
        
        // Then
        assertEquals(200, response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(testData, response.getData());
    }

    @Test
    @DisplayName("默认构造函数测试")
    void testDefaultConstructor() {
        // When
        ApiResponse<String> response = new ApiResponse<>();
        
        // Then
        assertNull(response.getCode());
        assertNull(response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("从原始Response创建ApiResponse - 成功情况")
    void testFromResponseSuccess() throws JsonProcessingException {
        // Given
        TestData originalData = new TestData("123", "test", 456);
        String dataJson = objectMapper.writeValueAsString(originalData);
        Response originalResponse = new Response(200, "ok", dataJson);
        
        // When
        ApiResponse<TestData> apiResponse = ApiResponse.from(originalResponse, TestData.class, objectMapper);
        
        // Then
        assertEquals(200, apiResponse.getCode());
        assertEquals("ok", apiResponse.getMessage());
        assertNotNull(apiResponse.getData());
        assertEquals(originalData, apiResponse.getData());
    }

    @Test
    @DisplayName("从原始Response创建ApiResponse - 空数据情况")
    void testFromResponseEmptyData() throws JsonProcessingException {
        // Given
        Response originalResponse = new Response(200, "ok", null);
        
        // When
        ApiResponse<TestData> apiResponse = ApiResponse.from(originalResponse, TestData.class, objectMapper);
        
        // Then
        assertEquals(200, apiResponse.getCode());
        assertEquals("ok", apiResponse.getMessage());
        assertNull(apiResponse.getData());
    }

    @Test
    @DisplayName("从原始Response创建ApiResponse - 空字符串数据")
    void testFromResponseEmptyStringData() throws JsonProcessingException {
        // Given
        Response originalResponse = new Response(200, "ok", "");
        
        // When
        ApiResponse<TestData> apiResponse = ApiResponse.from(originalResponse, TestData.class, objectMapper);
        
        // Then
        assertEquals(200, apiResponse.getCode());
        assertEquals("ok", apiResponse.getMessage());
        assertNull(apiResponse.getData());
    }

    @Test
    @DisplayName("从原始Response创建ApiResponse - null类型")
    void testFromResponseNullClass() throws JsonProcessingException {
        // Given
        Response originalResponse = new Response(200, "ok", "{\"test\": \"data\"}");
        
        // When
        ApiResponse<TestData> apiResponse = ApiResponse.from(originalResponse, null, objectMapper);
        
        // Then
        assertEquals(200, apiResponse.getCode());
        assertEquals("ok", apiResponse.getMessage());
        assertNull(apiResponse.getData());
    }

    @Test
    @DisplayName("创建空数据ApiResponse")
    void testFromEmpty() {
        // Given
        Response originalResponse = new Response(200, "ok", "some_data");
        
        // When
        ApiResponse<Void> apiResponse = ApiResponse.fromEmpty(originalResponse);
        
        // Then
        assertEquals(200, apiResponse.getCode());
        assertEquals("ok", apiResponse.getMessage());
        assertNull(apiResponse.getData());
    }

    @Test
    @DisplayName("isSuccess方法测试 - 成功情况")
    void testIsSuccessTrue() {
        // Given
        ApiResponse<String> response = new ApiResponse<>(200, "ok", "test");
        
        // When & Then
        assertTrue(response.isSuccess());
        assertFalse(response.isError());
    }

    @Test
    @DisplayName("isSuccess方法测试 - 失败情况")
    void testIsSuccessFalse() {
        // Given
        ApiResponse<String> response = new ApiResponse<>(400, "error", null);
        
        // When & Then
        assertFalse(response.isSuccess());
        assertTrue(response.isError());
    }

    @Test
    @DisplayName("isSuccess方法测试 - null状态码")
    void testIsSuccessNullCode() {
        // Given
        ApiResponse<String> response = new ApiResponse<>(null, "unknown", null);
        
        // When & Then
        assertFalse(response.isSuccess());
        assertTrue(response.isError());
    }

    @Test
    @DisplayName("getDataOrThrow方法测试 - 成功情况")
    void testGetDataOrThrowSuccess() {
        // Given
        TestData testData = new TestData("123", "test", 456);
        ApiResponse<TestData> response = new ApiResponse<>(200, "ok", testData);
        
        // When
        TestData result = response.getDataOrThrow();
        
        // Then
        assertEquals(testData, result);
    }

    @Test
    @DisplayName("getDataOrThrow方法测试 - 失败情况抛异常")
    void testGetDataOrThrowError() {
        // Given
        ApiResponse<TestData> response = new ApiResponse<>(400, "Bad Request", null);
        
        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, response::getDataOrThrow);
        assertEquals("API 错误 [400]: Bad Request", exception.getMessage());
    }

    @Test
    @DisplayName("equals和hashCode测试")
    void testEqualsAndHashCode() {
        // Given
        TestData testData = new TestData("123", "test", 456);
        ApiResponse<TestData> response1 = new ApiResponse<>(200, "ok", testData);
        ApiResponse<TestData> response2 = new ApiResponse<>(200, "ok", testData);
        ApiResponse<TestData> response3 = new ApiResponse<>(400, "error", null);
        
        // When & Then
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
        
        // 测试相同对象
        assertEquals(response1, response1);
        
        // 测试null和不同类型
        assertNotEquals(response1, null);
        assertNotEquals(response1, "string");
    }

    @Test
    @DisplayName("toString方法测试")
    void testToString() {
        // Given
        TestData testData = new TestData("123", "test", 456);
        ApiResponse<TestData> response = new ApiResponse<>(200, "ok", testData);
        
        // When
        String result = response.toString();
        
        // Then
        assertTrue(result.contains("ApiResponse"));
        assertTrue(result.contains("200"));
        assertTrue(result.contains("ok"));
        assertTrue(result.contains("TestData"));
    }

    @Test
    @DisplayName("JSON序列化兼容性测试")
    void testJsonSerialization() throws JsonProcessingException {
        // Given
        TestData testData = new TestData("123", "test", 456);
        ApiResponse<TestData> originalResponse = new ApiResponse<>(200, "ok", testData);
        
        // When - 序列化
        String json = objectMapper.writeValueAsString(originalResponse);
        assertNotNull(json);
        assertTrue(json.contains("\"code\":200"));
        assertTrue(json.contains("\"message\":\"ok\""));
        
        // Note: 由于泛型类型擦除，反序列化需要TypeReference，这里主要测试序列化功能
    }

    @Test
    @DisplayName("从JSON格式的Response数据创建ApiResponse")
    void testFromJsonResponseData() throws JsonProcessingException {
        // Given
        TestData originalData = new TestData("123", "test", 456);
        String dataJson = objectMapper.writeValueAsString(originalData);
        Response response = new Response(200, "ok", dataJson);
        
        // When
        ApiResponse<TestData> apiResponse = ApiResponse.from(response, TestData.class, objectMapper);
        
        // Then
        assertEquals(originalData, apiResponse.getData());
    }

    @Test
    @DisplayName("处理无效JSON数据")
    void testFromInvalidJsonData() {
        // Given
        Response response = new Response(200, "ok", "{invalid json}");
        
        // When & Then
        assertThrows(JsonProcessingException.class, () -> {
            ApiResponse.from(response, TestData.class, objectMapper);
        });
    }
}