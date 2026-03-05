/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: client
 * @ClassName: http_client
 * @Description: 麦芽田配送开放平台SDK - HTTP客户端实现
 * 提供企业级HTTP客户端，支持连接池、重试、日志等特性
 * @Version 1.0
 */

// Package client provides HTTP client configuration and management
package client

import (
	"context"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"encoding/hex"
	"encoding/json"
	"errors"
	"fmt"
	"log"
	"net/http"
	"os"
	"sort"
	"strings"
	"time"

	"github.com/go-resty/resty/v2"
	"github.com/maiyatian/delivery/myt-go-sdk/consts"
	"github.com/maiyatian/delivery/myt-go-sdk/utils"
	"github.com/maiyatian/delivery/myt-go-sdk/vars"
)

// SDKVersion SDK 版本号
// 从 vars.VERSION 获取，该值来自 version.txt 文件
var SDKVersion = strings.TrimSpace(vars.VERSION)

// HTTPClientManger 企业级 HTTP 客户端管理器
// 封装了麦芽田开放平台 API 调用的所有功能：
// - 连接池管理：复用 HTTP 连接，提升性能
// - 自动重试：网络错误和 5xx 错误自动重试
// - 签名生成：自动生成符合麦芽田规范的请求签名
// - 日志记录：记录请求和响应的详细信息，便于调试
// - 超时控制：连接、请求、读取的多层超时保护
type HTTPClientManger struct {
	config *HTTPClientConfig // 客户端配置
	client *resty.Client     // 底层 HTTP 客户端
	logger *log.Logger       // 日志记录器
}

// HTTPRequest 标准请求结构
//
// 字段说明:
//   - AppKey: 应用密钥，用于标识调用方
//   - RequestId: 请求唯一标识，用于追踪请求链路
//   - Token: 授权令牌，用于身份验证
//   - Timestamp: 请求时间戳，Unix秒级时间戳
//   - Data: 业务数据，JSON字符串格式
//   - Signature: 请求签名，HmacSHA256签名值
type HTTPRequest struct {
	AppKey    string `json:"app_key"`    // 应用密钥，由麦芽田开放平台分配
	RequestId string `json:"request_id"` // 请求唯一标识（UUID），每次请求需生成新值
	Token     string `json:"token"`      // 商户授权令牌
	Timestamp int64  `json:"timestamp"`  // 请求时间戳（Unix 秒），需与实际时间误差不超过 10 分钟
	Data      string `json:"data"`       // 业务参数，序列化为 JSON 字符串
	Signature string `json:"signature"`  // 请求签名，用于验证请求合法性
}

// HTTPResponse 标准化HTTP响应结构
//
// 字段说明:
//   - Code: 业务状态码，200表示成功
//   - Message: 响应消息，成功时为"ok"
//   - Data: 业务数据，JSON字符串格式（需要反序列化）
type HTTPResponse struct {
	Code    int    `json:"code"`          // 状态码：200 表示成功，其他表示失败
	Message string `json:"message"`       // 返回消息：成功时为 "ok"，失败时为错误描述
	Data    string `json:"data,optional"` // 业务数据，序列化为 JSON 字符串（可选）
}

func (r *HTTPResponse) UnmarshalData(formatData *any) error {
	dataBytes := []byte(r.Data)
	err := json.Unmarshal(dataBytes, formatData)
	return err
}

type ApiResponse[T any] struct {
	Code    int    `json:"code"`          // 状态码：200 表示成功，其他表示失败
	Message string `json:"message"`       // 返回消息：成功时为 "ok"，失败时为错误描述
	Data    *T     `json:"data,optional"` // 业务数据，序列化为 JSON 字符串（可选）
}

func (r *ApiResponse[T]) FormatData(response *HTTPResponse) error {
	dataBytes := []byte(response.Data)
	err := json.Unmarshal(dataBytes, r.Data)
	return err
}

