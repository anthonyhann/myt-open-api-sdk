/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CreateOrderReq单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("CreateOrderReq 测试")
class CreateOrderReqTest {

    private ObjectMapper objectMapper;
    private CreateOrderReq createOrderReq;
    private OrderInfo mockOrderInfo;
    private OrderCustomerInfo mockOrderCustomerInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        createOrderReq = new CreateOrderReq();
        
        // 创建模拟的OrderInfo对象
        mockOrderInfo = new OrderInfo();
        mockOrderInfo.setOrderId("test_order_123");
        mockOrderInfo.setShopId("test_shop_456");
        
        // 创建模拟的OrderCustomerInfo对象
        mockOrderCustomerInfo = new OrderCustomerInfo();
        mockOrderCustomerInfo.setRealName("张三");
        mockOrderCustomerInfo.setPhone("13800138000");
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            CreateOrderReq req = new CreateOrderReq();
            
            assertAll("默认构造函数验证",
                () -> assertNull(req.getOrder(), "order应该为null"),
                () -> assertNull(req.getOrderCustomer(), "orderCustomer应该为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void fullConstructor_ShouldSetAllFields() {
            Long updateTime = System.currentTimeMillis() / 1000;
            
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, updateTime);
            
            assertAll("全参构造函数验证",
                () -> assertEquals(mockOrderInfo, req.getOrder(), "order应该被正确设置"),
                () -> assertEquals(mockOrderCustomerInfo, req.getOrderCustomer(), "orderCustomer应该被正确设置"),
                () -> assertEquals(updateTime, req.getUpdateTime(), "updateTime应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该允许null值")
        void fullConstructor_ShouldAllowNullValues() {
            CreateOrderReq req = new CreateOrderReq(null, null, null);
            
            assertAll("null值验证",
                () -> assertNull(req.getOrder(), "order应该为null"),
                () -> assertNull(req.getOrderCustomer(), "orderCustomer应该为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime应该为null")
            );
        }
    }

    @Nested
    @DisplayName("继承特性测试")
    class InheritanceTests {

        @Test
        @DisplayName("CreateOrderReq应该继承OrderBaseData")
        void shouldExtendOrderBaseData() {
            assertTrue(createOrderReq instanceof OrderBaseData, "CreateOrderReq应该是OrderBaseData的子类");
        }

        @Test
        @DisplayName("应该能够访问父类的所有方法")
        void shouldAccessParentMethods() {
            Long updateTime = System.currentTimeMillis() / 1000;
            
            // 测试父类方法的访问
            createOrderReq.setOrder(mockOrderInfo);
            createOrderReq.setOrderCustomer(mockOrderCustomerInfo);
            createOrderReq.setUpdateTime(updateTime);
            
            assertAll("父类方法访问验证",
                () -> assertEquals(mockOrderInfo, createOrderReq.getOrder(), "应该能访问父类的getOrder方法"),
                () -> assertEquals(mockOrderCustomerInfo, createOrderReq.getOrderCustomer(), "应该能访问父类的getOrderCustomer方法"),
                () -> assertEquals(updateTime, createOrderReq.getUpdateTime(), "应该能访问父类的getUpdateTime方法")
            );
        }

        @Test
        @DisplayName("应该能够使用父类的equals方法")
        void shouldUseParentEquals() {
            CreateOrderReq req1 = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            CreateOrderReq req2 = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            
            // 由于没有重写equals方法，应该使用父类的equals实现
            assertEquals(req1, req2, "应该使用父类的equals方法进行比较");
        }

        @Test
        @DisplayName("应该能够使用父类的hashCode方法")
        void shouldUseParentHashCode() {
            CreateOrderReq req1 = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            CreateOrderReq req2 = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            
            // 由于没有重写hashCode方法，应该使用父类的hashCode实现
            assertEquals(req1.hashCode(), req2.hashCode(), "应该使用父类的hashCode方法");
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该重写父类方法并包含CreateOrderReq标识")
        void toString_ShouldOverrideParentAndIncludeClassName() {
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            String result = req.toString();
            
            assertAll("toString内容验证",
                () -> assertNotNull(result, "toString不应该返回null"),
                () -> assertTrue(result.contains("CreateOrderReq"), "应该包含CreateOrderReq类名"),
                () -> assertTrue(result.contains("order="), "应该包含order字段"),
                () -> assertTrue(result.contains("orderCustomer="), "应该包含orderCustomer字段"),
                () -> assertTrue(result.contains("updateTime="), "应该包含updateTime字段")
            );
        }

        @Test
        @DisplayName("toString应该正确处理null值")
        void toString_ShouldHandleNullValues() {
            CreateOrderReq req = new CreateOrderReq();
            String result = req.toString();
            
            assertAll("null值toString验证",
                () -> assertTrue(result.contains("order=null"), "应该正确显示null order"),
                () -> assertTrue(result.contains("orderCustomer=null"), "应该正确显示null orderCustomer"),
                () -> assertTrue(result.contains("updateTime=null"), "应该正确显示null updateTime")
            );
        }

        @Test
        @DisplayName("toString应该返回非null非空字符串")
        void toString_ShouldReturnNonNullNonEmptyString() {
            CreateOrderReq req = new CreateOrderReq();
            String result = req.toString();
            
            assertNotNull(result, "toString不应该返回null");
            assertFalse(result.isEmpty(), "toString不应该返回空字符串");
        }
    }

    @Nested
    @DisplayName("JSON序列化/反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该正确序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            Long updateTime = 1234567890L;
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, updateTime);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertAll("JSON序列化验证",
                () -> assertTrue(json.contains("\"order\""), "应该包含order字段"),
                () -> assertTrue(json.contains("\"order_customer\""), "应该包含order_customer字段"),
                () -> assertTrue(json.contains("\"update_time\""), "应该包含update_time字段"),
                () -> assertTrue(json.contains("\"update_time\":1234567890"), "应该正确序列化updateTime")
            );
        }

        @Test
        @DisplayName("应该正确从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"order\":{\"order_id\":\"test_123\",\"shop_id\":\"shop_456\"}," +
                         "\"order_customer\":{\"real_name\":\"张三\",\"phone\":\"13800138000\"}," +
                         "\"update_time\":1234567890}";
            
            CreateOrderReq req = objectMapper.readValue(json, CreateOrderReq.class);
            
            assertAll("JSON反序列化验证",
                () -> assertNotNull(req.getOrder(), "order应该不为null"),
                () -> assertNotNull(req.getOrderCustomer(), "orderCustomer应该不为null"),
                () -> assertEquals(Long.valueOf(1234567890L), req.getUpdateTime(), "updateTime应该正确反序列化")
            );
        }

        @Test
        @DisplayName("应该正确处理JSON中的null值")
        void shouldHandleNullValuesInJson() throws Exception {
            String json = "{\"order\":null,\"order_customer\":null,\"update_time\":null}";
            
            CreateOrderReq req = objectMapper.readValue(json, CreateOrderReq.class);
            
            assertAll("JSON null值处理验证",
                () -> assertNull(req.getOrder(), "order应该为null"),
                () -> assertNull(req.getOrderCustomer(), "orderCustomer应该为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime应该为null")
            );
        }

        @Test
        @DisplayName("序列化和反序列化应该保持一致")
        void serializationRoundTrip_ShouldMaintainConsistency() throws Exception {
            CreateOrderReq original = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            
            String json = objectMapper.writeValueAsString(original);
            CreateOrderReq deserialized = objectMapper.readValue(json, CreateOrderReq.class);
            
            assertAll("序列化一致性验证",
                () -> assertEquals(original.getUpdateTime(), deserialized.getUpdateTime(), "updateTime应该一致"),
                () -> assertNotNull(deserialized.getOrder(), "反序列化后order不应该为null"),
                () -> assertNotNull(deserialized.getOrderCustomer(), "反序列化后orderCustomer不应该为null")
            );
        }

        @Test
        @DisplayName("应该正确处理空JSON对象")
        void shouldHandleEmptyJsonObject() throws Exception {
            String json = "{}";
            
            CreateOrderReq req = objectMapper.readValue(json, CreateOrderReq.class);
            
            assertAll("空JSON对象处理验证",
                () -> assertNull(req.getOrder(), "order应该为null"),
                () -> assertNull(req.getOrderCustomer(), "orderCustomer应该为null"),
                () -> assertNull(req.getUpdateTime(), "updateTime应该为null")
            );
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应该处理极大的时间戳")
        void shouldHandleLargeTimestamps() {
            Long largeTimestamp = Long.MAX_VALUE;
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, largeTimestamp);
            
            assertEquals(largeTimestamp, req.getUpdateTime(), "应该能处理Long.MAX_VALUE的时间戳");
        }

        @Test
        @DisplayName("应该处理极小的时间戳")
        void shouldHandleSmallTimestamps() {
            Long smallTimestamp = 0L;
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, smallTimestamp);
            
            assertEquals(smallTimestamp, req.getUpdateTime(), "应该能处理0的时间戳");
        }

