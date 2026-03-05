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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SelfDeliveryChangeReq 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("SelfDeliveryChangeReq 单元测试")
class SelfDeliveryChangeReqTest {

    private ObjectMapper objectMapper;
    private Validator validator;
    
    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            // When
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrderNo());
            assertNull(req.getSourceOrderNo());
            assertNull(req.getShopId());
            assertNull(req.getStatus());
            assertNull(req.getTag());
            assertNull(req.getRiderName());
            assertNull(req.getRiderPhone());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
            assertNull(req.getPickupCode());
            assertNull(req.getCancelType());
            assertNull(req.getCancelReason());
            assertNull(req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有属性")
        void fullArgsConstructor_ShouldSetAllProperties() {
            // Given
            String orderNo = "MT2023123001";
            String sourceOrderNo = "order123";
            String shopId = "shop123";
            String status = "DELIVERING";
            String tag = "配送中";
            String riderName = "张三";
            String riderPhone = "13800138000";
            String longitude = "116.397499";
            String latitude = "39.908722";
            String pickupCode = "1234";
            Integer cancelType = 1;
            String cancelReason = "用户取消";
            Long updateTime = 1640995200L;
            
            // When
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq(orderNo, sourceOrderNo, shopId, status, 
                    tag, riderName, riderPhone, longitude, latitude, pickupCode, 
                    cancelType, cancelReason, updateTime);
            
            // Then
            assertNotNull(req);
            assertEquals(orderNo, req.getOrderNo());
            assertEquals(sourceOrderNo, req.getSourceOrderNo());
            assertEquals(shopId, req.getShopId());
            assertEquals(status, req.getStatus());
            assertEquals(tag, req.getTag());
            assertEquals(riderName, req.getRiderName());
            assertEquals(riderPhone, req.getRiderPhone());
            assertEquals(longitude, req.getLongitude());
            assertEquals(latitude, req.getLatitude());
            assertEquals(pickupCode, req.getPickupCode());
            assertEquals(cancelType, req.getCancelType());
            assertEquals(cancelReason, req.getCancelReason());
            assertEquals(updateTime, req.getUpdateTime());
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void fullArgsConstructor_ShouldAcceptNullValues() {
            // When
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq(null, null, null, null, 
                    null, null, null, null, null, null, null, null, null);
            
            // Then
            assertNotNull(req);
            assertNull(req.getOrderNo());
            assertNull(req.getSourceOrderNo());
            assertNull(req.getShopId());
            assertNull(req.getStatus());
            assertNull(req.getTag());
            assertNull(req.getRiderName());
            assertNull(req.getRiderPhone());
            assertNull(req.getLongitude());
            assertNull(req.getLatitude());
            assertNull(req.getPickupCode());
            assertNull(req.getCancelType());
            assertNull(req.getCancelReason());
            assertNull(req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        private SelfDeliveryChangeReq req;

        @BeforeEach
        void setUp() {
            req = new SelfDeliveryChangeReq();
        }

        @Test
        @DisplayName("orderNo的getter和setter应该正常工作")
        void orderNo_GetterSetter_ShouldWork() {
            // Given
            String orderNo = "MT2023123001";
            
            // When
            req.setOrderNo(orderNo);
            
            // Then
            assertEquals(orderNo, req.getOrderNo());
        }

        @Test
        @DisplayName("sourceOrderNo的getter和setter应该正常工作")
        void sourceOrderNo_GetterSetter_ShouldWork() {
            // Given
            String sourceOrderNo = "order123";
            
            // When
            req.setSourceOrderNo(sourceOrderNo);
            
            // Then
            assertEquals(sourceOrderNo, req.getSourceOrderNo());
        }

        @Test
        @DisplayName("shopId的getter和setter应该正常工作")
        void shopId_GetterSetter_ShouldWork() {
            // Given
            String shopId = "shop123";
            
            // When
            req.setShopId(shopId);
            
            // Then
            assertEquals(shopId, req.getShopId());
        }

        @Test
        @DisplayName("status的getter和setter应该正常工作")
        void status_GetterSetter_ShouldWork() {
            // Given
            String status = "DELIVERING";
            
            // When
            req.setStatus(status);
            
            // Then
            assertEquals(status, req.getStatus());
        }

        @Test
        @DisplayName("tag的getter和setter应该正常工作")
        void tag_GetterSetter_ShouldWork() {
            // Given
            String tag = "配送中";
            
            // When
            req.setTag(tag);
            
            // Then
            assertEquals(tag, req.getTag());
        }

        @Test
        @DisplayName("riderName的getter和setter应该正常工作")
        void riderName_GetterSetter_ShouldWork() {
            // Given
            String riderName = "张三";
            
            // When
            req.setRiderName(riderName);
            
            // Then
            assertEquals(riderName, req.getRiderName());
        }

        @Test
        @DisplayName("riderPhone的getter和setter应该正常工作")
        void riderPhone_GetterSetter_ShouldWork() {
            // Given
            String riderPhone = "13800138000";
            
            // When
            req.setRiderPhone(riderPhone);
            
            // Then
            assertEquals(riderPhone, req.getRiderPhone());
        }

        @Test
        @DisplayName("longitude的getter和setter应该正常工作")
        void longitude_GetterSetter_ShouldWork() {
            // Given
            String longitude = "116.397499";
            
            // When
            req.setLongitude(longitude);
            
            // Then
            assertEquals(longitude, req.getLongitude());
        }

        @Test
        @DisplayName("latitude的getter和setter应该正常工作")
        void latitude_GetterSetter_ShouldWork() {
            // Given
            String latitude = "39.908722";
            
            // When
            req.setLatitude(latitude);
            
            // Then
            assertEquals(latitude, req.getLatitude());
        }

        @Test
        @DisplayName("pickupCode的getter和setter应该正常工作")
        void pickupCode_GetterSetter_ShouldWork() {
            // Given
            String pickupCode = "1234";
            
            // When
            req.setPickupCode(pickupCode);
            
            // Then
            assertEquals(pickupCode, req.getPickupCode());
        }

        @Test
        @DisplayName("cancelType的getter和setter应该正常工作")
        void cancelType_GetterSetter_ShouldWork() {
            // Given
            Integer cancelType = 1;
            
            // When
            req.setCancelType(cancelType);
            
            // Then
            assertEquals(cancelType, req.getCancelType());
        }

        @Test
        @DisplayName("cancelReason的getter和setter应该正常工作")
        void cancelReason_GetterSetter_ShouldWork() {
            // Given
            String cancelReason = "用户取消";
            
            // When
            req.setCancelReason(cancelReason);
            
            // Then
            assertEquals(cancelReason, req.getCancelReason());
        }

        @Test
        @DisplayName("updateTime的getter和setter应该正常工作")
        void updateTime_GetterSetter_ShouldWork() {
            // Given
            Long updateTime = System.currentTimeMillis() / 1000;
            
            // When
            req.setUpdateTime(updateTime);
            
            // Then
            assertEquals(updateTime, req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("验证注解测试")
    class ValidationTests {

        @Test
        @DisplayName("orderNo不能为空")
        void orderNo_ShouldNotBeBlank() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setOrderNo(null);
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "麦芽田订单号不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("orderNo不能为空字符串")
        void orderNo_ShouldNotBeEmptyString() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setOrderNo("");
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "麦芽田订单号不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("sourceOrderNo不能为空")
        void sourceOrderNo_ShouldNotBeBlank() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setSourceOrderNo(null);
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "原始订单号不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("sourceOrderNo不能为空字符串")
        void sourceOrderNo_ShouldNotBeEmptyString() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setSourceOrderNo("");
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "原始订单号不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为空")
        void shopId_ShouldNotBeBlank() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setShopId(null);
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("shopId不能为空字符串")
        void shopId_ShouldNotBeEmptyString() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setShopId("");
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "门店ID不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("status不能为空")
        void status_ShouldNotBeBlank() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setStatus(null);
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "配送状态不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("status不能为空字符串")
        void status_ShouldNotBeEmptyString() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            req.setStatus("");
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> "配送状态不能为空".equals(v.getMessage())));
        }

        @Test
        @DisplayName("有效对象应该通过验证")
        void validObject_ShouldPassValidation() {
            // Given
            SelfDeliveryChangeReq req = createValidReq();
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("可选字段为null应该通过验证")
        void optionalFieldsNull_ShouldPassValidation() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setOrderNo("MT2023123001");
            req.setSourceOrderNo("order123");
            req.setShopId("shop123");
            req.setStatus("DELIVERING");
            // 可选字段设置为null
            req.setTag(null);
            req.setRiderName(null);
            req.setRiderPhone(null);
            req.setLongitude(null);
            req.setLatitude(null);
            req.setPickupCode(null);
            req.setCancelType(null);
            req.setCancelReason(null);
            req.setUpdateTime(null);
            
            // When
            Set<ConstraintViolation<SelfDeliveryChangeReq>> violations = validator.validate(req);
            
            // Then
            assertTrue(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            
            // When & Then
            assertEquals(req, req);
        }

        @Test
        @DisplayName("具有相同属性的对象应该相等")
        void sameProperties_ShouldBeEqual() {
            // Given
            SelfDeliveryChangeReq req1 = createTestReq();
            SelfDeliveryChangeReq req2 = createTestReq();
            
            // When & Then
            assertEquals(req1, req2);
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            
            // When & Then
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            String other = "other";
            
            // When & Then
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("orderNo不同时应该不相等")
        void differentOrderNo_ShouldNotBeEqual() {
            // Given
            SelfDeliveryChangeReq req1 = createTestReq();
            SelfDeliveryChangeReq req2 = createTestReq();
            req2.setOrderNo("different");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("status不同时应该不相等")
        void differentStatus_ShouldNotBeEqual() {
            // Given
            SelfDeliveryChangeReq req1 = createTestReq();
            SelfDeliveryChangeReq req2 = createTestReq();
            req2.setStatus("DONE");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("riderName不同时应该不相等")
        void differentRiderName_ShouldNotBeEqual() {
            // Given
            SelfDeliveryChangeReq req1 = createTestReq();
            SelfDeliveryChangeReq req2 = createTestReq();
            req2.setRiderName("李四");
            
            // When & Then
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("longitude不同时应该不相等")
        void differentLongitude_ShouldNotBeEqual() {
            // Given
            SelfDeliveryChangeReq req1 = createTestReq();
            SelfDeliveryChangeReq req2 = createTestReq();
            req2.setLongitude("120.000000");
            
            // When & Then
            assertNotEquals(req1, req2);
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相等对象应该有相同的hashCode")
        void equalObjects_ShouldHaveSameHashCode() {
            // Given
            SelfDeliveryChangeReq req1 = createTestReq();
            SelfDeliveryChangeReq req2 = createTestReq();
            
            // When & Then
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("多次调用应该返回相同的hashCode")
        void multipleInvocations_ShouldReturnSameHashCode() {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            
            // When
            int hashCode1 = req.hashCode();
            int hashCode2 = req.hashCode();
            
            // Then
            assertEquals(hashCode1, hashCode2);
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含类名")
        void toString_ShouldContainClassName() {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("SelfDeliveryChangeReq"));
        }

        @Test
        @DisplayName("toString应该包含所有属性")
        void toString_ShouldContainAllProperties() {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertTrue(result.contains("orderNo"));
            assertTrue(result.contains("sourceOrderNo"));
            assertTrue(result.contains("shopId"));
            assertTrue(result.contains("status"));
            assertTrue(result.contains("tag"));
            assertTrue(result.contains("riderName"));
            assertTrue(result.contains("riderPhone"));
            assertTrue(result.contains("longitude"));
            assertTrue(result.contains("latitude"));
            assertTrue(result.contains("pickupCode"));
            assertTrue(result.contains("cancelType"));
            assertTrue(result.contains("cancelReason"));
            assertTrue(result.contains("updateTime"));
        }

        @Test
        @DisplayName("toString不应该返回null")
        void toString_ShouldNotReturnNull() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            
            // When
            String result = req.toString();
            
            // Then
            assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应该能够序列化为JSON")
        void object_ShouldSerializeToJson() throws Exception {
            // Given
            SelfDeliveryChangeReq req = createTestReq();
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order_no\":\"MT2023123001\""));
            assertTrue(json.contains("\"source_order_no\":\"order123\""));
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"status\":\"DELIVERING\""));
            assertTrue(json.contains("\"tag\":\"配送中\""));
            assertTrue(json.contains("\"rider_name\":\"张三\""));
            assertTrue(json.contains("\"rider_phone\":\"13800138000\""));
            assertTrue(json.contains("\"longitude\":\"116.397499\""));
            assertTrue(json.contains("\"latitude\":\"39.908722\""));
            assertTrue(json.contains("\"pickup_code\":\"1234\""));
            assertTrue(json.contains("\"cancel_type\":1"));
            assertTrue(json.contains("\"cancel_reason\":\"用户取消\""));
            assertTrue(json.contains("\"update_time\":1640995200"));
        }

        @Test
        @DisplayName("JSON应该能够反序列化为对象")
        void json_ShouldDeserializeToObject() throws Exception {
            // Given
            String json = "{\"order_no\":\"MT2023123001\",\"source_order_no\":\"order123\"," +
                         "\"shop_id\":\"shop123\",\"status\":\"DELIVERING\",\"tag\":\"配送中\"," +
                         "\"rider_name\":\"张三\",\"rider_phone\":\"13800138000\"," +
                         "\"longitude\":\"116.397499\",\"latitude\":\"39.908722\"," +
                         "\"pickup_code\":\"1234\",\"cancel_type\":1," +
                         "\"cancel_reason\":\"用户取消\",\"update_time\":1640995200}";
            
            // When
            SelfDeliveryChangeReq req = objectMapper.readValue(json, SelfDeliveryChangeReq.class);
            
            // Then
            assertNotNull(req);
            assertEquals("MT2023123001", req.getOrderNo());
            assertEquals("order123", req.getSourceOrderNo());
            assertEquals("shop123", req.getShopId());
            assertEquals("DELIVERING", req.getStatus());
            assertEquals("配送中", req.getTag());
            assertEquals("张三", req.getRiderName());
            assertEquals("13800138000", req.getRiderPhone());
            assertEquals("116.397499", req.getLongitude());
            assertEquals("39.908722", req.getLatitude());
            assertEquals("1234", req.getPickupCode());
            assertEquals(Integer.valueOf(1), req.getCancelType());
            assertEquals("用户取消", req.getCancelReason());
            assertEquals(Long.valueOf(1640995200L), req.getUpdateTime());
        }

        @Test
        @DisplayName("序列化后再反序列化应该得到相等的对象")
        void serializeAndDeserialize_ShouldGetEqualObject() throws Exception {
            // Given
            SelfDeliveryChangeReq original = createTestReq();
            
            // When
            String json = objectMapper.writeValueAsString(original);
            SelfDeliveryChangeReq deserialized = objectMapper.readValue(json, SelfDeliveryChangeReq.class);
            
            // Then
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("包含null字段的对象应该能够序列化")
        void objectWithNullFields_ShouldSerialize() throws Exception {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setOrderNo("MT2023123001");
            req.setSourceOrderNo("order123");
            req.setShopId("shop123");
            req.setStatus("DELIVERING");
            // 其他字段保持null
            
            // When
            String json = objectMapper.writeValueAsString(req);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"order_no\":\"MT2023123001\""));
            assertTrue(json.contains("\"status\":\"DELIVERING\""));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("所有字段为null时应该正常工作")
        void allFieldsNull_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            
            // When & Then
            assertDoesNotThrow(() -> {
                req.toString();
                req.hashCode();
                req.equals(new SelfDeliveryChangeReq());
            });
        }

        @Test
        @DisplayName("cancelType为0时应该正常工作")
        void cancelTypeZero_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setCancelType(0);
            
            // When & Then
            assertEquals(Integer.valueOf(0), req.getCancelType());
        }

        @Test
        @DisplayName("cancelType为负数时应该正常工作")
        void cancelTypeNegative_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setCancelType(-1);
            
            // When & Then
            assertEquals(Integer.valueOf(-1), req.getCancelType());
        }

        @Test
        @DisplayName("updateTime为0时应该正常工作")
        void updateTimeZero_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setUpdateTime(0L);
            
            // When & Then
            assertEquals(Long.valueOf(0L), req.getUpdateTime());
        }

        @Test
        @DisplayName("字符串字段为空字符串时应该正常工作")
        void stringFieldsEmpty_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setOrderNo("");
            req.setSourceOrderNo("");
            req.setShopId("");
            req.setStatus("");
            req.setTag("");
            req.setRiderName("");
            req.setRiderPhone("");
            req.setLongitude("");
            req.setLatitude("");
            req.setPickupCode("");
            req.setCancelReason("");
            
            // When & Then
            assertEquals("", req.getOrderNo());
            assertEquals("", req.getSourceOrderNo());
            assertEquals("", req.getShopId());
            assertEquals("", req.getStatus());
            assertEquals("", req.getTag());
            assertEquals("", req.getRiderName());
            assertEquals("", req.getRiderPhone());
            assertEquals("", req.getLongitude());
            assertEquals("", req.getLatitude());
            assertEquals("", req.getPickupCode());
            assertEquals("", req.getCancelReason());
        }

        @Test
        @DisplayName("字符串字段包含特殊字符时应该正常工作")
        void stringFieldsWithSpecialCharacters_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            String specialText = "测试@#$%^&*()_+{}|:<>?[]\\;'\",./-=`~";
            req.setOrderNo(specialText);
            req.setSourceOrderNo(specialText);
            req.setShopId(specialText);
            req.setStatus(specialText);
            req.setTag(specialText);
            req.setRiderName(specialText);
            req.setRiderPhone(specialText);
            req.setLongitude(specialText);
            req.setLatitude(specialText);
            req.setPickupCode(specialText);
            req.setCancelReason(specialText);
            
            // When & Then
            assertEquals(specialText, req.getOrderNo());
            assertEquals(specialText, req.getSourceOrderNo());
            assertEquals(specialText, req.getShopId());
            assertEquals(specialText, req.getStatus());
            assertEquals(specialText, req.getTag());
            assertEquals(specialText, req.getRiderName());
            assertEquals(specialText, req.getRiderPhone());
            assertEquals(specialText, req.getLongitude());
            assertEquals(specialText, req.getLatitude());
            assertEquals(specialText, req.getPickupCode());
            assertEquals(specialText, req.getCancelReason());
        }

        @Test
        @DisplayName("数字字段为最大值时应该正常工作")
        void numericFieldsMaxValue_ShouldWork() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            req.setCancelType(Integer.MAX_VALUE);
            req.setUpdateTime(Long.MAX_VALUE);
            
            // When & Then
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), req.getCancelType());
            assertEquals(Long.valueOf(Long.MAX_VALUE), req.getUpdateTime());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("配送状态应该支持有效值")
        void deliveryStatus_ShouldSupportValidValues() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            String[] validStatuses = {"PENDING", "GRABBED", "ATSHOP", "PICKUP", "DELIVERING", "DONE", "CANCEL", "EXPECT"};
            
            // When & Then
            for (String status : validStatuses) {
                req.setStatus(status);
                assertEquals(status, req.getStatus());
            }
        }

        @Test
        @DisplayName("取消类型应该支持有效值")
        void cancelType_ShouldSupportValidValues() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            
            // When & Then - 用户取消
            req.setCancelType(1);
            assertEquals(Integer.valueOf(1), req.getCancelType());
            
            // 商户取消
            req.setCancelType(2);
            assertEquals(Integer.valueOf(2), req.getCancelType());
            
            // 客服取消
            req.setCancelType(3);
            assertEquals(Integer.valueOf(3), req.getCancelType());
            
            // 系统取消
            req.setCancelType(4);
            assertEquals(Integer.valueOf(4), req.getCancelType());
            
            // 其他
            req.setCancelType(5);
            assertEquals(Integer.valueOf(5), req.getCancelType());
        }

        @Test
        @DisplayName("经纬度应该支持GPS坐标格式")
        void coordinates_ShouldSupportGpsFormat() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            String longitude = "116.397499";  // 北京天安门经度
            String latitude = "39.908722";    // 北京天安门纬度
            
            // When
            req.setLongitude(longitude);
            req.setLatitude(latitude);
            
            // Then
            assertEquals(longitude, req.getLongitude());
            assertEquals(latitude, req.getLatitude());
        }

        @Test
        @DisplayName("骑手信息应该支持中文姓名")
        void riderInfo_ShouldSupportChineseName() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            String chineseName = "张三";
            
            // When
            req.setRiderName(chineseName);
            
            // Then
            assertEquals(chineseName, req.getRiderName());
        }

        @Test
        @DisplayName("取货码应该支持数字格式")
        void pickupCode_ShouldSupportNumericFormat() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            String pickupCode = "1234";
            
            // When
            req.setPickupCode(pickupCode);
            
            // Then
            assertEquals(pickupCode, req.getPickupCode());
        }

        @Test
        @DisplayName("麦芽田订单号应该支持特定格式")
        void orderNo_ShouldSupportMytFormat() {
            // Given
            SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
            String mytOrderNo = "MT2023123001";
            
            // When
            req.setOrderNo(mytOrderNo);
            
            // Then
            assertEquals(mytOrderNo, req.getOrderNo());
        }
    }

    /**
     * 创建测试用的SelfDeliveryChangeReq对象
     */
    private SelfDeliveryChangeReq createTestReq() {
        SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
        req.setOrderNo("MT2023123001");
        req.setSourceOrderNo("order123");
        req.setShopId("shop123");
        req.setStatus("DELIVERING");
        req.setTag("配送中");
        req.setRiderName("张三");
        req.setRiderPhone("13800138000");
        req.setLongitude("116.397499");
        req.setLatitude("39.908722");
        req.setPickupCode("1234");
        req.setCancelType(1);
        req.setCancelReason("用户取消");
        req.setUpdateTime(1640995200L);
        return req;
    }

    /**
     * 创建有效的SelfDeliveryChangeReq对象（通过验证）
     */
    private SelfDeliveryChangeReq createValidReq() {
        SelfDeliveryChangeReq req = new SelfDeliveryChangeReq();
        req.setOrderNo("MT2023123001");
        req.setSourceOrderNo("order123");
        req.setShopId("shop123");
        req.setStatus("DELIVERING");
        req.setTag("配送中");
        req.setRiderName("张三");
        req.setRiderPhone("13800138000");
        req.setLongitude("116.397499");
        req.setLatitude("39.908722");
        req.setPickupCode("1234");
        req.setCancelType(1);
        req.setCancelReason("用户取消");
        req.setUpdateTime(1640995200L);
        return req;
    }
}