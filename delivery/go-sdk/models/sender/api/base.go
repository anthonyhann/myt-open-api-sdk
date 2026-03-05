/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: api
 * @ClassName: base
 * @Description: 麦芽田配送开放平台 - Sender接口基础定义
 * Sender代表三方配送服务商主动调用麦芽田开放平台的接口
 * @Version 1.0
 */

package api

import (
	"context"

	"github.com/maiyatian/delivery/myt-go-sdk/client"
	entity "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/auth"
	entityDelivery "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/delivery"
	entityExpress "github.com/maiyatian/delivery/myt-go-sdk/models/sender/entity/express"
)

// IDeliverySender 配送服务发送端接口定义
//
// 接口说明:
//   - Sender接口用于三方配送服务商主动向麦芽田平台推送数据和请求服务
//   - 包括：配送状态同步、快递轨迹回传、授权相关接口
//
// 使用场景:
//   - 三方配送服务商需要向麦芽田平台推送配送状态时
//   - 三方配送服务商需要推送快递轨迹信息时
//   - 三方配送服务商需要获取或刷新访问令牌时
type IDeliverySender interface {
	// DeliveryChange 配送状态同步(必接)
	//
	// 功能: 同步订单配送状态操作
	// 说明: 三方配送服务商主动推送订单配送状态到麦芽田平台
	// command: delivery_change
	// 必接: 是
	DeliveryChange(ctx context.Context, token string, data *entityDelivery.DeliveryChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// LocationChange 快递轨迹回传(必接)
	//
	// 功能: 推送快递轨迹信息
	// 说明: 三方配送服务商主动推送快递轨迹信息到麦芽田平台
	// command: location_change
	// 必接: 是
	LocationChange(ctx context.Context, token string, data *entityExpress.LocationChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// AccessToken 授权码code换取访问令牌Token(必接)
	//
	// 功能: 用授权码换取访问令牌
	// 说明: 用授权码code换取长时令牌refreshToken以及访问令牌accessToken
	// command: access_token
	// 必接: 是
	AccessToken(ctx context.Context, data *entity.AccessTokenReq) (*client.ApiResponse[*entity.AccessTokenResp], error)

	// RefreshToken 刷新访问令牌Token(必接)
	//
	// 功能: 刷新过期的访问令牌
	// 说明: 当token过期时，使用refresh_token重新获取新的token
	// command: refresh_token
	// 必接: 是
	RefreshToken(ctx context.Context, token string, data *entity.RefreshTokenReq) (*client.ApiResponse[*entity.RefreshTokenResp], error)
}

// deliverySender Sender接口实现
type deliverySender struct {
	apiClient *client.HTTPClientManger
}

type ApiResponseEmptyData = any

// mergeConfig 合并用户配置到默认配置
// 用户配置中的非零值会覆盖默认配置
func mergeConfig(defaultConfig *client.HTTPClientConfig, userConfig *client.HTTPClientConfig) *client.HTTPClientConfig {
	if userConfig == nil {
		return defaultConfig
	}

	// 基础配置
	if userConfig.BaseURL != "" {
		defaultConfig.BaseURL = userConfig.BaseURL
	}
	if userConfig.APIKey != "" {
		defaultConfig.APIKey = userConfig.APIKey
	}
	if userConfig.APISecret != "" {
		defaultConfig.APISecret = userConfig.APISecret
	}

	// 连接池配置
	if userConfig.MaxConnections != 0 {
		defaultConfig.MaxConnections = userConfig.MaxConnections
	}
	if userConfig.MaxConnectionsPerHost != 0 {
		defaultConfig.MaxConnectionsPerHost = userConfig.MaxConnectionsPerHost
	}
	if userConfig.KeepAliveTimeout != 0 {
		defaultConfig.KeepAliveTimeout = userConfig.KeepAliveTimeout
	}

	// 超时配置
	if userConfig.RequestTimeout != 0 {
		defaultConfig.RequestTimeout = userConfig.RequestTimeout
	}
	if userConfig.ConnectionTimeout != 0 {
		defaultConfig.ConnectionTimeout = userConfig.ConnectionTimeout
	}
	if userConfig.ReadTimeout != 0 {
		defaultConfig.ReadTimeout = userConfig.ReadTimeout
	}

	// 重试配置
	if userConfig.RetryMaxAttempts != 0 {
		defaultConfig.RetryMaxAttempts = userConfig.RetryMaxAttempts
	}
	if userConfig.RetryBaseDelay != 0 {
		defaultConfig.RetryBaseDelay = userConfig.RetryBaseDelay
	}
	if userConfig.RetryMaxDelay != 0 {
		defaultConfig.RetryMaxDelay = userConfig.RetryMaxDelay
	}

	// 日志配置
	// 注意：bool 类型的零值是 false，所以需要特殊处理
	// 这里我们假设如果用户显式设置了 bool 值，就应该使用用户的值
	defaultConfig.EnableLogging = userConfig.EnableLogging
	defaultConfig.LogRequestBody = userConfig.LogRequestBody
	defaultConfig.LogResponseBody = userConfig.LogResponseBody

	return defaultConfig
}

// NewDeliverySender 创建配送服务发送端实例
//
// 功能说明:
//   - 创建用于三方配送服务商主动调用麦芽田平台的客户端实例
//   - 自动合并默认配置和用户提供的配置
//   - 初始化HTTP客户端管理器
//
// 参数说明:
//   - config: HTTP客户端配置，包含BaseURL、APIKey、APISecret等
//
// 返回值:
//   - IDeliverySender: 配送服务发送端接口实例
//
// 注意事项:
//   - 必须提供有效的APIKey和APISecret
//   - 配置验证失败会触发panic
//
// 使用示例:
//
//	config := client.NewConfigBuilder().
//	    BaseURL("https://open-api.maiyatian.com").
//	    APIKey("your_app_key").
//	    APISecret("your_app_secret").
//	    Build()
//
//	sender := api.NewDeliverySender(config)
func NewDeliverySender(config *client.HTTPClientConfig) IDeliverySender {
	// 获取默认配置
	defaultConfig := client.NewDefaultConfig()

	// 合并用户配置（用户配置优先）
	mergedConfig := mergeConfig(defaultConfig, config)

	// 创建 HTTP 客户端管理器
	apiClient, err := client.NewHTTPClientManager(mergedConfig)
	if err != nil {
		panic(err)
	}

	return &deliverySender{
		apiClient: apiClient,
	}
}
