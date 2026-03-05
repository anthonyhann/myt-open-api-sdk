<?php

/**
 * 简单的测试运行器
 * 由于可能没有安装PHPUnit，我们创建一个简单的测试运行器
 */

require_once __DIR__ . '/tests/bootstrap.php';

// 测试类列表
$testClasses = [
    'Maiyatian\Channel\PhpSdk\Tests\ConstsTest',
    'Maiyatian\Channel\PhpSdk\Tests\VariablesTest',
    'Maiyatian\Channel\PhpSdk\Tests\ApiResponseTest',
    'Maiyatian\Channel\PhpSdk\Tests\ChannelSDKTest'
];

$totalTests = 0;
$passedTests = 0;
$failedTests = 0;

echo "开始运行 Channel PHP SDK 测试套件...\n\n";

foreach ($testClasses as $testClass) {
    echo "运行测试类: $testClass\n";
    echo str_repeat('-', 50) . "\n";
    
    try {
        if (!class_exists($testClass)) {
            echo "❌ 测试类不存在\n\n";
            $failedTests++;
            continue;
        }
        
        $reflection = new ReflectionClass($testClass);
        $methods = $reflection->getMethods(ReflectionMethod::IS_PUBLIC);
        
        $instance = $reflection->newInstance();
        
        foreach ($methods as $method) {
            $methodName = $method->getName();
            
            // 只运行以test开头的方法
            if (strpos($methodName, 'test') !== 0) {
                continue;
            }
            
            $totalTests++;
            
            try {
                // 运行setUp方法（如果存在）
                if ($reflection->hasMethod('setUp')) {
                    $instance->setUp();
                }
                
                // 运行测试方法
                $method->invoke($instance);
                
                echo "✅ $methodName - 通过\n";
                $passedTests++;
                
                // 运行tearDown方法（如果存在）
                if ($reflection->hasMethod('tearDown')) {
                    $instance->tearDown();
                }
                
            } catch (Exception $e) {
                echo "❌ $methodName - 失败: " . $e->getMessage() . "\n";
                $failedTests++;
            }
        }
        
    } catch (Exception $e) {
        echo "❌ 测试类初始化失败: " . $e->getMessage() . "\n";
        $failedTests++;
    }
    
    echo "\n";
}

echo str_repeat('=', 60) . "\n";
echo "测试结果统计:\n";
echo "总测试数: $totalTests\n";
echo "通过: $passedTests\n";
echo "失败: $failedTests\n";
echo "成功率: " . ($totalTests > 0 ? round(($passedTests / $totalTests) * 100, 2) : 0) . "%\n";

if ($failedTests === 0) {
    echo "\n🎉 所有测试通过！\n";
    exit(0);
} else {
    echo "\n⚠️ 有测试失败，请检查代码\n";
    exit(1);
}