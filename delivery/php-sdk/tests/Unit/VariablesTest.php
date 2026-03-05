<?php

namespace Tests\Unit;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\PhpSdk\Vars\Variables;

/**
 * 变量管理测试
 * 测试版本信息、调试开关等全局变量的管理
 */
class VariablesTest extends TestCase
{
    /**
     * 每个测试前清理环境
     */
    protected function setUp(): void
    {
        parent::setUp();
        Variables::reset();
    }

    /**
     * 每个测试后清理环境
     */
    protected function tearDown(): void
    {
        parent::tearDown();
        Variables::reset();
        putenv('DEBUG'); // 清除环境变量
    }

    /**
     * 测试版本号获取
     */
    public function testGetVersion()
    {
        $version = Variables::getVersion();
        
        $this->assertIsString($version);
        $this->assertNotEmpty($version);
        
        // 版本号应该符合语义版本格式 (major.minor.patch)
        $this->assertMatchesRegularExpression('/^\d+\.\d+\.\d+/', $version);
    }

    /**
     * 测试版本号设置
     */
    public function testSetVersion()
    {
        $testVersion = '2.0.0';
        Variables::setVersion($testVersion);
        
        $this->assertEquals($testVersion, Variables::getVersion());
    }

    /**
     * 测试调试模式 - 默认关闭
     */
    public function testIsDebuggingDefault()
    {
        // 默认应该关闭调试
        $this->assertFalse(Variables::isDebugging());
    }

    /**
     * 测试调试模式 - 环境变量开启
     */
    public function testIsDebuggingWithEnvTrue()
    {
        $trueValues = ['1', 'true', 'yes', 'on'];
        
        foreach ($trueValues as $value) {
            putenv("DEBUG={$value}");
            Variables::reset(); // 重置缓存
            
            $this->assertTrue(Variables::isDebugging(), "DEBUG={$value} 应该开启调试模式");
        }
    }

    /**
     * 测试调试模式 - 环境变量关闭
     */
    public function testIsDebuggingWithEnvFalse()
    {
        $falseValues = ['0', 'false', 'no', 'off', 'random'];
        
        foreach ($falseValues as $value) {
            putenv("DEBUG={$value}");
            Variables::reset(); // 重置缓存
            
            $this->assertFalse(Variables::isDebugging(), "DEBUG={$value} 应该关闭调试模式");
        }
    }

    /**
     * 测试调试模式 - 大小写不敏感
     */
    public function testIsDebuggingCaseInsensitive()
    {
        $testCases = [
            'TRUE' => true,
            'True' => true,
            'tRuE' => true,
            'FALSE' => false,
            'False' => false,
            'fAlSe' => false
        ];
        
        foreach ($testCases as $value => $expected) {
            putenv("DEBUG={$value}");
            Variables::reset(); // 重置缓存
            
            $this->assertEquals($expected, Variables::isDebugging(), 
                "DEBUG={$value} 应该" . ($expected ? '开启' : '关闭') . '调试模式');
        }
    }

    /**
     * 测试设置调试模式
     */
    public function testSetDebugging()
    {
        Variables::setDebugging(true);
        $this->assertTrue(Variables::isDebugging());
        
        Variables::setDebugging(false);
        $this->assertFalse(Variables::isDebugging());
    }

    /**
     * 测试用户代理字符串
     */
    public function testGetUserAgent()
    {
        $userAgent = Variables::getUserAgent();
        
        $this->assertIsString($userAgent);
        $this->assertNotEmpty($userAgent);
        
        // 应该包含SDK标识
        $this->assertStringContainsString('Maiyatian-Delivery-PHP-SDK', $userAgent);
        
        // 应该包含版本号
        $this->assertStringContainsString(Variables::getVersion(), $userAgent);
    }

    /**
     * 测试SDK类型
     */
    public function testGetSdkType()
    {
        $sdkType = Variables::getSdkType();
        
        $this->assertEquals('delivery', $sdkType);
    }

    /**
     * 测试支持的API版本列表
     */
    public function testGetSupportedApiVersions()
    {
        $versions = Variables::getSupportedApiVersions();
        
        $this->assertIsArray($versions);
        $this->assertNotEmpty($versions);
        $this->assertContains('v1', $versions);
    }

