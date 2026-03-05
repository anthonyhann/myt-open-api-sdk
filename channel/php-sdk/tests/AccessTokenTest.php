<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Models\Sender\Api\ChannelSender;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Auth\AccessTokenReq;

class AccessTokenTest extends TestCase
{
    public function testAccessToken()
    {
        // 创建配置
        $config = ConfigBuilder::NewConfigBuilder()
            ->BaseURL('https://open-api-test.maiyatian.com')
            ->APIKey('test_app_key')
            ->APISecret('test_app_secret')
            ->EnableLogging(false)
            ->Build();

        // 创建发送者
        $sender = ChannelSender::NewChannelSender($config);

        // 创建请求参数
        $req = new AccessTokenReq();
        $req->grant_type = 'shop';
        $req->code = 'test_code';
        $req->shop_id = 'test_shop_id';
        $req->category = 'yinpin';
        $req->name = '测试店铺';
        $req->type = 'waimai';
        $req->longitude = '116.397128';
        $req->latitude = '39.916527';

        // 这里我们不实际调用API，而是验证发送者能正确创建
        $this->assertNotNull($sender);
        $this->assertInstanceOf(\Maiyatian\Channel\PhpSdk\Models\Sender\Api\IChannelSender::class, $sender);
    }
}
