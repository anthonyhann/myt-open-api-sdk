/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.models.types.ApiResponse;
import com.maiyatian.delivery.sdk.models.types.Response;

import java.io.IOException;
import java.util.Map;

/**
 * API客户端工具类
 * <p>
 * 提供类型安全的HTTP请求方法，实现Go SDK中泛型功能的Java等价实现
 * 对应Go SDK中的RequestWithApiClient[T any]函数
 * </p>
 * 
 * <h3>功能特性：</h3>
 * <ul>
 * <li><strong>泛型支持</strong>：类型安全的API调用</li>
 * <li><strong>自动转换</strong>：Response到ApiResponse的自动转换</li>
 * <li><strong>异常处理</strong>：统一的异常处理机制</li>
 * <li><strong>空数据处理</strong>：优雅处理空响应数据</li>
 * </ul>
 * 
 * <h3>对应关系：</h3>
 * <ul>
 * <li>Go: {@code RequestWithApiClient[T any](...) (*ApiResponse[T], error)} → 
 *     Java: {@code requestWithApiClient(..., Class<T>) ApiResponse<T>}</li>
 * <li>Go: {@code ApiResponse[T]} → Java: {@code ApiResponse<T>}</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ApiClientUtils {
    
    /**
     * 执行类型安全的API请求（核心方法）
     * <p>
     * 实现Go SDK中的RequestWithApiClient[T any]函数功能
     * 提供类型安全的HTTP请求，自动处理响应转换
     * </p>
     * 
     * <h3>对应Go函数：</h3>
     * <pre>{@code
     * func RequestWithApiClient[T any](ctx context.Context, apiClient *HTTPClientManger, 
     *     method, path string, data any, token string, headers *map[string]string) (*ApiResponse[T], error)
     * }</pre>
     * 
     * <h3>类型转换流程：</h3>
     * <ol>
     * <li>调用底层HTTP客户端获取原始Response</li>
     * <li>创建ApiResponse&lt;T&gt;实例，复制code和message</li>
     * <li>解析Response.data到指定类型T</li>
     * <li>返回类型安全的ApiResponse&lt;T&gt;</li>
     * </ol>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * // 调用返回具体类型的API
     * ApiResponse<AccessTokenEntity.Resp> response = ApiClientUtils.requestWithApiClient(
     *     httpClient, "POST", "v1/delivery/access_token", 
     *     requestData, token, null, AccessTokenEntity.Resp.class
     * );
     * AccessTokenEntity.Resp tokenData = response.getDataOrThrow();
     * 
     * // 调用无返回数据的API
     * ApiResponse<Void> response = ApiClientUtils.requestWithApiClient(
     *     httpClient, "POST", "v1/delivery/delivery_change", 
     *     requestData, token, null, Void.class
     * );
     * }</pre>
     * 
     * @param <T> 响应数据类型
     * @param apiClient HTTP客户端管理器实例
     * @param method HTTP方法（GET/POST）
     * @param path API路径
     * @param data 请求数据（会被序列化为JSON）
     * @param token 授权令牌
     * @param headers 额外的HTTP请求头（可选）
     * @param responseClass 响应数据的Class对象，用于类型转换
     * @return 类型安全的API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化/反序列化失败时
     */
    public static <T> ApiResponse<T> requestWithApiClient(
            HttpClientManager apiClient,
            String method,
            String path,
            Object data,
            String token,
            Map<String, String> headers,
            Class<T> responseClass
    ) throws IOException, JsonProcessingException {
        
        // 1. 调用底层HTTP客户端获取原始响应
        Response rawResponse = apiClient.request(method, path, data, token, headers);
        
        // 2. 创建类型安全的ApiResponse实例
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(rawResponse.getCode());
        apiResponse.setMessage(rawResponse.getMessage());
        
        // 3. 处理响应数据转换
        if (rawResponse.getData() != null && !rawResponse.getData().trim().isEmpty()) {
            // 有数据的情况：解析到指定类型
            if (responseClass != Void.class && responseClass != void.class) {
                T parsedData = rawResponse.parseData(responseClass);
                apiResponse.setData(parsedData);
            }
            // 如果responseClass是Void类型，data保持为null
        }
        
        return apiResponse;
    }
    
    /**
     * 执行无返回数据的API请求
     * <p>
     * 专门用于处理无业务数据返回的API（如状态推送接口）
     * 简化调用，无需传递responseClass参数
     * </p>
     * 
     * <h3>适用场景：</h3>
     * <ul>
     * <li>配送状态变更推送</li>
     * <li>位置变更推送</li>
     * <li>其他只返回成功/失败状态的接口</li>
     * </ul>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * ApiResponse<Void> response = ApiClientUtils.requestWithEmptyResponse(
     *     httpClient, "POST", "v1/delivery/delivery_change", 
     *     deliveryChangeData, token, null
     * );
     * 
     * if (response.isSuccess()) {
     *     System.out.println("配送状态推送成功");
     * }
     * }</pre>
     * 
     * @param apiClient HTTP客户端管理器实例
     * @param method HTTP方法（GET/POST）
     * @param path API路径
     * @param data 请求数据
     * @param token 授权令牌
     * @param headers 额外的HTTP请求头（可选）
     * @return 空数据类型的API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化失败时
     */
    @SuppressWarnings("unchecked")
    public static ApiResponse<Void> requestWithEmptyResponse(
            HttpClientManager apiClient,
            String method,
            String path,
            Object data,
            String token,
            Map<String, String> headers
    ) throws IOException, JsonProcessingException {
        
        return (ApiResponse<Void>) requestWithApiClient(
            apiClient, method, path, data, token, headers, Void.class
        );
    }
    
    /**
     * 执行GET请求（类型安全版本）
     * <p>
     * 封装GET请求的泛型调用，简化参数传递
     * </p>
     * 
     * @param <T> 响应数据类型
     * @param apiClient HTTP客户端管理器
     * @param path API路径
     * @param token 授权令牌
     * @param headers 请求头（可选）
     * @param responseClass 响应类型
     * @return 类型安全的API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON处理失败时
     */
    public static <T> ApiResponse<T> get(
            HttpClientManager apiClient,
            String path,
            String token,
            Map<String, String> headers,
            Class<T> responseClass
    ) throws IOException, JsonProcessingException {
        
        return requestWithApiClient(apiClient, "GET", path, null, token, headers, responseClass);
    }
    
    /**
     * 执行POST请求（类型安全版本）
     * <p>
     * 封装POST请求的泛型调用，简化参数传递
     * </p>
     * 
     * @param <T> 响应数据类型
     * @param apiClient HTTP客户端管理器
     * @param path API路径
     * @param data 请求数据
     * @param token 授权令牌
     * @param headers 请求头（可选）
     * @param responseClass 响应类型
     * @return 类型安全的API响应
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON处理失败时
     */
    public static <T> ApiResponse<T> post(
            HttpClientManager apiClient,
            String path,
            Object data,
            String token,
            Map<String, String> headers,
            Class<T> responseClass
    ) throws IOException, JsonProcessingException {
        
        return requestWithApiClient(apiClient, "POST", path, data, token, headers, responseClass);
    }
    
    /**
     * 私有构造函数，防止实例化
     * <p>
     * 工具类不应该被实例化，所有方法都应该是静态的
     * </p>
     */
    private ApiClientUtils() {
        throw new AssertionError("ApiClientUtils class should not be instantiated");
    }
}