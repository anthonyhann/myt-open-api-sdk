# 麦芽田配送开放平台PHP SDK

## 项目介绍

麦芽田配送开放平台PHP SDK是为开发者提供的PHP语言开发工具包，用于快速接入麦芽田配送开放平台API。

## 功能特性

- 企业级HTTP客户端，支持连接池、重试、超时控制
- 完整的API封装，包括授权、配送状态同步、快递轨迹回传等
- 统一的请求和响应处理
- 完善的错误处理机制
- 支持测试环境和生产环境切换
- 详细的文档和示例代码

## 安装方法

### Composer安装

```bash
composer require maiyatian/delivery-sdk-php
```

### 手动安装

1. 下载SDK源码
2. 将SDK源码放入项目目录
3. 在项目中引入自动加载文件

```php
require __DIR__ . '/path/to/sdk/vendor/autoload.php';
```

## 快速开始

### 初始化配置

```php
use Maiyatian\Delivery\SDK\client\ConfigBuilder;
use Maiyatian\Delivery\SDK\models\types\Constants;

// 创建配置构建器
$configBuilder = new ConfigBuilder();

// 构建配置
$config = $configBuilder
    ->baseURL(Constants::TEST_BASE_URL) // 测试环境URL
    ->apiKey("your_api_key") // 替换为你的API Key
    ->apiSecret("your_api_secret") // 替换为你的API Secret
    ->maxConnections(100)
    ->requestTimeout(30)
    ->retryMaxAttempts(5)
    ->enableLogging(true)
    ->build();
```

### 创建HTTP客户端

```php
use Maiyatian\Delivery\SDK\client\HTTPClientManager;

// 创建HTTP客户端管理器
$clientManager = new HTTPClientManager($config);
```

### 调用API

```php
use Maiyatian\Delivery\SDK\models\sender\api\AccessToken;

// 创建API实例
$accessTokenApi = new AccessToken($clientManager);

// 调用API
$response = $accessTokenApi->getAccessToken(
    "your_code", // 授权码
    "1", // 授权类型：门店
    "13800138000", // 手机号
    "store_123", // 门店ID
    "北京市", // 城市名称
    "10001", // 城市编码
    "source_key", // 密钥
    "JD" // 平台标识
);

// 处理响应
echo "Status Code: {$response->code}\n";
echo "Message: {$response->message}\n";
echo "Data: {$response->data}\n";
```

## API列表

### Sender API（三方服务推送麦芽田开放平台服务接口）

1. **授权码获取access_token**
   - 接口：`AccessToken::getAccessToken()`
   - 功能：使用授权码获取access_token和refresh_token

2. **refreshToken刷新accessToken**
   - 接口：`RefreshToken::refreshToken()`
   - 功能：使用refreshToken刷新accessToken

3. **配送状态同步**
   - 接口：`DeliveryChange::syncDeliveryStatus()`
   - 功能：同步订单配送状态

4. **快递轨迹回传**
   - 接口：`LocationChange::reportLocation()`
   - 功能：回传快递轨迹信息

### Receiver API（麦芽田开放平台主动请求三方服务接口）

1. **获取城市运力**
   - 功能：获取麦芽田开放平台支持的城市列表

2. **订单计费接口**
   - 功能：查询订单运费

3. **下单接口**
   - 功能：创建配送订单

4. **添加小费接口**
   - 功能：为订单添加小费

5. **预取消配送**
   - 功能：预取消配送订单

6. **取消配送**
   - 功能：取消配送订单

## 错误处理

SDK使用异常机制处理错误，建议使用try-catch块捕获异常：

```php
try {
    // 调用API
    $response = $accessTokenApi->getAccessToken("invalid_code");
} catch (\InvalidArgumentException $e) {
    // 参数错误
    echo "参数错误：" . $e->getMessage() . "\n";
} catch (\GuzzleHttp\Exception\GuzzleException $e) {
    // HTTP请求错误
    echo "HTTP请求错误：" . $e->getMessage() . "\n";
} catch (\Exception $e) {
    // 其他错误
    echo "其他错误：" . $e->getMessage() . "\n";
}
```

## 日志配置

SDK支持配置日志记录，可在初始化配置时开启：

```php
$config = $configBuilder
    ->enableLogging(true) // 启用日志
    ->build();
```

## 环境切换

```php
use Maiyatian\Delivery\SDK\models\types\Constants;

// 测试环境
$config = $configBuilder
    ->baseURL(Constants::TEST_BASE_URL)
    ->build();

// 生产环境
$config = $configBuilder
    ->baseURL(Constants::PROD_BASE_URL)
    ->build();
```

## 版本历史

- v1.0.0 (2025-12-09)
  - 初始版本发布
  - 支持授权相关接口
  - 支持配送状态同步
  - 支持快递轨迹回传
  - 支持城市运力查询
  - 支持订单计费
  - 支持下单接口
  - 支持取消配送
  - 支持添加小费
  - 支持预取消配送

## 联系我们

- 官方网站：https://www.maiyatian.com
- 技术支持：support@maiyatian.com
- API文档：https://open-api.maiyatian.com/docs

## 许可证

MIT License
