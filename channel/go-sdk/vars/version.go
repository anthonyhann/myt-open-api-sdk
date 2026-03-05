// Package vars 提供麦芽田开放平台 Go SDK 的全局变量
// 包含版本信息、调试开关等运行时变量
package vars

import _ "embed"

// 自动生成版本信息
// 使用 go:generate 指令调用脚本自动更新版本号
//go:generate bash ./gen_version.sh

// VERSION SDK版本号
// 通过 go:embed 指令从 version.txt 文件嵌入版本信息
// 版本格式：v1.2.3 或 v1.2.3-beta.1
//
// 使用示例：
//
//	fmt.Printf("当前SDK版本: %s", vars.VERSION)
//
// 注意：
//   - 此变量在编译时确定，运行时只读
//   - 版本信息用于HTTP请求头中的User-Agent
//   - 遵循语义化版本控制(SemVer)规范
//
//go:embed version.txt
var VERSION string
