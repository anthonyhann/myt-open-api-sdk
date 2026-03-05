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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 获取访问令牌响应数据
 * <p>
 * 麦芽田返回的访问令牌和刷新令牌
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class AccessTokenResp {

    /**
     * 平台方渠道 ID
     */
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 访问令牌，有效期 30 天
     */
    @NotBlank(message = "访问令牌不能为空")
    @JsonProperty("token")
    private String token;

    /**
     * 刷新令牌，有效期 30 天
     */
    @NotBlank(message = "刷新令牌不能为空")
    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * 访问令牌过期时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("expire_time")
    private Long expireTime;

    /**
     * 刷新令牌过期时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("refresh_expire_time")
    private Long refreshExpireTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public AccessTokenResp() {
    }

    /**
     * 核心参数构造函数
     */
    public AccessTokenResp(String shopId, String token, String refreshToken) {
        this.shopId = shopId;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    /**
     * 全参构造函数
     */
    public AccessTokenResp(String shopId, String token, String refreshToken, 
                          Long expireTime, Long refreshExpireTime) {
        this.shopId = shopId;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
        this.refreshExpireTime = refreshExpireTime;
    }

    // ==================== 便利方法 ====================

    /**
     * 判断访问令牌是否已过期
     * 
     * @return true 如果已过期，false 如果未过期或过期时间未设置
     */
    @JsonIgnore
    public boolean isTokenExpired() {
        if (expireTime == null || expireTime == 0) {
            return false; // 永久有效或未设置过期时间
        }
        return Instant.now().getEpochSecond() >= expireTime;
    }

    /**
     * 判断刷新令牌是否已过期
     * 
     * @return true 如果已过期，false 如果未过期或过期时间未设置
     */
    @JsonIgnore
    public boolean isRefreshTokenExpired() {
        if (refreshExpireTime == null || refreshExpireTime == 0) {
            return false; // 永久有效或未设置过期时间
        }
        return Instant.now().getEpochSecond() >= refreshExpireTime;
    }

    /**
     * 判断是否需要刷新令牌（距离过期时间少于指定秒数）
     * 
     * @param beforeSeconds 提前刷新的秒数，默认建议 7 天（7 * 24 * 3600）
     * @return true 如果需要刷新，false 如果不需要
     */
    @JsonIgnore
    public boolean needsRefresh(long beforeSeconds) {
        if (expireTime == null || expireTime == 0) {
            return false; // 永久有效或未设置过期时间
        }
        return Instant.now().getEpochSecond() >= (expireTime - beforeSeconds);
    }

    /**
     * 判断是否需要刷新令牌（默认提前 7 天）
     * 
     * @return true 如果需要刷新，false 如果不需要
     */
    @JsonIgnore
    public boolean needsRefresh() {
        return needsRefresh(7 * 24 * 3600L); // 提前 7 天
    }

    /**
     * 获取访问令牌过期时间的 Instant 对象
     * 
     * @return 过期时间，如果未设置则返回 null
     */
    @JsonIgnore
    public Instant getExpireTimeAsInstant() {
        return expireTime != null && expireTime > 0 ? Instant.ofEpochSecond(expireTime) : null;
    }

    /**
     * 获取刷新令牌过期时间的 Instant 对象
     * 
     * @return 过期时间，如果未设置则返回 null
     */
    @JsonIgnore
    public Instant getRefreshExpireTimeAsInstant() {
        return refreshExpireTime != null && refreshExpireTime > 0 ? 
                Instant.ofEpochSecond(refreshExpireTime) : null;
    }

    /**
     * 获取访问令牌过期时间的格式化字符串
     * 
     * @return 格式化的过期时间字符串，如果未设置则返回 "永久有效"
     */
    @JsonIgnore
    public String getFormattedExpireTime() {
        return formatTimestamp(expireTime);
    }

    /**
     * 获取刷新令牌过期时间的格式化字符串
     * 
     * @return 格式化的过期时间字符串，如果未设置则返回 "永久有效"
     */
    @JsonIgnore
    public String getFormattedRefreshExpireTime() {
        return formatTimestamp(refreshExpireTime);
    }

    /**
     * 获取访问令牌剩余有效时间（秒）
     * 
     * @return 剩余秒数，如果已过期则返回 0，如果永久有效则返回 Long.MAX_VALUE
     */
    @JsonIgnore
    public long getRemainingSeconds() {
        if (expireTime == null || expireTime == 0 || expireTime == Long.MAX_VALUE) {
            return Long.MAX_VALUE; // 永久有效
        }
        long remaining = expireTime - Instant.now().getEpochSecond();
        return Math.max(0, remaining);
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 格式化时间戳
     */
    private String formatTimestamp(Long timestamp) {
        if (timestamp == null || timestamp == 0) {
            return "永久有效";
        }

        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp), 
                ZoneId.systemDefault()
        );
        
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

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

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessTokenResp that = (AccessTokenResp) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(token, that.token) &&
               Objects.equals(refreshToken, that.refreshToken) &&
               Objects.equals(expireTime, that.expireTime) &&
               Objects.equals(refreshExpireTime, that.refreshExpireTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, token, refreshToken, expireTime, refreshExpireTime);
    }

    @Override
    public String toString() {
        return "AccessTokenResp{" +
                "shopId='" + shopId + '\'' +
                ", token='" + (token != null ? token.substring(0, Math.min(token.length(), 8)) + "***" : "null") + '\'' +
                ", refreshToken='" + (refreshToken != null ? refreshToken.substring(0, Math.min(refreshToken.length(), 8)) + "***" : "null") + '\'' +
                ", expireTime=" + expireTime + " (" + getFormattedExpireTime() + ")" +
                ", refreshExpireTime=" + refreshExpireTime + " (" + getFormattedRefreshExpireTime() + ")" +
                ", isTokenExpired=" + isTokenExpired() +
                ", needsRefresh=" + needsRefresh() +
                '}';
    }
}