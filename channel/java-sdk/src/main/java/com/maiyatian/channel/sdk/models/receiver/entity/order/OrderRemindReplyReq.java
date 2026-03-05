/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 催单回复请求参数（麦芽田推送给三方）
 * <p>
 * 麦芽田通知三方催单的回复消息
 * </p>
 * 
 * <h3>使用场景：</h3>
 * 麦芽田通知三方商家回复顾客催单的消息
 * 
 * <h3>业务流程：</h3>
 * 麦芽田 → 推送催单回复请求 → 三方处理回复逻辑 → 三方回复处理结果
 * 
 * <h3>注意事项：</h3>
 * <ul>
 * <li>这是麦芽田推送给三方的回调接口</li>
 * <li>三方需要实现此接口的回调处理逻辑</li>
 * <li>回复类型：1-备货中，2-已送出，3-天气原因，4-人手不足，5-其他</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderRemindReplyReq {

    /**
     * 平台方渠道 ID（三方平台的门店标识）
     */
    @NotBlank(message = "门店ID不能为空")
    @JsonProperty("shop_id")
    private String shopId;

    /**
     * 催单 ID
     */
    @NotNull(message = "催单ID不能为空")
    @JsonProperty("remind_id")
    private Integer remindId;

    /**
     * 回复类型：1-备货中，2-已送出，3-天气原因，4-人手不足，5-其他
     */
    @JsonProperty("reply_type")
    private Integer replyType;

    /**
     * 回复内容
     */
    @JsonProperty("reply_content")
    private String replyContent;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public OrderRemindReplyReq() {
    }

    /**
     * 全参构造函数
     * 
     * @param shopId 平台方渠道ID
     * @param remindId 催单ID
     * @param replyType 回复类型
     * @param replyContent 回复内容
     */
    public OrderRemindReplyReq(String shopId, Integer remindId, Integer replyType, String replyContent) {
        this.shopId = shopId;
        this.remindId = remindId;
        this.replyType = replyType;
        this.replyContent = replyContent;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getRemindId() {
        return remindId;
    }

    public void setRemindId(Integer remindId) {
        this.remindId = remindId;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRemindReplyReq that = (OrderRemindReplyReq) o;
        return Objects.equals(shopId, that.shopId) &&
               Objects.equals(remindId, that.remindId) &&
               Objects.equals(replyType, that.replyType) &&
               Objects.equals(replyContent, that.replyContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, remindId, replyType, replyContent);
    }

    @Override
    public String toString() {
        return "OrderRemindReplyReq{" +
                "shopId='" + shopId + '\'' +
                ", remindId=" + remindId +
                ", replyType=" + replyType +
                ", replyContent='" + replyContent + '\'' +
                '}';
    }

    // ==================== 便捷方法 ====================

    /**
     * 检查请求参数是否有效
     * 
     * @return 如果门店ID和催单ID都不为空返回true，否则返回false
     */
    @JsonIgnore
    public boolean isValid() {
        return shopId != null && !shopId.trim().isEmpty() &&
               remindId != null;
    }

    /**
     * 检查是否有回复类型
     * 
     * @return 如果有回复类型返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasReplyType() {
        return replyType != null;
    }

    /**
     * 检查是否有回复内容
     * 
     * @return 如果有回复内容返回true，否则返回false
     */
    @JsonIgnore
    public boolean hasReplyContent() {
        return replyContent != null && !replyContent.trim().isEmpty();
    }

    /**
     * 获取回复类型的中文描述
     * 
     * @return 回复类型的中文描述
     */
    @JsonIgnore
    public String getReplyTypeDescription() {
        if (replyType == null) {
            return "未知类型";
        }
        
        ReplyType type = ReplyType.fromCode(replyType);
        return type != null ? type.getDescription() : "未知类型(" + replyType + ")";
    }

    /**
     * 获取格式化的回复信息
     * 
     * @return 格式化的回复信息
     */
    @JsonIgnore
    public String getFormattedReply() {
        StringBuilder sb = new StringBuilder();
        sb.append("回复类型：").append(getReplyTypeDescription());
        if (hasReplyContent()) {
            sb.append("，回复内容：").append(replyContent);
        }
        return sb.toString();
    }

    // ==================== 枚举定义 ====================

    /**
     * 回复类型枚举
     */
    public enum ReplyType {
        /**
         * 备货中
         */
        PREPARING(1, "备货中"),

        /**
         * 已送出
         */
        DISPATCHED(2, "已送出"),

        /**
         * 天气原因
         */
        WEATHER_ISSUE(3, "天气原因"),

        /**
         * 人手不足
         */
        STAFF_SHORTAGE(4, "人手不足"),

        /**
         * 其他
         */
        OTHER(5, "其他");

        private final Integer code;
        private final String description;

        ReplyType(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        /**
         * 根据代码获取枚举值
         * 
         * @param code 回复类型代码
         * @return 对应的枚举值
         */
        public static ReplyType fromCode(Integer code) {
            if (code == null) {
                return null;
            }
            for (ReplyType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            return null;
        }
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建催单回复请求的便捷工厂方法
     * 
     * @param shopId 门店ID
     * @param remindId 催单ID
     * @param replyType 回复类型
     * @param replyContent 回复内容
     * @return 催单回复请求对象
     */
    public static OrderRemindReplyReq of(String shopId, Integer remindId, Integer replyType, String replyContent) {
        return new OrderRemindReplyReq(shopId, remindId, replyType, replyContent);
    }

    /**
     * 使用回复类型枚举创建催单回复请求
     * 
     * @param shopId 门店ID
     * @param remindId 催单ID
     * @param replyType 回复类型枚举
     * @param replyContent 回复内容
     * @return 催单回复请求对象
     */
    public static OrderRemindReplyReq of(String shopId, Integer remindId, ReplyType replyType, String replyContent) {
        Integer typeCode = replyType != null ? replyType.getCode() : null;
        return new OrderRemindReplyReq(shopId, remindId, typeCode, replyContent);
    }

    /**
     * 创建基本催单回复请求（仅必要字段）
     * 
     * @param shopId 门店ID
     * @param remindId 催单ID
     * @return 催单回复请求对象
     */
    public static OrderRemindReplyReq basic(String shopId, Integer remindId) {
        return new OrderRemindReplyReq(shopId, remindId, null, null);
    }

    /**
     * 创建预定义类型的催单回复请求
     * 
     * @param shopId 门店ID
     * @param remindId 催单ID
     * @param replyType 回复类型枚举
     * @return 催单回复请求对象
     */
    public static OrderRemindReplyReq withType(String shopId, Integer remindId, ReplyType replyType) {
        Integer typeCode = replyType != null ? replyType.getCode() : null;
        return new OrderRemindReplyReq(shopId, remindId, typeCode, null);
    }
}