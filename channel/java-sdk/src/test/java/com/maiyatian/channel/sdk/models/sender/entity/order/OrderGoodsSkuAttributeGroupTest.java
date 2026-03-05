/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderGoodsSkuAttributeGroup 单元测试
 * 
 * @author Test Generator
 * @version 1.0.0
 */
@DisplayName("OrderGoodsSkuAttributeGroup 测试")
public class OrderGoodsSkuAttributeGroupTest {

    private ObjectMapper objectMapper;
    private OrderGoodsSkuAttributeGroup attributeGroup;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        attributeGroup = new OrderGoodsSkuAttributeGroup();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数")
        void testDefaultConstructor() {
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup();
            
            // Then
            assertNotNull(group);
            assertNull(group.getGroupName());
            assertNull(group.getItems());
        }

        @Test
        @DisplayName("全参构造函数")
        void testFullConstructor() {
            // Given
            String groupName = "主食";
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个"),
                new OrderGoodsSkuAttribute("鸡腿", 2, "个")
            );
            
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(groupName, items);
            
            // Then
            assertNotNull(group);
            assertEquals(groupName, group.getGroupName());
            assertEquals(items, group.getItems());
            assertEquals(2, group.getItems().size());
        }

        @Test
        @DisplayName("全参构造函数 - null值测试")
        void testFullConstructorWithNullValues() {
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(null, null);
            
            // Then
            assertNotNull(group);
            assertNull(group.getGroupName());
            assertNull(group.getItems());
        }

        @Test
        @DisplayName("全参构造函数 - 空列表测试")
        void testFullConstructorWithEmptyList() {
            // Given
            String groupName = "饮料";
            List<OrderGoodsSkuAttribute> items = Collections.emptyList();
            
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(groupName, items);
            
            // Then
            assertNotNull(group);
            assertEquals(groupName, group.getGroupName());
            assertEquals(items, group.getItems());
            assertTrue(group.getItems().isEmpty());
        }

        @Test
        @DisplayName("全参构造函数 - 部分null值测试")
        void testFullConstructorWithPartialNullValues() {
            // Given
            String groupName = "小料";
            
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(groupName, null);
            
            // Then
            assertNotNull(group);
            assertEquals(groupName, group.getGroupName());
            assertNull(group.getItems());
        }
    }

    @Nested
    @DisplayName("Getter 和 Setter 测试")
    class GetterSetterTests {

        @Test
        @DisplayName("分组名称测试")
        void testGroupName() {
            // Given
            String groupName = "主食";
            
            // When
            attributeGroup.setGroupName(groupName);
            
            // Then
            assertEquals(groupName, attributeGroup.getGroupName());
        }

        @Test
        @DisplayName("分组名称 - null值测试")
        void testGroupNameWithNull() {
            // When
            attributeGroup.setGroupName(null);
            
            // Then
            assertNull(attributeGroup.getGroupName());
        }

        @Test
        @DisplayName("分组名称 - 空字符串测试")
        void testGroupNameWithEmptyString() {
            // When
            attributeGroup.setGroupName("");
            
            // Then
            assertEquals("", attributeGroup.getGroupName());
        }

        @Test
        @DisplayName("商品列表测试")
        void testItems() {
            // Given
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个"),
                new OrderGoodsSkuAttribute("薯条", 1, "份")
            );
            
            // When
            attributeGroup.setItems(items);
            
            // Then
            assertEquals(items, attributeGroup.getItems());
            assertEquals(2, attributeGroup.getItems().size());
        }

        @Test
        @DisplayName("商品列表 - null值测试")
        void testItemsWithNull() {
            // When
            attributeGroup.setItems(null);
            
            // Then
            assertNull(attributeGroup.getItems());
        }

        @Test
        @DisplayName("商品列表 - 空列表测试")
        void testItemsWithEmptyList() {
            // Given
            List<OrderGoodsSkuAttribute> items = Collections.emptyList();
            
            // When
            attributeGroup.setItems(items);
            
            // Then
            assertEquals(items, attributeGroup.getItems());
            assertTrue(attributeGroup.getItems().isEmpty());
        }

        @Test
        @DisplayName("商品列表 - 单个商品测试")
        void testItemsWithSingleItem() {
            // Given
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("可乐", 1, "杯")
            );
            
            // When
            attributeGroup.setItems(items);
            
            // Then
            assertEquals(items, attributeGroup.getItems());
            assertEquals(1, attributeGroup.getItems().size());
            assertEquals("可乐", attributeGroup.getItems().get(0).getName());
        }

        @Test
        @DisplayName("商品列表 - 多个商品测试")
        void testItemsWithMultipleItems() {
            // Given
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个"),
                new OrderGoodsSkuAttribute("鸡翅", 2, "个"),
                new OrderGoodsSkuAttribute("薯条", 1, "份"),
                new OrderGoodsSkuAttribute("可乐", 1, "杯")
            );
            
            // When
            attributeGroup.setItems(items);
            
            // Then
            assertEquals(items, attributeGroup.getItems());
            assertEquals(4, attributeGroup.getItems().size());
        }
    }

    @Nested
    @DisplayName("equals 和 hashCode 测试")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("相同对象 equals 测试")
        void testEqualsWithSameInstance() {
            // Then
            assertEquals(attributeGroup, attributeGroup);
            assertEquals(attributeGroup.hashCode(), attributeGroup.hashCode());
        }

        @Test
        @DisplayName("null 对象 equals 测试")
        void testEqualsWithNull() {
            // Then
            assertNotEquals(null, attributeGroup);
        }

        @Test
        @DisplayName("不同类型对象 equals 测试")
        void testEqualsWithDifferentType() {
            // Given
            String differentType = "different";
            
            // Then
            assertNotEquals(attributeGroup, differentType);
        }

        @Test
        @DisplayName("相同属性对象 equals 测试")
        void testEqualsWithSameProperties() {
            // Given
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个")
            );
            OrderGoodsSkuAttributeGroup group1 = new OrderGoodsSkuAttributeGroup("主食", items);
            OrderGoodsSkuAttributeGroup group2 = new OrderGoodsSkuAttributeGroup("主食", items);
            
            // Then
            assertEquals(group1, group2);
            assertEquals(group1.hashCode(), group2.hashCode());
        }

        @Test
        @DisplayName("不同分组名称 equals 测试")
        void testEqualsWithDifferentGroupName() {
            // Given
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个")
            );
            OrderGoodsSkuAttributeGroup group1 = new OrderGoodsSkuAttributeGroup("主食", items);
            OrderGoodsSkuAttributeGroup group2 = new OrderGoodsSkuAttributeGroup("饮料", items);
            
            // Then
            assertNotEquals(group1, group2);
        }

        @Test
        @DisplayName("不同商品列表 equals 测试")
        void testEqualsWithDifferentItems() {
            // Given
            OrderGoodsSkuAttributeGroup group1 = new OrderGoodsSkuAttributeGroup("主食", 
                Arrays.asList(new OrderGoodsSkuAttribute("汉堡", 1, "个")));
            OrderGoodsSkuAttributeGroup group2 = new OrderGoodsSkuAttributeGroup("主食", 
                Arrays.asList(new OrderGoodsSkuAttribute("鸡腿", 1, "个")));
            
            // Then
            assertNotEquals(group1, group2);
        }

        @Test
        @DisplayName("null值属性 equals 测试")
        void testEqualsWithNullProperties() {
            // Given
            OrderGoodsSkuAttributeGroup group1 = new OrderGoodsSkuAttributeGroup(null, null);
            OrderGoodsSkuAttributeGroup group2 = new OrderGoodsSkuAttributeGroup(null, null);
            
            // Then
            assertEquals(group1, group2);
            assertEquals(group1.hashCode(), group2.hashCode());
        }

        @Test
        @DisplayName("部分null值属性 equals 测试")
        void testEqualsWithPartialNullProperties() {
            // Given
            OrderGoodsSkuAttributeGroup group1 = new OrderGoodsSkuAttributeGroup("主食", null);
            OrderGoodsSkuAttributeGroup group2 = new OrderGoodsSkuAttributeGroup("主食", Collections.emptyList());
            
            // Then
            assertNotEquals(group1, group2);
        }

        @Test
        @DisplayName("空列表与非空列表 equals 测试")
        void testEqualsWithEmptyAndNonEmptyList() {
            // Given
            OrderGoodsSkuAttributeGroup group1 = new OrderGoodsSkuAttributeGroup("主食", Collections.emptyList());
            OrderGoodsSkuAttributeGroup group2 = new OrderGoodsSkuAttributeGroup("主食", 
                Arrays.asList(new OrderGoodsSkuAttribute("汉堡", 1, "个")));
            
            // Then
            assertNotEquals(group1, group2);
        }
    }

    @Nested
    @DisplayName("toString 测试")
    class ToStringTests {

        @Test
        @DisplayName("空对象 toString 测试")
        void testToStringWithEmptyObject() {
            // When
            String result = attributeGroup.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderGoodsSkuAttributeGroup"));
            assertTrue(result.contains("groupName='null'"));
            assertTrue(result.contains("items=null"));
        }

        @Test
        @DisplayName("完整对象 toString 测试")
        void testToStringWithFullObject() {
            // Given
            attributeGroup.setGroupName("主食");
            attributeGroup.setItems(Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个")
            ));
            
            // When
            String result = attributeGroup.toString();
            
            // Then
            assertNotNull(result);
            assertTrue(result.contains("OrderGoodsSkuAttributeGroup"));
            assertTrue(result.contains("groupName='主食'"));
            assertTrue(result.contains("items="));
        }

        @Test
        @DisplayName("空列表 toString 测试")
        void testToStringWithEmptyList() {
            // Given
            attributeGroup.setGroupName("饮料");
            attributeGroup.setItems(Collections.emptyList());
            
            // When
            String result = attributeGroup.toString();
            
            // Then
            assertTrue(result.contains("groupName='饮料'"));
            assertTrue(result.contains("items=[]"));
        }

        @Test
        @DisplayName("多个商品 toString 测试")
        void testToStringWithMultipleItems() {
            // Given
            attributeGroup.setGroupName("套餐");
            attributeGroup.setItems(Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个"),
                new OrderGoodsSkuAttribute("薯条", 1, "份"),
                new OrderGoodsSkuAttribute("可乐", 1, "杯")
            ));
            
            // When
            String result = attributeGroup.toString();
            
            // Then
            assertTrue(result.contains("groupName='套餐'"));
            assertTrue(result.contains("items=["));
            assertTrue(result.contains("OrderGoodsSkuAttribute"));
        }

        @Test
        @DisplayName("特殊字符 toString 测试")
        void testToStringWithSpecialCharacters() {
            // Given
            attributeGroup.setGroupName("主食@#$%");
            attributeGroup.setItems(Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡@特制", 1, "个")
            ));
            
            // When
            String result = attributeGroup.toString();
            
            // Then
            assertTrue(result.contains("groupName='主食@#$%'"));
        }
    }

    @Nested
    @DisplayName("JSON 序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("空对象序列化测试")
        void testSerializeEmptyObject() throws JsonProcessingException {
            // When
            String json = objectMapper.writeValueAsString(attributeGroup);
            
            // Then
            assertNotNull(json);
            assertEquals("{}", json);
        }

        @Test
        @DisplayName("完整对象序列化测试")
        void testSerializeFullObject() throws JsonProcessingException {
            // Given
            attributeGroup.setGroupName("主食");
            attributeGroup.setItems(Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个")
            ));
            
            // When
            String json = objectMapper.writeValueAsString(attributeGroup);
            
            // Then
            assertNotNull(json);
            assertTrue(json.contains("\"group_name\":\"主食\""));
            assertTrue(json.contains("\"items\":["));
            assertTrue(json.contains("\"name\":\"汉堡\""));
        }

        @Test
        @DisplayName("只有分组名称序列化测试")
        void testSerializeOnlyGroupName() throws JsonProcessingException {
            // Given
            attributeGroup.setGroupName("饮料");
            
            // When
            String json = objectMapper.writeValueAsString(attributeGroup);
            
            // Then
            assertTrue(json.contains("\"group_name\":\"饮料\""));
            assertFalse(json.contains("\"items\":"));
        }

        @Test
        @DisplayName("空列表序列化测试")
        void testSerializeWithEmptyList() throws JsonProcessingException {
            // Given
            attributeGroup.setGroupName("小料");
            attributeGroup.setItems(Collections.emptyList());
            
            // When
            String json = objectMapper.writeValueAsString(attributeGroup);
            
            // Then
            assertTrue(json.contains("\"group_name\":\"小料\""));
            assertTrue(json.contains("\"items\":[]"));
        }

        @Test
        @DisplayName("反序列化测试")
        void testDeserialize() throws JsonProcessingException {
            // Given
            String json = "{\"group_name\":\"主食\",\"items\":[{\"name\":\"汉堡\",\"number\":1,\"unit\":\"个\"}]}";
            
            // When
            OrderGoodsSkuAttributeGroup group = objectMapper.readValue(json, OrderGoodsSkuAttributeGroup.class);
            
            // Then
            assertNotNull(group);
            assertEquals("主食", group.getGroupName());
            assertNotNull(group.getItems());
            assertEquals(1, group.getItems().size());
            assertEquals("汉堡", group.getItems().get(0).getName());
            assertEquals(Integer.valueOf(1), group.getItems().get(0).getNumber());
            assertEquals("个", group.getItems().get(0).getUnit());
        }

        @Test
        @DisplayName("序列化后反序列化一致性测试")
        void testSerializeDeserializeConsistency() throws JsonProcessingException {
            // Given
            OrderGoodsSkuAttributeGroup original = new OrderGoodsSkuAttributeGroup("主食",
                Arrays.asList(new OrderGoodsSkuAttribute("汉堡", 1, "个")));
            
            // When
            String json = objectMapper.writeValueAsString(original);
            OrderGoodsSkuAttributeGroup deserialized = objectMapper.readValue(json, OrderGoodsSkuAttributeGroup.class);
            
            // Then
            assertEquals(original.getGroupName(), deserialized.getGroupName());
            assertEquals(original.getItems().size(), deserialized.getItems().size());
            assertEquals(original.getItems().get(0).getName(), deserialized.getItems().get(0).getName());
            assertEquals(original, deserialized);
        }

        @Test
        @DisplayName("多个商品序列化测试")
        void testSerializeWithMultipleItems() throws JsonProcessingException {
            // Given
            attributeGroup.setGroupName("套餐");
            attributeGroup.setItems(Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个"),
                new OrderGoodsSkuAttribute("薯条", 1, "份"),
                new OrderGoodsSkuAttribute("可乐", 1, "杯")
            ));
            
            // When
            String json = objectMapper.writeValueAsString(attributeGroup);
            OrderGoodsSkuAttributeGroup deserialized = objectMapper.readValue(json, OrderGoodsSkuAttributeGroup.class);
            
            // Then
            assertEquals(3, deserialized.getItems().size());
            assertEquals("汉堡", deserialized.getItems().get(0).getName());
            assertEquals("薯条", deserialized.getItems().get(1).getName());
            assertEquals("可乐", deserialized.getItems().get(2).getName());
        }

        @Test
        @DisplayName("中文字符序列化测试")
        void testSerializeChineseCharacters() throws JsonProcessingException {
            // Given
            attributeGroup.setGroupName("麻辣烫套餐");
            attributeGroup.setItems(Arrays.asList(
                new OrderGoodsSkuAttribute("麻辣烫", 1, "碗"),
                new OrderGoodsSkuAttribute("酸梅汤", 1, "杯")
            ));
            
            // When
            String json = objectMapper.writeValueAsString(attributeGroup);
            OrderGoodsSkuAttributeGroup deserialized = objectMapper.readValue(json, OrderGoodsSkuAttributeGroup.class);
            
            // Then
            assertEquals("麻辣烫套餐", deserialized.getGroupName());
            assertEquals("麻辣烫", deserialized.getItems().get(0).getName());
            assertEquals("酸梅汤", deserialized.getItems().get(1).getName());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("长字符串分组名称测试")
        void testLongGroupName() {
            // Given
            StringBuilder longGroupNameBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longGroupNameBuilder.append("a");
            }
            String longGroupName = longGroupNameBuilder.toString();
            
            // When
            attributeGroup.setGroupName(longGroupName);
            
            // Then
            assertEquals(longGroupName, attributeGroup.getGroupName());
            assertEquals(1000, attributeGroup.getGroupName().length());
        }

        @Test
        @DisplayName("特殊字符分组名称测试")
        void testSpecialCharacterGroupName() {
            // Given
            String specialGroupName = "分组@#$%^&*()_+{}|:<>?";
            
            // When
            attributeGroup.setGroupName(specialGroupName);
            
            // Then
            assertEquals(specialGroupName, attributeGroup.getGroupName());
        }

        @Test
        @DisplayName("Unicode字符测试")
        void testUnicodeCharacters() {
            // Given
            String unicodeGroupName = "🍔主食组🍟";
            
            // When
            attributeGroup.setGroupName(unicodeGroupName);
            
            // Then
            assertEquals(unicodeGroupName, attributeGroup.getGroupName());
        }

        @Test
        @DisplayName("空格字符测试")
        void testWhitespaceCharacters() {
            // Given
            String nameWithSpaces = "  主食组  ";
            
            // When
            attributeGroup.setGroupName(nameWithSpaces);
            
            // Then
            assertEquals(nameWithSpaces, attributeGroup.getGroupName());
        }

        @Test
        @DisplayName("大量商品列表测试")
        void testLargeItemsList() {
            // Given
            List<OrderGoodsSkuAttribute> largeList = Arrays.asList(
                new OrderGoodsSkuAttribute("商品1", 1, "个"),
                new OrderGoodsSkuAttribute("商品2", 1, "个"),
                new OrderGoodsSkuAttribute("商品3", 1, "个"),
                new OrderGoodsSkuAttribute("商品4", 1, "个"),
                new OrderGoodsSkuAttribute("商品5", 1, "个"),
                new OrderGoodsSkuAttribute("商品6", 1, "个"),
                new OrderGoodsSkuAttribute("商品7", 1, "个"),
                new OrderGoodsSkuAttribute("商品8", 1, "个"),
                new OrderGoodsSkuAttribute("商品9", 1, "个"),
                new OrderGoodsSkuAttribute("商品10", 1, "个")
            );
            
            // When
            attributeGroup.setItems(largeList);
            
            // Then
            assertEquals(largeList, attributeGroup.getItems());
            assertEquals(10, attributeGroup.getItems().size());
        }
    }

    @Nested
    @DisplayName("业务逻辑测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("套餐分组创建测试")
        void testComboGroupCreation() {
            // Given
            String groupName = "汉堡套餐";
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("汉堡", 1, "个"),
                new OrderGoodsSkuAttribute("薯条", 1, "份"),
                new OrderGoodsSkuAttribute("可乐", 1, "杯")
            );
            
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(groupName, items);
            
            // Then
            assertTrue(isValidGroup(group));
            assertEquals(3, group.getItems().size());
        }

        @Test
        @DisplayName("单品分组创建测试")
        void testSingleItemGroupCreation() {
            // Given
            String groupName = "单品";
            List<OrderGoodsSkuAttribute> items = Arrays.asList(
                new OrderGoodsSkuAttribute("珍珠奶茶", 1, "杯")
            );
            
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(groupName, items);
            
            // Then
            assertTrue(isValidGroup(group));
            assertEquals(1, group.getItems().size());
        }

        @Test
        @DisplayName("空分组创建测试")
        void testEmptyGroupCreation() {
            // Given
            String groupName = "空分组";
            List<OrderGoodsSkuAttribute> items = Collections.emptyList();
            
            // When
            OrderGoodsSkuAttributeGroup group = new OrderGoodsSkuAttributeGroup(groupName, items);
            
            // Then
            assertEquals(groupName, group.getGroupName());
            assertTrue(group.getItems().isEmpty());
            // 空分组在某些业务场景下可能是有效的
        }

        @Test
        @DisplayName("分组名称验证测试")
        void testGroupNameValidation() {
            // Given
            String validGroupName = "主食";
            String emptyGroupName = "";
            String nullGroupName = null;
            
            // When & Then
            attributeGroup.setGroupName(validGroupName);
            assertTrue(isValidGroupName(attributeGroup.getGroupName()));
            
            attributeGroup.setGroupName(emptyGroupName);
            assertFalse(isValidGroupName(attributeGroup.getGroupName()));
            
            attributeGroup.setGroupName(nullGroupName);
            assertFalse(isValidGroupName(attributeGroup.getGroupName()));
        }

        /**
         * 简单的业务逻辑验证：分组是否有效
         */
        private boolean isValidGroup(OrderGoodsSkuAttributeGroup group) {
            return group != null && 
                   isValidGroupName(group.getGroupName()) && 
                   group.getItems() != null;
        }

        /**
         * 简单的业务逻辑验证：分组名称是否有效
         */
        private boolean isValidGroupName(String groupName) {
            return groupName != null && !groupName.trim().isEmpty();
        }
    }
}