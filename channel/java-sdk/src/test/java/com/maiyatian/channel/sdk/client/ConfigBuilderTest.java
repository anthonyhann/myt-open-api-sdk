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
 * ConfigBuilder 配置构建器单元测试
 * 
 * 测试覆盖范围：
 * 1. Builder模式的链式调用
 * 2. 默认值设置和验证
 * 3. 构建验证逻辑
 * 4. 各种配置参数的设置
 * 5. 边界条件和错误处理
 */
@DisplayName("ConfigBuilder 配置构建器测试")
public class ConfigBuilderTest {

    @Nested
    @DisplayName("Builder模式链式调用测试")
    class ChainedCallTests {

        @Test
        @DisplayName("完整链式调用")
        void testFullChainedCall() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .sdkVersion("1.2.0")
                        .maxConnections(80)
                        .maxConnectionsPerHost(15)
                        .keepAliveTimeout(Duration.ofSeconds(45))
                        .requestTimeout(Duration.ofSeconds(90))
                        .connectionTimeout(Duration.ofSeconds(25))
                        .readTimeout(Duration.ofSeconds(75))
                        .retryMaxAttempts(4)
                        .retryBaseDelay(Duration.ofMillis(1500))
                        .retryMaxDelay(Duration.ofSeconds(45))
                        .enableLogging(true)
                        .logRequestBody(true)
                        .logResponseBody(false)
                        .build();

