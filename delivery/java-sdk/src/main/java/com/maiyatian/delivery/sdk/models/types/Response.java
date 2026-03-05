/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.utils.JsonUtils;

import java.io.IOException;

/**
 * 标准化HTTP响应结构
 * <p>
 * 包含业务状态码、响应消息和业务数据
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class Response {
    
    /**
     * 业务状态码，200表示成功
     */
    private int code;
    
    /**
     * 响应消息，成功时为"ok"
     */
    private String message;
    
    /**
     * 业务数据，JSON字符串格式（需要反序列化）
     */
    private String data;
    
    // ==================== 构造函数 ====================
    
    /**
     * 默认构造函数
     */
    public Response() {
    }
    
    /**
     * 完整构造函数
     * 
     * @param code 业务状态码
     * @param message 响应消息
     * @param data 业务数据
     */
    public Response(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    // ==================== Getter 和 Setter 方法 ====================
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
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
    
    /**
     * 检查响应是否成功
     * 
     * @return true if code is 200, false otherwise
     */
    public boolean isSuccess() {
        return this.code == 200;
    }
    
    /**
     * 解析JSON数据到指定类型
     * 
     * @param <T> 目标类型
     * @param clazz 目标类型的Class对象
     * @return 解析后的对象
     * @throws JsonProcessingException 如果JSON解析失败
     */
    public <T> T parseData(Class<T> clazz) throws IOException {
        if (this.data == null || this.data.trim().isEmpty()) {
            return null;
        }
        return JsonUtils.fromJson(this.data, clazz);
    }
    
    // ==================== Object 方法 ====================
    
    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}