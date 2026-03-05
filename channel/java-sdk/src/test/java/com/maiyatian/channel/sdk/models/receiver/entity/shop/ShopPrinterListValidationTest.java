package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 门店打印机列表实体类验证测试
 */
@DisplayName("门店打印机列表实体类测试")
class ShopPrinterListValidationTest {

    private final ObjectMapper objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();

    @Nested
    @DisplayName("ShopPrinterListReq测试")
    class ShopPrinterListReqTests {
        
        @Test
        @DisplayName("测试基本请求")
        void testBasicRequest() {
            // 测试基本请求
            ShopPrinterListReq basicReq = ShopPrinterListReq.of("SHOP_001");
            assertTrue(basicReq.isValid(), "基本请求应该有效");
            assertFalse(basicReq.isNormalPagination(), "基本请求不应该有分页信息");
            assertFalse(basicReq.isScrollPagination(), "基本请求不应该有游标分页");
            assertFalse(basicReq.isValidPagination(), "基本请求分页参数应该无效");
        }
        
        @Test
        @DisplayName("测试普通分页请求")
        void testNormalPaginationRequest() {
            // 测试普通分页请求
            ShopPrinterListReq pageReq = ShopPrinterListReq.ofPage("SHOP_001", 1, 20);
            assertTrue(pageReq.isNormalPagination(), "应该是普通分页");
            assertFalse(pageReq.isScrollPagination(), "不应该是游标分页");
            assertTrue(pageReq.isValidPagination(), "分页参数应该有效");
            assertEquals("普通分页", pageReq.getPaginationType(), "分页类型应该是普通分页");
        }
        
        @Test
        @DisplayName("测试游标分页请求")
        void testScrollPaginationRequest() {
            // 测试游标分页请求
            ShopPrinterListReq scrollReq = ShopPrinterListReq.ofScroll("SHOP_001", "scroll_123");
            assertTrue(scrollReq.isScrollPagination(), "应该是游标分页");
            assertFalse(scrollReq.isNormalPagination(), "不应该是普通分页");
            assertTrue(scrollReq.isValidPagination(), "分页参数应该有效");
            assertEquals("游标分页", scrollReq.getPaginationType(), "分页类型应该是游标分页");
        }
        
        @Test
        @DisplayName("测试构建器模式")
        void testBuilderPattern() {
            // 测试构建器模式
            ShopPrinterListReq builderReq = ShopPrinterListReq.builder()
                .shopId("SHOP_002")
                .page(1)
                .pageSize(30)
                .build();
            assertEquals("SHOP_002", builderReq.getShopId(), "门店ID应该是SHOP_002");
            assertEquals(Integer.valueOf(1), builderReq.getPage(), "页码应该是1");
            assertEquals(Integer.valueOf(30), builderReq.getPageSize(), "每页数量应该是30");
        }
        
        @Test
        @DisplayName("测试分页操作")
        void testPaginationOperations() {
            // 测试分页操作
            ShopPrinterListReq firstPageReq = ShopPrinterListReq.firstPage("SHOP_003");
            assertEquals(Integer.valueOf(1), firstPageReq.getPage(), "第一页页码应该是1");
            firstPageReq.nextPage();
            assertEquals(Integer.valueOf(2), firstPageReq.getPage(), "下一页页码应该是2");
            firstPageReq.previousPage();
            assertEquals(Integer.valueOf(1), firstPageReq.getPage(), "上一页页码应该是1");
        }
    }

    @Nested
    @DisplayName("PrinterItem测试")
    class PrinterItemTests {
        
        @Test
        @DisplayName("测试基本打印机信息")
        void testBasicPrinterInfo() {
            // 测试基本打印机信息
            PrinterItem basicPrinter = PrinterItem.of("SHOP_001", "PRINTER_001");
            assertTrue(basicPrinter.isValid(), "基本打印机信息应该有效");
            assertFalse(basicPrinter.hasBrand(), "不应该有品牌");
            assertFalse(basicPrinter.hasName(), "不应该有名称");
            assertFalse(basicPrinter.hasSign(), "不应该有设备密钥");
            assertFalse(basicPrinter.hasPrintNumber(), "不应该有打印份数");
            assertFalse(basicPrinter.hasWidth(), "不应该有纸张宽度");
        }
        
