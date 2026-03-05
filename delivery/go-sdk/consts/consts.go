// Package consts 提供麦芽田配送开放平台 Go SDK 的常量定义
// 包含HTTP头、状态码等全局常量，确保整个SDK的一致性
package consts

// HTTP Content-Type 相关常量
const (
	// ContentTypeJson 标准JSON内容类型
	// 用于HTTP请求头中的Content-Type字段
	ContentTypeJson = "application/json"

	// ContentTypeJsonUTF JSON内容类型（带UTF-8编码声明）
	// 推荐使用此类型，确保中文字符正确处理
	// 麦芽田配送API推荐使用UTF-8编码
	ContentTypeJsonUTF = "application/json; charset=utf-8"
)

// HTTP状态码相关常量
const (
	// StatusExceptionServerError 服务器异常状态码分界线
	// HTTP状态码 >= 600 被视为服务器严重异常
	// SDK会在遇到此类状态码时停止重试机制
	// 用于区分普通5xx错误和严重系统异常
	StatusExceptionServerError = 600
)
