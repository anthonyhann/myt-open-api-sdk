## myt-open-api-sdk

`myt-open-api-sdk` is a **multi-language, enterprise-grade SDK collection** for the **Maiyatian Channel Open Platform**.  
It helps:

- Brand merchants manage omni-channel orders and outbound delivery capacity.
- Delivery platforms receive omni-channel order pushes and report delivery status back to Maiyatian via a unified, strongly-typed interface.

The repository currently contains **Go** and **Java** SDKs for the Channel Open Platform.

---

## Project Overview

This repository is the **unified codebase for multi-language SDKs of the Maiyatian Channel Open Platform**.  
It targets third-party service providers and brand merchants, and provides:

- **Standardized Sender capabilities (3rd party → Maiyatian)**  
  Push orders, order status, delivery status, refund information, shop information, etc.
- **Standardized Receiver capabilities (Maiyatian → 3rd party)**  
  Receive callbacks from Maiyatian for order confirmation, status changes, refund decisions, rider locations, token unbinding, and more.
- **Unified enterprise-grade features**  
  Automatic signing, HTTP connection pooling, timeouts and retry policies, structured logging and error handling (implemented per language SDK following the same specification).

You will find **Go / Java Channel SDKs** in this repository.  
Any future languages will follow the same conventions and architecture.

---

## Architecture & Core Concepts (Sender / Receiver Bi‑Directional Flow)

### Sender vs Receiver

- **Sender**
  - Actor: **3rd-party service system**
  - Direction: 3rd party → Maiyatian
  - Responsibility: Actively push business events to the Maiyatian Open Platform, such as:
    - New orders
    - Order confirmation / completion
    - Self-delivery status updates
    - Refund requests
    - Shop information

- **Receiver**
  - Actor: **3rd-party service system**
  - Direction: Maiyatian → 3rd party
  - Responsibility: Expose HTTP callback endpoints to receive events pushed by Maiyatian, such as:
    - Requesting the 3rd party to confirm orders
    - Order / delivery status changes
    - Refund decisions (agree / reject)
    - Rider location updates
    - Token unbinding notifications

### Bi‑Directional Flow Diagram

```text
┌─────────────────────────────┐                  ┌─────────────────────────────┐
│       Third-Party System    │                  │     Maiyatian Open Platform │
│   Business System / SaaS    │                  │        Channel Open API     │
└─────────────┬──────────────┘                  └─────────────┬──────────────┘
              │ Sender (active calls)                         │ Receiver (callbacks)
              │ 3rd party → Maiyatian                         │ Maiyatian → 3rd party
              │                                               │
   Push new orders / order status / delivery status / refunds │   Ask for order confirmation /
              │                                               │   notify status changes /
              ▼                                               │   push rider locations /
        sender/api/*                                          │   notify token unbinding …
                                                              ▼
                                                       receiver/* HTTP callbacks
```

In most real-world flows, **Sender and Receiver form a pair**. For example, the **order confirmation** flow is typically:

1. Maiyatian calls a 3rd-party **Receiver** callback to ask for order confirmation.
2. After internal processing, the 3rd party uses **Sender** to push the confirmation result back to Maiyatian.

This design ensures a **bi‑directional, strongly consistent integration model**.

---

## Repository Structure & Language Support

Only the parts related to the Channel Open Platform SDKs are shown here:

```text
.
├── channel/
│   ├── go-sdk/        # Maiyatian Channel Open Platform Go SDK (Sender + Receiver)
│   └── java-sdk/      # Maiyatian Channel Open Platform Java SDK (Sender + Receiver)
├── LICENSE            # MIT License
└── README.md          # Main (Chinese) README
```

Each language SDK maintains its own README, architecture notes, and sample code under its directory.  
This root README (and this English version) are mainly for:

- Explaining cross-language business & technical concepts (Sender / Receiver / signing / environments, etc.).
- Providing language entry points and Quick Start examples.
- Describing cross-language mandatory capabilities and conventions.

---

## Supported Languages & Installation Overview

| Language | SDK Directory        | Runtime Requirement     | Installation                         | Docs |
|---------|----------------------|-------------------------|--------------------------------------|------|
| Go      | `channel/go-sdk`     | Go **1.24+**            | `go get github.com/maiyatian/channel/myt-go-sdk` | [`channel/go-sdk/README.md`](channel/go-sdk/README.md) |
| Java    | `channel/java-sdk`   | Java **8+** (11+/17+ recommended) | Maven / Gradle dependency           | [`channel/java-sdk/README.md`](channel/java-sdk/README.md) |

