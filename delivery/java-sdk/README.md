# 麦芽田配送开放平台 Java SDK

## 1. 项目简介

麦芽田配送开放平台 Java SDK 是一个企业级的SDK，用于帮助开发者快速接入麦芽田配送开放平台的各项服务。SDK 提供了完整的 HTTP 客户端、请求签名、重试机制、日志记录等企业级特性，简化了开发者的接入工作。

## 2. 功能特性

- **企业级 HTTP 客户端**：基于 OkHttp 实现，支持连接池管理、超时控制、自动重试等特性
- **请求签名**：自动生成 HmacSHA256 签名，确保请求安全性
- **完整的 API 模型**：包含所有配送开放平台 API 的请求和响应模型
- **灵活的配置**：使用 Builder 模式配置，满足不同场景需求
- **详细的日志记录**：基于 SLF4J 实现，支持请求和响应日志，便于调试和监控
- **完整的示例代码**：提供快速开始示例，帮助开发者快速上手
- **全面的单元测试**：确保 SDK 质量和稳定性
- **面向接口设计**：提供清晰的 API 调用接口，支持灵活扩展
- **支持 try-with-resources**：实现 AutoCloseable 接口，方便资源管理
- **Java 8 兼容**：确保在 Java 8 环境下正常运行

## 3. 环境要求

- Java 8 或更高版本
- Maven 3.6.0 或更高版本

## 4. 安装方式

### 4.1 Maven 依赖

将以下依赖添加到您的 `pom.xml` 文件中：

```xml
<dependency>
    <groupId>com.maiyatian</groupId>
    <artifactId>delivery-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 4.2 本地构建

如果您需要本地构建 SDK，可以按照以下步骤进行：

1. 进入 SDK 目录：
   ```bash
   cd /path/to/delivery/java-sdk
   ```

2. 构建项目：
   ```bash
   mvn clean install
   ```

3. 运行测试：
   ```bash
   mvn test
   ```

## 5. 快速开始

### 5.1 初始化配置

```java
import com.maiyatian.delivery.sdk.client.HttpClientConfig;
import com.maiyatian.delivery.sdk.models.types.Constants;

// 创建配置
HttpClientConfig config = HttpClientConfig.newBuilder()
        .baseUrl(Constants.TEST_BASE_URL) // 测试环境
        .apiKey("your_app_key") // 替换为您的app_key
        .apiSecret("your_app_secret") // 替换为您的app_secret
        .requestTimeout(java.time.Duration.ofSeconds(30))
        .retryMaxAttempts(3)
        .enableLogging(true)
        .build();
```

### 5.2 创建配送发送者实例

```java
import com.maiyatian.delivery.sdk.models.sender.api.DeliverySender;
import com.maiyatian.delivery.sdk.models.sender.api.IDeliverySender;

// 创建配送发送者实例，支持 try-with-resources
try (IDeliverySender sender = new DeliverySender(config)) {
    // 使用 sender 调用 API
    // ...
}
```

### 5.3 调用 API

```java
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import com.maiyatian.delivery.sdk.models.types.Constants;

// 创建请求参数
AccessTokenEntity.Req accessTokenReq = new AccessTokenEntity.Req();
accessTokenReq.setGrantType("1"); // 1: 门店授权
accessTokenReq.setCode("your_auth_code"); // 替换为您的授权码
accessTokenReq.setMobile("13800138000"); // 替换为您的手机号
accessTokenReq.setStoreId("your_store_id"); // 替换为您的门店 ID
accessTokenReq.setCity("北京市");
accessTokenReq.setCityCode("10001");

// 调用 API 获取访问令牌
AccessTokenEntity.Resp accessTokenResp = sender.accessToken(
        "", // 首次获取令牌时不需要 token
        accessTokenReq
);

// 使用获取到的 token 调用其他 API
String token = accessTokenResp.getToken();
String refreshToken = accessTokenResp.getRefreshToken();

// 调用刷新令牌 API
RefreshTokenEntity.Req refreshTokenReq = new RefreshTokenEntity.Req();
refreshTokenReq.setToken(token);
refreshTokenReq.setRefreshToken(refreshToken);

RefreshTokenEntity.Resp refreshTokenResp = sender.refreshToken(
        token,
        refreshTokenReq
);

