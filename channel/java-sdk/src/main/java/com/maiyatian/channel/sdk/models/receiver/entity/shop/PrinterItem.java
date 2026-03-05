/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 打印机设备信息
 * <p>
 * 单个打印机设备的详细配置
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用于门店打印机列表查询的响应数据，包含打印机的完整配置信息
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>machineCode是打印机的唯一设备编号</li>
 * <li>machineSign是设备密钥，用于安全验证</li>
 * <li>printNumber表示每次打印的份数</li>
 * <li>width表示打印机纸张宽度规格</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrinterItem {

    /**
     * 平台方渠道 ID
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 打印机品牌
     */
    @JsonProperty("brand")
    private String brand;

    /**
     * 打印机名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 打印机设备编号
     */
    @JsonProperty("machine_code")
    private String machineCode;

    /**
     * 打印机设备密钥
     */
    @JsonProperty("machine_sign")
    private String machineSign;

    /**
     * 打印份数
     */
    @Min(value = 0, message = "打印份数不能为负数")
    @JsonProperty("print_number")
    private Integer printNumber;

    /**
     * 打印机纸张宽度
     */
    @JsonProperty("width")
    private String width;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public PrinterItem() {
    }

    /**
     * 基本信息构造函数
     * 
     * @param shopId 门店ID
     * @param machineCode 设备编号
     */
    public PrinterItem(String shopId, String machineCode) {
        this.shopId = shopId;
        this.machineCode = machineCode;
    }

    /**
     * 包含基本配置的构造函数
     * 
     * @param shopId 门店ID
     * @param brand 品牌
     * @param name 名称
     * @param machineCode 设备编号
     * @param machineSign 设备密钥
     */
    public PrinterItem(String shopId, String brand, String name, String machineCode, String machineSign) {
        this.shopId = shopId;
        this.brand = brand;
        this.name = name;
        this.machineCode = machineCode;
        this.machineSign = machineSign;
    }

    /**
     * 全参构造函数
     * 
     * @param shopId 门店ID
     * @param brand 品牌
     * @param name 名称
     * @param machineCode 设备编号
     * @param machineSign 设备密钥
     * @param printNumber 打印份数
     * @param width 纸张宽度
     */
    public PrinterItem(String shopId, String brand, String name, String machineCode, 
                      String machineSign, Integer printNumber, String width) {
        this.shopId = shopId;
        this.brand = brand;
        this.name = name;
        this.machineCode = machineCode;
        this.machineSign = machineSign;
        this.printNumber = printNumber;
        this.width = width;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMachineSign() {
        return machineSign;
    }

    public void setMachineSign(String machineSign) {
        this.machineSign = machineSign;
    }

    public Integer getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(Integer printNumber) {
        this.printNumber = printNumber;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrinterItem that = (PrinterItem) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(brand, that.brand) &&
               Objects.equals(name, that.name) &&
               Objects.equals(machineCode, that.machineCode) &&
               Objects.equals(machineSign, that.machineSign) &&
               Objects.equals(printNumber, that.printNumber) &&
               Objects.equals(width, that.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, brand, name, machineCode, machineSign, printNumber, width);
    }

    @Override
    public String toString() {
        return "PrinterItem{" +
                "shopId='" + shopId + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", machineCode='" + machineCode + '\'' +
                ", machineSign='" + machineSign + '\'' +
                ", printNumber=" + printNumber +
                ", width='" + width + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查必要信息是否完整
     * 
     * @return 如果门店ID和设备编号都不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return shopId != null && !shopId.trim().isEmpty() &&
               machineCode != null && !machineCode.trim().isEmpty();
    }

    /**
     * 检查是否包含品牌信息
     * 
     * @return 如果包含品牌信息返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasBrand() {
        return brand != null && !brand.trim().isEmpty();
    }

    /**
     * 检查是否包含打印机名称
     * 
     * @return 如果包含打印机名称返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasName() {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * 检查是否包含设备密钥
     * 
     * @return 如果包含设备密钥返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasSign() {
        return machineSign != null && !machineSign.trim().isEmpty();
    }

    /**
     * 检查是否设置了打印份数
     * 
     * @return 如果设置了打印份数返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasPrintNumber() {
        return printNumber != null && printNumber >= 0;
    }

    /**
     * 检查是否包含纸张宽度信息
     * 
     * @return 如果包含纸张宽度信息返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasWidth() {
        return width != null && !width.trim().isEmpty();
    }

    /**
     * 获取打印机显示名称
     * 
     * @return 打印机的显示名称
     */
    @JsonIgnore
    public String getDisplayName() {
        if (hasName()) {
            if (hasBrand()) {
                return String.format("[%s] %s", brand.trim(), name.trim());
            }
            return name.trim();
        }
        if (hasBrand()) {
            return brand.trim();
        }
        return machineCode != null ? machineCode : "未知打印机";
    }

    /**
     * 获取打印机规格信息
     * 
     * @return 打印机的规格描述
     */
    @JsonIgnore
    public String getSpecification() {
        StringBuilder spec = new StringBuilder();
        
        if (hasWidth()) {
            spec.append("纸张宽度: ").append(width.trim());
        }
        
        if (hasPrintNumber()) {
            if (spec.length() > 0) spec.append(", ");
            spec.append("打印份数: ").append(printNumber);
        }
        
        return spec.length() > 0 ? spec.toString() : "规格信息不可用";
    }

    /**
     * 获取设备安全状态
     * 
     * @return 设备的安全状态描述
     */
    @JsonIgnore
    public String getSecurityStatus() {
        if (hasSign()) {
            return "已配置设备密钥";
        }
        return "未配置设备密钥";
    }

    /**
     * 检查是否为同一台设备
     * 
     * @param other 另一个打印机对象
     * @return 如果是同一台设备返回true，否则返回false
     */
    @JsonIgnore
    public boolean isSameDevice(PrinterItem other) {
        if (other == null) return false;
        return Objects.equals(this.machineCode, other.machineCode) &&
               Objects.equals(this.shopId, other.shopId);
    }

    /**
     * 检查是否属于指定门店
     * 
     * @param targetShopId 目标门店ID
     * @return 如果属于指定门店返回true，否则返回false
     */
    @JsonIgnore
    public boolean belongsToShop(String targetShopId) {
        return targetShopId != null && targetShopId.equals(this.shopId);
    }

    /**
     * 检查是否为指定品牌
     * 
     * @param targetBrand 目标品牌
     * @return 如果是指定品牌返回true，否则返回false
     */
    @JsonIgnore
    public boolean isBrand(String targetBrand) {
        return targetBrand != null && targetBrand.equals(this.brand);
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建基本打印机信息
     * 
     * @param shopId 门店ID
     * @param machineCode 设备编号
     * @return 打印机信息对象
     */
    public static PrinterItem of(String shopId, String machineCode) {
        return new PrinterItem(shopId, machineCode);
    }

    /**
     * 创建包含基本配置的打印机信息
     * 
     * @param shopId 门店ID
     * @param brand 品牌
     * @param name 名称
     * @param machineCode 设备编号
     * @param machineSign 设备密钥
     * @return 打印机信息对象
     */
    public static PrinterItem basic(String shopId, String brand, String name, 
                                  String machineCode, String machineSign) {
        return new PrinterItem(shopId, brand, name, machineCode, machineSign);
    }

    /**
     * 创建完整的打印机信息
     * 
     * @param shopId 门店ID
     * @param brand 品牌
     * @param name 名称
     * @param machineCode 设备编号
     * @param machineSign 设备密钥
     * @param printNumber 打印份数
     * @param width 纸张宽度
     * @return 打印机信息对象
     */
    public static PrinterItem complete(String shopId, String brand, String name, String machineCode, 
                                     String machineSign, Integer printNumber, String width) {
        return new PrinterItem(shopId, brand, name, machineCode, machineSign, printNumber, width);
    }

    /**
     * 构建器模式创建打印机信息
     * 
     * @return 打印机信息构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    // ==================== 构建器 ====================

    /**
     * 打印机信息构建器
     */
    public static class Builder {
        private String shopId;
        private String brand;
        private String name;
        private String machineCode;
        private String machineSign;
        private Integer printNumber;
        private String width;

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
         * 设置品牌
         * 
         * @param brand 品牌
         * @return 构建器实例
         */
        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        /**
         * 设置名称
         * 
         * @param name 名称
         * @return 构建器实例
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * 设置设备编号
         * 
         * @param machineCode 设备编号
         * @return 构建器实例
         */
        public Builder machineCode(String machineCode) {
            this.machineCode = machineCode;
            return this;
        }

        /**
         * 设置设备密钥
         * 
         * @param machineSign 设备密钥
         * @return 构建器实例
         */
        public Builder machineSign(String machineSign) {
            this.machineSign = machineSign;
            return this;
        }

        /**
         * 设置打印份数
         * 
         * @param printNumber 打印份数
         * @return 构建器实例
         */
        public Builder printNumber(Integer printNumber) {
            this.printNumber = printNumber;
            return this;
        }

        /**
         * 设置纸张宽度
         * 
         * @param width 纸张宽度
         * @return 构建器实例
         */
        public Builder width(String width) {
            this.width = width;
            return this;
        }

        /**
         * 构建打印机信息对象
         * 
         * @return 打印机信息对象
         */
        public PrinterItem build() {
            return new PrinterItem(shopId, brand, name, machineCode, machineSign, printNumber, width);
        }
    }
}