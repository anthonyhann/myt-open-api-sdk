<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: client
 * @ClassName: Config
 * @Description: 麦芽田配送开放平台SDK - HTTP客户端配置管理
 * @Version 1.0
 */

namespace Maiyatian\Delivery\PhpSdk\Client;

use InvalidArgumentException;

class Config
{
    // ==================== 基础配置 ====================
    /**
     * API服务基础URL
     * 测试环境: https://open-api-test.maiyatian.com
     * 正式环境: https://open-api.maiyatian.com
     * 注意: 严禁使用正式地址进行接口调试
     * @var string
     */
    public $baseURL = "https://open-api.maiyatian.com";

    /**
     * 应用密钥
     * 从麦芽田开放平台申请获得，用于标识调用方
     * 必填项，未提供会导致配置验证失败
     * @var string
     */
    public $apiKey = "";

    /**
     * 应用密钥
     * 从麦芽田开放平台申请获得，用于请求签名
     * 必填项，未提供会导致配置验证失败
     * 注意: 请妥善保管，不要泄露
     * @var string
     */
    public $apiSecret = "";

    /**
     * SDK版本号
     * 用于User-Agent标识
     * @var string
     */
    public $sdkVersion = "1.0.0";

    // ==================== 连接池配置 ====================
    /**
     * 最大连接数
     * 控制整个客户端的最大TCP连接数
     * @var int
     */
    public $maxConnections = 50;

    /**
     * 每个主机的最大连接数
     * 控制对单个主机的最大并发连接数
     * @var int
     */
    public $maxConnectionsPerHost = 10;

    /**
     * 连接保持时间(秒)
     * HTTP Keep-Alive连接的空闲超时时间
     * @var int
     */
    public $keepAliveTimeout = 30;

    // ==================== 超时配置 ====================
    /**
     * 请求超时(秒)
     * 整个HTTP请求（包括连接、发送、接收）的总超时时间
     * @var int
     */
    public $requestTimeout = 60;

    /**
     * 连接超时(秒)
     * 建立TCP连接的超时时间
     * @var int
     */
    public $connectionTimeout = 30;

    /**
     * 读取超时(秒)
     * 从服务器读取响应的超时时间
     * @var int
     */
    public $readTimeout = 60;

    // ==================== 重试配置 ====================
    /**
     * 最大重试次数
     * 网络错误或5xx错误时的最大重试次数
     * @var int
     */
    public $retryMaxAttempts = 3;

    /**
     * 重试基础延迟(毫秒)
     * 首次重试的等待时间
     * @var int
     */
    public $retryBaseDelay = 1000;

    /**
     * 重试最大延迟(毫秒)
     * 重试等待的最大时间
     * @var int
     */
    public $retryMaxDelay = 30000;

    // ==================== 日志配置 ====================
    /**
     * 启用日志记录
     * 是否记录HTTP请求和响应日志
     * @var bool
     */
    public $enableLogging = true;

    /**
     * 记录请求体
     * 是否在日志中记录请求体内容
     * 警告: 可能包含敏感信息，生产环境谨慎启用
     * @var bool
     */
    public $logRequestBody = false;

    /**
     * 记录响应体
     * 是否在日志中记录响应体内容
     * 说明: 用于调试，可能影响性能
     * @var bool
     */
    public $logResponseBody = false;

    /**
     * 验证配置有效性
     *
     * 功能说明:
     *   - 检查配置参数是否符合要求
     *   - 在创建HTTP客户端前自动调用
     *   - 防止使用无效配置导致运行时错误
     *
     * 验证规则:
     *   - baseURL: 不能为空
     *   - apiKey: 不能为空（必填项）
     *   - apiSecret: 不能为空（必填项）
     *   - maxConnections: 必须大于0
     *   - requestTimeout: 必须大于0
     *   - retryMaxAttempts: 不能为负数
     *
     * @return bool
     * @throws InvalidArgumentException
     */
    public function validate()
    {
        if (empty($this->baseURL)) {
            throw new InvalidArgumentException("baseURL cannot be empty");
        }

        if (empty($this->apiKey)) {
            throw new InvalidArgumentException("apiKey cannot be empty");
        }

        if (empty($this->apiSecret)) {
            throw new InvalidArgumentException("apiSecret cannot be empty");
        }

        if ($this->maxConnections <= 0) {
            throw new InvalidArgumentException("maxConnections must be positive");
        }

        if ($this->requestTimeout <= 0) {
            throw new InvalidArgumentException("requestTimeout must be positive");
        }

        if ($this->retryMaxAttempts < 0) {
            throw new InvalidArgumentException("retryMaxAttempts cannot be negative");
        }

        return true;
    }