// 调用配送状态同步 API
DeliveryChangeEntity.Req deliveryChangeReq = new DeliveryChangeEntity.Req();
deliveryChangeReq.setOrderNo("1234567890");
deliveryChangeReq.setSourceOrderNo("9876543210");
deliveryChangeReq.setShopId("your_shop_id");
deliveryChangeReq.setStatus(Constants.DELIVERY_STATUS_DELIVERING);
deliveryChangeReq.setRiderName("林骑手");
deliveryChangeReq.setRiderPhone("13888888888_1234");
deliveryChangeReq.setLongitude("103.11111");
deliveryChangeReq.setLatitude("90.1123123");
deliveryChangeReq.setUpdateTime(System.currentTimeMillis() / 1000);

sender.deliveryChange(token, deliveryChangeReq);

// 调用快递轨迹回传 API
LocationChangeEntity.Req locationChangeReq = new LocationChangeEntity.Req();
locationChangeReq.setOrderNo("1234567890");
locationChangeReq.setSourceOrderNo("9876543210");
locationChangeReq.setShopId("your_shop_id");

// 添加轨迹点
List<LocationChangeEntity.Location> locations = new ArrayList<>();
LocationChangeEntity.Location location = new LocationChangeEntity.Location();
location.setDescription("[合肥市]快件已到达【安徽合肥转运中心】");
location.setCity("合肥市");
location.setLongitude("117.227231");
location.setLatitude("31.820571");
location.setStatus(Constants.DELIVERY_STATUS_DELIVERING);
location.setUpdateTime(System.currentTimeMillis() / 1000);
locations.add(location);

locationChangeReq.setLocations(locations);

sender.locationChange(token, locationChangeReq);
```

## 6. 核心功能说明

### 6.1 客户端配置

`HttpClientConfig` 类提供了完整的 HTTP 客户端配置选项，包括：

| 配置项 | 描述 | 默认值 |
| --- | --- | --- |
| **基础配置** | | |
| `baseUrl` | API 基础 URL | `https://open-api.maiyatian.com` |
| `apiKey` | 应用标识 | -（必填） |
| `apiSecret` | 应用密钥（用于签名） | -（必填） |
| `sdkVersion` | SDK 版本号 | `1.0.0` |
| **连接池配置** | | |
| `maxConnections` | 最大连接数 | `50` |
| `maxConnectionsPerHost` | 每个主机的最大连接数 | `10` |
| `keepAliveTimeout` | 连接保活超时 | `30秒` |
| **超时配置** | | |
| `requestTimeout` | 请求总超时 | `60秒` |
| `connectionTimeout` | 连接超时 | `30秒` |
| `readTimeout` | 读取超时 | `60秒` |
| **重试配置** | | |
| `retryMaxAttempts` | 最大重试次数 | `3次` |
| `retryBaseDelay` | 重试基础延迟 | `1秒` |
| `retryMaxDelay` | 重试最大延迟 | `30秒` |
| **日志配置** | | |
| `enableLogging` | 是否启用日志 | `true` |
| `logRequestBody` | 是否记录请求体 | `false` |
| `logResponseBody` | 是否记录响应体 | `false` |

### 6.2 配送发送者

`IDeliverySender` 是 SDK 的核心接口，定义了所有配送相关 API 的调用方法：

#### 6.2.1 授权管理
- `accessToken(String token, AccessTokenEntity.Req data)` - 获取访问令牌
- `refreshToken(String token, RefreshTokenEntity.Req data)` - 刷新访问令牌

#### 6.2.2 配送变更
- `deliveryChange(String token, DeliveryChangeEntity.Req data)` - 同步配送状态

#### 6.2.3 位置变更
- `locationChange(String token, LocationChangeEntity.Req data)` - 回传快递轨迹

`DeliverySender` 是 `IDeliverySender` 接口的实现类，负责执行实际的 HTTP 请求、处理重试逻辑和日志记录。

### 6.3 请求签名

SDK 自动处理请求签名，签名规则如下：

1. 提取请求体中的 `app_key`、`token`、`timestamp`、`data` 字段及 path 中的 `command`
2. 按字母顺序排序
3. 用半角逗号连接：`key1=value1,key2=value2,...`
4. 使用 `appSecret` 计算 HmacSHA256 值
5. 对结果进行 URL 安全的 Base64 编码

### 6.4 重试机制

SDK 支持自动重试机制，重试条件包括：

- 网络错误（如超时、连接失败等）
- HTTP 5xx 服务器错误
- 业务状态码 5xx 错误

重试策略：
- 默认最大重试次数：3 次
- 重试延迟：使用指数退避策略，基础延迟为 1 秒，最大延迟为 30 秒
- 延迟计算公式：`min(baseDelay * (2^(attempt-1)), maxDelay)`

