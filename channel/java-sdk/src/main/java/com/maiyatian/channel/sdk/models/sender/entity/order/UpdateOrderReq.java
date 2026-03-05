/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

/**
 * 订单修改推送请求参数
 * <p>
 * 三方服务推送订单修改信息给麦芽田时使用
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 用户在订单确认前修改订单信息，三方推送修改内容给麦芽田
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>此接口仅订单确认前可调用</li>
 * <li>修改内容仅推送修改部分的数据</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class UpdateOrderReq extends OrderBaseData {

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public UpdateOrderReq() {
        super();
    }

    /**
     * 全参构造函数
     */
    public UpdateOrderReq(OrderInfo order, OrderCustomerInfo orderCustomer, Long updateTime) {
        super(order, orderCustomer, updateTime);
    }

    @Override
    public String toString() {
        return "UpdateOrderReq{" +
                "order=" + getOrder() +
                ", orderCustomer=" + getOrderCustomer() +
                ", updateTime=" + getUpdateTime() +
                '}';
    }
}