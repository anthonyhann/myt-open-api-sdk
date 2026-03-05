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
 * ActivityInfo单元测试类
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("ActivityInfo 测试")
class ActivityInfoTest {

    private ObjectMapper objectMapper;
    private ActivityInfo activityInfo;

    @BeforeEach
    void setUp() {
        objectMapper = TestObjectMapperUtil.createConfiguredObjectMapper();
        activityInfo = new ActivityInfo();
    }

    @Nested
    @DisplayName("构造函数测试")
    class ConstructorTests {

        @Test
        @DisplayName("默认构造函数应该创建空对象")
        void defaultConstructor_ShouldCreateEmptyObject() {
            ActivityInfo info = new ActivityInfo();
            
            assertAll("默认构造函数验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getMerchant(), "merchant应该为null"),
                () -> assertNull(info.getReduce(), "reduce应该为null")
            );
        }

        @Test
        @DisplayName("全参构造函数应该正确设置所有字段")
        void fullConstructor_ShouldSetAllFields() {
            Integer type = 1;
            String title = "满30减10";
            Integer merchant = 500;
            Integer reduce = 1000;
            
            ActivityInfo info = new ActivityInfo(type, title, merchant, reduce);
            
            assertAll("全参构造函数验证",
                () -> assertEquals(type, info.getType(), "type应该被正确设置"),
                () -> assertEquals(title, info.getTitle(), "title应该被正确设置"),
                () -> assertEquals(merchant, info.getMerchant(), "merchant应该被正确设置"),
                () -> assertEquals(reduce, info.getReduce(), "reduce应该被正确设置")
            );
        }

        @Test
        @DisplayName("全参构造函数应该允许null值")
        void fullConstructor_ShouldAllowNullValues() {
            ActivityInfo info = new ActivityInfo(null, null, null, null);
            
            assertAll("null值验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getMerchant(), "merchant应该为null"),
                () -> assertNull(info.getReduce(), "reduce应该为null")
            );
        }
    }

    @Nested
    @DisplayName("Getter和Setter测试")
    class GetterSetterTests {

        @Test
        @DisplayName("type字段的getter和setter")
        void typeGetterSetter() {
            assertNull(activityInfo.getType(), "初始值应该为null");
            
            // 测试满减活动
            activityInfo.setType(1);
            assertEquals(Integer.valueOf(1), activityInfo.getType(), "满减活动类型应该正确设置");
            
            // 测试折扣活动
            activityInfo.setType(2);
            assertEquals(Integer.valueOf(2), activityInfo.getType(), "折扣活动类型应该正确设置");
            
            // 测试其他活动
            activityInfo.setType(3);
            assertEquals(Integer.valueOf(3), activityInfo.getType(), "其他活动类型应该正确设置");
            
            // 测试设置null
            activityInfo.setType(null);
            assertNull(activityInfo.getType(), "应该能够设置为null");
        }

        @Test
        @DisplayName("title字段的getter和setter")
        void titleGetterSetter() {
            assertNull(activityInfo.getTitle(), "初始值应该为null");
            
            String title = "满30减10";
            activityInfo.setTitle(title);
            assertEquals(title, activityInfo.getTitle(), "title应该正确设置");
            
            // 测试空字符串
            activityInfo.setTitle("");
            assertEquals("", activityInfo.getTitle(), "应该能够设置空字符串");
            
            // 测试null
            activityInfo.setTitle(null);
            assertNull(activityInfo.getTitle(), "应该能够设置为null");
        }

        @Test
        @DisplayName("merchant字段的getter和setter")
        void merchantGetterSetter() {
            assertNull(activityInfo.getMerchant(), "初始值应该为null");
            
            Integer merchant = 500;
            activityInfo.setMerchant(merchant);
            assertEquals(merchant, activityInfo.getMerchant(), "merchant应该正确设置");
            
            // 测试零值
            activityInfo.setMerchant(0);
            assertEquals(Integer.valueOf(0), activityInfo.getMerchant(), "应该能够设置为0");
            
            // 测试负数
            activityInfo.setMerchant(-100);
            assertEquals(Integer.valueOf(-100), activityInfo.getMerchant(), "应该能够设置负数");
            
            // 测试null
            activityInfo.setMerchant(null);
            assertNull(activityInfo.getMerchant(), "应该能够设置为null");
        }