// NewHTTPClientManager 创建新的 HTTP 客户端管理器
//
// 参数：
//
//	config: HTTP 客户端配置，必须通过 Validate() 验证
//
// 返回：
//
//	*HTTPClientManger: 客户端管理器实例
//	error: 配置验证失败时返回错误
//
// 功能特性：
//   - 连接池管理：配置最大连接数和每主机连接数
//   - 自动重试：网络错误和 5xx 错误自动重试，支持指数退避
//   - 请求头设置：自动添加 Content-Type、User-Agent 等标准请求头
//   - JSON 编解码：使用 Go 标准库 encoding/json
//   - 日志记录：可选的请求和响应日志
func NewHTTPClientManager(config *HTTPClientConfig) (*HTTPClientManger, error) {
	// 验证配置有效性
	if err := config.Validate(); err != nil {
		return nil, fmt.Errorf("配置验证失败: %w", err)
	}

	// 创建 resty HTTP 客户端
	client := resty.New()

	// 配置基础设置
	client.SetBaseURL(config.BaseURL)                // API 基础地址
	client.SetTimeout(config.RequestTimeout)         // 请求总超时
	client.SetRetryCount(config.RetryMaxAttempts)    // 最大重试次数
	client.SetRetryWaitTime(config.RetryBaseDelay)   // 重试基础延迟
	client.SetRetryMaxWaitTime(config.RetryMaxDelay) // 重试最大延迟

	// 设置默认请求头
	client.SetHeaders(map[string]string{
		"Content-Type":    consts.ContentTypeJsonUTF,                // 请求体格式
		"User-Agent":      fmt.Sprintf("Myt-Go-SDK/%s", SDKVersion), // SDK 标识
		"Accept":          consts.ContentTypeJson,                   // 接受响应格式
		"Accept-Encoding": "gzip, deflate",                          // 支持压缩
	})

	// 配置连接池（提升性能，复用连接）
	transport := client.GetClient().Transport.(*http.Transport)
	transport.MaxIdleConns = config.MaxConnections               // 最大空闲连接数
	transport.MaxIdleConnsPerHost = config.MaxConnectionsPerHost // 每个主机的最大空闲连接数
	transport.IdleConnTimeout = config.KeepAliveTimeout          // 空闲连接超时时间

	// 设置重试条件：网络错误或 5xx 错误时重试
	client.AddRetryCondition(func(r *resty.Response, err error) bool {
		// 网络错误，需要重试
		if err != nil {
			return true
		}

		// HTTP 5xx 服务器错误，需要重试
		if r.StatusCode() >= http.StatusInternalServerError {
			return true
		}

		// 业务层 5xx 错误，需要重试（响应 code 字段）
		if r.StatusCode() == http.StatusOK {
			var response HTTPResponse
			if jsonErr := json.Unmarshal(r.Body(), &response); jsonErr == nil {
				return response.Code >= http.StatusInternalServerError && response.Code < consts.StatusExceptionServerError
			}
		}

		return false
	})

	// 创建客户端管理器实例
	manager := &HTTPClientManger{
		config: config,
		client: client,
	}

	// 配置日志记录（如果启用）
	if config.EnableLogging {
		manager.logger = log.New(os.Stderr, "[MytSDK] ", log.LstdFlags|log.Lshortfile)
		manager.setupLogging()
	}

	return manager, nil
}

