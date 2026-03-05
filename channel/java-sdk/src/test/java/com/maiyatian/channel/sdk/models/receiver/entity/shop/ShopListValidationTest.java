package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 门店列表实体类验证测试
 */
@DisplayName("门店列表实体类测试")
class ShopListValidationTest {

    private final ObjectMapper objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();

    @Nested
    @DisplayName("ShopListReq测试")
    class ShopListReqTests {
        
        @Test
        @DisplayName("测试普通分页请求")
        void testNormalPaginationRequest() {
            // 测试普通分页请求
            ShopListReq normalPageReq = ShopListReq.ofPage(1, 20);
            assertTrue(normalPageReq.isNormalPagination(), "应该是普通分页");
            assertFalse(normalPageReq.isScrollPagination(), "不应该是游标分页");
            assertTrue(normalPageReq.isValidPagination(), "分页参数应该有效");
            assertEquals("普通分页", normalPageReq.getPaginationType(), "分页类型应该是普通分页");
        }
        
        @Test
        @DisplayName("测试游标分页请求")
        void testScrollPaginationRequest() {
            // 测试游标分页请求
            ShopListReq scrollReq = ShopListReq.ofScroll("scroll_123");
            assertTrue(scrollReq.isScrollPagination(), "应该是游标分页");
            assertFalse(scrollReq.isNormalPagination(), "不应该是普通分页");
            assertTrue(scrollReq.isValidPagination(), "分页参数应该有效");
            assertEquals("游标分页", scrollReq.getPaginationType(), "分页类型应该是游标分页");
        }
        
        @Test
        @DisplayName("测试构建器模式")
        void testBuilderPattern() {
            // 测试构建器模式
            ShopListReq builderReq = ShopListReq.builder()
                .page(1)
                .pageSize(30)
                .build();
            assertEquals(Integer.valueOf(1), builderReq.getPage(), "页码应该是1");
            assertEquals(Integer.valueOf(30), builderReq.getPageSize(), "每页数量应该是30");
        }
        
        @Test
        @DisplayName("测试分页操作")
        void testPaginationOperations() {
            // 测试分页操作
            ShopListReq pageReq = ShopListReq.firstPage();
            assertEquals(Integer.valueOf(1), pageReq.getPage(), "第一页页码应该是1");
            pageReq.nextPage();
            assertEquals(Integer.valueOf(2), pageReq.getPage(), "下一页页码应该是2");
            pageReq.previousPage();
            assertEquals(Integer.valueOf(1), pageReq.getPage(), "上一页页码应该是1");
        }
    }

    @Nested
    @DisplayName("ShopItem测试")
    class ShopItemTests {
        
        @Test
        @DisplayName("测试基本门店信息")
        void testBasicShopInfo() {
            // 测试基本门店信息
            ShopItem basicShop = ShopItem.of("SHOP_001", "测试门店");
            assertTrue(basicShop.isValid(), "基本门店信息应该有效");
            assertFalse(basicShop.hasPhone(), "不应该有电话");
            assertFalse(basicShop.hasAddress(), "不应该有地址");
            assertFalse(basicShop.hasCoordinates(), "不应该有坐标");
            assertEquals("测试门店", basicShop.getDisplayName(), "显示名称应该是门店名称");
        }
        
        @Test
        @DisplayName("测试包含地址的门店信息")
        void testShopWithAddress() {
            // 测试包含地址的门店信息
            ShopItem shopWithAddress = ShopItem.withAddress(
                "SHOP_002", "北京门店", "010-12345678", 
                "北京市", "朝阳区", "三里屯街道1号"
            );
            assertTrue(shopWithAddress.hasPhone(), "应该有电话");
            assertTrue(shopWithAddress.hasAddress(), "应该有地址");
            assertTrue(shopWithAddress.isInCity("朝阳区"), "应该在朝阳区");
            assertTrue(shopWithAddress.isInProvince("北京市"), "应该在北京市");
            assertEquals("北京市 朝阳区 三里屯街道1号", shopWithAddress.getFullAddress(), "完整地址格式不正确");
            assertEquals("[朝阳区] 北京门店", shopWithAddress.getDisplayName(), "显示名称应该包含城市");
        }
        
