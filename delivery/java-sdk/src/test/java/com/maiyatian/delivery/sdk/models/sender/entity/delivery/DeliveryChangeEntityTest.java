/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 * DeliveryChangeEntity 单元测试
 * <p>
 * 测试配送状态变更实体类的JSON序列化和反序列化功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class DeliveryChangeEntityTest {

    @Test
    void testDeliveryChangeEntitySerialization() throws IOException {
        // 创建请求对象
        DeliveryChangeEntity.Req req = new DeliveryChangeEntity.Req();
        req.setOrderNo("116157133388249348");
        req.setSourceOrderNo("11667336008123456");
        req.setShopId("123");
        req.setStatus("DELIVERING");
        req.setRiderName("林骑手");
        req.setRiderPhone("13888888888_1234");
        req.setLongitude("103.11111");
        req.setLatitude("90.1123123");
        req.setPickupCode("取货码");
        req.setDistance(1000);
        req.setDeliveryFee(850);
        req.setCancelType(1);
        req.setCancelReason("取消原因");
        req.setCancelDeditAmount(200);
        req.setUpdateTime(System.currentTimeMillis() / 1000);
        req.setTransship(false);
        
        // 创建车辆信息
        DeliveryChangeEntity.Req.VehicleInfo vehicleInfo = new DeliveryChangeEntity.Req.VehicleInfo();
        vehicleInfo.setVehicleName("雪铁龙C5");
        vehicleInfo.setVehicleColor("蓝色");
        vehicleInfo.setVehicleNo("冀E4WE32");
        req.setVehicleInfo(vehicleInfo);
        
        // 序列化并反序列化
        String json = JsonUtils.toJson(req);
        DeliveryChangeEntity.Req deserialized = JsonUtils.fromJson(json, DeliveryChangeEntity.Req.class);
        
        // 验证字段值
        assertEquals(req.getOrderNo(), deserialized.getOrderNo());
        assertEquals(req.getSourceOrderNo(), deserialized.getSourceOrderNo());
        assertEquals(req.getShopId(), deserialized.getShopId());
        assertEquals(req.getStatus(), deserialized.getStatus());
        assertEquals(req.getRiderName(), deserialized.getRiderName());
        assertEquals(req.getRiderPhone(), deserialized.getRiderPhone());
        assertEquals(req.isTransship(), deserialized.isTransship());
        assertEquals(req.getVehicleInfo().getVehicleName(), deserialized.getVehicleInfo().getVehicleName());
        assertEquals(req.getVehicleInfo().getVehicleColor(), deserialized.getVehicleInfo().getVehicleColor());
        assertEquals(req.getVehicleInfo().getVehicleNo(), deserialized.getVehicleInfo().getVehicleNo());
    }
}