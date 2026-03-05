<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Consts\Consts;

/**
 * Consts 常量类单元测试
 * 验证所有常量定义的正确性
 */
class ConstsTest extends TestCase
{
    /**
     * 测试HTTP Content-Type常量
     */
    public function testContentTypeConstants()
    {
        $this->assertEquals('application/json', Consts::CONTENT_TYPE_JSON);
        $this->assertEquals('application/json; charset=utf-8', Consts::CONTENT_TYPE_JSON_UTF8);
    }
    
    /**
     * 测试HTTP状态码常量
     */
    public function testStatusCodeConstants()
    {
        $this->assertEquals(600, Consts::STATUS_EXCEPTION_SERVER_ERROR);
        $this->assertEquals(500, Consts::SERVER_ERROR_START);
    }
    
    /**
     * 测试API路径常量
     */
    public function testApiPathConstants()
    {
        $this->assertEquals('v1', Consts::API_VERSION);
        $this->assertEquals('v1/channel', Consts::CHANNEL_PATH_PREFIX);
    }
    
    /**
     * 测试业务状态码常量
     */
    public function testBusinessStatusConstants()
    {
        $this->assertEquals(200, Consts::SUCCESS_CODE);
    }
    
    /**
     * 测试环境URL常量
     */
    public function testEnvironmentUrlConstants()
    {
        $this->assertEquals('https://open-api-test.maiyatian.com', Consts::TEST_BASE_URL);
        $this->assertEquals('https://open-api.maiyatian.com', Consts::PROD_BASE_URL);
        
        // 验证URL格式正确性
        $this->assertStringStartsWith('https://', Consts::TEST_BASE_URL);
        $this->assertStringStartsWith('https://', Consts::PROD_BASE_URL);
        
        // 验证URL不以斜杠结尾
        $this->assertStringNotEndsWith('/', Consts::TEST_BASE_URL);
        $this->assertStringNotEndsWith('/', Consts::PROD_BASE_URL);
    }
    
    /**
     * 测试授权类型常量
     */
    public function testGrantTypeConstants()
    {
        $this->assertEquals('1', Consts::GRANT_TYPE_STORE);
        $this->assertEquals('2', Consts::GRANT_TYPE_MERCHANT);
        
        // 验证授权类型是字符串（与API规范一致）
        $this->assertIsString(Consts::GRANT_TYPE_STORE);
        $this->assertIsString(Consts::GRANT_TYPE_MERCHANT);
    }
    
    /**
     * 测试请求相关常量
     */
    public function testRequestConstants()
    {
        $this->assertEquals(60, Consts::DEFAULT_TIMEOUT);
        $this->assertEquals(30, Consts::DEFAULT_CONNECT_TIMEOUT);
        $this->assertEquals(3, Consts::DEFAULT_RETRY_ATTEMPTS);
        $this->assertEquals(1, Consts::DEFAULT_RETRY_BASE_DELAY);
        $this->assertEquals(30, Consts::DEFAULT_RETRY_MAX_DELAY);
        
        // 验证超时值的合理性
        $this->assertGreaterThan(0, Consts::DEFAULT_TIMEOUT);
        $this->assertGreaterThan(0, Consts::DEFAULT_CONNECT_TIMEOUT);
        $this->assertLessThanOrEqual(Consts::DEFAULT_TIMEOUT, Consts::DEFAULT_CONNECT_TIMEOUT);
        
        // 验证重试配置的合理性
        $this->assertGreaterThan(0, Consts::DEFAULT_RETRY_ATTEMPTS);
        $this->assertGreaterThan(0, Consts::DEFAULT_RETRY_BASE_DELAY);
        $this->assertGreaterThanOrEqual(Consts::DEFAULT_RETRY_BASE_DELAY, Consts::DEFAULT_RETRY_MAX_DELAY);
    }
    
    /**
     * 测试常量的不可变性
     */
    public function testConstantsAreImmutable()
    {
        // PHP常量定义后应该是不可变的
        $reflection = new \ReflectionClass(Consts::class);
        $constants = $reflection->getConstants();
        
        $this->assertNotEmpty($constants, '应该定义了常量');
        
        // 验证每个常量都有值
        foreach ($constants as $name => $value) {
            $this->assertNotNull($value, "常量 $name 不应该为null");
            
            // 验证字符串常量不为空
            if (is_string($value)) {
                $this->assertNotEmpty($value, "字符串常量 $name 不应该为空");
            }
        }
    }
    
