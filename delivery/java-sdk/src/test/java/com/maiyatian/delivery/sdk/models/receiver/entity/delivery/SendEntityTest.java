/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maiyatian.delivery.sdk.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SendEntity 单元测试
 * <p>
 * 测试配送下单实体类的JSON序列化和反序列化功能
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class SendEntityTest {

    @Test
    void testSendEntitySerialization() throws IOException {
        // 创建请求对象
        SendEntity.Req req = new SendEntity.Req();
        req.setShopId("123");
        req.setPreOrder(false);
        req.setTransship(false);
        req.setDelayDeliveryTime(1665487116L);
        req.setExpectFinishTime(1665490716L);
        req.setTips(100);
        req.setSourceOrderNo("11667336008123456");
        req.setRemark("备注");
        req.setExpType("1");
        req.setPickupStartTime(0L);
        req.setPickupEndTime(0L);
        req.setCustid("xxxxxxxxx");
        req.setPayMode(0);
        
        // 创建发货人信息
        SendEntity.Sender sender = new SendEntity.Sender();
        sender.setName("发货门店名称");
        sender.setPhone("发货门店电话");
        sender.setAddress("发货门店地址");
        sender.setAddressDetail("发货门店地址详情");
        sender.setLongitude("103.11111");
        sender.setLatitude("90.1123123");
        sender.setProvinceCode("110100");
        sender.setCityCode("110100");
        sender.setDistrictCode("110100");
        req.setSender(sender);
        
        // 创建收货人信息
        SendEntity.Receiver receiver = new SendEntity.Receiver();
        receiver.setName("收货人");
        receiver.setPhone("收货人电话");
        receiver.setAddress("收货人地址");
        receiver.setAddressDetail("收货人地址详情");
        receiver.setLongitude("103.11111");
        receiver.setLatitude("90.1123123");
        receiver.setProvinceCode("110100");
        receiver.setCityCode("110100");
        receiver.setDistrictCode("110100");
        req.setReceiver(receiver);
        
        // 创建订单信息
        SendEntity.OrderInfo orderInfo = new SendEntity.OrderInfo();
        orderInfo.setSn(101);
        orderInfo.setFullSn("101_1230");
        orderInfo.setSource("meituan");
        orderInfo.setChannelTag("meituan");
        orderInfo.setChannelName("渠道名称");
        orderInfo.setSourceNo("1667336008123456");
        orderInfo.setCategory("xiaochi");
        orderInfo.setWeight(1);
        orderInfo.setTotalFee(1300);
        orderInfo.setPaidFee(1200);
        orderInfo.setIsFromDoor(1);
        orderInfo.setIsToDoor(1);
        
        // 创建商品列表
        List<SendEntity.OrderInfo.Goods> goodsList = new ArrayList<>();
        SendEntity.OrderInfo.Goods goods = new SendEntity.OrderInfo.Goods();
        goods.setName("商品名称");
        goods.setNumber(1);
        goods.setPrice(100);
        goodsList.add(goods);
        orderInfo.setGoodsList(goodsList);
        
        req.setOrderInfo(orderInfo);
        
        // 序列化并反序列化
        String json = JsonUtils.toJson(req);
        SendEntity.Req deserialized = JsonUtils.fromJson(json, SendEntity.Req.class);
        
        // 验证字段值
        assertEquals(req.getShopId(), deserialized.getShopId());
        assertEquals(req.isPreOrder(), deserialized.isPreOrder());
        assertEquals(req.isTransship(), deserialized.isTransship());
        assertEquals(req.getTips(), deserialized.getTips());
        assertEquals(req.getSourceOrderNo(), deserialized.getSourceOrderNo());
        assertEquals(req.getSender().getName(), deserialized.getSender().getName());
        assertEquals(req.getReceiver().getName(), deserialized.getReceiver().getName());
        assertEquals(req.getOrderInfo().getSn(), deserialized.getOrderInfo().getSn());
    }
    
    @Test
    void testSendEntityResponseDeserialization() throws IOException {
        // 模拟响应JSON - 使用camelCase字段名，与Java类保持一致
        String json = "{\"orderNo\":\"123434543454\",\"sourceOrderNo\":\"DH3123123123123\",\"payAmount\":500,\"coupon\":100,\"distance\":1200}";
        
        // 反序列化
        SendEntity.Resp resp = JsonUtils.fromJson(json, SendEntity.Resp.class);
        
        // 验证响应
        assertEquals("123434543454", resp.getOrderNo());
        assertEquals("DH3123123123123", resp.getSourceOrderNo());
        assertEquals(500, resp.getPayAmount());
        assertEquals(100, resp.getCoupon());
        assertEquals(1200, resp.getDistance());
    }
}