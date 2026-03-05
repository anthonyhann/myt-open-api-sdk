/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import com.maiyatian.delivery.sdk.consts.Constants;
import com.maiyatian.delivery.sdk.vars.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ConfigBuilder 类单元测试
 * <p>
 * 测试HTTP客户端配置构建器的功能，包括：
 * <ul>
 * <li>Builder模式的链式调用</li>
 * <li>默认值设置和覆盖</li>
 * <li>参数验证机制</li>
 * <li>配置构建和验证</li>
 * <li>异常处理</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("ConfigBuilder 单元测试")
public class ConfigBuilderTest {
    
    private ConfigBuilder configBuilder;
    
    @BeforeEach
    void setUp() {
        configBuilder = new ConfigBuilder();
    }
    
    /**
     * 测试默认值设置
     */
    @Test
    @DisplayName("测试默认值设置")
    void testDefaultValues() {
        HttpClientConfig config = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .build();
        
        // 验证默认值
        assertEquals(Constants.PROD_BASE_URL, config.getBaseUrl(), "默认应该使用生产环境URL");
        assertEquals(Variables.getVersion(), config.getSdkVersion(), "默认应该使用当前SDK版本");
        assertEquals(50, config.getMaxConnections(), "默认最大连接数应该是50");
        assertEquals(10, config.getMaxConnectionsPerHost(), "默认每主机连接数应该是10");
        assertEquals(Duration.ofSeconds(30), config.getKeepAliveTimeout(), "默认保活时间应该是30秒");
        assertEquals(Duration.ofSeconds(60), config.getRequestTimeout(), "默认请求超时应该是60秒");
        assertEquals(Duration.ofSeconds(30), config.getConnectionTimeout(), "默认连接超时应该是30秒");
        assertEquals(Duration.ofSeconds(60), config.getReadTimeout(), "默认读取超时应该是60秒");
        assertEquals(3, config.getRetryMaxAttempts(), "默认最大重试次数应该是3");
        assertEquals(Duration.ofSeconds(1), config.getRetryBaseDelay(), "默认重试基础延迟应该是1秒");
        assertEquals(Duration.ofSeconds(30), config.getRetryMaxDelay(), "默认重试最大延迟应该是30秒");
        assertTrue(config.isEnableLogging(), "默认应该启用日志");
        assertFalse(config.isLogRequestBody(), "默认不应该记录请求体");
        assertFalse(config.isLogResponseBody(), "默认不应该记录响应体");
    }
    
    /**
     * 测试基础配置链式调用
     */
    @Test
    @DisplayName("测试基础配置的链式调用")
    void testBasicConfigurationChaining() {
        String testBaseUrl = "https://test-api.example.com";
        String testApiKey = "test_api_key_123";
        String testApiSecret = "test_api_secret_456";
        String testSdkVersion = "2.0.0";
        
        HttpClientConfig config = configBuilder
            .baseUrl(testBaseUrl)
            .apiKey(testApiKey)
            .apiSecret(testApiSecret)
            .sdkVersion(testSdkVersion)
            .build();
        
        assertEquals(testBaseUrl, config.getBaseUrl(), "BaseURL应该正确设置");
        assertEquals(testApiKey, config.getApiKey(), "APIKey应该正确设置");
        assertEquals(testApiSecret, config.getApiSecret(), "APISecret应该正确设置");
        assertEquals(testSdkVersion, config.getSdkVersion(), "SDK版本应该正确设置");
    }
    
    /**
     * 测试连接池配置
     */
    @Test
    @DisplayName("测试连接池配置")
    void testConnectionPoolConfiguration() {
        int testMaxConnections = 100;
        int testMaxConnectionsPerHost = 20;
        Duration testKeepAliveTimeout = Duration.ofSeconds(45);
        
        HttpClientConfig config = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .maxConnections(testMaxConnections)
            .maxConnectionsPerHost(testMaxConnectionsPerHost)
            .keepAliveTimeout(testKeepAliveTimeout)
            .build();
        
        assertEquals(testMaxConnections, config.getMaxConnections(), "最大连接数应该正确设置");
        assertEquals(testMaxConnectionsPerHost, config.getMaxConnectionsPerHost(), "每主机连接数应该正确设置");
        assertEquals(testKeepAliveTimeout, config.getKeepAliveTimeout(), "保活时间应该正确设置");
    }
    
