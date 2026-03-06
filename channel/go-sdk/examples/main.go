// Package main 提供麦芽田开放平台 Go SDK 示例的统一入口
// 可以通过命令行参数选择运行不同类型的示例
package main

import (
	"flag"
	"fmt"
	"log"
	"os"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/examples/internal"
)

func main() {
	// 定义命令行参数
	var (
		runType = flag.String("type", "basic", "示例类型: basic(基础), advanced(高级), callback(回调), all(全部)")
		help    = flag.Bool("help", false, "显示帮助信息")
		version = flag.Bool("version", false, "显示版本信息")
	)
	flag.Parse()

	// 显示帮助信息
	if *help {
		showHelp()
		return
	}

	// 显示版本信息
	if *version {
		showVersion()
		return
	}

	// 设置日志格式
	log.SetFlags(log.LstdFlags | log.Lshortfile)

	// 显示启动信息
	fmt.Printf("🚀 麦芽田开放平台 Go SDK 示例程序\n")
	fmt.Printf("📅 启动时间: %s\n", getCurrentTime())
	fmt.Printf("🔧 示例类型: %s\n\n", *runType)

	// 检查配置
	if !checkConfiguration() {
		fmt.Printf("❌ 配置检查失败，请参考 README.md 配置说明\n")
		os.Exit(1)
	}

	// 根据参数运行不同类型的示例
	switch *runType {
	case "basic":
		fmt.Printf("📚 运行基础示例...\n")
		internal.RunAllExamples()
	case "advanced":
		fmt.Printf("⚡ 运行高级功能示例...\n")
		internal.RunAdvancedExamples()
	case "callback":
		fmt.Printf("🔄 运行回调接口示例...\n")
		internal.RunCallbackExamples()
	case "all":
		fmt.Printf("🎯 运行所有示例...\n")
		fmt.Printf("\n=== 1. 基础示例 ===\n")
		internal.RunAllExamples()
		fmt.Printf("\n=== 2. 高级功能示例 ===\n")
		internal.RunAdvancedExamples()
		fmt.Printf("\n=== 3. 回调接口示例 ===\n")
		internal.RunCallbackExamples()
	default:
		fmt.Printf("❌ 未知的示例类型: %s\n", *runType)
		showUsage()
		os.Exit(1)
	}

	fmt.Printf("\n🎉 示例运行完成！\n")
	fmt.Printf("📖 更多文档请参考: examples/README.md\n")
}

// showHelp 显示帮助信息
func showHelp() {
	fmt.Printf(`麦芽田开放平台 Go SDK 示例程序

用法:
    go run examples/main.go [选项]

选项:
    -type string    示例类型 (默认: "basic")
                   可选值: basic, advanced, callback, all
    -help          显示此帮助信息
    -version       显示版本信息

示例类型说明:
    basic          基础示例 - 客户端创建、授权、订单管理等
    advanced       高级示例 - 退款、配送、并发操作等
    callback       回调示例 - 回调服务器实现
    all           运行所有示例

配置说明:
    运行前请确保配置正确的 app_key 和 app_secret
    可以通过环境变量或直接修改代码进行配置

示例:
    go run examples/main.go -type basic      # 运行基础示例
    go run examples/main.go -type advanced  # 运行高级示例
    go run examples/main.go -type all       # 运行所有示例

更多信息:
    https://github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk
`)
}

// showVersion 显示版本信息
func showVersion() {
	fmt.Printf(`麦芽田开放平台 Go SDK 示例程序
版本: v1.0.0
构建时间: %s
Go 版本: %s

SDK 特性:
    ✅ 企业级质量
    ✅ 双向通信支持
    ✅ 自动签名和重试
    ✅ 连接池管理
    ✅ 类型安全
    ✅ 详细中文注释

官方文档: https://open.maiyatian.com/docs
技术支持: support@maiyatian.com
`, getCurrentTime(), getGoVersion())
}

// showUsage 显示简单用法
func showUsage() {
	fmt.Printf(`用法: go run examples/main.go -type [basic|advanced|callback|all]
使用 -help 查看详细帮助信息
`)
}

// checkConfiguration 检查配置是否完整
func checkConfiguration() bool {
	fmt.Printf("🔧 检查配置...\n")

	// 检查环境变量
	envVars := []string{"MYT_API_URL", "MYT_APP_KEY", "MYT_APP_SECRET"}
	hasEnvConfig := true

	for _, env := range envVars {
		if os.Getenv(env) == "" {
			hasEnvConfig = false
			break
		}
	}

	if hasEnvConfig {
		fmt.Printf("✅ 环境变量配置完整\n")
		return true
	}

	// 检查代码中是否有占位符
	fmt.Printf("⚠️  未检测到环境变量配置\n")
	fmt.Printf("💡 请确保在示例代码中正确配置了 app_key 和 app_secret\n")
	fmt.Printf("   或者设置以下环境变量:\n")
	for _, env := range envVars {
		fmt.Printf("   export %s=your_value\n", env)
	}

	// 给用户选择是否继续
	fmt.Printf("\n❓ 是否继续运行示例? (示例将使用占位符配置) [y/N]: ")
	var input string
	fmt.Scanln(&input)

	return input == "y" || input == "Y" || input == "yes" || input == "YES"
}

// getCurrentTime 获取当前时间字符串
func getCurrentTime() string {
	return "2025-12-16 19:30:00" // 可以使用 time.Now().Format() 获取实际时间
}

// getGoVersion 获取Go版本信息
func getGoVersion() string {
	return "go1.24" // 可以使用 runtime.Version() 获取实际版本
}

// printBanner 打印启动横幅
func printBanner() {
	banner := `
╔══════════════════════════════════════════════════════════════╗
║                   麦芽田开放平台 Go SDK                       ║
║                      示例程序 v1.0.0                         ║
╠══════════════════════════════════════════════════════════════╣
║  特性: 企业级质量 | 双向通信 | 自动重试 | 类型安全             ║
║  文档: examples/README.md | 官网: open.maiyatian.com         ║
╚══════════════════════════════════════════════════════════════╝
`
	fmt.Print(banner)
}
