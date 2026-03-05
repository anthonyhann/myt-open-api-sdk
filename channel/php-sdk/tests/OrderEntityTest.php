<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\CreateOrderReq;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderInfo;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderCustomerInfo;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderGoodsInfo;
use Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderFeeInfo;

class OrderEntityTest extends TestCase
{
    public function testCreateOrder()
    {
        // 创建新订单请求
        $req = new CreateOrderReq();
        
        // 设置订单信息
        $req->Order->OrderId = 'test_order_id';
        $req->Order->OrderSn = 12345678;
        $req->Order->ShopId = 'test_shop_id';
        $req->Order->ShopName = '测试店铺';
        $req->Order->Category = 'yinpin';
        $req->Order->IsPreOrder = false;
        $req->Order->TotalPrice = 1000;
        $req->Order->BalancePrice = 800;
        $req->Order->CreateTime = time();
        $req->Order->DeliveryTime = time() + 3600;
        $req->Order->DeliveryType = 0;
        $req->Order->IsPicker = false;
        $req->Order->UserRemark = '测试备注';
        
        // 设置顾客信息
        $req->OrderCustomer->RealName = '张三';
        $req->OrderCustomer->Phone = '13800138000';
        $req->OrderCustomer->SecretPhone = '138****8000';
        $req->OrderCustomer->Address = '北京市朝阳区测试地址';
        $req->OrderCustomer->Longitude = '116.397128';
        $req->OrderCustomer->Latitude = '39.916527';
        
        // 设置订单费用
        $req->Order->OrderFee->TotalFee = 1000;
        $req->Order->OrderFee->SendFee = 200;
        $req->Order->OrderFee->PackageFee = 50;
        $req->Order->OrderFee->DiscountFee = 250;
        $req->Order->OrderFee->ShopFee = 750;
        $req->Order->OrderFee->UserFee = 800;
        $req->Order->OrderFee->PayType = 2;
        
        // 添加商品
        $goods = new OrderGoodsInfo();
        $goods->GoodsId = 'test_goods_id';
        $goods->GoodsName = '测试商品';
        $goods->Thumb = 'https://example.com/test.jpg';
        $goods->SkuId = 'test_sku_id';
        $goods->SkuName = '测试规格';
        $goods->Unit = '份';
        $goods->Weight = 500;
        $goods->Number = 2;
        $goods->GoodsPrice = 500;
        $goods->GoodsTotalFee = 1000;
        $goods->TotalFee = 950;
        
        $req->Order->AddGoods($goods);
        
        // 验证订单数据结构
        $this->assertEquals('test_order_id', $req->Order->OrderId);
        $this->assertEquals('测试店铺', $req->Order->ShopName);
        $this->assertEquals('yinpin', $req->Order->Category);
        $this->assertEquals(2, count($req->Order->OrderGoods));
        $this->assertEquals('张三', $req->OrderCustomer->RealName);
        $this->assertEquals('13800138000', $req->OrderCustomer->Phone);
        $this->assertEquals(1000, $req->Order->OrderFee->TotalFee);
    }
    
    public function testOrderCanceledReq()
    {
        // 测试订单取消请求
        $req = new \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderCanceledReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->reason = '用户取消';
        $req->reason_code = 1;
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('用户取消', $req->reason);
        $this->assertEquals(1, $req->reason_code);
        $this->assertNotNull($req->update_time);
    }
    
    public function testOrderConfirmedReq()
    {
        // 测试订单确认请求
        $req = new \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderConfirmedReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertNotNull($req->update_time);
    }
    
    public function testOrderDoneReq()
    {
        // 测试订单完成请求
        $req = new \Maiyatian\Channel\PhpSdk\Models\Sender\Entity\Order\OrderDoneReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertNotNull($req->update_time);
    }
}
