/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * 查询门店列表请求参数（三方接收麦芽田请求）
 * <p>
 * 麦芽田向三方查询门店列表时的请求参数
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田查询三方商户名下的所有门店
 * 
 * <h3>分页方式：</h3>
 * <ul>
 * <li>普通分页：使用 Page 和 PageSize</li>
 * <li>游标分页：使用 ScrollId</li>
 * </ul>
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>页码从1开始</li>
 * <li>建议每页数量为10-100条</li>
 * <li>游标分页适用于大数据量场景</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopListReq {

    /**
     * 页码（从 1 开始）
     */
    @Min(value = 1, message = "页码必须大于等于1")
    @JsonProperty("page")
    private Integer page;

    /**
     * 每页数量（建议值：10-100）
     */
    @Min(value = 1, message = "每页数量必须大于0")
    @Max(value = 1000, message = "每页数量不能超过1000")
    @JsonProperty("page_size")
    private Integer pageSize;

    /**
     * 游标 ID
     */
    @JsonProperty("scroll_id")
    private String scrollId;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ShopListReq() {
    }

    /**
     * 普通分页构造函数
     * 
     * @param page 页码
     * @param pageSize 每页数量
     */
    public ShopListReq(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    /**
     * 游标分页构造函数
     * 
     * @param scrollId 游标ID
     */
    public ShopListReq(String scrollId) {
        this.scrollId = scrollId;
    }

    /**
     * 全参构造函数
     * 
     * @param page 页码
     * @param pageSize 每页数量
     * @param scrollId 游标ID
     */
    public ShopListReq(Integer page, Integer pageSize, String scrollId) {
        this.page = page;
        this.pageSize = pageSize;
        this.scrollId = scrollId;
    }

    // ==================== Getter 和 Setter 方法 ====================

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

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopListReq that = (ShopListReq) o;
        return Objects.equals(page, that.page) &&
               Objects.equals(pageSize, that.pageSize) &&
               Objects.equals(scrollId, that.scrollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, scrollId);
    }

    @Override
    public String toString() {
        return "ShopListReq{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", scrollId='" + scrollId + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查是否使用普通分页
     * 
     * @return 如果指定了页码和页大小返回true，否则返回false
     */
    @JsonIgnore
    public boolean isNormalPagination() {
        return page != null && pageSize != null && page > 0 && pageSize > 0;
    }

    /**
     * 检查是否使用游标分页
     * 
     * @return 如果指定了游标ID返回true，否则返回false
     */
    @JsonIgnore
    public boolean isScrollPagination() {
        return scrollId != null && !scrollId.trim().isEmpty();
    }

    /**
     * 检查分页参数是否有效
     * 
     * @return 如果普通分页或游标分页参数有效返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValidPagination() {
        return isNormalPagination() || isScrollPagination();
    }

    /**
     * 获取分页类型描述
     * 
     * @return 分页类型的描述文本
     */
    @JsonIgnore
    public String getPaginationType() {
        if (isScrollPagination()) {
            return "游标分页";
        } else if (isNormalPagination()) {
            return "普通分页";
        } else {
            return "无效分页";
        }
    }

    /**
     * 重置为第一页
     */
    public void resetToFirstPage() {
        this.page = 1;
        this.scrollId = null;
    }

    /**
     * 移动到下一页（仅适用于普通分页）
     */
    public void nextPage() {
        if (page != null) {
            this.page = page + 1;
        }
    }

    /**
     * 移动到上一页（仅适用于普通分页）
     */
    public void previousPage() {
        if (page != null && page > 1) {
            this.page = page - 1;
        }
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建普通分页请求
     * 
     * @param page 页码
     * @param pageSize 每页数量
     * @return 门店列表请求对象
     */
    public static ShopListReq ofPage(int page, int pageSize) {
        return new ShopListReq(page, pageSize);
    }

    /**
     * 创建游标分页请求
     * 
     * @param scrollId 游标ID
     * @return 门店列表请求对象
     */
    public static ShopListReq ofScroll(String scrollId) {
        return new ShopListReq(scrollId);
    }

    /**
     * 创建第一页请求（默认每页20条）
     * 
     * @return 门店列表请求对象
     */
    public static ShopListReq firstPage() {
        return new ShopListReq(1, 20);
    }

    /**
     * 创建第一页请求（指定每页数量）
     * 
     * @param pageSize 每页数量
     * @return 门店列表请求对象
     */
    public static ShopListReq firstPage(int pageSize) {
        return new ShopListReq(1, pageSize);
    }

    /**
     * 构建器模式创建请求
     * 
     * @return 门店列表请求构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 门店列表请求构建器
     */
    public static class Builder {
        private Integer page;
        private Integer pageSize;
        private String scrollId;

        /**
         * 设置页码
         * 
         * @param page 页码
         * @return 构建器实例
         */
        public Builder page(int page) {
            this.page = page;
            return this;
        }

        /**
         * 设置每页数量
         * 
         * @param pageSize 每页数量
         * @return 构建器实例
         */
        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * 设置游标ID
         * 
         * @param scrollId 游标ID
         * @return 构建器实例
         */
        public Builder scrollId(String scrollId) {
            this.scrollId = scrollId;
            return this;
        }

        /**
         * 构建门店列表请求对象
         * 
         * @return 门店列表请求对象
         */
        public ShopListReq build() {
            return new ShopListReq(page, pageSize, scrollId);
        }
    }
}