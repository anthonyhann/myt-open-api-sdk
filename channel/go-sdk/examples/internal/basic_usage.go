// Package examples 提供麦芽田开放平台 Go SDK 的使用示例
// 展示SDK的基础用法，包括客户端创建、授权流程、订单推送等核心功能
package internal

import (
	"context"
	"fmt"
	"log"
	"time"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/api"
	entityAuth "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/auth"
	entityOrder "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
	entityShop "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/shop"
)

// BasicUsageExample 基础使用示例
// 演示 SDK 的基础配置和客户端创建
func BasicUsageExample() {
	fmt.Println("=== 麦芽田开放平台 Go SDK 基础使用示例 ===")

	// 1. 创建客户端配置
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com"). // 测试环境地址
		APIKey("your_app_key"). // 替换为你的应用密钥
		APISecret("your_app_secret"). // 替换为你的应用密钥
		RequestTimeout(30 * time.Second). // 请求超时 30 秒
		RetryMaxAttempts(3). // 最大重试 3 次
		EnableLogging(true). // 启用日志记录
		Build()

	// 2. 创建 Sender 客户端（用于主动调用麦芽田 API）
	sender := api.NewChannelSender(config)

	log.Printf("✅ SDK 客户端创建成功！")
	log.Printf("📍 API 地址: %s", config.BaseURL)
	log.Printf("🔑 应用密钥: %s", config.APIKey)

	// 注意：实际使用时需要替换配置中的 app_key 和 app_secret
	_ = sender // 避免未使用变量的编译错误
}

