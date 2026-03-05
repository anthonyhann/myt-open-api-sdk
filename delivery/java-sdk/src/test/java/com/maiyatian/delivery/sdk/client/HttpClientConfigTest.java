/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 客户端配置测试
 * <p>
 * 测试客户端配置的验证和构建器模式
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class HttpClientConfigTest {
    
    @Test
    void testConfigValidation_success() {
        // 测试有效配置
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .build();
            
            // 验证配置
            config.validate();
            
            // 断言配置值正确
            assertEquals("https://open-api-test.maiyatian.com", config.getBaseUrl());
            assertEquals("test_api_key", config.getApiKey());
            assertEquals("test_api_secret", config.getApiSecret());
            assertEquals(Duration.ofSeconds(60), config.getRequestTimeout());
            assertEquals(3, config.getRetryMaxAttempts());
            assertTrue(config.isEnableLogging());
        } catch (ConfigValidationException e) {
            fail("配置验证应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testConfigValidation_failure() {
        // 测试无效配置 - 缺少baseUrl
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .build();
            fail("配置验证应该失败，但成功了");
        } catch (ConfigValidationException e) {
            assertEquals("BaseURL", e.getField());
            assertEquals("API 基础地址不能为空", e.getMessage());
        }
        
        // 测试无效配置 - 缺少apiKey
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiSecret("test_api_secret")
                    .build();
            fail("配置验证应该失败，但成功了");
        } catch (ConfigValidationException e) {
            assertEquals("APIKey", e.getField());
            assertEquals("应用密钥（app_key）不能为空", e.getMessage());
        }
        
        // 测试无效配置 - 缺少apiSecret
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiKey("test_api_key")
                    .build();
            fail("配置验证应该失败，但成功了");
        } catch (ConfigValidationException e) {
            assertEquals("APISecret", e.getField());
            assertEquals("应用密钥（app_secret）不能为空", e.getMessage());
        }
        
        // 测试无效配置 - 无效的maxConnections
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .maxConnections(0)
                    .build();
            fail("配置验证应该失败，但成功了");
        } catch (ConfigValidationException e) {
            assertEquals("MaxConnections", e.getField());
            assertEquals("最大连接数必须大于 0", e.getMessage());
        }
    }
    
    @Test
    void testConfigBuilder() {
        // 测试构建器模式
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api.maiyatian.com")
                    .apiKey("prod_api_key")
                    .apiSecret("prod_api_secret")
                    .maxConnections(100)
                    .maxConnectionsPerHost(20)
                    .requestTimeout(Duration.ofSeconds(30))
                    .retryMaxAttempts(5)
                    .enableLogging(false)
                    .logRequestBody(true)
                    .logResponseBody(true)
                    .build();
            
            // 断言配置值正确
            assertEquals("https://open-api.maiyatian.com", config.getBaseUrl());
            assertEquals("prod_api_key", config.getApiKey());
            assertEquals("prod_api_secret", config.getApiSecret());
            assertEquals(100, config.getMaxConnections());
            assertEquals(20, config.getMaxConnectionsPerHost());
            assertEquals(Duration.ofSeconds(30), config.getRequestTimeout());
            assertEquals(5, config.getRetryMaxAttempts());
            assertFalse(config.isEnableLogging());
            assertTrue(config.isLogRequestBody());
            assertTrue(config.isLogResponseBody());
        } catch (ConfigValidationException e) {
            fail("配置构建应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testDefaultConfig() {
        // 测试默认配置
        HttpClientConfig config = HttpClientConfig.createDefault();
        
        // 断言默认值正确
        assertEquals("1.0.0", config.getSdkVersion());
        assertEquals(50, config.getMaxConnections());
        assertEquals(10, config.getMaxConnectionsPerHost());
        assertEquals(Duration.ofSeconds(30), config.getKeepAliveTimeout());
        assertEquals(Duration.ofSeconds(60), config.getRequestTimeout());
        assertEquals(Duration.ofSeconds(30), config.getConnectionTimeout());
        assertEquals(Duration.ofSeconds(60), config.getReadTimeout());
        assertEquals(3, config.getRetryMaxAttempts());
        assertEquals(Duration.ofSeconds(1), config.getRetryBaseDelay());
        assertEquals(Duration.ofSeconds(30), config.getRetryMaxDelay());
        assertTrue(config.isEnableLogging());
        assertFalse(config.isLogRequestBody());
        assertFalse(config.isLogResponseBody());
    }
    
    @Test
    void testConfigEqualsAndHashCode() {
        // 测试配置的equals和hashCode方法
        try {
            HttpClientConfig config1 = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .build();
            
            HttpClientConfig config2 = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .build();
            
            HttpClientConfig config3 = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api.maiyatian.com")
                    .apiKey("different_api_key")
                    .apiSecret("different_api_secret")
                    .build();
            
            // 断言equals方法
            assertEquals(config1, config2);
            assertNotEquals(config1, config3);
            
            // 断言hashCode方法
            assertEquals(config1.hashCode(), config2.hashCode());
            assertNotEquals(config1.hashCode(), config3.hashCode());
        } catch (ConfigValidationException e) {
            fail("配置构建应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testConfigToString() {
        // 测试配置的toString方法
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://open-api-test.maiyatian.com")
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .build();
            
            String toString = config.toString();
            
            // 断言toString包含关键信息
            assertTrue(toString.contains("https://open-api-test.maiyatian.com"));
            assertTrue(toString.contains("test_api")); // apiKey前8个字符
            assertTrue(toString.contains("***")); // apiKey和apiSecret应该被隐藏
            assertFalse(toString.contains("test_api_secret")); // apiSecret不应该明文显示
        } catch (ConfigValidationException e) {
            fail("配置构建应该成功，但失败了: " + e.getMessage());
        }
    }
}