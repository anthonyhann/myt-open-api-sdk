# myt-open-api-sdk

麦芽田开放平台多语言企业级 SDK 集合，为品牌商家提供全渠道订单管理、全网配送运力接入能力，也为配送平台提供全渠道订单推送与配送状态回传的标准封装。

---

## 项目简介

本仓库是 **麦芽田渠道开放平台（Channel Open Platform）多语言 SDK 的统一代码仓库**，面向第三方服务商和品牌商家，提供：

- **标准化 Sender 能力（三方 → 麦芽田）**：推送订单、订单状态、配送状态、退款信息、门店信息等。
- **标准化 Receiver 能力（麦芽田 → 三方）**：接收麦芽田的订单确认、状态变更、退款结果、骑手位置、Token 解绑等回调。
- **统一的企业级特性**：自动签名、连接池、超时与重试策略、日志与错误处理等（由各语言 SDK 按本仓库统一规范实现）。

你可以在本仓库中找到 **Go / Java 渠道 SDK** 的实现与文档，后续若扩展其他语言也会遵循同一套规范。

---

## 整体架构与核心概念（Sender / Receiver 双向链路）

### Sender vs Receiver

- **Sender（发送方）**
  - 主体：**第三方服务商系统**
  - 方向：三方 → 麦芽田
  - 职责：将业务事件主动推送到麦芽田开放平台（如：新订单、订单确认/完成、自配送状态、退款申请等）。
- **Receiver（接收方）**
  - 主体：**第三方服务商系统**
  - 方向：麦芽田 → 三方
  - 职责：提供 HTTP 回调接口，接收麦芽田推送的事件（如：请求确认订单、订单状态/配送状态变更、退款决策、骑手位置等）。

### 双向链路示意

```text
┌─────────────────────────────┐                  ┌─────────────────────────────┐
│        第三方服务商系统      │                  │        麦芽田开放平台        │
│      业务系统 / SaaS 平台    │                  │      Channel Open API      │
└─────────────┬──────────────┘                  └─────────────┬──────────────┘
              │ Sender（主动调用）                             │ Receiver（回调推送）
              │ 三方 → 麦芽田                                 │ 麦芽田 → 三方
              │                                               │
   推送新订单 / 订单状态 / 配送状态 / 退款申请  …               │   请求确认订单 / 通知状态变更 /
              │                                               │   推送骑手位置 / 通知解绑 Token …
              ▼                                               ▼
        sender/api/*                                   receiver/* 回调接口
```

在多数业务场景中，**Sender 与 Receiver 是成对出现的**：  
例如“订单确认链路”通常是：

1. 麦芽田通过 Receiver 回调请求三方确认订单。
2. 三方完成内部业务处理后，再通过 Sender 主动调用将确认结果推回麦芽田。

---

## 仓库结构与语言支持

当前仓库的主要目录结构（与渠道开放平台 SDK 相关部分）如下：

```text
.
├── channel/
│   ├── go-sdk/        # 麦芽田渠道开放平台 Go SDK（Sender + Receiver）
│   └── java-sdk/      # 麦芽田渠道开放平台 Java SDK（Sender + Receiver）
├── LICENSE            # 许可证（MIT）
└── README.md          # 本文件
```

各语言 SDK 在各自目录下维护独立的 README / 架构文档与示例代码，本根 README 主要承担：

- 统一解释业务与技术概念（Sender / Receiver / 签名 / 环境等）。
- 给出各语言入口与 Quick Start。
- 说明跨语言通用的必接能力与约定。

---

## 支持语言与安装方式总览

| 语言 | SDK 目录 | 运行环境要求 | 安装方式 | 详细文档 |
|------|----------|--------------|----------|----------|
| Go   | `channel/go-sdk`   | Go **1.24+** | `go get github.com/maiyatian/channel/myt-go-sdk` | [`channel/go-sdk/README.md`](channel/go-sdk/README.md) |
| Java | `channel/java-sdk` | Java **8+**（推荐 11+/17+） | Maven / Gradle 依赖 | [`channel/java-sdk/README.md`](channel/java-sdk/README.md) |

> ⚠️ 上述模块路径与坐标以各子目录 README 中的说明为准，如后续发布到公共仓库，以实际发布信息为权威来源。

---

## 快速开始（Quick Start）

下面给出 Go / Java 的最小示例，帮助你在几分钟内完成 SDK 初始化与简单调用。  
**业务详情（订单/配送/退款等）请参阅对应语言 SDK 的 README。**

### Go 渠道 SDK Quick Start

> 详细文档与完整示例请参考：`channel/go-sdk/README.md`、`channel/go-sdk/examples/README.md`

