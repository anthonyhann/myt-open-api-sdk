/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.sender.entity.express;

import com.maiyatian.delivery.sdk.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LocationChangeEntity 单元测试
 * <p>
 * 测试快递轨迹回传实体类的JSON序列化和反序列化功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class LocationChangeEntityTest {

    @Test
    public void testLocationChangeRequestSerialization() throws IOException {
        // 创建请求对象
        LocationChangeEntity.Req req = new LocationChangeEntity.Req();
        req.setOrderNo("DPK364322890411");
        req.setSourceOrderNo("12826392824659312640");
        req.setShopId("1214123");
        req.setTag("test_tag");
        
        // 添加轨迹点
        List<LocationChangeEntity.Location> locations = new ArrayList<>();
        LocationChangeEntity.Location location = new LocationChangeEntity.Location();
        location.setDescription("[合肥市]【安徽合肥沐涵公司】的西瓜揽收已揽收");
        location.setCity("合肥市");
        location.setLongitude("117.227231");
        location.setLatitude("31.820571");
        location.setStatus("PICKUP");
        location.setRemark("测试备注");
        location.setUpdateTime(System.currentTimeMillis() / 1000);
        locations.add(location);
        
        LocationChangeEntity.Location location2 = new LocationChangeEntity.Location();
        location2.setDescription("[合肥市]安徽合肥沐涵公司-西瓜揽收-已收件");
        location2.setCity("合肥市");
        location2.setLongitude("117.227231");
        location2.setLatitude("31.820571");
        location2.setStatus("DELIVERING");
        location2.setUpdateTime(System.currentTimeMillis() / 1000);
        locations.add(location2);
        
        req.setLocations(locations);
        
        // 序列化并反序列化
        String json = JsonUtils.toJson(req);
        LocationChangeEntity.Req deserialized = JsonUtils.fromJson(json, LocationChangeEntity.Req.class);
        
        // 验证字段值
        assertEquals(req.getOrderNo(), deserialized.getOrderNo());
        assertEquals(req.getSourceOrderNo(), deserialized.getSourceOrderNo());
        assertEquals(req.getShopId(), deserialized.getShopId());
        assertEquals(req.getTag(), deserialized.getTag());
        assertNotNull(deserialized.getLocations());
        assertEquals(2, deserialized.getLocations().size());
        assertEquals(req.getLocations().get(0).getDescription(), deserialized.getLocations().get(0).getDescription());
        assertEquals(req.getLocations().get(1).getDescription(), deserialized.getLocations().get(1).getDescription());
    }
    

}