        @Test
        @DisplayName("测试包含坐标的门店信息")
        void testShopWithCoordinates() {
            // 测试包含坐标的门店信息
            ShopItem shopWithCoords = ShopItem.withCoordinates(
                "SHOP_003", "上海门店", "116.397477", "39.916668"
            );
            assertTrue(shopWithCoords.hasCoordinates(), "应该有坐标");
            assertEquals("经度: 116.397477, 纬度: 39.916668", shopWithCoords.getFormattedCoordinates(), "格式化坐标不正确");
        }
        
        @Test
        @DisplayName("测试构建器模式")
        void testBuilderPattern() {
            // 测试构建器模式
            ShopItem builderShop = ShopItem.builder()
                .shopId("SHOP_004")
                .name("完整门店")
                .phone("021-87654321")
                .province("上海市")
                .city("黄浦区")
                .address("南京路100号")
                .coordinates("121.473704", "31.230391")
                .build();
            assertTrue(builderShop.isValid(), "构建器创建的门店应该有效");
            assertTrue(builderShop.hasPhone(), "应该有电话");
            assertTrue(builderShop.hasAddress(), "应该有地址");
            assertTrue(builderShop.hasCoordinates(), "应该有坐标");
        }
    }

    @Nested
    @DisplayName("ShopListResp测试")
    class ShopListRespTests {
        
        private ShopItem shop1 = ShopItem.of("SHOP_001", "门店1");
        private ShopItem shop2 = ShopItem.of("SHOP_002", "门店2");
        private ShopItem shop3 = ShopItem.withAddress("SHOP_003", "北京门店", "010-12345678", 
            "北京市", "朝阳区", "测试地址");
        
        @Test
        @DisplayName("测试空响应")
        void testEmptyResponse() {
            // 测试空响应
            ShopListResp emptyResp = ShopListResp.empty();
            assertTrue(emptyResp.isEmpty(), "空响应应该为空");
            assertFalse(emptyResp.hasShops(), "空响应不应该有门店");
            assertEquals(0, emptyResp.getShopCount(), "空响应门店数量应该为0");
        }
        
        @Test
        @DisplayName("测试普通分页响应")
        void testNormalPaginationResponse() {
            // 测试普通分页响应
            ShopListResp pageResp = ShopListResp.ofPage(java.util.Arrays.asList(shop1, shop2), 1, 2, 3);
            assertTrue(pageResp.hasShops(), "分页响应应该有门店");
            assertEquals(2, pageResp.getShopCount(), "分页响应门店数量应该是2");
            assertTrue(pageResp.isNormalPagination(), "应该是普通分页");
            assertFalse(pageResp.isScrollPagination(), "不应该是游标分页");
            assertTrue(pageResp.hasNextPage(), "应该有下一页");
            assertEquals("普通分页", pageResp.getPaginationType(), "分页类型应该是普通分页");
        }
        
        @Test
        @DisplayName("测试游标分页响应")
        void testScrollPaginationResponse() {
            // 测试游标分页响应
            ShopListResp scrollResp = ShopListResp.ofScroll(java.util.Arrays.asList(shop1), "scroll_123", false);
            assertTrue(scrollResp.isScrollPagination(), "应该是游标分页");
            assertFalse(scrollResp.isNormalPagination(), "不应该是普通分页");
            assertTrue(scrollResp.hasNextPage(), "应该有下一页");
            assertEquals("游标分页", scrollResp.getPaginationType(), "分页类型应该是游标分页");
        }
        
        @Test
        @DisplayName("测试最后一页响应")
        void testLastPageResponse() {
            // 测试最后一页响应
            ShopListResp lastResp = ShopListResp.lastPage(java.util.Arrays.asList(shop1));
            assertFalse(lastResp.hasNextPage(), "最后一页不应该有下一页");
        }
        
