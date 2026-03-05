<?php

/**
 * PHPUnit Bootstrap File
 * 测试环境初始化和配置
 */

// 设置错误报告级别
error_reporting(E_ALL);
ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');

// 设置时区
date_default_timezone_set('Asia/Shanghai');

// 设置测试环境变量
putenv('APP_ENV=testing');
putenv('DEBUG=false');

// 定义根目录
define('SDK_ROOT_PATH', dirname(__DIR__));

// 自动加载器（如果使用Composer）
$composerAutoload = SDK_ROOT_PATH . '/vendor/autoload.php';
if (file_exists($composerAutoload)) {
    require_once $composerAutoload;
} else {
    // 手动加载SDK文件（开发环境）
    spl_autoload_register(function ($class) {
        // 移除命名空间前缀
        $prefix = 'Maiyatian\\Delivery\\PhpSdk\\';
        $base_dir = SDK_ROOT_PATH . '/';
        
        // 检查类是否使用了命名空间前缀
        $len = strlen($prefix);
        if (strncmp($prefix, $class, $len) !== 0) {
            return;
        }
        
        // 获取相对类名
        $relative_class = substr($class, $len);
        
        // 将命名空间分隔符替换为目录分隔符，添加.php后缀
        $file = $base_dir . str_replace('\\', '/', $relative_class) . '.php';
        
        // 如果文件存在，则包含它
        if (file_exists($file)) {
            require $file;
        } else {
            // 尝试备选路径（特殊情况处理）
            $alternatives = [
                $base_dir . str_replace('\\', '/', $relative_class) . '.php',
                $base_dir . 'models/DeliveryModels.php' // ModelFactory在这个文件中
            ];
            
            foreach ($alternatives as $altFile) {
                if (file_exists($altFile)) {
                    require $altFile;
                    break;
                }
            }
        }
    });
}

// 测试辅助函数
if (!function_exists('createTestConfig')) {
    /**
     * 创建测试用的配置对象
     * 
     * @return \Maiyatian\Delivery\PhpSdk\Client\Config
     */
    function createTestConfig(): \Maiyatian\Delivery\PhpSdk\Client\Config
    {
        return (new \Maiyatian\Delivery\PhpSdk\Client\ConfigBuilder())
            ->baseURL('https://test-api.maiyatian.com')
            ->apiKey('test-api-key')
            ->apiSecret('test-api-secret')
            ->requestTimeout(30)
            ->retryMaxAttempts(2)
            ->enableLogging(false)
            ->build();
    }
}

if (!function_exists('createTestAddress')) {
    /**
     * 创建测试地址对象
     * 
     * @param string $type 地址类型 'from' 或 'to'
     * @return \Maiyatian\Delivery\PhpSdk\Models\Address
     */
    function createTestAddress(string $type = 'from'): \Maiyatian\Delivery\PhpSdk\Models\Address
    {
        if ($type === 'from') {
            $address = new \Maiyatian\Delivery\PhpSdk\Models\Address(
                '北京市朝阳区建国门外大街1号',
                '发件人',
                '13800000001',
                116.456789,
                39.123456
            );
        } else {
            $address = new \Maiyatian\Delivery\PhpSdk\Models\Address(
                '上海市浦东新区陆家嘴环路1000号',
                '收件人',
                '13900000001',
                121.567890,
                31.234567
            );
        }
        
        $address->province = $type === 'from' ? '北京市' : '上海市';
        $address->city = $type === 'from' ? '北京市' : '上海市';
        $address->district = $type === 'from' ? '朝阳区' : '浦东新区';
        
        return $address;
    }
}

