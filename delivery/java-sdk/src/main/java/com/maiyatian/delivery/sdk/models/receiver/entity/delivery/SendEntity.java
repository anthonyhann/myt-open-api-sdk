/*
 * 麦芽田配送开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.delivery.sdk.models.receiver.entity.delivery;

import java.util.List;

/**
 * 配送下单实体
 * <p>
 * 用于麦芽田请求三方服务进行配送下单，command值为send，是必接接口
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public class SendEntity {
    
    /**
     * 配送下单请求参数
     * <p>
     * command: send
     * 必接: 是
     * 说明: 麦芽田平台主动调用三方配送服务创建配送订单
     */
    public static class Req {
        
        /**
         * 麦芽田方门店ID
         */
        private String shopId;
        
        /**
         * 是否预约单
         */
        private boolean isPreOrder;
        
        /**
         * 是否接驳单
         */
        private boolean isTransship;
        
        /**
         * 期望送达时间（单位：unix 时间戳，精确到秒）
         */
        private long delayDeliveryTime;
        
        /**
         * 期望开始时间（单位：unix 时间戳，精确到秒）
         */
        private long expectStartTime;
        
        /**
         * 期望完成时间（单位：unix 时间戳，精确到秒）
         */
        private long expectFinishTime;
        
        /**
         * 小费（单位：分）
         */
        private long tips;
        
        /**
         * 订单号
         */
        private String sourceOrderNo;
        
        /**
         * 用户备注
         */
        private String remark;
        
        /**
         * 快递类型 1: 非地图类快递; 2: 地图类快递
         */
        private String expType;
        
        /**
         * 快递预约上门揽件开始时间
         */
        private long pickupStartTime;
        
        /**
         * 快递预约上门揽件结束时间
         */
        private long pickupEndTime;
        
        /**
         * 门店ID
         */
        private String custid;
        
        /**
         * 运费付款方式，根据实际情况选择一种付款方式：
         * <ul>
         * <li>0-寄付现结</li>
         * <li>1-寄付月结</li>
         * <li>2-收方付</li>
         * <li>3-第三方付</li>
         * </ul>
         */
        private Integer payMode;
        
        /**
         * 订单业务标识（[]string类型）
         */
        private List<String> tags;
        
        /**
         * 计价单号，用于锁定计费
         */
        private String billingNo;
        
        /**
         * 发货人信息
         */
        private Sender sender;
        
        /**
         * 收货人信息
         */
        private Receiver receiver;
        
        /**
         * 订单信息
         */
        private OrderInfo orderInfo;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getShopId() {
            return shopId;
        }
        
        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
        
        public boolean isPreOrder() {
            return isPreOrder;
        }
        
        public void setPreOrder(boolean preOrder) {
            isPreOrder = preOrder;
        }
        
        public boolean isTransship() {
            return isTransship;
        }
        
        public void setTransship(boolean transship) {
            isTransship = transship;
        }
        
        public long getDelayDeliveryTime() {
            return delayDeliveryTime;
        }
        
        public void setDelayDeliveryTime(long delayDeliveryTime) {
            this.delayDeliveryTime = delayDeliveryTime;
        }
        
        public long getExpectStartTime() {
            return expectStartTime;
        }
        
        public void setExpectStartTime(long expectStartTime) {
            this.expectStartTime = expectStartTime;
        }
        
        public long getExpectFinishTime() {
            return expectFinishTime;
        }
        
        public void setExpectFinishTime(long expectFinishTime) {
            this.expectFinishTime = expectFinishTime;
        }
        
        public long getTips() {
            return tips;
        }
        
        public void setTips(long tips) {
            this.tips = tips;
        }
        
        public String getSourceOrderNo() {
            return sourceOrderNo;
        }
        
        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }
        
        public String getRemark() {
            return remark;
        }
        
        public void setRemark(String remark) {
            this.remark = remark;
        }
        
        public String getExpType() {
            return expType;
        }
        
        public void setExpType(String expType) {
            this.expType = expType;
        }
        
        public long getPickupStartTime() {
            return pickupStartTime;
        }
        
        public void setPickupStartTime(long pickupStartTime) {
            this.pickupStartTime = pickupStartTime;
        }
        
        public long getPickupEndTime() {
            return pickupEndTime;
        }
        
        public void setPickupEndTime(long pickupEndTime) {
            this.pickupEndTime = pickupEndTime;
        }
        
        public String getCustid() {
            return custid;
        }
        
        public void setCustid(String custid) {
            this.custid = custid;
        }
        
        public Integer getPayMode() {
            return payMode;
        }
        
        public void setPayMode(Integer payMode) {
            this.payMode = payMode;
        }
        
        public List<String> getTags() {
            return tags;
        }
        
        public void setTags(List<String> tags) {
            this.tags = tags;
        }
        
        public String getBillingNo() {
            return billingNo;
        }
        
        public void setBillingNo(String billingNo) {
            this.billingNo = billingNo;
        }
        
        public Sender getSender() {
            return sender;
        }
        
        public void setSender(Sender sender) {
            this.sender = sender;
        }
        
        public Receiver getReceiver() {
            return receiver;
        }
        
        public void setReceiver(Receiver receiver) {
            this.receiver = receiver;
        }
        
        public OrderInfo getOrderInfo() {
            return orderInfo;
        }
        
        public void setOrderInfo(OrderInfo orderInfo) {
            this.orderInfo = orderInfo;
        }
    }
    
    /**
     * 配送下单响应参数
     */
    public static class Resp {
        
        /**
         * 三方运单号
         */
        private String orderNo;
        
        /**
         * 麦芽田订单号
         */
        private String sourceOrderNo;
        
        /**
         * 运费（单位：分）
         */
        private long payAmount;
        
        /**
         * 优惠费用（单位：分，如遇没有可传0）
         */
        private long coupon;
        
        /**
         * 当前溢价（单位：分，如遇没有可传0）
         */
        private long premium;
        
        /**
         * 加费（单位：分，如遇没有可传0）
         */
        private long tips;
        
        /**
         * 配送距离（单位：米）
         */
        private long distance;
        
        /**
         * 重量（单位：克）
         */
        private int weight;
        
        /**
         * 超重（单位：克）
         */
        private int overweight;
        
        /**
         * 计费时间（单位：unix 时间戳，精确到秒）
         */
        private long atTime;
        
        /**
         * 期望送达时间（单位：unix 时间戳，精确到秒，如遇没有可传0）
         */
        private long expectTime;
        
        /**
         * 服务包内容
         */
        private String servicePkg;
        
        /**
         * 服务标签
         */
        private String serviceTag;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getOrderNo() {
            return orderNo;
        }
        
        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        
        public String getSourceOrderNo() {
            return sourceOrderNo;
        }
        
        public void setSourceOrderNo(String sourceOrderNo) {
            this.sourceOrderNo = sourceOrderNo;
        }
        
        public long getPayAmount() {
            return payAmount;
        }
        
        public void setPayAmount(long payAmount) {
            this.payAmount = payAmount;
        }
        
        public long getCoupon() {
            return coupon;
        }
        
        public void setCoupon(long coupon) {
            this.coupon = coupon;
        }
        
        public long getPremium() {
            return premium;
        }
        
        public void setPremium(long premium) {
            this.premium = premium;
        }
        
        public long getTips() {
            return tips;
        }
        
        public void setTips(long tips) {
            this.tips = tips;
        }
        
        public long getDistance() {
            return distance;
        }
        
        public void setDistance(long distance) {
            this.distance = distance;
        }
        
        public int getWeight() {
            return weight;
        }
        
        public void setWeight(int weight) {
            this.weight = weight;
        }
        
        public int getOverweight() {
            return overweight;
        }
        
        public void setOverweight(int overweight) {
            this.overweight = overweight;
        }
        
        public long getAtTime() {
            return atTime;
        }
        
        public void setAtTime(long atTime) {
            this.atTime = atTime;
        }
        
        public long getExpectTime() {
            return expectTime;
        }
        
        public void setExpectTime(long expectTime) {
            this.expectTime = expectTime;
        }
        
        public String getServicePkg() {
            return servicePkg;
        }
        
        public void setServicePkg(String servicePkg) {
            this.servicePkg = servicePkg;
        }
        
        public String getServiceTag() {
            return serviceTag;
        }
        
        public void setServiceTag(String serviceTag) {
            this.serviceTag = serviceTag;
        }
    }
    
    /**
     * 发货人信息
     */
    public static class Sender {
        
        /**
         * 发货门店名称
         */
        private String name;
        
        /**
         * 发货门店电话
         */
        private String phone;
        
        /**
         * 发货门店地址
         */
        private String address;
        
        /**
         * 发货门店地址详情
         */
        private String addressDetail;
        
        /**
         * 经度(国测局02标准，如高德)
         */
        private String longitude;
        
        /**
         * 纬度(国测局02标准，如高德)
         */
        private String latitude;
        
        /**
         * 行政区编码（省）
         */
        private String provinceCode;
        
        /**
         * 行政区编码（城市）
         */
        private String cityCode;
        
        /**
         * 行政区编码（县区）
         */
        private String districtCode;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getPhone() {
            return phone;
        }
        
        public void setPhone(String phone) {
            this.phone = phone;
        }
        
        public String getAddress() {
            return address;
        }
        
        public void setAddress(String address) {
            this.address = address;
        }
        
        public String getAddressDetail() {
            return addressDetail;
        }
        
        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }
        
        public String getLongitude() {
            return longitude;
        }
        
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
        
        public String getLatitude() {
            return latitude;
        }
        
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
        
        public String getProvinceCode() {
            return provinceCode;
        }
        
        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }
        
        public String getCityCode() {
            return cityCode;
        }
        
        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
        
        public String getDistrictCode() {
            return districtCode;
        }
        
        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }
    }
    
    /**
     * 收货人信息
     */
    public static class Receiver {
        
        /**
         * 收货人姓名
         */
        private String name;
        
        /**
         * 收货人电话
         */
        private String phone;
        
        /**
         * 收货人地址
         */
        private String address;
        
        /**
         * 收货人地址详情
         */
        private String addressDetail;
        
        /**
         * 经度(国测局02标准，如高德)
         */
        private String longitude;
        
        /**
         * 纬度(国测局02标准，如高德)
         */
        private String latitude;
        
        /**
         * 收货人行政区编码（省）
         */
        private String provinceCode;
        
        /**
         * 收货人行政区编码（城市）
         */
        private String cityCode;
        
        /**
         * 收货人行政区编码（县区）
         */
        private String districtCode;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getPhone() {
            return phone;
        }
        
        public void setPhone(String phone) {
            this.phone = phone;
        }
        
        public String getAddress() {
            return address;
        }
        
        public void setAddress(String address) {
            this.address = address;
        }
        
        public String getAddressDetail() {
            return addressDetail;
        }
        
        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }
        
        public String getLongitude() {
            return longitude;
        }
        
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
        
        public String getLatitude() {
            return latitude;
        }
        
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
        
        public String getProvinceCode() {
            return provinceCode;
        }
        
        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }
        
        public String getCityCode() {
            return cityCode;
        }
        
        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
        
        public String getDistrictCode() {
            return districtCode;
        }
        
        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }
    }
    
    /**
     * 订单信息
     */
    public static class OrderInfo {
        
        /**
         * 订单流水号
         */
        private long sn;
        
        /**
         * 完整订单流水号
         */
        private String fullSn;
        
        /**
         * 订单来源标识（来源枚举见附录）
         */
        private String source;
        
        /**
         * 订单渠道tag
         */
        private String channelTag;
        
        /**
         * 订单店铺名称
         */
        private String channelName;
        
        /**
         * 渠道订单号
         */
        private String sourceNo;
        
        /**
         * 店铺分类(麦芽田枚举)
         */
        private String category;
        
        /**
         * 商品重量（单位：千克）
         */
        private int weight;
        
        /**
         * 合计费用（折扣后）（单位：分）
         */
        private long totalFee;
        
        /**
         * 实付金额(分)
         */
        private long paidFee;
        
        /**
         * 是否上门取件,0 不上门取件 1 上门取件
         */
        private int isFromDoor;
        
        /**
         * 是否送件上门,0 不送件上门 1 送件上门
         */
        private int isToDoor;
        
        /**
         * 商品列表
         */
        private List<Goods> goodsList;
        
        // ==================== Getter 和 Setter 方法 ====================
        
        public long getSn() {
            return sn;
        }
        
        public void setSn(long sn) {
            this.sn = sn;
        }
        
        public String getFullSn() {
            return fullSn;
        }
        
        public void setFullSn(String fullSn) {
            this.fullSn = fullSn;
        }
        
        public String getSource() {
            return source;
        }
        
        public void setSource(String source) {
            this.source = source;
        }
        
        public String getChannelTag() {
            return channelTag;
        }
        
        public void setChannelTag(String channelTag) {
            this.channelTag = channelTag;
        }
        
        public String getChannelName() {
            return channelName;
        }
        
        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }
        
        public String getSourceNo() {
            return sourceNo;
        }
        
        public void setSourceNo(String sourceNo) {
            this.sourceNo = sourceNo;
        }
        
        public String getCategory() {
            return category;
        }
        
        public void setCategory(String category) {
            this.category = category;
        }
        
        public int getWeight() {
            return weight;
        }
        
        public void setWeight(int weight) {
            this.weight = weight;
        }
        
        public long getTotalFee() {
            return totalFee;
        }
        
        public void setTotalFee(long totalFee) {
            this.totalFee = totalFee;
        }
        
        public long getPaidFee() {
            return paidFee;
        }
        
        public void setPaidFee(long paidFee) {
            this.paidFee = paidFee;
        }
        
        public int getIsFromDoor() {
            return isFromDoor;
        }
        
        public void setIsFromDoor(int isFromDoor) {
            this.isFromDoor = isFromDoor;
        }
        
        public int getIsToDoor() {
            return isToDoor;
        }
        
        public void setIsToDoor(int isToDoor) {
            this.isToDoor = isToDoor;
        }
        
        public List<Goods> getGoodsList() {
            return goodsList;
        }
        
        public void setGoodsList(List<Goods> goodsList) {
            this.goodsList = goodsList;
        }
        
        /**
         * 商品信息
         */
        public static class Goods {
            
            /**
             * 商品名称
             */
            private String name;
            
            /**
             * 数量
             */
            private Integer number;
            
            /**
             * 价格（单位：分）
             */
            private long price;
            
            /**
             * 商品规格
             */
            private String size;
            
            // ==================== Getter 和 Setter 方法 ====================
            
            public String getName() {
                return name;
            }
            
            public void setName(String name) {
                this.name = name;
            }
            
            public Integer getNumber() {
                return number;
            }
            
            public void setNumber(Integer number) {
                this.number = number;
            }
            
            public long getPrice() {
                return price;
            }
            
            public void setPrice(long price) {
                this.price = price;
            }
            
            public String getSize() {
                return size;
            }
            
            public void setSize(String size) {
                this.size = size;
            }
        }
    }
}