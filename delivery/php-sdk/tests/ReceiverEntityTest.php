<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/15
 * @PackageName: tests
 * @ClassName: ReceiverEntityTest
 * @Description: 麦芽田配送开放平台SDK - 配送服务接收端实体单元测试
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\SDK\models\receiver\entity\account\BalanceReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\account\BalanceResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\account\RechargeUrlReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\account\RechargeUrlResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\account\TokenUnbindReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\account\TokenUnbindResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\auth\CreateCodeReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\CreateThirdShopReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\CreateThirdShopResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\MultiRiderLocationsReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\MultiRiderLocationsResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\RiderLocationReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\RiderLocationResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\QueryInfoReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\QueryInfoResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\RiderTrackPoint;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\RiderTrackPointsReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\RiderTrackPointsResp;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\ServicePkg;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\ServicePkgListReq;
use Maiyatian\Delivery\SDK\models\receiver\entity\delivery\ServicePkgListResp;

class ReceiverEntityTest extends TestCase
{
    /**
     * 测试账号余额实体
     */
    public function testBalanceEntities()
    {
        // 测试请求实体
        $balanceReq = new BalanceReq();
        $balanceReq->shop_id = "shop_123";
        $balanceReq->token = "test_token";
        
        $this->assertEquals("shop_123", $balanceReq->shop_id);
        $this->assertEquals("test_token", $balanceReq->token);
        
        // 测试响应实体
        $balanceResp = new BalanceResp();
        $balanceResp->balance = 10000;
        
        $this->assertEquals(10000, $balanceResp->balance);
        $this->assertIsInt($balanceResp->at_time);
        $this->assertGreaterThan(0, $balanceResp->at_time);
    }
    
    /**
     * 测试充值链接实体
     */
    public function testRechargeUrlEntities()
    {
        // 测试请求实体
        $rechargeUrlReq = new RechargeUrlReq();
        $rechargeUrlReq->shop_id = "shop_123";
        
        $this->assertEquals("shop_123", $rechargeUrlReq->shop_id);
        
        // 测试响应实体
        $rechargeUrlResp = new RechargeUrlResp();
        $rechargeUrlResp->recharge_url = "https://example.com/recharge";
        
        $this->assertEquals("https://example.com/recharge", $rechargeUrlResp->recharge_url);
        $this->assertIsInt($rechargeUrlResp->at_time);
        $this->assertGreaterThan(0, $rechargeUrlResp->at_time);
    }
    
    /**
     * 测试token解绑实体
     */
    public function testTokenUnbindEntities()
    {
        // 测试请求实体
        $tokenUnbindReq = new TokenUnbindReq();
        $tokenUnbindReq->shop_id = "shop_123";
        $tokenUnbindReq->token = "test_token";
        
        $this->assertEquals("shop_123", $tokenUnbindReq->shop_id);
        $this->assertEquals("test_token", $tokenUnbindReq->token);
        
        // 测试响应实体
        $tokenUnbindResp = new TokenUnbindResp();
        $tokenUnbindResp->shop_id = "shop_123";
        $tokenUnbindResp->token = "test_token";
        
        $this->assertEquals("shop_123", $tokenUnbindResp->shop_id);
        $this->assertEquals("test_token", $tokenUnbindResp->token);
    }
    
    /**
     * 测试授权码实体
     */
    public function testCreateCodeEntity()
    {
        // 测试请求实体
        $createCodeReq = new CreateCodeReq();
        $createCodeReq->redirect_uri = "https://example.com/callback";
        $createCodeReq->code = "test_code_123";
        $createCodeReq->state = "test_state";
        
        $this->assertEquals("code", $createCodeReq->response_type);
        $this->assertEquals("web", $createCodeReq->view);
        $this->assertEquals("https://example.com/callback", $createCodeReq->redirect_uri);
        $this->assertEquals("test_code_123", $createCodeReq->code);
        $this->assertEquals("test_state", $createCodeReq->state);
        
        // 测试自定义view
        $createCodeReq->view = "mobile";
        $this->assertEquals("mobile", $createCodeReq->view);
    }
    