// setupLogging 配置 HTTP 请求和响应的日志记录
// 使用 resty 的中间件机制，在请求前后记录详细信息
// 日志格式为 JSON，便于解析和分析
func (h *HTTPClientManger) setupLogging() {
	// 检查日志是否启用
	if !h.config.EnableLogging || h.logger == nil {
		return
	}

	// 请求日志中间件：在发送请求前记录请求信息
	h.client.OnBeforeRequest(func(c *resty.Client, req *resty.Request) error {
		logData := map[string]interface{}{
			"method":    req.Method,                      // HTTP 方法（GET/POST）
			"url":       req.URL,                         // 请求 URL
			"has_body":  req.Body != nil,                 // 是否有请求体
			"timestamp": time.Now().Format(time.RFC3339), // 请求时间
		}

		// 记录请求体内容（如果配置允许）
		// 注意：请求体可能包含敏感信息，生产环境建议关闭
		if req.Body != nil && h.config.LogRequestBody {
			if bodyBytes, ok := req.Body.([]byte); ok {
				var bodyData map[string]interface{}
				if err := json.Unmarshal(bodyBytes, &bodyData); err == nil {
					logData["body"] = bodyData
				}
			}
		}

		// 输出 JSON 格式日志
		if logJSON, err := json.Marshal(logData); err == nil {
			h.logger.Printf("请求: %s", string(logJSON))
		}
		return nil
	})

	// 响应日志中间件：在收到响应后记录响应信息
	h.client.OnAfterResponse(func(c *resty.Client, resp *resty.Response) error {
		logData := map[string]interface{}{
			"status_code":      resp.StatusCode(),               // HTTP 状态码
			"response_time_ms": resp.Time().Milliseconds(),      // 响应时间（毫秒）
			"response_size":    len(resp.Body()),                // 响应体大小（字节）
			"timestamp":        time.Now().Format(time.RFC3339), // 响应时间
		}

		// 记录响应体内容（如果配置允许）
		if h.config.LogResponseBody && len(resp.Body()) > 0 {
			var responseData map[string]interface{}
			if err := json.Unmarshal(resp.Body(), &responseData); err == nil {
				logData["body"] = responseData
			}
		}

		// 输出 JSON 格式日志
		if logJSON, err := json.Marshal(logData); err == nil {
			h.logger.Printf("响应: %s", string(logJSON))
		}
		return nil
	})
}

// Request 执行 HTTP 请求（核心方法）
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	method: HTTP 方法，支持 GET 和 POST
//	path: API 路径，如 "v1/channel/order_created"
//	data: 业务参数，会被序列化为 JSON 字符串放入 data 字段
//	token: 商户授权令牌
//	headers: 额外的 HTTP 请求头（可选）
//
// 返回：
//
//	*HTTPResponse: API 响应结构
//	error: 请求失败或业务错误时返回
//
// 功能说明：
//  1. 自动生成请求 ID 和时间戳
//  2. 自动生成请求签名
//  3. 自动处理重试（网络错误和 5xx 错误）
//  4. 自动解析响应并检查业务状态码
func (h *HTTPClientManger) Request(ctx context.Context, method string, path string, data any, token string, headers *map[string]string) (*HTTPResponse, error) {
	// 创建请求并设置上下文
	req := h.client.R().SetContext(ctx)

	// 设置额外的请求头
	if headers != nil {
		req.SetHeaders(*headers)
	}

	// 验证业务参数
	if data == nil {
		return nil, errors.New("业务参数 data 不能为空")
	}

	// 序列化业务参数为 JSON 字符串
	dataBytes, err := json.Marshal(data)
	if err != nil {
		return nil, fmt.Errorf("序列化业务参数失败: %w", err)
	}
	dataString := string(dataBytes)

	// 构建麦芽田标准请求结构
	reqData := &HTTPRequest{
		AppKey:    h.config.APIKey,       // 应用密钥
		RequestId: h.generateRequestID(), // 请求唯一标识
		Token:     token,                 // 商户授权令牌
		Timestamp: time.Now().Unix(),     // 当前时间戳
		Data:      dataString,            // 业务参数（JSON 字符串）
		Signature: "",                    // 签名（稍后生成）
	}

	// 生成请求签名
	reqData.Signature, err = h.GenSign(reqData, h.config.APISecret)
	if err != nil {
		return nil, fmt.Errorf("生成请求签名失败: %w", err)
	}

	// 构建完整的请求 URL
	url := path
	if !h.isAbsoluteURL(path) {
		url = h.config.BaseURL + "/" + path
	}

	// 执行 HTTP 请求
	var resp *resty.Response
	switch method {
	case http.MethodGet:
		resp, err = req.Get(url)
	case http.MethodPost:
		// POST 请求需要设置请求体
		reqDataBytes, _ := json.Marshal(reqData)
		reqDataStr := string(reqDataBytes)
		req.SetBody(reqDataStr)
		resp, err = req.Post(url)
	default:
		return nil, fmt.Errorf("不支持的 HTTP 方法: %s", method)
	}

	// 检查网络错误
	if err != nil {
		return nil, fmt.Errorf("HTTP 请求失败: %w", err)
	}

	// 解析响应体
	var response HTTPResponse
	if err := json.Unmarshal(resp.Body(), &response); err != nil {
		return nil, fmt.Errorf("解析响应失败: %w", err)
	}

	// 检查业务状态码（200 表示成功）
	if response.Code != http.StatusOK {
		return &response, fmt.Errorf("API 错误 [%d]: %s", response.Code, response.Message)
	}

	return &response, nil
}

