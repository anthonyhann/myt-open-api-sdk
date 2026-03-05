<?php

namespace Tests\Unit;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\PhpSdk\Client\Config;
use Maiyatian\Delivery\PhpSdk\Client\ConfigBuilder;

/**
 * 配置管理测试
 * 测试HTTP客户端配置的创建、验证和构建器模式
 */
class ConfigTest extends TestCase
{
    /**
     * 测试默认配置创建
     */
    public function testNewDefaultConfig()
    {
        $config = Config::newDefaultConfig();
        
        $this->assertInstanceOf(Config::class, $config);
        $this->assertNotEmpty($config->baseURL);
        $this->assertEquals('1.0.0', $config->sdkVersion);
        $this->assertEquals(50, $config->maxConnections);
        $this->assertEquals(60, $config->requestTimeout);
        $this->assertTrue($config->enableLogging);
    }

    /**
     * 测试配置验证 - 成功案例
     */
    public function testValidateSuccess()
    {
        $config = new Config();
        $config->baseURL = 'https://api.example.com';
        $config->apiKey = 'test-key';
        $config->apiSecret = 'test-secret';
        $config->maxConnections = 10;
        $config->requestTimeout = 30;
        $config->retryMaxAttempts = 3;
        
        $this->assertTrue($config->validate());
    }

    /**
     * 测试配置验证 - baseURL为空
     */
    public function testValidateEmptyBaseURL()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('baseURL cannot be empty');
        
        $config = new Config();
        $config->baseURL = '';
        $config->apiKey = 'test-key';
        $config->apiSecret = 'test-secret';
        
