/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.client.ConfigValidationException;
import com.maiyatian.delivery.sdk.client.HttpClientConfig;
import com.maiyatian.delivery.sdk.models.sender.api.DeliverySender;
import com.maiyatian.delivery.sdk.models.sender.api.IDeliverySender;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.AccessTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.auth.RefreshTokenEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.delivery.DeliveryChangeEntity;
import com.maiyatian.delivery.sdk.models.sender.entity.express.LocationChangeEntity;
import com.maiyatian.delivery.sdk.models.types.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 快速开始示例
 * <p>
 * 展示SDK的基本使用方法
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class QuickStartExample {
    
    public static void main(String[] args) {
        // 1. 初始化配置
        try {
            HttpClientConfig config = HttpClientConfig.newBuilder()
                    .baseUrl(Constants.TEST_BASE_URL) // 测试环境
                    .apiKey("your_app_key") // 替换为你的app_key
                    .apiSecret("your_app_secret") // 替换为你的app_secret
                    .requestTimeout(java.time.Duration.ofSeconds(30))
                    .retryMaxAttempts(3)
                    .enableLogging(true)
                    .build();
            
            // 2. 创建配送发送者实例
            try (IDeliverySender sender = new DeliverySender(config)) {

                
                // 3. 调用API获取访问令牌
                System.out.println("=== 获取访问令牌 ===");
                AccessTokenEntity.Req accessTokenReq = new AccessTokenEntity.Req();
                accessTokenReq.setGrantType("1"); // 1: 门店授权
                accessTokenReq.setCode("your_auth_code"); // 替换为你的授权码
                accessTokenReq.setMobile("13800138000"); // 替换为你的手机号
                accessTokenReq.setStoreId("your_store_id"); // 替换为你的门店ID
                accessTokenReq.setCity("北京市");
                accessTokenReq.setCityCode("10001");
                
                AccessTokenEntity.Resp accessTokenResp = sender.accessToken(
                        "", // 首次获取令牌时不需要token
                        accessTokenReq
                );
                
                System.out.println("获取访问令牌成功");
                System.out.println("响应数据: token=" + accessTokenResp.getToken() + ", refreshToken=" + accessTokenResp.getRefreshToken());
                
                String token = accessTokenResp.getToken();
                String refreshToken = accessTokenResp.getRefreshToken();
                
                // 4. 调用API刷新访问令牌
                System.out.println("\n=== 刷新访问令牌 ===");
                RefreshTokenEntity.Req refreshTokenReq = new RefreshTokenEntity.Req();
                refreshTokenReq.setToken(token);
                refreshTokenReq.setRefreshToken(refreshToken);
                
                RefreshTokenEntity.Resp refreshTokenResp = sender.refreshToken(
                        token,
                        refreshTokenReq
                );
                
                System.out.println("刷新访问令牌成功");
                System.out.println("响应数据: newToken=" + refreshTokenResp.getToken() + ", newRefreshToken=" + refreshTokenResp.getRefreshToken());
                
                // 5. 调用API同步配送状态
                System.out.println("\n=== 同步配送状态 ===");
                DeliveryChangeEntity.Req deliveryChangeReq = new DeliveryChangeEntity.Req();
                deliveryChangeReq.setOrderNo("1234567890");
                deliveryChangeReq.setSourceOrderNo("9876543210");
                deliveryChangeReq.setShopId("your_shop_id");
                deliveryChangeReq.setStatus(Constants.DELIVERY_STATUS_DELIVERING); // 配送中
                deliveryChangeReq.setRiderName("林骑手");
                deliveryChangeReq.setRiderPhone("13888888888_1234");
                deliveryChangeReq.setLongitude("103.11111");
                deliveryChangeReq.setLatitude("90.1123123");
                deliveryChangeReq.setUpdateTime(System.currentTimeMillis() / 1000);
                
                sender.deliveryChange(
                        token,
                        deliveryChangeReq
                );
                
                System.out.println("同步配送状态成功");
                
                // 6. 调用API回传快递轨迹
                System.out.println("\n=== 回传快递轨迹 ===");
                LocationChangeEntity.Req locationChangeReq = new LocationChangeEntity.Req();
                locationChangeReq.setOrderNo("1234567890");
                locationChangeReq.setSourceOrderNo("9876543210");
                locationChangeReq.setShopId("your_shop_id");
                
                // 添加轨迹点
                List<LocationChangeEntity.Location> locations = new ArrayList<>();
                LocationChangeEntity.Location location = new LocationChangeEntity.Location();
                location.setDescription("[合肥市]快件已到达【安徽合肥转运中心】");
                location.setCity("合肥市");
                location.setLongitude("117.227231");
                location.setLatitude("31.820571");
                location.setStatus(Constants.DELIVERY_STATUS_DELIVERING);
                location.setUpdateTime(System.currentTimeMillis() / 1000);
                locations.add(location);
                
                locationChangeReq.setLocations(locations);
                
                sender.locationChange(
                        token,
                        locationChangeReq
                );
                
                System.out.println("回传快递轨迹成功");
            } catch (ConfigValidationException e) {
                System.err.println("配置验证失败: " + e.getMessage());
            } catch (IOException e) {
                // JsonProcessingException 是 IOException 的子类，会被这个 catch 块捕获
                if (e instanceof JsonProcessingException) {
                    System.err.println("JSON处理失败: " + e.getMessage());
                } else {
                    System.err.println("HTTP请求失败: " + e.getMessage());
                }
                e.printStackTrace();
            }
        } catch (ConfigValidationException e) {
            System.err.println("配置构建失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