func RequestWithApiClient[T any](ctx context.Context, apiClient *HTTPClientManger, method, path string, data any, token string, headers *map[string]string) (*ApiResponse[T], error) {
	resp, err := apiClient.Request(ctx, method, path, data, token, headers)
	if err != nil {
		return nil, err
	}
	response := ApiResponse[T]{
		Code:    resp.Code,
		Message: resp.Message,
	}

	err = response.FormatData(resp)
	if err != nil {
		return nil, err
	}

	return &response, nil
}

// Get 执行 GET 请求
//
// 参数：
//
//	ctx: 上下文
//	path: API 路径
//	token: 商户授权令牌
//	headers: 额外的请求头（可选）
//
// 返回：
//
//	*HTTPResponse: API 响应
//	error: 请求失败时返回错误
//
// 注意：GET 请求不传递业务参数（data 为 nil）
func (h *HTTPClientManger) Get(ctx context.Context, path string, token string, headers *map[string]string) (*HTTPResponse, error) {
	return h.Request(ctx, http.MethodGet, path, nil, token, headers)
}

// Post 执行 POST 请求
//
// 参数：
//
//	ctx: 上下文，用于超时控制和取消操作
//	path: API 路径，如 "v1/channel/order_created"
//	data: 业务参数，会被序列化为 JSON 字符串
//	token: 商户授权令牌
//	headers: 额外的请求头（可选）
//
// 返回：
//
//	*HTTPResponse: API 响应
//	error: 请求失败或业务错误时返回
//
// 使用示例：
//
//	resp, err := client.Post(ctx, "v1/channel/order_created", orderData, token, nil)
func (h *HTTPClientManger) Post(
	ctx context.Context,
	path string,
	data any,
	token string,
	headers *map[string]string,
) (*HTTPResponse, error) {
	return h.Request(ctx, http.MethodPost, path, data, token, headers)
}

// Close 关闭 HTTP 客户端，释放资源
//
// 返回：
//
//	error: 关闭失败时返回错误（通常不会失败）
//
// 注意：
//
//	resty 客户端会自动管理连接池，无需显式关闭
//	此方法主要用于记录日志和清理可能的资源
func (h *HTTPClientManger) Close() error {
	// resty 客户端自动管理连接池，无需显式关闭
	if h.logger != nil {
		h.logger.Println("HTTP 客户端已关闭")
	}
	return nil
}

// GenSign 生成请求签名
// 签名规则：
// 1. 提取请求体中的 app_key、token、timestamp、data 字段
// 2. 按 key 进行 ksort 排序
// 3. 用半角逗号连接生成字符串（格式：key1=value1,key2=value2）
// 4. 对字符串进行 UTF8 编码
// 5. 使用 appSecret 计算 HmacSHA256 值
// 6. 结果使用 URL 安全的 Base64 编码
func (h *HTTPClientManger) GenSign(in *HTTPRequest, secretKey string) (string, error) {
	// 参数验证
	if in == nil || len(in.Data) == 0 {
		return "", errors.New("request data is required")
	}

	// 将结构体转为 map，便于排序
	data, err := utils.ToMap(in, "json")
	if err != nil {
		return "", errors.New("struct to map fail")
	}

	// 提取所有 key 并排序（ksort）
	keys := make([]string, 0, len(data))
	for k := range data {
		keys = append(keys, k)
	}
	sort.Strings(keys)

	// 按排序后的 key 构建待签名字符串（格式：key1=value1,key2=value2）
	dataToSign := ""
	for _, k := range keys {
		dataToSign += fmt.Sprintf("%s=%v,", k, data[k])
	}
	// 移除最后一个逗号
	dataToSign = dataToSign[:len(dataToSign)-1]

	// 计算 HmacSHA256 签名
	mac := hmac.New(sha256.New, []byte(secretKey))
	mac.Write([]byte(dataToSign))
	signature := mac.Sum(nil)

	// 使用 URL 安全的 Base64 编码返回签名
	return base64.URLEncoding.EncodeToString(signature), nil
}