        @Test
        @DisplayName("测试包含基本配置的打印机")
        void testBasicConfigPrinter() {
            // 测试包含基本配置的打印机
            PrinterItem basicConfigPrinter = PrinterItem.basic(
                "SHOP_001", "佳博", "GP-80250I", "PRINTER_001", "SECRET_001"
            );
            assertTrue(basicConfigPrinter.hasBrand(), "应该有品牌");
            assertTrue(basicConfigPrinter.hasName(), "应该有名称");
            assertTrue(basicConfigPrinter.hasSign(), "应该有设备密钥");
            assertEquals("[佳博] GP-80250I", basicConfigPrinter.getDisplayName(), "显示名称应该包含品牌和名称");
            assertEquals("已配置设备密钥", basicConfigPrinter.getSecurityStatus(), "安全状态应该已配置");
            assertTrue(basicConfigPrinter.belongsToShop("SHOP_001"), "应该属于SHOP_001");
            assertTrue(basicConfigPrinter.isBrand("佳博"), "应该是佳博品牌");
        }
        
        @Test
        @DisplayName("测试完整的打印机信息")
        void testCompletePrinterInfo() {
            // 测试完整的打印机信息
            PrinterItem completePrinter = PrinterItem.complete(
                "SHOP_002", "芯烨", "XP-Q800", "PRINTER_002", "SECRET_002", 2, "80mm"
            );
            assertTrue(completePrinter.isValid(), "完整打印机信息应该有效");
            assertTrue(completePrinter.hasPrintNumber(), "应该有打印份数");
            assertTrue(completePrinter.hasWidth(), "应该有纸张宽度");
            assertEquals(Integer.valueOf(2), completePrinter.getPrintNumber(), "打印份数应该是2");
            assertEquals("80mm", completePrinter.getWidth(), "纸张宽度应该是80mm");
            assertEquals("纸张宽度: 80mm, 打印份数: 2", completePrinter.getSpecification(), "规格信息不正确");
        }
        
        @Test
        @DisplayName("测试构建器模式")
        void testBuilderPattern() {
            // 测试构建器模式
            PrinterItem builderPrinter = PrinterItem.builder()
                .shopId("SHOP_003")
                .brand("汉印")
                .name("HM-A300")
                .machineCode("PRINTER_003")
                .machineSign("SECRET_003")
                .printNumber(1)
                .width("58mm")
                .build();
            assertTrue(builderPrinter.isValid(), "构建器创建的打印机应该有效");
            assertEquals("汉印", builderPrinter.getBrand(), "品牌应该是汉印");
            assertEquals("HM-A300", builderPrinter.getName(), "名称应该是HM-A300");
        }
        
        @Test
        @DisplayName("测试设备比较")
        void testDeviceComparison() {
            // 测试设备比较
            PrinterItem basicPrinter = PrinterItem.of("SHOP_001", "PRINTER_001");
            PrinterItem samePrinter = PrinterItem.of("SHOP_001", "PRINTER_001");
            assertTrue(basicPrinter.isSameDevice(samePrinter), "应该是同一台设备");
            
            PrinterItem differentPrinter = PrinterItem.of("SHOP_002", "PRINTER_001");
            assertFalse(basicPrinter.isSameDevice(differentPrinter), "不应该是同一台设备");
        }
    }

    @Nested
    @DisplayName("ShopPrinterListResp测试")
    class ShopPrinterListRespTests {
        
        private PrinterItem printer1 = PrinterItem.basic("SHOP_001", "佳博", "GP-80250I", "PRINTER_001", "SECRET_001");
        private PrinterItem printer2 = PrinterItem.basic("SHOP_001", "芯烨", "XP-Q800", "PRINTER_002", "SECRET_002");
        private PrinterItem printer3 = PrinterItem.basic("SHOP_002", "汉印", "HM-A300", "PRINTER_003", "SECRET_003");
        