        $config->validate();
    }

    /**
     * 测试配置验证 - apiKey为空
     */
    public function testValidateEmptyApiKey()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('apiKey cannot be empty');
        
        $config = new Config();
        $config->baseURL = 'https://api.example.com';
        $config->apiKey = '';
        $config->apiSecret = 'test-secret';
        
        $config->validate();
    }

    /**
     * 测试配置验证 - apiSecret为空
     */
    public function testValidateEmptyApiSecret()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('apiSecret cannot be empty');
        
        $config = new Config();
        $config->baseURL = 'https://api.example.com';
        $config->apiKey = 'test-key';
        $config->apiSecret = '';
        
        $config->validate();
    }

    /**
     * 测试配置验证 - maxConnections无效
     */
    public function testValidateInvalidMaxConnections()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('maxConnections must be positive');
        
        $config = new Config();
        $config->baseURL = 'https://api.example.com';
        $config->apiKey = 'test-key';
        $config->apiSecret = 'test-secret';
        $config->maxConnections = -1;
        
        $config->validate();
    }

    /**
     * 测试配置验证 - requestTimeout无效
     */
    public function testValidateInvalidRequestTimeout()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('requestTimeout must be positive');
        
        $config = new Config();
        $config->baseURL = 'https://api.example.com';
        $config->apiKey = 'test-key';
        $config->apiSecret = 'test-secret';
        $config->requestTimeout = 0;
        
        $config->validate();
    }

    /**
     * 测试配置验证 - retryMaxAttempts负数
     */
    public function testValidateNegativeRetryMaxAttempts()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('retryMaxAttempts cannot be negative');
        
        $config = new Config();
        $config->baseURL = 'https://api.example.com';
        $config->apiKey = 'test-key';
        $config->apiSecret = 'test-secret';
        $config->retryMaxAttempts = -1;
        
        $config->validate();
    }

    /**
     * 测试ConfigBuilder基本构建
     */
    public function testConfigBuilderBasicBuild()
    {
        $config = (new ConfigBuilder())
            ->baseURL('https://test-api.example.com')
            ->apiKey('builder-key')
            ->apiSecret('builder-secret')
            ->build();
        
        $this->assertInstanceOf(Config::class, $config);
        $this->assertEquals('https://test-api.example.com', $config->baseURL);
        $this->assertEquals('builder-key', $config->apiKey);
        $this->assertEquals('builder-secret', $config->apiSecret);
    }

    /**
     * 测试ConfigBuilder链式调用
     */
    public function testConfigBuilderChaining()
    {
        $config = (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiKey('test-key')
            ->apiSecret('test-secret')
            ->maxConnections(20)
            ->requestTimeout(45)
            ->retryMaxAttempts(5)
            ->enableLogging(false)
            ->build();
        
        $this->assertEquals('https://api.example.com', $config->baseURL);
        $this->assertEquals('test-key', $config->apiKey);
        $this->assertEquals('test-secret', $config->apiSecret);
        $this->assertEquals(20, $config->maxConnections);
        $this->assertEquals(45, $config->requestTimeout);
        $this->assertEquals(5, $config->retryMaxAttempts);
        $this->assertFalse($config->enableLogging);
    }

    /**
     * 测试ConfigBuilder缺少必填字段
     */
    public function testConfigBuilderMissingBaseURL()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('baseURL is required');
        
        (new ConfigBuilder())
            ->apiKey('test-key')
            ->apiSecret('test-secret')
            ->build();
    }

    /**
     * 测试ConfigBuilder缺少apiKey
     */
    public function testConfigBuilderMissingApiKey()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('apiKey is required');
        
        (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiSecret('test-secret')
            ->build();
    }

    /**
     * 测试ConfigBuilder缺少apiSecret
     */
    public function testConfigBuilderMissingApiSecret()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('apiSecret is required');
        
        (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiKey('test-key')
            ->build();
    }

    /**
     * 测试ConfigBuilder默认值
     */
    public function testConfigBuilderDefaults()
    {
        $config = (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiKey('test-key')
            ->apiSecret('test-secret')
            ->build();
        
        // 验证默认值
        $this->assertEquals('https://api.maiyatian.com', $config->baseURL); // 构建器会使用默认配置的值
        $this->assertEquals(50, $config->maxConnections);
        $this->assertEquals(60, $config->requestTimeout);
        $this->assertEquals(3, $config->retryMaxAttempts);
        $this->assertTrue($config->enableLogging);
    }

    /**
     * 测试ConfigBuilder Builder模式特性
     */
    public function testConfigBuilderPatternCharacteristics()
    {
        $builder = new ConfigBuilder();
        
        // 每个方法都应该返回builder实例（链式调用）
        $this->assertInstanceOf(ConfigBuilder::class, $builder->baseURL('https://api.example.com'));
        $this->assertInstanceOf(ConfigBuilder::class, $builder->apiKey('test-key'));
        $this->assertInstanceOf(ConfigBuilder::class, $builder->apiSecret('test-secret'));
        $this->assertInstanceOf(ConfigBuilder::class, $builder->maxConnections(10));
        $this->assertInstanceOf(ConfigBuilder::class, $builder->requestTimeout(30));
        $this->assertInstanceOf(ConfigBuilder::class, $builder->retryMaxAttempts(2));
        $this->assertInstanceOf(ConfigBuilder::class, $builder->enableLogging(false));
        
        // build方法应该返回Config实例
        $config = $builder->build();
        $this->assertInstanceOf(Config::class, $config);
    }

    /**
     * 测试配置属性的数据类型
     */
    public function testConfigPropertyTypes()
    {
        $config = (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiKey('test-key')
            ->apiSecret('test-secret')
            ->maxConnections(25)
            ->requestTimeout(120)
            ->retryMaxAttempts(4)
            ->enableLogging(true)
            ->build();
        
        $this->assertIsString($config->baseURL);
        $this->assertIsString($config->apiKey);
        $this->assertIsString($config->apiSecret);
        $this->assertIsInt($config->maxConnections);
        $this->assertIsInt($config->requestTimeout);
        $this->assertIsInt($config->retryMaxAttempts);
        $this->assertIsBool($config->enableLogging);
    }

    /**
     * 测试配置值的合理性范围
     */
    public function testConfigValueRanges()
    {
        $config = (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiKey('test-key')
            ->apiSecret('test-secret')
            ->maxConnections(100)
            ->requestTimeout(300)
            ->retryMaxAttempts(10)
            ->build();
        
        // 测试数值在合理范围内
        $this->assertGreaterThan(0, $config->maxConnections);
        $this->assertLessThanOrEqual(1000, $config->maxConnections);
        
        $this->assertGreaterThan(0, $config->requestTimeout);
        $this->assertLessThanOrEqual(600, $config->requestTimeout); // 10分钟
        
        $this->assertGreaterThanOrEqual(0, $config->retryMaxAttempts);
        $this->assertLessThanOrEqual(10, $config->retryMaxAttempts);
    }

    /**
     * 测试URL格式验证
     */
    public function testURLFormatValidation()
    {
        $validUrls = [
            'https://api.example.com',
            'http://localhost:8080',
            'https://subdomain.example.co.uk/path',
        ];
        
        foreach ($validUrls as $url) {
            $config = (new ConfigBuilder())
                ->baseURL($url)
                ->apiKey('test-key')
                ->apiSecret('test-secret')
                ->build();
            
            $this->assertStringStartsWith('http', $config->baseURL);
        }
    }

    /**
     * 测试配置对象的可变性
     */
    public function testConfigMutability()
    {
        $config = (new ConfigBuilder())
            ->baseURL('https://api.example.com')
            ->apiKey('test-key')
            ->apiSecret('test-secret')
            ->build();
        
        $originalTimeout = $config->requestTimeout;
        
        // 配置对象应该是可变的
        $config->requestTimeout = 90;
        $this->assertEquals(90, $config->requestTimeout);
        $this->assertNotEquals($originalTimeout, $config->requestTimeout);
    }

    /**
     * 测试多个配置实例的独立性
     */
    public function testMultipleConfigIndependence()
    {
        $config1 = (new ConfigBuilder())
            ->baseURL('https://api1.example.com')
            ->apiKey('key1')
            ->apiSecret('secret1')
            ->maxConnections(10)
            ->build();
        
        $config2 = (new ConfigBuilder())
            ->baseURL('https://api2.example.com')
            ->apiKey('key2')
            ->apiSecret('secret2')
            ->maxConnections(20)
            ->build();
        
        // 两个配置实例应该是独立的
        $this->assertNotEquals($config1->baseURL, $config2->baseURL);
        $this->assertNotEquals($config1->apiKey, $config2->apiKey);
        $this->assertNotEquals($config1->maxConnections, $config2->maxConnections);
        
        // 修改一个不应该影响另一个
        $config1->requestTimeout = 999;
        $this->assertNotEquals($config1->requestTimeout, $config2->requestTimeout);
    }
}