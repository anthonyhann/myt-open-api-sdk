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
 * 查询门店打印机列表响应数据（三方返回给麦芽田）
 * <p>
 * 三方返回麦芽田的打印机设备列表
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 三方接收麦芽田的门店打印机列表查询请求后，返回分页的打印机设备数据
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
public class ShopPrinterListResp {

    /**
     * 打印机设备列表
     */
    @Valid
    @JsonProperty("data")
    private List<PrinterItem> data;

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
     * 打印机总数
     */
    @JsonProperty("total")
    private Integer total;

    /**
     * 是否最后一页
     */
    @JsonProperty("isLast")
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
    public ShopPrinterListResp() {
        this.data = new ArrayList<>();
    }

    /**
     * 基本构造函数
     * 
     * @param data 打印机设备列表
     */
    public ShopPrinterListResp(List<PrinterItem> data) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
    }

    /**
     * 普通分页构造函数
     * 
     * @param data 打印机设备列表
     * @param page 当前页码
     * @param totalPage 总页数
     * @param total 打印机总数
     * @param isLast 是否最后一页
     */
    public ShopPrinterListResp(List<PrinterItem> data, Integer page, Integer totalPage, Integer total, Boolean isLast) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
        this.page = page;
        this.totalPage = totalPage;
        this.total = total;
        this.isLast = isLast;
    }

    /**
     * 游标分页构造函数
     * 
     * @param data 打印机设备列表
     * @param scrollId 游标ID
     * @param isLast 是否最后一页
     */
    public ShopPrinterListResp(List<PrinterItem> data, String scrollId, Boolean isLast) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
        this.scrollId = scrollId;
        this.isLast = isLast;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public List<PrinterItem> getData() {
        return data;
    }

    public void setData(List<PrinterItem> data) {
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
        ShopPrinterListResp that = (ShopPrinterListResp) o;
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
        return "ShopPrinterListResp{" +
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
     * 获取打印机数量
     * 
     * @return 当前页的打印机数量
     */
    @JsonIgnore
    public int getPrinterCount() {
        return data != null ? data.size() : 0;
    }

    /**
     * 检查是否有打印机数据
     * 
     * @return 如果有打印机数据返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasPrinters() {
        return data != null && !data.isEmpty();
    }

    /**
     * 检查是否为空响应
     * 
     * @return 如果没有打印机数据返回true，否则返回false
     */
    @JsonIgnore
    public boolean isEmpty() {
        return !hasPrinters();
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
     * 添加打印机
     * 
     * @param printer 打印机信息
     */
    public void addPrinter(PrinterItem printer) {
        if (printer != null) {
            if (data == null) {
                data = new ArrayList<>();
            }
            data.add(printer);
        }
    }

    /**
     * 添加多个打印机
     * 
     * @param printers 打印机列表
     */
    public void addPrinters(List<PrinterItem> printers) {
        if (printers != null && !printers.isEmpty()) {
            if (data == null) {
                data = new ArrayList<>();
            }
            data.addAll(printers);
        }
    }

    /**
     * 清空打印机数据
     */
    public void clearPrinters() {
        if (data != null) {
            data.clear();
        }
    }

    /**
     * 获取不可变的打印机列表
     * 
     * @return 不可变的打印机列表
     */
    @JsonIgnore
    public List<PrinterItem> getImmutablePrinters() {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(data);
    }

    /**
     * 获取有效的打印机列表
     * 
     * @return 有效的打印机列表
     */
    @JsonIgnore
    public List<PrinterItem> getValidPrinters() {
        if (!hasPrinters()) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(printer -> printer != null && printer.isValid())
                .collect(Collectors.toList());
    }

    /**
     * 按品牌筛选打印机
     * 
     * @param brand 品牌名称
     * @return 指定品牌的打印机列表
     */
    @JsonIgnore
    public List<PrinterItem> getPrintersByBrand(String brand) {
        if (!hasPrinters() || brand == null) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(printer -> printer != null && printer.isBrand(brand))
                .collect(Collectors.toList());
    }

    /**
     * 按门店ID筛选打印机
     * 
     * @param shopId 门店ID
     * @return 指定门店的打印机列表
     */
    @JsonIgnore
    public List<PrinterItem> getPrintersByShop(String shopId) {
        if (!hasPrinters() || shopId == null) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(printer -> printer != null && printer.belongsToShop(shopId))
                .collect(Collectors.toList());
    }

    /**
     * 按设备编号查找打印机
     * 
     * @param machineCode 设备编号
     * @return 匹配的打印机信息，如果未找到返回null
     */
    @JsonIgnore
    public PrinterItem findPrinterByCode(String machineCode) {
        if (!hasPrinters() || machineCode == null) {
            return null;
        }
        return data.stream()
                .filter(printer -> printer != null && machineCode.equals(printer.getMachineCode()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取包含设备密钥的打印机列表
     * 
     * @return 包含设备密钥的打印机列表
     */
    @JsonIgnore
    public List<PrinterItem> getPrintersWithSign() {
        if (!hasPrinters()) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(printer -> printer != null && printer.hasSign())
                .collect(Collectors.toList());
    }

    /**
     * 获取所有品牌列表
     * 
     * @return 去重后的品牌列表
     */
    @JsonIgnore
    public List<String> getAllBrands() {
        if (!hasPrinters()) {
            return Collections.emptyList();
        }
        return data.stream()
                .filter(printer -> printer != null && printer.hasBrand())
                .map(PrinterItem::getBrand)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 获取打印机统计信息
     * 
     * @return 打印机统计信息描述
     */
    @JsonIgnore
    public String getStatistics() {
        if (isEmpty()) {
            return "无打印机设备";
        }
        
        StringBuilder stats = new StringBuilder();
        stats.append("设备总数: ").append(getPrinterCount());
        
        List<String> brands = getAllBrands();
        if (!brands.isEmpty()) {
            stats.append(", 品牌数: ").append(brands.size());
            stats.append(" (").append(String.join(", ", brands)).append(")");
        }
        
        long withSignCount = getPrintersWithSign().size();
        if (withSignCount > 0) {
            stats.append(", 已配置密钥: ").append(withSignCount).append("台");
        }
        
        return stats.toString();
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建空响应
     * 
     * @return 空的打印机列表响应对象
     */
    public static ShopPrinterListResp empty() {
        return new ShopPrinterListResp();
    }

    /**
     * 创建包含打印机数据的响应
     * 
     * @param printers 打印机列表
     * @return 打印机列表响应对象
     */
    public static ShopPrinterListResp of(List<PrinterItem> printers) {
        return new ShopPrinterListResp(printers);
    }

    /**
     * 创建普通分页响应
     * 
     * @param printers 打印机列表
     * @param page 当前页码
     * @param totalPage 总页数
     * @param total 打印机总数
     * @return 打印机列表响应对象
     */
    public static ShopPrinterListResp ofPage(List<PrinterItem> printers, int page, int totalPage, int total) {
        return new ShopPrinterListResp(printers, page, totalPage, total, page >= totalPage);
    }

    /**
     * 创建游标分页响应
     * 
     * @param printers 打印机列表
     * @param scrollId 游标ID
     * @param isLast 是否最后一页
     * @return 打印机列表响应对象
     */
    public static ShopPrinterListResp ofScroll(List<PrinterItem> printers, String scrollId, boolean isLast) {
        return new ShopPrinterListResp(printers, scrollId, isLast);
    }

    /**
     * 创建最后一页响应
     * 
     * @param printers 打印机列表
     * @return 打印机列表响应对象
     */
    public static ShopPrinterListResp lastPage(List<PrinterItem> printers) {
        ShopPrinterListResp resp = new ShopPrinterListResp(printers);
        resp.setIsLast(true);
        return resp;
    }

    /**
     * 构建器模式创建响应
     * 
     * @return 打印机列表响应构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 门店打印机列表响应构建器
     */
    public static class Builder {
        private final List<PrinterItem> data = new ArrayList<>();
        private Integer page;
        private Integer totalPage;
        private Integer total;
        private Boolean isLast;
        private String scrollId;

        /**
         * 添加打印机
         * 
         * @param printer 打印机信息
         * @return 构建器实例
         */
        public Builder addPrinter(PrinterItem printer) {
            if (printer != null) {
                data.add(printer);
            }
            return this;
        }

        /**
         * 添加多个打印机
         * 
         * @param printers 打印机列表
         * @return 构建器实例
         */
        public Builder addPrinters(List<PrinterItem> printers) {
            if (printers != null) {
                data.addAll(printers);
            }
            return this;
        }

        /**
         * 设置打印机列表
         * 
         * @param printers 打印机列表
         * @return 构建器实例
         */
        public Builder data(List<PrinterItem> printers) {
            data.clear();
            if (printers != null) {
                data.addAll(printers);
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
         * 设置打印机总数
         * 
         * @param total 打印机总数
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
         * 构建门店打印机列表响应对象
         * 
         * @return 门店打印机列表响应对象
         */
        public ShopPrinterListResp build() {
            ShopPrinterListResp resp = new ShopPrinterListResp(data);
            resp.setPage(page);
            resp.setTotalPage(totalPage);
            resp.setTotal(total);
            resp.setIsLast(isLast);
            resp.setScrollId(scrollId);
            return resp;
        }
    }
}