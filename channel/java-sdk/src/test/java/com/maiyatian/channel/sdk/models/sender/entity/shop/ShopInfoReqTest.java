/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShopInfoReq 单元测试
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("ShopInfoReq 单元测试")
class ShopInfoReqTest {

    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void defaultConstructorShouldCreateEmptyObject() {
            // When
            ShopInfoReq request = new ShopInfoReq();

            // Then
            assertNotNull(request);
            assertNull(request.getShopId());
        }

        @Test
        @DisplayName("全参构造函数应正确初始化所有字段")
        void allArgsConstructorShouldInitializeAllFields() {
            // Given
            String shopId = "shop123";

            // When
            ShopInfoReq request = new ShopInfoReq(shopId);

            // Then
            assertNotNull(request);
            assertEquals(shopId, request.getShopId());
        }

        @Test
        @DisplayName("构造函数应接受null参数")
        void constructorShouldAcceptNullParameters() {
            // When
            ShopInfoReq request = new ShopInfoReq(null);

            // Then
            assertNotNull(request);
            assertNull(request.getShopId());
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId的getter和setter应正常工作")
        void shopIdGetterAndSetterShouldWork() {
            // Given
            ShopInfoReq request = new ShopInfoReq();
            String shopId = "shop456";

            // When
            request.setShopId(shopId);

            // Then
            assertEquals(shopId, request.getShopId());
        }

        @Test
        @DisplayName("设置null值应正常工作")
        void settingNullValueShouldWork() {
            // Given
            ShopInfoReq request = new ShopInfoReq("shop123");

            // When
            request.setShopId(null);

            // Then
            assertNull(request.getShopId());
        }

        @Test
        @DisplayName("设置空字符串应正常工作")
        void settingEmptyStringShouldWork() {
            // Given
            ShopInfoReq request = new ShopInfoReq();

            // When
            request.setShopId("");

            // Then
            assertEquals("", request.getShopId());
        }
    }

    @Nested
    @DisplayName("equals和hashCode方法测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象应相等")
        void sameObjectShouldBeEqual() {
            // Given
            ShopInfoReq request = new ShopInfoReq("shop123");

            // Then
            assertEquals(request, request);
            assertEquals(request.hashCode(), request.hashCode());
        }

        @Test
        @DisplayName("具有相同shopId的不同对象应相等")
        void differentObjectsWithSameShopIdShouldBeEqual() {
            // Given
            ShopInfoReq request1 = new ShopInfoReq("shop123");
            ShopInfoReq request2 = new ShopInfoReq("shop123");

            // Then
            assertEquals(request1, request2);
            assertEquals(request1.hashCode(), request2.hashCode());
        }

        @Test
        @DisplayName("具有不同shopId的对象应不相等")
        void objectsWithDifferentShopIdShouldNotBeEqual() {
            // Given
            ShopInfoReq request1 = new ShopInfoReq("shop123");
            ShopInfoReq request2 = new ShopInfoReq("shop456");

            // Then
            assertNotEquals(request1, request2);
        }

        @Test
        @DisplayName("一个shopId为null另一个不为null的对象应不相等")
        void objectsWithNullAndNonNullShopIdShouldNotBeEqual() {
            // Given
            ShopInfoReq request1 = new ShopInfoReq(null);
            ShopInfoReq request2 = new ShopInfoReq("shop123");

            // Then
            assertNotEquals(request1, request2);
            assertNotEquals(request2, request1);
        }

        @Test
        @DisplayName("两个shopId都为null的对象应相等")
        void objectsWithBothNullShopIdShouldBeEqual() {
            // Given
            ShopInfoReq request1 = new ShopInfoReq(null);
            ShopInfoReq request2 = new ShopInfoReq(null);

            // Then
            assertEquals(request1, request2);
            assertEquals(request1.hashCode(), request2.hashCode());
        }

        @Test
        @DisplayName("与null比较应返回false")
        void compareWithNullShouldReturnFalse() {
            // Given
            ShopInfoReq request = new ShopInfoReq("shop123");

            // Then
            assertNotEquals(request, null);
        }

        @Test
        @DisplayName("与不同类型对象比较应返回false")
        void compareWithDifferentTypeShouldReturnFalse() {
            // Given
            ShopInfoReq request = new ShopInfoReq("shop123");
            String otherObject = "shop123";

            // Then
            assertNotEquals(request, otherObject);
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
            ShopInfoReq request = new ShopInfoReq(shopId);

            // When
            String result = request.toString();

            // Then
            assertNotNull(result);
            assertTrue(result.contains("ShopInfoReq"));
            assertTrue(result.contains("shopId='" + shopId + "'"));
        }

