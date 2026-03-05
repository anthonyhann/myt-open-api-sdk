/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderFeeInfo 单元测试
 * 
 * @author Test Generator
 * @version 1.0.0
 */
@DisplayName("OrderFeeInfo 测试")
public class OrderFeeInfoTest {

    private ObjectMapper objectMapper;
    private OrderFeeInfo orderFeeInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderFeeInfo = new OrderFeeInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            // When
            OrderFeeInfo feeInfo = new OrderFeeInfo();
            
            // Then
            assertNotNull(feeInfo);
            assertNull(feeInfo.getTotalFee());
            assertNull(feeInfo.getSendFee());
            assertNull(feeInfo.getPackageFee());
            assertNull(feeInfo.getDiscountFee());
            assertNull(feeInfo.getShopFee());
            assertNull(feeInfo.getUserFee());
            assertNull(feeInfo.getPayType());
            assertNull(feeInfo.getNeedInvoice());
            assertNull(feeInfo.getInvoice());
            assertNull(feeInfo.getActivity());
            assertNull(feeInfo.getCommission());
            assertNull(feeInfo.getIsFirst());
            assertNull(feeInfo.getIsFavorite());
        }
    }

    @Nested
    @DisplayName("Getter 和 Setter 测试")
    class GetterSetterTests {

        @Test
        @DisplayName("总费用测试")
        void testTotalFee() {
            // Given
            Integer totalFee = 10000;
            
            // When
            orderFeeInfo.setTotalFee(totalFee);
            
            // Then
            assertEquals(totalFee, orderFeeInfo.getTotalFee());
        }

        @Test
        @DisplayName("配送费测试")
        void testSendFee() {
            // Given
            Integer sendFee = 500;
            
            // When
            orderFeeInfo.setSendFee(sendFee);
            
            // Then
            assertEquals(sendFee, orderFeeInfo.getSendFee());
        }

        @Test
        @DisplayName("餐盒费测试")
        void testPackageFee() {
            // Given
            Integer packageFee = 200;
            
            // When
            orderFeeInfo.setPackageFee(packageFee);
            
            // Then
            assertEquals(packageFee, orderFeeInfo.getPackageFee());
        }

        @Test
        @DisplayName("优惠金额测试")
        void testDiscountFee() {
            // Given
            Integer discountFee = 1000;
            
            // When
            orderFeeInfo.setDiscountFee(discountFee);
            
            // Then
            assertEquals(discountFee, orderFeeInfo.getDiscountFee());
        }

        @Test
        @DisplayName("商户应收金额测试")
        void testShopFee() {
            // Given
            Integer shopFee = 9000;
            
            // When
            orderFeeInfo.setShopFee(shopFee);
            
            // Then
            assertEquals(shopFee, orderFeeInfo.getShopFee());
        }

        @Test
        @DisplayName("用户实付金额测试")
        void testUserFee() {
            // Given
            Integer userFee = 9700;
            
            // When
            orderFeeInfo.setUserFee(userFee);
            
            // Then
            assertEquals(userFee, orderFeeInfo.getUserFee());
        }

        @Test
        @DisplayName("支付类型测试")
        void testPayType() {
            // Given
            Integer payType = 1;
            
            // When
            orderFeeInfo.setPayType(payType);
            
            // Then
            assertEquals(payType, orderFeeInfo.getPayType());
        }

        @Test
        @DisplayName("是否需要发票测试")
        void testNeedInvoice() {
            // Given
            Boolean needInvoice = true;
            
            // When
            orderFeeInfo.setNeedInvoice(needInvoice);
            
            // Then
            assertEquals(needInvoice, orderFeeInfo.getNeedInvoice());
        }

        @Test
        @DisplayName("发票信息测试")
        void testInvoice() {
            // Given
            InvoiceInfo invoice = new InvoiceInfo();
            
            // When
            orderFeeInfo.setInvoice(invoice);
            
            // Then
            assertEquals(invoice, orderFeeInfo.getInvoice());
        }

        @Test
        @DisplayName("活动信息列表测试")
        void testActivity() {
            // Given
            List<ActivityInfo> activities = Arrays.asList(new ActivityInfo(), new ActivityInfo());
            
            // When
            orderFeeInfo.setActivity(activities);
            
            // Then
            assertEquals(activities, orderFeeInfo.getActivity());
            assertEquals(2, orderFeeInfo.getActivity().size());
        }

        @Test
        @DisplayName("平台佣金测试")
        void testCommission() {
            // Given
            Integer commission = 300;
            
            // When
            orderFeeInfo.setCommission(commission);
            
            // Then
            assertEquals(commission, orderFeeInfo.getCommission());
        }

        @Test
        @DisplayName("是否首单测试")
        void testIsFirst() {
            // Given
            Boolean isFirst = true;
            
            // When
            orderFeeInfo.setIsFirst(isFirst);
            
            // Then
            assertEquals(isFirst, orderFeeInfo.getIsFirst());
        }

        @Test
        @DisplayName("是否收藏用户测试")
        void testIsFavorite() {
            // Given
            Boolean isFavorite = false;
            
            // When
            orderFeeInfo.setIsFavorite(isFavorite);
            
            // Then
            assertEquals(isFavorite, orderFeeInfo.getIsFavorite());
        }
    }

    @Nested
    @DisplayName("equals 和 hashCode 测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象 equals 测试")
        void testEqualsWithSameInstance() {
            // Then
            assertEquals(orderFeeInfo, orderFeeInfo);
            assertEquals(orderFeeInfo.hashCode(), orderFeeInfo.hashCode());
        }

        @Test
        @DisplayName("null 对象 equals 测试")
        void testEqualsWithNull() {
            // Then
            assertNotEquals(null, orderFeeInfo);
        }

        @Test
        @DisplayName("不同类型对象 equals 测试")
        void testEqualsWithDifferentType() {
            // Given
            String differentType = "different";
            
            // Then
            assertNotEquals(orderFeeInfo, differentType);
        }

        @Test
        @DisplayName("相同属性对象 equals 测试")
        void testEqualsWithSameProperties() {
            // Given
            OrderFeeInfo feeInfo1 = createFullOrderFeeInfo();
            OrderFeeInfo feeInfo2 = createFullOrderFeeInfo();
            
            // Then
            assertEquals(feeInfo1, feeInfo2);
            assertEquals(feeInfo1.hashCode(), feeInfo2.hashCode());
        }

        @Test
        @DisplayName("不同属性对象 equals 测试")
        void testEqualsWithDifferentProperties() {
            // Given
            OrderFeeInfo feeInfo1 = createFullOrderFeeInfo();
            OrderFeeInfo feeInfo2 = createFullOrderFeeInfo();
            feeInfo2.setTotalFee(99999);
            
            // Then
            assertNotEquals(feeInfo1, feeInfo2);
        }

        @Test
        @DisplayName("不同总费用 equals 测试")
        void testEqualsWithDifferentTotalFee() {
            // Given
            orderFeeInfo.setTotalFee(1000);
            OrderFeeInfo other = new OrderFeeInfo();
            other.setTotalFee(2000);
            
            // Then
            assertNotEquals(orderFeeInfo, other);
        }

        @Test
        @DisplayName("不同配送费 equals 测试")
        void testEqualsWithDifferentSendFee() {
            // Given
            orderFeeInfo.setSendFee(500);
            OrderFeeInfo other = new OrderFeeInfo();
            other.setSendFee(300);
            
            // Then
            assertNotEquals(orderFeeInfo, other);
        }

        @Test
        @DisplayName("不同支付类型 equals 测试")
        void testEqualsWithDifferentPayType() {
            // Given
            orderFeeInfo.setPayType(1);
            OrderFeeInfo other = new OrderFeeInfo();
            other.setPayType(2);
            
            // Then
            assertNotEquals(orderFeeInfo, other);
        }

        @Test
        @DisplayName("不同活动信息 equals 测试")
        void testEqualsWithDifferentActivity() {
            // Given
            orderFeeInfo.setActivity(Arrays.asList(new ActivityInfo()));
            OrderFeeInfo other = new OrderFeeInfo();
            other.setActivity(Collections.emptyList());
            
            // Then
            assertNotEquals(orderFeeInfo, other);
        }
    }

    @Nested
    @DisplayName("toString 测试")
    class ToStringTests {

        @Test
        @DisplayName("空对象 toString 测试")
        void testToStringWithEmptyObject() {
            // When
            String result = orderFeeInfo.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderFeeInfo"));
            assertTrue(result.contains("totalFee=null"));
            assertTrue(result.contains("sendFee=null"));
            assertTrue(result.contains("userFee=null"));
        }

        @Test
        @DisplayName("完整对象 toString 测试")
        void testToStringWithFullObject() {
            // Given
            OrderFeeInfo feeInfo = createFullOrderFeeInfo();
            
            // When
            String result = feeInfo.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderFeeInfo"));
            assertTrue(result.contains("totalFee=10000"));
            assertTrue(result.contains("sendFee=500"));
            assertTrue(result.contains("userFee=9700"));
            assertTrue(result.contains("payType=2"));
            assertTrue(result.contains("needInvoice=true"));
        }

        @Test
        @DisplayName("包含布尔值的 toString 测试")
        void testToStringWithBooleanValues() {
            // Given
            orderFeeInfo.setNeedInvoice(false);
            orderFeeInfo.setIsFirst(true);
            orderFeeInfo.setIsFavorite(false);
            
            // When
            String result = orderFeeInfo.toString();
            
            // Then
            assertTrue(result.contains("needInvoice=false"));
            assertTrue(result.contains("isFirst=true"));
            assertTrue(result.contains("isFavorite=false"));
        }
    }

    @Nested
    @DisplayName("JSON 序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("空对象序列化测试")
        void testSerializeEmptyObject() throws JsonProcessingException {
            // When
            String json = objectMapper.writeValueAsString(orderFeeInfo);
            
            // Then
            assertNotNull(json);
            assertEquals("{}", json);
        }

        @Test
        @DisplayName("完整对象序列化测试")
        void testSerializeFullObject() throws JsonProcessingException {
            // Given
            OrderFeeInfo feeInfo = createFullOrderFeeInfo();
            
            // When
            String json = objectMapper.writeValueAsString(feeInfo);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"total_fee\":10000"));
            assertTrue(json.contains("\"send_fee\":500"));
            assertTrue(json.contains("\"user_fee\":9700"));
            assertTrue(json.contains("\"pay_type\":2"));
            assertTrue(json.contains("\"need_invoice\":true"));
        }

        @Test
        @DisplayName("反序列化测试")
        void testDeserialize() throws JsonProcessingException {
            // Given
            String json = "{\"total_fee\":10000,\"send_fee\":500,\"package_fee\":200,\"user_fee\":9700,\"pay_type\":2,\"need_invoice\":true}";
            
            // When
            OrderFeeInfo feeInfo = objectMapper.readValue(json, OrderFeeInfo.class);
            
            // Then
            assertNotNull(feeInfo);
            assertEquals(Integer.valueOf(10000), feeInfo.getTotalFee());
            assertEquals(Integer.valueOf(500), feeInfo.getSendFee());
            assertEquals(Integer.valueOf(200), feeInfo.getPackageFee());
            assertEquals(Integer.valueOf(9700), feeInfo.getUserFee());
            assertEquals(Integer.valueOf(2), feeInfo.getPayType());
            assertTrue(feeInfo.getNeedInvoice());
        }

        @Test
        @DisplayName("序列化后反序列化一致性测试")
        void testSerializeDeserializeConsistency() throws JsonProcessingException {
            // Given
            OrderFeeInfo original = createFullOrderFeeInfo();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderFeeInfo deserialized = objectMapper.readValue(json, OrderFeeInfo.class);
            
            // Then
            assertEquals(original.getTotalFee(), deserialized.getTotalFee());
            assertEquals(original.getSendFee(), deserialized.getSendFee());
            assertEquals(original.getPackageFee(), deserialized.getPackageFee());
            assertEquals(original.getDiscountFee(), deserialized.getDiscountFee());
            assertEquals(original.getShopFee(), deserialized.getShopFee());
            assertEquals(original.getUserFee(), deserialized.getUserFee());
            assertEquals(original.getPayType(), deserialized.getPayType());
            assertEquals(original.getNeedInvoice(), deserialized.getNeedInvoice());
            assertEquals(original.getCommission(), deserialized.getCommission());
            assertEquals(original.getIsFirst(), deserialized.getIsFirst());
            assertEquals(original.getIsFavorite(), deserialized.getIsFavorite());
        }

        @Test
        @DisplayName("包含空活动列表的序列化测试")
        void testSerializeWithEmptyActivity() throws JsonProcessingException {
            // Given
            orderFeeInfo.setActivity(Collections.emptyList());
            
            // When
            String json = objectMapper.writeValueAsString(orderFeeInfo);
            
            // Then
            assertTrue(json.contains("\"activity\":[]"));
        }

        @Test
        @DisplayName("布尔值序列化测试")
        void testSerializeBooleanValues() throws JsonProcessingException {
            // Given
            orderFeeInfo.setNeedInvoice(false);
            orderFeeInfo.setIsFirst(true);
            orderFeeInfo.setIsFavorite(null);
            
            // When
            String json = objectMapper.writeValueAsString(orderFeeInfo);
            
            // Then
            assertTrue(json.contains("\"need_invoice\":false"));
            assertTrue(json.contains("\"is_first\":true"));
            assertFalse(json.contains("\"is_favorite\":"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("最大整数值测试")
        void testMaxIntegerValues() {
            // Given
            orderFeeInfo.setTotalFee(Integer.MAX_VALUE);
            orderFeeInfo.setSendFee(Integer.MAX_VALUE);
            orderFeeInfo.setPackageFee(Integer.MAX_VALUE);
            
            // Then
            assertEquals(Integer.MAX_VALUE, orderFeeInfo.getTotalFee());
            assertEquals(Integer.MAX_VALUE, orderFeeInfo.getSendFee());
            assertEquals(Integer.MAX_VALUE, orderFeeInfo.getPackageFee());
        }

        @Test
        @DisplayName("最小整数值测试")
        void testMinIntegerValues() {
            // Given
            orderFeeInfo.setTotalFee(Integer.MIN_VALUE);
            orderFeeInfo.setSendFee(Integer.MIN_VALUE);
            orderFeeInfo.setPackageFee(Integer.MIN_VALUE);
            
            // Then
            assertEquals(Integer.MIN_VALUE, orderFeeInfo.getTotalFee());
            assertEquals(Integer.MIN_VALUE, orderFeeInfo.getSendFee());
            assertEquals(Integer.MIN_VALUE, orderFeeInfo.getPackageFee());
        }

        @Test
        @DisplayName("零值测试")
        void testZeroValues() {
            // Given
            orderFeeInfo.setTotalFee(0);
            orderFeeInfo.setSendFee(0);
            orderFeeInfo.setUserFee(0);
            
            // Then
            assertEquals(Integer.valueOf(0), orderFeeInfo.getTotalFee());
            assertEquals(Integer.valueOf(0), orderFeeInfo.getSendFee());
            assertEquals(Integer.valueOf(0), orderFeeInfo.getUserFee());
        }

        @Test
        @DisplayName("负数测试")
        void testNegativeValues() {
            // Given
            orderFeeInfo.setDiscountFee(-1000);
            orderFeeInfo.setCommission(-500);
            
            // Then
            assertEquals(Integer.valueOf(-1000), orderFeeInfo.getDiscountFee());
            assertEquals(Integer.valueOf(-500), orderFeeInfo.getCommission());
        }

        @Test
        @DisplayName("支付类型边界值测试")
        void testPayTypeBoundaryValues() {
            // Given & When & Then
            orderFeeInfo.setPayType(1);
            assertEquals(Integer.valueOf(1), orderFeeInfo.getPayType());
            
            orderFeeInfo.setPayType(2);
            assertEquals(Integer.valueOf(2), orderFeeInfo.getPayType());
            
            orderFeeInfo.setPayType(0);
            assertEquals(Integer.valueOf(0), orderFeeInfo.getPayType());
        }
    }

    /**
     * 创建完整的OrderFeeInfo对象用于测试
     */
    private OrderFeeInfo createFullOrderFeeInfo() {
        OrderFeeInfo feeInfo = new OrderFeeInfo();
        feeInfo.setTotalFee(10000);
        feeInfo.setSendFee(500);
        feeInfo.setPackageFee(200);
        feeInfo.setDiscountFee(1000);
        feeInfo.setShopFee(9000);
        feeInfo.setUserFee(9700);
        feeInfo.setPayType(2);
        feeInfo.setNeedInvoice(true);
        feeInfo.setInvoice(new InvoiceInfo());
        feeInfo.setActivity(Arrays.asList(new ActivityInfo()));
        feeInfo.setCommission(300);
        feeInfo.setIsFirst(false);
        feeInfo.setIsFavorite(true);
        return feeInfo;
    }
}