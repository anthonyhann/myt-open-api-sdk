// Package examples 提供麦芽田开放平台 Go SDK 的回调接口实现示例
// 演示如何实现接收麦芽田推送数据的回调服务器
package internal

import (
	"crypto/hmac"
	"crypto/sha256"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"sort"
	"time"

	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/client"
	"github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/api"
	entityOrder "github.com/anthonyhann/myt-open-api-sdk/channel/go-sdk/models/sender/entity/order"
)

// CallbackServer 回调服务器结构
// 封装了处理麦芽田回调请求的所有功能
type CallbackServer struct {
	appKey    string             // 应用密钥
	appSecret string             // 应用密钥（用于签名验证）
	sender    api.IChannelSender // SDK Sender 客户端（用于回调后的状态推送）
	server    *http.Server       // HTTP 服务器
}

// CallbackRequest 麦芽田回调请求结构
// 所有回调接口都使用统一的请求格式
type CallbackRequest struct {
	AppKey    string `json:"app_key"`   // 应用密钥
	Token     string `json:"token"`     // 商户授权令牌
	Timestamp int64  `json:"timestamp"` // 请求时间戳
	Data      string `json:"data"`      // 业务数据（JSON 字符串）
	Signature string `json:"signature"` // 请求签名
}

// CallbackResponse 回调响应结构
// 三方必须返回标准的响应格式
type CallbackResponse struct {
	Code    int    `json:"code"`    // 状态码：200 表示成功
	Message string `json:"message"` // 消息：成功时为 "ok"
}

// ConfirmOrderCallbackData 确认订单回调数据
// 麦芽田推送给三方的确认订单请求
type ConfirmOrderCallbackData struct {
	OrderId    string `json:"order_id"`    // 订单ID
	ShopId     string `json:"shop_id"`     // 门店ID
	UpdateTime int64  `json:"update_time"` // 更新时间
}

// DeliveryChangeCallbackData 配送状态变更回调数据
// 麦芽田推送给三方的配送状态变更
type DeliveryChangeCallbackData struct {
	OrderId     string `json:"order_id"`     // 订单ID
	ShopId      string `json:"shop_id"`      // 门店ID
	Status      string `json:"status"`       // 配送状态
	RiderName   string `json:"rider_name"`   // 骑手姓名
	RiderPhone  string `json:"rider_phone"`  // 骑手电话
	LogisticNo  string `json:"logistic_no"`  // 物流单号
	LogisticTag string `json:"logistic_tag"` // 物流标识
	Longitude   string `json:"longitude"`    // 骑手经度
	Latitude    string `json:"latitude"`     // 骑手纬度
	Distance    int    `json:"distance"`     // 距离顾客距离（米）
	UpdateTime  int64  `json:"update_time"`  // 更新时间
}

// AgreeRefundCallbackData 同意退款回调数据
// 麦芽田通知三方同意退款
type AgreeRefundCallbackData struct {
	OrderId    string `json:"order_id"`    // 订单ID
	ShopId     string `json:"shop_id"`     // 门店ID
	RefundId   string `json:"refund_id"`   // 退款ID
	Reason     string `json:"reason"`      // 同意原因
	TotalPrice int    `json:"total_price"` // 退款金额（分）
	UpdateTime int64  `json:"update_time"` // 更新时间
}

// RejectRefundCallbackData 拒绝退款回调数据
// 麦芽田通知三方拒绝退款
type RejectRefundCallbackData struct {
	OrderId    string `json:"order_id"`    // 订单ID
	ShopId     string `json:"shop_id"`     // 门店ID
	RefundId   string `json:"refund_id"`   // 退款ID
	Reason     string `json:"reason"`      // 拒绝原因
	UpdateTime int64  `json:"update_time"` // 更新时间
}

