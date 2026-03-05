<?php

namespace MytChannel\Models\Receiver\Entity\Auth;

/**
 * 获取授权码请求参数（用于组装授权 URL）
 * 三方服务组装授权页面 URL 时使用的参数
 * 
 * 使用场景：
 *   三方平台引导商户到麦芽田授权页面完成授权
 *
 * 授权流程：
 *   1. 三方组装授权页面 URL（包含本请求参数）
 *   2. 商户在麦芽田授权页面登录并同意授权
 *   3. 授权成功后，授权码 code 通过回调地址返回给三方
 *   4. 三方使用 code 调用 AccessToken 接口向麦芽田换取访问令牌
 *
 * URL 组装示例：
 *   http://saas.open.test.maiyatian.com/oauth/?app_key=xxx&redirect_uri=xxx&view=web&response_type=code&shop_id=xxx&state=xxx
 */
class CreateCodeReq {
    /**
     * @var string 应用密钥（app_key），由麦芽田开放平台分配
     */
    public $app_key;
    
    /**
     * @var string 授权步骤类型，固定值："code"
     */
    public $response_type = "code";
    
    /**
     * @var string 授权页面类型："web"（PC端）、"h5"（移动端）
     */
    public $view = "web";
    
    /**
     * @var string 授权成功后的回调地址（三方的回调地址）
     */
    public $redirect_uri;
    
    /**
     * @var string 平台方渠道唯一 ID（三方平台的门店标识）
     */
    public $shop_id;
    
    /**
     * @var string 自定义状态值，用于防止 CSRF 攻击
     */
    public $state;
}

/**
 * 获取授权码响应数据（麦芽田通过回调返回）
 * 授权成功后麦芽田通过重定向返回给三方的参数
 * 
 * 返回方式：
 *   授权成功后，麦芽田将浏览器重定向到三方回调地址：
 *   http(s)://redirect_uri?code=CODE&state=STATE
 */
class CreateCodeResp {
    /**
     * @var string 授权码，用于换取 access_token，有效期 5 分钟，只能使用一次
     */
    public $code;
    
    /**
     * @var string 自定义状态值，原样返回
     */
    public $state;
}