// GetConfig 获取客户端配置
//
// 返回：
//
//	*HTTPClientConfig: 当前客户端使用的配置
func (h *HTTPClientManger) GetConfig() *HTTPClientConfig {
	return h.config
}

// UpdateTimeout 动态更新请求超时设置
//
// 参数：
//
//	timeout: 新的请求超时时间
//
// 注意：
//
//	此方法会同时更新配置和底层 HTTP 客户端的超时设置
//	修改会立即生效，影响后续所有请求
func (h *HTTPClientManger) UpdateTimeout(timeout time.Duration) {
	h.config.RequestTimeout = timeout
	h.client.SetTimeout(timeout)
}

// UpdateRetrySettings 动态更新重试设置
//
// 参数：
//
//	maxAttempts: 最大重试次数
//	baseDelay: 重试基础延迟时间
//	maxDelay: 重试最大延迟时间（指数退避的上限）
//
// 注意：
//
//	此方法会同时更新配置和底层 HTTP 客户端的重试设置
//	修改会立即生效，影响后续所有请求
func (h *HTTPClientManger) UpdateRetrySettings(maxAttempts int, baseDelay, maxDelay time.Duration) {
	h.config.RetryMaxAttempts = maxAttempts
	h.config.RetryBaseDelay = baseDelay
	h.config.RetryMaxDelay = maxDelay

	h.client.SetRetryCount(maxAttempts)
	h.client.SetRetryWaitTime(baseDelay)
	h.client.SetRetryMaxWaitTime(maxDelay)
}

// isAbsoluteURL 检查 URL 是否为绝对 URL
//
// 参数：
//
//	url: 待检查的 URL 字符串
//
// 返回：
//
//	bool: true 表示绝对 URL（以 http:// 或 https:// 开头），false 表示相对 URL
func (h *HTTPClientManger) isAbsoluteURL(url string) bool {
	return strings.HasPrefix(url, "http://") || strings.HasPrefix(url, "https://")
}

// generateRequestID 生成请求唯一标识（UUID 格式）
//
// 返回：
//
//	string: UUID 格式的请求 ID，如 "a1b2c3d4-e5f6-7890-abcd-ef1234567890"
//
// 格式说明：
//
//	使用 16 字节（128 位）随机数生成标准 UUID 格式
//	格式：8-4-4-4-12（以十六进制表示）
//
// 注意：
//
//	如果随机数生成失败，会使用备用值（不影响功能，但可能影响唯一性）
func (h *HTTPClientManger) generateRequestID() string {
	// 生成 16 字节随机数（128 位）
	b := make([]byte, 16)
	if _, err := rand.Read(b); err != nil {
		// 随机数生成失败时的备用值
		b = []byte("fallback-request-id")
	}

	// 格式化为标准 UUID 格式：8-4-4-4-12
	return fmt.Sprintf("%s-%s-%s-%s-%s",
		hex.EncodeToString(b[0:4]),  // 8 位
		hex.EncodeToString(b[4:6]),  // 4 位
		hex.EncodeToString(b[6:8]),  // 4 位
		hex.EncodeToString(b[8:10]), // 4 位
		hex.EncodeToString(b[10:]))  // 12 位
}