// NewCallbackServer 创建回调服务器实例
func NewCallbackServer(appKey, appSecret string, port int) *CallbackServer {
	// 创建 Sender 客户端（用于回调后推送状态）
	config := client.NewConfigBuilder().
		BaseURL("https://open-api-test.maiyatian.com").
		APIKey(appKey).
		APISecret(appSecret).
		Build()

	sender := api.NewChannelSender(config)

	// 创建 HTTP 服务器
	mux := http.NewServeMux()
	server := &http.Server{
		Addr:    fmt.Sprintf(":%d", port),
		Handler: mux,
	}

	callbackServer := &CallbackServer{
		appKey:    appKey,
		appSecret: appSecret,
		sender:    sender,
		server:    server,
	}

	// 注册回调路由
	callbackServer.registerRoutes(mux)

	return callbackServer
}

// registerRoutes 注册所有回调路由
func (s *CallbackServer) registerRoutes(mux *http.ServeMux) {
	// 订单相关回调
	mux.HandleFunc("/callback/order_confirm", s.handleOrderConfirm)
	mux.HandleFunc("/callback/delivery_change", s.handleDeliveryChange)
	mux.HandleFunc("/callback/order_change", s.handleOrderChange)

	// 退款相关回调
	mux.HandleFunc("/callback/agree_refund", s.handleAgreeRefund)
	mux.HandleFunc("/callback/reject_refund", s.handleRejectRefund)

	// 其他回调
	mux.HandleFunc("/callback/token_unbind", s.handleTokenUnbind)

	// 健康检查
	mux.HandleFunc("/health", s.handleHealth)
}

// Start 启动回调服务器
func (s *CallbackServer) Start() error {
	log.Printf("🚀 启动回调服务器...")
	log.Printf("📍 监听地址: %s", s.server.Addr)
	log.Printf("🔗 回调地址示例:")
	log.Printf("   - 订单确认: http://localhost%s/callback/order_confirm", s.server.Addr)
	log.Printf("   - 配送变更: http://localhost%s/callback/delivery_change", s.server.Addr)
	log.Printf("   - 同意退款: http://localhost%s/callback/agree_refund", s.server.Addr)

	return s.server.ListenAndServe()
}

// Stop 停止回调服务器
func (s *CallbackServer) Stop() error {
	log.Printf("🛑 停止回调服务器...")
	return s.server.Shutdown(nil)
}

// handleOrderConfirm 处理订单确认回调
// 麦芽田通知三方确认订单
func (s *CallbackServer) handleOrderConfirm(w http.ResponseWriter, r *http.Request) {
	log.Printf("📞 收到订单确认回调: %s %s", r.Method, r.URL.Path)

	// 解析回调请求
	var callbackReq CallbackRequest
	if err := s.parseCallbackRequest(r, &callbackReq); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析请求失败: %v", err))
		return
	}

	// 验证签名
	if !s.verifySignature(&callbackReq) {
		log.Printf("❌ 签名验证失败")
		s.sendErrorResponse(w, 401, "签名验证失败")
		return
	}

	// 解析业务数据
	var confirmData ConfirmOrderCallbackData
	if err := json.Unmarshal([]byte(callbackReq.Data), &confirmData); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析业务数据失败: %v", err))
		return
	}

	log.Printf("📦 确认订单请求: OrderId=%s, ShopId=%s", confirmData.OrderId, confirmData.ShopId)

	// 业务逻辑：三方确认订单
	success := s.processOrderConfirm(&confirmData)
	if !success {
		s.sendErrorResponse(w, 500, "订单确认处理失败")
		return
	}

	// 推送确认状态给麦芽田
	s.pushOrderConfirmed(&callbackReq, &confirmData)

	// 返回成功响应
	s.sendSuccessResponse(w)
}

