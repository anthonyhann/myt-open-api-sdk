/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 商品套餐属性
 * <p>
 * 表示套餐中的单个商品项
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderGoodsSkuAttribute {

    /**
     * 属性名称（如："烧鸡200g"、"大杯可乐"）
     */
    @JsonProperty("name")
    private String name;

    /**
     * 数量（可选，默认为 1）
     */
    @JsonProperty("number")
    private Integer number;

    /**
     * 单位（可选，如："份"、"个"）
     */
    @JsonProperty("unit")
    private String unit;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderGoodsSkuAttribute() {
    }

    /**
     * 全参构造函数
     */
    public OrderGoodsSkuAttribute(String name, Integer number, String unit) {
        this.name = name;
        this.number = number;
        this.unit = unit;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoodsSkuAttribute that = (OrderGoodsSkuAttribute) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(number, that.number) &&
               Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, unit);
    }

    @Override
    public String toString() {
        return "OrderGoodsSkuAttribute{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                '}';
    }
}