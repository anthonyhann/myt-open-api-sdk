/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.client;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HttpClientConfig 配置类单元测试
 * 
 * 测试覆盖范围：
 * 1. 配置构建和验证
 * 2. Builder模式功能
 * 3. 默认值和边界条件
 * 4. 配置验证逻辑
 * 5. equals/hashCode/toString方法
 * 6. 静态工厂方法
 */
@DisplayName("HttpClientConfig 配置测试")
public class HttpClientConfigTest {

    @Nested
    @DisplayName("配置构建测试")
    class ConfigurationBuildingTests {

        @Test
        @DisplayName("Builder模式正常构建")
        void testBuilderPatternSuccess() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .connectionTimeout(Duration.ofSeconds(30))
                        .readTimeout(Duration.ofSeconds(60))
                        .retryMaxAttempts(3)
                        .enableLogging(true)
                        .build();

                assertNotNull(config);
                assertEquals("https://api.maiyatian.com", config.getBaseUrl());
                assertEquals("test_key", config.getApiKey());
                assertEquals("test_secret", config.getApiSecret());
                assertEquals(Duration.ofSeconds(30), config.getConnectionTimeout());
                assertEquals(Duration.ofSeconds(60), config.getReadTimeout());
                assertEquals(3, config.getRetryMaxAttempts());
                assertTrue(config.isEnableLogging());
            });
        }

        @Test
        @DisplayName("默认配置创建")
        void testCreateDefaultConfig() {
            HttpClientConfig config = HttpClientConfig.createDefault();
            
            assertNotNull(config);
            // 验证默认值
            assertNotNull(config.getConnectionTimeout());
            assertNotNull(config.getReadTimeout());
            assertNotNull(config.getRequestTimeout());
            assertTrue(config.getRetryMaxAttempts() >= 0);
        }

        @Test
        @DisplayName("最小配置构建")
        void testMinimalConfigBuild() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();

                assertNotNull(config);
                // 验证默认值被正确应用
                assertNotNull(config.getConnectionTimeout());
                assertNotNull(config.getReadTimeout());
                assertEquals(3, config.getRetryMaxAttempts());
            });
        }

        @Test
        @DisplayName("完整配置构建")
        void testFullConfigBuild() {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .sdkVersion("1.1.0")
                    .maxConnections(100)
                    .maxConnectionsPerHost(20)
                    .keepAliveTimeout(Duration.ofSeconds(45))
                    .requestTimeout(Duration.ofSeconds(90))
                    .connectionTimeout(Duration.ofSeconds(20))
                    .readTimeout(Duration.ofSeconds(120))
                    .retryMaxAttempts(5)
                    .retryBaseDelay(Duration.ofSeconds(2))
                    .retryMaxDelay(Duration.ofSeconds(60))
                    .enableLogging(true)
                    .logRequestBody(true)
                    .logResponseBody(false)
                    .build();

            // 验证所有配置项
            assertEquals("https://api.maiyatian.com", config.getBaseUrl());
            assertEquals("test_key", config.getApiKey());
            assertEquals("test_secret", config.getApiSecret());
            assertEquals("1.1.0", config.getSdkVersion());
            assertEquals(100, config.getMaxConnections());
            assertEquals(20, config.getMaxConnectionsPerHost());
            assertEquals(Duration.ofSeconds(45), config.getKeepAliveTimeout());
            assertEquals(Duration.ofSeconds(90), config.getRequestTimeout());
            assertEquals(Duration.ofSeconds(20), config.getConnectionTimeout());
            assertEquals(Duration.ofSeconds(120), config.getReadTimeout());
            assertEquals(5, config.getRetryMaxAttempts());
            assertEquals(Duration.ofSeconds(2), config.getRetryBaseDelay());
            assertEquals(Duration.ofSeconds(60), config.getRetryMaxDelay());
            assertTrue(config.isEnableLogging());
            assertTrue(config.isLogRequestBody());
            assertFalse(config.isLogResponseBody());
        }
    }

    @Nested
    @DisplayName("配置验证测试")
    class ConfigurationValidationTests {

        @Test
        @DisplayName("缺失BaseURL验证失败")
        void testMissingBaseUrlValidationFailure() {
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("") // 空URL
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
            });
        }

        @Test
        @DisplayName("缺失APIKey验证失败")
        void testMissingApiKeyValidationFailure() {
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("") // 空Key
                        .apiSecret("test_secret")
                        .build();
            });
        }

        @Test
        @DisplayName("缺失APISecret验证失败")
        void testMissingApiSecretValidationFailure() {
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("") // 空Secret
                        .build();
            });
        }

        @Test
        @DisplayName("配置validate方法测试")
        void testConfigValidateMethod() {
            HttpClientConfig validConfig = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .build();

            assertDoesNotThrow(() -> {
                validConfig.validate();
            });

            // 测试无效配置
            HttpClientConfig invalidConfig = new HttpClientConfig();
            assertThrows(ConfigValidationException.class, () -> {
                invalidConfig.validate();
            });
        }

        @Test
        @DisplayName("边界值验证")
        void testBoundaryValues() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .maxConnections(1) // 最小值
                        .retryMaxAttempts(0) // 最小值
                        .connectionTimeout(Duration.ofMillis(1)) // 最小值
                        .build();

                config.validate();
            });
        }

        @Test
        @DisplayName("无效边界值验证失败")
        void testInvalidBoundaryValues() {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .maxConnections(-1) // 无效值
                    .build();

            assertThrows(ConfigValidationException.class, () -> {
                config.validate();
            });
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals和hashCode方法")
        void testEqualsAndHashCode() {
            HttpClientConfig config1 = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .connectionTimeout(Duration.ofSeconds(30))
                    .build();

            HttpClientConfig config2 = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .connectionTimeout(Duration.ofSeconds(30))
                    .build();

            HttpClientConfig config3 = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("different_key")
                    .apiSecret("test_secret")
                    .connectionTimeout(Duration.ofSeconds(30))
                    .build();

            // 测试equals
            assertEquals(config1, config2);
            assertNotEquals(config1, config3);
            assertNotEquals(config1, null);
            assertEquals(config1, config1); // 自反性

            // 测试hashCode
            assertEquals(config1.hashCode(), config2.hashCode());
            // 不同配置的hashCode通常不同（但不是严格要求）
        }

        @Test
        @DisplayName("toString方法安全性")
        void testToStringSecurityMasking() {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("very_long_api_key_for_testing")
                    .apiSecret("super_secret_value")
                    .build();

            String str = config.toString();
            
            // 验证敏感信息被遮掩
            assertFalse(str.contains("super_secret_value"));
            assertTrue(str.contains("***")); // Secret应该被遮掩
            
            // APIKey应该只显示部分
            assertFalse(str.contains("very_long_api_key_for_testing"));
            assertTrue(str.contains("very_lon")); // 显示前8位
        }
    }

    @Nested
    @DisplayName("Getter和Setter测试")
    class GetterSetterTests {

        @Test
        @DisplayName("所有Getter方法")
        void testAllGetterMethods() {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .sdkVersion("1.0.0")
                    .maxConnections(50)
                    .maxConnectionsPerHost(10)
                    .keepAliveTimeout(Duration.ofSeconds(30))
                    .requestTimeout(Duration.ofSeconds(60))
                    .connectionTimeout(Duration.ofSeconds(30))
                    .readTimeout(Duration.ofSeconds(60))
                    .retryMaxAttempts(3)
                    .retryBaseDelay(Duration.ofSeconds(1))
                    .retryMaxDelay(Duration.ofSeconds(30))
                    .enableLogging(true)
                    .logRequestBody(false)
                    .logResponseBody(false)
                    .build();

            // 测试所有getter方法
            assertEquals("https://api.maiyatian.com", config.getBaseUrl());
            assertEquals("test_key", config.getApiKey());
            assertEquals("test_secret", config.getApiSecret());
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
        @DisplayName("Setter方法测试")
        void testSetterMethods() {
            HttpClientConfig config = new HttpClientConfig();
            
            config.setBaseUrl("https://test.maiyatian.com");
            config.setApiKey("new_key");
            config.setApiSecret("new_secret");
            config.setSdkVersion("2.0.0");
            config.setMaxConnections(100);
            config.setMaxConnectionsPerHost(20);
            config.setKeepAliveTimeout(Duration.ofSeconds(60));
            config.setRequestTimeout(Duration.ofSeconds(120));
            config.setConnectionTimeout(Duration.ofSeconds(45));
            config.setReadTimeout(Duration.ofSeconds(90));
            config.setRetryMaxAttempts(5);
            config.setRetryBaseDelay(Duration.ofSeconds(2));
            config.setRetryMaxDelay(Duration.ofSeconds(60));
            config.setEnableLogging(false);
            config.setLogRequestBody(true);
            config.setLogResponseBody(true);

            // 验证setter效果
            assertEquals("https://test.maiyatian.com", config.getBaseUrl());
            assertEquals("new_key", config.getApiKey());
            assertEquals("new_secret", config.getApiSecret());
            assertEquals("2.0.0", config.getSdkVersion());
            assertEquals(100, config.getMaxConnections());
            assertEquals(20, config.getMaxConnectionsPerHost());
            assertEquals(Duration.ofSeconds(60), config.getKeepAliveTimeout());
            assertEquals(Duration.ofSeconds(120), config.getRequestTimeout());
            assertEquals(Duration.ofSeconds(45), config.getConnectionTimeout());
            assertEquals(Duration.ofSeconds(90), config.getReadTimeout());
            assertEquals(5, config.getRetryMaxAttempts());
            assertEquals(Duration.ofSeconds(2), config.getRetryBaseDelay());
            assertEquals(Duration.ofSeconds(60), config.getRetryMaxDelay());
            assertFalse(config.isEnableLogging());
            assertTrue(config.isLogRequestBody());
            assertTrue(config.isLogResponseBody());
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("newBuilder静态方法")
        void testNewBuilderStaticMethod() {
            ConfigBuilder builder = HttpClientConfig.newBuilder();
            assertNotNull(builder);
            
            // 验证builder可以正常构建配置
            assertDoesNotThrow(() -> {
                HttpClientConfig config = builder
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
                assertNotNull(config);
            });
        }

        @Test
        @DisplayName("createDefault静态方法")
        void testCreateDefaultStaticMethod() {
            HttpClientConfig config = HttpClientConfig.createDefault();
            assertNotNull(config);
            
            // 验证默认配置包含合理的值
            assertNotNull(config.getConnectionTimeout());
            assertNotNull(config.getReadTimeout());
            assertNotNull(config.getRequestTimeout());
            assertNotNull(config.getKeepAliveTimeout());
            assertNotNull(config.getRetryBaseDelay());
            assertNotNull(config.getRetryMaxDelay());
            assertTrue(config.getMaxConnections() > 0);
            assertTrue(config.getMaxConnectionsPerHost() > 0);
            assertTrue(config.getRetryMaxAttempts() >= 0);
        }
    }

    @Nested
    @DisplayName("边界条件和极端情况测试")
    class EdgeCasesTests {

        @Test
        @DisplayName("空字符串和null值处理")
        void testNullAndEmptyStringHandling() {
            ConfigBuilder builder = HttpClientConfig.newBuilder();
            
            // 测试空字符串
            assertThrows(IllegalArgumentException.class, () -> {
                builder.baseUrl("").apiKey("key").apiSecret("secret").build();
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                builder.baseUrl("url").apiKey("").apiSecret("secret").build();
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                builder.baseUrl("url").apiKey("key").apiSecret("").build();
            });
        }

        @Test
        @DisplayName("极端超时值处理")
        void testExtremeTimeoutValues() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .connectionTimeout(Duration.ofNanos(1)) // 极小值
                        .readTimeout(Duration.ofDays(1)) // 极大值
                        .build();
                
                assertNotNull(config);
            });
        }

        @Test
        @DisplayName("特殊字符URL处理")
        void testSpecialCharacterUrls() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api-test.maiyatian.com:8080/v1")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
                
                assertEquals("https://api-test.maiyatian.com:8080/v1", config.getBaseUrl());
            });
        }
    }
}