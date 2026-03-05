<?php

/**
 * 简单的类加载测试
 */

require_once __DIR__ . '/tests/bootstrap.php';

echo "测试类加载...\n\n";

// 测试基础类
$classes = [
    'Maiyatian\\Channel\\PhpSdk\\Consts\\Consts',
    'Maiyatian\\Channel\\PhpSdk\\Vars\\Variables', 
    'Maiyatian\\Channel\\PhpSdk\\Client\\ApiResponse',
    'Maiyatian\\Channel\\PhpSdk\\ChannelSDK'
];

foreach ($classes as $class) {
    echo "检查类: $class ... ";
    if (class_exists($class)) {
        echo "✅ 存在\n";
    } else {
        echo "❌ 不存在\n";
        
        // 调试：尝试手动加载
        $file = '';
        if ($class === 'Maiyatian\\Channel\\PhpSdk\\Consts\\Consts') {
            $file = __DIR__ . '/consts/Consts.php';
        } elseif ($class === 'Maiyatian\\Channel\\PhpSdk\\Vars\\Variables') {
            $file = __DIR__ . '/vars/Variables.php';
        } elseif ($class === 'Maiyatian\\Channel\\PhpSdk\\Client\\ApiResponse') {
            $file = __DIR__ . '/client/ApiResponse.php';
        } elseif ($class === 'Maiyatian\\Channel\\PhpSdk\\ChannelSDK') {
            $file = __DIR__ . '/ChannelSDK.php';
        }
        
        echo "  尝试加载文件: $file ... ";
        if (file_exists($file)) {
            echo "文件存在\n";
            require_once $file;
            echo "  重新检查类: ";
            if (class_exists($class)) {
                echo "✅ 现在存在\n";
            } else {
                echo "❌ 仍然不存在\n";
            }
        } else {
            echo "文件不存在\n";
        }
    }
}

echo "\n测试基础功能...\n";

// 测试 Variables 类
try {
    $version = \Maiyatian\Channel\PhpSdk\Vars\Variables::getVersion();
    echo "✅ Variables::getVersion() = $version\n";
} catch (Exception $e) {
    echo "❌ Variables::getVersion() 失败: " . $e->getMessage() . "\n";
}

// 测试 Consts 类
try {
    $successCode = \Maiyatian\Channel\PhpSdk\Consts\Consts::SUCCESS_CODE;
    echo "✅ Consts::SUCCESS_CODE = $successCode\n";
} catch (Exception $e) {
    echo "❌ Consts::SUCCESS_CODE 失败: " . $e->getMessage() . "\n";
}

// 测试 ApiResponse 类
try {
    $response = \Maiyatian\Channel\PhpSdk\Client\ApiResponse::success(['test' => 'data']);
    echo "✅ ApiResponse::success() 工作正常\n";
    echo "  isSuccess: " . ($response->isSuccess() ? 'true' : 'false') . "\n";
    echo "  code: " . $response->getCode() . "\n";
} catch (Exception $e) {
    echo "❌ ApiResponse::success() 失败: " . $e->getMessage() . "\n";
}

echo "\n测试完成。\n";