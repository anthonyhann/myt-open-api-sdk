/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 时间范围
 * <p>
 * 用于表示订单的送达时间窗口等
 * </p>
 * 
 * <h3>示例：</h3>
 * <pre>{@code
 * TimeRange range = new TimeRange(1657098625L, 1657099625L);
 * Instant start = range.getStartTime();
 * Instant end = range.getEndTime();
 * 
 * // 从 LocalDateTime 创建
 * LocalDateTime startTime = LocalDateTime.of(2022, 7, 6, 14, 30);
 * LocalDateTime endTime = LocalDateTime.of(2022, 7, 6, 15, 30);
 * TimeRange range2 = TimeRange.of(startTime, endTime);
 * }</pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeRange {

    /**
     * 开始时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 结束时间（Unix 时间戳，单位：秒）
     */
    @JsonProperty("end_time")
    private Long endTime;

    // ==================== 构造函数 ====================

    /**
     * 默认构造函数
     */
    public TimeRange() {
    }

    /**
     * 全参构造函数
     * 
     * @param startTime 开始时间（Unix 时间戳，单位：秒）
     * @param endTime 结束时间（Unix 时间戳，单位：秒）
     */
    public TimeRange(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 从 LocalDateTime 创建时间范围
     * 
     * @param start 开始时间
     * @param end 结束时间
     * @return 时间范围对象
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    public static TimeRange of(LocalDateTime start, LocalDateTime end) {
        return of(start, end, ZoneId.systemDefault());
    }

    /**
     * 从 LocalDateTime 创建时间范围（指定时区）
     * 
     * @param start 开始时间
     * @param end 结束时间
     * @param zoneId 时区
     * @return 时间范围对象
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    public static TimeRange of(LocalDateTime start, LocalDateTime end, ZoneId zoneId) {
        if (start == null || end == null || zoneId == null) {
            throw new IllegalArgumentException("时间和时区不能为 null");
        }

        long startTimestamp = start.atZone(zoneId).toEpochSecond();
        long endTimestamp = end.atZone(zoneId).toEpochSecond();

        return new TimeRange(startTimestamp, endTimestamp);
    }

    /**
     * 从 Instant 创建时间范围
     * 
     * @param start 开始时间
     * @param end 结束时间
     * @return 时间范围对象
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    public static TimeRange of(Instant start, Instant end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("时间不能为 null");
        }

        return new TimeRange(start.getEpochSecond(), end.getEpochSecond());
    }

    // ==================== 转换方法 ====================

    /**
     * 获取开始时间的 Instant 对象
     * 
     * @return 开始时间，如果 startTime 为 null 则返回 null
     */
    @JsonIgnore
    public Instant getStartTimeAsInstant() {
        return startTime != null ? Instant.ofEpochSecond(startTime) : null;
    }

    /**
     * 获取结束时间的 Instant 对象
     * 
     * @return 结束时间，如果 endTime 为 null 则返回 null
     */
    @JsonIgnore
    public Instant getEndTimeAsInstant() {
        return endTime != null ? Instant.ofEpochSecond(endTime) : null;
    }

    /**
     * 获取开始时间的 LocalDateTime 对象（系统默认时区）
     * 
     * @return 开始时间，如果 startTime 为 null 则返回 null
     */
    @JsonIgnore
    public LocalDateTime getStartTimeAsLocalDateTime() {
        return getStartTimeAsLocalDateTime(ZoneId.systemDefault());
    }

    /**
     * 获取开始时间的 LocalDateTime 对象（指定时区）
     * 
     * @param zoneId 时区
     * @return 开始时间，如果 startTime 为 null 则返回 null
     * 
     * @throws IllegalArgumentException 如果时区为 null
     */
    @JsonIgnore
    public LocalDateTime getStartTimeAsLocalDateTime(ZoneId zoneId) {
        if (zoneId == null) {
            throw new IllegalArgumentException("时区不能为 null");
        }
        return startTime != null ? 
                LocalDateTime.ofInstant(Instant.ofEpochSecond(startTime), zoneId) : null;
    }

    /**
     * 获取结束时间的 LocalDateTime 对象（系统默认时区）
     * 
     * @return 结束时间，如果 endTime 为 null 则返回 null
     */
    @JsonIgnore
    public LocalDateTime getEndTimeAsLocalDateTime() {
        return getEndTimeAsLocalDateTime(ZoneId.systemDefault());
    }

    /**
     * 获取结束时间的 LocalDateTime 对象（指定时区）
     * 
     * @param zoneId 时区
     * @return 结束时间，如果 endTime 为 null 则返回 null
     * 
     * @throws IllegalArgumentException 如果时区为 null
     */
    @JsonIgnore
    public LocalDateTime getEndTimeAsLocalDateTime(ZoneId zoneId) {
        if (zoneId == null) {
            throw new IllegalArgumentException("时区不能为 null");
        }
        return endTime != null ? 
                LocalDateTime.ofInstant(Instant.ofEpochSecond(endTime), zoneId) : null;
    }

    // ==================== 业务方法 ====================

    /**
     * 计算时间范围的持续时间（秒）
     * 
     * @return 持续时间（秒），如果任一时间为 null 则返回 null
     */
    @JsonIgnore
    public Long getDurationInSeconds() {
        return (startTime != null && endTime != null) ? endTime - startTime : null;
    }

    /**
     * 判断时间范围是否有效（开始时间小于结束时间）
     * 
     * @return true 如果有效，false 如果无效或任一时间为 null
     */
    @JsonIgnore
    public boolean isValid() {
        return startTime != null && endTime != null && startTime < endTime;
    }

    /**
     * 判断指定时间是否在时间范围内
     * 
     * @param timestamp Unix 时间戳（秒）
     * @return true 如果在范围内，false 如果不在范围内或时间范围无效
     */
    @JsonIgnore
    public boolean contains(long timestamp) {
        return isValid() && timestamp >= startTime && timestamp <= endTime;
    }

    /**
     * 判断指定时间是否在时间范围内
     * 
     * @param instant 时间对象
     * @return true 如果在范围内，false 如果不在范围内或时间范围无效
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    @JsonIgnore
    public boolean contains(Instant instant) {
        if (instant == null) {
            throw new IllegalArgumentException("时间不能为 null");
        }
        return contains(instant.getEpochSecond());
    }

    /**
     * 格式化为可读字符串
     * 
     * @return 格式化后的字符串，如 "2022-07-06 14:30:25 ~ 2022-07-06 15:30:25"
     */
    @JsonIgnore
    public String format() {
        return format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 使用指定格式器格式化为可读字符串
     * 
     * @param formatter 时间格式器
     * @return 格式化后的字符串
     * 
     * @throws IllegalArgumentException 如果格式器为 null
     */
    @JsonIgnore
    public String format(DateTimeFormatter formatter) {
        if (formatter == null) {
            throw new IllegalArgumentException("时间格式器不能为 null");
        }

        LocalDateTime start = getStartTimeAsLocalDateTime();
        LocalDateTime end = getEndTimeAsLocalDateTime();

        if (start == null || end == null) {
            return "Invalid TimeRange";
        }

        return String.format("%s ~ %s", 
                start.format(formatter), 
                end.format(formatter));
    }

    // ==================== Getter 和 Setter 方法 ====================

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    // ==================== Object 方法 ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeRange timeRange = (TimeRange) o;
        return Objects.equals(startTime, timeRange.startTime) &&
               Objects.equals(endTime, timeRange.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", formatted='" + (isValid() ? format() : "Invalid") + '\'' +
                '}';
    }
}