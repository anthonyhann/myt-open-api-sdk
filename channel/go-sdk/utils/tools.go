// Package utils 提供SDK的通用工具函数
// 包含结构体转Map、字符串处理、加密解密等常用工具
package utils

import (
	"fmt"
	"reflect"
)

// ToMap 将结构体转换为 map[string]any
// 使用反射机制提取结构体字段及其值，可指定 tag 名称作为 key
//
// 参数：
//
//	in: 待转换的结构体或结构体指针
//	tagName: 使用的 tag 名称（如 "json"），为空则使用字段名
//
// 返回：
//
//	map[string]any: 转换后的 map，key 为 tag 值或字段名，value 为字段值
//	error: 转换失败时返回错误（仅接受结构体或结构体指针）
//
// 使用示例：
//
//	type User struct {
//	    Name string `json:"name"`
//	    Age  int    `json:"age"`
//	}
//	user := &User{Name: "张三", Age: 25}
//	m, err := utils.ToMap(user, "json")
//	// m = {"name": "张三", "age": 25}
//
// 注意事项：
//   - 仅支持结构体或结构体指针类型
//   - 如果 tagName 为空，使用字段名作为 key
//   - 导出字段（首字母大写）和未导出字段都会被转换
func ToMap(in any, tagName string) (map[string]any, error) {
	// 初始化输出 map
	out := make(map[string]any)

	// 获取反射值
	v := reflect.ValueOf(in)

	// 如果是指针，解引用获取实际值
	if v.Kind() == reflect.Ptr {
		v = v.Elem()
	}

	// 验证输入类型必须是结构体
	if v.Kind() != reflect.Struct {
		return nil, fmt.Errorf("ToMap 仅接受结构体或结构体指针类型，实际类型：%T", in)
	}

	// 获取结构体类型信息
	t := v.Type()

	// 遍历结构体字段
	for i := range v.NumField() {
		fi := t.Field(i)    // 字段类型信息
		tagValue := fi.Name // 默认使用字段名

		// 如果指定了 tagName，尝试获取 tag 值
		if tagName != "" {
			tagValue = fi.Tag.Get(tagName)
		}

		// 将字段值存入 map
		out[tagValue] = v.Field(i).Interface()
	}

	return out, nil
}