    /**
     * 创建默认配置
     *
     * 功能说明:
     *   - 创建一个包含推荐默认值的HTTP客户端配置
     *   - 适用于大多数使用场景
     *   - 可以在此基础上覆盖特定配置项
     *
     * @return Config
     */
    public static function newDefaultConfig()
    {
        return new self();
    }
}

/**
 * 配置构建器类
 * 使用Builder模式构建HTTP客户端配置
 */
class ConfigBuilder
{
    /**
     * @var Config
     */
    private $config;

    /**
     * 创建新的配置构建器
     *
     * 功能说明:
     *   - 创建配置构建器实例
     *   - 自动填充默认配置值
     *   - 可通过链式调用设置配置项
     */
    public function __construct()
    {
        $this->config = Config::newDefaultConfig();
    }

    /**
     * 设置API服务基础URL
     *
     * 参数说明:
     *   - url: API服务地址
     *     - 测试环境: https://open-api-test.maiyatian.com
     *     - 正式环境: https://open-api.maiyatian.com
     *
     * @param string $url
     * @return ConfigBuilder
     */
    public function baseURL(string $url): ConfigBuilder
    {
        $this->config->baseURL = $url;
        return $this;
    }

    /**
     * 设置应用密钥
     *
     * 参数说明:
     *   - key: 从麦芽田开放平台申请获得的AppKey
     *
     * @param string $key
     * @return ConfigBuilder
     */
    public function apiKey(string $key): ConfigBuilder
    {
        $this->config->apiKey = $key;
        return $this;
    }

    /**
     * 设置应用密钥
     *
     * 参数说明:
     *   - secret: 从麦芽田开放平台申请获得的AppSecret
     *
     * @param string $secret
     * @return ConfigBuilder
     */
    public function apiSecret(string $secret): ConfigBuilder
    {
        $this->config->apiSecret = $secret;
        return $this;
    }

    /**
     * 设置最大连接数
     *
     * @param int $max
     * @return ConfigBuilder
     */
    public function maxConnections(int $max): ConfigBuilder
    {
        $this->config->maxConnections = $max;
        return $this;
    }

    /**
     * 设置请求超时时间
     *
     * @param int $timeout 秒
     * @return ConfigBuilder
     */
    public function requestTimeout(int $timeout): ConfigBuilder
    {
        $this->config->requestTimeout = $timeout;
        return $this;
    }

    /**
     * 设置最大重试次数
     *
     * @param int $attempts
     * @return ConfigBuilder
     */
    public function retryMaxAttempts(int $attempts): ConfigBuilder
    {
        $this->config->retryMaxAttempts = $attempts;
        return $this;
    }

    /**
     * 启用日志记录
     *
     * @param bool $enable
     * @return ConfigBuilder
     */
    public function enableLogging(bool $enable): ConfigBuilder
    {
        $this->config->enableLogging = $enable;
        return $this;
    }

    /**
     * 构建最终配置
     *
     * 功能说明:
     *   - 完成配置构建并返回配置对象
     *   - 自动验证必填项
     *
     * @return Config
     * @throws InvalidArgumentException
     */
    public function build(): Config
    {
        // 配置验证
        if (empty($this->config->baseURL)) {
            throw new InvalidArgumentException("baseURL is required");
        }

        if (empty($this->config->apiKey)) {
            throw new InvalidArgumentException("apiKey is required");
        }

        if (empty($this->config->apiSecret)) {
            throw new InvalidArgumentException("apiSecret is required");
        }

        $this->config->validate();
        return $this->config;
    }
}
