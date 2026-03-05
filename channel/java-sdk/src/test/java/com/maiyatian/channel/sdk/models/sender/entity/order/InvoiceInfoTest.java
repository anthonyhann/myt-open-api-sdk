/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.sender.entity.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiyatian.channel.sdk.test.TestObjectMapperUtil;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * InvoiceInfo单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("InvoiceInfo 测试")
class InvoiceInfoTest {

    private ObjectMapper objectMapper;
    private InvoiceInfo invoiceInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        invoiceInfo = new InvoiceInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            InvoiceInfo info = new InvoiceInfo();
            
            assertAll("默认构造函数验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getTaxerId(), "taxerId应该为null"),
                () -> assertNull(info.getEmail(), "email应该为null"),
                () -> assertNull(info.getFormType(), "formType应该为null"),
                () -> assertNull(info.getEQrcode(), "eQrcode应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void fullConstructor_ShouldSetAllFields() {
            Integer type = 1;
            String title = "北京测试科技有限公司";
            String taxerId = "91110000123456789X";
            String email = "invoice@test.com";
            Integer formType = 2;
            String eQrcode = "https://test.com/qrcode.png";
            
            InvoiceInfo info = new InvoiceInfo(type, title, taxerId, email, formType, eQrcode);
            
            assertAll("全参构造函数验证",
                () -> assertEquals(type, info.getType(), "type应该被正确设置"),
                () -> assertEquals(title, info.getTitle(), "title应该被正确设置"),
                () -> assertEquals(taxerId, info.getTaxerId(), "taxerId应该被正确设置"),
                () -> assertEquals(email, info.getEmail(), "email应该被正确设置"),
                () -> assertEquals(formType, info.getFormType(), "formType应该被正确设置"),
                () -> assertEquals(eQrcode, info.getEQrcode(), "eQrcode应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该允许null值")
        void fullConstructor_ShouldAllowNullValues() {
            InvoiceInfo info = new InvoiceInfo(null, null, null, null, null, null);
            
            assertAll("null值验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getTaxerId(), "taxerId应该为null"),
                () -> assertNull(info.getEmail(), "email应该为null"),
                () -> assertNull(info.getFormType(), "formType应该为null"),
                () -> assertNull(info.getEQrcode(), "eQrcode应该为null")
            );
        }
    }

    @Nested
    @DisplayName("Getter和Setter测试")
    class GetterSetterTests {

        @Test
        @DisplayName("type字段的getter和setter")
        void typeGetterSetter() {
            assertNull(invoiceInfo.getType(), "初始值应该为null");
            
            // 测试公司类型
            invoiceInfo.setType(1);
            assertEquals(Integer.valueOf(1), invoiceInfo.getType(), "公司类型应该正确设置");
            
            // 测试个人类型
            invoiceInfo.setType(2);
            assertEquals(Integer.valueOf(2), invoiceInfo.getType(), "个人类型应该正确设置");
            
            // 测试设置null
            invoiceInfo.setType(null);
            assertNull(invoiceInfo.getType(), "应该能够设置为null");
        }

        @Test
        @DisplayName("title字段的getter和setter")
        void titleGetterSetter() {
            assertNull(invoiceInfo.getTitle(), "初始值应该为null");
            
            // 测试公司发票抬头
            String companyTitle = "北京测试科技有限公司";
            invoiceInfo.setTitle(companyTitle);
            assertEquals(companyTitle, invoiceInfo.getTitle(), "公司发票抬头应该正确设置");
            
            // 测试个人发票抬头
            String personalTitle = "张三";
            invoiceInfo.setTitle(personalTitle);
            assertEquals(personalTitle, invoiceInfo.getTitle(), "个人发票抬头应该正确设置");
            
            // 测试空字符串
            invoiceInfo.setTitle("");
            assertEquals("", invoiceInfo.getTitle(), "应该能够设置空字符串");
            
            // 测试null
            invoiceInfo.setTitle(null);
            assertNull(invoiceInfo.getTitle(), "应该能够设置为null");
        }

        @Test
        @DisplayName("taxerId字段的getter和setter")
        void taxerIdGetterSetter() {
            assertNull(invoiceInfo.getTaxerId(), "初始值应该为null");
            
            // 测试企业税号
            String taxerId = "91110000123456789X";
            invoiceInfo.setTaxerId(taxerId);
            assertEquals(taxerId, invoiceInfo.getTaxerId(), "税号应该正确设置");
            
            // 测试空字符串（个人可为空）
            invoiceInfo.setTaxerId("");
            assertEquals("", invoiceInfo.getTaxerId(), "应该能够设置空字符串");
            
            // 测试null
            invoiceInfo.setTaxerId(null);
            assertNull(invoiceInfo.getTaxerId(), "应该能够设置为null");
        }

        @Test
        @DisplayName("email字段的getter和setter")
        void emailGetterSetter() {
            assertNull(invoiceInfo.getEmail(), "初始值应该为null");
            
            String email = "invoice@test.com";
            invoiceInfo.setEmail(email);
            assertEquals(email, invoiceInfo.getEmail(), "email应该正确设置");
            
            // 测试不同邮箱格式
            String email2 = "user.name+tag@domain.com";
            invoiceInfo.setEmail(email2);
            assertEquals(email2, invoiceInfo.getEmail(), "复杂邮箱格式应该正确设置");
            
            // 测试空字符串
            invoiceInfo.setEmail("");
            assertEquals("", invoiceInfo.getEmail(), "应该能够设置空字符串");
            
            // 测试null
            invoiceInfo.setEmail(null);
            assertNull(invoiceInfo.getEmail(), "应该能够设置为null");
        }

        @Test
        @DisplayName("formType字段的getter和setter")
        void formTypeGetterSetter() {
            assertNull(invoiceInfo.getFormType(), "初始值应该为null");
            
            // 测试纸质发票
            invoiceInfo.setFormType(1);
            assertEquals(Integer.valueOf(1), invoiceInfo.getFormType(), "纸质发票类型应该正确设置");
            
            // 测试电子发票
            invoiceInfo.setFormType(2);
            assertEquals(Integer.valueOf(2), invoiceInfo.getFormType(), "电子发票类型应该正确设置");
            
            // 测试设置null
            invoiceInfo.setFormType(null);
            assertNull(invoiceInfo.getFormType(), "应该能够设置为null");
        }

        @Test
        @DisplayName("eQrcode字段的getter和setter")
        void eQrcodeGetterSetter() {
            assertNull(invoiceInfo.getEQrcode(), "初始值应该为null");
            
            String qrcode = "https://test.com/qrcode.png";
            invoiceInfo.setEQrcode(qrcode);
            assertEquals(qrcode, invoiceInfo.getEQrcode(), "二维码URL应该正确设置");
            
            // 测试Base64格式
            String base64Qrcode = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJ";
            invoiceInfo.setEQrcode(base64Qrcode);
            assertEquals(base64Qrcode, invoiceInfo.getEQrcode(), "Base64二维码应该正确设置");
            
            // 测试空字符串
            invoiceInfo.setEQrcode("");
            assertEquals("", invoiceInfo.getEQrcode(), "应该能够设置空字符串");
            
            // 测试null
            invoiceInfo.setEQrcode(null);
            assertNull(invoiceInfo.getEQrcode(), "应该能够设置为null");
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertTrue(invoiceInfo.equals(invoiceInfo), "对象应该与自身相等");
        }

        @Test
        @DisplayName("null对象应该不相等")
        void nullObject_ShouldNotBeEqual() {
            assertFalse(invoiceInfo.equals(null), "对象不应该与null相等");
        }

        @Test
        @DisplayName("不同类型对象应该不相等")
        void differentType_ShouldNotBeEqual() {
            String differentType = "not an InvoiceInfo";
            assertFalse(invoiceInfo.equals(differentType), "不同类型的对象不应该相等");
        }

        @Test
        @DisplayName("具有相同值的对象应该相等")
        void sameValues_ShouldBeEqual() {
            InvoiceInfo info1 = new InvoiceInfo(1, "北京测试科技有限公司", "91110000123456789X", 
                                               "invoice@test.com", 2, "https://test.com/qrcode.png");
            InvoiceInfo info2 = new InvoiceInfo(1, "北京测试科技有限公司", "91110000123456789X", 
                                               "invoice@test.com", 2, "https://test.com/qrcode.png");
            
            assertEquals(info1, info2, "具有相同值的对象应该相等");
            assertEquals(info2, info1, "equals方法应该是对称的");
        }

        @Test
        @DisplayName("具有不同值的对象应该不相等")
        void differentValues_ShouldNotBeEqual() {
            InvoiceInfo info1 = new InvoiceInfo(1, "公司A", "91110000123456789X", 
                                               "a@test.com", 2, "qrcode1.png");
            InvoiceInfo info2 = new InvoiceInfo(1, "公司B", "91110000123456789X", 
                                               "a@test.com", 2, "qrcode1.png");
            
            assertNotEquals(info1, info2, "具有不同值的对象不应该相等");
        }

        @Test
        @DisplayName("null字段的对象应该正确比较")
        void nullFields_ShouldCompareCorrectly() {
            InvoiceInfo info1 = new InvoiceInfo(null, null, null, null, null, null);
            InvoiceInfo info2 = new InvoiceInfo(null, null, null, null, null, null);
            InvoiceInfo info3 = new InvoiceInfo(1, null, null, null, null, null);
            
            assertEquals(info1, info2, "都为null的字段应该相等");
            assertNotEquals(info1, info3, "部分null的字段不应该相等");
        }

        @Test
        @DisplayName("equals方法应该具有传递性")
        void equals_ShouldBeTransitive() {
            InvoiceInfo info1 = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            InvoiceInfo info2 = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            InvoiceInfo info3 = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            
            assertTrue(info1.equals(info2), "info1应该等于info2");
            assertTrue(info2.equals(info3), "info2应该等于info3");
            assertTrue(info1.equals(info3), "info1应该等于info3（传递性）");
        }
    }

    @Nested
    @DisplayName("hashCode方法测试")
    class HashCodeTests {

        @Test
        @DisplayName("相等对象应该有相同的hashCode")
        void equalObjects_ShouldHaveSameHashCode() {
            InvoiceInfo info1 = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            InvoiceInfo info2 = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            
            assertEquals(info1.hashCode(), info2.hashCode(), 
                "相等的对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致")
        void hashCode_ShouldBeConsistent() {
            InvoiceInfo info = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            int hashCode1 = info.hashCode();
            int hashCode2 = info.hashCode();
            
            assertEquals(hashCode1, hashCode2, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段的hashCode应该正确计算")
        void nullFields_ShouldCalculateHashCodeCorrectly() {
            InvoiceInfo info = new InvoiceInfo(null, null, null, null, null, null);
            
            assertDoesNotThrow(() -> info.hashCode(), "null字段不应该导致hashCode抛出异常");
        }

        @Test
        @DisplayName("修改对象后hashCode应该改变")
        void modifiedObject_ShouldChangeHashCode() {
            InvoiceInfo info = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            int originalHashCode = info.hashCode();
            
            info.setType(2);
            int newHashCode = info.hashCode();
            
            assertNotEquals(originalHashCode, newHashCode, "修改对象后hashCode应该改变");
        }
    }

    @Nested
    @DisplayName("toString方法测试")
    class ToStringTests {

        @Test
        @DisplayName("toString应该包含所有字段")
        void toString_ShouldContainAllFields() {
            InvoiceInfo info = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            String result = info.toString();
            
            assertAll("toString内容验证",
                () -> assertTrue(result.contains("InvoiceInfo"), "应该包含类名"),
                () -> assertTrue(result.contains("type=1"), "应该包含type字段"),
                () -> assertTrue(result.contains("title='测试公司'"), "应该包含title字段"),
                () -> assertTrue(result.contains("taxerId='12345'"), "应该包含taxerId字段"),
                () -> assertTrue(result.contains("email='test@test.com'"), "应该包含email字段"),
                () -> assertTrue(result.contains("formType=2"), "应该包含formType字段"),
                () -> assertTrue(result.contains("eQrcode='qr.png'"), "应该包含eQrcode字段")
            );
        }

        @Test
        @DisplayName("toString应该正确处理null值")
        void toString_ShouldHandleNullValues() {
            InvoiceInfo info = new InvoiceInfo(null, null, null, null, null, null);
            String result = info.toString();
            
            assertAll("null值toString验证",
                () -> assertTrue(result.contains("type=null"), "应该正确显示null type"),
                () -> assertTrue(result.contains("title=null"), "应该正确显示null title"),
                () -> assertTrue(result.contains("taxerId=null"), "应该正确显示null taxerId"),
                () -> assertTrue(result.contains("email=null"), "应该正确显示null email"),
                () -> assertTrue(result.contains("formType=null"), "应该正确显示null formType"),
                () -> assertTrue(result.contains("eQrcode=null"), "应该正确显示null eQrcode")
            );
        }

        @Test
        @DisplayName("toString应该返回非null字符串")
        void toString_ShouldReturnNonNullString() {
            InvoiceInfo info = new InvoiceInfo();
            String result = info.toString();
            
            assertNotNull(result, "toString不应该返回null");
            assertFalse(result.isEmpty(), "toString不应该返回空字符串");
        }
    }

    @Nested
    @DisplayName("JSON序列化/反序列化测试")
    class JsonSerializationTests {

        @Test
        @DisplayName("应该正确序列化为JSON")
        void shouldSerializeToJson() throws Exception {
            InvoiceInfo info = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            
            String json = objectMapper.writeValueAsString(info);
            
            assertAll("JSON序列化验证",
                () -> assertTrue(json.contains("\"type\":1"), "应该包含type字段"),
                () -> assertTrue(json.contains("\"title\":\"测试公司\""), "应该包含title字段"),
                () -> assertTrue(json.contains("\"taxer_id\":\"12345\""), "应该包含taxer_id字段"),
                () -> assertTrue(json.contains("\"email\":\"test@test.com\""), "应该包含email字段"),
                () -> assertTrue(json.contains("\"form_type\":2"), "应该包含form_type字段"),
                () -> assertTrue(json.contains("\"e_qrcode\":\"qr.png\""), "应该包含e_qrcode字段")
            );
        }

        @Test
        @DisplayName("应该正确从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"type\":1,\"title\":\"测试公司\",\"taxer_id\":\"12345\"," +
                         "\"email\":\"test@test.com\",\"form_type\":2,\"e_qrcode\":\"qr.png\"}";
            
            InvoiceInfo info = objectMapper.readValue(json, InvoiceInfo.class);
            
            assertAll("JSON反序列化验证",
                () -> assertEquals(Integer.valueOf(1), info.getType(), "type应该正确反序列化"),
                () -> assertEquals("测试公司", info.getTitle(), "title应该正确反序列化"),
                () -> assertEquals("12345", info.getTaxerId(), "taxerId应该正确反序列化"),
                () -> assertEquals("test@test.com", info.getEmail(), "email应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(2), info.getFormType(), "formType应该正确反序列化"),
                () -> assertEquals("qr.png", info.getEQrcode(), "eQrcode应该正确反序列化")
            );
        }

        @Test
        @DisplayName("应该正确处理JSON中的null值")
        void shouldHandleNullValuesInJson() throws Exception {
            String json = "{\"type\":null,\"title\":null,\"taxer_id\":null," +
                         "\"email\":null,\"form_type\":null,\"e_qrcode\":null}";
            
            InvoiceInfo info = objectMapper.readValue(json, InvoiceInfo.class);
            
            assertAll("JSON null值处理验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getTaxerId(), "taxerId应该为null"),
                () -> assertNull(info.getEmail(), "email应该为null"),
                () -> assertNull(info.getFormType(), "formType应该为null"),
                () -> assertNull(info.getEQrcode(), "eQrcode应该为null")
            );
        }

        @Test
        @DisplayName("序列化和反序列化应该保持一致")
        void serializationRoundTrip_ShouldMaintainConsistency() throws Exception {
            InvoiceInfo original = new InvoiceInfo(1, "测试公司", "12345", "test@test.com", 2, "qr.png");
            
            String json = objectMapper.writeValueAsString(original);
            InvoiceInfo deserialized = objectMapper.readValue(json, InvoiceInfo.class);
            
            assertEquals(original, deserialized, "序列化和反序列化后对象应该相等");
        }

        @Test
        @DisplayName("应该正确处理空JSON对象")
        void shouldHandleEmptyJsonObject() throws Exception {
            String json = "{}";
            
            InvoiceInfo info = objectMapper.readValue(json, InvoiceInfo.class);
            
            assertAll("空JSON对象处理验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getTaxerId(), "taxerId应该为null"),
                () -> assertNull(info.getEmail(), "email应该为null"),
                () -> assertNull(info.getFormType(), "formType应该为null"),
                () -> assertNull(info.getEQrcode(), "eQrcode应该为null")
            );
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应该处理极长的发票抬头")
        void shouldHandleVeryLongTitle() {
            StringBuilder longTitle = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                longTitle.append("测试公司名称");
            }
            String title = longTitle.toString();
            
            InvoiceInfo info = new InvoiceInfo(1, title, "12345", "test@test.com", 2, "qr.png");
            
            assertEquals(title, info.getTitle(), "应该能处理极长的发票抬头");
        }

        @Test
        @DisplayName("应该处理各种格式的税号")
        void shouldHandleVariousTaxIdFormats() {
            String[] taxIds = {
                "91110000123456789X",    // 统一社会信用代码
                "110000123456789",       // 旧版税号
                "123456789012345678",    // 18位数字
                "ABCD123456789",         // 包含字母
                "12-34-56789",           // 包含连字符
                ""                       // 个人可为空
            };
            
            for (String taxId : taxIds) {
                InvoiceInfo info = new InvoiceInfo(1, "测试", taxId, "test@test.com", 2, "qr");
                assertEquals(taxId, info.getTaxerId(), "应该能处理税号格式: " + taxId);
            }
        }

        @Test
        @DisplayName("应该处理各种格式的邮箱")
        void shouldHandleVariousEmailFormats() {
            String[] emails = {
                "test@example.com",
                "user.name+tag@domain.co.uk",
                "x@example.com",
                "test.email-with-dash@example.com",
                "test@sub.domain.com",
                "test@[192.168.1.1]"
            };
            
            for (String email : emails) {
                InvoiceInfo info = new InvoiceInfo(1, "测试", "12345", email, 2, "qr");
                assertEquals(email, info.getEmail(), "应该能处理邮箱格式: " + email);
            }
        }

        @Test
        @DisplayName("应该处理各种格式的二维码数据")
        void shouldHandleVariousQrcodeFormats() {
            String[] qrcodes = {
                "https://example.com/qrcode.png",
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJ",
                "/relative/path/to/qrcode.png",
                "file://local/path/qrcode.png",
                "简单文本二维码内容",
                ""
            };
            
            for (String qrcode : qrcodes) {
                InvoiceInfo info = new InvoiceInfo(1, "测试", "12345", "test@test.com", 2, qrcode);
                assertEquals(qrcode, info.getEQrcode(), "应该能处理二维码格式: " + qrcode);
            }
        }

        @Test
        @DisplayName("应该处理包含特殊字符的字符串")
        void shouldHandleSpecialCharacters() {
            String specialTitle = "测试公司（北京）有限责任公司-分公司@#$%^&*()";
            String specialEmail = "test+special@sub-domain.com";
            String specialTaxer = "91110000-123456789-X";
            
            InvoiceInfo info = new InvoiceInfo(1, specialTitle, specialTaxer, specialEmail, 2, "qr");
            
            assertAll("特殊字符处理验证",
                () -> assertEquals(specialTitle, info.getTitle(), "应该能处理包含特殊字符的标题"),
                () -> assertEquals(specialEmail, info.getEmail(), "应该能处理包含特殊字符的邮箱"),
                () -> assertEquals(specialTaxer, info.getTaxerId(), "应该能处理包含特殊字符的税号")
            );
        }
    }

    @Nested
    @DisplayName("业务逻辑验证测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("发票抬头类型应该符合业务枚举")
        void invoiceType_ShouldFollowBusinessEnum() {
            // 1-公司（企业），2-个人
            InvoiceInfo companyInvoice = new InvoiceInfo(1, "北京测试科技有限公司", 
                "91110000123456789X", "company@test.com", 2, "qr.png");
            InvoiceInfo personalInvoice = new InvoiceInfo(2, "张三", 
                null, "personal@test.com", 2, "qr.png");
            
            assertAll("业务发票类型验证",
                () -> assertEquals(Integer.valueOf(1), companyInvoice.getType(), "企业发票类型应该为1"),
                () -> assertEquals(Integer.valueOf(2), personalInvoice.getType(), "个人发票类型应该为2")
            );
        }

        @Test
        @DisplayName("企业发票应该有税号，个人发票可以没有")
        void enterpriseInvoice_ShouldHaveTaxerId_PersonalCanBeEmpty() {
            InvoiceInfo enterpriseInvoice = new InvoiceInfo(1, "北京测试科技有限公司", 
                "91110000123456789X", "company@test.com", 2, "qr.png");
            InvoiceInfo personalInvoice = new InvoiceInfo(2, "张三", 
                null, "personal@test.com", 2, "qr.png");
            
            assertAll("税号业务逻辑验证",
                () -> assertNotNull(enterpriseInvoice.getTaxerId(), "企业发票应该有税号"),
                () -> assertFalse(enterpriseInvoice.getTaxerId().isEmpty(), "企业税号不应该为空"),
                () -> assertEquals(1, (int)enterpriseInvoice.getType(), "企业发票类型应该为1")
            );
            
            // 个人发票税号可以为null或空
            assertDoesNotThrow(() -> {
                personalInvoice.setTaxerId(null);
                personalInvoice.setTaxerId("");
            }, "个人发票税号可以为空");
        }

        @Test
        @DisplayName("发票形式应该符合业务枚举")
        void invoiceFormType_ShouldFollowBusinessEnum() {
            // 1-纸质发票，2-电子发票
            InvoiceInfo paperInvoice = new InvoiceInfo(1, "测试公司", "12345", 
                "test@test.com", 1, null);
            InvoiceInfo electronicInvoice = new InvoiceInfo(1, "测试公司", "12345", 
                "test@test.com", 2, "qr.png");
            
            assertAll("发票形式验证",
                () -> assertEquals(Integer.valueOf(1), paperInvoice.getFormType(), "纸质发票类型应该为1"),
                () -> assertEquals(Integer.valueOf(2), electronicInvoice.getFormType(), "电子发票类型应该为2")
            );
        }

        @Test
        @DisplayName("电子发票应该有二维码")
        void electronicInvoice_ShouldHaveQrcode() {
            InvoiceInfo electronicInvoice = new InvoiceInfo(1, "测试公司", "12345", 
                "test@test.com", 2, "https://test.com/qr.png");
            
            assertAll("电子发票二维码验证",
                () -> assertEquals(Integer.valueOf(2), electronicInvoice.getFormType(), "应该是电子发票"),
                () -> assertNotNull(electronicInvoice.getEQrcode(), "电子发票应该有二维码"),
                () -> assertFalse(electronicInvoice.getEQrcode().isEmpty(), "二维码不应该为空")
            );
        }

        @Test
        @DisplayName("发票邮箱应该是有效格式")
        void invoiceEmail_ShouldBeValidFormat() {
            InvoiceInfo invoice = new InvoiceInfo(1, "测试公司", "12345", 
                "test@example.com", 2, "qr.png");
            
            String email = invoice.getEmail();
            assertAll("邮箱格式验证",
                () -> assertNotNull(email, "发票邮箱不应该为null"),
                () -> assertTrue(email.contains("@"), "邮箱应该包含@符号"),
                () -> assertTrue(email.contains("."), "邮箱应该包含域名后缀")
            );
        }

        @Test
        @DisplayName("统一社会信用代码应该是18位")
        void unifiedSocialCreditCode_ShouldBe18Characters() {
            String validCreditCode = "91110000123456789X";
            InvoiceInfo invoice = new InvoiceInfo(1, "测试公司", validCreditCode, 
                "test@test.com", 2, "qr.png");
            
            assertEquals(18, validCreditCode.length(), "统一社会信用代码应该是18位");
            assertEquals(validCreditCode, invoice.getTaxerId(), "应该正确设置信用代码");
        }

        @Test
        @DisplayName("发票信息应该支持完整的开票流程")
        void invoiceInfo_ShouldSupportCompleteInvoicingProcess() {
            // 模拟完整的开票信息
            InvoiceInfo fullInvoice = new InvoiceInfo(
                1,  // 企业发票
                "北京麦芽田科技有限公司",  // 发票抬头
                "91110000123456789X",  // 统一社会信用代码
                "invoice@maiyatian.com",  // 接收邮箱
                2,  // 电子发票
                "https://invoice.maiyatian.com/qr/123456.png"  // 二维码链接
            );
            
            assertAll("完整开票信息验证",
                () -> assertEquals(Integer.valueOf(1), fullInvoice.getType(), "企业发票类型"),
                () -> assertNotNull(fullInvoice.getTitle(), "发票抬头不能为空"),
                () -> assertNotNull(fullInvoice.getTaxerId(), "企业税号不能为空"),
                () -> assertNotNull(fullInvoice.getEmail(), "接收邮箱不能为空"),
                () -> assertEquals(Integer.valueOf(2), fullInvoice.getFormType(), "电子发票形式"),
                () -> assertNotNull(fullInvoice.getEQrcode(), "电子发票二维码不能为空")
            );
        }
    }
}