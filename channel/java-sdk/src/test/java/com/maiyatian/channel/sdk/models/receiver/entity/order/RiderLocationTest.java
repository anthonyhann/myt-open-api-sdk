/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RiderLocation 骑手位置信息和 MultiRiderLocationReq 批量请求单元测试
 * 
 * 测试覆盖范围：
 * 1. RiderLocation 构造函数测试
 * 2. RiderLocation Getter/Setter 方法测试
 * 3. RiderLocation equals/hashCode/toString 方法测试
 * 4. RiderLocation 业务逻辑方法测试
 * 5. RiderLocation JSON 序列化/反序列化测试
 * 6. RiderLocation 静态工厂方法测试
 * 7. MultiRiderLocationReq 完整功能测试
 * 8. 边界条件测试
 */
@DisplayName("RiderLocation 骑手位置信息测试")
public class RiderLocationTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("RiderLocation 构造函数测试")
    class RiderLocationConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            RiderLocation location = new RiderLocation();
            
            assertNotNull(location);
            assertNull(location.getOrderId());
            assertNull(location.getShopId());
            assertNull(location.getStatus());
            assertNull(location.getRiderName());
            assertNull(location.getRiderPhone());
            assertNull(location.getLogisticNo());
            assertNull(location.getLogisticTag());
            assertNull(location.getLongitude());
            assertNull(location.getLatitude());
            assertNull(location.getDistance());
            assertNull(location.getIsExpress());
            assertNull(location.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            Long currentTime = System.currentTimeMillis() / 1000;
            RiderLocation location = new RiderLocation(
                "ORDER_001", "SHOP_001", "DELIVERING",
                "张三", "13800138000", "LOG_001", "TAG_001",
                "116.397477", "39.916668", 500,
                false, currentTime
            );
            
            assertNotNull(location);
            assertEquals("ORDER_001", location.getOrderId());
            assertEquals("SHOP_001", location.getShopId());
            assertEquals("DELIVERING", location.getStatus());
            assertEquals("张三", location.getRiderName());
            assertEquals("13800138000", location.getRiderPhone());
            assertEquals("LOG_001", location.getLogisticNo());
            assertEquals("TAG_001", location.getLogisticTag());
            assertEquals("116.397477", location.getLongitude());
            assertEquals("39.916668", location.getLatitude());
            assertEquals(Integer.valueOf(500), location.getDistance());
            assertEquals(false, location.getIsExpress());
            assertEquals(currentTime, location.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("RiderLocation Getter/Setter 方法测试")
    class RiderLocationGetterSetterTests {

        @Test
        @DisplayName("基本字段设置和获取")
        void testBasicFieldsGetterSetter() {
            RiderLocation location = new RiderLocation();
            
            location.setOrderId("ORDER_123");
            location.setShopId("SHOP_123");
            location.setStatus("PICKED_UP");
            location.setRiderName("王五");
            location.setRiderPhone("13700137000");
            location.setLogisticNo("LOG_123");
            location.setLogisticTag("TAG_123");
            
            assertEquals("ORDER_123", location.getOrderId());
            assertEquals("SHOP_123", location.getShopId());
            assertEquals("PICKED_UP", location.getStatus());
            assertEquals("王五", location.getRiderName());
            assertEquals("13700137000", location.getRiderPhone());
            assertEquals("LOG_123", location.getLogisticNo());
            assertEquals("TAG_123", location.getLogisticTag());
        }

        @Test
        @DisplayName("位置和配送字段设置和获取")
        void testLocationAndDeliveryFieldsGetterSetter() {
            RiderLocation location = new RiderLocation();
            Long updateTime = System.currentTimeMillis() / 1000;
            
            location.setLongitude("121.473701");
            location.setLatitude("31.230416");
            location.setDistance(1200);
            location.setIsExpress(true);
            location.setUpdateTime(updateTime);
            
            assertEquals("121.473701", location.getLongitude());
            assertEquals("31.230416", location.getLatitude());
            assertEquals(Integer.valueOf(1200), location.getDistance());
            assertEquals(true, location.getIsExpress());
            assertEquals(updateTime, location.getUpdateTime());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            RiderLocation location = createValidRiderLocation();
            
            location.setOrderId(null);
            location.setShopId(null);
            location.setStatus(null);
            location.setRiderName(null);
            location.setRiderPhone(null);
            location.setLogisticNo(null);
            location.setLogisticTag(null);
            location.setLongitude(null);
            location.setLatitude(null);
            location.setDistance(null);
            location.setIsExpress(null);
            location.setUpdateTime(null);
            
            assertNull(location.getOrderId());
            assertNull(location.getShopId());
            assertNull(location.getStatus());
            assertNull(location.getRiderName());
            assertNull(location.getRiderPhone());
            assertNull(location.getLogisticNo());
            assertNull(location.getLogisticTag());
            assertNull(location.getLongitude());
            assertNull(location.getLatitude());
            assertNull(location.getDistance());
            assertNull(location.getIsExpress());
            assertNull(location.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("RiderLocation Object方法测试")
    class RiderLocationObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            RiderLocation location1 = createValidRiderLocation();
            RiderLocation location2 = createValidRiderLocation();
            
            assertEquals(location1, location2);
            assertEquals(location1, location1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            RiderLocation location1 = createValidRiderLocation();
            RiderLocation location2 = createValidRiderLocation();
            location2.setOrderId("DIFFERENT_ORDER");
            
            RiderLocation location3 = createValidRiderLocation();
            location3.setRiderName("不同骑手");
            
            RiderLocation location4 = createValidRiderLocation();
            location4.setDistance(999);
            
            assertNotEquals(location1, location2);
            assertNotEquals(location1, location3);
            assertNotEquals(location1, location4);
            assertNotEquals(location1, null);
            assertNotEquals(location1, "not a RiderLocation");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            RiderLocation location1 = createValidRiderLocation();
            RiderLocation location2 = createValidRiderLocation();
            RiderLocation location3 = createValidRiderLocation();
            location3.setOrderId("DIFFERENT_ORDER");
            
            assertEquals(location1.hashCode(), location2.hashCode());
            assertNotEquals(location1.hashCode(), location3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            RiderLocation location = createValidRiderLocation();
            
            String result = location.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_001"));
            assertTrue(result.contains("张三"));
            assertTrue(result.contains("RiderLocation"));
            assertTrue(result.contains("SHOP_001"));
        }
    }

    @Nested
    @DisplayName("RiderLocation 业务逻辑方法测试")
    class RiderLocationBusinessLogicTests {

        @Test
        @DisplayName("isValid方法测试 - 有效数据")
        void testIsValid_ValidData() {
            RiderLocation location = createValidRiderLocation();
            assertTrue(location.isValid());
        }

        @Test
        @DisplayName("isValid方法测试 - 无效数据")
        void testIsValid_InvalidData() {
            // 测试null值
            assertFalse(new RiderLocation().isValid());
            
            RiderLocation location = new RiderLocation();
            location.setOrderId("ORDER_001");
            assertFalse(location.isValid()); // 缺少其他必要字段
            
            // 测试空字符串
            location = createValidRiderLocation();
            location.setOrderId("");
            assertFalse(location.isValid());
            
            location = createValidRiderLocation();
            location.setRiderName("   ");
            assertFalse(location.isValid());
            
            location = createValidRiderLocation();
            location.setRiderPhone(null);
            assertFalse(location.isValid());
        }

        @Test
        @DisplayName("hasLocation方法测试")
        void testHasLocation() {
            RiderLocation location = new RiderLocation();
            
            // 没有坐标
            assertFalse(location.hasLocation());
            
            // 只有经度
            location.setLongitude("116.397477");
            assertFalse(location.hasLocation());
            
            // 有完整坐标
            location.setLatitude("39.916668");
            assertTrue(location.hasLocation());
            
            // 测试空字符串
            location.setLongitude("");
            assertFalse(location.hasLocation());
            
            // 测试空白字符
            location.setLongitude("   ");
            location.setLatitude("   ");
            assertFalse(location.hasLocation());
        }

        @Test
        @DisplayName("hasDistance方法测试")
        void testHasDistance() {
            RiderLocation location = new RiderLocation();
            
            // 没有距离
            assertFalse(location.hasDistance());
            
            // 有距离
            location.setDistance(500);
            assertTrue(location.hasDistance());
            
            // 零距离
            location.setDistance(0);
            assertTrue(location.hasDistance());
            
            // 负距离
            location.setDistance(-100);
            assertFalse(location.hasDistance());
            
            // null距离
            location.setDistance(null);
            assertFalse(location.hasDistance());
        }

        @Test
        @DisplayName("getFormattedLocation方法测试")
        void testGetFormattedLocation() {
            RiderLocation location = new RiderLocation();
            
            // 没有坐标
            assertEquals("位置信息不可用", location.getFormattedLocation());
            
            // 有坐标
            location.setLongitude("116.397477");
            location.setLatitude("39.916668");
            assertEquals("经度: 116.397477, 纬度: 39.916668", location.getFormattedLocation());
        }

        @Test
        @DisplayName("getFormattedDistance方法测试")
        void testGetFormattedDistance() {
            RiderLocation location = new RiderLocation();
            
            // 没有距离
            assertEquals("距离不可用", location.getFormattedDistance());
            
            // 小于1000米
            location.setDistance(500);
            assertEquals("500米", location.getFormattedDistance());
            
            // 等于1000米
            location.setDistance(1000);
            assertEquals("1.0公里", location.getFormattedDistance());
            
            // 大于1000米
            location.setDistance(1500);
            assertEquals("1.5公里", location.getFormattedDistance());
            
            // 复杂小数
            location.setDistance(1234);
            assertEquals("1.2公里", location.getFormattedDistance());
            
            // 零距离
            location.setDistance(0);
            assertEquals("0米", location.getFormattedDistance());
        }

        @Test
        @DisplayName("isExpressDelivery方法测试")
        void testIsExpressDelivery() {
            RiderLocation location = new RiderLocation();
            
            // null值
            assertFalse(location.isExpressDelivery());
            
            // false值
            location.setIsExpress(false);
            assertFalse(location.isExpressDelivery());
            
            // true值
            location.setIsExpress(true);
            assertTrue(location.isExpressDelivery());
        }

        @Test
        @DisplayName("getDeliveryTypeDescription方法测试")
        void testGetDeliveryTypeDescription() {
            RiderLocation location = new RiderLocation();
            
            // 默认同城配送
            assertEquals("同城配送", location.getDeliveryTypeDescription());
            
            // 同城配送
            location.setIsExpress(false);
            assertEquals("同城配送", location.getDeliveryTypeDescription());
            
            // 快递配送
            location.setIsExpress(true);
            assertEquals("快递配送", location.getDeliveryTypeDescription());
        }
    }

    @Nested
    @DisplayName("RiderLocation 静态工厂方法测试")
    class RiderLocationStaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试")
        void testOfFactoryMethod() {
            Long updateTime = System.currentTimeMillis() / 1000;
            RiderLocation location = RiderLocation.of(
                "ORDER_001", "SHOP_001", "DELIVERING",
                "张三", "13800138000", "LOG_001", "TAG_001",
                "116.397477", "39.916668", 500,
                false, updateTime
            );
            
            assertNotNull(location);
            assertEquals("ORDER_001", location.getOrderId());
            assertEquals("SHOP_001", location.getShopId());
            assertEquals("DELIVERING", location.getStatus());
            assertEquals("张三", location.getRiderName());
            assertEquals("13800138000", location.getRiderPhone());
            assertEquals("LOG_001", location.getLogisticNo());
            assertEquals("TAG_001", location.getLogisticTag());
            assertEquals("116.397477", location.getLongitude());
            assertEquals("39.916668", location.getLatitude());
            assertEquals(Integer.valueOf(500), location.getDistance());
            assertEquals(false, location.getIsExpress());
            assertEquals(updateTime, location.getUpdateTime());
            assertTrue(location.isValid());
            assertTrue(location.hasLocation());
            assertTrue(location.hasDistance());
        }

        @Test
        @DisplayName("basic静态工厂方法测试")
        void testBasicFactoryMethod() {
            RiderLocation location = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            assertNotNull(location);
            assertEquals("ORDER_001", location.getOrderId());
            assertEquals("SHOP_001", location.getShopId());
            assertEquals("张三", location.getRiderName());
            assertTrue(location.isValid());
            assertFalse(location.hasLocation());
            assertFalse(location.hasDistance());
            assertNull(location.getLongitude());
            assertNull(location.getLatitude());
            assertNull(location.getDistance());
            assertNull(location.getIsExpress());
            assertNull(location.getUpdateTime());
        }

        @Test
        @DisplayName("withLocation静态工厂方法测试")
        void testWithLocationFactoryMethod() {
            Long updateTime = System.currentTimeMillis() / 1000;
            RiderLocation location = RiderLocation.withLocation(
                "ORDER_002", "SHOP_002", "DELIVERING",
                "李四", "13900139000", "LOG_002", "TAG_002",
                "116.397477", "39.916668", updateTime
            );
            
            assertNotNull(location);
            assertEquals("ORDER_002", location.getOrderId());
            assertEquals("李四", location.getRiderName());
            assertTrue(location.hasLocation());
            assertEquals("经度: 116.397477, 纬度: 39.916668", location.getFormattedLocation());
            assertFalse(location.isExpressDelivery());
            assertEquals("同城配送", location.getDeliveryTypeDescription());
            assertEquals(updateTime, location.getUpdateTime());
            assertNull(location.getDistance());
            assertNull(location.getIsExpress());
        }
    }

    @Nested
    @DisplayName("RiderLocation JSON序列化测试")
    class RiderLocationJsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            RiderLocation location = createValidRiderLocation();
            
            String json = objectMapper.writeValueAsString(location);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_001\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_001\""));
            assertTrue(json.contains("\"rider_name\":\"张三\""));
            assertTrue(json.contains("\"rider_phone\":\"13800138000\""));
            assertTrue(json.contains("\"longitude\":\"116.397477\""));
            assertTrue(json.contains("\"latitude\":\"39.916668\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{" +
                "\"order_id\":\"ORDER_001\"," +
                "\"shop_id\":\"SHOP_001\"," +
                "\"status\":\"DELIVERING\"," +
                "\"rider_name\":\"张三\"," +
                "\"rider_phone\":\"13800138000\"," +
                "\"logistic_no\":\"LOG_001\"," +
                "\"logistic_tag\":\"TAG_001\"," +
                "\"longitude\":\"116.397477\"," +
                "\"latitude\":\"39.916668\"," +
                "\"distance\":500," +
                "\"is_express\":false," +
                "\"update_time\":1640995200" +
                "}";
            
            RiderLocation location = objectMapper.readValue(json, RiderLocation.class);
            
            assertNotNull(location);
            assertEquals("ORDER_001", location.getOrderId());
            assertEquals("SHOP_001", location.getShopId());
            assertEquals("DELIVERING", location.getStatus());
            assertEquals("张三", location.getRiderName());
            assertEquals("13800138000", location.getRiderPhone());
            assertEquals("LOG_001", location.getLogisticNo());
            assertEquals("TAG_001", location.getLogisticTag());
            assertEquals("116.397477", location.getLongitude());
            assertEquals("39.916668", location.getLatitude());
            assertEquals(Integer.valueOf(500), location.getDistance());
            assertEquals(false, location.getIsExpress());
            assertEquals(Long.valueOf(1640995200L), location.getUpdateTime());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            RiderLocation original = createValidRiderLocation();
            
            String json = objectMapper.writeValueAsString(original);
            RiderLocation deserialized = objectMapper.readValue(json, RiderLocation.class);
            
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("MultiRiderLocationReq 测试")
    class MultiRiderLocationReqTests {

        @Test
        @DisplayName("MultiRiderLocationReq 基本功能测试")
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
            assertTrue(request.isEmpty());
            assertEquals(0, request.getLocationCount());
            
            request.addLocation(location1);
            request.addLocation(location2);
            
            assertFalse(request.isEmpty());
            assertEquals(2, request.getLocationCount());
            assertTrue(request.hasLocations());
            assertTrue(request.isAllLocationsValid());
        }

        @Test
        @DisplayName("MultiRiderLocationReq 构建器测试")
        void testMultiRiderLocationReqBuilder() {
            // 测试构建器模式
            RiderLocation location1 = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            RiderLocation location2 = RiderLocation.basic(
                "ORDER_002", "SHOP_001", "PICKED_UP", 
                "李四", "13900139000", "LOG_002", "TAG_002"
            );

            MultiRiderLocationReq request = MultiRiderLocationReq.builder()
                .addLocation(location1)
                .addLocation(location2)
                .build();
            
            assertEquals(2, request.getLocationCount());
            assertNotNull(request.findByOrderId("ORDER_001"));
            assertNotNull(request.findByOrderId("ORDER_002"));
            assertNull(request.findByOrderId("ORDER_999"));
            
            assertEquals(2, request.findByShopId("SHOP_001").size());
            assertEquals(0, request.findByShopId("SHOP_999").size());
        }

        @Test
        @DisplayName("MultiRiderLocationReq 静态工厂方法测试")
        void testMultiRiderLocationReqStaticFactoryMethods() {
            RiderLocation location = createValidRiderLocation();
            
            // 测试single
            MultiRiderLocationReq singleRequest = MultiRiderLocationReq.single(location);
            assertEquals(1, singleRequest.getLocationCount());
            assertEquals(location, singleRequest.findByOrderId("ORDER_001"));
            
            // 测试of (varargs)
            RiderLocation location2 = RiderLocation.basic(
                "ORDER_002", "SHOP_002", "DELIVERING", 
                "李四", "13900139000", "LOG_002", "TAG_002"
            );
            
            MultiRiderLocationReq multiRequest = MultiRiderLocationReq.of(location, location2);
            assertEquals(2, multiRequest.getLocationCount());
            
            // 测试empty
            MultiRiderLocationReq emptyRequest = MultiRiderLocationReq.empty();
            assertTrue(emptyRequest.isEmpty());
        }

        @Test
        @DisplayName("MultiRiderLocationReq 查询方法测试")
        void testMultiRiderLocationReqQueryMethods() {
            RiderLocation location1 = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            
            RiderLocation location2 = RiderLocation.withLocation(
                "ORDER_002", "SHOP_001", "PICKED_UP", 
                "李四", "13900139000", "LOG_002", "TAG_002",
                "116.397477", "39.916668", System.currentTimeMillis() / 1000
            );
            
            MultiRiderLocationReq request = MultiRiderLocationReq.of(location1, location2);
            
            // 测试getValidLocations
            assertEquals(2, request.getValidLocations().size());
            
            // 测试getLocationsWithCoordinates
            assertEquals(1, request.getLocationsWithCoordinates().size());
            assertEquals(location2, request.getLocationsWithCoordinates().get(0));
            
            // 测试getImmutableLocations
            assertNotNull(request.getImmutableLocations());
            assertEquals(2, request.getImmutableLocations().size());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("RiderLocation 验证测试")
        void testRiderLocationValidation() {
            // 测试验证功能
            RiderLocation validLocation = RiderLocation.basic(
                "ORDER_001", "SHOP_001", "DELIVERING", 
                "张三", "13800138000", "LOG_001", "TAG_001"
            );
            assertTrue(validLocation.isValid());

            // 测试无效的位置（缺少必要字段）
            RiderLocation invalidLocation = new RiderLocation();
            invalidLocation.setOrderId("ORDER_001");
            // 缺少其他必要字段
            assertFalse(invalidLocation.isValid());
        }

        @Test
        @DisplayName("距离格式化测试")
        void testDistanceFormatting() {
            // 测试距离格式化
            RiderLocation location = new RiderLocation();
            location.setDistance(500);
            assertTrue(location.hasDistance());
            assertEquals("500米", location.getFormattedDistance());
            
            location.setDistance(1500);
            assertEquals("1.5公里", location.getFormattedDistance());
            
            location.setDistance(null);
            assertFalse(location.hasDistance());
            assertEquals("距离不可用", location.getFormattedDistance());
        }

        @Test
        @DisplayName("超长字符串测试")
        void testVeryLongStrings() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
            String longString = sb.toString();
            
            RiderLocation location = new RiderLocation();
            location.setOrderId(longString);
            location.setShopId(longString);
            location.setRiderName(longString);
            location.setStatus("DELIVERING");
            location.setRiderPhone("13800138000");
            location.setLogisticNo("LOG_001");
            location.setLogisticTag("TAG_001");
            
            assertEquals(longString, location.getOrderId());
            assertEquals(longString, location.getShopId());
            assertEquals(longString, location.getRiderName());
            assertTrue(location.isValid());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialChars = "测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            
            RiderLocation location = RiderLocation.basic(
                specialChars, specialChars, "DELIVERING",
                specialChars, "13800138000", "LOG_001", "TAG_001"
            );
            
            assertEquals(specialChars, location.getOrderId());
            assertEquals(specialChars, location.getShopId());
            assertEquals(specialChars, location.getRiderName());
            assertTrue(location.isValid());
        }

        @Test
        @DisplayName("坐标格式测试")
        void testCoordinateFormats() {
            String[] validCoordinates = {
                "0", "0.0", "123.456789", "-123.456789",
                "180.0", "-180.0", "90.0", "-90.0"
            };
            
            for (String coordinate : validCoordinates) {
                RiderLocation location = new RiderLocation();
                location.setLongitude(coordinate);
                location.setLatitude(coordinate);
                
                assertEquals(coordinate, location.getLongitude());
                assertEquals(coordinate, location.getLatitude());
                assertTrue(location.hasLocation());
            }
        }

        @Test
        @DisplayName("MultiRiderLocationReq null处理测试")
        void testMultiRiderLocationReqNullHandling() {
            MultiRiderLocationReq request = MultiRiderLocationReq.empty();
            
            // 添加null位置
            request.addLocation(null);
            assertTrue(request.isEmpty());
            
            // 查找null订单ID
            assertNull(request.findByOrderId(null));
            
            // 查找null门店ID
            assertEquals(0, request.findByShopId(null).size());
        }
    }

    // 辅助方法
    private RiderLocation createValidRiderLocation() {
        return new RiderLocation(
            "ORDER_001", "SHOP_001", "DELIVERING",
            "张三", "13800138000", "LOG_001", "TAG_001",
            "116.397477", "39.916668", 500,
            false, System.currentTimeMillis() / 1000
        );
    }
}