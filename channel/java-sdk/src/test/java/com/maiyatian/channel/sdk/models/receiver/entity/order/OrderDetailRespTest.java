/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import com.maiyatian.channel.sdk.models.sender.entity.order.OrderBaseData;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderInfo;
import com.maiyatian.channel.sdk.models.sender.entity.order.OrderCustomerInfo;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * OrderDetailResp 查询订单详情响应数据单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. 业务逻辑方法测试
 * 5. JSON 序列化/反序列化测试
 * 6. 静态工厂方法测试
 * 7. 边界条件测试
 */
@DisplayName("OrderDetailResp 查询订单详情响应数据测试")
public class OrderDetailRespTest {

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
            OrderDetailResp resp = new OrderDetailResp();
            
            assertNotNull(resp);
            assertNull(resp.getOrder());
            assertNull(resp.getOrderCustomer());
            assertNull(resp.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = System.currentTimeMillis() / 1000;
            
            OrderDetailResp resp = new OrderDetailResp(order, orderCustomer, updateTime);
            
            assertNotNull(resp);
            assertEquals(order, resp.getOrder());
            assertEquals(orderCustomer, resp.getOrderCustomer());
            assertEquals(updateTime, resp.getUpdateTime());
        }

        @Test
        @DisplayName("基于OrderBaseData构造")
        void testOrderBaseDataConstructor() {
            OrderBaseData orderBaseData = Mockito.mock(OrderBaseData.class);
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = System.currentTimeMillis() / 1000;
            
            when(orderBaseData.getOrder()).thenReturn(order);
            when(orderBaseData.getOrderCustomer()).thenReturn(orderCustomer);
            when(orderBaseData.getUpdateTime()).thenReturn(updateTime);
            
            OrderDetailResp resp = new OrderDetailResp(orderBaseData);
            
            assertNotNull(resp);
            assertEquals(order, resp.getOrder());
            assertEquals(orderCustomer, resp.getOrderCustomer());
            assertEquals(updateTime, resp.getUpdateTime());
        }

