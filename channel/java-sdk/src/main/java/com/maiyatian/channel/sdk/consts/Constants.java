/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.consts;

/**
 * 麦芽田开放平台 Java SDK 常量定义
 * <p>
 * 对应 Go SDK 中的 consts/consts.go，提供HTTP头、状态码等全局常量
 * 确保整个SDK的一致性，遵循SOLID原则的单一职责原则
 * </p>
 * 
 * <h3>SOLID原则应用：</h3>
 * <ul>
 * <li><strong>S (单一职责)</strong>：专门负责常量定义，不包含业务逻辑</li>
 * <li><strong>O (开闭原则)</strong>：常量为final，不可修改，扩展通过新增常量实现</li>
 * <li><strong>L (里氏替换)</strong>：常量类型明确，可安全替换使用</li>
 * <li><strong>I (接口隔离)</strong>：按功能分组常量，避免单一巨大常量类</li>
 * <li><strong>D (依赖反转)</strong>：上层模块依赖抽象常量，而非具体值</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public final class Constants {

    // 私有构造函数，防止实例化（SOLID：单一职责原则）
    private Constants() {
        throw new UnsupportedOperationException("常量类不应该被实例化");
    }

    /**
     * HTTP Content-Type 相关常量
     * <p>
     * 对应 Go SDK 中的 ContentType 常量定义
     * </p>
     */
    public static final class ContentType {
        
        private ContentType() {
            throw new UnsupportedOperationException("常量类不应该被实例化");
        }

        /**
         * 标准JSON内容类型
         * <p>
         * 对应 Go SDK: {@code ContentTypeJson = "application/json"}
         * 用于HTTP请求头中的Content-Type字段
         * </p>
         */
        public static final String JSON = "application/json";

        /**
         * JSON内容类型（带UTF-8编码声明）
         * <p>
         * 对应 Go SDK: {@code ContentTypeJsonUTF = "application/json; charset=utf-8"}
         * 推荐使用此类型，确保中文字符正确处理
         * 麦芽田API推荐使用UTF-8编码
         * </p>
         */
        public static final String JSON_UTF8 = "application/json; charset=utf-8";
    }

    /**
     * HTTP状态码相关常量
     * <p>
     * 对应 Go SDK 中的状态码常量定义，用于重试逻辑和错误处理
     * </p>
     */
    public static final class HttpStatus {
        
        private HttpStatus() {
            throw new UnsupportedOperationException("常量类不应该被实例化");
        }

        /**
         * HTTP成功状态码
         * <p>
         * 标准HTTP 200 OK状态码，表示请求成功处理
         * </p>
         */
        public static final int OK = 200;

        /**
         * HTTP客户端错误起始状态码
         * <p>
         * 4xx系列错误的起始值，客户端错误通常不需要重试
         * </p>
         */
        public static final int BAD_REQUEST_START = 400;

        /**
         * HTTP服务器错误起始状态码
         * <p>
         * 5xx系列错误的起始值，服务器错误通常需要重试
         * </p>
         */
        public static final int INTERNAL_SERVER_ERROR = 500;

        /**
         * 服务器异常状态码分界线
         * <p>
         * 对应 Go SDK: {@code StatusExceptionServerError = 600}
         * HTTP状态码 >= 600 被视为服务器严重异常
         * SDK会在遇到此类状态码时停止重试机制
         * 用于区分普通5xx错误和严重系统异常
         * </p>
         */
        public static final int EXCEPTION_SERVER_ERROR = 600;
    }

    /**
     * SDK相关常量
     * <p>
     * SDK运行时的基础配置常量
     * </p>
     */
    public static final class SDK {
        
        private SDK() {
            throw new UnsupportedOperationException("常量类不应该被实例化");
        }

        /**
         * SDK名称标识
         * <p>
         * 用于HTTP请求头中的User-Agent字段
         * </p>
         */
        public static final String NAME = "Myt-Java-SDK";

        /**
         * 默认请求超时时间（毫秒）
         * <p>
         * 30秒超时，适合大多数网络环境
         * </p>
         */
        public static final int DEFAULT_TIMEOUT_MS = 30000;

        /**
         * 默认重试次数
         * <p>
         * 网络错误和5xx错误的最大重试次数
         * </p>
         */
        public static final int DEFAULT_RETRY_ATTEMPTS = 3;

        /**
         * 默认连接池大小
         * <p>
         * HTTP连接池的默认最大连接数
         * </p>
         */
        public static final int DEFAULT_MAX_CONNECTIONS = 50;
    }

    /**
     * API路径常量
     * <p>
     * 麦芽田开放平台API接口路径定义，确保路径的一致性和可维护性
     * </p>
     */
    public static final class ApiPath {
        
        private ApiPath() {
            throw new UnsupportedOperationException("常量类不应该被实例化");
        }

        /**
         * API版本前缀
         */
        public static final String V1_PREFIX = "v1/channel/";

        // 授权管理接口路径
        public static final String ACCESS_TOKEN = V1_PREFIX + "access_token";
        public static final String REFRESH_TOKEN = V1_PREFIX + "refresh_token";

        // 订单推送接口路径
        public static final String ORDER_CREATED = V1_PREFIX + "order_created";
        public static final String ORDER_MODIFIED = V1_PREFIX + "order_modified";
        public static final String ORDER_CONFIRMED = V1_PREFIX + "order_confirmed";
        public static final String ORDER_REMIND = V1_PREFIX + "order_remind";
        public static final String ORDER_CANCELED = V1_PREFIX + "order_canceled";
        public static final String ORDER_DONE = V1_PREFIX + "order_done";

        // 退款推送接口路径
        public static final String ORDER_APPLY_REFUND = V1_PREFIX + "order_apply_refund";
        public static final String ORDER_REFUNDED = V1_PREFIX + "order_refunded";

        // 配送推送接口路径
        public static final String SELF_DELIVERY_CHANGE = V1_PREFIX + "self_delivery_change";

        // 门店查询接口路径
        public static final String SHOP_INFO = V1_PREFIX + "shop_info";
    }

    /**
     * 日志相关常量
     * <p>
     * SDK日志记录的配置常量
     * </p>
     */
    public static final class Logging {
        
        private Logging() {
            throw new UnsupportedOperationException("常量类不应该被实例化");
        }

        /**
         * SDK日志前缀
         * <p>
         * 用于区分SDK日志和应用日志
         * </p>
         */
        public static final String LOG_PREFIX = "[MytSDK] ";

        /**
         * 默认日志级别
         * <p>
         * 生产环境建议使用INFO级别
         * </p>
         */
        public static final String DEFAULT_LOG_LEVEL = "INFO";
    }

    /**
     * 环境变量相关常量
     * <p>
     * SDK使用的环境变量名称定义，对应Go SDK的环境变量配置
     * </p>
     */
    public static final class Environment {
        
        private Environment() {
            throw new UnsupportedOperationException("常量类不应该被实例化");
        }

        /**
         * 调试模式环境变量名
         * <p>
         * 对应 Go SDK 中的 DEBUG 环境变量
         * 设置为 "true" 开启调试模式，其他值或未设置则关闭
         * </p>
         */
        public static final String DEBUG = "DEBUG";

        /**
         * SDK版本环境变量名
         * <p>
         * 可以通过环境变量覆盖默认版本号
         * </p>
         */
        public static final String SDK_VERSION = "MYT_SDK_VERSION";

        /**
         * API基础地址环境变量名
         * <p>
         * 可以通过环境变量配置API地址，便于不同环境切换
         * </p>
         */
        public static final String API_BASE_URL = "MYT_API_BASE_URL";
    }
}