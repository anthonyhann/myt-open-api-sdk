/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.types;

import com.maiyatian.delivery.sdk.consts.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * ApiResponse 类单元测试
 * <p>
 * 测试类型安全的API响应包装器功能，包括：
 * <ul>
 * <li>成功和失败响应处理</li>
 * <li>数据获取和类型转换</li>
 * <li>错误处理机制</li>
 * <li>链式操作支持</li>
 * <li>静态工厂方法</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("ApiResponse 单元测试")
public class ApiResponseTest {
    
    @Mock
    private Response mockResponse;
    
    private ApiResponse<String> apiResponse;
    private ApiResponse<TestData> complexApiResponse;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // 创建基本的ApiResponse实例
        apiResponse = new ApiResponse<>();
        apiResponse.setCode(Constants.SUCCESS_CODE);
        apiResponse.setMessage("ok");
        apiResponse.setData("test_data");
        
        // 创建复杂类型的ApiResponse实例
        complexApiResponse = new ApiResponse<>();
        complexApiResponse.setCode(Constants.SUCCESS_CODE);
        complexApiResponse.setMessage("ok");
        complexApiResponse.setData(new TestData("test_id", "test_name"));
    }
    
    /**
     * 测试成功响应创建
     */
    @Test
    @DisplayName("测试成功响应的创建和访问")
    void testSuccessResponse() {
        // 测试静态工厂方法创建成功响应
        String testData = "success_data";
        ApiResponse<String> response = ApiResponse.success(testData);
        
        assertTrue(response.isSuccess(), "应该是成功响应");
        assertFalse(response.isError(), "不应该是错误响应");
        assertEquals(Constants.SUCCESS_CODE, response.getCode(), "状态码应该是200");
        assertEquals("ok", response.getMessage(), "消息应该是'ok'");
        assertEquals(testData, response.getData(), "数据应该正确设置");
        assertTrue(response.hasData(), "应该有数据");
    }
    
    /**
     * 测试空数据成功响应
     */
    @Test
    @DisplayName("测试空数据成功响应")
    void testSuccessResponseWithoutData() {
        ApiResponse<Void> response = ApiResponse.success();
        
        assertTrue(response.isSuccess(), "应该是成功响应");
        assertFalse(response.isError(), "不应该是错误响应");
        assertEquals(Constants.SUCCESS_CODE, response.getCode(), "状态码应该是200");
        assertEquals("ok", response.getMessage(), "消息应该是'ok'");
        assertNull(response.getData(), "数据应该为null");
        assertFalse(response.hasData(), "不应该有数据");
    }
    
    /**
     * 测试错误响应创建
     */
    @Test
    @DisplayName("测试错误响应的创建和访问")
    void testErrorResponse() {
        int errorCode = 400;
        String errorMessage = "Bad Request";
        ApiResponse<String> response = ApiResponse.error(errorCode, errorMessage);
        
        assertFalse(response.isSuccess(), "不应该是成功响应");
        assertTrue(response.isError(), "应该是错误响应");
        assertEquals(errorCode, response.getCode(), "错误码应该正确设置");
        assertEquals(errorMessage, response.getMessage(), "错误消息应该正确设置");
        assertNull(response.getData(), "错误响应不应该有数据");
        assertFalse(response.hasData(), "错误响应不应该有数据");
    }
    
    /**
     * 测试数据获取方法
     */
    @Test
    @DisplayName("测试数据获取方法")
    void testDataAccess() {
        // 测试成功情况下的数据获取
        String testData = "test_data";
        ApiResponse<String> successResponse = ApiResponse.success(testData);
        
        assertEquals(testData, successResponse.getDataOrThrow(), "getDataOrThrow应该返回正确数据");
        assertEquals(testData, successResponse.getDataOrDefault("default"), "getDataOrDefault应该返回实际数据");
        
        // 测试失败情况下的数据获取
        ApiResponse<String> errorResponse = ApiResponse.error(500, "Internal Error");
        
        assertThrows(RuntimeException.class, errorResponse::getDataOrThrow, 
            "错误响应调用getDataOrThrow应该抛出异常");
        
        String defaultValue = "default_value";
        assertEquals(defaultValue, errorResponse.getDataOrDefault(defaultValue),
            "错误响应的getDataOrDefault应该返回默认值");
    }
    
    /**
     * 测试空数据情况的处理
     */
    @Test
    @DisplayName("测试空数据情况的处理")
    void testNullDataHandling() {
        ApiResponse<String> responseWithNullData = new ApiResponse<>(Constants.SUCCESS_CODE, "ok", null);
        
        assertTrue(responseWithNullData.isSuccess(), "状态码200应该是成功");
        assertFalse(responseWithNullData.hasData(), "null数据不应该被认为有数据");
        
        assertThrows(RuntimeException.class, responseWithNullData::getDataOrThrow,
            "null数据调用getDataOrThrow应该抛出异常");
        
        String defaultValue = "default";
        assertEquals(defaultValue, responseWithNullData.getDataOrDefault(defaultValue),
            "null数据应该返回默认值");
    }
    
    /**
     * 测试链式操作 - map方法
     */
    @Test
    @DisplayName("测试链式操作 - map方法")
    void testMapOperation() {
        ApiResponse<String> originalResponse = ApiResponse.success("123");
        
        // 测试成功响应的map操作
        ApiResponse<Integer> mappedResponse = originalResponse.map(Integer::parseInt);
        
        assertTrue(mappedResponse.isSuccess(), "map后应该仍然是成功响应");
        assertEquals(Integer.valueOf(123), mappedResponse.getData(), "map应该正确转换数据类型");
        
        // 测试错误响应的map操作
        ApiResponse<String> errorResponse = ApiResponse.error(400, "Error");
        ApiResponse<Integer> mappedErrorResponse = errorResponse.map(Integer::parseInt);
        
        assertTrue(mappedErrorResponse.isError(), "错误响应map后应该仍然是错误响应");
        assertEquals(400, mappedErrorResponse.getCode(), "错误码应该保持不变");
        assertNull(mappedErrorResponse.getData(), "错误响应map后数据应该为null");
    }
    
    /**
     * 测试链式操作 - map异常处理
     */
    @Test
    @DisplayName("测试map操作的异常处理")
    void testMapOperationWithException() {
        ApiResponse<String> response = ApiResponse.success("not_a_number");
        
        // map操作抛出异常时，应该返回错误响应
        ApiResponse<Integer> mappedResponse = response.map(Integer::parseInt);
        
        assertTrue(mappedResponse.isError(), "map异常应该导致错误响应");
        assertEquals(500, mappedResponse.getCode(), "map异常应该返回500错误码");
        assertTrue(mappedResponse.getMessage().contains("数据转换失败"), "错误消息应该说明转换失败");
    }
    
    /**
     * 测试链式操作 - filter方法
     */
    @Test
    @DisplayName("测试链式操作 - filter方法")
    void testFilterOperation() {
        // 测试满足条件的过滤
        ApiResponse<String> successResponse = ApiResponse.success("test");
        ApiResponse<String> filteredSuccess = successResponse.filter(ApiResponse::isSuccess);
        
        assertTrue(filteredSuccess.isSuccess(), "满足过滤条件应该返回原响应");
        assertEquals("test", filteredSuccess.getData(), "过滤后数据应该保持不变");
        
        // 测试不满足条件的过滤
        ApiResponse<String> filteredFail = successResponse.filter(ApiResponse::isError);
        
        assertTrue(filteredFail.isError(), "不满足过滤条件应该返回错误响应");
        assertEquals(400, filteredFail.getCode(), "过滤失败应该返回400错误码");
        assertTrue(filteredFail.getMessage().contains("不满足过滤条件"), "应该有过滤失败的错误消息");
    }
    
    /**
     * 测试从Response转换
     */
    @Test
    @DisplayName("测试从Response转换为ApiResponse")
    void testFromResponseConversion() throws IOException {
        // 模拟Response对象
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("{\"name\":\"test\"}");
        when(mockResponse.parseData(TestData.class)).thenReturn(new TestData("1", "test"));
        
        // 测试转换
        ApiResponse<TestData> convertedResponse = ApiResponse.from(mockResponse, TestData.class);
        
        assertTrue(convertedResponse.isSuccess(), "转换后应该是成功响应");
        assertEquals("ok", convertedResponse.getMessage(), "消息应该正确转换");
        assertNotNull(convertedResponse.getData(), "应该有转换后的数据");
        assertEquals("test", convertedResponse.getData().getName(), "数据应该正确解析");
    }
    
    /**
     * 测试从Response转换空数据
     */
    @Test
    @DisplayName("测试从Response转换空数据情况")
    void testFromEmptyResponseConversion() {
        // 模拟空数据Response
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("");
        
        ApiResponse<Void> emptyResponse = ApiResponse.fromEmpty(mockResponse);
        
        assertTrue(emptyResponse.isSuccess(), "空数据响应应该是成功的");
        assertFalse(emptyResponse.hasData(), "空数据响应不应该有数据");
    }
    
    /**
     * 测试Response转换异常处理
     */
    @Test
    @DisplayName("测试Response转换异常处理")
    void testFromResponseConversionWithException() throws IOException {
        // 模拟解析异常
        when(mockResponse.getCode()).thenReturn(Constants.SUCCESS_CODE);
        when(mockResponse.getMessage()).thenReturn("ok");
        when(mockResponse.getData()).thenReturn("invalid_json");
        when(mockResponse.parseData(TestData.class)).thenThrow(new RuntimeException("Parse error"));
        
        // 转换应该抛出异常
        assertThrows(RuntimeException.class, () -> {
            ApiResponse.from(mockResponse, TestData.class);
        }, "解析失败应该抛出运行时异常");
    }
    
    /**
     * 测试equals和hashCode方法
     */
    @Test
    @DisplayName("测试equals和hashCode方法")
    void testEqualsAndHashCode() {
        ApiResponse<String> response1 = new ApiResponse<>(200, "ok", "data");
        ApiResponse<String> response2 = new ApiResponse<>(200, "ok", "data");
        ApiResponse<String> response3 = new ApiResponse<>(400, "error", null);
        
        // 测试相等性
        assertEquals(response1, response2, "相同内容的响应应该相等");
        assertNotEquals(response1, response3, "不同内容的响应不应该相等");
        
        // 测试hashCode一致性
        assertEquals(response1.hashCode(), response2.hashCode(), "相等对象的hashCode应该相同");
    }
    
    /**
     * 测试toString方法
     */
    @Test
    @DisplayName("测试toString方法")
    void testToString() {
        ApiResponse<String> response = new ApiResponse<>(200, "ok", "test_data");
        String toString = response.toString();
        
        assertTrue(toString.contains("200"), "toString应该包含状态码");
        assertTrue(toString.contains("ok"), "toString应该包含消息");
        assertTrue(toString.contains("test_data"), "toString应该包含数据");
        assertTrue(toString.contains("hasData=true"), "toString应该包含hasData状态");
    }
    
    /**
     * 测试复杂类型的响应处理
     */
    @Test
    @DisplayName("测试复杂类型的响应处理")
    void testComplexTypeResponse() {
        TestData originalData = new TestData("123", "Test Name");
        ApiResponse<TestData> response = ApiResponse.success(originalData);
        
        assertTrue(response.hasData(), "复杂类型响应应该有数据");
        assertEquals(originalData, response.getData(), "复杂类型数据应该正确保存");
        
        // 测试map操作提取字段
        ApiResponse<String> nameResponse = response.map(TestData::getName);
        assertTrue(nameResponse.isSuccess(), "map操作应该成功");
        assertEquals("Test Name", nameResponse.getData(), "应该正确提取字段");
    }
    
    /**
     * 性能测试：大量创建和访问
     */
    @Test
    @DisplayName("性能测试 - 大量创建和访问")
    void testPerformance() {
        int iterations = 10000;
        long startTime = System.nanoTime();
        
        for (int i = 0; i < iterations; i++) {
            ApiResponse<String> response = ApiResponse.success("data_" + i);
            assertTrue(response.isSuccess());
            assertNotNull(response.getData());
        }
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double avgTimePerOperation = duration / (double) iterations;
        
        // 平均每次操作应该在10微秒以内
        assertTrue(avgTimePerOperation < 10000, // 10000纳秒 = 10微秒
            String.format("性能测试失败，平均耗时: %.2f纳秒", avgTimePerOperation));
        
        System.out.printf("ApiResponse性能测试 - %d次操作，平均耗时: %.2f纳秒%n", iterations, avgTimePerOperation);
    }
    
    /**
     * 测试数据类，用于复杂类型测试
     */
    private static class TestData {
        private String id;
        private String name;
        
        public TestData(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        
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