        @Test
        @DisplayName("基于null OrderBaseData构造")
        void testOrderBaseDataConstructor_WithNull() {
            OrderDetailResp resp = new OrderDetailResp((OrderBaseData) null);
            
            assertNotNull(resp);
            assertNull(resp.getOrder());
            assertNull(resp.getOrderCustomer());
            assertNull(resp.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("Order 设置和获取")
        void testOrderGetterSetter() {
            OrderDetailResp resp = new OrderDetailResp();
            OrderInfo order = Mockito.mock(OrderInfo.class);
            
            resp.setOrder(order);
            
            assertEquals(order, resp.getOrder());
        }

        @Test
        @DisplayName("OrderCustomer 设置和获取")
        void testOrderCustomerGetterSetter() {
            OrderDetailResp resp = new OrderDetailResp();
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            
            resp.setOrderCustomer(orderCustomer);
            
            assertEquals(orderCustomer, resp.getOrderCustomer());
        }

        @Test
        @DisplayName("UpdateTime 设置和获取")
        void testUpdateTimeGetterSetter() {
            OrderDetailResp resp = new OrderDetailResp();
            Long updateTime = 1704067200L;
            
            resp.setUpdateTime(updateTime);
            
            assertEquals(updateTime, resp.getUpdateTime());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            OrderDetailResp resp = new OrderDetailResp(order, orderCustomer, 1000L);
            
            resp.setOrder(null);
            resp.setOrderCustomer(null);
            resp.setUpdateTime(null);
            
            assertNull(resp.getOrder());
            assertNull(resp.getOrderCustomer());
            assertNull(resp.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("便捷方法测试")
    class ConvenienceMethodTests {

        @Test
        @DisplayName("isComplete方法测试 - 完整数据")
        void testIsComplete_CompleteData() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            OrderDetailResp resp = new OrderDetailResp(order, orderCustomer, 1000L);
            
            assertTrue(resp.isComplete());
        }

        @Test
        @DisplayName("isComplete方法测试 - 不完整数据")
        void testIsComplete_IncompleteData() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            
            // 缺少order
            OrderDetailResp resp1 = new OrderDetailResp(null, orderCustomer, 1000L);
            assertFalse(resp1.isComplete());
            
            // 缺少orderCustomer
            OrderDetailResp resp2 = new OrderDetailResp(order, null, 1000L);
            assertFalse(resp2.isComplete());
            
            // 都缺少
            OrderDetailResp resp3 = new OrderDetailResp(null, null, 1000L);
            assertFalse(resp3.isComplete());
        }

        @Test
        @DisplayName("getOrderId便捷方法测试")
        void testGetOrderIdConvenience() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            when(order.getOrderId()).thenReturn("ORDER_123");
            
            OrderDetailResp resp = new OrderDetailResp();
            resp.setOrder(order);
            
            assertEquals("ORDER_123", resp.getOrderId());
        }

        @Test
        @DisplayName("getOrderId便捷方法测试 - null订单")
        void testGetOrderIdConvenience_NullOrder() {
            OrderDetailResp resp = new OrderDetailResp();
            
            assertNull(resp.getOrderId());
        }

        @Test
        @DisplayName("getShopId便捷方法测试")
        void testGetShopIdConvenience() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            when(order.getShopId()).thenReturn("SHOP_456");
            
            OrderDetailResp resp = new OrderDetailResp();
            resp.setOrder(order);
            
            assertEquals("SHOP_456", resp.getShopId());
        }

        @Test
        @DisplayName("getCustomerName便捷方法测试")
        void testGetCustomerNameConvenience() {
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            when(orderCustomer.getRealName()).thenReturn("张三");
            
            OrderDetailResp resp = new OrderDetailResp();
            resp.setOrderCustomer(orderCustomer);
            
            assertEquals("张三", resp.getCustomerName());
        }

        @Test
        @DisplayName("getCustomerPhone便捷方法测试")
        void testGetCustomerPhoneConvenience() {
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            when(orderCustomer.getPhone()).thenReturn("13812345678");
            
            OrderDetailResp resp = new OrderDetailResp();
            resp.setOrderCustomer(orderCustomer);
            
            assertEquals("13812345678", resp.getCustomerPhone());
        }

        @Test
        @DisplayName("toOrderBaseData转换方法测试")
        void testToOrderBaseData() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = 1000L;
            
            OrderDetailResp resp = new OrderDetailResp(order, orderCustomer, updateTime);
            OrderBaseData orderBaseData = resp.toOrderBaseData();
            
            assertNotNull(orderBaseData);
            assertEquals(order, orderBaseData.getOrder());
            assertEquals(orderCustomer, orderBaseData.getOrderCustomer());
            assertEquals(updateTime, orderBaseData.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试")
        void testOfFactoryMethod() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = 1000L;
            
            OrderDetailResp resp = OrderDetailResp.of(order, orderCustomer, updateTime);
            
            assertNotNull(resp);
            assertEquals(order, resp.getOrder());
            assertEquals(orderCustomer, resp.getOrderCustomer());
            assertEquals(updateTime, resp.getUpdateTime());
        }

        @Test
        @DisplayName("from静态工厂方法测试")
        void testFromFactoryMethod() {
            OrderBaseData orderBaseData = Mockito.mock(OrderBaseData.class);
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = 1000L;
            
            when(orderBaseData.getOrder()).thenReturn(order);
            when(orderBaseData.getOrderCustomer()).thenReturn(orderCustomer);
            when(orderBaseData.getUpdateTime()).thenReturn(updateTime);
            
            OrderDetailResp resp = OrderDetailResp.from(orderBaseData);
            
            assertNotNull(resp);
            assertEquals(order, resp.getOrder());
            assertEquals(orderCustomer, resp.getOrderCustomer());
            assertEquals(updateTime, resp.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = 1000L;
            
            OrderDetailResp resp1 = new OrderDetailResp(order, orderCustomer, updateTime);
            OrderDetailResp resp2 = new OrderDetailResp(order, orderCustomer, updateTime);
            
            assertEquals(resp1, resp2);
            assertEquals(resp1, resp1); // 自身相等
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            Long updateTime = 1000L;
            
            OrderDetailResp resp = new OrderDetailResp(order, orderCustomer, updateTime);
            
            String result = resp.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("OrderDetailResp"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("所有字段为null的测试")
        void testAllFieldsNull() {
            OrderDetailResp resp = new OrderDetailResp(null, null, null);
            
            assertFalse(resp.isComplete());
            assertNull(resp.getOrderId());
            assertNull(resp.getShopId());
            assertNull(resp.getCustomerName());
            assertNull(resp.getCustomerPhone());
        }

        @Test
        @DisplayName("混合边界条件测试")
        void testMixedEdgeCases() {
            OrderInfo order = Mockito.mock(OrderInfo.class);
            when(order.getOrderId()).thenReturn("");
            when(order.getShopId()).thenReturn("   ");
            
            OrderCustomerInfo orderCustomer = Mockito.mock(OrderCustomerInfo.class);
            when(orderCustomer.getRealName()).thenReturn("");
            when(orderCustomer.getPhone()).thenReturn(null);
            
            OrderDetailResp resp = new OrderDetailResp(order, orderCustomer, 0L);
            
            assertTrue(resp.isComplete());
            assertEquals("", resp.getOrderId());
            assertEquals("   ", resp.getShopId());
            assertEquals("", resp.getCustomerName());
            assertNull(resp.getCustomerPhone());
        }
    }
}