        @Test
        @DisplayName("reduce字段的getter和setter")
        void reduceGetterSetter() {
            assertNull(activityInfo.getReduce(), "初始值应该为null");
            
            Integer reduce = 1000;
            activityInfo.setReduce(reduce);
            assertEquals(reduce, activityInfo.getReduce(), "reduce应该正确设置");
            
            // 测试零值
            activityInfo.setReduce(0);
            assertEquals(Integer.valueOf(0), activityInfo.getReduce(), "应该能够设置为0");
            
            // 测试负数
            activityInfo.setReduce(-100);
            assertEquals(Integer.valueOf(-100), activityInfo.getReduce(), "应该能够设置负数");
            
            // 测试null
            activityInfo.setReduce(null);
            assertNull(activityInfo.getReduce(), "应该能够设置为null");
        }
    }

    @Nested
    @DisplayName("equals方法测试")
    class EqualsTests {

        @Test
        @DisplayName("相同对象应该相等")
        void sameObject_ShouldBeEqual() {
            assertTrue(activityInfo.equals(activityInfo), "对象应该与自身相等");
        }

        @Test
        @DisplayName("null对象应该不相等")
        void nullObject_ShouldNotBeEqual() {
            assertFalse(activityInfo.equals(null), "对象不应该与null相等");
        }

        @Test
        @DisplayName("不同类型对象应该不相等")
        void differentType_ShouldNotBeEqual() {
            String differentType = "not an ActivityInfo";
            assertFalse(activityInfo.equals(differentType), "不同类型的对象不应该相等");
        }

        @Test
        @DisplayName("具有相同值的对象应该相等")
        void sameValues_ShouldBeEqual() {
            ActivityInfo info1 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo info2 = new ActivityInfo(1, "满30减10", 500, 1000);
            
            assertEquals(info1, info2, "具有相同值的对象应该相等");
            assertEquals(info2, info1, "equals方法应该是对称的");
        }

        @Test
        @DisplayName("具有不同值的对象应该不相等")
        void differentValues_ShouldNotBeEqual() {
            ActivityInfo info1 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo info2 = new ActivityInfo(2, "满30减10", 500, 1000);
            
            assertNotEquals(info1, info2, "具有不同值的对象不应该相等");
        }

        @Test
        @DisplayName("null字段的对象应该正确比较")
        void nullFields_ShouldCompareCorrectly() {
            ActivityInfo info1 = new ActivityInfo(null, null, null, null);
            ActivityInfo info2 = new ActivityInfo(null, null, null, null);
            ActivityInfo info3 = new ActivityInfo(1, null, null, null);
            
            assertEquals(info1, info2, "都为null的字段应该相等");
            assertNotEquals(info1, info3, "部分null的字段不应该相等");
        }

        @Test
        @DisplayName("equals方法应该具有传递性")
        void equals_ShouldBeTransitive() {
            ActivityInfo info1 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo info2 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo info3 = new ActivityInfo(1, "满30减10", 500, 1000);
            
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
            ActivityInfo info1 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo info2 = new ActivityInfo(1, "满30减10", 500, 1000);
            
            assertEquals(info1.hashCode(), info2.hashCode(), 
                "相等的对象应该有相同的hashCode");
        }

        @Test
        @DisplayName("hashCode应该保持一致")
        void hashCode_ShouldBeConsistent() {
            ActivityInfo info = new ActivityInfo(1, "满30减10", 500, 1000);
            int hashCode1 = info.hashCode();
            int hashCode2 = info.hashCode();
            
            assertEquals(hashCode1, hashCode2, "多次调用hashCode应该返回相同值");
        }

        @Test
        @DisplayName("null字段的hashCode应该正确计算")
        void nullFields_ShouldCalculateHashCodeCorrectly() {
            ActivityInfo info = new ActivityInfo(null, null, null, null);
            
            assertDoesNotThrow(() -> info.hashCode(), "null字段不应该导致hashCode抛出异常");
        }

        @Test
        @DisplayName("修改对象后hashCode应该改变")
        void modifiedObject_ShouldChangeHashCode() {
            ActivityInfo info = new ActivityInfo(1, "满30减10", 500, 1000);
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
            ActivityInfo info = new ActivityInfo(1, "满30减10", 500, 1000);
            String result = info.toString();
            
            assertAll("toString内容验证",
                () -> assertTrue(result.contains("ActivityInfo"), "应该包含类名"),
                () -> assertTrue(result.contains("type=1"), "应该包含type字段"),
                () -> assertTrue(result.contains("title='满30减10'"), "应该包含title字段"),
                () -> assertTrue(result.contains("merchant=500"), "应该包含merchant字段"),
                () -> assertTrue(result.contains("reduce=1000"), "应该包含reduce字段")
            );
        }

