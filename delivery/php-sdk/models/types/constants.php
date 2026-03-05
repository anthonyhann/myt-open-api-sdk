<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/types
 * @ClassName: constants
 * @Description: 麦芽田配送开放平台SDK - 常量定义
 * 提供API常量、状态码、错误码等定义
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\types;

class Constants
{
    // ==================== API相关常量 ====================

    /**
     * API版本
     */
    const API_VERSION = 'v1';

    /**
     * API路径前缀
     */
    const API_PATH_PREFIX = '/v1/delivery';

    /**
     * 测试环境URL
     */
    const TEST_BASE_URL = 'https://open-api-test.maiyatian.com';

    /**
     * 正式环境URL
     */
    const PROD_BASE_URL = 'https://open-api.maiyatian.com';

    /**
     * SDK版本
     */
    const SDK_VERSION = '1.0.0';

    // ==================== 授权相关常量 ====================

    /**
     * 授权类型：门店
     */
    const GRANT_TYPE_STORE = '1';

    /**
     * 授权类型：商户
     */
    const GRANT_TYPE_MERCHANT = '2';

    /**
     * 授权类型：三方门店直联授权
     */
    const GRANT_TYPE_THIRD_PARTY_STORE = '3';

    // ==================== 配送状态常量 ====================

    /**
     * 配送状态：待抢单
     */
    const DELIVERY_STATUS_PENDING = 'PENDING';

    /**
     * 配送状态：已分配骑手
     */
    const DELIVERY_STATUS_GRABBED = 'GRABBED';

    /**
     * 配送状态：待取货
     */
    const DELIVERY_STATUS_PICKUP = 'PICKUP';

    /**
     * 配送状态：配送中
     */
    const DELIVERY_STATUS_DELIVERING = 'DELIVERING';

    /**
     * 配送状态：已完成
     */
    const DELIVERY_STATUS_DONE = 'DONE';

    /**
     * 配送状态：已取消
     */
    const DELIVERY_STATUS_CANCEL = 'CANCEL';

    /**
     * 配送状态：骑手取消
     */
    const DELIVERY_STATUS_RIDER_CANCEL = 'RIDER_CANCEL';

    /**
     * 配送状态：骑手转单
     */
    const DELIVERY_STATUS_TRANSFER = 'TRANSFER';

    /**
     * 配送状态：配送异常
     */
    const DELIVERY_STATUS_EXPECT = 'EXPECT';

    // ==================== 轨迹状态常量 ====================

    /**
     * 轨迹状态：未处理
     */
    const TRACK_STATUS_UNPROGRESS = 'UNPROGRESS';

    /**
     * 轨迹状态：待确认
     */
    const TRACK_STATUS_CREATED = 'CREATED';

    /**
     * 轨迹状态：已确认
     */
    const TRACK_STATUS_CONFIRM = 'CONFIRM';

    /**
     * 轨迹状态：待抢单
     */
    const TRACK_STATUS_DELIVERY = 'DELIVERY';

    /**
     * 轨迹状态：待取货
     */
    const TRACK_STATUS_PICKUP = 'PICKUP';

    /**
     * 轨迹状态：配送中
     */
    const TRACK_STATUS_DELIVERING = 'DELIVERING';

    /**
     * 轨迹状态：已完成
     */
    const TRACK_STATUS_DONE = 'DONE';

    /**
     * 轨迹状态：已取消
     */
    const TRACK_STATUS_CANCEL = 'CANCEL';

    /**
     * 轨迹状态：已删除
     */
    const TRACK_STATUS_DELETE = 'DELETE';

    /**
     * 轨迹状态：配送异常
     */
    const TRACK_STATUS_EXPECT = 'EXPECT';

    // ==================== 支付方式常量 ====================

    /**
     * 支付方式：寄付现结
     */
    const PAY_MODE_SEND_CASH = 0;

    /**
     * 支付方式：寄付月结
     */
    const PAY_MODE_SEND_MONTHLY = 1;

    /**
     * 支付方式：收方付
     */
    const PAY_MODE_RECEIVE = 2;

