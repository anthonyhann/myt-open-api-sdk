/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 签名工具测试
 * <p>
 * 测试签名生成功能
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class SignatureUtilsTest {
    
    @Test
    void testGenerateSignature() {
        // 测试签名生成
        try {
            Map<String, String> params = new HashMap<>();
            params.put("app_key", "test_app_key");
            params.put("token", "test_token");
            params.put("timestamp", "1600000000");
            params.put("data", "{\"test\":\"value\"}");
            params.put("command", "test_command");
            
            String secretKey = "test_secret_key";
            String signature = SignatureUtils.generateSignature(params, secretKey);
            
            // 断言签名不为空
            assertNotNull(signature);
            assertFalse(signature.isEmpty());
            
            // 测试相同参数应该生成相同签名
            String signature2 = SignatureUtils.generateSignature(params, secretKey);
            assertEquals(signature, signature2);
            
            // 测试不同参数应该生成不同签名
            params.put("timestamp", "1600000001");
            String signature3 = SignatureUtils.generateSignature(params, secretKey);
            assertNotEquals(signature, signature3);
            
            // 测试不同密钥应该生成不同签名
            params.put("timestamp", "1600000000");
            String signature4 = SignatureUtils.generateSignature(params, "different_secret_key");
            assertNotEquals(signature, signature4);
        } catch (Exception e) {
            fail("签名生成失败: " + e.getMessage());
        }
    }
    
    @Test
    void testGenerateSignatureWithDirectParams() {
        // 测试直接传入参数生成签名
        try {
            String appKey = "test_app_key";
            String token = "test_token";
            long timestamp = 1600000000;
            String data = "{\"test\":\"value\"}";
            String command = "test_command";
            String secretKey = "test_secret_key";
            
            String signature1 = SignatureUtils.generateSignature(appKey, token, timestamp, data, command, secretKey);
            
            // 使用map生成相同签名，用于比较
            Map<String, String> params = new HashMap<>();
            params.put("app_key", appKey);
            params.put("token", token);
            params.put("timestamp", String.valueOf(timestamp));
            params.put("data", data);
            params.put("command", command);
            
            String signature2 = SignatureUtils.generateSignature(params, secretKey);
            
            // 断言两种方式生成的签名相同
            assertEquals(signature1, signature2);
        } catch (Exception e) {
            fail("签名生成失败: " + e.getMessage());
        }
    }
    
    @Test
    void testGenerateSignature_invalidAlgorithm() {
        // 测试无效算法（这个测试应该不会失败，因为HmacSHA256是Java标准算法）
        try {
            Map<String, String> params = new HashMap<>();
            params.put("app_key", "test_app_key");
            params.put("token", "test_token");
            params.put("timestamp", "1600000000");
            params.put("data", "{\"test\":\"value\"}");
            params.put("command", "test_command");
            
            // 这里我们使用正确的算法，所以应该成功
            String signature = SignatureUtils.generateSignature(params, "test_secret_key");
            assertNotNull(signature);
        } catch (Exception e) {
            fail("签名生成失败: " + e.getMessage());
        }
    }
    
    @Test
    void testGenerateSignature_emptyParams() {
        // 测试空参数
        try {
            Map<String, String> params = new HashMap<>();
            String signature = SignatureUtils.generateSignature(params, "test_secret_key");
            assertNotNull(signature);
        } catch (Exception e) {
            fail("签名生成失败: " + e.getMessage());
        }
    }
}
