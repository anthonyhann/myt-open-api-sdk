/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: client
 * @ClassName: config
 * @Description: 麦芽田配送开放平台SDK - HTTP客户端配置管理
 * @Version 1.0
 */

package client

import (
	"fmt"
	"time"
)

// HTTPClientConfig 企业级 HTTP 客户端配置
//
// 配置说明:
//   - 提供完整的HTTP客户端配置选项
//   - 支持连接池、超时、重试、日志等企业级特性
//   - 使用Builder模式构建，提供默认配置
//
// 使用方式:
//   - 方式1: 直接创建配置对象
//   - 方式2: 使用ConfigBuilder构建器（推荐）
//   - 方式3: 使用默认配置并部分覆盖
type HTTPClientConfig struct {
	// ==================== 基础配置 ====================

	// BaseURL API服务基础URL
	// 测试环境: https://open-api-test.maiyatian.com
	// 正式环境: https://open-api.maiyatian.com
	// 注意: 严禁使用正式地址进行接口调试
	BaseURL string `json:"base_url"`

	// APIKey 应用密钥
	// 从麦芽田开放平台申请获得，用于标识调用方
	// 必填项，未提供会导致配置验证失败
	APIKey string `json:"api_key"`

	// APISecret 应用密钥
	// 从麦芽田开放平台申请获得，用于请求签名
	// 必填项，未提供会导致配置验证失败
	// 注意: 请妥善保管，不要泄露
	APISecret string `json:"api_secret"`

	// ==================== 连接池配置 ====================

	// MaxConnections 最大连接数
	// 控制整个客户端的最大TCP连接数
	// 默认: 50
	// 建议: 根据并发量调整，一般50-200
	MaxConnections int `json:"max_connections"`

	// MaxConnectionsPerHost 每个主机的最大连接数
	// 控制对单个主机的最大并发连接数
	// 默认: 10
	// 建议: 不超过MaxConnections
	MaxConnectionsPerHost int `json:"max_connections_per_host"`

	// KeepAliveTimeout 连接保持时间
	// HTTP Keep-Alive连接的空闲超时时间
	// 默认: 30秒
	// 说明: 超过此时间的空闲连接会被关闭
	KeepAliveTimeout time.Duration `json:"keep_alive_timeout"`

	// ==================== 超时配置 ====================

	// RequestTimeout 请求超时
	// 整个HTTP请求（包括连接、发送、接收）的总超时时间
	// 默认: 60秒
	// 建议: 根据业务复杂度调整，一般30-120秒
	RequestTimeout time.Duration `json:"request_timeout"`

	// ConnectionTimeout 连接超时
	// 建立TCP连接的超时时间
	// 默认: 30秒
	// 说明: 包括DNS解析和TCP握手时间
	ConnectionTimeout time.Duration `json:"connection_timeout"`

	// ReadTimeout 读取超时
	// 从服务器读取响应的超时时间
	// 默认: 60秒
	// 说明: 从开始读取到完全读取完成的时间
	ReadTimeout time.Duration `json:"read_timeout"`

	// ==================== 重试配置 ====================

	// RetryMaxAttempts 最大重试次数
	// 网络错误或5xx错误时的最大重试次数
	// 默认: 3次
	// 说明: 0表示不重试，建议设置2-5次
	RetryMaxAttempts int `json:"retry_max_attempts"`

	// RetryBaseDelay 重试基础延迟
	// 首次重试的等待时间
	// 默认: 1秒
	// 说明: 后续重试会使用指数退避策略
	RetryBaseDelay time.Duration `json:"retry_base_delay"`

	// RetryMaxDelay 重试最大延迟
	// 重试等待的最大时间
	// 默认: 30秒
	// 说明: 防止指数退避导致等待时间过长
	RetryMaxDelay time.Duration `json:"retry_max_delay"`

	// ==================== 日志配置 ====================

	// EnableLogging 启用日志记录
	// 是否记录HTTP请求和响应日志
	// 默认: true
	// 建议: 开发环境启用，生产环境根据需要配置
	EnableLogging bool `json:"enable_logging"`

	// LogRequestBody 记录请求体
	// 是否在日志中记录请求体内容
	// 默认: false
	// 警告: 可能包含敏感信息，生产环境谨慎启用
	LogRequestBody bool `json:"log_request_body"`

	// LogResponseBody 记录响应体
	// 是否在日志中记录响应体内容
	// 默认: false
	// 说明: 用于调试，可能影响性能
	LogResponseBody bool `json:"log_response_body"`
}