        @Test
        @DisplayName("测试空响应")
        void testEmptyResponse() {
            // 测试空响应
            ShopPrinterListResp emptyResp = ShopPrinterListResp.empty();
            assertTrue(emptyResp.isEmpty(), "空响应应该为空");
            assertFalse(emptyResp.hasPrinters(), "空响应不应该有打印机");
            assertEquals(0, emptyResp.getPrinterCount(), "空响应打印机数量应该为0");
        }
        
        @Test
        @DisplayName("测试普通分页响应")
        void testNormalPaginationResponse() {
            // 测试普通分页响应
            ShopPrinterListResp pageResp = ShopPrinterListResp.ofPage(
                java.util.Arrays.asList(printer1, printer2), 1, 2, 4
            );
            assertTrue(pageResp.hasPrinters(), "分页响应应该有打印机");
            assertEquals(2, pageResp.getPrinterCount(), "分页响应打印机数量应该是2");
            assertTrue(pageResp.isNormalPagination(), "应该是普通分页");
            assertFalse(pageResp.isScrollPagination(), "不应该是游标分页");
            assertTrue(pageResp.hasNextPage(), "应该有下一页");
            assertEquals("普通分页", pageResp.getPaginationType(), "分页类型应该是普通分页");
        }
        
        @Test
        @DisplayName("测试游标分页响应")
        void testScrollPaginationResponse() {
            // 测试游标分页响应
            ShopPrinterListResp scrollResp = ShopPrinterListResp.ofScroll(
                java.util.Arrays.asList(printer1), "scroll_123", false
            );
            assertTrue(scrollResp.isScrollPagination(), "应该是游标分页");
            assertFalse(scrollResp.isNormalPagination(), "不应该是普通分页");
            assertTrue(scrollResp.hasNextPage(), "应该有下一页");
            assertEquals("游标分页", scrollResp.getPaginationType(), "分页类型应该是游标分页");
        }
        
        @Test
        @DisplayName("测试最后一页响应")
        void testLastPageResponse() {
            // 测试最后一页响应
            ShopPrinterListResp lastResp = ShopPrinterListResp.lastPage(java.util.Arrays.asList(printer1));
            assertFalse(lastResp.hasNextPage(), "最后一页不应该有下一页");
        }
        
        @Test
        @DisplayName("测试构建器模式和查找功能")
        void testBuilderPatternAndSearchFunctionality() {
            // 测试构建器模式
            ShopPrinterListResp builderResp = ShopPrinterListResp.builder()
                .addPrinter(printer1)
                .addPrinter(printer2)
                .addPrinter(printer3)
                .page(1)
                .totalPage(1)
                .total(3)
                .isLast(true)
                .build();
            assertEquals(3, builderResp.getPrinterCount(), "构建器创建的响应打印机数量应该是3");

            // 测试查找功能
            assertNotNull(builderResp.findPrinterByCode("PRINTER_001"), "应该能找到PRINTER_001");
            assertNull(builderResp.findPrinterByCode("PRINTER_999"), "不应该找到PRINTER_999");

            // 测试筛选功能
            assertEquals(2, builderResp.getPrintersByShop("SHOP_001").size(), "SHOP_001应该有2台打印机");
            assertEquals(1, builderResp.getPrintersByShop("SHOP_002").size(), "SHOP_002应该有1台打印机");
            assertEquals(1, builderResp.getPrintersByBrand("佳博").size(), "佳博品牌应该有1台打印机");
            assertEquals(3, builderResp.getValidPrinters().size(), "有效打印机应该有3台");
            assertEquals(3, builderResp.getPrintersWithSign().size(), "有密钥的打印机应该有3台");
        }
        
