# 麦芽田渠道开放平台 Go SDK 架构说明

## 概念说明

### Sender vs Receiver

**Sender（发送）**：
- 定义：三方服务**主动推送**数据到麦芽田开放平台
- 目录：`models/sender/`
- 使用方：三方服务商
- 数据流向：三方 → 麦芽田

**Receiver（接收）**：
- 定义：麦芽田开放平台**主动推送**数据到三方服务（回调）
- 目录：`models/receiver/`
- 使用方：三方服务商（需要实现回调接口）
- 数据流向：麦芽田 → 三方

## 目录结构

```
go-sdk/
├── client/                          # HTTP 客户端
│   ├── config.go                   # 客户端配置
│   └── http_client.go              # HTTP 客户端实现
├── models/
│   ├── types/                      # 通用类型定义
│   │   ├── types.go               # 请求/响应结构
│   │   └── common.go              # 枚举类型
│   ├── sender/                     # 三方主动调用麦芽田的接口
│   │   ├── api/                   # API 接口实现
│   │   │   ├── base.go           # 接口定义（IChannelSender）
│   │   │   ├── order_created.go  # 新订单推送
│   │   │   ├── order_confirmed.go # 订单确认推送
│   │   │   ├── access_token.go   # 获取访问令牌
│   │   │   └── ...
│   │   └── entity/                # 请求/响应实体
│   │       ├── auth/             # 授权相关
│   │       ├── order/            # 订单相关
│   │       └── shop/             # 门店相关
│   └── receiver/                   # 麦芽田推送给三方的回调接口
│       └── entity/                # 回调数据实体
│           ├── auth/             # 授权回调
│           ├── order/            # 订单回调
│           └── shop/             # 门店回调
└── utils/                          # 工具函数
    └── tools.go                    # 通用工具
```

## 业务流程示例

### 1. 授权流程

```
1. 三方组装授权 URL (sender/entity/auth/create_code_entity.go)
   ↓
2. 商户在麦芽田授权页面完成授权
   ↓
3. 麦芽田重定向到三方回调地址，带上 code
   ↓
4. 三方调用 sender/api/AccessToken 向麦芽田换取 token
   ↓
5. 麦芽田返回 token 和 refresh_token (sender/entity/auth/access_token_entity.go)
```

### 2. 订单推送流程（三方 → 麦芽田）

```
1. 用户在三方平台下单
   ↓
2. 三方创建订单
   ↓
3. 三方调用 sender/api/OrderCreated 推送订单给麦芽田
   (使用 sender/entity/order/order_entity.go 的 CreateOrderReq)
   ↓
4. 麦芽田接收订单并返回成功
```

### 3. 订单确认回调流程（麦芽田 → 三方）

```
1. 麦芽田需要三方确认订单
   ↓
2. 麦芽田调用三方的回调接口，推送确认请求
   (使用 receiver/entity/order/order_confirm_entity.go 的 ConfirmOrderReq)
   ↓
3. 三方商户确认接单
   ↓
4. 三方调用 sender/api/OrderConfirmed 推送确认状态给麦芽田
   (使用 sender/entity/order/order_confirmed_entity.go 的 OrderConfirmedReq)
   ↓
5. 麦芽田更新订单状态为"已确认"
```

## 接口分类

### Sender 接口（三方主动调用麦芽田）

#### 必接接口
- `OrderCreated`：新订单推送
- `OrderConfirmed`：订单确认推送
- `OrderDone`：订单完成推送
- `AccessToken`：获取访问令牌

#### 订单管理
- `OrderModified`：订单修改推送
- `OrderCanceled`：订单取消推送
- `OrderRemind`：订单催单推送
- `SelfDeliveryChange`：自配送状态变更推送

#### 退款管理
- `OrderApplyRefund`：退款申请推送
- `OrderRefunded`：退款结果推送

#### 授权管理
- `AccessToken`：获取访问令牌
- `RefreshToken`：刷新访问令牌

#### 门店查询
- `ShopInfo`：查询门店信息

### Receiver 接口（麦芽田回调三方）

#### 订单回调
- `ConfirmOrder`：通知三方确认订单
- `OrderChange`：通知三方订单状态变更
- `OrderDetail`：提供订单详情查询
- `OrderList`：提供订单列表查询

#### 退款回调
- `OrderAgreeRefund`：通知三方同意退款
- `OrderRejectRefund`：通知三方拒绝退款

#### 配送回调
- `DeliveryChange`：通知三方配送状态变更
- `MealPicking`：通知三方更新出餐状态
- `MultiRiderLocation`：批量推送骑手位置

#### 催单回调
- `OrderRemindReply`：通知三方回复催单

#### 门店回调
- `ShopDetail`：提供门店详情查询
- `ShopList`：提供门店列表查询
- `ShopPrinterList`：提供打印机列表查询
- `TokenUnbind`：通知三方解绑令牌

## 开发指南

### 三方服务需要实现的内容

#### 1. 主动调用麦芽田接口（使用 Sender）

```go
// 创建 Sender 客户端
config := client.NewConfigBuilder().
    BaseURL("https://open-api.maiyatian.com").
    APIKey("your_app_key").
    APISecret("your_app_secret").
    Build()

sender := api.NewChannelSender(config)

// 推送新订单给麦芽田
resp, err := sender.OrderCreated(ctx, token, orderData)
resp, err := sender.OrderComnfirmed(ctx, token, orderData)
resp, err := sender.OrderDone(ctx, token, orderData)
```

#### 2. 实现回调接口（处理 Receiver 数据）

```go
// 三方需要提供回调接口给麦芽田
// 例如：POST http://your-domain.com/callback/order_confirm

func HandleOrderConfirmCallback(w http.ResponseWriter, r *http.Request) {
    // 1. 解析麦芽田推送的数据
    var req receiver.ConfirmOrderReq
    json.NewDecoder(r.Body).Decode(&req)
    
    // 2. 验证签名（麦芽田会带签名）
    // ... 签名验证逻辑 ...
    
    // 3. 处理确认逻辑
    // ... 三方确认订单逻辑 ...
    
    // 4. 推送确认状态给麦芽田
    sender.OrderConfirmed(ctx, token, confirmedData)
    
    // 5. 返回成功响应
    json.NewEncoder(w).Encode(map[string]any{
        "code": 200,
        "message": "ok",
    })
}
```

## 注意事项

1. **Sender 接口**：三方主动调用，SDK 已封装好，直接调用即可
2. **Receiver 接口**：三方需要实现回调接口，接收麦芽田的推送
3. **双向通信**：很多业务场景需要双向通信（麦芽田推送 → 三方处理 → 三方推送结果给麦芽田）
4. **签名验证**：接收麦芽田回调时需要验证签名，确保数据安全
