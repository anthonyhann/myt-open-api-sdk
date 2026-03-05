/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 商品套餐分组
 * <p>
 * 用于表示套餐类商品的分组结构（如：主食、小料、饮料）
 * </p>
 * 
 * <h3>示例：</h3>
 * <pre>
 * 套餐A（汉堡套餐）
 *   - 主食组：汉堡×1
 *   - 小食组：薯条×1
 *   - 饮料组：可乐×1
 * </pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderGoodsSkuAttributeGroup {

    /**
     * 分组名称（如："主食"、"小料"、"饮料"）
     */
    @JsonProperty("group_name")
    private String groupName;

    /**
     * 该分组下的商品列表
     */
    @Valid
    @JsonProperty("items")
    private List<OrderGoodsSkuAttribute> items;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderGoodsSkuAttributeGroup() {
    }

    /**
     * 全参构造函数
     */
    public OrderGoodsSkuAttributeGroup(String groupName, List<OrderGoodsSkuAttribute> items) {
        this.groupName = groupName;
        this.items = items;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<OrderGoodsSkuAttribute> getItems() {
        return items;
    }

    public void setItems(List<OrderGoodsSkuAttribute> items) {
        this.items = items;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoodsSkuAttributeGroup that = (OrderGoodsSkuAttributeGroup) o;
        return Objects.equals(groupName, that.groupName) &&
               Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, items);
    }

    @Override
    public String toString() {
        return "OrderGoodsSkuAttributeGroup{" +
                "groupName='" + groupName + '\'' +
                ", items=" + items +
                '}';
    }
}