        @Test
        @DisplayName("测试构建器模式和查找功能")
        void testBuilderPatternAndSearchFunctionality() {
            // 测试构建器模式
            ShopListResp builderResp = ShopListResp.builder()
                .addShop(shop1)
                .addShop(shop2)
                .addShop(shop3)
                .page(1)
                .totalPage(1)
                .total(3)
                .isLast(true)
                .build();
            assertEquals(3, builderResp.getShopCount(), "构建器创建的响应门店数量应该是3");

            // 测试查找功能
            assertNotNull(builderResp.findShopById("SHOP_001"), "应该能找到SHOP_001");
            assertNull(builderResp.findShopById("SHOP_999"), "不应该找到SHOP_999");

            // 测试筛选功能
            assertEquals(1, builderResp.getShopsByCity("朝阳区").size(), "朝阳区应该有1个门店");
            assertEquals(1, builderResp.getShopsByProvince("北京市").size(), "北京市应该有1个门店");
            assertEquals(3, builderResp.getValidShops().size(), "有效门店应该有3个");
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {
        
        @Test
        @DisplayName("测试ShopListReq序列化和反序列化")
        void testShopListReqSerialization() throws Exception {
            // 测试ShopListReq序列化和反序列化
            ShopListReq req = ShopListReq.builder()
                .page(1)
                .pageSize(20)
                .scrollId("scroll_123")
                .build();

            String reqJson = objectMapper.writeValueAsString(req);
            assertTrue(reqJson.contains("\"page\":1"), "JSON应包含page字段");
            assertTrue(reqJson.contains("\"page_size\":20"), "JSON应包含page_size字段");
            assertTrue(reqJson.contains("\"scroll_id\":\"scroll_123\""), "JSON应包含scroll_id字段");

            ShopListReq deserializedReq = objectMapper.readValue(reqJson, ShopListReq.class);
            assertEquals(req, deserializedReq, "反序列化后的请求对象应该相等");
        }
        
        @Test
        @DisplayName("测试ShopItem序列化和反序列化")
        void testShopItemSerialization() throws Exception {
            // 测试ShopItem序列化和反序列化
            ShopItem shop = ShopItem.complete(
                "SHOP_001", "测试门店", "010-12345678", 
                "北京市", "朝阳区", "测试地址", 
                "121.473704", "31.230391"
            );

            String shopJson = objectMapper.writeValueAsString(shop);
            assertTrue(shopJson.contains("\"shop_id\":\"SHOP_001\""), "JSON应包含shop_id字段");
            assertTrue(shopJson.contains("\"name\":\"测试门店\""), "JSON应包含name字段");

            ShopItem deserializedShop = objectMapper.readValue(shopJson, ShopItem.class);
            assertEquals(shop, deserializedShop, "反序列化后的门店对象应该相等");
        }
        
        @Test
        @DisplayName("测试ShopListResp序列化和反序列化")
        void testShopListRespSerialization() throws Exception {
            // 测试ShopListResp序列化和反序列化
            ShopItem shop = ShopItem.complete(
                "SHOP_001", "测试门店", "010-12345678", 
                "北京市", "朝阳区", "测试地址", 
                "121.473704", "31.230391"
            );
            
            ShopListResp resp = ShopListResp.builder()
                .addShop(shop)
                .page(1)
                .totalPage(1)
                .total(1)
                .isLast(true)
                .build();

            String respJson = objectMapper.writeValueAsString(resp);
            assertTrue(respJson.contains("\"data\":["), "JSON应包含data数组");
            assertTrue(respJson.contains("\"page\":1"), "JSON应包含page字段");
            assertTrue(respJson.contains("\"total\":1"), "JSON应包含total字段");

            ShopListResp deserializedResp = objectMapper.readValue(respJson, ShopListResp.class);
            assertEquals(resp, deserializedResp, "反序列化后的响应对象应该相等");
        }
    }
}