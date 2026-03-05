<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: models/sender/api
 * @ClassName: base
 * @Description: 麦芽田配送开放平台SDK - Sender API基类
 * 提供Sender API的基础功能和公共方法
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\models\sender\api;

use Maiyatian\Delivery\SDK\client\HTTPClientManager;
use Maiyatian\Delivery\SDK\utils\Tools;

abstract class Base
{
    /**
     * @var HTTPClientManager
     */
    protected $clientManager;

    /**
     * Base constructor.
     *
     * @param HTTPClientManager $clientManager
     */
    public function __construct(HTTPClientManager $clientManager)
    {
        $this->clientManager = $clientManager;
    }

    /**
     * 获取HTTP客户端管理器
     *
     * @return HTTPClientManager
     */
    public function getClientManager(): HTTPClientManager
    {
        return $this->clientManager;
    }

    /**
     * 设置HTTP客户端管理器
     *
     * @param HTTPClientManager $clientManager
     */
    public function setClientManager(HTTPClientManager $clientManager): void
    {
        $this->clientManager = $clientManager;
    }

    /**
     * 发送POST请求
     *
     * @param string $path 请求路径
     * @param mixed $data 业务数据
     * @param string $token 授权令牌
     * @return HTTPClientManager\HTTPResponse
     */
    protected function post(string $path, $data, string $token = ''): HTTPClientManager\HTTPResponse
    {
        return $this->clientManager->post($path, $data, $token);
    }

    /**
     * 发送GET请求
     *
     * @param string $path 请求路径
     * @param string $token 授权令牌
     * @return HTTPClientManager\HTTPResponse
     */
    protected function get(string $path, string $token = ''): HTTPClientManager\HTTPResponse
    {
        return $this->clientManager->get($path, $token);
    }

    /**
     * 解析响应数据
     *
     * @param string $data JSON字符串
     * @param bool $assoc 是否返回关联数组
     * @return mixed
     */
    protected function parseResponseData(string $data, bool $assoc = false)
    {
        return Tools::jsonDecode($data, $assoc);
    }
}
