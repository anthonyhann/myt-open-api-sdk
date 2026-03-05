/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk;

import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.models.sender.api.IChannelSenderV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MytChannelSDK 工厂类测试
 * <p>
 * 测试SDK工厂类的Builder模式和配置管理功能，对应 Go SDK 中的 NewChannelSender 函数测试
 * </p>
 */
@DisplayName("MytChannelSDK工厂类测试")
class MytChannelSDKTest {

    private static final String TEST_BASE_URL = "https://open-api-test.maiyatian.com";
    private static final String TEST_API_KEY = "test_app_key";
    private static final String TEST_API_SECRET = "test_app_secret";

    // ==================== Builder 模式测试 ====================

    @Test
    @DisplayName("基础Builder配置测试")
    void testBasicBuilderConfiguration() {
        // When
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey(TEST_API_KEY)
                    .apiSecret(TEST_API_SECRET)
                    .build()) {
                
                // Then
                assertNotNull(sender);
            }
        });
    }

    @Test
    @DisplayName("高级Builder配置测试")
    void testAdvancedBuilderConfiguration() {
        // When
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey(TEST_API_KEY)
                    .apiSecret(TEST_API_SECRET)
                    .maxConnections(100)
                    .requestTimeout(Duration.ofSeconds(30))
                    .retryMaxAttempts(5)
                    .enableLogging(true)
                    .logRequestBody(false)
                    .logResponseBody(true)
                    .build()) {
                
                // Then
                assertNotNull(sender);
            }
        });
    }

    @Test
    @DisplayName("Builder链式调用测试")
    void testBuilderChaining() {
        // Given & When
        MytChannelSDK.SDKBuilder builder = MytChannelSDK.newBuilder();
        
        // 测试链式调用返回同一个builder实例
        MytChannelSDK.SDKBuilder result = builder
                .baseUrl(TEST_BASE_URL)
                .apiKey(TEST_API_KEY)
                .apiSecret(TEST_API_SECRET)
                .maxConnections(50)
                .requestTimeout(Duration.ofSeconds(25))
                .retryMaxAttempts(3)
                .enableLogging(false);

        // Then
        assertSame(builder, result, "Builder方法应该返回相同的实例以支持链式调用");
        
        // 验证可以正常构建
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = result.build()) {
                assertNotNull(sender);
            }
        });
    }

    @Test
    @DisplayName("Builder配置buildConfig测试")
    void testBuilderBuildConfig() {
        // Given
        Duration testTimeout = Duration.ofSeconds(45);
        int testMaxConnections = 80;
        int testRetryAttempts = 4;

        // When
        HttpClientConfig config = MytChannelSDK.newBuilder()
                .baseUrl(TEST_BASE_URL)
                .apiKey(TEST_API_KEY)
                .apiSecret(TEST_API_SECRET)
                .maxConnections(testMaxConnections)
                .requestTimeout(testTimeout)
                .retryMaxAttempts(testRetryAttempts)
                .enableLogging(true)
                .buildConfig();

        // Then
        assertNotNull(config);
        assertEquals(TEST_BASE_URL, config.getBaseUrl());
        assertEquals(TEST_API_KEY, config.getApiKey());
        assertEquals(TEST_API_SECRET, config.getApiSecret());
        assertEquals(testMaxConnections, config.getMaxConnections());
        assertEquals(testTimeout, config.getRequestTimeout());
        assertEquals(testRetryAttempts, config.getRetryMaxAttempts());
        assertTrue(config.isEnableLogging());
    }

    // ==================== 工厂方法测试 ====================

    @Test
    @DisplayName("createDefault工厂方法测试")
    void testCreateDefaultFactory() {
        // When & Then
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.createDefault()) {
                assertNotNull(sender);
            }
        });
    }

    @Test
    @DisplayName("create自定义配置工厂方法测试")
    void testCreateWithCustomConfig() {
        // Given
        HttpClientConfig config = HttpClientConfig.builder()
                .baseUrl(TEST_BASE_URL)
                .apiKey(TEST_API_KEY)
                .apiSecret(TEST_API_SECRET)
                .maxConnections(60)
                .requestTimeout(Duration.ofSeconds(20))
                .retryMaxAttempts(2)
                .enableLogging(false)
                .build();

        // When & Then
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.create(config)) {
                assertNotNull(sender);
            }
        });
    }

    @Test
    @DisplayName("create方法null配置处理测试")
    void testCreateWithNullConfig() {
        // When & Then
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.create(null)) {
                assertNotNull(sender);
            }
        });
    }

    // ==================== 错误处理测试 ====================

    @Test
    @DisplayName("Builder缺少必填字段测试")
    void testBuilderMissingRequiredFields() {
        // Given - 缺少apiKey和apiSecret
        MytChannelSDK.SDKBuilder builder = MytChannelSDK.newBuilder()
                .baseUrl(TEST_BASE_URL);

        // When & Then - 应该在build时抛出异常
        assertThrows(RuntimeException.class, builder::build);
    }

    @Test
    @DisplayName("Builder无效baseUrl测试")
    void testBuilderInvalidBaseUrl() {
        // Given - 无效的URL
        MytChannelSDK.SDKBuilder builder = MytChannelSDK.newBuilder()
                .baseUrl("invalid-url")
                .apiKey(TEST_API_KEY)
                .apiSecret(TEST_API_SECRET);

        // When & Then - 应该在build时抛出异常
        assertThrows(RuntimeException.class, builder::build);
    }

    @Test
    @DisplayName("Builder空字符串参数测试")
    void testBuilderEmptyStringParameters() {
        // Given - 空字符串参数
        MytChannelSDK.SDKBuilder builder = MytChannelSDK.newBuilder()
                .baseUrl("")
                .apiKey("")
                .apiSecret("");

        // When & Then - 应该在build时抛出异常
        assertThrows(RuntimeException.class, builder::build);
    }

    // ==================== 版本信息测试 ====================

    @Test
    @DisplayName("SDK版本号测试")
    void testSDKVersion() {
        // When
        String version = MytChannelSDK.VERSION;

        // Then
        assertNotNull(version, "SDK版本号不能为null");
        assertFalse(version.trim().isEmpty(), "SDK版本号不能为空");
        assertTrue(version.matches("\\d+\\.\\d+\\.\\d+(-.+)?"), 
                   "SDK版本号应该符合语义化版本格式");
    }

    // ==================== 资源管理测试 ====================

    @Test
    @DisplayName("try-with-resources资源管理测试")
    void testAutoCloseableResourceManagement() {
        // Given
        IChannelSenderV2[] senderRef = new IChannelSenderV2[1];

        // When & Then
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey(TEST_API_KEY)
                    .apiSecret(TEST_API_SECRET)
                    .build()) {
                
                senderRef[0] = sender;
                assertNotNull(sender);
            }
            // 在try-with-resources块结束后，sender应该已经被关闭
        });
        
        // 验证资源已被正确管理（虽然无法直接测试close是否被调用，但可以确保没有异常）
        assertNotNull(senderRef[0]);
    }

    @Test
    @DisplayName("多个SDK实例创建测试")
    void testMultipleSDKInstances() {
        // When & Then - 应该能够创建多个独立的SDK实例
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender1 = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey(TEST_API_KEY)
                    .apiSecret(TEST_API_SECRET)
                    .enableLogging(true)
                    .build();
                 
                 IChannelSenderV2 sender2 = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey("different_key")
                    .apiSecret("different_secret")
                    .enableLogging(false)
                    .build()) {
                
                assertNotNull(sender1);
                assertNotNull(sender2);
                assertNotSame(sender1, sender2, "不同的SDK实例应该是不同的对象");
            }
        });
    }

    // ==================== 配置验证测试 ====================

    @Test
    @DisplayName("配置参数边界值测试")
    void testConfigurationBoundaryValues() {
        // When & Then - 测试边界值不应该抛出异常
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey(TEST_API_KEY)
                    .apiSecret(TEST_API_SECRET)
                    .maxConnections(1)          // 最小值
                    .requestTimeout(Duration.ofSeconds(1))  // 最小超时
                    .retryMaxAttempts(0)        // 不重试
                    .build()) {
                
                assertNotNull(sender);
            }
        });
    }

    @Test
    @DisplayName("配置参数最大值测试")
    void testConfigurationMaximumValues() {
        // When & Then - 测试较大的配置值
        assertDoesNotThrow(() -> {
            try (IChannelSenderV2 sender = MytChannelSDK.newBuilder()
                    .baseUrl(TEST_BASE_URL)
                    .apiKey(TEST_API_KEY)
                    .apiSecret(TEST_API_SECRET)
                    .maxConnections(1000)       // 大连接数
                    .requestTimeout(Duration.ofMinutes(10))  // 长超时
                    .retryMaxAttempts(10)       // 多次重试
                    .build()) {
                
                assertNotNull(sender);
            }
        });
    }

    // ==================== 实例化防护测试 ====================

    @Test
    @DisplayName("工厂类实例化防护测试")
    void testFactoryClassInstantiationPrevention() {
        // When & Then - MytChannelSDK类应该无法被实例化
        assertThrows(UnsupportedOperationException.class, () -> {
            // 使用反射尝试实例化
            java.lang.reflect.Constructor<MytChannelSDK> constructor = 
                MytChannelSDK.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}