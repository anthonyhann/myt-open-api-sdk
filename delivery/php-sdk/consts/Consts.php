<?php

namespace Maiyatian\Delivery\PhpSdk\Consts;

/**
 * 麦芽田配送开放平台 PHP SDK 常量定义
 * 对应 Go SDK 的 consts 包，确保整个SDK的一致性
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class Consts
{
    // ==================== HTTP Content-Type 相关常量 ====================
    
    /**
     * 标准JSON内容类型
     * 用于HTTP请求头中的Content-Type字段
     */
    public const CONTENT_TYPE_JSON = 'application/json';
    
    /**
     * JSON内容类型（带UTF-8编码声明）
     * 推荐使用此类型，确保中文字符正确处理
     * 麦芽田配送API推荐使用UTF-8编码
     */
    public const CONTENT_TYPE_JSON_UTF8 = 'application/json; charset=utf-8';
    
    // ==================== HTTP状态码相关常量 ====================
    
    /**
     * 服务器异常状态码分界线
     * HTTP状态码 >= 600 被视为服务器严重异常
     * SDK会在遇到此类状态码时停止重试机制
     * 用于区分普通5xx错误和严重系统异常
     */
    public const STATUS_EXCEPTION_SERVER_ERROR = 600;
    
    /**
     * 服务器内部错误起始码
     */
    public const SERVER_ERROR_START = 500;
    
    // ==================== API 路径常量 ====================
    
    /**
     * 默认API版本前缀
     */
    public const API_VERSION = 'v1';
    
    /**
     * 配送API路径前缀
     */
    public const DELIVERY_PATH_PREFIX = 'v1/delivery';
    
    /**
     * 快递API路径前缀
     */
    public const EXPRESS_PATH_PREFIX = 'v1/express';
    
    // ==================== 业务状态码常量 ====================
    
    /**
     * API调用成功状态码
     */
    public const SUCCESS_CODE = 200;
    
    // ==================== 环境常量 ====================
    
    /**
     * 测试环境基础URL
     */
    public const TEST_BASE_URL = 'https://open-api-test.maiyatian.com';
    
    /**
     * 正式环境基础URL  
     */
    public const PROD_BASE_URL = 'https://open-api.maiyatian.com';
    
    // ==================== 授权相关常量 ====================
    
    /**
     * 门店级授权类型
     */
    public const GRANT_TYPE_STORE = '1';
    
    /**
     * 商户级授权类型
     */
    public const GRANT_TYPE_MERCHANT = '2';
    
    // ==================== 请求相关常量 ====================
    
    /**
     * 默认请求超时时间（秒）
     */
    public const DEFAULT_TIMEOUT = 60;
    
    /**
     * 默认连接超时时间（秒）
     */
    public const DEFAULT_CONNECT_TIMEOUT = 30;
    
    /**
     * 默认重试次数
     */
    public const DEFAULT_RETRY_ATTEMPTS = 3;
    
    /**
     * 默认重试基础延迟（秒）
     */
    public const DEFAULT_RETRY_BASE_DELAY = 1;
    
    /**
     * 默认重试最大延迟（秒）
     */
    public const DEFAULT_RETRY_MAX_DELAY = 30;
    
    // ==================== 配送业务相关常量 ====================
    
    /**
     * 配送状态：待分配
     */
    public const DELIVERY_STATUS_PENDING = 1;
    
    /**
     * 配送状态：配送中
     */
    public const DELIVERY_STATUS_DELIVERING = 2;
    
    /**
     * 配送状态：已送达
     */
    public const DELIVERY_STATUS_DELIVERED = 3;
    
    /**
     * 配送状态：已取消
     */
    public const DELIVERY_STATUS_CANCELED = 4;
    
    // ==================== 快递业务相关常量 ====================
    
    /**
     * 快递类型：即时配送
     */
    public const EXPRESS_TYPE_INSTANT = 'instant';
    
    /**
     * 快递类型：预约配送
     */
    public const EXPRESS_TYPE_SCHEDULED = 'scheduled';
    
    /**
     * 快递类型：次日配送
     */
    public const EXPRESS_TYPE_NEXT_DAY = 'next_day';
    
    // ==================== 位置变更类型常量 ====================
    
    /**
     * 位置变更类型：实时位置
     */
    public const LOCATION_TYPE_REAL_TIME = 'real_time';
    
    /**
     * 位置变更类型：路径轨迹
     */
    public const LOCATION_TYPE_TRACK = 'track';
    
    /**
     * 位置变更类型：关键节点
     */
    public const LOCATION_TYPE_MILESTONE = 'milestone';
}