```go
package main

import (
	"context"
	"log"
	"time"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	"github.com/maiyatian/channel/myt-go-sdk/models/sender/api"
)

func main() {
	// 1. 创建配置（测试环境示例）
	cfg := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		RequestTimeout(30 * time.Second).
		EnableLogging(true).
		Build()

	// 2. 创建 Sender 客户端（三方 → 麦芽田）
	sender := api.NewChannelSender(cfg)

	// 3. 示例：检查 SDK 是否可用（实际业务请调用 AccessToken / OrderCreated 等接口）
	log.Println("Go Sender client initialized")

	// 示例：调用 AccessToken / OrderCreated 等接口时，务必传入 context 和 token
	_ = context.Background()
}
```

如需快速体验完整链路（基础用法 / 高级特性 / 回调服务器），可以在 `channel/go-sdk` 目录中运行：

```bash
go run examples/main.go -type basic     # 基础示例
go run examples/main.go -type advanced  # 高级功能
go run examples/main.go -type callback  # 回调示例
```

---

### Java 渠道 SDK Quick Start

> 详细文档与完整示例请参考：`channel/java-sdk/README.md`

**Maven 依赖：**

```xml
<dependency>
    <groupId>com.maiyatian</groupId>
    <artifactId>channel-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle 依赖：**

```gradle
implementation "com.maiyatian:channel-java-sdk:1.0.0"
```

**最小示例：创建 Sender 客户端并完成初始化：**

```java
import com.maiyatian.channel.sdk.client.HttpClientConfig;
import com.maiyatian.channel.sdk.client.ConfigValidationException;
import com.maiyatian.channel.sdk.models.sender.api.ChannelSender;

import java.time.Duration;