// handleDeliveryChange 处理配送状态变更回调
// 麦芽田推送配送状态变更给三方
func (s *CallbackServer) handleDeliveryChange(w http.ResponseWriter, r *http.Request) {
	log.Printf("📞 收到配送状态变更回调: %s %s", r.Method, r.URL.Path)

	// 解析回调请求
	var callbackReq CallbackRequest
	if err := s.parseCallbackRequest(r, &callbackReq); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析请求失败: %v", err))
		return
	}

	// 验证签名
	if !s.verifySignature(&callbackReq) {
		log.Printf("❌ 签名验证失败")
		s.sendErrorResponse(w, 401, "签名验证失败")
		return
	}

	// 解析业务数据
	var deliveryData DeliveryChangeCallbackData
	if err := json.Unmarshal([]byte(callbackReq.Data), &deliveryData); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析业务数据失败: %v", err))
		return
	}

	log.Printf("🚚 配送状态变更: OrderId=%s, Status=%s, Rider=%s",
		deliveryData.OrderId, deliveryData.Status, deliveryData.RiderName)

	// 业务逻辑：同步配送状态到三方系统
	success := s.processDeliveryChange(&deliveryData)
	if !success {
		s.sendErrorResponse(w, 500, "配送状态同步失败")
		return
	}

	// 返回成功响应
	s.sendSuccessResponse(w)
}

// handleOrderChange 处理订单状态变更回调
// 麦芽田推送订单状态变更给三方
func (s *CallbackServer) handleOrderChange(w http.ResponseWriter, r *http.Request) {
	log.Printf("📞 收到订单状态变更回调: %s %s", r.Method, r.URL.Path)

	// 基础的请求处理逻辑
	var callbackReq CallbackRequest
	if err := s.parseCallbackRequest(r, &callbackReq); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析请求失败: %v", err))
		return
	}

	if !s.verifySignature(&callbackReq) {
		s.sendErrorResponse(w, 401, "签名验证失败")
		return
	}

	log.Printf("📋 订单状态变更: %s", callbackReq.Data)

	// 业务逻辑：处理订单状态变更
	// 这里可以根据具体的状态变更类型进行不同处理

	s.sendSuccessResponse(w)
}

// handleAgreeRefund 处理同意退款回调
// 麦芽田通知三方同意退款
func (s *CallbackServer) handleAgreeRefund(w http.ResponseWriter, r *http.Request) {
	log.Printf("📞 收到同意退款回调: %s %s", r.Method, r.URL.Path)

	var callbackReq CallbackRequest
	if err := s.parseCallbackRequest(r, &callbackReq); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析请求失败: %v", err))
		return
	}

	if !s.verifySignature(&callbackReq) {
		s.sendErrorResponse(w, 401, "签名验证失败")
		return
	}

	var refundData AgreeRefundCallbackData
	if err := json.Unmarshal([]byte(callbackReq.Data), &refundData); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析业务数据失败: %v", err))
		return
	}

	log.Printf("💰 同意退款: OrderId=%s, RefundId=%s, Amount=%.2f元",
		refundData.OrderId, refundData.RefundId, float64(refundData.TotalPrice)/100)

	// 业务逻辑：处理同意退款
	success := s.processAgreeRefund(&refundData)
	if !success {
		s.sendErrorResponse(w, 500, "退款处理失败")
		return
	}

	s.sendSuccessResponse(w)
}

// handleRejectRefund 处理拒绝退款回调
// 麦芽田通知三方拒绝退款
func (s *CallbackServer) handleRejectRefund(w http.ResponseWriter, r *http.Request) {
	log.Printf("📞 收到拒绝退款回调: %s %s", r.Method, r.URL.Path)

	var callbackReq CallbackRequest
	if err := s.parseCallbackRequest(r, &callbackReq); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析请求失败: %v", err))
		return
	}

	if !s.verifySignature(&callbackReq) {
		s.sendErrorResponse(w, 401, "签名验证失败")
		return
	}

	var refundData RejectRefundCallbackData
	if err := json.Unmarshal([]byte(callbackReq.Data), &refundData); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析业务数据失败: %v", err))
		return
	}

	log.Printf("❌ 拒绝退款: OrderId=%s, RefundId=%s, Reason=%s",
		refundData.OrderId, refundData.RefundId, refundData.Reason)

	// 业务逻辑：处理拒绝退款
	success := s.processRejectRefund(&refundData)
	if !success {
		s.sendErrorResponse(w, 500, "退款处理失败")
		return
	}

	s.sendSuccessResponse(w)
}

