/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Response 单元测试
 * <p>
 * 测试标准响应结构的JSON序列化和反序列化功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResponseTest {

    @Test
    public void testResponseSerialization() throws Exception {
        // 创建响应对象
        Response response = new Response();
        response.setCode(200);
        response.setData("test_data");
        response.setMessage("成功");
        
        // 序列化并反序列化
        String json = JsonUtils.toJson(response);
        Response deserialized = JsonUtils.fromJson(json, Response.class);
        
        // 验证字段值
        assertEquals(response.getCode(), deserialized.getCode());
        assertEquals(response.getData(), deserialized.getData());
        assertEquals(response.getMessage(), deserialized.getMessage());
    }
    
    @Test
    public void testResponseDeserialization() throws Exception {
        // 模拟响应JSON
        String json = "{\"code\":200,\"data\":\"test_data\",\"message\":\"成功\"}";
        
        // 反序列化
        Response response = JsonUtils.fromJson(json, Response.class);
        
        // 验证响应
        assertEquals(200, response.getCode());
        assertEquals("test_data", response.getData());
        assertEquals("成功", response.getMessage());
    }
}