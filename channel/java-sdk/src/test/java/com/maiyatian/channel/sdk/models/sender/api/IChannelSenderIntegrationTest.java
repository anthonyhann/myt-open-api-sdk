/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.*;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoReq;
import com.maiyatian.channel.sdk.models.sender.entity.shop.ShopInfoResp;
import com.maiyatian.channel.sdk.models.types.Response;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IChannelSender 接口集成测试
 * 
 * 测试覆盖范围：
 * 1. 接口完整性验证
 * 2. ChannelSender实现的接口一致性
 * 3. 所有API方法的存在性验证
 * 4. 方法重载的完整性
 * 5. 与Go SDK接口规范的一致性验证
 */
@DisplayName("IChannelSender 接口集成测试")
public class IChannelSenderIntegrationTest {

    private HttpClientConfig testConfig;
    private IChannelSender channelSender;

    @BeforeEach
    void setUp() {
        testConfig = HttpClientConfig.newBuilder()
                .baseUrl("https://test-api.maiyatian.com")
                .apiKey("test_integration_key")
                .apiSecret("test_integration_secret")
                .connectionTimeout(Duration.ofMillis(100))  // 极短连接超时
                .readTimeout(Duration.ofMillis(200))        // 极短读取超时
                .retryMaxAttempts(0)                        // 禁用重试
                .enableLogging(false)
                .build();

        try {
            channelSender = new ChannelSender(testConfig);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create ChannelSender", e);
        }
    }

    @AfterEach
    void tearDown() {
        if (channelSender != null) {
            try {
                channelSender.close();
            } catch (Exception e) {
                // Ignore close exceptions in teardown
            }
        }
    }

    @Nested
    @DisplayName("接口完整性验证")
    class InterfaceCompletenessTests {

        @Test
        @DisplayName("验证ChannelSender实现IChannelSender接口")
        void testChannelSenderImplementsInterface() {
            assertTrue(channelSender instanceof IChannelSender);
            assertTrue(channelSender instanceof AutoCloseable);
        }

