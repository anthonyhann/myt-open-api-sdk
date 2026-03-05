/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 已退款商品信息
 * <p>
 * 包含已退款商品的详细信息和退款金额
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderRefundedGoodsInfo {

    /**
     * 平台方商品 ID
     */
    @JsonProperty("goods_id")
    private String goodsId;

    /**
     * 商品名称
     */
    @JsonProperty("goods_name")
    private String goodsName;

    /**
     * 平台方规格 ID
     */
    @JsonProperty("sku_id")
    private String skuId;

    /**
     * 商品统一编码（UPC 条形码）
     */
    @JsonProperty("upc")
    private String upc;

    /**
     * 货架号
     */
    @JsonProperty("shelf_no")
    private String shelfNo;

    /**
     * 退款数量
     */
    @JsonProperty("number")
    private Integer number;

    /**
     * 商品总价（单位：分）
     */
    @JsonProperty("goods_total_fee")
    private Integer goodsTotalFee;

    /**
     * 包装盒数量
     */
    @JsonProperty("package_number")
    private Integer packageNumber;

    /**
     * 包装盒费用（单位：分）
     */
    @JsonProperty("package_fee")
    private Integer packageFee;

    /**
     * 实际退款金额（单位：分）
     */
    @JsonProperty("refund_fee")
    private Integer refundFee;

    // ==================== 构造函数 ====================

    public OrderRefundedGoodsInfo() {
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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
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

    public Integer getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(Integer packageFee) {
        this.packageFee = packageFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRefundedGoodsInfo that = (OrderRefundedGoodsInfo) o;
        return Objects.equals(goodsId, that.goodsId) &&
               Objects.equals(goodsName, that.goodsName) &&
               Objects.equals(skuId, that.skuId) &&
               Objects.equals(upc, that.upc) &&
               Objects.equals(shelfNo, that.shelfNo) &&
               Objects.equals(number, that.number) &&
               Objects.equals(goodsTotalFee, that.goodsTotalFee) &&
               Objects.equals(packageNumber, that.packageNumber) &&
               Objects.equals(packageFee, that.packageFee) &&
               Objects.equals(refundFee, that.refundFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, skuId, upc, shelfNo, number,
                           goodsTotalFee, packageNumber, packageFee, refundFee);
    }

    @Override
    public String toString() {
        return "OrderRefundedGoodsInfo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", skuId='" + skuId + '\'' +
                ", upc='" + upc + '\'' +
                ", shelfNo='" + shelfNo + '\'' +
                ", number=" + number +
                ", goodsTotalFee=" + goodsTotalFee +
                ", packageNumber=" + packageNumber +
                ", packageFee=" + packageFee +
                ", refundFee=" + refundFee +
                '}';
    }
}