    /**
     * 测试超时配置
     */
    @Test
    @DisplayName("测试超时配置")
    void testTimeoutConfiguration() {
        Duration testRequestTimeout = Duration.ofSeconds(90);
        Duration testConnectionTimeout = Duration.ofSeconds(20);
        Duration testReadTimeout = Duration.ofSeconds(120);
        
        HttpClientConfig config = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .requestTimeout(testRequestTimeout)
            .connectionTimeout(testConnectionTimeout)
            .readTimeout(testReadTimeout)
            .build();
        
        assertEquals(testRequestTimeout, config.getRequestTimeout(), "请求超时应该正确设置");
        assertEquals(testConnectionTimeout, config.getConnectionTimeout(), "连接超时应该正确设置");
        assertEquals(testReadTimeout, config.getReadTimeout(), "读取超时应该正确设置");
    }
    
    /**
     * 测试重试配置
     */
    @Test
    @DisplayName("测试重试配置")
    void testRetryConfiguration() {
        int testRetryMaxAttempts = 5;
        Duration testRetryBaseDelay = Duration.ofMillis(500);
        Duration testRetryMaxDelay = Duration.ofSeconds(10);
        
        HttpClientConfig config = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .retryMaxAttempts(testRetryMaxAttempts)
            .retryBaseDelay(testRetryBaseDelay)
            .retryMaxDelay(testRetryMaxDelay)
            .build();
        
        assertEquals(testRetryMaxAttempts, config.getRetryMaxAttempts(), "最大重试次数应该正确设置");
        assertEquals(testRetryBaseDelay, config.getRetryBaseDelay(), "重试基础延迟应该正确设置");
        assertEquals(testRetryMaxDelay, config.getRetryMaxDelay(), "重试最大延迟应该正确设置");
    }
    
    /**
     * 测试日志配置
     */
    @Test
    @DisplayName("测试日志配置")
    void testLoggingConfiguration() {
        HttpClientConfig config = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .enableLogging(false)
            .logRequestBody(true)
            .logResponseBody(true)
            .build();
        
        assertFalse(config.isEnableLogging(), "日志应该被禁用");
        assertTrue(config.isLogRequestBody(), "请求体日志应该启用");
        assertTrue(config.isLogResponseBody(), "响应体日志应该启用");
    }
    
    /**
     * 测试完整配置链式调用
     */
    @Test
    @DisplayName("测试完整配置的链式调用")
    void testCompleteConfigurationChaining() {
        HttpClientConfig config = configBuilder
            .baseUrl(Constants.TEST_BASE_URL)
            .apiKey("complete_test_key")
            .apiSecret("complete_test_secret")
            .sdkVersion("3.0.0")
            .maxConnections(200)
            .maxConnectionsPerHost(50)
            .keepAliveTimeout(Duration.ofSeconds(60))
            .requestTimeout(Duration.ofSeconds(120))
            .connectionTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(90))
            .retryMaxAttempts(7)
            .retryBaseDelay(Duration.ofMillis(200))
            .retryMaxDelay(Duration.ofSeconds(60))
            .enableLogging(true)
            .logRequestBody(false)
            .logResponseBody(true)
            .build();
        