// AuthorizationExample 授权流程示例
// 演示完整的授权流程：引导授权 → 获取令牌 → 刷新令牌
func AuthorizationExample() {
	fmt.Println("\n=== 授权流程示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	// 步骤 1：组装授权页面 URL
	authURL := fmt.Sprintf(
		"https://saas.open.test.maiyatian.com/oauth/?app_key=%s&redirect_uri=%s&view=web&response_type=code&shop_id=%s&state=%s",
		"your_app_key",                     // 替换为真实的 app_key
		"https://your-domain.com/callback", // 替换为你的回调地址
		"shop_123",                         // 门店ID
		"custom_state_123",                 // 自定义状态值
	)

	log.Printf("📋 授权页面地址：")
	log.Printf("🔗 %s", authURL)
	log.Printf("💡 请引导商户访问上述地址完成授权")

	// 步骤 2：获取访问令牌（模拟从回调获取到 code）
	ctx := context.Background()
	accessTokenReq := &entityAuth.AccessTokenReq{
		GrantType: "shop",
		Code:      "mock_auth_code_from_callback", // 从回调地址获取的授权码
		ShopId:    "shop_123",
		Category:  "canyin",
		Name:      "测试门店",
		Type:      "waimai",
		Longitude: "116.397428",
		Latitude:  "39.90923",
	}

	log.Printf("🔄 正在获取访问令牌...")
	resp, err := sender.AccessToken(ctx, accessTokenReq)
	if err != nil {
		log.Printf("❌ 获取访问令牌失败: %v", err)
		return
	}

	if resp.Code == 200 && resp.Data != nil {
		log.Printf("✅ 获取访问令牌成功！")
		log.Printf("📄 令牌信息: Token=%s", (*resp.Data).Token[:20]+"...")
		log.Printf("⏰ 过期时间: %s", time.Unix((*resp.Data).ExpiresIn, 0).Format("2006-01-02 15:04:05"))

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

// OrderManagementExample 订单管理示例
// 演示订单的完整生命周期：新订单推送 → 确认订单 → 订单完成
func OrderManagementExample() {
	fmt.Println("\n=== 订单管理示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token" // 从授权流程获取的访问令牌

	// 步骤 1：推送新订单
	log.Printf("📦 正在推送新订单...")

	createOrderReq := &entityOrder.CreateOrderReq{
		OrderBaseData: entityOrder.OrderBaseData{
			Order: entityOrder.OrderInfo{
				OrderId:      "order_" + fmt.Sprintf("%d", time.Now().Unix()),
				OrderSn:      123456,
				ShopId:       "shop_123",
				ShopName:     "测试门店",
				OriginTag:    "youzan2",
				Category:     "canyin",
				IsPreOrder:   false,
				TotalPrice:   5000, // 50.00 元（单位：分）
				BalancePrice: 4500, // 实付 45.00 元
				CreateTime:   time.Now().Unix(),
				DeliveryTime: time.Now().Add(1 * time.Hour).Unix(),
				DeliveryEnd:  time.Now().Add(2 * time.Hour).Unix(),
				DeliveryType: 1, // 1-同城配送
				IsPicker:     false,
				UserRemark:   "少放辣椒，多加香菜",
				OrderFee: entityOrder.OrderFeeInfo{
					TotalFee:    5000,
					SendFee:     500,  // 配送费 5.00 元
					PackageFee:  100,  // 餐盒费 1.00 元
					DiscountFee: 500,  // 优惠金额 5.00 元
					ShopFee:     4400, // 商户应收 44.00 元
					UserFee:     4500, // 用户实付 45.00 元
					PayType:     2,    // 2-在线支付
				},
				OrderGoods: []entityOrder.OrderGoodsInfo{
					{
						GoodsId:       "goods_001",
						GoodsName:     "宫保鸡丁",
						Thumb:         "https://example.com/images/gongbao.jpg",
						SkuId:         "sku_001",
						SkuName:       "中份",
						Unit:          "份",
						Weight:        300,
						Number:        1,
						GoodsPrice:    2000,
						GoodsTotalFee: 2000,
						ReduceFee:     1900, // 折扣后 19.00 元
						DiscountFee:   100,  // 优惠 1.00 元
						TotalFee:      1900,
					},
					{
						GoodsId:       "goods_002",
						GoodsName:     "米饭",
						Thumb:         "https://example.com/images/rice.jpg",
						SkuId:         "sku_002",
						SkuName:       "中碗",
						Unit:          "份",
						Weight:        200,
						Number:        2,
						GoodsPrice:    300,
						GoodsTotalFee: 600,
						ReduceFee:     600,
						DiscountFee:   0,
						TotalFee:      600,
					},
				},
			},
			OrderCustomer: entityOrder.OrderCustomerInfo{
				RealName:  "张*生",
				Phone:     "138****8000",
				Address:   "朝阳区某某街道某某号楼某某单元某某室",
				Longitude: "116.397428",
				Latitude:  "39.90923",
			},
			UpdateTime: time.Now().Unix(),
		},
	}

	orderResp, err := sender.OrderCreated(ctx, token, createOrderReq)
	if err != nil {
		log.Printf("❌ 推送订单失败: %v", err)
		return
	}

	if orderResp.Code == 200 {
		log.Printf("✅ 订单推送成功！")
		log.Printf("📦 订单ID: %s", createOrderReq.Order.OrderId)
		log.Printf("💰 订单金额: %.2f 元", float64(createOrderReq.Order.BalancePrice)/100)

		// 步骤 2：推送订单确认（模拟商户确认接单）
		time.Sleep(3 * time.Second)
		log.Printf("✋ 商户正在确认接单...")

		confirmReq := &entityOrder.OrderConfirmedReq{
			OrderId:    createOrderReq.Order.OrderId,
			ShopId:     createOrderReq.Order.ShopId,
			UpdateTime: time.Now().Unix(),
		}

		confirmResp, err := sender.OrderConfirmed(ctx, token, confirmReq)
		if err != nil {
			log.Printf("❌ 推送确认状态失败: %v", err)
			return
		}

		if confirmResp.Code == 200 {
			log.Printf("✅ 订单确认成功！商户已接单")

			// 步骤 3：推送订单完成（模拟配送完成）
			time.Sleep(5 * time.Second)
			log.Printf("🚚 订单配送完成...")

			doneReq := &entityOrder.OrderDoneReq{
				OrderId:    createOrderReq.Order.OrderId,
				ShopId:     createOrderReq.Order.ShopId,
				UpdateTime: time.Now().Unix(),
			}

			doneResp, err := sender.OrderDone(ctx, token, doneReq)
			if err != nil {
				log.Printf("❌ 推送完成状态失败: %v", err)
				return
			}

			if doneResp.Code == 200 {
				log.Printf("✅ 订单完成！生命周期结束")
				log.Printf("🎉 订单从创建到完成耗时: %s", "约8秒（模拟）")
			}
		}
	} else {
		log.Printf("❌ 推送订单失败: [%d] %s", orderResp.Code, orderResp.Message)
	}
}

// ShopManagementExample 门店管理示例
// 演示门店信息查询功能
func ShopManagementExample() {
	fmt.Println("\n=== 门店管理示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 查询门店信息
	shopReq := &entityShop.ShopInfoReq{
		ShopId: "shop_123",
	}

	log.Printf("🏪 正在查询门店信息...")
	resp, err := sender.ShopInfo(ctx, token, shopReq)
	if err != nil {
		log.Printf("❌ 查询门店信息失败: %v", err)
		return
	}

	if resp.Code == 200 && resp.Data != nil {
		log.Printf("✅ 查询门店信息成功！")
		log.Printf("🏪 门店名称: %s", (*resp.Data).ShopName)
		log.Printf("📱 联系电话: %s", (*resp.Data).Phone)
		log.Printf("⭐ 门店状态: %d", (*resp.Data).Status)
	} else {
		log.Printf("❌ 查询门店信息失败: [%d] %s", resp.Code, resp.Message)
	}
}

// ErrorHandlingExample 错误处理示例
// 演示如何正确处理各种错误情况
func ErrorHandlingExample() {
	fmt.Println("\n=== 错误处理示例 ===")

	// 故意使用错误的配置来演示错误处理
	config := client.NewConfigBuilder().
		BaseURL("https://invalid-api.example.com"). // 无效的API地址
		APIKey("invalid_app_key").
		APISecret("invalid_app_secret").
		RequestTimeout(5 * time.Second). // 较短的超时时间
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	invalidToken := "invalid_token"

	// 尝试查询门店信息（预期失败）
	shopReq := &entityShop.ShopInfoReq{
		ShopId: "invalid_shop_id",
	}

	log.Printf("🧪 正在测试错误处理...")
	resp, err := sender.ShopInfo(ctx, invalidToken, shopReq)
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
			log.Printf("💡 建议：检查请求的资源是否存在（如门店ID）")
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

// RunAllExamples 运行所有示例
// 统一运行所有示例代码，便于快速测试
func RunAllExamples() {
	log.Printf("🚀 开始运行麦芽田开放平台 Go SDK 示例...")
	log.Printf("⏰ 当前时间: %s\n", time.Now().Format("2006-01-02 15:04:05"))

	// 运行基础使用示例
	BasicUsageExample()

	// 运行授权流程示例
	AuthorizationExample()

	// 运行订单管理示例
	OrderManagementExample()

	// 运行门店管理示例
	ShopManagementExample()

	// 运行错误处理示例
	ErrorHandlingExample()

	log.Printf("\n🎉 所有示例运行完成！")
	log.Printf("📚 更多文档请参考: README.md 和 ARCHITECTURE.md")
}
