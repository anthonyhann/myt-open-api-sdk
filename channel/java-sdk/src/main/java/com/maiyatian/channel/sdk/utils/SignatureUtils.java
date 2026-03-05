/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

/**
 * 签名工具类
 * <p>
 * 提供麦芽田开放平台接口签名生成和验证功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public final class SignatureUtils {

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    /**
     * 私有构造函数，防止实例化
     */
    private SignatureUtils() {
        throw new UnsupportedOperationException("工具类不允许实例化");
    }

    /**
     * 生成请求签名
     * <p>
     * 签名规则：
     * <ol>
     * <li>提取请求体中的 app_key、token、timestamp、data 字段</li>
     * <li>按 key 进行字典序排序</li>
     * <li>用半角逗号连接生成字符串（格式：key1=value1,key2=value2）</li>
     * <li>对字符串进行 UTF8 编码</li>
     * <li>使用 appSecret 计算 HmacSHA256 值</li>
     * <li>结果使用 URL 安全的 Base64 编码</li>
     * </ol>
     * 
     * @param parameters 请求参数 Map
     * @param appSecret 应用密钥
     * @return 签名字符串
     * @throws IllegalArgumentException 如果参数为空或密钥为空
     * @throws RuntimeException 如果签名生成失败
     */
    public static String generateSignature(Map<String, Object> parameters, String appSecret) {
        if (parameters == null || parameters.isEmpty()) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        if (appSecret == null || appSecret.trim().isEmpty()) {
            throw new IllegalArgumentException("应用密钥不能为空");
        }

        try {
            // 使用 TreeMap 自动按 key 排序
            Map<String, Object> sortedParams = new TreeMap<>(parameters);
            
            // 构建待签名字符串
            StringJoiner joiner = new StringJoiner(",");
            for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
                if (entry.getValue() != null) {
                    joiner.add(entry.getKey() + "=" + entry.getValue());
                }
            }
            String dataToSign = joiner.toString();

            // 计算 HmacSHA256 签名
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    appSecret.getBytes(StandardCharsets.UTF_8), 
                    HMAC_SHA256_ALGORITHM
            );
            mac.init(secretKeySpec);
            byte[] signature = mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));

            // 使用 URL 安全的 Base64 编码
            return Base64.getUrlEncoder().encodeToString(signature);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("生成签名失败", e);
        }
    }

    /**
     * 验证请求签名
     * 
     * @param parameters 请求参数 Map
     * @param appSecret 应用密钥
     * @param expectedSignature 期望的签名值
     * @return true 如果签名验证通过，false 如果验证失败
     */
    public static boolean verifySignature(Map<String, Object> parameters, String appSecret, String expectedSignature) {
        if (expectedSignature == null || expectedSignature.trim().isEmpty()) {
            return false;
        }

        try {
            String actualSignature = generateSignature(parameters, appSecret);
            return expectedSignature.equals(actualSignature);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成时间戳（Unix 秒）
     * 
     * @return 当前时间戳
     */
    public static long generateTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 验证时间戳是否在允许的时间范围内（10分钟误差）
     * 
     * @param timestamp 要验证的时间戳
     * @return true 如果在允许范围内，false 如果超出范围
     */
    public static boolean isValidTimestamp(long timestamp) {
        return isValidTimestamp(timestamp, 600); // 10 分钟 = 600 秒
    }

    /**
     * 验证时间戳是否在允许的时间范围内
     * 
     * @param timestamp 要验证的时间戳
     * @param allowedDifferenceSeconds 允许的时间差（秒）
     * @return true 如果在允许范围内，false 如果超出范围
     */
    public static boolean isValidTimestamp(long timestamp, long allowedDifferenceSeconds) {
        long currentTimestamp = generateTimestamp();
        long difference = Math.abs(currentTimestamp - timestamp);
        return difference <= allowedDifferenceSeconds;
    }
}