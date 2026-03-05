# 麦芽田开放平台 Go SDK 示例代码

本目录包含了麦芽田开放平台 Go SDK 的完整使用示例，涵盖了从基础配置到高级功能的各种使用场景。

## 📁 文件说明

### [`basic_usage.go`](basic_usage.go)
**基础使用示例** - 适合初学者
- ✅ 客户端配置和创建
- ✅ 授权流程（获取令牌、刷新令牌）
- ✅ 订单管理（新订单、确认、完成）
- ✅ 门店信息查询
- ✅ 错误处理最佳实践

### [`advanced_features.go`](advanced_features.go)
**高级功能示例** - 适合进阶开发
- ⚡ 退款管理（申请退款、处理结果）
- ⚡ 配送状态同步
- ⚡ 订单修改和取消
- ⚡ 订单催单功能
- ⚡ 并发操作和性能优化

### [`receiver_callbacks.go`](receiver_callbacks.go)
**回调接口示例** - 服务器端实现
- 🔄 订单确认回调处理
- 🚚 配送状态变更回调
- 💰 退款处理回调
- 🔐 签名验证机制
- 🚀 HTTP服务器搭建

## 🚀 快速开始

### 1. 运行基础示例

```go
package main

import "github.com/maiyatian/channel/myt-go-sdk/examples"

func main() {
    // 运行所有基础示例
    examples.RunAllExamples()
}
```

### 2. 运行高级功能示例

```go
package main

import "github.com/maiyatian/channel/myt-go-sdk/examples"

func main() {
    // 运行所有高级功能示例
    examples.RunAdvancedExamples()
}
```

### 3. 运行回调服务器示例

```go
package main

import "github.com/maiyatian/channel/myt-go-sdk/examples"

func main() {
    // 运行回调接口示例
    examples.RunCallbackExamples()
}
```

## ⚙️ 配置说明

在运行示例前，请确保正确配置以下信息：

### 必需配置
```go
config := client.NewConfigBuilder().
    BaseURL("https://open-api-test.maiyatian.com"). // API地址（测试/正式）
    APIKey("your_app_key").                         // 你的应用密钥
    APISecret("your_app_secret").                   // 你的应用密钥
    Build()
```

### 环境地址
- **测试环境**：`https://open-api-test.maiyatian.com`
- **正式环境**：`https://open-api.maiyatian.com`

## 📋 示例功能清单

### 🔐 授权管理
- [x] 组装授权URL
- [x] 获取访问令牌
- [x] 刷新访问令牌
- [x] 处理授权过期

### 📦 订单管理
- [x] 推送新订单
- [x] 推送订单确认
- [x] 推送订单完成
- [x] 推送订单修改
- [x] 推送订单取消
- [x] 推送订单催单

### 💰 退款管理
- [x] 推送退款申请
- [x] 推送退款结果
- [x] 处理退款回调

### 🚚 配送管理
- [x] 推送配送状态变更
- [x] 批量推送骑手位置
- [x] 处理配送回调

### 🏪 门店管理
- [x] 查询门店信息
- [x] 处理门店状态

### 📞 回调处理
- [x] 订单确认回调
- [x] 配送状态变更回调
- [x] 退款处理回调
- [x] 令牌解绑回调
- [x] 签名验证

### ⚡ 高级特性
- [x] 并发操作
- [x] 连接池管理
- [x] 自动重试机制
- [x] 日志记录
- [x] 错误处理

## 🔧 最佳实践

### 1. 配置管理
```go
// 推荐：使用环境变量管理敏感信息
config := client.NewConfigBuilder().
    BaseURL(os.Getenv("MYT_API_URL")).
    APIKey(os.Getenv("MYT_APP_KEY")).
    APISecret(os.Getenv("MYT_APP_SECRET")).
    Build()
```

### 2. 错误处理
```go
resp, err := sender.OrderCreated(ctx, token, orderData)
if err != nil {
    // 网络或系统错误
    log.Printf("请求失败: %v", err)
    return
}

if resp.Code != 200 {
    // 业务错误
    log.Printf("业务错误 [%d]: %s", resp.Code, resp.Message)
    return
}
```

### 3. 令牌管理
```go
// 检查令牌是否过期
if resp.Code == 401 {
    // 尝试刷新令牌
    newResp, err := sender.RefreshToken(ctx, currentToken, refreshReq)
    if err == nil && newResp.Code == 200 {
        // 使用新令牌重试
        currentToken = newResp.Data.Token
    } else {
        // 重新授权
        redirectToAuthPage()
    }
}
```

### 4. 并发控制
```go
// 使用信号量限制并发数
semaphore := make(chan struct{}, 10) // 最多10个并发

for _, order := range orders {
    semaphore <- struct{}{} // 获取许可
    go func(o Order) {
        defer func() { <-semaphore }() // 释放许可
        sender.OrderCreated(ctx, token, o)
    }(order)
}
```

## 🔍 调试技巧

### 1. 启用详细日志
```go
config := client.NewConfigBuilder().
    // ... 其他配置
    EnableLogging(true).      // 启用日志
    LogRequestBody(true).     // 记录请求体（注意敏感信息）
    LogResponseBody(true).    // 记录响应体
    Build()
```

### 2. 使用调试模式
```bash
# 设置环境变量启用调试
export DEBUG=true
go run examples/basic_usage.go
```

### 3. 网络问题排查
```go
// 调整超时和重试设置
config := client.NewConfigBuilder().
    // ... 其他配置
    RequestTimeout(60 * time.Second). // 增加超时时间
    RetryMaxAttempts(5).              // 增加重试次数
    Build()
```

## 🛠️ 常见问题

### Q1: 签名验证失败
**原因**：时间不同步、密钥错误、编码问题
**解决**：
1. 检查系统时间是否准确
2. 确认 app_key 和 app_secret 正确
3. 使用麦芽田提供的签名校验工具验证

### Q2: 授权码无效
**原因**：code 超时或已使用
**解决**：
1. 确保在5分钟内使用 code
2. 每个 code 只能使用一次
3. 重新引导用户授权

### Q3: 回调接口无响应
**原因**：网络不通、签名验证失败
**解决**：
1. 确保回调地址可从外网访问
2. 检查签名验证逻辑
3. 返回正确的响应格式

### Q4: 并发请求失败
**原因**：连接池不足、频率限制
**解决**：
1. 增加连接池大小
2. 控制并发请求数量
3. 实现请求限流

## 📚 相关文档

- [SDK 架构说明](../ARCHITECTURE.md)
- [API 接口文档](../README.md)
- [常见问题解答](../README.md#常见问题)
- [麦芽田开放平台官方文档](https://open.maiyatian.com/docs)

## 💬 技术支持

如有问题，请联系：
- 📧 技术支持邮箱
- 💬 开发者群组
- 📞 技术支持电话

---

**🚀 开始使用麦芽田开放平台 Go SDK，快速接入麦芽田生态！**