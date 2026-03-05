# 麦芽田渠道开放平台PHP SDK

## 概述

麦芽田渠道开放平台PHP SDK是一个用于对接麦芽田开放平台API的开发工具包，提供了便捷的API调用方式和完整的错误处理机制，帮助开发者快速集成麦芽田开放平台的功能。

## 功能特性

- **完整的API支持**：支持麦芽田开放平台所有API接口
- **便捷的配置管理**：提供灵活的配置选项，支持测试环境和生产环境切换
- **自动签名生成**：自动生成符合麦芽田规范的请求签名，无需手动处理
- **完善的错误处理**：提供详细的错误信息和堆栈跟踪，便于调试
- **日志记录**：支持请求和响应日志记录，便于调试和监控
- **重试机制**：自动处理网络错误和服务器错误，提高请求成功率
- **类型安全**：完善的类型定义，支持IDE智能提示

## 安装

### 使用Composer安装（推荐）

```bash
composer require maiyatian/channel-sdk
```

### 手动安装

1. 下载SDK代码
2. 解压到项目目录
3. 在项目中添加自动加载配置

## 快速开始

### 基本配置

```php
<?php

require __DIR__ . '/vendor/autoload.php';

use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;

// 创建配置
$config = ConfigBuilder::NewConfigBuilder()
    ->BaseURL('https://open-api-test.maiyatian.com')
    ->APIKey('your_app_key')
    ->APISecret('your_app_secret')
    ->RequestTimeout(30)
    ->EnableLogging(true)
    ->Build();
```

### 初始化SDK

```php
<?php

require __DIR__ . '/vendor/autoload.php';

use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Models\Sender\Api\ChannelSender;

// 创建配置
$config = ConfigBuilder::NewConfigBuilder()
    ->BaseURL('https://open-api-test.maiyatian.com')
    ->APIKey('your_app_key')
    ->APISecret('your_app_secret')
    ->Build();

// 创建发送者
$sender = ChannelSender::NewChannelSender($config);
```

### 调用API接口

#### 获取访问令牌

```php
<?php

require __DIR__ . '/vendor/autoload.php';

use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Models\Sender\Api\ChannelSender;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Auth\AccessTokenReq;

// 创建配置和发送者
$config = ConfigBuilder::NewConfigBuilder()
    ->BaseURL('https://open-api-test.maiyatian.com')
    ->APIKey('your_app_key')
    ->APISecret('your_app_secret')
    ->Build();
$sender = ChannelSender::NewChannelSender($config);

// 创建请求参数
$req = new AccessTokenReq();
$req->grant_type = 'shop';
$req->code = 'your_authorization_code';
$req->shop_id = 'your_shop_id';
$req->category = 'yinpin';
$req->name = '测试店铺';
$req->type = 'waimai';
$req->longitude = '116.397128';
$req->latitude = '39.916527';

// 调用API
$response = $sender->AccessToken($req);

// 处理响应
if ($response->Code === 200) {
    $data = json_decode($response->Data, true);
    $token = $data['token'];
    $refreshToken = $data['refresh_token'];
    echo "获取令牌成功: {$token}\n";
} else {
    echo "获取令牌失败: {$response->Message}\n";
}
```

#### 推送新订单

```php
<?php

require __DIR__ . '/vendor/autoload.php';

use Maiyatian\Channel\PhpSdk\Client\ConfigBuilder;
use Maiyatian\Channel\PhpSdk\Models\Sender\Api\ChannelSender;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\CreateOrderReq;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderGoodsInfo;

// 创建配置和发送者
$config = ConfigBuilder::NewConfigBuilder()
    ->BaseURL('https://open-api-test.maiyatian.com')
    ->APIKey('your_app_key')
    ->APISecret('your_app_secret')
    ->Build();
$sender = ChannelSender::NewChannelSender($config);

// 创建新订单请求
$req = new CreateOrderReq();

// 设置订单信息
$req->Order->OrderId = 'test_order_id_001';
$req->Order->OrderSn = 12345678;
$req->Order->ShopId = 'your_shop_id';
$req->Order->ShopName = '测试店铺';
$req->Order->Category = 'yinpin';
$req->Order->IsPreOrder = false;
$req->Order->TotalPrice = 1000; // 单位：分
$req->Order->BalancePrice = 800; // 单位：分
$req->Order->CreateTime = time();
$req->Order->DeliveryTime = time() + 3600;
$req->Order->DeliveryType = 0;
$req->Order->IsPicker = false;

// 设置顾客信息
$req->OrderCustomer->RealName = '张三';
$req->OrderCustomer->Phone = '13800138000';
$req->OrderCustomer->Address = '北京市朝阳区测试地址';
$req->OrderCustomer->Longitude = '116.397128';
$req->OrderCustomer->Latitude = '39.916527';

// 添加商品
$goods = new OrderGoodsInfo();
$goods->GoodsId = 'test_goods_id_001';
$goods->GoodsName = '测试商品';
$goods->Thumb = 'https://example.com/test.jpg';
$goods->SkuId = 'test_sku_id_001';
$goods->SkuName = '测试规格';
$goods->Unit = '份';
$goods->Weight = 500;
$goods->Number = 2;
$goods->GoodsPrice = 500;
$goods->GoodsTotalFee = 1000;
$goods->TotalFee = 950;

$req->Order->AddGoods($goods);

// 调用API
$response = $sender->OrderCreated('your_token', $req);

// 处理响应
if ($response->Code === 200) {
    echo "推送订单成功\n";
} else {
    echo "推送订单失败: {$response->Message}\n";
}
```

