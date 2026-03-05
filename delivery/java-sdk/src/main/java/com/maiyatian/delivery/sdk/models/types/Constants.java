/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.types;

/**
 * 常量定义
 * <p>
 * 包含API文档中提到的各种枚举值和常量
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Constants {
    
    // ==================== HTTP 相关常量 ====================
    
    /**
     * 测试环境API基础URL
     */
    public static final String TEST_BASE_URL = "https://open-api-test.maiyatian.com";
    
    /**
     * 正式环境API基础URL
     */
    public static final String PROD_BASE_URL = "https://open-api.maiyatian.com";
    
    /**
     * 默认SDK版本号
     */
    public static final String DEFAULT_SDK_VERSION = "1.0.0";
    
    // ==================== 状态码相关常量 ====================
    
    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 200;
    
    /**
     * 服务器错误状态码起始值
     */
    public static final int SERVER_ERROR_START = 500;
    
    // ==================== 配送状态相关常量 ====================
    
    /**
     * 配送状态：待接单
     */
    public static final String DELIVERY_STATUS_PENDING = "PENDING";
    
    /**
     * 配送状态：已分配骑手
     */
    public static final String DELIVERY_STATUS_GRABBED = "GRABBED";
    
    /**
     * 配送状态：待取货
     */
    public static final String DELIVERY_STATUS_PICKUP = "PICKUP";
    
    /**
     * 配送状态：配送中
     */
    public static final String DELIVERY_STATUS_DELIVERING = "DELIVERING";
    
    /**
     * 配送状态：已完成
     */
    public static final String DELIVERY_STATUS_DONE = "DONE";
    
    /**
     * 配送状态：已取消
     */
    public static final String DELIVERY_STATUS_CANCEL = "CANCEL";
    
    /**
     * 配送状态：骑手取消
     */
    public static final String DELIVERY_STATUS_RIDER_CANCEL = "RIDER_CANCEL";
    
    /**
     * 配送状态：骑手转单
     */
    public static final String DELIVERY_STATUS_TRANSFER = "TRANSFER";
    
    /**
     * 配送状态：配送异常
     */
    public static final String DELIVERY_STATUS_EXPECT = "EXPECT";
    
    // ==================== 订单来源相关常量 ====================
    
    /**
     * 订单来源：美团
     */
    public static final String ORDER_SOURCE_MEITUAN = "meituan";
    
    /**
     * 订单来源：饿了么
     */
    public static final String ORDER_SOURCE_ELEME = "eleme";
    
    /**
     * 订单来源：京东
     */
    public static final String ORDER_SOURCE_JD = "jd";
    
    // ==================== 授权相关常量 ====================
    
    /**
     * 授权级别：门店
     */
    public static final int GRANT_TYPE_STORE = 1;
    
    /**
     * 授权级别：商户
     */
    public static final int GRANT_TYPE_MERCHANT = 2;
    
    /**
     * 授权级别：三方门店直联授权
     */
    public static final int GRANT_TYPE_THIRD_PARTY_STORE = 3;
    
    // ==================== 发票类型相关常量 ====================
    
    /**
     * 发票类型：公司
     */
    public static final String INVOICE_TYPE_COMPANY = "1";
    
    /**
     * 发票类型：个人
     */
    public static final String INVOICE_TYPE_PERSONAL = "2";
    
    // ==================== 支付方式相关常量 ====================
    
    /**
     * 支付方式：寄付现结
     */
    public static final int PAY_MODE_SENDER_PAY_CASH = 0;
    
    /**
     * 支付方式：寄付月结
     */
    public static final int PAY_MODE_SENDER_PAY_MONTHLY = 1;
    
    /**
     * 支付方式：收方付
     */
    public static final int PAY_MODE_RECEIVER_PAY = 2;
    
    /**
     * 支付方式：第三方付
     */
    public static final int PAY_MODE_THIRD_PARTY_PAY = 3;
    
    // ==================== 上门服务相关常量 ====================
    
    /**
     * 不上门服务
     */
    public static final int NOT_DOOR_SERVICE = 0;
    
    /**
     * 上门服务
     */
    public static final int DOOR_SERVICE = 1;
    
    // ==================== 构造函数 ====================
    
    /**
     * 私有构造函数，防止实例化
     */
    private Constants() {
        throw new AssertionError("Constants class should not be instantiated");
    }
}