> The exact module path and coordinates are subject to the per-language README and the actual published artifacts.  
> Always treat the per-language README as the source of truth.

---

## Quick Start

Below are minimal examples for Go and Java that show how to initialize the Sender client.  
For full business flows (orders, delivery, refunds, shops, etc.), please refer to the per-language README.

### Go Channel SDK Quick Start

> For detailed docs and complete examples, see:  
> `channel/go-sdk/README.md` and `channel/go-sdk/examples/README.md`

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
	// 1. Build config (test environment example)
	cfg := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		RequestTimeout(30 * time.Second).
		EnableLogging(true).
		Build()

	// 2. Create Sender client (3rd party → Maiyatian)
	sender := api.NewChannelSender(cfg)

	// 3. Sanity check — in real code, call AccessToken / OrderCreated etc.
	log.Println("Go Sender client initialized")

	// Always pass a proper context and token when calling APIs
	_ = context.Background()
}
```

To run the bundled examples from the Go SDK:

```bash
cd channel/go-sdk

go run examples/main.go -type basic     # basic usage
go run examples/main.go -type advanced  # advanced features
go run examples/main.go -type callback  # callback server
```

---

### Java Channel SDK Quick Start

> For detailed docs and examples, see:  
> `channel/java-sdk/README.md`

**Maven dependency:**

```xml
<dependency>
    <groupId>com.maiyatian</groupId>
    <artifactId>channel-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle dependency:**

```gradle
implementation "com.maiyatian:channel-java-sdk:1.0.0"
```

