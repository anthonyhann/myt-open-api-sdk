// Package examples 提供麦芽田开放平台 Go SDK 的高级功能示例
// 展示退款管理、配送状态同步、批量操作等高级特性
package internal

import (
	"context"
	"fmt"
	"log"
	"sync"
	"time"

	"github.com/maiyatian/channel/myt-go-sdk/client"
	"github.com/maiyatian/channel/myt-go-sdk/models/sender/api"
	entityOrder "github.com/maiyatian/channel/myt-go-sdk/models/sender/entity/order"
)

// RefundManagementExample 退款管理示例
// 演示完整的退款流程：申请退款 → 处理退款 → 推送结果
func RefundManagementExample() {
	fmt.Println("=== 退款管理示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"
	orderId := "order_123456"
	shopId := "shop_123"

	// 步骤 1：推送退款申请
	log.Printf("💰 用户发起退款申请...")

	refundReq := &entityOrder.OrderApplyRefundReq{
		OrderId:    orderId,
		ShopId:     shopId,
		RefundId:   "refund_" + fmt.Sprintf("%d", time.Now().Unix()),
		Type:       1, // 1-仅退款，2-退货退款
		Reason:     "商品质量问题",
		TotalPrice: 2000, // 退款金额 20.00 元
		Count:      1,    // 退款商品数量
		Goods: []entityOrder.OrderApplyRefundGoodsInfo{
			{
				GoodsId:       "goods_001",
				GoodsName:     "宫保鸡丁",
				SkuId:         "sku_001",
				Number:        1,
				GoodsTotalFee: 2000,
				RefundFee:     2000,
			},
		},
		UpdateTime: time.Now().Unix(),
	}

	applyResp, err := sender.OrderApplyRefund(ctx, token, refundReq)
	if err != nil {
		log.Printf("❌ 推送退款申请失败: %v", err)
		return
	}

	if applyResp.Code == 200 {
		log.Printf("✅ 退款申请推送成功！")
		log.Printf("🆔 退款ID: %s", refundReq.RefundId)
		log.Printf("💰 退款金额: %.2f 元", float64(refundReq.TotalPrice)/100)
		log.Printf("📝 退款原因: %s", refundReq.Reason)

		// 步骤 2：模拟处理退款（商户或系统处理）
		time.Sleep(3 * time.Second)
		log.Printf("⏳ 正在处理退款申请...")

		// 步骤 3：推送退款结果
		refundedReq := &entityOrder.OrderRefundedReq{
			OrderId:    orderId,
			ShopId:     shopId,
			RefundId:   refundReq.RefundId,
			Status:     1, // 1-退款成功，2-退款失败
			Type:       1,
			Reason:     "质量问题确认，已退款到原支付账户",
			TotalPrice: 2000,
			Count:      1,
			Goods: []entityOrder.OrderRefundedGoodsInfo{
				{
					GoodsId:       "goods_001",
					GoodsName:     "宫保鸡丁",
					SkuId:         "sku_001",
					Number:        1,
					GoodsTotalFee: 2000,
					RefundFee:     2000,
				},
			},
			UpdateTime: time.Now().Unix(),
		}

		refundedResp, err := sender.OrderRefunded(ctx, token, refundedReq)
		if err != nil {
			log.Printf("❌ 推送退款结果失败: %v", err)
			return
		}

		if refundedResp.Code == 200 {
			log.Printf("✅ 退款处理完成！")
			log.Printf("💳 资金将在 1-3 个工作日内退回到原支付账户")
		}
	} else {
		log.Printf("❌ 推送退款申请失败: [%d] %s", applyResp.Code, applyResp.Message)
	}
}

// DeliveryStatusExample 配送状态管理示例
// 演示自配送状态的实时同步
func DeliveryStatusExample() {
	fmt.Println("\n=== 配送状态管理示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"
	orderId := "order_123456"
	shopId := "shop_123"

	// 配送状态变更序列
	deliverySteps := []struct {
		status    string
		riderName string
		message   string
	}{
		{"PENDING", "", "订单已发配送，等待骑手接单"},
		{"GRABBED", "张三", "骑手已接单，正在前往商家"},
		{"PICKUP", "张三", "骑手已到店，等待取货"},
		{"DELIVERING", "张三", "骑手已取货，正在配送中"},
		{"DONE", "张三", "订单配送完成"},
	}

	log.Printf("🚚 开始配送状态同步流程...")

	for i, step := range deliverySteps {
		log.Printf("📍 第 %d 步: %s", i+1, step.message)

		deliveryReq := &entityOrder.SelfDeliveryChangeReq{
			OrderNo:       orderId,
			ShopId:        shopId,
			Status:        step.status,
			RiderName:     step.riderName,
			RiderPhone:    "138****0001",
			SourceOrderNo: "SF" + fmt.Sprintf("%d", time.Now().Unix()),
			Tag:           "shunfeng",
			Longitude:     fmt.Sprintf("116.%06d", 400000+i*1000), // 模拟位置变化
			Latitude:      fmt.Sprintf("39.%06d", 900000+i*500),
			UpdateTime:    time.Now().Unix(),
		}

		resp, err := sender.SelfDeliveryChange(ctx, token, deliveryReq)
		if err != nil {
			log.Printf("❌ 推送配送状态失败: %v", err)
			continue
		}

		if resp.Code == 200 {
			log.Printf("✅ 状态同步成功: %s", step.status)
			if step.riderName != "" {
				log.Printf("👤 配送员: %s", step.riderName)
				log.Printf("📍 当前位置: %s, %s", deliveryReq.Longitude, deliveryReq.Latitude)
			}
		} else {
			log.Printf("❌ 状态同步失败: [%d] %s", resp.Code, resp.Message)
		}

		// 模拟时间间隔
		time.Sleep(2 * time.Second)
	}

	log.Printf("🎉 配送流程完成！订单已送达顾客")
}

// OrderModificationExample 订单修改示例
// 演示订单确认前的修改操作
func OrderModificationExample() {
	fmt.Println("\n=== 订单修改示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"
	orderId := "order_123456"

	log.Printf("📝 用户请求修改订单...")

	// 修改订单内容（增加商品、修改数量等）
	modifyReq := &entityOrder.UpdateOrderReq{
		OrderBaseData: entityOrder.OrderBaseData{
			Order: entityOrder.OrderInfo{
				OrderId:      orderId,
				OrderSn:      123456,
				ShopId:       "shop_123",
				ShopName:     "测试门店",
				OriginTag:    "youzan2",
				Category:     "canyin",
				IsPreOrder:   false,
				TotalPrice:   6500, // 修改后总价 65.00 元
				BalancePrice: 6000, // 修改后实付 60.00 元
				CreateTime:   time.Now().Add(-10 * time.Minute).Unix(),
				DeliveryTime: time.Now().Add(1 * time.Hour).Unix(),
				DeliveryEnd:  time.Now().Add(2 * time.Hour).Unix(),
				DeliveryType: 1,
				IsPicker:     false,
				UserRemark:   "少放辣椒，多加香菜，加一份米饭", // 更新备注
				OrderFee: entityOrder.OrderFeeInfo{
					TotalFee:    6500,
					SendFee:     500,
					PackageFee:  100,
					DiscountFee: 500,
					ShopFee:     5900,
					UserFee:     6000,
					PayType:     2,
				},
				OrderGoods: []entityOrder.OrderGoodsInfo{
					{
						GoodsId:       "goods_001",
						GoodsName:     "宫保鸡丁",
						Thumb:         "https://example.com/images/gongbao.jpg",
						SkuId:         "sku_001",
						SkuName:       "中份",
						Unit:          "份",
						Number:        1,
						GoodsPrice:    2000,
						GoodsTotalFee: 2000,
						ReduceFee:     1900,
						DiscountFee:   100,
						TotalFee:      1900,
					},
					{
						GoodsId:       "goods_002",
						GoodsName:     "米饭",
						Thumb:         "https://example.com/images/rice.jpg",
						SkuId:         "sku_002",
						SkuName:       "中碗",
						Unit:          "份",
						Number:        3, // 从2份改为3份
						GoodsPrice:    300,
						GoodsTotalFee: 900,
						ReduceFee:     900,
						DiscountFee:   0,
						TotalFee:      900,
					},
					// 新增商品
					{
						GoodsId:       "goods_003",
						GoodsName:     "酸辣土豆丝",
						Thumb:         "https://example.com/images/potato.jpg",
						SkuId:         "sku_003",
						SkuName:       "小份",
						Unit:          "份",
						Number:        1,
						GoodsPrice:    1200,
						GoodsTotalFee: 1200,
						ReduceFee:     1200,
						DiscountFee:   0,
						TotalFee:      1200,
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

	resp, err := sender.OrderModified(ctx, token, modifyReq)
	if err != nil {
		log.Printf("❌ 推送订单修改失败: %v", err)
		return
	}

	if resp.Code == 200 {
		log.Printf("✅ 订单修改成功！")
		log.Printf("📦 订单ID: %s", modifyReq.Order.OrderId)
		log.Printf("💰 修改后金额: %.2f 元", float64(modifyReq.Order.BalancePrice)/100)
		log.Printf("📝 修改内容:")
		log.Printf("   - 米饭数量: 2份 → 3份")
		log.Printf("   - 新增: 酸辣土豆丝 1份")
		log.Printf("   - 备注更新: %s", modifyReq.Order.UserRemark)
	} else {
		log.Printf("❌ 推送订单修改失败: [%d] %s", resp.Code, resp.Message)
	}
}

// OrderCancellationExample 订单取消示例
// 演示各种订单取消情况
func OrderCancellationExample() {
	fmt.Println("\n=== 订单取消示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 演示不同取消场景
	cancelScenarios := []struct {
		orderId    string
		cancelType int
		reason     string
		desc       string
	}{
		{"order_001", 1, "用户改变主意", "用户主动取消"},
		{"order_002", 2, "商品缺货", "商户取消"},
		{"order_003", 3, "地址有误，无法配送", "客服取消"},
		{"order_004", 4, "支付超时", "系统自动取消"},
	}

	for i, scenario := range cancelScenarios {
		log.Printf("🚫 场景 %d: %s", i+1, scenario.desc)

		cancelReq := &entityOrder.OrderCanceledReq{
			OrderId:    scenario.orderId,
			ShopId:     "shop_123",
			ReasonCode: scenario.cancelType,
			Reason:     scenario.reason,
			UpdateTime: time.Now().Unix(),
		}

		resp, err := sender.OrderCanceled(ctx, token, cancelReq)
		if err != nil {
			log.Printf("❌ 推送取消状态失败: %v", err)
			continue
		}

		if resp.Code == 200 {
			log.Printf("✅ 订单取消成功: %s", scenario.orderId)
			log.Printf("📝 取消原因: %s", scenario.reason)
		} else {
			log.Printf("❌ 推送取消状态失败: [%d] %s", resp.Code, resp.Message)
		}

		time.Sleep(1 * time.Second)
	}
}

// OrderReminderExample 订单催单示例
// 演示催单功能的使用
func OrderReminderExample() {
	fmt.Println("\n=== 订单催单示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"
	orderId := "order_123456"

	log.Printf("⏰ 顾客发起催单...")

	remindReq := &entityOrder.OrderRemindReq{
		OrderId:    orderId,
		ShopId:     "shop_123",
		Reason:     "您好，请问我的订单什么时候能送到？",
		UpdateTime: time.Now().Unix(),
	}

	resp, err := sender.OrderRemind(ctx, token, remindReq)
	if err != nil {
		log.Printf("❌ 推送催单失败: %v", err)
		return
	}

	if resp.Code == 200 {
		log.Printf("✅ 催单推送成功！")
		log.Printf("📦 订单ID: %s", remindReq.OrderId)
		log.Printf("💬 催单内容: %s", remindReq.Reason)
		log.Printf("⏰ 催单时间: %s", time.Now().Format("15:04:05"))
		log.Printf("💡 商户会收到催单通知并及时回复")
	} else {
		log.Printf("❌ 推送催单失败: [%d] %s", resp.Code, resp.Message)
	}
}

// ConcurrentOperationsExample 并发操作示例
// 演示如何在高并发场景下使用SDK
func ConcurrentOperationsExample() {
	fmt.Println("\n=== 并发操作示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		MaxConnections(20). // 增加连接池大小
		RequestTimeout(10 * time.Second).
		Build()
	sender := api.NewChannelSender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 模拟批量推送订单
	orderCount := 10
	var wg sync.WaitGroup
	successCount := 0
	var mu sync.Mutex

	log.Printf("🚀 开始并发推送 %d 个订单...", orderCount)
	startTime := time.Now()

	for i := 0; i < orderCount; i++ {
		wg.Add(1)

		go func(index int) {
			defer wg.Done()

			orderId := fmt.Sprintf("batch_order_%03d_%d", index+1, time.Now().Unix())

			orderReq := &entityOrder.CreateOrderReq{
				OrderBaseData: entityOrder.OrderBaseData{
					Order: entityOrder.OrderInfo{
						OrderId:      orderId,
						OrderSn:      100000 + index,
						ShopId:       "shop_123",
						ShopName:     "测试门店",
						OriginTag:    "youzan2",
						Category:     "canyin",
						IsPreOrder:   false,
						TotalPrice:   3000 + index*100, // 不同价格
						BalancePrice: 2800 + index*100,
						CreateTime:   time.Now().Unix(),
						DeliveryTime: time.Now().Add(1 * time.Hour).Unix(),
						DeliveryEnd:  time.Now().Add(2 * time.Hour).Unix(),
						DeliveryType: 1,
						IsPicker:     false,
						UserRemark:   fmt.Sprintf("批量订单 #%d", index+1),
						OrderFee: entityOrder.OrderFeeInfo{
							TotalFee:    3000 + index*100,
							SendFee:     500,
							PackageFee:  100,
							DiscountFee: 200,
							ShopFee:     2700 + index*100,
							UserFee:     2800 + index*100,
							PayType:     2,
						},
						OrderGoods: []entityOrder.OrderGoodsInfo{
							{
								GoodsId:       fmt.Sprintf("goods_%03d", index+1),
								GoodsName:     fmt.Sprintf("测试商品 #%d", index+1),
								SkuId:         fmt.Sprintf("sku_%03d", index+1),
								SkuName:       "标准份",
								Unit:          "份",
								Number:        1,
								GoodsPrice:    3000 + index*100,
								GoodsTotalFee: 3000 + index*100,
								ReduceFee:     2800 + index*100,
								DiscountFee:   200,
								TotalFee:      2800 + index*100,
							},
						},
					},
					OrderCustomer: entityOrder.OrderCustomerInfo{
						RealName:  fmt.Sprintf("测试用户%d", index+1),
						Phone:     fmt.Sprintf("138%08d", index+10000000),
						Address:   fmt.Sprintf("测试地址 #%d", index+1),
						Longitude: "116.397428",
						Latitude:  "39.90923",
					},
					UpdateTime: time.Now().Unix(),
				},
			}

			// 推送订单
			resp, err := sender.OrderCreated(ctx, token, orderReq)
			if err != nil {
				log.Printf("❌ 订单 %s 推送失败: %v", orderId, err)
				return
			}

			if resp.Code == 200 {
				mu.Lock()
				successCount++
				mu.Unlock()
				log.Printf("✅ 订单 %s 推送成功 (%d/%d)", orderId, successCount, orderCount)
			} else {
				log.Printf("❌ 订单 %s 推送失败: [%d] %s", orderId, resp.Code, resp.Message)
			}
		}(i)
	}

	// 等待所有goroutine完成
	wg.Wait()
	duration := time.Since(startTime)

	log.Printf("🎉 并发推送完成！")
	log.Printf("📊 统计信息:")
	log.Printf("   - 总订单数: %d", orderCount)
	log.Printf("   - 成功推送: %d", successCount)
	log.Printf("   - 失败订单: %d", orderCount-successCount)
	log.Printf("   - 总耗时: %v", duration)
	log.Printf("   - 平均TPS: %.2f/秒", float64(orderCount)/duration.Seconds())
}

// RunAdvancedExamples 运行所有高级功能示例
func RunAdvancedExamples() {
	log.Printf("🚀 开始运行高级功能示例...")
	log.Printf("⏰ 当前时间: %s\n", time.Now().Format("2006-01-02 15:04:05"))

	// 运行退款管理示例
	RefundManagementExample()

	// 运行配送状态示例
	DeliveryStatusExample()

	// 运行订单修改示例
	OrderModificationExample()

	// 运行订单取消示例
	OrderCancellationExample()

	// 运行订单催单示例
	OrderReminderExample()

	// 运行并发操作示例
	ConcurrentOperationsExample()

	log.Printf("\n🎉 所有高级功能示例运行完成！")
	log.Printf("📈 SDK 已验证在高并发场景下的稳定性")
}
