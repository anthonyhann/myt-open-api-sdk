/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.vars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Variables 类单元测试
 * <p>
 * 测试SDK全局变量管理功能，包括：
 * <ul>
 * <li>版本号获取和格式验证</li>
 * <li>调试开关功能</li>
 * <li>User-Agent生成</li>
 * <li>环境变量解析</li>
 * </ul>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class VariablesTest {
    
    private String originalDebugValue;
    
    @BeforeEach
    void setUp() {
        // 保存原始DEBUG环境变量值
        originalDebugValue = System.getenv("DEBUG");
    }
    
    @AfterEach
    void tearDown() {
        // 恢复环境变量（注意：无法在运行时修改环境变量，这里仅作记录）
        // 实际测试中需要通过JVM参数或系统属性来模拟
    }
    
    /**
     * 测试版本号获取功能
     */
    @Test
    void testGetVersion() {
        String version = Variables.getVersion();
        
        // 验证版本号不为空
        assertNotNull(version, "版本号不能为null");
        assertFalse(version.trim().isEmpty(), "版本号不能为空字符串");
        
        // 验证版本号格式（应该是数字.数字.数字格式，或包含默认值）
        assertTrue(
            version.matches("\\d+\\.\\d+\\.\\d+.*") || version.equals("1.0.0"),
            "版本号应该是语义化版本格式或默认值1.0.0，实际值: " + version
        );
        
        // 验证版本号不包含多余的空白字符
        assertEquals(version, version.trim(), "版本号不应包含首尾空白字符");
        
        System.out.println("当前SDK版本: " + version);
    }
    
    /**
     * 测试User-Agent生成功能
     */
    @Test
    void testGetUserAgent() {
        String userAgent = Variables.getUserAgent();
        String version = Variables.getVersion();
        
        // 验证User-Agent格式
        assertNotNull(userAgent, "User-Agent不能为null");
        assertEquals("Myt-Java-SDK/" + version, userAgent, "User-Agent格式不正确");
        
        // 验证包含版本号
        assertTrue(userAgent.contains(version), "User-Agent应该包含版本号");
        
        // 验证格式符合HTTP规范（不包含非法字符）
        assertTrue(userAgent.matches("[\\w./-]+"), "User-Agent应该只包含合法的HTTP头字符");
        
        System.out.println("生成的User-Agent: " + userAgent);
    }
    
    /**
     * 测试调试开关功能（调试模式关闭时）
     */
    @Test
    @DisabledIfEnvironmentVariable(named = "DEBUG", matches = "true|TRUE|1|yes|YES|on|ON")
    void testIsDebuggingWhenDisabled() {
        boolean isDebugging = Variables.isDebugging();
        
        // 在没有设置DEBUG环境变量或设置为false时，应该返回false
        assertFalse(isDebugging, "DEBUG环境变量未设置或设置为false时，isDebugging()应该返回false");
        
        System.out.println("调试模式状态: " + (isDebugging ? "启用" : "禁用"));
    }
    
    /**
     * 测试调试开关功能（调试模式启用时）
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true|TRUE|1|yes|YES|on|ON")
    void testIsDebuggingWhenEnabled() {
        boolean isDebugging = Variables.isDebugging();
        
        // 当DEBUG环境变量设置为true时，应该返回true
        assertTrue(isDebugging, "DEBUG环境变量设置为true时，isDebugging()应该返回true");
        
        System.out.println("调试模式状态: " + (isDebugging ? "启用" : "禁用"));
    }
    
    /**
     * 测试版本号一致性
     * 多次调用应该返回相同的值
     */
    @Test
    void testVersionConsistency() {
        String version1 = Variables.getVersion();
        String version2 = Variables.getVersion();
        String version3 = Variables.getVersion();
        
        assertEquals(version1, version2, "多次调用getVersion()应该返回相同的值");
        assertEquals(version2, version3, "多次调用getVersion()应该返回相同的值");
        assertEquals(version1, version3, "多次调用getVersion()应该返回相同的值");
    }
    
    /**
     * 测试调试开关一致性
     * 多次调用应该返回相同的值
     */
    @Test
    void testDebuggingConsistency() {
        boolean debugging1 = Variables.isDebugging();
        boolean debugging2 = Variables.isDebugging();
        boolean debugging3 = Variables.isDebugging();
        
        assertEquals(debugging1, debugging2, "多次调用isDebugging()应该返回相同的值");
        assertEquals(debugging2, debugging3, "多次调用isDebugging()应该返回相同的值");
        assertEquals(debugging1, debugging3, "多次调用isDebugging()应该返回相同的值");
    }
    
    /**
     * 测试User-Agent一致性
     * 多次调用应该返回相同的值
     */
    @Test
    void testUserAgentConsistency() {
        String userAgent1 = Variables.getUserAgent();
        String userAgent2 = Variables.getUserAgent();
        String userAgent3 = Variables.getUserAgent();
        
        assertEquals(userAgent1, userAgent2, "多次调用getUserAgent()应该返回相同的值");
        assertEquals(userAgent2, userAgent3, "多次调用getUserAgent()应该返回相同的值");
        assertEquals(userAgent1, userAgent3, "多次调用getUserAgent()应该返回相同的值");
    }
    
    /**
     * 测试静态初始化
     * Variables类应该能够正确初始化静态成员
     */
    @Test
    void testStaticInitialization() {
        // 这个测试主要验证类加载时没有抛出异常
        assertDoesNotThrow(() -> {
            Variables.getVersion();
            Variables.isDebugging();
            Variables.getUserAgent();
        }, "Variables类的静态初始化应该能够正常完成");
    }
    
    /**
     * 测试无法实例化
     * Variables应该是工具类，不能被实例化
     */
    @Test
    void testCannotInstantiate() {
        // Variables类应该有私有构造函数，无法实例化
        assertThrows(Exception.class, () -> {
            // 使用反射尝试创建实例
            Variables.class.getDeclaredConstructor().newInstance();
        }, "Variables类不应该能够被实例化");
    }
    
    /**
     * 性能测试：版本号获取
     * 测试大量调用时的性能表现
     */
    @Test
    void testVersionPerformance() {
        int iterations = 10000;
        long startTime = System.nanoTime();
        
        for (int i = 0; i < iterations; i++) {
            Variables.getVersion();
        }
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double avgTimePerCall = duration / (double) iterations;
        
        // 平均每次调用应该在1微秒以内（由于是缓存值）
        assertTrue(avgTimePerCall < 1000, // 1000纳秒 = 1微秒
            String.format("版本号获取性能测试失败，平均耗时: %.2f纳秒", avgTimePerCall));
        
        System.out.printf("版本号获取性能测试 - %d次调用，平均耗时: %.2f纳秒%n", iterations, avgTimePerCall);
    }
    
    /**
     * 集成测试：所有方法联合使用
     */
    @Test
    void testIntegrationUsage() {
        // 模拟SDK使用场景
        String version = Variables.getVersion();
        boolean debugging = Variables.isDebugging();
        String userAgent = Variables.getUserAgent();
        
        // 验证所有值都正常获取
        assertNotNull(version);
        assertNotNull(userAgent);
        // debugging可以是true或false，都是正常的
        
        // 验证User-Agent包含版本信息
        assertTrue(userAgent.contains(version));
        
        // 打印集成测试结果
        System.out.println("=== Variables集成测试结果 ===");
        System.out.println("版本号: " + version);
        System.out.println("调试模式: " + (debugging ? "启用" : "禁用"));
        System.out.println("User-Agent: " + userAgent);
        
        // 模拟调试日志输出
        if (debugging) {
            System.out.println("[DEBUG] 调试模式已启用，SDK版本: " + version);
        }
    }
}