        @Test
        @DisplayName("toString应该正确处理null值")
        void toString_ShouldHandleNullValues() {
            ActivityInfo info = new ActivityInfo(null, null, null, null);
            String result = info.toString();
            
            assertAll("null值toString验证",
                () -> assertTrue(result.contains("type=null"), "应该正确显示null type"),
                () -> assertTrue(result.contains("title=null"), "应该正确显示null title"),
                () -> assertTrue(result.contains("merchant=null"), "应该正确显示null merchant"),
                () -> assertTrue(result.contains("reduce=null"), "应该正确显示null reduce")
            );
        }

        @Test
        @DisplayName("toString应该返回非null字符串")
        void toString_ShouldReturnNonNullString() {
            ActivityInfo info = new ActivityInfo();
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
            ActivityInfo info = new ActivityInfo(1, "满30减10", 500, 1000);
            
            String json = objectMapper.writeValueAsString(info);
            
            assertAll("JSON序列化验证",
                () -> assertTrue(json.contains("\"type\":1"), "应该包含type字段"),
                () -> assertTrue(json.contains("\"title\":\"满30减10\""), "应该包含title字段"),
                () -> assertTrue(json.contains("\"merchant\":500"), "应该包含merchant字段"),
                () -> assertTrue(json.contains("\"reduce\":1000"), "应该包含reduce字段")
            );
        }

