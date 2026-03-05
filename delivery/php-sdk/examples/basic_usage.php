<?php
/**
 * 麦芽田配送开放平台 PHP SDK - 基础使用示例
 * 展示SDK的基本功能和使用方法
 */

require_once __DIR__ . '/../vendor/autoload.php';

use Maiyatian\Delivery\PhpSdk\Client\Config;
use Maiyatian\Delivery\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Delivery\PhpSdk\Client\HTTPClient;
use Maiyatian\Delivery\PhpSdk\Client\ApiClientUtils;
use Maiyatian\Delivery\PhpSdk\Models\DeliveryOrder;
use Maiyatian\Delivery\PhpSdk\Models\Address;
use Maiyatian\Delivery\PhpSdk\Models\OrderItem;
use Maiyatian\Delivery\PhpSdk\Models\ModelFactory;
use Maiyatian\Delivery\PhpSdk\Consts\Consts;
use Maiyatian\Delivery\PhpSdk\Vars\Variables;

// 启用调试模式（可选）
putenv('DEBUG=true');

echo "=== 麦芽田配送开放平台 PHP SDK 基础使用示例 ===\n\n";

try {
    // 1. 创建HTTP客户端配置
    echo "1. 创建HTTP客户端配置...\n";
    
    $config = (new ConfigBuilder())
        ->baseURL(Consts::TEST_BASE_URL)  // 测试环境
        ->apiKey('your-api-key')          // 替换为实际的API Key
        ->apiSecret('your-api-secret')    // 替换为实际的API Secret
        ->requestTimeout(30)              // 30秒超时
        ->retryMaxAttempts(3)             // 最大重试3次
        ->enableLogging(true)             // 启用日志
        ->build();
    
    echo "✓ 配置创建成功\n";
    echo sprintf("  - BaseURL: %s\n", $config->baseURL);
    echo sprintf("  - SDK版本: %s\n", Variables::getVersion());
    echo sprintf("  - 调试模式: %s\n\n", Variables::isDebugging() ? '开启' : '关闭');

    // 2. 创建HTTP客户端
    echo "2. 创建HTTP客户端...\n";
    $httpClient = new HTTPClient($config);
    echo "✓ HTTP客户端创建成功\n\n";

    // 3. 获取访问令牌
    echo "3. 获取访问令牌...\n";
    
    try {
        $tokenResponse = ApiClientUtils::getAccessToken($httpClient, Consts::GRANT_TYPE_STORE);
        
        if ($tokenResponse->isSuccess()) {
            $tokenData = $tokenResponse->getData();
            $accessToken = $tokenData['access_token'] ?? '';
            echo "✓ 访问令牌获取成功\n";
            echo sprintf("  - 令牌类型: %s\n", $tokenData['token_type'] ?? 'Bearer');
            echo sprintf("  - 有效期: %d秒\n\n", $tokenData['expires_in'] ?? 3600);
        } else {
            throw new Exception(sprintf("获取访问令牌失败: [%d] %s", 
                $tokenResponse->getCode(), $tokenResponse->getMessage()));
        }
    } catch (Exception $e) {
        echo "⚠ 访问令牌获取失败（演示继续）: " . $e->getMessage() . "\n";
        $accessToken = 'demo-token';  // 使用演示令牌继续
        echo "\n";
    }

    // 4. 创建配送订单
    echo "4. 创建配送订单示例...\n";
    
    // 创建发货地址
    $fromAddress = new Address(
        '北京市朝阳区三里屯SOHO 1号楼',
        '张三',
        '13800138000',
        116.456789,
        39.923456
    );
    $fromAddress->province = '北京市';
    $fromAddress->city = '北京市';
    $fromAddress->district = '朝阳区';
    
    // 创建收货地址
    $toAddress = new Address(
        '北京市海淀区中关村大街1号',
        '李四',
        '13900139000',
        116.298765,
        39.987654
    );
    $toAddress->province = '北京市';
    $toAddress->city = '北京市';
    $toAddress->district = '海淀区';
    
    // 创建订单商品
    $items = [
        new OrderItem('MacBook Pro', 1, 12999.00),
        new OrderItem('iPhone 15', 2, 5999.00)
    ];
    $items[0]->weight = 2000; // 2kg
    $items[0]->remark = '贵重物品，请轻拿轻放';
    
    // 构建订单数据
    $orderData = [
        'merchant_order_id' => 'ORDER_' . uniqid(),
        'from_address' => $fromAddress->toArray(),
        'to_address' => $toAddress->toArray(),
        'items' => array_map(fn($item) => $item->toArray(), $items),
        'delivery_fee' => 15.00,
        'remark' => '请在工作日配送，联系收货人确认时间',
        'expected_delivery_time' => (new DateTime('+2 hours'))->format('Y-m-d H:i:s')
    ];
    
    echo "✓ 订单数据构建完成\n";
    echo sprintf("  - 商户订单号: %s\n", $orderData['merchant_order_id']);
    echo sprintf("  - 商品数量: %d\n", count($items));
    echo sprintf("  - 配送费: ¥%.2f\n", $orderData['delivery_fee']);
    
    // 模拟创建订单请求
    try {
        // 注意：这里是示例代码，实际需要有效的API端点
        echo "\n正在调用配送订单创建API...\n";
        
        // 使用ApiClientUtils创建订单
        $createResponse = ApiClientUtils::createDeliveryOrder($httpClient, $orderData);
        
        if ($createResponse->isDeliverySuccess()) {
            $orderResult = $createResponse->getData();
            echo "✓ 配送订单创建成功\n";
            echo sprintf("  - 订单ID: %s\n", $orderResult['order_id'] ?? 'N/A');
            echo sprintf("  - 配送单号: %s\n", $orderResult['delivery_id'] ?? '待分配');
        } else {
            echo "⚠ 配送订单创建失败: " . $createResponse->getMessage() . "\n";
        }
    } catch (Exception $e) {
        echo "⚠ 订单创建请求失败（API端点不存在或网络问题）\n";
        echo "  错误信息: " . $e->getMessage() . "\n";
    }

    echo "\n";

    // 5. 查询配送状态示例
    echo "5. 查询配送状态示例...\n";
    
    $deliveryId = 'DEL_' . uniqid();  // 模拟配送单号
    
    try {
        echo sprintf("正在查询配送单 %s 的状态...\n", $deliveryId);
        
        $statusResponse = ApiClientUtils::getDeliveryStatus($httpClient, $deliveryId);
        
        if ($statusResponse->isSuccess()) {
            $statusData = $statusResponse->getData();
            echo "✓ 配送状态查询成功\n";
            echo sprintf("  - 状态: %s\n", $statusData['status_description'] ?? '未知');
            echo sprintf("  - 配送员: %s\n", $statusData['driver']['name'] ?? '未分配');
        } else {
            echo "⚠ 配送状态查询失败: " . $statusResponse->getMessage() . "\n";
        }
    } catch (Exception $e) {
        echo "⚠ 状态查询请求失败\n";
        echo "  错误信息: " . $e->getMessage() . "\n";
    }

    echo "\n";

    // 6. 使用类型安全容器示例
    echo "6. 类型安全容器使用示例...\n";
    
    // 创建订单对象并使用类型安全容器
    $order = ModelFactory::createDeliveryOrderFromArray([
        'order_id' => 'ORDER_001',
        'merchant_order_id' => 'MERCHANT_001',
        'status' => Consts::DELIVERY_STATUS_DELIVERING,
        'delivery_fee' => 15.00,
        'created_at' => (new DateTime())->format('Y-m-d H:i:s')
    ]);
    
    echo "✓ 订单对象创建完成\n";
    echo sprintf("  - 订单状态: %s\n", $order->getStatusDescription());
    echo sprintf("  - 是否配送中: %s\n", $order->isDelivering() ? '是' : '否');
    
    // 使用函数式编程风格处理数据
    $orderContainer = \Maiyatian\Delivery\PhpSdk\Generics\TypeSafeContainer::of($order, DeliveryOrder::class);
    
    $statusDescription = $orderContainer
        ->map(fn($o) => $o->getStatusDescription())
        ->getOrDefault('未知状态');
    
    echo sprintf("  - 通过容器获取状态: %s\n", $statusDescription);
    
    echo "\n";

    // 7. 批量操作示例
    echo "7. 批量操作示例...\n";
    
    $batchRequests = [
        [
            'method' => 'GET',
            'endpoint' => 'v1/delivery/orders',
            'data' => null,
            'responseType' => 'array'
        ],
        [
            'method' => 'GET',
            'endpoint' => 'v1/delivery/drivers',
            'data' => null,
            'responseType' => 'array'
        ]
    ];
    
    try {
        $batchResponses = ApiClientUtils::batchRequest($httpClient, $batchRequests);
        echo sprintf("✓ 批量请求完成，共 %d 个请求\n", count($batchResponses));
        
        foreach ($batchResponses as $index => $response) {
            $endpoint = $batchRequests[$index]['endpoint'];
            if ($response->isSuccess()) {
                echo sprintf("  - %s: 成功\n", $endpoint);
            } else {
                echo sprintf("  - %s: 失败 (%s)\n", $endpoint, $response->getMessage());
            }
        }
    } catch (Exception $e) {
        echo "⚠ 批量请求失败: " . $e->getMessage() . "\n";
    }
    
    echo "\n";

    // 8. 资源清理
    echo "8. 清理资源...\n";
    $httpClient->close();
    echo "✓ HTTP客户端已关闭\n";

    echo "\n=== 示例执行完成 ===\n";
    echo "注意：本示例使用的是演示数据，实际使用时需要:\n";
    echo "1. 替换为真实的API Key和Secret\n";
    echo "2. 使用有效的访问令牌\n";
    echo "3. 确保网络可以访问麦芽田开放平台API\n";

} catch (Exception $e) {
    echo "\n❌ 示例执行失败\n";
    echo "错误信息: " . $e->getMessage() . "\n";
    echo "错误文件: " . $e->getFile() . ":" . $e->getLine() . "\n";
    
    if (Variables::isDebugging()) {
        echo "\n调试信息:\n";
        echo $e->getTraceAsString() . "\n";
    }
}