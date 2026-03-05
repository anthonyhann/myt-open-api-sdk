<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;

// Auth entities
use MytChannel\Models\Receiver\Entity\Auth\CreateCodeReq;
use MytChannel\Models\Receiver\Entity\Auth\CreateCodeResp;

// Order entities
use MytChannel\Models\Receiver\Entity\Order\OrderListReq;
use MytChannel\Models\Receiver\Entity\Order\OrderListResp;
use MytChannel\Models\Receiver\Entity\Order\OrderDetailReq;
use MytChannel\Models\Receiver\Entity\Order\OrderDetailResp;
use MytChannel\Models\Receiver\Entity\Order\OrderConfirmReq;
use MytChannel\Models\Receiver\Entity\Order\OrderConfirmResp;
use MytChannel\Models\Receiver\Entity\Order\OrderRemindReplyReq;
use MytChannel\Models\Receiver\Entity\Order\OrderRemindReplyResp;
use MytChannel\Models\Receiver\Entity\Order\OrderRejectRefundReq;
use MytChannel\Models\Receiver\Entity\Order\OrderRejectRefundResp;
use MytChannel\Models\Receiver\Entity\Order\OrderAgreeRefundReq;
use MytChannel\Models\Receiver\Entity\Order\OrderAgreeRefundResp;
use MytChannel\Models\Receiver\Entity\Order\OrderChangeReq;
use MytChannel\Models\Receiver\Entity\Order\OrderChangeResp;
use MytChannel\Models\Receiver\Entity\Order\RiderLocationReq;
use MytChannel\Models\Receiver\Entity\Order\RiderLocationResp;
use MytChannel\Models\Receiver\Entity\Order\MealPickingReq;
use MytChannel\Models\Receiver\Entity\Order\MealPickingResp;
use MytChannel\Models\Receiver\Entity\Order\DeliveryChangeReq;
use MytChannel\Models\Receiver\Entity\Order\DeliveryChangeResp;

// Shop entities
use MytChannel\Models\Receiver\Entity\Shop\ShopItem;
use MytChannel\Models\Receiver\Entity\Shop\ShopListReq;
use MytChannel\Models\Receiver\Entity\Shop\ShopListResp;
use MytChannel\Models\Receiver\Entity\Shop\ShopDetailReq;
use MytChannel\Models\Receiver\Entity\Shop\ShopDetailResp;
use MytChannel\Models\Receiver\Entity\Shop\PrinterItem;
use MytChannel\Models\Receiver\Entity\Shop\ShopPrinterListReq;
use MytChannel\Models\Receiver\Entity\Shop\ShopPrinterListResp;
use MytChannel\Models\Receiver\Entity\Shop\TokenUnbindReq;

class ReceiverEntityTest extends TestCase
{
    // Auth entity tests
    public function testCreateCodeReq()
    {
        $req = new CreateCodeReq();
        $req->app_key = 'test_app_key';
        $req->redirect_uri = 'https://example.com/callback';
        $req->shop_id = 'test_shop_id';
        $req->state = 'test_state';
        $req->view = 'h5';
        
        $this->assertEquals('test_app_key', $req->app_key);
        $this->assertEquals('code', $req->response_type); // default value
        $this->assertEquals('h5', $req->view);
        $this->assertEquals('https://example.com/callback', $req->redirect_uri);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('test_state', $req->state);
    }
    
    public function testCreateCodeResp()
    {
        $resp = new CreateCodeResp();
        $resp->code = 'test_code';
        $resp->state = 'test_state';
        
        $this->assertEquals('test_code', $resp->code);
        $this->assertEquals('test_state', $resp->state);
    }
    
    // Order entity tests
    public function testOrderListReq()
    {
        $req = new OrderListReq();
        $req->shop_id = 'test_shop_id';
        $req->start_time = 1609459200;
        $req->end_time = 1610064000;
        $req->page = 1;
        $req->page_size = 20;
        
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals(1609459200, $req->start_time);
        $this->assertEquals(1610064000, $req->end_time);
        $this->assertEquals(1, $req->page);
        $this->assertEquals(20, $req->page_size);
    }
    
    public function testOrderListResp()
    {
        $resp = new OrderListResp();
        $resp->page = 1;
        $resp->total = 100;
        $resp->total_page = 5;
        $resp->is_last = false;
        
        $this->assertEquals(1, $resp->page);
        $this->assertEquals(100, $resp->total);
        $this->assertEquals(5, $resp->total_page);
        $this->assertEquals(false, $resp->is_last);
        $this->assertIsArray($resp->data);
        $this->assertEmpty($resp->data);
    }
    
