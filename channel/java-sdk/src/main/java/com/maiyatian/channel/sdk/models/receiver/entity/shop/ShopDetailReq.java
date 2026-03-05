/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 查询门店详情请求参数（三方接收麦芽田请求）
 * <p>
 * 麦芽田向三方查询门店详情时的请求参数
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 查询三方门店基本信息
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>此结构用于麦芽田调用三方的查询接口</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopDetailReq {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @JsonProperty("shop_id")
    private String shopId;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ShopDetailReq() {
    }

    /**
     * 全参构造函数
     */
    public ShopDetailReq(String shopId) {
        this.shopId = shopId;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDetailReq that = (ShopDetailReq) o;
        return Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId);
    }

    @Override
    public String toString() {
        return "ShopDetailReq{" +
                "shopId=" + (shopId != null ? "'" + shopId + "'" : "null") +
                '}';
    }
}