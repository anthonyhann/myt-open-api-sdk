/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.receiver.entity.shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShopItem 门店信息项单元测试
 * 
 * 测试覆盖范围：
 * 1. 构造函数测试
 * 2. Getter/Setter 方法测试
 * 3. equals/hashCode/toString 方法测试
 * 4. 业务逻辑方法测试
 * 5. JSON 序列化/反序列化测试
 * 6. 静态工厂方法测试
 * 7. 构建器模式测试
 * 8. 边界条件测试
 */
@DisplayName("ShopItem 门店信息项测试")
public class ShopItemTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            ShopItem shop = new ShopItem();
            
            assertNotNull(shop);
            assertNull(shop.getShopId());
            assertNull(shop.getName());
            assertNull(shop.getPhone());
        }

        @Test
        @DisplayName("基本信息构造函数")
        void testBasicConstructor() {
            ShopItem shop = new ShopItem("SHOP_123", "测试门店");
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertNull(shop.getPhone());
        }

        @Test
        @DisplayName("包含地址信息的构造函数")
        void testAddressConstructor() {
            ShopItem shop = new ShopItem(
                "SHOP_123", "测试门店", "13800138000",
                "北京市", "朝阳区", "三里屯街道1号"
            );
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
            assertEquals("北京市", shop.getProvince());
            assertEquals("朝阳区", shop.getCity());
            assertEquals("三里屯街道1号", shop.getAddress());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            ShopItem shop = new ShopItem(
                "SHOP_123", "测试门店", "13800138000",
                "北京市", "朝阳区", "三里屯街道1号",
                "39.916527", "116.397128"
            );
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
            assertEquals("北京市", shop.getProvince());
            assertEquals("朝阳区", shop.getCity());
            assertEquals("三里屯街道1号", shop.getAddress());
            assertEquals("39.916527", shop.getLatitude());
            assertEquals("116.397128", shop.getLongitude());
        }
    }

    @Nested
    @DisplayName("Getter/Setter 方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("基本字段设置和获取")
        void testBasicFieldsGetterSetter() {
            ShopItem shop = new ShopItem();
            
            shop.setShopId("SHOP_456");
            shop.setName("新门店");
            shop.setPhone("13900139000");
            
            assertEquals("SHOP_456", shop.getShopId());
            assertEquals("新门店", shop.getName());
            assertEquals("13900139000", shop.getPhone());
        }

        @Test
        @DisplayName("地址字段设置和获取")
        void testAddressFieldsGetterSetter() {
            ShopItem shop = new ShopItem();
            
            shop.setProvince("上海市");
            shop.setCity("浦东新区");
            shop.setAddress("陆家嘴金融贸易区2号");
            
            assertEquals("上海市", shop.getProvince());
            assertEquals("浦东新区", shop.getCity());
            assertEquals("陆家嘴金融贸易区2号", shop.getAddress());
        }

        @Test
        @DisplayName("坐标字段设置和获取")
        void testCoordinateFieldsGetterSetter() {
            ShopItem shop = new ShopItem();
            
            shop.setLatitude("31.230416");
            shop.setLongitude("121.473701");
            
            assertEquals("31.230416", shop.getLatitude());
            assertEquals("121.473701", shop.getLongitude());
        }

        @Test
        @DisplayName("设置null值")
        void testSetNullValues() {
            ShopItem shop = createValidShop();
            
            shop.setShopId(null);
            shop.setName(null);
            shop.setPhone(null);
            shop.setProvince(null);
            shop.setCity(null);
            shop.setAddress(null);
            shop.setLatitude(null);
            shop.setLongitude(null);
            
            assertNull(shop.getShopId());
            assertNull(shop.getName());
            assertNull(shop.getPhone());
            assertNull(shop.getProvince());
            assertNull(shop.getCity());
            assertNull(shop.getAddress());
            assertNull(shop.getLatitude());
            assertNull(shop.getLongitude());
        }
    }

    @Nested
    @DisplayName("Object方法测试")
    class ObjectMethodTests {

        @Test
        @DisplayName("equals方法测试 - 相等对象")
        void testEquals_EqualObjects() {
            ShopItem shop1 = createValidShop();
            ShopItem shop2 = createValidShop();
            
            assertEquals(shop1, shop2);
            assertEquals(shop1, shop1); // 自身相等
        }

        @Test
        @DisplayName("equals方法测试 - 不相等对象")
        void testEquals_NotEqualObjects() {
            ShopItem shop1 = createValidShop();
            ShopItem shop2 = createValidShop();
            shop2.setShopId("DIFFERENT_SHOP");
            
            ShopItem shop3 = createValidShop();
            shop3.setName("不同门店");
            
            assertNotEquals(shop1, shop2);
            assertNotEquals(shop1, shop3);
            assertNotEquals(shop1, null);
            assertNotEquals(shop1, "not a ShopItem");
        }

        @Test
        @DisplayName("hashCode方法测试")
        void testHashCode() {
            ShopItem shop1 = createValidShop();
            ShopItem shop2 = createValidShop();
            ShopItem shop3 = createValidShop();
            shop3.setShopId("DIFFERENT_SHOP");
            
            assertEquals(shop1.hashCode(), shop2.hashCode());
            assertNotEquals(shop1.hashCode(), shop3.hashCode());
        }

        @Test
        @DisplayName("toString方法测试")
        void testToString() {
            ShopItem shop = createValidShop();
            
            String result = shop.toString();
            
            assertNotNull(result);
            assertTrue(result.contains("SHOP_123"));
            assertTrue(result.contains("测试门店"));
            assertTrue(result.contains("ShopItem"));
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid方法测试 - 有效数据")
        void testIsValid_ValidData() {
            ShopItem shop = createValidShop();
            assertTrue(shop.isValid());
        }

        @Test
        @DisplayName("isValid方法测试 - 无效数据")
        void testIsValid_InvalidData() {
            // 测试null值
            assertFalse(new ShopItem(null, "测试门店").isValid());
            assertFalse(new ShopItem("SHOP_123", null).isValid());
            assertFalse(new ShopItem(null, null).isValid());
            
            // 测试空字符串
            assertFalse(new ShopItem("", "测试门店").isValid());
            assertFalse(new ShopItem("SHOP_123", "").isValid());
            assertFalse(new ShopItem("", "").isValid());
            
            // 测试只包含空白字符
            assertFalse(new ShopItem("   ", "测试门店").isValid());
            assertFalse(new ShopItem("SHOP_123", "   ").isValid());
        }

        @Test
        @DisplayName("hasPhone方法测试")
        void testHasPhone() {
            ShopItem shop = new ShopItem();
            
            // 没有电话
            assertFalse(shop.hasPhone());
            
            // 有电话
            shop.setPhone("13800138000");
            assertTrue(shop.hasPhone());
            
            // 空字符串
            shop.setPhone("");
            assertFalse(shop.hasPhone());
            
            // 空白字符
            shop.setPhone("   ");
            assertFalse(shop.hasPhone());
        }

        @Test
        @DisplayName("hasAddress方法测试")
        void testHasAddress() {
            ShopItem shop = new ShopItem();
            
            // 没有地址
            assertFalse(shop.hasAddress());
            
            // 有地址
            shop.setAddress("测试地址");
            assertTrue(shop.hasAddress());
            
            // 空字符串
            shop.setAddress("");
            assertFalse(shop.hasAddress());
        }

        @Test
        @DisplayName("hasCoordinates方法测试")
        void testHasCoordinates() {
            ShopItem shop = new ShopItem();
            
            // 没有坐标
            assertFalse(shop.hasCoordinates());
            
            // 只有经度
            shop.setLongitude("116.397128");
            assertFalse(shop.hasCoordinates());
            
            // 有完整坐标
            shop.setLatitude("39.916527");
            assertTrue(shop.hasCoordinates());
            
            // 测试空字符串
            shop.setLongitude("");
            assertFalse(shop.hasCoordinates());
        }

        @Test
        @DisplayName("getFullAddress方法测试")
        void testGetFullAddress() {
            ShopItem shop = new ShopItem();
            
            // 空地址
            assertEquals("", shop.getFullAddress());
            
            // 只有省份
            shop.setProvince("北京市");
            assertEquals("北京市", shop.getFullAddress());
            
            // 省份 + 城市
            shop.setCity("朝阳区");
            assertEquals("北京市 朝阳区", shop.getFullAddress());
            
            // 完整地址
            shop.setAddress("三里屯街道1号");
            assertEquals("北京市 朝阳区 三里屯街道1号", shop.getFullAddress());
        }

        @Test
        @DisplayName("getFormattedCoordinates方法测试")
        void testGetFormattedCoordinates() {
            ShopItem shop = new ShopItem();
            
            // 没有坐标
            assertEquals("位置坐标不可用", shop.getFormattedCoordinates());
            
            // 有坐标
            shop.setLongitude("116.397128");
            shop.setLatitude("39.916527");
            
            String formatted = shop.getFormattedCoordinates();
            assertTrue(formatted.contains("116.397128"));
            assertTrue(formatted.contains("39.916527"));
        }

        @Test
        @DisplayName("getDisplayName方法测试")
        void testGetDisplayName() {
            ShopItem shop = new ShopItem();
            
            // 只有名称
            shop.setName("测试门店");
            assertEquals("测试门店", shop.getDisplayName());
            
            // 有城市信息
            shop.setCity("北京市");
            assertEquals("[北京市] 测试门店", shop.getDisplayName());
            
            // 空名称
            shop.setName(null);
            assertEquals("[北京市] ", shop.getDisplayName());
        }

        @Test
        @DisplayName("isInCity方法测试")
        void testIsInCity() {
            ShopItem shop = new ShopItem();
            shop.setCity("北京市");
            
            assertTrue(shop.isInCity("北京市"));
            assertFalse(shop.isInCity("上海市"));
            assertFalse(shop.isInCity(null));
            
            // 测试大小写敏感
            assertFalse(shop.isInCity("beijing市"));
        }

        @Test
        @DisplayName("isInProvince方法测试")
        void testIsInProvince() {
            ShopItem shop = new ShopItem();
            shop.setProvince("北京");
            
            assertTrue(shop.isInProvince("北京"));
            assertFalse(shop.isInProvince("上海"));
            assertFalse(shop.isInProvince(null));
        }
    }

    @Nested
    @DisplayName("静态工厂方法测试")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of静态工厂方法测试")
        void testOfFactoryMethod() {
            ShopItem shop = ShopItem.of("SHOP_123", "测试门店");
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertNull(shop.getPhone());
        }

        @Test
        @DisplayName("withAddress静态工厂方法测试")
        void testWithAddressFactoryMethod() {
            ShopItem shop = ShopItem.withAddress(
                "SHOP_123", "测试门店", "13800138000",
                "北京市", "朝阳区", "三里屯街道1号"
            );
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
            assertEquals("北京市", shop.getProvince());
            assertEquals("朝阳区", shop.getCity());
            assertEquals("三里屯街道1号", shop.getAddress());
        }

        @Test
        @DisplayName("withCoordinates静态工厂方法测试")
        void testWithCoordinatesFactoryMethod() {
            ShopItem shop = ShopItem.withCoordinates(
                "SHOP_123", "测试门店", "116.397128", "39.916527"
            );
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("116.397128", shop.getLongitude());
            assertEquals("39.916527", shop.getLatitude());
            assertNull(shop.getPhone());
            assertTrue(shop.hasCoordinates());
        }

        @Test
        @DisplayName("complete静态工厂方法测试")
        void testCompleteFactoryMethod() {
            ShopItem shop = ShopItem.complete(
                "SHOP_123", "测试门店", "13800138000",
                "北京市", "朝阳区", "三里屯街道1号",
                "116.397128", "39.916527"
            );
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
            assertEquals("北京市", shop.getProvince());
            assertEquals("朝阳区", shop.getCity());
            assertEquals("三里屯街道1号", shop.getAddress());
            assertEquals("116.397128", shop.getLongitude());
            assertEquals("39.916527", shop.getLatitude());
            assertTrue(shop.isValid());
            assertTrue(shop.hasPhone());
            assertTrue(shop.hasAddress());
            assertTrue(shop.hasCoordinates());
        }
    }

    @Nested
    @DisplayName("构建器模式测试")
    class BuilderTests {

        @Test
        @DisplayName("构建器基本功能测试")
        void testBuilderBasicFunctionality() {
            ShopItem shop = ShopItem.builder()
                .shopId("SHOP_123")
                .name("测试门店")
                .phone("13800138000")
                .build();
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
        }

        @Test
        @DisplayName("构建器完整功能测试")
        void testBuilderFullFunctionality() {
            ShopItem shop = ShopItem.builder()
                .shopId("SHOP_123")
                .name("测试门店")
                .phone("13800138000")
                .province("北京市")
                .city("朝阳区")
                .address("三里屯街道1号")
                .longitude("116.397128")
                .latitude("39.916527")
                .build();
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
            assertEquals("北京市", shop.getProvince());
            assertEquals("朝阳区", shop.getCity());
            assertEquals("三里屯街道1号", shop.getAddress());
            assertEquals("116.397128", shop.getLongitude());
            assertEquals("39.916527", shop.getLatitude());
        }

        @Test
        @DisplayName("构建器coordinates方法测试")
        void testBuilderCoordinatesMethod() {
            ShopItem shop = ShopItem.builder()
                .shopId("SHOP_123")
                .name("测试门店")
                .coordinates("116.397128", "39.916527")
                .build();
            
            assertNotNull(shop);
            assertEquals("116.397128", shop.getLongitude());
            assertEquals("39.916527", shop.getLatitude());
            assertTrue(shop.hasCoordinates());
        }

        @Test
        @DisplayName("构建器链式调用测试")
        void testBuilderChaining() {
            ShopItem.Builder builder = ShopItem.builder();
            
            assertSame(builder, builder.shopId("SHOP_123"));
            assertSame(builder, builder.name("测试门店"));
            assertSame(builder, builder.phone("13800138000"));
            assertSame(builder, builder.province("北京市"));
            assertSame(builder, builder.city("朝阳区"));
            assertSame(builder, builder.address("三里屯街道1号"));
            assertSame(builder, builder.longitude("116.397128"));
            assertSame(builder, builder.latitude("39.916527"));
        }
    }

    @Nested
    @DisplayName("JSON序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("JSON序列化测试")
        void testJsonSerialization() throws JsonProcessingException {
            ShopItem shop = createValidShop();
            
            String json = objectMapper.writeValueAsString(shop);
            
            assertNotNull(json);
            assertTrue(json.contains("\"shop_id\":\"SHOP_123\""));
            assertTrue(json.contains("\"name\":\"测试门店\""));
            assertTrue(json.contains("\"phone\":\"13800138000\""));
        }

        @Test
        @DisplayName("JSON反序列化测试")
        void testJsonDeserialization() throws JsonProcessingException {
            String json = "{" +
                "\"shop_id\":\"SHOP_123\"," +
                "\"name\":\"测试门店\"," +
                "\"phone\":\"13800138000\"," +
                "\"province\":\"北京市\"," +
                "\"city\":\"朝阳区\"," +
                "\"address\":\"三里屯街道1号\"," +
                "\"longitude\":\"116.397128\"," +
                "\"latitude\":\"39.916527\"" +
                "}";
            
            ShopItem shop = objectMapper.readValue(json, ShopItem.class);
            
            assertNotNull(shop);
            assertEquals("SHOP_123", shop.getShopId());
            assertEquals("测试门店", shop.getName());
            assertEquals("13800138000", shop.getPhone());
            assertEquals("北京市", shop.getProvince());
            assertEquals("朝阳区", shop.getCity());
            assertEquals("三里屯街道1号", shop.getAddress());
            assertEquals("116.397128", shop.getLongitude());
            assertEquals("39.916527", shop.getLatitude());
        }

        @Test
        @DisplayName("JSON往返测试")
        void testJsonRoundTrip() throws JsonProcessingException {
            ShopItem original = createValidShop();
            
            String json = objectMapper.writeValueAsString(original);
            ShopItem deserialized = objectMapper.readValue(json, ShopItem.class);
            
            assertEquals(original, deserialized);
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("超长字符串测试")
        void testVeryLongStrings() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("A");
            }
            String longString = sb.toString();
            ShopItem shop = ShopItem.builder()
                .shopId(longString)
                .name(longString)
                .phone(longString)
                .province(longString)
                .city(longString)
                .address(longString)
                .build();
            
            assertEquals(longString, shop.getShopId());
            assertEquals(longString, shop.getName());
            assertEquals(longString, shop.getPhone());
            assertTrue(shop.isValid());
        }

        @Test
        @DisplayName("特殊字符测试")
        void testSpecialCharacters() {
            String specialChars = "测试@#$%^&*()_+-={}[]|\\:;\"'<>?,./'";
            ShopItem shop = ShopItem.builder()
                .shopId(specialChars)
                .name(specialChars)
                .phone(specialChars)
                .build();
            
            assertEquals(specialChars, shop.getShopId());
            assertEquals(specialChars, shop.getName());
            assertTrue(shop.isValid());
        }

        @Test
        @DisplayName("坐标格式测试")
        void testCoordinateFormats() {
            String[] validCoordinates = {
                "0", "0.0", "123.456789", "-123.456789",
                "180.0", "-180.0", "90.0", "-90.0"
            };
            
            for (String coordinate : validCoordinates) {
                ShopItem shop = new ShopItem();
                shop.setLongitude(coordinate);
                shop.setLatitude(coordinate);
                
                assertEquals(coordinate, shop.getLongitude());
                assertEquals(coordinate, shop.getLatitude());
                assertTrue(shop.hasCoordinates());
            }
        }

        @Test
        @DisplayName("地址信息组合测试")
        void testAddressCombinations() {
            // 只有省份
            ShopItem shop1 = new ShopItem();
            shop1.setProvince("北京市");
            assertEquals("北京市", shop1.getFullAddress());
            
            // 只有城市
            ShopItem shop2 = new ShopItem();
            shop2.setCity("朝阳区");
            assertEquals("朝阳区", shop2.getFullAddress());
            
            // 只有详细地址
            ShopItem shop3 = new ShopItem();
            shop3.setAddress("三里屯街道1号");
            assertEquals("三里屯街道1号", shop3.getFullAddress());
            
            // 省份 + 详细地址（跳过城市）
            ShopItem shop4 = new ShopItem();
            shop4.setProvince("北京市");
            shop4.setAddress("三里屯街道1号");
            assertEquals("北京市 三里屯街道1号", shop4.getFullAddress());
        }
    }

    // 辅助方法
    private ShopItem createValidShop() {
        return new ShopItem(
            "SHOP_123", "测试门店", "13800138000",
            "北京市", "朝阳区", "三里屯街道1号",
            "39.916527", "116.397128"
        );
    }
}