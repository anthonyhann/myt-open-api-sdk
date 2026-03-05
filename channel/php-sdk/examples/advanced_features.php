<?php

/**
 * 高级功能示例
 * 演示 Channel PHP SDK 的高级功能和最佳实践
 * 对应 Go SDK 的高级泛型和并发功能
 */

require_once __DIR__ . '/../vendor/autoload.php';

use Maiyatian\Channel\PhpSdk\ChannelSDK;
use Maiyatian\Channel\PhpSdk\Client\ApiResponse;
use Maiyatian\Channel\PhpSdk\Client\ApiClientUtils;
use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

echo "=== 麦芽田 Channel PHP SDK 高级功能示例 ===\n";

// ==================== 1. 高级配置和连接池管理 ====================

echo "=== 企业级客户端配置 ===\n";

try {
    // 使用构建器模式创建企业级配置
    $config = ConfigBuilder::NewConfigBuilder()
        ->APIKey('enterprise-app-key')
        ->APISecret('enterprise-app-secret')
        ->BaseURL('https://open-api.maiyatian.com')
        ->MaxConnections(100)                    // 最大连接数
        ->RequestTimeout(45)                     // 请求超时 45 秒
        ->RetryMaxAttempts(5)                    // 最多重试 5 次
        ->EnableLogging(true)                    // 启用详细日志
        ->build();
    
    $sdk = new ChannelSDK($config);
    
    echo "✅ 企业级 SDK 配置创建成功\n";
    echo "最大连接数: " . $config->MaxConnections . "\n";
    echo "请求超时: " . $config->RequestTimeout . " 秒\n";
    echo "重试次数: " . $config->RetryMaxAttempts . "\n\n";
    
} catch (Exception $e) {
    echo "❌ 企业级配置失败: " . $e->getMessage() . "\n";
    exit(1);
}

// ==================== 2. 类型安全的 API 调用（模拟 Go 泛型） ====================

echo "=== 类型安全的 API 调用 ===\n";

// 定义自定义响应数据类
class ShopInfoData {
    public $shop_id;
    public $shop_name;
    public $status;
    public $address;
    public $phone;
    
    public function getDisplayName(): string {
        return "{$this->shop_name} (ID: {$this->shop_id})";
    }
    
    public function isActive(): bool {
        return $this->status === 'active' || $this->status === 1;
    }
}

class OrderData {
    public $order_id;
    public $shop_id;
    public $order_amount;
    public $order_status;
    public $create_time;
    public $items = [];
    public $customer;
    
    public function getTotalAmount(): float {
        return $this->order_amount / 100; // 转换为元
    }
    
    public function getItemCount(): int {
        return array_sum(array_column($this->items, 'quantity'));
    }
}