        @Test
        @DisplayName("应该处理负数时间戳")
        void shouldHandleNegativeTimestamps() {
            Long negativeTimestamp = -1L;
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, negativeTimestamp);
            
            assertEquals(negativeTimestamp, req.getUpdateTime(), "应该能处理负数时间戳");
        }

        @Test
        @DisplayName("应该处理复杂的嵌套对象")
        void shouldHandleComplexNestedObjects() {
            // 创建复杂的OrderInfo对象
            OrderInfo complexOrderInfo = new OrderInfo();
            complexOrderInfo.setOrderId("complex_order_123456789");
            complexOrderInfo.setShopId("complex_shop_987654321");
            complexOrderInfo.setShopName("测试店铺名称");
            complexOrderInfo.setTotalPrice(10000);
            complexOrderInfo.setBalancePrice(9000);
            complexOrderInfo.setCreateTime(1234567890L);
            complexOrderInfo.setDeliveryTime(1234567900L);
            
            // 创建复杂的OrderCustomerInfo对象
            OrderCustomerInfo complexCustomerInfo = new OrderCustomerInfo();
            complexCustomerInfo.setRealName("复杂姓名测试");
            complexCustomerInfo.setPhone("13800138000");
            complexCustomerInfo.setSecretPhone("138****8000");
            complexCustomerInfo.setAddress("复杂地址测试：北京市朝阳区望京SOHO T1 A座 1001室");
            complexCustomerInfo.setLongitude("116.456789");
            complexCustomerInfo.setLatitude("39.987654");
            
            CreateOrderReq req = new CreateOrderReq(complexOrderInfo, complexCustomerInfo, 1234567890L);
            
            assertAll("复杂嵌套对象验证",
                () -> assertNotNull(req.getOrder(), "复杂OrderInfo应该不为null"),
                () -> assertNotNull(req.getOrderCustomer(), "复杂OrderCustomerInfo应该不为null"),
                () -> assertEquals("complex_order_123456789", req.getOrder().getOrderId(), "应该正确设置复杂订单ID"),
                () -> assertEquals("复杂姓名测试", req.getOrderCustomer().getRealName(), "应该正确设置复杂客户姓名")
            );
        }
    }

    @Nested
    @DisplayName("业务逻辑验证测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("新订单推送请求应该包含完整订单信息")
        void createOrderRequest_ShouldContainCompleteOrderInfo() {
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, 1234567890L);
            
            assertAll("完整订单信息验证",
                () -> assertNotNull(req.getOrder(), "订单信息不应该为null"),
                () -> assertNotNull(req.getOrderCustomer(), "客户信息不应该为null"),
                () -> assertNotNull(req.getUpdateTime(), "更新时间不应该为null")
            );
        }

        @Test
        @DisplayName("更新时间应该为Unix时间戳格式")
        void updateTime_ShouldBeUnixTimestamp() {
            // 当前时间的Unix时间戳（秒）
            Long currentTimestamp = System.currentTimeMillis() / 1000;
            CreateOrderReq req = new CreateOrderReq(mockOrderInfo, mockOrderCustomerInfo, currentTimestamp);
            
            assertAll("Unix时间戳验证",
                () -> assertNotNull(req.getUpdateTime(), "时间戳不应该为null"),
                () -> assertTrue(req.getUpdateTime() > 0, "时间戳应该大于0"),
                () -> assertTrue(req.getUpdateTime().toString().length() >= 10, "Unix时间戳长度应该至少10位")
            );
        }

        @Test
        @DisplayName("CreateOrderReq应该用于三方服务推送新订单")
        void createOrderRequest_ShouldBeUsedForThirdPartyOrderPush() {
            // 模拟三方平台（如美团、饿了么）推送订单的场景
            OrderInfo thirdPartyOrder = new OrderInfo();
            thirdPartyOrder.setOrderId("meituan_order_12345");
            thirdPartyOrder.setShopId("meituan_shop_67890");
            thirdPartyOrder.setOriginOrderSn("MT123456789");
            thirdPartyOrder.setOriginTag("meituan");
            
            OrderCustomerInfo customerInfo = new OrderCustomerInfo();
            customerInfo.setRealName("张*生"); // 脱敏处理
            customerInfo.setPhone("15525426477_0067"); // 虚拟号
            customerInfo.setSecretPhone("155****6477"); // 隐私号
            
            Long orderTime = System.currentTimeMillis() / 1000;
            CreateOrderReq req = new CreateOrderReq(thirdPartyOrder, customerInfo, orderTime);
            
            assertAll("三方推送订单验证",
                () -> assertEquals("meituan_order_12345", req.getOrder().getOrderId(), "应该包含三方订单ID"),
                () -> assertEquals("meituan", req.getOrder().getOriginTag(), "应该标识原始订单来源"),
                () -> assertTrue(req.getOrderCustomer().getRealName().contains("*"), "客户姓名应该脱敏"),
                () -> assertTrue(req.getOrderCustomer().getPhone().contains("_"), "电话应该是虚拟号")
            );
        }

        @Test
        @DisplayName("应该支持不同类型的订单（即时单和预约单）")
        void shouldSupportDifferentOrderTypes() {
            // 即时单
            OrderInfo immediateOrder = new OrderInfo();
            immediateOrder.setOrderId("immediate_order_123");
            immediateOrder.setIsPreOrder(false);
            immediateOrder.setDeliveryTime(System.currentTimeMillis() / 1000 + 3600); // 1小时后
            
            // 预约单
            OrderInfo preOrder = new OrderInfo();
            preOrder.setOrderId("pre_order_456");
            preOrder.setIsPreOrder(true);
            preOrder.setDeliveryTime(System.currentTimeMillis() / 1000 + 86400); // 明天
            
            CreateOrderReq immediateReq = new CreateOrderReq(immediateOrder, mockOrderCustomerInfo, 1234567890L);
            CreateOrderReq preReq = new CreateOrderReq(preOrder, mockOrderCustomerInfo, 1234567890L);
            
            assertAll("不同订单类型验证",
                () -> assertEquals(Boolean.FALSE, immediateReq.getOrder().getIsPreOrder(), "即时单应该设置为false"),
                () -> assertEquals(Boolean.TRUE, preReq.getOrder().getIsPreOrder(), "预约单应该设置为true")
            );
        }

        @Test
        @DisplayName("应该支持自提订单")
        void shouldSupportPickupOrders() {
            OrderInfo pickupOrder = new OrderInfo();
            pickupOrder.setOrderId("pickup_order_789");
            pickupOrder.setIsPicker(true);
            pickupOrder.setPickTime(System.currentTimeMillis() / 1000 + 7200); // 2小时后自提
            pickupOrder.setPickupCode("ABCD1234");
            
            CreateOrderReq req = new CreateOrderReq(pickupOrder, mockOrderCustomerInfo, 1234567890L);
            
            assertAll("自提订单验证",
                () -> assertEquals(Boolean.TRUE, req.getOrder().getIsPicker(), "应该标识为自提订单"),
                () -> assertNotNull(req.getOrder().getPickTime(), "应该有自提时间"),
                () -> assertNotNull(req.getOrder().getPickupCode(), "应该有取货码")
            );
        }
    }

    @Nested
    @DisplayName("类型安全测试")
    class TypeSafetyTests {

        @Test
        @DisplayName("应该确保类型安全的继承关系")
        void shouldEnsureTypeSafeInheritance() {
            CreateOrderReq req = new CreateOrderReq();
            
            // 确保可以安全地转换为父类
            OrderBaseData baseData = req;
            assertNotNull(baseData, "应该能安全转换为父类");
            
            // 确保可以安全地从父类转换回来
            if (baseData instanceof CreateOrderReq) {
                CreateOrderReq convertedReq = (CreateOrderReq) baseData;
                assertNotNull(convertedReq, "应该能安全从父类转换回来");
            }
        }

        @Test
        @DisplayName("应该正确处理泛型类型")
        void shouldHandleGenericTypes() {
            CreateOrderReq req = new CreateOrderReq();
            
            // 测试设置和获取操作的类型安全性
            assertDoesNotThrow(() -> {
                req.setOrder(mockOrderInfo);
                req.setOrderCustomer(mockOrderCustomerInfo);
                req.setUpdateTime(1234567890L);
            }, "设置操作应该类型安全");
            
            assertDoesNotThrow(() -> {
                OrderInfo order = req.getOrder();
                OrderCustomerInfo customer = req.getOrderCustomer();
                Long updateTime = req.getUpdateTime();
            }, "获取操作应该类型安全");
        }
    }
}