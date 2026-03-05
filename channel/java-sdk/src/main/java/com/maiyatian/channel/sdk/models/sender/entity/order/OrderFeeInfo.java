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
 * 订单费用信息
 * <p>
 * 包含订单的各项费用明细：原价、配送费、优惠、佣金等
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderFeeInfo {

    /**
     * 订单原价总金额（单位：分，折扣前的商品总价）
     */
    @JsonProperty("total_fee")
    private Integer totalFee;

    /**
     * 配送费（单位：分）
     */
    @JsonProperty("send_fee")
    private Integer sendFee;

    /**
     * 餐盒费（单位：分）
     */
    @JsonProperty("package_fee")
    private Integer packageFee;

    /**
     * 优惠总金额（单位：分，所有优惠的总和）
     */
    @JsonProperty("discount_fee")
    private Integer discountFee;

    /**
     * 商户应收金额（单位：分，扣除平台佣金后商户实际收入）
     */
    @JsonProperty("shop_fee")
    private Integer shopFee;

    /**
     * 用户实付金额（单位：分，用户最终支付的金额）
     */
    @JsonProperty("user_fee")
    private Integer userFee;

    /**
     * 付款类型：1-货到付款，2-在线支付
     */
    @JsonProperty("pay_type")
    private Integer payType;

    /**
     * 是否需要发票
     */
    @JsonProperty("need_invoice")
    private Boolean needInvoice;

    /**
     * 发票信息（仅当需要发票时有值）
     */
    @Valid
    @JsonProperty("invoice")
    private InvoiceInfo invoice;

    /**
     * 订单活动信息列表（满减、折扣等营销活动）
     */
    @Valid
    @JsonProperty("activity")
    private List<ActivityInfo> activity;

    /**
     * 平台佣金（单位：分）
     */
    @JsonProperty("commission")
    private Integer commission;

    /**
     * 是否首次购买（用于首单优惠等营销场景）
     */
    @JsonProperty("is_first")
    private Boolean isFirst;

    /**
     * 是否收藏用户（用于会员营销）
     */
    @JsonProperty("is_favorite")
    private Boolean isFavorite;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderFeeInfo() {
    }

    // ==================== Getter 和 Setter 方法 ====================

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSendFee() {
        return sendFee;
    }

    public void setSendFee(Integer sendFee) {
        this.sendFee = sendFee;
    }

    public Integer getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(Integer packageFee) {
        this.packageFee = packageFee;
    }

    public Integer getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(Integer discountFee) {
        this.discountFee = discountFee;
    }

    public Integer getShopFee() {
        return shopFee;
    }

    public void setShopFee(Integer shopFee) {
        this.shopFee = shopFee;
    }

    public Integer getUserFee() {
        return userFee;
    }

    public void setUserFee(Integer userFee) {
        this.userFee = userFee;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Boolean getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(Boolean needInvoice) {
        this.needInvoice = needInvoice;
    }

    public InvoiceInfo getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceInfo invoice) {
        this.invoice = invoice;
    }

    public List<ActivityInfo> getActivity() {
        return activity;
    }

    public void setActivity(List<ActivityInfo> activity) {
        this.activity = activity;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFeeInfo that = (OrderFeeInfo) o;
        return Objects.equals(totalFee, that.totalFee) &&
               Objects.equals(sendFee, that.sendFee) &&
               Objects.equals(packageFee, that.packageFee) &&
               Objects.equals(discountFee, that.discountFee) &&
               Objects.equals(shopFee, that.shopFee) &&
               Objects.equals(userFee, that.userFee) &&
               Objects.equals(payType, that.payType) &&
               Objects.equals(needInvoice, that.needInvoice) &&
               Objects.equals(invoice, that.invoice) &&
               Objects.equals(activity, that.activity) &&
               Objects.equals(commission, that.commission) &&
               Objects.equals(isFirst, that.isFirst) &&
               Objects.equals(isFavorite, that.isFavorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalFee, sendFee, packageFee, discountFee, shopFee,
                           userFee, payType, needInvoice, invoice, activity,
                           commission, isFirst, isFavorite);
    }

    @Override
    public String toString() {
        return "OrderFeeInfo{" +
                "totalFee=" + totalFee +
                ", sendFee=" + sendFee +
                ", packageFee=" + packageFee +
                ", discountFee=" + discountFee +
                ", shopFee=" + shopFee +
                ", userFee=" + userFee +
                ", payType=" + payType +
                ", needInvoice=" + needInvoice +
                ", invoice=" + invoice +
                ", activity=" + activity +
                ", commission=" + commission +
                ", isFirst=" + isFirst +
                ", isFavorite=" + isFavorite +
                '}';
    }
}