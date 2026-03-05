# 麦芽田配送开放平台 Go SDK 示例代码

本目录包含了麦芽田配送开放平台 Go SDK 的完整使用示例，专门针对配送服务商和物流公司的业务场景。

## 📁 文件说明

### [`basic_usage.go`](basic_usage.go)
**基础使用示例** - 适合配送服务商入门
- ✅ 配送SDK客户端配置和创建
- ✅ 配送服务商授权流程
- ✅ 配送状态同步（接单、取货、配送中、完成）
- ✅ 快递轨迹回传
- ✅ 配送取消和异常处理
- ✅ 错误处理最佳实践

### [`advanced_features.go`](advanced_features.go)
**高级功能示例** - 适合大型配送服务商
- ⚡ 批量配送状态更新
- ⚡ 实时位置追踪
- ⚡ 多模式配送（同城配送 vs 快递配送）
- ⚡ 配送性能分析
- ⚡ 高并发配送处理

## 🚀 快速开始

### 1. 运行基础示例

```go
package main

import "github.com/maiyatian/delivery/myt-go-sdk/examples"

func main() {
    // 运行所有基础示例
    examples.RunAllExamples()
}
```

### 2. 运行高级功能示例

```go
package main

import "github.com/maiyatian/delivery/myt-go-sdk/examples"

func main() {
    // 运行所有高级功能示例
    examples.RunAdvancedExamples()
}
```

### 3. 命令行快速测试

```bash
# 进入SDK目录
cd sdk-generator/generated-sdks/delivery/go-sdk

# 创建测试文件
cat > main.go << 'EOF'
package main

import "github.com/maiyatian/delivery/myt-go-sdk/examples"

func main() {
    examples.RunAllExamples()
}
EOF

# 运行示例
go mod tidy
go run main.go
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

### 环境变量配置（推荐）
```bash
export MYT_DELIVERY_API_URL=https://open-api-test.maiyatian.com
export MYT_DELIVERY_APP_KEY=your_app_key
export MYT_DELIVERY_APP_SECRET=your_app_secret
export DEBUG=true  # 开启调试模式
```

## 📋 配送SDK功能清单

### 🔐 授权管理
- [x] 配送服务商授权URL组装
- [x] 获取访问令牌
- [x] 刷新访问令牌
- [x] 处理授权过期

### 🚚 配送状态管理
- [x] 推送配送状态变更（必接）
  - 已分配骑手（GRABBED）
  - 待取货（PICKUP）
  - 配送中（DELIVERING）
  - 已完成（DONE）
  - 已取消（CANCEL）
  - 骑手取消（RIDER_CANCEL）
  - 骑手转单（TRANSFER）
  - 配送异常（EXPECT）
- [x] 实时位置更新
- [x] 预计送达时间推送
- [x] 配送距离信息

### 📦 快递轨迹管理
- [x] 推送快递轨迹信息（必接）
- [x] 多节点轨迹批量推送
- [x] 跨城轨迹追踪
- [x] 轨迹状态同步

### ⚡ 高级特性
- [x] 批量状态更新
- [x] 并发处理能力
- [x] 多模式配送支持
- [x] 性能分析和监控
- [x] 自动重试机制
- [x] 连接池管理
- [x] 详细日志记录

## 🔧 最佳实践

### 1. 配置管理
```go
// 推荐：使用环境变量管理敏感信息
config := client.NewConfigBuilder().
    BaseURL(os.Getenv("MYT_DELIVERY_API_URL")).
    APIKey(os.Getenv("MYT_DELIVERY_APP_KEY")).
    APISecret(os.Getenv("MYT_DELIVERY_APP_SECRET")).
    EnableLogging(true).
    Build()
```

### 2. 配送状态同步
```go
// 及时推送状态变更
deliveryReq := &entityDelivery.DeliveryChangeReq{
    OrderNo:       orderNo,
    SourceOrderNo: sourceOrderNo,
    ShopId:        shopId,
    Status:        "DELIVERING",
    RiderName:     "张骑手",
    RiderPhone:    "138****0001",
    Longitude:     "116.397428", // 使用国测局GCJ-02坐标
    Latitude:      "39.90923",
    UpdateTime:    time.Now().Unix(),
}

resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
```

### 3. 快递轨迹推送
```go
// 批量推送轨迹节点
locationReq := &entityExpress.LocationChangeReq{
    OrderNo:       orderNo,
    SourceOrderNo: sourceOrderNo,
    ShopId:        shopId,
    Locations: []entityExpress.Location{
        {
            Description: "[北京市]快件已揽收",
            City:        "北京市",
            Status:      "PICKUP",
            UpdateTime:  time.Now().Unix(),
        },
        // ... 更多轨迹节点
    },
}

resp, err := sender.LocationChange(ctx, token, locationReq)
```

### 4. 错误处理
```go
resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
if err != nil {
    // 网络或系统错误
    log.Printf("请求失败: %v", err)
    return
}

if resp.Code != 200 {
    // 业务错误
    log.Printf("业务错误 [%d]: %s", resp.Code, resp.Message)
    // 根据错误码进行相应处理
    return
}
```

### 5. 并发控制
```go
// 使用信号量限制并发数
semaphore := make(chan struct{}, 10) // 最多10个并发

for _, delivery := range deliveries {
    semaphore <- struct{}{} // 获取许可
    go func(d Delivery) {
        defer func() { <-semaphore }() // 释放许可
        sender.DeliveryChange(ctx, token, d)
    }(delivery)
}
```

## 🔍 调试技巧

### 1. 启用详细日志
```go
config := client.NewConfigBuilder().
    // ... 其他配置
    EnableLogging(true).      // 启用日志
    LogRequestBody(true).     // 记录请求体
    LogResponseBody(true).    // 记录响应体
    Build()
```

### 2. 使用调试模式
```bash
# 设置环境变量启用调试
export DEBUG=true
go run examples/basic_usage.go
```

## 📚 配送业务场景

### 同城配送场景
- ⭐ 餐饮外卖配送
- ⭐ 生鲜即时配送
- ⭐ 商超便民配送
- ⭐ 药品紧急配送

### 快递配送场景
- 📦 电商包裹配送
- 📦 跨城快递服务
- 📦 大件货物配送
- 📦 特殊物品配送

### 企业级应用
- 🏢 大型配送公司系统集成
- 🏢 多城市配送网络管理
- 🏢 配送数据分析平台
- 🏢 配送效率优化系统

## 🛠️ 常见问题

### Q1: 配送状态如何选择？
**状态说明**：
- `GRABBED`: 骑手已接单，准备取货
- `PICKUP`: 骑手已到达商家，准备取货
- `DELIVERING`: 骑手已取货，正在配送
- `DONE`: 配送已完成
- `CANCEL`: 配送已取消
- `RIDER_CANCEL`: 骑手主动取消
- `EXPECT`: 配送异常

### Q2: 坐标系统要求？
**坐标标准**：
- 使用国测局 GCJ-02 标准（如高德地图坐标）
- 不要使用 WGS84 或 BD09 坐标
- 经纬度格式：字符串类型，如 "116.397428"

### Q3: 如何处理高并发？
**优化建议**：
1. 增加连接池大小：`MaxConnections(50)`
2. 控制并发请求数量：使用信号量
3. 实现请求限流：避免过于频繁的调用
4. 使用批量接口：减少网络开销

### Q4: 轨迹推送频率？
**推送建议**：
- 关键节点必须推送：揽收、中转、派送、签收
- 配送中状态：建议每5-10分钟推送一次位置
- 异常状态：立即推送
- 完成状态：必须及时推送

## 📚 相关文档

- [SDK 架构说明](../ARCHITECTURE.md)
- [API 接口文档](../README.md)
- [麦芽田配送开放平台官方文档](https://open.maiyatian.com/delivery/docs)

## 💬 技术支持

如有问题，请联系：
- 📧 配送技术支持邮箱
- 💬 配送开发者群组  
- 📞 配送技术支持电话

---

**🚚 开始使用麦芽田配送开放平台 Go SDK，快速接入配送生态！**