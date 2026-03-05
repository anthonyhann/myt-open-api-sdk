/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

import java.util.List;

/**
 * 拍照实体
 * <p>
 * 用于麦芽田请求三方服务进行拍照相关操作
 *
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class PhotographEntity {

    /**
     * 拍照请求参数
     */
    public static class PhotographReq {

        /**
         * 订单号
         */
        private String orderNo;

        /**
         * 源订单号
         */
        private String sourceOrderNo;

        /**
         * 类型，默认值为0，可选参数
         */
        private Long type;

        // ==================== Getter 和 Setter 方法 ====================

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getSourceOrderNo() {
            return sourceOrderNo;
        }

        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }

        public Long getType() {
            return type;
        }

        public void setType(Long type) {
            this.type = type;
        }
    }

    /**
     * 拍照响应结果
     */
    public static class PhotographResp {

        /**
         * 订单号
         */
        private String orderNo;

        /**
         * 源订单号
         */
        private String sourceOrderNo;

        /**
         * 骑手姓名
         */
        private String riderName;

        /**
         * 骑手电话
         */
        private String riderPhone;

        /**
         * 取货照片列表
         */
        private List<String> pickupPhotos;

        /**
         * 配送照片列表
         */
        private List<String> deliveryPhotos;

        // ==================== Getter 和 Setter 方法 ====================

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getSourceOrderNo() {
            return sourceOrderNo;
        }

        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }

        public String getRiderName() {
            return riderName;
        }

        public void setRiderName(String riderName) {
            this.riderName = riderName;
        }

        public String getRiderPhone() {
            return riderPhone;
        }

        public void setRiderPhone(String riderPhone) {
            this.riderPhone = riderPhone;
        }

        public List<String> getPickupPhotos() {
            return pickupPhotos;
        }

        public void setPickupPhotos(List<String> pickupPhotos) {
            this.pickupPhotos = pickupPhotos;
        }

        public List<String> getDeliveryPhotos() {
            return deliveryPhotos;
        }

        public void setDeliveryPhotos(List<String> deliveryPhotos) {
            this.deliveryPhotos = deliveryPhotos;
        }
    }
}

