/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 查询门店列表响应数据（三方返回给麦芽田）
 * <p>
 * 三方返回给麦芽田的分页门店列表
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 三方接收麦芽田的门店列表查询请求后，返回分页的门店数据
 * 
 * <h3>分页信息：</h3>
 * <ul>
 * <li>支持普通分页（Page + TotalPage + Total）</li>
 * <li>支持游标分页（ScrollId + IsLast）</li>
 * </ul>
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>Data字段为必填，其他分页字段为可选</li>
 * <li>IsLast字段表示是否最后一页</li>
 * <li>ScrollId用于游标分页的下一页请求</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopListResp {

    /**
     * 门店数据列表
     */
    @Valid
    @JsonProperty("data")
    private List<ShopItem> data;

    /**
     * 当前页码
     */
    @JsonProperty("page")
    private Integer page;

    /**
     * 总页数
     */
    @JsonProperty("total_page")
    private Integer totalPage;

    /**
     * 门店总数
     */
    @JsonProperty("total")
    private Integer total;

    /**
     * 是否最后一页
     */
    @JsonProperty("is_last")
    private Boolean isLast;

    /**
     * 游标 ID
     */
    @JsonProperty("scroll_id")
    private String scrollId;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public ShopListResp() {
        this.data = new ArrayList<>();
    }

    /**
     * 基本构造函数
     * 
     * @param data 门店数据列表
     */
    public ShopListResp(List<ShopItem> data) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
    }

    /**
     * 普通分页构造函数
     * 
     * @param data 门店数据列表
     * @param page 当前页码
     * @param totalPage 总页数
     * @param total 门店总数
     * @param isLast 是否最后一页
     */
    public ShopListResp(List<ShopItem> data, Integer page, Integer totalPage, Integer total, Boolean isLast) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
        this.page = page;
        this.totalPage = totalPage;
        this.total = total;
        this.isLast = isLast;
    }

    /**
     * 游标分页构造函数
     * 
     * @param data 门店数据列表
     * @param scrollId 游标ID
     * @param isLast 是否最后一页
     */
    public ShopListResp(List<ShopItem> data, String scrollId, Boolean isLast) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
        this.scrollId = scrollId;
        this.isLast = isLast;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public List<ShopItem> getData() {
        return data;
    }

    public void setData(List<ShopItem> data) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
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
        ShopListResp that = (ShopListResp) o;
        return Objects.equals(data, that.data) &&
               Objects.equals(page, that.page) &&
               Objects.equals(totalPage, that.totalPage) &&
               Objects.equals(total, that.total) &&
               Objects.equals(isLast, that.isLast) &&
               Objects.equals(scrollId, that.scrollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, page, totalPage, total, isLast, scrollId);
    }

    @Override
    public String toString() {
        return "ShopListResp{" +
                "data=" + data +
                ", page=" + page +
                ", totalPage=" + totalPage +
                ", total=" + total +
                ", isLast=" + isLast +
                ", scrollId='" + scrollId + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 获取门店数量
     * 
     * @return 当前页的门店数量
     */
    @JsonIgnore
    public int getShopCount() {
        return data != null ? data.size() : 0;
    }

    /**
     * 检查是否有门店数据
     * 
     * @return 如果有门店数据返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasShops() {
        return data != null && !data.isEmpty();
    }

    /**
     * 检查是否为空响应
     * 
     * @return 如果没有门店数据返回true，否则返回false
     */
    @JsonIgnore
    public boolean isEmpty() {
        return !hasShops();
    }

    /**
     * 检查是否使用普通分页
     * 
     * @return 如果包含分页信息返回true，否则返回false
     */
    @JsonIgnore
    public boolean isNormalPagination() {
        return page != null && totalPage != null;
    }

    /**
     * 检查是否使用游标分页
     * 
     * @return 如果包含游标ID返回true，否则返回false
     */
    @JsonIgnore
    public boolean isScrollPagination() {
        return scrollId != null && !scrollId.trim().isEmpty();
    }

    /**
     * 检查是否还有下一页
     * 
     * @return 如果还有下一页返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasNextPage() {
        if (isLast != null) {
            return !isLast;
        }
        if (isNormalPagination()) {
            return page != null && totalPage != null && page < totalPage;
        }
        return false;
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
            return "无分页信息";
        }
    }

    /**
     * 添加门店
     * 
     * @param shop 门店信息
     */
    public void addShop(ShopItem shop) {
        if (shop != null) {
            if (data == null) {
                data = new ArrayList<>();
            }
            data.add(shop);
        }
    }

    /**
     * 添加多个门店
     * 
     * @param shops 门店列表
     */
    public void addShops(List<ShopItem> shops) {
        if (shops != null && !shops.isEmpty()) {
            if (data == null) {
                data = new ArrayList<>();
            }
            data.addAll(shops);
        }
    }

    /**
     * 清空门店数据
     */
    public void clearShops() {
        if (data != null) {
            data.clear();
        }
    }

    /**
     * 获取不可变的门店列表
     * 
     * @return 不可变的门店列表
     */
    @JsonIgnore
    public List<ShopItem> getImmutableShops() {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(data);
    }

    /**
     * 获取有效的门店列表
     * 
     * @return 有效的门店列表
     */
    @JsonIgnore
    public List<ShopItem> getValidShops() {
        if (!hasShops()) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(shop -> shop != null && shop.isValid())
                .collect(Collectors.toList());
    }

    /**
     * 按城市筛选门店
     * 
     * @param cityName 城市名称
     * @return 指定城市的门店列表
     */
    @JsonIgnore
    public List<ShopItem> getShopsByCity(String cityName) {
        if (!hasShops() || cityName == null) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(shop -> shop != null && shop.isInCity(cityName))
                .collect(Collectors.toList());
    }

    /**
     * 按省份筛选门店
     * 
     * @param provinceName 省份名称
     * @return 指定省份的门店列表
     */
    @JsonIgnore
    public List<ShopItem> getShopsByProvince(String provinceName) {
        if (!hasShops() || provinceName == null) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(shop -> shop != null && shop.isInProvince(provinceName))
                .collect(Collectors.toList());
    }

    /**
     * 按门店ID查找门店
     * 
     * @param shopId 门店ID
     * @return 匹配的门店信息，如果未找到返回null
     */
    @JsonIgnore
    public ShopItem findShopById(String shopId) {
        if (!hasShops() || shopId == null) {
            return null;
        }
        return data.stream()
                .filter(shop -> shop != null && shopId.equals(shop.getShopId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取包含坐标的门店列表
     * 
     * @return 包含坐标的门店列表
     */
    @JsonIgnore
    public List<ShopItem> getShopsWithCoordinates() {
        if (!hasShops()) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(shop -> shop != null && shop.hasCoordinates())
                .collect(Collectors.toList());
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建空响应
     * 
     * @return 空的门店列表响应对象
     */
    public static ShopListResp empty() {
        return new ShopListResp();
    }

    /**
     * 创建包含门店数据的响应
     * 
     * @param shops 门店列表
     * @return 门店列表响应对象
     */
    public static ShopListResp of(List<ShopItem> shops) {
        return new ShopListResp(shops);
    }

    /**
     * 创建普通分页响应
     * 
     * @param shops 门店列表
     * @param page 当前页码
     * @param totalPage 总页数
     * @param total 门店总数
     * @return 门店列表响应对象
     */
    public static ShopListResp ofPage(List<ShopItem> shops, int page, int totalPage, int total) {
        return new ShopListResp(shops, page, totalPage, total, page >= totalPage);
    }

    /**
     * 创建游标分页响应
     * 
     * @param shops 门店列表
     * @param scrollId 游标ID
     * @param isLast 是否最后一页
     * @return 门店列表响应对象
     */
    public static ShopListResp ofScroll(List<ShopItem> shops, String scrollId, boolean isLast) {
        return new ShopListResp(shops, scrollId, isLast);
    }

    /**
     * 创建最后一页响应
     * 
     * @param shops 门店列表
     * @return 门店列表响应对象
     */
    public static ShopListResp lastPage(List<ShopItem> shops) {
        ShopListResp resp = new ShopListResp(shops);
        resp.setIsLast(true);
        return resp;
    }

    /**
     * 构建器模式创建响应
     * 
     * @return 门店列表响应构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 门店列表响应构建器
     */
    public static class Builder {
        private final List<ShopItem> data = new ArrayList<>();
        private Integer page;
        private Integer totalPage;
        private Integer total;
        private Boolean isLast;
        private String scrollId;

        /**
         * 添加门店
         * 
         * @param shop 门店信息
         * @return 构建器实例
         */
        public Builder addShop(ShopItem shop) {
            if (shop != null) {
                data.add(shop);
            }
            return this;
        }

        /**
         * 添加多个门店
         * 
         * @param shops 门店列表
         * @return 构建器实例
         */
        public Builder addShops(List<ShopItem> shops) {
            if (shops != null) {
                data.addAll(shops);
            }
            return this;
        }

        /**
         * 设置门店列表
         * 
         * @param shops 门店列表
         * @return 构建器实例
         */
        public Builder data(List<ShopItem> shops) {
            data.clear();
            if (shops != null) {
                data.addAll(shops);
            }
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
         * 设置总页数
         * 
         * @param totalPage 总页数
         * @return 构建器实例
         */
        public Builder totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        /**
         * 设置门店总数
         * 
         * @param total 门店总数
         * @return 构建器实例
         */
        public Builder total(int total) {
            this.total = total;
            return this;
        }

        /**
         * 设置是否最后一页
         * 
         * @param isLast 是否最后一页
         * @return 构建器实例
         */
        public Builder isLast(boolean isLast) {
            this.isLast = isLast;
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
         * 构建门店列表响应对象
         * 
         * @return 门店列表响应对象
         */
        public ShopListResp build() {
            ShopListResp resp = new ShopListResp(data);
            resp.setPage(page);
            resp.setTotalPage(totalPage);
            resp.setTotal(total);
            resp.setIsLast(isLast);
            resp.setScrollId(scrollId);
            return resp;
        }
    }
}