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
 * ShopPrinterListResp 实体类的 JUnit 5 单元测试
 */
@DisplayName("ShopPrinterListResp Tests")
class ShopPrinterListRespTest {

    private ShopPrinterListResp shopPrinterListResp;
    private ObjectMapper objectMapper;
    private Validator validator;
    private List<PrinterItem> testPrinters;

    @BeforeEach
    void setUp() {
        shopPrinterListResp = new ShopPrinterListResp();
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        
        // 创建测试用的打印机列表
        testPrinters = createTestPrinters();
    }

    private List<PrinterItem> createTestPrinters() {
        List<PrinterItem> printers = new ArrayList<>();
        
        PrinterItem printer1 = new PrinterItem();
        printer1.setShopId("shop001");
        printer1.setBrand("飞鹅");
        printer1.setMachineCode("printer001");
        printer1.setMachineSign("sign001");
        printers.add(printer1);
        
        PrinterItem printer2 = new PrinterItem();
        printer2.setShopId("shop001");
        printer2.setBrand("小票");
        printer2.setMachineCode("printer002");
        printer2.setMachineSign("sign002");
        printers.add(printer2);
        
        return printers;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应创建空响应对象")
        void testDefaultConstructor() {
            ShopPrinterListResp resp = new ShopPrinterListResp();
            
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
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            
            assertNotNull(resp.getData());
            assertEquals(2, resp.getData().size());
            assertEquals("printer001", resp.getData().get(0).getMachineCode());
            assertEquals("printer002", resp.getData().get(1).getMachineCode());
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
            
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters, page, totalPage, total, isLast);
            
            assertEquals(testPrinters.size(), resp.getData().size());
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
            
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters, scrollId, isLast);
            