// handleTokenUnbind 处理令牌解绑回调
// 麦芽田通知三方解绑令牌
func (s *CallbackServer) handleTokenUnbind(w http.ResponseWriter, r *http.Request) {
	log.Printf("📞 收到令牌解绑回调: %s %s", r.Method, r.URL.Path)

	var callbackReq CallbackRequest
	if err := s.parseCallbackRequest(r, &callbackReq); err != nil {
		s.sendErrorResponse(w, 400, fmt.Sprintf("解析请求失败: %v", err))
		return
	}

	if !s.verifySignature(&callbackReq) {
		s.sendErrorResponse(w, 401, "签名验证失败")
		return
	}

	log.Printf("🔓 令牌解绑: Token=%s", callbackReq.Token[:20]+"...")

	// 业务逻辑：处理令牌解绑
	// 三方需要清理本地存储的令牌信息
	s.processTokenUnbind(&callbackReq)

	s.sendSuccessResponse(w)
}

// handleHealth 健康检查接口
func (s *CallbackServer) handleHealth(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(map[string]any{
		"status":    "ok",
		"timestamp": time.Now().Unix(),
		"version":   "1.0.0",
	})
}

// parseCallbackRequest 解析回调请求
func (s *CallbackServer) parseCallbackRequest(r *http.Request, req *CallbackRequest) error {
	if r.Method != http.MethodPost {
		return fmt.Errorf("仅支持 POST 请求")
	}

	if err := json.NewDecoder(r.Body).Decode(req); err != nil {
		return fmt.Errorf("JSON 解析失败: %v", err)
	}

	return nil
}

// verifySignature 验证签名
func (s *CallbackServer) verifySignature(req *CallbackRequest) bool {
	// 构建待签名的数据
	data := map[string]any{
		"app_key":   req.AppKey,
		"token":     req.Token,
		"timestamp": req.Timestamp,
		"data":      req.Data,
	}

	// 提取并排序 key
	keys := make([]string, 0, len(data))
	for k := range data {
		keys = append(keys, k)
	}
	sort.Strings(keys)

	// 构建待签名字符串
	dataToSign := ""
	for _, k := range keys {
		dataToSign += fmt.Sprintf("%s=%v,", k, data[k])
	}
	dataToSign = dataToSign[:len(dataToSign)-1] // 移除最后一个逗号

	// 计算 HmacSHA256 签名
	mac := hmac.New(sha256.New, []byte(s.appSecret))
	mac.Write([]byte(dataToSign))
	expectedSign := base64.URLEncoding.EncodeToString(mac.Sum(nil))

	// 比较签名
	return expectedSign == req.Signature
}

// processOrderConfirm 处理订单确认业务逻辑
func (s *CallbackServer) processOrderConfirm(data *ConfirmOrderCallbackData) bool {
	log.Printf("🔄 处理订单确认业务逻辑...")
	log.Printf("   - 检查订单状态")
	log.Printf("   - 验证库存")
	log.Printf("   - 更新订单状态为已确认")

	// 模拟业务处理时间
	time.Sleep(1 * time.Second)

	log.Printf("✅ 订单确认处理完成")
	return true
}

// processDeliveryChange 处理配送状态变更业务逻辑
func (s *CallbackServer) processDeliveryChange(data *DeliveryChangeCallbackData) bool {
	log.Printf("🔄 处理配送状态变更业务逻辑...")
	log.Printf("   - 同步配送状态: %s", data.Status)
	log.Printf("   - 更新骑手信息: %s (%s)", data.RiderName, data.RiderPhone)
	log.Printf("   - 更新位置信息: (%s, %s)", data.Longitude, data.Latitude)

	// 可以在这里向用户推送配送状态更新
	log.Printf("📱 向用户推送配送状态更新")

	return true
}

// processAgreeRefund 处理同意退款业务逻辑
func (s *CallbackServer) processAgreeRefund(data *AgreeRefundCallbackData) bool {
	log.Printf("🔄 处理同意退款业务逻辑...")
	log.Printf("   - 验证退款信息")
	log.Printf("   - 处理资金退回")
	log.Printf("   - 更新订单状态")

	// 模拟退款处理时间
	time.Sleep(2 * time.Second)

	log.Printf("💳 退款已处理，资金将在1-3个工作日内到账")
	return true
}