    /**
     * 测试创建三方门店实体
     */
    public function testCreateThirdShopEntities()
    {
        // 测试请求实体
        $createThirdShopReq = new CreateThirdShopReq();
        $createThirdShopReq->shop_id = 12345;
        $createThirdShopReq->name = "测试门店";
        $createThirdShopReq->phone = "13800138000";
        $createThirdShopReq->province = "北京市";
        $createThirdShopReq->province_code = 110000;
        $createThirdShopReq->city = "北京市";
        $createThirdShopReq->city_code = 110100;
        $createThirdShopReq->district = "朝阳区";
        $createThirdShopReq->district_code = 110105;
        $createThirdShopReq->address = "测试地址";
        $createThirdShopReq->map_type = "1";
        $createThirdShopReq->category = "xiaochi";
        $createThirdShopReq->longitude = "116.404";
        $createThirdShopReq->latitude = "39.915";
        
        $this->assertEquals(12345, $createThirdShopReq->shop_id);
        $this->assertEquals("测试门店", $createThirdShopReq->name);
        $this->assertEquals("13800138000", $createThirdShopReq->phone);
        $this->assertEquals("北京市", $createThirdShopReq->province);
        $this->assertEquals(110000, $createThirdShopReq->province_code);
        $this->assertEquals("116.404", $createThirdShopReq->longitude);
        $this->assertEquals("39.915", $createThirdShopReq->latitude);
        
        // 测试响应实体
        $createThirdShopResp = new CreateThirdShopResp();
        $createThirdShopResp->store_id = "store_123";
        
        $this->assertEquals("store_123", $createThirdShopResp->store_id);
    }
    
    /**
     * 测试批量骑手位置实体
     */
    public function testMultiRiderLocationsEntities()
    {
        // 测试单个骑手位置请求
        $riderLocationReq1 = new RiderLocationReq();
        $riderLocationReq1->order_no = "order_123";
        $riderLocationReq1->source_order_no = "source_order_123";
        
        $riderLocationReq2 = new RiderLocationReq();
        $riderLocationReq2->order_no = "order_456";
        $riderLocationReq2->source_order_no = "source_order_456";
        
        // 测试批量请求实体
        $multiRiderLocationsReq = new MultiRiderLocationsReq();
        $multiRiderLocationsReq->orders = [$riderLocationReq1, $riderLocationReq2];
        
        $this->assertCount(2, $multiRiderLocationsReq->orders);
        $this->assertEquals("order_123", $multiRiderLocationsReq->orders[0]->order_no);
        $this->assertEquals("order_456", $multiRiderLocationsReq->orders[1]->order_no);
        
        // 测试单个骑手位置响应
        $riderLocationResp1 = new RiderLocationResp();
        $riderLocationResp1->source_order_no = "source_order_123";
        $riderLocationResp1->order_no = "order_123";
        $riderLocationResp1->rider_name = "骑手A";
        $riderLocationResp1->rider_phone = "13800138000";
        $riderLocationResp1->longitude = "116.404";
        $riderLocationResp1->latitude = "39.915";
        
        // 测试批量响应实体
        $multiRiderLocationsResp = new MultiRiderLocationsResp();
        $multiRiderLocationsResp->data = [$riderLocationResp1];
        
        $this->assertCount(1, $multiRiderLocationsResp->data);
        $this->assertEquals("骑手A", $multiRiderLocationsResp->data[0]->rider_name);
        $this->assertEquals("13800138000", $multiRiderLocationsResp->data[0]->rider_phone);
        $this->assertIsInt($multiRiderLocationsResp->data[0]->at_time);
    }
    
