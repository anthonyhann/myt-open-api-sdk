<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

/**
 * Variables 类单元测试
 * 测试全局变量管理功能
 */
class VariablesTest extends TestCase
{
    /**
     * 每个测试前重置变量状态
     */
    protected function setUp(): void
    {
        Variables::reset();
    }
    
    /**
     * 每个测试后清理环境变量
     */
    protected function tearDown(): void
    {
        putenv('DEBUG');
        Variables::reset();
    }
    
    /**
     * 测试默认版本号
     */
    public function testDefaultVersion()
    {
        $version = Variables::getVersion();
        $this->assertEquals('1.0.0', $version);
    }
    
    /**
     * 测试设置版本号
     */
    public function testSetVersion()
    {
        Variables::setVersion('2.0.0');
        $this->assertEquals('2.0.0', Variables::getVersion());
    }
    
    /**
     * 测试默认调试模式（关闭）
     */
    public function testDefaultDebugging()
    {
        $this->assertFalse(Variables::isDebugging());
    }
    
    /**
     * 测试通过环境变量开启调试模式
     */
    public function testEnableDebuggingViaEnvironment()
    {
        // 测试各种真值
        $trueValues = ['1', 'true', 'True', 'TRUE', 'yes', 'YES', 'on', 'ON'];
        
        foreach ($trueValues as $value) {
            Variables::reset();
            putenv("DEBUG=$value");
            $this->assertTrue(Variables::isDebugging(), "值 '$value' 应该启用调试模式");
        }
    }
    
    /**
     * 测试通过环境变量关闭调试模式
     */
    public function testDisableDebuggingViaEnvironment()
    {
        // 测试各种假值
        $falseValues = ['0', 'false', 'False', 'FALSE', 'no', 'NO', 'off', 'OFF', 'random'];
        
        foreach ($falseValues as $value) {
            Variables::reset();
            putenv("DEBUG=$value");
            $this->assertFalse(Variables::isDebugging(), "值 '$value' 应该关闭调试模式");
        }
    }
    
    /**
     * 测试环境变量不存在时的调试模式
     */
    public function testDebuggingWithoutEnvironmentVariable()
    {
        putenv('DEBUG'); // 删除环境变量
        Variables::reset();
        $this->assertFalse(Variables::isDebugging());
    }
    
    /**
     * 测试手动设置调试模式
     */
    public function testSetDebugging()
    {
        Variables::setDebugging(true);
        $this->assertTrue(Variables::isDebugging());
        
        Variables::setDebugging(false);
        $this->assertFalse(Variables::isDebugging());
    }
    
    /**
     * 测试手动设置覆盖环境变量
     */
    public function testSetDebuggingOverridesEnvironment()
    {
        putenv('DEBUG=true');
        Variables::setDebugging(false);
        $this->assertFalse(Variables::isDebugging());
        
        putenv('DEBUG=false');
        Variables::setDebugging(true);
        $this->assertTrue(Variables::isDebugging());
    }
    
    /**
     * 测试用户代理字符串生成
     */
    public function testGetUserAgent()
    {
        $userAgent = Variables::getUserAgent();
        $this->assertStringStartsWith('Maiyatian-PHP-SDK/', $userAgent);
        $this->assertStringContains(Variables::getVersion(), $userAgent);
    }
    
    /**
     * 测试用户代理字符串随版本变化
     */
    public function testUserAgentChangesWithVersion()
    {
        $originalUserAgent = Variables::getUserAgent();
        
        Variables::setVersion('3.0.0');
        $newUserAgent = Variables::getUserAgent();
        
        $this->assertNotEquals($originalUserAgent, $newUserAgent);
        $this->assertStringContains('3.0.0', $newUserAgent);
    }
    
    /**
     * 测试从composer.json读取版本号（模拟）
     */
    public function testVersionFromComposerJson()
    {
        // 这个测试需要实际的composer.json文件，这里只测试默认行为
        $version = Variables::getVersion();
        $this->assertIsString($version);
        $this->assertNotEmpty($version);
    }
    
    /**
     * 测试reset方法
     */
    public function testReset()
    {
        // 修改状态
        Variables::setVersion('9.9.9');
        Variables::setDebugging(true);
        
        // 验证修改生效
        $this->assertEquals('9.9.9', Variables::getVersion());
        $this->assertTrue(Variables::isDebugging());
        
        // 重置
        Variables::reset();
        
        // 验证恢复默认值
        $this->assertEquals('1.0.0', Variables::getVersion());
        $this->assertFalse(Variables::isDebugging());
    }
    
    /**
     * 测试调试模式的缓存行为
     */
    public function testDebuggingCaching()
    {
        // 首次调用，从环境变量读取
        putenv('DEBUG=true');
        Variables::reset();
        $this->assertTrue(Variables::isDebugging());
        
        // 改变环境变量，但应该使用缓存值
        putenv('DEBUG=false');
        $this->assertTrue(Variables::isDebugging(), '应该使用缓存的调试状态');
        
        // 重置后重新读取
        Variables::reset();
        $this->assertFalse(Variables::isDebugging(), '重置后应该读取新的环境变量值');
    }
    
    /**
     * 测试边界情况
     */
    public function testEdgeCases()
    {
        // 空字符串版本号
        Variables::setVersion('');
        $this->assertEquals('', Variables::getVersion());
        
        // 空字符串环境变量
        putenv('DEBUG=');
        Variables::reset();
        $this->assertFalse(Variables::isDebugging());
        
        // 含空格的环境变量
        putenv('DEBUG= true ');
        Variables::reset();
        $this->assertFalse(Variables::isDebugging(), '含空格的值应该被识别为false');
    }
    
    /**
     * 测试大小写不敏感的环境变量解析
     */
    public function testCaseInsensitiveEnvironmentParsing()
    {
        $testCases = [
            'True' => true,
            'TRUE' => true,
            'tRuE' => true,
            'False' => false,
            'FALSE' => false,
            'fAlSe' => false,
            'YES' => true,
            'yes' => true,
            'No' => false,
            'NO' => false
        ];
        
        foreach ($testCases as $envValue => $expected) {
            Variables::reset();
            putenv("DEBUG=$envValue");
            $actual = Variables::isDebugging();
            $this->assertEquals($expected, $actual, "环境变量值 '$envValue' 的解析结果不正确");
        }
    }
}