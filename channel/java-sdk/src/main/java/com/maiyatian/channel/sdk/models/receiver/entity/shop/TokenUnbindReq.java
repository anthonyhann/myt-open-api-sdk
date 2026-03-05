/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 解绑授权令牌请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方解绑授权令牌
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方解除授权绑定
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送解绑请求 → 三方处理解绑逻辑 → 三方清理本地 token
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>三方收到解绑通知后应清理本地存储的 token</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class TokenUnbindReq {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 要解绑的访问令牌
     */
    @JsonProperty("token")
    private String token;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public TokenUnbindReq() {
    }

    /**
     * 全参构造函数
     */
    public TokenUnbindReq(String shopId, String token) {
        this.shopId = shopId;
        this.token = token;
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

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenUnbindReq that = (TokenUnbindReq) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, token);
    }

    @Override
    public String toString() {
        return "TokenUnbindReq{" +
                "shopId='" + shopId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}