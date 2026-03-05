<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\ChannelSDK;
use Maiyatian\Channel\PhpSdk\Client\HTTPClientConfig;
use Maiyatian\Channel\PhpSdk\Client\ConfigValidationError;
use Maiyatian\Channel\PhpSdk\Consts\Consts;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

/**
 * ChannelSDK 主入口类单元测试
 */
class ChannelSDKTest extends TestCase
{
    private $testApiKey = 'test-api-key';
    private $testApiSecret = 'test-api-secret';
    
    protected function setUp(): void
    {
        Variables::reset();
    }
    
    protected function tearDown(): void
    {
        // 清理环境变量
        putenv('MAIYATIAN_APP_KEY');
        putenv('MAIYATIAN_APP_SECRET');
        putenv('MAIYATIAN_API_BASE_URL');
        Variables::reset();
    }
    
    /**
     * 测试使用默认配置创建SDK
     */
    public function testNewDefault()
    {
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret);
        
        $this->assertInstanceOf(ChannelSDK::class, $sdk);
        
        $config = $sdk->getConfig();
        $this->assertEquals($this->testApiKey, $config->APIKey);
        $this->assertEquals($this->testApiSecret, $config->APISecret);
        $this->assertEquals(Consts::PROD_BASE_URL, $config->BaseURL);
        