        @Test
        @DisplayName("应该正确从JSON反序列化")
        void shouldDeserializeFromJson() throws Exception {
            String json = "{\"type\":1,\"title\":\"满30减10\",\"merchant\":500,\"reduce\":1000}";
            
            ActivityInfo info = objectMapper.readValue(json, ActivityInfo.class);
            
            assertAll("JSON反序列化验证",
                () -> assertEquals(Integer.valueOf(1), info.getType(), "type应该正确反序列化"),
                () -> assertEquals("满30减10", info.getTitle(), "title应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(500), info.getMerchant(), "merchant应该正确反序列化"),
                () -> assertEquals(Integer.valueOf(1000), info.getReduce(), "reduce应该正确反序列化")
            );
        }

        @Test
        @DisplayName("应该正确处理JSON中的null值")
        void shouldHandleNullValuesInJson() throws Exception {
            String json = "{\"type\":null,\"title\":null,\"merchant\":null,\"reduce\":null}";
            
            ActivityInfo info = objectMapper.readValue(json, ActivityInfo.class);
            
            assertAll("JSON null值处理验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getMerchant(), "merchant应该为null"),
                () -> assertNull(info.getReduce(), "reduce应该为null")
            );
        }

        @Test
        @DisplayName("序列化和反序列化应该保持一致")
        void serializationRoundTrip_ShouldMaintainConsistency() throws Exception {
            ActivityInfo original = new ActivityInfo(1, "满30减10", 500, 1000);
            
            String json = objectMapper.writeValueAsString(original);
            ActivityInfo deserialized = objectMapper.readValue(json, ActivityInfo.class);
            
            assertEquals(original, deserialized, "序列化和反序列化后对象应该相等");
        }

        @Test
        @DisplayName("应该正确处理空JSON对象")
        void shouldHandleEmptyJsonObject() throws Exception {
            String json = "{}";
            
            ActivityInfo info = objectMapper.readValue(json, ActivityInfo.class);
            
            assertAll("空JSON对象处理验证",
                () -> assertNull(info.getType(), "type应该为null"),
                () -> assertNull(info.getTitle(), "title应该为null"),
                () -> assertNull(info.getMerchant(), "merchant应该为null"),
                () -> assertNull(info.getReduce(), "reduce应该为null")
            );
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {

        @Test
        @DisplayName("应该处理极大的数值")
        void shouldHandleLargeNumbers() {
            Integer maxValue = Integer.MAX_VALUE;
            ActivityInfo info = new ActivityInfo(maxValue, "大额活动", maxValue, maxValue);
            
            assertAll("极大数值验证",
                () -> assertEquals(maxValue, info.getType(), "应该能处理Integer.MAX_VALUE的type"),
                () -> assertEquals(maxValue, info.getMerchant(), "应该能处理Integer.MAX_VALUE的merchant"),
                () -> assertEquals(maxValue, info.getReduce(), "应该能处理Integer.MAX_VALUE的reduce")
            );
        }

        @Test
        @DisplayName("应该处理极小的数值")
        void shouldHandleSmallNumbers() {
            Integer minValue = Integer.MIN_VALUE;
            ActivityInfo info = new ActivityInfo(minValue, "负数活动", minValue, minValue);
            
            assertAll("极小数值验证",
                () -> assertEquals(minValue, info.getType(), "应该能处理Integer.MIN_VALUE的type"),
                () -> assertEquals(minValue, info.getMerchant(), "应该能处理Integer.MIN_VALUE的merchant"),
                () -> assertEquals(minValue, info.getReduce(), "应该能处理Integer.MIN_VALUE的reduce")
            );
        }

        @Test
        @DisplayName("应该处理长字符串")
        void shouldHandleLongStrings() {
            StringBuilder longString = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longString.append("长字符串测试");
            }
            String longTitle = longString.toString();
            
            ActivityInfo info = new ActivityInfo(1, longTitle, 500, 1000);
            
            assertEquals(longTitle, info.getTitle(), "应该能处理长字符串");
        }

        @Test
        @DisplayName("应该处理包含特殊字符的字符串")
        void shouldHandleSpecialCharacters() {
            String specialTitle = "满30减10！@#$%^&*()_+{}|:<>?[]\\;'\".,/~`";
            ActivityInfo info = new ActivityInfo(1, specialTitle, 500, 1000);
            
            assertEquals(specialTitle, info.getTitle(), "应该能处理包含特殊字符的字符串");
        }

        @Test
        @DisplayName("应该处理包含Unicode字符的字符串")
        void shouldHandleUnicodeCharacters() {
            String unicodeTitle = "满30减10 🎉 活动优惠 ￥";
            ActivityInfo info = new ActivityInfo(1, unicodeTitle, 500, 1000);
            
            assertEquals(unicodeTitle, info.getTitle(), "应该能处理包含Unicode字符的字符串");
        }
    }

    @Nested
    @DisplayName("业务逻辑验证测试")
    class BusinessLogicTests {

        @Test
        @DisplayName("活动类型应该符合业务枚举")
        void activityType_ShouldFollowBusinessEnum() {
            // 1-满减活动，2-折扣活动，3-其他
            ActivityInfo fullReductionActivity = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo discountActivity = new ActivityInfo(2, "8折优惠", 200, 800);
            ActivityInfo otherActivity = new ActivityInfo(3, "其他活动", 100, 500);
            
            assertAll("业务活动类型验证",
                () -> assertEquals(Integer.valueOf(1), fullReductionActivity.getType(), "满减活动类型应该为1"),
                () -> assertEquals(Integer.valueOf(2), discountActivity.getType(), "折扣活动类型应该为2"),
                () -> assertEquals(Integer.valueOf(3), otherActivity.getType(), "其他活动类型应该为3")
            );
        }

        @Test
        @DisplayName("减免金额应该大于等于商户承担金额")
        void reduce_ShouldBeGreaterThanOrEqualToMerchant() {
            ActivityInfo validActivity1 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo validActivity2 = new ActivityInfo(1, "满50减20", 1000, 1000);
            
            assertAll("减免金额业务逻辑验证",
                () -> assertTrue(validActivity1.getReduce() >= validActivity1.getMerchant(), 
                    "减免金额应该大于等于商户承担金额"),
                () -> assertTrue(validActivity2.getReduce() >= validActivity2.getMerchant(), 
                    "减免金额可以等于商户承担金额")
            );
        }

        @Test
        @DisplayName("活动描述应该清晰表达优惠信息")
        void title_ShouldClearlyDescribeDiscount() {
            ActivityInfo activity1 = new ActivityInfo(1, "满30减10", 500, 1000);
            ActivityInfo activity2 = new ActivityInfo(2, "8折优惠", 200, 800);
            
            assertAll("活动描述验证",
                () -> assertNotNull(activity1.getTitle(), "活动描述不应该为null"),
                () -> assertFalse(activity1.getTitle().isEmpty(), "活动描述不应该为空"),
                () -> assertNotNull(activity2.getTitle(), "活动描述不应该为null"),
                () -> assertFalse(activity2.getTitle().isEmpty(), "活动描述不应该为空")
            );
        }

        @Test
        @DisplayName("金额字段应该使用分为单位")
        void amounts_ShouldBeInCents() {
            // 测试金额以分为单位的合理性
            ActivityInfo activity = new ActivityInfo(1, "满3元减1元", 50, 100);
            
            assertAll("金额单位验证",
                () -> assertEquals(Integer.valueOf(50), activity.getMerchant(), "商户承担50分（0.5元）"),
                () -> assertEquals(Integer.valueOf(100), activity.getReduce(), "减免100分（1元）")
            );
        }
    }
}