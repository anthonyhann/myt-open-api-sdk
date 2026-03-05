/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * 麦芽田 API 泛型响应结构
 * <p>
 * 对应 Go SDK 中的 ApiResponse[T any] 结构，提供类型安全的API响应处理
 * </p>
 * 
 * @param <T> 业务数据类型
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApiResponse<T> {

    /**
     * 状态码：200 表示成功，其他表示失败
     */
    @JsonProperty("code")
    private Integer code;

    /**
     * 返回消息：成功时为 "ok"，失败时为错误描述
     */
    @JsonProperty("message")
    private String message;

    /**
     * 业务数据，已反序列化为指定类型
     */
    @JsonProperty("data")
    private T data;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ApiResponse() {
    }

    /**
     * 全参构造函数
     */
    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 从原始Response创建ApiResponse
     * 
     * @param response 原始响应
     * @param dataClass 数据类型
     * @param objectMapper JSON序列化器
     * @param <T> 数据类型参数
     * @return 泛型化的响应对象
     * @throws JsonProcessingException JSON反序列化失败时
     */
    public static <T> ApiResponse<T> from(Response response, Class<T> dataClass, ObjectMapper objectMapper) 
            throws JsonProcessingException {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.code = response.getCode();
        apiResponse.message = response.getMessage();
        
        if (response.getData() != null && !response.getData().isEmpty() && dataClass != null) {
            apiResponse.data = objectMapper.readValue(response.getData(), dataClass);
        }
        
        return apiResponse;
    }

    /**
     * 创建空数据的ApiResponse（用于返回void的接口）
     * 
     * @param response 原始响应
     * @return 空数据的响应对象
     */
    public static ApiResponse<Void> fromEmpty(Response response) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.code = response.getCode();
        apiResponse.message = response.getMessage();
        apiResponse.data = null;
        return apiResponse;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // ==================== 便利方法 ====================

    /**
     * 判断响应是否成功
     * 
     * @return true 表示成功（状态码为 200），false 表示失败
     */
    @JsonIgnore
    public boolean isSuccess() {
        return Integer.valueOf(200).equals(code);
    }

    /**
     * 判断响应是否失败
     * 
     * @return true 表示失败（状态码不为 200），false 表示成功
     */
    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    /**
     * 获取业务数据，如果响应失败则抛出异常
     * 
     * @return 业务数据
     * @throws RuntimeException 如果响应失败
     */
    @JsonIgnore
    public T getDataOrThrow() {
        if (isError()) {
            throw new RuntimeException(String.format("API 错误 [%d]: %s", code, message));
        }
        return data;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse<?> that = (ApiResponse<?>) o;
        return Objects.equals(code, that.code) &&
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
                '}';
    }
}