<?php
/**
 * 麦芽田配送开放平台 PHP SDK - 高级使用示例
 * 展示泛型容器、函数式编程、错误处理等高级功能
 */

require_once __DIR__ . '/../vendor/autoload.php';

use Maiyatian\Delivery\PhpSdk\Client\Config;
use Maiyatian\Delivery\PhpSdk\Client\HTTPClient;
use Maiyatian\Delivery\PhpSdk\Client\ApiClientUtils;
use Maiyatian\Delivery\PhpSdk\Models\DeliveryOrder;
use Maiyatian\Delivery\PhpSdk\Models\Address;
use Maiyatian\Delivery\PhpSdk\Models\OrderItem;
use Maiyatian\Delivery\PhpSdk\Models\ModelFactory;
use Maiyatian\Delivery\PhpSdk\Generics\TypeSafeContainer;
use Maiyatian\Delivery\PhpSdk\Generics\TypeSafeList;
use Maiyatian\Delivery\PhpSdk\Generics\Result;
use Maiyatian\Delivery\PhpSdk\Consts\Consts;
use Maiyatian\Delivery\PhpSdk\Vars\Variables;

// 启用调试模式
putenv('DEBUG=true');

echo "=== 麦芽田配送开放平台 PHP SDK 高级功能示例 ===\n\n";