        @Test
        @DisplayName("shopId为null时toString应正常工作")
        void toStringShouldWorkWhenShopIdIsNull() {
            // Given
            ShopInfoReq request = new ShopInfoReq(null);

            // When
            String result = request.toString();

            // Then
            assertNotNull(result);
            assertTrue(result.contains("ShopInfoReq"));
            assertTrue(result.contains("shopId='null'"));
        }
    }

    @Nested
    @DisplayName("JSON序列化/反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void objectShouldSerializeToJsonCorrectly() throws Exception {
            // Given
            String shopId = "shop123";
            ShopInfoReq request = new ShopInfoReq(shopId);

            // When
            String json = objectMapper.writeValueAsString(request);

            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"" + shopId + "\""));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void jsonShouldDeserializeToObjectCorrectly() throws Exception {
            // Given
            String json = "{\"shop_id\":\"shop123\"}";

            // When
            ShopInfoReq request = objectMapper.readValue(json, ShopInfoReq.class);

            // Then
            assertNotNull(request);
            assertEquals("shop123", request.getShopId());
        }

        @Test
        @DisplayName("序列化和反序列化应保持对象一致性")
        void serializationAndDeserializationShouldMaintainObjectIntegrity() throws Exception {
            // Given
            ShopInfoReq originalRequest = new ShopInfoReq("shop456");

            // When
            String json = objectMapper.writeValueAsString(originalRequest);
            ShopInfoReq deserializedRequest = objectMapper.readValue(json, ShopInfoReq.class);

            // Then
            assertEquals(originalRequest, deserializedRequest);
        }

        @Test
        @DisplayName("空JSON对象应能反序列化")
        void emptyJsonObjectShouldDeserialize() throws Exception {
            // Given
            String json = "{}";

            // When
            ShopInfoReq request = objectMapper.readValue(json, ShopInfoReq.class);

            // Then
            assertNotNull(request);
            assertNull(request.getShopId());
        }

        @Test
        @DisplayName("shopId为null的对象应能正确序列化")
        void objectWithNullShopIdShouldSerializeCorrectly() throws Exception {
            // Given
            ShopInfoReq request = new ShopInfoReq(null);

            // When
            String json = objectMapper.writeValueAsString(request);

            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":null"));
        }
    }

    @Nested
    @DisplayName("参数验证测试")
    class ValidationTests {

        @Test
        @DisplayName("有效的shopId应通过验证")
        void validShopIdShouldPassValidation() {
            // Given
            ShopInfoReq request = new ShopInfoReq("shop123");

            // When
            Set<ConstraintViolation<ShopInfoReq>> violations = validator.validate(request);

            // Then
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("null shopId应验证失败")
        void nullShopIdShouldFailValidation() {
            // Given
            ShopInfoReq request = new ShopInfoReq(null);

            // When
            Set<ConstraintViolation<ShopInfoReq>> violations = validator.validate(request);

            // Then
            assertFalse(violations.isEmpty());
            assertEquals(1, violations.size());
            ConstraintViolation<ShopInfoReq> violation = violations.iterator().next();
            assertEquals("门店ID不能为空", violation.getMessage());
        }

        @Test
        @DisplayName("空字符串shopId应验证失败")
        void emptyShopIdShouldFailValidation() {
            // Given
            ShopInfoReq request = new ShopInfoReq("");

            // When
            Set<ConstraintViolation<ShopInfoReq>> violations = validator.validate(request);

            // Then
            assertFalse(violations.isEmpty());
            assertEquals(1, violations.size());
            ConstraintViolation<ShopInfoReq> violation = violations.iterator().next();
            assertEquals("门店ID不能为空", violation.getMessage());
        }

        @Test
        @DisplayName("只包含空格的shopId应验证失败")
        void blankShopIdShouldFailValidation() {
            // Given
            ShopInfoReq request = new ShopInfoReq("   ");

            // When
            Set<ConstraintViolation<ShopInfoReq>> violations = validator.validate(request);

            // Then
            assertFalse(violations.isEmpty());
            assertEquals(1, violations.size());
            ConstraintViolation<ShopInfoReq> violation = violations.iterator().next();
            assertEquals("门店ID不能为空", violation.getMessage());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应能处理极长的shopId")
        void shouldHandleVeryLongShopId() {
            // Given
            StringBuilder longShopId = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longShopId.append("a");
            }
            String shopId = longShopId.toString();

            // When
            ShopInfoReq request = new ShopInfoReq(shopId);

            // Then
            assertNotNull(request);
            assertEquals(shopId, request.getShopId());
        }

        @Test
        @DisplayName("应能处理包含特殊字符的shopId")
        void shouldHandleSpecialCharactersInShopId() {
            // Given
            String shopId = "shop_123-456@example.com#$%^&*()";

            // When
            ShopInfoReq request = new ShopInfoReq(shopId);

            // Then
            assertNotNull(request);
            assertEquals(shopId, request.getShopId());
        }

        @Test
        @DisplayName("应能处理包含Unicode字符的shopId")
        void shouldHandleUnicodeCharactersInShopId() {
            // Given
            String shopId = "门店123_测试店铺";

            // When
            ShopInfoReq request = new ShopInfoReq(shopId);

            // Then
            assertNotNull(request);
            assertEquals(shopId, request.getShopId());
        }

        @Test
        @DisplayName("应能处理单字符shopId")
        void shouldHandleSingleCharacterShopId() {
            // Given
            String shopId = "1";

            // When
            ShopInfoReq request = new ShopInfoReq(shopId);

            // Then
            assertNotNull(request);
            assertEquals(shopId, request.getShopId());
        }
    }
}