// NewDefaultConfig 创建默认配置
//
// 功能说明:
//   - 创建一个包含推荐默认值的HTTP客户端配置
//   - 适用于大多数使用场景
//   - 可以在此基础上覆盖特定配置项
//
// 默认配置值:
//   - BaseURL: https://open-api.maiyatian.com (正式环境)
//   - SDKVersion: 1.0.0
//   - 连接池: 最大50个连接，每主机10个
//   - 超时: 请求60秒，连接30秒
//   - 重试: 最多3次，基础延迟1秒
//   - 日志: 启用日志，但不记录请求/响应体
//
// 返回值:
//   - *HTTPClientConfig: 默认配置对象
//
// 使用示例:
//
//	// 使用默认配置
//	config := client.NewDefaultConfig()
//	config.APIKey = "your_app_key"
//	config.APISecret = "your_app_secret"
//
//	// 或者覆盖部分配置
//	config.BaseURL = "https://open-api-test.maiyatian.com"  // 改为测试环境
//	config.RequestTimeout = 30 * time.Second                 // 减少超时时间
//
// 注意事项:
//   - BaseURL默认为正式环境，测试时需要修改
//   - 必须设置APIKey和APISecret才能通过验证
func NewDefaultConfig() *HTTPClientConfig {
	return &HTTPClientConfig{
		// 基础配置
		BaseURL: "https://open-api.maiyatian.com", // 正式环境地址

		// 连接池配置
		MaxConnections:        50,               // 最大 50 个连接
		MaxConnectionsPerHost: 10,               // 每个主机最多 10 个连接
		KeepAliveTimeout:      30 * time.Second, // 连接保活 30 秒

		// 超时配置
		RequestTimeout:    60 * time.Second, // 请求总超时 60 秒
		ConnectionTimeout: 30 * time.Second, // 连接超时 30 秒
		ReadTimeout:       60 * time.Second, // 读取超时 60 秒

		// 重试配置
		RetryMaxAttempts: 3,                // 最多重试 3 次
		RetryBaseDelay:   1 * time.Second,  // 首次重试延迟 1 秒
		RetryMaxDelay:    30 * time.Second, // 最大重试延迟 30 秒

		// 日志配置
		EnableLogging:   true,  // 启用日志
		LogRequestBody:  false, // 不记录请求体（避免敏感信息泄露）
		LogResponseBody: false, // 不记录响应体
	}
}

// Validate 验证配置有效性
//
// 功能说明:
//   - 检查配置参数是否符合要求
//   - 在创建HTTP客户端前自动调用
//   - 防止使用无效配置导致运行时错误
//
// 验证规则:
//   - BaseURL: 不能为空
//   - APIKey: 不能为空（必填项）
//   - APISecret: 不能为空（必填项）
//   - MaxConnections: 必须大于0
//   - RequestTimeout: 必须大于0
//   - RetryMaxAttempts: 不能为负数
//
// 返回值:
//   - error: 验证失败时返回ConfigValidationError，成功时返回nil
//
// 使用示例:
//
//	config := &HTTPClientConfig{
//	    BaseURL:   "https://open-api-test.maiyatian.com",
//	    APIKey:    "your_app_key",
//	    APISecret: "your_app_secret",
//	}
//
//	if err := config.Validate(); err != nil {
//	    log.Fatalf("配置验证失败: %v", err)
//	}
//
// 注意事项:
//   - NewHTTPClientManager会自动调用此方法
//   - 手动创建配置对象时建议主动调用验证
func (c *HTTPClientConfig) Validate() error {
	// 验证基础配置
	if c.BaseURL == "" {
		return &ConfigValidationError{
			Field:   "BaseURL",
			Message: "API 基础地址不能为空",
		}
	}
	if c.APIKey == "" {
		return &ConfigValidationError{
			Field:   "APIKey",
			Message: "应用密钥（app_key）不能为空",
		}
	}
	if c.APISecret == "" {
		return &ConfigValidationError{
			Field:   "APISecret",
			Message: "应用密钥（app_secret）不能为空",
		}
	}

	// 验证连接池配置
	if c.MaxConnections <= 0 {
		return &ConfigValidationError{
			Field:   "MaxConnections",
			Message: "最大连接数必须大于 0",
		}
	}

	// 验证超时配置
	if c.RequestTimeout <= 0 {
		return &ConfigValidationError{
			Field:   "RequestTimeout",
			Message: "请求超时时间必须大于 0",
		}
	}

	// 验证重试配置
	if c.RetryMaxAttempts < 0 {
		return &ConfigValidationError{
			Field:   "RetryMaxAttempts",
			Message: "最大重试次数不能为负数",
		}
	}

	return nil
}

