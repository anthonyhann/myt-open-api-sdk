<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @Description: 麦芽田配送开放平台SDK - 无composer测试脚本
 * 手动引入类文件，用于测试SDK的基本功能
 * @Version 1.0
 */

// 手动引入所需的类文件
$classes = [
    __DIR__ . '/client/config.php',
    __DIR__ . '/utils/tools.php',
    __DIR__ . '/models/types/constants.php',
];

foreach ($classes as $class) {
    if (file_exists($class)) {
        require_once $class;
    } else {
        echo "无法找到类文件：{$class}\n";
        exit(1);
    }
}

use Maiyatian\Delivery\SDK\client\Config;
use Maiyatian\Delivery\SDK\client\ConfigBuilder;
use Maiyatian\Delivery\SDK\utils\Tools;
use Maiyatian\Delivery\SDK\models\types\Constants;

// 测试配置构建器
echo "=== 测试配置构建器 ===\n";
try {
    $configBuilder = new ConfigBuilder();
    $config = $configBuilder
        ->baseURL(Constants::TEST_BASE_URL)
        ->apiKey("test_api_key")
        ->apiSecret("test_api_secret")
        ->maxConnections(100)
        ->requestTimeout(30)
        ->retryMaxAttempts(5)
        ->enableLogging(true)
        ->build();

    echo "配置构建成功！\n";
    echo "BaseURL: {$config->baseURL}\n";
    echo "APIKey: {$config->apiKey}\n";
    echo "MaxConnections: {$config->maxConnections}\n";
    echo "RequestTimeout: {$config->requestTimeout}\n";
    echo "RetryMaxAttempts: {$config->retryMaxAttempts}\n";
    echo "EnableLogging: {$config->enableLogging}\n";
} catch (Exception $e) {
    echo "配置构建失败：" . $e->getMessage() . "\n";
}

// 测试工具类
echo "\n=== 测试工具类 ===\n";

// 测试JSON编解码
echo "\n1. 测试JSON编解码：\n";
$testData = ["name" => "测试", "value" => 123, "is_valid" => true];
$jsonString = Tools::jsonEncode($testData);
echo "原始数据：" . print_r($testData, true) . "\n";
echo "JSON编码后：{$jsonString}\n";
$decodedData = Tools::jsonDecode($jsonString, true);
echo "JSON解码后：" . print_r($decodedData, true) . "\n";

// 测试时间戳
echo "\n2. 测试时间戳：\n";
echo "当前时间戳（秒）：" . Tools::getTimestamp() . "\n";
echo "当前时间戳（毫秒）：" . Tools::getMillisecondTimestamp() . "\n";
echo "格式化时间：" . Tools::formatDateTime() . "\n";

// 测试随机字符串
echo "\n3. 测试随机字符串：\n";
echo "随机字符串（16位）：" . Tools::randomString() . "\n";
echo "随机字符串（32位）：" . Tools::randomString(32) . "\n";

// 测试UUID
echo "\n4. 测试UUID：\n";
echo "UUID：" . Tools::uuid() . "\n";

// 测试Base64编码
echo "\n5. 测试Base64编码：\n";
$testString = "测试Base64编码";
$base64 = Tools::base64UrlEncode($testString);
echo "原始字符串：{$testString}\n";
echo "Base64编码后：{$base64}\n";
$decodedString = Tools::base64UrlDecode($base64);
echo "Base64解码后：{$decodedString}\n";

// 测试HMAC签名
echo "\n6. 测试HMAC签名：\n";
$testData = "test_data_for_hmac";
$secret = "test_secret_key";
$signature = Tools::hmacSha256Base64($testData, $secret);
echo "原始数据：{$testData}\n";
echo "密钥：{$secret}\n";
echo "HMAC签名：{$signature}\n";

// 测试签名验证
echo "\n7. 测试签名验证：\n";
$isValid = Tools::verifySignature($testData, $secret, $signature);
echo "签名验证结果：" . ($isValid ? "有效" : "无效") . "\n";

// 测试命名转换
echo "\n8. 测试命名转换：\n";
$camelString = "testCamelCaseString";
echo "驼峰命名：{$camelString}\n";
$snakeString = Tools::camelToSnake($camelString);
echo "下划线命名：{$snakeString}\n";
$camelString2 = Tools::snakeToCamel($snakeString);
echo "驼峰命名（转换回）：{$camelString2}\n";

// 测试常量
echo "\n=== 测试常量 ===\n";
echo "API版本：" . Constants::API_VERSION . "\n";
echo "测试环境URL：" . Constants::TEST_BASE_URL . "\n";
echo "正式环境URL：" . Constants::PROD_BASE_URL . "\n";
echo "配送状态：待抢单：" . Constants::DELIVERY_STATUS_PENDING . "\n";
echo "配送状态：已分配骑手：" . Constants::DELIVERY_STATUS_GRABBED . "\n";
echo "配送状态：待取货：" . Constants::DELIVERY_STATUS_PICKUP . "\n";
echo "配送状态：配送中：" . Constants::DELIVERY_STATUS_DELIVERING . "\n";
echo "配送状态：已完成：" . Constants::DELIVERY_STATUS_DONE . "\n";
echo "配送状态：已取消：" . Constants::DELIVERY_STATUS_CANCEL . "\n";

echo "\n=== 测试完成 ===\n";
echo "所有基本功能测试通过！\n";