    /**
     * 测试查询配送详情实体
     */
    public function testQueryInfoEntities()
    {
        // 测试请求实体
        $queryInfoReq = new QueryInfoReq();
        $queryInfoReq->order_no = "order_123";
        $queryInfoReq->source_order_no = "source_order_123";
        $queryInfoReq->mobile = "13800138000";
        $queryInfoReq->type = 1;
        $queryInfoReq->shipper_code = "shipper_123";
        
        $this->assertEquals("order_123", $queryInfoReq->order_no);
        $this->assertEquals("source_order_123", $queryInfoReq->source_order_no);
        $this->assertEquals("13800138000", $queryInfoReq->mobile);
        $this->assertEquals(1, $queryInfoReq->type);
        $this->assertEquals("shipper_123", $queryInfoReq->shipper_code);
        
        // 测试响应实体
        $queryInfoResp = new QueryInfoResp();
        $queryInfoResp->order_no = "order_123";
        $queryInfoResp->source_order_no = "source_order_123";
        $queryInfoResp->status = "DONE";
        $queryInfoResp->status_name = "已完成";
        $queryInfoResp->pay_amount = 850;
        $queryInfoResp->coupon = 0;
        $queryInfoResp->premium = 0;
        $queryInfoResp->tips = 0;
        $queryInfoResp->distance = 1000;
        $queryInfoResp->create_time = time();
        $queryInfoResp->accept_time = time() + 60;
        $queryInfoResp->fetch_time = time() + 300;
        $queryInfoResp->finish_time = time() + 600;
        $queryInfoResp->cancel_time = 0;
        $queryInfoResp->rider_name = "骑手A";
        $queryInfoResp->rider_phone = "13800138000";
        $queryInfoResp->longitude = "116.404";
        $queryInfoResp->latitude = "39.915";
        $queryInfoResp->is_transship = false;
        
        $this->assertEquals("order_123", $queryInfoResp->order_no);
        $this->assertEquals("DONE", $queryInfoResp->status);
        $this->assertEquals(850, $queryInfoResp->pay_amount);
        $this->assertEquals("骑手A", $queryInfoResp->rider_name);
        $this->assertEquals(false, $queryInfoResp->is_transship);
    }
    
    /**
     * 测试骑手轨迹点实体
     */
    public function testRiderTrackPointsEntities()
    {
        // 测试请求实体
        $riderTrackPointsReq = new RiderTrackPointsReq();
        $riderTrackPointsReq->order_no = "order_123";
        $riderTrackPointsReq->source_order_no = "source_order_123";
        $riderTrackPointsReq->start_time = time() - 3600;
        $riderTrackPointsReq->end_time = time();
        
        $this->assertEquals("order_123", $riderTrackPointsReq->order_no);
        $this->assertEquals("source_order_123", $riderTrackPointsReq->source_order_no);
        $this->assertLessThanOrEqual(time(), $riderTrackPointsReq->end_time);
        
        // 测试轨迹点实体
        $riderTrackPoint = new RiderTrackPoint();
        $riderTrackPoint->rider_name = "骑手A";
        $riderTrackPoint->rider_phone = "13800138000";
        $riderTrackPoint->longitude = "116.404";
        $riderTrackPoint->latitude = "39.915";
        
        $this->assertEquals("骑手A", $riderTrackPoint->rider_name);
        $this->assertEquals("13800138000", $riderTrackPoint->rider_phone);
        $this->assertEquals("116.404", $riderTrackPoint->longitude);
        $this->assertEquals("39.915", $riderTrackPoint->latitude);
        $this->assertIsInt($riderTrackPoint->at_time);
        
        // 测试响应实体
        $riderTrackPointsResp = new RiderTrackPointsResp();
        $riderTrackPointsResp->source_order_no = "source_order_123";
        $riderTrackPointsResp->order_no = "order_123";
        $riderTrackPointsResp->rider_track_points = [$riderTrackPoint];
        
        $this->assertEquals("source_order_123", $riderTrackPointsResp->source_order_no);
        $this->assertEquals("order_123", $riderTrackPointsResp->order_no);
        $this->assertCount(1, $riderTrackPointsResp->rider_track_points);
    }
    
    /**
     * 测试服务包列表实体
     */
    public function testServicePkgListEntities()
    {
        // 测试请求实体
        $servicePkgListReq = new ServicePkgListReq();
        $servicePkgListReq->shop_id = "shop_123";
        $servicePkgListReq->city = "110100";
        
        $this->assertEquals("shop_123", $servicePkgListReq->shop_id);
        $this->assertEquals("110100", $servicePkgListReq->city);
        
        // 测试服务包实体
        $servicePkg1 = new ServicePkg();
        $servicePkg1->name = "经济配送";
        $servicePkg1->service_pkg = "base";
        
        $servicePkg2 = new ServicePkg();
        $servicePkg2->name = "专人直送";
        $servicePkg2->service_pkg = "direct";
        
        // 测试响应实体
        $servicePkgListResp = new ServicePkgListResp();
        $servicePkgListResp->data = [$servicePkg1, $servicePkg2];
        
        $this->assertCount(2, $servicePkgListResp->data);
        $this->assertEquals("经济配送", $servicePkgListResp->data[0]->name);
        $this->assertEquals("base", $servicePkgListResp->data[0]->service_pkg);
        $this->assertEquals("专人直送", $servicePkgListResp->data[1]->name);
        $this->assertEquals("direct", $servicePkgListResp->data[1]->service_pkg);
    }
}
