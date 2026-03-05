# 麦芽田配送开放平台 Go SDK

[![Go Version](https://img.shields.io/badge/Go-%3E%3D1.24-blue.svg)](https://golang.org/dl/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![SDK Version](https://img.shields.io/badge/SDK-v1.0.0-brightgreen.svg)](https://github.com/maiyatian/delivery/myt-go-sdk/releases)

**麦芽田配送开放平台 Go SDK** - 为配送服务商和物流公司提供专业的配送状态同步和快递轨迹回传能力。

## 📋 目录

- [功能特性](#功能特性)
- [快速开始](#快速开始)
- [架构说明](#架构说明)
- [接口说明](#接口说明)
- [使用示例](#使用示例)
- [配置说明](#配置说明)
- [错误处理](#错误处理)
- [常见问题](#常见问题)
- [更新日志](#更新日志)

## 🚀 功能特性

### 🎯 专业配送服务
- ✅ **配送状态同步** - 支持接单、取货、配送中、完成等全流程状态
- ✅ **快递轨迹追踪** - 多节点轨迹推送，支持跨城物流轨迹
- ✅ **实时位置更新** - 骑手位置实时同步，距离计算
- ✅ **多模式配送** - 同城配送、快递配送双重支持

### 🔧 企业级架构
- ✅ **完整API支持** - 覆盖配送开放平台所有Sender接口
- ✅ **自动签名验证** - HmacSHA256签名自动处理
- ✅ **连接池管理** - 高并发下的连接复用和管理
- ✅ **智能重试机制** - 网络异常和服务异常自动重试
- ✅ **详细日志记录** - 可配置的请求/响应日志追踪
- ✅ **灵活超时控制** - 分级超时配置，避免请求阻塞
- ✅ **类型安全保障** - 完整类型定义和枚举常量
- ✅ **中文文档注释** - 每个接口和字段都有详细说明

## 📦 快速开始

### 安装

```bash
go get github.com/maiyatian/delivery/myt-go-sdk
```

### 基础用法

```go
package main

import (
    "context"
    "log"
    "time"

    "github.com/maiyatian/delivery/myt-go-sdk/client"
    "github.com/maiyatian/delivery/myt-go-sdk/models/sender/api"
    entityAuth "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/auth"
    entityDelivery "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/delivery"
)

func main() {
    // 1. 创建配送SDK配置
    config := client.NewConfigBuilder().
        BaseURL("https://open-api-test.maiyatian.com").  // 测试环境
        APIKey("your_app_key").                          // 替换为您的AppKey
        APISecret("your_app_secret").                    // 替换为您的AppSecret
        RequestTimeout(30 * time.Second).
        RetryMaxAttempts(3).
        EnableLogging(true).
        Build()

    // 2. 创建配送Sender客户端
    sender := api.NewDeliverySender(config)
    ctx := context.Background()

    // 3. 配送服务商授权流程
    authReq := &entityAuth.AccessTokenReq{
        GrantType: "delivery_service",     // 配送服务商授权
        Code:      "auth_code_from_oauth", // 从OAuth授权获取
        ShopId:    "delivery_service_001", // 配送服务商ID
        Category:  "delivery",             // 业务分类
        Name:      "测试配送服务商",          // 服务商名称
        Type:      "express",              // 配送类型
        Longitude: "116.397428",           // 服务中心经度
        Latitude:  "39.90923",             // 服务中心纬度
    }

    authResp, err := sender.AccessToken(ctx, authReq)
    if err != nil {
        log.Fatal("授权失败:", err)
    }

    token := authResp.Data.Token
    log.Printf("✅ 配送服务商授权成功: %s", token[:20]+"...")

    // 4. 推送配送状态示例
    deliveryReq := &entityDelivery.DeliveryChangeReq{
        OrderNo:       "delivery_order_123",
        SourceOrderNo: "myt_order_456",
        ShopId:        "shop_001",
        Status:        "DELIVERING",      // 配送中
        Tag:           "express_delivery",
        RiderName:     "张骑手",
        RiderPhone:    "138****0001",
        Longitude:     "116.407428",     // 当前位置经度
        Latitude:      "39.91923",       // 当前位置纬度
        Distance:      1500,             // 距离客户1.5公里
        UpdateTime:    time.Now().Unix(),
    }

    deliveryResp, err := sender.DeliveryChange(ctx, token, deliveryReq)
    if err != nil {
        log.Fatal("配送状态推送失败:", err)
    }

    if deliveryResp.Code == 200 {
        log.Printf("✅ 配送状态同步成功: %s", deliveryReq.Status)
    }
}
```

## 🏗️ 架构说明

### Sender vs Receiver

SDK接口分为两类：

#### Sender 接口（配送服务商 → 麦芽田）

配送服务商**主动调用**麦芽田平台的接口，实现配送状态和轨迹的实时同步：

- ✅ `AccessToken` - 配送服务商授权获取访问令牌
- ✅ `RefreshToken` - 刷新访问令牌延长有效期
- ✅ `DeliveryChange` - 推送配送状态（接单→取货→配送中→完成）
- ✅ `LocationChange` - 推送快递轨迹（揽收→中转→派送→签收）

```go
// 配送状态同步示例
sender := api.NewDeliverySender(config)
resp, err := sender.DeliveryChange(ctx, token, deliveryReq)

// 快递轨迹推送示例
resp, err := sender.LocationChange(ctx, token, locationReq)
```

#### Receiver 接口（麦芽田 → 三方）

麦芽田平台**主动回调**三方配送服务商的接口（需要三方实现HTTP服务）：

- 📋 `city_capacity` - 获取城市运力
- 📋 `valuating` - 订单计费
- 📋 `send` - 配送下单
- 📋 `tips` - 添加小费
- 📋 `cancel` / `precancel` - 取消配送
- 📋 `query_info` - 查询配送详情
- 📋 `rider_location` - 获取骑手位置
- 📋 `balance` - 查询余额
- 📋 `token_unbind` - 授权解绑
- 📋 更多...

> **注意**: Receiver接口需要三方配送服务商自己实现HTTP服务来接收回调，SDK中的entity定义可帮助理解数据结构。

### 项目结构

```
myt-go-sdk/
├── client/              # HTTP客户端实现
│   ├── config.go       # 配置管理和Builder模式
│   └── http_client.go  # HTTP客户端和连接池
├── models/
│   ├── sender/         # Sender接口（配送服务商→麦芽田）
│   │   ├── api/        # API实现层
│   │   │   ├── access_token.go      # 授权相关接口
│   │   │   ├── refresh_token.go     # 令牌刷新接口
│   │   │   ├── delivery_change.go   # 配送状态同步
│   │   │   ├── location_change.go   # 快递轨迹推送
│   │   │   └── base.go              # 基础API封装
│   │   └── entity/     # 请求响应实体
│   │       ├── auth/               # 授权实体
│   │       ├── delivery/           # 配送实体
│   │       └── express/            # 快递轨迹实体
│   └── receiver/       # Receiver接口（麦芽田→配送服务商）
│       └── entity/     # 回调请求实体定义
├── consts/             # 常量定义
│   └── consts.go      # HTTP状态码、内容类型等
├── vars/               # 全局变量
│   ├── version.go     # SDK版本管理
│   └── vars.go        # 调试变量
├── examples/           # 完整使用示例
│   ├── basic_usage.go     # 基础功能示例
│   ├── advanced_features.go # 高级功能示例
│   ├── main.go           # 统一入口程序
│   └── README.md         # 示例说明文档
├── ARCHITECTURE.md     # 架构说明文档
└── README.md           # 项目说明文档
```

## 📖 接口说明

### Sender 接口列表

| 接口 | Command | 必接 | 说明 |
|------|---------|------|------|
| AccessToken | access_token | ✅ 是 | 授权码换取访问令牌 |
| RefreshToken | refresh_token | ✅ 是 | 刷新访问令牌 |
| DeliveryChange | delivery_change | ✅ 是 | 推送配送状态 |
| LocationChange | location_change | ✅ 是 | 推送快递轨迹 |

### 配送状态枚举

```go
import "github.com/maiyatian/delivery/myt-go-sdk/models/types"

// 配送状态
const (
    DeliveryStatusGrabbed      = "GRABBED"      // 已分配骑手
    DeliveryStatusPickup       = "PICKUP"       // 待取货
    DeliveryStatusDelivering   = "DELIVERING"   // 配送中
    DeliveryStatusDone         = "DONE"         // 已完成
    DeliveryStatusCancel       = "CANCEL"       // 已取消
    DeliveryStatusRiderCancel  = "RIDER_CANCEL" // 骑手取消
    DeliveryStatusTransfer     = "TRANSFER"     // 骑手转单
    DeliveryStatusExpect       = "EXPECT"       // 配送异常
)

// 取消类型
const (
    CancelTypeUser            = 1  // 用户取消
    CancelTypeMerchant        = 2  // 商户取消
    CancelTypeCustomerService = 3  // 客服取消
    CancelTypeSystem          = 4  // 系统取消
    CancelTypeOther           = 5  // 其他原因
)
```

## 💡 使用示例

### 1. 获取访问令牌

```go
req := &entityAuth.AccessTokenReq{
    GrantType: "1",          // 1:门店 2:商户 3:三方门店直连
    Code:      "auth_code",  // 从OAuth2授权流程获取
    StoreId:   "store_123",
    Mobile:    "13800138000",
    City:      "北京市",
    CityCode:  "110100",
}

resp, err := sender.AccessToken(ctx, "", req)
if err != nil {
    log.Fatal(err)
}

// 解析响应
var tokenResp entityAuth.AccessTokenResp
json.Unmarshal([]byte(resp.Data), &tokenResp)
log.Printf("Token: %s", tokenResp.Token)
```

### 2. 推送配送状态

```go
req := &entityDelivery.DeliveryChangeReq{
    OrderNo:       "delivery_order_123",  // 三方订单号
    SourceOrderNo: "myt_order_456",       // 麦芽田订单号
    ShopId:        "shop_789",
    Status:        "DELIVERING",          // 配送中
    RiderName:     "张骑手",
    RiderPhone:    "13800138000",
    Longitude:     "116.397128",          // 高德坐标
    Latitude:      "39.916527",
    Distance:      1500,                  // 米
    DeliveryFee:   850,                   // 分
    UpdateTime:    time.Now().Unix(),
}

resp, err := sender.DeliveryChange(ctx, token, req)
```

### 3. 推送快递轨迹

```go
locations := []entityExpress.Location{
    {
        Description: "[北京市]快件已揽收",
        City:        "北京市",
        Longitude:   "116.397128",
        Latitude:    "39.916527",
        Status:      "PICKUP",
        UpdateTime:  time.Now().Unix(),
    },
    {
        Description: "[北京市]快件正在配送中",
        City:        "北京市",
        Status:      "DELIVERING",
        UpdateTime:  time.Now().Unix(),
    },
}

req := &entityExpress.LocationChangeReq{
    OrderNo:       "express_order_123",
    SourceOrderNo: "myt_order_456",
    ShopId:        "shop_789",
    Locations:     locations,
}

resp, err := sender.LocationChange(ctx, token, req)
```

更多完整示例请查看 [examples](./examples/) 目录：

- 📚 [基础功能示例](./examples/basic_usage.go) - 配置创建、授权流程、状态同步、轨迹推送
- ⚡ [高级功能示例](./examples/advanced_features.go) - 批量操作、实时追踪、并发处理、性能分析
- 🚀 [快速运行](./examples/main.go) - 命令行工具，支持不同示例类型
- 📖 [示例文档](./examples/README.md) - 详细的使用指南和最佳实践

## ⚙️ 配置说明

### 基础配置

```go
config := &client.HTTPClientConfig{
    // 必填项
    BaseURL:   "https://open-api-test.maiyatian.com",
    APIKey:    "your_app_key",
    APISecret: "your_app_secret",
    
    // 连接池配置
    MaxConnections:        50,                   // 最大连接数
    MaxConnectionsPerHost: 10,                   // 每个主机最大连接数
    KeepAliveTimeout:      30 * time.Second,    // 连接保持时间
    
    // 超时配置
    RequestTimeout:    60 * time.Second,         // 请求超时
    ConnectionTimeout: 30 * time.Second,         // 连接超时
    ReadTimeout:       60 * time.Second,         // 读取超时
    
    // 重试配置
    RetryMaxAttempts:  3,                        // 最大重试次数
    RetryBaseDelay:    1 * time.Second,          // 基础延迟
    RetryMaxDelay:     30 * time.Second,         // 最大延迟
    
    // 日志配置
    EnableLogging:     true,                     // 启用日志
    LogRequestBody:    false,                    // 记录请求体
    LogResponseBody:   false,                    // 记录响应体
}
```

### 使用Builder模式

```go
config := client.NewConfigBuilder().
    BaseURL("https://open-api-test.maiyatian.com").
    APIKey("your_app_key").
    APISecret("your_app_secret").
    MaxConnections(100).
    RequestTimeout(30 * time.Second).
    RetryMaxAttempts(5).
    EnableLogging(true).
    Build()
```

### 环境配置

- **测试环境**: `https://open-api-test.maiyatian.com`
- **正式环境**: `https://open-api.maiyatian.com`

> ⚠️ **重要**: 严禁使用正式地址进行接口调试！

## 🔧 错误处理

### 错误码说明

| Code | 说明 | 处理方式 |
|------|------|----------|
| 200 | 成功 | 正常处理 |
| 401 | 接口未授权 | 检查AppKey/Secret和签名 |
| 404 | 资源不存在 | 检查请求路径 |
| 422 | 参数错误 | 检查请求参数 |
| 500 | 服务器内部错误 | 自动重试 |
| 503 | 服务不可用 | 自动重试 |

### 错误处理示例

```go
resp, err := sender.DeliveryChange(ctx, token, req)
if err != nil {
    // 处理错误
    log.Printf("API调用失败: %v", err)
    return
}

if resp.Code != 200 {
    // 处理业务错误
    log.Printf("业务错误: Code=%d, Message=%s", resp.Code, resp.Message)
    return
}

// 成功处理响应
log.Printf("操作成功: %s", resp.Data)
```

### 自动重试策略

SDK会在以下情况自动重试：

1. 网络错误
2. HTTP 5xx 错误
3. 业务状态码 5xx 错误

默认重试配置：
- 最大重试次数：3次
- 重试间隔：1分钟
- 最大重试延迟：30秒

## ❓ 常见问题

### Q1: 如何获取AppKey和AppSecret?

请联系麦芽田开放平台技术团队申请沙箱环境账号，需要提供：
- 公司名称
- 联系人信息  
- 业务场景说明

### Q2: 授权码(code)如何获取?

通过OAuth2授权流程获取，详见[授权说明文档](https://console-docs.apipost.cn/preview/272c3a66e3a4af27/4c99da58f091d4ff?target_id=27366b4)。

授权码有效期**5分钟**，过期需重新授权。

### Q3: Token过期怎么办?

使用`RefreshToken`接口刷新：

```go
req := &entityAuth.RefreshTokenReq{
    Token:        oldToken,
    RefreshToken: refreshToken,
}
resp, err := sender.RefreshToken(ctx, oldToken, req)
```

**注意**: refresh_token也有过期时间（30天），建议在有效期内及时刷新。

### Q4: 签名验证失败怎么办?

1. 使用官方签名工具验证：https://open-api-test.maiyatian.com/test/
2. 检查AppKey和AppSecret是否正确
3. 确认请求体JSON格式正确
4. SDK已自动处理签名，通常不需要手动处理

### Q5: 如何处理高并发配送状态推送?

**优化建议**：
1. **连接池配置**：增加MaxConnections到50以上
2. **并发控制**：使用信号量控制并发请求数量
3. **批量处理**：将多个状态更新合并为批次处理
4. **重试策略**：配置合理的重试间隔，避免重试风暴

```go
// 高并发配置示例
config := client.NewConfigBuilder().
    MaxConnections(100).
    MaxConnectionsPerHost(20).
    RequestTimeout(10 * time.Second).
    RetryMaxAttempts(3).
    Build()

// 使用信号量控制并发
semaphore := make(chan struct{}, 10) // 限制10个并发
for _, delivery := range deliveries {
    semaphore <- struct{}{}
    go func(d Delivery) {
        defer func() { <-semaphore }()
        sender.DeliveryChange(ctx, token, d)
    }(delivery)
}
```

### Q6: Receiver接口如何实现?

Receiver接口需要三方配送服务商自己实现HTTP服务来接收麦芽田的回调请求。

SDK中的`models/receiver/entity`包含了所有Receiver接口的请求响应实体定义，可以参考这些定义实现您的HTTP服务。

### Q7: 配送状态推送频率建议？

**推送频率建议**：
- 关键状态立即推送：接单、取货、完成、取消
- 配送中状态：每5-10分钟推送一次位置
- 异常状态：立即推送，并包含详细异常信息
- 批量推送：避免频繁小批量，建议每次推送10-50个状态

## 📝 更新日志

### v1.0.0 (2025-12-16)

**🎯 配送专业特性**:
- ✅ 配送服务商授权流程完整支持
- ✅ 配送状态全生命周期管理（GRABBED→PICKUP→DELIVERING→DONE）
- ✅ 快递轨迹多节点推送支持
- ✅ 实时位置更新和距离计算
- ✅ 多模式配送支持（同城配送 + 快递配送）
- ✅ 配送异常和取消场景完整覆盖

**🔧 技术架构**:
- ✅ 完整实现所有Sender接口
- ✅ 企业级HTTP客户端和连接池管理
- ✅ HmacSHA256自动签名验证
- ✅ 智能重试和错误处理机制
- ✅ 详细的中文API文档和注释
- ✅ 完整的类型定义和枚举常量
- ✅ Go 1.24+ 兼容性支持

**📚 示例和文档**:
- ✅ 基础使用示例 - 涵盖核心配送功能
- ✅ 高级功能示例 - 批量处理、并发操作、性能分析
- ✅ 命令行运行工具 - 快速测试和验证
- ✅ 详细的使用指南和最佳实践文档
- ✅ 企业级架构说明和部署指导

**🚚 核心接口支持**:
- ✅ AccessToken - 配送服务商授权获取令牌
- ✅ RefreshToken - 访问令牌续期管理
- ✅ DeliveryChange - 配送状态实时同步
- ✅ LocationChange - 快递轨迹批量推送

## 📚 相关文档

### 📖 官方文档
- [配送开放平台接口规范](https://console-docs.apipost.cn/preview/272c3a66e3a4af27/4c99da58f091d4ff)
- [配送服务商授权流程](https://console-docs.apipost.cn/preview/272c3a66e3a4af27/4c99da58f091d4ff?target_id=27366b4)
- [配送API签名验证工具](https://open-api-test.maiyatian.com/test/)
- [配送测试后台管理](http://saas.open.test.maiyatian.com/)

### 📋 SDK文档
- [架构设计说明](./ARCHITECTURE.md) - 详细的技术架构和设计理念
- [基础使用示例](./examples/basic_usage.go) - 入门级配送功能演示
- [高级功能示例](./examples/advanced_features.go) - 企业级配送场景实现
- [示例使用指南](./examples/README.md) - 完整的配送最佳实践

### 🔧 开发工具
- [配送状态枚举定义](./consts/consts.go) - 标准配送状态常量
- [SDK版本管理](./vars/version.go) - 版本信息和更新历史

## 🤝 技术支持

如遇到问题，请联系麦芽田开放平台技术团队。

提问时请提供：
1. 问题描述
2. 接口地址
3. 请求报文
4. 返回报文

## 📄 License

MIT License

---

**🚚 专为配送服务商打造，助力配送生态数字化升级**

**Made with ❤️ by Maiyatian Delivery Platform Team**

