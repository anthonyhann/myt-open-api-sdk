/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShopInfoResp 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("ShopInfoResp 单元测试")
class ShopInfoRespTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void defaultConstructorShouldCreateEmptyObject() {
            // When
            ShopInfoResp response = new ShopInfoResp();

            // Then
            assertNotNull(response);
            assertNull(response.getShopId());
            assertNull(response.getShopName());
            assertNull(response.getMerchantName());
            assertNull(response.getPhone());
            assertNull(response.getStatus());
        }

        @Test
        @DisplayName("全参构造函数应正确初始化所有字段")
        void allArgsConstructorShouldInitializeAllFields() {
            // Given
            String shopId = "shop123";
            String shopName = "测试门店";
            String merchantName = "测试商户";
            String phone = "13812345678";
            Long status = 1L;

            // When
            ShopInfoResp response = new ShopInfoResp(shopId, shopName, merchantName, phone, status);

            // Then
            assertNotNull(response);
            assertEquals(shopId, response.getShopId());
            assertEquals(shopName, response.getShopName());
            assertEquals(merchantName, response.getMerchantName());
            assertEquals(phone, response.getPhone());
            assertEquals(status, response.getStatus());
        }

        @Test
        @DisplayName("构造函数应接受null参数")
        void constructorShouldAcceptNullParameters() {
            // When
            ShopInfoResp response = new ShopInfoResp(null, null, null, null, null);

            // Then
            assertNotNull(response);
            assertNull(response.getShopId());
            assertNull(response.getShopName());
            assertNull(response.getMerchantName());
            assertNull(response.getPhone());
            assertNull(response.getStatus());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId的getter和setter应正常工作")
        void shopIdGetterAndSetterShouldWork() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            String shopId = "shop456";

            // When
            response.setShopId(shopId);

            // Then
            assertEquals(shopId, response.getShopId());
        }

        @Test
        @DisplayName("shopName的getter和setter应正常工作")
        void shopNameGetterAndSetterShouldWork() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            String shopName = "新门店名称";

            // When
            response.setShopName(shopName);

            // Then
            assertEquals(shopName, response.getShopName());
        }

        @Test
        @DisplayName("merchantName的getter和setter应正常工作")
        void merchantNameGetterAndSetterShouldWork() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            String merchantName = "新商户名称";

            // When
            response.setMerchantName(merchantName);

            // Then
            assertEquals(merchantName, response.getMerchantName());
        }

        @Test
        @DisplayName("phone的getter和setter应正常工作")
        void phoneGetterAndSetterShouldWork() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            String phone = "15812345678";

            // When
            response.setPhone(phone);

            // Then
            assertEquals(phone, response.getPhone());
        }

        @Test
        @DisplayName("status的getter和setter应正常工作")
        void statusGetterAndSetterShouldWork() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            Long status = 2L;

            // When
            response.setStatus(status);

            // Then
            assertEquals(status, response.getStatus());
        }

        @Test
        @DisplayName("设置null值应正常工作")
        void settingNullValuesShouldWork() {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);

            // When
            response.setShopId(null);
            response.setShopName(null);
            response.setMerchantName(null);
            response.setPhone(null);
            response.setStatus(null);

            // Then
            assertNull(response.getShopId());
            assertNull(response.getShopName());
            assertNull(response.getMerchantName());
            assertNull(response.getPhone());
            assertNull(response.getStatus());
        }
    }

    @Nested
    @DisplayName("equals和hashCode方法测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象应相等")
        void sameObjectShouldBeEqual() {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);

            // Then
            assertEquals(response, response);
            assertEquals(response.hashCode(), response.hashCode());
        }

        @Test
        @DisplayName("具有相同字段值的不同对象应相等")
        void differentObjectsWithSameFieldsShouldBeEqual() {
            // Given
            ShopInfoResp response1 = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);
            ShopInfoResp response2 = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);

            // Then
            assertEquals(response1, response2);
            assertEquals(response1.hashCode(), response2.hashCode());
        }

        @Test
        @DisplayName("具有不同字段值的对象应不相等")
        void objectsWithDifferentFieldsShouldNotBeEqual() {
            // Given
            ShopInfoResp response1 = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);
            ShopInfoResp response2 = new ShopInfoResp("shop456", "店铺", "商户", "123456", 1L);

            // Then
            assertNotEquals(response1, response2);
        }

        @Test
        @DisplayName("所有字段都为null的对象应相等")
        void objectsWithAllNullFieldsShouldBeEqual() {
            // Given
            ShopInfoResp response1 = new ShopInfoResp(null, null, null, null, null);
            ShopInfoResp response2 = new ShopInfoResp(null, null, null, null, null);

            // Then
            assertEquals(response1, response2);
            assertEquals(response1.hashCode(), response2.hashCode());
        }

        @Test
        @DisplayName("与null比较应返回false")
        void compareWithNullShouldReturnFalse() {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);

            // Then
            assertNotEquals(response, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应返回false")
        void compareWithDifferentTypeShouldReturnFalse() {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);
            String otherObject = "shop123";

            // Then
            assertNotEquals(response, otherObject);
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应返回包含所有字段的字符串")
        void toStringShouldContainAllFields() {
            // Given
            String shopId = "shop123";
            String shopName = "测试店铺";
            String merchantName = "测试商户";
            String phone = "13812345678";
            Long status = 1L;
            ShopInfoResp response = new ShopInfoResp(shopId, shopName, merchantName, phone, status);

            // When
            String result = response.toString();

            // Then
            assertNotNull(result);
            assertTrue(result.contains("ShopInfoResp"));
            assertTrue(result.contains("shopId='" + shopId + "'"));
            assertTrue(result.contains("shopName='" + shopName + "'"));
            assertTrue(result.contains("merchantName='" + merchantName + "'"));
            assertTrue(result.contains("phone='" + phone + "'"));
            assertTrue(result.contains("status=" + status));
        }

        @Test
        @DisplayName("字段为null时toString应正常工作")
        void toStringShouldWorkWhenFieldsAreNull() {
            // Given
            ShopInfoResp response = new ShopInfoResp(null, null, null, null, null);

            // When
            String result = response.toString();

            // Then
            assertNotNull(result);
            assertTrue(result.contains("ShopInfoResp"));
            assertTrue(result.contains("shopId='null'"));
            assertTrue(result.contains("shopName='null'"));
            assertTrue(result.contains("merchantName='null'"));
            assertTrue(result.contains("phone='null'"));
            assertTrue(result.contains("status=null"));
        }
    }

    @Nested
    @DisplayName("JSON序列化/反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void objectShouldSerializeToJsonCorrectly() throws Exception {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", "测试店铺", "测试商户", "13812345678", 1L);

            // When
            String json = objectMapper.writeValueAsString(response);

            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"shop_name\":\"测试店铺\""));
            assertTrue(json.contains("\"merchant_name\":\"测试商户\""));
            assertTrue(json.contains("\"phone\":\"13812345678\""));
            assertTrue(json.contains("\"status\":1"));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void jsonShouldDeserializeToObjectCorrectly() throws Exception {
            // Given
            String json = "{\"shop_id\":\"shop123\",\"shop_name\":\"测试店铺\",\"merchant_name\":\"测试商户\",\"phone\":\"13812345678\",\"status\":1}";

            // When
            ShopInfoResp response = objectMapper.readValue(json, ShopInfoResp.class);

            // Then
            assertNotNull(response);
            assertEquals("shop123", response.getShopId());
            assertEquals("测试店铺", response.getShopName());
            assertEquals("测试商户", response.getMerchantName());
            assertEquals("13812345678", response.getPhone());
            assertEquals(Long.valueOf(1), response.getStatus());
        }

        @Test
        @DisplayName("序列化和反序列化应保持对象一致性")
        void serializationAndDeserializationShouldMaintainObjectIntegrity() throws Exception {
            // Given
            ShopInfoResp originalResponse = new ShopInfoResp("shop456", "店铺", "商户", "15812345678", 2L);

            // When
            String json = objectMapper.writeValueAsString(originalResponse);
            ShopInfoResp deserializedResponse = objectMapper.readValue(json, ShopInfoResp.class);

            // Then
            assertEquals(originalResponse, deserializedResponse);
        }

        @Test
        @DisplayName("空JSON对象应能反序列化")
        void emptyJsonObjectShouldDeserialize() throws Exception {
            // Given
            String json = "{}";

            // When
            ShopInfoResp response = objectMapper.readValue(json, ShopInfoResp.class);

            // Then
            assertNotNull(response);
            assertNull(response.getShopId());
            assertNull(response.getShopName());
            assertNull(response.getMerchantName());
            assertNull(response.getPhone());
            assertNull(response.getStatus());
        }

        @Test
        @DisplayName("部分字段为null的对象应能正确序列化")
        void objectWithNullFieldsShouldSerializeCorrectly() throws Exception {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", null, null, null, null);

            // When
            String json = objectMapper.writeValueAsString(response);

            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"shop_name\":null"));
            assertTrue(json.contains("\"merchant_name\":null"));
            assertTrue(json.contains("\"phone\":null"));
            assertTrue(json.contains("\"status\":null"));
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicMethodsTests {

        @Test
        @DisplayName("getStatusDescription - 营业中状态")
        void getStatusDescriptionShouldReturnOpenForStatus1() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(1L);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("营业中", description);
        }

        @Test
        @DisplayName("getStatusDescription - 休息中状态")
        void getStatusDescriptionShouldReturnRestingForStatus2() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(2L);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("休息中", description);
        }

        @Test
        @DisplayName("getStatusDescription - 已打烊状态")
        void getStatusDescriptionShouldReturnClosedForStatus3() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(3L);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("已打烊", description);
        }

        @Test
        @DisplayName("getStatusDescription - 未知状态")
        void getStatusDescriptionShouldReturnUnknownForOtherStatus() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(999L);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("未知状态", description);
        }

        @Test
        @DisplayName("getStatusDescription - null状态")
        void getStatusDescriptionShouldReturnUnknownForNullStatus() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(null);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("未知", description);
        }

        @Test
        @DisplayName("isOpen - 营业中应返回true")
        void isOpenShouldReturnTrueForStatus1() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(1L);

            // When
            boolean isOpen = response.isOpen();

            // Then
            assertTrue(isOpen);
        }

        @Test
        @DisplayName("isOpen - 非营业状态应返回false")
        void isOpenShouldReturnFalseForNonOpenStatus() {
            // Given
            ShopInfoResp response1 = new ShopInfoResp();
            response1.setStatus(2L);
            
            ShopInfoResp response2 = new ShopInfoResp();
            response2.setStatus(3L);

            // When & Then
            assertFalse(response1.isOpen());
            assertFalse(response2.isOpen());
        }

        @Test
        @DisplayName("isOpen - null状态应返回false")
        void isOpenShouldReturnFalseForNullStatus() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(null);

            // When
            boolean isOpen = response.isOpen();

            // Then
            assertFalse(isOpen);
        }

        @Test
        @DisplayName("isOpen - 未知状态应返回false")
        void isOpenShouldReturnFalseForUnknownStatus() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(999L);

            // When
            boolean isOpen = response.isOpen();

            // Then
            assertFalse(isOpen);
        }

        @Test
        @DisplayName("业务逻辑方法应有@JsonIgnore注解以防止序列化")
        void businessLogicMethodsShouldHaveJsonIgnoreAnnotation() throws Exception {
            // Given
            ShopInfoResp response = new ShopInfoResp("shop123", "店铺", "商户", "123456", 1L);

            // When
            String json = objectMapper.writeValueAsString(response);

            // Then
            // 业务逻辑方法的返回值不应该出现在JSON中
            assertFalse(json.contains("\"statusDescription\""));
            assertFalse(json.contains("\"open\""));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应能处理极长的字符串字段")
        void shouldHandleVeryLongStringFields() {
            // Given
            StringBuilder longString = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longString.append("a");
            }
            String longValue = longString.toString();

            // When
            ShopInfoResp response = new ShopInfoResp(longValue, longValue, longValue, longValue, 1L);

            // Then
            assertNotNull(response);
            assertEquals(longValue, response.getShopId());
            assertEquals(longValue, response.getShopName());
            assertEquals(longValue, response.getMerchantName());
            assertEquals(longValue, response.getPhone());
        }

        @Test
        @DisplayName("应能处理包含特殊字符的字段")
        void shouldHandleSpecialCharactersInFields() {
            // Given
            String specialChars = "店铺@#$%^&*()_+-={}[]|\\:;\"'<>?,./";

            // When
            ShopInfoResp response = new ShopInfoResp(specialChars, specialChars, specialChars, specialChars, 1L);

            // Then
            assertNotNull(response);
            assertEquals(specialChars, response.getShopId());
            assertEquals(specialChars, response.getShopName());
            assertEquals(specialChars, response.getMerchantName());
            assertEquals(specialChars, response.getPhone());
        }

        @Test
        @DisplayName("应能处理包含Unicode字符的字段")
        void shouldHandleUnicodeCharactersInFields() {
            // Given
            String unicode = "门店🏪测试店铺😀商户名称";

            // When
            ShopInfoResp response = new ShopInfoResp(unicode, unicode, unicode, unicode, 1L);

            // Then
            assertNotNull(response);
            assertEquals(unicode, response.getShopId());
            assertEquals(unicode, response.getShopName());
            assertEquals(unicode, response.getMerchantName());
            assertEquals(unicode, response.getPhone());
        }

        @Test
        @DisplayName("应能处理空字符串字段")
        void shouldHandleEmptyStringFields() {
            // Given
            String empty = "";

            // When
            ShopInfoResp response = new ShopInfoResp(empty, empty, empty, empty, 0L);

            // Then
            assertNotNull(response);
            assertEquals(empty, response.getShopId());
            assertEquals(empty, response.getShopName());
            assertEquals(empty, response.getMerchantName());
            assertEquals(empty, response.getPhone());
        }

        @Test
        @DisplayName("应能处理极大和极小的status值")
        void shouldHandleExtremeStatusValues() {
            // Given
            Long maxStatus = Long.MAX_VALUE;
            Long minStatus = Long.MIN_VALUE;

            // When
            ShopInfoResp response1 = new ShopInfoResp("shop1", "店铺1", "商户1", "123456", maxStatus);
            ShopInfoResp response2 = new ShopInfoResp("shop2", "店铺2", "商户2", "654321", minStatus);

            // Then
            assertEquals(maxStatus, response1.getStatus());
            assertEquals(minStatus, response2.getStatus());
            assertEquals("未知状态", response1.getStatusDescription());
            assertEquals("未知状态", response2.getStatusDescription());
            assertFalse(response1.isOpen());
            assertFalse(response2.isOpen());
        }

        @Test
        @DisplayName("getStatusDescription应能处理status为0的情况")
        void getStatusDescriptionShouldHandleZeroStatus() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(0L);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("未知状态", description);
        }

        @Test
        @DisplayName("getStatusDescription应能处理负数status")
        void getStatusDescriptionShouldHandleNegativeStatus() {
            // Given
            ShopInfoResp response = new ShopInfoResp();
            response.setStatus(-1L);

            // When
            String description = response.getStatusDescription();

            // Then
            assertEquals("未知状态", description);
        }
    }
}