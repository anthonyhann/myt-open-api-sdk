/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 营销活动信息
 * <p>
 * 记录订单参与的各类营销活动及优惠金额分担
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ActivityInfo {

    /**
     * 活动类型：1-满减活动，2-折扣活动，3-其他
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 活动说明（如："满30减10"）
     */
    @JsonProperty("title")
    private String title;

    /**
     * 商户承担优惠金额（单位：分）
     */
    @JsonProperty("merchant")
    private Integer merchant;

    /**
     * 活动减免总金额（单位：分）
     */
    @JsonProperty("reduce")
    private Integer reduce;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ActivityInfo() {
    }

    /**
     * 全参构造函数
     */
    public ActivityInfo(Integer type, String title, Integer merchant, Integer reduce) {
        this.type = type;
        this.title = title;
        this.merchant = merchant;
        this.reduce = reduce;
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

    public Integer getMerchant() {
        return merchant;
    }

    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }

    public Integer getReduce() {
        return reduce;
    }

    public void setReduce(Integer reduce) {
        this.reduce = reduce;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityInfo that = (ActivityInfo) o;
        return Objects.equals(type, that.type) &&
               Objects.equals(title, that.title) &&
               Objects.equals(merchant, that.merchant) &&
               Objects.equals(reduce, that.reduce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, merchant, reduce);
    }

    @Override
    public String toString() {
        return "ActivityInfo{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", merchant=" + merchant +
                ", reduce=" + reduce +
                '}';
    }
}