try {
    $accessToken = 'demo-access-token-for-advanced-features';
    
    // 类型安全的门店信息获取
    echo "--- 类型安全的门店信息获取 ---\n";
    
    $shopResponse = ApiClientUtils::requestWithApiClient(
        $sdk->getClient(),
        'GET',
        'v1/channel/shop_info',
        [],
        $accessToken,
        ShopInfoData::class  // 指定响应数据类型
    );
    
    if ($shopResponse->isSuccess()) {
        /** @var ShopInfoData $shopData */
        $shopData = $shopResponse->getData();
        
        if ($shopData instanceof ShopInfoData) {
            echo "✅ 类型安全获取门店信息成功\n";
            echo "门店显示名: " . $shopData->getDisplayName() . "\n";
            echo "门店状态: " . ($shopData->isActive() ? '营业中' : '休息中') . "\n";
        } else {
            echo "⚠️ 响应数据类型转换失败，使用原始数组\n";
            echo "门店信息: " . json_encode($shopData, JSON_UNESCAPED_UNICODE) . "\n";
        }
    } else {
        echo "❌ 获取门店信息失败: [" . $shopResponse->getCode() . "] " . $shopResponse->getMessage() . "\n";
    }
    
    // 使用自定义转换函数
    echo "--- 使用自定义数据转换 ---\n";
    
    $customResponse = ApiClientUtils::requestWithApiClient(
        $sdk->getClient(),
        'GET',
        'v1/channel/shop_info',
        [],
        $accessToken,
        function($data) {
            // 自定义数据转换逻辑
            if (is_array($data)) {
                return [
                    'id' => $data['shop_id'] ?? '',
                    'name' => $data['shop_name'] ?? '',
                    'is_open' => ($data['status'] ?? 0) == 1,
                    'formatted_address' => ($data['address'] ?? '') . ' - ' . ($data['phone'] ?? ''),
                    'metadata' => [
                        'processed_at' => date('Y-m-d H:i:s'),
                        'sdk_version' => Variables::getVersion()
                    ]
                ];
            }
            return $data;
        }
    );
    
    if ($customResponse->isSuccess()) {
        echo "✅ 自定义数据转换成功\n";
        $transformedData = $customResponse->getData();
        echo "转换后数据: " . json_encode($transformedData, JSON_UNESCAPED_UNICODE) . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 类型安全调用异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 3. 函数式编程和响应处理链 ====================

echo "=== 函数式编程和响应处理链 ===\n";

try {
    // 获取门店信息并进行链式处理
    $processedResponse = $sdk->getShopInfo($accessToken)
        ->map(function($data) {
            // 数据增强
            if (is_array($data)) {
                $data['enhanced_name'] = strtoupper($data['shop_name'] ?? '');
                $data['processing_time'] = microtime(true);
            }
            return $data;
        })
        ->filter(function($response) {
            // 数据验证：检查必要字段
            $data = $response->getData();
            return is_array($data) && 
                   isset($data['shop_id']) && 
                   !empty($data['shop_id']);
        })
        ->map(function($data) {
            // 最终格式化
            if (is_array($data)) {
                return [
                    'shop' => [
                        'id' => $data['shop_id'],
                        'name' => $data['enhanced_name'] ?? $data['shop_name'] ?? '',
                        'status' => $data['status'] ?? 'unknown'
                    ],
                    'meta' => [
                        'processed_at' => $data['processing_time'] ?? 0,
                        'chain_completed' => true
                    ]
                ];
            }
            return $data;
        });
    
    if ($processedResponse->isSuccess()) {
        echo "✅ 函数式处理链执行成功\n";
        $finalData = $processedResponse->getData();
        echo "处理结果: " . json_encode($finalData, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT) . "\n";
    } else {
        echo "❌ 处理链失败: [" . $processedResponse->getCode() . "] " . $processedResponse->getMessage() . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 函数式处理异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 4. 高级错误处理和重试策略 ====================

echo "=== 高级错误处理和重试策略 ===\n";

// 4.1 智能重试策略
echo "--- 智能重试策略 ---\n";

try {
    $advancedRetryConfig = [
        'maxAttempts' => 5,
        'baseDelay' => 2,
        'maxDelay' => 60,
        'retryOnCodes' => [429, 500, 502, 503, 504, 520, 521, 522, 523, 524]
    ];
    
    $retryResponse = ApiClientUtils::requestWithRetry(
        $sdk->getClient(),
        'GET',
        'v1/channel/shop_info',
        [],
        $accessToken,
        function($data) {
            // 数据后处理
            if (is_array($data)) {
                $data['retry_processed'] = true;
                $data['retry_timestamp'] = time();
            }
            return $data;
        },
        $advancedRetryConfig
    );
    
    if ($retryResponse->isSuccess()) {
        echo "✅ 智能重试成功\n";
        $retryData = $retryResponse->getData();
        if (isset($retryData['retry_processed'])) {
            echo "重试处理时间: " . date('Y-m-d H:i:s', $retryData['retry_timestamp']) . "\n";
        }
    } else {
        echo "❌ 智能重试失败: [" . $retryResponse->getCode() . "] " . $retryResponse->getMessage() . "\n";
    }
    
} catch (Exception $e) {
    echo "❌ 重试策略异常: " . $e->getMessage() . "\n";
}

// 4.2 响应验证和安全数据提取
echo "--- 响应验证和安全数据提取 ---\n";

try {
    $response = $sdk->getShopInfo($accessToken);
    
    // 验证响应数据完整性
    $requiredFields = ['shop_id', 'shop_name', 'status'];
    $isValid = ApiClientUtils::validateResponse($response, $requiredFields);
    
    if ($isValid) {
        echo "✅ 响应数据验证通过\n";
        
        // 安全数据提取
        $shopData = ApiClientUtils::extractTypedData($response, ShopInfoData::class, new ShopInfoData());
        
        if ($shopData instanceof ShopInfoData) {
            echo "类型安全提取成功: " . $shopData->getDisplayName() . "\n";
        } else {
            echo "使用默认数据对象\n";
        }
        
        // 带默认值的安全获取
        $shopName = $response->getDataOrDefault([])['shop_name'] ?? '默认门店名称';
        echo "门店名称（安全获取）: $shopName\n";
        
    } else {
        echo "⚠️ 响应数据验证失败，缺少必需字段\n";
    }
    
} catch (Exception $e) {
    echo "❌ 响应验证异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 5. 批量处理和并发模拟 ====================

echo "=== 批量处理和并发模拟 ===\n";

try {
    // 准备批量订单数据
    $batchOrders = [];
    for ($i = 1; $i <= 5; $i++) {
        $batchOrders[] = [
            'order_id' => 'BATCH_ORDER_' . $i . '_' . time(),
            'shop_id' => '123456',
            'order_amount' => rand(2000, 8000),
            'order_status' => 1,
            'create_time' => time(),
            'customer_id' => 'CUSTOMER_' . $i
        ];
    }
    
    // 构建批量请求
    $batchRequests = [];
    foreach ($batchOrders as $index => $order) {
        $batchRequests[] = [
            'method' => 'POST',
            'path' => 'v1/channel/order_created',
            'data' => $order,
            'token' => $accessToken,
            'dataClass' => function($data) use ($index) {
                if (is_array($data)) {
                    $data['batch_index'] = $index;
                    $data['batch_processed_at'] = microtime(true);
                }
                return $data;
            }
        ];
    }
    
    echo "准备批量处理 " . count($batchRequests) . " 个订单...\n";
    
    // 执行批量请求
    $startTime = microtime(true);
    $batchResults = $sdk->batchRequest($batchRequests);
    $endTime = microtime(true);
    
    echo "批量处理完成，耗时: " . round(($endTime - $startTime) * 1000, 2) . " 毫秒\n";
    
    // 统计结果
    $successCount = 0;
    $errorCount = 0;
    
    foreach ($batchResults as $index => $result) {
        if ($result->isSuccess()) {
            $successCount++;
            echo "✅ 订单 [$index] 处理成功\n";
        } else {
            $errorCount++;
            echo "❌ 订单 [$index] 处理失败: [" . $result->getCode() . "] " . $result->getMessage() . "\n";
        }
    }
    
    echo "批量处理统计: 成功 $successCount 个，失败 $errorCount 个\n";
    
} catch (Exception $e) {
    echo "❌ 批量处理异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 6. 性能监控和调试信息 ====================

echo "=== 性能监控和调试信息 ===\n";

try {
    // 启用详细调试信息
    Variables::setDebugging(true);
    
    echo "调试模式状态: " . (Variables::isDebugging() ? '开启' : '关闭') . "\n";
    echo "SDK 版本: " . Variables::getVersion() . "\n";
    echo "用户代理: " . Variables::getUserAgent() . "\n";
    
    // 执行一个请求来观察调试输出
    $debugResponse = $sdk->get('v1/channel/shop_info', $accessToken);
    
    if ($debugResponse->isSuccess()) {
        echo "✅ 调试请求成功\n";
    }
    
    // 获取客户端配置信息
    $config = $sdk->getConfig();
    echo "客户端配置:\n";
    echo "  - 基础URL: " . $config->BaseURL . "\n";
    echo "  - 请求超时: " . $config->RequestTimeout . " 秒\n";
    echo "  - 最大重试: " . $config->RetryMaxAttempts . " 次\n";
    echo "  - 日志启用: " . ($config->EnableLogging ? '是' : '否') . "\n";
    
} catch (Exception $e) {
    echo "❌ 性能监控异常: " . $e->getMessage() . "\n";
}

echo "\n";

// ==================== 7. 最佳实践演示 ====================

echo "=== 最佳实践演示 ===\n";

try {
    echo "--- 错误处理最佳实践 ---\n";
    
    // 标准化错误响应创建
    try {
        throw new Exception('模拟业务异常', 1001);
    } catch (Exception $e) {
        $errorResponse = ApiClientUtils::createErrorResponse('订单处理', $e);
        echo "标准化错误响应: [" . $errorResponse->getCode() . "] " . $errorResponse->getMessage() . "\n";
    }
    
    echo "--- 资源管理最佳实践 ---\n";
    
    // 使用 try-finally 模式确保资源清理
    $tempSdk = null;
    try {
        $tempSdk = ChannelSDK::newForTest('temp-key', 'temp-secret');
        echo "临时 SDK 创建成功\n";
        
        // 执行业务逻辑...
        $tempResponse = $tempSdk->getShopInfo($accessToken);
        echo "临时 SDK 请求完成\n";
        
    } finally {
        // 确保资源清理
        if ($tempSdk) {
            $tempSdk->close();
            echo "临时 SDK 资源已清理\n";
        }
    }
    
    echo "--- 配置管理最佳实践 ---\n";
    
    // 环境感知配置
    $environment = getenv('APP_ENV') ?: 'production';
    
    switch ($environment) {
        case 'development':
        case 'test':
            $bestPracticeSdk = ChannelSDK::newForTest('dev-key', 'dev-secret');
            echo "开发/测试环境 SDK 配置\n";
            break;
        case 'staging':
            $bestPracticeSdk = ChannelSDK::newDefault('staging-key', 'staging-secret', 'https://staging-api.example.com');
            echo "预发布环境 SDK 配置\n";
            break;
        default:
            $bestPracticeSdk = ChannelSDK::newFromEnv(); // 生产环境使用环境变量
            echo "生产环境 SDK 配置\n";
    }
    
    echo "当前环境: $environment\n";
    
} catch (Exception $e) {
    echo "❌ 最佳实践演示异常: " . $e->getMessage() . "\n";
}

// ==================== 8. 资源清理 ====================

echo "\n=== 资源清理 ===\n";

$sdk->close();
if (isset($bestPracticeSdk)) {
    $bestPracticeSdk->close();
}

echo "✅ 所有 SDK 资源已清理\n";
echo "=== 高级功能示例完成 ===\n";

/**
 * 运行此高级示例的环境要求：
 * 
 * 1. PHP 7.4+ 
 * 2. 已安装的扩展：curl, json
 * 3. 环境变量设置：
 *    export MAIYATIAN_APP_KEY="your-app-key"
 *    export MAIYATIAN_APP_SECRET="your-app-secret"
 *    export DEBUG="true"
 *    export APP_ENV="development"
 * 
 * 运行命令：
 *    php examples/advanced_features.php
 * 
 * 预期输出：
 * - 详细的功能演示日志
 * - 性能统计信息
 * - 错误处理示例
 * - 调试信息输出
 */