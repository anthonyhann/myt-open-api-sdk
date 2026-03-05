<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: tests
 * @ClassName: DeliverySenderTest
 * @Description: 麦芽田配送开放平台SDK - 配送服务发送端单元测试
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\SDK\client\Config;
use Maiyatian\Delivery\SDK\client\ConfigBuilder;
use Maiyatian\Delivery\SDK\models\sender\api\DeliverySenderFactory;
use Maiyatian\Delivery\SDK\models\sender\entity\auth\AccessTokenRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\auth\RefreshTokenRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\delivery\DeliveryChangeRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\delivery\VehicleInfo;
use Maiyatian\Delivery\SDK\models\sender\entity\express\LocationChangeRequest;
use Maiyatian\Delivery\SDK\models\sender\entity\express\LocationPoint;
use Maiyatian\Delivery\SDK\models\types\Constants;

class DeliverySenderTest extends TestCase
{
    /**
     * @var Config
     */
    private $config;

    /**
     * @var IDeliverySender
     */
    private $sender;

    /**
     * @var string
     */
    private $testToken;

    /**
     * 初始化测试环境
     */
    protected function setUp(): void
    {
        // 创建配置
        $configBuilder = new ConfigBuilder();
        $this->config = $configBuilder
            ->baseURL(Constants::TEST_BASE_URL)
            ->apiKey("test_api_key")
            ->apiSecret("test_api_secret")
            ->build();

        // 创建配送服务发送端实例
        $this->sender = DeliverySenderFactory::create($this->config);

        // 测试用的token
        $this->testToken = "test_token_123";
    }

    /**
     * 测试创建DeliverySender实例
     */
    public function testCreateDeliverySender()
    {
        $this->assertInstanceOf(IDeliverySender::class, $this->sender);
    }

    /**
     * 测试AccessToken请求数据
     */
    public function testAccessTokenRequest()
    {
        // 创建请求数据
        $request = new AccessTokenRequest("test_code");
        $request->mobile = "13800138000";
        $request->store_id = "store_123";
        $request->city = "北京市";
        $request->city_code = "10001";
        $request->source_key = "source_key_123";
        $request->platform = "JD";

        // 验证请求数据
        $this->assertEquals("test_code", $request->code);
        $this->assertEquals("13800138000", $request->mobile);
        $this->assertEquals("store_123", $request->store_id);
        $this->assertEquals("北京市", $request->city);
        $this->assertEquals("10001", $request->city_code);
        $this->assertEquals("source_key_123", $request->source_key);
        $this->assertEquals("JD", $request->platform);
    }

    /**
     * 测试RefreshToken请求数据
     */
    public function testRefreshTokenRequest()
    {
        // 创建请求数据
        $request = new RefreshTokenRequest("old_token", "refresh_token");

        // 验证请求数据
        $this->assertEquals("old_token", $request->token);
        $this->assertEquals("refresh_token", $request->refresh_token);
    }

    /**
     * 测试DeliveryChange请求数据
     */
    public function testDeliveryChangeRequest()
    {
        // 创建车辆信息
        $vehicleInfo = new VehicleInfo("雪铁龙C5", "蓝色", "冀E4WE32");

        // 创建请求数据
        $request = new DeliveryChangeRequest("order_123", "source_order_123", "shop_123", "PICKUP");
        $request->rider_name = "林骑手";
        $request->rider_phone = "13888888888_1234";
        $request->longitude = "103.11111";
        $request->latitude = "90.1123123";
        $request->pickup_code = "取货码123";
        $request->distance = 1000;
        $request->delivery_fee = 850;
        $request->cancel_type = 1;
        $request->cancel_reason = "取消原因";
        $request->cancel_dedit_amount = 200;
        $request->vehicle_info = $vehicleInfo;
        $request->is_transship = false;

        // 验证请求数据
        $this->assertEquals("order_123", $request->order_no);
        $this->assertEquals("source_order_123", $request->source_order_no);
        $this->assertEquals("shop_123", $request->shop_id);
        $this->assertEquals("PICKUP", $request->status);
        $this->assertEquals("林骑手", $request->rider_name);
        $this->assertEquals("13888888888_1234", $request->rider_phone);
        $this->assertEquals("雪铁龙C5", $request->vehicle_info->vehicle_name);
        $this->assertEquals("蓝色", $request->vehicle_info->vehicle_color);
        $this->assertEquals("冀E4WE32", $request->vehicle_info->vehicle_no);
    }