## 配置选项

### 基本配置

| 配置项 | 类型 | 默认值 | 描述 |
| --- | --- | --- | --- |
| BaseURL | string | https://open-api.maiyatian.com | API基础地址 |
| APIKey | string | - | 应用密钥（必填） |
| APISecret | string | - | 应用密钥（必填） |
| SDKVersion | string | 1.0.0 | SDK版本号 |

### 连接池配置

| 配置项 | 类型 | 默认值 | 描述 |
| --- | --- | --- | --- |
| MaxConnections | int | 50 | 最大连接数 |
| MaxConnectionsPerHost | int | 10 | 每个主机的最大连接数 |
| KeepAliveTimeout | int | 30 | 连接保活超时时间（秒） |

### 超时配置

| 配置项 | 类型 | 默认值 | 描述 |
| --- | --- | --- | --- |
| RequestTimeout | int | 60 | 请求总超时时间（秒） |
| ConnectionTimeout | int | 30 | 连接建立超时时间（秒） |
| ReadTimeout | int | 60 | 读取响应超时时间（秒） |

### 重试配置

| 配置项 | 类型 | 默认值 | 描述 |
| --- | --- | --- | --- |
| RetryMaxAttempts | int | 3 | 最大重试次数 |
| RetryBaseDelay | int | 1 | 重试基础延迟时间（秒） |
| RetryMaxDelay | int | 30 | 重试最大延迟时间（秒） |

### 日志配置

| 配置项 | 类型 | 默认值 | 描述 |
| --- | --- | --- | --- |
| EnableLogging | bool | true | 是否启用日志记录 |
| LogRequestBody | bool | false | 是否记录请求体 |
| LogResponseBody | bool | false | 是否记录响应体 |

## API参考

### 授权相关

#### 获取访问令牌

```php
$response = $sender->AccessToken($req);
```

#### 刷新访问令牌

```php
$response = $sender->RefreshToken($token, $req);
```

### 订单相关

#### 推送新订单

```php
$response = $sender->OrderCreated($token, $req);
```

#### 推送订单修改

```php
$response = $sender->OrderModified($token, $req);
```

#### 推送订单确认

```php
$response = $sender->OrderConfirmed($token, $req);
```

#### 推送订单完成

```php
$response = $sender->OrderDone($token, $req);
```

#### 推送订单取消

```php
$response = $sender->OrderCanceled($token, $req);
```

#### 推送催单信息

```php
$response = $sender->OrderRemind($token, $req);
```

### 退款相关

#### 推送退款申请

```php
$response = $sender->OrderApplyRefund($token, $req);
```

#### 推送退款结果

```php
$response = $sender->OrderRefunded($token, $req);
```

### 配送相关

#### 推送自配送状态变更

```php
$response = $sender->SelfDeliveryChange($token, $req);
```

### 门店相关

#### 查询门店信息

```php
$response = $sender->ShopInfo($token, $req);
```

## 错误处理

SDK提供了详细的错误信息，包括HTTP状态码、业务状态码和错误描述。开发者可以根据错误信息进行相应的处理。

```php
try {
    $response = $sender->AccessToken($req);
    if ($response->Code !== 200) {
        // 处理业务错误
        echo "业务错误: {$response->Message}\n";
    }
} catch (\Exception $e) {
    // 处理网络错误或其他异常
    echo "请求失败: {$e->getMessage()}\n";
    echo "堆栈跟踪: {$e->getTraceAsString()}\n";
}
```

## 日志记录

SDK支持请求和响应日志记录，可以通过配置启用。日志记录有助于调试和监控API调用情况。

```php
$config = ConfigBuilder::NewConfigBuilder()
    ->BaseURL('https://open-api-test.maiyatian.com')
    ->APIKey('your_app_key')
    ->APISecret('your_app_secret')
    ->EnableLogging(true)
    ->LogRequestBody(true)
    ->LogResponseBody(true)
    ->Build();
```

## 最佳实践

1. **合理配置超时时间**：根据网络环境和API响应时间，合理设置超时时间
2. **使用重试机制**：对于重要的API请求，启用重试机制可以提高成功率
3. **记录日志**：在开发环境中启用日志记录，便于调试；在生产环境中可以选择性记录日志，避免性能问题
4. **安全存储密钥**：不要将API密钥硬编码在代码中，建议使用环境变量或配置文件管理
5. **定期更新SDK**：定期更新SDK版本，以获取最新的功能和安全修复
6. **合理处理错误**：根据错误类型进行不同的处理，如重试、告警或降级处理

## 版本历史

### v1.0.0
- 初始版本
- 支持麦芽田开放平台所有API接口
- 提供完整的错误处理机制
- 支持日志记录和重试机制

## 贡献指南

欢迎提交Issue和Pull Request，共同改进SDK。

## 许可证

本SDK采用MIT许可证，详见LICENSE文件。

## 联系方式

- **技术支持**：support@maiyatian.com
- **API文档**：https://open-api.maiyatian.com/doc
- **问题反馈**：https://github.com/maiyatian/channel-sdk/issues
