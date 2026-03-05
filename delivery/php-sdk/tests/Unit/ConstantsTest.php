<?php

namespace Tests\Unit;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\PhpSdk\Consts\Consts;

/**
 * 常量定义测试
 * 确保所有常量值的正确性和可用性
 */
class ConstantsTest extends TestCase
{
    /**
     * 测试HTTP Content-Type常量
     */
    public function testContentTypeConstants()
    {
        $this->assertEquals('application/json', Consts::CONTENT_TYPE_JSON);
        $this->assertEquals('application/json; charset=utf-8', Consts::CONTENT_TYPE_JSON_UTF8);
    }

    /**
     * 测试HTTP状态码常量
     */
    public function testStatusCodeConstants()
    {
        $this->assertEquals(600, Consts::STATUS_EXCEPTION_SERVER_ERROR);
        $this->assertEquals(500, Consts::SERVER_ERROR_START);
    }

    /**
     * 测试API路径常量
     */
    public function testApiPathConstants()
    {
        $this->assertEquals('v1', Consts::API_VERSION);
        $this->assertEquals('v1/delivery', Consts::DELIVERY_PATH_PREFIX);
        $this->assertEquals('v1/express', Consts::EXPRESS_PATH_PREFIX);
    }

    /**
     * 测试业务状态码常量
     */
    public function testBusinessCodeConstants()
    {
        $this->assertEquals(200, Consts::SUCCESS_CODE);
    }

    /**
     * 测试环境URL常量
     */
    public function testEnvironmentUrls()
    {
        $this->assertEquals('https://open-api-test.maiyatian.com', Consts::TEST_BASE_URL);
        $this->assertEquals('https://open-api.maiyatian.com', Consts::PROD_BASE_URL);
        
        // 验证URL格式
        $this->assertTrue(filter_var(Consts::TEST_BASE_URL, FILTER_VALIDATE_URL) !== false);
        $this->assertTrue(filter_var(Consts::PROD_BASE_URL, FILTER_VALIDATE_URL) !== false);
    }

    /**
     * 测试授权类型常量
     */
    public function testGrantTypeConstants()
    {
        $this->assertEquals('1', Consts::GRANT_TYPE_STORE);
        $this->assertEquals('2', Consts::GRANT_TYPE_MERCHANT);
    }

    /**
     * 测试请求配置常量
     */
    public function testRequestConfigConstants()
    {
        $this->assertEquals(60, Consts::DEFAULT_TIMEOUT);
        $this->assertEquals(30, Consts::DEFAULT_CONNECT_TIMEOUT);
        $this->assertEquals(3, Consts::DEFAULT_RETRY_ATTEMPTS);
        $this->assertEquals(1, Consts::DEFAULT_RETRY_BASE_DELAY);
        $this->assertEquals(30, Consts::DEFAULT_RETRY_MAX_DELAY);
        
        // 验证数值合理性
        $this->assertGreaterThan(0, Consts::DEFAULT_TIMEOUT);
        $this->assertGreaterThan(0, Consts::DEFAULT_CONNECT_TIMEOUT);
        $this->assertGreaterThanOrEqual(0, Consts::DEFAULT_RETRY_ATTEMPTS);
    }

    /**
     * 测试配送状态常量
     */
    public function testDeliveryStatusConstants()
    {
        $this->assertEquals(1, Consts::DELIVERY_STATUS_PENDING);
        $this->assertEquals(2, Consts::DELIVERY_STATUS_DELIVERING);
        $this->assertEquals(3, Consts::DELIVERY_STATUS_DELIVERED);
        $this->assertEquals(4, Consts::DELIVERY_STATUS_CANCELED);
        
        // 验证状态值的唯一性
        $statuses = [
            Consts::DELIVERY_STATUS_PENDING,
            Consts::DELIVERY_STATUS_DELIVERING,
            Consts::DELIVERY_STATUS_DELIVERED,
            Consts::DELIVERY_STATUS_CANCELED
        ];
        
        $this->assertCount(4, array_unique($statuses), '配送状态常量值应该是唯一的');
    }

    /**
     * 测试快递类型常量
     */
    public function testExpressTypeConstants()
    {
        $this->assertEquals('instant', Consts::EXPRESS_TYPE_INSTANT);
        $this->assertEquals('scheduled', Consts::EXPRESS_TYPE_SCHEDULED);
        $this->assertEquals('next_day', Consts::EXPRESS_TYPE_NEXT_DAY);
        
        // 验证常量值不为空
        $this->assertNotEmpty(Consts::EXPRESS_TYPE_INSTANT);
        $this->assertNotEmpty(Consts::EXPRESS_TYPE_SCHEDULED);
        $this->assertNotEmpty(Consts::EXPRESS_TYPE_NEXT_DAY);
    }

    /**
     * 测试位置变更类型常量
     */
    public function testLocationTypeConstants()
    {
        $this->assertEquals('real_time', Consts::LOCATION_TYPE_REAL_TIME);
        $this->assertEquals('track', Consts::LOCATION_TYPE_TRACK);
        $this->assertEquals('milestone', Consts::LOCATION_TYPE_MILESTONE);
    }

    /**
     * 测试常量定义的完整性
     */
    public function testConstantCompletion()
    {
        // 使用反射检查所有公有常量
        $reflection = new \ReflectionClass(Consts::class);
        $constants = $reflection->getConstants();
        
        // 验证关键常量存在
        $requiredConstants = [
            'CONTENT_TYPE_JSON',
            'CONTENT_TYPE_JSON_UTF8',
            'SUCCESS_CODE',
            'TEST_BASE_URL',
            'PROD_BASE_URL',
            'DELIVERY_STATUS_PENDING',
            'DELIVERY_STATUS_DELIVERING',
            'DELIVERY_STATUS_DELIVERED',
            'DELIVERY_STATUS_CANCELED'
        ];
        
        foreach ($requiredConstants as $constantName) {
            $this->assertArrayHasKey($constantName, $constants, "常量 {$constantName} 应该被定义");
        }
        
        // 验证常量数量合理
        $this->assertGreaterThan(20, count($constants), '应该定义足够数量的常量');
    }

    /**
     * 测试状态码逻辑关系
     */
    public function testStatusCodeLogic()
    {
        // 服务器异常码应该大于一般服务器错误码
        $this->assertGreaterThan(Consts::SERVER_ERROR_START, Consts::STATUS_EXCEPTION_SERVER_ERROR);
        
        // 成功码应该是2xx范围
        $this->assertGreaterThanOrEqual(200, Consts::SUCCESS_CODE);
        $this->assertLessThan(300, Consts::SUCCESS_CODE);
    }

    /**
     * 测试配送业务逻辑
     */
    public function testDeliveryBusinessLogic()
    {
        // 配送状态应该按照业务流程递增
        $this->assertLessThan(Consts::DELIVERY_STATUS_DELIVERING, Consts::DELIVERY_STATUS_PENDING);
        $this->assertLessThan(Consts::DELIVERY_STATUS_DELIVERED, Consts::DELIVERY_STATUS_DELIVERING);
        
        // 已取消状态应该是最大值（表示终态）
        $this->assertGreaterThan(Consts::DELIVERY_STATUS_DELIVERED, Consts::DELIVERY_STATUS_CANCELED);
    }
}