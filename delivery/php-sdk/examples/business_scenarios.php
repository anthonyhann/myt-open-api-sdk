<?php
/**
 * 麦芽田配送开放平台 PHP SDK - 实际业务场景示例
 * 展示电商、外卖、同城配送等真实业务场景的使用方法
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
use Maiyatian\Delivery\PhpSdk\Models\AccessTokenResponse;
use Maiyatian\Delivery\PhpSdk\Generics\TypeSafeContainer;
use Maiyatian\Delivery\PhpSdk\Generics\Result;
use Maiyatian\Delivery\PhpSdk\Consts\Consts;
use Maiyatian\Delivery\PhpSdk\Vars\Variables;

// 配置环境
putenv('DEBUG=false'); // 生产环境关闭详细调试

echo "=== 麦芽田配送开放平台 PHP SDK 业务场景示例 ===\n\n";

class DeliveryService 
{
    private $httpClient;
    private $accessToken;
    
    public function __construct(HTTPClient $httpClient) 
    {
        $this->httpClient = $httpClient;
        $this->initializeToken();
    }
    
    /**
     * 初始化访问令牌
     */
    private function initializeToken(): void 
    {
        try {
            $response = ApiClientUtils::getAccessToken($this->httpClient);
            if ($response->isSuccess()) {
                $tokenData = $response->getData();
                $this->accessToken = $tokenData['access_token'] ?? 'demo-token';
                echo "✓ 访问令牌初始化成功\n";
            } else {
                echo "⚠ 使用演示令牌\n";
                $this->accessToken = 'demo-token';
            }
        } catch (Exception $e) {
            echo "⚠ 访问令牌初始化失败，使用演示模式\n";
            $this->accessToken = 'demo-token';
        }
    }
    
    /**
     * 创建配送订单
     */
    public function createOrder(array $orderData): Result 
    {
        try {
            $response = ApiClientUtils::createDeliveryOrder($this->httpClient, $orderData);
            
            if ($response->isDeliverySuccess()) {
                return Result::success($response->getData());
            } else {
                return Result::error($response->getMessage());
            }
        } catch (Exception $e) {
            return Result::error($e->getMessage());
        }
    }
    
    /**
     * 查询订单状态
     */
    public function getOrderStatus(string $deliveryId): Result 
    {
        try {
            $response = ApiClientUtils::getDeliveryStatus($this->httpClient, $deliveryId);
            
            if ($response->isSuccess()) {
                return Result::success($response->getData());
            } else {
                return Result::error($response->getMessage());
            }
        } catch (Exception $e) {
            return Result::error($e->getMessage());
        }
    }
    
    /**
     * 取消订单
     */
    public function cancelOrder(string $orderId, string $reason): Result 
    {
        try {
            $response = ApiClientUtils::cancelDeliveryOrder($this->httpClient, $orderId, $reason);
            
            if ($response->isSuccess()) {
                return Result::success($response->getData());
            } else {
                return Result::error($response->getMessage());
            }
        } catch (Exception $e) {
            return Result::error($e->getMessage());
        }
    }
}

