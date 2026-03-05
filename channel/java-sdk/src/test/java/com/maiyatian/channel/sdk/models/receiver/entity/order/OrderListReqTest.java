/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderListReq 查询订单列表请求参数单元测试
 */
@DisplayName("OrderListReq 查询订单列表请求参数测试")
public class OrderListReqTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            OrderListReq req = new OrderListReq();
            
            assertNotNull(req);
            assertNull(req.getShopId());
            assertNull(req.getStartTime());
            assertNull(req.getEndTime());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            String shopId = "SHOP_456";
            Long startTime = 1704067200L;
            Long endTime = 1704153600L;
            Integer page = 1;
            Integer pageSize = 20;
            
            OrderListReq req = new OrderListReq(shopId, startTime, endTime, page, pageSize);
            
            assertNotNull(req);
            assertEquals(shopId, req.getShopId());
            assertEquals(startTime, req.getStartTime());
            assertEquals(endTime, req.getEndTime());
            assertEquals(page, req.getPage());
            assertEquals(pageSize, req.getPageSize());
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid方法测试 - 有效数据")
        void testIsValid_ValidData() {
            OrderListReq req = new OrderListReq("SHOP_456", 1000L, 2000L, 1, 20);
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("isValid方法测试 - 无效数据")
        void testIsValid_InvalidData() {
            assertFalse(new OrderListReq(null, 1000L, 2000L, 1, 20).isValid());
            assertFalse(new OrderListReq("", 1000L, 2000L, 1, 20).isValid());
            assertFalse(new OrderListReq("   ", 1000L, 2000L, 1, 20).isValid());
        }

        @Test
        @DisplayName("hasValidTimeRange方法测试")
        void testHasValidTimeRange() {
            OrderListReq req1 = new OrderListReq("SHOP_456", 1000L, 2000L, 1, 20);
            assertTrue(req1.hasValidTimeRange());
            
            OrderListReq req2 = new OrderListReq("SHOP_456", 2000L, 1000L, 1, 20);
            assertFalse(req2.hasValidTimeRange());
            
            OrderListReq req3 = new OrderListReq("SHOP_456", null, 2000L, 1, 20);
            assertFalse(req3.hasValidTimeRange());
        }

        @Test
        @DisplayName("hasValidPagination方法测试")
        void testHasValidPagination() {
            OrderListReq req1 = new OrderListReq("SHOP_456", 1000L, 2000L, 1, 20);
            assertTrue(req1.hasValidPagination());
            
            OrderListReq req2 = new OrderListReq("SHOP_456", 1000L, 2000L, 0, 20);
            assertFalse(req2.hasValidPagination());
            
            OrderListReq req3 = new OrderListReq("SHOP_456", 1000L, 2000L, 1, 0);
            assertFalse(req3.hasValidPagination());
        }

        @Test
        @DisplayName("getTimeRangeDays方法测试")
        void testGetTimeRangeDays() {
            long oneDaySeconds = 24 * 60 * 60; // 一天的秒数
            OrderListReq req = new OrderListReq("SHOP_456", 1000L, 1000L + oneDaySeconds, 1, 20);
            assertEquals(1, req.getTimeRangeDays());
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试")
        void testOfFactoryMethod() {
            OrderListReq req = OrderListReq.of("SHOP_456", 1000L, 2000L, 1, 20);
            
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(1000L, req.getStartTime());
            assertEquals(2000L, req.getEndTime());
            assertEquals(1, req.getPage());
            assertEquals(20, req.getPageSize());
        }

        @Test
        @DisplayName("basic静态工厂方法测试")
        void testBasicFactoryMethod() {
            OrderListReq req = OrderListReq.basic("SHOP_456", 1000L, 2000L);
            
            assertEquals("SHOP_456", req.getShopId());
            assertEquals(1000L, req.getStartTime());
            assertEquals(2000L, req.getEndTime());
            assertEquals(1, req.getPage());
            assertEquals(20, req.getPageSize());
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderListReq original = new OrderListReq("SHOP_456", 1000L, 2000L, 1, 20);
            
            String json = objectMapper.writeValueAsString(original);
            OrderListReq deserialized = objectMapper.readValue(json, OrderListReq.class);
            
            assertEquals(original, deserialized);
        }
    }
}