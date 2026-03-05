/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.client.HttpClientConfig;
import com.maiyatian.delivery.sdk.client.HttpClientManager;
import com.maiyatian.delivery.sdk.client.ApiClientUtils;
import com.maiyatian.delivery.sdk.consts.Constants;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import com.maiyatian.delivery.sdk.models.types.ApiResponse;
import com.maiyatian.delivery.sdk.vars.Variables;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 完整配送业务流程示例
 * <p>
 * 模拟真实的配送业务场景，展示从授权到配送完成的完整流程
 * </p>
 * 
 * <h3>业务流程：</h3>
 * <ol>
 * <li><strong>授权认证</strong>：获取和刷新访问令牌</li>
 * <li><strong>订单接单</strong>：配送员接单并更新状态</li>
 * <li><strong>配送过程</strong>：实时同步配送状态和位置</li>
 * <li><strong>轨迹回传</strong>：记录配送轨迹信息</li>
 * <li><strong>配送完成</strong>：最终状态确认</li>
 * </ol>
 * 
 * <h3>技术特点：</h3>
 * <ul>
 * <li>使用新架构的泛型API调用</li>
 * <li>完整的错误处理和重试机制</li>
 * <li>真实的业务数据模拟</li>
 * <li>详细的日志输出</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeliveryWorkflowExample {
    
    /**
     * 示例配置信息
     */
    private static final String APP_KEY = "demo_delivery_app";
    private static final String APP_SECRET = "demo_delivery_secret";
    private static final String STORE_ID = "STORE_BJ_001";
    private static final String SHOP_ID = "SHOP_BJ_001";
    
    /**
     * 模拟订单信息
     */
    private static final String ORDER_NO = "DLV_" + System.currentTimeMillis();
    private static final String SOURCE_ORDER_NO = "SRC_" + System.currentTimeMillis();
    
    /**
     * 主演示方法
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("=== 麦芽田配送平台业务流程演示 ===");
        System.out.println("SDK版本: " + Variables.getVersion());
        System.out.println("订单号: " + ORDER_NO);
        System.out.println("源订单号: " + SOURCE_ORDER_NO);
        System.out.println();
        
        DeliveryWorkflowExample example = new DeliveryWorkflowExample();
        example.runCompleteWorkflow();
    }
    
    /**
     * 运行完整的配送业务流程
     */
    public void runCompleteWorkflow() {
        // 创建企业级配置
        HttpClientConfig config = createProductionConfig();
        
        try (HttpClientManager httpClient = new HttpClientManager(config)) {
            
            // 第1步：获取访问令牌
            String accessToken = step1_obtainAccessToken(httpClient);
            if (accessToken == null) {
                System.err.println("❌ 授权失败，终止流程");
                return;
            }
            
            // 第2步：刷新令牌（模拟令牌快过期的场景）
            accessToken = step2_refreshAccessToken(httpClient, accessToken);
            if (accessToken == null) {
                System.err.println("❌ 令牌刷新失败，终止流程");
                return;
            }
            
            // 第3步：配送员接单
            boolean orderAccepted = step3_acceptOrder(httpClient, accessToken);
            if (!orderAccepted) {
                System.err.println("❌ 接单失败，终止流程");
                return;
            }
            
            // 第4步：配送过程模拟
            boolean deliveryInProgress = step4_deliveryProcess(httpClient, accessToken);
            if (!deliveryInProgress) {
                System.err.println("❌ 配送过程异常，终止流程");
                return;
            }
            
            // 第5步：轨迹回传
            boolean trackingUpdated = step5_updateTracking(httpClient, accessToken);
            if (!trackingUpdated) {
                System.err.println("⚠️ 轨迹回传失败，但不影响配送流程");
            }
            
            // 第6步：配送完成
            boolean deliveryCompleted = step6_completeDelivery(httpClient, accessToken);
            if (!deliveryCompleted) {
                System.err.println("❌ 配送完成状态更新失败");
                return;
            }
            
            System.out.println("✅ 完整配送流程演示成功完成！");
            
        } catch (Exception e) {
            System.err.println("❌ 流程执行异常: " + e.getMessage());
            if (Variables.isDebugging()) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 创建生产级配置
     * 
     * @return 配置对象
     * @throws RuntimeException 如果配置创建失败
     */
    private HttpClientConfig createProductionConfig() {
        try {
            return HttpClientConfig.builder()
                .baseUrl(Constants.TEST_BASE_URL)        // 使用测试环境
                .apiKey(APP_KEY)
                .apiSecret(APP_SECRET)
                .maxConnections(50)                      // 适中的连接池
                .maxConnectionsPerHost(15)               // 每主机连接数
                .requestTimeout(Duration.ofSeconds(30))  // 30秒超时
                .retryMaxAttempts(3)                     // 重试3次
                .retryBaseDelay(Duration.ofMillis(200))  // 200ms基础延迟
                .retryMaxDelay(Duration.ofSeconds(5))    // 最大5秒延迟
                .enableLogging(Variables.isDebugging())  // 根据调试标志启用日志
                .logRequestBody(false)                   // 不记录敏感请求体
                .logResponseBody(false)                  // 不记录响应体（性能考虑）
                .build();
        } catch (Exception e) {
            throw new RuntimeException("配置创建失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 第1步：获取访问令牌
     * 
     * @param httpClient HTTP客户端
     * @return 访问令牌，失败时返回null
     */
    private String step1_obtainAccessToken(HttpClientManager httpClient) {
        System.out.println("📋 第1步：获取访问令牌");
        
        try {
            AccessTokenEntity.Req request = new AccessTokenEntity.Req();
            request.setGrantType(Constants.GRANT_TYPE_STORE);  // 门店级授权
            request.setCode("DEMO_AUTH_CODE_" + System.currentTimeMillis());
            request.setMobile("13800138001");
            request.setStoreId(STORE_ID);
            request.setCity("北京市");
            request.setCityCode("110100");
            
            // 自定义请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("X-Request-Source", "DeliveryWorkflowExample");
            headers.put("X-Request-ID", "REQ_TOKEN_" + System.currentTimeMillis());
            
            ApiResponse<AccessTokenEntity.Resp> response = ApiClientUtils.requestWithApiClient(
                httpClient, "POST", Constants.ACCESS_TOKEN_PATH,
                request, "", headers, AccessTokenEntity.Resp.class
            );
            
            if (response.isSuccess() && response.hasData()) {
                AccessTokenEntity.Resp tokenData = response.getData();
                System.out.println("   ✅ 访问令牌获取成功");
                System.out.println("   📱 令牌: " + maskSensitiveData(tokenData.getToken()));
                System.out.println("   🔄 刷新令牌: " + maskSensitiveData(tokenData.getRefreshToken()));
                System.out.println("   ⏰ 过期时间: " + tokenData.getExpireTime());
                return tokenData.getToken();
                
            } else {
                System.err.println("   ❌ 访问令牌获取失败: " + response.getMessage());
                return null;
            }
            
        } catch (JsonProcessingException e) {
            System.err.println("   ❌ JSON解析异常: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("   ❌ 请求异常: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 第2步：刷新访问令牌
     * 
     * @param httpClient HTTP客户端
     * @param currentToken 当前令牌
     * @return 新的访问令牌，失败时返回原令牌
     */
    private String step2_refreshAccessToken(HttpClientManager httpClient, String currentToken) {
        System.out.println("\n🔄 第2步：刷新访问令牌");
        
        try {
            RefreshTokenEntity.Req request = new RefreshTokenEntity.Req();
            request.setToken(currentToken);
            request.setRefreshToken("DEMO_REFRESH_TOKEN_" + System.currentTimeMillis());
            
            ApiResponse<RefreshTokenEntity.Resp> response = ApiClientUtils.requestWithApiClient(
                httpClient, "POST", Constants.REFRESH_TOKEN_PATH,
                request, currentToken, null, RefreshTokenEntity.Resp.class
            );
            
            if (response.isSuccess() && response.hasData()) {
                RefreshTokenEntity.Resp tokenData = response.getData();
                System.out.println("   ✅ 令牌刷新成功");
                System.out.println("   📱 新令牌: " + maskSensitiveData(tokenData.getToken()));
                System.out.println("   🔄 新刷新令牌: " + maskSensitiveData(tokenData.getRefreshToken()));
                return tokenData.getToken();
                
            } else {
                System.err.println("   ❌ 令牌刷新失败，继续使用原令牌: " + response.getMessage());
                return currentToken;  // 返回原令牌继续使用
            }
            
        } catch (JsonProcessingException e) {
            System.err.println("   ❌ JSON解析异常，继续使用原令牌: " + e.getMessage());
            return currentToken;  // 返回原令牌继续使用
        } catch (IOException e) {
            System.err.println("   ❌ 刷新异常，继续使用原令牌: " + e.getMessage());
            return currentToken;  // 返回原令牌继续使用
        }
    }
    
    /**
     * 第3步：配送员接单
     * 
     * @param httpClient HTTP客户端
     * @param token 访问令牌
     * @return 是否成功接单
     */
    private boolean step3_acceptOrder(HttpClientManager httpClient, String token) {
        System.out.println("\n📦 第3步：配送员接单");
        
        try {
            DeliveryChangeEntity.Req request = new DeliveryChangeEntity.Req();
            request.setOrderNo(ORDER_NO);
            request.setSourceOrderNo(SOURCE_ORDER_NO);
            request.setShopId(SHOP_ID);
            request.setStatus(Constants.DELIVERY_STATUS_GRABBED);  // 已分配骑手
            request.setRiderName("张三");
            request.setRiderPhone("13888888888");
            request.setTag("快速配送");
            request.setLongitude("116.397128");  // 北京天安门坐标
            request.setLatitude("39.916527");
            request.setUpdateTime(System.currentTimeMillis() / 1000);
            
            ApiResponse<Void> response = ApiClientUtils.requestWithEmptyResponse(
                httpClient, "POST", Constants.DELIVERY_CHANGE_PATH,
                request, token, null
            );
            
            if (response.isSuccess()) {
                System.out.println("   ✅ 配送员成功接单");
                System.out.println("   🚴 配送员: " + request.getRiderName());
                System.out.println("   📞 联系电话: " + request.getRiderPhone());
                System.out.println("   📍 当前位置: " + request.getLongitude() + ", " + request.getLatitude());
                return true;
                
            } else {
                System.err.println("   ❌ 接单失败: " + response.getMessage());
                return false;
            }
            
        } catch (JsonProcessingException e) {
            System.err.println("   ❌ JSON解析异常: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("   ❌ 接单异常: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 第4步：配送过程模拟
     * 
     * @param httpClient HTTP客户端
     * @param token 访问令牌
     * @return 是否配送过程正常
     */
    private boolean step4_deliveryProcess(HttpClientManager httpClient, String token) {
        System.out.println("\n🚚 第4步：配送过程模拟");
        
        // 4.1 到店取货
        boolean pickupSuccess = updateDeliveryStatus(httpClient, token, 
            Constants.DELIVERY_STATUS_PICKUP, "到店取货", 
            "116.399128", "39.918527", "取货码: 1234");
        
        if (!pickupSuccess) return false;
        
        // 模拟配送过程中的延迟
        simulateDelay(2000, "配送员取货中...");
        
        // 4.2 配送中
        boolean deliveringSuccess = updateDeliveryStatus(httpClient, token,
            Constants.DELIVERY_STATUS_DELIVERING, "配送中",
            "116.401128", "39.920527", "预计15分钟到达");
        
        if (!deliveringSuccess) return false;
        
        simulateDelay(3000, "配送员配送中...");
        
        return true;
    }
    
    /**
     * 第5步：更新轨迹信息
     * 
     * @param httpClient HTTP客户端
     * @param token 访问令牌
     * @return 是否成功更新轨迹
     */
    private boolean step5_updateTracking(HttpClientManager httpClient, String token) {
        System.out.println("\n🗺️ 第5步：轨迹回传");
        
        try {
            LocationChangeEntity.Req request = new LocationChangeEntity.Req();
            request.setOrderNo(ORDER_NO);
            request.setSourceOrderNo(SOURCE_ORDER_NO);
            request.setShopId(SHOP_ID);
            request.setTag("配送轨迹");
            
            // 构建详细的轨迹信息
            LocationChangeEntity.Location location1 = createTrackingLocation(
                "[北京市]配送员已从【海淀区餐厅】取货", "北京市",
                "116.399128", "39.918527", Constants.LOCATION_STATUS_PICKUP,
                System.currentTimeMillis() / 1000 - 600  // 10分钟前
            );
            
            LocationChangeEntity.Location location2 = createTrackingLocation(
                "[北京市]配送员正在配送途中", "北京市", 
                "116.401128", "39.920527", Constants.LOCATION_STATUS_DELIVERING,
                System.currentTimeMillis() / 1000 - 300  // 5分钟前
            );
            
            LocationChangeEntity.Location location3 = createTrackingLocation(
                "[北京市]配送员即将到达目的地", "北京市",
                "116.403128", "39.922527", Constants.LOCATION_STATUS_DELIVERING,
                System.currentTimeMillis() / 1000  // 现在
            );
            
            request.setLocations(Arrays.asList(location1, location2, location3));
            
            ApiResponse<Void> response = ApiClientUtils.requestWithEmptyResponse(
                httpClient, "POST", Constants.LOCATION_CHANGE_PATH,
                request, token, null
            );
            
            if (response.isSuccess()) {
                System.out.println("   ✅ 轨迹信息回传成功");
                System.out.println("   📍 回传轨迹点数: " + request.getLocations().size());
                request.getLocations().forEach(location -> 
                    System.out.println("      - " + location.getDescription())
                );
                return true;
                
            } else {
                System.err.println("   ❌ 轨迹回传失败: " + response.getMessage());
                return false;
            }
            
        } catch (JsonProcessingException e) {
            System.err.println("   ❌ JSON解析异常: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("   ❌ 轨迹回传异常: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 第6步：配送完成
     * 
     * @param httpClient HTTP客户端
     * @param token 访问令牌
     * @return 是否成功完成配送
     */
    private boolean step6_completeDelivery(HttpClientManager httpClient, String token) {
        System.out.println("\n✅ 第6步：配送完成");
        
        return updateDeliveryStatus(httpClient, token,
            Constants.DELIVERY_STATUS_DONE, "配送完成",
            "116.405128", "39.924527", "客户已签收，配送完成");
    }
    
    /**
     * 更新配送状态的辅助方法
     * 
     * @param httpClient HTTP客户端
     * @param token 访问令牌
     * @param status 配送状态
     * @param description 状态描述
     * @param longitude 经度
     * @param latitude 纬度
     * @param remark 备注
     * @return 是否更新成功
     */
    private boolean updateDeliveryStatus(HttpClientManager httpClient, String token,
                                       String status, String description, 
                                       String longitude, String latitude, String remark) {
        try {
            DeliveryChangeEntity.Req request = new DeliveryChangeEntity.Req();
            request.setOrderNo(ORDER_NO);
            request.setSourceOrderNo(SOURCE_ORDER_NO);
            request.setShopId(SHOP_ID);
            request.setStatus(status);
            request.setRiderName("张三");
            request.setRiderPhone("13888888888");
            request.setLongitude(longitude);
            request.setLatitude(latitude);
            request.setUpdateTime(System.currentTimeMillis() / 1000);
            
            ApiResponse<Void> response = ApiClientUtils.requestWithEmptyResponse(
                httpClient, "POST", Constants.DELIVERY_CHANGE_PATH,
                request, token, null
            );
            
            if (response.isSuccess()) {
                System.out.println("   ✅ " + description);
                System.out.println("   📍 位置: " + longitude + ", " + latitude);
                if (remark != null && !remark.isEmpty()) {
                    System.out.println("   💬 " + remark);
                }
                return true;
                
            } else {
                System.err.println("   ❌ " + description + "失败: " + response.getMessage());
                return false;
            }
            
        } catch (JsonProcessingException e) {
            System.err.println("   ❌ " + description + "JSON解析异常: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("   ❌ " + description + "异常: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 创建轨迹位置信息
     * 
     * @param description 轨迹描述
     * @param city 城市
     * @param longitude 经度
     * @param latitude 纬度
     * @param status 状态
     * @param timestamp 时间戳
     * @return 轨迹位置对象
     */
    private LocationChangeEntity.Location createTrackingLocation(String description, String city,
                                                                String longitude, String latitude,
                                                                String status, long timestamp) {
        LocationChangeEntity.Location location = new LocationChangeEntity.Location();
        location.setDescription(description);
        location.setCity(city);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setStatus(status);
        location.setUpdateTime(timestamp);
        return location;
    }
    
    /**
     * 模拟延迟
     * 
     * @param milliseconds 延迟毫秒数
     * @param message 延迟期间显示的消息
     */
    private void simulateDelay(long milliseconds, String message) {
        try {
            System.out.println("   ⏳ " + message);
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * 掩码敏感数据
     * 
     * @param data 敏感数据
     * @return 掩码后的数据
     */
    private String maskSensitiveData(String data) {
        if (data == null || data.length() <= 8) {
            return "***";
        }
        return data.substring(0, 4) + "****" + data.substring(data.length() - 4);
    }
}