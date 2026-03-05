/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 发票信息
 * <p>
 * 包含发票抬头、税号、类型等开票所需信息
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvoiceInfo {

    /**
     * 发票抬头类型：1-公司（企业），2-个人
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 发票抬头（公司名称或个人姓名）
     */
    @JsonProperty("title")
    private String title;

    /**
     * 纳税人识别号（企业税号，个人可为空）
     */
    @JsonProperty("taxer_id")
    private String taxerId;

    /**
     * 发票接收邮箱（如：example@qq.com）
     */
    @JsonProperty("email")
    private String email;

    /**
     * 发票形式：1-纸质发票，2-电子发票
     */
    @JsonProperty("form_type")
    private Integer formType;

    /**
     * 电子发票二维码（电子发票专用）
     */
    @JsonProperty("e_qrcode")
    private String eQrcode;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public InvoiceInfo() {
    }

    /**
     * 全参构造函数
     */
    public InvoiceInfo(Integer type, String title, String taxerId, String email, Integer formType, String eQrcode) {
        this.type = type;
        this.title = title;
        this.taxerId = taxerId;
        this.email = email;
        this.formType = formType;
        this.eQrcode = eQrcode;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxerId() {
        return taxerId;
    }

    public void setTaxerId(String taxerId) {
        this.taxerId = taxerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public String getEQrcode() {
        return eQrcode;
    }

    public void setEQrcode(String eQrcode) {
        this.eQrcode = eQrcode;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceInfo that = (InvoiceInfo) o;
        return Objects.equals(type, that.type) &&
               Objects.equals(title, that.title) &&
               Objects.equals(taxerId, that.taxerId) &&
               Objects.equals(email, that.email) &&
               Objects.equals(formType, that.formType) &&
               Objects.equals(eQrcode, that.eQrcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, taxerId, email, formType, eQrcode);
    }

    @Override
    public String toString() {
        return "InvoiceInfo{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", taxerId='" + taxerId + '\'' +
                ", email='" + email + '\'' +
                ", formType=" + formType +
                ", eQrcode='" + eQrcode + '\'' +
                '}';
    }
}