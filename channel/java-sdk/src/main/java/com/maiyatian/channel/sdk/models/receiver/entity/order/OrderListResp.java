/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderBaseData;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 查询订单列表响应数据（三方返回给麦芽田）
 * <p>
 * 三方返回麦芽田的分页订单列表
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderListResp {

    /**
     * 订单数据列表
     */
    @Valid
    @JsonProperty("data")
    private List<OrderBaseData> data;

    /**
     * 当前页码
     */
    @JsonProperty("page")
    private Integer page;

    /**
     * 订单总数
     */
    @JsonProperty("total")
    private Integer total;

    /**
     * 总页数
     */
    @JsonProperty("total_page")
    private Integer totalPage;

    /**
     * 是否最后一页
     */
    @JsonProperty("is_last")
    private Boolean isLast;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderListResp() {
    }

    /**
     * 全参构造函数
     */
    public OrderListResp(List<OrderBaseData> data, Integer page, Integer total, Integer totalPage, Boolean isLast) {
        this.data = data;
        this.page = page;
        this.total = total;
        this.totalPage = totalPage;
        this.isLast = isLast;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public List<OrderBaseData> getData() {
        return data;
    }

    public void setData(List<OrderBaseData> data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderListResp that = (OrderListResp) o;
        return Objects.equals(data, that.data) &&
               Objects.equals(page, that.page) &&
               Objects.equals(total, that.total) &&
               Objects.equals(totalPage, that.totalPage) &&
               Objects.equals(isLast, that.isLast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, page, total, totalPage, isLast);
    }

    @Override
    public String toString() {
        return "OrderListResp{" +
                "data=" + data +
                ", page=" + page +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", isLast=" + isLast +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查响应数据是否为空
     * 
     * @return 如果数据不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasData() {
        return data != null && !data.isEmpty();
    }

    /**
     * 获取订单数量
     * 
     * @return 当前页面的订单数量
     */
    @JsonIgnore
    public int getOrderCount() {
        return data != null ? data.size() : 0;
    }

    /**
     * 检查是否有下一页
     * 
     * @return 如果有下一页返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasNextPage() {
        if (isLast != null) {
            return !isLast;
        }
        return page != null && totalPage != null && page < totalPage;
    }

    /**
     * 检查是否有上一页
     * 
     * @return 如果有上一页返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasPreviousPage() {
        return page != null && page > 1;
    }

    /**
     * 获取下一页页码
     * 
     * @return 下一页页码，如果没有下一页返回null
     */
    @JsonIgnore
    public Integer getNextPage() {
        return hasNextPage() ? page + 1 : null;
    }

    /**
     * 获取上一页页码
     * 
     * @return 上一页页码，如果没有上一页返回null
     */
    @JsonIgnore
    public Integer getPreviousPage() {
        return hasPreviousPage() ? page - 1 : null;
    }

    /**
     * 检查分页信息是否完整
     * 
     * @return 如果分页信息完整返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasValidPagination() {
        return page != null && page > 0 && total != null && total >= 0 && totalPage != null && totalPage >= 0;
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建订单列表响应的便捷工厂方法
     * 
     * @param data 订单数据列表
     * @param page 当前页码
     * @param total 总数
     * @param totalPage 总页数
     * @param isLast 是否最后一页
     * @return 订单列表响应对象
     */
    public static OrderListResp of(List<OrderBaseData> data, Integer page, Integer total, Integer totalPage, Boolean isLast) {
        return new OrderListResp(data, page, total, totalPage, isLast);
    }

    /**
     * 创建空的订单列表响应
     * 
     * @param page 当前页码
     * @return 空的订单列表响应对象
     */
    public static OrderListResp empty(Integer page) {
        return new OrderListResp(Collections.emptyList(), page, 0, 0, true);
    }

    /**
     * 创建单页订单列表响应
     * 
     * @param data 订单数据列表
     * @return 单页订单列表响应对象
     */
    public static OrderListResp singlePage(List<OrderBaseData> data) {
        int total = data != null ? data.size() : 0;
        return new OrderListResp(data, 1, total, 1, true);
    }
}