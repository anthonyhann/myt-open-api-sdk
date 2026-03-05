/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.channel.sdk.models.types.ApiResponse;
import com.maiyatian.channel.sdk.models.types.Response;
import com.maiyatian.channel.sdk.consts.Constants;
import com.maiyatian.channel.sdk.vars.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * API客户端工具类
 * <p>
 * 对应 Go SDK 中的 RequestWithApiClient[T any] 函数，提供类型安全的泛型API调用
 * 实现了Go语言泛型在Java中的等价功能，遵循SOLID设计原则
 * </p>
 * 
 * <h3>核心功能：Go SDK等价实现</h3>
 * <ul>
 * <li><strong>Go SDK:</strong> {@code RequestWithApiClient[T any](ctx context.Context, apiClient *HTTPClientManger, method, path string, data any, token string, headers *map[string]string) (*ApiResponse[T], error)}</li>
 * <li><strong>Java SDK:</strong> {@code requestWithApiClient(HttpClientManager, String, String, Object, String, Map, Class)}</li>
 * </ul>
 * 
 * <h3>SOLID原则应用：</h3>
 * <ul>
 * <li><strong>S (单一职责)</strong>：专门负责泛型API调用的类型安全转换</li>
 * <li><strong>O (开闭原则)</strong>：支持任意响应类型T，无需修改核心逻辑</li>
 * <li><strong>L (里氏替换)</strong>：泛型类型T可安全替换为任何具体类型</li>
 * <li><strong>I (接口隔离)</strong>：提供最小化的API接口，避免复杂依赖</li>
 * <li><strong>D (依赖反转)</strong>：依赖HttpClientManager抽象，而非具体实现</li>
 * </ul>
 * 
 * <h3>Go泛型高级用法的Java等价实现：</h3>
 * <pre>{@code
 * // Go SDK 用法
 * response, err := client.RequestWithApiClient[AccessTokenResp](
 *     ctx, httpClient, "POST", "v1/channel/access_token", data, token, headers
 * )
 * 
 * // Java SDK 等价用法
 * ApiResponse<AccessTokenResp> response = ApiClientUtils.requestWithApiClient(
 *     httpClient, "POST", "v1/channel/access_token", data, token, headers, AccessTokenResp.class
 * );
 * }</pre>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public final class ApiClientUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(ApiClientUtils.class);

    // 私有构造函数，防止实例化（SOLID：单一职责原则）
    private ApiClientUtils() {
        throw new UnsupportedOperationException("工具类不应该被实例化");
    }

    /**
     * 泛型API客户端请求方法
     * <p>
     * 对应 Go SDK: {@code RequestWithApiClient[T any]}
     * 这是Java SDK中Go泛型功能的核心实现，提供完全的类型安全
     * </p>
     * 
     * <h3>Go SDK对应关系：</h3>
     * <table border="1">
     * <tr><th>Go SDK参数</th><th>Java SDK参数</th><th>说明</th></tr>
     * <tr><td>ctx context.Context</td><td>-</td><td>Java使用异常处理替代</td></tr>
     * <tr><td>apiClient *HTTPClientManger</td><td>apiClient HttpClientManager</td><td>HTTP客户端管理器</td></tr>
     * <tr><td>method string</td><td>method String</td><td>HTTP方法</td></tr>
     * <tr><td>path string</td><td>path String</td><td>API路径</td></tr>
     * <tr><td>data any</td><td>data Object</td><td>请求数据</td></tr>
     * <tr><td>token string</td><td>token String</td><td>访问令牌</td></tr>
     * <tr><td>headers *map[string]string</td><td>headers Map&lt;String, String&gt;</td><td>请求头</td></tr>
     * <tr><td>*ApiResponse[T]</td><td>ApiResponse&lt;T&gt;</td><td>泛型响应</td></tr>
     * </table>
     * 
     * <h3>类型安全保证：</h3>
     * <ul>
     * <li>编译时类型检查：确保responseClass与返回类型T一致</li>
     * <li>运行时类型转换：安全地将JSON转换为目标类型</li>
     * <li>异常传播：保持完整的错误信息链</li>
     * </ul>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * // 获取访问令牌（有返回数据）
     * ApiResponse<AccessTokenResp> tokenResponse = ApiClientUtils.requestWithApiClient(
     *     httpClient, "POST", "v1/channel/access_token", 
     *     tokenRequest, "", null, AccessTokenResp.class
     * );
     * 
     * // 推送订单（无返回数据）
     * ApiResponse<Void> orderResponse = ApiClientUtils.requestWithApiClient(
     *     httpClient, "POST", "v1/channel/order_created", 
     *     orderRequest, token, headers, Void.class
     * );
     * 
     * // 查询门店信息（有返回数据）
     * ApiResponse<ShopInfoResp> shopResponse = ApiClientUtils.requestWithApiClient(
     *     httpClient, "POST", "v1/channel/shop_info", 
     *     shopRequest, token, null, ShopInfoResp.class
     * );
     * }</pre>
     * 
     * @param <T> 响应数据类型，对应Go SDK中的泛型参数T
     * @param apiClient HTTP客户端管理器，对应Go SDK的HTTPClientManger
     * @param method HTTP方法，如"GET"、"POST"
     * @param path API路径，如"v1/channel/order_created"
     * @param data 请求数据，会被序列化为JSON
     * @param token 访问令牌
     * @param headers 额外的HTTP请求头（可选）
     * @param responseClass 响应数据类型的Class对象，用于类型转换
     * @return 类型安全的泛型响应对象
     * @throws IOException 网络请求失败时
     * @throws JsonProcessingException JSON序列化/反序列化失败时
     * @throws IllegalArgumentException 参数验证失败时
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
        
        // 参数验证（SOLID：单一职责 - 专门负责参数检查）
        validateRequestParameters(apiClient, method, path, responseClass);

        // 调试日志输出
        if (Variables.isDebugging()) {
            logger.debug("执行泛型API请求: method={}, path={}, responseClass={}", 
                        method, path, responseClass.getSimpleName());
        }

        try {
            // 执行底层HTTP请求
            Response rawResponse = apiClient.request(method, path, data, token, headers);
            
            // 将原始响应转换为泛型响应
            ApiResponse<T> typedResponse = convertToTypedResponse(rawResponse, responseClass, apiClient);
            
            // 调试日志输出
            if (Variables.isDebugging()) {
                logger.debug("泛型API请求成功: code={}, hasData={}", 
                           typedResponse.getCode(), typedResponse.getData() != null);
            }
            
            return typedResponse;
            
        } catch (JsonProcessingException e) {
            // 记录JSON处理错误日志
            logger.error("泛型API请求JSON处理失败: method={}, path={}, error={}", 
                        method, path, e.getMessage());
            throw e;
        } catch (IOException e) {
            // 记录IO错误日志
            logger.error("泛型API请求网络失败: method={}, path={}, error={}", 
                        method, path, e.getMessage());
            throw e;
        } catch (Exception e) {
            // 包装未预期的异常
            logger.error("泛型API请求出现未知错误: method={}, path={}", method, path, e);
            throw new RuntimeException("泛型API请求失败", e);
        }
    }

    /**
     * 便捷方法：GET请求
     * <p>
     * 对应Go SDK中GET方法的泛型版本
     * </p>
     * 
     * @param <T> 响应数据类型
     * @param apiClient HTTP客户端管理器
     * @param path API路径
     * @param token 访问令牌
     * @param headers 请求头（可选）
     * @param responseClass 响应类型
     * @return 类型安全的响应
     * @throws IOException 请求失败时
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
     * 便捷方法：POST请求（有返回数据）
     * <p>
     * 对应Go SDK中POST方法的泛型版本
     * </p>
     * 
     * @param <T> 响应数据类型
     * @param apiClient HTTP客户端管理器
     * @param path API路径
     * @param data 请求数据
     * @param token 访问令牌
     * @param headers 请求头（可选）
     * @param responseClass 响应类型
     * @return 类型安全的响应
     * @throws IOException 请求失败时
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
     * 便捷方法：POST请求（无返回数据）
     * <p>
     * 对应Go SDK中返回ApiResponseEmptyData的接口
     * </p>
     * 
     * @param apiClient HTTP客户端管理器
     * @param path API路径
     * @param data 请求数据
     * @param token 访问令牌
     * @param headers 请求头（可选）
     * @return 无数据的响应
     * @throws IOException 请求失败时
     * @throws JsonProcessingException JSON处理失败时
     */
    public static ApiResponse<Void> postEmpty(
            HttpClientManager apiClient,
            String path,
            Object data,
            String token,
            Map<String, String> headers
    ) throws IOException, JsonProcessingException {
        return requestWithApiClient(apiClient, "POST", path, data, token, headers, Void.class);
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 验证请求参数
     * 
     * @param apiClient HTTP客户端
     * @param method HTTP方法
     * @param path API路径
     * @param responseClass 响应类型
     * @throws IllegalArgumentException 参数无效时
     */
    private static void validateRequestParameters(
            HttpClientManager apiClient,
            String method,
            String path,
            Class<?> responseClass
    ) {
        if (apiClient == null) {
            throw new IllegalArgumentException("HTTP客户端不能为null");
        }
        if (method == null || method.trim().isEmpty()) {
            throw new IllegalArgumentException("HTTP方法不能为空");
        }
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("API路径不能为空");
        }
        if (responseClass == null) {
            throw new IllegalArgumentException("响应类型不能为null");
        }
    }

    /**
     * 将原始响应转换为泛型响应
     * <p>
     * 对应Go SDK中的类型转换逻辑：
     * {@code response.FormatData(resp)}
     * </p>
     * 
     * @param <T> 目标类型
     * @param rawResponse 原始响应
     * @param responseClass 目标类型Class
     * @param apiClient HTTP客户端管理器（用于获取ObjectMapper）
     * @return 类型安全的响应
     * @throws JsonProcessingException JSON转换失败时
     */
    @SuppressWarnings("unchecked")
    private static <T> ApiResponse<T> convertToTypedResponse(
            Response rawResponse,
            Class<T> responseClass,
            HttpClientManager apiClient
    ) throws JsonProcessingException {
        
        // 处理Void类型（对应Go SDK的ApiResponseEmptyData）
        if (responseClass == Void.class || responseClass == void.class) {
            // 安全的类型转换：Void类型的响应
            return (ApiResponse<T>) ApiResponse.fromEmpty(rawResponse);
        }
        
        // 处理有数据类型的响应
        return ApiResponse.from(rawResponse, responseClass, apiClient.getObjectMapper());
    }
}