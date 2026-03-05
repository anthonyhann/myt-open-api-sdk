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
 * AccessTokenEntity 单元测试
 * <p>
 * 测试获取访问令牌实体类的JSON序列化和反序列化功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class AccessTokenEntityTest {

    @Test
    public void testAccessTokenRequestSerialization() throws IOException {
        // 创建请求对象
        AccessTokenEntity.Req req = new AccessTokenEntity.Req();
        req.setGrantType("1");
        req.setCode("your_auth_code");
        req.setMobile("13800138000");
        req.setStoreId("your_store_id");
        req.setCity("北京市");
        req.setCityCode("10001");
        req.setSourceKey("your_source_key");
        req.setPlatform("JD");
        
        // 序列化并反序列化
        String json = JsonUtils.toJson(req);
        AccessTokenEntity.Req deserialized = JsonUtils.fromJson(json, AccessTokenEntity.Req.class);
        
        // 验证字段值
        assertEquals(req.getGrantType(), deserialized.getGrantType());
        assertEquals(req.getCode(), deserialized.getCode());
        assertEquals(req.getMobile(), deserialized.getMobile());
        assertEquals(req.getStoreId(), deserialized.getStoreId());
        assertEquals(req.getCity(), deserialized.getCity());
        assertEquals(req.getCityCode(), deserialized.getCityCode());
        assertEquals(req.getSourceKey(), deserialized.getSourceKey());
        assertEquals(req.getPlatform(), deserialized.getPlatform());
    }
    
    @Test
    public void testAccessTokenResponseDeserialization() throws IOException {
        // 模拟响应JSON - 使用camelCase字段名，与Java类保持一致
        String json = "{\"shopId\":\"1\",\"token\":\"123123123\",\"refreshToken\":\"123123123\",\"expireTime\":123123123,\"refreshExpireTime\":123123123}";
        
        // 反序列化
        AccessTokenEntity.Resp resp = JsonUtils.fromJson(json, AccessTokenEntity.Resp.class);
        
        // 验证响应
        assertEquals("1", resp.getShopId());
        assertEquals("123123123", resp.getToken());
        assertEquals("123123123", resp.getRefreshToken());
        assertEquals(123123123, resp.getExpireTime());
        assertEquals(123123123, resp.getRefreshExpireTime());
    }
}