/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShopPrinterListReq 实体类的 JUnit 5 单元测试
 */
@DisplayName("ShopPrinterListReq Tests")
class ShopPrinterListReqTest {

    private ShopPrinterListReq shopPrinterListReq;
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        shopPrinterListReq = new ShopPrinterListReq();
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空对象")
        void testDefaultConstructor() {
            ShopPrinterListReq req = new ShopPrinterListReq();
            
            assertNull(req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("基本构造函数应正确设置门店ID")
        void testBasicConstructor() {
            String shopId = "shop123";
            
            ShopPrinterListReq req = new ShopPrinterListReq(shopId);
            
            assertEquals(shopId, req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("普通分页构造函数应正确设置所有分页参数")
        void testNormalPaginationConstructor() {
            String shopId = "shop123";
            Integer page = 1;
            Integer pageSize = 20;
            
            ShopPrinterListReq req = new ShopPrinterListReq(shopId, page, pageSize);
            
            assertEquals(shopId, req.getShopId());
            assertEquals(page, req.getPage());
            assertEquals(pageSize, req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("游标分页构造函数应正确设置游标参数")
        void testScrollPaginationConstructor() {
            String shopId = "shop123";
            String scrollId = "scroll456";
            
            ShopPrinterListReq req = new ShopPrinterListReq(shopId, scrollId);
            
            assertEquals(shopId, req.getShopId());
            assertEquals(scrollId, req.getScrollId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullConstructor() {
            String shopId = "shop123";
            Integer page = 2;
            Integer pageSize = 30;
            String scrollId = "scroll456";
            
            ShopPrinterListReq req = new ShopPrinterListReq(shopId, page, pageSize, scrollId);
            
            assertEquals(shopId, req.getShopId());
            assertEquals(page, req.getPage());
            assertEquals(pageSize, req.getPageSize());
            assertEquals(scrollId, req.getScrollId());
        }

        @Test
        @DisplayName("构造函数应能处理null参数")
        void testConstructorWithNullParameters() {
            ShopPrinterListReq req = new ShopPrinterListReq(null, null, null, null);
            
            assertNull(req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId 的 getter 和 setter 应正常工作")
        void testShopIdGetterSetter() {
            String shopId = "shop123";
            shopPrinterListReq.setShopId(shopId);
            assertEquals(shopId, shopPrinterListReq.getShopId());
        }

        @Test
        @DisplayName("page 的 getter 和 setter 应正常工作")
        void testPageGetterSetter() {
            Integer page = 3;
            shopPrinterListReq.setPage(page);
            assertEquals(page, shopPrinterListReq.getPage());
        }

        @Test
        @DisplayName("pageSize 的 getter 和 setter 应正常工作")
        void testPageSizeGetterSetter() {
            Integer pageSize = 25;
            shopPrinterListReq.setPageSize(pageSize);
            assertEquals(pageSize, shopPrinterListReq.getPageSize());
        }

        @Test
        @DisplayName("scrollId 的 getter 和 setter 应正常工作")
        void testScrollIdGetterSetter() {
            String scrollId = "scroll789";
            shopPrinterListReq.setScrollId(scrollId);
            assertEquals(scrollId, shopPrinterListReq.getScrollId());
        }

        @Test
        @DisplayName("所有字段设置null值应正常工作")
        void testSetNullValues() {
            shopPrinterListReq.setShopId(null);
            shopPrinterListReq.setPage(null);
            shopPrinterListReq.setPageSize(null);
            shopPrinterListReq.setScrollId(null);
            
            assertNull(shopPrinterListReq.getShopId());
            assertNull(shopPrinterListReq.getPage());
            assertNull(shopPrinterListReq.getPageSize());
            assertNull(shopPrinterListReq.getScrollId());
        }

        @Test
        @DisplayName("字段设置空字符串应正常工作")
        void testSetEmptyStrings() {
            shopPrinterListReq.setShopId("");
            shopPrinterListReq.setScrollId("");
            
            assertEquals("", shopPrinterListReq.getShopId());
            assertEquals("", shopPrinterListReq.getScrollId());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop456", 1, 20, "scroll456");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 20);
            
            assertEquals(req, req);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123");
            
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123");
            String other = "different type";
            
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("所有字段都为null的对象应相等")
        void testEqualsWithAllNull() {
            ShopPrinterListReq req1 = new ShopPrinterListReq();
            ShopPrinterListReq req2 = new ShopPrinterListReq();
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("部分字段不同的对象应不相等")
        void testEqualsWithPartialDifference() {
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", 2, 20, "scroll456");
            
            assertNotEquals(req1, req2);
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段信息")
        void testToString() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopPrinterListReq"));
            assertTrue(result.contains("shopId='shop123'"));
            assertTrue(result.contains("page=1"));
            assertTrue(result.contains("pageSize=20"));
            assertTrue(result.contains("scrollId='scroll456'"));
        }

        @Test
        @DisplayName("空对象的toString应正确处理null值")
        void testToStringWithNullValues() {
            ShopPrinterListReq req = new ShopPrinterListReq();
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopPrinterListReq"));
            assertTrue(result.contains("shopId=null"));
            assertTrue(result.contains("page=null"));
            assertTrue(result.contains("pageSize=null"));
            assertTrue(result.contains("scrollId=null"));
        }

        @Test
        @DisplayName("包含空字符串的toString应正确处理")
        void testToStringWithEmptyValues() {
            ShopPrinterListReq req = new ShopPrinterListReq("", null, null, "");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopPrinterListReq"));
            assertTrue(result.contains("shopId=''"));
            assertTrue(result.contains("scrollId=''"));
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"page\":1"));
            assertTrue(json.contains("\"page_size\":20"));
            assertTrue(json.contains("\"scroll_id\":\"scroll456\""));
        }

        @Test
        @DisplayName("包含null值的对象应能正确序列化")
        void testSerializationWithNullValues() throws IOException {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", null, null, null);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"shop_id\":\"shop123\""));
            assertTrue(json.contains("\"page\":null"));
            assertTrue(json.contains("\"page_size\":null"));
            assertTrue(json.contains("\"scroll_id\":null"));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"page\":2,\"page_size\":30,\"scroll_id\":\"scroll456\"}";
            
            ShopPrinterListReq req = objectMapper.readValue(json, ShopPrinterListReq.class);
            
            assertEquals("shop123", req.getShopId());
            assertEquals(2, req.getPage().intValue());
            assertEquals(30, req.getPageSize().intValue());
            assertEquals("scroll456", req.getScrollId());
        }

        @Test
        @DisplayName("部分字段缺失的JSON应能正确反序列化")
        void testDeserializationWithMissingFields() throws IOException {
            String json = "{\"shop_id\":\"shop123\"}";
            
            ShopPrinterListReq req = objectMapper.readValue(json, ShopPrinterListReq.class);
            
            assertEquals("shop123", req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("包含null值的JSON应能正确反序列化")
        void testDeserializationWithNullValues() throws IOException {
            String json = "{\"shop_id\":\"shop123\",\"page\":null,\"page_size\":null,\"scroll_id\":null}";
            
            ShopPrinterListReq req = objectMapper.readValue(json, ShopPrinterListReq.class);
            
            assertEquals("shop123", req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("序列化和反序列化应保持数据一致性")
        void testSerializationDeserializationConsistency() throws IOException {
            ShopPrinterListReq original = new ShopPrinterListReq("shop123", 3, 15, "scroll789");
            
            String json = objectMapper.writeValueAsString(original);
            ShopPrinterListReq deserialized = objectMapper.readValue(json, ShopPrinterListReq.class);
            
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的请求对象应通过验证")
        void testValidRequest() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 20);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("空的shopId应触发验证错误")
        void testEmptyShopId() {
            ShopPrinterListReq req = new ShopPrinterListReq("", 1, 20);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("shopId")));
        }

        @Test
        @DisplayName("null的shopId应触发验证错误")
        void testNullShopId() {
            ShopPrinterListReq req = new ShopPrinterListReq(null, 1, 20);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("shopId")));
        }

        @Test
        @DisplayName("页码为0应触发验证错误")
        void testZeroPage() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 0, 20);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("page")));
        }

        @Test
        @DisplayName("页码为负数应触发验证错误")
        void testNegativePage() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", -1, 20);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("page")));
        }

        @Test
        @DisplayName("页大小为0应触发验证错误")
        void testZeroPageSize() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 0);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("pageSize")));
        }

        @Test
        @DisplayName("页大小为负数应触发验证错误")
        void testNegativePageSize() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, -10);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("pageSize")));
        }

        @Test
        @DisplayName("页大小超过1000应触发验证错误")
        void testOversizedPageSize() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 1001);
            
            Set<ConstraintViolation<ShopPrinterListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getPropertyPath().toString().equals("pageSize")));
        }

        @Test
        @DisplayName("边界值页大小应通过验证")
        void testBoundaryPageSize() {
            // 测试最小值
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 1);
            Set<ConstraintViolation<ShopPrinterListReq>> violations1 = validator.validate(req1);
            assertTrue(violations1.isEmpty());
            
            // 测试最大值
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", 1, 1000);
            Set<ConstraintViolation<ShopPrinterListReq>> violations2 = validator.validate(req2);
            assertTrue(violations2.isEmpty());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid应正确判断请求是否有效")
        void testIsValid() {
            // 有效请求
            ShopPrinterListReq validReq = new ShopPrinterListReq("shop123");
            assertTrue(validReq.isValid());
            
            // 无效请求 - null shopId
            ShopPrinterListReq invalidReq1 = new ShopPrinterListReq();
            assertFalse(invalidReq1.isValid());
            
            // 无效请求 - 空字符串 shopId
            ShopPrinterListReq invalidReq2 = new ShopPrinterListReq("");
            assertFalse(invalidReq2.isValid());
            
            // 无效请求 - 空格字符串 shopId
            ShopPrinterListReq invalidReq3 = new ShopPrinterListReq("  ");
            assertFalse(invalidReq3.isValid());
        }

        @Test
        @DisplayName("isNormalPagination应正确判断是否为普通分页")
        void testIsNormalPagination() {
            // 有效的普通分页
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 20);
            assertTrue(req1.isNormalPagination());
            
            // 缺少页码
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", null, 20);
            assertFalse(req2.isNormalPagination());
            
            // 缺少页大小
            ShopPrinterListReq req3 = new ShopPrinterListReq("shop123", 1, null);
            assertFalse(req3.isNormalPagination());
            
            // 页码为0
            ShopPrinterListReq req4 = new ShopPrinterListReq("shop123", 0, 20);
            assertFalse(req4.isNormalPagination());
            
            // 页大小为0
            ShopPrinterListReq req5 = new ShopPrinterListReq("shop123", 1, 0);
            assertFalse(req5.isNormalPagination());
        }

        @Test
        @DisplayName("isScrollPagination应正确判断是否为游标分页")
        void testIsScrollPagination() {
            // 有效的游标分页
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", "scroll456");
            assertTrue(req1.isScrollPagination());
            
            // 无效的游标分页 - null scrollId
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123");
            assertFalse(req2.isScrollPagination());
            
            // 无效的游标分页 - 空字符串 scrollId
            ShopPrinterListReq req3 = new ShopPrinterListReq("shop123", "");
            assertFalse(req3.isScrollPagination());
            
            // 无效的游标分页 - 空格字符串 scrollId
            ShopPrinterListReq req4 = new ShopPrinterListReq();
            req4.setShopId("shop123");
            req4.setScrollId("  ");
            assertFalse(req4.isScrollPagination());
        }

        @Test
        @DisplayName("isValidPagination应正确判断分页参数是否有效")
        void testIsValidPagination() {
            // 有效的普通分页
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 20);
            assertTrue(req1.isValidPagination());
            
            // 有效的游标分页
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", "scroll456");
            assertTrue(req2.isValidPagination());
            
            // 无效分页 - 没有任何分页参数
            ShopPrinterListReq req3 = new ShopPrinterListReq("shop123");
            assertFalse(req3.isValidPagination());
            
            // 无效分页 - 分页参数不完整
            ShopPrinterListReq req4 = new ShopPrinterListReq("shop123", 1, null);
            assertFalse(req4.isValidPagination());
        }

        @Test
        @DisplayName("getPaginationType应返回正确的分页类型描述")
        void testGetPaginationType() {
            // 游标分页
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", "scroll456");
            assertEquals("游标分页", req1.getPaginationType());
            
            // 普通分页
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", 1, 20);
            assertEquals("普通分页", req2.getPaginationType());
            
            // 无分页参数
            ShopPrinterListReq req3 = new ShopPrinterListReq("shop123");
            assertEquals("无分页参数", req3.getPaginationType());
            
            // 同时设置了两种分页参数 - 游标分页优先
            ShopPrinterListReq req4 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            assertEquals("游标分页", req4.getPaginationType());
        }

        @Test
        @DisplayName("resetToFirstPage应正确重置到第一页")
        void testResetToFirstPage() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 5, 20, "scroll456");
            
            req.resetToFirstPage();
            
            assertEquals(1, req.getPage().intValue());
            assertNull(req.getScrollId());
            assertEquals(20, req.getPageSize().intValue()); // pageSize应该保持不变
        }

        @Test
        @DisplayName("nextPage应正确移动到下一页")
        void testNextPage() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 3, 20);
            
            req.nextPage();
            
            assertEquals(4, req.getPage().intValue());
            
            // 当page为null时不应该抛出异常
            req.setPage(null);
            assertDoesNotThrow(() -> req.nextPage());
        }

        @Test
        @DisplayName("previousPage应正确移动到上一页")
        void testPreviousPage() {
            // 正常情况
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 3, 20);
            req1.previousPage();
            assertEquals(2, req1.getPage().intValue());
            
            // 第一页时不应该变为0
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", 1, 20);
            req2.previousPage();
            assertEquals(1, req2.getPage().intValue());
            
            // page为null时不应该抛出异常
            ShopPrinterListReq req3 = new ShopPrinterListReq("shop123");
            assertDoesNotThrow(() -> req3.previousPage());
        }
    }

    @Nested
    @DisplayName("Static Factory Methods Tests")
    class StaticFactoryMethodsTests {

        @Test
        @DisplayName("of应创建基本请求对象")
        void testOf() {
            ShopPrinterListReq req = ShopPrinterListReq.of("shop123");
            
            assertEquals("shop123", req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("ofPage应创建普通分页请求对象")
        void testOfPage() {
            ShopPrinterListReq req = ShopPrinterListReq.ofPage("shop123", 2, 30);
            
            assertEquals("shop123", req.getShopId());
            assertEquals(2, req.getPage().intValue());
            assertEquals(30, req.getPageSize().intValue());
            assertNull(req.getScrollId());
            assertTrue(req.isNormalPagination());
        }

        @Test
        @DisplayName("ofScroll应创建游标分页请求对象")
        void testOfScroll() {
            ShopPrinterListReq req = ShopPrinterListReq.ofScroll("shop123", "scroll456");
            
            assertEquals("shop123", req.getShopId());
            assertEquals("scroll456", req.getScrollId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertTrue(req.isScrollPagination());
        }

        @Test
        @DisplayName("firstPage应创建第一页请求对象（默认每页20条）")
        void testFirstPageDefault() {
            ShopPrinterListReq req = ShopPrinterListReq.firstPage("shop123");
            
            assertEquals("shop123", req.getShopId());
            assertEquals(1, req.getPage().intValue());
            assertEquals(20, req.getPageSize().intValue());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("firstPage应创建第一页请求对象（指定每页数量）")
        void testFirstPageWithSize() {
            ShopPrinterListReq req = ShopPrinterListReq.firstPage("shop123", 50);
            
            assertEquals("shop123", req.getShopId());
            assertEquals(1, req.getPage().intValue());
            assertEquals(50, req.getPageSize().intValue());
            assertNull(req.getScrollId());
        }
    }

    @Nested
    @DisplayName("Builder Tests")
    class BuilderTests {

        @Test
        @DisplayName("Builder应能正确构建请求对象")
        void testBuilder() {
            ShopPrinterListReq req = ShopPrinterListReq.builder()
                    .shopId("shop123")
                    .page(2)
                    .pageSize(30)
                    .scrollId("scroll456")
                    .build();
            
            assertEquals("shop123", req.getShopId());
            assertEquals(2, req.getPage().intValue());
            assertEquals(30, req.getPageSize().intValue());
            assertEquals("scroll456", req.getScrollId());
        }

        @Test
        @DisplayName("Builder应能处理部分字段")
        void testBuilderPartialFields() {
            ShopPrinterListReq req = ShopPrinterListReq.builder()
                    .shopId("shop123")
                    .page(1)
                    .build();
            
            assertEquals("shop123", req.getShopId());
            assertEquals(1, req.getPage().intValue());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("Builder应能处理空构建")
        void testBuilderEmpty() {
            ShopPrinterListReq req = ShopPrinterListReq.builder().build();
            
            assertNull(req.getShopId());
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("处理极端页码值")
        void testExtremePageValues() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123");
            
            // 测试最大整数值
            req.setPage(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, req.getPage().intValue());
            
            // 测试最小正整数值
            req.setPage(1);
            assertEquals(1, req.getPage().intValue());
        }

        @Test
        @DisplayName("处理极端页大小值")
        void testExtremePageSizeValues() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123");
            
            // 测试最大允许值
            req.setPageSize(1000);
            assertEquals(1000, req.getPageSize().intValue());
            
            // 测试最小正整数值
            req.setPageSize(1);
            assertEquals(1, req.getPageSize().intValue());
        }

        @Test
        @DisplayName("处理特殊字符的shopId")
        void testSpecialCharactersInShopId() {
            String specialShopId = "shop_123-test@example.com";
            ShopPrinterListReq req = new ShopPrinterListReq(specialShopId);
            
            assertEquals(specialShopId, req.getShopId());
            assertTrue(req.isValid());
        }

        @Test
        @DisplayName("处理很长的scrollId")
        void testLongScrollId() {
            StringBuilder longScrollId = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longScrollId.append("a");
            }
            
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", longScrollId.toString());
            
            assertEquals(longScrollId.toString(), req.getScrollId());
            assertTrue(req.isScrollPagination());
        }

        @Test
        @DisplayName("处理Unicode字符")
        void testUnicodeCharacters() {
            String unicodeShopId = "门店123";
            String unicodeScrollId = "游标456";
            
            ShopPrinterListReq req = new ShopPrinterListReq(unicodeShopId, unicodeScrollId);
            
            assertEquals(unicodeShopId, req.getShopId());
            assertEquals(unicodeScrollId, req.getScrollId());
            assertTrue(req.isValid());
            assertTrue(req.isScrollPagination());
        }

        @Test
        @DisplayName("同时设置普通分页和游标分页参数")
        void testMixedPaginationParameters() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            
            // 两种分页都应该被识别
            assertTrue(req.isNormalPagination());
            assertTrue(req.isScrollPagination());
            assertTrue(req.isValidPagination());
            
            // 游标分页应该优先
            assertEquals("游标分页", req.getPaginationType());
        }
    }

    @Nested
    @DisplayName("Performance Tests")
    class PerformanceTests {

        @Test
        @DisplayName("大量对象创建不应导致性能问题")
        void testMassObjectCreation() {
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    ShopPrinterListReq req = new ShopPrinterListReq("shop" + i, i % 10 + 1, 20, "scroll" + i);
                    assertEquals("shop" + i, req.getShopId());
                    assertTrue(req.isValid());
                }
            });
        }

        @Test
        @DisplayName("频繁的验证操作应高效执行")
        void testValidationPerformance() {
            ShopPrinterListReq req = new ShopPrinterListReq("shop123", 1, 20);
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    boolean isValid = req.isValid();
                    assertTrue(isValid);
                    
                    boolean isNormalPag = req.isNormalPagination();
                    assertTrue(isNormalPag);
                    
                    String paginationType = req.getPaginationType();
                    assertEquals("普通分页", paginationType);
                }
            });
        }

        @Test
        @DisplayName("equals和hashCode方法应高效执行")
        void testEqualsHashCodePerformance() {
            ShopPrinterListReq req1 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            ShopPrinterListReq req2 = new ShopPrinterListReq("shop123", 1, 20, "scroll456");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    boolean result = req1.equals(req2);
                    assertTrue(result);
                    
                    int hash1 = req1.hashCode();
                    int hash2 = req2.hashCode();
                    assertEquals(hash1, hash2);
                }
            });
        }
    }
}