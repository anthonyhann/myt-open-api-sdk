/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 查询麦芽田门店信息响应数据
 * <p>
 * 麦芽田返回的门店基本信息和营业状态
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopInfoResp {

    /**
     * 平台方渠道 ID
     */
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 门店名称
     */
    @JsonProperty("shop_name")
    private String shopName;

    /**
     * 商户名称
     */
    @JsonProperty("merchant_name")
    private String merchantName;

    /**
     * 门店联系电话
     */
    @JsonProperty("phone")
    private String phone;

    /**
     * 门店状态：1-营业中，2-休息中，3-已打烊
     */
    @JsonProperty("status")
    private Long status;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ShopInfoResp() {
    }

    /**
     * 全参构造函数
     */
    public ShopInfoResp(String shopId, String shopName, String merchantName, String phone, Long status) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.merchantName = merchantName;
        this.phone = phone;
        this.status = status;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    // ==================== 便利方法 ====================

    /**
     * 获取门店状态描述
     * 
     * @return 状态描述
     */
    @JsonIgnore
    public String getStatusDescription() {
        if (status == null) return "未知";
        switch (status.intValue()) {
            case 1: return "营业中";
            case 2: return "休息中";
            case 3: return "已打烊";
            default: return "未知状态";
        }
    }

    /**
     * 检查门店是否营业
     * 
     * @return 如果营业返回true
     */
    @JsonIgnore
    public boolean isOpen() {
        return status != null && status == 1;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopInfoResp that = (ShopInfoResp) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(shopName, that.shopName) &&
               Objects.equals(merchantName, that.merchantName) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, shopName, merchantName, phone, status);
    }

    @Override
    public String toString() {
        return "ShopInfoResp{" +
                "shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}