    /**
     * 测试LocationChange请求数据
     */
    public function testLocationChangeRequest()
    {
        // 创建轨迹点
        $locationPoint1 = new LocationPoint("[合肥市]已揽收", "PICKUP");
        $locationPoint1->city = "合肥市";
        $locationPoint1->longitude = "117.230410";
        $locationPoint1->latitude = "31.820640";

        $locationPoint2 = new LocationPoint("[合肥市]已发出", "DELIVERING");
        $locationPoint2->city = "合肥市";
        $locationPoint2->longitude = "117.230420";
        $locationPoint2->latitude = "31.820650";

        // 创建请求数据
        $request = new LocationChangeRequest("dpk_123", "source_dpk_123", "shop_123");
        $request->addLocationPoint($locationPoint1);
        $request->addLocationPoint($locationPoint2);

        // 验证请求数据
        $this->assertEquals("dpk_123", $request->order_no);
        $this->assertEquals("source_dpk_123", $request->source_order_no);
        $this->assertEquals("shop_123", $request->shop_id);
        $this->assertCount(2, $request->locations);
        $this->assertEquals("[合肥市]已揽收", $request->locations[0]->description);
        $this->assertEquals("PICKUP", $request->locations[0]->status);
        $this->assertEquals("[合肥市]已发出", $request->locations[1]->description);
        $this->assertEquals("DELIVERING", $request->locations[1]->status);
    }

    /**
     * 测试常量定义
     */
    public function testConstants()
    {
        $this->assertEquals("v1", Constants::API_VERSION);
        $this->assertEquals("/v1/delivery", Constants::API_PATH_PREFIX);
        $this->assertEquals("https://open-api-test.maiyatian.com", Constants::TEST_BASE_URL);
        $this->assertEquals("https://open-api.maiyatian.com", Constants::PROD_BASE_URL);
        $this->assertEquals("1.0.0", Constants::SDK_VERSION);

        // 测试配送状态常量
        $this->assertEquals("PENDING", Constants::DELIVERY_STATUS_PENDING);
        $this->assertEquals("GRABBED", Constants::DELIVERY_STATUS_GRABBED);
        $this->assertEquals("PICKUP", Constants::DELIVERY_STATUS_PICKUP);
        $this->assertEquals("DELIVERING", Constants::DELIVERY_STATUS_DELIVERING);
        $this->assertEquals("DONE", Constants::DELIVERY_STATUS_DONE);
        $this->assertEquals("CANCEL", Constants::DELIVERY_STATUS_CANCEL);

        // 测试API命令常量
        $this->assertEquals("access_token", Constants::COMMAND_ACCESS_TOKEN);
        $this->assertEquals("refresh_token", Constants::COMMAND_REFRESH_TOKEN);
        $this->assertEquals("delivery_change", Constants::COMMAND_DELIVERY_CHANGE);
        $this->assertEquals("location_change", Constants::COMMAND_LOCATION_CHANGE);
    }

    /**
     * 测试配置构建器
     */
    public function testConfigBuilder()
    {
        $configBuilder = new ConfigBuilder();
        $customConfig = $configBuilder
            ->baseURL("https://custom-url.maiyatian.com")
            ->apiKey("custom_api_key")
            ->apiSecret("custom_api_secret")
            ->maxConnections(200)
            ->requestTimeout(60)
            ->retryMaxAttempts(3)
            ->enableLogging(false)
            ->build();

        $this->assertEquals("https://custom-url.maiyatian.com", $customConfig->baseURL);
        $this->assertEquals("custom_api_key", $customConfig->apiKey);
        $this->assertEquals("custom_api_secret", $customConfig->apiSecret);
        $this->assertEquals(200, $customConfig->maxConnections);
        $this->assertEquals(60, $customConfig->requestTimeout);
        $this->assertEquals(3, $customConfig->retryMaxAttempts);
        $this->assertEquals(false, $customConfig->enableLogging);
    }

    /**
     * 测试配置默认值
     */
    public function testConfigDefaults()
    {
        $configBuilder = new ConfigBuilder();
        $defaultConfig = $configBuilder
            ->baseURL(Constants::TEST_BASE_URL)
            ->apiKey("test_api_key")
            ->apiSecret("test_api_secret")
            ->build();

        $this->assertEquals(50, $defaultConfig->maxConnections);
        $this->assertEquals(10, $defaultConfig->maxConnectionsPerHost);
        $this->assertEquals(30, $defaultConfig->keepAliveTimeout);
        $this->assertEquals(60, $defaultConfig->requestTimeout);
        $this->assertEquals(3, $defaultConfig->retryMaxAttempts);
        $this->assertEquals(true, $defaultConfig->enableLogging);
    }
}
