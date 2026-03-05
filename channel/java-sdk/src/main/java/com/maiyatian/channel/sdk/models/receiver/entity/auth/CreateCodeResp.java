/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 获取授权码响应数据（麦芽田通过回调返回）
 * <p>
 * 授权成功后麦芽田通过重定向返回给三方的参数
 * </p>
 * 
 * <h3>返回方式：</h3>
 * 授权成功后，麦芽田将浏览器重定向到三方回调地址：
 * <pre>
 * http(s)://redirect_uri?code=CODE&state=STATE
 * </pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CreateCodeResp {

    /**
     * 授权码，用于换取 access_token，有效期 5 分钟，只能使用一次
     */
    @JsonProperty("code")
    private String code;

    /**
     * 自定义状态值，原样返回
     */
    @JsonProperty("state")
    private String state;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public CreateCodeResp() {
    }

    /**
     * 全参构造函数
     */
    public CreateCodeResp(String code, String state) {
        this.code = code;
        this.state = state;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        CreateCodeResp that = (CreateCodeResp) o;
        return Objects.equals(code, that.code) &&
               Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, state);
    }

    @Override
    public String toString() {
        return "CreateCodeResp{" +
                "code='" + code + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}