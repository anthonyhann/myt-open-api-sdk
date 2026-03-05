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
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 查询门店打印机列表请求参数（三方接收麦芽田请求）
 * <p>
 * 麦芽田向三方查询打印机列表时的请求参数
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田查询三方门店绑定的打印机设备
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>门店必须先在打印机平台后台绑定打印机</li>
 * <li>页码从1开始</li>
 * <li>建议每页数量为10-50条</li>
 * <li>支持普通分页和游标分页两种方式</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopPrinterListReq {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 页码（从 1 开始）
     */
    @Min(value = 1, message = "页码必须大于等于1")
    @JsonProperty("page")
    private Integer page;

    /**
     * 每页数量（建议值：10-50）
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
    public ShopPrinterListReq() {
    }

    /**
     * 基本构造函数
     * 
     * @param shopId 门店ID
     */
    public ShopPrinterListReq(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 普通分页构造函数
     * 
     * @param shopId 门店ID
     * @param page 页码
     * @param pageSize 每页数量
     */
    public ShopPrinterListReq(String shopId, Integer page, Integer pageSize) {
        this.shopId = shopId;
        this.page = page;
        this.pageSize = pageSize;
    }

    /**
     * 游标分页构造函数
     * 
     * @param shopId 门店ID
     * @param scrollId 游标ID
     */
    public ShopPrinterListReq(String shopId, String scrollId) {
        this.shopId = shopId;
        this.scrollId = scrollId;
    }

    /**
     * 全参构造函数
     * 
     * @param shopId 门店ID
     * @param page 页码
     * @param pageSize 每页数量
     * @param scrollId 游标ID
     */
    public ShopPrinterListReq(String shopId, Integer page, Integer pageSize, String scrollId) {
        this.shopId = shopId;
        this.page = page;
        this.pageSize = pageSize;
        this.scrollId = scrollId;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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
        ShopPrinterListReq that = (ShopPrinterListReq) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(page, that.page) &&
               Objects.equals(pageSize, that.pageSize) &&
               Objects.equals(scrollId, that.scrollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, page, pageSize, scrollId);
    }

    @Override
    public String toString() {
        return "ShopPrinterListReq{" +
                "shopId='" + shopId + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", scrollId='" + scrollId + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查必要字段是否完整
     * 
     * @return 如果门店ID不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return shopId != null && !shopId.trim().isEmpty();
    }

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
            return "无分页参数";
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
     * 创建基本请求（仅指定门店ID）
     * 
     * @param shopId 门店ID
     * @return 打印机列表请求对象
     */
    public static ShopPrinterListReq of(String shopId) {
        return new ShopPrinterListReq(shopId);
    }

    /**
     * 创建普通分页请求
     * 
     * @param shopId 门店ID
     * @param page 页码
     * @param pageSize 每页数量
     * @return 打印机列表请求对象
     */
    public static ShopPrinterListReq ofPage(String shopId, int page, int pageSize) {
        return new ShopPrinterListReq(shopId, page, pageSize);
    }

    /**
     * 创建游标分页请求
     * 
     * @param shopId 门店ID
     * @param scrollId 游标ID
     * @return 打印机列表请求对象
     */
    public static ShopPrinterListReq ofScroll(String shopId, String scrollId) {
        return new ShopPrinterListReq(shopId, scrollId);
    }

    /**
     * 创建第一页请求（默认每页20条）
     * 
     * @param shopId 门店ID
     * @return 打印机列表请求对象
     */
    public static ShopPrinterListReq firstPage(String shopId) {
        return new ShopPrinterListReq(shopId, 1, 20);
    }

    /**
     * 创建第一页请求（指定每页数量）
     * 
     * @param shopId 门店ID
     * @param pageSize 每页数量
     * @return 打印机列表请求对象
     */
    public static ShopPrinterListReq firstPage(String shopId, int pageSize) {
        return new ShopPrinterListReq(shopId, 1, pageSize);
    }

    /**
     * 构建器模式创建请求
     * 
     * @return 打印机列表请求构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 门店打印机列表请求构建器
     */
    public static class Builder {
        private String shopId;
        private Integer page;
        private Integer pageSize;
        private String scrollId;

        /**
         * 设置门店ID
         * 
         * @param shopId 门店ID
         * @return 构建器实例
         */
        public Builder shopId(String shopId) {
            this.shopId = shopId;
            return this;
        }

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
         * 构建门店打印机列表请求对象
         * 
         * @return 门店打印机列表请求对象
         */
        public ShopPrinterListReq build() {
            return new ShopPrinterListReq(shopId, page, pageSize, scrollId);
        }
    }
}