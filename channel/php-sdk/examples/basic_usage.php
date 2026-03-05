<?php

/**
 * 基础使用示例
 * 演示 Channel PHP SDK 的基本功能和使用方法
 * 对应 Go SDK 的基础功能示例
 */

require_once __DIR__ . '/../vendor/autoload.php'; // 假设使用 Composer 自动加载

use Maiyatian\Channel\PhpSdk\ChannelSDK;
use Maiyatian\Channel\PhpSdk\Client\ApiResponse;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

echo "=== 麦芽田 Channel PHP SDK 基础使用示例 ===\n";
echo "SDK 版本: " . Variables::getVersion() . "\n";
echo "调试模式: " . (Variables::isDebugging() ? '开启' : '关闭') . "\n\n";

// ==================== 1. 创建 SDK 实例 ====================

try {
    // 方法一：使用默认配置（推荐）
    $sdk = ChannelSDK::newDefault(
        'your-app-key',      // 应用密钥
        'your-app-secret',   // 应用密钥
        ''                   // 留空使用正式环境，或指定自定义 URL
    );
    
    echo "✅ SDK 实例创建成功（正式环境）\n";
    
    // 方法二：使用测试环境
    $testSdk = ChannelSDK::newForTest(
        'test-app-key',      // 测试环境应用密钥
        'test-app-secret'    // 测试环境应用密钥
    );
    
    echo "✅ 测试环境 SDK 实例创建成功\n";
    
    // 方法三：从环境变量创建（推荐生产环境使用）
    // 设置环境变量：
    // export MAIYATIAN_APP_KEY="your-app-key"
    // export MAIYATIAN_APP_SECRET="your-app-secret"
    // export MAIYATIAN_API_BASE_URL="https://custom-api.example.com"  # 可选
    
    try {
        $envSdk = ChannelSDK::newFromEnv();
        echo "✅ 从环境变量创建 SDK 实例成功\n";
    } catch (Exception $e) {
        echo "⚠️ 从环境变量创建失败（可能未设置）: " . $e->getMessage() . "\n";
    }
    
    // 方法四：使用构建器模式（高级配置）
    $builderSdk = ChannelSDK::newBuilder()
        ->APIKey('your-app-key')
        ->APISecret('your-app-secret')
        ->BaseURL('https://open-api-test.maiyatian.com')
        ->RequestTimeout(30)
        ->RetryMaxAttempts(2)
        ->EnableLogging(true)
        ->build();
    
    echo "✅ 使用构建器模式创建 SDK 实例成功\n\n";

} catch (Exception $e) {
    echo "❌ SDK 初始化失败: " . $e->getMessage() . "\n";
    exit(1);
}

// ==================== 2. 获取授权令牌 ====================

echo "=== 获取授权令牌 ===\n";

try {
    // 门店级授权
    $storeAuthData = [
        'grant_type' => '1',  // 门店级授权
        'code' => 'auth-code-from-authorization-flow',
    ];
    
    $tokenResponse = $sdk->getAccessToken($storeAuthData);
    
    if ($tokenResponse->isSuccess()) {
        $tokenData = $tokenResponse->getData();
        echo "✅ 获取访问令牌成功\n";
        echo "访问令牌: " . ($tokenData['access_token'] ?? 'N/A') . "\n";
        echo "刷新令牌: " . ($tokenData['refresh_token'] ?? 'N/A') . "\n";
        echo "过期时间: " . ($tokenData['expires_in'] ?? 'N/A') . " 秒\n";
        
        // 保存令牌供后续使用
        $accessToken = $tokenData['access_token'] ?? '';
        $refreshToken = $tokenData['refresh_token'] ?? '';
        
    } else {
        echo "❌ 获取访问令牌失败: [" . $tokenResponse->getCode() . "] " . $tokenResponse->getMessage() . "\n";
        $accessToken = 'demo-access-token'; // 使用演示令牌继续示例
    }
    
} catch (Exception $e) {
    echo "❌ 授权请求异常: " . $e->getMessage() . "\n";
    $accessToken = 'demo-access-token'; // 使用演示令牌继续示例
}

echo "\n";

// ==================== 3. 获取门店信息 ====================

echo "=== 获取门店信息 ===\n";

