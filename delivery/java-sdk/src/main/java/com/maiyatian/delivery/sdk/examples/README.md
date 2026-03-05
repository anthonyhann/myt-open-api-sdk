# 麦芽田配送开放平台 Java SDK 示例

本目录包含了麦芽田配送开放平台 Java SDK 的使用示例，展示了 SDK 的各种功能和最佳实践。

## 📁 示例文件

### 1. QuickStartExample.java
**快速开始示例** - 展示SDK的基本使用方法

- ✅ 基础配置设置
- ✅ 获取访问令牌
- ✅ 刷新访问令牌
- ✅ 配送状态同步
- ✅ 快递轨迹回传

**适用场景：** 初次接入SDK，了解基本API调用流程

### 2. AdvancedExample.java
**高级特性示例** - 展示SDK的高级功能

- 🔧 Builder模式配置
- 🎯 泛型API调用 (ApiResponse<T>)
- ⚠️ 错误处理和重试机制
- 📋 自定义请求头
- 🐛 调试模式使用

**适用场景：** 需要使用SDK高级特性的开发者

### 3. DeliveryWorkflowExample.java
**完整业务流程示例** - 模拟真实配送场景

- 📋 完整的配送业务流程
- 🔐 授权认证流程
- 🚚 配送状态实时同步
- 🗺️ 轨迹信息回传
- ✅ 配送完成确认

**适用场景：** 理解完整业务流程，生产环境参考

## 🚀 运行示例

### 前置条件

1. **获取API密钥**
   ```bash
   # 从麦芽田开放平台获取
   APP_KEY=your_app_key
   APP_SECRET=your_app_secret
   ```

2. **设置环境变量（可选）**
   ```bash
   # 启用调试模式
   export DEBUG=true
   ```

### 运行快速开始示例

```bash
# 编译项目
mvn clean compile

# 运行快速开始示例
mvn exec:java -Dexec.mainClass="com.maiyatian.delivery.sdk.examples.QuickStartExample"
```

### 运行高级特性示例

```bash
# 运行高级示例
mvn exec:java -Dexec.mainClass="com.maiyatian.delivery.sdk.examples.AdvancedExample"
```

### 运行完整业务流程示例

```bash
# 运行业务流程示例
mvn exec:java -Dexec.mainClass="com.maiyatian.delivery.sdk.examples.DeliveryWorkflowExample"
```

## 🎯 新架构特性

### 1. Builder模式配置

```java
// 使用新的Builder模式创建配置
HttpClientConfig config = HttpClientConfig.builder()
    .baseUrl("https://open-api-test.maiyatian.com")
    .apiKey("your_app_key")
    .apiSecret("your_app_secret")
    .maxConnections(100)
    .requestTimeout(Duration.ofSeconds(30))
    .retryMaxAttempts(5)
    .enableLogging(true)
    .build();
```

### 2. 泛型API调用

```java
// 类型安全的API调用
ApiResponse<AccessTokenEntity.Resp> response = ApiClientUtils.requestWithApiClient(
    httpClient, "POST", Constants.ACCESS_TOKEN_PATH,
    request, token, headers, AccessTokenEntity.Resp.class
);

// 检查响应并获取数据
if (response.isSuccess()) {
    AccessTokenEntity.Resp tokenData = response.getDataOrThrow();
    String token = tokenData.getToken();
}
```

### 3. 常量和变量管理

```java
// 使用统一的常量
String status = Constants.DELIVERY_STATUS_DELIVERING;
String apiPath = Constants.DELIVERY_CHANGE_PATH;

// 获取SDK信息
String version = Variables.getVersion();
boolean isDebug = Variables.isDebugging();
String userAgent = Variables.getUserAgent();
```

### 4. 错误处理

