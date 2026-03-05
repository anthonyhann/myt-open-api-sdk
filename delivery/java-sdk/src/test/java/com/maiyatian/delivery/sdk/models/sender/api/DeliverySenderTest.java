/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.api;

import com.maiyatian.delivery.sdk.client.ConfigValidationException;
import com.maiyatian.delivery.sdk.client.HttpClientConfig;
import com.maiyatian.delivery.sdk.models.types.Constants;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 配送发送者测试
 * <p>
 * 测试配送发送者的功能
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class DeliverySenderTest {
    
    private IDeliverySender sender;
    
    @BeforeEach
    void setUp() throws ConfigValidationException {
        // 初始化配送发送者实例
        HttpClientConfig config = HttpClientConfig.newBuilder()
                .baseUrl(Constants.TEST_BASE_URL)
                .apiKey("test_api_key")
                .apiSecret("test_api_secret")
                .enableLogging(false)
                .build();
        
        sender = new DeliverySender(config);
    }
    
    @AfterEach
    void tearDown() {
        // 关闭配送发送者实例
        if (sender != null) {
            try {
                sender.close();
            } catch (Exception e) {
                // 忽略关闭异常
            }
        }
    }
    
    @Test
    void testDeliverySenderCreation() {
        // 测试配送发送者创建
        assertNotNull(sender);
    }
    
    @Test
    void testDeliverySenderClose() {
        // 测试配送发送者关闭
        assertDoesNotThrow(() -> {
            sender.close();
        });
    }
    
    @Test
    void testDeliverySenderConfigValidation() {
        // 测试配送发送者配置验证
        assertThrows(ConfigValidationException.class, () -> {
            HttpClientConfig invalidConfig = HttpClientConfig.newBuilder()
                    .baseUrl("") // 无效的baseUrl
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .build();
            new DeliverySender(invalidConfig);
        });
    }
    
    @Test
    void testDeliverySenderWithConfigBuilder() {
        // 测试通过配置构建器创建配送发送者
        assertDoesNotThrow(() -> {
            new DeliverySender(HttpClientConfig.newBuilder()
                    .baseUrl(Constants.TEST_BASE_URL)
                    .apiKey("test_api_key")
                    .apiSecret("test_api_secret")
                    .enableLogging(false));
        });
    }
    
    @Nested
    @DisplayName("授权管理API测试")
    class AuthenticationApiTests {
        
        @Test
        @DisplayName("获取访问令牌 - 空请求参数")
        void testAccessTokenWithNullRequest() {
            assertThrows(Exception.class, () -> {
                sender.accessToken("token", null);
            }, "应该抛出异常，因为请求参数为null");
        }
        
        @Test
        @DisplayName("获取访问令牌 - 带自定义headers")
        void testAccessTokenWithCustomHeaders() {
            AccessTokenEntity.Req request = new AccessTokenEntity.Req();
            Map<String, String> headers = new HashMap<>();
            headers.put("Custom-Header", "test-value");
            
            assertThrows(Exception.class, () -> {
                sender.accessToken("", request, headers);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
        
        @Test
        @DisplayName("获取访问令牌 - 简化版本")
        void testAccessTokenSimple() {
            AccessTokenEntity.Req request = new AccessTokenEntity.Req();
            
            assertThrows(Exception.class, () -> {
                sender.accessToken("", request);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
        
        @Test
        @DisplayName("刷新访问令牌 - 空请求参数")
        void testRefreshTokenWithNullRequest() {
            assertThrows(Exception.class, () -> {
                sender.refreshToken("token", null);
            }, "应该抛出异常，因为请求参数为null");
        }
        
        @Test
        @DisplayName("刷新访问令牌 - 带自定义headers")
        void testRefreshTokenWithCustomHeaders() {
            RefreshTokenEntity.Req request = new RefreshTokenEntity.Req();
            Map<String, String> headers = new HashMap<>();
            headers.put("Custom-Header", "test-value");
            
            assertThrows(Exception.class, () -> {
                sender.refreshToken("token", request, headers);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
        
        @Test
        @DisplayName("刷新访问令牌 - 简化版本")
        void testRefreshTokenSimple() {
            RefreshTokenEntity.Req request = new RefreshTokenEntity.Req();
            
            assertThrows(Exception.class, () -> {
                sender.refreshToken("token", request);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
    }
    
    @Nested
    @DisplayName("配送变更API测试")
    class DeliveryChangeApiTests {
        
        @Test
        @DisplayName("配送变更 - 空请求参数")
        void testDeliveryChangeWithNullRequest() {
            assertThrows(Exception.class, () -> {
                sender.deliveryChange("token", null);
            }, "应该抛出异常，因为请求参数为null");
        }
        
        @Test
        @DisplayName("配送变更 - 带自定义headers")
        void testDeliveryChangeWithCustomHeaders() {
            DeliveryChangeEntity.Req request = new DeliveryChangeEntity.Req();
            Map<String, String> headers = new HashMap<>();
            headers.put("Custom-Header", "test-value");
            
            assertThrows(Exception.class, () -> {
                sender.deliveryChange("token", request, headers);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
        
        @Test
        @DisplayName("配送变更 - 简化版本")
        void testDeliveryChangeSimple() {
            DeliveryChangeEntity.Req request = new DeliveryChangeEntity.Req();
            
            assertThrows(Exception.class, () -> {
                sender.deliveryChange("token", request);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
    }
    
    @Nested
    @DisplayName("位置变更API测试")
    class LocationChangeApiTests {
        
        @Test
        @DisplayName("位置变更 - 空请求参数")
        void testLocationChangeWithNullRequest() {
            assertThrows(Exception.class, () -> {
                sender.locationChange("token", null);
            }, "应该抛出异常，因为请求参数为null");
        }
        
        @Test
        @DisplayName("位置变更 - 带自定义headers")
        void testLocationChangeWithCustomHeaders() {
            LocationChangeEntity.Req request = new LocationChangeEntity.Req();
            Map<String, String> headers = new HashMap<>();
            headers.put("Custom-Header", "test-value");
            
            assertThrows(Exception.class, () -> {
                sender.locationChange("token", request, headers);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
        
        @Test
        @DisplayName("位置变更 - 简化版本")
        void testLocationChangeSimple() {
            LocationChangeEntity.Req request = new LocationChangeEntity.Req();
            
            assertThrows(Exception.class, () -> {
                sender.locationChange("token", request);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
    }
    
    @Nested
    @DisplayName("工具方法测试")
    class UtilityMethodTests {
        
        @Test
        @DisplayName("原始请求方法")
        void testRawRequest() {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            
            assertThrows(Exception.class, () -> {
                sender.request("POST", "/test", "{}", "token", headers);
            }, "应该抛出网络异常，因为没有真实的HTTP服务器");
        }
        
        @Test
        @DisplayName("资源清理测试")
        void testResourceCleanup() {
            assertDoesNotThrow(() -> {
                sender.close();
                // 再次调用close应该是安全的
                sender.close();
            });
        }
    }
}
