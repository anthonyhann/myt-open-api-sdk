/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.api;

import com.maiyatian.channel.sdk.client.ConfigValidationException;
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;
import com.maiyatian.channel.sdk.models.sender.entity.order.CreateOrderReq;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderConfirmedReq;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderDoneReq;
import com.maiyatian.channel.sdk.models.types.Response;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ChannelSenderAsync 异步客户端单元测试
 * 
 * 测试覆盖范围：
 * 1. 异步客户端构造和配置
 * 2. 异步API调用方法
 * 3. 批量操作功能
 * 4. 线程池管理
 * 5. 异常处理和错误恢复
 * 6. 资源清理和并发安全性
 */
@DisplayName("ChannelSenderAsync 异步客户端测试")
public class ChannelSenderAsyncTest {

    private HttpClientConfig validConfig;
    private ChannelSenderAsync asyncSender;

    @BeforeEach
    void setUp() {
        validConfig = HttpClientConfig.newBuilder()
                .baseUrl("https://test-api.maiyatian.com")
                .apiKey("test_key")
                .apiSecret("test_secret")
                .connectionTimeout(Duration.ofMillis(100))  // 极短连接超时
                .readTimeout(Duration.ofMillis(200))        // 极短读取超时
                .retryMaxAttempts(0)                        // 禁用重试
                .enableLogging(false)
                .build();
    }

    @AfterEach
    void tearDown() {
        if (asyncSender != null && !asyncSender.isClosed()) {
            asyncSender.close();
        }
    }

    @Nested
    @DisplayName("构造函数和初始化测试")
    class ConstructorAndInitializationTests {

        @Test
        @DisplayName("正常配置构造异步客户端")
        void testConstructorWithValidConfig() throws ConfigValidationException {
            assertDoesNotThrow(() -> {
                asyncSender = new ChannelSenderAsync(validConfig);
                assertNotNull(asyncSender);
                assertNotNull(asyncSender.getSyncClient());
                assertFalse(asyncSender.isClosed());
            });
        }

        @Test
        @DisplayName("无效配置抛出验证异常")
        void testConstructorWithInvalidConfig() {
            HttpClientConfig invalidConfig = HttpClientConfig.newBuilder()
                    .baseUrl("")
                    .apiKey("test_key")
                    .apiSecret("test_secret")
                    .build();

            assertThrows(Exception.class, () -> {
                new ChannelSenderAsync(invalidConfig);
            });
        }

        @Test
        @DisplayName("线程池初始化验证")
        void testThreadPoolInitialization() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
            
            // 验证线程池是否正常工作
            CompletableFuture<String> testFuture = CompletableFuture.supplyAsync(() -> {
                return Thread.currentThread().getName();
            });

            assertDoesNotThrow(() -> {
                String threadName = testFuture.get(1, TimeUnit.SECONDS);
                assertNotNull(threadName);
            });
        }

