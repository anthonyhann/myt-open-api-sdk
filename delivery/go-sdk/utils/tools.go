/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: utils
 * @ClassName: tools
 * @Description: 麦芽田配送开放平台SDK - 工具函数集合
 * @Version 1.0
 */

package utils

import (
	"fmt"
	"reflect"
)

// ToMap 将结构体转换为map[string]any
//
// 功能说明:
//   - 将Go结构体转换为键值对映射
//   - 支持使用结构体标签（tag）作为map的键名
//   - 主要用于签名生成等场景
//
// 参数说明:
//   - in: 输入的结构体或结构体指针
//   - tagName: 用作map键名的结构体标签名称（如"json"），空字符串时使用字段名
//
// 返回值:
//   - map[string]any: 转换后的映射，键为标签值或字段名，值为字段值
//   - error: 错误信息，成功时为nil
//
// 错误情况:
//   - 输入参数不是结构体或结构体指针时返回错误
//
// 使用示例:
//
//	type User struct {
//	    Name  string `json:"name"`
//	    Age   int    `json:"age"`
//	    Email string `json:"email"`
//	}
//
//	user := User{Name: "张三", Age: 25, Email: "zhangsan@example.com"}
//
//	// 使用json标签作为键名
//	m, err := ToMap(user, "json")
//	// m = {"name": "张三", "age": 25, "email": "zhangsan@example.com"}
//
//	// 使用字段名作为键名
//	m, err := ToMap(user, "")
//	// m = {"Name": "张三", "Age": 25, "Email": "zhangsan@example.com"}
//
// 注意事项:
//   - 仅转换导出的（首字母大写的）字段
//   - 嵌套结构体不会递归展开
//   - 支持结构体指针作为输入
func ToMap(in any, tagName string) (map[string]any, error) {
	out := make(map[string]any)

	v := reflect.ValueOf(in)
	if v.Kind() == reflect.Ptr {
		v = v.Elem()
	}

	if v.Kind() != reflect.Struct {
		return nil, fmt.Errorf("ToMap only accepts struct or struct pointer; got %T", v)
	}

	t := v.Type()
	for i := range v.NumField() {
		fi := t.Field(i)
		tagValue := fi.Name
		if tagName != "" {
			tagValue = fi.Tag.Get(tagName)
		}
		out[tagValue] = v.Field(i).Interface()
	}
	return out, nil
}
