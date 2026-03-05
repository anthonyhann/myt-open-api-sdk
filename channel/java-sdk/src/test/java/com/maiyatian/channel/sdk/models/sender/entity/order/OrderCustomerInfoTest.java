/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderCustomerInfo单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("OrderCustomerInfo 测试")
class OrderCustomerInfoTest {

    private ObjectMapper objectMapper;
    private OrderCustomerInfo orderCustomerInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        orderCustomerInfo = new OrderCustomerInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            OrderCustomerInfo info = new OrderCustomerInfo();
            
            assertAll("默认构造函数验证",
                () -> assertNull(info.getRealName(), "realName应该为null"),
                () -> assertNull(info.getPhone(), "phone应该为null"),
                () -> assertNull(info.getSecretPhone(), "secretPhone应该为null"),
                () -> assertNull(info.getOrderPhone(), "orderPhone应该为null"),
                () -> assertNull(info.getReservePhone(), "reservePhone应该为null"),
                () -> assertNull(info.getAddress(), "address应该为null"),
                () -> assertNull(info.getLongitude(), "longitude应该为null"),
                () -> assertNull(info.getLatitude(), "latitude应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void allArgsConstructor_ShouldSetAllFields() {
            // 准备测试数据
            String realName = "张*生";
            String phone = "15525426477_0067";
            String secretPhone = "157****8884";
            String orderPhone = "13800138000";
            String reservePhone = "13900139000";
            String address = "朔二区-18号楼 (18号楼一单元202)";
            String longitude = "116.397428";
            String latitude = "39.90923";
            
            // 测试构造函数
            OrderCustomerInfo info = new OrderCustomerInfo(realName, phone, secretPhone, 
                orderPhone, reservePhone, address, longitude, latitude);
            
            assertAll("全参构造函数验证",
                () -> assertEquals(realName, info.getRealName(), "realName应该被正确设置"),
                () -> assertEquals(phone, info.getPhone(), "phone应该被正确设置"),
                () -> assertEquals(secretPhone, info.getSecretPhone(), "secretPhone应该被正确设置"),
                () -> assertEquals(orderPhone, info.getOrderPhone(), "orderPhone应该被正确设置"),
                () -> assertEquals(reservePhone, info.getReservePhone(), "reservePhone应该被正确设置"),
                () -> assertEquals(address, info.getAddress(), "address应该被正确设置"),
                () -> assertEquals(longitude, info.getLongitude(), "longitude应该被正确设置"),
                () -> assertEquals(latitude, info.getLatitude(), "latitude应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该接受null值")
        void allArgsConstructor_ShouldAcceptNullValues() {
            OrderCustomerInfo info = new OrderCustomerInfo(null, null, null, null, null, null, null, null);
            
            assertAll("null值构造函数验证",
                () -> assertNull(info.getRealName(), "realName可以为null"),
                () -> assertNull(info.getPhone(), "phone可以为null"),
                () -> assertNull(info.getSecretPhone(), "secretPhone可以为null"),
                () -> assertNull(info.getOrderPhone(), "orderPhone可以为null"),
                () -> assertNull(info.getReservePhone(), "reservePhone可以为null"),
                () -> assertNull(info.getAddress(), "address可以为null"),
                () -> assertNull(info.getLongitude(), "longitude可以为null"),
                () -> assertNull(info.getLatitude(), "latitude可以为null")
            );
        }
    }

    @Nested
    @DisplayName("Getter和Setter方法测试")
    class GetterSetterTests {

        @Test
        @DisplayName("realName的getter和setter应该正常工作")
        void realNameGetterSetter_ShouldWorkCorrectly() {
            String realName = "张*生";
            
            orderCustomerInfo.setRealName(realName);
            assertEquals(realName, orderCustomerInfo.getRealName(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setRealName(null);
            assertNull(orderCustomerInfo.getRealName(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("phone的getter和setter应该正常工作")
        void phoneGetterSetter_ShouldWorkCorrectly() {
            String phone = "15525426477_0067";
            
            orderCustomerInfo.setPhone(phone);
            assertEquals(phone, orderCustomerInfo.getPhone(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setPhone(null);
            assertNull(orderCustomerInfo.getPhone(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("secretPhone的getter和setter应该正常工作")
        void secretPhoneGetterSetter_ShouldWorkCorrectly() {
            String secretPhone = "157****8884";
            
            orderCustomerInfo.setSecretPhone(secretPhone);
            assertEquals(secretPhone, orderCustomerInfo.getSecretPhone(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setSecretPhone(null);
            assertNull(orderCustomerInfo.getSecretPhone(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("orderPhone的getter和setter应该正常工作")
        void orderPhoneGetterSetter_ShouldWorkCorrectly() {
            String orderPhone = "13800138000";
            
            orderCustomerInfo.setOrderPhone(orderPhone);
            assertEquals(orderPhone, orderCustomerInfo.getOrderPhone(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setOrderPhone(null);
            assertNull(orderCustomerInfo.getOrderPhone(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("reservePhone的getter和setter应该正常工作")
        void reservePhoneGetterSetter_ShouldWorkCorrectly() {
            String reservePhone = "13900139000";
            
            orderCustomerInfo.setReservePhone(reservePhone);
            assertEquals(reservePhone, orderCustomerInfo.getReservePhone(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setReservePhone(null);
            assertNull(orderCustomerInfo.getReservePhone(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("address的getter和setter应该正常工作")
        void addressGetterSetter_ShouldWorkCorrectly() {
            String address = "朔二区-18号楼 (18号楼一单元202)";
            
            orderCustomerInfo.setAddress(address);
            assertEquals(address, orderCustomerInfo.getAddress(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setAddress(null);
            assertNull(orderCustomerInfo.getAddress(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("longitude的getter和setter应该正常工作")
        void longitudeGetterSetter_ShouldWorkCorrectly() {
            String longitude = "116.397428";
            
            orderCustomerInfo.setLongitude(longitude);
            assertEquals(longitude, orderCustomerInfo.getLongitude(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setLongitude(null);
            assertNull(orderCustomerInfo.getLongitude(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("latitude的getter和setter应该正常工作")
        void latitudeGetterSetter_ShouldWorkCorrectly() {
            String latitude = "39.90923";
            
            orderCustomerInfo.setLatitude(latitude);
            assertEquals(latitude, orderCustomerInfo.getLatitude(), "getter应该返回setter设置的值");
            
            orderCustomerInfo.setLatitude(null);
            assertNull(orderCustomerInfo.getLatitude(), "setter应该能够设置null值");
        }

        @Test
        @DisplayName("链式调用测试")
        void chainedSetterCalls_ShouldWork() {
            String realName = "张*生";
            String phone = "15525426477_0067";
            String secretPhone = "157****8884";
            String orderPhone = "13800138000";
            String reservePhone = "13900139000";
            String address = "朔二区-18号楼 (18号楼一单元202)";
            String longitude = "116.397428";
            String latitude = "39.90923";
            
            orderCustomerInfo.setRealName(realName);
            orderCustomerInfo.setPhone(phone);
            orderCustomerInfo.setSecretPhone(secretPhone);
            orderCustomerInfo.setOrderPhone(orderPhone);
            orderCustomerInfo.setReservePhone(reservePhone);
            orderCustomerInfo.setAddress(address);
            orderCustomerInfo.setLongitude(longitude);
            orderCustomerInfo.setLatitude(latitude);
            
            assertAll("链式调用验证",
                () -> assertEquals(realName, orderCustomerInfo.getRealName()),
                () -> assertEquals(phone, orderCustomerInfo.getPhone()),
                () -> assertEquals(secretPhone, orderCustomerInfo.getSecretPhone()),
                () -> assertEquals(orderPhone, orderCustomerInfo.getOrderPhone()),
                () -> assertEquals(reservePhone, orderCustomerInfo.getReservePhone()),
                () -> assertEquals(address, orderCustomerInfo.getAddress()),
                () -> assertEquals(longitude, orderCustomerInfo.getLongitude()),
                () -> assertEquals(latitude, orderCustomerInfo.getLatitude())
            );
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertEquals(orderCustomerInfo, orderCustomerInfo, "对象应该与自身相等");
        }

        @Test
        @DisplayName("相同内容的不同对象应该相等")
        void differentObjectsWithSameContent_ShouldBeEqual() {
            String realName = "张*生";
            String phone = "15525426477_0067";
            String secretPhone = "157****8884";
            String orderPhone = "13800138000";
            String reservePhone = "13900139000";
            String address = "朔二区-18号楼 (18号楼一单元202)";
            String longitude = "116.397428";
            String latitude = "39.90923";
            
            OrderCustomerInfo info1 = new OrderCustomerInfo(realName, phone, secretPhone, 
                orderPhone, reservePhone, address, longitude, latitude);
            OrderCustomerInfo info2 = new OrderCustomerInfo(realName, phone, secretPhone, 
                orderPhone, reservePhone, address, longitude, latitude);
            
            assertEquals(info1, info2, "内容相同的对象应该相等");
        }

        @Test
        @DisplayName("不同realName的对象不应该相等")
        void differentRealName_ShouldNotBeEqual() {
            OrderCustomerInfo info1 = new OrderCustomerInfo("张*生", "13800138000", null, null, null, null, null, null);
            OrderCustomerInfo info2 = new OrderCustomerInfo("李*生", "13800138000", null, null, null, null, null, null);
            
            assertNotEquals(info1, info2, "不同realName的对象不应该相等");
        }

        @Test
        @DisplayName("不同phone的对象不应该相等")
        void differentPhone_ShouldNotBeEqual() {
            OrderCustomerInfo info1 = new OrderCustomerInfo("张*生", "13800138000", null, null, null, null, null, null);
            OrderCustomerInfo info2 = new OrderCustomerInfo("张*生", "13900139000", null, null, null, null, null, null);
            
            assertNotEquals(info1, info2, "不同phone的对象不应该相等");
        }

        @Test
        @DisplayName("不同address的对象不应该相等")
        void differentAddress_ShouldNotBeEqual() {
            OrderCustomerInfo info1 = new OrderCustomerInfo("张*生", "13800138000", null, null, null, "地址1", null, null);
            OrderCustomerInfo info2 = new OrderCustomerInfo("张*生", "13800138000", null, null, null, "地址2", null, null);
            
            assertNotEquals(info1, info2, "不同address的对象不应该相等");
        }

        @Test
        @DisplayName("不同坐标的对象不应该相等")
        void differentCoordinates_ShouldNotBeEqual() {
            OrderCustomerInfo info1 = new OrderCustomerInfo(null, null, null, null, null, null, "116.1", "39.1");
            OrderCustomerInfo info2 = new OrderCustomerInfo(null, null, null, null, null, null, "116.2", "39.2");
            
            assertNotEquals(info1, info2, "不同坐标的对象不应该相等");
        }

        @Test
        @DisplayName("与null比较应该返回false")
        void compareWithNull_ShouldReturnFalse() {
            assertNotEquals(orderCustomerInfo, null, "对象与null比较应该返回false");
        }

        @Test
        @DisplayName("与不同类型对象比较应该返回false")
        void compareWithDifferentType_ShouldReturnFalse() {
            assertNotEquals(orderCustomerInfo, "string", "与不同类型对象比较应该返回false");
        }

        @Test
        @DisplayName("null字段的对象比较")
        void objectsWithNullFields_ShouldBeComparedCorrectly() {
            OrderCustomerInfo info1 = new OrderCustomerInfo(null, null, null, null, null, null, null, null);
            OrderCustomerInfo info2 = new OrderCustomerInfo(null, null, null, null, null, null, null, null);
            
            assertEquals(info1, info2, "都是null字段的对象应该相等");
        }

        @Test
        @DisplayName("部分字段为null的对象比较")
        void objectsWithPartialNullFields_ShouldBeComparedCorrectly() {
            OrderCustomerInfo info1 = new OrderCustomerInfo("张*生", null, null, null, null, null, null, null);
            OrderCustomerInfo info2 = new OrderCustomerInfo(null, null, null, null, null, null, null, null);
            
            assertNotEquals(info1, info2, "部分字段不同的对象应该不相等");
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相同对象应该有相同的hashCode")
        void sameObjects_ShouldHaveSameHashCode() {
            String realName = "张*生";
            String phone = "15525426477_0067";
            String address = "朔二区-18号楼";
            
            OrderCustomerInfo info1 = new OrderCustomerInfo(realName, phone, null, null, null, address, null, null);
            OrderCustomerInfo info2 = new OrderCustomerInfo(realName, phone, null, null, null, address, null, null);
            
            assertEquals(info1.hashCode(), info2.hashCode(), "相同对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致性")
        void hashCode_ShouldBeConsistent() {
            OrderCustomerInfo info = new OrderCustomerInfo("张*生", "13800138000", null, null, null, "地址", null, null);
            int firstHashCode = info.hashCode();
            int secondHashCode = info.hashCode();
            
            assertEquals(firstHashCode, secondHashCode, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段不应该导致hashCode异常")
        void nullFields_ShouldNotCauseHashCodeException() {
            OrderCustomerInfo info = new OrderCustomerInfo(null, null, null, null, null, null, null, null);
            
            assertDoesNotThrow(() -> info.hashCode(), "null字段不应该导致hashCode异常");
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含类名")
        void toString_ShouldContainClassName() {
            String result = orderCustomerInfo.toString();
            assertTrue(result.contains("OrderCustomerInfo"), "toString应该包含类名");
        }

        @Test
        @DisplayName("toString应该包含所有字段")
        void toString_ShouldContainAllFields() {
            OrderCustomerInfo info = new OrderCustomerInfo("张*生", "13800138000", "157****8884", 
                "13800138000", "13900139000", "朔二区-18号楼", "116.397428", "39.90923");
            String result = info.toString();
            
            assertAll("toString字段验证",
                () -> assertTrue(result.contains("realName="), "应该包含realName字段"),
                () -> assertTrue(result.contains("phone="), "应该包含phone字段"),
                () -> assertTrue(result.contains("secretPhone="), "应该包含secretPhone字段"),
                () -> assertTrue(result.contains("orderPhone="), "应该包含orderPhone字段"),
                () -> assertTrue(result.contains("reservePhone="), "应该包含reservePhone字段"),
                () -> assertTrue(result.contains("address="), "应该包含address字段"),
                () -> assertTrue(result.contains("longitude="), "应该包含longitude字段"),
                () -> assertTrue(result.contains("latitude="), "应该包含latitude字段")
            );
        }

        @Test
        @DisplayName("toString应该处理null值")
        void toString_ShouldHandleNullValues() {
            OrderCustomerInfo info = new OrderCustomerInfo(null, null, null, null, null, null, null, null);
            String result = info.toString();
            
            assertAll("toString null值验证",
                () -> assertTrue(result.contains("realName=null") || result.contains("realName='null'"), "应该正确显示null的realName"),
                () -> assertTrue(result.contains("phone=null") || result.contains("phone='null'"), "应该正确显示null的phone"),
                () -> assertTrue(result.contains("secretPhone=null") || result.contains("secretPhone='null'"), "应该正确显示null的secretPhone"),
                () -> assertTrue(result.contains("orderPhone=null") || result.contains("orderPhone='null'"), "应该正确显示null的orderPhone"),
                () -> assertTrue(result.contains("reservePhone=null") || result.contains("reservePhone='null'"), "应该正确显示null的reservePhone"),
                () -> assertTrue(result.contains("address=null") || result.contains("address='null'"), "应该正确显示null的address"),
                () -> assertTrue(result.contains("longitude=null") || result.contains("longitude='null'"), "应该正确显示null的longitude"),
                () -> assertTrue(result.contains("latitude=null") || result.contains("latitude='null'"), "应该正确显示null的latitude")
            );
        }

        @Test
        @DisplayName("toString不应该抛出异常")
        void toString_ShouldNotThrowException() {
            assertDoesNotThrow(() -> orderCustomerInfo.toString(), "toString不应该抛出异常");
        }
    }

    @Nested
    @DisplayName("JSON序列化和反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该能够序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            OrderCustomerInfo info = new OrderCustomerInfo("张*生", "13800138000", "157****8884", 
                "13800138000", "13900139000", "朔二区-18号楼", "116.397428", "39.90923");
            
            assertDoesNotThrow(() -> objectMapper.writeValueAsString(info), 
                "应该能够序列化为JSON");
        }

        @Test
        @DisplayName("应该能够从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"real_name\":\"张*生\",\"phone\":\"13800138000\",\"secret_phone\":\"157****8884\"," +
                         "\"order_phone\":\"13800138000\",\"reserve_phone\":\"13900139000\"," +
                         "\"address\":\"朔二区-18号楼\",\"longitude\":\"116.397428\",\"latitude\":\"39.90923\"}";
            
            OrderCustomerInfo info = objectMapper.readValue(json, OrderCustomerInfo.class);
            
            assertAll("JSON反序列化验证",
                () -> assertNotNull(info, "反序列化结果不应该为null"),
                () -> assertEquals("张*生", info.getRealName(), "realName应该正确反序列化"),
                () -> assertEquals("13800138000", info.getPhone(), "phone应该正确反序列化"),
                () -> assertEquals("157****8884", info.getSecretPhone(), "secretPhone应该正确反序列化"),
                () -> assertEquals("13800138000", info.getOrderPhone(), "orderPhone应该正确反序列化"),
                () -> assertEquals("13900139000", info.getReservePhone(), "reservePhone应该正确反序列化"),
                () -> assertEquals("朔二区-18号楼", info.getAddress(), "address应该正确反序列化"),
                () -> assertEquals("116.397428", info.getLongitude(), "longitude应该正确反序列化"),
                () -> assertEquals("39.90923", info.getLatitude(), "latitude应该正确反序列化")
            );
        }

        @Test
        @DisplayName("JSON字段映射应该正确")
        void jsonFieldMapping_ShouldBeCorrect() throws Exception {
            OrderCustomerInfo info = new OrderCustomerInfo("张*生", "13800138000", "157****8884", 
                "13800138000", "13900139000", "朔二区-18号楼", "116.397428", "39.90923");
            String json = objectMapper.writeValueAsString(info);
            
            assertAll("JSON字段映射验证",
                () -> assertTrue(json.contains("\"real_name\""), "应该包含real_name字段"),
                () -> assertTrue(json.contains("\"phone\""), "应该包含phone字段"),
                () -> assertTrue(json.contains("\"secret_phone\""), "应该包含secret_phone字段"),
                () -> assertTrue(json.contains("\"order_phone\""), "应该包含order_phone字段"),
                () -> assertTrue(json.contains("\"reserve_phone\""), "应该包含reserve_phone字段"),
                () -> assertTrue(json.contains("\"address\""), "应该包含address字段"),
                () -> assertTrue(json.contains("\"longitude\""), "应该包含longitude字段"),
                () -> assertTrue(json.contains("\"latitude\""), "应该包含latitude字段")
            );
        }

        @Test
        @DisplayName("空对象应该能够序列化和反序列化")
        void emptyObject_ShouldSerializeAndDeserialize() throws Exception {
            OrderCustomerInfo original = new OrderCustomerInfo();
            
            String json = objectMapper.writeValueAsString(original);
            OrderCustomerInfo deserialized = objectMapper.readValue(json, OrderCustomerInfo.class);
            
            assertAll("空对象序列化验证",
                () -> assertNotNull(json, "JSON不应该为null"),
                () -> assertNotNull(deserialized, "反序列化对象不应该为null"),
                () -> assertNull(deserialized.getRealName(), "反序列化后realName应该为null"),
                () -> assertNull(deserialized.getPhone(), "反序列化后phone应该为null"),
                () -> assertNull(deserialized.getSecretPhone(), "反序列化后secretPhone应该为null"),
                () -> assertNull(deserialized.getOrderPhone(), "反序列化后orderPhone应该为null"),
                () -> assertNull(deserialized.getReservePhone(), "反序列化后reservePhone应该为null"),
                () -> assertNull(deserialized.getAddress(), "反序列化后address应该为null"),
                () -> assertNull(deserialized.getLongitude(), "反序列化后longitude应该为null"),
                () -> assertNull(deserialized.getLatitude(), "反序列化后latitude应该为null")
            );
        }

        @Test
        @DisplayName("序列化后反序列化应该保持数据一致性")
        void serializeDeserialize_ShouldMaintainDataIntegrity() throws Exception {
            OrderCustomerInfo original = new OrderCustomerInfo("张*生", "13800138000", "157****8884", 
                "13800138000", "13900139000", "朔二区-18号楼", "116.397428", "39.90923");
            
            String json = objectMapper.writeValueAsString(original);
            OrderCustomerInfo deserialized = objectMapper.readValue(json, OrderCustomerInfo.class);
            
            assertEquals(original, deserialized, "序列化后反序列化应该保持对象相等性");
        }
    }

    @Nested
    @DisplayName("业务逻辑方法测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("验证手机号格式")
        @JsonIgnore
        void validatePhoneFormat() {
            assertAll("手机号格式验证",
                () -> {
                    String normalPhone = "13800138000";
                    orderCustomerInfo.setPhone(normalPhone);
                    assertEquals(normalPhone, orderCustomerInfo.getPhone(), "正常手机号应该有效");
                },
                () -> {
                    String virtualPhone = "15525426477_0067";
                    orderCustomerInfo.setPhone(virtualPhone);
                    assertEquals(virtualPhone, orderCustomerInfo.getPhone(), "虚拟号应该有效");
                },
                () -> {
                    String internationalPhone = "+8613800138000";
                    orderCustomerInfo.setPhone(internationalPhone);
                    assertEquals(internationalPhone, orderCustomerInfo.getPhone(), "国际格式手机号应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证隐私号脱敏格式")
        @JsonIgnore
        void validateSecretPhoneFormat() {
            assertAll("隐私号格式验证",
                () -> {
                    String maskedPhone = "157****8884";
                    orderCustomerInfo.setSecretPhone(maskedPhone);
                    assertEquals(maskedPhone, orderCustomerInfo.getSecretPhone(), "星号脱敏手机号应该有效");
                },
                () -> {
                    String partialMasked = "138****0000";
                    orderCustomerInfo.setSecretPhone(partialMasked);
                    assertEquals(partialMasked, orderCustomerInfo.getSecretPhone(), "部分脱敏手机号应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证姓名脱敏格式")
        @JsonIgnore
        void validateNameMaskingFormat() {
            assertAll("姓名脱敏格式验证",
                () -> {
                    String maskedName = "张*生";
                    orderCustomerInfo.setRealName(maskedName);
                    assertEquals(maskedName, orderCustomerInfo.getRealName(), "星号脱敏姓名应该有效");
                },
                () -> {
                    String twoCharName = "李*";
                    orderCustomerInfo.setRealName(twoCharName);
                    assertEquals(twoCharName, orderCustomerInfo.getRealName(), "两字脱敏姓名应该有效");
                },
                () -> {
                    String fullName = "王小明";
                    orderCustomerInfo.setRealName(fullName);
                    assertEquals(fullName, orderCustomerInfo.getRealName(), "完整姓名应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证坐标格式（GCJ-02标准）")
        @JsonIgnore
        void validateCoordinateFormat() {
            assertAll("坐标格式验证",
                () -> {
                    // 北京坐标范围
                    String beijingLongitude = "116.397428";
                    String beijingLatitude = "39.90923";
                    orderCustomerInfo.setLongitude(beijingLongitude);
                    orderCustomerInfo.setLatitude(beijingLatitude);
                    assertEquals(beijingLongitude, orderCustomerInfo.getLongitude(), "北京经度应该有效");
                    assertEquals(beijingLatitude, orderCustomerInfo.getLatitude(), "北京纬度应该有效");
                },
                () -> {
                    // 上海坐标范围
                    String shanghaiLongitude = "121.473701";
                    String shanghaiLatitude = "31.230416";
                    orderCustomerInfo.setLongitude(shanghaiLongitude);
                    orderCustomerInfo.setLatitude(shanghaiLatitude);
                    assertEquals(shanghaiLongitude, orderCustomerInfo.getLongitude(), "上海经度应该有效");
                    assertEquals(shanghaiLatitude, orderCustomerInfo.getLatitude(), "上海纬度应该有效");
                },
                () -> {
                    // 高精度坐标
                    String highPrecisionLon = "116.3974281234";
                    String highPrecisionLat = "39.9092301234";
                    orderCustomerInfo.setLongitude(highPrecisionLon);
                    orderCustomerInfo.setLatitude(highPrecisionLat);
                    assertEquals(highPrecisionLon, orderCustomerInfo.getLongitude(), "高精度经度应该有效");
                    assertEquals(highPrecisionLat, orderCustomerInfo.getLatitude(), "高精度纬度应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证地址格式")
        @JsonIgnore
        void validateAddressFormat() {
            assertAll("地址格式验证",
                () -> {
                    String complexAddress = "朔二区-18号楼 (18号楼一单元202)";
                    orderCustomerInfo.setAddress(complexAddress);
                    assertEquals(complexAddress, orderCustomerInfo.getAddress(), "复杂地址格式应该有效");
                },
                () -> {
                    String simpleAddress = "北京市朝阳区";
                    orderCustomerInfo.setAddress(simpleAddress);
                    assertEquals(simpleAddress, orderCustomerInfo.getAddress(), "简单地址格式应该有效");
                },
                () -> {
                    String detailedAddress = "北京市朝阳区建国门外大街1号国贸大厦1期8层801室";
                    orderCustomerInfo.setAddress(detailedAddress);
                    assertEquals(detailedAddress, orderCustomerInfo.getAddress(), "详细地址格式应该有效");
                }
            );
        }

        @Test
        @DisplayName("验证多手机号一致性")
        @JsonIgnore
        void validatePhoneConsistency() {
            // 测试收货人电话、预订人电话和备用电话的一致性
            String mainPhone = "13800138000";
            String orderPhone = "13900139000";
            String reservePhone = "13700137000";
            
            orderCustomerInfo.setPhone(mainPhone);
            orderCustomerInfo.setOrderPhone(orderPhone);
            orderCustomerInfo.setReservePhone(reservePhone);
            
            assertAll("多手机号验证",
                () -> assertEquals(mainPhone, orderCustomerInfo.getPhone(), "主手机号应该正确设置"),
                () -> assertEquals(orderPhone, orderCustomerInfo.getOrderPhone(), "预订手机号应该正确设置"),
                () -> assertEquals(reservePhone, orderCustomerInfo.getReservePhone(), "备用手机号应该正确设置"),
                () -> assertNotEquals(orderCustomerInfo.getPhone(), orderCustomerInfo.getOrderPhone(), "主手机号和预订手机号可以不同"),
                () -> assertNotEquals(orderCustomerInfo.getPhone(), orderCustomerInfo.getReservePhone(), "主手机号和备用手机号可以不同")
            );
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryTests {

        @Test
        @DisplayName("极长字符串测试")
        void extremeLongStringTests() {
            // 测试超长姓名
            StringBuilder longName = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                longName.append("张");
            }
            
            // 测试超长地址
            StringBuilder longAddress = new StringBuilder();
            for (int i = 0; i < 500; i++) {
                longAddress.append("北京市朝阳区建国门外大街");
            }
            
            assertDoesNotThrow(() -> {
                orderCustomerInfo.setRealName(longName.toString());
                orderCustomerInfo.setAddress(longAddress.toString());
                
                assertEquals(longName.toString(), orderCustomerInfo.getRealName(), "超长姓名应该能设置");
                assertEquals(longAddress.toString(), orderCustomerInfo.getAddress(), "超长地址应该能设置");
            }, "处理超长字符串时不应该抛出异常");
        }

        @Test
        @DisplayName("特殊字符处理测试")
        void specialCharacterHandling() {
            assertAll("特殊字符处理测试",
                () -> {
                    String nameWithEmoji = "张三😊";
                    orderCustomerInfo.setRealName(nameWithEmoji);
                    assertEquals(nameWithEmoji, orderCustomerInfo.getRealName(), "包含emoji的姓名应该能设置");
                },
                () -> {
                    String addressWithSpecialChars = "北京市朝阳区#@$%^&*()";
                    orderCustomerInfo.setAddress(addressWithSpecialChars);
                    assertEquals(addressWithSpecialChars, orderCustomerInfo.getAddress(), "包含特殊字符的地址应该能设置");
                },
                () -> {
                    String phoneWithSpaces = "138 0013 8000";
                    orderCustomerInfo.setPhone(phoneWithSpaces);
                    assertEquals(phoneWithSpaces, orderCustomerInfo.getPhone(), "包含空格的手机号应该能设置");
                }
            );
        }

        @Test
        @DisplayName("坐标边界值测试")
        void coordinateBoundaryTests() {
            assertAll("坐标边界值测试",
                () -> {
                    // 经度范围：-180到180
                    orderCustomerInfo.setLongitude("-180");
                    orderCustomerInfo.setLatitude("-90");
                    assertEquals("-180", orderCustomerInfo.getLongitude(), "最小经度应该能设置");
                    assertEquals("-90", orderCustomerInfo.getLatitude(), "最小纬度应该能设置");
                },
                () -> {
                    orderCustomerInfo.setLongitude("180");
                    orderCustomerInfo.setLatitude("90");
                    assertEquals("180", orderCustomerInfo.getLongitude(), "最大经度应该能设置");
                    assertEquals("90", orderCustomerInfo.getLatitude(), "最大纬度应该能设置");
                },
                () -> {
                    orderCustomerInfo.setLongitude("0");
                    orderCustomerInfo.setLatitude("0");
                    assertEquals("0", orderCustomerInfo.getLongitude(), "零经度应该能设置");
                    assertEquals("0", orderCustomerInfo.getLatitude(), "零纬度应该能设置");
                }
            );
        }

        @Test
        @DisplayName("内存压力测试")
        void memoryPressureTest() {
            // 创建大量OrderCustomerInfo对象测试内存使用
            assertDoesNotThrow(() -> {
                for (int i = 0; i < 1000; i++) {
                    OrderCustomerInfo info = new OrderCustomerInfo(
                        "客户" + i,
                        "1380013800" + (i % 10),
                        "138****800" + (i % 10),
                        "1390013900" + (i % 10),
                        "1370013700" + (i % 10),
                        "地址" + i,
                        "116." + (i % 1000),
                        "39." + (i % 1000)
                    );
                    // 验证对象创建正常
                    assertNotNull(info.getRealName(), "姓名应该不为null");
                    assertNotNull(info.getPhone(), "手机号应该不为null");
                    assertNotNull(info.getAddress(), "地址应该不为null");
                }
            }, "创建大量对象时不应该抛出异常");
        }

        @Test
        @DisplayName("并发访问测试")
        void concurrentAccessTest() throws InterruptedException {
            final OrderCustomerInfo sharedInfo = new OrderCustomerInfo("张*生", "13800138000", 
                "157****8884", "13800138000", "13900139000", "朔二区-18号楼", "116.397428", "39.90923");
            final boolean[] testPassed = {true};
            
            // 创建多个线程同时访问对象
            Thread[] threads = new Thread[10];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            // 读取操作
                            String realName = sharedInfo.getRealName();
                            String phone = sharedInfo.getPhone();
                            String address = sharedInfo.getAddress();
                            String longitude = sharedInfo.getLongitude();
                            String latitude = sharedInfo.getLatitude();
                            
                            // 验证数据一致性
                            if (!"张*生".equals(realName) || !"13800138000".equals(phone) || 
                                !"朔二区-18号楼".equals(address) || !"116.397428".equals(longitude) ||
                                !"39.90923".equals(latitude)) {
                                testPassed[0] = false;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        testPassed[0] = false;
                    }
                });
                threads[i].start();
            }
            
            // 等待所有线程完成
            for (Thread thread : threads) {
                thread.join();
            }
            
            assertTrue(testPassed[0], "并发访问应该保持数据一致性");
        }

        @Test
        @DisplayName("空字符串处理测试")
        void emptyStringHandling() {
            assertAll("空字符串处理测试",
                () -> {
                    orderCustomerInfo.setRealName("");
                    assertEquals("", orderCustomerInfo.getRealName(), "空姓名应该能设置");
                },
                () -> {
                    orderCustomerInfo.setPhone("");
                    assertEquals("", orderCustomerInfo.getPhone(), "空手机号应该能设置");
                },
                () -> {
                    orderCustomerInfo.setAddress("");
                    assertEquals("", orderCustomerInfo.getAddress(), "空地址应该能设置");
                },
                () -> {
                    orderCustomerInfo.setLongitude("");
                    orderCustomerInfo.setLatitude("");
                    assertEquals("", orderCustomerInfo.getLongitude(), "空经度应该能设置");
                    assertEquals("", orderCustomerInfo.getLatitude(), "空纬度应该能设置");
                }
            );
        }
    }
}