/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 金额类型（单位：分）
 * <p>
 * 使用整数表示金额，避免浮点数精度问题
 * </p>
 * 
 * <h3>示例：</h3>
 * <pre>{@code
 * Money money = Money.of(12345); // 表示 123.45 元
 * BigDecimal yuan = money.toYuan(); // 转换为 123.45
 * 
 * // 从元创建
 * Money money2 = Money.fromYuan(new BigDecimal("123.45")); // 12345 分
 * }</pre>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class Money {

    /**
     * 金额值（单位：分）
     */
    @JsonValue
    private final long value;

    /**
     * 私有构造函数
     * 
     * @param value 金额值（单位：分）
     */
    private Money(long value) {
        this.value = value;
    }

    /**
     * 创建金额对象
     * 
     * @param fen 金额值（单位：分）
     * @return 金额对象
     */
    public static Money of(long fen) {
        return new Money(fen);
    }

    /**
     * 从元转换为分
     * 
     * @param yuan 以元为单位的金额
     * @return 金额对象（单位：分）
     * 
     * @throws IllegalArgumentException 如果金额为 null
     * 
     * @example Money money = Money.fromYuan(new BigDecimal("123.45")); // 返回 12345 分
     */
    public static Money fromYuan(BigDecimal yuan) {
        if (yuan == null) {
            throw new IllegalArgumentException("金额不能为 null");
        }
        
        // 乘以 100 并四舍五入到整数
        BigDecimal fen = yuan.multiply(BigDecimal.valueOf(100))
                             .setScale(0, RoundingMode.HALF_UP);
        return new Money(fen.longValue());
    }

    /**
     * 从元转换为分（使用 double）
     * 
     * @param yuan 以元为单位的金额
     * @return 金额对象（单位：分）
     * 
     * @deprecated 推荐使用 {@link #fromYuan(BigDecimal)} 避免精度问题
     */
    @Deprecated
    public static Money fromYuan(double yuan) {
        return fromYuan(BigDecimal.valueOf(yuan));
    }

    /**
     * 转换为元（BigDecimal）
     * 
     * @return 以元为单位的金额（保留 2 位小数）
     * 
     * @example 
     * Money money = Money.of(12345);
     * BigDecimal yuan = money.toYuan(); // 返回 123.45
     */
    @JsonIgnore
    public BigDecimal toYuan() {
        return BigDecimal.valueOf(value)
                         .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    /**
     * 转换为元（double）
     * 
     * @return 以元为单位的金额
     * 
     * @deprecated 推荐使用 {@link #toYuan()} 避免精度问题
     */
    @Deprecated
    @JsonIgnore
    public double toYuanDouble() {
        return value / 100.0;
    }

    /**
     * 获取原始值（单位：分）
     * 
     * @return 金额值（单位：分）
     */
    public long getValue() {
        return value;
    }

    /**
     * 金额相加
     * 
     * @param other 另一个金额对象
     * @return 相加后的新金额对象
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    @JsonIgnore
    public Money add(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("金额不能为 null");
        }
        return new Money(this.value + other.value);
    }

    /**
     * 金额相减
     * 
     * @param other 另一个金额对象
     * @return 相减后的新金额对象
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    @JsonIgnore
    public Money subtract(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("金额不能为 null");
        }
        return new Money(this.value - other.value);
    }

    /**
     * 金额乘法
     * 
     * @param multiplier 乘数
     * @return 相乘后的新金额对象
     */
    @JsonIgnore
    public Money multiply(long multiplier) {
        return new Money(this.value * multiplier);
    }

    /**
     * 金额除法
     * 
     * @param divisor 除数
     * @return 相除后的新金额对象
     * 
     * @throws ArithmeticException 如果除数为 0
     */
    @JsonIgnore
    public Money divide(long divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("除数不能为 0");
        }
        return new Money(this.value / divisor);
    }

    /**
     * 判断是否为正数
     * 
     * @return true 如果金额大于 0
     */
    @JsonIgnore
    public boolean isPositive() {
        return value > 0;
    }

    /**
     * 判断是否为负数
     * 
     * @return true 如果金额小于 0
     */
    @JsonIgnore
    public boolean isNegative() {
        return value < 0;
    }

    /**
     * 判断是否为零
     * 
     * @return true 如果金额等于 0
     */
    @JsonIgnore
    public boolean isZero() {
        return value == 0;
    }

    /**
     * 比较金额大小
     * 
     * @param other 另一个金额对象
     * @return 负数、零、正数分别表示小于、等于、大于
     * 
     * @throws IllegalArgumentException 如果参数为 null
     */
    public int compareTo(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("金额不能为 null");
        }
        return Long.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("Money{value=%d分, yuan=%s元}", value, toYuan().toString());
    }

    // ==================== 静态常量 ====================

    /** 零元 */
    public static final Money ZERO = new Money(0);

    /** 一分 */
    public static final Money ONE_FEN = new Money(1);

    /** 一元 */
    public static final Money ONE_YUAN = new Money(100);
}