        $sdk->close();
    }
    
    /**
     * 测试使用自定义URL创建SDK
     */
    public function testNewDefaultWithCustomUrl()
    {
        $customUrl = 'https://custom-api.example.com';
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret, $customUrl);
        
        $config = $sdk->getConfig();
        $this->assertEquals($customUrl, $config->BaseURL);
        
        $sdk->close();
    }
    
    /**
     * 测试创建测试环境SDK
     */
    public function testNewForTest()
    {
        $sdk = ChannelSDK::newForTest($this->testApiKey, $this->testApiSecret);
        
        $config = $sdk->getConfig();
        $this->assertEquals($this->testApiKey, $config->APIKey);
        $this->assertEquals($this->testApiSecret, $config->APISecret);
        $this->assertEquals(Consts::TEST_BASE_URL, $config->BaseURL);
        $this->assertTrue($config->EnableLogging);
        
        $sdk->close();
    }
    
    /**
     * 测试从环境变量创建SDK
     */
    public function testNewFromEnv()
    {
        putenv("MAIYATIAN_APP_KEY={$this->testApiKey}");
        putenv("MAIYATIAN_APP_SECRET={$this->testApiSecret}");
        putenv('MAIYATIAN_API_BASE_URL=https://env-api.example.com');
        
        $sdk = ChannelSDK::newFromEnv();
        
        $config = $sdk->getConfig();
        $this->assertEquals($this->testApiKey, $config->APIKey);
        $this->assertEquals($this->testApiSecret, $config->APISecret);
        $this->assertEquals('https://env-api.example.com', $config->BaseURL);
        
        $sdk->close();
    }
    
    /**
     * 测试从环境变量创建SDK时缺少必要变量
     */
    public function testNewFromEnvMissingKey()
    {
        putenv('MAIYATIAN_APP_KEY'); // 删除变量
        putenv("MAIYATIAN_APP_SECRET={$this->testApiSecret}");
        
        $this->expectException(\Exception::class);
        $this->expectExceptionMessage('环境变量 MAIYATIAN_APP_KEY 未设置');
        
        ChannelSDK::newFromEnv();
    }
    
    /**
     * 测试从环境变量创建SDK时缺少密钥
     */
    public function testNewFromEnvMissingSecret()
    {
        putenv("MAIYATIAN_APP_KEY={$this->testApiKey}");
        putenv('MAIYATIAN_APP_SECRET'); // 删除变量
        
        $this->expectException(\Exception::class);
        $this->expectExceptionMessage('环境变量 MAIYATIAN_APP_SECRET 未设置');
        
        ChannelSDK::newFromEnv();
    }
    
    /**
     * 测试使用构建器模式创建SDK
     */
    public function testNewBuilder()
    {
        $builder = ChannelSDK::newBuilder();
        $this->assertInstanceOf(\Maiyatian\Channel\PhpSdk\Client\ConfigBuilder::class, $builder);
        
        // 测试链式调用
        $sdk = $builder
            ->APIKey($this->testApiKey)
            ->APISecret($this->testApiSecret)
            ->BaseURL('https://builder-api.example.com')
            ->RequestTimeout(45)
            ->RetryMaxAttempts(5)
            ->EnableLogging(true)
            ->build();
        
        $this->assertInstanceOf(ChannelSDK::class, $sdk);
        
        $config = $sdk->getConfig();
        $this->assertEquals($this->testApiKey, $config->APIKey);
        $this->assertEquals($this->testApiSecret, $config->APISecret);
        $this->assertEquals('https://builder-api.example.com', $config->BaseURL);
        $this->assertEquals(45, $config->RequestTimeout);
        $this->assertEquals(5, $config->RetryMaxAttempts);
        $this->assertTrue($config->EnableLogging);
        
        $sdk->close();
    }
    
    /**
     * 测试SDK工具方法
     */
    public function testUtilityMethods()
    {
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret);
        
        // 测试版本获取
        $version = $sdk->getVersion();
        $this->assertIsString($version);
        $this->assertNotEmpty($version);
        
        // 测试调试状态
        $isDebugging = $sdk->isDebugging();
        $this->assertIsBool($isDebugging);
        
        // 测试配置获取
        $config = $sdk->getConfig();
        $this->assertInstanceOf(HTTPClientConfig::class, $config);
        
        // 测试客户端获取
        $client = $sdk->getClient();
        $this->assertInstanceOf(\Maiyatian\Channel\PhpSdk\Client\HTTPClientManager::class, $client);
        
        $sdk->close();
    }
    
    /**
     * 测试业务接口方法的存在性
     */
    public function testBusinessMethods()
    {
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret);
        
        // 验证所有业务方法都存在
        $businessMethods = [
            'getAccessToken',
            'refreshToken',
            'getShopInfo',
            'orderCreated',
            'orderConfirmed',
            'orderDone',
            'orderCanceled',
            'orderApplyRefund',
            'orderRefunded',
            'orderModified',
            'orderRemind',
            'selfDeliveryChange'
        ];
        
        foreach ($businessMethods as $method) {
            $this->assertTrue(method_exists($sdk, $method), "业务方法 $method 应该存在");
        }
        
        $sdk->close();
    }
    
    /**
     * 测试通用API调用方法的存在性
     */
    public function testApiCallMethods()
    {
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret);
        
        // 验证所有API调用方法都存在
        $apiMethods = [
            'request',
            'get',
            'post',
            'requestWithRetry',
            'batchRequest'
        ];
        
        foreach ($apiMethods as $method) {
            $this->assertTrue(method_exists($sdk, $method), "API方法 $method 应该存在");
        }
        
        $sdk->close();
    }
    
    /**
     * 测试配置验证
     */
    public function testConfigValidation()
    {
        // 测试空API Key
        $this->expectException(ConfigValidationError::class);
        $this->expectExceptionMessage('APIKey');
        
        $config = HTTPClientConfig::NewDefaultConfig();
        $config->APIKey = '';
        $config->APISecret = $this->testApiSecret;
        
        new ChannelSDK($config);
    }
    
    /**
     * 测试资源清理
     */
    public function testResourceCleanup()
    {
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret);
        
        // 手动关闭
        $sdk->close();
        
        // 多次关闭应该安全
        $sdk->close();
        
        // 析构函数测试会在对象销毁时自动执行
        $this->assertTrue(true); // 如果没有异常则测试通过
    }
    
    /**
     * 测试调试模式对测试环境SDK的影响
     */
    public function testDebuggingInfluenceOnTestSdk()
    {
        // 开启调试模式
        Variables::setDebugging(true);
        
        $sdk = ChannelSDK::newForTest($this->testApiKey, $this->testApiSecret);
        $config = $sdk->getConfig();
        
        $this->assertTrue($config->LogRequestBody, '调试模式下测试环境应该记录请求体');
        $this->assertTrue($config->LogResponseBody, '调试模式下测试环境应该记录响应体');
        
        $sdk->close();
        
        // 关闭调试模式
        Variables::setDebugging(false);
        
        $sdk2 = ChannelSDK::newForTest($this->testApiKey, $this->testApiSecret);
        $config2 = $sdk2->getConfig();
        
        $this->assertFalse($config2->LogRequestBody, '非调试模式下测试环境不应该记录请求体');
        $this->assertFalse($config2->LogResponseBody, '非调试模式下测试环境不应该记录响应体');
        
        $sdk2->close();
    }
    
    /**
     * 测试SDK与Variables的集成
     */
    public function testVariablesIntegration()
    {
        $sdk = ChannelSDK::newDefault($this->testApiKey, $this->testApiSecret);
        
        // 测试版本号一致性
        $this->assertEquals(Variables::getVersion(), $sdk->getVersion());
        
        // 测试调试状态一致性
        $this->assertEquals(Variables::isDebugging(), $sdk->isDebugging());
        
        // 修改全局调试状态
        Variables::setDebugging(true);
        $this->assertEquals(Variables::isDebugging(), $sdk->isDebugging());
        
        $sdk->close();
    }
}