// ConfigBuilder Builder模式配置构建器
//
// 功能说明:
//   - 提供流式API构建HTTP客户端配置
//   - 自动初始化默认值
//   - 支持链式调用，代码更简洁
//
// 使用场景:
//   - 推荐的配置创建方式
//   - 只需配置必要参数，其他使用默认值
//   - 提高代码可读性
type ConfigBuilder struct {
	config *HTTPClientConfig
}

// NewConfigBuilder 创建新的配置构建器
//
// 功能说明:
//   - 创建配置构建器实例
//   - 自动填充默认配置值
//   - 可通过链式调用设置配置项
//
// 返回值:
//   - *ConfigBuilder: 配置构建器实例
//
// 使用示例:
//
//	  config := client.NewConfigBuilder().
//	      BaseURL("https://open-api-test.maiyatian.com").
//	      APIKey("your_app_key").
//	      APISecret("your_app_secret").
//	      RequestTimeout(30 * time.Second).
//		    RetryMaxAttempts(5).
//		    EnableLogging(true).
//	      Build()
//
// 优点:
//   - 代码清晰易读
//   - 只需设置必要参数
//   - 自动使用推荐默认值
//   - 支持IDE自动补全
func NewConfigBuilder() *ConfigBuilder {
	return &ConfigBuilder{
		config: NewDefaultConfig(),
	}
}

// BaseURL 设置API服务基础URL
//
// 参数说明:
//   - url: API服务地址
//   - 测试环境: https://open-api-test.maiyatian.com
//   - 正式环境: https://open-api.maiyatian.com
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 注意事项:
//   - 必填项，不能为空
//   - 严禁在正式环境进行接口调试
func (b *ConfigBuilder) BaseURL(url string) *ConfigBuilder {
	b.config.BaseURL = url
	return b
}

// APIKey 设置应用密钥
//
// 参数说明:
//   - key: 从麦芽田开放平台申请获得的AppKey
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 注意事项:
//   - 必填项，用于标识调用方身份
//   - 请妥善保管，不要提交到版本控制系统
func (b *ConfigBuilder) APIKey(key string) *ConfigBuilder {
	b.config.APIKey = key
	return b
}

// APISecret 设置应用密钥
//
// 参数说明:
//   - secret: 从麦芽田开放平台申请获得的AppSecret
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 注意事项:
//   - 必填项，用于请求签名
//   - 请妥善保管，不要泄露或提交到版本控制系统
//   - 泄露后请立即重置密钥
func (b *ConfigBuilder) APISecret(secret string) *ConfigBuilder {
	b.config.APISecret = secret
	return b
}

// MaxConnections 设置最大连接数
//
// 参数说明:
//   - max: 整个客户端的最大TCP连接数
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 建议配置:
//   - 低并发（<10 QPS）: 20-50
//   - 中并发（10-100 QPS）: 50-100
//   - 高并发（>100 QPS）: 100-200
//
// 注意事项:
//   - 不是越大越好，过大会占用系统资源
//   - 需要配合MaxConnectionsPerHost使用
func (b *ConfigBuilder) MaxConnections(max int) *ConfigBuilder {
	b.config.MaxConnections = max
	return b
}

// RequestTimeout 设置请求超时时间
//
// 参数说明:
//   - timeout: 整个HTTP请求的总超时时间
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 建议配置:
//   - 快速接口（查询类）: 10-30秒
//   - 一般接口: 30-60秒
//   - 慢速接口（下单类）: 60-120秒
//
// 注意事项:
//   - 包括连接建立、请求发送、响应接收的总时间
//   - 设置过短可能导致正常请求超时
//   - 设置过长会影响错误发现
func (b *ConfigBuilder) RequestTimeout(timeout time.Duration) *ConfigBuilder {
	b.config.RequestTimeout = timeout
	return b
}

// RetryMaxAttempts 设置最大重试次数
//
// 参数说明:
//   - attempts: 失败时的最大重试次数（不包括首次请求）
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 重试场景:
//   - 网络错误（连接失败、超时等）
//   - HTTP 5xx错误
//   - 业务状态码5xx错误
//
// 建议配置:
//   - 关键接口: 3-5次
//   - 一般接口: 2-3次
//   - 幂等接口: 可适当增加
//   - 非幂等接口: 谨慎配置
//
// 注意事项:
//   - 0表示不重试
//   - 重试会增加响应时间
//   - 配合RetryBaseDelay控制重试间隔
func (b *ConfigBuilder) RetryMaxAttempts(attempts int) *ConfigBuilder {
	b.config.RetryMaxAttempts = attempts
	return b
}