try {
    // 1. 类型安全容器高级用法
    echo "1. 类型安全容器高级用法...\n";
    
    // 创建包含配送订单的容器
    $orderData = [
        'order_id' => 'ORDER_001',
        'merchant_order_id' => 'MERCHANT_001',
        'status' => Consts::DELIVERY_STATUS_DELIVERING,
        'delivery_fee' => 25.50,
        'created_at' => '2024-12-17 10:30:00',
        'from_address' => [
            'detail' => '北京市朝阳区建国门外大街1号',
            'contact_name' => '发货人',
            'contact_phone' => '13800000001',
            'longitude' => 116.456789,
            'latitude' => 39.123456
        ],
        'to_address' => [
            'detail' => '上海市浦东新区陆家嘴环路1000号',
            'contact_name' => '收货人', 
            'contact_phone' => '13900000001',
            'longitude' => 121.567890,
            'latitude' => 31.234567
        ],
        'items' => [
            [
                'name' => '文件资料',
                'quantity' => 1,
                'price' => 0.0,
                'weight' => 500
            ]
        ]
    ];
    
    $order = ModelFactory::createDeliveryOrderFromArray($orderData);
    $orderContainer = TypeSafeContainer::of($order, DeliveryOrder::class);
    
    echo "✓ 订单容器创建成功\n";
    
    // 使用函数式编程风格处理订单
    $deliveryFeeContainer = $orderContainer
        ->map(fn($order) => $order->deliveryFee, 'float')
        ->filter(fn($fee) => $fee > 0);
    
    if ($deliveryFeeContainer->hasValue()) {
        echo sprintf("  - 配送费: ¥%.2f\n", $deliveryFeeContainer->get());
    }
    
    // 链式操作处理订单状态
    $statusMessage = $orderContainer
        ->map(fn($order) => $order->getStatusDescription())
        ->map(fn($status) => "当前状态: {$status}")
        ->getOrDefault('状态未知');
    
    echo sprintf("  - %s\n", $statusMessage);
    
    // 安全获取嵌套数据
    $fromAddressContainer = $orderContainer->map(fn($order) => $order->fromAddress, Address::class);
    $contactInfo = $fromAddressContainer
        ->map(fn($addr) => sprintf('%s (%s)', $addr->contactName, $addr->contactPhone))
        ->getOrDefault('联系人信息缺失');
    
    echo sprintf("  - 发货联系人: %s\n", $contactInfo);
    
    echo "\n";

    // 2. 类型安全列表操作
    echo "2. 类型安全列表操作...\n";
    
    // 创建多个订单的列表
    $ordersData = [
        ['order_id' => 'ORDER_001', 'status' => Consts::DELIVERY_STATUS_PENDING, 'delivery_fee' => 15.0],
        ['order_id' => 'ORDER_002', 'status' => Consts::DELIVERY_STATUS_DELIVERING, 'delivery_fee' => 25.0],
        ['order_id' => 'ORDER_003', 'status' => Consts::DELIVERY_STATUS_DELIVERED, 'delivery_fee' => 18.0],
        ['order_id' => 'ORDER_004', 'status' => Consts::DELIVERY_STATUS_CANCELED, 'delivery_fee' => 0.0],
    ];
    
    $orders = array_map(fn($data) => ModelFactory::createDeliveryOrderFromArray($data), $ordersData);
    $ordersList = new TypeSafeList($orders, DeliveryOrder::class);
    
    echo sprintf("✓ 创建订单列表，共 %d 个订单\n", $ordersList->count());
    
    // 过滤正在配送的订单
    $deliveringOrders = $ordersList->filter(fn($order) => $order->isDelivering());
    echo sprintf("  - 正在配送的订单: %d 个\n", $deliveringOrders->count());
    
    // 计算总配送费
    $totalFee = $ordersList->fold(0.0, fn($total, $order) => $total + $order->deliveryFee);
    echo sprintf("  - 总配送费: ¥%.2f\n", $totalFee);
    
    // 获取所有订单状态描述
    $statusList = $ordersList->map(fn($order) => $order->getStatusDescription(), 'string');
    echo sprintf("  - 状态列表: %s\n", implode(', ', $statusList->toArray()));
    
    // 查找第一个已完成的订单
    $completedOrder = $ordersList->findFirst(fn($order) => $order->isCompleted());
    if ($completedOrder->hasValue()) {
        $order = $completedOrder->get();
        echo sprintf("  - 第一个已完成订单: %s\n", $order->orderId);
    } else {
        echo "  - 没有找到已完成的订单\n";
    }
    
    echo "\n";

    // 3. Result模式处理错误
    echo "3. Result模式错误处理...\n";
    
    // 模拟API调用的Result模式
    function simulateApiCall(string $orderId): Result {
        if (empty($orderId)) {
            return Result::error('订单ID不能为空');
        }
        
        if (strpos($orderId, 'ERROR') !== false) {
            return Result::error('API调用失败: 订单不存在');
        }
        
        // 模拟成功响应
        return Result::success([
            'order_id' => $orderId,
            'status' => 'delivered',
            'delivery_time' => '2024-12-17 15:30:00'
        ]);
    }
    
    // 成功案例
    $result1 = simulateApiCall('ORDER_SUCCESS_001');
    $result1->effect(
        function($data) {
            echo sprintf("✓ 订单 %s 查询成功，状态: %s\n", $data['order_id'], $data['status']);
        },
        function($error) {
            echo sprintf("❌ 查询失败: %s\n", $error);
        }
    );
    
    // 错误案例
    $result2 = simulateApiCall('ORDER_ERROR_001');
    $recoveredResult = $result2->recover(function($error) {
        echo sprintf("⚠ 发生错误: %s，使用默认数据\n", $error);
        return ['order_id' => 'DEFAULT', 'status' => 'unknown'];
    });
    
    if ($recoveredResult->isSuccess()) {
        $data = $recoveredResult->getValue();
        echo sprintf("✓ 恢复后的数据: %s (状态: %s)\n", $data['order_id'], $data['status']);
    }
    
    // Result链式操作
    $processedResult = simulateApiCall('ORDER_SUCCESS_002')
        ->map(fn($data) => $data['status'])  // 提取状态
        ->map(fn($status) => strtoupper($status))  // 转大写
        ->map(fn($status) => "状态: {$status}");  // 格式化
    
    if ($processedResult->isSuccess()) {
        echo sprintf("✓ 处理结果: %s\n", $processedResult->getValue());
    }
    
    echo "\n";

    // 4. 高级HTTP客户端功能
    echo "4. 高级HTTP客户端功能...\n";
    
    $config = (new \Maiyatian\Delivery\PhpSdk\Client\ConfigBuilder())
        ->baseURL(Consts::TEST_BASE_URL)
        ->apiKey('demo-key')
        ->apiSecret('demo-secret')
        ->requestTimeout(15)
        ->retryMaxAttempts(2)
        ->enableLogging(true)
        ->build();
    
    $httpClient = new HTTPClient($config);
    echo "✓ HTTP客户端创建成功\n";
    
    // 动态调整配置
    echo "  - 原始超时: {$config->requestTimeout}秒\n";
    $httpClient->updateTimeout(20);
    echo "  - 调整后超时: 20秒\n";
    
    $httpClient->updateRetrySettings(5, 2000, 10000);
    echo "  - 重试设置已更新: 最大5次，延迟2-10秒\n";
    
    echo "\n";

    // 5. 模型工厂和类型转换
    echo "5. 模型工厂和类型转换...\n";
    
    // 复杂地址对象创建
    $addressData = [
        'detail' => '北京市海淀区中关村大街59号人民大学',
        'contact_name' => '王五',
        'contact_phone' => '13712345678',
        'longitude' => 116.308654,
        'latitude' => 39.966847,
        'province' => '北京市',
        'city' => '北京市',
        'district' => '海淀区'
    ];
    
    $address = ModelFactory::createAddressFromArray($addressData);
    echo "✓ 地址对象创建成功\n";
    echo sprintf("  - 详细地址: %s\n", $address->detail);
    echo sprintf("  - 联系人: %s (%s)\n", $address->contactName, $address->contactPhone);
    echo sprintf("  - 坐标: %.6f, %.6f\n", $address->longitude, $address->latitude);
    echo sprintf("  - 地址有效性: %s\n", $address->isValid() ? '有效' : '无效');
    
    // 商品列表创建和处理
    $itemsData = [
        ['name' => '华为P60 Pro', 'quantity' => 1, 'price' => 6988.0, 'weight' => 300],
        ['name' => '小米14 Ultra', 'quantity' => 2, 'price' => 6499.0, 'weight' => 280],
        ['name' => 'vivo X100', 'quantity' => 1, 'price' => 4499.0, 'weight' => 260]
    ];
    
    $items = array_map(fn($data) => ModelFactory::createOrderItemFromArray($data), $itemsData);
    $itemsList = new TypeSafeList($items, OrderItem::class);
    
    echo "\n✓ 商品列表创建成功\n";
    echo sprintf("  - 商品种类: %d\n", $itemsList->count());
    
    // 计算商品统计信息
    $totalQuantity = $itemsList->fold(0, fn($sum, $item) => $sum + $item->quantity);
    $totalWeight = $itemsList->fold(0.0, fn($sum, $item) => $sum + ($item->weight ?? 0));
    $totalValue = $itemsList->fold(0.0, fn($sum, $item) => $sum + $item->getTotalPrice());
    
    echo sprintf("  - 总数量: %d\n", $totalQuantity);
    echo sprintf("  - 总重量: %.0fg\n", $totalWeight);
    echo sprintf("  - 总价值: ¥%.2f\n", $totalValue);
    
    // 查找最贵的商品
    $mostExpensive = $itemsList
        ->fold(null, fn($max, $item) => 
            $max === null || $item->price > $max->price ? $item : $max
        );
    
    if ($mostExpensive) {
        echo sprintf("  - 最贵商品: %s (¥%.2f)\n", $mostExpensive->name, $mostExpensive->price);
    }
    
    echo "\n";

    // 6. 错误处理和重试机制演示
    echo "6. 错误处理和重试机制演示...\n";
    
    // 模拟网络不稳定的请求
    function unstableRequest(int $attempt): Result {
        // 前2次失败，第3次成功
        if ($attempt < 3) {
            return Result::error("网络错误 (尝试 {$attempt}/3)");
        }
        return Result::success(['data' => '请求成功', 'attempt' => $attempt]);
    }
    
    // 重试逻辑演示
    $maxRetries = 3;
    $finalResult = null;
    
    for ($attempt = 1; $attempt <= $maxRetries; $attempt++) {
        echo sprintf("  尝试第 %d 次请求...\n", $attempt);
        $result = unstableRequest($attempt);
        
        if ($result->isSuccess()) {
            echo "  ✓ 请求成功！\n";
            $finalResult = $result;
            break;
        } else {
            echo sprintf("  ❌ %s\n", $result->getError());
            if ($attempt < $maxRetries) {
                echo "    等待重试...\n";
                usleep(500000); // 0.5秒延迟
            }
        }
    }
    
    if ($finalResult && $finalResult->isSuccess()) {
        $data = $finalResult->getValue();
        echo sprintf("✓ 最终结果: %s (第%d次尝试)\n", $data['data'], $data['attempt']);
    } else {
        echo "❌ 所有重试均失败\n";
    }
    
    echo "\n";

    // 7. 性能监控示例
    echo "7. 性能监控示例...\n";
    
    $startTime = microtime(true);
    $memoryStart = memory_get_usage(true);
    
    // 大量数据处理示例
    $largeOrderList = new TypeSafeList([], DeliveryOrder::class);
    
    // 生成1000个测试订单
    for ($i = 1; $i <= 1000; $i++) {
        $testOrder = ModelFactory::createDeliveryOrderFromArray([
            'order_id' => sprintf('TEST_%04d', $i),
            'merchant_order_id' => sprintf('MERCHANT_%04d', $i),
            'status' => ($i % 4) + 1, // 轮流分配状态
            'delivery_fee' => round(rand(1000, 5000) / 100, 2), // 10-50元随机费用
            'created_at' => (new DateTime())->format('Y-m-d H:i:s')
        ]);
        $largeOrderList->add($testOrder);
    }
    
    $endTime = microtime(true);
    $memoryEnd = memory_get_usage(true);
    
    echo sprintf("✓ 生成了 %d 个测试订单\n", $largeOrderList->count());
    echo sprintf("  - 耗时: %.3f秒\n", $endTime - $startTime);
    echo sprintf("  - 内存使用: %.2fMB\n", ($memoryEnd - $memoryStart) / 1024 / 1024);
    
    // 批量统计
    $statusStats = $largeOrderList->fold([], function($stats, $order) {
        $status = $order->getStatusDescription();
        $stats[$status] = ($stats[$status] ?? 0) + 1;
        return $stats;
    });
    
    echo "  - 状态分布:\n";
    foreach ($statusStats as $status => $count) {
        echo sprintf("    %s: %d\n", $status, $count);
    }
    
    // 清理资源
    $httpClient->close();
    
    echo "\n=== 高级功能示例完成 ===\n";
    echo "本示例展示了:\n";
    echo "1. 类型安全容器的高级用法\n";
    echo "2. 函数式编程风格的数据处理\n";
    echo "3. Result模式的错误处理\n";
    echo "4. 动态配置调整\n";
    echo "5. 复杂模型对象的创建和操作\n";
    echo "6. 重试机制和错误恢复\n";
    echo "7. 性能监控和大数据量处理\n";

} catch (Exception $e) {
    echo "\n❌ 示例执行失败\n";
    echo "错误信息: " . $e->getMessage() . "\n";
    echo "错误位置: " . $e->getFile() . ":" . $e->getLine() . "\n";
    
    if (Variables::isDebugging()) {
        echo "\n详细错误堆栈:\n";
        echo $e->getTraceAsString() . "\n";
    }
}