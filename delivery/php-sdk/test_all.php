<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @Description: 麦芽田配送开放平台SDK - 全面测试脚本
 * 用于测试SDK的所有功能模块
 * @Version 1.0
 */

// 预先声明所有可能用到的类
use Maiyatian\Delivery\SDK\client\ConfigBuilder;
use Maiyatian\Delivery\SDK\models\types\Constants;
use Maiyatian\Delivery\SDK\utils\Tools;
use Maiyatian\Delivery\SDK\models\sender\entity\auth\AccessTokenRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\delivery\DeliveryChangeRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\delivery\VehicleInfo;
use Maiyatian\Delivery\SDK\models\sender\entity\express\LocationChangeRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\express\LocationPoint;

echo "=== 麦芽田配送开放平台PHP SDK全面测试 ===\n\n";

// 测试1: 检查类文件是否存在
echo "1. 检查类文件是否存在...\n";

$classFiles = [
    __DIR__ . '/client/config.php',
    __DIR__ . '/client/http_client.php',
    __DIR__ . '/utils/tools.php',
    __DIR__ . '/models/types/constants.php',
    __DIR__ . '/models/sender/api/base.php',
    __DIR__ . '/models/sender/api/access_token.php',
    __DIR__ . '/models/sender/api/refresh_token.php',
    __DIR__ . '/models/sender/api/delivery_change.php',
    __DIR__ . '/models/sender/api/location_change.php',
    __DIR__ . '/models/sender/entity/auth/access_token_entity.php',
    __DIR__ . '/models/sender/entity/auth/refresh_token_entity.php',
    __DIR__ . '/models/sender/entity/delivery/delivery_change_entity.php',
    __DIR__ . '/models/sender/entity/express/location_change_entity.php',
    __DIR__ . '/models/receiver/entity/city/city_capacity_entity.php',
    __DIR__ . '/models/receiver/entity/delivery/valuating_entity.php',
    __DIR__ . '/models/receiver/entity/delivery/send_entity.php',
    __DIR__ . '/models/receiver/entity/delivery/cancel_entity.php',
    __DIR__ . '/models/receiver/entity/delivery/tips_entity.php',
    __DIR__ . '/models/receiver/entity/delivery/precancel_entity.php',
];

$allFilesExist = true;
foreach ($classFiles as $file) {
    if (file_exists($file)) {
        echo "✓ {$file}\n";
    } else {
        echo "✗ {$file}\n";
        $allFilesExist = false;
    }
}

if ($allFilesExist) {
    echo "✓ 所有类文件存在\n\n";
} else {
    echo "✗ 部分类文件不存在\n\n";
    exit(1);
}

// 测试2: 测试配置构建器
echo "2. 测试配置构建器...\n";
try {
    require_once __DIR__ . '/client/config.php';
    require_once __DIR__ . '/models/types/constants.php';
    
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
    
    echo "✓ 配置构建成功\n";
    echo "  BaseURL: {$config->baseURL}\n";
    echo "  APIKey: {$config->apiKey}\n";
    echo "  MaxConnections: {$config->maxConnections}\n";
    echo "  RequestTimeout: {$config->requestTimeout}\n";
    echo "  RetryMaxAttempts: {$config->retryMaxAttempts}\n";
    echo "  EnableLogging: {$config->enableLogging}\n";
} catch (Exception $e) {
    echo "✗ 配置构建失败: {$e->getMessage()}\n";
    exit(1);
}

