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
 * PrinterItem 实体类的 JUnit 5 单元测试
 */
@DisplayName("PrinterItem Tests")
class PrinterItemTest {

    private PrinterItem printerItem;
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        printerItem = new PrinterItem();
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
            PrinterItem item = new PrinterItem();
            
            assertNull(item.getShopId());
            assertNull(item.getBrand());
            assertNull(item.getName());
            assertNull(item.getMachineCode());
            assertNull(item.getMachineSign());
            assertNull(item.getPrintNumber());
            assertNull(item.getWidth());
        }

        @Test
        @DisplayName("基本信息构造函数应正确设置参数")
        void testBasicConstructor() {
            String shopId = "shop123";
            String machineCode = "MC001";
            
            PrinterItem item = new PrinterItem(shopId, machineCode);
            
            assertEquals(shopId, item.getShopId());
            assertEquals(machineCode, item.getMachineCode());
            assertNull(item.getBrand());
            assertNull(item.getName());
            assertNull(item.getMachineSign());
            assertNull(item.getPrintNumber());
            assertNull(item.getWidth());
        }

        @Test
        @DisplayName("包含基本配置的构造函数应正确设置参数")
        void testBasicConfigConstructor() {
            String shopId = "shop123";
            String brand = "Canon";
            String name = "Printer A1";
            String machineCode = "MC001";
            String machineSign = "sign123";
            
            PrinterItem item = new PrinterItem(shopId, brand, name, machineCode, machineSign);
            
            assertEquals(shopId, item.getShopId());
            assertEquals(brand, item.getBrand());
            assertEquals(name, item.getName());
            assertEquals(machineCode, item.getMachineCode());
            assertEquals(machineSign, item.getMachineSign());
            assertNull(item.getPrintNumber());
            assertNull(item.getWidth());
        }

        @Test
        @DisplayName("全参构造函数应正确设置所有参数")
        void testFullConstructor() {
            String shopId = "shop123";
            String brand = "Canon";
            String name = "Printer A1";
            String machineCode = "MC001";
            String machineSign = "sign123";
            Integer printNumber = 2;
            String width = "80mm";
            
            PrinterItem item = new PrinterItem(shopId, brand, name, machineCode, machineSign, printNumber, width);
            
            assertEquals(shopId, item.getShopId());
            assertEquals(brand, item.getBrand());
            assertEquals(name, item.getName());
            assertEquals(machineCode, item.getMachineCode());
            assertEquals(machineSign, item.getMachineSign());
            assertEquals(printNumber, item.getPrintNumber());
            assertEquals(width, item.getWidth());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("shopId 的 getter 和 setter 应正常工作")
        void testShopIdGetterSetter() {
            String shopId = "shop123";
            printerItem.setShopId(shopId);
            assertEquals(shopId, printerItem.getShopId());
        }

        @Test
        @DisplayName("brand 的 getter 和 setter 应正常工作")
        void testBrandGetterSetter() {
            String brand = "Canon";
            printerItem.setBrand(brand);
            assertEquals(brand, printerItem.getBrand());
        }

        @Test
        @DisplayName("name 的 getter 和 setter 应正常工作")
        void testNameGetterSetter() {
            String name = "Printer A1";
            printerItem.setName(name);
            assertEquals(name, printerItem.getName());
        }

        @Test
        @DisplayName("machineCode 的 getter 和 setter 应正常工作")
        void testMachineCodeGetterSetter() {
            String machineCode = "MC001";
            printerItem.setMachineCode(machineCode);
            assertEquals(machineCode, printerItem.getMachineCode());
        }

        @Test
        @DisplayName("machineSign 的 getter 和 setter 应正常工作")
        void testMachineSignGetterSetter() {
            String machineSign = "sign123";
            printerItem.setMachineSign(machineSign);
            assertEquals(machineSign, printerItem.getMachineSign());
        }

        @Test
        @DisplayName("printNumber 的 getter 和 setter 应正常工作")
        void testPrintNumberGetterSetter() {
            Integer printNumber = 3;
            printerItem.setPrintNumber(printNumber);
            assertEquals(printNumber, printerItem.getPrintNumber());
        }

        @Test
        @DisplayName("width 的 getter 和 setter 应正常工作")
        void testWidthGetterSetter() {
            String width = "58mm";
            printerItem.setWidth(width);
            assertEquals(width, printerItem.getWidth());
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同内容的对象应相等")
        void testEqualsWithSameContent() {
            PrinterItem item1 = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            PrinterItem item2 = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            assertEquals(item1, item2);
            assertEquals(item1.hashCode(), item2.hashCode());
        }

        @Test
        @DisplayName("不同内容的对象应不相等")
        void testEqualsWithDifferentContent() {
            PrinterItem item1 = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            PrinterItem item2 = new PrinterItem("shop2", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            assertNotEquals(item1, item2);
        }

        @Test
        @DisplayName("对象与自身应相等")
        void testEqualsWithSelf() {
            PrinterItem item = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            assertEquals(item, item);
        }

        @Test
        @DisplayName("对象与null应不相等")
        void testEqualsWithNull() {
            PrinterItem item = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            assertNotEquals(item, null);
        }

        @Test
        @DisplayName("对象与不同类型应不相等")
        void testEqualsWithDifferentType() {
            PrinterItem item = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            String other = "different type";
            
            assertNotEquals(item, other);
        }

        @Test
        @DisplayName("部分字段为null的对象equals测试")
        void testEqualsWithNullFields() {
            PrinterItem item1 = new PrinterItem();
            PrinterItem item2 = new PrinterItem();
            
            assertEquals(item1, item2);
            assertEquals(item1.hashCode(), item2.hashCode());
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString应包含所有字段信息")
        void testToString() {
            PrinterItem item = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            String result = item.toString();
            
            assertTrue(result.contains("PrinterItem"));
            assertTrue(result.contains("shopId='shop1'"));
            assertTrue(result.contains("brand='Canon'"));
            assertTrue(result.contains("name='Printer A'"));
            assertTrue(result.contains("machineCode='MC001'"));
            assertTrue(result.contains("machineSign='sign123'"));
            assertTrue(result.contains("printNumber=2"));
            assertTrue(result.contains("width='80mm'"));
        }

        @Test
        @DisplayName("空对象的toString应正确处理null值")
        void testToStringWithNullValues() {
            PrinterItem item = new PrinterItem();
            
            String result = item.toString();
            
            assertTrue(result.contains("PrinterItem"));
            assertTrue(result.contains("shopId=null"));
            assertTrue(result.contains("brand=null"));
            assertTrue(result.contains("name=null"));
            assertTrue(result.contains("machineCode=null"));
        }
    }

    @Nested
    @DisplayName("JSON Serialization Tests")
    class JsonSerializationTests {

        @Test
        @DisplayName("对象应能正确序列化为JSON")
        void testSerialization() throws IOException {
            PrinterItem item = new PrinterItem("shop1", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            String json = objectMapper.writeValueAsString(item);
            
            assertTrue(json.contains("\"shop_id\":\"shop1\""));
            assertTrue(json.contains("\"brand\":\"Canon\""));
            assertTrue(json.contains("\"name\":\"Printer A\""));
            assertTrue(json.contains("\"machine_code\":\"MC001\""));
            assertTrue(json.contains("\"machine_sign\":\"sign123\""));
            assertTrue(json.contains("\"print_number\":2"));
            assertTrue(json.contains("\"width\":\"80mm\""));
        }

        @Test
        @DisplayName("JSON应能正确反序列化为对象")
        void testDeserialization() throws IOException {
            String json = "{\"shop_id\":\"shop1\",\"brand\":\"Canon\",\"name\":\"Printer A\",\"machine_code\":\"MC001\",\"machine_sign\":\"sign123\",\"print_number\":2,\"width\":\"80mm\"}";
            
            PrinterItem item = objectMapper.readValue(json, PrinterItem.class);
            
            assertEquals("shop1", item.getShopId());
            assertEquals("Canon", item.getBrand());
            assertEquals("Printer A", item.getName());
            assertEquals("MC001", item.getMachineCode());
            assertEquals("sign123", item.getMachineSign());
            assertEquals(Integer.valueOf(2), item.getPrintNumber());
            assertEquals("80mm", item.getWidth());
        }

        @Test
        @DisplayName("部分字段缺失的JSON应能正确反序列化")
        void testDeserializationWithMissingFields() throws IOException {
            String json = "{\"shop_id\":\"shop1\",\"machine_code\":\"MC001\"}";
            
            PrinterItem item = objectMapper.readValue(json, PrinterItem.class);
            
            assertEquals("shop1", item.getShopId());
            assertEquals("MC001", item.getMachineCode());
            assertNull(item.getBrand());
            assertNull(item.getName());
            assertNull(item.getMachineSign());
            assertNull(item.getPrintNumber());
            assertNull(item.getWidth());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("有效的打印机信息应通过验证")
        void testValidPrinterItem() {
            PrinterItem item = new PrinterItem("shop123", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            Set<ConstraintViolation<PrinterItem>> violations = validator.validate(item);
            
            assertTrue(violations.isEmpty());
        }

        @Test
        @DisplayName("shopId为空应验证失败")
        void testInvalidShopIdBlank() {
            PrinterItem item = new PrinterItem("", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            Set<ConstraintViolation<PrinterItem>> violations = validator.validate(item);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("门店ID不能为空")));
        }

        @Test
        @DisplayName("shopId为null应验证失败")
        void testInvalidShopIdNull() {
            PrinterItem item = new PrinterItem(null, "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            Set<ConstraintViolation<PrinterItem>> violations = validator.validate(item);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("门店ID不能为空")));
        }

        @Test
        @DisplayName("printNumber为负数应验证失败")
        void testInvalidPrintNumberNegative() {
            PrinterItem item = new PrinterItem("shop123", "Canon", "Printer A", "MC001", "sign123", -1, "80mm");
            
            Set<ConstraintViolation<PrinterItem>> violations = validator.validate(item);
            
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("打印份数不能为负数")));
        }

        @Test
        @DisplayName("printNumber为0应通过验证")
        void testValidPrintNumberZero() {
            PrinterItem item = new PrinterItem("shop123", "Canon", "Printer A", "MC001", "sign123", 0, "80mm");
            
            Set<ConstraintViolation<PrinterItem>> violations = validator.validate(item);
            
            assertTrue(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("isValid应在shopId和machineCode都不为空时返回true")
        void testIsValidWithValidData() {
            PrinterItem item = new PrinterItem("shop123", "MC001");
            
            assertTrue(item.isValid());
        }

        @Test
        @DisplayName("isValid应在shopId为空时返回false")
        void testIsValidWithEmptyShopId() {
            PrinterItem item = new PrinterItem("", "MC001");
            
            assertFalse(item.isValid());
        }

        @Test
        @DisplayName("isValid应在machineCode为空时返回false")
        void testIsValidWithEmptyMachineCode() {
            PrinterItem item = new PrinterItem("shop123", "");
            
            assertFalse(item.isValid());
        }

        @Test
        @DisplayName("isValid应在参数为null时返回false")
        void testIsValidWithNullValues() {
            PrinterItem item = new PrinterItem(null, null);
            
            assertFalse(item.isValid());
        }

        @Test
        @DisplayName("hasBrand应在品牌信息不为空时返回true")
        void testHasBrandTrue() {
            PrinterItem item = new PrinterItem();
            item.setBrand("Canon");
            
            assertTrue(item.hasBrand());
        }

        @Test
        @DisplayName("hasBrand应在品牌信息为空或null时返回false")
        void testHasBrandFalse() {
            PrinterItem item1 = new PrinterItem();
            item1.setBrand("");
            
            PrinterItem item2 = new PrinterItem();
            item2.setBrand(null);
            
            assertFalse(item1.hasBrand());
            assertFalse(item2.hasBrand());
        }

        @Test
        @DisplayName("hasName应在名称不为空时返回true")
        void testHasNameTrue() {
            PrinterItem item = new PrinterItem();
            item.setName("Printer A");
            
            assertTrue(item.hasName());
        }

        @Test
        @DisplayName("hasSign应在设备密钥不为空时返回true")
        void testHasSignTrue() {
            PrinterItem item = new PrinterItem();
            item.setMachineSign("sign123");
            
            assertTrue(item.hasSign());
        }

        @Test
        @DisplayName("hasPrintNumber应在打印份数不为null且非负数时返回true")
        void testHasPrintNumberTrue() {
            PrinterItem item = new PrinterItem();
            item.setPrintNumber(2);
            
            assertTrue(item.hasPrintNumber());
        }

        @Test
        @DisplayName("hasPrintNumber应在打印份数为0时返回true")
        void testHasPrintNumberZero() {
            PrinterItem item = new PrinterItem();
            item.setPrintNumber(0);
            
            assertTrue(item.hasPrintNumber());
        }

        @Test
        @DisplayName("hasWidth应在纸张宽度不为空时返回true")
        void testHasWidthTrue() {
            PrinterItem item = new PrinterItem();
            item.setWidth("80mm");
            
            assertTrue(item.hasWidth());
        }
    }

    @Nested
    @DisplayName("Display Methods Tests")
    class DisplayMethodsTests {

        @Test
        @DisplayName("getDisplayName应在有品牌和名称时返回格式化名称")
        void testGetDisplayNameWithBrandAndName() {
            PrinterItem item = new PrinterItem();
            item.setBrand("Canon");
            item.setName("Printer A");
            item.setMachineCode("MC001");
            
            String displayName = item.getDisplayName();
            
            assertEquals("[Canon] Printer A", displayName);
        }

        @Test
        @DisplayName("getDisplayName应在只有名称时返回名称")
        void testGetDisplayNameWithNameOnly() {
            PrinterItem item = new PrinterItem();
            item.setName("Printer A");
            item.setMachineCode("MC001");
            
            String displayName = item.getDisplayName();
            
            assertEquals("Printer A", displayName);
        }

        @Test
        @DisplayName("getDisplayName应在只有品牌时返回品牌")
        void testGetDisplayNameWithBrandOnly() {
            PrinterItem item = new PrinterItem();
            item.setBrand("Canon");
            item.setMachineCode("MC001");
            
            String displayName = item.getDisplayName();
            
            assertEquals("Canon", displayName);
        }

        @Test
        @DisplayName("getDisplayName应在没有品牌和名称时返回设备编号")
        void testGetDisplayNameWithMachineCodeOnly() {
            PrinterItem item = new PrinterItem();
            item.setMachineCode("MC001");
            
            String displayName = item.getDisplayName();
            
            assertEquals("MC001", displayName);
        }

        @Test
        @DisplayName("getDisplayName应在所有信息都为空时返回默认文本")
        void testGetDisplayNameWithNoInfo() {
            PrinterItem item = new PrinterItem();
            
            String displayName = item.getDisplayName();
            
            assertEquals("未知打印机", displayName);
        }

        @Test
        @DisplayName("getSpecification应在有宽度和打印份数时返回完整规格")
        void testGetSpecificationWithComplete() {
            PrinterItem item = new PrinterItem();
            item.setWidth("80mm");
            item.setPrintNumber(2);
            
            String spec = item.getSpecification();
            
            assertEquals("纸张宽度: 80mm, 打印份数: 2", spec);
        }

        @Test
        @DisplayName("getSpecification应在只有宽度时返回宽度信息")
        void testGetSpecificationWithWidthOnly() {
            PrinterItem item = new PrinterItem();
            item.setWidth("58mm");
            
            String spec = item.getSpecification();
            
            assertEquals("纸张宽度: 58mm", spec);
        }

        @Test
        @DisplayName("getSpecification应在只有打印份数时返回份数信息")
        void testGetSpecificationWithPrintNumberOnly() {
            PrinterItem item = new PrinterItem();
            item.setPrintNumber(3);
            
            String spec = item.getSpecification();
            
            assertEquals("打印份数: 3", spec);
        }

        @Test
        @DisplayName("getSpecification应在没有规格信息时返回默认文本")
        void testGetSpecificationWithNoInfo() {
            PrinterItem item = new PrinterItem();
            
            String spec = item.getSpecification();
            
            assertEquals("规格信息不可用", spec);
        }

        @Test
        @DisplayName("getSecurityStatus应在有设备密钥时返回已配置状态")
        void testGetSecurityStatusWithSign() {
            PrinterItem item = new PrinterItem();
            item.setMachineSign("sign123");
            
            String status = item.getSecurityStatus();
            
            assertEquals("已配置设备密钥", status);
        }

        @Test
        @DisplayName("getSecurityStatus应在无设备密钥时返回未配置状态")
        void testGetSecurityStatusWithoutSign() {
            PrinterItem item = new PrinterItem();
            
            String status = item.getSecurityStatus();
            
            assertEquals("未配置设备密钥", status);
        }
    }

    @Nested
    @DisplayName("Comparison Methods Tests")
    class ComparisonMethodsTests {

        @Test
        @DisplayName("isSameDevice应在相同设备编号和门店ID时返回true")
        void testIsSameDeviceTrue() {
            PrinterItem item1 = new PrinterItem("shop1", "MC001");
            PrinterItem item2 = new PrinterItem("shop1", "MC001");
            
            assertTrue(item1.isSameDevice(item2));
        }

        @Test
        @DisplayName("isSameDevice应在不同设备编号时返回false")
        void testIsSameDeviceDifferentMachineCode() {
            PrinterItem item1 = new PrinterItem("shop1", "MC001");
            PrinterItem item2 = new PrinterItem("shop1", "MC002");
            
            assertFalse(item1.isSameDevice(item2));
        }

        @Test
        @DisplayName("isSameDevice应在不同门店ID时返回false")
        void testIsSameDeviceDifferentShopId() {
            PrinterItem item1 = new PrinterItem("shop1", "MC001");
            PrinterItem item2 = new PrinterItem("shop2", "MC001");
            
            assertFalse(item1.isSameDevice(item2));
        }

        @Test
        @DisplayName("isSameDevice应在比较对象为null时返回false")
        void testIsSameDeviceWithNull() {
            PrinterItem item = new PrinterItem("shop1", "MC001");
            
            assertFalse(item.isSameDevice(null));
        }

        @Test
        @DisplayName("belongsToShop应在门店ID匹配时返回true")
        void testBelongsToShopTrue() {
            PrinterItem item = new PrinterItem("shop123", "MC001");
            
            assertTrue(item.belongsToShop("shop123"));
        }

        @Test
        @DisplayName("belongsToShop应在门店ID不匹配时返回false")
        void testBelongsToShopFalse() {
            PrinterItem item = new PrinterItem("shop123", "MC001");
            
            assertFalse(item.belongsToShop("shop456"));
        }

        @Test
        @DisplayName("belongsToShop应在目标门店ID为null时返回false")
        void testBelongsToShopWithNull() {
            PrinterItem item = new PrinterItem("shop123", "MC001");
            
            assertFalse(item.belongsToShop(null));
        }

        @Test
        @DisplayName("isBrand应在品牌匹配时返回true")
        void testIsBrandTrue() {
            PrinterItem item = new PrinterItem();
            item.setBrand("Canon");
            
            assertTrue(item.isBrand("Canon"));
        }

        @Test
        @DisplayName("isBrand应在品牌不匹配时返回false")
        void testIsBrandFalse() {
            PrinterItem item = new PrinterItem();
            item.setBrand("Canon");
            
            assertFalse(item.isBrand("HP"));
        }
    }

    @Nested
    @DisplayName("Static Factory Methods Tests")
    class StaticFactoryMethodsTests {

        @Test
        @DisplayName("of方法应创建基本打印机信息")
        void testOfMethod() {
            PrinterItem item = PrinterItem.of("shop123", "MC001");
            
            assertEquals("shop123", item.getShopId());
            assertEquals("MC001", item.getMachineCode());
            assertNull(item.getBrand());
        }

        @Test
        @DisplayName("basic方法应创建包含基本配置的打印机信息")
        void testBasicMethod() {
            PrinterItem item = PrinterItem.basic("shop123", "Canon", "Printer A", "MC001", "sign123");
            
            assertEquals("shop123", item.getShopId());
            assertEquals("Canon", item.getBrand());
            assertEquals("Printer A", item.getName());
            assertEquals("MC001", item.getMachineCode());
            assertEquals("sign123", item.getMachineSign());
            assertNull(item.getPrintNumber());
            assertNull(item.getWidth());
        }

        @Test
        @DisplayName("complete方法应创建完整的打印机信息")
        void testCompleteMethod() {
            PrinterItem item = PrinterItem.complete("shop123", "Canon", "Printer A", "MC001", "sign123", 2, "80mm");
            
            assertEquals("shop123", item.getShopId());
            assertEquals("Canon", item.getBrand());
            assertEquals("Printer A", item.getName());
            assertEquals("MC001", item.getMachineCode());
            assertEquals("sign123", item.getMachineSign());
            assertEquals(Integer.valueOf(2), item.getPrintNumber());
            assertEquals("80mm", item.getWidth());
        }
    }

    @Nested
    @DisplayName("Builder Tests")
    class BuilderTests {

        @Test
        @DisplayName("Builder应能构建完整的打印机信息")
        void testBuilderComplete() {
            PrinterItem item = PrinterItem.builder()
                .shopId("shop123")
                .brand("Canon")
                .name("Printer A")
                .machineCode("MC001")
                .machineSign("sign123")
                .printNumber(2)
                .width("80mm")
                .build();
            
            assertEquals("shop123", item.getShopId());
            assertEquals("Canon", item.getBrand());
            assertEquals("Printer A", item.getName());
            assertEquals("MC001", item.getMachineCode());
            assertEquals("sign123", item.getMachineSign());
            assertEquals(Integer.valueOf(2), item.getPrintNumber());
            assertEquals("80mm", item.getWidth());
        }

        @Test
        @DisplayName("Builder应能构建部分信息的打印机")
        void testBuilderPartial() {
            PrinterItem item = PrinterItem.builder()
                .shopId("shop123")
                .machineCode("MC001")
                .build();
            
            assertEquals("shop123", item.getShopId());
            assertEquals("MC001", item.getMachineCode());
            assertNull(item.getBrand());
            assertNull(item.getName());
            assertNull(item.getMachineSign());
            assertNull(item.getPrintNumber());
            assertNull(item.getWidth());
        }

        @Test
        @DisplayName("Builder方法应支持链式调用")
        void testBuilderChaining() {
            PrinterItem.Builder builder = PrinterItem.builder();
            
            assertSame(builder, builder.shopId("shop123"));
            assertSame(builder, builder.brand("Canon"));
            assertSame(builder, builder.name("Printer A"));
            assertSame(builder, builder.machineCode("MC001"));
            assertSame(builder, builder.machineSign("sign123"));
            assertSame(builder, builder.printNumber(2));
            assertSame(builder, builder.width("80mm"));
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("所有字符串字段为空字符串的处理")
        void testEmptyStringFields() {
            PrinterItem item = new PrinterItem("", "", "", "", "", 0, "");
            
            assertFalse(item.isValid());
            assertFalse(item.hasBrand());
            assertFalse(item.hasName());
            assertFalse(item.hasSign());
            assertTrue(item.hasPrintNumber()); // 0是有效的打印份数
            assertFalse(item.hasWidth());
        }

        @Test
        @DisplayName("字符串字段包含空格的处理")
        void testWhitespaceStringFields() {
            PrinterItem item = new PrinterItem("  ", "  ", "  ", "  ", "  ", 1, "  ");
            
            assertFalse(item.isValid());
            assertFalse(item.hasBrand());
            assertFalse(item.hasName());
            assertFalse(item.hasSign());
            assertTrue(item.hasPrintNumber());
            assertFalse(item.hasWidth());
        }

        @Test
        @DisplayName("getDisplayName处理包含空格的字段")
        void testGetDisplayNameWithWhitespace() {
            PrinterItem item = new PrinterItem();
            item.setBrand("  Canon  ");
            item.setName("  Printer A  ");
            
            String displayName = item.getDisplayName();
            
            assertEquals("[Canon] Printer A", displayName);
        }

        @Test
        @DisplayName("hasPrintNumber对负数的处理")
        void testHasPrintNumberWithNegative() {
            PrinterItem item = new PrinterItem();
            item.setPrintNumber(-1);
            
            assertFalse(item.hasPrintNumber());
        }
    }
}