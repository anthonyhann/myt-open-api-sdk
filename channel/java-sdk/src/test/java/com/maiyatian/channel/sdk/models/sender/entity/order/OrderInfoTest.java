/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderInfo 单元测试
 * 
 * @author Test Generator
 * @version 1.0.0
 */
@DisplayName("OrderInfo 测试")
public class OrderInfoTest {

    private ObjectMapper objectMapper;
    private OrderInfo orderInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderInfo = new OrderInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            // When
            OrderInfo info = new OrderInfo();
            
            // Then
            assertNotNull(info);
            assertNull(info.getOrderId());
            assertNull(info.getOrderSn());
            assertNull(info.getShopId());
            assertNull(info.getShopName());
            assertNull(info.getOriginOrderSn());
            assertNull(info.getOriginTag());
            assertNull(info.getCategory());
            assertNull(info.getIsPreOrder());
            assertNull(info.getTotalPrice());
            assertNull(info.getBalancePrice());
            assertNull(info.getCreateTime());
            assertNull(info.getDeliveryTime());
            assertNull(info.getDeliveryEnd());
            assertNull(info.getDeliveryType());
            assertNull(info.getIsPicker());
            assertNull(info.getPickTime());
            assertNull(info.getUserRemark());
            assertNull(info.getGreeting());
            assertNull(info.getPickupCode());
            assertNull(info.getOrderFee());
            assertNull(info.getOrderGoods());
        }
    }

    @Nested
    @DisplayName("Getter 和 Setter 测试")
    class GetterSetterTests {

        @Test
        @DisplayName("订单基本信息测试")
        void testBasicOrderInfo() {
            // Given
            String orderId = "order_12345";
            Integer orderSn = 12345678;
            String shopId = "shop_001";
            String shopName = "测试餐厅";
            String originOrderSn = "mt_order_001";
            String originTag = "meituan";
            String category = "canyin";
            
            // When
            orderInfo.setOrderId(orderId);
            orderInfo.setOrderSn(orderSn);
            orderInfo.setShopId(shopId);
            orderInfo.setShopName(shopName);
            orderInfo.setOriginOrderSn(originOrderSn);
            orderInfo.setOriginTag(originTag);
            orderInfo.setCategory(category);
            
            // Then
            assertEquals(orderId, orderInfo.getOrderId());
            assertEquals(orderSn, orderInfo.getOrderSn());
            assertEquals(shopId, orderInfo.getShopId());
            assertEquals(shopName, orderInfo.getShopName());
            assertEquals(originOrderSn, orderInfo.getOriginOrderSn());
            assertEquals(originTag, orderInfo.getOriginTag());
            assertEquals(category, orderInfo.getCategory());
        }

        @Test
        @DisplayName("订单状态和类型测试")
        void testOrderStatusAndType() {
            // Given
            Boolean isPreOrder = true;
            Boolean isPicker = false;
            
            // When
            orderInfo.setIsPreOrder(isPreOrder);
            orderInfo.setIsPicker(isPicker);
            
            // Then
            assertEquals(isPreOrder, orderInfo.getIsPreOrder());
            assertEquals(isPicker, orderInfo.getIsPicker());
        }

        @Test
        @DisplayName("价格信息测试")
        void testPriceInfo() {
            // Given
            Integer totalPrice = 5000;
            Integer balancePrice = 4500;
            
            // When
            orderInfo.setTotalPrice(totalPrice);
            orderInfo.setBalancePrice(balancePrice);
            
            // Then
            assertEquals(totalPrice, orderInfo.getTotalPrice());
            assertEquals(balancePrice, orderInfo.getBalancePrice());
        }

        @Test
        @DisplayName("时间信息测试")
        void testTimeInfo() {
            // Given
            Long createTime = 1640995200L; // 2022-01-01 00:00:00
            Long deliveryTime = 1641038400L; // 2022-01-01 12:00:00
            Long deliveryEnd = 1641042000L; // 2022-01-01 13:00:00
            Long pickTime = 1641045600L; // 2022-01-01 14:00:00
            
            // When
            orderInfo.setCreateTime(createTime);
            orderInfo.setDeliveryTime(deliveryTime);
            orderInfo.setDeliveryEnd(deliveryEnd);
            orderInfo.setPickTime(pickTime);
            
            // Then
            assertEquals(createTime, orderInfo.getCreateTime());
            assertEquals(deliveryTime, orderInfo.getDeliveryTime());
            assertEquals(deliveryEnd, orderInfo.getDeliveryEnd());
            assertEquals(pickTime, orderInfo.getPickTime());
        }

        @Test
        @DisplayName("配送类型测试")
        void testDeliveryType() {
            // Given
            Long deliveryType = 1L; // 同城配送
            
            // When
            orderInfo.setDeliveryType(deliveryType);
            
            // Then
            assertEquals(deliveryType, orderInfo.getDeliveryType());
        }

        @Test
        @DisplayName("备注信息测试")
        void testRemarkInfo() {
            // Given
            String userRemark = "少盐少油";
            String greeting = "生日快乐!";
            String pickupCode = "1234";
            
            // When
            orderInfo.setUserRemark(userRemark);
            orderInfo.setGreeting(greeting);
            orderInfo.setPickupCode(pickupCode);
            
            // Then
            assertEquals(userRemark, orderInfo.getUserRemark());
            assertEquals(greeting, orderInfo.getGreeting());
            assertEquals(pickupCode, orderInfo.getPickupCode());
        }

        @Test
        @DisplayName("订单费用信息测试")
        void testOrderFeeInfo() {
            // Given
            OrderFeeInfo orderFee = new OrderFeeInfo();
            orderFee.setTotalFee(5000);
            orderFee.setUserFee(4500);
            
            // When
            orderInfo.setOrderFee(orderFee);
            
            // Then
            assertEquals(orderFee, orderInfo.getOrderFee());
            assertEquals(Integer.valueOf(5000), orderInfo.getOrderFee().getTotalFee());
        }

        @Test
        @DisplayName("订单商品列表测试")
        void testOrderGoodsList() {
            // Given
            List<OrderGoodsInfo> orderGoods = Arrays.asList(
                createTestOrderGoodsInfo("goods_001", "汉堡"),
                createTestOrderGoodsInfo("goods_002", "可乐")
            );
            
            // When
            orderInfo.setOrderGoods(orderGoods);
            
            // Then
            assertEquals(orderGoods, orderInfo.getOrderGoods());
            assertEquals(2, orderInfo.getOrderGoods().size());
        }
    }

    @Nested
    @DisplayName("equals 和 hashCode 测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象 equals 测试")
        void testEqualsWithSameInstance() {
            // Then
            assertEquals(orderInfo, orderInfo);
            assertEquals(orderInfo.hashCode(), orderInfo.hashCode());
        }

        @Test
        @DisplayName("null 对象 equals 测试")
        void testEqualsWithNull() {
            // Then
            assertNotEquals(null, orderInfo);
        }

        @Test
        @DisplayName("不同类型对象 equals 测试")
        void testEqualsWithDifferentType() {
            // Given
            String differentType = "different";
            
            // Then
            assertNotEquals(orderInfo, differentType);
        }

        @Test
        @DisplayName("相同属性对象 equals 测试")
        void testEqualsWithSameProperties() {
            // Given
            OrderInfo info1 = createFullOrderInfo();
            OrderInfo info2 = createFullOrderInfo();
            
            // Then
            assertEquals(info1, info2);
            assertEquals(info1.hashCode(), info2.hashCode());
        }

        @Test
        @DisplayName("不同订单ID equals 测试")
        void testEqualsWithDifferentOrderId() {
            // Given
            orderInfo.setOrderId("order_001");
            OrderInfo other = new OrderInfo();
            other.setOrderId("order_002");
            
            // Then
            assertNotEquals(orderInfo, other);
        }

        @Test
        @DisplayName("不同商店ID equals 测试")
        void testEqualsWithDifferentShopId() {
            // Given
            orderInfo.setShopId("shop_001");
            OrderInfo other = new OrderInfo();
            other.setShopId("shop_002");
            
            // Then
            assertNotEquals(orderInfo, other);
        }

        @Test
        @DisplayName("不同价格 equals 测试")
        void testEqualsWithDifferentPrice() {
            // Given
            orderInfo.setTotalPrice(1000);
            OrderInfo other = new OrderInfo();
            other.setTotalPrice(2000);
            
            // Then
            assertNotEquals(orderInfo, other);
        }

        @Test
        @DisplayName("不同布尔属性 equals 测试")
        void testEqualsWithDifferentBooleanProperties() {
            // Given
            orderInfo.setIsPreOrder(true);
            orderInfo.setIsPicker(false);
            OrderInfo other = new OrderInfo();
            other.setIsPreOrder(false);
            other.setIsPicker(true);
            
            // Then
            assertNotEquals(orderInfo, other);
        }
    }

    @Nested
    @DisplayName("toString 测试")
    class ToStringTests {

        @Test
        @DisplayName("空对象 toString 测试")
        void testToStringWithEmptyObject() {
            // When
            String result = orderInfo.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderInfo"));
            assertTrue(result.contains("orderId='null'"));
            assertTrue(result.contains("shopId='null'"));
            assertTrue(result.contains("totalPrice=null"));
        }

        @Test
        @DisplayName("完整对象 toString 测试")
        void testToStringWithFullObject() {
            // Given
            OrderInfo info = createFullOrderInfo();
            
            // When
            String result = info.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderInfo"));
            assertTrue(result.contains("orderId='order_12345'"));
            assertTrue(result.contains("shopId='shop_001'"));
            assertTrue(result.contains("totalPrice=5000"));
            assertTrue(result.contains("isPreOrder=false"));
        }

        @Test
        @DisplayName("包含布尔值的 toString 测试")
        void testToStringWithBooleanValues() {
            // Given
            orderInfo.setIsPreOrder(true);
            orderInfo.setIsPicker(false);
            
            // When
            String result = orderInfo.toString();
            
            // Then
            assertTrue(result.contains("isPreOrder=true"));
            assertTrue(result.contains("isPicker=false"));
        }

        @Test
        @DisplayName("包含时间戳的 toString 测试")
        void testToStringWithTimestamps() {
            // Given
            orderInfo.setCreateTime(1640995200L);
            orderInfo.setDeliveryTime(1641038400L);
            
            // When
            String result = orderInfo.toString();
            
            // Then
            assertTrue(result.contains("createTime=1640995200"));
            assertTrue(result.contains("deliveryTime=1641038400"));
        }
    }

    @Nested
    @DisplayName("JSON 序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("空对象序列化测试")
        void testSerializeEmptyObject() throws JsonProcessingException {
            // When
            String json = objectMapper.writeValueAsString(orderInfo);
            
            // Then
            assertNotNull(json);
            assertEquals("{}", json);
        }

        @Test
        @DisplayName("基本信息序列化测试")
        void testSerializeBasicInfo() throws JsonProcessingException {
            // Given
            orderInfo.setOrderId("order_12345");
            orderInfo.setShopId("shop_001");
            orderInfo.setTotalPrice(5000);
            orderInfo.setIsPreOrder(false);
            
            // When
            String json = objectMapper.writeValueAsString(orderInfo);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order_id\":\"order_12345\""));
            assertTrue(json.contains("\"shop_id\":\"shop_001\""));
            assertTrue(json.contains("\"total_price\":5000"));
            assertTrue(json.contains("\"is_pre_order\":false"));
        }

        @Test
        @DisplayName("反序列化测试")
        void testDeserialize() throws JsonProcessingException {
            // Given
            String json = "{\"order_id\":\"order_12345\",\"shop_id\":\"shop_001\",\"total_price\":5000,\"is_pre_order\":false}";
            
            // When
            OrderInfo info = objectMapper.readValue(json, OrderInfo.class);
            
            // Then
            assertNotNull(info);
            assertEquals("order_12345", info.getOrderId());
            assertEquals("shop_001", info.getShopId());
            assertEquals(Integer.valueOf(5000), info.getTotalPrice());
            assertEquals(Boolean.FALSE, info.getIsPreOrder());
        }

        @Test
        @DisplayName("序列化后反序列化一致性测试")
        void testSerializeDeserializeConsistency() throws JsonProcessingException {
            // Given
            OrderInfo original = createFullOrderInfo();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderInfo deserialized = objectMapper.readValue(json, OrderInfo.class);
            
            // Then
            assertEquals(original.getOrderId(), deserialized.getOrderId());
            assertEquals(original.getShopId(), deserialized.getShopId());
            assertEquals(original.getTotalPrice(), deserialized.getTotalPrice());
            assertEquals(original.getIsPreOrder(), deserialized.getIsPreOrder());
            assertEquals(original.getCreateTime(), deserialized.getCreateTime());
        }

        @Test
        @DisplayName("时间戳序列化测试")
        void testSerializeTimestamps() throws JsonProcessingException {
            // Given
            orderInfo.setCreateTime(1640995200L);
            orderInfo.setDeliveryTime(1641038400L);
            orderInfo.setDeliveryEnd(1641042000L);
            
            // When
            String json = objectMapper.writeValueAsString(orderInfo);
            
            // Then
            assertTrue(json.contains("\"create_time\":1640995200"));
            assertTrue(json.contains("\"delivery_time\":1641038400"));
            assertTrue(json.contains("\"delivery_end\":1641042000"));
        }

        @Test
        @DisplayName("布尔值序列化测试")
        void testSerializeBooleanValues() throws JsonProcessingException {
            // Given
            orderInfo.setIsPreOrder(true);
            orderInfo.setIsPicker(false);
            
            // When
            String json = objectMapper.writeValueAsString(orderInfo);
            
            // Then
            assertTrue(json.contains("\"is_pre_order\":true"));
            assertTrue(json.contains("\"is_picker\":false"));
        }

        @Test
        @DisplayName("嵌套对象序列化测试")
        void testSerializeNestedObjects() throws JsonProcessingException {
            // Given
            OrderFeeInfo orderFee = new OrderFeeInfo();
            orderFee.setTotalFee(5000);
            orderInfo.setOrderFee(orderFee);
            
            orderInfo.setOrderGoods(Arrays.asList(createTestOrderGoodsInfo("goods_001", "汉堡")));
            
            // When
            String json = objectMapper.writeValueAsString(orderInfo);
            
            // Then
            assertTrue(json.contains("\"order_fee\":"));
            assertTrue(json.contains("\"order_goods\":"));
            assertTrue(json.contains("\"total_fee\":5000"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("最大整数值测试")
        void testMaxIntegerValues() {
            // Given
            orderInfo.setOrderSn(Integer.MAX_VALUE);
            orderInfo.setTotalPrice(Integer.MAX_VALUE);
            orderInfo.setBalancePrice(Integer.MAX_VALUE);
            
            // Then
            assertEquals(Integer.MAX_VALUE, orderInfo.getOrderSn());
            assertEquals(Integer.MAX_VALUE, orderInfo.getTotalPrice());
            assertEquals(Integer.MAX_VALUE, orderInfo.getBalancePrice());
        }

        @Test
        @DisplayName("最小整数值测试")
        void testMinIntegerValues() {
            // Given
            orderInfo.setOrderSn(Integer.MIN_VALUE);
            orderInfo.setTotalPrice(Integer.MIN_VALUE);
            orderInfo.setBalancePrice(Integer.MIN_VALUE);
            
            // Then
            assertEquals(Integer.MIN_VALUE, orderInfo.getOrderSn());
            assertEquals(Integer.MIN_VALUE, orderInfo.getTotalPrice());
            assertEquals(Integer.MIN_VALUE, orderInfo.getBalancePrice());
        }

        @Test
        @DisplayName("零值测试")
        void testZeroValues() {
            // Given
            orderInfo.setOrderSn(0);
            orderInfo.setTotalPrice(0);
            orderInfo.setBalancePrice(0);
            orderInfo.setCreateTime(0L);
            
            // Then
            assertEquals(Integer.valueOf(0), orderInfo.getOrderSn());
            assertEquals(Integer.valueOf(0), orderInfo.getTotalPrice());
            assertEquals(Integer.valueOf(0), orderInfo.getBalancePrice());
            assertEquals(Long.valueOf(0), orderInfo.getCreateTime());
        }

        @Test
        @DisplayName("负数测试")
        void testNegativeValues() {
            // Given
            orderInfo.setTotalPrice(-1000);
            orderInfo.setBalancePrice(-500);
            
            // Then
            assertEquals(Integer.valueOf(-1000), orderInfo.getTotalPrice());
            assertEquals(Integer.valueOf(-500), orderInfo.getBalancePrice());
        }

        @Test
        @DisplayName("订单流水号边界值测试")
        void testOrderSnBoundaryValues() {
            // 测试最大允许值
            orderInfo.setOrderSn(99999999);
            assertEquals(Integer.valueOf(99999999), orderInfo.getOrderSn());
            
            // 测试最小值
            orderInfo.setOrderSn(1);
            assertEquals(Integer.valueOf(1), orderInfo.getOrderSn());
        }

        @Test
        @DisplayName("配送类型边界值测试")
        void testDeliveryTypeBoundaryValues() {
            // Given & When & Then
            orderInfo.setDeliveryType(0L);
            assertEquals(Long.valueOf(0), orderInfo.getDeliveryType());
            
            orderInfo.setDeliveryType(1L);
            assertEquals(Long.valueOf(1), orderInfo.getDeliveryType());
            
            orderInfo.setDeliveryType(2L);
            assertEquals(Long.valueOf(2), orderInfo.getDeliveryType());
        }

        @Test
        @DisplayName("长字符串测试")
        void testLongStrings() {
            // Given
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("a");
            }
            String longString = longStringBuilder.toString();
            
            // When
            orderInfo.setOrderId(longString);
            orderInfo.setUserRemark(longString);
            orderInfo.setGreeting(longString);
            
            // Then
            assertEquals(longString, orderInfo.getOrderId());
            assertEquals(longString, orderInfo.getUserRemark());
            assertEquals(longString, orderInfo.getGreeting());
            assertEquals(1000, orderInfo.getOrderId().length());
        }

        @Test
        @DisplayName("空字符串测试")
        void testEmptyStrings() {
            // Given
            orderInfo.setOrderId("");
            orderInfo.setShopId("");
            orderInfo.setUserRemark("");
            
            // Then
            assertEquals("", orderInfo.getOrderId());
            assertEquals("", orderInfo.getShopId());
            assertEquals("", orderInfo.getUserRemark());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            // Given
            String specialOrderId = "order@#$%^&*()_+123";
            String specialRemark = "备注!@#$%^&*()";
            
            // When
            orderInfo.setOrderId(specialOrderId);
            orderInfo.setUserRemark(specialRemark);
            
            // Then
            assertEquals(specialOrderId, orderInfo.getOrderId());
            assertEquals(specialRemark, orderInfo.getUserRemark());
        }

        @Test
        @DisplayName("中文字符测试")
        void testChineseCharacters() {
            // Given
            String chineseOrderId = "订单12345";
            String chineseShopName = "麦芽田餐厅";
            String chineseRemark = "少盐少油，谢谢";
            
            // When
            orderInfo.setOrderId(chineseOrderId);
            orderInfo.setShopName(chineseShopName);
            orderInfo.setUserRemark(chineseRemark);
            
            // Then
            assertEquals(chineseOrderId, orderInfo.getOrderId());
            assertEquals(chineseShopName, orderInfo.getShopName());
            assertEquals(chineseRemark, orderInfo.getUserRemark());
        }

        @Test
        @DisplayName("时间戳边界值测试")
        void testTimestampBoundaryValues() {
            // Given
            Long maxTimestamp = Long.MAX_VALUE;
            Long minTimestamp = Long.MIN_VALUE;
            
            // When
            orderInfo.setCreateTime(maxTimestamp);
            orderInfo.setDeliveryTime(minTimestamp);
            
            // Then
            assertEquals(maxTimestamp, orderInfo.getCreateTime());
            assertEquals(minTimestamp, orderInfo.getDeliveryTime());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("即时单创建测试")
        void testImmediateOrderCreation() {
            // Given
            OrderInfo info = createValidOrderInfo();
            info.setIsPreOrder(false);
            
            // Then
            assertFalse(info.getIsPreOrder());
            assertTrue(isValidOrderInfo(info));
        }

        @Test
        @DisplayName("预约单创建测试")
        void testPreOrderCreation() {
            // Given
            OrderInfo info = createValidOrderInfo();
            info.setIsPreOrder(true);
            info.setDeliveryTime(System.currentTimeMillis() / 1000 + 3600); // 1小时后
            
            // Then
            assertTrue(info.getIsPreOrder());
            assertTrue(isValidOrderInfo(info));
        }

        @Test
        @DisplayName("自提单创建测试")
        void testPickupOrderCreation() {
            // Given
            OrderInfo info = createValidOrderInfo();
            info.setIsPicker(true);
            info.setPickupCode("1234");
            info.setPickTime(System.currentTimeMillis() / 1000 + 1800); // 30分钟后
            
            // Then
            assertTrue(info.getIsPicker());
            assertEquals("1234", info.getPickupCode());
            assertTrue(isValidOrderInfo(info));
        }

        @Test
        @DisplayName("配送单创建测试")
        void testDeliveryOrderCreation() {
            // Given
            OrderInfo info = createValidOrderInfo();
            info.setIsPicker(false);
            info.setDeliveryType(1L); // 同城配送
            
            // Then
            assertFalse(info.getIsPicker());
            assertEquals(Long.valueOf(1), info.getDeliveryType());
            assertTrue(isValidOrderInfo(info));
        }

        @Test
        @DisplayName("订单价格一致性测试")
        void testOrderPriceConsistency() {
            // Given
            OrderInfo info = createValidOrderInfo();
            info.setTotalPrice(5000);
            info.setBalancePrice(4500); // 有优惠
            
            // Then
            assertTrue(info.getBalancePrice() <= info.getTotalPrice());
        }

        /**
         * 创建有效的订单信息
         */
        private OrderInfo createValidOrderInfo() {
            OrderInfo info = new OrderInfo();
            info.setOrderId("order_12345");
            info.setShopId("shop_001");
            info.setOrderFee(new OrderFeeInfo());
            info.setOrderGoods(Arrays.asList(createTestOrderGoodsInfo("goods_001", "测试商品")));
            return info;
        }

        /**
         * 简单的业务逻辑验证
         */
        private boolean isValidOrderInfo(OrderInfo info) {
            return info != null && 
                   info.getOrderId() != null && !info.getOrderId().trim().isEmpty() &&
                   info.getShopId() != null && !info.getShopId().trim().isEmpty() &&
                   info.getOrderFee() != null &&
                   info.getOrderGoods() != null && !info.getOrderGoods().isEmpty();
        }
    }

    /**
     * 创建完整的OrderInfo对象用于测试
     */
    private OrderInfo createFullOrderInfo() {
        OrderInfo info = new OrderInfo();
        
        // 基本信息
        info.setOrderId("order_12345");
        info.setOrderSn(12345678);
        info.setShopId("shop_001");
        info.setShopName("测试餐厅");
        info.setOriginOrderSn("mt_order_001");
        info.setOriginTag("meituan");
        info.setCategory("canyin");
        
        // 状态信息
        info.setIsPreOrder(false);
        info.setIsPicker(false);
        
        // 价格信息
        info.setTotalPrice(5000);
        info.setBalancePrice(4500);
        
        // 时间信息
        info.setCreateTime(1640995200L);
        info.setDeliveryTime(1641038400L);
        info.setDeliveryEnd(1641042000L);
        info.setDeliveryType(1L);
        info.setPickTime(1641045600L);
        
        // 备注信息
        info.setUserRemark("少盐少油");
        info.setGreeting("生日快乐!");
        info.setPickupCode("1234");
        
        // 费用信息
        OrderFeeInfo orderFee = new OrderFeeInfo();
        orderFee.setTotalFee(5000);
        orderFee.setUserFee(4500);
        info.setOrderFee(orderFee);
        
        // 商品信息
        info.setOrderGoods(Arrays.asList(
            createTestOrderGoodsInfo("goods_001", "汉堡"),
            createTestOrderGoodsInfo("goods_002", "可乐")
        ));
        
        return info;
    }

    /**
     * 创建测试用的OrderGoodsInfo对象
     */
    private OrderGoodsInfo createTestOrderGoodsInfo(String goodsId, String goodsName) {
        OrderGoodsInfo goodsInfo = new OrderGoodsInfo();
        goodsInfo.setGoodsId(goodsId);
        goodsInfo.setGoodsName(goodsName);
        goodsInfo.setNumber(1);
        goodsInfo.setGoodsPrice(2000);
        goodsInfo.setTotalFee(2000);
        return goodsInfo;
    }
}