/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Map;

/**
 * JSON编解码工具
 * <p>
 * 使用Jackson库实现JSON的序列化和反序列化
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class JsonUtils {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            // 忽略未知字段
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            // 允许空字符串转换为null
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            // 关闭日期时间的默认格式化
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
            // 注册Java 8时间模块
            .registerModule(new JavaTimeModule());
    
    /**
     * 将对象转换为JSON字符串
     * 
     * @param object 要序列化的对象
     * @return JSON字符串
     * @throws IOException 如果序列化失败
     */
    public static String toJson(Object object) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }
    
    /**
     * 将JSON字符串转换为指定类型的对象
     * 
     * @param json JSON字符串
     * @param clazz 目标对象类型
     * @param <T> 目标对象类型
     * @return 转换后的对象
     * @throws IOException 如果反序列化失败
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }
    
    /**
     * 将JSON字符串转换为Map
     * 
     * @param json JSON字符串
     * @return Map对象
     * @throws IOException 如果反序列化失败
     */
    public static Map<String, Object> toMap(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, Map.class);
    }
    
    /**
     * 将Map转换为指定类型的对象
     * 
     * @param map Map对象
     * @param clazz 目标对象类型
     * @param <T> 目标对象类型
     * @return 转换后的对象
     * @throws IOException 如果反序列化失败
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }
    
    /**
     * 获取ObjectMapper实例
     * 
     * @return ObjectMapper实例
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}