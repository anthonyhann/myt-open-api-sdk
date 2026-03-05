/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.vars;

import java.io.InputStream;
import java.util.Properties;

/**
 * SDK全局变量管理
 * <p>
 * 提供版本信息、调试开关等运行时变量管理
 * 对应Go SDK中的vars包功能
 * </p>
 * 
 * <h3>功能特性：</h3>
 * <ul>
 * <li><strong>版本管理</strong>：自动从version.properties读取版本信息</li>
 * <li><strong>调试开关</strong>：从环境变量DEBUG控制调试行为</li>
 * <li><strong>User-Agent</strong>：生成标准的HTTP User-Agent头</li>
 * </ul>
 * 
 * <h3>对应关系：</h3>
 * <ul>
 * <li>Go: {@code vars.VERSION} → Java: {@code Variables.getVersion()}</li>
 * <li>Go: {@code vars.IsDebugging} → Java: {@code Variables.isDebugging()}</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Variables {
    
    /**
     * SDK版本号
     * 从version.properties文件中读取
     */
    private static final String VERSION;
    
    /**
     * 调试模式开关
     * 从环境变量DEBUG中读取布尔值，控制SDK的调试行为
     */
    private static final boolean IS_DEBUGGING;
    
    /**
     * 默认版本号（当无法读取version.properties时使用）
     */
    private static final String DEFAULT_VERSION = "1.0.0";
    
    // 静态初始化块，加载版本和调试配置
    static {
        VERSION = loadVersion();
        IS_DEBUGGING = parseDebuggingFlag();
    }
    
    /**
     * 获取SDK版本号
     * <p>
     * 对应Go SDK中的vars.VERSION变量
     * </p>
     * 
     * <h3>版本格式：</h3>
     * <ul>
     * <li>稳定版：v1.2.3</li>
     * <li>测试版：v1.2.3-beta.1</li>
     * </ul>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * String version = Variables.getVersion();
     * System.out.println("当前SDK版本: " + version);
     * }</pre>
     * 
     * @return SDK版本号字符串
     */
    public static String getVersion() {
        return VERSION;
    }
    
    /**
     * 检查是否启用调试模式
     * <p>
     * 对应Go SDK中的vars.IsDebugging变量
     * </p>
     * 
     * <h3>设置方式：</h3>
     * <ul>
     * <li>export DEBUG=true   # 开启调试模式</li>
     * <li>export DEBUG=false  # 关闭调试模式（默认）</li>
     * </ul>
     * 
     * <h3>调试模式下的行为：</h3>
     * <ul>
     * <li>输出更详细的HTTP请求日志</li>
     * <li>显示签名生成过程</li>
     * <li>输出错误堆栈信息</li>
     * <li>记录重试详情</li>
     * </ul>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * if (Variables.isDebugging()) {
     *     System.out.println("调试信息: 请求URL=" + url);
     * }
     * }</pre>
     * 
     * @return true表示启用调试模式，false表示关闭
     */
    public static boolean isDebugging() {
        return IS_DEBUGGING;
    }
    
    /**
     * 生成标准User-Agent头
     * <p>
     * 格式：Myt-Java-SDK/版本号
     * 用于HTTP请求头中的User-Agent字段
     * </p>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * String userAgent = Variables.getUserAgent();
     * // 结果: "Myt-Java-SDK/1.0.0"
     * }</pre>
     * 
     * @return 标准格式的User-Agent字符串
     */
    public static String getUserAgent() {
        return "Myt-Java-SDK/" + VERSION;
    }
    
    /**
     * 从version.properties文件加载版本号
     * 
     * @return 版本号字符串，加载失败时返回默认版本
     */
    private static String loadVersion() {
        try (InputStream input = Variables.class.getClassLoader()
                .getResourceAsStream("version.properties")) {
            
            if (input == null) {
                // version.properties文件不存在，使用默认版本
                if (IS_DEBUGGING) {
                    System.err.println("[MytSDK] Warning: version.properties not found, using default version: " + DEFAULT_VERSION);
                }
                return DEFAULT_VERSION;
            }
            
            Properties props = new Properties();
            props.load(input);
            
            String version = props.getProperty("version", DEFAULT_VERSION).trim();
            
            if (IS_DEBUGGING) {
                System.out.println("[MytSDK] Loaded version from properties: " + version);
            }
            
            return version;
            
        } catch (Exception e) {
            // 加载异常，使用默认版本
            if (IS_DEBUGGING) {
                System.err.println("[MytSDK] Failed to load version.properties: " + e.getMessage());
            }
            return DEFAULT_VERSION;
        }
    }
    
    /**
     * 解析调试标志
     * 从环境变量DEBUG中读取布尔值
     * 
     * @return 调试标志，解析失败时返回false（安全优先）
     */
    private static boolean parseDebuggingFlag() {
        try {
            String debugEnv = System.getenv("DEBUG");
            if (debugEnv == null) {
                return false;
            }
            
            // 支持多种格式：true, TRUE, 1, yes, YES, on, ON
            debugEnv = debugEnv.trim().toLowerCase();
            return "true".equals(debugEnv) || 
                   "1".equals(debugEnv) || 
                   "yes".equals(debugEnv) || 
                   "on".equals(debugEnv);
                   
        } catch (Exception e) {
            // 解析失败时默认为false（安全优先）
            System.err.println("[MytSDK] Failed to parse DEBUG environment variable: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 私有构造函数，防止实例化
     */
    private Variables() {
        throw new AssertionError("Variables class should not be instantiated");
    }
}