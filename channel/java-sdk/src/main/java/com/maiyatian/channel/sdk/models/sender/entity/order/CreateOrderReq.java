/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

/**
 * 新订单推送请求参数
 * <p>
 * 三方服务推送新订单给麦芽田时使用
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用户在三方平台下单并支付成功后，三方推送完整订单信息给麦芽田
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是必接接口，三方必须实现</li>
 * <li>包含订单的完整信息（商品、费用、顾客、配送等）</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class CreateOrderReq extends OrderBaseData {

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public CreateOrderReq() {
        super();
    }

    /**
     * 全参构造函数
     */
    public CreateOrderReq(OrderInfo order, OrderCustomerInfo orderCustomer, Long updateTime) {
        super(order, orderCustomer, updateTime);
    }

    @Override
    public String toString() {
        return "CreateOrderReq{" +
                "order=" + getOrder() +
                ", orderCustomer=" + getOrderCustomer() +
                ", updateTime=" + getUpdateTime() +
                '}';
    }
}