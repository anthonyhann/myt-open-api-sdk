<?php

require __DIR__ . '/vendor/autoload.php';

use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;

// 测试配置构建器
$config = ConfigBuilder::NewConfigBuilder()
    ->BaseURL('https://open-api-test.maiyatian.com')
    ->APIKey('test_app_key')
    ->APISecret('test_app_secret')
    ->RequestTimeout(30)
    ->EnableLogging(false)
    ->Build();

echo "配置构建成功！\n";
echo "API 基础地址: {$config->BaseURL}\n";
echo "API 密钥: {$config->APIKey}\n";
echo "请求超时: {$config->RequestTimeout} 秒\n";
echo "是否启用日志: {$config->EnableLogging ? '是' : '否'}\n";

echo "\n测试完成！\n";
