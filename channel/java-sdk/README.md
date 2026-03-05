# 麦芽田渠道开放平台 Java SDK

[![Java Version](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Maven Central](https://img.shields.io/badge/Maven%20Central-1.0.0-blue.svg)](https://central.sonatype.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📖 简介

麦芽田渠道开放平台 Java SDK 是为第三方服务商提供的企业级 SDK，用于快速接入麦芽田开放平台。

### 核心特性

- ✅ **企业级质量**：对标 Spring Framework 质量标准，遵循 Java 生态最佳实践
- ✅ **双向通信**：支持三方主动调用（Sender）和接收麦芽田回调（Receiver）
- ✅ **自动签名**：自动生成符合麦芽田规范的请求签名
- ✅ **自动重试**：网络错误和 5xx 错误自动重试，支持指数退避
- ✅ **连接池管理**：基于 OkHttp 的连接池，提升性能
- ✅ **类型安全**：完整的类型定义和参数验证
- ✅ **详细注释**：100% Javadoc 注释覆盖，每个字段都有详细说明
- ✅ **日志记录**：基于 SLF4J 的结构化日志，便于调试

---

## 🚀 快速开始

### Maven 依赖

```xml
<dependency>
    <groupId>com.maiyatian</groupId>
    <artifactId>channel-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle 依赖

```gradle
implementation 'com.maiyatian:channel-java-sdk:1.0.0'
```

### 创建客户端

```java
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.client.ConfigValidationException;
import com.maiyatian.channel.sdk.models.sender.api.ChannelSender;

import java.time.Duration;

public class QuickStart {
    public static void main(String[] args) {
        // 配置参数
        String baseUrl = "https://open-api-test.maiyatian.com"; // 测试环境地址
        String apiKey = "your_app_key";      // 替换为你的应用密钥
        String apiSecret = "your_app_secret"; // 替换为你的应用密钥
        
        try {
            // 1. 创建配置
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl(baseUrl)
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    // 连接池配置
                    .maxConnections(50)
                    .maxConnectionsPerHost(10)
                    // 超时配置
                    .requestTimeout(Duration.ofSeconds(30))
                    .connectionTimeout(Duration.ofSeconds(10))
                    .readTimeout(Duration.ofSeconds(30))
                    // 重试配置
                    .retryMaxAttempts(3)
                    .retryBaseDelay(Duration.ofSeconds(1))
                    .retryMaxDelay(Duration.ofSeconds(30))
                    // 日志配置
                    .enableLogging(true)
                    .logRequestBody(false) // 生产环境建议设为 false
                    .logResponseBody(false) // 生产环境建议设为 false
                    .build();
            
            // 2. 创建 Sender 客户端（用于主动调用麦芽田）
            try (ChannelSender sender = new ChannelSender(config)) {
                System.out.println("✅ ChannelSender 客户端创建成功！");
                // 使用 sender 调用 API...
            } // try-with-resources 会自动关闭客户端
            
        } catch (ConfigValidationException e) {
            System.err.println("❌ 配置验证失败: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ 运行出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

---

## 📤 Sender 接口使用（三方主动调用麦芽田）

### 1. 授权流程

#### 步骤 1：引导商户授权

```java
// 组装授权页面 URL
String authUrl = String.format(
    "https://saas.maiyatian.com/oauth/?app_key=%s&redirect_uri=%s&view=web&response_type=code&shop_id=%s&state=%s",
    "your_app_key",
    "https://your-domain.com/callback",
    "shop_123",
    "custom_state"
);

// 引导商户访问此 URL 完成授权
System.out.println("请访问以下地址完成授权：" + authUrl);
```

#### 步骤 2：获取访问令牌

```java
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.AccessTokenResp;

// 商户授权成功后，从回调地址获取 code
// 例如：https://your-domain.com/callback?code=AUTH_CODE&state=custom_state

// 使用静态工厂方法创建请求
AccessTokenReq req = AccessTokenReq.createShopAuth("AUTH_CODE", "shop_123")
        .withCategory("canyin")
        .withName("测试门店")
        .withType("waimai")
        .withLocation("116.397428", "39.90923");

// 向麦芽田换取访问令牌（注意：token 参数传空字符串）
try (ChannelSender sender = new ChannelSender(config)) {
    AccessTokenResp resp = sender.accessToken("", req);
    System.out.printf("访问令牌: %s%n", resp.getToken());
    System.out.printf("过期时间: %s%n", resp.getFormattedExpireTime());
    System.out.printf("是否需要刷新: %s%n", resp.needsRefresh());
}
```

#### 步骤 3：刷新访问令牌

```java
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenReq;
import com.maiyatian.channel.sdk.models.sender.entity.auth.RefreshTokenResp;

// 当令牌接近过期时，使用刷新令牌获取新的访问令牌
RefreshTokenReq refreshReq = new RefreshTokenReq();
refreshReq.setToken(currentToken);
refreshReq.setRefreshToken(currentRefreshToken);

RefreshTokenResp resp = sender.refreshToken(currentToken, refreshReq);
System.out.printf("新访问令牌: %s%n", resp.getToken());
System.out.printf("新刷新令牌: %s%n", resp.getRefreshToken());
```

---

### 2. 订单推送（必接）

#### 2.1 推送新订单

```java
import com.maiyatian.channel.sdk.models.sender.entity.order.CreateOrderReq;

// 创建新订单请求
CreateOrderReq req = new CreateOrderReq();
// 设置订单信息...

// 推送新订单
sender.orderCreated(token, req);
System.out.println("新订单推送成功！");
```

#### 2.2 推送订单确认

```java
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderConfirmedReq;

// 创建订单确认请求
OrderConfirmedReq req = new OrderConfirmedReq();
// 设置订单确认信息...

// 推送订单确认
sender.orderConfirmed(token, req);
System.out.println("订单确认推送成功！");
```

#### 2.3 推送订单完成

```java
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderDoneReq;

// 创建订单完成请求
OrderDoneReq req = new OrderDoneReq();
// 设置订单完成信息...

// 推送订单完成
sender.orderDone(token, req);
System.out.println("订单完成推送成功！");
```

---

## 📥 Receiver 接口实现（接收麦芽田回调）

### 重要说明

⚠️ **三方必须实现回调接口**：麦芽田会主动推送数据到三方服务，三方需要：
1. 提供回调接口地址
2. 实现回调处理逻辑
3. 验证麦芽田的请求签名
4. 返回标准的响应格式

### 回调接口实现示例

#### 使用 Spring Boot 实现回调接口

```java
import com.maiyatian.channel.sdk.utils.SignatureUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/callback")
public class CallbackController {

    private final String appSecret = "your_app_secret";

    @PostMapping("/order_confirm")
    public Map<String, Object> handleOrderConfirm(@RequestBody Map<String, Object> request) {
        try {
            // 1. 验证签名（必须！确保请求来自麦芽田）
            String signature = (String) request.get("signature");
            Map<String, Object> signParams = new HashMap<>(request);
            signParams.remove("signature");
            
            if (!SignatureUtils.verifySignature(signParams, appSecret, signature)) {
                throw new RuntimeException("签名验证失败");
            }
            
            // 2. 解析业务数据
            String dataJson = (String) request.get("data");
            // TODO: 使用 ObjectMapper 解析 dataJson 到具体的实体类
            
            // 3. 处理确认订单逻辑
            // TODO: 三方执行确认订单的业务逻辑
            
            // 4. 返回标准响应给麦芽田
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "ok");
            return response;
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 500);
            errorResponse.put("message", "处理失败: " + e.getMessage());
            return errorResponse;
        }
    }
}
```

---

## 📋 必接接口清单

### Sender 接口（三方必须主动调用）

| 接口 | 说明 | 调用时机 | 实现状态 |
|------|------|----------|----------|
| ✅ **accessToken** | 获取访问令牌 | 商户授权后换取 token | 已实现 |
| ✅ **refreshToken** | 刷新访问令牌 | 令牌过期前刷新 | 已实现 |
| ✅ **orderCreated** | 推送新订单 | 用户下单并支付成功后 | 已实现 |
| ✅ **orderConfirmed** | 推送订单确认 | 商户确认接单后 | 已实现 |
| ✅ **orderDone** | 推送订单完成 | 订单配送完成后 | 已实现 |

### Receiver 接口（三方必须实现回调）

| 回调接口 | 说明 | 处理逻辑 |
|----------|------|----------|
| ConfirmOrder | 确认订单回调 | 接收麦芽田通知 → 确认订单 → 推送确认状态 |
| DeliveryChange | 配送状态变更回调 | 接收麦芽田推送 → 同步配送状态 |
| OrderChange | 订单状态变更回调 | 接收麦芽田推送 → 同步订单状态 |

---

## 🔐 签名验证

### 请求签名（SDK 自动处理）

SDK 会自动为每个请求生成签名，签名规则：

1. 提取请求体中的 `app_key`、`token`、`timestamp`、`data` 字段
2. 按 key 进行字典序排序
3. 用半角逗号连接生成字符串（格式：`key1=value1,key2=value2`）
4. 对字符串进行 UTF8 编码
5. 使用 `app_secret` 计算 HmacSHA256 值
6. 结果使用 URL 安全的 Base64 编码

### 回调签名验证（三方必须实现）

接收麦芽田回调时，必须验证签名：

```java
import com.maiyatian.channel.sdk.utils.SignatureUtils;

public boolean verifyCallback(Map<String, Object> request, String appSecret) {
    String signature = (String) request.get("signature");
    Map<String, Object> signParams = new HashMap<>(request);
    signParams.remove("signature");
    
    return SignatureUtils.verifySignature(signParams, appSecret, signature);
}
```

---

## ⚙️ 配置说明

### 环境配置

```java
// 测试环境
HttpClientConfig testConfig = HttpClientConfig.newBuilder()
        .baseUrl("https://open-api-test.maiyatian.com")
        .apiKey("test_app_key")
        .apiSecret("test_app_secret")
        .build();

// 正式环境
HttpClientConfig prodConfig = HttpClientConfig.newBuilder()
        .baseUrl("https://open-api.maiyatian.com")
        .apiKey("prod_app_key")
        .apiSecret("prod_app_secret")
        .build();
```

### 高级配置

```java
HttpClientConfig config = HttpClientConfig.newBuilder()
        .baseUrl("https://open-api.maiyatian.com")
        .apiKey("your_app_key")
        .apiSecret("your_app_secret")
        // 连接池配置
        .maxConnections(100)                         // 最大连接数（默认 50）
        .maxConnectionsPerHost(20)                   // 每个主机的最大连接数（默认 10）
        // 超时配置
        .requestTimeout(Duration.ofSeconds(60))      // 请求总超时（默认 60 秒）
        .connectionTimeout(Duration.ofSeconds(30))   // 连接超时（默认 30 秒）
        .readTimeout(Duration.ofSeconds(60))         // 读取超时（默认 60 秒）
        // 重试配置
        .retryMaxAttempts(3)                         // 最大重试次数（默认 3）
        .retryBaseDelay(Duration.ofSeconds(1))       // 重试基础延迟（默认 1 秒）
        .retryMaxDelay(Duration.ofSeconds(30))       // 重试最大延迟（默认 30 秒）
        // 日志配置
        .enableLogging(true)                         // 启用日志（默认 true）
        .logRequestBody(false)                       // 是否记录请求体（默认 false）
        .logResponseBody(false)                      // 是否记录响应体（默认 false）
        .build();
```

---

## 📚 枚举类型说明

### 订单状态（OrderStatus）

```java
import com.maiyatian.channel.sdk.models.types.Constants;

// 使用常量类
String status = Constants.OrderStatus.CREATED;    // 待确认
String status = Constants.OrderStatus.CONFIRM;    // 已确认
String status = Constants.OrderStatus.DELIVERING; // 配送中
String status = Constants.OrderStatus.DONE;       // 已完成
// ... 更多状态
```

### 订单分类（Category）

```java
String category = Constants.Category.CANYIN;   // 正餐快餐
String category = Constants.Category.TIANPIN;  // 甜品奶茶
String category = Constants.Category.SHAOKAO;  // 龙虾烧烤
// ... 更多分类
```

完整的枚举类型定义请参考 `Constants` 类

---

## ⚠️ 重要说明

### 1. 时间格式
- 所有时间字段使用 **Unix 时间戳**（单位：**秒**）
- 请求时间戳需与实际时间误差不超过 **10 分钟**

### 2. 金额单位
- 所有金额字段单位为 **"分"**（1 元 = 100 分）
- 使用 `Money` 类处理金额，避免精度问题

```java
import com.maiyatian.channel.sdk.models.types.Money;

Money price = Money.fromYuan(new BigDecimal("123.45")); // 12345 分
BigDecimal yuan = price.toYuan(); // 123.45 元
```

### 3. 坐标系统
- 所有经纬度使用 **国测局 GCJ-02 标准**（如高德地图坐标）
- 不要使用 WGS84 或 BD09 坐标

---

## 🔍 错误处理

### 标准错误处理

```java
try (ChannelSender sender = new ChannelSender(config)) {
    AccessTokenResp resp = sender.accessToken("", request);
    System.out.println("请求成功！");
} catch (ConfigValidationException e) {
    System.err.println("配置验证失败: " + e.getMessage());
} catch (IOException e) {
    System.err.println("网络请求失败: " + e.getMessage());
} catch (JsonProcessingException e) {
    System.err.println("JSON 处理失败: " + e.getMessage());
} catch (RuntimeException e) {
    System.err.println("业务错误: " + e.getMessage());
}
```

### 重试策略

SDK 会在以下情况自动重试：
- ✅ 网络错误（连接超时、连接拒绝等）
- ✅ HTTP 5xx 服务器错误
- ✅ 业务层 5xx 错误（响应 code 字段为 500-599）

**重试配置**：
- 最大重试次数：3 次（可配置）
- 重试间隔：1 秒起，指数退避，最大 30 秒（可配置）

---

## 📖 常见问题

### Q1: Sender 和 Receiver 有什么区别？

**Sender**：三方**主动推送**数据给麦芽田
- 例如：推送新订单、推送确认状态、查询门店信息
- 使用：`new ChannelSender(config)`

**Receiver**：麦芽田**主动推送**数据给三方（回调）
- 例如：通知确认订单、推送配送状态、推送骑手位置
- 使用：三方需要实现 HTTP 回调接口

### Q2: 如何处理授权过期？

```java
AccessTokenResp tokenResp = // ... 获取的令牌信息
if (tokenResp.needsRefresh()) {
    // TODO: 使用 RefreshToken 刷新
    System.out.println("需要刷新令牌");
}
```

### Q3: 如何启用详细日志？

在 `logback.xml` 中配置：

```xml
<logger name="com.maiyatian.channel.sdk" level="DEBUG"/>
<logger name="okhttp3" level="DEBUG"/>
```

或者使用配置：

```java
HttpClientConfig config = HttpClientConfig.newBuilder()
        .baseUrl("https://open-api-test.maiyatian.com")
        .apiKey("your_app_key")
        .apiSecret("your_app_secret")
        .enableLogging(true)       // 启用日志
        .logRequestBody(true)      // 记录请求体（注意敏感信息）
        .logResponseBody(true)     // 记录响应体
        .build();
```

---

## 🧪 测试环境

### 测试地址
- **API 地址**：https://open-api-test.maiyatian.com
- **商户后台**：http://saas.open.test.maiyatian.com/
- **授权中心**：http://saas.open.test.maiyatian.com/oauth/
- **签名校验工具**：https://open-api-test.maiyatian.com/test/

### 获取测试环境

请联系麦芽田开放平台申请测试环境，获取：
- 测试 app_key
- 测试 app_secret
- 测试商户账号

---

## 📄 相关文档

- [API 文档](https://docs.maiyatian.com/open-api/)
- [CHANGELOG.md](CHANGELOG.md) - 版本更新日志

---

## 💬 技术支持

如有问题，请联系麦芽田技术支持团队。

---

## 📜 版本历史

### v1.0.0 (2025-12-09)
- ✅ 初始版本发布
- ✅ 支持授权管理接口
- ✅ 企业级特性：连接池、重试、签名、日志
- ✅ 完整的 Javadoc 注释和使用文档
- ✅ 基于 Java 17 和现代化 Java 生态

---

## 📋 License

MIT License

Copyright (c) 2025 麦芽田开放平台

---

**🚀 开始使用麦芽田渠道开放平台 Java SDK，快速接入麦芽田生态！**