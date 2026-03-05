/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.types;

import com.maiyatian.delivery.sdk.consts.Constants;
import java.util.Objects;

/**
 * 类型安全的API响应包装器
 * <p>
 * 对应Go SDK中的ApiResponse[T any]结构，提供类型安全的响应数据访问
 * 实现从Response到强类型数据的自动转换
 * </p>
 * 
 * <h3>功能特性：</h3>
 * <ul>
 * <li><strong>类型安全</strong>：编译时类型检查，避免类型转换错误</li>
 * <li><strong>空值处理</strong>：优雅处理null和空数据的情况</li>
 * <li><strong>异常处理</strong>：提供getDataOrThrow()方法处理异常场景</li>
 * <li><strong>链式操作</strong>：支持流畅的API调用模式</li>
 * </ul>
 * 
 * <h3>对应关系：</h3>
 * <ul>
 * <li>Go: {@code type ApiResponse[T any] struct { Code int; Message string; Data *T }} → 
 *     Java: {@code class ApiResponse<T> { int code; String message; T data }}</li>
 * <li>Go: {@code response.Data} → Java: {@code response.getData()}</li>
 * </ul>
 * 
 * <h3>使用示例：</h3>
 * <pre>{@code
 * // 成功响应处理
 * ApiResponse<AccessTokenResp> response = deliverySender.accessToken(token, request);
 * if (response.isSuccess()) {
 *     AccessTokenResp tokenData = response.getDataOrThrow();
 *     System.out.println("访问令牌: " + tokenData.getToken());
 * }
 * 
 * // 错误响应处理
 * ApiResponse<String> response = deliverySender.someApi(data);
 * if (!response.isSuccess()) {
 *     System.err.println("API调用失败: " + response.getMessage());
 * }
 * 
 * // 链式操作
 * String token = deliverySender.accessToken(authData)
 *     .filter(ApiResponse::isSuccess)
 *     .map(ApiResponse::getDataOrThrow)
 *     .map(AccessTokenResp::getToken)
 *     .orElseThrow(() -> new RuntimeException("获取令牌失败"));
 * }</pre>
 * 
 * @param <T> 响应数据类型
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApiResponse<T> {
    
    /**
     * 业务状态码
     * <p>200表示成功，其他值表示失败</p>
     */
    private int code;
    
    /**
     * 响应消息
     * <p>成功时为"ok"，失败时为错误描述</p>
     */
    private String message;
    
    /**
     * 业务数据
     * <p>类型安全的强类型数据，可能为null</p>
     */
    private T data;
    
    // ==================== 构造函数 ====================
    
    /**
     * 默认构造函数
     */
    public ApiResponse() {
    }
    
    /**
     * 完整构造函数
     * 
     * @param code 业务状态码
     * @param message 响应消息
     * @param data 业务数据
     */
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    // ==================== 静态工厂方法 ====================
    
    /**
     * 创建成功响应
     * <p>
     * 工厂方法，创建一个表示成功的ApiResponse实例
     * </p>
     * 
     * @param <T> 数据类型
     * @param data 响应数据
     * @return 成功的ApiResponse实例
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Constants.SUCCESS_CODE, "ok", data);
    }
    
    /**
     * 创建成功响应（无数据）
     * <p>
     * 用于只返回成功状态，不返回具体数据的API
     * </p>
     * 
     * @return 成功的空数据ApiResponse实例
     */
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(Constants.SUCCESS_CODE, "ok", null);
    }
    
    /**
     * 创建错误响应
     * <p>
     * 工厂方法，创建一个表示失败的ApiResponse实例
     * </p>
     * 
     * @param <T> 数据类型
     * @param code 错误状态码
     * @param message 错误消息
     * @return 失败的ApiResponse实例
     */
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
    
    /**
     * 从Response转换为ApiResponse
     * <p>
     * 对应Go SDK中的FormatData()方法功能
     * 将原始Response转换为类型安全的ApiResponse
     * </p>
     * 
     * @param <T> 目标数据类型
     * @param response 原始响应
     * @param dataClass 目标数据类型的Class对象
     * @return 转换后的ApiResponse
     * @throws RuntimeException 如果数据解析失败
     */
    public static <T> ApiResponse<T> from(Response response, Class<T> dataClass) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(response.getCode());
        apiResponse.setMessage(response.getMessage());
        
        // 解析数据到指定类型
        if (response.getData() != null && !response.getData().trim().isEmpty()) {
            if (dataClass != Void.class && dataClass != void.class) {
                try {
                    T parsedData = response.parseData(dataClass);
                    apiResponse.setData(parsedData);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse response data to " + dataClass.getSimpleName(), e);
                }
            }
        }
        
        return apiResponse;
    }
    
    /**
     * 创建空数据响应
     * <p>
     * 专门用于处理无返回数据的API响应
     * </p>
     * 
     * @param response 原始响应
     * @return 空数据的ApiResponse
     */
    @SuppressWarnings("unchecked")
    public static ApiResponse<Void> fromEmpty(Response response) {
        return (ApiResponse<Void>) from(response, Void.class);
    }
    
    // ==================== 业务方法 ====================
    
    /**
     * 检查响应是否成功
     * <p>
     * 根据状态码判断API调用是否成功
     * </p>
     * 
     * @return true表示成功，false表示失败
     */
    public boolean isSuccess() {
        return code == Constants.SUCCESS_CODE;
    }
    
    /**
     * 检查响应是否失败
     * <p>
     * 与isSuccess()相反的逻辑
     * </p>
     * 
     * @return true表示失败，false表示成功
     */
    public boolean isError() {
        return !isSuccess();
    }
    
    /**
     * 获取数据，失败时抛出异常
     * <p>
     * 安全的数据获取方法，确保只在成功时返回数据
     * 失败时抛出包含错误信息的RuntimeException
     * </p>
     * 
     * <h3>使用场景：</h3>
     * <ul>
     * <li>确保API调用成功的场景</li>
     * <li>需要快速失败的业务逻辑</li>
     * <li>链式调用中的中间环节</li>
     * </ul>
     * 
     * @return 响应数据
     * @throws RuntimeException 如果响应失败或数据为null
     */
    public T getDataOrThrow() {
        if (isError()) {
            throw new RuntimeException("API调用失败 [" + code + "]: " + message);
        }
        if (data == null) {
            throw new RuntimeException("响应数据为空");
        }
        return data;
    }
    
    /**
     * 获取数据，失败时返回默认值
     * <p>
     * 安全的数据获取方法，失败时返回指定的默认值
     * 适用于可以容忍失败的业务场景
     * </p>
     * 
     * @param defaultValue 默认值
     * @return 响应数据或默认值
     */
    public T getDataOrDefault(T defaultValue) {
        if (isSuccess() && data != null) {
            return data;
        }
        return defaultValue;
    }
    
    /**
     * 检查是否有数据
     * <p>
     * 同时检查响应成功和数据不为null
     * </p>
     * 
     * @return true表示有有效数据，false表示无数据
     */
    public boolean hasData() {
        return isSuccess() && data != null;
    }
    
    /**
     * 转换数据类型
     * <p>
     * 提供类型转换功能，支持链式操作
     * </p>
     * 
     * @param <R> 目标类型
     * @param mapper 转换函数
     * @return 转换后的ApiResponse
     */
    public <R> ApiResponse<R> map(java.util.function.Function<T, R> mapper) {
        if (isError() || data == null) {
            return new ApiResponse<>(code, message, null);
        }
        
        try {
            R mappedData = mapper.apply(data);
            return new ApiResponse<>(code, message, mappedData);
        } catch (Exception e) {
            return ApiResponse.error(500, "数据转换失败: " + e.getMessage());
        }
    }
    
    /**
     * 过滤响应
     * <p>
     * 支持基于条件的响应过滤
     * </p>
     * 
     * @param predicate 过滤条件
     * @return 过滤后的ApiResponse
     */
    public ApiResponse<T> filter(java.util.function.Predicate<ApiResponse<T>> predicate) {
        if (predicate.test(this)) {
            return this;
        }
        return ApiResponse.error(400, "响应不满足过滤条件");
    }
    
    // ==================== Getter/Setter ====================
    
    /**
     * 获取业务状态码
     * 
     * @return 状态码
     */
    public int getCode() {
        return code;
    }
    
    /**
     * 设置业务状态码
     * 
     * @param code 状态码
     */
    public void setCode(int code) {
        this.code = code;
    }
    
    /**
     * 获取响应消息
     * 
     * @return 消息内容
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * 设置响应消息
     * 
     * @param message 消息内容
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * 获取业务数据
     * 
     * @return 业务数据（可能为null）
     */
    public T getData() {
        return data;
    }
    
    /**
     * 设置业务数据
     * 
     * @param data 业务数据
     */
    public void setData(T data) {
        this.data = data;
    }
    
    // ==================== Object方法 ====================
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse<?> that = (ApiResponse<?>) o;
        return code == that.code &&
               Objects.equals(message, that.message) &&
               Objects.equals(data, that.data);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }
    
    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", hasData=" + hasData() +
                '}';
    }
}