<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Client\ConfigValidationError;

class ConfigTest extends TestCase
{
    public function testCreateConfig()
    {
        // 测试创建配置
        $config = ConfigBuilder::NewConfigBuilder()
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->RequestTimeout(30)
            ->EnableLogging(false)
            ->Build();

        $this->assertEquals('https://open-api-test.maiyatian.com', $config->BaseURL);
        $this->assertEquals('test_app_key', $config->APIKey);
        $this->assertEquals('test_app_secret', $config->APISecret);
        $this->assertEquals(30, $config->RequestTimeout);
        $this->assertEquals(false, $config->EnableLogging);
        $this->assertEquals(1.0, $config->SDKVersion);
    }

    public function testDefaultConfig()
    {
        // 测试默认配置
        $config = ConfigBuilder::NewConfigBuilder()
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->Build();

        $this->assertEquals('https://open-api.maiyatian.com', $config->BaseURL);
        $this->assertEquals(60, $config->RequestTimeout);
        $this->assertEquals(true, $config->EnableLogging);
    }

    public function testValidateConfig()
    {
        // 测试无效配置
        $this->expectException(ConfigValidationError::class);
        $this->expectExceptionMessage('配置验证失败 [BaseURL]');

        ConfigBuilder::NewConfigBuilder()
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->BaseURL('')
            ->Build();
    }

    public function testValidateConfigWithEmptyAPIKey()
    {
        // 测试空APIKey
        $this->expectException(ConfigValidationError::class);
        $this->expectExceptionMessage('配置验证失败 [APIKey]');

        ConfigBuilder::NewConfigBuilder()
            ->APIKey('')
            ->APISecret('test_app_secret')
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->Build();
    }

    public function testValidateConfigWithEmptyAPISecret()
    {
        // 测试空APISecret
        $this->expectException(ConfigValidationError::class);
        $this->expectExceptionMessage('配置验证失败 [APISecret]');

        ConfigBuilder::NewConfigBuilder()
            ->APIKey('test_app_key')
            ->APISecret('')
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->Build();
    }
}