    /**
     * 支付方式：第三方付
     */
    const PAY_MODE_THIRD_PARTY = 3;

    // ==================== 上门取件/送件常量 ====================

    /**
     * 不上门取件/送件
     */
    const NOT_DOOR_SERVICE = 0;

    /**
     * 上门取件/送件
     */
    const DOOR_SERVICE = 1;

    // ==================== 响应状态码常量 ====================

    /**
     * 成功
     */
    const SUCCESS = 200;

    /**
     * 错误请求
     */
    const BAD_REQUEST = 400;

    /**
     * 未授权
     */
    const UNAUTHORIZED = 401;

    /**
     * 禁止访问
     */
    const FORBIDDEN = 403;

    /**
     * 资源不存在
     */
    const NOT_FOUND = 404;

    /**
     * 请求方法错误
     */
    const METHOD_NOT_ALLOWED = 405;

    /**
     * 请求超时
     */
    const REQUEST_TIMEOUT = 408;

    /**
     * 服务器内部错误
     */
    const INTERNAL_SERVER_ERROR = 500;

    /**
     * 服务不可用
     */
    const SERVICE_UNAVAILABLE = 503;

    // ==================== 业务错误码常量 ====================

    /**
     * 业务成功
     */
    const BUSINESS_SUCCESS = 0;

    /**
     * 业务失败
     */
    const BUSINESS_FAILURE = -1;

    /**
     * 参数错误
     */
    const PARAM_ERROR = 10001;

    /**
     * 签名错误
     */
    const SIGN_ERROR = 10002;

    /**
     * 令牌无效
     */
    const TOKEN_INVALID = 10003;

    /**
     * 令牌过期
     */
    const TOKEN_EXPIRED = 10004;

    /**
     * 无权限访问
     */
    const NO_PERMISSION = 10005;

    /**
     * 资源不存在
     */
    const RESOURCE_NOT_FOUND = 10006;

    /**
     * 系统错误
     */
    const SYSTEM_ERROR = 10007;

    /**
     * 业务逻辑错误
     */
    const BUSINESS_LOGIC_ERROR = 10008;

    /**
     * 重复请求
     */
    const DUPLICATE_REQUEST = 10009;

    /**
     * 请求频率过高
     */
    const REQUEST_TOO_FREQUENT = 10010;

    // ==================== API命令常量 ====================

    /**
     * 授权码获取access_token
     */
    const COMMAND_ACCESS_TOKEN = 'access_token';

    /**
     * refreshToken刷新accessToken
     */
    const COMMAND_REFRESH_TOKEN = 'refresh_token';

    /**
     * 配送状态同步
     */
    const COMMAND_DELIVERY_CHANGE = 'delivery_change';

    /**
     * 快递轨迹回传
     */
    const COMMAND_LOCATION_CHANGE = 'location_change';

    /**
     * 获取城市运力
     */
    const COMMAND_CITY_CAPACITY = 'city_capacity';

    /**
     * 订单计费接口
     */
    const COMMAND_VALUATING = 'valuating';

    /**
     * 下单接口
     */
    const COMMAND_SEND = 'send';

    /**
     * 添加小费接口
     */
    const COMMAND_TIPS = 'tips';

    /**
     * 预取消配送
     */
    const COMMAND_PRECANCEL = 'precancel';

    /**
     * 取消配送
     */
    const COMMAND_CANCEL = 'cancel';

    /**
     * 查询配送信息
     */
    const COMMAND_QUERY_INFO = 'query_info';

    /**
     * 查询骑手位置
     */
    const COMMAND_RIDER_TRACK_POINTS = 'rider_track_points';

    /**
     * 创建第三方门店
     */
    const COMMAND_CREATE_THIRD_SHOP = 'create_third_shop';

    /**
     * 查询服务包列表
     */
    const COMMAND_SERVICE_PKG_LIST = 'service_pkg_list';

    /**
     * 查询多骑手位置
     */
    const COMMAND_MULTI_RIDER_LOCATIONS = 'multi_rider_locations';
}