if (!function_exists('createTestOrderItem')) {
    /**
     * 创建测试订单商品
     * 
     * @param int $index 商品索引
     * @return \Maiyatian\Delivery\PhpSdk\Models\OrderItem
     */
    function createTestOrderItem(int $index = 1): \Maiyatian\Delivery\PhpSdk\Models\OrderItem
    {
        $items = [
            ['name' => 'iPhone 15 Pro', 'price' => 9999.00, 'weight' => 500],
            ['name' => '小米14 Ultra', 'price' => 6499.00, 'weight' => 280],
            ['name' => 'MacBook Pro', 'price' => 19999.00, 'weight' => 2000],
        ];
        
        $itemData = $items[($index - 1) % count($items)];
        
        $item = new \Maiyatian\Delivery\PhpSdk\Models\OrderItem(
            $itemData['name'],
            1,
            $itemData['price']
        );
        
        $item->weight = $itemData['weight'];
        $item->remark = "测试商品 {$index}";
        
        return $item;
    }
}

if (!function_exists('createTestDeliveryOrder')) {
    /**
     * 创建测试配送订单
     * 
     * @param array $overrides 覆盖的属性
     * @return \Maiyatian\Delivery\PhpSdk\Models\DeliveryOrder
     */
    function createTestDeliveryOrder(array $overrides = []): \Maiyatian\Delivery\PhpSdk\Models\DeliveryOrder
    {
        $defaultData = [
            'order_id' => 'TEST_ORDER_' . uniqid(),
            'merchant_order_id' => 'MERCHANT_' . time(),
            'status' => \Maiyatian\Delivery\PhpSdk\Consts\Consts::DELIVERY_STATUS_PENDING,
            'delivery_fee' => 15.00,
            'created_at' => (new DateTime())->format('Y-m-d H:i:s'),
            'from_address' => createTestAddress('from')->toArray(),
            'to_address' => createTestAddress('to')->toArray(),
            'items' => [createTestOrderItem(1)->toArray()]
        ];
        
        $data = array_merge($defaultData, $overrides);
        
        return \Maiyatian\Delivery\PhpSdk\Models\ModelFactory::createDeliveryOrderFromArray($data);
    }
}

if (!function_exists('mockHttpResponse')) {
    /**
     * 创建模拟HTTP响应
     * 
     * @param int $statusCode HTTP状态码
     * @param int $businessCode 业务状态码
     * @param string $message 响应消息
     * @param array|null $data 响应数据
     * @return \Maiyatian\Delivery\PhpSdk\Client\HTTPResponse
     */
    function mockHttpResponse(
        int $statusCode = 200, 
        int $businessCode = 200, 
        string $message = 'ok', 
        ?array $data = null
    ): \Maiyatian\Delivery\PhpSdk\Client\HTTPResponse {
        $response = new \Maiyatian\Delivery\PhpSdk\Client\HTTPResponse();
        $response->StatusCode = $statusCode;
        $response->Code = $businessCode;
        $response->Message = $message;
        $response->Data = $data ? json_encode($data, JSON_UNESCAPED_UNICODE) : '';
        
        return $response;
    }
}

// 预加载常用类（避免测试中的自动加载延迟）
class_exists(\Maiyatian\Delivery\PhpSdk\Consts\Consts::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Vars\Variables::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Client\Config::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Client\ConfigBuilder::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Client\ApiResponse::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Client\HTTPResponse::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Client\HTTPClient::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Generics\TypeSafeContainer::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Models\DeliveryOrder::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Models\Address::class);
class_exists(\Maiyatian\Delivery\PhpSdk\Models\ModelFactory::class);

// 测试环境检查
if (!class_exists('PHPUnit\Framework\TestCase', false)) {
    // 尝试加载PHPUnit（如果通过全局安装）
    @include_once 'PHPUnit/Framework/TestCase.php';
    
    if (!class_exists('PHPUnit\Framework\TestCase', false)) {
        echo "⚠ Warning: PHPUnit not detected in current context. Tests may fail if PHPUnit is not properly configured.\n";
        echo "  This is normal when running bootstrap.php directly.\n";
    }
}

echo "✓ Test environment initialized successfully\n";
echo "✓ SDK Root Path: " . SDK_ROOT_PATH . "\n";
echo "✓ PHP Version: " . PHP_VERSION . "\n";
echo "✓ Memory Limit: " . ini_get('memory_limit') . "\n";
echo "✓ Timezone: " . date_default_timezone_get() . "\n";
echo "✓ Current Time: " . date('Y-m-d H:i:s') . "\n\n";