// EnableLogging 启用日志记录
//
// 参数说明:
//   - enable: true启用，false禁用
//
// 返回值:
//   - *ConfigBuilder: 返回自身，支持链式调用
//
// 日志内容:
//   - 请求方法、URL、时间戳
//   - 响应状态码、响应时间
//   - 错误信息（如果有）
//   - 可选的请求体和响应体（需额外配置）
//
// 建议配置:
//   - 开发环境: true（便于调试）
//   - 测试环境: true（便于排查问题）
//   - 生产环境: 根据需要配置（注意性能影响）
//
// 注意事项:
//   - 启用LogRequestBody和LogResponseBody可能暴露敏感信息
//   - 大量日志可能影响性能
func (b *ConfigBuilder) EnableLogging(enable bool) *ConfigBuilder {
	b.config.EnableLogging = enable
	return b
}

// Build 构建最终配置
//
// 功能说明:
//   - 完成配置构建并返回配置对象
//   - 自动验证必填项
//   - 返回深拷贝的配置对象，避免后续修改
//
// 验证规则:
//   - BaseURL: 必填，不能为空
//   - APIKey: 必填，不能为空
//   - APISecret: 必填，不能为空
//
// 返回值:
//   - *HTTPClientConfig: 构建完成的配置对象
//
// Panic情况:
//   - BaseURL未设置时触发panic
//   - APIKey未设置时触发panic
//   - APISecret未设置时触发panic
//
// 使用示例:
//
//	// 完整示例
//	config := client.NewConfigBuilder().
//	    BaseURL("https://open-api-test.maiyatian.com").
//	    APIKey("your_app_key").
//	    APISecret("your_app_secret").
//	    MaxConnections(100).
//	    RequestTimeout(30 * time.Second).
//	    RetryMaxAttempts(5).
//	    EnableLogging(true).
//	    Build()
//
//	// 最简示例（其他使用默认值）
//	config := client.NewConfigBuilder().
//	    BaseURL("https://open-api-test.maiyatian.com").
//	    APIKey("your_app_key").
//	    APISecret("your_app_secret").
//	    Build()
//
// 注意事项:
//   - 必须最后调用Build()方法
//   - Build()返回配置副本，原builder不受影响
//   - 可以复用builder构建多个配置
func (b *ConfigBuilder) Build() *HTTPClientConfig {
	// 验证必填字段
	if b.config.BaseURL == "" {
		panic("BaseURL 不能为空，请使用 BaseURL() 方法设置 API 基础地址")
	}
	if b.config.APIKey == "" {
		panic("APIKey 不能为空，请使用 APIKey() 方法设置应用密钥")
	}
	if b.config.APISecret == "" {
		panic("APISecret 不能为空，请使用 APISecret() 方法设置应用密钥")
	}

	// 深拷贝返回配置，避免外部修改影响内部状态
	return &HTTPClientConfig{
		// 基础配置
		BaseURL:   b.config.BaseURL,
		APIKey:    b.config.APIKey,
		APISecret: b.config.APISecret,

		// 连接池配置
		MaxConnections:        b.config.MaxConnections,
		MaxConnectionsPerHost: b.config.MaxConnectionsPerHost,
		KeepAliveTimeout:      b.config.KeepAliveTimeout,

		// 超时配置
		RequestTimeout:    b.config.RequestTimeout,
		ConnectionTimeout: b.config.ConnectionTimeout,
		ReadTimeout:       b.config.ReadTimeout,

		// 重试配置
		RetryMaxAttempts: b.config.RetryMaxAttempts,
		RetryBaseDelay:   b.config.RetryBaseDelay,
		RetryMaxDelay:    b.config.RetryMaxDelay,

		// 日志配置
		EnableLogging:   b.config.EnableLogging,
		LogRequestBody:  b.config.LogRequestBody,
		LogResponseBody: b.config.LogResponseBody,
	}
}

// ConfigValidationError 配置验证错误
//
// 说明:
//   - 配置验证失败时返回的错误类型
//   - 包含失败的字段名和错误消息
//
// 字段说明:
//   - Field: 验证失败的配置字段名
//   - Message: 错误描述信息
//
// 使用示例:
//
//	if err := config.Validate(); err != nil {
//	    if configErr, ok := err.(*ConfigValidationError); ok {
//	        log.Printf("配置项 %s 验证失败: %s", configErr.Field, configErr.Message)
//	    }
//	}
type ConfigValidationError struct {
	Field   string // 出错的字段名
	Message string // 错误描述信息
}

// Error 实现 error 接口
//
// 返回值:
//   - string: 格式化的错误信息
func (e *ConfigValidationError) Error() string {
	return fmt.Sprintf("配置验证失败 [%s]: %s", e.Field, e.Message)
}
