<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Client\HTTPClientManager;
use Maiyatian\Channel\PhpSdk\Client\HTTPRequest;

class HTTPClientTest extends TestCase
{
    public function testCreateClient()
    {
        // 创建配置
        $config = ConfigBuilder::NewConfigBuilder()
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->EnableLogging(false)
            ->Build();

        // 创建HTTP客户端
        $client = HTTPClientManager::NewHTTPClientManager($config);
        
        $this->assertNotNull($client);
        $this->assertInstanceOf(HTTPClientManager::class, $client);
    }

    public function testGenerateRequestID()
    {
        // 创建配置
        $config = ConfigBuilder::NewConfigBuilder()
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->EnableLogging(false)
            ->Build();

        // 创建HTTP客户端
        $client = HTTPClientManager::NewHTTPClientManager($config);
        
        // 反射获取私有方法
        $reflection = new \ReflectionClass($client);
        $method = $reflection->getMethod('generateRequestID');
        $method->setAccessible(true);
        
        // 生成多个请求ID，验证唯一性
        $ids = [];
        for ($i = 0; $i < 10; $i++) {
            $id = $method->invoke($client);
            $this->assertNotContains($id, $ids);
            $ids[] = $id;
            // 验证UUID格式
            $this->assertMatchesRegularExpression('/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i', $id);
        }
    }

    public function testGenSign()
    {
        // 创建配置
        $config = ConfigBuilder::NewConfigBuilder()
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->EnableLogging(false)
            ->Build();

        // 创建HTTP客户端
        $client = HTTPClientManager::NewHTTPClientManager($config);
        
        // 创建请求对象
        $request = new HTTPRequest();
        $request->AppKey = 'test_app_key';
        $request->RequestId = 'test-request-id';
        $request->Token = 'test-token';
        $request->Timestamp = 1234567890;
        $request->Data = '{"key":"value"}';
        $request->Signature = '';
        
        // 生成签名
        $signature = $client->GenSign($request, 'test-secret');
        
        $this->assertNotNull($signature);
        $this->assertNotEmpty($signature);
        // 验证签名长度和格式
        $this->assertIsString($signature);
        $this->assertGreaterThan(0, strlen($signature));
    }
}