    /**
     * 测试URL常量的有效性
     */
    public function testUrlValidation()
    {
        $urls = [
            Consts::TEST_BASE_URL,
            Consts::PROD_BASE_URL
        ];
        
        foreach ($urls as $url) {
            $this->assertTrue(filter_var($url, FILTER_VALIDATE_URL) !== false, "URL '$url' 应该是有效的");
            $this->assertStringStartsWith('https://', $url, "URL '$url' 应该使用HTTPS");
        }
    }
    
    /**
     * 测试API路径组合
     */
    public function testApiPathComposition()
    {
        // 测试路径前缀与版本的关系
        $this->assertStringStartsWith(Consts::API_VERSION, Consts::CHANNEL_PATH_PREFIX);
        
        // 测试完整API路径构建
        $basePath = Consts::CHANNEL_PATH_PREFIX;
        $endpoints = [
            'access_token',
            'refresh_token',
            'shop_info',
            'order_created',
            'order_confirmed',
            'order_done',
            'order_canceled',
            'order_apply_refund',
            'order_refunded',
            'order_modified',
            'order_remind',
            'self_delivery_change'
        ];
        
        foreach ($endpoints as $endpoint) {
            $fullPath = $basePath . '/' . $endpoint;
            $this->assertStringStartsWith('v1/', $fullPath, "API路径应该以版本号开头");
            $this->assertStringContains('channel', $fullPath, "渠道API路径应该包含'channel'");
        }
    }
    
    /**
     * 测试Content-Type的兼容性
     */
    public function testContentTypeCompatibility()
    {
        // 验证JSON Content-Type的格式
        $this->assertMatchesRegularExpression('/^application\/json(;.*)?$/', Consts::CONTENT_TYPE_JSON);
        $this->assertMatchesRegularExpression('/^application\/json(;.*)?$/', Consts::CONTENT_TYPE_JSON_UTF8);
        
        // 验证UTF-8编码声明
        $this->assertStringContains('charset=utf-8', Consts::CONTENT_TYPE_JSON_UTF8);
    }
    
    /**
     * 测试状态码范围的逻辑性
     */
    public function testStatusCodeLogic()
    {
        // 验证状态码范围的逻辑关系
        $this->assertEquals(200, Consts::SUCCESS_CODE);
        $this->assertGreaterThan(Consts::SUCCESS_CODE, Consts::SERVER_ERROR_START);
        $this->assertGreaterThan(Consts::SERVER_ERROR_START, Consts::STATUS_EXCEPTION_SERVER_ERROR);
        
        // 验证HTTP状态码标准
        $this->assertGreaterThanOrEqual(100, Consts::SUCCESS_CODE);
        $this->assertLessThan(600, Consts::SUCCESS_CODE);
        $this->assertEquals(500, Consts::SERVER_ERROR_START); // 标准HTTP 5xx错误起始
    }
    
    /**
     * 测试数值常量的数据类型
     */
    public function testNumericConstantTypes()
    {
        $numericConstants = [
            Consts::SUCCESS_CODE,
            Consts::SERVER_ERROR_START,
            Consts::STATUS_EXCEPTION_SERVER_ERROR,
            Consts::DEFAULT_TIMEOUT,
            Consts::DEFAULT_CONNECT_TIMEOUT,
            Consts::DEFAULT_RETRY_ATTEMPTS,
            Consts::DEFAULT_RETRY_BASE_DELAY,
            Consts::DEFAULT_RETRY_MAX_DELAY
        ];
        
        foreach ($numericConstants as $constant) {
            $this->assertIsInt($constant, '数值常量应该是整数类型');
            $this->assertGreaterThan(0, $constant, '数值常量应该大于0');
        }
    }
    
    /**
     * 测试字符串常量的格式
     */
    public function testStringConstantFormats()
    {
        // 测试授权类型格式
        $this->assertMatchesRegularExpression('/^\d+$/', Consts::GRANT_TYPE_STORE);
        $this->assertMatchesRegularExpression('/^\d+$/', Consts::GRANT_TYPE_MERCHANT);
        
        // 测试API版本格式
        $this->assertMatchesRegularExpression('/^v\d+$/', Consts::API_VERSION);
        
        // 测试路径前缀格式
        $this->assertStringNotStartsWith('/', Consts::CHANNEL_PATH_PREFIX);
        $this->assertStringNotEndsWith('/', Consts::CHANNEL_PATH_PREFIX);
    }
}