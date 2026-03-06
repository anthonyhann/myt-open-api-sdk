// Package examples 提供麦芽田配送开放平台 Go SDK 的使用示例
// 展示SDK的基础用法，包括客户端创建、授权流程、配送状态同步等核心功能
package internal

import (
	"context"
	"fmt"
	"log"
	"time"

	"github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/client"
	"github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/api"
	entityAuth "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/auth"
	entityDelivery "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/delivery"
	entityExpress "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/express"
)

// BasicUsageExample 基础使用示例
// 演示配送SDK的基础配置和客户端创建
func BasicUsageExample() {
	fmt.Println("=== 麦芽田配送开放平台 Go SDK 基础使用示例 ===")

	// 1. 创建客户端配置
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com"). // 测试环境地址
		APIKey("your_app_key"). // 替换为你的应用密钥
		APISecret("your_app_secret"). // 替换为你的应用密钥
		RequestTimeout(30 * time.Second). // 请求超时 30 秒
		RetryMaxAttempts(3). // 最大重试 3 次
		EnableLogging(true). // 启用日志记录
		Build()

	// 2. 创建配送 Sender 客户端（用于主动调用麦芽田 API）
	sender := api.NewDeliverySender(config)

	log.Printf("✅ 配送SDK客户端创建成功！")
	log.Printf("📍 API 地址: %s", config.BaseURL)
	log.Printf("🔑 应用密钥: %s", config.APIKey)
	log.Printf("🚚 专注功能: 配送状态同步、快递轨迹回传")

	// 注意：实际使用时需要替换配置中的 app_key 和 app_secret
	_ = sender // 避免未使用变量的编译错误
}

