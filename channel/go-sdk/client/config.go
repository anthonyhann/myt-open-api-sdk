// Package client 提供麦芽田开放平台 Go SDK 的 HTTP 客户端配置和管理功能
package client

import (
	"fmt"
	"time"
)

// HTTPClientConfig 企业级 HTTP 客户端配置
// 提供完整的连接池、超时、重试、日志等企业级特性配置
type HTTPClientConfig struct {
	// ==================== 基础配置 ====================

	// BaseURL 麦芽田开放平台 API 基础地址
	// 测试环境：https://open-api-test.maiyatian.com
	// 正式环境：https://open-api.maiyatian.com
	BaseURL string `json:"base_url"`

	// APIKey 应用密钥（app_key）
	// 由麦芽田开放平台分配，用于标识应用身份
	APIKey string `json:"api_key"`

	// APISecret 应用密钥（app_secret）
	// 由麦芽田开放平台分配，用于请求签名，请妥善保管
	APISecret string `json:"api_secret"`

	// ==================== 连接池配置 ====================

	// MaxConnections 最大连接数
	// 控制整体连接池大小，建议值：50-100
	// 默认值：50
	MaxConnections int `json:"max_connections"`

	// MaxConnectionsPerHost 每个主机的最大连接数
	// 控制单个主机的并发连接数，建议值：10-20
	// 默认值：10
	MaxConnectionsPerHost int `json:"max_connections_per_host"`

	// KeepAliveTimeout 连接保活超时时间
	// 空闲连接在连接池中的保持时间
	// 默认值：30秒
	KeepAliveTimeout time.Duration `json:"keep_alive_timeout"`

	// ==================== 超时配置 ====================

	// RequestTimeout 请求总超时时间
	// 包含连接建立、请求发送、响应接收的总时间
	// 建议值：30-60秒，默认值：60秒
	RequestTimeout time.Duration `json:"request_timeout"`

	// ConnectionTimeout 连接建立超时时间
	// TCP 连接建立的最大等待时间
	// 默认值：30秒
	ConnectionTimeout time.Duration `json:"connection_timeout"`

	// ReadTimeout 读取响应超时时间
	// 从服务器读取响应数据的最大等待时间
	// 默认值：60秒
	ReadTimeout time.Duration `json:"read_timeout"`

	// ==================== 重试配置 ====================

	// RetryMaxAttempts 最大重试次数
	// 发生网络错误或 5xx 服务器错误时的重试次数
	// 建议值：2-3次，默认值：3
	RetryMaxAttempts int `json:"retry_max_attempts"`

	// RetryBaseDelay 重试基础延迟时间
	// 第一次重试前的等待时间
	// 默认值：1秒
	RetryBaseDelay time.Duration `json:"retry_base_delay"`

	// RetryMaxDelay 重试最大延迟时间
	// 重试等待时间的上限（使用指数退避策略）
	// 默认值：30秒
	RetryMaxDelay time.Duration `json:"retry_max_delay"`

	// ==================== 日志配置 ====================

	// EnableLogging 是否启用日志记录
	// 开启后会记录请求和响应的详细信息
	// 默认值：true
	EnableLogging bool `json:"enable_logging"`

	// LogRequestBody 是否记录请求体
	// 开启后会在日志中输出完整的请求体（注意：可能包含敏感信息）
	// 默认值：false
	LogRequestBody bool `json:"log_request_body"`

	// LogResponseBody 是否记录响应体
	// 开启后会在日志中输出完整的响应体
	// 默认值：false
	LogResponseBody bool `json:"log_response_body"`
}

// NewDefaultConfig 创建默认配置
// 返回一个包含合理默认值的配置对象，适用于大多数场景
// 使用前需要设置 APIKey 和 APISecret
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
// 检查必填字段和配置值的合理性
// 返回 ConfigValidationError 类型的错误，包含具体的字段和错误信息
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

// ConfigBuilder 配置构建器（Builder 模式）
// 提供链式调用的配置构建方式，使用更加灵活和直观
//
// 使用示例：
//
//	config := client.NewConfigBuilder().
//	    BaseURL("https://open-api-test.maiyatian.com").
//	    APIKey("your_app_key").
//	    APISecret("your_app_secret").
//	    RequestTimeout(30 * time.Second).
//	    Build()
type ConfigBuilder struct {
	config *HTTPClientConfig
}

// NewConfigBuilder 创建新的配置构建器
// 初始化时使用默认配置，然后通过链式调用修改需要的配置项
func NewConfigBuilder() *ConfigBuilder {
	return &ConfigBuilder{
		config: NewDefaultConfig(),
	}
}

// BaseURL 设置 API 基础地址
// url: API 基础地址，如 https://open-api.maiyatian.com
// 测试环境：https://open-api-test.maiyatian.com
// 正式环境：https://open-api.maiyatian.com
func (b *ConfigBuilder) BaseURL(url string) *ConfigBuilder {
	b.config.BaseURL = url
	return b
}

// APIKey 设置应用密钥（app_key）
// key: 由麦芽田开放平台分配的应用标识
func (b *ConfigBuilder) APIKey(key string) *ConfigBuilder {
	b.config.APIKey = key
	return b
}

// APISecret 设置应用密钥（app_secret）
// secret: 由麦芽田开放平台分配的应用密钥，用于签名，请妥善保管
func (b *ConfigBuilder) APISecret(secret string) *ConfigBuilder {
	b.config.APISecret = secret
	return b
}

// MaxConnections 设置最大连接数
// max: 连接池最大连接数，建议值 50-100
func (b *ConfigBuilder) MaxConnections(max int) *ConfigBuilder {
	b.config.MaxConnections = max
	return b
}

// RequestTimeout 设置请求超时时间
// timeout: 请求总超时时间，建议值 30-60 秒
func (b *ConfigBuilder) RequestTimeout(timeout time.Duration) *ConfigBuilder {
	b.config.RequestTimeout = timeout
	return b
}

// RetryMaxAttempts 设置最大重试次数
// attempts: 网络错误或 5xx 错误时的重试次数，建议值 2-3 次
func (b *ConfigBuilder) RetryMaxAttempts(attempts int) *ConfigBuilder {
	b.config.RetryMaxAttempts = attempts
	return b
}

// EnableLogging 设置是否启用日志记录
// enable: true 启用日志，false 禁用日志
func (b *ConfigBuilder) EnableLogging(enable bool) *ConfigBuilder {
	b.config.EnableLogging = enable
	return b
}

// Build 构建最终配置
// 验证必填字段后返回配置对象的深拷贝
// 如果必填字段缺失，会触发 panic
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
// 包含出错的字段名和错误描述信息
type ConfigValidationError struct {
	Field   string // 出错的字段名
	Message string // 错误描述信息
}

// Error 实现 error 接口
// 返回格式化的错误信息
func (e *ConfigValidationError) Error() string {
	return fmt.Sprintf("配置验证失败 [%s]: %s", e.Field, e.Message)
}
