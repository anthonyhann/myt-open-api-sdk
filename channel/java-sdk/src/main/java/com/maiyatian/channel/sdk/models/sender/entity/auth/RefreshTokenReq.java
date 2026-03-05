/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 刷新访问令牌请求参数
 * <p>
 * 三方服务使用刷新令牌向麦芽田换取新的访问令牌
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 当 token 过期时，三方使用 refresh_token 向麦芽田换取新的 token
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>刷新得到新的 token 和 refresh_token</li>
 * <li>旧的 token 随即在 5 分钟内失效</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class RefreshTokenReq {

    /**
     * 当前访问令牌
     */
    @NotBlank(message = "访问令牌不能为空")
    @JsonProperty("token")
    private String token;

    /**
     * 刷新令牌
     */
    @NotBlank(message = "刷新令牌不能为空")
    @JsonProperty("refresh_token")
    private String refreshToken;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public RefreshTokenReq() {
    }

    /**
     * 全参构造函数
     */
    public RefreshTokenReq(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
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

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshTokenReq that = (RefreshTokenReq) o;
        return Objects.equals(token, that.token) &&
               Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, refreshToken);
    }

    @Override
    public String toString() {
        return "RefreshTokenReq{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}