// AuthorizationExample 授权流程示例
// 演示配送服务商的授权流程：引导授权 → 获取令牌 → 刷新令牌
func AuthorizationExample() {
	fmt.Println("\n=== 配送服务商授权流程示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	// 步骤 1：组装授权页面 URL（配送服务商授权）
	authURL := fmt.Sprintf(
		"https://saas.open.test.maiyatian.com/oauth/?app_key=%s&redirect_uri=%s&view=web&response_type=code&shop_id=%s&state=%s",
		"your_app_key",                     // 替换为真实的 app_key
		"https://your-domain.com/callback", // 替换为你的回调地址
		"delivery_service_001",             // 配送服务商ID
		"delivery_auth_state",              // 自定义状态值
	)

	log.Printf("📋 配送服务商授权页面地址：")
	log.Printf("🔗 %s", authURL)
	log.Printf("💡 请引导配送服务商访问上述地址完成授权")

	// 步骤 2：获取访问令牌（模拟从回调获取到 code）
	ctx := context.Background()
	accessTokenReq := &entityAuth.AccessTokenReq{
		GrantType: "delivery_service",             // 配送服务商授权类型
		Code:      "mock_auth_code_from_callback", // 从回调地址获取的授权码
		StoreId:   "delivery_service_001",         // 配送服务商ID
		Mobile:    "13000020201",                  // 业务分类
		City:      "北京市",                       // 配送服务商名称
		CityCode:  "110100",                       // 配送类型：express-快递，local-同城
	}

	log.Printf("🔄 正在获取配送服务商访问令牌...")
	resp, err := sender.AccessToken(ctx, accessTokenReq)
	if err != nil {
		log.Printf("❌ 获取访问令牌失败: %v", err)
		return
	}

	if resp.Code == 200 && resp.Data != nil {
		log.Printf("✅ 获取访问令牌成功！")
		log.Printf("📄 令牌信息: Token=%s", (*resp.Data).Token[:20]+"...")
		log.Printf("⏰ 过期时间: %s", time.Unix((*resp.Data).ExpireTime, 0).Format("2006-01-02 15:04:05"))

		// 保存令牌信息（实际应用中需要持久化存储）
		currentToken := (*resp.Data).Token
		currentRefreshToken := (*resp.Data).RefreshToken

		// 步骤 3：刷新访问令牌（演示）
		time.Sleep(2 * time.Second) // 模拟时间流逝

		refreshReq := &entityAuth.RefreshTokenReq{
			Token:        currentToken,
			RefreshToken: currentRefreshToken,
		}

		log.Printf("🔄 正在刷新访问令牌...")
		refreshResp, err := sender.RefreshToken(ctx, currentToken, refreshReq)
		if err != nil {
			log.Printf("❌ 刷新令牌失败: %v", err)
			return
		}

		if refreshResp.Code == 200 && refreshResp.Data != nil {
			log.Printf("✅ 刷新令牌成功！")
			log.Printf("📄 新令牌: Token=%s", (*refreshResp.Data).Token[:20]+"...")
		}
	} else {
		log.Printf("❌ 获取访问令牌失败: [%d] %s", resp.Code, resp.Message)
	}
}

// DeliveryStatusExample 配送状态同步示例
// 演示订单配送全流程的状态同步：接单 → 取货 → 配送中 → 完成
func DeliveryStatusExample() {
	fmt.Println("\n=== 配送状态同步示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token" // 从授权流程获取的访问令牌

	// 模拟配送单信息
	orderNo := "delivery_" + fmt.Sprintf("%d", time.Now().Unix())
	sourceOrderNo := "myt_order_" + fmt.Sprintf("%d", time.Now().Unix())
	shopId := "shop_123"

	// 配送状态变更序列
	deliverySteps := []struct {
		status     string
		riderName  string
		riderPhone string
		longitude  string
		latitude   string
		message    string
	}{
		{"GRABBED", "张三", "138****0001", "116.397428", "39.90923", "骑手已接单，准备前往商家取货"},
		{"PICKUP", "张三", "138****0001", "116.397428", "39.90923", "骑手已到达商家，准备取货"},
		{"DELIVERING", "张三", "138****0001", "116.407428", "39.91923", "骑手已取货，正在配送中"},
		{"DONE", "张三", "138****0001", "116.417428", "39.92923", "订单配送完成，已送达客户"},
	}

	log.Printf("🚚 开始配送状态同步流程...")
	log.Printf("📦 配送单号: %s", orderNo)
	log.Printf("🔗 关联订单: %s", sourceOrderNo)

	for i, step := range deliverySteps {
		log.Printf("📍 第 %d 步: %s", i+1, step.message)

		deliveryReq := &entityDelivery.DeliveryChangeReq{
			OrderNo:       orderNo,
			SourceOrderNo: sourceOrderNo,
			ShopId:        shopId,
			Status:        step.status,
			Tag:           "express_delivery",
			RiderName:     step.riderName,
			RiderPhone:    step.riderPhone,
			Longitude:     step.longitude,
			Latitude:      step.latitude,
			UpdateTime:    time.Now().Unix(),
		}

		// 特殊状态的额外信息
		if step.status == "GRABBED" {
			deliveryReq.UpdateTime = time.Now().Add(30 * time.Minute).Unix() // 预计30分钟送达
		}

		resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
		if err != nil {
			log.Printf("❌ 推送配送状态失败: %v", err)
			continue
		}

		if resp.Code == 200 {
			log.Printf("✅ 状态同步成功: %s", step.status)
			if step.riderName != "" {
				log.Printf("👤 配送员: %s (%s)", step.riderName, step.riderPhone)
				log.Printf("📍 当前位置: %s, %s", deliveryReq.Longitude, deliveryReq.Latitude)
			}
		} else {
			log.Printf("❌ 状态同步失败: [%d] %s", resp.Code, resp.Message)
		}

		// 模拟配送时间间隔
		time.Sleep(3 * time.Second)
	}

	log.Printf("🎉 配送流程完成！订单已成功送达客户")
}

// ExpressTrackingExample 快递轨迹回传示例
// 演示快递订单的轨迹信息推送
func ExpressTrackingExample() {
	fmt.Println("\n=== 快递轨迹回传示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 模拟快递单信息
	orderNo := "express_" + fmt.Sprintf("%d", time.Now().Unix())
	sourceOrderNo := "myt_express_" + fmt.Sprintf("%d", time.Now().Unix())

	log.Printf("📦 开始快递轨迹回传...")
	log.Printf("📮 快递单号: %s", orderNo)
	log.Printf("🔗 关联订单: %s", sourceOrderNo)

	// 构建快递轨迹数据
	locationReq := &entityExpress.LocationChangeReq{
		OrderNo:       orderNo,
		SourceOrderNo: sourceOrderNo,
		ShopId:        "shop_123",
		Tag:           "sf_express",
		Locations: []entityExpress.Location{
			{
				Description: "[北京市朝阳区]快件已从【北京朝阳分拣中心】发出，下一站【北京海淀分拣中心】",
				City:        "北京市",
				Longitude:   "116.397428",
				Latitude:    "39.90923",
				Status:      "PICKUP",
				UpdateTime:  time.Now().Add(-2 * time.Hour).Unix(),
			},
			{
				Description: "[北京市海淀区]快件已到达【北京海淀分拣中心】",
				City:        "北京市",
				Longitude:   "116.307428",
				Latitude:    "39.98923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Add(-1 * time.Hour).Unix(),
			},
			{
				Description: "[北京市海淀区]快件已从【北京海淀分拣中心】发出，下一站【中关村营业点】",
				City:        "北京市",
				Longitude:   "116.317428",
				Latitude:    "39.99923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Add(-30 * time.Minute).Unix(),
			},
			{
				Description: "[北京市海淀区]快件已到达【中关村营业点】，正在安排派送",
				City:        "北京市",
				Longitude:   "116.327428",
				Latitude:    "40.00923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Add(-10 * time.Minute).Unix(),
			},
			{
				Description: "[北京市海淀区]【张派送员 13800138000】正在为您派送",
				City:        "北京市",
				Longitude:   "116.337428",
				Latitude:    "40.01923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Unix(),
			},
		},
	}

	log.Printf("🔄 正在推送快递轨迹信息...")
	log.Printf("📋 轨迹节点数量: %d", len(locationReq.Locations))

	resp, err := sender.LocationChange(ctx, token, locationReq)
	if err != nil {
		log.Printf("❌ 推送快递轨迹失败: %v", err)
		return
	}

	if resp.Code == 200 {
		log.Printf("✅ 快递轨迹推送成功！")
		log.Printf("📈 轨迹详情:")
		for i, loc := range locationReq.Locations {
			log.Printf("  %d. [%s] %s", i+1,
				time.Unix(loc.UpdateTime, 0).Format("01-02 15:04"),
				loc.Description)
		}
		log.Printf("📍 当前位置: %s", locationReq.Locations[len(locationReq.Locations)-1].City)
	} else {
		log.Printf("❌ 快递轨迹推送失败: [%d] %s", resp.Code, resp.Message)
	}
}

// DeliveryCancellationExample 配送取消示例
// 演示各种配送取消场景的状态推送
func DeliveryCancellationExample() {
	fmt.Println("\n=== 配送取消示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 演示不同取消场景
	cancelScenarios := []struct {
		orderNo      string
		cancelType   string
		cancelReason string
		desc         string
	}{
		{"delivery_001", "RIDER_CANCEL", "骑手临时有事，无法继续配送", "骑手主动取消"},
		{"delivery_002", "CANCEL", "商家缺货，无法出餐", "商家取消配送"},
		{"delivery_003", "CANCEL", "客户要求取消订单", "客户取消配送"},
		{"delivery_004", "EXPECT", "配送地址无法找到", "配送异常"},
	}

	for i, scenario := range cancelScenarios {
		log.Printf("🚫 场景 %d: %s", i+1, scenario.desc)

		deliveryReq := &entityDelivery.DeliveryChangeReq{
			OrderNo:       scenario.orderNo,
			SourceOrderNo: "myt_order_" + fmt.Sprintf("%d", i+1000),
			ShopId:        "shop_123",
			Status:        scenario.cancelType,
			Tag:           "express_delivery",
			RiderName:     "张三",
			RiderPhone:    "138****0001",
			CancelReason:  scenario.cancelReason,
			Longitude:     "116.397428",
			Latitude:      "39.90923",
			UpdateTime:    time.Now().Unix(),
		}

		resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
		if err != nil {
			log.Printf("❌ 推送取消状态失败: %v", err)
			continue
		}

		if resp.Code == 200 {
			log.Printf("✅ 取消状态推送成功: %s", scenario.orderNo)
			log.Printf("📝 取消原因: %s", scenario.cancelReason)
		} else {
			log.Printf("❌ 推送取消状态失败: [%d] %s", resp.Code, resp.Message)
		}

		time.Sleep(1 * time.Second)
	}
}

// ErrorHandlingExample 错误处理示例
// 演示如何正确处理配送SDK中的各种错误情况
func ErrorHandlingExample() {
	fmt.Println("\n=== 配送SDK错误处理示例 ===")

	// 故意使用错误的配置来演示错误处理
	config := client.NewConfigBuilder().
		BaseURL("https://invalid-api.example.com"). // 无效的API地址
		APIKey("invalid_app_key").
		APISecret("invalid_app_secret").
		RequestTimeout(5 * time.Second). // 较短的超时时间
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	invalidToken := "invalid_token"

	// 尝试推送配送状态（预期失败）
	deliveryReq := &entityDelivery.DeliveryChangeReq{
		OrderNo:       "invalid_order",
		SourceOrderNo: "invalid_source",
		ShopId:        "invalid_shop",
		Status:        "INVALID_STATUS",
	}

	log.Printf("🧪 正在测试错误处理...")
	resp, err := sender.DeliveryChange(ctx, invalidToken, deliveryReq)
	// 错误处理的最佳实践
	if err != nil {
		log.Printf("❌ 网络或系统错误: %v", err)
		log.Printf("💡 建议：检查网络连接、API地址和配置信息")
		return
	}

	// 检查业务状态码
	if resp.Code != 200 {
		log.Printf("❌ 业务错误 [%d]: %s", resp.Code, resp.Message)

		// 根据不同错误码进行相应处理
		switch resp.Code {
		case 401:
			log.Printf("💡 建议：检查 token 是否有效，可能需要重新授权或刷新令牌")
		case 403:
			log.Printf("💡 建议：检查 app_key 和 app_secret 是否正确")
		case 404:
			log.Printf("💡 建议：检查请求的资源是否存在（如订单号、商户ID）")
		case 422:
			log.Printf("💡 建议：检查请求参数格式，如配送状态、时间格式等")
		case 429:
			log.Printf("💡 建议：请求过于频繁，请稍后重试")
		case 500, 502, 503:
			log.Printf("💡 建议：服务器暂时异常，SDK会自动重试")
		default:
			log.Printf("💡 建议：查看详细错误信息，联系技术支持")
		}
		return
	}

	log.Printf("✅ 请求成功！")
}

// RunAllExamples 运行所有基础示例
// 统一运行所有示例代码，便于快速测试
func RunAllExamples() {
	log.Printf("🚀 开始运行麦芽田配送开放平台 Go SDK 示例...")
	log.Printf("⏰ 当前时间: %s\n", time.Now().Format("2006-01-02 15:04:05"))

	// 运行基础使用示例
	BasicUsageExample()

	// 运行授权流程示例
	AuthorizationExample()

	// 运行配送状态同步示例
	DeliveryStatusExample()

	// 运行快递轨迹回传示例
	ExpressTrackingExample()

	// 运行配送取消示例
	DeliveryCancellationExample()

	// 运行错误处理示例
	ErrorHandlingExample()

	log.Printf("\n🎉 所有基础示例运行完成！")
	log.Printf("📚 更多文档请参考: README.md 和 ARCHITECTURE.md")
}
