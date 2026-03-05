/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import com.maiyatian.channel.sdk.models.sender.entity.order.OrderBaseData;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderListResp 查询订单列表响应数据单元测试
 */
@DisplayName("OrderListResp 查询订单列表响应数据测试")
public class OrderListRespTest {

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
            OrderListResp resp = new OrderListResp();
            
            assertNotNull(resp);
            assertNull(resp.getData());
            assertNull(resp.getPage());
            assertNull(resp.getTotal());
            assertNull(resp.getTotalPage());
            assertNull(resp.getIsLast());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            List<OrderBaseData> data = Arrays.asList(Mockito.mock(OrderBaseData.class));
            Integer page = 1;
            Integer total = 100;
            Integer totalPage = 5;
            Boolean isLast = false;
            
            OrderListResp resp = new OrderListResp(data, page, total, totalPage, isLast);
            
            assertNotNull(resp);
            assertEquals(data, resp.getData());
            assertEquals(page, resp.getPage());
            assertEquals(total, resp.getTotal());
            assertEquals(totalPage, resp.getTotalPage());
            assertEquals(isLast, resp.getIsLast());
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("hasData方法测试")
        void testHasData() {
            OrderListResp resp1 = new OrderListResp();
            resp1.setData(Arrays.asList(Mockito.mock(OrderBaseData.class)));
            assertTrue(resp1.hasData());
            
            OrderListResp resp2 = new OrderListResp();
            resp2.setData(Collections.emptyList());
            assertFalse(resp2.hasData());
            
            OrderListResp resp3 = new OrderListResp();
            resp3.setData(null);
            assertFalse(resp3.hasData());
        }

        @Test
        @DisplayName("getOrderCount方法测试")
        void testGetOrderCount() {
            OrderListResp resp = new OrderListResp();
            resp.setData(Arrays.asList(Mockito.mock(OrderBaseData.class), Mockito.mock(OrderBaseData.class)));
            assertEquals(2, resp.getOrderCount());
        }

        @Test
        @DisplayName("hasNextPage方法测试")
        void testHasNextPage() {
            OrderListResp resp1 = new OrderListResp();
            resp1.setIsLast(false);
            assertTrue(resp1.hasNextPage());
            
            OrderListResp resp2 = new OrderListResp();
            resp2.setIsLast(true);
            assertFalse(resp2.hasNextPage());
            
            OrderListResp resp3 = new OrderListResp();
            resp3.setPage(2);
            resp3.setTotalPage(5);
            assertTrue(resp3.hasNextPage());
        }

        @Test
        @DisplayName("hasPreviousPage方法测试")
        void testHasPreviousPage() {
            OrderListResp resp1 = new OrderListResp();
            resp1.setPage(2);
            assertTrue(resp1.hasPreviousPage());
            
            OrderListResp resp2 = new OrderListResp();
            resp2.setPage(1);
            assertFalse(resp2.hasPreviousPage());
        }

        @Test
        @DisplayName("hasValidPagination方法测试")
        void testHasValidPagination() {
            OrderListResp resp = new OrderListResp();
            resp.setPage(1);
            resp.setTotal(100);
            resp.setTotalPage(5);
            assertTrue(resp.hasValidPagination());
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试")
        void testOfFactoryMethod() {
            List<OrderBaseData> data = Arrays.asList(Mockito.mock(OrderBaseData.class));
            OrderListResp resp = OrderListResp.of(data, 1, 100, 5, false);
            
            assertEquals(data, resp.getData());
            assertEquals(1, resp.getPage());
            assertEquals(100, resp.getTotal());
            assertEquals(5, resp.getTotalPage());
            assertEquals(false, resp.getIsLast());
        }

        @Test
        @DisplayName("empty静态工厂方法测试")
        void testEmptyFactoryMethod() {
            OrderListResp resp = OrderListResp.empty(1);
            
            assertNotNull(resp.getData());
            assertTrue(resp.getData().isEmpty());
            assertEquals(1, resp.getPage());
            assertEquals(0, resp.getTotal());
            assertEquals(0, resp.getTotalPage());
            assertEquals(true, resp.getIsLast());
        }

        @Test
        @DisplayName("singlePage静态工厂方法测试")
        void testSinglePageFactoryMethod() {
            List<OrderBaseData> data = Arrays.asList(Mockito.mock(OrderBaseData.class));
            OrderListResp resp = OrderListResp.singlePage(data);
            
            assertEquals(data, resp.getData());
            assertEquals(1, resp.getPage());
            assertEquals(1, resp.getTotal());
            assertEquals(1, resp.getTotalPage());
            assertEquals(true, resp.getIsLast());
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            OrderListResp original = OrderListResp.empty(1);
            
            String json = objectMapper.writeValueAsString(original);
            OrderListResp deserialized = objectMapper.readValue(json, OrderListResp.class);
            
            assertEquals(original.getPage(), deserialized.getPage());
            assertEquals(original.getTotal(), deserialized.getTotal());
            assertEquals(original.getTotalPage(), deserialized.getTotalPage());
            assertEquals(original.getIsLast(), deserialized.getIsLast());
        }
    }
}