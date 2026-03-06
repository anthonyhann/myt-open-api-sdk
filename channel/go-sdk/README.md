# 麦芽田渠道开放平台 Go SDK

[![Go Version](https://img.shields.io/badge/Go-1.24+-00ADD8?style=flat&logo=go)](https://go.dev/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📖 简介

麦芽田渠道开放平台 Go SDK 是为第三方服务商提供的企业级 SDK，用于快速接入麦芽田开放平台。

### 核心特性

- ✅ **企业级质量**：对标 Apache/Google 开源 SDK 质量标准
- ✅ **双向通信**：支持三方主动调用（Sender）和接收麦芽田回调（Receiver）
- ✅ **自动签名**：自动生成符合麦芽田规范的请求签名
- ✅ **自动重试**：网络错误和 5xx 错误自动重试，支持指数退避
- ✅ **连接池管理**：复用 HTTP 连接，提升性能
- ✅ **类型安全**：完整的类型定义和参数验证
- ✅ **详细注释**：100% 中文注释覆盖，每个字段都有详细说明
- ✅ **日志记录**：可选的结构化日志，便于调试

---

## 🏗️ SDK 架构

### Sender vs Receiver

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  📤 Sender（三方 → 麦芽田）                                  │
│  ────────────────────────────────────────────────            │
│  三方服务主动推送数据到麦芽田开放平台                          │
│  • 推送新订单                                                │
│  • 推送订单状态（确认、完成、取消等）                          │
│  • 推送配送信息                                              │
│  • 推送退款信息                                              │
│  • 查询门店信息                                              │
│  • 授权管理（获取/刷新 token）                                │
│                                                             │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  📥 Receiver（麦芽田 → 三方）                                │
│  ────────────────────────────────────────────────            │
│  麦芽田主动推送数据到三方服务（回调接口）                      │
│  • 通知三方确认订单                                          │
│  • 推送配送状态变更                                          │
│  • 推送骑手位置                                              │
│  • 推送退款处理请求                                          │
│  • 通知三方解绑令牌                                          │
│  注意：三方需要实现回调接口来接收这些推送                      │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## 🚀 快速开始

### 安装

```bash
go get github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk
```

### 5分钟上手

#### 1. 创建客户端

```go
package main

import (
    "context"
    "log"
    "time"
    
    "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
    "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/api"
)

func main() {
    // 1. 创建配置
    config := client.NewConfigBuilder().
        BaseURL("https://open-api-test.maiyatian.com").  // 测试环境地址
        APIKey("your_app_key").                          // 你的应用密钥
        APISecret("your_app_secret").                    // 你的应用密钥
        RequestTimeout(30 * time.Second).                // 请求超时时间
        EnableLogging(true).                             // 启用日志
        Build()
    
    // 2. 创建 Sender 客户端（用于主动调用麦芽田）
    sender := api.NewChannelSender(config)
    
    log.Println("✅ SDK 客户端创建成功！")
}
```

#### 2. 运行完整示例

```bash
# 克隆项目
git clone https://github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk.git
cd myt-go-sdk

# 运行基础示例
go run examples/main.go -type basic

# 运行高级功能示例  
go run examples/main.go -type advanced

# 运行回调服务器示例
go run examples/main.go -type callback

# 运行所有示例
go run examples/main.go -type all
```

#### 3. 查看示例代码

| 示例文件 | 功能说明 | 适用场景 |
|---------|---------|---------|
| [`examples/basic_usage.go`](examples/basic_usage.go) | 基础功能示例 | 新手入门 |
| [`examples/advanced_features.go`](examples/advanced_features.go) | 高级功能示例 | 进阶开发 |
| [`examples/receiver_callbacks.go`](examples/receiver_callbacks.go) | 回调服务器示例 | 服务端集成 |
| [`examples/README.md`](examples/README.md) | 详细使用说明 | 完整参考 |

---

## 📤 Sender 接口使用（三方主动调用麦芽田）

### 1. 授权流程

#### 步骤 1：引导商户授权

```go
import authEntity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/auth"

// 组装授权页面 URL
authURL := fmt.Sprintf(
    "https://saas.maiyatian.com/oauth/?app_key=%s&redirect_uri=%s&view=web&response_type=code&shop_id=%s&state=%s",
    "your_app_key",
    "https://your-domain.com/callback",
    "shop_123",
    "custom_state",
)

// 引导商户访问此 URL 完成授权
fmt.Println("请访问以下地址完成授权：", authURL)
```

#### 步骤 2：获取访问令牌

```go
// 商户授权成功后，从回调地址获取 code
// 例如：https://your-domain.com/callback?code=AUTH_CODE&state=custom_state

req := &authEntity.AccessTokenReq{
    GrantType: "shop",
    Code:      "AUTH_CODE",        // 从回调获取的授权码
    ShopId:    "shop_123",
    Category:  "canyin",
    Name:      "测试门店",
    Type:      "waimai",
    Longitude: "116.397428",
    Latitude:  "39.90923",
}

// 向麦芽田换取访问令牌（注意：token 参数传空字符串）
resp, err := sender.AccessToken(context.Background(), "", req)
if err != nil {
    log.Fatalf("获取访问令牌失败: %v", err)
}

// 解析响应，保存 token
var tokenResp authEntity.AccessTokenResp
json.Unmarshal([]byte(resp.Data), &tokenResp)
fmt.Printf("访问令牌: %s\n", tokenResp.Token)
```

#### 步骤 3：刷新访问令牌

```go
refreshReq := &authEntity.RefreshTokenReq{
    Token:        currentToken,
    RefreshToken: currentRefreshToken,
}

resp, err := sender.RefreshToken(context.Background(), currentToken, refreshReq)
if err != nil {
    log.Fatalf("刷新令牌失败: %v", err)
}

// 保存新的 token 和 refresh_token
```

---

### 2. 订单推送（必接）

#### 推送新订单

```go
import orderEntity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"

// 当用户在三方平台下单并支付成功后，推送订单给麦芽田
orderReq := &orderEntity.CreateOrderReq{
    OrderBaseData: orderEntity.OrderBaseData{
        Order: orderEntity.OrderInfo{
            OrderId:      "order_123456",
            OrderSn:      123456,
            ShopId:       "shop_123",
            ShopName:     "测试门店",
            OriginTag:    "youzan2",
            Category:     "canyin",
            IsPreOrder:   false,
            TotalPrice:   5000,  // 50元（单位：分）
            BalancePrice: 4500,  // 实付45元
            CreateTime:   time.Now().Unix(),
            DeliveryTime: time.Now().Add(1 * time.Hour).Unix(),
            DeliveryEnd:  time.Now().Add(2 * time.Hour).Unix(),
            DeliveryType: 1,  // 1-同城配送
            IsPicker:     false,
            UserRemark:   "少放辣椒",
            OrderFee: orderEntity.OrderFeeInfo{
                TotalFee:    5000,
                SendFee:     500,
                PackageFee:  100,
                DiscountFee: 500,
                ShopFee:     4400,
                UserFee:     4500,
                PayType:     2,  // 2-在线支付
            },
            OrderGoods: []orderEntity.OrderGoodsInfo{
                {
                    GoodsId:       "goods_001",
                    GoodsName:     "宫保鸡丁",
                    SkuId:         "sku_001",
                    SkuName:       "中份",
                    Number:        1,
                    GoodsPrice:    2000,
                    GoodsTotalFee: 2000,
                    Unit:          "份",
                },
            },
        },
        OrderCustomer: orderEntity.OrderCustomerInfo{
            RealName:  "张*生",
            Phone:     "13800138000",
            Address:   "朝阳区某某街道某某号",
            Longitude: "116.397428",
            Latitude:  "39.90923",
        },
        UpdateTime: time.Now().Unix(),
    },
}

// 推送新订单给麦芽田
resp, err := sender.OrderCreated(ctx, token, orderReq)
if err != nil {
    log.Printf("推送订单失败: %v", err)
    return
}

if resp.Code == 200 {
    log.Println("订单推送成功！")
} else {
    log.Printf("订单推送失败: [%d] %s", resp.Code, resp.Message)
}
```

#### 推送订单确认

```go
// 当三方商户确认接单后，推送确认状态给麦芽田
confirmReq := &orderEntity.OrderConfirmedReq{
    OrderId:    "order_123456",
    ShopId:     "shop_123",
    UpdateTime: time.Now().Unix(),
}

resp, err := sender.OrderConfirmed(ctx, token, confirmReq)
if err != nil {
    log.Printf("推送确认状态失败: %v", err)
    return
}

log.Println("确认状态推送成功！")
```

#### 推送订单完成

```go
// 当订单配送完成后，推送完成状态给麦芽田
doneReq := &orderEntity.OrderDoneReq{
    OrderId:    "order_123456",
    ShopId:     "shop_123",
    UpdateTime: time.Now().Unix(),
}

resp, err := sender.OrderDone(ctx, token, doneReq)
if err != nil {
    log.Printf("推送完成状态失败: %v", err)
    return
}

log.Println("完成状态推送成功！")
```

---

### 3. 配送状态推送

#### 推送配送状态变更

```go
// 当三方自配送订单状态变化时，推送给麦芽田
deliveryReq := &orderEntity.DeliveryChangeReq{
    OrderId:     "order_123456",
    ShopId:      "shop_123",
    Status:      "DELIVERING",  // 配送中
    RiderName:   "张三",
    RiderPhone:  "13800138000",
    LogisticNo:  "SF1234567890",
    LogisticTag: "shunfeng",
    Longitude:   "116.397428",
    Latitude:    "39.90923",
    UpdateTime:  time.Now().Unix(),
}

resp, err := sender.SelfDeliveryChange(ctx, token, deliveryReq)
```

#### 批量推送骑手位置

```go
import senderOrder "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"

locationReq := &senderOrder.MultiRiderLocationReq{
    Locations: []*senderOrder.RiderLocation{
        {
            OrderId:     "order_001",
            ShopId:      "shop_123",
            Status:      "DELIVERING",
            RiderName:   "张三",
            RiderPhone:  "13800138000",
            LogisticNo:  "SF001",
            LogisticTag: "shunfeng",
            Longitude:   "116.397428",
            Latitude:    "39.90923",
            Distance:    500,  // 距离顾客 500 米
            UpdateTime:  time.Now().Unix(),
        },
    },
}

// 注意：批量推送骑手位置需要调用对应的 HTTP 接口
// 可以使用底层的 HTTP 客户端
```

---

### 4. 退款管理

#### 推送退款申请

```go
// 当用户或商家发起退款时，推送退款申请给麦芽田
refundReq := &orderEntity.OrderApplyRefundReq{
    OrderId:    "order_123456",
    ShopId:     "shop_123",
    RefundId:   "refund_001",
    Type:       1,  // 1-仅退款
    Reason:     "商品质量问题",
    TotalPrice: 2000,  // 退款金额 20 元
    Count:      1,
    Goods: []orderEntity.OrderApplyRefundGoodsInfo{
        {
            GoodsId:       "goods_001",
            GoodsName:     "宫保鸡丁",
            SkuId:         "sku_001",
            Number:        1,
            GoodsTotalFee: 2000,
            RefundFee:     2000,
        },
    },
    UpdateTime: time.Now().Unix(),
}

resp, err := sender.OrderApplyRefund(ctx, token, refundReq)
```

#### 推送退款结果

```go
// 三方处理退款后，推送退款结果给麦芽田
refundedReq := &orderEntity.OrderRefundedReq{
    OrderId:    "order_123456",
    ShopId:     "shop_123",
    RefundId:   "refund_001",
    Status:     1,  // 1-退款成功
    Type:       1,
    Reason:     "商品质量问题，已退款",
    TotalPrice: 2000,
    Count:      1,
    Goods:      /* 退款商品列表 */,
    UpdateTime: time.Now().Unix(),
}

resp, err := sender.OrderRefunded(ctx, token, refundedReq)
```

---

### 5. 门店信息查询

```go
import shopEntity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/shop"

// 查询门店在麦芽田平台的信息和状态
shopReq := &shopEntity.ShopInfoReq{
    ShopId: "shop_123",
}

resp, err := sender.ShopInfo(ctx, token, shopReq)
if err != nil {
    log.Printf("查询门店信息失败: %v", err)
    return
}

// 解析门店信息
var shopInfo shopEntity.ShopInfoResp
json.Unmarshal([]byte(resp.Data), &shopInfo)
fmt.Printf("门店名称: %s, 状态: %d\n", shopInfo.ShopName, shopInfo.Status)
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

#### 1. 确认订单回调

```go
import (
    "encoding/json"
    "net/http"
    
    receiverEntity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/receiver/entity/order"
    senderEntity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
)

// 三方需要提供此回调接口给麦芽田
// 回调地址示例：POST https://your-domain.com/callback/order_confirm
func HandleOrderConfirmCallback(w http.ResponseWriter, r *http.Request) {
    ctx := r.Context()
    
    // 1. 解析麦芽田推送的确认订单请求
    var callbackReq struct {
        AppKey    string `json:"app_key"`
        Token     string `json:"token"`
        Timestamp int64  `json:"timestamp"`
        Data      string `json:"data"`
        Signature string `json:"signature"`
    }
    
    if err := json.NewDecoder(r.Body).Decode(&callbackReq); err != nil {
        http.Error(w, "解析请求失败", http.StatusBadRequest)
        return
    }
    
    // 2. 验证签名（必须！确保请求来自麦芽田）
    // TODO: 实现签名验证逻辑
    
    // 3. 解析业务数据
    var confirmReq receiverEntity.ConfirmOrderReq
    if err := json.Unmarshal([]byte(callbackReq.Data), &confirmReq); err != nil {
        http.Error(w, "解析业务数据失败", http.StatusBadRequest)
        return
    }
    
    // 4. 处理确认订单逻辑
    // TODO: 三方执行确认订单的业务逻辑
    log.Printf("收到订单确认请求: OrderId=%s, ShopId=%s", confirmReq.OrderId, confirmReq.ShopId)
    
    // 5. 推送确认状态给麦芽田（使用 Sender 接口）
    confirmData := &senderEntity.OrderConfirmedReq{
        OrderId:    confirmReq.OrderId,
        ShopId:     confirmReq.ShopId,
        UpdateTime: time.Now().Unix(),
    }
    
    if _, err := sender.OrderConfirmed(ctx, callbackReq.Token, confirmData); err != nil {
        log.Printf("推送确认状态失败: %v", err)
    }
    
    // 6. 返回标准响应给麦芽田
    w.Header().Set("Content-Type", "application/json;charset=utf-8")
    json.NewEncoder(w).Encode(map[string]any{
        "code":    200,
        "message": "ok",
    })
}
```

#### 2. 配送状态变更回调

```go
// 三方回调接口：POST https://your-domain.com/callback/delivery_change
func HandleDeliveryChangeCallback(w http.ResponseWriter, r *http.Request) {
    // 1. 解析麦芽田推送的配送状态变更
    var deliveryChangeReq receiverEntity.DeliveryChangeReq
    // ... 解析逻辑 ...
    
    // 2. 验证签名
    
    // 3. 同步配送状态到三方系统
    log.Printf("配送状态变更: OrderId=%s, Status=%s, RiderName=%s", 
        deliveryChangeReq.OrderId, 
        deliveryChangeReq.Status, 
        deliveryChangeReq.RiderName)
    
    // 4. 返回成功响应
    json.NewEncoder(w).Encode(map[string]any{
        "code":    200,
        "message": "ok",
    })
}
```

#### 3. 退款处理回调

```go
// 同意退款回调
func HandleAgreeRefundCallback(w http.ResponseWriter, r *http.Request) {
    var agreeRefundReq receiverEntity.OrderAgreeRefundReq
    // ... 解析和验证 ...
    
    // 处理同意退款逻辑
    log.Printf("麦芽田通知同意退款: OrderId=%s, RefundId=%s", 
        agreeRefundReq.OrderId, 
        agreeRefundReq.RefundId)
    
    // 返回响应
    json.NewEncoder(w).Encode(map[string]any{
        "code":    200,
        "message": "ok",
    })
}

// 拒绝退款回调
func HandleRejectRefundCallback(w http.ResponseWriter, r *http.Request) {
    var rejectRefundReq receiverEntity.OrderRejectRefundReq
    // ... 解析和验证 ...
    
    // 处理拒绝退款逻辑
    log.Printf("麦芽田通知拒绝退款: OrderId=%s, Reason=%s", 
        rejectRefundReq.OrderId, 
        rejectRefundReq.Reason)
    
    // 返回响应
    json.NewEncoder(w).Encode(map[string]any{
        "code":    200,
        "message": "ok",
    })
}
```

---

## 📋 必接接口清单

### Sender 接口（三方必须主动调用）

| 接口 | 说明 | 调用时机 |
|------|------|----------|
| ✅ **OrderCreated** | 推送新订单 | 用户下单并支付成功后 |
| ✅ **OrderConfirmed** | 推送订单确认 | 商户确认接单后 |
| ✅ **OrderDone** | 推送订单完成 | 订单配送完成后 |
| ✅ **AccessToken** | 获取访问令牌 | 商户授权后换取 token |

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
2. 按 key 进行 ksort 排序
3. 用半角逗号连接生成字符串（格式：`key1=value1,key2=value2`）
4. 对字符串进行 UTF8 编码
5. 使用 `app_secret` 计算 HmacSHA256 值
6. 结果使用 URL 安全的 Base64 编码

### 回调签名验证（三方必须实现）

接收麦芽田回调时，必须验证签名：

```go
import (
    "crypto/hmac"
    "crypto/sha256"
    "encoding/base64"
    "sort"
)

func VerifySignature(appSecret string, req map[string]any, signature string) bool {
    // 1. 提取并排序 key
    keys := make([]string, 0, len(req))
    for k := range req {
        keys = append(keys, k)
    }
    sort.Strings(keys)
    
    // 2. 构建待签名字符串
    dataToSign := ""
    for _, k := range keys {
        dataToSign += fmt.Sprintf("%s=%v,", k, req[k])
    }
    dataToSign = dataToSign[:len(dataToSign)-1]
    
    // 3. 计算 HmacSHA256
    mac := hmac.New(sha256.New, []byte(appSecret))
    mac.Write([]byte(dataToSign))
    expectedSign := base64.URLEncoding.EncodeToString(mac.Sum(nil))
    
    // 4. 比较签名
    return expectedSign == signature
}
```

---

## ⚙️ 配置说明

### 环境配置

```go
// 测试环境
testConfig := client.NewConfigBuilder().
    BaseURL("https://open-api-test.maiyatian.com").
    APIKey("test_app_key").
    APISecret("test_app_secret").
    Build()

// 正式环境
prodConfig := client.NewConfigBuilder().
    BaseURL("https://open-api.maiyatian.com").
    APIKey("prod_app_key").
    APISecret("prod_app_secret").
    Build()
```

### 高级配置

```go
config := client.NewConfigBuilder().
    BaseURL("https://open-api.maiyatian.com").
    APIKey("your_app_key").
    APISecret("your_app_secret").
    // 连接池配置
    MaxConnections(100).                         // 最大连接数（默认 50）
    // 超时配置
    RequestTimeout(60 * time.Second).            // 请求超时（默认 60 秒）
    // 重试配置
    RetryMaxAttempts(3).                         // 最大重试次数（默认 3）
    // 日志配置
    EnableLogging(true).                         // 启用日志（默认 true）
    Build()
```

---

## 📚 枚举类型说明

### 订单状态（OrderStatus）

```go
import "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/types"

const (
    types.StatusCreated    = "CREATED"     // 待确认
    types.StatusConfirm    = "CONFIRM"     // 已确认
    types.StatusDelivering = "DELIVERING"  // 配送中
    types.StatusDone       = "DONE"        // 已完成
    types.StatusCancel     = "CANCEL"      // 已取消
    // ... 更多状态
)
```

### 配送状态（DeliveryStatus）

```go
const (
    types.DeliveryStatusPending    = "PENDING"     // 待处理
    types.DeliveryStatusGrabbed    = "GRABBED"     // 已分配骑手
    types.DeliveryStatusDelivering = "DELIVERING"  // 配送中
    types.DeliveryStatusDone       = "DONE"        // 已完成
    // ... 更多状态
)
```

### 订单分类（Category）

```go
const (
    types.CategoryCanyin  = "canyin"   // 正餐快餐
    types.CategoryShaokao = "shaokao"  // 龙虾烧烤
    types.CategoryTianpin = "tianpin"  // 甜品奶茶
    // ... 更多分类
)
```

完整的枚举类型定义请参考 `models/types/common.go`

---

## ⚠️ 重要说明

### 1. 时间格式
- 所有时间字段使用 **Unix 时间戳**（单位：**秒**）
- 请求时间戳需与实际时间误差不超过 **10 分钟**

### 2. 金额单位
- 所有金额字段单位为 **"分"**（1 元 = 100 分）
- 避免使用浮点数，防止精度问题

### 3. 坐标系统
- 所有经纬度使用 **国测局 GCJ-02 标准**（如高德地图坐标）
- 不要使用 WGS84 或 BD09 坐标

### 4. 必须实现的内容

**Sender 接口（三方主动调用）**：
- ✅ OrderCreated - 推送新订单
- ✅ OrderConfirmed - 推送订单确认
- ✅ OrderDone - 推送订单完成
- ✅ AccessToken - 获取访问令牌

**Receiver 接口（三方实现回调）**：
- ✅ 提供回调接口地址
- ✅ 实现回调处理逻辑
- ✅ 验证请求签名
- ✅ 返回标准响应

---

## 🔍 错误处理

### 标准错误处理

```go
resp, err := sender.OrderCreated(ctx, token, orderData)
if err != nil {
    // 网络错误或请求失败
    log.Printf("请求失败: %v", err)
    return
}

// 检查业务状态码
if resp.Code != 200 {
    log.Printf("业务错误 [%d]: %s", resp.Code, resp.Message)
    return
}

// 解析响应数据
var data YourDataType
if err := json.Unmarshal([]byte(resp.Data), &data); err != nil {
    log.Printf("解析响应失败: %v", err)
    return
}

log.Println("请求成功！")
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
- 使用：`sender := api.NewChannelSender(config)`

**Receiver**：麦芽田**主动推送**数据给三方（回调）
- 例如：通知确认订单、推送配送状态、推送骑手位置
- 使用：三方需要实现 HTTP 回调接口

### Q2: 如何处理授权过期？

```go
// 检测 token 是否过期
if resp.Code == 401 {
    // 使用 refresh_token 刷新
    refreshReq := &authEntity.RefreshTokenReq{
        Token:        currentToken,
        RefreshToken: currentRefreshToken,
    }
    
    newResp, err := sender.RefreshToken(ctx, currentToken, refreshReq)
    if err != nil {
        // 刷新失败，需要重新授权
        log.Println("刷新令牌失败，请重新授权")
        return
    }
    
    // 保存新的 token 和 refresh_token
    var tokenResp authEntity.RefreshTokenResp
    json.Unmarshal([]byte(newResp.Data), &tokenResp)
}
```

### Q3: 如何启用详细日志？

```go
config := client.NewConfigBuilder().
    BaseURL("https://open-api-test.maiyatian.com").
    APIKey("your_app_key").
    APISecret("your_app_secret").
    EnableLogging(true).       // 启用日志
    LogRequestBody(true).      // 记录请求体（注意敏感信息）
    LogResponseBody(true).     // 记录响应体
    Build()
```

### Q4: 签名验证失败怎么办？

**检查清单**：
1. ✅ 确认 `app_key` 和 `app_secret` 是否正确
2. ✅ 确认系统时间是否准确（误差不超过 10 分钟）
3. ✅ 使用麦芽田提供的签名校验工具验证：https://open-api-test.maiyatian.com/test/

### Q5: 订单号使用哪个？

- **三方推送给麦芽田**：使用三方平台的订单号（`OrderId`）
- **字段说明**：
  - `OrderId`：三方平台的订单唯一 ID
  - `OrderNo`：麦芽田平台生成的订单号（部分接口会返回）
  - `SourceOrderNo`：原始订单号（三方平台的订单号）

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

### 📋 核心文档
- [ARCHITECTURE.md](ARCHITECTURE.md) - SDK 架构详细说明
- [examples/README.md](examples/README.md) - **示例代码完整指南** ⭐
- [CHANGELOG.md](CHANGELOG.md) - 版本更新日志

### 🎯 示例代码
- [examples/basic_usage.go](examples/basic_usage.go) - 基础功能示例（授权、订单、门店）
- [examples/advanced_features.go](examples/advanced_features.go) - 高级功能示例（退款、配送、并发）
- [examples/receiver_callbacks.go](examples/receiver_callbacks.go) - 回调服务器示例（完整实现）
- [examples/main.go](examples/main.go) - 统一运行入口

### 📚 技术文档  
- [SDK_ANNOTATION_COMPLETION.md](SDK_ANNOTATION_COMPLETION.md) - 注释完善报告

---

## 💬 技术支持

如有问题，请联系麦芽田技术支持团队。

---

## 📜 版本历史

### v1.1.0 (2025-12-16) 
- ✅ **重大更新**：完善代码注释和文档系统
- ✅ **新增**：完整的示例代码库（examples目录）
  - 基础功能示例：授权、订单管理、门店查询
  - 高级功能示例：退款、配送、并发操作
  - 回调服务器示例：完整的HTTP服务器实现
  - 统一运行入口：命令行参数控制
- ✅ **优化**：常量和变量结构重组（consts/、vars/ 目录）
- ✅ **改进**：Go 1.24.0 兼容性和依赖更新
- ✅ **增强**：调试模式和环境变量支持
- ✅ **完善**：所有文件100%中文注释覆盖

### v1.0.0 (2025-12-08)
- ✅ 初始版本发布
- ✅ 支持所有渠道开放平台 API
- ✅ 企业级特性：连接池、重试、签名、日志
- ✅ 完整的中文注释和使用文档
- ✅ 修复签名算法问题
- ✅ 纠正 Sender/Receiver 概念

---

## 📋 License

MIT License

Copyright (c) 2025 麦芽田开放平台

---

**🚀 开始使用麦芽田渠道开放平台 Go SDK，快速接入麦芽田生态！**

