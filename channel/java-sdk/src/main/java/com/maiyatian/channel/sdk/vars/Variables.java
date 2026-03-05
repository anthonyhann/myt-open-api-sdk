/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.vars;

import com.maiyatian.channel.sdk.consts.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 麦芽田开放平台 Java SDK 全局变量管理
 * <p>
 * 对应 Go SDK 中的 vars/vars.go 和 vars/version.go，提供版本信息、调试开关等运行时变量
 * 支持从 version.properties 文件和环境变量读取配置
 * </p>
 * 
 * <h3>SOLID原则应用：</h3>
 * <ul>
 * <li><strong>S (单一职责)</strong>：专门管理SDK全局变量和配置</li>
 * <li><strong>O (开闭原则)</strong>：通过环境变量扩展配置，无需修改代码</li>
 * <li><strong>L (里氏替换)</strong>：配置加载逻辑可被子类安全重写</li>
 * <li><strong>I (接口隔离)</strong>：分离版本管理和调试配置</li>
 * <li><strong>D (依赖反转)</strong>：依赖环境变量抽象，而非硬编码值</li>
 * </ul>
 * 
 * <h3>对应Go SDK功能：</h3>
 * <ul>
 * <li>Go: {@code vars.VERSION} → Java: {@code Variables.getVersion()}</li>
 * <li>Go: {@code vars.IsDebugging} → Java: {@code Variables.isDebugging()}</li>
 * <li>Go: {@code //go:embed version.txt} → Java: version.properties资源文件</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 2.0.0
 * @since 2.0.0
 */
public final class Variables {

    private static final String VERSION_PROPERTIES_FILE = "version.properties";
    private static final String VERSION_KEY = "sdk.version";
    private static final String DEFAULT_VERSION = "2.0.0";

    // 缓存变量，避免重复计算
    private static volatile String cachedVersion;
    private static volatile Boolean cachedIsDebugging;
    private static volatile String cachedUserAgent;

    // 私有构造函数，防止实例化（SOLID：单一职责原则）
    private Variables() {
        throw new UnsupportedOperationException("全局变量类不应该被实例化");
    }

    /**
     * 获取SDK版本号
     * <p>
     * 对应 Go SDK: {@code vars.VERSION}
     * 版本信息读取优先级：
     * </p>
     * <ol>
     * <li>环境变量 {@code MYT_SDK_VERSION}</li>
     * <li>version.properties 文件中的 {@code sdk.version} 属性</li>
     * <li>默认版本号 {@code 2.0.0}</li>
     * </ol>
     * 
     * <h3>使用示例：</h3>
     * <pre>{@code
     * String version = Variables.getVersion();
     * System.out.println("当前SDK版本: " + version);
     * }</pre>
     * 
     * <h3>版本格式：</h3>
     * <ul>
     * <li>遵循语义化版本控制(SemVer)规范：v1.2.3 或 v1.2.3-beta.1</li>
     * <li>用于HTTP请求头中的User-Agent标识</li>
     * <li>编译时确定，运行时缓存</li>
     * </ul>
     * 
     * @return SDK版本号，如 "2.0.0" 或 "2.1.0-beta.1"
     */
    public static String getVersion() {
        if (cachedVersion == null) {
            synchronized (Variables.class) {
                if (cachedVersion == null) {
                    cachedVersion = loadVersion();
                }
            }
        }
        return cachedVersion;
    }

    /**
     * 获取调试模式状态
     * <p>
     * 对应 Go SDK: {@code vars.IsDebugging}
     * 从环境变量 {@code DEBUG} 中读取布尔值，控制SDK的调试行为
     * </p>
     * 
     * <h3>设置方式：</h3>
     * <pre>{@code
     * // 命令行设置
     * export DEBUG=true   # 开启调试模式
     * export DEBUG=false  # 关闭调试模式（默认）
     * 
     * // Java系统属性设置
     * System.setProperty("DEBUG", "true");
     * }</pre>
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
     *     logger.debug("调试信息: 请求URL={}", url);
     * }
     * }</pre>
     * 
     * <h3>安全注意事项：</h3>
     * <ul>
     * <li>生产环境建议设为false，避免敏感信息泄露</li>
     * <li>解析失败时默认为false（安全优先）</li>
     * </ul>
     * 
     * @return true 表示开启调试模式，false 表示关闭（默认）
     */
    public static boolean isDebugging() {
        if (cachedIsDebugging == null) {
            synchronized (Variables.class) {
                if (cachedIsDebugging == null) {
                    cachedIsDebugging = loadDebuggingFlag();
                }
            }
        }
        return cachedIsDebugging;
    }

    /**
     * 获取User-Agent字符串
     * <p>
     * 对应 Go SDK 中的 User-Agent 生成逻辑
     * 格式：{@code Myt-Java-SDK/2.0.0}
     * </p>
     * 
     * <h3>用途：</h3>
     * <ul>
     * <li>HTTP请求头中的User-Agent标识</li>
     * <li>服务器端SDK使用统计</li>
     * <li>API调用追踪和调试</li>
     * </ul>
     * 
     * @return User-Agent字符串，如 "Myt-Java-SDK/2.0.0"
     */
    public static String getUserAgent() {
        if (cachedUserAgent == null) {
            synchronized (Variables.class) {
                if (cachedUserAgent == null) {
                    cachedUserAgent = String.format("%s/%s", Constants.SDK.NAME, getVersion());
                }
            }
        }
        return cachedUserAgent;
    }

    /**
     * 重新加载所有缓存变量
     * <p>
     * 清除所有缓存，强制重新读取配置
     * 用于运行时动态更新配置的场景
     * </p>
     * 
     * <h3>使用场景：</h3>
     * <ul>
     * <li>单元测试中重置环境</li>
     * <li>运行时切换调试模式</li>
     * <li>配置文件更新后刷新</li>
     * </ul>
     * 
     * <h3>注意：</h3>
     * 此方法是线程安全的，但可能会导致短暂的性能影响
     */
    public static void refresh() {
        synchronized (Variables.class) {
            cachedVersion = null;
            cachedIsDebugging = null;
            cachedUserAgent = null;
        }
    }

    /**
     * 检查是否为生产环境
     * <p>
     * 基于调试模式和版本信息判断运行环境
     * 用于环境特定的配置和行为控制
     * </p>
     * 
     * @return true 表示生产环境，false 表示开发/测试环境
     */
    public static boolean isProduction() {
        String version = getVersion();
        return !isDebugging() && 
               !version.contains("SNAPSHOT") && 
               !version.contains("beta") && 
               !version.contains("alpha") &&
               !version.contains("dev");
    }

    // ==================== 私有方法 ====================

    /**
     * 从配置源加载版本信息
     * 
     * @return SDK版本号
     */
    private static String loadVersion() {
        // 1. 优先从环境变量读取
        String envVersion = getEnvironmentVariable(Constants.Environment.SDK_VERSION);
        if (envVersion != null && !envVersion.trim().isEmpty()) {
            return envVersion.trim();
        }

        // 2. 从系统属性读取
        String sysVersion = System.getProperty(Constants.Environment.SDK_VERSION);
        if (sysVersion != null && !sysVersion.trim().isEmpty()) {
            return sysVersion.trim();
        }

        // 3. 从 version.properties 文件读取
        String fileVersion = loadVersionFromProperties();
        if (fileVersion != null && !fileVersion.trim().isEmpty()) {
            return fileVersion.trim();
        }

        // 4. 使用默认版本
        return DEFAULT_VERSION;
    }

    /**
     * 从 version.properties 文件加载版本信息
     * <p>
     * 对应 Go SDK 中的 {@code //go:embed version.txt}
     * </p>
     * 
     * @return 版本号，失败时返回null
     */
    private static String loadVersionFromProperties() {
        try (InputStream inputStream = Variables.class.getClassLoader()
                .getResourceAsStream(VERSION_PROPERTIES_FILE)) {
            
            if (inputStream == null) {
                return null;
            }

            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(VERSION_KEY);
            
        } catch (IOException e) {
            // 静默处理，返回null让上层使用默认值
            return null;
        }
    }

    /**
     * 加载调试模式标志
     * <p>
     * 对应 Go SDK: {@code IsDebugging, _ = strconv.ParseBool(os.Getenv("DEBUG"))}
     * </p>
     * 
     * @return 调试模式状态
     */
    private static boolean loadDebuggingFlag() {
        // 1. 从环境变量读取
        String envDebug = getEnvironmentVariable(Constants.Environment.DEBUG);
        if (envDebug != null) {
            return parseBoolean(envDebug);
        }

        // 2. 从系统属性读取
        String sysDebug = System.getProperty(Constants.Environment.DEBUG);
        if (sysDebug != null) {
            return parseBoolean(sysDebug);
        }

        // 3. 默认关闭调试模式（安全优先）
        return false;
    }

    /**
     * 安全地解析布尔值
     * <p>
     * 对应 Go SDK 中的 {@code strconv.ParseBool} 函数
     * 解析失败时返回false（安全优先）
     * </p>
     * 
     * @param value 待解析的字符串
     * @return 布尔值，解析失败时返回false
     */
    private static boolean parseBoolean(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        
        String lowerValue = value.trim().toLowerCase();
        return "true".equals(lowerValue) || 
               "1".equals(lowerValue) || 
               "yes".equals(lowerValue) || 
               "on".equals(lowerValue);
    }

    /**
     * 安全地获取环境变量
     * 
     * @param name 环境变量名
     * @return 环境变量值，获取失败时返回null
     */
    private static String getEnvironmentVariable(String name) {
        try {
            return System.getenv(name);
        } catch (SecurityException e) {
            // 在某些受限环境中可能无法访问环境变量
            return null;
        }
    }
}