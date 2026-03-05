/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 查询订单列表请求参数（三方接收麦芽田请求）
 * <p>
 * 麦芽田向三方查询订单列表时的请求参数
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田查询三方某个时间段的所有订单
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>时间范围不建议超过 31 天</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderListReq {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 查询开始时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 查询结束时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 页码（从 1 开始）
     */
    @JsonProperty("page")
    private Integer page;

    /**
     * 每页数量（建议值：10-100）
     */
    @JsonProperty("page_size")
    private Integer pageSize;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderListReq() {
    }

    /**
     * 全参构造函数
     */
    public OrderListReq(String shopId, Long startTime, Long endTime, Integer page, Integer pageSize) {
        this.shopId = shopId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.page = page;
        this.pageSize = pageSize;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderListReq that = (OrderListReq) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(startTime, that.startTime) &&
               Objects.equals(endTime, that.endTime) &&
               Objects.equals(page, that.page) &&
               Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, startTime, endTime, page, pageSize);
    }

    @Override
    public String toString() {
        return "OrderListReq{" +
                "shopId='" + shopId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查请求参数是否有效
     * 
     * @return 如果基本参数有效返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return shopId != null && !shopId.trim().isEmpty();
    }

    /**
     * 检查时间范围是否有效
     * 
     * @return 如果时间范围有效返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasValidTimeRange() {
        return startTime != null && endTime != null && startTime <= endTime;
    }

    /**
     * 检查分页参数是否有效
     * 
     * @return 如果分页参数有效返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasValidPagination() {
        return page != null && page > 0 && pageSize != null && pageSize > 0;
    }

    /**
     * 获取时间范围（天数）
     * 
     * @return 时间范围天数，如果时间无效返回-1
     */
    @JsonIgnore
    public long getTimeRangeDays() {
        if (!hasValidTimeRange()) {
            return -1;
        }
        return (endTime - startTime) / (24 * 60 * 60);
    }

    /**
     * 创建订单列表查询请求的便捷工厂方法
     * 
     * @param shopId 门店ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param page 页码
     * @param pageSize 页面大小
     * @return 订单列表查询请求对象
     */
    public static OrderListReq of(String shopId, Long startTime, Long endTime, Integer page, Integer pageSize) {
        return new OrderListReq(shopId, startTime, endTime, page, pageSize);
    }

    /**
     * 创建基本查询请求（默认第1页，每页20条）
     * 
     * @param shopId 门店ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订单列表查询请求对象
     */
    public static OrderListReq basic(String shopId, Long startTime, Long endTime) {
        return new OrderListReq(shopId, startTime, endTime, 1, 20);
    }
}