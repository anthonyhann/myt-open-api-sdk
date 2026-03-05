/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名生成工具
 * <p>
 * 实现麦芽田开放平台的HmacSHA256签名算法
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class SignatureUtils {
    
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final Base64.Encoder URL_SAFE_ENCODER = Base64.getUrlEncoder().withoutPadding();
    
    /**
     * 生成请求签名
     * <p>
     * 签名规则：
     * 1. 提取请求体中的app_key、token、timestamp、data字段及path中的command
     * 2. 按字母顺序排序
     * 3. 用半角逗号连接：key1=value1,key2=value2,...
     * 4. 使用appSecret计算HmacSHA256值
     * 5. 对结果进行URL安全的Base64编码
     * 
     * @param params 请求参数
     * @param secretKey API密钥，用于签名计算
     * @return 签名字符串
     * @throws NoSuchAlgorithmException 如果算法不存在
     * @throws InvalidKeyException 如果密钥无效
     */
    public static String generateSignature(Map<String, String> params, String secretKey) 
            throws NoSuchAlgorithmException, InvalidKeyException {
        // 使用TreeMap自动按字母顺序排序
        Map<String, String> sortedParams = new TreeMap<>(params);
        
        // 构建待签名字符串
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            first = false;
        }
        String dataToSign = sb.toString();
        
        // 计算HmacSHA256
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));
        
        // 进行URL安全的Base64编码
        return URL_SAFE_ENCODER.encodeToString(hash);
    }
    
    /**
     * 生成请求签名（重载方法）
     * <p>
     * 用于直接传入各个参数生成签名
     * 
     * @param appKey 应用密钥
     * @param token 访问令牌
     * @param timestamp 时间戳
     * @param data 业务数据
     * @param command 命令
     * @param secretKey API密钥
     * @return 签名字符串
     * @throws NoSuchAlgorithmException 如果算法不存在
     * @throws InvalidKeyException 如果密钥无效
     */
    public static String generateSignature(String appKey, String token, long timestamp, 
                                          String data, String command, String secretKey) 
            throws NoSuchAlgorithmException, InvalidKeyException {
        Map<String, String> params = new TreeMap<>();
        params.put("app_key", appKey);
        params.put("token", token);
        params.put("timestamp", String.valueOf(timestamp));
        params.put("data", data);
        params.put("command", command);
        
        return generateSignature(params, secretKey);
    }
}