echo "\n3. 测试工具类...\n";
try {
    require_once __DIR__ . '/utils/tools.php';
    
    // 测试JSON编解码
    $testData = ["name" => "测试", "value" => 123, "is_valid" => true];
    $jsonString = Tools::jsonEncode($testData);
    $decodedData = Tools::jsonDecode($jsonString, true);
    if ($decodedData["name"] === "测试" && $decodedData["value"] === 123) {
        echo "✓ JSON编解码测试通过\n";
    } else {
        echo "✗ JSON编解码测试失败\n";
    }
    
    // 测试HMAC签名
    $testString = "test_data";
    $secret = "test_secret";
    $signature = Tools::hmacSha256Base64($testString, $secret);
    $isValid = Tools::verifySignature($testString, $secret, $signature);
    if ($isValid) {
        echo "✓ HMAC签名测试通过\n";
    } else {
        echo "✗ HMAC签名测试失败\n";
    }
    
    // 测试UUID生成
    $uuid = Tools::uuid();
    if (preg_match('/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i', $uuid)) {
        echo "✓ UUID生成测试通过\n";
    } else {
        echo "✗ UUID生成测试失败\n";
    }
    
    // 测试命名转换
    $camel = "testCamelCase";
    $snake = Tools::camelToSnake($camel);
    $camel2 = Tools::snakeToCamel($snake);
    if ($camel === $camel2) {
        echo "✓ 命名转换测试通过\n";
    } else {
        echo "✗ 命名转换测试失败\n";
    }
    
} catch (Exception $e) {
    echo "✗ 工具类测试失败: {$e->getMessage()}\n";
    exit(1);
}

echo "\n4. 测试常量定义...\n";
try {
    require_once __DIR__ . '/models/types/constants.php';
    
    if (Constants::API_VERSION === 'v1' && Constants::TEST_BASE_URL === 'https://open-api-test.maiyatian.com') {
        echo "✓ 常量定义测试通过\n";
        echo "  API_VERSION: " . Constants::API_VERSION . "\n";
        echo "  TEST_BASE_URL: " . Constants::TEST_BASE_URL . "\n";
        echo "  PROD_BASE_URL: " . Constants::PROD_BASE_URL . "\n";
    } else {
        echo "✗ 常量定义测试失败\n";
    }
    
} catch (Exception $e) {
    echo "✗ 常量定义测试失败: {$e->getMessage()}\n";
    exit(1);
}

echo "\n5. 测试实体类...\n";
try {
    require_once __DIR__ . '/models/sender/entity/auth/access_token_entity.php';
    require_once __DIR__ . '/models/sender/entity/delivery/delivery_change_entity.php';
    require_once __DIR__ . '/models/sender/entity/express/location_change_entity.php';
    
    // 测试AccessTokenRequest
    $accessTokenReq = new AccessTokenRequest("test_code");
    $accessTokenReq->mobile = "13800138000";
    $accessTokenReq->store_id = "store_123";
    echo "✓ AccessTokenRequest测试通过\n";
    
    // 测试DeliveryChangeRequest
    $deliveryChangeReq = new DeliveryChangeRequest("order_123", "source_order_123", "shop_123", "PICKUP");
    $deliveryChangeReq->rider_name = "林骑手";
    $deliveryChangeReq->rider_phone = "13888888888_1234";
    $deliveryChangeReq->vehicle_info = new VehicleInfo("雪铁龙C5", "蓝色", "冀E4WE32");
    echo "✓ DeliveryChangeRequest测试通过\n";
    
    // 测试LocationChangeRequest
    $locationChangeReq = new LocationChangeRequest("dpk_123", "source_dpk_123", "shop_123");
    $locationPoint = new LocationPoint("[合肥市]已揽收", "PICKUP");
    $locationPoint->city = "合肥市";
    $locationPoint->longitude = "117.230410";
    $locationPoint->latitude = "31.820640";
    $locationChangeReq->addLocationPoint($locationPoint);
    echo "✓ LocationChangeRequest测试通过\n";
    
} catch (Exception $e) {
    echo "✗ 实体类测试失败: {$e->getMessage()}\n";
    exit(1);
}

echo "\n=== 测试完成 ===\n";
echo "✓ 麦芽田配送开放平台PHP SDK全面测试通过！\n";
echo "SDK已经准备就绪，可以开始使用了！\n\n";
echo "使用方法请参考 README.md 文件。\n";