try {
    $shopResponse = $sdk->getShopInfo($accessToken);
    
    if ($shopResponse->isSuccess()) {
        $shopData = $shopResponse->getData();
        echo "✅ 获取门店信息成功\n";
        echo "门店名称: " . ($shopData['shop_name'] ?? 'N/A') . "\n";
        echo "门店ID: " . ($shopData['shop_id'] ?? 'N/A') . "\n";
        echo "门店状态: " . ($shopData['status'] ?? 'N/A') . "\n";
    } else {
        echo "❌ 获取门店信息失败: [" . $shopResponse->getCode() . "] " . $shopResponse->getMessage() . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 门店信息请求异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 4. 订单相关操作 ====================

echo "=== 订单相关操作 ===\n";

// 订单创建通知
try {
    $orderData = [
        'order_id' => 'ORDER_' . time(),
        'shop_id' => '123456',
        'order_amount' => 5880, // 58.80 元，单位：分
        'order_status' => 1,
        'create_time' => time(),
        'items' => [
            [
                'item_id' => 'ITEM_001',
                'item_name' => '招牌麻辣烫',
                'quantity' => 2,
                'unit_price' => 2940, // 29.40 元
            ]
        ],
        'customer' => [
            'name' => '张三',
            'phone' => '13800138000',
            'address' => '北京市朝阳区xxx路xxx号'
        ]
    ];
    
    $createResponse = $sdk->orderCreated($orderData, $accessToken);
    
    if ($createResponse->isSuccess()) {
        echo "✅ 订单创建通知发送成功\n";
        $responseData = $createResponse->getData();
        echo "响应数据: " . json_encode($responseData, JSON_UNESCAPED_UNICODE) . "\n";
    } else {
        echo "❌ 订单创建通知失败: [" . $createResponse->getCode() . "] " . $createResponse->getMessage() . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 订单创建请求异常: " . $e->getMessage() . "\n";
}

// 订单确认通知
try {
    $confirmData = [
        'order_id' => $orderData['order_id'],
        'estimated_delivery_time' => time() + 1800, // 30分钟后送达
        'merchant_message' => '您的订单已确认，预计30分钟内送达'
    ];
    
    $confirmResponse = $sdk->orderConfirmed($confirmData, $accessToken);
    
    if ($confirmResponse->isSuccess()) {
        echo "✅ 订单确认通知发送成功\n";
    } else {
        echo "❌ 订单确认通知失败: [" . $confirmResponse->getCode() . "] " . $confirmResponse->getMessage() . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 订单确认请求异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 5. 高级功能演示 ====================

echo "=== 高级功能演示 ===\n";

// 5.1 带重试的 API 调用
echo "--- 带重试机制的请求 ---\n";
try {
    $retryConfig = [
        'maxAttempts' => 3,
        'baseDelay' => 1,
        'maxDelay' => 10,
        'retryOnCodes' => [429, 500, 502, 503, 504]
    ];
    
    $retryResponse = $sdk->requestWithRetry(
        'GET',
        'v1/channel/shop_info',
        [],
        $accessToken,
        null,
        $retryConfig
    );
    
    if ($retryResponse->isSuccess()) {
        echo "✅ 重试请求成功\n";
    } else {
        echo "❌ 重试请求失败: [" . $retryResponse->getCode() . "] " . $retryResponse->getMessage() . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 重试请求异常: " . $e->getMessage() . "\n";
}

// 5.2 批量请求
echo "--- 批量请求演示 ---\n";
try {
    $batchRequests = [
        [
            'method' => 'GET',
            'path' => 'v1/channel/shop_info',
            'data' => [],
            'token' => $accessToken
        ],
        [
            'method' => 'POST',
            'path' => 'v1/channel/order_remind',
            'data' => [
                'order_id' => $orderData['order_id'],
                'remind_message' => '您的订单正在制作中，请耐心等待'
            ],
            'token' => $accessToken
        ]
    ];
    
    $batchResponses = $sdk->batchRequest($batchRequests);
    
    foreach ($batchResponses as $index => $response) {
        if ($response->isSuccess()) {
            echo "✅ 批量请求 [$index] 成功\n";
        } else {
            echo "❌ 批量请求 [$index] 失败: [" . $response->getCode() . "] " . $response->getMessage() . "\n";
        }
    }
    
} catch (Exception $e) {
    echo "❌ 批量请求异常: " . $e->getMessage() . "\n";
}

// 5.3 类型安全的数据提取
echo "--- 类型安全数据提取演示 ---\n";
try {
    $response = $sdk->getShopInfo($accessToken);
    
    // 使用 ApiResponse 的函数式方法
    $mappedResponse = $response->map(function($data) {
        // 数据转换示例：添加计算字段
        if (is_array($data)) {
            $data['display_name'] = ($data['shop_name'] ?? '') . ' (' . ($data['shop_id'] ?? '') . ')';
        }
        return $data;
    });
    
    if ($mappedResponse->isSuccess()) {
        echo "✅ 数据转换成功\n";
        $transformedData = $mappedResponse->getData();
        echo "转换后的门店显示名: " . ($transformedData['display_name'] ?? 'N/A') . "\n";
    }
    
    // 安全获取数据
    $shopName = $response->getDataOrDefault([])['shop_name'] ?? '未知门店';
    echo "门店名称（带默认值）: $shopName\n";
    
} catch (Exception $e) {
    echo "❌ 数据处理异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 6. 资源清理 ====================

echo "=== 资源清理 ===\n";

// 手动关闭 SDK 连接（可选，析构函数会自动处理）
$sdk->close();
echo "✅ SDK 资源已清理\n";

echo "\n=== 示例完成 ===\n";

/**
 * 运行此示例的方法：
 * 
 * 1. 设置环境变量（推荐）：
 *    export MAIYATIAN_APP_KEY="your-actual-app-key"
 *    export MAIYATIAN_APP_SECRET="your-actual-app-secret"
 *    export DEBUG="true"  # 开启调试模式
 * 
 * 2. 运行示例：
 *    php examples/basic_usage.php
 * 
 * 3. 观察输出结果和日志信息
 */