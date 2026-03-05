/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 订单出餐状态更新请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方更新出餐状态
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方订单出餐状态变化
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送出餐状态请求 → 三方处理出餐逻辑 → 三方回复处理结果
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>Type 取值：0-等待拣货，1-拣货中，2-拣货完成</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class MealPickingReq {

    /**
     * 平台方订单唯一 ID（三方平台的订单号）
     */
    @NotBlank(message = "订单ID不能为空")
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 出餐类型：0-等待拣货，1-拣货中，2-拣货完成
     */
    @JsonProperty("type")
    private Long type;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public MealPickingReq() {
    }

    /**
     * 全参构造函数
     * 
     * @param orderId 平台方订单唯一ID
     * @param shopId 平台方渠道ID
     * @param type 出餐类型
     */
    public MealPickingReq(String orderId, String shopId, Long type) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.type = type;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealPickingReq that = (MealPickingReq) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(shopId, that.shopId) &&
               Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shopId, type);
    }

    @Override
    public String toString() {
        return "MealPickingReq{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", type=" + type +
                '}';
    }

    // ==================== 枚举定义 ====================

    /**
     * 出餐类型枚举
     */
    public enum MealPickingType {
        /**
         * 等待拣货
         */
        WAITING(0L, "等待拣货"),

        /**
         * 拣货中
         */
        PICKING(1L, "拣货中"),

        /**
         * 拣货完成
         */
        COMPLETED(2L, "拣货完成");

        private final Long code;
        private final String description;

        MealPickingType(Long code, String description) {
            this.code = code;
            this.description = description;
        }

        public Long getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        /**
         * 根据代码获取枚举值
         * 
         * @param code 状态代码
         * @return 对应的枚举值
         */
        public static MealPickingType fromCode(Long code) {
            if (code == null) {
                return null;
            }
            for (MealPickingType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            return null;
        }
    }
}