    public function testOrderDetailReq()
    {
        $req = new OrderDetailReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
    }
    
    public function testOrderConfirmReq()
    {
        $req = new OrderConfirmReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->myt_order_id = 'myt_test_order_id';
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('myt_test_order_id', $req->myt_order_id);
    }
    
    public function testOrderRemindReplyReq()
    {
        $req = new OrderRemindReplyReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->remind_content = '测试催单内容';
        $req->remind_time = time();
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('测试催单内容', $req->remind_content);
        $this->assertNotNull($req->remind_time);
    }
    
    public function testOrderRejectRefundReq()
    {
        $req = new OrderRejectRefundReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->refund_id = 'test_refund_id';
        $req->refuse_reason = '测试拒绝原因';
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('test_refund_id', $req->refund_id);
        $this->assertEquals('测试拒绝原因', $req->refuse_reason);
    }
    
    public function testOrderAgreeRefundReq()
    {
        $req = new OrderAgreeRefundReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->refund_id = 'test_refund_id';
        $req->refund_amount = 50.5;
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('test_refund_id', $req->refund_id);
        $this->assertEquals(50.5, $req->refund_amount);
    }
    
    public function testOrderChangeReq()
    {
        $req = new OrderChangeReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->change_type = 'price';
        $req->change_content = ['new_price' => 100];
        $req->change_time = time();
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('price', $req->change_type);
        $this->assertEquals(['new_price' => 100], $req->change_content);
        $this->assertNotNull($req->change_time);
    }
    
    public function testRiderLocationReq()
    {
        $req = new RiderLocationReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->rider_id = 'test_rider_id';
        $req->rider_name = '测试骑手';
        $req->rider_phone = '13800138000';
        $req->latitude = 39.916527;
        $req->longitude = 116.397128;
        $req->update_time = time();
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('test_rider_id', $req->rider_id);
        $this->assertEquals('测试骑手', $req->rider_name);
        $this->assertEquals('13800138000', $req->rider_phone);
        $this->assertEquals(39.916527, $req->latitude);
        $this->assertEquals(116.397128, $req->longitude);
        $this->assertNotNull($req->update_time);
    }
    
    public function testMealPickingReq()
    {
        $req = new MealPickingReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->picking_code = '123456';
        $req->update_time = time();
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('123456', $req->picking_code);
        $this->assertNotNull($req->update_time);
    }
    
    public function testDeliveryChangeReq()
    {
        $req = new DeliveryChangeReq();
        $req->order_id = 'test_order_id';
        $req->shop_id = 'test_shop_id';
        $req->delivery_status = 'delivered';
        $req->status_desc = '已送达';
        $req->change_time = time();
        
        $this->assertEquals('test_order_id', $req->order_id);
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('delivered', $req->delivery_status);
        $this->assertEquals('已送达', $req->status_desc);
        $this->assertNotNull($req->change_time);
    }
    
    // Shop entity tests
    public function testShopItem()
    {
        $item = new ShopItem();
        $item->shop_id = 'test_shop_id';
        $item->name = '测试店铺';
        $item->phone = '13800138000';
        $item->province = '北京市';
        $item->city = '北京市';
        $item->address = '测试地址';
        $item->latitude = '39.916527';
        $item->longitude = '116.397128';
        
        $this->assertEquals('test_shop_id', $item->shop_id);
        $this->assertEquals('测试店铺', $item->name);
        $this->assertEquals('13800138000', $item->phone);
        $this->assertEquals('北京市', $item->province);
        $this->assertEquals('北京市', $item->city);
        $this->assertEquals('测试地址', $item->address);
        $this->assertEquals('39.916527', $item->latitude);
        $this->assertEquals('116.397128', $item->longitude);
    }
    
    public function testShopListReq()
    {
        $req = new ShopListReq();
        $req->page = 1;
        $req->page_size = 20;
        $req->scroll_id = 'test_scroll_id';
        
        $this->assertEquals(1, $req->page);
        $this->assertEquals(20, $req->page_size);
        $this->assertEquals('test_scroll_id', $req->scroll_id);
    }
    