## 7. API 参考

### 7.1 Sender API

Sender API 用于三方服务推送数据给麦芽田开放平台：

| API 名称 | 命令值 | 描述 | 实现类 |
| --- | --- | --- | --- |
| 获取访问令牌 | `access_token` | 使用授权码换取访问令牌 | `AccessTokenEntity` |
| 刷新访问令牌 | `refresh_token` | 使用刷新令牌获取新的访问令牌 | `RefreshTokenEntity` |
| 配送状态同步 | `delivery_change` | 同步订单配送状态 | `DeliveryChangeEntity` |
| 快递轨迹回传 | `location_change` | 回传快递轨迹信息 | `LocationChangeEntity` |

## 8. 项目结构

```
src/main/java/com/maiyatian/delivery/sdk/
├── client/                    # HTTP客户端相关
│   ├── HttpClientConfig.java   # 客户端配置类
│   ├── HttpClientManager.java  # HTTP请求管理器
│   └── ConfigValidationException.java # 配置验证异常
├── examples/                  # 示例代码
│   └── QuickStartExample.java # 快速开始示例
├── models/                    # 数据模型
│   └── types/                 # 公共类型
│       ├── Constants.java      # 常量定义
│       ├── Request.java        # 请求基类
│       └── Response.java       # 响应基类
├── models/sender/             # 发送方模型（三方 -> 麦芽田）
│   ├── api/                   # API接口
│   │   ├── DeliverySender.java   # 配送发送者实现
│   │   └── IDeliverySender.java  # 配送发送者接口
│   └── entity/                # 实体类
│       ├── auth/              # 授权相关实体
│       │   ├── AccessTokenEntity.java   # 访问令牌实体
│       │   └── RefreshTokenEntity.java   # 刷新令牌实体
│       ├── delivery/          # 配送相关实体
│       │   └── DeliveryChangeEntity.java # 配送状态变更实体
│       └── express/           # 快递相关实体
│           └── LocationChangeEntity.java # 位置变更实体
└── utils/                     # 工具类
    ├── JsonUtils.java         # JSON处理工具
    └── SignatureUtils.java     # 签名生成工具
```

## 9. 示例代码

SDK 提供了完整的示例代码，位于 `src/main/java/com/maiyatian/delivery/sdk/examples` 目录下：

- `QuickStartExample.java`：快速开始示例，展示了 SDK 的基本使用方法，包括：
  - 配置初始化
  - 配送发送者创建
  - 获取访问令牌
  - 刷新访问令牌
  - 同步配送状态
  - 回传快递轨迹

## 10. 常见问题

### 10.1 如何切换测试环境和正式环境？

您可以通过修改 `baseUrl` 配置来切换环境：

```java
// 测试环境
HttpClientConfig.newBuilder().baseUrl(Constants.TEST_BASE_URL)...

// 正式环境
HttpClientConfig.newBuilder().baseUrl(Constants.PROD_BASE_URL)...
```

### 10.2 如何处理异常？

SDK 抛出的主要异常类型：

- `ConfigValidationException`：配置验证失败
- `IOException`：网络请求失败或 JSON 处理失败

处理示例：

```java
try {
    // SDK 调用
    sender.accessToken(token, req);
} catch (ConfigValidationException e) {
    System.err.println("配置错误: " + e.getMessage());
} catch (IOException e) {
    if (e instanceof JsonProcessingException) {
        System.err.println("JSON处理错误: " + e.getMessage());
    } else {
        System.err.println("网络请求错误: " + e.getMessage());
    }
}
```

### 10.3 如何查看日志？

SDK 使用 SLF4J 进行日志记录，您需要在项目中添加 SLF4J 实现，如 Logback 或 Log4j2：

```xml
<!-- Logback 示例 -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.11</version>
    <scope>runtime</scope>
</dependency>
```

### 10.4 如何自定义 HTTP 头？

您可以使用带有 headers 参数的 API 方法：

```java
Map<String, String> headers = new HashMap<>();
headers.put("Custom-Header", "value");
sender.accessToken(token, req, headers);
```

## 11. 版本历史

| 版本 | 发布时间 | 描述 |
| --- | --- | --- |
| 1.0.0 | 2025-12-11 | 初始版本，包含所有配送开放平台 API |

## 12. 许可证

MIT License

## 13. 联系方式

如有任何问题或建议，欢迎联系我们：

- 官方文档：https://docs.maiyatian.com
- 技术支持：support@maiyatian.com
- 项目地址：https://github.com/maiyatian/delivery-sdk
