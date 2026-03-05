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
 * 订单商品信息
 * <p>
 * 包含商品的详细信息：名称、规格、价格、数量、优惠等
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderGoodsInfo {

    /**
     * 平台方商品 ID（第三方平台的商品 ID）
     */
    @JsonProperty("goods_id")
    private String goodsId;

    /**
     * 商品名称
     */
    @JsonProperty("goods_name")
    private String goodsName;

    /**
     * 商户自定义商品编码（系统外部编码，可选）
     */
    @JsonProperty("goods_code")
    private String goodsCode;

    /**
     * 商品图片 URL
     */
    @JsonProperty("thumb")
    private String thumb;

    /**
     * 平台方规格 ID（商品 SKU ID）
     */
    @JsonProperty("sku_id")
    private String skuId;

    /**
     * 商品 SKU 名称（如："中杯"、"加辣"）
     */
    @JsonProperty("sku_name")
    private String skuName;

    /**
     * 商户自定义 SKU 编码（系统外部编码，可选）
     */
    @JsonProperty("sku_code")
    private String skuCode;

    /**
     * 商品规格属性列表（如：["6英寸", "原味"]）
     */
    @JsonProperty("food_property")
    private List<String> foodProperty;

    /**
     * 商品套餐属性列表（单层结构）
     */
    @Valid
    @JsonProperty("sku_attributes")
    private List<OrderGoodsSkuAttribute> skuAttributes;

    /**
     * 商品套餐分组列表（分组结构，如：主食、小料）
     */
    @Valid
    @JsonProperty("commodities")
    private List<OrderGoodsSkuAttributeGroup> commodities;

    /**
     * 商品单位（如："份"、"个"、"杯"）
     */
    @JsonProperty("unit")
    private String unit;

    /**
     * 单个商品重量（单位：克）
     */
    @JsonProperty("weight")
    private Integer weight;

    /**
     * 商品统一编码（UPC 条形码）
     */
    @JsonProperty("upc")
    private String upc;

    /**
     * 货架号（仓库管理用）
     */
    @JsonProperty("shelf_no")
    private String shelfNo;

    /**
     * 购买数量
     */
    @JsonProperty("number")
    private Integer number;

    /**
     * 商品单价（单位：分，折扣前）
     */
    @JsonProperty("goods_price")
    private Integer goodsPrice;

    /**
     * 商品总价（单位：分，折扣前 = 单价 × 数量）
     */
    @JsonProperty("goods_total_fee")
    private Integer goodsTotalFee;

    /**
     * 包装盒数量
     */
    @JsonProperty("package_number")
    private Integer packageNumber;

    /**
     * 包装盒单价（单位：分）
     */
    @JsonProperty("package_price")
    private Integer packagePrice;

    /**
     * 包装盒总价（单位：分）
     */
    @JsonProperty("package_total_fee")
    private Integer packageTotalFee;

    /**
     * 商品折扣单价（单位：分，折扣后的单价）
     */
    @JsonProperty("reduce_fee")
    private Integer reduceFee;

    /**
     * 商品总优惠金额（单位：分，此商品享受的所有优惠）
     */
    @JsonProperty("discount_fee")
    private Integer discountFee;

    /**
     * 平台渠道承担的优惠金额（单位：分）
     */
    @JsonProperty("discount_platform_fee")
    private Integer discountPlatformFee;

    /**
     * 商户承担的优惠金额（单位：分）
     */
    @JsonProperty("discount_merchant_fee")
    private Integer discountMerchantFee;

    /**
     * 代理商承担的优惠金额（单位：分）
     */
    @JsonProperty("discount_agent_fee")
    private Integer discountAgentFee;

    /**
     * 物流承担的优惠金额（单位：分）
     */
    @JsonProperty("discount_logistics_fee")
    private Integer discountLogisticsFee;

    /**
     * 商品合计费用（单位：分，折扣后实付）
     */
    @JsonProperty("total_fee")
    private Integer totalFee;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderGoodsInfo() {
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public List<String> getFoodProperty() {
        return foodProperty;
    }

    public void setFoodProperty(List<String> foodProperty) {
        this.foodProperty = foodProperty;
    }

    public List<OrderGoodsSkuAttribute> getSkuAttributes() {
        return skuAttributes;
    }

    public void setSkuAttributes(List<OrderGoodsSkuAttribute> skuAttributes) {
        this.skuAttributes = skuAttributes;
    }

    public List<OrderGoodsSkuAttributeGroup> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<OrderGoodsSkuAttributeGroup> commodities) {
        this.commodities = commodities;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Integer goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsTotalFee() {
        return goodsTotalFee;
    }

    public void setGoodsTotalFee(Integer goodsTotalFee) {
        this.goodsTotalFee = goodsTotalFee;
    }

    public Integer getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(Integer packageNumber) {
        this.packageNumber = packageNumber;
    }

    public Integer getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Integer packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Integer getPackageTotalFee() {
        return packageTotalFee;
    }

    public void setPackageTotalFee(Integer packageTotalFee) {
        this.packageTotalFee = packageTotalFee;
    }

    public Integer getReduceFee() {
        return reduceFee;
    }

    public void setReduceFee(Integer reduceFee) {
        this.reduceFee = reduceFee;
    }

    public Integer getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(Integer discountFee) {
        this.discountFee = discountFee;
    }

    public Integer getDiscountPlatformFee() {
        return discountPlatformFee;
    }

    public void setDiscountPlatformFee(Integer discountPlatformFee) {
        this.discountPlatformFee = discountPlatformFee;
    }

    public Integer getDiscountMerchantFee() {
        return discountMerchantFee;
    }

    public void setDiscountMerchantFee(Integer discountMerchantFee) {
        this.discountMerchantFee = discountMerchantFee;
    }

    public Integer getDiscountAgentFee() {
        return discountAgentFee;
    }

    public void setDiscountAgentFee(Integer discountAgentFee) {
        this.discountAgentFee = discountAgentFee;
    }

    public Integer getDiscountLogisticsFee() {
        return discountLogisticsFee;
    }

    public void setDiscountLogisticsFee(Integer discountLogisticsFee) {
        this.discountLogisticsFee = discountLogisticsFee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoodsInfo that = (OrderGoodsInfo) o;
        return Objects.equals(goodsId, that.goodsId) &&
               Objects.equals(goodsName, that.goodsName) &&
               Objects.equals(goodsCode, that.goodsCode) &&
               Objects.equals(thumb, that.thumb) &&
               Objects.equals(skuId, that.skuId) &&
               Objects.equals(skuName, that.skuName) &&
               Objects.equals(skuCode, that.skuCode) &&
               Objects.equals(foodProperty, that.foodProperty) &&
               Objects.equals(skuAttributes, that.skuAttributes) &&
               Objects.equals(commodities, that.commodities) &&
               Objects.equals(unit, that.unit) &&
               Objects.equals(weight, that.weight) &&
               Objects.equals(upc, that.upc) &&
               Objects.equals(shelfNo, that.shelfNo) &&
               Objects.equals(number, that.number) &&
               Objects.equals(goodsPrice, that.goodsPrice) &&
               Objects.equals(goodsTotalFee, that.goodsTotalFee) &&
               Objects.equals(packageNumber, that.packageNumber) &&
               Objects.equals(packagePrice, that.packagePrice) &&
               Objects.equals(packageTotalFee, that.packageTotalFee) &&
               Objects.equals(reduceFee, that.reduceFee) &&
               Objects.equals(discountFee, that.discountFee) &&
               Objects.equals(discountPlatformFee, that.discountPlatformFee) &&
               Objects.equals(discountMerchantFee, that.discountMerchantFee) &&
               Objects.equals(discountAgentFee, that.discountAgentFee) &&
               Objects.equals(discountLogisticsFee, that.discountLogisticsFee) &&
               Objects.equals(totalFee, that.totalFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, goodsCode, thumb, skuId, skuName, skuCode,
                           foodProperty, skuAttributes, commodities, unit, weight, upc,
                           shelfNo, number, goodsPrice, goodsTotalFee, packageNumber,
                           packagePrice, packageTotalFee, reduceFee, discountFee,
                           discountPlatformFee, discountMerchantFee, discountAgentFee,
                           discountLogisticsFee, totalFee);
    }

    @Override
    public String toString() {
        return "OrderGoodsInfo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", thumb='" + thumb + '\'' +
                ", skuId='" + skuId + '\'' +
                ", skuName='" + skuName + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", foodProperty=" + foodProperty +
                ", skuAttributes=" + skuAttributes +
                ", commodities=" + commodities +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                ", upc='" + upc + '\'' +
                ", shelfNo='" + shelfNo + '\'' +
                ", number=" + number +
                ", goodsPrice=" + goodsPrice +
                ", goodsTotalFee=" + goodsTotalFee +
                ", packageNumber=" + packageNumber +
                ", packagePrice=" + packagePrice +
                ", packageTotalFee=" + packageTotalFee +
                ", reduceFee=" + reduceFee +
                ", discountFee=" + discountFee +
                ", discountPlatformFee=" + discountPlatformFee +
                ", discountMerchantFee=" + discountMerchantFee +
                ", discountAgentFee=" + discountAgentFee +
                ", discountLogisticsFee=" + discountLogisticsFee +
                ", totalFee=" + totalFee +
                '}';
    }
}