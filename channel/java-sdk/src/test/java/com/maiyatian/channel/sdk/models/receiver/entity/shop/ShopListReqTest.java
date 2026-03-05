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
 * ShopListReq 实体类的 JUnit 5 单元测试
 */
@DisplayName("ShopListReq Tests")
class ShopListReqTest {

    private ShopListReq shopListReq;
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        shopListReq = new ShopListReq();
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
            ShopListReq req = new ShopListReq();
            
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("普通分页构造函数应正确设置参数")
        void testNormalPaginationConstructor() {
            Integer page = 1;
            Integer pageSize = 20;
            
            ShopListReq req = new ShopListReq(page, pageSize);
            
            assertEquals(page, req.getPage());
            assertEquals(pageSize, req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("游标分页构造函数应正确设置参数")
        void testScrollPaginationConstructor() {
            String scrollId = "scroll123";
            
            ShopListReq req = new ShopListReq(scrollId);
            
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertEquals(scrollId, req.getScrollId());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullConstructor() {
            Integer page = 1;
            Integer pageSize = 20;
            String scrollId = "scroll123";
            
            ShopListReq req = new ShopListReq(page, pageSize, scrollId);
            
            assertEquals(page, req.getPage());
            assertEquals(pageSize, req.getPageSize());
            assertEquals(scrollId, req.getScrollId());
        }

        @Test
        @DisplayName("构造函数应能处理null参数")
        void testConstructorWithNullParameters() {
            ShopListReq req1 = new ShopListReq(null, null);
            ShopListReq req2 = new ShopListReq((String) null);
            ShopListReq req3 = new ShopListReq(null, null, null);
            
            assertNull(req1.getPage());
            assertNull(req1.getPageSize());
            assertNull(req1.getScrollId());
            
            assertNull(req2.getPage());
            assertNull(req2.getPageSize());
            assertNull(req2.getScrollId());
            
            assertNull(req3.getPage());
            assertNull(req3.getPageSize());
            assertNull(req3.getScrollId());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("page 的 getter 和 setter 应正常工作")
        void testPageGetterSetter() {
            Integer page = 1;
            shopListReq.setPage(page);
            assertEquals(page, shopListReq.getPage());
        }

        @Test
        @DisplayName("pageSize 的 getter 和 setter 应正常工作")
        void testPageSizeGetterSetter() {
            Integer pageSize = 20;
            shopListReq.setPageSize(pageSize);
            assertEquals(pageSize, shopListReq.getPageSize());
        }

        @Test
        @DisplayName("scrollId 的 getter 和 setter 应正常工作")
        void testScrollIdGetterSetter() {
            String scrollId = "scroll123";
            shopListReq.setScrollId(scrollId);
            assertEquals(scrollId, shopListReq.getScrollId());
        }

        @Test
        @DisplayName("设置null值应正常工作")
        void testSetNullValues() {
            shopListReq.setPage(null);
            shopListReq.setPageSize(null);
            shopListReq.setScrollId(null);
            
            assertNull(shopListReq.getPage());
            assertNull(shopListReq.getPageSize());
            assertNull(shopListReq.getScrollId());
        }

        @Test
        @DisplayName("设置负数和零值应正常工作")
        void testSetNegativeAndZeroValues() {
            shopListReq.setPage(0);
            shopListReq.setPageSize(-1);
            
            assertEquals(Integer.valueOf(0), shopListReq.getPage());
            assertEquals(Integer.valueOf(-1), shopListReq.getPageSize());
        }

        @Test
        @DisplayName("设置空字符串scrollId应正常工作")
        void testSetEmptyScrollId() {
            shopListReq.setScrollId("");
            assertEquals("", shopListReq.getScrollId());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            ShopListReq req1 = new ShopListReq(1, 20, "scroll123");
            ShopListReq req2 = new ShopListReq(1, 20, "scroll123");
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            ShopListReq req1 = new ShopListReq(1, 20, "scroll123");
            ShopListReq req2 = new ShopListReq(2, 20, "scroll123");
            
            assertNotEquals(req1, req2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            assertEquals(req, req);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            assertNotEquals(req, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            String other = "different type";
            
            assertNotEquals(req, other);
        }

        @Test
        @DisplayName("所有字段都为null的对象应相等")
        void testEqualsWithAllNull() {
            ShopListReq req1 = new ShopListReq(null, null, null);
            ShopListReq req2 = new ShopListReq(null, null, null);
            
            assertEquals(req1, req2);
            assertEquals(req1.hashCode(), req2.hashCode());
        }

        @Test
        @DisplayName("部分字段不同的对象应不相等")
        void testEqualsWithPartialDifference() {
            ShopListReq req1 = new ShopListReq(1, 20, "scroll123");
            ShopListReq req2 = new ShopListReq(1, 30, "scroll123");
            
            assertNotEquals(req1, req2);
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段信息")
        void testToString() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopListReq"));
            assertTrue(result.contains("page=1"));
            assertTrue(result.contains("pageSize=20"));
            assertTrue(result.contains("scrollId='scroll123'"));
        }

        @Test
        @DisplayName("空对象的toString应正确处理null值")
        void testToStringWithNullValues() {
            ShopListReq req = new ShopListReq();
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopListReq"));
            assertTrue(result.contains("page=null"));
            assertTrue(result.contains("pageSize=null"));
            assertTrue(result.contains("scrollId=null"));
        }

        @Test
        @DisplayName("包含空字符串的toString应正确处理")
        void testToStringWithEmptyScrollId() {
            ShopListReq req = new ShopListReq(1, 20, "");
            
            String result = req.toString();
            
            assertTrue(result.contains("ShopListReq"));
            assertTrue(result.contains("page=1"));
            assertTrue(result.contains("pageSize=20"));
            assertTrue(result.contains("scrollId=''"));
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"page\":1"));
            assertTrue(json.contains("\"page_size\":20"));
            assertTrue(json.contains("\"scroll_id\":\"scroll123\""));
        }

        @Test
        @DisplayName("包含null值的对象应能正确序列化")
        void testSerializationWithNullValues() throws IOException {
            ShopListReq req = new ShopListReq(1, null, null);
            
            String json = objectMapper.writeValueAsString(req);
            
            assertTrue(json.contains("\"page\":1"));
            assertTrue(json.contains("\"page_size\":null"));
            assertTrue(json.contains("\"scroll_id\":null"));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"page\":1,\"page_size\":20,\"scroll_id\":\"scroll123\"}";
            
            ShopListReq req = objectMapper.readValue(json, ShopListReq.class);
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
            assertEquals("scroll123", req.getScrollId());
        }

        @Test
        @DisplayName("部分字段缺失的JSON应能正确反序列化")
        void testDeserializationWithMissingFields() throws IOException {
            String json = "{\"page\":1}";
            
            ShopListReq req = objectMapper.readValue(json, ShopListReq.class);
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("包含null值的JSON应能正确反序列化")
        void testDeserializationWithNullValues() throws IOException {
            String json = "{\"page\":1,\"page_size\":null,\"scroll_id\":null}";
            
            ShopListReq req = objectMapper.readValue(json, ShopListReq.class);
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("序列化和反序列化应保持数据一致性")
        void testSerializationDeserializationConsistency() throws IOException {
            ShopListReq original = new ShopListReq(1, 20, "scroll123");
            
            String json = objectMapper.writeValueAsString(original);
            ShopListReq deserialized = objectMapper.readValue(json, ShopListReq.class);
            
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的普通分页请求应通过验证")
        void testValidNormalPagination() {
            ShopListReq req = new ShopListReq(1, 20);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("有效的游标分页请求应通过验证")
        void testValidScrollPagination() {
            ShopListReq req = new ShopListReq("scroll123");
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("页码为0应验证失败")
        void testInvalidPageZero() {
            ShopListReq req = new ShopListReq(0, 20);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("页码必须大于等于1")));
        }

        @Test
        @DisplayName("页码为负数应验证失败")
        void testInvalidPageNegative() {
            ShopListReq req = new ShopListReq(-1, 20);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("页码必须大于等于1")));
        }

        @Test
        @DisplayName("页大小为0应验证失败")
        void testInvalidPageSizeZero() {
            ShopListReq req = new ShopListReq(1, 0);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("每页数量必须大于0")));
        }

        @Test
        @DisplayName("页大小为负数应验证失败")
        void testInvalidPageSizeNegative() {
            ShopListReq req = new ShopListReq(1, -1);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("每页数量必须大于0")));
        }

        @Test
        @DisplayName("页大小超过1000应验证失败")
        void testInvalidPageSizeExceedsMax() {
            ShopListReq req = new ShopListReq(1, 1001);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("每页数量不能超过1000")));
        }

        @Test
        @DisplayName("页大小为1000应通过验证")
        void testValidPageSizeMax() {
            ShopListReq req = new ShopListReq(1, 1000);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("页大小为1应通过验证")
        void testValidPageSizeMin() {
            ShopListReq req = new ShopListReq(1, 1);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("所有字段为null不应有验证错误")
        void testAllNullFields() {
            ShopListReq req = new ShopListReq(null, null, null);
            
            Set<ConstraintViolation<ShopListReq>> violations = validator.validate(req);
            
            assertTrue(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("isNormalPagination应在有有效页码和页大小时返回true")
        void testIsNormalPaginationTrue() {
            ShopListReq req = new ShopListReq(1, 20);
            
            assertTrue(req.isNormalPagination());
        }

        @Test
        @DisplayName("isNormalPagination应在页码或页大小为null时返回false")
        void testIsNormalPaginationFalseWithNull() {
            ShopListReq req1 = new ShopListReq(null, 20);
            ShopListReq req2 = new ShopListReq(1, null);
            ShopListReq req3 = new ShopListReq(null, null);
            
            assertFalse(req1.isNormalPagination());
            assertFalse(req2.isNormalPagination());
            assertFalse(req3.isNormalPagination());
        }

        @Test
        @DisplayName("isNormalPagination应在页码或页大小为0或负数时返回false")
        void testIsNormalPaginationFalseWithInvalidValues() {
            ShopListReq req1 = new ShopListReq(0, 20);
            ShopListReq req2 = new ShopListReq(1, 0);
            ShopListReq req3 = new ShopListReq(-1, 20);
            ShopListReq req4 = new ShopListReq(1, -1);
            
            assertFalse(req1.isNormalPagination());
            assertFalse(req2.isNormalPagination());
            assertFalse(req3.isNormalPagination());
            assertFalse(req4.isNormalPagination());
        }

        @Test
        @DisplayName("isScrollPagination应在有有效scrollId时返回true")
        void testIsScrollPaginationTrue() {
            ShopListReq req = new ShopListReq("scroll123");
            
            assertTrue(req.isScrollPagination());
        }

        @Test
        @DisplayName("isScrollPagination应在scrollId为null或空字符串时返回false")
        void testIsScrollPaginationFalse() {
            ShopListReq req1 = new ShopListReq((String) null);
            ShopListReq req2 = new ShopListReq("");
            ShopListReq req3 = new ShopListReq("   ");
            
            assertFalse(req1.isScrollPagination());
            assertFalse(req2.isScrollPagination());
            assertFalse(req3.isScrollPagination());
        }

        @Test
        @DisplayName("isValidPagination应在普通分页有效时返回true")
        void testIsValidPaginationWithNormalPagination() {
            ShopListReq req = new ShopListReq(1, 20);
            
            assertTrue(req.isValidPagination());
        }

        @Test
        @DisplayName("isValidPagination应在游标分页有效时返回true")
        void testIsValidPaginationWithScrollPagination() {
            ShopListReq req = new ShopListReq("scroll123");
            
            assertTrue(req.isValidPagination());
        }

        @Test
        @DisplayName("isValidPagination应在两种分页都无效时返回false")
        void testIsValidPaginationFalse() {
            ShopListReq req = new ShopListReq();
            
            assertFalse(req.isValidPagination());
        }

        @Test
        @DisplayName("getPaginationType应返回正确的分页类型")
        void testGetPaginationType() {
            ShopListReq normalReq = new ShopListReq(1, 20);
            ShopListReq scrollReq = new ShopListReq("scroll123");
            ShopListReq invalidReq = new ShopListReq();
            
            assertEquals("普通分页", normalReq.getPaginationType());
            assertEquals("游标分页", scrollReq.getPaginationType());
            assertEquals("无效分页", invalidReq.getPaginationType());
        }

        @Test
        @DisplayName("游标分页优先级应高于普通分页")
        void testScrollPaginationPriority() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            assertTrue(req.isScrollPagination());
            assertTrue(req.isNormalPagination()); // 两者都有效
            assertEquals("游标分页", req.getPaginationType()); // 但游标分页优先
        }

        @Test
        @DisplayName("resetToFirstPage应重置分页参数")
        void testResetToFirstPage() {
            ShopListReq req = new ShopListReq(5, 50, "scroll123");
            
            req.resetToFirstPage();
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(50), req.getPageSize()); // 保持原有页大小
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("nextPage应移动到下一页")
        void testNextPage() {
            ShopListReq req = new ShopListReq(1, 20);
            
            req.nextPage();
            
            assertEquals(Integer.valueOf(2), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
        }

        @Test
        @DisplayName("nextPage对null页码应不产生异常")
        void testNextPageWithNullPage() {
            ShopListReq req = new ShopListReq(null, 20);
            
            assertDoesNotThrow(req::nextPage);
            assertNull(req.getPage());
        }

        @Test
        @DisplayName("previousPage应移动到上一页")
        void testPreviousPage() {
            ShopListReq req = new ShopListReq(3, 20);
            
            req.previousPage();
            
            assertEquals(Integer.valueOf(2), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
        }

        @Test
        @DisplayName("previousPage在第一页时应保持不变")
        void testPreviousPageAtFirstPage() {
            ShopListReq req = new ShopListReq(1, 20);
            
            req.previousPage();
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
        }

        @Test
        @DisplayName("previousPage对null页码应不产生异常")
        void testPreviousPageWithNullPage() {
            ShopListReq req = new ShopListReq(null, 20);
            
            assertDoesNotThrow(req::previousPage);
            assertNull(req.getPage());
        }
    }

    @Nested
    @DisplayName("Static Factory Methods Tests")
    class StaticFactoryMethodsTests {

        @Test
        @DisplayName("ofPage方法应创建普通分页请求")
        void testOfPageMethod() {
            ShopListReq req = ShopListReq.ofPage(1, 20);
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("ofScroll方法应创建游标分页请求")
        void testOfScrollMethod() {
            ShopListReq req = ShopListReq.ofScroll("scroll123");
            
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertEquals("scroll123", req.getScrollId());
        }

        @Test
        @DisplayName("firstPage方法应创建第一页请求（默认20条）")
        void testFirstPageMethod() {
            ShopListReq req = ShopListReq.firstPage();
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("firstPage方法应创建第一页请求（指定页大小）")
        void testFirstPageWithSizeMethod() {
            ShopListReq req = ShopListReq.firstPage(50);
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(50), req.getPageSize());
            assertNull(req.getScrollId());
        }
    }

    @Nested
    @DisplayName("Builder Tests")
    class BuilderTests {

        @Test
        @DisplayName("Builder应能构建完整的请求对象")
        void testBuilderComplete() {
            ShopListReq req = ShopListReq.builder()
                .page(2)
                .pageSize(30)
                .scrollId("scroll123")
                .build();
            
            assertEquals(Integer.valueOf(2), req.getPage());
            assertEquals(Integer.valueOf(30), req.getPageSize());
            assertEquals("scroll123", req.getScrollId());
        }

        @Test
        @DisplayName("Builder应能构建部分信息的请求")
        void testBuilderPartial() {
            ShopListReq req = ShopListReq.builder()
                .page(1)
                .pageSize(20)
                .build();
            
            assertEquals(Integer.valueOf(1), req.getPage());
            assertEquals(Integer.valueOf(20), req.getPageSize());
            assertNull(req.getScrollId());
        }

        @Test
        @DisplayName("Builder应能只构建游标分页请求")
        void testBuilderScrollOnly() {
            ShopListReq req = ShopListReq.builder()
                .scrollId("scroll123")
                .build();
            
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertEquals("scroll123", req.getScrollId());
        }

        @Test
        @DisplayName("Builder方法应支持链式调用")
        void testBuilderChaining() {
            ShopListReq.Builder builder = ShopListReq.builder();
            
            assertSame(builder, builder.page(1));
            assertSame(builder, builder.pageSize(20));
            assertSame(builder, builder.scrollId("scroll123"));
        }

        @Test
        @DisplayName("空Builder应创建空对象")
        void testEmptyBuilder() {
            ShopListReq req = ShopListReq.builder().build();
            
            assertNull(req.getPage());
            assertNull(req.getPageSize());
            assertNull(req.getScrollId());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("非常大的页码和页大小处理")
        void testVeryLargeValues() {
            int maxPage = Integer.MAX_VALUE;
            int maxPageSize = 1000; // 受验证约束限制
            
            ShopListReq req = new ShopListReq(maxPage, maxPageSize);
            
            assertEquals(Integer.valueOf(maxPage), req.getPage());
            assertEquals(Integer.valueOf(maxPageSize), req.getPageSize());
        }

        @Test
        @DisplayName("非常长的scrollId处理")
        void testVeryLongScrollId() {
            StringBuilder longScrollId = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longScrollId.append("scroll");
            }
            
            ShopListReq req = new ShopListReq(longScrollId.toString());
            
            assertEquals(longScrollId.toString(), req.getScrollId());
        }

        @Test
        @DisplayName("包含特殊字符的scrollId处理")
        void testScrollIdWithSpecialCharacters() {
            String specialScrollId = "scroll_123-test@example.com/path?query=value&param=123";
            
            ShopListReq req = new ShopListReq(specialScrollId);
            
            assertEquals(specialScrollId, req.getScrollId());
        }

        @Test
        @DisplayName("包含Unicode字符的scrollId处理")
        void testScrollIdWithUnicodeCharacters() {
            String unicodeScrollId = "游标_123_测试";
            
            ShopListReq req = new ShopListReq(unicodeScrollId);
            
            assertEquals(unicodeScrollId, req.getScrollId());
        }

        @Test
        @DisplayName("JSON特殊字符的序列化和反序列化")
        void testJsonSpecialCharactersSerializationDeserialization() throws IOException {
            String scrollIdWithSpecialChars = "scroll\"123\\456/789\b\f\n\r\t";
            ShopListReq original = new ShopListReq(scrollIdWithSpecialChars);
            
            String json = objectMapper.writeValueAsString(original);
            ShopListReq deserialized = objectMapper.readValue(json, ShopListReq.class);
            
            assertEquals(original, deserialized);
            assertEquals(scrollIdWithSpecialChars, deserialized.getScrollId());
        }

        @Test
        @DisplayName("重复调用业务方法应保持一致性")
        void testRepeatedBusinessMethodCalls() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            // 重复调用多次
            for (int i = 0; i < 10; i++) {
                assertTrue(req.isNormalPagination());
                assertTrue(req.isScrollPagination());
                assertTrue(req.isValidPagination());
                assertEquals("游标分页", req.getPaginationType());
            }
        }

        @Test
        @DisplayName("混合设置普通分页和游标分页参数")
        void testMixedPaginationParameters() {
            ShopListReq req = new ShopListReq();
            
            // 先设置普通分页
            req.setPage(1);
            req.setPageSize(20);
            assertTrue(req.isNormalPagination());
            assertFalse(req.isScrollPagination());
            assertEquals("普通分页", req.getPaginationType());
            
            // 再设置游标分页
            req.setScrollId("scroll123");
            assertTrue(req.isNormalPagination());
            assertTrue(req.isScrollPagination());
            assertEquals("游标分页", req.getPaginationType()); // 游标分页优先
            
            // 清除游标分页
            req.setScrollId(null);
            assertTrue(req.isNormalPagination());
            assertFalse(req.isScrollPagination());
            assertEquals("普通分页", req.getPaginationType());
        }
    }

    @Nested
    @DisplayName("Performance Tests")
    class PerformanceTests {

        @Test
        @DisplayName("大量对象创建不应导致内存溢出")
        void testMassObjectCreation() {
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 5000; i++) {
                    ShopListReq req = new ShopListReq(i + 1, (i % 100) + 1, "scroll" + i);
                    assertEquals(Integer.valueOf(i + 1), req.getPage());
                    assertEquals(Integer.valueOf((i % 100) + 1), req.getPageSize());
                    assertEquals("scroll" + i, req.getScrollId());
                }
            });
        }

        @Test
        @DisplayName("业务逻辑方法应高效执行")
        void testBusinessMethodsPerformance() {
            ShopListReq req = new ShopListReq(1, 20, "scroll123");
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    assertTrue(req.isNormalPagination());
                    assertTrue(req.isScrollPagination());
                    assertTrue(req.isValidPagination());
                    assertEquals("游标分页", req.getPaginationType());
                }
            });
        }

        @Test
        @DisplayName("分页操作方法应高效执行")
        void testPaginationOperationsPerformance() {
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    ShopListReq req = new ShopListReq(1, 20);
                    req.nextPage();
                    assertEquals(Integer.valueOf(2), req.getPage());
                    req.previousPage();
                    assertEquals(Integer.valueOf(1), req.getPage());
                    req.resetToFirstPage();
                    assertEquals(Integer.valueOf(1), req.getPage());
                }
            });
        }
    }
}