            assertEquals(testPrinters.size(), resp.getData().size());
            assertEquals(scrollId, resp.getScrollId());
            assertEquals(isLast, resp.getIsLast());
            assertNull(resp.getPage());
            assertNull(resp.getTotalPage());
        }

        @Test
        @DisplayName("构造函数应能处理null数据列表")
        void testConstructorWithNullData() {
            ShopPrinterListResp resp = new ShopPrinterListResp(null);
            
            assertNotNull(resp.getData());
            assertTrue(resp.getData().isEmpty());
        }

        @Test
        @DisplayName("构造函数应能防御性地复制数据列表")
        void testConstructorDefensiveCopy() {
            List<PrinterItem> originalPrinters = new ArrayList<>(testPrinters);
            ShopPrinterListResp resp = new ShopPrinterListResp(originalPrinters);
            
            // 修改原始列表不应影响响应对象
            originalPrinters.clear();
            
            assertEquals(2, resp.getData().size());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("data 的 getter 和 setter 应正常工作")
        void testDataGetterSetter() {
            shopPrinterListResp.setData(testPrinters);
            assertEquals(testPrinters.size(), shopPrinterListResp.getData().size());
            assertEquals("printer001", shopPrinterListResp.getData().get(0).getMachineCode());
        }

        @Test
        @DisplayName("setData 应防御性地复制列表")
        void testSetDataDefensiveCopy() {
            List<PrinterItem> originalPrinters = new ArrayList<>(testPrinters);
            shopPrinterListResp.setData(originalPrinters);
            
            // 修改原始列表不应影响响应对象
            originalPrinters.clear();
            
            assertEquals(2, shopPrinterListResp.getData().size());
        }

        @Test
        @DisplayName("page 的 getter 和 setter 应正常工作")
        void testPageGetterSetter() {
            Integer page = 3;
            shopPrinterListResp.setPage(page);
            assertEquals(page, shopPrinterListResp.getPage());
        }

        @Test
        @DisplayName("totalPage 的 getter 和 setter 应正常工作")
        void testTotalPageGetterSetter() {
            Integer totalPage = 10;
            shopPrinterListResp.setTotalPage(totalPage);
            assertEquals(totalPage, shopPrinterListResp.getTotalPage());
        }

        @Test
        @DisplayName("total 的 getter 和 setter 应正常工作")
        void testTotalGetterSetter() {
            Integer total = 200;
            shopPrinterListResp.setTotal(total);
            assertEquals(total, shopPrinterListResp.getTotal());
        }

        @Test
        @DisplayName("isLast 的 getter 和 setter 应正常工作")
        void testIsLastGetterSetter() {
            Boolean isLast = true;
            shopPrinterListResp.setIsLast(isLast);
            assertEquals(isLast, shopPrinterListResp.getIsLast());
        }

        @Test
        @DisplayName("scrollId 的 getter 和 setter 应正常工作")
        void testScrollIdGetterSetter() {
            String scrollId = "scroll456";
            shopPrinterListResp.setScrollId(scrollId);
            assertEquals(scrollId, shopPrinterListResp.getScrollId());
        }

        @Test
        @DisplayName("所有字段设置null值应正常工作")
        void testSetNullValues() {
            shopPrinterListResp.setData(null);
            shopPrinterListResp.setPage(null);
            shopPrinterListResp.setTotalPage(null);
            shopPrinterListResp.setTotal(null);
            shopPrinterListResp.setIsLast(null);
            shopPrinterListResp.setScrollId(null);
            
            assertNotNull(shopPrinterListResp.getData());
            assertTrue(shopPrinterListResp.getData().isEmpty());
            assertNull(shopPrinterListResp.getPage());
            assertNull(shopPrinterListResp.getTotalPage());
            assertNull(shopPrinterListResp.getTotal());
            assertNull(shopPrinterListResp.getIsLast());
            assertNull(shopPrinterListResp.getScrollId());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            ShopPrinterListResp resp1 = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            ShopPrinterListResp resp2 = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            
            assertEquals(resp1, resp2);
            assertEquals(resp1.hashCode(), resp2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            ShopPrinterListResp resp1 = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            ShopPrinterListResp resp2 = new ShopPrinterListResp(testPrinters, 2, 5, 100, false);
            
            assertNotEquals(resp1, resp2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            
            assertEquals(resp, resp);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            
            assertNotEquals(resp, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            String other = "different type";
            
            assertNotEquals(resp, other);
        }

        @Test
        @DisplayName("空响应对象应相等")
        void testEqualsEmptyResponses() {
            ShopPrinterListResp resp1 = new ShopPrinterListResp();
            ShopPrinterListResp resp2 = new ShopPrinterListResp();
            
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
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            resp.setScrollId("scroll123");
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopPrinterListResp"));
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
            ShopPrinterListResp resp = new ShopPrinterListResp();
            
            String result = resp.toString();
            
            assertTrue(result.contains("ShopPrinterListResp"));
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
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            resp.setScrollId("scroll123");
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertTrue(json.contains("\"data\""));
            assertTrue(json.contains("\"page\":1"));
            assertTrue(json.contains("\"total_page\":5"));
            assertTrue(json.contains("\"total\":100"));
            assertTrue(json.contains("\"isLast\":false"));
            assertTrue(json.contains("\"scroll_id\":\"scroll123\""));
        }

        @Test
        @DisplayName("包含null值的对象应能正确序列化")
        void testSerializationWithNullValues() throws IOException {
            ShopPrinterListResp resp = new ShopPrinterListResp();
            
            String json = objectMapper.writeValueAsString(resp);
            
            assertTrue(json.contains("\"data\":[]"));
            assertTrue(json.contains("\"page\":null"));
            assertTrue(json.contains("\"total_page\":null"));
            assertTrue(json.contains("\"total\":null"));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"data\":[],\"page\":1,\"total_page\":5,\"total\":100,\"isLast\":false,\"scroll_id\":\"scroll123\"}";
            
            ShopPrinterListResp resp = objectMapper.readValue(json, ShopPrinterListResp.class);
            
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
            ShopPrinterListResp original = new ShopPrinterListResp(testPrinters, 2, 10, 200, true);
            
            String json = objectMapper.writeValueAsString(original);
            ShopPrinterListResp deserialized = objectMapper.readValue(json, ShopPrinterListResp.class);
            
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
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            
            Set<ConstraintViolation<ShopPrinterListResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("空响应对象应通过验证")
        void testEmptyResponse() {
            ShopPrinterListResp resp = new ShopPrinterListResp();
            
            Set<ConstraintViolation<ShopPrinterListResp>> violations = validator.validate(resp);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("包含无效打印机数据的响应应有验证错误")
        void testResponseWithInvalidPrinters() {
            List<PrinterItem> invalidPrinters = new ArrayList<>();
            PrinterItem invalidPrinter = new PrinterItem();
            invalidPrinter.setShopId(""); // 空的shopId应该触发验证错误
            invalidPrinter.setMachineCode(""); // 空的machineCode应该触发验证错误
            invalidPrinters.add(invalidPrinter);
            
            ShopPrinterListResp resp = new ShopPrinterListResp(invalidPrinters);
            
            Set<ConstraintViolation<ShopPrinterListResp>> violations = validator.validate(resp);
            
            assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("getPrinterCount应返回正确的打印机数量")
        void testGetPrinterCount() {
            assertEquals(0, shopPrinterListResp.getPrinterCount());
            
            shopPrinterListResp.setData(testPrinters);
            assertEquals(2, shopPrinterListResp.getPrinterCount());
            
            shopPrinterListResp.setData(null);
            assertEquals(0, shopPrinterListResp.getPrinterCount());
        }

        @Test
        @DisplayName("hasPrinters应正确判断是否有打印机数据")
        void testHasPrinters() {
            assertFalse(shopPrinterListResp.hasPrinters());
            
            shopPrinterListResp.setData(testPrinters);
            assertTrue(shopPrinterListResp.hasPrinters());
            
            shopPrinterListResp.setData(new ArrayList<>());
            assertFalse(shopPrinterListResp.hasPrinters());
        }

        @Test
        @DisplayName("isEmpty应正确判断响应是否为空")
        void testIsEmpty() {
            assertTrue(shopPrinterListResp.isEmpty());
            
            shopPrinterListResp.setData(testPrinters);
            assertFalse(shopPrinterListResp.isEmpty());
        }

        @Test
        @DisplayName("isNormalPagination应正确判断是否为普通分页")
        void testIsNormalPagination() {
            assertFalse(shopPrinterListResp.isNormalPagination());
            
            shopPrinterListResp.setPage(1);
            assertFalse(shopPrinterListResp.isNormalPagination());
            
            shopPrinterListResp.setTotalPage(10);
            assertTrue(shopPrinterListResp.isNormalPagination());
        }

        @Test
        @DisplayName("isScrollPagination应正确判断是否为游标分页")
        void testIsScrollPagination() {
            assertFalse(shopPrinterListResp.isScrollPagination());
            
            shopPrinterListResp.setScrollId("");
            assertFalse(shopPrinterListResp.isScrollPagination());
            
            shopPrinterListResp.setScrollId("  ");
            assertFalse(shopPrinterListResp.isScrollPagination());
            
            shopPrinterListResp.setScrollId("scroll123");
            assertTrue(shopPrinterListResp.isScrollPagination());
        }

        @Test
        @DisplayName("hasNextPage应正确判断是否有下一页")
        void testHasNextPage() {
            // 测试isLast标记
            shopPrinterListResp.setIsLast(false);
            assertTrue(shopPrinterListResp.hasNextPage());
            
            shopPrinterListResp.setIsLast(true);
            assertFalse(shopPrinterListResp.hasNextPage());
            
            // 测试普通分页
            shopPrinterListResp.setIsLast(null);
            shopPrinterListResp.setPage(2);
            shopPrinterListResp.setTotalPage(5);
            assertTrue(shopPrinterListResp.hasNextPage());
            
            shopPrinterListResp.setPage(5);
            shopPrinterListResp.setTotalPage(5);
            assertFalse(shopPrinterListResp.hasNextPage());
            
            shopPrinterListResp.setPage(6);
            shopPrinterListResp.setTotalPage(5);
            assertFalse(shopPrinterListResp.hasNextPage());
        }

        @Test
        @DisplayName("getPaginationType应返回正确的分页类型")
        void testGetPaginationType() {
            assertEquals("无分页信息", shopPrinterListResp.getPaginationType());
            
            shopPrinterListResp.setScrollId("scroll123");
            assertEquals("游标分页", shopPrinterListResp.getPaginationType());
            
            shopPrinterListResp.setScrollId(null);
            shopPrinterListResp.setPage(1);
            shopPrinterListResp.setTotalPage(5);
            assertEquals("普通分页", shopPrinterListResp.getPaginationType());
        }

        @Test
        @DisplayName("addPrinter应正确添加打印机")
        void testAddPrinter() {
            PrinterItem newPrinter = new PrinterItem();
            newPrinter.setShopId("shop002");
            newPrinter.setBrand("新品牌");
            newPrinter.setMachineCode("printer003");
            
            shopPrinterListResp.addPrinter(newPrinter);
            assertEquals(1, shopPrinterListResp.getPrinterCount());
            assertEquals("printer003", shopPrinterListResp.getData().get(0).getMachineCode());
            
            shopPrinterListResp.addPrinter(null);
            assertEquals(1, shopPrinterListResp.getPrinterCount());
        }

        @Test
        @DisplayName("addPrinters应正确添加多个打印机")
        void testAddPrinters() {
            shopPrinterListResp.addPrinters(testPrinters);
            assertEquals(2, shopPrinterListResp.getPrinterCount());
            
            shopPrinterListResp.addPrinters(null);
            assertEquals(2, shopPrinterListResp.getPrinterCount());
            
            shopPrinterListResp.addPrinters(new ArrayList<>());
            assertEquals(2, shopPrinterListResp.getPrinterCount());
        }

        @Test
        @DisplayName("clearPrinters应清空打印机数据")
        void testClearPrinters() {
            shopPrinterListResp.setData(testPrinters);
            assertEquals(2, shopPrinterListResp.getPrinterCount());
            
            shopPrinterListResp.clearPrinters();
            assertEquals(0, shopPrinterListResp.getPrinterCount());
            assertTrue(shopPrinterListResp.isEmpty());
        }

        @Test
        @DisplayName("getImmutablePrinters应返回不可变列表")
        void testGetImmutablePrinters() {
            shopPrinterListResp.setData(testPrinters);
            
            List<PrinterItem> immutablePrinters = shopPrinterListResp.getImmutablePrinters();
            assertEquals(2, immutablePrinters.size());
            
            assertThrows(UnsupportedOperationException.class, () -> {
                immutablePrinters.add(new PrinterItem());
            });
        }

        @Test
        @DisplayName("getValidPrinters应返回有效的打印机列表")
        void testGetValidPrinters() {
            // 创建包含有效和无效打印机的列表
            List<PrinterItem> mixedPrinters = new ArrayList<>();
            PrinterItem validPrinter = new PrinterItem();
            validPrinter.setShopId("shop001");
            validPrinter.setMachineCode("valid001");
            validPrinter.setBrand("有效品牌");
            mixedPrinters.add(validPrinter);
            
            PrinterItem invalidPrinter = new PrinterItem();
            invalidPrinter.setShopId("");
            invalidPrinter.setMachineCode("");
            mixedPrinters.add(invalidPrinter);
            
            mixedPrinters.add(null);
            
            shopPrinterListResp.setData(mixedPrinters);
            
            List<PrinterItem> validPrinters = shopPrinterListResp.getValidPrinters();
            assertEquals(1, validPrinters.size());
            assertEquals("valid001", validPrinters.get(0).getMachineCode());
        }

        @Test
        @DisplayName("findPrinterByCode应能找到正确的打印机")
        void testFindPrinterByCode() {
            shopPrinterListResp.setData(testPrinters);
            
            PrinterItem found = shopPrinterListResp.findPrinterByCode("printer001");
            assertNotNull(found);
            assertEquals("printer001", found.getMachineCode());
            assertEquals("飞鹅", found.getBrand());
            
            PrinterItem notFound = shopPrinterListResp.findPrinterByCode("printer999");
            assertNull(notFound);
            
            PrinterItem nullSearch = shopPrinterListResp.findPrinterByCode(null);
            assertNull(nullSearch);
        }

        @Test
        @DisplayName("getPrintersByBrand应按品牌筛选打印机")
        void testGetPrintersByBrand() {
            shopPrinterListResp.setData(testPrinters);
            
            List<PrinterItem> flygeePrinters = shopPrinterListResp.getPrintersByBrand("飞鹅");
            assertEquals(1, flygeePrinters.size());
            assertEquals("printer001", flygeePrinters.get(0).getMachineCode());
            
            List<PrinterItem> notFound = shopPrinterListResp.getPrintersByBrand("不存在品牌");
            assertTrue(notFound.isEmpty());
            
            List<PrinterItem> nullBrand = shopPrinterListResp.getPrintersByBrand(null);
            assertTrue(nullBrand.isEmpty());
        }

        @Test
        @DisplayName("getPrintersByShop应按门店筛选打印机")
        void testGetPrintersByShop() {
            shopPrinterListResp.setData(testPrinters);
            
            List<PrinterItem> shop001Printers = shopPrinterListResp.getPrintersByShop("shop001");
            assertEquals(2, shop001Printers.size());
            
            List<PrinterItem> notFound = shopPrinterListResp.getPrintersByShop("shop999");
            assertTrue(notFound.isEmpty());
        }

        @Test
        @DisplayName("getAllBrands应返回去重的品牌列表")
        void testGetAllBrands() {
            shopPrinterListResp.setData(testPrinters);
            
            List<String> brands = shopPrinterListResp.getAllBrands();
            assertEquals(2, brands.size());
            assertTrue(brands.contains("飞鹅"));
            assertTrue(brands.contains("小票"));
        }

        @Test
        @DisplayName("getStatistics应返回正确的统计信息")
        void testGetStatistics() {
            // 空响应
            String emptyStats = shopPrinterListResp.getStatistics();
            assertEquals("无打印机设备", emptyStats);
            
            // 有数据的响应
            shopPrinterListResp.setData(testPrinters);
            String stats = shopPrinterListResp.getStatistics();
            
            assertTrue(stats.contains("设备总数: 2"));
            assertTrue(stats.contains("品牌数: 2"));
            assertTrue(stats.contains("飞鹅"));
            assertTrue(stats.contains("小票"));
        }
    }

    @Nested
    @DisplayName("Static Factory Methods Tests")
    class StaticFactoryMethodsTests {

        @Test
        @DisplayName("empty应创建空响应对象")
        void testEmpty() {
            ShopPrinterListResp resp = ShopPrinterListResp.empty();
            
            assertNotNull(resp);
            assertTrue(resp.isEmpty());
            assertEquals(0, resp.getPrinterCount());
        }

        @Test
        @DisplayName("of应创建包含打印机数据的响应对象")
        void testOf() {
            ShopPrinterListResp resp = ShopPrinterListResp.of(testPrinters);
            
            assertNotNull(resp);
            assertEquals(2, resp.getPrinterCount());
            assertFalse(resp.isEmpty());
        }

        @Test
        @DisplayName("ofPage应创建普通分页响应对象")
        void testOfPage() {
            ShopPrinterListResp resp = ShopPrinterListResp.ofPage(testPrinters, 2, 5, 100);
            
            assertNotNull(resp);
            assertEquals(2, resp.getPrinterCount());
            assertEquals(2, resp.getPage().intValue());
            assertEquals(5, resp.getTotalPage().intValue());
            assertEquals(100, resp.getTotal().intValue());
            assertFalse(resp.getIsLast());
            assertTrue(resp.isNormalPagination());
        }

        @Test
        @DisplayName("ofScroll应创建游标分页响应对象")
        void testOfScroll() {
            ShopPrinterListResp resp = ShopPrinterListResp.ofScroll(testPrinters, "scroll123", false);
            
            assertNotNull(resp);
            assertEquals(2, resp.getPrinterCount());
            assertEquals("scroll123", resp.getScrollId());
            assertFalse(resp.getIsLast());
            assertTrue(resp.isScrollPagination());
        }

        @Test
        @DisplayName("lastPage应创建最后一页响应对象")
        void testLastPage() {
            ShopPrinterListResp resp = ShopPrinterListResp.lastPage(testPrinters);
            
            assertNotNull(resp);
            assertEquals(2, resp.getPrinterCount());
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
            ShopPrinterListResp resp = ShopPrinterListResp.builder()
                    .data(testPrinters)
                    .page(1)
                    .totalPage(5)
                    .total(100)
                    .isLast(false)
                    .scrollId("scroll123")
                    .build();
            
            assertEquals(2, resp.getPrinterCount());
            assertEquals(1, resp.getPage().intValue());
            assertEquals(5, resp.getTotalPage().intValue());
            assertEquals(100, resp.getTotal().intValue());
            assertFalse(resp.getIsLast());
            assertEquals("scroll123", resp.getScrollId());
        }

        @Test
        @DisplayName("Builder应能添加单个打印机")
        void testBuilderAddPrinter() {
            PrinterItem printer = testPrinters.get(0);
            
            ShopPrinterListResp resp = ShopPrinterListResp.builder()
                    .addPrinter(printer)
                    .build();
            
            assertEquals(1, resp.getPrinterCount());
            assertEquals("printer001", resp.getData().get(0).getMachineCode());
        }

        @Test
        @DisplayName("Builder应能添加多个打印机")
        void testBuilderAddPrinters() {
            ShopPrinterListResp resp = ShopPrinterListResp.builder()
                    .addPrinters(testPrinters)
                    .build();
            
            assertEquals(2, resp.getPrinterCount());
        }

        @Test
        @DisplayName("Builder应能处理null值")
        void testBuilderWithNullValues() {
            ShopPrinterListResp resp = ShopPrinterListResp.builder()
                    .addPrinter(null)
                    .addPrinters(null)
                    .data(null)
                    .build();
            
            assertEquals(0, resp.getPrinterCount());
            assertTrue(resp.isEmpty());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("处理大量打印机数据")
        void testLargeDataSet() {
            List<PrinterItem> largePrinterList = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                PrinterItem printer = new PrinterItem();
                printer.setShopId("shop001");
                printer.setBrand("品牌" + (i % 5));
                printer.setMachineCode("printer" + i);
                printer.setMachineSign("sign" + i);
                largePrinterList.add(printer);
            }
            
            ShopPrinterListResp resp = new ShopPrinterListResp(largePrinterList);
            
            assertEquals(1000, resp.getPrinterCount());
            assertTrue(resp.hasPrinters());
            assertFalse(resp.isEmpty());
            assertEquals(5, resp.getAllBrands().size()); // 5个不同的品牌
        }

        @Test
        @DisplayName("处理包含null元素的打印机列表")
        void testDataListWithNullElements() {
            List<PrinterItem> printersWithNulls = new ArrayList<>();
            printersWithNulls.add(testPrinters.get(0));
            printersWithNulls.add(null);
            printersWithNulls.add(testPrinters.get(1));
            
            ShopPrinterListResp resp = new ShopPrinterListResp(printersWithNulls);
            
            assertEquals(3, resp.getPrinterCount());
            assertNull(resp.getData().get(1));
            
            // getValidPrinters应过滤掉null元素
            List<PrinterItem> validPrinters = resp.getValidPrinters();
            assertEquals(2, validPrinters.size());
        }

        @Test
        @DisplayName("处理极端分页参数")
        void testExtremePaginationParameters() {
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            
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
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            
            resp.setScrollId("");
            assertFalse(resp.isScrollPagination());
            assertEquals("无分页信息", resp.getPaginationType());
            
            resp.setScrollId("   ");
            assertFalse(resp.isScrollPagination());
        }

        @Test
        @DisplayName("处理同时设置两种分页参数")
        void testMixedPaginationParameters() {
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            resp.setPage(1);
            resp.setTotalPage(5);
            resp.setScrollId("scroll123");
            
            // 游标分页优先
            assertTrue(resp.isScrollPagination());
            assertTrue(resp.isNormalPagination());
            assertEquals("游标分页", resp.getPaginationType());
        }

        @Test
        @DisplayName("处理没有品牌信息的打印机")
        void testPrintersWithoutBrand() {
            List<PrinterItem> noBrandPrinters = new ArrayList<>();
            PrinterItem printer1 = new PrinterItem();
            printer1.setShopId("shop001");
            printer1.setMachineCode("printer001");
            printer1.setBrand(null);
            noBrandPrinters.add(printer1);
            
            PrinterItem printer2 = new PrinterItem();
            printer2.setShopId("shop001");
            printer2.setMachineCode("printer002");
            printer2.setBrand("");
            noBrandPrinters.add(printer2);
            
            ShopPrinterListResp resp = new ShopPrinterListResp(noBrandPrinters);
            
            List<String> brands = resp.getAllBrands();
            assertTrue(brands.isEmpty()); // 没有有效品牌
            
            String stats = resp.getStatistics();
            assertTrue(stats.contains("设备总数: 2"));
            assertFalse(stats.contains("品牌数")); // 没有品牌信息
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
                    ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters, i, 100, 10000, i >= 99);
                    assertEquals(2, resp.getPrinterCount());
                }
            });
        }

        @Test
        @DisplayName("大数据列表操作应高效执行")
        void testLargeDataOperations() {
            List<PrinterItem> largeList = new ArrayList<>();
            for (int i = 0; i < 5000; i++) {
                PrinterItem printer = new PrinterItem();
                printer.setShopId("shop001");
                printer.setBrand("品牌" + (i % 10));
                printer.setMachineCode("printer" + i);
                printer.setMachineSign("sign" + i);
                largeList.add(printer);
            }
            
            assertDoesNotThrow(() -> {
                ShopPrinterListResp resp = new ShopPrinterListResp();
                resp.addPrinters(largeList);
                assertEquals(5000, resp.getPrinterCount());
                
                List<PrinterItem> immutable = resp.getImmutablePrinters();
                assertNotNull(immutable);
                assertEquals(5000, immutable.size());
                
                List<String> brands = resp.getAllBrands();
                assertEquals(10, brands.size());
            });
        }

        @Test
        @DisplayName("频繁的筛选操作应高效执行")
        void testFilteringPerformance() {
            ShopPrinterListResp resp = new ShopPrinterListResp(testPrinters);
            
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    List<PrinterItem> byBrand = resp.getPrintersByBrand("飞鹅");
                    assertEquals(1, byBrand.size());
                    
                    List<PrinterItem> byShop = resp.getPrintersByShop("shop001");
                    assertEquals(2, byShop.size());
                    
                    PrinterItem found = resp.findPrinterByCode("printer001");
                    assertNotNull(found);
                }
            });
        }

        @Test
        @DisplayName("equals和hashCode方法应高效执行")
        void testEqualsHashCodePerformance() {
            ShopPrinterListResp resp1 = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            ShopPrinterListResp resp2 = new ShopPrinterListResp(testPrinters, 1, 5, 100, false);
            
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