/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.consts.Constants;
import com.maiyatian.delivery.sdk.models.types.ApiResponse;
import com.maiyatian.delivery.sdk.models.types.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ApiClientUtils 类单元测试
 * <p>
 * 测试API客户端工具类的功能，包括：
 * <ul>
 * <li>泛型HTTP请求方法</li>
 * <li>类型安全的响应转换</li>
 * <li>空数据响应处理</li>
 * <li>GET/POST便捷方法</li>
 * <li>异常处理机制</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("ApiClientUtils 单元测试")
public class ApiClientUtilsTest {
    
    @Mock
    private HttpClientManager mockHttpClient;
    
    @Mock
    private Response mockResponse;
    
    private Map<String, String> testHeaders;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testHeaders = new HashMap<>();
        testHeaders.put("X-Test-Header", "test-value");
        testHeaders.put("X-Request-ID", "test-request-123");
    }
    
    /**
     * 测试成功的泛型API请求
     */
    @Test
    @DisplayName("测试成功的泛型API请求")
    void testSuccessfulGenericApiRequest() throws IOException, JsonProcessingException {
        // 准备测试数据
        TestData expectedData = new TestData("123", "Test Name");
        
        // 模拟Response行为
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("{\"id\":\"123\",\"name\":\"Test Name\"}");
        when(mockResponse.parseData(TestData.class)).thenReturn(expectedData);
        
        // 模拟HttpClientManager行为
        when(mockHttpClient.request(eq("POST"), eq("/test/api"), any(), eq("test_token"), eq(testHeaders)))
            .thenReturn(mockResponse);
        
        // 执行测试
        ApiResponse<TestData> result = ApiClientUtils.requestWithApiClient(
            mockHttpClient, "POST", "/test/api", 
            new HashMap<>(), "test_token", testHeaders, TestData.class
        );
        
        // 验证结果
        assertTrue(result.isSuccess(), "应该是成功响应");
        assertEquals(Constants.SUCCESS_CODE, result.getCode(), "状态码应该正确");
        assertEquals("ok", result.getMessage(), "消息应该正确");
        assertEquals(expectedData, result.getData(), "数据应该正确转换");
        
        // 验证调用
        verify(mockHttpClient).request("POST", "/test/api", new HashMap<>(), "test_token", testHeaders);
        verify(mockResponse).parseData(TestData.class);
    }
    
    /**
     * 测试失败的API请求
     */
    @Test
    @DisplayName("测试失败的API请求")
    void testFailedApiRequest() throws IOException, JsonProcessingException {
        // 模拟失败响应
        when(mockResponse.getCode()).thenReturn(400);
        when(mockResponse.getMessage()).thenReturn("Bad Request");
        when(mockResponse.getData()).thenReturn("");
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenReturn(mockResponse);
        
        // 执行测试
        ApiResponse<TestData> result = ApiClientUtils.requestWithApiClient(
            mockHttpClient, "POST", "/test/api",
            new HashMap<>(), "test_token", null, TestData.class
        );
        
        // 验证结果
        assertFalse(result.isSuccess(), "应该是失败响应");
        assertEquals(400, result.getCode(), "状态码应该正确");
        assertEquals("Bad Request", result.getMessage(), "错误消息应该正确");
        assertNull(result.getData(), "失败响应不应该有数据");
    }
    
    /**
     * 测试空数据响应处理
     */
    @Test
    @DisplayName("测试空数据响应处理")
    void testEmptyDataResponse() throws IOException, JsonProcessingException {
        // 模拟空数据成功响应
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn(null);
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenReturn(mockResponse);
        
        // 测试Void类型请求
        ApiResponse<Void> result = ApiClientUtils.requestWithApiClient(
            mockHttpClient, "POST", "/test/api",
            new HashMap<>(), "test_token", null, Void.class
        );
        
        // 验证结果
        assertTrue(result.isSuccess(), "应该是成功响应");
        assertNull(result.getData(), "Void类型响应数据应该为null");
        assertFalse(result.hasData(), "不应该有数据");
        
        // 验证没有调用parseData（因为是Void类型）
        verify(mockResponse, never()).parseData(any());
    }
    
    /**
     * 测试requestWithEmptyResponse便捷方法
     */
    @Test
    @DisplayName("测试requestWithEmptyResponse便捷方法")
    void testRequestWithEmptyResponse() throws IOException, JsonProcessingException {
        // 模拟成功响应
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("");
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenReturn(mockResponse);
        
        // 执行测试
        ApiResponse<Void> result = ApiClientUtils.requestWithEmptyResponse(
            mockHttpClient, "POST", "/test/api",
            new HashMap<>(), "test_token", testHeaders
        );
        
        // 验证结果
        assertTrue(result.isSuccess(), "应该是成功响应");
        assertNull(result.getData(), "空响应数据应该为null");
        
        // 验证调用参数
        verify(mockHttpClient).request("POST", "/test/api", new HashMap<>(), "test_token", testHeaders);
    }
    
    /**
     * 测试GET便捷方法
     */
    @Test
    @DisplayName("测试GET便捷方法")
    void testGetConvenienceMethod() throws IOException, JsonProcessingException {
        TestData expectedData = new TestData("456", "Get Test");
        
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("{\"id\":\"456\",\"name\":\"Get Test\"}");
        when(mockResponse.parseData(TestData.class)).thenReturn(expectedData);
        
        when(mockHttpClient.request(eq("GET"), eq("/test/get"), eq(null), eq("get_token"), eq(testHeaders)))
            .thenReturn(mockResponse);
        
        // 执行测试
        ApiResponse<TestData> result = ApiClientUtils.get(
            mockHttpClient, "/test/get", "get_token", testHeaders, TestData.class
        );
        
        // 验证结果
        assertTrue(result.isSuccess(), "GET请求应该成功");
        assertEquals(expectedData, result.getData(), "GET数据应该正确");
        
        // 验证GET请求的data参数为null
        verify(mockHttpClient).request("GET", "/test/get", null, "get_token", testHeaders);
    }
    
    /**
     * 测试POST便捷方法
     */
    @Test
    @DisplayName("测试POST便捷方法")
    void testPostConvenienceMethod() throws IOException, JsonProcessingException {
        TestData postData = new TestData("789", "Post Data");
        TestData responseData = new TestData("789", "Posted Data");
        
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("{\"id\":\"789\",\"name\":\"Posted Data\"}");
        when(mockResponse.parseData(TestData.class)).thenReturn(responseData);
        
        when(mockHttpClient.request(eq("POST"), eq("/test/post"), eq(postData), eq("post_token"), eq(testHeaders)))
            .thenReturn(mockResponse);
        
        // 执行测试
        ApiResponse<TestData> result = ApiClientUtils.post(
            mockHttpClient, "/test/post", postData, "post_token", testHeaders, TestData.class
        );
        
        // 验证结果
        assertTrue(result.isSuccess(), "POST请求应该成功");
        assertEquals(responseData, result.getData(), "POST响应数据应该正确");
        
        // 验证POST请求携带了数据
        verify(mockHttpClient).request("POST", "/test/post", postData, "post_token", testHeaders);
    }
    
    /**
     * 测试IOException异常处理
     */
    @Test
    @DisplayName("测试IOException异常处理")
    void testIOExceptionHandling() throws IOException, JsonProcessingException {
        // 模拟IOException
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenThrow(new IOException("Network error"));
        
        // 验证异常传播
        assertThrows(IOException.class, () -> {
            ApiClientUtils.requestWithApiClient(
                mockHttpClient, "POST", "/test/api",
                new HashMap<>(), "test_token", null, TestData.class
            );
        }, "IOException应该被传播");
    }
    
    /**
     * 测试JsonProcessingException异常处理
     */
    @Test
    @DisplayName("测试JsonProcessingException异常处理")
    void testJsonProcessingExceptionHandling() throws IOException, JsonProcessingException {
        // 模拟JsonProcessingException
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenThrow(new JsonProcessingException("JSON parse error") {});
        
        // 验证异常传播
        assertThrows(JsonProcessingException.class, () -> {
            ApiClientUtils.requestWithApiClient(
                mockHttpClient, "POST", "/test/api",
                new HashMap<>(), "test_token", null, TestData.class
            );
        }, "JsonProcessingException应该被传播");
    }
    
    /**
     * 测试数据解析异常处理
     */
    @Test
    @DisplayName("测试数据解析异常处理")
    void testDataParsingException() throws IOException, JsonProcessingException {
        // 模拟成功HTTP响应但数据解析失败
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("invalid_json_data");
        when(mockResponse.parseData(TestData.class)).thenThrow(new RuntimeException("Parse failed"));
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenReturn(mockResponse);
        
        // 验证解析异常传播
        assertThrows(RuntimeException.class, () -> {
            ApiClientUtils.requestWithApiClient(
                mockHttpClient, "POST", "/test/api",
                new HashMap<>(), "test_token", null, TestData.class
            );
        }, "数据解析异常应该被传播");
    }
    
    /**
     * 测试无headers情况
     */
    @Test
    @DisplayName("测试无headers的请求")
    void testRequestWithoutHeaders() throws IOException, JsonProcessingException {
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("");
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), isNull()))
            .thenReturn(mockResponse);
        
        // 执行测试（headers为null）
        ApiResponse<Void> result = ApiClientUtils.requestWithEmptyResponse(
            mockHttpClient, "POST", "/test/api",
            new HashMap<>(), "test_token", null
        );
        
        assertTrue(result.isSuccess(), "无headers的请求应该成功");
        verify(mockHttpClient).request("POST", "/test/api", new HashMap<>(), "test_token", null);
    }
    
    /**
     * 测试空字符串数据处理
     */
    @Test
    @DisplayName("测试空字符串数据处理")
    void testEmptyStringDataHandling() throws IOException, JsonProcessingException {
        // 模拟空字符串数据
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("");
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenReturn(mockResponse);
        
        // 执行测试
        ApiResponse<TestData> result = ApiClientUtils.requestWithApiClient(
            mockHttpClient, "POST", "/test/api",
            new HashMap<>(), "test_token", null, TestData.class
        );
        
        // 验证结果
        assertTrue(result.isSuccess(), "应该是成功响应");
        assertNull(result.getData(), "空字符串数据应该导致null");
        
        // 验证没有调用parseData（因为数据为空）
        verify(mockResponse, never()).parseData(any());
    }
    
    /**
     * 测试工具类不能实例化
     */
    @Test
    @DisplayName("测试工具类不能实例化")
    void testUtilityClassCannotBeInstantiated() {
        // ApiClientUtils应该是工具类，不能被实例化
        assertThrows(Exception.class, () -> {
            // 使用反射尝试创建实例
            ApiClientUtils.class.getDeclaredConstructor().newInstance();
        }, "工具类不应该能够被实例化");
    }
    
    /**
     * 性能测试：大量API调用
     */
    @Test
    @DisplayName("性能测试 - 大量API调用")
    void testPerformance() throws IOException, JsonProcessingException {
        // 准备模拟数据
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("");
        
        when(mockHttpClient.request(anyString(), anyString(), any(), anyString(), any()))
            .thenReturn(mockResponse);
        
        int iterations = 1000;
        long startTime = System.nanoTime();
        
        // 执行大量API调用
        for (int i = 0; i < iterations; i++) {
            ApiResponse<Void> result = ApiClientUtils.requestWithEmptyResponse(
                mockHttpClient, "POST", "/test/api",
                new HashMap<>(), "token_" + i, null
            );
            assertTrue(result.isSuccess(), "每次API调用都应该成功");
        }
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double avgTimePerCall = duration / (double) iterations;
        
        // 平均每次调用应该在100微秒以内（不包括网络时间，只是对象创建和转换）
        assertTrue(avgTimePerCall < 100000, // 100000纳秒 = 100微秒
            String.format("性能测试失败，平均耗时: %.2f纳秒", avgTimePerCall));
        
        System.out.printf("ApiClientUtils性能测试 - %d次调用，平均耗时: %.2f纳秒%n", iterations, avgTimePerCall);
    }
    
    /**
     * 测试数据类，用于测试
     */
    private static class TestData {
        private String id;
        private String name;
        
        public TestData() {}
        
        public TestData(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestData testData = (TestData) o;
            return java.util.Objects.equals(id, testData.id) && 
                   java.util.Objects.equals(name, testData.name);
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(id, name);
        }
        
        @Override
        public String toString() {
            return "TestData{id='" + id + "', name='" + name + "'}";
        }
    }
}