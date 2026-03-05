/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.client;

/**
 * 配置验证错误异常
 * <p>
 * 包含出错的字段名和错误描述信息
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 出错的字段名
     */
    private final String field;

    /**
     * 错误描述信息
     */
    private final String description;

    /**
     * 构造配置验证异常
     * 
     * @param field 出错的字段名
     * @param description 错误描述信息
     */
    public ConfigValidationException(String field, String description) {
        super(String.format("配置验证失败 [%s]: %s", field, description));
        this.field = field;
        this.description = description;
    }

    /**
     * 获取出错的字段名
     * 
     * @return 字段名
     */
    public String getField() {
        return field;
    }

    /**
     * 获取错误描述信息
     * 
     * @return 错误描述
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ConfigValidationException{" +
                "field='" + field + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}