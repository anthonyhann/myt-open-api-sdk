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
 * DeliveryChangeReq 配送状态变更请求参数单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. JSON 序列化/反序列化测试
 * 5. 边界条件测试
 */
@DisplayName("DeliveryChangeReq 配送状态变更请求参数测试")
public class DeliveryChangeReqTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            
            assertNotNull(req);
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getStatus());
            assertNull(req.getRiderName());
            assertNull(req.getRiderPhone());
            assertNull(req.getLogisticNo());
            assertNull(req.getLogisticTag());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
            assertEquals(false, req.getIsTransship()); // 默认值
            assertEquals(false, req.getIsExpress());   // 默认值
            assertEquals(0L, req.getDeliveryFee());    // 默认值
            assertNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String orderId = "ORDER_123";
            String shopId = "SHOP_456";
            String status = "DELIVERED";
            String riderName = "张三";
            String riderPhone = "13812345678";
            String logisticNo = "LOG_789";
            String logisticTag = "MEITUAN";
            String longitude = "116.397128";
            String latitude = "39.916527";
            Boolean isTransship = true;
            Boolean isExpress = false;
            Long deliveryFee = 500L;
            Long updateTime = System.currentTimeMillis() / 1000;
            
            DeliveryChangeReq req = new DeliveryChangeReq(orderId, shopId, status, riderName,
                    riderPhone, logisticNo, logisticTag, longitude, latitude, 
                    isTransship, isExpress, deliveryFee, updateTime);
            
            assertNotNull(req);
            assertEquals(orderId, req.getOrderId());
            assertEquals(shopId, req.getShopId());
            assertEquals(status, req.getStatus());
            assertEquals(riderName, req.getRiderName());
            assertEquals(riderPhone, req.getRiderPhone());
            assertEquals(logisticNo, req.getLogisticNo());
            assertEquals(logisticTag, req.getLogisticTag());
            assertEquals(longitude, req.getLongitude());
            assertEquals(latitude, req.getLatitude());
            assertEquals(isTransship, req.getIsTransship());
            assertEquals(isExpress, req.getIsExpress());
            assertEquals(deliveryFee, req.getDeliveryFee());
            assertEquals(updateTime, req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("OrderId 设置和获取")
        void testOrderIdGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String orderId = "TEST_ORDER_789";
            
            req.setOrderId(orderId);
            
            assertEquals(orderId, req.getOrderId());
        }

        @Test
        @DisplayName("ShopId 设置和获取")
        void testShopIdGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String shopId = "TEST_SHOP_123";
            
            req.setShopId(shopId);
            
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("Status 设置和获取")
        void testStatusGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String status = "DISPATCHED";
            
            req.setStatus(status);
            
            assertEquals(status, req.getStatus());
        }

        @Test
        @DisplayName("RiderName 设置和获取")
        void testRiderNameGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String riderName = "李四";
            
            req.setRiderName(riderName);
            
            assertEquals(riderName, req.getRiderName());
        }

        @Test
        @DisplayName("RiderPhone 设置和获取")
        void testRiderPhoneGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String riderPhone = "13987654321";
            
            req.setRiderPhone(riderPhone);
            
            assertEquals(riderPhone, req.getRiderPhone());
        }

        @Test
        @DisplayName("LogisticNo 设置和获取")
        void testLogisticNoGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String logisticNo = "LOG_456";
            
            req.setLogisticNo(logisticNo);
            
            assertEquals(logisticNo, req.getLogisticNo());
        }

        @Test
        @DisplayName("LogisticTag 设置和获取")
        void testLogisticTagGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String logisticTag = "ELEME";
            
            req.setLogisticTag(logisticTag);
            
            assertEquals(logisticTag, req.getLogisticTag());
        }

        @Test
        @DisplayName("Longitude 设置和获取")
        void testLongitudeGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String longitude = "121.473701";
            
            req.setLongitude(longitude);
            
            assertEquals(longitude, req.getLongitude());
        }

        @Test
        @DisplayName("Latitude 设置和获取")
        void testLatitudeGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            String latitude = "31.230416";
            
            req.setLatitude(latitude);
            
            assertEquals(latitude, req.getLatitude());
        }

        @Test
        @DisplayName("IsTransship 设置和获取")
        void testIsTransshipGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            Boolean isTransship = true;
            
            req.setIsTransship(isTransship);
            
            assertEquals(isTransship, req.getIsTransship());
        }

        @Test
        @DisplayName("IsExpress 设置和获取")
        void testIsExpressGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            Boolean isExpress = true;
            
            req.setIsExpress(isExpress);
            
            assertEquals(isExpress, req.getIsExpress());
        }

        @Test
        @DisplayName("DeliveryFee 设置和获取")
        void testDeliveryFeeGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            Long deliveryFee = 800L;
            
            req.setDeliveryFee(deliveryFee);
            
            assertEquals(deliveryFee, req.getDeliveryFee());
        }

        @Test
        @DisplayName("UpdateTime 设置和获取")
        void testUpdateTimeGetterSetter() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            Long updateTime = 1704067200L; // 2024-01-01 00:00:00
            
            req.setUpdateTime(updateTime);
            
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            DeliveryChangeReq req = new DeliveryChangeReq("order", "shop", "status", "rider",
                    "phone", "logistic", "tag", "lng", "lat", true, true, 100L, 1000L);
            
            req.setOrderId(null);
            req.setShopId(null);
            req.setStatus(null);
            req.setRiderName(null);
            req.setRiderPhone(null);
            req.setLogisticNo(null);
            req.setLogisticTag(null);
            req.setLongitude(null);
            req.setLatitude(null);
            req.setIsTransship(null);
            req.setIsExpress(null);
            req.setDeliveryFee(null);
            req.setUpdateTime(null);
            
            assertNull(req.getOrderId());
            assertNull(req.getShopId());
            assertNull(req.getStatus());
            assertNull(req.getRiderName());
            assertNull(req.getRiderPhone());
            assertNull(req.getLogisticNo());
            assertNull(req.getLogisticTag());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
            assertNull(req.getIsTransship());
            assertNull(req.getIsExpress());
            assertNull(req.getDeliveryFee());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            DeliveryChangeReq req1 = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            DeliveryChangeReq req2 = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            
            assertEquals(req1, req2);
            assertEquals(req1, req1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            DeliveryChangeReq req1 = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            DeliveryChangeReq req2 = new DeliveryChangeReq("ORDER_456", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            DeliveryChangeReq req3 = new DeliveryChangeReq("ORDER_123", "SHOP_789", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            DeliveryChangeReq req4 = null;
            
            assertNotEquals(req1, req2);
            assertNotEquals(req1, req3);
            assertNotEquals(req1, req4);
            assertNotEquals(req1, "not a DeliveryChangeReq");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            DeliveryChangeReq req1 = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            DeliveryChangeReq req2 = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            DeliveryChangeReq req3 = new DeliveryChangeReq("ORDER_456", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            
            assertEquals(req1.hashCode(), req2.hashCode());
            assertNotEquals(req1.hashCode(), req3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            DeliveryChangeReq req = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("ORDER_123"));
            assertTrue(result.contains("SHOP_456"));
            assertTrue(result.contains("DELIVERED"));
            assertTrue(result.contains("张三"));
            assertTrue(result.contains("13812345678"));
            assertTrue(result.contains("DeliveryChangeReq"));
        }

        @Test
        @DisplayName("toString方法测试 - null值")
        void testToString_WithNullValues() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            
            String result = req.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("DeliveryChangeReq"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            DeliveryChangeReq req = new DeliveryChangeReq("ORDER_123", "SHOP_456", "DELIVERED", 
                    "张三", "13812345678", "LOG_789", "MEITUAN", "116.397", "39.916", 
                    true, false, 500L, 1704067200L);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"ORDER_123\""));
            assertTrue(json.contains("\"shop_id\":\"SHOP_456\""));
            assertTrue(json.contains("\"status\":\"DELIVERED\""));
            assertTrue(json.contains("\"rider_name\":\"张三\""));
            assertTrue(json.contains("\"rider_phone\":\"13812345678\""));
            assertTrue(json.contains("\"logistic_no\":\"LOG_789\""));
            assertTrue(json.contains("\"logistic_tag\":\"MEITUAN\""));
            assertTrue(json.contains("\"longitude\":\"116.397\""));
            assertTrue(json.contains("\"latitude\":\"39.916\""));
            assertTrue(json.contains("\"is_transship\":true"));
            assertTrue(json.contains("\"is_express\":false"));
            assertTrue(json.contains("\"delivery_fee\":500"));
            assertTrue(json.contains("\"update_time\":1704067200"));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"status\":\"DELIVERED\"," +
                    "\"rider_name\":\"张三\",\"rider_phone\":\"13812345678\",\"logistic_no\":\"LOG_789\"," +
                    "\"logistic_tag\":\"MEITUAN\",\"longitude\":\"116.397\",\"latitude\":\"39.916\"," +
                    "\"is_transship\":true,\"is_express\":false,\"delivery_fee\":500,\"update_time\":1704067200}";
            
            DeliveryChangeReq req = objectMapper.readValue(json, DeliveryChangeReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals("DELIVERED", req.getStatus());
            assertEquals("张三", req.getRiderName());
            assertEquals("13812345678", req.getRiderPhone());
            assertEquals("LOG_789", req.getLogisticNo());
            assertEquals("MEITUAN", req.getLogisticTag());
            assertEquals("116.397", req.getLongitude());
            assertEquals("39.916", req.getLatitude());
            assertEquals(true, req.getIsTransship());
            assertEquals(false, req.getIsExpress());
            assertEquals(500L, req.getDeliveryFee());
            assertEquals(1704067200L, req.getUpdateTime());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            DeliveryChangeReq original = new DeliveryChangeReq("ORDER_789", "SHOP_123", "PICKING_UP", 
                    "李四", "13987654321", "LOG_456", "ELEME", "121.473", "31.230", 
                    false, true, 800L, 1704153600L);
            
            String json = objectMapper.writeValueAsString(original);
            DeliveryChangeReq deserialized = objectMapper.readValue(json, DeliveryChangeReq.class);
            
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("JSON序列化测试 - null值")
        void testJsonSerialization_WithNulls() throws JsonProcessingException {
            DeliveryChangeReq req = new DeliveryChangeReq();
            
            String json = objectMapper.writeValueAsString(req);
            
            assertNotNull(json);
            // Jackson默认不序列化null值，除非特别配置
        }

        @Test
        @DisplayName("JSON反序列化测试 - 部分字段")
        void testJsonDeserialization_PartialFields() throws JsonProcessingException {
            String json = "{\"order_id\":\"ORDER_123\",\"shop_id\":\"SHOP_456\",\"status\":\"DELIVERED\"}";
            
            DeliveryChangeReq req = objectMapper.readValue(json, DeliveryChangeReq.class);
            
            assertNotNull(req);
            assertEquals("ORDER_123", req.getOrderId());
            assertEquals("SHOP_456", req.getShopId());
            assertEquals("DELIVERED", req.getStatus());
            assertNull(req.getRiderName());
            assertNull(req.getRiderPhone());
            assertEquals(false, req.getIsTransship()); // 默认值
            assertEquals(false, req.getIsExpress());   // 默认值
            assertEquals(0L, req.getDeliveryFee());    // 默认值
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("超长字符串测试")
        void testVeryLongStrings() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
            String longString = sb.toString();
            
            DeliveryChangeReq req = new DeliveryChangeReq();
            req.setOrderId(longString);
            req.setShopId(longString);
            req.setStatus(longString);
            req.setRiderName(longString);
            req.setRiderPhone(longString);
            req.setLogisticNo(longString);
            req.setLogisticTag(longString);
            req.setLongitude(longString);
            req.setLatitude(longString);
            
            assertEquals(longString, req.getOrderId());
            assertEquals(longString, req.getShopId());
            assertEquals(longString, req.getStatus());
            assertEquals(longString, req.getRiderName());
            assertEquals(longString, req.getRiderPhone());
            assertEquals(longString, req.getLogisticNo());
            assertEquals(longString, req.getLogisticTag());
            assertEquals(longString, req.getLongitude());
            assertEquals(longString, req.getLatitude());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialOrderId = "ORDER_测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            String specialShopId = "SHOP_特殊字符🚀🎉";
            String specialRiderName = "骑手_李四@北京";
            
            DeliveryChangeReq req = new DeliveryChangeReq();
            req.setOrderId(specialOrderId);
            req.setShopId(specialShopId);
            req.setRiderName(specialRiderName);
            
            assertEquals(specialOrderId, req.getOrderId());
            assertEquals(specialShopId, req.getShopId());
            assertEquals(specialRiderName, req.getRiderName());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            String unicodeOrderId = "ORDER_汉字_العربية_русский";
            String unicodeShopId = "SHOP_🌟⭐✨";
            String unicodeRiderName = "骑手_日本語_한국어";
            
            DeliveryChangeReq req = new DeliveryChangeReq();
            req.setOrderId(unicodeOrderId);
            req.setShopId(unicodeShopId);
            req.setRiderName(unicodeRiderName);
            
            assertEquals(unicodeOrderId, req.getOrderId());
            assertEquals(unicodeShopId, req.getShopId());
            assertEquals(unicodeRiderName, req.getRiderName());
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            req.setOrderId("");
            req.setShopId("");
            req.setStatus("");
            req.setRiderName("");
            req.setRiderPhone("");
            
            assertEquals("", req.getOrderId());
            assertEquals("", req.getShopId());
            assertEquals("", req.getStatus());
            assertEquals("", req.getRiderName());
            assertEquals("", req.getRiderPhone());
        }

        @Test
        @DisplayName("数值边界测试")
        void testNumericalBoundaries() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            
            // 测试极大值
            Long maxLong = Long.MAX_VALUE;
            req.setDeliveryFee(maxLong);
            req.setUpdateTime(maxLong);
            assertEquals(maxLong, req.getDeliveryFee());
            assertEquals(maxLong, req.getUpdateTime());
            
            // 测试零值
            req.setDeliveryFee(0L);
            req.setUpdateTime(0L);
            assertEquals(0L, req.getDeliveryFee());
            assertEquals(0L, req.getUpdateTime());
            
            // 测试负值
            Long negativeValue = -1L;
            req.setDeliveryFee(negativeValue);
            req.setUpdateTime(negativeValue);
            assertEquals(negativeValue, req.getDeliveryFee());
            assertEquals(negativeValue, req.getUpdateTime());
        }

        @Test
        @DisplayName("布尔值边界测试")
        void testBooleanBoundaries() {
            DeliveryChangeReq req = new DeliveryChangeReq();
            
            // 测试true值
            req.setIsTransship(true);
            req.setIsExpress(true);
            assertEquals(true, req.getIsTransship());
            assertEquals(true, req.getIsExpress());
            
            // 测试false值
            req.setIsTransship(false);
            req.setIsExpress(false);
            assertEquals(false, req.getIsTransship());
            assertEquals(false, req.getIsExpress());
            
            // 测试null值
            req.setIsTransship(null);
            req.setIsExpress(null);
            assertNull(req.getIsTransship());
            assertNull(req.getIsExpress());
        }
    }
}