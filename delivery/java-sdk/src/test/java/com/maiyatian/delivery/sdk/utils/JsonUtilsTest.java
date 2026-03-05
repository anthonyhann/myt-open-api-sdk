/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JSON工具测试
 * <p>
 * 测试JSON编解码功能
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class JsonUtilsTest {
    
    // 测试用的POJO类
    static class TestPOJO {
        private String name;
        private int age;
        private boolean active;
        
        // 无参构造函数
        public TestPOJO() {
        }
        
        // 全参构造函数
        public TestPOJO(String name, int age, boolean active) {
            this.name = name;
            this.age = age;
            this.active = active;
        }
        
        // Getter 和 Setter 方法
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getAge() {
            return age;
        }
        
        public void setAge(int age) {
            this.age = age;
        }
        
        public boolean isActive() {
            return active;
        }
        
        public void setActive(boolean active) {
            this.active = active;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestPOJO testPOJO = (TestPOJO) o;
            return age == testPOJO.age && active == testPOJO.active && name.equals(testPOJO.name);
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
    
    @Test
    void testToJson() {
        // 测试对象转JSON
        try {
            TestPOJO pojo = new TestPOJO("Test Name", 25, true);
            String json = JsonUtils.toJson(pojo);
            
            // 断言JSON不为空
            assertNotNull(json);
            assertFalse(json.isEmpty());
            
            // 断言JSON包含预期字段
            assertTrue(json.contains("name"));
            assertTrue(json.contains("Test Name"));
            assertTrue(json.contains("age"));
            assertTrue(json.contains("25"));
            assertTrue(json.contains("active"));
            assertTrue(json.contains("true"));
        } catch (IOException e) {
            fail("对象转JSON应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testFromJson() {
        // 测试JSON转对象
        try {
            String json = "{\"name\":\"Test Name\",\"age\":25,\"active\":true}";
            TestPOJO pojo = JsonUtils.fromJson(json, TestPOJO.class);
            
            // 断言对象不为空
            assertNotNull(pojo);
            
            // 断言字段值正确
            assertEquals("Test Name", pojo.getName());
            assertEquals(25, pojo.getAge());
            assertTrue(pojo.isActive());
        } catch (IOException e) {
            fail("JSON转对象应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testToMap() {
        // 测试JSON转Map
        try {
            String json = "{\"name\":\"Test Name\",\"age\":25,\"active\":true}";
            Map<String, Object> map = JsonUtils.toMap(json);
            
            // 断言Map不为空
            assertNotNull(map);
            assertEquals(3, map.size());
            
            // 断言Map包含预期字段和值
            assertEquals("Test Name", map.get("name"));
            assertEquals(25, map.get("age")); // JSON数字可能被解析为Integer
            assertEquals(true, map.get("active"));
        } catch (IOException e) {
            fail("JSON转Map应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testMapToObject() {
        // 测试Map转对象
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "Test Name");
            map.put("age", 25);
            map.put("active", true);
            
            TestPOJO pojo = JsonUtils.mapToObject(map, TestPOJO.class);
            
            // 断言对象不为空
            assertNotNull(pojo);
            
            // 断言字段值正确
            assertEquals("Test Name", pojo.getName());
            assertEquals(25, pojo.getAge());
            assertTrue(pojo.isActive());
        } catch (IOException e) {
            fail("Map转对象应该成功，但失败了: " + e.getMessage());
        }
    }
    
    @Test
    void testRoundTrip() {
        // 测试对象 -> JSON -> 对象的往返转换
        try {
            TestPOJO original = new TestPOJO("Test Name", 25, true);
            
            // 对象转JSON
            String json = JsonUtils.toJson(original);
            
            // JSON转对象
            TestPOJO result = JsonUtils.fromJson(json, TestPOJO.class);
            
            // 断言结果与原对象相等
            assertEquals(original, result);
            assertEquals(original.getName(), result.getName());
            assertEquals(original.getAge(), result.getAge());
            assertEquals(original.isActive(), result.isActive());
        } catch (IOException e) {
            fail("往返转换应该成功，但失败了: " + e.getMessage());
        }
    }
}
