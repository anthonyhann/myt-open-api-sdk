<?php

/**
 * PHPUnit 测试引导文件
 * 设置自动加载和测试环境
 */

// 错误报告设置
error_reporting(E_ALL);
ini_set('display_errors', 1);

// 设置时区
date_default_timezone_set('Asia/Shanghai');

// 定义项目根目录
define('SDK_ROOT', dirname(__DIR__));

// 简单的自动加载器 (在没有Composer的情况下使用)
spl_autoload_register(function ($className) {
    // 移除命名空间前缀
    $prefix = 'Maiyatian\\Channel\\PhpSdk\\';
    if (strpos($className, $prefix) !== 0) {
        return;
    }
    
    $relativeClass = substr($className, strlen($prefix));
    
    // 特殊处理：Tests 目录
    if (strpos($relativeClass, 'Tests\\') === 0) {
        $testClass = substr($relativeClass, 6); // 移除 'Tests\'
        $file = SDK_ROOT . '/tests/' . $testClass . '.php';
        if (file_exists($file)) {
            require_once $file;
            return;
        }
    }
    
    // 替换命名空间分隔符为目录分隔符
    $relativeClass = str_replace('\\', '/', $relativeClass);
    
    // 尝试不同的路径组合
    $possiblePaths = [
        SDK_ROOT . '/' . $relativeClass . '.php',
        SDK_ROOT . '/' . strtolower(dirname($relativeClass)) . '/' . basename($relativeClass) . '.php',
        SDK_ROOT . '/' . dirname($relativeClass) . '/' . basename($relativeClass) . '.php'
    ];
    
    // 特殊映射：处理文件名与类名不匹配的情况
    $specialMappings = [
        'Client/HTTPClientConfig' => 'client/config.php',
        'Client/ConfigBuilder' => 'client/config.php',
        'Client/ConfigValidationError' => 'client/config.php',
        'Client/HTTPClientManager' => 'client/http_client.php',
        'Client/HTTPRequest' => 'client/http_client.php',
        'Client/HTTPResponse' => 'client/http_client.php'
    ];
    
    if (isset($specialMappings[$relativeClass])) {
        $file = SDK_ROOT . '/' . $specialMappings[$relativeClass];
        if (file_exists($file)) {
            require_once $file;
            return;
        }
    }
    
    foreach ($possiblePaths as $file) {
        if (file_exists($file)) {
            require_once $file;
            return;
        }
    }
});

// 测试环境配置
putenv('APP_ENV=testing');
putenv('DEBUG=false');

echo "PHP SDK 测试环境初始化完成\n";
echo "PHP 版本: " . PHP_VERSION . "\n";
echo "测试目录: " . SDK_ROOT . "\n";
echo "当前时间: " . date('Y-m-d H:i:s') . "\n";
echo str_repeat('=', 50) . "\n\n";