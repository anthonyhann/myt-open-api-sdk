/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 麦芽田 API 通用响应结构
 * <p>
 * 所有接口响应都遵循统一的格式
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class Response {

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
     * 业务数据，序列化为 JSON 格式的字符串
     */
    @JsonProperty("data")
    private String data;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public Response() {
    }

    /**
     * 全参构造函数
     */
    public Response(Integer code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(code, response.code) &&
               Objects.equals(message, response.message) &&
               Objects.equals(data, response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data='" + (data != null ? data.substring(0, Math.min(data.length(), 100)) + (data.length() > 100 ? "..." : "") : "null") + '\'' +
                '}';
    }
}