**Minimal example: create a Sender client and initialize it:**

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
                // Call accessToken / orderCreated / orderConfirmed etc. here
            }
        } catch (ConfigValidationException e) {
            System.err.println("Config error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## Cross-Language Business Capabilities & Required Interfaces

Regardless of whether you use Go or Java, the **business semantics and interface categories** are aligned across languages.

### Sender APIs (3rd Party → Maiyatian)

> Implemented by `models/sender/api/*` and `models/sender/entity/*` in each language.

**Highly recommended / “must-have” Sender APIs:**

- `AccessToken`: Exchange authorization `code` for an access token `token`.
- `RefreshToken`: Refresh the token before it expires.
- `OrderCreated`: Push a new order to Maiyatian.
- `OrderConfirmed`: Push order confirmation after the merchant accepts the order.
- `OrderDone`: Push order-completion status after delivery is finished.

**Common additional Sender APIs:**

- Order flow: `OrderModified`, `OrderCanceled`, `OrderRemind`, etc.
- Delivery flow: `SelfDeliveryChange` for self-delivery status, batch rider location reporting, etc.
- Refund flow: `OrderApplyRefund` (request), `OrderRefunded` (result).
- Shop flow: `ShopInfo` and related shop information queries.

### Receiver APIs (Maiyatian → 3rd Party)

> Defined by `models/receiver/entity/*`.  
> You must expose HTTP endpoints in your own service and use these entities to parse the callback payloads.

**Order callbacks:**

- `ConfirmOrder`: Ask the 3rd party to confirm the order  
  (usually paired with Sender`s `OrderConfirmed` to close the loop).
- `OrderChange`: Notify order status changes.
- `OrderDetail` / `OrderList`: Provide order details and list queries.

**Delivery callbacks:**

- `DeliveryChange`: Notify delivery status changes.
- `MealPicking`: Notify meal readiness / kitchen status.
- `MultiRiderLocation`: Batch rider location pushes.

**Refund callbacks:**

- `OrderAgreeRefund`: Notify that a refund is approved.
- `OrderRejectRefund`: Notify that a refund is rejected.

**Shop & authorization callbacks:**

- `ShopDetail`, `ShopList`, `ShopPrinterList`: Shop and printer information.
- `TokenUnbind`: Notify that a token is unbound (you must clear local token bindings).

> **Key practice:**  
> Many critical flows are modeled as **“Receiver triggers a request → Sender returns the final result”**, forming a bi‑directional, strongly consistent integration.  
> When designing your integration, always consider both sides: *which callbacks you receive* and *which Sender APIs you must call in response*.

---

## Signing & Security (Cross-Language Rules)

All SDKs:

- **Automatically sign outbound Sender requests** to Maiyatian.
- **Require you to verify signatures on inbound Receiver callbacks** to guard against forged requests.

### Signing Algorithm (Abstract)

1. Extract the following fields from the request body: `app_key`, `token`, `timestamp`, `data`.
2. Sort the keys lexicographically (dictionary order).
3. Concatenate into a string using commas:  
   `key1=value1,key2=value2,...`
4. Encode the string as UTF‑8 bytes.
5. Compute `HmacSHA256` with your `app_secret` as the key.
6. Encode the result using URL-safe Base64 to obtain the final signature.

In the Go / Java SDKs:

- **For outbound requests (Sender)**:  
  This process is handled internally by the SDK; you typically do not need to implement it yourself.
- **For inbound callbacks (Receiver)**:  
  The SDK provides helpers / examples. You still need to:
  - Extract the original fields and the `signature` from the callback.
  - Recompute the expected signature using your locally stored `app_secret`.
  - Compare it with the provided signature.

See:

- Go: signing section and helper functions in `channel/go-sdk/README.md`.
- Java: `SignatureUtils` usage in `channel/java-sdk/README.md`.

---

## Environments & Testing

Environment configuration differs slightly per language but follows the same overall rules.

- **Test environment:**
  - API base URL: `https://open-api-test.maiyatian.com`
  - Merchant console: `http://saas.open.test.maiyatian.com/`
  - Authorization center: `http://saas.open.test.maiyatian.com/oauth/`
  - Signature testing tool: `https://open-api-test.maiyatian.com/test/`

- **Production environment:**
  - API base URL: `https://open-api.maiyatian.com`
  - Other URLs: see the official Open Platform documentation.

> Please contact the Maiyatian Open Platform team to apply for test environment credentials, including:
> - `app_key`
> - `app_secret`
> - Test merchant accounts

Each language SDK’s README shows:

- Test / production environment configuration examples.
- How to configure timeouts, connection pool sizes, retry policies, logging, etc.

---

## Error Handling & Retry Strategy (Overview)

Common cross-language rules:

- **Network-level:**
  - Connection timeouts / connection refused errors trigger automatic retries.
  - HTTP `5xx` status codes trigger automatic retries.

- **Business-level:**
  - Business `code` values in the `500–599` range also trigger retry logic.

- **Retry policy (typical defaults):**
  - Max attempts: 3 (configurable in each SDK).
  - Interval: starts around 1s, uses exponential backoff, capped at ~30s.

At the application level, you should still:

- Check both transport errors and business `code` fields.
- Design idempotent operations and deduplication for critical flows.
- Handle token expiration (e.g. `401`) by invoking the refresh / re‑authorization flow as described in the per-language README.

---

## Unified Business Conventions (Time / Money / Coordinates)

To ensure consistent behavior across languages, the Open Platform defines:

- **Time fields**
  - All timestamps use **Unix time in seconds**.
  - Request timestamps must not differ from real time by more than **10 minutes**.

- **Money fields**
  - All amounts use **integer cents** (`int` / `long`), not floating-point.
  - 1 yuan = 100 cents. It is recommended to introduce a money type wrapper in your app to avoid precision issues.

- **Geo coordinates**
  - All coordinates use the **GCJ‑02** standard (e.g. AMap in China).
  - Do not directly use WGS84 or BD09 coordinates.

For complete lists of enums and constants:

- Go: `channel/go-sdk/models/types/common.go`
- Java: `channel/java-sdk/src/main/java/com/maiyatian/channel/sdk/consts/Constants.java`

---

## License

All SDKs in this repository are released under the **MIT License**.  
See:

- [`LICENSE`](LICENSE)

You may use, modify, and redistribute the code under the terms of the MIT license.

---

## Related Docs & Support

- **Go Channel SDK**
  - Usage & examples: [`channel/go-sdk/README.md`](channel/go-sdk/README.md)
  - Architecture: [`channel/go-sdk/ARCHITECTURE.md`](channel/go-sdk/ARCHITECTURE.md)
- **Java Channel SDK**
  - Usage & examples: [`channel/java-sdk/README.md`](channel/java-sdk/README.md)
- **Open Platform API Docs**
  - Please refer to the official Maiyatian Channel Open Platform API documentation (internal / public URLs as provided by the platform).

For further integration support, please contact the Maiyatian Open Platform technical support team.