// processRejectRefund 处理拒绝退款业务逻辑
func (s *CallbackServer) processRejectRefund(data *RejectRefundCallbackData) bool {
	log.Printf("🔄 处理拒绝退款业务逻辑...")
	log.Printf("   - 记录拒绝原因: %s", data.Reason)
	log.Printf("   - 更新退款状态为已拒绝")
	log.Printf("   - 通知用户退款被拒绝")

	return true
}

// processTokenUnbind 处理令牌解绑业务逻辑
func (s *CallbackServer) processTokenUnbind(req *CallbackRequest) {
	log.Printf("🔄 处理令牌解绑业务逻辑...")
	log.Printf("   - 清理本地令牌缓存")
	log.Printf("   - 更新授权状态")
	log.Printf("   - 停止相关服务")

	// 这里应该清理本地存储的令牌信息
	log.Printf("🗑️ 令牌已清理: %s", req.Token[:20]+"...")
}

// pushOrderConfirmed 推送订单确认状态给麦芽田
func (s *CallbackServer) pushOrderConfirmed(req *CallbackRequest, data *ConfirmOrderCallbackData) {
	log.Printf("📤 推送订单确认状态给麦芽田...")

	confirmReq := &entityOrder.OrderConfirmedReq{
		OrderId:    data.OrderId,
		ShopId:     data.ShopId,
		UpdateTime: time.Now().Unix(),
	}

	resp, err := s.sender.OrderConfirmed(nil, req.Token, confirmReq)
	if err != nil {
		log.Printf("❌ 推送确认状态失败: %v", err)
		return
	}

	if resp.Code == 200 {
		log.Printf("✅ 确认状态推送成功")
	} else {
		log.Printf("❌ 确认状态推送失败: [%d] %s", resp.Code, resp.Message)
	}
}

// sendSuccessResponse 发送成功响应
func (s *CallbackServer) sendSuccessResponse(w http.ResponseWriter) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(CallbackResponse{
		Code:    200,
		Message: "ok",
	})
}

// sendErrorResponse 发送错误响应
func (s *CallbackServer) sendErrorResponse(w http.ResponseWriter, code int, message string) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(code)
	json.NewEncoder(w).Encode(CallbackResponse{
		Code:    code,
		Message: message,
	})
}

// CallbackServerExample 回调服务器示例
// 演示如何搭建和运行回调服务器
func CallbackServerExample() {
	fmt.Println("=== 回调服务器示例 ===")

	// 创建回调服务器
	server := NewCallbackServer("your_app_key", "your_app_secret", 8080)

	// 启动服务器（这里只是演示，实际使用时应该在 goroutine 中启动）
	log.Printf("💡 回调服务器示例创建成功")
	log.Printf("📋 使用说明:")
	log.Printf("   1. 将以下回调地址配置到麦芽田开放平台:")
	log.Printf("      - 订单确认: http://your-domain.com:8080/callback/order_confirm")
	log.Printf("      - 配送变更: http://your-domain.com:8080/callback/delivery_change")
	log.Printf("      - 同意退款: http://your-domain.com:8080/callback/agree_refund")
	log.Printf("      - 拒绝退款: http://your-domain.com:8080/callback/reject_refund")
	log.Printf("   2. 确保服务器可以从外网访问")
	log.Printf("   3. 配置正确的 app_key 和 app_secret")
	log.Printf("   4. 调用 server.Start() 启动服务器")

	// 在实际应用中，应该这样启动:
	// go func() {
	//     if err := server.Start(); err != nil {
	//         log.Printf("服务器启动失败: %v", err)
	//     }
	// }()

	// 程序退出时停止服务器:
	// defer server.Stop()

	_ = server // 避免未使用变量的编译错误
}

// RunCallbackExamples 运行回调接口示例
func RunCallbackExamples() {
	log.Printf("🚀 开始运行回调接口示例...")

	// 运行回调服务器示例
	CallbackServerExample()

	log.Printf("🎉 回调接口示例完成！")
	log.Printf("📚 请参考代码注释了解详细的实现逻辑")
}
