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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShopListResp 实体类的 JUnit 5 单元测试
 */
@DisplayName("ShopListResp Tests")
class ShopListRespTest {

    private ShopListResp shopListResp;
    private ObjectMapper objectMapper;
    private Validator validator;
    private List<ShopItem> testShops;

    @BeforeEach
    void setUp() {
        shopListResp = new ShopListResp();
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        
        // 创建测试用的门店列表
        testShops = createTestShops();
    }

    private List<ShopItem> createTestShops() {
        List<ShopItem> shops = new ArrayList<>();
        ShopItem shop1 = new ShopItem();
        shop1.setShopId("shop001");
        shop1.setName("测试门店1");
        shop1.setCity("北京");
        shop1.setProvince("北京市");
        shops.add(shop1);
        
        ShopItem shop2 = new ShopItem();
        shop2.setShopId("shop002");
        shop2.setName("测试门店2");
        shop2.setCity("上海");
        shop2.setProvince("上海市");
        shops.add(shop2);
        
        return shops;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空响应对象")
        void testDefaultConstructor() {
            ShopListResp resp = new ShopListResp();
            
            assertNotNull(resp.getData());
            assertTrue(resp.getData().isEmpty());
            assertNull(resp.getPage());
            assertNull(resp.getTotalPage());
            assertNull(resp.getTotal());
            assertNull(resp.getIsLast());
            assertNull(resp.getScrollId());
        }

        @Test
        @DisplayName("基本构造函数应正确设置数据列表")
        void testBasicConstructor() {
            ShopListResp resp = new ShopListResp(testShops);
            
            assertNotNull(resp.getData());
            assertEquals(2, resp.getData().size());
            assertEquals("shop001", resp.getData().get(0).getShopId());
            assertEquals("shop002", resp.getData().get(1).getShopId());
            assertNull(resp.getPage());
            assertNull(resp.getTotalPage());
        }

        @Test
        @DisplayName("普通分页构造函数应正确设置所有分页参数")
        void testNormalPaginationConstructor() {
            Integer page = 1;
            Integer totalPage = 5;
            Integer total = 100;
            Boolean isLast = false;
            
            ShopListResp resp = new ShopListResp(testShops, page, totalPage, total, isLast);
            
            assertEquals(testShops.size(), resp.getData().size());
            assertEquals(page, resp.getPage());
            assertEquals(totalPage, resp.getTotalPage());
            assertEquals(total, resp.getTotal());
            assertEquals(isLast, resp.getIsLast());
            assertNull(resp.getScrollId());
        }

        @Test
        @DisplayName("游标分页构造函数应正确设置游标参数")
        void testScrollPaginationConstructor() {
            String scrollId = "scroll123";
            Boolean isLast = false;
            
            ShopListResp resp = new ShopListResp(testShops, scrollId, isLast);
            
            assertEquals(testShops.size(), resp.getData().size());
            assertEquals(scrollId, resp.getScrollId());
            assertEquals(isLast, resp.getIsLast());
            assertNull(resp.getPage());
            assertNull(resp.getTotalPage());
        }

        @Test
        @DisplayName("构造函数应能处理null数据列表")
        void testConstructorWithNullData() {
            ShopListResp resp = new ShopListResp(null);
            
            assertNotNull(resp.getData());
            assertTrue(resp.getData().isEmpty());
        }

        @Test
        @DisplayName("构造函数应能防御性地复制数据列表")
        void testConstructorDefensiveCopy() {
            List<ShopItem> originalShops = new ArrayList<>(testShops);
            ShopListResp resp = new ShopListResp(originalShops);
            
            // 修改原始列表不应影响响应对象
            originalShops.clear();
            
            assertEquals(2, resp.getData().size());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("data 的 getter 和 setter 应正常工作")
        void testDataGetterSetter() {
            shopListResp.setData(testShops);
            assertEquals(testShops.size(), shopListResp.getData().size());
            assertEquals("shop001", shopListResp.getData().get(0).getShopId());
        }

        @Test
        @DisplayName("setData 应防御性地复制列表")
        void testSetDataDefensiveCopy() {
            List<ShopItem> originalShops = new ArrayList<>(testShops);
            shopListResp.setData(originalShops);
            
            // 修改原始列表不应影响响应对象
            originalShops.clear();
            
            assertEquals(2, shopListResp.getData().size());
        }

        @Test
        @DisplayName("page 的 getter 和 setter 应正常工作")
        void testPageGetterSetter() {
            Integer page = 3;
            shopListResp.setPage(page);
            assertEquals(page, shopListResp.getPage());
        }

        @Test
        @DisplayName("totalPage 的 getter 和 setter 应正常工作")
        void testTotalPageGetterSetter() {
            Integer totalPage = 10;
            shopListResp.setTotalPage(totalPage);
            assertEquals(totalPage, shopListResp.getTotalPage());
        }

        @Test
        @DisplayName("total 的 getter 和 setter 应正常工作")
        void testTotalGetterSetter() {
            Integer total = 200;
            shopListResp.setTotal(total);
            assertEquals(total, shopListResp.getTotal());
        }

        @Test
        @DisplayName("isLast 的 getter 和 setter 应正常工作")
        void testIsLastGetterSetter() {
            Boolean isLast = true;
            shopListResp.setIsLast(isLast);
            assertEquals(isLast, shopListResp.getIsLast());
        }

        @Test
        @DisplayName("scrollId 的 getter 和 setter 应正常工作")
        void testScrollIdGetterSetter() {
            String scrollId = "scroll456";
            shopListResp.setScrollId(scrollId);
            assertEquals(scrollId, shopListResp.getScrollId());
        }

        @Test
        @DisplayName("所有字段设置null值应正常工作")
        void testSetNullValues() {
            shopListResp.setData(null);
            shopListResp.setPage(null);
            shopListResp.setTotalPage(null);
            shopListResp.setTotal(null);
            shopListResp.setIsLast(null);
            shopListResp.setScrollId(null);
            
            assertNotNull(shopListResp.getData());
            assertTrue(shopListResp.getData().isEmpty());
            assertNull(shopListResp.getPage());
            assertNull(shopListResp.getTotalPage());
            assertNull(shopListResp.getTotal());
            assertNull(shopListResp.getIsLast());
            assertNull(shopListResp.getScrollId());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            ShopListResp resp1 = new ShopListResp(testShops, 1, 5, 100, false);
            ShopListResp resp2 = new ShopListResp(testShops, 1, 5, 100, false);
            
            assertEquals(resp1, resp2);
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            ShopListResp resp1 = new ShopListResp(testShops, 1, 5, 100, false);
            ShopListResp resp2 = new ShopListResp(testShops, 2, 5, 100, false);
            
            assertNotEquals(resp1, resp2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            ShopListResp resp = new ShopListResp(testShops);
            
            assertEquals(resp, resp);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            ShopListResp resp = new ShopListResp(testShops);
            
            assertNotEquals(resp, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            ShopListResp resp = new ShopListResp(testShops);
            String other = "different type";
            
            assertNotEquals(resp, other);
        }

        @Test
        @DisplayName("空响应对象应相等")
        void testEqualsEmptyResponses() {
            ShopListResp resp1 = new ShopListResp();
            ShopListResp resp2 = new ShopListResp();
            
            assertEquals(resp1, resp2);
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段信息")
        void testToString() {
            ShopListResp resp = new ShopListResp(testShops, 1, 5, 100, false);
            resp.setScrollId("scroll123");
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopListResp"));
            assertTrue(result.contains("data="));
            assertTrue(result.contains("page=1"));
            assertTrue(result.contains("totalPage=5"));
            assertTrue(result.contains("total=100"));
            assertTrue(result.contains("isLast=false"));
            assertTrue(result.contains("scrollId='scroll123'"));
        }

        @Test
        @DisplayName("空响应的toString应正确处理null值")
        void testToStringWithNullValues() {
            ShopListResp resp = new ShopListResp();
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopListResp"));
            assertTrue(result.contains("page=null"));
            assertTrue(result.contains("totalPage=null"));
            assertTrue(result.contains("total=null"));
            assertTrue(result.contains("isLast=null"));
            assertTrue(result.contains("scrollId=null"));
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            ShopListResp resp = new ShopListResp(testShops, 1, 5, 100, false);
            resp.setScrollId("scroll123");
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertTrue(json.contains("\"data\""));
            assertTrue(json.contains("\"page\":1"));
            assertTrue(json.contains("\"total_page\":5"));
            assertTrue(json.contains("\"total\":100"));
            assertTrue(json.contains("\"is_last\":false"));
            assertTrue(json.contains("\"scroll_id\":\"scroll123\""));
        }

        @Test
        @DisplayName("包含null值的对象应能正确序列化")
        void testSerializationWithNullValues() throws IOException {
            ShopListResp resp = new ShopListResp();
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertTrue(json.contains("\"data\":[]"));
            assertTrue(json.contains("\"page\":null"));
            assertTrue(json.contains("\"total_page\":null"));
            assertTrue(json.contains("\"total\":null"));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"data\":[],\"page\":1,\"total_page\":5,\"total\":100,\"is_last\":false,\"scroll_id\":\"scroll123\"}";
            
            ShopListResp resp = objectMapper.readValue(json, ShopListResp.class);
            
            assertNotNull(resp.getData());
            assertEquals(1, resp.getPage().intValue());
            assertEquals(5, resp.getTotalPage().intValue());
            assertEquals(100, resp.getTotal().intValue());
            assertFalse(resp.getIsLast());
            assertEquals("scroll123", resp.getScrollId());
        }

        @Test
        @DisplayName("序列化和反序列化应保持数据一致性")
        void testSerializationDeserializationConsistency() throws IOException {
            ShopListResp original = new ShopListResp(testShops, 2, 10, 200, true);
            
            String json = objectMapper.writeValueAsString(original);
            ShopListResp deserialized = objectMapper.readValue(json, ShopListResp.class);
            
            assertEquals(original.getData().size(), deserialized.getData().size());
            assertEquals(original.getPage(), deserialized.getPage());
            assertEquals(original.getTotalPage(), deserialized.getTotalPage());
            assertEquals(original.getTotal(), deserialized.getTotal());
            assertEquals(original.getIsLast(), deserialized.getIsLast());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的响应对象应通过验证")
        void testValidResponse() {
            ShopListResp resp = new ShopListResp(testShops, 1, 5, 100, false);
            
            Set<ConstraintViolation<ShopListResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("空响应对象应通过验证")
        void testEmptyResponse() {
            ShopListResp resp = new ShopListResp();
            
            Set<ConstraintViolation<ShopListResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("包含无效门店数据的响应应有验证错误")
        void testResponseWithInvalidShops() {
            List<ShopItem> invalidShops = new ArrayList<>();
            ShopItem invalidShop = new ShopItem();
            invalidShop.setShopId(""); // 空的shopId应该触发验证错误
            invalidShop.setName(""); // 空的name应该触发验证错误
            invalidShops.add(invalidShop);
            
            ShopListResp resp = new ShopListResp(invalidShops);
            
            Set<ConstraintViolation<ShopListResp>> violations = validator.validate(resp);
            
            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("getShopCount应返回正确的门店数量")
        void testGetShopCount() {
            assertEquals(0, shopListResp.getShopCount());
            
            shopListResp.setData(testShops);
            assertEquals(2, shopListResp.getShopCount());
            
            shopListResp.setData(null);
            assertEquals(0, shopListResp.getShopCount());
        }

        @Test
        @DisplayName("hasShops应正确判断是否有门店数据")
        void testHasShops() {
            assertFalse(shopListResp.hasShops());
            
            shopListResp.setData(testShops);
            assertTrue(shopListResp.hasShops());
            
            shopListResp.setData(new ArrayList<>());
            assertFalse(shopListResp.hasShops());
        }

        @Test
        @DisplayName("isEmpty应正确判断响应是否为空")
        void testIsEmpty() {
            assertTrue(shopListResp.isEmpty());
            
            shopListResp.setData(testShops);
            assertFalse(shopListResp.isEmpty());
        }

        @Test
        @DisplayName("isNormalPagination应正确判断是否为普通分页")
        void testIsNormalPagination() {
            assertFalse(shopListResp.isNormalPagination());
            
            shopListResp.setPage(1);
            assertFalse(shopListResp.isNormalPagination());
            
            shopListResp.setTotalPage(10);
            assertTrue(shopListResp.isNormalPagination());
        }

        @Test
        @DisplayName("isScrollPagination应正确判断是否为游标分页")
        void testIsScrollPagination() {
            assertFalse(shopListResp.isScrollPagination());
            
            shopListResp.setScrollId("");
            assertFalse(shopListResp.isScrollPagination());
            
            shopListResp.setScrollId("  ");
            assertFalse(shopListResp.isScrollPagination());
            
            shopListResp.setScrollId("scroll123");
            assertTrue(shopListResp.isScrollPagination());
        }

        @Test
        @DisplayName("hasNextPage应正确判断是否有下一页")
        void testHasNextPage() {
            // 测试isLast标记
            shopListResp.setIsLast(false);
            assertTrue(shopListResp.hasNextPage());
            
            shopListResp.setIsLast(true);
            assertFalse(shopListResp.hasNextPage());
            
            // 测试普通分页
            shopListResp.setIsLast(null);
            shopListResp.setPage(2);
            shopListResp.setTotalPage(5);
            assertTrue(shopListResp.hasNextPage());
            
            shopListResp.setPage(5);
            shopListResp.setTotalPage(5);
            assertFalse(shopListResp.hasNextPage());
            
            shopListResp.setPage(6);
            shopListResp.setTotalPage(5);
            assertFalse(shopListResp.hasNextPage());
        }

        @Test
        @DisplayName("getPaginationType应返回正确的分页类型")
        void testGetPaginationType() {
            assertEquals("无分页信息", shopListResp.getPaginationType());
            
            shopListResp.setScrollId("scroll123");
            assertEquals("游标分页", shopListResp.getPaginationType());
            
            shopListResp.setScrollId(null);
            shopListResp.setPage(1);
            shopListResp.setTotalPage(5);
            assertEquals("普通分页", shopListResp.getPaginationType());
        }

        @Test
        @DisplayName("addShop应正确添加门店")
        void testAddShop() {
            ShopItem newShop = new ShopItem();
            newShop.setShopId("shop003");
            newShop.setName("新门店");
            
            shopListResp.addShop(newShop);
            assertEquals(1, shopListResp.getShopCount());
            assertEquals("shop003", shopListResp.getData().get(0).getShopId());
            
            shopListResp.addShop(null);
            assertEquals(1, shopListResp.getShopCount());
        }

        @Test
        @DisplayName("addShops应正确添加多个门店")
        void testAddShops() {
            shopListResp.addShops(testShops);
            assertEquals(2, shopListResp.getShopCount());
            
            shopListResp.addShops(null);
            assertEquals(2, shopListResp.getShopCount());
            
            shopListResp.addShops(new ArrayList<>());
            assertEquals(2, shopListResp.getShopCount());
        }

        @Test
        @DisplayName("clearShops应清空门店数据")
        void testClearShops() {
            shopListResp.setData(testShops);
            assertEquals(2, shopListResp.getShopCount());
            
            shopListResp.clearShops();
            assertEquals(0, shopListResp.getShopCount());
            assertTrue(shopListResp.isEmpty());
        }

        @Test
        @DisplayName("getImmutableShops应返回不可变列表")
        void testGetImmutableShops() {
            shopListResp.setData(testShops);
            
            List<ShopItem> immutableShops = shopListResp.getImmutableShops();
            assertEquals(2, immutableShops.size());
            
            assertThrows(UnsupportedOperationException.class, () -> {
                immutableShops.add(new ShopItem());
            });
        }

        @Test
        @DisplayName("getValidShops应返回有效的门店列表")
        void testGetValidShops() {
            // 创建包含有效和无效门店的列表
            List<ShopItem> mixedShops = new ArrayList<>();
            ShopItem validShop = new ShopItem();
            validShop.setShopId("valid001");
            validShop.setName("有效门店");
            mixedShops.add(validShop);
            
            ShopItem invalidShop = new ShopItem();
            invalidShop.setShopId("");
            invalidShop.setName("");
            mixedShops.add(invalidShop);
            
            mixedShops.add(null);
            
            shopListResp.setData(mixedShops);
            
            List<ShopItem> validShops = shopListResp.getValidShops();
            assertEquals(1, validShops.size());
            assertEquals("valid001", validShops.get(0).getShopId());
        }

        @Test
        @DisplayName("findShopById应能找到正确的门店")
        void testFindShopById() {
            shopListResp.setData(testShops);
            
            ShopItem found = shopListResp.findShopById("shop001");
            assertNotNull(found);
            assertEquals("shop001", found.getShopId());
            assertEquals("测试门店1", found.getName());
            
            ShopItem notFound = shopListResp.findShopById("shop999");
            assertNull(notFound);
            
            ShopItem nullSearch = shopListResp.findShopById(null);
            assertNull(nullSearch);
        }
    }

    @Nested
    @DisplayName("Static Factory Methods Tests")
    class StaticFactoryMethodsTests {

        @Test
        @DisplayName("empty应创建空响应对象")
        void testEmpty() {
            ShopListResp resp = ShopListResp.empty();
            
            assertNotNull(resp);
            assertTrue(resp.isEmpty());
            assertEquals(0, resp.getShopCount());
        }

        @Test
        @DisplayName("of应创建包含门店数据的响应对象")
        void testOf() {
            ShopListResp resp = ShopListResp.of(testShops);
            
            assertNotNull(resp);
            assertEquals(2, resp.getShopCount());
            assertFalse(resp.isEmpty());
        }

        @Test
        @DisplayName("ofPage应创建普通分页响应对象")
        void testOfPage() {
            ShopListResp resp = ShopListResp.ofPage(testShops, 2, 5, 100);
            
            assertNotNull(resp);
            assertEquals(2, resp.getShopCount());
            assertEquals(2, resp.getPage().intValue());
            assertEquals(5, resp.getTotalPage().intValue());
            assertEquals(100, resp.getTotal().intValue());
            assertFalse(resp.getIsLast());
            assertTrue(resp.isNormalPagination());
        }

        @Test
        @DisplayName("ofScroll应创建游标分页响应对象")
        void testOfScroll() {
            ShopListResp resp = ShopListResp.ofScroll(testShops, "scroll123", false);
            
            assertNotNull(resp);
            assertEquals(2, resp.getShopCount());
            assertEquals("scroll123", resp.getScrollId());
            assertFalse(resp.getIsLast());
            assertTrue(resp.isScrollPagination());
        }

        @Test
        @DisplayName("lastPage应创建最后一页响应对象")
        void testLastPage() {
            ShopListResp resp = ShopListResp.lastPage(testShops);
            
            assertNotNull(resp);
            assertEquals(2, resp.getShopCount());
            assertTrue(resp.getIsLast());
            assertFalse(resp.hasNextPage());
        }
    }

    @Nested
    @DisplayName("Builder Tests")
    class BuilderTests {

        @Test
        @DisplayName("Builder应能正确构建响应对象")
        void testBuilder() {
            ShopListResp resp = ShopListResp.builder()
                    .data(testShops)
                    .page(1)
                    .totalPage(5)
                    .total(100)
                    .isLast(false)
                    .scrollId("scroll123")
                    .build();
            
            assertEquals(2, resp.getShopCount());
            assertEquals(1, resp.getPage().intValue());
            assertEquals(5, resp.getTotalPage().intValue());
            assertEquals(100, resp.getTotal().intValue());
            assertFalse(resp.getIsLast());
            assertEquals("scroll123", resp.getScrollId());
        }

        @Test
        @DisplayName("Builder应能添加单个门店")
        void testBuilderAddShop() {
            ShopItem shop = testShops.get(0);
            
            ShopListResp resp = ShopListResp.builder()
                    .addShop(shop)
                    .build();
            
            assertEquals(1, resp.getShopCount());
            assertEquals("shop001", resp.getData().get(0).getShopId());
        }

        @Test
        @DisplayName("Builder应能添加多个门店")
        void testBuilderAddShops() {
            ShopListResp resp = ShopListResp.builder()
                    .addShops(testShops)
                    .build();
            
            assertEquals(2, resp.getShopCount());
        }

        @Test
        @DisplayName("Builder应能处理null值")
        void testBuilderWithNullValues() {
            ShopListResp resp = ShopListResp.builder()
                    .addShop(null)
                    .addShops(null)
                    .data(null)
                    .build();
            
            assertEquals(0, resp.getShopCount());
            assertTrue(resp.isEmpty());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("处理大量门店数据")
        void testLargeDataSet() {
            List<ShopItem> largeShopList = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                ShopItem shop = new ShopItem();
                shop.setShopId("shop" + i);
                shop.setName("门店" + i);
                largeShopList.add(shop);
            }
            
            ShopListResp resp = new ShopListResp(largeShopList);
            
            assertEquals(1000, resp.getShopCount());
            assertTrue(resp.hasShops());
            assertFalse(resp.isEmpty());
        }

        @Test
        @DisplayName("处理包含null元素的门店列表")
        void testDataListWithNullElements() {
            List<ShopItem> shopWithNulls = new ArrayList<>();
            shopWithNulls.add(testShops.get(0));
            shopWithNulls.add(null);
            shopWithNulls.add(testShops.get(1));
            
            ShopListResp resp = new ShopListResp(shopWithNulls);
            
            assertEquals(3, resp.getShopCount());
            assertNull(resp.getData().get(1));
        }

        @Test
        @DisplayName("处理极端分页参数")
        void testExtremePaginationParameters() {
            ShopListResp resp = new ShopListResp(testShops);
            
            // 测试负数页码
            resp.setPage(-1);
            resp.setTotalPage(5);
            assertFalse(resp.hasNextPage());
            
            // 测试零页码
            resp.setPage(0);
            assertFalse(resp.hasNextPage());
            
            // 测试极大页码
            resp.setPage(Integer.MAX_VALUE);
            resp.setTotalPage(Integer.MAX_VALUE);
            assertFalse(resp.hasNextPage());
        }

        @Test
        @DisplayName("处理空字符串游标ID")
        void testEmptyScrollId() {
            ShopListResp resp = new ShopListResp(testShops);
            
            resp.setScrollId("");
            assertFalse(resp.isScrollPagination());
            assertEquals("无分页信息", resp.getPaginationType());
            
            resp.setScrollId("   ");
            assertFalse(resp.isScrollPagination());
        }

        @Test
        @DisplayName("处理同时设置两种分页参数")
        void testMixedPaginationParameters() {
            ShopListResp resp = new ShopListResp(testShops);
            resp.setPage(1);
            resp.setTotalPage(5);
            resp.setScrollId("scroll123");
            
            // 游标分页优先
            assertTrue(resp.isScrollPagination());
            assertTrue(resp.isNormalPagination());
            assertEquals("游标分页", resp.getPaginationType());
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
                    ShopListResp resp = new ShopListResp(testShops, i, 100, 10000, i >= 99);
                    assertEquals(2, resp.getShopCount());
                }
            });
        }

        @Test
        @DisplayName("大数据列表操作应高效执行")
        void testLargeDataOperations() {
            List<ShopItem> largeList = new ArrayList<>();
            for (int i = 0; i < 5000; i++) {
                ShopItem shop = new ShopItem();
                shop.setShopId("shop" + i);
                shop.setName("门店" + i);
                largeList.add(shop);
            }
            
            assertDoesNotThrow(() -> {
                ShopListResp resp = new ShopListResp();
                resp.addShops(largeList);
                assertEquals(5000, resp.getShopCount());
                
                List<ShopItem> immutable = resp.getImmutableShops();
                assertNotNull(immutable);
                assertEquals(5000, immutable.size());
            });
        }

        @Test
        @DisplayName("频繁的equals和hashCode调用应高效执行")
        void testEqualsHashCodePerformance() {
            ShopListResp resp1 = new ShopListResp(testShops, 1, 5, 100, false);
            ShopListResp resp2 = new ShopListResp(testShops, 1, 5, 100, false);
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    boolean result = resp1.equals(resp2);
                    assertTrue(result);
                    int hash1 = resp1.hashCode();
                    int hash2 = resp2.hashCode();
                    assertEquals(hash1, hash2);
                }
            });
        }
    }
}