    public function testShopListResp()
    {
        $resp = new ShopListResp();
        $resp->page = 1;
        $resp->total = 100;
        $resp->total_page = 5;
        $resp->is_last = false;
        $resp->scroll_id = 'test_scroll_id';
        
        // Add a shop item
        $shopItem = new ShopItem();
        $shopItem->shop_id = 'test_shop_id';
        $shopItem->name = '测试店铺';
        $resp->data[] = $shopItem;
        
        $this->assertEquals(1, $resp->page);
        $this->assertEquals(100, $resp->total);
        $this->assertEquals(5, $resp->total_page);
        $this->assertEquals(false, $resp->is_last);
        $this->assertEquals('test_scroll_id', $resp->scroll_id);
        $this->assertEquals(1, count($resp->data));
        $this->assertEquals('test_shop_id', $resp->data[0]->shop_id);
        $this->assertEquals('测试店铺', $resp->data[0]->name);
    }
    
    public function testShopDetailReq()
    {
        $req = new ShopDetailReq();
        $req->shop_id = 'test_shop_id';
        
        $this->assertEquals('test_shop_id', $req->shop_id);
    }
    
    public function testShopDetailResp()
    {
        $resp = new ShopDetailResp();
        $resp->shop_id = 'test_shop_id';
        $resp->name = '测试店铺';
        $resp->phone = '13800138000';
        $resp->province = '北京市';
        $resp->city = '北京市';
        $resp->address = '测试地址';
        $resp->latitude = '39.916527';
        $resp->longitude = '116.397128';
        
        $this->assertEquals('test_shop_id', $resp->shop_id);
        $this->assertEquals('测试店铺', $resp->name);
        $this->assertEquals('13800138000', $resp->phone);
        $this->assertEquals('北京市', $resp->province);
        $this->assertEquals('北京市', $resp->city);
        $this->assertEquals('测试地址', $resp->address);
        $this->assertEquals('39.916527', $resp->latitude);
        $this->assertEquals('116.397128', $resp->longitude);
    }
    
    public function testPrinterItem()
    {
        $item = new PrinterItem();
        $item->shop_id = 'test_shop_id';
        $item->brand = 'epson';
        $item->name = '测试打印机';
        $item->machine_code = 'test_machine_code';
        $item->machine_sign = 'test_machine_sign';
        $item->print_number = 1;
        $item->width = '80mm';
        
        $this->assertEquals('test_shop_id', $item->shop_id);
        $this->assertEquals('epson', $item->brand);
        $this->assertEquals('测试打印机', $item->name);
        $this->assertEquals('test_machine_code', $item->machine_code);
        $this->assertEquals('test_machine_sign', $item->machine_sign);
        $this->assertEquals(1, $item->print_number);
        $this->assertEquals('80mm', $item->width);
    }
    
    public function testShopPrinterListReq()
    {
        $req = new ShopPrinterListReq();
        $req->shop_id = 'test_shop_id';
        $req->page = 1;
        $req->page_size = 20;
        $req->scroll_id = 'test_scroll_id';
        
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals(1, $req->page);
        $this->assertEquals(20, $req->page_size);
        $this->assertEquals('test_scroll_id', $req->scroll_id);
    }
    
    public function testShopPrinterListResp()
    {
        $resp = new ShopPrinterListResp();
        $resp->page = 1;
        $resp->total = 10;
        $resp->total_page = 1;
        $resp->is_last = true;
        $resp->scroll_id = 'test_scroll_id';
        
        // Add a printer item
        $printerItem = new PrinterItem();
        $printerItem->shop_id = 'test_shop_id';
        $printerItem->brand = 'epson';
        $resp->data[] = $printerItem;
        
        $this->assertEquals(1, $resp->page);
        $this->assertEquals(10, $resp->total);
        $this->assertEquals(1, $resp->total_page);
        $this->assertEquals(true, $resp->is_last);
        $this->assertEquals('test_scroll_id', $resp->scroll_id);
        $this->assertEquals(1, count($resp->data));
        $this->assertEquals('test_shop_id', $resp->data[0]->shop_id);
        $this->assertEquals('epson', $resp->data[0]->brand);
    }
    
    public function testTokenUnbindReq()
    {
        $req = new TokenUnbindReq();
        $req->shop_id = 'test_shop_id';
        $req->token = 'test_token';
        
        $this->assertEquals('test_shop_id', $req->shop_id);
        $this->assertEquals('test_token', $req->token);
    }
}