```java
try {
    ApiResponse<TokenResp> response = ApiClientUtils.requestWithApiClient(...);
    
    if (response.isSuccess()) {
        // 处理成功响应
        TokenResp data = response.getDataOrThrow();
    } else {
        // 处理业务错误
        System.err.println("业务错误: " + response.getMessage());
    }
    
} catch (IOException e) {
    // 处理网络异常
} catch (JsonProcessingException e) {
    // 处理JSON序列化异常
}
```

## 📚 Go SDK 对比

### 配置创建

| Go SDK | Java SDK |
|--------|----------|
| `client.NewConfigBuilder().BaseURL("...").Build()` | `HttpClientConfig.builder().baseUrl("...").build()` |

### 泛型API调用

| Go SDK | Java SDK |
|--------|----------|
| `RequestWithApiClient[T any](...)` | `ApiClientUtils.requestWithApiClient(..., Class<T>)` |
| `ApiResponse[T]{Code, Message, Data}` | `ApiResponse<T>{code, message, data}` |

### 常量访问

| Go SDK | Java SDK |
|--------|----------|
| `consts.ContentTypeJsonUTF` | `Constants.CONTENT_TYPE_JSON_UTF8` |
| `vars.VERSION` | `Variables.getVersion()` |
| `vars.IsDebugging` | `Variables.isDebugging()` |

## 🔧 自定义配置

### 测试环境配置

```java
HttpClientConfig testConfig = HttpClientConfig.builder()
    .baseUrl(Constants.TEST_BASE_URL)  // 测试环境
    .apiKey("test_app_key")
    .apiSecret("test_app_secret")
    .requestTimeout(Duration.ofSeconds(10))  // 短超时
    .retryMaxAttempts(1)  // 少重试
    .enableLogging(true)  // 启用日志
    .logRequestBody(true)  // 记录请求体（调试用）
    .build();
```

### 生产环境配置

```java
HttpClientConfig prodConfig = HttpClientConfig.builder()
    .baseUrl(Constants.PROD_BASE_URL)  // 生产环境
    .apiKey("prod_app_key")
    .apiSecret("prod_app_secret")
    .maxConnections(200)  // 高并发
    .maxConnectionsPerHost(50)
    .requestTimeout(Duration.ofSeconds(60))  // 长超时
    .retryMaxAttempts(5)  // 多重试
    .enableLogging(false)  // 关闭日志（性能）
    .logRequestBody(false)  // 不记录敏感信息
    .build();
```

## 🐛 调试技巧

### 1. 启用调试模式

```bash
# 设置环境变量
export DEBUG=true

# 运行程序
java -jar your-app.jar
```

### 2. 日志配置

```java
HttpClientConfig config = HttpClientConfig.builder()
    .enableLogging(Variables.isDebugging())  // 根据调试标志动态启用
    .logRequestBody(Variables.isDebugging())  // 调试时记录请求体
    .build();
```

### 3. 自定义请求头

```java
Map<String, String> headers = new HashMap<>();
headers.put("X-Request-ID", "REQ_" + System.currentTimeMillis());
headers.put("X-Client-Version", Variables.getVersion());

ApiResponse<T> response = ApiClientUtils.requestWithApiClient(
    httpClient, method, path, data, token, headers, responseClass
);
```

## ⚠️ 注意事项

1. **敏感信息保护**
   - 不要在日志中记录`app_secret`
   - 生产环境建议关闭请求体日志

2. **错误处理**
   - 总是检查`response.isSuccess()`
   - 使用`getDataOrThrow()`进行快速失败
   - 使用`getDataOrDefault()`进行容错处理

3. **资源管理**
   - 使用try-with-resources管理HttpClientManager
   - 及时关闭不再使用的客户端

4. **网络配置**
   - 根据业务需求调整超时时间
   - 合理设置重试次数和延迟

## 📖 更多文档

- [API文档](../../../README.md)
- [麦芽田开放平台文档](https://open.maiyatian.com/docs)
- [SDK GitHub仓库](https://github.com/maiyatian/delivery-java-sdk)