                // 验证所有配置都被正确设置
                assertEquals("https://api.maiyatian.com", config.getBaseUrl());
                assertEquals("test_key", config.getApiKey());
                assertEquals("test_secret", config.getApiSecret());
                assertEquals("1.2.0", config.getSdkVersion());
                assertEquals(80, config.getMaxConnections());
                assertEquals(15, config.getMaxConnectionsPerHost());
                assertEquals(Duration.ofSeconds(45), config.getKeepAliveTimeout());
                assertEquals(Duration.ofSeconds(90), config.getRequestTimeout());
                assertEquals(Duration.ofSeconds(25), config.getConnectionTimeout());
                assertEquals(Duration.ofSeconds(75), config.getReadTimeout());
                assertEquals(4, config.getRetryMaxAttempts());
                assertEquals(Duration.ofMillis(1500), config.getRetryBaseDelay());
                assertEquals(Duration.ofSeconds(45), config.getRetryMaxDelay());
                assertTrue(config.isEnableLogging());
                assertTrue(config.isLogRequestBody());
                assertFalse(config.isLogResponseBody());
            });
        }

        @Test
        @DisplayName("部分链式调用使用默认值")
        void testPartialChainedCallWithDefaults() {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://test-api.maiyatian.com")
                    .apiKey("minimal_key")
                    .apiSecret("minimal_secret")
                    .connectionTimeout(Duration.ofSeconds(15))
                    .enableLogging(false)
                    .build();

            // 验证设置的值
            assertEquals("https://test-api.maiyatian.com", config.getBaseUrl());
            assertEquals("minimal_key", config.getApiKey());
            assertEquals("minimal_secret", config.getApiSecret());
            assertEquals(Duration.ofSeconds(15), config.getConnectionTimeout());
            assertFalse(config.isEnableLogging());

            // 验证默认值
            assertEquals("1.0.0", config.getSdkVersion());
            assertEquals(50, config.getMaxConnections());
            assertEquals(10, config.getMaxConnectionsPerHost());
            assertEquals(3, config.getRetryMaxAttempts());
        }

        @Test
        @DisplayName("链式调用返回Builder实例")
        void testChainedCallReturnsSameBuilder() {
            ConfigBuilder builder = HttpClientConfig.newBuilder();
            
            // 验证每个方法都返回builder实例，支持链式调用
            assertSame(builder, builder.baseUrl("https://api.maiyatian.com"));
            assertSame(builder, builder.apiKey("test_key"));
            assertSame(builder, builder.apiSecret("test_secret"));
            assertSame(builder, builder.sdkVersion("1.0.0"));
            assertSame(builder, builder.maxConnections(100));
            assertSame(builder, builder.maxConnectionsPerHost(20));
            assertSame(builder, builder.keepAliveTimeout(Duration.ofSeconds(30)));
            assertSame(builder, builder.requestTimeout(Duration.ofSeconds(60)));
            assertSame(builder, builder.connectionTimeout(Duration.ofSeconds(30)));
            assertSame(builder, builder.readTimeout(Duration.ofSeconds(60)));
            assertSame(builder, builder.retryMaxAttempts(3));
            assertSame(builder, builder.retryBaseDelay(Duration.ofSeconds(1)));
            assertSame(builder, builder.retryMaxDelay(Duration.ofSeconds(30)));
            assertSame(builder, builder.enableLogging(true));
            assertSame(builder, builder.logRequestBody(false));
            assertSame(builder, builder.logResponseBody(false));
        }
    }

    @Nested
    @DisplayName("默认值测试")
    class DefaultValueTests {

        @Test
        @DisplayName("Builder默认值验证")
        void testBuilderDefaultValues() {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .build();

            // 验证默认值
            assertEquals("https://api.maiyatian.com", config.getBaseUrl()); // 默认值被覆盖
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
        @DisplayName("覆盖默认BaseURL")
        void testOverrideDefaultBaseUrl() {
            // 默认baseUrl应该是麦芽田的正式环境地址
            String customUrl = "https://custom-api.maiyatian.com";
            
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl(customUrl)
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .build();

            assertEquals(customUrl, config.getBaseUrl());
        }
    }

    @Nested
    @DisplayName("参数设置测试")
    class ParameterSettingTests {

        @Test
        @DisplayName("基础配置参数设置")
        void testBasicConfigurationParameters() {
            String baseUrl = "https://custom-api.maiyatian.com";
            String apiKey = "custom_api_key";
            String apiSecret = "custom_api_secret";
            String sdkVersion = "2.1.0";

            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl(baseUrl)
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    .sdkVersion(sdkVersion)
                    .build();

            assertEquals(baseUrl, config.getBaseUrl());
            assertEquals(apiKey, config.getApiKey());
            assertEquals(apiSecret, config.getApiSecret());
            assertEquals(sdkVersion, config.getSdkVersion());
        }

        @Test
        @DisplayName("连接池配置参数设置")
        void testConnectionPoolParameters() {
            int maxConnections = 100;
            int maxConnectionsPerHost = 25;
            Duration keepAliveTimeout = Duration.ofSeconds(60);

            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .maxConnections(maxConnections)
                    .maxConnectionsPerHost(maxConnectionsPerHost)
                    .keepAliveTimeout(keepAliveTimeout)
                    .build();

            assertEquals(maxConnections, config.getMaxConnections());
            assertEquals(maxConnectionsPerHost, config.getMaxConnectionsPerHost());
            assertEquals(keepAliveTimeout, config.getKeepAliveTimeout());
        }

        @Test
        @DisplayName("超时配置参数设置")
        void testTimeoutParameters() {
            Duration requestTimeout = Duration.ofSeconds(120);
            Duration connectionTimeout = Duration.ofSeconds(20);
            Duration readTimeout = Duration.ofSeconds(90);

            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .requestTimeout(requestTimeout)
                    .connectionTimeout(connectionTimeout)
                    .readTimeout(readTimeout)
                    .build();

            assertEquals(requestTimeout, config.getRequestTimeout());
            assertEquals(connectionTimeout, config.getConnectionTimeout());
            assertEquals(readTimeout, config.getReadTimeout());
        }

        @Test
        @DisplayName("重试配置参数设置")
        void testRetryParameters() {
            int retryMaxAttempts = 5;
            Duration retryBaseDelay = Duration.ofMillis(2000);
            Duration retryMaxDelay = Duration.ofSeconds(60);

            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .retryMaxAttempts(retryMaxAttempts)
                    .retryBaseDelay(retryBaseDelay)
                    .retryMaxDelay(retryMaxDelay)
                    .build();

            assertEquals(retryMaxAttempts, config.getRetryMaxAttempts());
            assertEquals(retryBaseDelay, config.getRetryBaseDelay());
            assertEquals(retryMaxDelay, config.getRetryMaxDelay());
        }

        @Test
        @DisplayName("日志配置参数设置")
        void testLoggingParameters() {
            HttpClientConfig config1 = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .enableLogging(true)
                    .logRequestBody(true)
                    .logResponseBody(true)
                    .build();

            assertTrue(config1.isEnableLogging());
            assertTrue(config1.isLogRequestBody());
            assertTrue(config1.isLogResponseBody());

            HttpClientConfig config2 = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .enableLogging(false)
                    .logRequestBody(false)
                    .logResponseBody(false)
                    .build();

            assertFalse(config2.isEnableLogging());
            assertFalse(config2.isLogRequestBody());
            assertFalse(config2.isLogResponseBody());
        }
    }

    @Nested
    @DisplayName("构建验证测试")
    class BuildValidationTests {

        @Test
        @DisplayName("缺少必填字段BaseURL - 设置为null")
        void testMissingRequiredBaseUrl() {
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl(null)  // 明确设置为null
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
            });
        }

        @Test
        @DisplayName("缺少必填字段APIKey")
        void testMissingRequiredApiKey() {
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiSecret("test_secret")
                        .build();
            });
        }

        @Test
        @DisplayName("缺少必填字段APISecret")
        void testMissingRequiredApiSecret() {
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .build();
            });
        }

        @Test
        @DisplayName("空字符串验证失败")
        void testEmptyStringValidationFailure() {
            // 空BaseURL
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
            });

            // 空APIKey
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("")
                        .apiSecret("test_secret")
                        .build();
            });

            // 空APISecret
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("")
                        .build();
            });
        }

        @Test
        @DisplayName("Null值验证失败")
        void testNullValueValidationFailure() {
            // Null BaseURL
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl(null)
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
            });

            // Null APIKey
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey(null)
                        .apiSecret("test_secret")
                        .build();
            });

            // Null APISecret
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret(null)
                        .build();
            });
        }

        @Test
        @DisplayName("只包含空白字符的字段验证失败")
        void testWhitespaceOnlyFieldsValidationFailure() {
            // 只有空格的BaseURL
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("   ")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .build();
            });

            // 只有制表符的APIKey
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("\t\t")
                        .apiSecret("test_secret")
                        .build();
            });

            // 只有换行符的APISecret
            assertThrows(IllegalArgumentException.class, () -> {
                HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("\n\r")
                        .build();
            });
        }
    }

    @Nested
    @DisplayName("边界条件和极端情况测试")
    class EdgeCasesTests {

        @Test
        @DisplayName("极端超时值设置")
        void testExtremeTimeoutValues() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .connectionTimeout(Duration.ofNanos(1)) // 极小值
                        .readTimeout(Duration.ofHours(24)) // 极大值
                        .retryBaseDelay(Duration.ZERO) // 零值
                        .retryMaxDelay(Duration.ofDays(1)) // 非常大的值
                        .build();

                assertNotNull(config);
                assertEquals(Duration.ofNanos(1), config.getConnectionTimeout());
                assertEquals(Duration.ofHours(24), config.getReadTimeout());
                assertEquals(Duration.ZERO, config.getRetryBaseDelay());
                assertEquals(Duration.ofDays(1), config.getRetryMaxDelay());
            });
        }

        @Test
        @DisplayName("极端连接数设置")
        void testExtremeConnectionValues() {
            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .maxConnections(1) // 最小值
                        .maxConnectionsPerHost(1) // 最小值
                        .retryMaxAttempts(0) // 最小值
                        .build();

                assertNotNull(config);
                assertEquals(1, config.getMaxConnections());
                assertEquals(1, config.getMaxConnectionsPerHost());
                assertEquals(0, config.getRetryMaxAttempts());
            });

            assertDoesNotThrow(() -> {
                HttpClientConfig config = HttpClientConfig.newBuilder()
                        .baseUrl("https://api.maiyatian.com")
                        .apiKey("test_key")
                        .apiSecret("test_secret")
                        .maxConnections(10000) // 很大的值
                        .maxConnectionsPerHost(1000) // 很大的值
                        .retryMaxAttempts(100) // 很大的值
                        .build();

                assertNotNull(config);
                assertEquals(10000, config.getMaxConnections());
                assertEquals(1000, config.getMaxConnectionsPerHost());
                assertEquals(100, config.getRetryMaxAttempts());
            });
        }

        @Test
        @DisplayName("特殊URL格式处理")
        void testSpecialUrlFormats() {
            String[] specialUrls = {
                "https://api.maiyatian.com",
                "https://api.maiyatian.com/",
                "https://api.maiyatian.com:443",
                "https://api.maiyatian.com:8080/v1",
                "https://sub.api.maiyatian.com",
                "https://api-test.maiyatian.com"
            };

            for (String url : specialUrls) {
                assertDoesNotThrow(() -> {
                    HttpClientConfig config = HttpClientConfig.newBuilder()
                            .baseUrl(url)
                            .apiKey("test_key")
                            .apiSecret("test_secret")
                            .build();

                    assertEquals(url, config.getBaseUrl());
                }, "URL: " + url + " should be valid");
            }
        }

        @Test
        @DisplayName("重复构建测试")
        void testReusableBuilder() {
            ConfigBuilder builder = HttpClientConfig.newBuilder()
                    .baseUrl("https://api.maiyatian.com")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .connectionTimeout(Duration.ofSeconds(30));

            // 第一次构建
            HttpClientConfig config1 = builder.build();
            assertNotNull(config1);
            assertEquals(Duration.ofSeconds(30), config1.getConnectionTimeout());

            // 修改配置后再次构建
            HttpClientConfig config2 = builder.connectionTimeout(Duration.ofSeconds(60)).build();
            assertNotNull(config2);
            assertEquals(Duration.ofSeconds(60), config2.getConnectionTimeout());

            // 验证两个配置对象是独立的
            assertNotSame(config1, config2);
            assertEquals(Duration.ofSeconds(30), config1.getConnectionTimeout());
            assertEquals(Duration.ofSeconds(60), config2.getConnectionTimeout());
        }
    }
}