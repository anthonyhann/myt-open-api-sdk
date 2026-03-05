/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

/**
 * 订单计费实体
 * <p>
 * 用于麦芽田请求三方服务查询订单运费，command值为valuating，是必接接口
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValuatingEntity {
    
    /**
     * 订单计费请求参数
     * <p>
     * command: valuating
     * 必接: 是
     * 说明: 查询订单运费，麦芽田平台调用三方配送服务计算订单运费
     * 注意: 请求参数与下单接口SendReq完全相同
     */
    public static class Req extends SendEntity.Req {
        // 请求参数与SendEntity.Req完全相同，无需额外定义
    }
    
    /**
     * 订单计费响应参数
     * <p>
     * 注意: 响应参数基本继承自SendResp，额外增加计价单号字段
     */
    public static class Resp extends SendEntity.Resp {
        
        /**
         * 计价三方唯一计费号
         * <p>用于锁定计费，在下单时传入此号码以确保计费一致性
         */
        private String billingNo;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getBillingNo() {
            return billingNo;
        }
        
        public void setBillingNo(String billingNo) {
            this.billingNo = billingNo;
        }
    }
}