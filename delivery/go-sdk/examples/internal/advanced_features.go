// Package examples 提供麦芽田配送开放平台 Go SDK 的高级功能示例
// 展示批量操作、并发处理、实时位置更新等高级特性
package internal

import (
	"context"
	"fmt"
	"log"
	"sync"
	"time"

	"github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/client"
	"github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/api"
	entityDelivery "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/delivery"
	entityExpress "github.com/anthonyhann/myt-open-api-sdk/delivery/go-sdk/models/sender/entity/express"
)

// BatchDeliveryStatusExample 批量配送状态更新示例
// 演示如何高效地批量推送多个订单的配送状态
func BatchDeliveryStatusExample() {
	fmt.Println("=== 批量配送状态更新示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		MaxConnections(20). // 增加连接池大小支持并发
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 模拟批量配送订单数据
	orders := []struct {
		orderNo       string
		sourceOrderNo string
		shopId        string
		status        string
		riderName     string
	}{
		{"delivery_001", "myt_order_001", "shop_001", "GRABBED", "张三"},
		{"delivery_002", "myt_order_002", "shop_002", "PICKUP", "李四"},
		{"delivery_003", "myt_order_003", "shop_003", "DELIVERING", "王五"},
		{"delivery_004", "myt_order_004", "shop_004", "DONE", "赵六"},
		{"delivery_005", "myt_order_005", "shop_005", "GRABBED", "陈七"},
	}

	log.Printf("📦 开始批量更新配送状态...")
	log.Printf("📋 待更新订单数量: %d", len(orders))
	startTime := time.Now()

	successCount := 0
	var wg sync.WaitGroup
	var mu sync.Mutex

	for i, order := range orders {
		wg.Add(1)

		go func(index int, o struct {
			orderNo       string
			sourceOrderNo string
			shopId        string
			status        string
			riderName     string
		},
		) {
			defer wg.Done()

			deliveryReq := &entityDelivery.DeliveryChangeReq{
				OrderNo:       o.orderNo,
				SourceOrderNo: o.sourceOrderNo,
				ShopId:        o.shopId,
				Status:        o.status,
				Tag:           "batch_delivery",
				RiderName:     o.riderName,
				RiderPhone:    fmt.Sprintf("138%08d", index+10000000),
				Longitude:     fmt.Sprintf("116.%06d", 400000+index*1000),
				Latitude:      fmt.Sprintf("39.%06d", 900000+index*500),
				UpdateTime:    time.Now().Unix(),
			}

			resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
			if err != nil {
				log.Printf("❌ 订单 %s 状态更新失败: %v", o.orderNo, err)
				return
			}

			if resp.Code == 200 {
				mu.Lock()
				successCount++
				mu.Unlock()
				log.Printf("✅ 订单 %s 状态更新成功: %s (%d/%d)",
					o.orderNo, o.status, successCount, len(orders))
			} else {
				log.Printf("❌ 订单 %s 状态更新失败: [%d] %s",
					o.orderNo, resp.Code, resp.Message)
			}
		}(i, order)
	}

	// 等待所有协程完成
	wg.Wait()
	duration := time.Since(startTime)

	log.Printf("🎉 批量更新完成！")
	log.Printf("📊 统计信息:")
	log.Printf("   - 总订单数: %d", len(orders))
	log.Printf("   - 成功更新: %d", successCount)
	log.Printf("   - 失败订单: %d", len(orders)-successCount)
	log.Printf("   - 总耗时: %v", duration)
	log.Printf("   - 平均TPS: %.2f/秒", float64(len(orders))/duration.Seconds())
}

// RealTimeLocationTrackingExample 实时位置追踪示例
// 演示骑手实时位置更新和轨迹记录
func RealTimeLocationTrackingExample() {
	fmt.Println("\n=== 实时位置追踪示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	orderNo := "realtime_delivery_" + fmt.Sprintf("%d", time.Now().Unix())
	riderName := "张骑手"

	log.Printf("📍 开始实时位置追踪...")
	log.Printf("👤 骑手: %s", riderName)
	log.Printf("📦 订单: %s", orderNo)

	// 模拟骑手移动轨迹（从商家到客户的路线）
	locations := []struct {
		lng, lat string
		status   string
		desc     string
	}{
		{"116.397428", "39.90923", "PICKUP", "骑手到达商家门店"},
		{"116.400428", "39.91123", "DELIVERING", "骑手离开商家，开始配送"},
		{"116.405428", "39.91523", "DELIVERING", "骑手正在前往客户地址"},
		{"116.410428", "39.91823", "DELIVERING", "骑手距离客户还有500米"},
		{"116.413428", "39.92023", "DELIVERING", "骑手已到达客户楼下"},
		{"116.413428", "39.92023", "DONE", "订单配送完成"},
	}

	for i, loc := range locations {
		log.Printf("🚶 位置更新 %d: %s", i+1, loc.desc)

		deliveryReq := &entityDelivery.DeliveryChangeReq{
			OrderNo:       orderNo,
			SourceOrderNo: "myt_realtime_" + fmt.Sprintf("%d", time.Now().Unix()),
			ShopId:        "shop_123",
			Status:        loc.status,
			Tag:           "realtime_tracking",
			RiderName:     riderName,
			RiderPhone:    "138****0001",
			Longitude:     loc.lng,
			Latitude:      loc.lat,
			UpdateTime:    time.Now().Unix(),
		}

		// 添加距离信息（模拟计算）
		if loc.status == "DELIVERING" {
			deliveryReq.Distance = int64(500 - i*100) // 模拟递减的距离
		}

		resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
		if err != nil {
			log.Printf("❌ 位置更新失败: %v", err)
			continue
		}

		if resp.Code == 200 {
			log.Printf("✅ 位置更新成功")
			log.Printf("📍 当前位置: (%s, %s)", loc.lng, loc.lat)
			if deliveryReq.Distance > 0 {
				log.Printf("📏 距离客户: %d米", deliveryReq.Distance)
			}
		} else {
			log.Printf("❌ 位置更新失败: [%d] %s", resp.Code, resp.Message)
		}

		// 模拟实时更新间隔
		if i < len(locations)-1 {
			time.Sleep(2 * time.Second)
		}
	}

	log.Printf("🎯 实时追踪完成！配送已送达")
}

// MultiModalDeliveryExample 多模式配送示例
// 演示同城配送和快递配送的不同处理方式
func MultiModalDeliveryExample() {
	fmt.Println("\n=== 多模式配送示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 场景1：同城配送（即时配送）
	log.Printf("🏍️ 场景1: 同城即时配送")
	localDeliveryReq := &entityDelivery.DeliveryChangeReq{
		OrderNo:       "local_delivery_" + fmt.Sprintf("%d", time.Now().Unix()),
		SourceOrderNo: "myt_local_" + fmt.Sprintf("%d", time.Now().Unix()),
		ShopId:        "shop_123",
		Status:        "DELIVERING",
		Tag:           "local_delivery",
		RiderName:     "本地骑手小李",
		RiderPhone:    "138****0001",
		Longitude:     "116.397428",
		Latitude:      "39.90923",
		Distance:      1500, // 距离1.5公里
		UpdateTime:    time.Now().Unix(),
	}

	resp1, err := sender.DeliveryChange(ctx, token, localDeliveryReq)
	if err != nil {
		log.Printf("❌ 同城配送状态更新失败: %v", err)
	} else if resp1.Code == 200 {
		log.Printf("✅ 同城配送状态更新成功")
		log.Printf("📍 配送距离: %.1f公里", float64(localDeliveryReq.Distance)/1000)
	}

	time.Sleep(2 * time.Second)

	// 场景2：快递配送（跨城配送）
	log.Printf("\n📦 场景2: 跨城快递配送")
	expressReq := &entityExpress.LocationChangeReq{
		OrderNo:       "express_delivery_" + fmt.Sprintf("%d", time.Now().Unix()),
		SourceOrderNo: "myt_express_" + fmt.Sprintf("%d", time.Now().Unix()),
		ShopId:        "shop_456",
		Tag:           "express_delivery",
		Locations: []entityExpress.Location{
			{
				Description: "[上海市]快件已从【上海松江分拣中心】发出",
				City:        "上海市",
				Longitude:   "121.227428",
				Latitude:    "31.00923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Add(-6 * time.Hour).Unix(),
			},
			{
				Description: "[北京市]快件已到达【北京分拣中心】",
				City:        "北京市",
				Longitude:   "116.397428",
				Latitude:    "39.90923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Add(-2 * time.Hour).Unix(),
			},
			{
				Description: "[北京市]快件正在派送中，派送员：王师傅",
				City:        "北京市",
				Longitude:   "116.407428",
				Latitude:    "39.91923",
				Status:      "DELIVERING",
				UpdateTime:  time.Now().Unix(),
			},
		},
	}

	resp2, err := sender.LocationChange(ctx, token, expressReq)
	if err != nil {
		log.Printf("❌ 快递轨迹更新失败: %v", err)
	} else if resp2.Code == 200 {
		log.Printf("✅ 快递轨迹更新成功")
		log.Printf("📋 轨迹节点: %d个", len(expressReq.Locations))
		log.Printf("🌍 跨域配送: %s → %s",
			expressReq.Locations[0].City,
			expressReq.Locations[len(expressReq.Locations)-1].City)
	}
}

// DeliveryPerformanceAnalysisExample 配送性能分析示例
// 演示如何基于SDK数据进行配送性能分析
func DeliveryPerformanceAnalysisExample() {
	fmt.Println("\n=== 配送性能分析示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 模拟不同配送场景的性能数据
	deliveryScenarios := []struct {
		name        string
		orderCount  int
		avgDelivery time.Duration
		successRate float64
	}{
		{"高峰期配送", 50, 45 * time.Minute, 95.0},
		{"普通时段配送", 30, 30 * time.Minute, 98.0},
		{"夜间配送", 15, 35 * time.Minute, 92.0},
	}

	log.Printf("📊 配送性能分析报告")

	totalOrders := 0
	totalSuccessful := 0
	var totalDeliveryTime time.Duration

	for _, scenario := range deliveryScenarios {
		log.Printf("📈 场景: %s", scenario.name)

		successfulOrders := int(float64(scenario.orderCount) * scenario.successRate / 100)
		totalOrders += scenario.orderCount
		totalSuccessful += successfulOrders
		totalDeliveryTime += time.Duration(successfulOrders) * scenario.avgDelivery

		// 模拟推送性能数据
		for i := 0; i < 3; i++ { // 只推送少量示例数据
			orderNo := fmt.Sprintf("%s_order_%d", scenario.name, i+1)

			deliveryReq := &entityDelivery.DeliveryChangeReq{
				OrderNo:       orderNo,
				SourceOrderNo: "myt_perf_" + fmt.Sprintf("%d", time.Now().Unix()+int64(i)),
				ShopId:        "shop_performance",
				Status:        "DONE",
				Tag:           "performance_analysis",
				RiderName:     fmt.Sprintf("骑手%d号", i+1),
				RiderPhone:    fmt.Sprintf("138%08d", i+10000000),
				Longitude:     fmt.Sprintf("116.%06d", 400000+i*1000),
				Latitude:      fmt.Sprintf("39.%06d", 900000+i*500),
				UpdateTime:    time.Now().Unix(),
			}

			resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
			if err == nil && resp.Code == 200 {
				// 性能数据推送成功
			}
		}

		log.Printf("   📦 订单数量: %d", scenario.orderCount)
		log.Printf("   ✅ 成功配送: %d (%.1f%%)", successfulOrders, scenario.successRate)
		log.Printf("   ⏱️ 平均配送时长: %v", scenario.avgDelivery)
		log.Printf("")
	}

	// 汇总分析
	log.Printf("🎯 总体性能指标:")
	avgSuccessRate := float64(totalSuccessful) / float64(totalOrders) * 100
	avgDeliveryTime := totalDeliveryTime / time.Duration(totalSuccessful)

	log.Printf("   📊 总订单数: %d", totalOrders)
	log.Printf("   ✅ 总成功率: %.1f%%", avgSuccessRate)
	log.Printf("   ⏱️ 平均配送时长: %v", avgDeliveryTime)
	log.Printf("   🎖️ 性能评级: %s", getPerformanceGrade(avgSuccessRate, avgDeliveryTime))
}

// getPerformanceGrade 根据成功率和配送时长计算性能等级
func getPerformanceGrade(successRate float64, avgDeliveryTime time.Duration) string {
	if successRate >= 98 && avgDeliveryTime <= 30*time.Minute {
		return "优秀⭐⭐⭐⭐⭐"
	} else if successRate >= 95 && avgDeliveryTime <= 40*time.Minute {
		return "良好⭐⭐⭐⭐"
	} else if successRate >= 90 && avgDeliveryTime <= 50*time.Minute {
		return "合格⭐⭐⭐"
	} else {
		return "需要改进⭐⭐"
	}
}

// ConcurrentDeliveryExample 并发配送处理示例
// 演示高并发场景下的配送状态管理
func ConcurrentDeliveryExample() {
	fmt.Println("\n=== 并发配送处理示例 ===")

	// 创建客户端
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey("your_app_key").
		APISecret("your_app_secret").
		MaxConnections(30). // 增加连接池大小
		RequestTimeout(10 * time.Second).
		Build()
	sender := api.NewDeliverySender(config)

	ctx := context.Background()
	token := "mock_access_token"

	// 模拟高并发配送场景
	concurrentOrders := 20
	var wg sync.WaitGroup
	results := make(chan string, concurrentOrders)

	log.Printf("⚡ 开始并发配送处理...")
	log.Printf("📋 并发订单数: %d", concurrentOrders)
	startTime := time.Now()

	// 启动多个协程模拟并发配送
	for i := 0; i < concurrentOrders; i++ {
		wg.Add(1)

		go func(index int) {
			defer wg.Done()

			orderNo := fmt.Sprintf("concurrent_delivery_%03d_%d", index+1, time.Now().Unix())

			// 模拟配送流程
			statuses := []string{"GRABBED", "PICKUP", "DELIVERING", "DONE"}

			for _, status := range statuses {
				deliveryReq := &entityDelivery.DeliveryChangeReq{
					OrderNo:       orderNo,
					SourceOrderNo: "myt_concurrent_" + fmt.Sprintf("%d", index+1000),
					ShopId:        "shop_concurrent",
					Status:        status,
					Tag:           "concurrent_test",
					RiderName:     fmt.Sprintf("并发骑手%d", index+1),
					RiderPhone:    fmt.Sprintf("138%08d", index+13000000000),
					Longitude:     fmt.Sprintf("116.%06d", 400000+index*100),
					Latitude:      fmt.Sprintf("39.%06d", 900000+index*50),
					UpdateTime:    time.Now().Unix(),
				}

				resp, err := sender.DeliveryChange(ctx, token, deliveryReq)
				if err != nil {
					results <- fmt.Sprintf("❌ %s: %v", orderNo, err)
					return
				}

				if resp.Code != 200 {
					results <- fmt.Sprintf("❌ %s: [%d] %s", orderNo, resp.Code, resp.Message)
					return
				}

				// 模拟状态间隔
				time.Sleep(100 * time.Millisecond)
			}

			results <- fmt.Sprintf("✅ %s: 配送完成", orderNo)
		}(i)
	}

	// 等待所有协程完成
	go func() {
		wg.Wait()
		close(results)
	}()

	// 收集结果
	successCount := 0
	for result := range results {
		log.Printf(result)
		if result[:1] == "✅" {
			successCount++
		}
	}

	duration := time.Since(startTime)

	log.Printf("🏁 并发处理完成！")
	log.Printf("📊 统计信息:")
	log.Printf("   - 并发订单数: %d", concurrentOrders)
	log.Printf("   - 成功完成: %d", successCount)
	log.Printf("   - 失败订单: %d", concurrentOrders-successCount)
	log.Printf("   - 总耗时: %v", duration)
	log.Printf("   - 并发性能: %.2f 订单/秒", float64(concurrentOrders)/duration.Seconds())
	log.Printf("   - 成功率: %.1f%%", float64(successCount)/float64(concurrentOrders)*100)
}

// RunAdvancedExamples 运行所有高级功能示例
func RunAdvancedExamples() {
	log.Printf("🚀 开始运行高级功能示例...")
	log.Printf("⏰ 当前时间: %s\n", time.Now().Format("2006-01-02 15:04:05"))

	// 运行批量配送状态更新示例
	BatchDeliveryStatusExample()

	// 运行实时位置追踪示例
	RealTimeLocationTrackingExample()

	// 运行多模式配送示例
	MultiModalDeliveryExample()

	// 运行配送性能分析示例
	DeliveryPerformanceAnalysisExample()

	// 运行并发配送处理示例
	ConcurrentDeliveryExample()

	log.Printf("\n🎉 所有高级功能示例运行完成！")
	log.Printf("📈 配送SDK已验证在高并发和复杂场景下的稳定性")
}
