<?php

namespace Maiyatian\Delivery\PhpSdk\Vars;

/**
 * 麦芽田配送开放平台 PHP SDK 全局变量管理
 * 对应 Go SDK 的 vars 包，包含版本信息、调试开关等运行时变量
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class Variables
{
    /**
     * SDK版本号
     * 对应 Go SDK 中的 VERSION 变量
     * @var string
     */
    private static $version = '1.0.0';
    
    /**
     * 调试模式开关
     * 对应 Go SDK 中的 IsDebugging 变量
     * 从环境变量 DEBUG 中读取布尔值，控制SDK的调试行为
     * @var bool
     */
    private static $isDebugging = null;
    
    /**
     * 获取SDK版本号
     * 对应 Go SDK 中的 vars.VERSION
     * 
     * 使用示例：
     *   echo Variables::getVersion(); // 输出: 1.0.0
     * 
     * @return string SDK版本号
     */
    public static function getVersion(): string
    {
        // 尝试从composer.json或其他配置文件读取版本
        if (self::$version === '1.0.0') {
            $composerFile = __DIR__ . '/../../composer.json';
            if (file_exists($composerFile)) {
                $composer = json_decode(file_get_contents($composerFile), true);
                if (isset($composer['version'])) {
                    self::$version = $composer['version'];
                }
            }
        }
        
        return self::$version;
    }
    
    /**
     * 设置SDK版本号（内部使用）
     * 
     * @param string $version 版本号
     * @internal
     */
    public static function setVersion(string $version): void
    {
        self::$version = $version;
    }
    
    /**
     * 检查是否处于调试模式
     * 对应 Go SDK 中的 vars.IsDebugging
     * 
     * 设置方式：
     *   export DEBUG=true   # 开启调试模式
     *   export DEBUG=false  # 关闭调试模式（默认）
     *   
     * 调试模式下的行为：
     *   - 输出更详细的HTTP请求日志
     *   - 显示签名生成过程
     *   - 输出错误堆栈信息
     *   - 记录重试详情
     *
     * 使用示例：
     *   if (Variables::isDebugging()) {
     *       error_log("调试信息: 请求URL={$url}");
     *   }
     *
     * 注意：
     *   - 生产环境建议设为false，避免敏感信息泄露
     *   - 解析失败时默认为false（安全优先）
     * 
     * @return bool 是否处于调试模式
     */
    public static function isDebugging(): bool
    {
        if (self::$isDebugging === null) {
            $debugEnv = getenv('DEBUG');
            if ($debugEnv === false) {
                // 环境变量不存在，默认关闭调试
                self::$isDebugging = false;
            } else {
                // 解析环境变量值
                $debugEnv = strtolower($debugEnv);
                self::$isDebugging = in_array($debugEnv, ['1', 'true', 'yes', 'on'], true);
            }
        }
        
        return self::$isDebugging;
    }
    
    /**
     * 设置调试模式（用于测试）
     * 
     * @param bool $debugging 是否开启调试
     * @internal
     */
    public static function setDebugging(bool $debugging): void
    {
        self::$isDebugging = $debugging;
    }
    
    /**
     * 获取用户代理字符串
     * 对应 Go SDK 中的 User-Agent 生成逻辑
     * 
     * @return string 用户代理字符串
     */
    public static function getUserAgent(): string
    {
        return sprintf('Maiyatian-Delivery-PHP-SDK/%s', self::getVersion());
    }
    
    /**
     * 获取SDK类型标识
     * 用于区分不同的SDK（Channel、Delivery等）
     * 
     * @return string SDK类型
     */
    public static function getSdkType(): string
    {
        return 'delivery';
    }
    
    /**
     * 获取支持的API版本列表
     * 
     * @return array 支持的API版本
     */
    public static function getSupportedApiVersions(): array
    {
        return ['v1'];
    }
    
    /**
     * 检查指定的API版本是否支持
     * 
     * @param string $version API版本
     * @return bool 是否支持
     */
    public static function isApiVersionSupported(string $version): bool
    {
        return in_array($version, self::getSupportedApiVersions(), true);
    }
    
    /**
     * 获取默认API版本
     * 
     * @return string 默认API版本
     */
    public static function getDefaultApiVersion(): string
    {
        return 'v1';
    }
    
    /**
     * 重置所有缓存的变量（用于测试）
     * 
     * @internal
     */
    public static function reset(): void
    {
        self::$version = '1.0.0';
        self::$isDebugging = null;
    }
}