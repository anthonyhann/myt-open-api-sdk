/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maiyatian.channel.sdk.consts.Constants;
import com.maiyatian.channel.sdk.models.types.Request;
import com.maiyatian.channel.sdk.models.types.Response;
import com.maiyatian.channel.sdk.models.types.ApiResponse;
import com.maiyatian.channel.sdk.vars.Variables;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 企业级 HTTP 客户端管理器
 * <p>
 * 封装了麦芽田开放平台 API 调用的所有功能：
 * <ul>
 * <li>连接池管理：复用 HTTP 连接，提升性能</li>
 * <li>自动重试：网络错误和 5xx 错误自动重试</li>
 * <li>签名生成：自动生成符合麦芽田规范的请求签名</li>
 * <li>日志记录：记录请求和响应的详细信息，便于调试</li>
 * <li>超时控制：连接、请求、读取的多层超时保护</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpClientManager implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientManager.class);
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json;charset=utf-8");

    private final HttpClientConfig config;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final SecureRandom secureRandom;

    /**
     * 创建新的 HTTP 客户端管理器
     * 
     * @param config HTTP 客户端配置，必须通过 validate() 验证
     * @throws ConfigValidationException 如果配置验证失败
     * @throws IllegalArgumentException 如果配置为 null
     */
    public HttpClientManager(HttpClientConfig config) throws ConfigValidationException {
        if (config == null) {
            throw new IllegalArgumentException("配置不能为空");
        }
        
        // 验证配置有效性
        config.validate();
        
        this.config = config;
        this.secureRandom = new SecureRandom();
        
        // 配置 JSON 序列化器
        this.objectMapper = createObjectMapper();
        
        // 创建和配置 HTTP 客户端
        this.httpClient = createHttpClient();
        
        if (config.isEnableLogging()) {
            logger.info("HTTP 客户端管理器初始化完成，基础地址: {}", config.getBaseUrl());
        }
    }

    /**
     * 执行 HTTP 请求（核心方法）
     * 
     * @param method HTTP 方法，支持 GET 和 POST
     * @param path API 路径，如 "v1/channel/order_created"
     * @param data 业务参数，会被序列化为 JSON 字符串放入 data 字段
     * @param token 商户授权令牌
     * @param headers 额外的 HTTP 请求头（可选）
     * @return API 响应结构
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 序列化/反序列化失败时
     * @throws RuntimeException 签名生成失败或其他运行时错误
     */
    public Response request(String method, String path, Object data, String token, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        // 验证业务参数
        if (data == null) {
            throw new IllegalArgumentException("业务参数 data 不能为空");
        }

        // 序列化业务参数为 JSON 字符串
        String dataString = objectMapper.writeValueAsString(data);

        // 构建麦芽田标准请求结构
        Request requestData = new Request();
        requestData.setAppKey(config.getApiKey());
        requestData.setRequestId(generateRequestId());
        requestData.setToken(token);
        requestData.setTimestamp(Instant.now().getEpochSecond());
        requestData.setData(dataString);

        // 生成请求签名
        try {
            requestData.setSignature(generateSignature(requestData, config.getApiSecret()));
        } catch (Exception e) {
            throw new RuntimeException("生成请求签名失败", e);
        }

        // 构建完整的请求 URL
        String url = isAbsoluteUrl(path) ? path : config.getBaseUrl() + "/" + path;

        // 构建 HTTP 请求
        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("Content-Type", Constants.ContentType.JSON_UTF8)
                .addHeader("User-Agent", Variables.getUserAgent())
                .addHeader("Accept", Constants.ContentType.JSON)
                .addHeader("Accept-Encoding", "gzip, deflate");

        // 添加额外的请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 根据 HTTP 方法设置请求体
        if ("POST".equalsIgnoreCase(method)) {
            String requestDataJson = objectMapper.writeValueAsString(requestData);
            RequestBody requestBody = RequestBody.create(requestDataJson, JSON_MEDIA_TYPE);
            requestBuilder.post(requestBody);
        } else if ("GET".equalsIgnoreCase(method)) {
            requestBuilder.get();
        } else {
            throw new IllegalArgumentException("不支持的 HTTP 方法: " + method);
        }

        okhttp3.Request request = requestBuilder.build();

        // 记录请求日志
        if (config.isEnableLogging()) {
            logRequest(request, requestData);
        }

        // 执行请求（带重试机制）
        return executeWithRetry(request);
    }

    /**
     * 执行 GET 请求
     * 
     * @param path API 路径
     * @param token 商户授权令牌
     * @param headers 额外的请求头（可选）
     * @return API 响应
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 处理失败时
     */
    public Response get(String path, String token, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        // GET 请求使用空的 data 对象
        return request("GET", path, Collections.emptyMap(), token, headers);
    }

    /**
     * 执行 POST 请求
     * 
     * @param path API 路径，如 "v1/channel/order_created"
     * @param data 业务参数，会被序列化为 JSON 字符串
     * @param token 商户授权令牌
     * @param headers 额外的请求头（可选）
     * @return API 响应
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 处理失败时
     */
    public Response post(String path, Object data, String token, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        return request("POST", path, data, token, headers);
    }

    // ==================== 泛型 API 方法（对应 Go SDK RequestWithApiClient[T]）====================

    /**
     * 执行泛型化的 HTTP 请求
     * <p>
     * 对应 Go SDK 中的 RequestWithApiClient[T any] 函数，提供类型安全的 API 调用
     * </p>
     * 
     * @param <T> 响应数据类型
     * @param method HTTP 方法，支持 GET 和 POST
     * @param path API 路径，如 "v1/channel/order_created"
     * @param data 业务参数，会被序列化为 JSON 字符串放入 data 字段
     * @param token 商户授权令牌
     * @param headers 额外的 HTTP 请求头（可选）
     * @param responseClass 响应数据类型的 Class 对象
     * @return 泛型化的 API 响应结构
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 序列化/反序列化失败时
     * @throws RuntimeException 签名生成失败或其他运行时错误
     */
    public <T> ApiResponse<T> requestWithType(String method, String path, Object data, String token, 
                                            Map<String, String> headers, Class<T> responseClass) 
            throws IOException, JsonProcessingException {
        
        // 执行原始请求
        Response response = request(method, path, data, token, headers);
        
        // 转换为泛型响应
        return ApiResponse.from(response, responseClass, objectMapper);
    }

    /**
     * 执行泛型化的 GET 请求
     * 
     * @param <T> 响应数据类型
     * @param path API 路径
     * @param token 商户授权令牌
     * @param headers 额外的请求头（可选）
     * @param responseClass 响应数据类型的 Class 对象
     * @return 泛型化的 API 响应
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 处理失败时
     */
    public <T> ApiResponse<T> getWithType(String path, String token, Map<String, String> headers, Class<T> responseClass) 
            throws IOException, JsonProcessingException {
        return requestWithType("GET", path, Collections.emptyMap(), token, headers, responseClass);
    }

    /**
     * 执行泛型化的 POST 请求
     * 
     * @param <T> 响应数据类型
     * @param path API 路径，如 "v1/channel/order_created"
     * @param data 业务参数，会被序列化为 JSON 字符串
     * @param token 商户授权令牌
     * @param headers 额外的请求头（可选）
     * @param responseClass 响应数据类型的 Class 对象
     * @return 泛型化的 API 响应
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 处理失败时
     */
    public <T> ApiResponse<T> postWithType(String path, Object data, String token, 
                                         Map<String, String> headers, Class<T> responseClass) 
            throws IOException, JsonProcessingException {
        return requestWithType("POST", path, data, token, headers, responseClass);
    }

    /**
     * 执行不返回数据的 POST 请求（对应 Go SDK 中返回 ApiResponseEmptyData 的接口）
     * 
     * @param path API 路径，如 "v1/channel/order_created"
     * @param data 业务参数，会被序列化为 JSON 字符串
     * @param token 商户授权令牌
     * @param headers 额外的请求头（可选）
     * @return 空数据的 API 响应
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON 处理失败时
     */
    public ApiResponse<Void> postEmpty(String path, Object data, String token, Map<String, String> headers) 
            throws IOException, JsonProcessingException {
        
        // 执行原始请求
        Response response = request("POST", path, data, token, headers);
        
        // 转换为空数据响应
        return ApiResponse.fromEmpty(response);
    }

    /**
     * 获取客户端配置
     * 
     * @return 当前客户端使用的配置
     */
    public HttpClientConfig getConfig() {
        return config;
    }

    /**
     * 获取JSON序列化器
     * <p>
     * 对应 Go SDK 中的 JSON 处理功能，用于泛型响应的类型转换
     * 提供给 ApiClientUtils 使用，支持类型安全的数据转换
     * </p>
     * 
     * @return ObjectMapper 实例，用于JSON序列化/反序列化
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 生成请求签名
     * <p>
     * 签名规则：
     * <ol>
     * <li>提取请求体中的 app_key、token、timestamp、data 字段</li>
     * <li>按 key 进行 ksort 排序</li>
     * <li>用半角逗号连接生成字符串（格式：key1=value1,key2=value2）</li>
     * <li>对字符串进行 UTF8 编码</li>
     * <li>使用 appSecret 计算 HmacSHA256 值</li>
     * <li>结果使用 URL 安全的 Base64 编码</li>
     * </ol>
     * 
     * @param request 请求对象
     * @param secretKey 应用密钥
     * @return 签名字符串
     * @throws NoSuchAlgorithmException 算法不支持时
     * @throws InvalidKeyException 密钥无效时
     */
    public String generateSignature(Request request, String secretKey) 
            throws NoSuchAlgorithmException, InvalidKeyException {
        
        if (request == null || request.getData() == null || request.getData().isEmpty()) {
            throw new IllegalArgumentException("请求数据不能为空");
        }

        // 将请求对象转换为 Map
        Map<String, Object> dataMap = new TreeMap<>(); // TreeMap 自动按 key 排序
        dataMap.put("app_key", request.getAppKey());
        dataMap.put("data", request.getData());
        dataMap.put("request_id", request.getRequestId());
        dataMap.put("timestamp", request.getTimestamp());
        dataMap.put("token", request.getToken());

        // 按排序后的 key 构建待签名字符串
        StringJoiner joiner = new StringJoiner(",");
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            joiner.add(entry.getKey() + "=" + entry.getValue());
        }
        String dataToSign = joiner.toString();

        // 计算 HmacSHA256 签名
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
        mac.init(secretKeySpec);
        byte[] signature = mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));

        // 使用 URL 安全的 Base64 编码返回签名
        return Base64.getUrlEncoder().encodeToString(signature);
    }

    @Override
    public void close() {
        // OkHttp 客户端自动管理连接池
        if (config.isEnableLogging()) {
            logger.info("HTTP 客户端管理器已关闭");
        }
    }

    // ==================== 私有方法 ====================

    /**
     * 创建和配置 ObjectMapper
     */
    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        
        // 序列化配置
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        // 反序列化配置：忽略未知属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        return mapper;
    }

    /**
     * 创建和配置 OkHttp 客户端
     */
    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(config.getConnectionTimeout())
                .readTimeout(config.getReadTimeout())
                .callTimeout(config.getRequestTimeout())
                .connectionPool(new ConnectionPool(
                        config.getMaxConnections(),
                        config.getKeepAliveTimeout().toMillis(),
                        TimeUnit.MILLISECONDS
                ));

        // 添加日志拦截器（如果启用）
        if (config.isEnableLogging()) {
            builder.addInterceptor(new LoggingInterceptor());
        }

        return builder.build();
    }

    /**
     * 带重试机制的请求执行
     */
    private Response executeWithRetry(okhttp3.Request request) throws IOException {
        int attempt = 0;
        long delay = config.getRetryBaseDelay().toMillis();

        while (attempt <= config.getRetryMaxAttempts()) {
            try {
                okhttp3.Response response = httpClient.newCall(request).execute();
                String responseBody = response.body().string();

                // 记录响应日志
                if (config.isEnableLogging()) {
                    logResponse(response, responseBody);
                }

                // 解析响应
                Response apiResponse = objectMapper.readValue(responseBody, Response.class);

                // 检查是否需要重试
                if (shouldRetry(response.code(), apiResponse.getCode()) && attempt < config.getRetryMaxAttempts()) {
                    attempt++;
                    if (config.isEnableLogging()) {
                        logger.warn("请求失败，将在 {}ms 后重试 (第 {}/{} 次)", delay, attempt, config.getRetryMaxAttempts());
                    }
                    
                    Thread.sleep(delay);
                    delay = Math.min(delay * 2, config.getRetryMaxDelay().toMillis()); // 指数退避
                    continue;
                }

                // 检查业务状态码
                if (apiResponse.getCode() != 200) {
                    throw new RuntimeException(String.format("API 错误 [%d]: %s", 
                            apiResponse.getCode(), apiResponse.getMessage()));
                }

                return apiResponse;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("请求被中断", e);
            } catch (IOException e) {
                if (attempt < config.getRetryMaxAttempts()) {
                    attempt++;
                    if (config.isEnableLogging()) {
                        logger.warn("网络错误，将在 {}ms 后重试 (第 {}/{} 次): {}", 
                                delay, attempt, config.getRetryMaxAttempts(), e.getMessage());
                    }
                    
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new IOException("重试等待被中断", ie);
                    }
                    delay = Math.min(delay * 2, config.getRetryMaxDelay().toMillis());
                    continue;
                }
                throw e;
            }
        }

        throw new IOException("请求失败，已达到最大重试次数");
    }

    /**
     * 判断是否需要重试
     */
    private boolean shouldRetry(int httpStatusCode, int apiStatusCode) {
        // HTTP 5xx 服务器错误，需要重试
        if (httpStatusCode >= Constants.HttpStatus.INTERNAL_SERVER_ERROR) {
            return true;
        }

        // 业务层 5xx 错误，需要重试（但不包括严重异常）
        // 对应 Go SDK: response.Code >= http.StatusInternalServerError && response.Code < consts.StatusExceptionServerError
        return apiStatusCode >= Constants.HttpStatus.INTERNAL_SERVER_ERROR && 
               apiStatusCode < Constants.HttpStatus.EXCEPTION_SERVER_ERROR;
    }

    /**
     * 生成请求唯一标识（UUID 格式）
     */
    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 检查 URL 是否为绝对 URL
     */
    private boolean isAbsoluteUrl(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    /**
     * 记录请求日志
     */
    private void logRequest(okhttp3.Request request, Request requestData) {
        if (!config.isEnableLogging()) {
            return;
        }

        try {
            Map<String, Object> logData = new HashMap<>();
            logData.put("method", request.method());
            logData.put("url", request.url().toString());
            logData.put("has_body", request.body() != null);
            logData.put("timestamp", Instant.now().toString());

            if (config.isLogRequestBody() && request.body() != null) {
                logData.put("body", requestData);
            }

            logger.info("请求: {}", objectMapper.writeValueAsString(logData));
        } catch (Exception e) {
            logger.warn("记录请求日志失败", e);
        }
    }

    /**
     * 记录响应日志
     */
    private void logResponse(okhttp3.Response response, String responseBody) {
        if (!config.isEnableLogging()) {
            return;
        }

        try {
            Map<String, Object> logData = new HashMap<>();
            logData.put("status_code", response.code());
            logData.put("response_size", responseBody.length());
            logData.put("timestamp", Instant.now().toString());

            if (config.isLogResponseBody() && !responseBody.isEmpty()) {
                try {
                    Object responseData = objectMapper.readValue(responseBody, Object.class);
                    logData.put("body", responseData);
                } catch (Exception e) {
                    logData.put("body", responseBody);
                }
            }

            logger.info("响应: {}", objectMapper.writeValueAsString(logData));
        } catch (Exception e) {
            logger.warn("记录响应日志失败", e);
        }
    }

    /**
     * OkHttp 日志拦截器
     */
    private class LoggingInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
            long startTime = System.nanoTime();

            okhttp3.Response response = chain.proceed(request);

            long responseTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            
            if (config.isEnableLogging()) {
                logger.debug("HTTP 请求完成: {} {} - 状态码: {}, 耗时: {}ms", 
                        request.method(), request.url(), response.code(), responseTime);
            }

            return response;
        }
    }
}