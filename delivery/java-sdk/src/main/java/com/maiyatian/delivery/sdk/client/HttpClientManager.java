/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import com.maiyatian.delivery.sdk.utils.JsonUtils;
import com.maiyatian.delivery.sdk.utils.SignatureUtils;
import com.maiyatian.delivery.sdk.models.types.Request;
import com.maiyatian.delivery.sdk.models.types.Response;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 企业级HTTP客户端管理器
 * <p>
 * 提供连接池管理、自动重试、请求签名、日志记录等企业级特性
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpClientManager implements AutoCloseable {
    
    private static final Logger logger = LoggerFactory.getLogger(HttpClientManager.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    private final HttpClientConfig config;
    private final OkHttpClient client;
    
    // ==================== 构造函数 ====================
    
    /**
     * 创建新的HTTP客户端管理器
     * 
     * @param config HTTP客户端配置
     * @throws ConfigValidationException 如果配置验证失败
     */
    public HttpClientManager(HttpClientConfig config) throws ConfigValidationException {
        // 验证配置
        config.validate();
        this.config = config;
        
        // 创建OkHttpClient实例
        this.client = new OkHttpClient.Builder()
                // 设置连接池配置
                .connectionPool(new ConnectionPool(
                        config.getMaxConnections(),
                        (long) config.getKeepAliveTimeout().getSeconds(),
                        TimeUnit.SECONDS
                ))
                // 设置超时配置
                .callTimeout((long) config.getRequestTimeout().getSeconds(), TimeUnit.SECONDS)
                .connectTimeout((long) config.getConnectionTimeout().getSeconds(), TimeUnit.SECONDS)
                .readTimeout((long) config.getReadTimeout().getSeconds(), TimeUnit.SECONDS)
                .writeTimeout((long) config.getReadTimeout().getSeconds(), TimeUnit.SECONDS)
                // 添加日志拦截器
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
    }
    
    // ==================== 核心方法 ====================
    
    /**
     * 执行HTTP请求
     * <p>
     * 自动处理签名、重试等逻辑
     * 
     * @param method HTTP方法，如"POST"
     * @param path 请求路径
     * @param data 业务数据对象
     * @param token 授权令牌
     * @param headers 额外的请求头
     * @return 标准响应结构
     * @throws IOException 如果请求失败
     */
    public Response request(String method, String path, Object data, String token, Map<String, String> headers) 
            throws IOException {
        // 生成请求ID
        String requestId = generateRequestId();
        
        // 序列化业务数据
        String dataStr = data != null ? JsonUtils.toJson(data) : "";
        
        // 生成时间戳
        long timestamp = System.currentTimeMillis() / 1000;
        
        // 构建签名参数
        Map<String, String> signParams = new HashMap<>();
        signParams.put("app_key", config.getApiKey());
        signParams.put("token", token != null ? token : "");
        signParams.put("timestamp", String.valueOf(timestamp));
        signParams.put("data", dataStr);
        signParams.put("command", path);
        
        // 生成签名
        String signature;
        try {
            signature = SignatureUtils.generateSignature(signParams, config.getApiSecret());
        } catch (Exception e) {
            throw new IOException("签名生成失败: " + e.getMessage(), e);
        }
        
        // 构建请求对象
        Request request = new Request(
                config.getApiKey(),
                requestId,
                token,
                timestamp,
                dataStr,
                signature
        );
        
        // 序列化请求对象
        String requestBodyStr = JsonUtils.toJson(request);
        
        // 构建完整URL
        String url = buildUrl(path);
        
        // 构建请求
        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBodyStr, JSON))
                .header("User-Agent", String.format("Maiyatian-Java-SDK/%s", config.getSdkVersion()))
                .header("Accept", "application/json");
        
        // 添加自定义headers
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.header(header.getKey(), header.getValue());
            }
        }
        
        okhttp3.Request okHttpRequest = requestBuilder.build();
        
        // 处理重试逻辑
        int maxAttempts = config.getRetryMaxAttempts() + 1; // +1 表示首次请求
        IOException lastException = null;
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                // 执行请求
                okhttp3.Response okHttpResponse = client.newCall(okHttpRequest).execute();
                
                try (ResponseBody responseBody = okHttpResponse.body()) {
                    // 检查HTTP状态码
                    if (okHttpResponse.isSuccessful()) {
                        // 解析响应
                        String responseBodyStr = responseBody.string();
                        Response httpResponse = JsonUtils.fromJson(responseBodyStr, Response.class);
                        
                        // 检查业务状态码
                        if (httpResponse.getCode() == 200) {
                            return httpResponse;
                        } else if (httpResponse.getCode() >= 500) {
                            // 5xx业务错误，可重试
                            logger.warn("API error (retryable): {} - {}, attempt {}/{}, request_id={}", 
                                    httpResponse.getCode(), httpResponse.getMessage(), attempt, maxAttempts, requestId);
                        } else {
                            // 非重试错误，直接返回
                            return httpResponse;
                        }
                    } else {
                        // HTTP状态码错误
                        if (okHttpResponse.code() >= 500 || okHttpResponse.code() == 429) {
                            // 5xx或429 Too Many Requests，可重试
                            logger.warn("HTTP error (retryable): {} - {}, attempt {}/{}, request_id={}", 
                                    okHttpResponse.code(), okHttpResponse.message(), attempt, maxAttempts, requestId);
                        } else {
                            // 非重试错误，直接返回
                            String responseBodyStr = responseBody.string();
                            logger.error("HTTP error (non-retryable): {} - {}, body: {}, request_id={}", 
                                    okHttpResponse.code(), okHttpResponse.message(), responseBodyStr, requestId);
                            
                            // 尝试解析响应体
                            try {
                                return JsonUtils.fromJson(responseBodyStr, Response.class);
                            } catch (IOException e) {
                                // 解析失败，创建默认响应
                                return new Response(okHttpResponse.code(), okHttpResponse.message(), null);
                            }
                        }
                    }
                } finally {
                    okHttpResponse.close();
                }
            } catch (SocketTimeoutException e) {
                // 超时异常，可重试
                logger.warn("Socket timeout (retryable), attempt {}/{}, request_id={}", 
                        attempt, maxAttempts, requestId);
                lastException = e;
            } catch (IOException e) {
                // 其他IO异常
                if (e.getMessage() != null && e.getMessage().contains("timeout")) {
                    // 超时相关异常，可重试
                    logger.warn("IO timeout (retryable), attempt {}/{}, request_id={}", 
                            attempt, maxAttempts, requestId);
                    lastException = e;
                } else {
                    // 非重试错误，直接抛出
                    logger.error("IO error (non-retryable), attempt {}/{}, request_id={}: {}", 
                            attempt, maxAttempts, requestId, e.getMessage());
                    throw e;
                }
            }
            
            // 重试间隔
            if (attempt < maxAttempts) {
                long delay = calculateRetryDelay(attempt);
                logger.info("Retrying in {}ms, attempt {}/{}, request_id={}", delay, attempt + 1, maxAttempts, requestId);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Retry interrupted", ie);
                }
            }
        }
        
        // 重试次数用尽
        logger.error("All retry attempts failed, request_id={}", requestId);
        if (lastException != null) {
            throw lastException;
        }
        throw new IOException("All retry attempts failed");
    }
    
    /**
     * 执行HTTP请求（不带自定义headers）
     * <p>
     * 向后兼容性重载方法
     * 
     * @param method HTTP方法，如"POST"
     * @param path 请求路径
     * @param data 业务数据对象
     * @param token 授权令牌
     * @return 标准响应结构
     * @throws IOException 如果请求失败
     */
    public Response request(String method, String path, Object data, String token) 
            throws IOException {
        return request(method, path, data, token, null);
    }
    
    /**
     * 执行GET请求
     * 
     * @param path 请求路径
     * @param token 授权令牌
     * @return 标准响应结构
     * @throws IOException 如果请求失败
     */
    public Response get(String path, String token) 
            throws IOException {
        return request("GET", path, null, token);
    }
    
    /**
     * 执行POST请求
     * 
     * @param path 请求路径
     * @param data 业务数据
     * @param token 授权令牌
     * @return 标准响应结构
     * @throws IOException 如果请求失败
     */
    public Response post(String path, Object data, String token) 
            throws IOException {
        return request("POST", path, data, token);
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 生成唯一请求ID
     * 
     * @return 请求ID
     */
    private String generateRequestId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * 构建完整URL
     * 
     * @param path 请求路径
     * @return 完整URL
     */
    private String buildUrl(String path) {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return path;
        }
        String baseUrl = config.getBaseUrl();
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        // 根据配送开放平台接口文档，所有请求都需要添加 /v1/delivery/ 前缀
        return baseUrl + "/v1/delivery/" + path;
    }
    
    /**
     * 计算重试延迟时间（指数退避策略）
     * 
     * @param attempt 当前重试次数（从1开始）
     * @return 延迟时间（毫秒）
     */
    private long calculateRetryDelay(int attempt) {
        long baseDelay = config.getRetryBaseDelay().toMillis();
        long maxDelay = config.getRetryMaxDelay().toMillis();
        
        // 指数退避：baseDelay * (2^(attempt-1))
        long delay = baseDelay * (1L << (attempt - 1));
        
        // 不超过最大延迟
        return Math.min(delay, maxDelay);
    }
    
    /**
     * HTTP日志拦截器
     */
    private class HttpLoggingInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            if (!config.isEnableLogging()) {
                return chain.proceed(chain.request());
            }
            
            okhttp3.Request request = chain.request();
            long startTime = System.nanoTime();
            
            // 记录请求
            logger.debug("HTTP Request: {} {}", request.method(), request.url());
            if (config.isLogRequestBody() && request.body() != null) {
                RequestBody requestBody = request.body();
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                logger.debug("Request Body: {}", buffer.readUtf8());
            }
            
            // 执行请求
            okhttp3.Response response = chain.proceed(request);
            
            // 记录响应
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // 转换为毫秒
            logger.debug("HTTP Response: {} {} ({}ms)", 
                    response.code(), response.message(), duration);
            
            if (config.isLogResponseBody() && response.body() != null) {
                ResponseBody responseBody = response.body();
                String responseBodyStr = responseBody.string();
                logger.debug("Response Body: {}", responseBodyStr);
                
                // 重新构建响应，因为body只能读取一次
                return response.newBuilder()
                        .body(ResponseBody.create(responseBodyStr, responseBody.contentType()))
                        .build();
            }
            
            return response;
        }
    }
    
    // ==================== Getter 和 Setter 方法 ====================
    
    public HttpClientConfig getConfig() {
        return config;
    }
    
    // ==================== 关闭资源 ====================
    
    /**
     * 关闭HTTP客户端，释放资源
     */
    @Override
    public void close() {
        // OkHttpClient会自动管理连接池，无需显式关闭
        logger.debug("HTTP client closed");
    }
}