public class QuickStart {
    public static void main(String[] args) {
        String baseUrl = "https://open-api-test.maiyatian.com";
        String apiKey = "your_app_key";
        String apiSecret = "your_app_secret";

        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl(baseUrl)
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    .requestTimeout(Duration.ofSeconds(30))
                    .connectionTimeout(Duration.ofSeconds(10))
                    .readTimeout(Duration.ofSeconds(30))
                    .retryMaxAttempts(3)
                    .enableLogging(true)
                    .build();

            try (ChannelSender sender = new ChannelSender(config)) {
                System.out.println("✅ ChannelSender initialized");
                // 在此处调用 accessToken / orderCreated / orderConfirmed 等接口
            }
        } catch (ConfigValidationException e) {
            System.err.println("配置错误: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 通用业务能力与必接接口（跨语言）

无论使用 Go 还是 Java，**下列能力与接口分类在业务语义上是一致的**；各语言 SDK 在命名和目录结构上会保持对应关系。

### Sender 接口（三方主动调用麦芽田）

> 由 `models/sender/api/*` + `models/sender/entity/*` 实现，分别在 Go / Java 中提供强类型封装。

- **必接 Sender 接口（强烈建议所有接入方实现）：**
  - `AccessToken`：根据授权 `code` 换取访问令牌 `token`。
  - `RefreshToken`：在 `token` 过期前刷新。
  - `OrderCreated`：向麦芽田推送新订单。
  - `OrderConfirmed`：商户确认接单后推送订单确认状态。
  - `OrderDone`：订单配送完成后推送完成状态。
- **常用扩展 Sender 接口：**
  - 订单链路：`OrderModified`、`OrderCanceled`、`OrderRemind` 等。
  - 配送链路：`SelfDeliveryChange`（自配送状态变更）、批量骑手位置上报等。
  - 退款链路：`OrderApplyRefund`（退款申请）、`OrderRefunded`（退款结果）。
  - 门店链路：`ShopInfo` 等门店信息查询。

### Receiver 接口（麦芽田回调三方）

> 由 `models/receiver/entity/*` 中的实体定义；**三方需在自己的服务中提供 HTTP 回调接口并使用这些实体解析请求**。

- **订单回调：**
  - `ConfirmOrder`：通知三方确认订单（通常需要结合 Sender 的 `OrderConfirmed` 接口完成闭环）。
  - `OrderChange`：通知三方订单状态变更。
  - `OrderDetail` / `OrderList`：订单详情/列表查询场景。
- **配送回调：**
  - `DeliveryChange`：通知三方配送状态变更。
  - `MealPicking`：出餐状态变更。
  - `MultiRiderLocation`：批量推送骑手位置。
- **退款回调：**
  - `OrderAgreeRefund`：通知三方同意退款。
  - `OrderRejectRefund`：通知三方拒绝退款。
- **门店与授权回调：**
  - `ShopDetail`、`ShopList`、`ShopPrinterList`：门店及打印机信息。
  - `TokenUnbind`：通知三方解绑令牌（需要清理本地 token 绑定关系）。

> **核心实践：**  
> 大多数关键业务链路都由“Receiver 触发请求 + Sender 回传结果”构成，是一种 **双向、强一致的协作模式**。  
> 实现时务必同时关注“收到什么回调”与“需要往回推送什么结果”这两个维度。

---

## 签名与安全（跨语言统一说明）

所有 SDK 在 **主动调用开放平台（Sender）时都会自动完成签名**；  
而在 **接收麦芽田回调（Receiver）时，需要三方自行验签**，以防止伪造请求。

### 签名规则（抽象描述）

1. 从请求体中取出字段：`app_key`、`token`、`timestamp`、`data`。
2. 对上述字段按 key 做字典序排序（ksort）。
3. 使用半角逗号拼接成字符串：`key1=value1,key2=value2,...`。
4. 将字符串进行 UTF-8 编码。
5. 使用对应的 `app_secret` 执行 `HmacSHA256` 计算摘要。
6. 对摘要结果使用 URL 安全的 Base64 编码，得到最终签名。

在 Go / Java SDK 中：

- **发起请求时**：上述流程由 SDK 内部自动完成，正常情况下你无需手写。
- **接收回调时**：SDK 会提供工具函数/示例代码用于验签，你需要：
  - 从回调请求中取出原始字段与 `signature`。
  - 使用本地保存的 `app_secret` 计算预期签名。
  - 与请求头/请求体中的签名进行比对。

具体实现细节请参考：

- Go：`channel/go-sdk/README.md` 中的“签名验证”章节与工具函数示例。
- Java：`channel/java-sdk/README.md` 中的 `SignatureUtils` 用法。

---

## 环境与测试

SDK 在不同语言中的环境配置细节略有差异，但整体遵循以下约定：

- **测试环境：**
  - API 地址：`https://open-api-test.maiyatian.com`
  - 商户后台：`http://saas.open.test.maiyatian.com/`
  - 授权中心：`http://saas.open.test.maiyatian.com/oauth/`
  - 签名校验工具：`https://open-api-test.maiyatian.com/test/`
- **生产环境：**
  - API 地址：`https://open-api.maiyatian.com`
  - 其他地址以官方开放平台文档为准。

> 获取测试环境（包括 `app_key`、`app_secret`、测试商户账号）请联系麦芽田开放平台技术支持。

各语言 SDK 在其 README 中分别提供了：

- 测试 / 生产环境配置示例。
- 环境切换与超时、连接池、重试策略等高级配置项。

---

## 错误处理与重试策略概览

跨语言通用的错误与重试约定如下：

- **网络请求维度：**
  - 连接超时、连接拒绝等网络错误将触发自动重试。
  - HTTP `5xx` 状态码会触发自动重试。
- **业务维度：**
  - 返回体中 `code` 字段在 `500–599` 范围内的业务错误，也会触发重试逻辑。
- **重试策略（典型配置）：**
  - 默认最大重试次数：3 次（可按语言 SDK 配置）。
  - 重试间隔：起始约 1s，采用指数退避，最大约 30s。

在应用层你仍然需要：

- 检查 `err`（异常/错误）以及业务 `code` 字段。
- 对幂等性敏感的业务做好防重、去重设计。
- 对 token 过期（如 `401`）等业务错误，按语言 SDK README 提示执行刷新/重新授权逻辑。

---

## 统一业务约定（时间 / 金额 / 坐标）

为保证跨语言、一致、可预期的行为，开放平台在以下领域有统一约定：

- **时间字段**
  - 全部使用 **Unix 时间戳（单位：秒）**。
  - 请求时间戳需与实际时间误差不超过 **10 分钟**。
- **金额字段**
  - 单位统一为 **分**（`int` / `long`），避免使用浮点数。
  - 1 元 = 100 分，建议在应用层引入货币类型封装以避免精度问题。
- **经纬度坐标**
  - 使用 **国测局 GCJ-02 标准**（如高德地图坐标）。
  - 请勿直接使用 WGS84 或 BD09 坐标。

具体的枚举值与常量定义：

- Go：`channel/go-sdk/models/types/common.go`
- Java：`channel/java-sdk/src/main/java/com/maiyatian/channel/sdk/consts/Constants.java`

---

## License

本仓库及其中各语言 SDK 统一采用 **MIT License** 开源协议，详细条款见根目录：

- [`LICENSE`](LICENSE)

你可以在遵守 MIT 协议的前提下自由使用、修改与分发本项目。

---

## 相关文档与技术支持

- **Go 渠道 SDK**
  - 使用指南与示例：[`channel/go-sdk/README.md`](channel/go-sdk/README.md)
  - 架构说明：[`channel/go-sdk/ARCHITECTURE.md`](channel/go-sdk/ARCHITECTURE.md)
- **Java 渠道 SDK**
  - 使用指南与示例：[`channel/java-sdk/README.md`](channel/java-sdk/README.md)
- **开放平台接口文档**
  - 请参考公司内部/对外发布的“麦芽田渠道开放平台 API 文档”（URL 以官方渠道为准）。

如需更多接入支持，请联系麦芽田开放平台技术支持团队。
