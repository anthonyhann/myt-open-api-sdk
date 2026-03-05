/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * 订单相关提醒时间
 * <p>
 * 包含订单处理过程中的关键时间节点提醒
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeReminder {

    /**
     * 建议最晚呼叫骑手时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("estimated_call_rider_time")
    private Long estimatedCallRiderTime;

    /**
     * 建议最晚出餐时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("estimated_finish_cooking_time")
    private Long estimatedFinishCookingTime;

    /**
     * 最晚开始备餐时间（Unix 时间戳，单位：秒，仅预订单会返回该值）
     */
    @JsonProperty("latest_start_cooking_time")
    private Long latestStartCookingTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public TimeReminder() {
    }

    /**
     * 全参构造函数
     */
    public TimeReminder(Long estimatedCallRiderTime, Long estimatedFinishCookingTime, Long latestStartCookingTime) {
        this.estimatedCallRiderTime = estimatedCallRiderTime;
        this.estimatedFinishCookingTime = estimatedFinishCookingTime;
        this.latestStartCookingTime = latestStartCookingTime;
    }

    // ==================== Getter 和 Setter 方法 ====================

    public Long getEstimatedCallRiderTime() {
        return estimatedCallRiderTime;
    }

    public void setEstimatedCallRiderTime(Long estimatedCallRiderTime) {
        this.estimatedCallRiderTime = estimatedCallRiderTime;
    }

    public Long getEstimatedFinishCookingTime() {
        return estimatedFinishCookingTime;
    }

    public void setEstimatedFinishCookingTime(Long estimatedFinishCookingTime) {
        this.estimatedFinishCookingTime = estimatedFinishCookingTime;
    }

    public Long getLatestStartCookingTime() {
        return latestStartCookingTime;
    }

    public void setLatestStartCookingTime(Long latestStartCookingTime) {
        this.latestStartCookingTime = latestStartCookingTime;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeReminder that = (TimeReminder) o;
        return Objects.equals(estimatedCallRiderTime, that.estimatedCallRiderTime) &&
               Objects.equals(estimatedFinishCookingTime, that.estimatedFinishCookingTime) &&
               Objects.equals(latestStartCookingTime, that.latestStartCookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estimatedCallRiderTime, estimatedFinishCookingTime, latestStartCookingTime);
    }

    @Override
    public String toString() {
        return "TimeReminder{" +
                "estimatedCallRiderTime=" + estimatedCallRiderTime +
                ", estimatedFinishCookingTime=" + estimatedFinishCookingTime +
                ", latestStartCookingTime=" + latestStartCookingTime +
                '}';
    }
}
