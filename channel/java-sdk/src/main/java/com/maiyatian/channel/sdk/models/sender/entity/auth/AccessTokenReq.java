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
 * 获取访问令牌请求参数
 * <p>
 * 三方服务使用授权码向麦芽田换取访问令牌
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 商户完成 OAuth2 授权后，三方使用授权码向麦芽田换取访问令牌
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>此接口的公共参数 token 传空字符串</li>
 * <li>授权码 code 有效期 5 分钟</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class AccessTokenReq {

    /**
     * 授权类型，固定值："shop"
     */
    @NotBlank(message = "授权类型不能为空")
    @JsonProperty("grant_type")
    private String grantType;

    /**
     * 授权码，有效期 5 分钟
     */
    @NotBlank(message = "授权码不能为空")
    @JsonProperty("code")
    private String code;

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 订单分类，麦芽田枚举值
     * <p>可选值参考 {@link com.maiyatian.channel.sdk.models.types.Constants.Category}</p>
     */
    @JsonProperty("category")
    private String category;

    /**
     * 门店名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 店铺类型
     * <p>可选值：["waimai", "shop", "other"]</p>
     */
    @JsonProperty("type")
    private String type;

    /**
     * 门店经度（国测局 GCJ-02 标准）
     */
    @JsonProperty("longitude")
    private String longitude;

    /**
     * 门店纬度（国测局 GCJ-02 标准）
     */
    @JsonProperty("latitude")
    private String latitude;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public AccessTokenReq() {
    }

    /**
     * 核心参数构造函数
     */
    public AccessTokenReq(String grantType, String code, String shopId) {
        this.grantType = grantType;
        this.code = code;
        this.shopId = shopId;
    }

    /**
     * 全参构造函数
     */
    public AccessTokenReq(String grantType, String code, String shopId, String category, 
                         String name, String type, String longitude, String latitude) {
        this.grantType = grantType;
        this.code = code;
        this.shopId = shopId;
        this.category = category;
        this.name = name;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建标准的门店授权请求
     * 
     * @param code 授权码
     * @param shopId 门店ID
     * @return 访问令牌请求对象
     */
    public static AccessTokenReq createShopAuth(String code, String shopId) {
        return new AccessTokenReq("shop", code, shopId);
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    // ==================== 链式设置方法 ====================

    public AccessTokenReq withCategory(String category) {
        this.category = category;
        return this;
    }

    public AccessTokenReq withName(String name) {
        this.name = name;
        return this;
    }

    public AccessTokenReq withType(String type) {
        this.type = type;
        return this;
    }

    public AccessTokenReq withLocation(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        return this;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessTokenReq that = (AccessTokenReq) o;
        return Objects.equals(grantType, that.grantType) &&
               Objects.equals(code, that.code) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(category, that.category) &&
               Objects.equals(name, that.name) &&
               Objects.equals(type, that.type) &&
               Objects.equals(longitude, that.longitude) &&
               Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grantType, code, shopId, category, name, type, longitude, latitude);
    }

    @Override
    public String toString() {
        return "AccessTokenReq{" +
                "grantType='" + grantType + '\'' +
                ", code='" + (code != null ? code.substring(0, Math.min(code.length(), 8)) + "***" : "null") + '\'' +
                ", shopId='" + shopId + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}