/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 获取授权码请求参数（用于组装授权 URL）
 * <p>
 * 三方服务组装授权页面 URL 时使用的参数
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 三方平台引导商户到麦芽田授权页面完成授权
 * 
 * <h3>授权流程：</h3>
 * <ol>
 * <li>三方组装授权页面 URL（包含本请求参数）</li>
 * <li>商户在麦芽田授权页面登录并同意授权</li>
 * <li>授权成功后，授权码 code 通过回调地址返回给三方</li>
 * <li>三方使用 code 调用 AccessToken 接口向麦芽田换取访问令牌</li>
 * </ol>
 * 
 * <h3>URL 组装示例：</h3>
 * <pre>
 * http://saas.open.test.maiyatian.com/oauth/?app_key=xxx&redirect_uri=xxx&view=web&response_type=code&shop_id=xxx&state=xxx
 * </pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CreateCodeReq {

    /**
     * 应用密钥（app_key），由麦芽田开放平台分配
     */
    @JsonProperty("app_key")
    private String appKey;

    /**
     * 授权步骤类型，固定值："code"
     */
    @JsonProperty("response_type")
    private String responseType = "code";

    /**
     * 授权页面类型："web"（PC端）、"h5"（移动端）
     */
    @JsonProperty("view")
    private String view = "web";

    /**
     * 授权成功后的回调地址（三方的回调地址）
     */
    @JsonProperty("redirect_uri")
    private String redirectUri;

    /**
     * 平台方渠道唯一 ID（三方平台的门店标识）
     */
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 自定义状态值，用于防止 CSRF 攻击
     */
    @JsonProperty("state")
    private String state;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public CreateCodeReq() {
    }

    /**
     * 全参构造函数
     */
    public CreateCodeReq(String appKey, String responseType, String view, String redirectUri,
                        String shopId, String state) {
        this.appKey = appKey;
        this.responseType = responseType;
        this.view = view;
        this.redirectUri = redirectUri;
        this.shopId = shopId;
        this.state = state;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCodeReq that = (CreateCodeReq) o;
        return Objects.equals(appKey, that.appKey) &&
               Objects.equals(responseType, that.responseType) &&
               Objects.equals(view, that.view) &&
               Objects.equals(redirectUri, that.redirectUri) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appKey, responseType, view, redirectUri, shopId, state);
    }

    @Override
    public String toString() {
        return "CreateCodeReq{" +
                "appKey='" + appKey + '\'' +
                ", responseType='" + responseType + '\'' +
                ", view='" + view + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", shopId='" + shopId + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}