        @Test
        @DisplayName("测试统计功能")
        void testStatisticsFunctionality() {
            // 测试统计功能
            ShopPrinterListResp builderResp = ShopPrinterListResp.builder()
                .addPrinter(printer1)
                .addPrinter(printer2)
                .addPrinter(printer3)
                .page(1)
                .totalPage(1)
                .total(3)
                .isLast(true)
                .build();
            
            assertEquals(3, builderResp.getAllBrands().size(), "应该有3个不同品牌");
            assertTrue(builderResp.getAllBrands().contains("佳博"), "应该包含佳博品牌");
            assertTrue(builderResp.getAllBrands().contains("芯烨"), "应该包含芯烨品牌");
            assertTrue(builderResp.getAllBrands().contains("汉印"), "应该包含汉印品牌");

            String stats = builderResp.getStatistics();
            assertTrue(stats.contains("设备总数: 3"), "统计信息应该包含设备总数");
            assertTrue(stats.contains("品牌数: 3"), "统计信息应该包含品牌数");
            assertTrue(stats.contains("已配置密钥: 3台"), "统计信息应该包含已配置密钥数量");
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {
        
        @Test
        @DisplayName("测试ShopPrinterListReq序列化和反序列化")
        void testShopPrinterListReqSerialization() throws Exception {
            // 测试ShopPrinterListReq序列化和反序列化
            ShopPrinterListReq req = ShopPrinterListReq.builder()
                .shopId("SHOP_001")
                .page(1)
                .pageSize(20)
                .scrollId("scroll_123")
                .build();

            String reqJson = objectMapper.writeValueAsString(req);
            assertTrue(reqJson.contains("\"shop_id\":\"SHOP_001\""), "JSON应包含shop_id字段");
            assertTrue(reqJson.contains("\"page\":1"), "JSON应包含page字段");
            assertTrue(reqJson.contains("\"page_size\":20"), "JSON应包含page_size字段");
            assertTrue(reqJson.contains("\"scroll_id\":\"scroll_123\""), "JSON应包含scroll_id字段");

            ShopPrinterListReq deserializedReq = objectMapper.readValue(reqJson, ShopPrinterListReq.class);
            assertEquals(req, deserializedReq, "反序列化后的请求对象应该相等");
        }
        
        @Test
        @DisplayName("测试PrinterItem序列化和反序列化")
        void testPrinterItemSerialization() throws Exception {
            // 测试PrinterItem序列化和反序列化
            PrinterItem printer = PrinterItem.complete(
                "SHOP_001", "佳博", "GP-80250I", "PRINTER_001", "SECRET_001", 2, "80mm"
            );

            String printerJson = objectMapper.writeValueAsString(printer);
            assertTrue(printerJson.contains("\"shop_id\":\"SHOP_001\""), "JSON应包含shop_id字段");
            assertTrue(printerJson.contains("\"brand\":\"佳博\""), "JSON应包含brand字段");
            assertTrue(printerJson.contains("\"machine_code\":\"PRINTER_001\""), "JSON应包含machine_code字段");
            assertTrue(printerJson.contains("\"print_number\":2"), "JSON应包含print_number字段");

            PrinterItem deserializedPrinter = objectMapper.readValue(printerJson, PrinterItem.class);
            assertEquals(printer, deserializedPrinter, "反序列化后的打印机对象应该相等");
        }
        
        @Test
        @DisplayName("测试ShopPrinterListResp序列化和反序列化")
        void testShopPrinterListRespSerialization() throws Exception {
            // 测试ShopPrinterListResp序列化和反序列化
            PrinterItem printer = PrinterItem.complete(
                "SHOP_001", "佳博", "GP-80250I", "PRINTER_001", "SECRET_001", 2, "80mm"
            );
            
            ShopPrinterListResp resp = ShopPrinterListResp.builder()
                .addPrinter(printer)
                .page(1)
                .totalPage(1)
                .total(1)
                .isLast(true)
                .build();

            String respJson = objectMapper.writeValueAsString(resp);
            assertTrue(respJson.contains("\"data\":["), "JSON应包含data数组");
            assertTrue(respJson.contains("\"page\":1"), "JSON应包含page字段");
            assertTrue(respJson.contains("\"total\":1"), "JSON应包含total字段");
            assertTrue(respJson.contains("\"isLast\":true"), "JSON应包含isLast字段");

            ShopPrinterListResp deserializedResp = objectMapper.readValue(respJson, ShopPrinterListResp.class);
            assertEquals(resp, deserializedResp, "反序列化后的响应对象应该相等");
        }
    }
}