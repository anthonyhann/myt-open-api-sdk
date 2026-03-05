/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.consts;

/**
 * SDK常量定义
 * <p>
 * 提供HTTP头、状态码等全局常量，确保整个SDK的一致性
 * 对应Go SDK中的consts包功能
 * </p>
 * 
 * <h3>功能特性：</h3>
 * <ul>
 * <li><strong>HTTP常量</strong>：Content-Type等HTTP相关常量</li>
 * <li><strong>状态码常量</strong>：业务状态码和错误码定义</li>
 * <li><strong>跨平台一致</strong>：与Go SDK保持完全一致的常量值</li>
 * </ul>
 * 
 * <h3>对应关系：</h3>
 * <ul>
 * <li>Go: {@code consts.ContentTypeJson} → Java: {@code Constants.CONTENT_TYPE_JSON}</li>
 * <li>Go: {@code consts.StatusExceptionServerError} → Java: {@code Constants.EXCEPTION_SERVER_ERROR}</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Constants {
    
    // ==================== HTTP Content-Type 相关常量 ====================
    
    /**
     * 标准JSON内容类型
     * <p>
     * 用于HTTP请求头中的Content-Type字段
     * 对应Go SDK: consts.ContentTypeJson
     * </p>
     */
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    /**
     * JSON内容类型（带UTF-8编码声明）
     * <p>
     * 推荐使用此类型，确保中文字符正确处理
     * 麦芽田配送API推荐使用UTF-8编码
     * 对应Go SDK: consts.ContentTypeJsonUTF
     * </p>
     */
    public static final String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=utf-8";
    
    // ==================== HTTP状态码相关常量 ====================
    
    /**
     * 成功状态码
     * <p>HTTP和业务层面都使用200表示成功</p>
     */
    public static final int SUCCESS_CODE = 200;
    
    /**
     * 服务器异常状态码分界线
     * <p>
     * HTTP状态码 >= 600 被视为服务器严重异常
     * SDK会在遇到此类状态码时停止重试机制
     * 用于区分普通5xx错误和严重系统异常
     * 对应Go SDK: consts.StatusExceptionServerError
     * </p>
     */
    public static final int EXCEPTION_SERVER_ERROR = 600;
    
    /**
     * 服务器错误状态码起始值
     * <p>用于重试逻辑判断</p>
     */
    public static final int SERVER_ERROR_START = 500;
    
    // ==================== API版本相关常量 ====================
    
    /**
     * API版本前缀
     * <p>所有配送API都使用v1版本</p>
     */
    public static final String API_VERSION = "v1";
    
    /**
     * 配送API路径前缀
     * <p>完整格式：v1/delivery/</p>
     */
    public static final String DELIVERY_API_PREFIX = API_VERSION + "/delivery";
    
    // ==================== 接口路径常量 ====================
    
    /**
     * 授权码换取访问令牌接口路径
     * <p>对应Go SDK中的 AccessToken 常量</p>
     */
    public static final String ACCESS_TOKEN_PATH = DELIVERY_API_PREFIX + "/access_token";
    
    /**
     * 刷新访问令牌接口路径
     */
    public static final String REFRESH_TOKEN_PATH = DELIVERY_API_PREFIX + "/refresh_token";
    
    /**
     * 配送状态变更接口路径
     */
    public static final String DELIVERY_CHANGE_PATH = DELIVERY_API_PREFIX + "/delivery_change";
    
    /**
     * 位置变更接口路径
     */
    public static final String LOCATION_CHANGE_PATH = DELIVERY_API_PREFIX + "/location_change";
    
    // ==================== 配送状态相关常量 ====================
    
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
    
    // ==================== 位置状态相关常量 ====================
    
    /**
     * 位置状态：未处理
     */
    public static final String LOCATION_STATUS_UNPROGRESS = "UNPROGRESS";
    
    /**
     * 位置状态：待确认
     */
    public static final String LOCATION_STATUS_CREATED = "CREATED";
    
    /**
     * 位置状态：已确认
     */
    public static final String LOCATION_STATUS_CONFIRM = "CONFIRM";
    
    /**
     * 位置状态：待抢单
     */
    public static final String LOCATION_STATUS_DELIVERY = "DELIVERY";
    
    /**
     * 位置状态：待取货
     */
    public static final String LOCATION_STATUS_PICKUP = "PICKUP";
    
    /**
     * 位置状态：配送中
     */
    public static final String LOCATION_STATUS_DELIVERING = "DELIVERING";
    
    /**
     * 位置状态：已完成
     */
    public static final String LOCATION_STATUS_DONE = "DONE";
    
    /**
     * 位置状态：已取消
     */
    public static final String LOCATION_STATUS_CANCEL = "CANCEL";
    
    /**
     * 位置状态：已删除
     */
    public static final String LOCATION_STATUS_DELETE = "DELETE";
    
    /**
     * 位置状态：配送异常
     */
    public static final String LOCATION_STATUS_EXPECT = "EXPECT";
    
    // ==================== 授权相关常量 ====================
    
    /**
     * 授权级别：门店级授权
     */
    public static final String GRANT_TYPE_STORE = "1";
    
    /**
     * 授权级别：商户级授权
     */
    public static final String GRANT_TYPE_MERCHANT = "2";
    
    /**
     * 授权级别：三方门店直联授权
     */
    public static final String GRANT_TYPE_THIRD_PARTY_STORE = "3";
    
    // ==================== 取消类型相关常量 ====================
    
    /**
     * 取消类型：用户取消
     */
    public static final int CANCEL_TYPE_USER = 1;
    
    /**
     * 取消类型：商户取消
     */
    public static final int CANCEL_TYPE_MERCHANT = 2;
    
    /**
     * 取消类型：客服取消
     */
    public static final int CANCEL_TYPE_SERVICE = 3;
    
    /**
     * 取消类型：系统取消
     */
    public static final int CANCEL_TYPE_SYSTEM = 4;
    
    /**
     * 取消类型：其他原因
     */
    public static final int CANCEL_TYPE_OTHER = 5;
    
    // ==================== 默认配置相关常量 ====================
    
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
    
    // ==================== 构造函数 ====================
    
    /**
     * 私有构造函数，防止实例化
     * <p>
     * 常量类不应该被实例化，所有成员都应该是静态的
     * </p>
     */
    private Constants() {
        throw new AssertionError("Constants class should not be instantiated");
    }
}