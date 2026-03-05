<?php

// 手动加载类文件
function autoload($class)
{
    // 直接加载配置文件，因为它是我们需要的第一个类
    $configFilePath = __DIR__ . '/client/config.php';
    if (file_exists($configFilePath)) {
        require_once $configFilePath;
    } else {
        echo "配置文件不存在: {$configFilePath}\n";
    }
}

spl_autoload_register('autoload');

use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;

// 测试配置构建器
try {
    $config = ConfigBuilder::NewConfigBuilder()
        ->BaseURL('https://open-api-test.maiyatian.com')
        ->APIKey('test_app_key')
        ->APISecret('test_app_secret')
        ->RequestTimeout(30)
        ->EnableLogging(false)
        ->Build();

    echo "配置构建成功！\n";
    echo "API 基础地址: " . $config->BaseURL . "\n";
    echo "API 密钥: " . $config->APIKey . "\n";
    echo "请求超时: " . $config->RequestTimeout . " 秒\n";
    echo "是否启用日志: " . ($config->EnableLogging ? '是' : '否') . "\n";
    echo "SDK 版本: " . $config->SDKVersion . "\n";
    echo "\n测试通过！\n";
} catch (Exception $e) {
    echo "测试失败: {$e->getMessage()}\n";
    echo "堆栈跟踪: {$e->getTraceAsString()}\n";
}