try {
    // 初始化配送服务
    $config = (new ConfigBuilder())
        ->baseURL(Consts::TEST_BASE_URL)
        ->apiKey('your-api-key')
        ->apiSecret('your-api-secret')
        ->requestTimeout(30)
        ->retryMaxAttempts(3)
        ->enableLogging(false) // 生产环境建议关闭详细日志
        ->build();
    
    $httpClient = new HTTPClient($config);
    $deliveryService = new DeliveryService($httpClient);
    
    echo "\n";

    // ==================== 场景1: 电商订单配送 ====================
    echo "【场景1: 电商订单配送】\n";
    echo "模拟电商平台的商品配送流程\n\n";
    
    // 创建电商订单
    $ecommerceOrderData = [
        'merchant_order_id' => 'EC_' . date('YmdHis') . '_001',
        'from_address' => [
            'detail' => '上海市浦东新区张江高科技园区祖冲之路899号',
            'contact_name' => '仓库管理员',
            'contact_phone' => '400-888-1001',
            'longitude' => 121.567542,
            'latitude' => 31.205479,
            'province' => '上海市',
            'city' => '上海市',
            'district' => '浦东新区'
        ],
        'to_address' => [
            'detail' => '北京市朝阳区国贸CBD建外SOHO西区15号楼',
            'contact_name' => '张先生',
            'contact_phone' => '13912345678',
            'longitude' => 116.459242,
            'latitude' => 39.912345,
            'province' => '北京市',
            'city' => '北京市',
            'district' => '朝阳区'
        ],
        'items' => [
            [
                'name' => 'iPhone 15 Pro Max 256GB 深空黑色',
                'quantity' => 1,
                'price' => 9999.00,
                'weight' => 500,
                'size' => '15.9×7.7×0.83cm',
                'remark' => '贵重商品，需验货签收'
            ],
            [
                'name' => 'MagSafe 无线充电器',
                'quantity' => 1,
                'price' => 329.00,
                'weight' => 150,
                'remark' => '配件'
            ]
        ],
        'delivery_fee' => 35.00,
        'remark' => '客户要求次日达，请优先安排配送',
        'expected_delivery_time' => (new DateTime('+1 day 10:00'))->format('Y-m-d H:i:s')
    ];
    
    echo "正在创建电商订单...\n";
    $orderResult = $deliveryService->createOrder($ecommerceOrderData);
    
    $orderResult->effect(
        function($data) {
            echo "✓ 电商订单创建成功\n";
            echo sprintf("  - 订单号: %s\n", $data['order_id'] ?? 'N/A');
            echo sprintf("  - 配送单号: %s\n", $data['delivery_id'] ?? '待分配');
            echo sprintf("  - 预计送达: %s\n", $data['estimated_delivery_time'] ?? '待确认');
        },
        function($error) {
            echo "❌ 电商订单创建失败: {$error}\n";
        }
    );

    echo "\n";

    // ==================== 场景2: 餐饮外卖配送 ====================
    echo "【场景2: 餐饮外卖配送】\n";
    echo "模拟外卖平台的即时配送流程\n\n";
    
    $foodOrderData = [
        'merchant_order_id' => 'FOOD_' . date('YmdHis') . '_002',
        'from_address' => [
            'detail' => '北京市朝阳区三里屯太古里美食广场麦当劳',
            'contact_name' => '店长',
            'contact_phone' => '010-85512345',
            'longitude' => 116.456123,
            'latitude' => 39.937890,
            'province' => '北京市',
            'city' => '北京市',
            'district' => '朝阳区'
        ],
        'to_address' => [
            'detail' => '北京市朝阳区工体北路8号三里屯SOHO 5号楼1201',
            'contact_name' => '李小姐',
            'contact_phone' => '13587654321',
            'longitude' => 116.454321,
            'latitude' => 39.934567,
            'province' => '北京市',
            'city' => '北京市',
            'district' => '朝阳区'
        ],
        'items' => [
            [
                'name' => '巨无霸套餐',
                'quantity' => 2,
                'price' => 45.00,
                'remark' => '不要洋葱'
            ],
            [
                'name' => '麦旋风奥利奥',
                'quantity' => 1,
                'price' => 18.00,
                'remark' => '常温'
            ],
            [
                'name' => '可乐(中杯)',
                'quantity' => 2,
                'price' => 12.00,
                'remark' => '少冰'
            ]
        ],
        'delivery_fee' => 6.00,
        'remark' => '外卖订单，45分钟内送达，保温配送',
        'expected_delivery_time' => (new DateTime('+45 minutes'))->format('Y-m-d H:i:s')
    ];
    
    echo "正在创建外卖订单...\n";
    $foodResult = $deliveryService->createOrder($foodOrderData);
    
    $foodResult->effect(
        function($data) {
            echo "✓ 外卖订单创建成功\n";
            echo sprintf("  - 订单号: %s\n", $data['order_id'] ?? 'N/A');
            echo sprintf("  - 预计送达: 45分钟内\n");
            echo sprintf("  - 配送类型: %s\n", Consts::EXPRESS_TYPE_INSTANT);
        },
        function($error) {
            echo "❌ 外卖订单创建失败: {$error}\n";
        }
    );

    echo "\n";

    // ==================== 场景3: 同城快递配送 ====================
    echo "【场景3: 同城快递配送】\n";
    echo "模拟同城快递的文件/小件配送\n\n";
    
    $expressOrderData = [
        'merchant_order_id' => 'EXPRESS_' . date('YmdHis') . '_003',
        'from_address' => [
            'detail' => '深圳市南山区科技园深南大道10000号腾讯大厦',
            'contact_name' => '王秘书',
            'contact_phone' => '0755-86013388',
            'longitude' => 113.960145,
            'latitude' => 22.539835,
            'province' => '广东省',
            'city' => '深圳市',
            'district' => '南山区'
        ],
        'to_address' => [
            'detail' => '深圳市福田区福田街道福华一路大中华国际交易广场',
            'contact_name' => '陈总',
            'contact_phone' => '13698765432',
            'longitude' => 114.061123,
            'latitude' => 22.540987,
            'province' => '广东省',
            'city' => '深圳市',
            'district' => '福田区'
        ],
        'items' => [
            [
                'name' => '合同文件',
                'quantity' => 1,
                'price' => 0.0,
                'weight' => 200,
                'size' => 'A4文件夹',
                'remark' => '重要文件，需当面签收'
            ],
            [
                'name' => '公章',
                'quantity' => 1,
                'price' => 0.0,
                'weight' => 100,
                'remark' => '贵重物品'
            ]
        ],
        'delivery_fee' => 25.00,
        'remark' => '同城快递，2小时内送达，需身份验证',
        'expected_delivery_time' => (new DateTime('+2 hours'))->format('Y-m-d H:i:s')
    ];
    
    echo "正在创建同城快递订单...\n";
    $expressResult = $deliveryService->createOrder($expressOrderData);
    
    $expressResult->effect(
        function($data) {
            echo "✓ 同城快递订单创建成功\n";
            echo sprintf("  - 订单号: %s\n", $data['order_id'] ?? 'N/A');
            echo sprintf("  - 服务类型: 同城即时达\n");
            echo sprintf("  - 预计送达: 2小时内\n");
        },
        function($error) {
            echo "❌ 同城快递订单创建失败: {$error}\n";
        }
    );

    echo "\n";

    // ==================== 场景4: 批量订单处理 ====================
    echo "【场景4: 批量订单处理】\n";
    echo "模拟电商平台每日批量订单的处理流程\n\n";
    
    // 模拟一批订单数据
    $batchOrders = [];
    $cities = [
        ['name' => '北京', 'lng' => 116.4074, 'lat' => 39.9042],
        ['name' => '上海', 'lng' => 121.4737, 'lat' => 31.2304],
        ['name' => '广州', 'lng' => 113.2644, 'lat' => 23.1291],
        ['name' => '深圳', 'lng' => 114.0579, 'lat' => 22.5431],
        ['name' => '杭州', 'lng' => 120.1551, 'lat' => 30.2741]
    ];
    
    for ($i = 1; $i <= 10; $i++) {
        $fromCity = $cities[array_rand($cities)];
        $toCity = $cities[array_rand($cities)];
        
        $batchOrders[] = [
            'merchant_order_id' => sprintf('BATCH_%s_%03d', date('YmdHis'), $i),
            'from_address' => [
                'detail' => sprintf('%s市配送中心%d号', $fromCity['name'], $i),
                'contact_name' => '配送中心',
                'contact_phone' => sprintf('400-000-%04d', 1000 + $i),
                'longitude' => $fromCity['lng'] + (rand(-100, 100) / 10000),
                'latitude' => $fromCity['lat'] + (rand(-100, 100) / 10000)
            ],
            'to_address' => [
                'detail' => sprintf('%s市某小区%d号楼', $toCity['name'], $i),
                'contact_name' => sprintf('客户%03d', $i),
                'contact_phone' => sprintf('139%08d', rand(10000000, 99999999)),
                'longitude' => $toCity['lng'] + (rand(-100, 100) / 10000),
                'latitude' => $toCity['lat'] + (rand(-100, 100) / 10000)
            ],
            'items' => [
                [
                    'name' => sprintf('商品%03d', $i),
                    'quantity' => rand(1, 3),
                    'price' => rand(1000, 50000) / 100,
                    'weight' => rand(100, 2000)
                ]
            ],
            'delivery_fee' => rand(800, 3000) / 100,
            'remark' => sprintf('批次订单 #%03d', $i)
        ];
    }
    
    echo sprintf("开始处理 %d 个批量订单...\n", count($batchOrders));
    
    $successCount = 0;
    $failCount = 0;
    $startTime = microtime(true);
    
    foreach ($batchOrders as $index => $orderData) {
        $result = $deliveryService->createOrder($orderData);
        
        if ($result->isSuccess()) {
            $successCount++;
            echo sprintf("  ✓ 订单 %02d 创建成功: %s\n", $index + 1, $orderData['merchant_order_id']);
        } else {
            $failCount++;
            echo sprintf("  ❌ 订单 %02d 创建失败: %s\n", $index + 1, $result->getError());
        }
        
        // 模拟处理间隔
        usleep(100000); // 0.1秒
    }
    
    $endTime = microtime(true);
    $processingTime = $endTime - $startTime;
    
    echo "\n批量订单处理完成:\n";
    echo sprintf("  - 成功: %d 个\n", $successCount);
    echo sprintf("  - 失败: %d 个\n", $failCount);
    echo sprintf("  - 成功率: %.1f%%\n", ($successCount / count($batchOrders)) * 100);
    echo sprintf("  - 处理时间: %.2f 秒\n", $processingTime);
    echo sprintf("  - 平均耗时: %.3f 秒/订单\n", $processingTime / count($batchOrders));

    echo "\n";

    // ==================== 场景5: 订单状态跟踪 ====================
    echo "【场景5: 订单状态跟踪】\n";
    echo "模拟订单全生命周期的状态跟踪\n\n";
    
    // 模拟订单状态变化
    $trackingOrderId = 'TRACKING_' . uniqid();
    $statusFlow = [
        ['status' => Consts::DELIVERY_STATUS_PENDING, 'desc' => '订单已创建，等待分配配送员'],
        ['status' => Consts::DELIVERY_STATUS_DELIVERING, 'desc' => '配送员已接单，正在配送途中'],
        ['status' => Consts::DELIVERY_STATUS_DELIVERED, 'desc' => '订单已送达，客户签收完成']
    ];
    
    echo sprintf("跟踪订单: %s\n", $trackingOrderId);
    
    foreach ($statusFlow as $index => $statusInfo) {
        echo sprintf("\n第 %d 阶段: %s\n", $index + 1, $statusInfo['desc']);
        
        // 模拟状态查询
        $statusResult = $deliveryService->getOrderStatus($trackingOrderId);
        
        $statusResult->effect(
            function($data) use ($statusInfo) {
                echo sprintf("  ✓ 状态更新成功\n");
                echo sprintf("  - 当前状态: %s\n", $this->getStatusDescription($statusInfo['status']));
                echo sprintf("  - 更新时间: %s\n", date('Y-m-d H:i:s'));
                
                if (isset($data['driver'])) {
                    echo sprintf("  - 配送员: %s (%s)\n", 
                        $data['driver']['name'] ?? '未分配', 
                        $data['driver']['phone'] ?? '无'
                    );
                }
                
                if (isset($data['location'])) {
                    echo sprintf("  - 当前位置: %.6f, %.6f\n", 
                        $data['location']['longitude'] ?? 0, 
                        $data['location']['latitude'] ?? 0
                    );
                }
            },
            function($error) {
                echo "  ❌ 状态查询失败: {$error}\n";
            }
        );
        
        // 模拟状态推进时间间隔
        if ($index < count($statusFlow) - 1) {
            echo "  等待状态变更...\n";
            sleep(1);
        }
    }

    echo "\n";

    // ==================== 场景6: 异常处理与订单取消 ====================
    echo "【场景6: 异常处理与订单取消】\n";
    echo "模拟订单异常情况的处理流程\n\n";
    
    $cancelOrderId = 'CANCEL_' . uniqid();
    $cancelReasons = [
        '客户主动取消订单',
        '商品缺货无法发货', 
        '配送地址超出服务范围',
        '恶劣天气无法配送',
        '收货人联系不上'
    ];
    
    $selectedReason = $cancelReasons[array_rand($cancelReasons)];
    
    echo sprintf("尝试取消订单: %s\n", $cancelOrderId);
    echo sprintf("取消原因: %s\n", $selectedReason);
    
    $cancelResult = $deliveryService->cancelOrder($cancelOrderId, $selectedReason);
    
    $cancelResult->effect(
        function($data) use ($selectedReason) {
            echo "✓ 订单取消成功\n";
            echo sprintf("  - 取消时间: %s\n", date('Y-m-d H:i:s'));
            echo sprintf("  - 取消原因: %s\n", $selectedReason);
            echo sprintf("  - 退款状态: %s\n", $data['refund_status'] ?? '处理中');
        },
        function($error) {
            echo "❌ 订单取消失败: {$error}\n";
            echo "  可能的原因:\n";
            echo "  - 订单已在配送途中\n";
            echo "  - 订单状态不允许取消\n";
            echo "  - 系统异常\n";
        }
    );

    echo "\n";

    // 清理资源
    $httpClient->close();
    
    echo "=== 业务场景示例完成 ===\n";
    echo "本示例展示了以下业务场景:\n";
    echo "1. 电商平台的商品配送（次日达、贵重物品配送）\n";
    echo "2. 餐饮外卖的即时配送（保温、限时配送）\n";
    echo "3. 同城快递的文件配送（身份验证、当面签收）\n";
    echo "4. 批量订单的高效处理（性能统计、成功率监控）\n";
    echo "5. 订单全生命周期跟踪（状态流转、位置更新）\n";
    echo "6. 异常情况处理（订单取消、退款处理）\n\n";
    echo "这些场景涵盖了配送平台的核心业务流程，\n";
    echo "为实际项目集成提供了完整的参考实现。\n";

} catch (Exception $e) {
    echo "\n❌ 业务场景示例执行失败\n";
    echo "错误信息: " . $e->getMessage() . "\n";
    echo "错误位置: " . $e->getFile() . ":" . $e->getLine() . "\n";
    
    if (Variables::isDebugging()) {
        echo "\n错误堆栈:\n";
        echo $e->getTraceAsString() . "\n";
    }
}

/**
 * 辅助方法：获取状态描述
 */
function getStatusDescription(int $status): string 
{
    $statusMap = [
        Consts::DELIVERY_STATUS_PENDING => '待分配',
        Consts::DELIVERY_STATUS_DELIVERING => '配送中',
        Consts::DELIVERY_STATUS_DELIVERED => '已送达',
        Consts::DELIVERY_STATUS_CANCELED => '已取消'
    ];
    
    return $statusMap[$status] ?? '未知状态';
}