/*
 * 麦芽田渠道开放平台 Java SDK
 * Copyright (c) 2025 麦芽田开放平台
 * MIT License
 */

package com.maiyatian.channel.sdk.models.types;

/**
 * 麦芽田开放平台的核心枚举类型常量
 * <p>
 * 包含订单状态、配送状态、业务分类等通用业务枚举定义
 * </p>
 * 
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Constants {

    /**
     * 私有构造函数，防止实例化
     */
    private Constants() {
        throw new UnsupportedOperationException("常量类不允许实例化");
    }

    // ==================== 订单状态枚举 ====================

    /**
     * 订单状态枚举
     * <p>表示订单在整个生命周期中的不同状态</p>
     */
    public static final class OrderStatus {
        /** 未处理：订单刚创建，尚未被商家处理 */
        public static final String UNPROGRESS = "UNPROGRESS";
        /** 待确认：订单等待商家确认接单 */
        public static final String CREATED = "CREATED";
        /** 已确认：商家已确认接单，准备出餐 */
        public static final String CONFIRM = "CONFIRM";
        /** 待抢单：订单已发配送，等待骑手抢单 */
        public static final String DELIVERY = "DELIVERY";
        /** 待取货：骑手已抢单，等待到店取货 */
        public static final String PICKUP = "PICKUP";
        /** 配送中：骑手正在配送中 */
        public static final String DELIVERING = "DELIVERING";
        /** 已完成：订单已完成配送 */
        public static final String DONE = "DONE";
        /** 已取消：订单已取消 */
        public static final String CANCEL = "CANCEL";
        /** 已删除：订单已删除 */
        public static final String DELETE = "DELETE";
        /** 配送异常：配送过程中出现异常 */
        public static final String EXPECT = "EXPECT";

        private OrderStatus() {}
    }

    // ==================== 订单分类枚举 ====================

    /**
     * 订单分类枚举
     * <p>表示店铺的经营品类，用于商品分类和配送匹配</p>
     */
    public static final class Category {
        /** 小吃美食：小吃、快餐、地方特色小吃等 */
        public static final String XIAOCHI = "xiaochi";
        /** 正餐快餐：中餐、快餐、便当等 */
        public static final String CANYIN = "canyin";
        /** 龙虾烧烤：烧烤、龙虾、海鲜烧烤等 */
        public static final String SHAOKAO = "shaokao";
        /** 烘焙蛋糕：蛋糕、面包、烘焙甜点等 */
        public static final String DANGAO = "dangao";
        /** 甜品奶茶：奶茶、甜品、饮料等 */
        public static final String TIANPIN = "tianpin";
        /** 西餐料理：西餐、日料、韩料等 */
        public static final String LIAOLI = "liaoli";
        /** 火锅串串：火锅、麻辣烫、串串香等 */
        public static final String HUOGUO = "huoguo";
        /** 浪漫鲜花：鲜花、花束、绿植等 */
        public static final String XIANHUA = "xianhua";
        /** 生鲜果蔬：水果、蔬菜、生鲜产品等 */
        public static final String SHUIGUO = "shuiguo";
        /** 酒水零售：酒水、饮料、零食等 */
        public static final String YINPIN = "yinpin";
        /** 超市百货：日用品、零食、百货等 */
        public static final String CHAOSHI = "chaoshi";
        /** 医药成人：药品、成人用品、保健品等 */
        public static final String CHENGREN = "chengren";

        private Category() {}
    }

    // ==================== 订单来源渠道标识 ====================

    /**
     * 订单来源渠道标识
     * <p>标识订单的原始来源平台，用于数据统计和业务分析</p>
     */
    public static final class OriginTag {
        /** 美团外卖：美团外卖平台订单 */
        public static final String MEITUAN = "meituan";
        /** 美团闪购：美团闪购平台订单 */
        public static final String SHANGOU = "shangou";
        /** 美团品牌：美团品牌订单 */
        public static final String MEITUANKA = "meituanka";
        /** 饿了么：饿了么外卖平台订单 */
        public static final String ELEME = "eleme";
        /** 饿百：饿了么超市订单 */
        public static final String EBAI = "ebai";
        /** 抖音外卖：抖音本地生活外卖订单 */
        public static final String TIKTOK = "tiktok";
        /** 抖店小时达：抖音电商小时达订单 */
        public static final String DOUDIAN = "doudian";
        /** 快手：快手平台订单 */
        public static final String KUAISHOU = "kuaishou";
        /** 京东：京东到家订单 */
        public static final String DAOJIA = "daojia";
        /** 微盟：微盟 SaaS 平台订单 */
        public static final String WEIMOB = "weimob";
        /** 有赞：有赞 SaaS 平台订单 */
        public static final String YOUZAN2 = "youzan2";
        /** 微信：微信小程序/公众号订单 */
        public static final String WEIXIN = "weixin";
        /** 支付宝：支付宝小程序订单 */
        public static final String ALIPAY = "alipay";
        /** 小程序类：其他小程序平台订单 */
        public static final String APPLET = "applet";
        /** 其他：其他未分类的订单来源 */
        public static final String OTHER = "other";

        private OriginTag() {}
    }

    // ==================== 配送状态枚举 ====================

    /**
     * 配送状态枚举
     * <p>表示订单在配送过程中的不同状态</p>
     */
    public static final class DeliveryStatus {
        /** 待处理：配送订单已创建，等待分配骑手 */
        public static final String PENDING = "PENDING";
        /** 已分配骑手：骑手已接单 */
        public static final String GRABBED = "GRABBED";
        /** 已到店：骑手已到达商家 */
        public static final String AT_SHOP = "ATSHOP";
        /** 待取货：等待商家出餐，骑手取货 */
        public static final String PICKUP = "PICKUP";
        /** 配送中：骑手正在配送途中 */
        public static final String DELIVERING = "DELIVERING";
        /** 已完成：配送已完成 */
        public static final String DONE = "DONE";
        /** 已取消：配送已取消 */
        public static final String CANCEL = "CANCEL";
        /** 配送异常：配送过程中出现异常 */
        public static final String EXPECT = "EXPECT";
        /** 骑手转单：骑手将订单转给其他骑手 */
        public static final String TRANSFER = "TRANSFER";
        /** 骑手取消：骑手主动取消订单 */
        public static final String RIDER_CANCEL = "RIDER_CANCEL";

        private DeliveryStatus() {}
    }

    // ==================== 订单取消类型枚举 ====================

    /**
     * 订单取消类型枚举
     * <p>标识订单取消的发起方</p>
     */
    public static final class CancelType {
        /** 用户取消：顾客主动取消订单 */
        public static final int USER = 1;
        /** 商户取消：商家主动取消订单 */
        public static final int MERCHANT = 2;
        /** 客服取消：客服介入取消订单 */
        public static final int SERVICE = 3;
        /** 系统取消：系统自动取消订单（如超时未支付） */
        public static final int SYSTEM = 4;
        /** 其他取消：其他原因取消 */
        public static final int OTHER = 5;

        private CancelType() {}
    }

    // ==================== 订单出餐类型枚举 ====================

    /**
     * 订单出餐类型枚举
     * <p>表示商家备货和出餐的状态</p>
     */
    public static final class MealPickingType {
        /** 等待拣货：订单等待商家备货 */
        public static final int WAIT = 0;
        /** 拣货中：商家正在备货、出餐 */
        public static final int DOING = 1;
        /** 拣货完成：商家已完成出餐，等待骑手取货 */
        public static final int DONE = 2;

        private MealPickingType() {}
    }

    // ==================== 订单催单回复类型枚举 ====================

    /**
     * 订单催单回复类型枚举
     * <p>商家对顾客催单的回复原因</p>
     */
    public static final class OrderRemindReplyType {
        /** 备货中，正在烹饪：商家正在制作餐品 */
        public static final int PREPARING = 1;
        /** 已送出：订单已交给骑手配送 */
        public static final int DELIVERING = 2;
        /** 天气原因：因天气原因导致延迟 */
        public static final int WEATHER = 3;
        /** 人手不足：因人手不足导致延迟 */
        public static final int SHORTHAND = 4;
        /** 其他：其他原因导致延迟 */
        public static final int OTHER = 5;

        private OrderRemindReplyType() {}
    }
}