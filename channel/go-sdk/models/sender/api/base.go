// Package api 提供三方服务主动调用麦芽田开放平台的 API 接口封装
// 包含订单管理、授权、门店信息等核心业务接口
package api

import (
	"context"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	entity "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/auth"
	entityOrder "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
	entityShop "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/shop"
)

// IChannelSender 渠道发送接口
// 定义三方服务主动推送数据到麦芽田开放平台的所有接口
//
// 接口说明：
//
//	这些接口由三方服务商主动调用，向麦芽田开放平台推送订单数据、状态变更等信息
//
// 接口分类：
//   - 订单推送：新订单推送、订单修改推送、订单确认推送、订单完成推送、订单取消推送、催单推送
//   - 退款推送：退款申请推送、退款结果推送
//   - 配送推送：自配送状态变更推送
//   - 授权管理：获取访问令牌、刷新令牌
//   - 门店查询：查询门店信息
//
// 必接接口（标注【必接】）：
//   - OrderCreated：新订单推送（三方必须推送新订单给麦芽田）
//   - OrderConfirmed：订单确认推送（三方确认接单后推送给麦芽田）
//   - OrderDone：订单完成推送（三方订单完成后推送给麦芽田）
//   - AccessToken：获取访问令牌（授权流程必需）
type IChannelSender interface {
	// OrderCreated 新订单推送接口【必接】
	// 三方服务在用户下单并付款成功后，主动推送新订单给麦芽田
	//
	// 参数：
	//   ctx: 上下文，用于超时控制和取消操作
	//   token: 商户授权令牌
	//   data: 订单详细信息（包含商品、费用、顾客、配送等完整数据）
	//
	// 返回：
	//   *client.HTTPResponse: API 响应（code=200 表示成功）
	//   error: 请求失败或业务错误时返回
	//
	// 业务流程：
	//   用户在三方平台下单 → 三方创建订单 → 调用此接口推送给麦芽田 → 麦芽田接收并处理订单
	//
	// 接口路径：
	//   POST /v1/channel/order_created
	OrderCreated(ctx context.Context, token string, data *entityOrder.CreateOrderReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderModified 订单修改推送接口
	// 三方服务在用户修改订单后，主动推送订单变更信息给麦芽田
	//
	// 业务流程：
	//   用户在三方平台修改订单 → 三方更新订单 → 调用此接口推送给麦芽田 → 麦芽田更新订单信息
	//
	// 注意事项：
	//   - 仅订单确认前可调用
	//   - 修改内容仅推送修改部分的数据
	//
	// 接口路径：
	//   POST /v1/channel/order_modified
	OrderModified(ctx context.Context, token string, data *entityOrder.UpdateOrderReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderConfirmed 订单确认推送接口【必接】
	// 三方服务在商户确认订单后，主动推送确认状态给麦芽田
	//
	// 业务流程：
	//   三方商户确认接单 → 调用此接口推送给麦芽田 → 麦芽田更新订单状态为"已确认"
	//
	// 注意事项：
	//   - 订单确认后不可撤销
	//   - 确认后开始计算出餐时间
	//
	// 接口路径：
	//   POST /v1/channel/order_confirmed
	OrderConfirmed(ctx context.Context, token string, data *entityOrder.OrderConfirmedReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderRemind 订单催单推送接口
	// 三方服务在用户催单后，主动推送催单信息给麦芽田
	//
	// 业务流程：
	//   用户在三方平台催单 → 三方调用此接口推送给麦芽田 → 麦芽田记录催单
	//
	// 接口路径：
	//   POST /v1/channel/order_remind
	OrderRemind(ctx context.Context, token string, data *entityOrder.OrderRemindReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderApplyRefund 订单申请退款推送接口
	// 三方服务在顾客或商家发起退款申请后，主动推送退款申请给麦芽田
	//
	// 业务流程：
	//   用户/商家在三方平台申请退款 → 调用此接口推送给麦芽田 → 麦芽田处理退款流程
	//
	// 接口路径：
	//   POST /v1/channel/order_apply_refund
	OrderApplyRefund(ctx context.Context, token string, data *entityOrder.OrderApplyRefundReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderRefunded 订单退款结果推送接口
	// 三方服务在处理退款申请后，主动推送退款结果给麦芽田
	//
	// 业务流程：
	//   三方处理退款 → 调用此接口推送退款结果给麦芽田 → 麦芽田更新退款状态
	//
	// 接口路径：
	//   POST /v1/channel/order_refunded
	OrderRefunded(ctx context.Context, token string, data *entityOrder.OrderRefundedReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderCanceled 订单取消推送接口
	// 三方服务在订单被取消后，主动推送取消状态给麦芽田
	//
	// 业务流程：
	//   用户/商家取消订单 → 三方调用此接口推送给麦芽田 → 麦芽田更新订单状态
	//
	// 注意事项：
	//   - 订单取消后不可恢复
	//
	// 接口路径：
	//   POST /v1/channel/order_canceled
	OrderCanceled(ctx context.Context, token string, data *entityOrder.OrderCanceledReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// OrderDone 订单完成推送接口【必接】
	// 三方服务在订单配送完成后，主动推送完成状态给麦芽田
	//
	// 业务流程：
	//   订单配送完成 → 三方调用此接口推送给麦芽田 → 麦芽田更新订单状态并进入结算
	//
	// 接口路径：
	//   POST /v1/channel/order_done
	OrderDone(ctx context.Context, token string, data *entityOrder.OrderDoneReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// SelfDeliveryChange 自配送状态变更推送接口
	// 三方服务在使用自配送时，主动推送配送状态变更给麦芽田
	//
	// 业务流程：
	//   三方自配送状态变化（骑手接单、到店、配送中等）→ 调用此接口推送给麦芽田 → 麦芽田同步配送状态
	//
	// 注意事项：
	//   - 仅适用于三方自配送订单
	//   - 需要及时推送状态，让用户可以追踪订单
	//
	// 接口路径：
	//   POST /v1/channel/self_delivery_change
	SelfDeliveryChange(ctx context.Context, token string, data *entityOrder.SelfDeliveryChangeReq) (*client.ApiResponse[ApiResponseEmptyData], error)

	// ShopInfo 查询麦芽田门店信息接口
	// 三方服务主动查询门店在麦芽田平台的基本信息和营业状态
	//
	// 业务流程：
	//   三方需要门店信息 → 调用此接口查询麦芽田 → 麦芽田返回门店信息
	//
	// 接口路径：
	//   POST /v1/channel/shop_info
	ShopInfo(ctx context.Context, token string, data *entityShop.ShopInfoReq) (*client.ApiResponse[*entityShop.ShopInfoResp], error)

	// AccessToken 获取访问令牌接口【必接】
	// 三方服务使用授权码 code 向麦芽田换取访问令牌
	//
	// 业务流程：
	//   商户授权完成 → 三方获得 code → 调用此接口向麦芽田换取 token → 麦芽田返回访问令牌
	//
	// 注意事项：
	//   - 此接口的公共参数 token 传空字符串
	//   - 授权码 code 有效期 5 分钟，只能使用一次
	//
	// 接口路径：
	//   POST /v1/channel/access_token
	AccessToken(ctx context.Context, data *entity.AccessTokenReq) (*client.ApiResponse[*entity.AccessTokenResp], error)

	// RefreshToken 刷新访问令牌接口
	// 三方服务使用刷新令牌向麦芽田重新获取新的访问令牌
	//
	// 业务流程：
	//   token 即将过期 → 调用此接口向麦芽田刷新 → 麦芽田返回新的 token 和 refresh_token
	//
	// 注意事项：
	//   - 刷新得到新的 token 和 refresh_token
	//   - 旧的 token 随即在 5 分钟内失效
	//   - 需要在 refresh_token 时效内换取新的，避免授权频繁失效
	//
	// 接口路径：
	//   POST /v1/channel/refresh_token
	RefreshToken(ctx context.Context, token string, data *entity.RefreshTokenReq) (*client.ApiResponse[*entity.RefreshTokenResp], error)
}

// channelSender 渠道发送接口实现
// 封装 HTTP 客户端，提供三方服务主动调用麦芽田 API 的便捷方法
type channelSender struct {
	apiClient *client.HTTPClientManger // HTTP 客户端管理器
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

// NewChannelSender 创建渠道发送接口实例
//
// 参数：
//
//	config: HTTP 客户端配置，包含 API 地址、密钥、超时、重试等配置
//
// 返回：
//
//	IChannelSender: 渠道发送接口实例
//
// 使用示例：
//
//	config := client.NewConfigBuilder().
//	    BaseURL("https://open-api.maiyatian.com").
//	    APIKey("your_app_key").
//	    APISecret("your_app_secret").
//	    Build()
//	sender := api.NewChannelSender(config)
//
//	// 三方推送新订单给麦芽田
//	resp, err := sender.OrderCreated(ctx, token, orderData)
//
// 注意事项：
//   - 配置对象会与默认配置合并（用户配置优先）
//   - 如果配置验证失败会触发 panic
//   - 所有接口都是三方主动推送数据到麦芽田
func NewChannelSender(config *client.HTTPClientConfig) IChannelSender {
	// 获取默认配置
	defaultConfig := client.NewDefaultConfig()

	// 合并用户配置（用户配置优先）
	mergedConfig := mergeConfig(defaultConfig, config)

	// 创建 HTTP 客户端管理器
	apiClient, err := client.NewHTTPClientManager(mergedConfig)
	if err != nil {
		panic(err)
	}

	// 返回接口实例
	return &channelSender{
		apiClient: apiClient,
	}
}