        // 验证所有配置都正确设置
        assertEquals(Constants.TEST_BASE_URL, config.getBaseUrl());
        assertEquals("complete_test_key", config.getApiKey());
        assertEquals("complete_test_secret", config.getApiSecret());
        assertEquals("3.0.0", config.getSdkVersion());
        assertEquals(200, config.getMaxConnections());
        assertEquals(50, config.getMaxConnectionsPerHost());
        assertEquals(Duration.ofSeconds(60), config.getKeepAliveTimeout());
        assertEquals(Duration.ofSeconds(120), config.getRequestTimeout());
        assertEquals(Duration.ofSeconds(15), config.getConnectionTimeout());
        assertEquals(Duration.ofSeconds(90), config.getReadTimeout());
        assertEquals(7, config.getRetryMaxAttempts());
        assertEquals(Duration.ofMillis(200), config.getRetryBaseDelay());
        assertEquals(Duration.ofSeconds(60), config.getRetryMaxDelay());
        assertTrue(config.isEnableLogging());
        assertFalse(config.isLogRequestBody());
        assertTrue(config.isLogResponseBody());
    }
    
    /**
     * 测试必填项验证 - 缺少BaseURL
     */
    @Test
    @DisplayName("测试必填项验证 - 缺少BaseURL")
    void testMissingBaseUrlValidation() {
        assertThrows(IllegalStateException.class, () -> {
            configBuilder
                .apiKey("test_key")
                .apiSecret("test_secret")
                .baseUrl("") // 空字符串
                .build();
        }, "空BaseURL应该抛出异常");
        
        assertThrows(IllegalStateException.class, () -> {
            configBuilder
                .apiKey("test_key")
                .apiSecret("test_secret")
                .baseUrl(null) // null值
                .build();
        }, "null BaseURL应该抛出异常");
    }
    
    /**
     * 测试必填项验证 - 缺少APIKey
     */
    @Test
    @DisplayName("测试必填项验证 - 缺少APIKey")
    void testMissingApiKeyValidation() {
        assertThrows(IllegalStateException.class, () -> {
            configBuilder
                .baseUrl(Constants.TEST_BASE_URL)
                .apiSecret("test_secret")
                .build(); // 没有设置APIKey
        }, "缺少APIKey应该抛出异常");
        
        assertThrows(IllegalStateException.class, () -> {
            configBuilder
                .baseUrl(Constants.TEST_BASE_URL)
                .apiKey("") // 空字符串
                .apiSecret("test_secret")
                .build();
        }, "空APIKey应该抛出异常");
    }
    
    /**
     * 测试必填项验证 - 缺少APISecret
     */
    @Test
    @DisplayName("测试必填项验证 - 缺少APISecret")
    void testMissingApiSecretValidation() {
        assertThrows(IllegalStateException.class, () -> {
            configBuilder
                .baseUrl(Constants.TEST_BASE_URL)
                .apiKey("test_key")
                .build(); // 没有设置APISecret
        }, "缺少APISecret应该抛出异常");
        
        assertThrows(IllegalStateException.class, () -> {
            configBuilder
                .baseUrl(Constants.TEST_BASE_URL)
                .apiKey("test_key")
                .apiSecret("") // 空字符串
                .build();
        }, "空APISecret应该抛出异常");
    }
    
    /**
     * 测试参数验证 - 无效的连接数
     */
    @Test
    @DisplayName("测试参数验证 - 无效的连接数")
    void testInvalidConnectionValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.maxConnections(0);
        }, "最大连接数为0应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.maxConnections(-1);
        }, "负数最大连接数应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.maxConnectionsPerHost(0);
        }, "每主机连接数为0应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.maxConnectionsPerHost(-5);
        }, "负数每主机连接数应该抛出异常");
    }
    
    /**
     * 测试参数验证 - 无效的超时时间
     */
    @Test
    @DisplayName("测试参数验证 - 无效的超时时间")
    void testInvalidTimeoutValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.requestTimeout(null);
        }, "null请求超时应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.requestTimeout(Duration.ZERO);
        }, "零请求超时应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.requestTimeout(Duration.ofSeconds(-1));
        }, "负数请求超时应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.connectionTimeout(null);
        }, "null连接超时应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.readTimeout(Duration.ZERO);
        }, "零读取超时应该抛出异常");
    }
    
    /**
     * 测试参数验证 - 无效的重试配置
     */
    @Test
    @DisplayName("测试参数验证 - 无效的重试配置")
    void testInvalidRetryValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.retryMaxAttempts(-1);
        }, "负数重试次数应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.retryBaseDelay(null);
        }, "null重试基础延迟应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.retryBaseDelay(Duration.ofSeconds(-1));
        }, "负数重试延迟应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.retryMaxDelay(null);
        }, "null重试最大延迟应该抛出异常");
        
        assertThrows(IllegalArgumentException.class, () -> {
            configBuilder.retryMaxDelay(Duration.ofMillis(-100));
        }, "负数重试最大延迟应该抛出异常");
    }
    
    /**
     * 测试边界值
     */
    @Test
    @DisplayName("测试边界值")
    void testBoundaryValues() {
        // 测试重试次数为0（合法）
        HttpClientConfig config = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .retryMaxAttempts(0) // 0次重试是合法的
            .retryBaseDelay(Duration.ZERO) // 0延迟是合法的
            .build();
        
        assertEquals(0, config.getRetryMaxAttempts(), "0次重试应该是合法的");
        assertEquals(Duration.ZERO, config.getRetryBaseDelay(), "0延迟应该是合法的");
        
        // 测试最小连接数（1）
        HttpClientConfig config2 = configBuilder
            .apiKey("test_key")
            .apiSecret("test_secret")
            .maxConnections(1)
            .maxConnectionsPerHost(1)
            .build();
        
        assertEquals(1, config2.getMaxConnections(), "最小连接数应该是1");
        assertEquals(1, config2.getMaxConnectionsPerHost(), "每主机最小连接数应该是1");
    }
    
    /**
     * 测试字符串参数的trim处理
     */
    @Test
    @DisplayName("测试字符串参数的trim处理")
    void testStringParameterTrimming() {
        HttpClientConfig config = configBuilder
            .baseUrl("  " + Constants.TEST_BASE_URL + "  ") // 前后有空格
            .apiKey("  test_key  ") // 前后有空格
            .apiSecret("  test_secret  ") // 前后有空格
            .build();
        
        assertEquals(Constants.TEST_BASE_URL, config.getBaseUrl(), "BaseURL应该被trim");
        assertEquals("test_key", config.getApiKey(), "APIKey应该被trim");
        assertEquals("test_secret", config.getApiSecret(), "APISecret应该被trim");
    }
    
    /**
     * 测试构建器复用
     */
    @Test
    @DisplayName("测试构建器复用")
    void testBuilderReuse() {
        // 创建第一个配置
        HttpClientConfig config1 = configBuilder
            .apiKey("key1")
            .apiSecret("secret1")
            .maxConnections(100)
            .build();
        
        // 修改builder并创建第二个配置
        HttpClientConfig config2 = configBuilder
            .apiKey("key2")
            .apiSecret("secret2")
            .maxConnections(200)
            .build();
        
        // 验证两个配置的独立性
        assertEquals("key1", config1.getApiKey(), "第一个配置不应该受影响");
        assertEquals(100, config1.getMaxConnections(), "第一个配置不应该受影响");
        
        assertEquals("key2", config2.getApiKey(), "第二个配置应该正确");
        assertEquals(200, config2.getMaxConnections(), "第二个配置应该正确");
    }
    
    /**
     * 性能测试：大量配置构建
     */
    @Test
    @DisplayName("性能测试 - 大量配置构建")
    void testBuildingPerformance() {
        int iterations = 1000;
        long startTime = System.nanoTime();
        
        for (int i = 0; i < iterations; i++) {
            HttpClientConfig config = new ConfigBuilder()
                .apiKey("key_" + i)
                .apiSecret("secret_" + i)
                .maxConnections(50 + i)
                .requestTimeout(Duration.ofSeconds(60 + i))
                .build();
            
            assertNotNull(config, "每次构建都应该成功");
        }
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double avgTimePerBuild = duration / (double) iterations;
        
        // 平均每次构建应该在50微秒以内
        assertTrue(avgTimePerBuild < 50000, // 50000纳秒 = 50微秒
            String.format("性能测试失败，平均耗时: %.2f纳秒", avgTimePerBuild));
        
        System.out.printf("ConfigBuilder性能测试 - %d次构建，平均耗时: %.2f纳秒%n", iterations, avgTimePerBuild);
    }
}