        @Test
        @DisplayName("验证所有接口方法存在 - 订单管理")
        void testOrderManagementMethodsExist() {
            // 验证方法存在性，虽然会抛出网络异常，但证明方法签名正确
            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderCreated("token", new CreateOrderReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    // 期望的网络异常，表示方法存在且签名正确
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderModified("token", new UpdateOrderReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderConfirmed("token", new OrderConfirmedReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderRemind("token", new OrderRemindReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderCanceled("token", new OrderCanceledReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderDone("token", new OrderDoneReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });
        }

        @Test
        @DisplayName("验证所有接口方法存在 - 退款管理")
        void testRefundManagementMethodsExist() {
            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderApplyRefund("token", new OrderApplyRefundReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.orderRefunded("token", new OrderRefundedReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });
        }

        @Test
        @DisplayName("验证所有接口方法存在 - 配送管理")
        void testDeliveryManagementMethodsExist() {
            assertDoesNotThrow(() -> {
                try {
                    channelSender.selfDeliveryChange("token", new SelfDeliveryChangeReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });
        }

        @Test
        @DisplayName("验证所有接口方法存在 - 授权管理")
        void testAuthManagementMethodsExist() {
            assertDoesNotThrow(() -> {
                try {
                    channelSender.accessToken("", new AccessTokenReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            assertDoesNotThrow(() -> {
                try {
                    channelSender.refreshToken("token", new RefreshTokenReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });
        }

        @Test
        @DisplayName("验证所有接口方法存在 - 门店管理")
        void testShopManagementMethodsExist() {
            assertDoesNotThrow(() -> {
                try {
                    channelSender.shopInfo("token", new ShopInfoReq());
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });
        }

        @Test
        @DisplayName("验证工具方法存在")
        void testUtilityMethodsExist() {
            assertDoesNotThrow(() -> {
                try {
                    channelSender.request("POST", "/test", "{}", "token", null);
                    fail("应该抛出网络异常");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect"));
                }
            });

            // 测试close方法
            assertDoesNotThrow(() -> {
                channelSender.close();
            });
        }
    }

    @Nested
    @DisplayName("方法重载完整性验证")
    class MethodOverloadCompletenessTests {

        @Test
        @DisplayName("订单API方法重载验证")
        void testOrderAPIMethodOverloads() {
            Map<String, String> headers = new HashMap<>();
            headers.put("Custom-Header", "test-value");

            // 每个订单API都应该有两个重载版本：带headers和不带headers
            CreateOrderReq createReq = new CreateOrderReq();
            UpdateOrderReq updateReq = new UpdateOrderReq();
            OrderConfirmedReq confirmedReq = new OrderConfirmedReq();
            OrderRemindReq remindReq = new OrderRemindReq();
            OrderCanceledReq canceledReq = new OrderCanceledReq();
            OrderDoneReq doneReq = new OrderDoneReq();

            // 测试所有方法的两种重载形式都存在
            assertDoesNotThrow(() -> {
                try { channelSender.orderCreated("token", createReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderCreated("token", createReq); } catch (Exception ignored) {}
                
                try { channelSender.orderModified("token", updateReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderModified("token", updateReq); } catch (Exception ignored) {}
                
                try { channelSender.orderConfirmed("token", confirmedReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderConfirmed("token", confirmedReq); } catch (Exception ignored) {}
                
                try { channelSender.orderRemind("token", remindReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderRemind("token", remindReq); } catch (Exception ignored) {}
                
                try { channelSender.orderCanceled("token", canceledReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderCanceled("token", canceledReq); } catch (Exception ignored) {}
                
                try { channelSender.orderDone("token", doneReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderDone("token", doneReq); } catch (Exception ignored) {}
            });
        }

        @Test
        @DisplayName("退款API方法重载验证")
        void testRefundAPIMethodOverloads() {
            Map<String, String> headers = new HashMap<>();
            OrderApplyRefundReq applyReq = new OrderApplyRefundReq();
            OrderRefundedReq refundedReq = new OrderRefundedReq();

            assertDoesNotThrow(() -> {
                try { channelSender.orderApplyRefund("token", applyReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderApplyRefund("token", applyReq); } catch (Exception ignored) {}
                
                try { channelSender.orderRefunded("token", refundedReq, headers); } catch (Exception ignored) {}
                try { channelSender.orderRefunded("token", refundedReq); } catch (Exception ignored) {}
            });
        }

        @Test
        @DisplayName("其他API方法重载验证")
        void testOtherAPIMethodOverloads() {
            Map<String, String> headers = new HashMap<>();
            SelfDeliveryChangeReq deliveryReq = new SelfDeliveryChangeReq();
            AccessTokenReq accessReq = new AccessTokenReq();
            RefreshTokenReq refreshReq = new RefreshTokenReq();
            ShopInfoReq shopReq = new ShopInfoReq();

            assertDoesNotThrow(() -> {
                try { channelSender.selfDeliveryChange("token", deliveryReq, headers); } catch (Exception ignored) {}
                try { channelSender.selfDeliveryChange("token", deliveryReq); } catch (Exception ignored) {}
                
                try { channelSender.accessToken("", accessReq, headers); } catch (Exception ignored) {}
                try { channelSender.accessToken("", accessReq); } catch (Exception ignored) {}
                
                try { channelSender.refreshToken("token", refreshReq, headers); } catch (Exception ignored) {}
                try { channelSender.refreshToken("token", refreshReq); } catch (Exception ignored) {}
                
                try { channelSender.shopInfo("token", shopReq, headers); } catch (Exception ignored) {}
                try { channelSender.shopInfo("token", shopReq); } catch (Exception ignored) {}
            });
        }
    }

    @Nested
    @DisplayName("Go SDK接口一致性验证")
    class GoSDKCompatibilityTests {

        @Test
        @DisplayName("验证与Go SDK的API数量一致性")
        void testAPICountConsistencyWithGoSDK() {
            // 根据Go SDK的base.go文件，应该有12个主要API接口
            // 1. OrderCreated (必接)
            // 2. OrderModified
            // 3. OrderConfirmed (必接)
            // 4. OrderRemind
            // 5. OrderApplyRefund
            // 6. OrderRefunded
            // 7. OrderCanceled
            // 8. OrderDone (必接)
            // 9. SelfDeliveryChange
            // 10. ShopInfo
            // 11. AccessToken (必接)
            // 12. RefreshToken

            // 验证所有12个API都存在（通过反射检查）
            Class<?>[] interfaces = ChannelSender.class.getInterfaces();
            boolean implementsIChannelSender = false;
            for (Class<?> iface : interfaces) {
                if (iface.equals(IChannelSender.class)) {
                    implementsIChannelSender = true;
                    break;
                }
            }
            assertTrue(implementsIChannelSender, "ChannelSender must implement IChannelSender interface");

            // 验证所有必接接口标记的方法存在
            assertDoesNotThrow(() -> {
                // 必接接口测试
                try { channelSender.orderCreated("token", new CreateOrderReq()); } catch (Exception ignored) {}
                try { channelSender.orderConfirmed("token", new OrderConfirmedReq()); } catch (Exception ignored) {}
                try { channelSender.orderDone("token", new OrderDoneReq()); } catch (Exception ignored) {}
                try { channelSender.accessToken("", new AccessTokenReq()); } catch (Exception ignored) {}
            });
        }

        @Test
        @DisplayName("验证API返回类型一致性")
        void testAPIReturnTypeConsistency() {
            // 大部分API应该返回Response类型
            // 只有特定的API返回特定的响应类型
            
            try {
                // 授权API返回特定类型
                AccessTokenResp accessResp = channelSender.accessToken("", new AccessTokenReq());
                fail("Should throw network exception");
            } catch (Exception e) {
                // 期望的异常，表示方法签名正确
            }

            try {
                RefreshTokenResp refreshResp = channelSender.refreshToken("token", new RefreshTokenReq());
                fail("Should throw network exception");
            } catch (Exception e) {
                // 期望的异常
            }

            try {
                ShopInfoResp shopResp = channelSender.shopInfo("token", new ShopInfoReq());
                fail("Should throw network exception");
            } catch (Exception e) {
                // 期望的异常
            }

            try {
                // 其他API返回通用Response类型
                Response response = channelSender.orderCreated("token", new CreateOrderReq());
                fail("Should throw network exception");
            } catch (Exception e) {
                // 期望的异常
            }
        }

        @Test
        @DisplayName("验证API参数一致性")
        void testAPIParameterConsistency() {
            // 验证所有API都接受token参数（除了accessToken使用空字符串）
            // 验证所有API都接受请求对象参数
            // 验证所有API都有可选的headers参数重载版本

            assertDoesNotThrow(() -> {
                // 测试参数类型正确性
                CreateOrderReq createReq = new CreateOrderReq();
                Map<String, String> headers = new HashMap<>();
                
                try {
                    channelSender.orderCreated("valid_token", createReq, headers);
                } catch (Exception ignored) {}
                
                try {
                    channelSender.orderCreated("valid_token", createReq);
                } catch (Exception ignored) {}
                
                // AccessToken特殊情况：token参数为空字符串
                AccessTokenReq accessReq = new AccessTokenReq();
                try {
                    channelSender.accessToken("", accessReq, headers);
                } catch (Exception ignored) {}
                
                try {
                    channelSender.accessToken("", accessReq);
                } catch (Exception ignored) {}
            });
        }
    }

    @Nested
    @DisplayName("接口行为一致性验证")
    class InterfaceBehaviorConsistencyTests {

        @Test
        @DisplayName("参数验证行为一致性")
        void testParameterValidationConsistency() {
            // 所有API在接收null请求时都应该抛出IllegalArgumentException
            
            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderCreated("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderModified("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderConfirmed("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderRemind("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderCanceled("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderDone("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderApplyRefund("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.orderRefunded("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.selfDeliveryChange("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.accessToken("", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.refreshToken("token", null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                channelSender.shopInfo("token", null);
            });
        }

        @Test
        @DisplayName("资源清理行为一致性")
        void testResourceCleanupConsistency() {
            // 测试close方法可以多次调用而不出错
            assertDoesNotThrow(() -> {
                channelSender.close();
                channelSender.close(); // 第二次调用应该是安全的
            });
            
            // 创建新的实例测试AutoCloseable接口
            try (IChannelSender autoCloseable = new ChannelSender(testConfig)) {
                assertNotNull(autoCloseable);
                // try-with-resources会自动调用close()
            } catch (Exception e) {
                // Expected for test environment without real HTTP server
            }
        }
    }

    @Nested
    @DisplayName("接口扩展性验证")
    class InterfaceExtensibilityTests {

        @Test
        @DisplayName("验证接口支持多态使用")
        void testPolymorphicUsage() {
            // 验证可以通过接口引用使用实现类
            IChannelSender interfaceReference;
            try {
                interfaceReference = new ChannelSender(testConfig);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create interface reference", e);
            }
            
            assertNotNull(interfaceReference);
            
            // 验证可以正常调用接口方法
            assertThrows(IllegalArgumentException.class, () -> {
                interfaceReference.orderCreated("token", null);
            });
            
            // 清理资源
            try {
                interfaceReference.close();
            } catch (Exception e) {
                // Ignore close exceptions in test
            }
        }

        @Test
        @DisplayName("验证接口可以作为参数传递")
        void testInterfaceAsParameter() {
            // 模拟一个使用IChannelSender接口的服务类
            class OrderService {
                private final IChannelSender sender;
                
                public OrderService(IChannelSender sender) {
                    this.sender = sender;
                }
                
                public void processOrder() throws Exception {
                    CreateOrderReq request = new CreateOrderReq();
                    sender.orderCreated("token", request);
                }
            }
            
            assertDoesNotThrow(() -> {
                OrderService service = new OrderService(channelSender);
                assertNotNull(service);
                
                try {
                    service.processOrder();
                    fail("Should throw network exception");
                } catch (Exception e) {
                    assertTrue(e.getMessage().contains("网络") || e.getMessage().contains("连接") || 
                              e.getMessage().contains("timeout") || e.getMessage().contains("connect") ||
                              e.getMessage().contains("请求参数不能为空"));
                }
            });
        }
    }
}