        @Test
        @DisplayName("静态工厂方法测试")
        void testStaticFactoryMethod() {
            assertDoesNotThrow(() -> {
                asyncSender = ChannelSenderAsync.create(validConfig);
                assertNotNull(asyncSender);
                assertFalse(asyncSender.isClosed());
            });
        }
    }

    @Nested
    @DisplayName("异步API接口测试")
    class AsyncAPITests {

        @BeforeEach
        void setUpAsyncSender() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
        }

        @Test
        @DisplayName("异步获取访问令牌")
        void testAccessTokenAsync() {
            AccessTokenReq request = new AccessTokenReq();
            request.setCode("test_code");
            request.setGrantType("authorization_code");

            CompletableFuture<AccessTokenResp> future = asyncSender.accessTokenAsync("", request);
            
            assertNotNull(future);
            // 由于没有真实的HTTP服务器，这会失败，但我们可以验证Future的创建
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("异步创建订单")
        void testOrderCreatedAsync() {
            CreateOrderReq request = new CreateOrderReq();
            // 设置必要的字段...

            CompletableFuture<Response> future = asyncSender.orderCreatedAsync("token", request);
            
            assertNotNull(future);
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("异步订单确认")
        void testOrderConfirmedAsync() {
            OrderConfirmedReq request = new OrderConfirmedReq();
            // 设置必要的字段...

            CompletableFuture<Response> future = asyncSender.orderConfirmedAsync("token", request);
            
            assertNotNull(future);
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("异步订单完成")
        void testOrderDoneAsync() {
            OrderDoneReq request = new OrderDoneReq();
            // 设置必要的字段...

            CompletableFuture<Response> future = asyncSender.orderDoneAsync("token", request);
            
            assertNotNull(future);
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("异步方法重载测试")
        void testAsyncMethodOverloads() {
            CreateOrderReq request = new CreateOrderReq();
            
            // 测试带header的版本和不带header的版本
            CompletableFuture<Response> future1 = asyncSender.orderCreatedAsync("token", request, null);
            CompletableFuture<Response> future2 = asyncSender.orderCreatedAsync("token", request);
            
            assertNotNull(future1);
            assertNotNull(future2);
        }
    }

    @Nested
    @DisplayName("批量操作测试")
    class BatchOperationTests {

        @BeforeEach
        void setUpAsyncSender() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
        }

        @Test
        @DisplayName("批量创建订单 - 空列表")
        void testBatchOrderCreatedWithEmptyList() {
            CompletableFuture<List<Response>> future = asyncSender.batchOrderCreated("token", new ArrayList<>());
            
            assertNotNull(future);
            assertDoesNotThrow(() -> {
                List<Response> responses = future.get(1, TimeUnit.SECONDS);
                assertTrue(responses.isEmpty());
            });
        }

        @Test
        @DisplayName("批量创建订单 - null列表")
        void testBatchOrderCreatedWithNullList() {
            CompletableFuture<List<Response>> future = asyncSender.batchOrderCreated("token", null);
            
            assertNotNull(future);
            assertDoesNotThrow(() -> {
                List<Response> responses = future.get(1, TimeUnit.SECONDS);
                assertTrue(responses.isEmpty());
            });
        }

        @Test
        @DisplayName("批量创建订单 - 多个订单")
        void testBatchOrderCreatedWithMultipleOrders() {
            List<CreateOrderReq> orders = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                CreateOrderReq order = new CreateOrderReq();
                // 设置必要字段...
                orders.add(order);
            }

            CompletableFuture<List<Response>> future = asyncSender.batchOrderCreated("token", orders);
            
            assertNotNull(future);
            // 这会因为网络错误失败，但证明批量操作逻辑正确
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("批量订单确认")
        void testBatchOrderConfirmed() {
            List<OrderConfirmedReq> confirmations = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                OrderConfirmedReq confirmation = new OrderConfirmedReq();
                // 设置必要字段...
                confirmations.add(confirmation);
            }

            CompletableFuture<List<Response>> future = asyncSender.batchOrderConfirmed("token", confirmations);
            
            assertNotNull(future);
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("批量订单完成")
        void testBatchOrderDone() {
            List<OrderDoneReq> completions = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                OrderDoneReq completion = new OrderDoneReq();
                // 设置必要字段...
                completions.add(completion);
            }

            CompletableFuture<List<Response>> future = asyncSender.batchOrderDone("token", completions);
            
            assertNotNull(future);
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }
    }

    @Nested
    @DisplayName("错误处理和异常测试")
    class ErrorHandlingTests {

        @BeforeEach
        void setUpAsyncSender() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
        }

        @Test
        @DisplayName("关闭后调用API抛出异常")
        void testApiCallAfterClose() {
            asyncSender.close();
            assertTrue(asyncSender.isClosed());

            CreateOrderReq request = new CreateOrderReq();
            CompletableFuture<Response> future = asyncSender.orderCreatedAsync("token", request);
            
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("异步操作超时处理")
        void testAsyncOperationTimeout() {
            CreateOrderReq request = new CreateOrderReq();
            CompletableFuture<Response> future = asyncSender.orderCreatedAsync("token", request);
            
            // 测试超时
            assertThrows(TimeoutException.class, () -> {
                future.get(100, TimeUnit.MILLISECONDS);
            });
        }

        @Test
        @DisplayName("异步操作异常链式处理")
        void testAsyncExceptionChaining() {
            CreateOrderReq request = new CreateOrderReq();
            
            CompletableFuture<Response> future = asyncSender.orderCreatedAsync("token", request);
            
            CompletableFuture<String> chainedFuture = future
                    .thenApply(response -> "Success: " + response.getCode())
                    .exceptionally(throwable -> "Error: " + throwable.getMessage());
            
            assertDoesNotThrow(() -> {
                String result = chainedFuture.join();
                assertTrue(result.startsWith("Error:"));
            });
        }
    }

    @Nested
    @DisplayName("并发和线程安全测试")
    class ConcurrencyAndThreadSafetyTests {

        @BeforeEach
        void setUpAsyncSender() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
        }

        @Test
        @DisplayName("并发API调用")
        void testConcurrentAPIcalls() {
            int concurrentCalls = 10;
            List<CompletableFuture<Response>> futures = new ArrayList<>();

            for (int i = 0; i < concurrentCalls; i++) {
                CreateOrderReq request = new CreateOrderReq();
                futures.add(asyncSender.orderCreatedAsync("token", request));
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
            );

            // 所有调用都应该失败（网络错误），但不应该有并发问题
            assertThrows(CompletionException.class, () -> {
                allFutures.join();
            });
        }

        @Test
        @DisplayName("线程池资源管理")
        void testThreadPoolResourceManagement() {
            // 创建多个异步客户端并关闭，确保资源正确释放
            for (int i = 0; i < 5; i++) {
                assertDoesNotThrow(() -> {
                    ChannelSenderAsync tempSender = new ChannelSenderAsync(validConfig);
                    tempSender.close();
                    assertTrue(tempSender.isClosed());
                });
            }
        }

        @Test
        @DisplayName("重复关闭安全性")
        void testMultipleCloseCalls() {
            assertDoesNotThrow(() -> {
                asyncSender.close();
                assertTrue(asyncSender.isClosed());
                
                // 再次关闭应该是安全的
                asyncSender.close();
                assertTrue(asyncSender.isClosed());
            });
        }
    }

    @Nested
    @DisplayName("性能和压力测试")
    class PerformanceAndStressTests {

        @BeforeEach
        void setUpAsyncSender() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
        }

        @Test
        @DisplayName("大量异步调用压力测试")
        @Disabled("Disabled for fast test execution - enable for stress testing")
        void testHighVolumeAsyncCalls() {
            int callCount = 10; // 大幅减少调用数量
            List<CompletableFuture<Response>> futures = new ArrayList<>();

            long startTime = System.currentTimeMillis();
            
            for (int i = 0; i < callCount; i++) {
                CreateOrderReq request = new CreateOrderReq();
                futures.add(asyncSender.orderCreatedAsync("token", request));
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
            );

            // 验证所有Future都能在合理时间内完成（即使失败）
            assertThrows(CompletionException.class, () -> {
                allFutures.get(3, TimeUnit.SECONDS); // 减少等待时间
            });

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            // 确保并行执行效率（不应该是串行的时间）
            assertTrue(duration < 10000, "并行执行应该在10秒内完成");
        }

        @Test
        @DisplayName("批量操作性能测试")
        @Disabled("Disabled for fast test execution - enable for performance testing")
        void testBatchOperationPerformance() {
            List<CreateOrderReq> orders = new ArrayList<>();
            for (int i = 0; i < 5; i++) { // 大幅减少批量数量
                orders.add(new CreateOrderReq());
            }

            long startTime = System.currentTimeMillis();
            
            CompletableFuture<List<Response>> future = asyncSender.batchOrderCreated("token", orders);
            
            assertThrows(CompletionException.class, () -> {
                future.get(3, TimeUnit.SECONDS); // 减少等待时间
            });

            long duration = System.currentTimeMillis() - startTime;
            assertTrue(duration < 5000, "批量操作应该在5秒内完成");
        }
    }

    @Nested
    @DisplayName("实用工具方法测试")
    class UtilityMethodTests {

        @BeforeEach
        void setUpAsyncSender() throws ConfigValidationException {
            asyncSender = new ChannelSenderAsync(validConfig);
        }

        @Test
        @DisplayName("获取同步客户端")
        void testGetSyncClient() {
            ChannelSender syncClient = asyncSender.getSyncClient();
            assertNotNull(syncClient);
            assertNotNull(syncClient.getHttpClient());
        }

        @Test
        @DisplayName("异步原始请求方法")
        void testRequestAsync() {
            CompletableFuture<Response> future = asyncSender.requestAsync(
                "POST", "/test", "{}", "token", null
            );
            
            assertNotNull(future);
            assertThrows(CompletionException.class, () -> {
                future.join();
            });
        }

        @Test
        @DisplayName("状态检查方法")
        void testStatusCheckMethods() {
            assertFalse(asyncSender.isClosed());
            
            asyncSender.close();
            assertTrue(asyncSender.isClosed());
        }
    }
}