/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.shop;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 查询麦芽田门店信息请求参数
 * <p>
 * 三方服务向麦芽田查询门店信息
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 三方查询门店在麦芽田平台的基本信息和状态
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopInfoReq {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ShopInfoReq() {
    }

    /**
     * 全参构造函数
     */
    public ShopInfoReq(String shopId) {
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
        ShopInfoReq that = (ShopInfoReq) o;
        return Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId);
    }

    @Override
    public String toString() {
        return "ShopInfoReq{" +
                "shopId='" + shopId + '\'' +
                '}';
    }
}