    /**
     * 测试API版本支持检查
     */
    public function testIsApiVersionSupported()
    {
        $this->assertTrue(Variables::isApiVersionSupported('v1'));
        $this->assertFalse(Variables::isApiVersionSupported('v999'));
        $this->assertFalse(Variables::isApiVersionSupported(''));
        $this->assertFalse(Variables::isApiVersionSupported('invalid'));
    }

    /**
     * 测试默认API版本
     */
    public function testGetDefaultApiVersion()
    {
        $defaultVersion = Variables::getDefaultApiVersion();
        
        $this->assertEquals('v1', $defaultVersion);
        $this->assertTrue(Variables::isApiVersionSupported($defaultVersion));
    }

    /**
     * 测试重置功能
     */
    public function testReset()
    {
        // 设置自定义值
        Variables::setVersion('9.9.9');
        Variables::setDebugging(true);
        
        // 验证设置生效
        $this->assertEquals('9.9.9', Variables::getVersion());
        $this->assertTrue(Variables::isDebugging());
        
        // 重置
        Variables::reset();
        
        // 验证重置到默认值
        $this->assertEquals('1.0.0', Variables::getVersion());
        $this->assertFalse(Variables::isDebugging());
    }

    /**
     * 测试缓存机制
     */
    public function testCachingMechanism()
    {
        // 首次调用
        putenv('DEBUG=true');
        $result1 = Variables::isDebugging();
        
        // 改变环境变量但不重置（应该使用缓存）
        putenv('DEBUG=false');
        $result2 = Variables::isDebugging();
        
        $this->assertEquals($result1, $result2, '缓存机制应该生效');
        
        // 重置后应该读取新的环境变量
        Variables::reset();
        $result3 = Variables::isDebugging();
        
        $this->assertNotEquals($result1, $result3, '重置后应该读取新的环境变量');
    }

    /**
     * 测试版本号来源 - composer.json
     */
    public function testVersionFromComposerJson()
    {
        // 模拟composer.json文件
        $composerPath = __DIR__ . '/../../composer.json';
        $composerDir = dirname($composerPath);
        
        // 如果composer.json不存在，创建一个测试用的
        if (!file_exists($composerPath)) {
            if (!is_dir($composerDir)) {
                mkdir($composerDir, 0755, true);
            }
            
            $composerContent = json_encode([
                'name' => 'maiyatian/delivery-php-sdk',
                'version' => '1.2.3',
                'description' => 'Test package'
            ], JSON_PRETTY_PRINT);
            
            file_put_contents($composerPath, $composerContent);
            
            // 清理版本缓存
            Variables::reset();
            
            // 应该读取composer.json中的版本
            $version = Variables::getVersion();
            $this->assertEquals('1.2.3', $version);
            
            // 清理测试文件
            unlink($composerPath);
            if (is_dir($composerDir) && count(scandir($composerDir)) == 2) {
                rmdir($composerDir);
            }
        }
    }

    /**
     * 测试环境变量边界情况
     */
    public function testEnvironmentVariableEdgeCases()
    {
        // 空字符串
        putenv('DEBUG=');
        Variables::reset();
        $this->assertFalse(Variables::isDebugging());
        
        // 只有空格
        putenv('DEBUG=   ');
        Variables::reset();
        $this->assertFalse(Variables::isDebugging());
        
        // 包含空格的true
        putenv('DEBUG=  true  ');
        Variables::reset();
        $this->assertTrue(Variables::isDebugging());
    }

    /**
     * 测试User Agent格式
     */
    public function testUserAgentFormat()
    {
        Variables::setVersion('2.1.0');
        
        $userAgent = Variables::getUserAgent();
        $expected = 'Maiyatian-Delivery-PHP-SDK/2.1.0';
        
        $this->assertEquals($expected, $userAgent);
    }

    /**
     * 测试API版本管理的一致性
     */
    public function testApiVersionConsistency()
    {
        $supportedVersions = Variables::getSupportedApiVersions();
        $defaultVersion = Variables::getDefaultApiVersion();
        
        // 默认版本应该在支持列表中
        $this->assertContains($defaultVersion, $supportedVersions);
        
        // 所有支持的版本都应该通过检查
        foreach ($supportedVersions as $version) {
            $this->assertTrue(Variables::isApiVersionSupported($version));
        }
    }
}