/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.auth;

import com.maiyatian.delivery.sdk.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 * RefreshTokenEntity 单元测试
 * <p>
 * 测试刷新访问令牌实体类的JSON序列化和反序列化功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RefreshTokenEntityTest {

    @Test
    public void testRefreshTokenRequestSerialization() throws IOException {
        // 创建请求对象
        RefreshTokenEntity.Req req = new RefreshTokenEntity.Req();
        req.setToken("123123123");
        req.setRefreshToken("123123123");
        
        // 序列化并反序列化
        String json = JsonUtils.toJson(req);
        RefreshTokenEntity.Req deserialized = JsonUtils.fromJson(json, RefreshTokenEntity.Req.class);
        
        // 验证字段值
        assertEquals(req.getToken(), deserialized.getToken());
        assertEquals(req.getRefreshToken(), deserialized.getRefreshToken());
    }
    
    @Test
    public void testRefreshTokenResponseDeserialization() throws IOException {
        // 模拟响应JSON - 使用camelCase字段名，与Java类保持一致
        String json = "{\"token\":\"123123123\",\"refreshToken\":\"123123123\",\"expireTime\":123123123,\"refreshExpireTime\":123123123}";
        
        // 反序列化
        RefreshTokenEntity.Resp resp = JsonUtils.fromJson(json, RefreshTokenEntity.Resp.class);
        
        // 验证响应
        assertEquals("123123123", resp.getToken());
        assertEquals("123123123", resp.getRefreshToken());
        assertEquals(123123123, resp.getExpireTime());
        assertEquals(123123123, resp.getRefreshExpireTime());
    }
}