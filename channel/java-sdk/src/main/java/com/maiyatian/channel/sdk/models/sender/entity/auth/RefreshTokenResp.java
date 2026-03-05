/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 刷新访问令牌响应数据
 * <p>
 * 麦芽田返回的新访问令牌和刷新令牌
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RefreshTokenResp {

    /**
     * 新的访问令牌，有效期 30 天
     */
    @NotBlank(message = "访问令牌不能为空")
    @JsonProperty("token")
    private String token;

    /**
     * 新的刷新令牌，有效期 30 天
     */
    @NotBlank(message = "刷新令牌不能为空")
    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * 新访问令牌过期时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("expire_time")
    private Long expireTime;

    /**
     * 新刷新令牌过期时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("refresh_expire_time")
    private Long refreshExpireTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public RefreshTokenResp() {
    }

    /**
     * 全参构造函数
     */
    public RefreshTokenResp(String token, String refreshToken, Long expireTime, Long refreshExpireTime) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
        this.refreshExpireTime = refreshExpireTime;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getRefreshExpireTime() {
        return refreshExpireTime;
    }

    public void setRefreshExpireTime(Long refreshExpireTime) {
        this.refreshExpireTime = refreshExpireTime;
    }

    // ==================== 便利方法 ====================

    /**
     * 获取格式化的过期时间
     * 
     * @return 格式化后的过期时间字符串
     */
    @JsonIgnore
    public String getFormattedExpireTime() {
        if (expireTime == null) return "未知";
        if (expireTime == Long.MAX_VALUE) return "永久有效";
        try {
            return Instant.ofEpochSecond(expireTime)
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return "无效时间";
        }
    }

    /**
     * 获取格式化的刷新令牌过期时间
     * 
     * @return 格式化后的刷新令牌过期时间字符串
     */
    @JsonIgnore
    public String getFormattedRefreshExpireTime() {
        if (refreshExpireTime == null) return "未知";
        if (refreshExpireTime == Long.MAX_VALUE) return "永久有效";
        try {
            return Instant.ofEpochSecond(refreshExpireTime)
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return "无效时间";
        }
    }

    /**
     * 检查访问令牌是否需要刷新（剩余时间少于1小时）
     * 
     * @return 如果需要刷新返回true
     */
    @JsonIgnore
    public boolean needsRefresh() {
        if (expireTime == null) return true;
        long currentTime = System.currentTimeMillis() / 1000;
        long timeLeft = expireTime - currentTime;
        return timeLeft < 3600; // 少于1小时需要刷新
    }

    /**
     * 检查访问令牌是否已过期
     * 
     * @return 如果已过期返回true
     */
    @JsonIgnore
    public boolean isExpired() {
        if (expireTime == null) return true;
        long currentTime = System.currentTimeMillis() / 1000;
        return currentTime >= expireTime;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshTokenResp that = (RefreshTokenResp) o;
        return Objects.equals(token, that.token) &&
               Objects.equals(refreshToken, that.refreshToken) &&
               Objects.equals(expireTime, that.expireTime) &&
               Objects.equals(refreshExpireTime, that.refreshExpireTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, refreshToken, expireTime, refreshExpireTime);
    }

    @Override
    public String toString() {
        return "RefreshTokenResp{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expireTime=" + expireTime +
                ", refreshExpireTime=" + refreshExpireTime +
                '}';
    }
}