// Package vars 提供麦芽田配送开放平台 Go SDK 的全局变量
package vars

import (
	"os"
	"strconv"
)

// IsDebugging 调试模式开关
// 从环境变量 DEBUG 中读取布尔值，控制SDK的调试行为
//
// 设置方式：
//
//	export DEBUG=true   # 开启调试模式
//	export DEBUG=false  # 关闭调试模式（默认）
//
// 调试模式下的行为：
//   - 输出更详细的HTTP请求日志
//   - 显示签名生成过程
//   - 输出错误堆栈信息
//   - 记录重试详情
//
// 使用示例：
//
//	if vars.IsDebugging {
//	    log.Printf("调试信息: 请求URL=%s", url)
//	}
//
// 注意：
//   - 生产环境建议设为false，避免敏感信息泄露
//   - 解析失败时默认为false（安全优先）
var IsDebugging, _ = strconv.ParseBool(os.Getenv("DEBUG"))
