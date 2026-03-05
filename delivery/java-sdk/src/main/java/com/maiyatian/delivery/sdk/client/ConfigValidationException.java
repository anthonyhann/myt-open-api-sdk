/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.client;

/**
 * 配置验证异常
 * <p>
 * 当配置参数不满足要求时抛出此异常
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigValidationException extends Exception {
    
    private final String field;
    private final String message;
    
    /**
     * 构造函数
     * 
     * @param field 验证失败的字段名
     * @param message 错误信息
     */
    public ConfigValidationException(String field, String message) {
        super(String.format("Config validation error: %s - %s", field, message));
        this.field = field;
        this.message = message;
    }
    
    /**
     * 获取验证失败的字段名
     * 
     * @return 字段名
     */
    public String getField() {
        return field;
    }
    
    /**
     * 获取错误信息
     * 
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return message;
    }
}