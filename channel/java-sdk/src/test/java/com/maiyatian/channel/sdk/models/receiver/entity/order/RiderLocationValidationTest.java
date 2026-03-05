package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RiderLocation实体类验证测试
 */
@DisplayName("RiderLocation和MultiRiderLocationReq实体类测试")
class RiderLocationValidationTest {

    private final ObjectMapper objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();

    @Nested
    @DisplayName("RiderLocation基本功能测试")
    class RiderLocationBasicFunctionalityTests {
        
        @Test
        @DisplayName("测试基本RiderLocation创建")
        void testBasicRiderLocationCreation() {
            // 测试基本的RiderLocation创建
            RiderLocation location = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            assertNotNull(location, "RiderLocation创建失败");
            assertEquals("ORDER_001", location.getOrderId(), "订单ID不匹配");
            assertEquals("SHOP_001", location.getShopId(), "门店ID不匹配");
            assertEquals("张三", location.getRiderName(), "骑手姓名不匹配");
            assertTrue(location.isValid(), "RiderLocation验证失败");
            assertFalse(location.hasLocation(), "不应该有位置信息");
            assertFalse(location.hasDistance(), "不应该有距离信息");
        }
        
        @Test
        @DisplayName("测试带坐标的RiderLocation")
        void testRiderLocationWithCoordinates() {
            // 测试带坐标的RiderLocation
            RiderLocation locationWithCoords = RiderLocation.withLocation(
                "ORDER_002", "SHOP_002", "DELIVERING",
                "李四", "13900139000", "LOG_002", "TAG_002",
                "116.397477", "39.916668", System.currentTimeMillis() / 1000
            );
            
            assertTrue(locationWithCoords.hasLocation(), "应该有位置信息");
            assertEquals("经度: 116.397477, 纬度: 39.916668", locationWithCoords.getFormattedLocation(), "格式化位置信息不匹配");
            assertFalse(locationWithCoords.isExpressDelivery(), "不应该是快递配送");
            assertEquals("同城配送", locationWithCoords.getDeliveryTypeDescription(), "配送类型描述不匹配");
        }
    }

    @Nested
    @DisplayName("RiderLocation JSON序列化测试")
    class RiderLocationJsonSerializationTests {
        
        @Test
        @DisplayName("测试RiderLocation JSON序列化和反序列化")
        void testRiderLocationJsonSerialization() throws Exception {
            // 创建测试对象
            RiderLocation location = RiderLocation.of(
                "ORDER_001", "SHOP_001", "DELIVERING", "张三", "13800138000",
                "LOG_001", "TAG_001", "116.397477", "39.916668", 1500,
                false, System.currentTimeMillis() / 1000
            );
            
            // 序列化
            String json = objectMapper.writeValueAsString(location);
            assertTrue(json.contains("ORDER_001"), "JSON序列化缺少订单ID");
            assertTrue(json.contains("张三"), "JSON序列化缺少骑手姓名");
            assertTrue(json.contains("116.397477"), "JSON序列化缺少经度");
            
            // 反序列化
            RiderLocation deserialized = objectMapper.readValue(json, RiderLocation.class);
            assertEquals(location, deserialized, "JSON反序列化后对象不相等");
        }
    }

    @Nested
    @DisplayName("MultiRiderLocationReq测试")
    class MultiRiderLocationReqTests {
        
        @Test
        @DisplayName("测试MultiRiderLocationReq基本功能")
        void testMultiRiderLocationReqBasicFunctionality() {
            // 创建骑手位置信息
            RiderLocation location1 = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            RiderLocation location2 = RiderLocation.basic(
                "ORDER_002", "SHOP_001", "PICKED_UP", 
                "李四", "13900139000", "LOG_002", "TAG_002"
            );

            // 测试MultiRiderLocationReq
            MultiRiderLocationReq request = MultiRiderLocationReq.empty();
            assertTrue(request.isEmpty(), "新建的请求应该为空");
            assertEquals(0, request.getLocationCount(), "新建的请求位置数应为0");
            
            request.addLocation(location1);
            request.addLocation(location2);
            
            assertFalse(request.isEmpty(), "添加位置后不应为空");
            assertEquals(2, request.getLocationCount(), "位置数应为2");
            assertTrue(request.hasLocations(), "应该有位置信息");
            assertTrue(request.isAllLocationsValid(), "所有位置应该有效");
        }
        
        @Test
        @DisplayName("测试MultiRiderLocationReq构建器模式")
        void testMultiRiderLocationReqBuilder() {
            // 创建骑手位置信息
            RiderLocation location1 = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            RiderLocation location2 = RiderLocation.basic(
                "ORDER_002", "SHOP_001", "PICKED_UP", 
                "李四", "13900139000", "LOG_002", "TAG_002"
            );
            
            // 测试构建器模式
            MultiRiderLocationReq builtRequest = MultiRiderLocationReq.builder()
                .addLocation(location1)
                .addLocation(location2)
                .build();
            
            assertEquals(2, builtRequest.getLocationCount(), "构建器创建的请求位置数应为2");
            assertNotNull(builtRequest.findByOrderId("ORDER_001"), "应该能找到ORDER_001");
            assertNotNull(builtRequest.findByOrderId("ORDER_002"), "应该能找到ORDER_002");
            assertNull(builtRequest.findByOrderId("ORDER_999"), "不应该找到ORDER_999");
            
            assertEquals(2, builtRequest.findByShopId("SHOP_001").size(), "SHOP_001应该有2个位置");
            assertEquals(0, builtRequest.findByShopId("SHOP_999").size(), "SHOP_999应该有0个位置");
        }
        
        @Test
        @DisplayName("测试MultiRiderLocationReq JSON序列化")
        void testMultiRiderLocationReqJsonSerialization() throws Exception {
            // 创建骑手位置信息
            RiderLocation location1 = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            RiderLocation location2 = RiderLocation.basic(
                "ORDER_002", "SHOP_001", "PICKED_UP", 
                "李四", "13900139000", "LOG_002", "TAG_002"
            );
            
            MultiRiderLocationReq builtRequest = MultiRiderLocationReq.builder()
                .addLocation(location1)
                .addLocation(location2)
                .build();
            
            // 测试JSON序列化
            String json = objectMapper.writeValueAsString(builtRequest);
            assertTrue(json.contains("locations"), "JSON应包含locations字段");
            assertTrue(json.contains("ORDER_001"), "JSON应包含ORDER_001");
            assertTrue(json.contains("ORDER_002"), "JSON应包含ORDER_002");
            
            // 测试JSON反序列化
            MultiRiderLocationReq deserializedRequest = objectMapper.readValue(json, MultiRiderLocationReq.class);
            assertEquals(builtRequest.getLocationCount(), deserializedRequest.getLocationCount(), "反序列化后位置数应相等");
        }
    }
}