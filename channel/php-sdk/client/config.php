<?php

namespace Maiyatian\Channel\PhpSdk\Client;

use Maiyatian\Channel\PhpSdk\Consts\Consts;
use Maiyatian\Channel\PhpSdk\Vars\Variables;

/**
 * 企业级 HTTP 客户端配置类
 * 提供完整的连接池、超时、重试、日志等企业级特性配置
 */
class HTTPClientConfig
{
    /**
     * API 基础地址
     * 测试环境：https://open-api-test.maiyatian.com
     * 正式环境：https://open-api.maiyatian.com
     * @var string
     */
    public $BaseURL;

    /**
     * 应用密钥（app_key）
     * 由麦芽田开放平台分配，用于标识应用身份
     * @var string
     */
    public $APIKey;

    /**
     * 应用密钥（app_secret）
     * 由麦芽田开放平台分配，用于请求签名，请妥善保管
     * @var string
     */
    public $APISecret;

    /**
     * SDK 版本号
     * @var string
     */
    public $SDKVersion;

    /**
     * 最大连接数
     * 控制整体连接池大小，建议值：50-100
     * @var int
     */
    public $MaxConnections;

    /**
     * 每个主机的最大连接数
     * 控制单个主机的并发连接数，建议值：10-20
     * @var int
     */
    public $MaxConnectionsPerHost;

    /**
     * 连接保活超时时间（秒）
     * 空闲连接在连接池中的保持时间
     * @var int
     */
    public $KeepAliveTimeout;

    /**
     * 请求总超时时间（秒）
     * 包含连接建立、请求发送、响应接收的总时间
     * 建议值：30-60秒
     * @var int
     */
    public $RequestTimeout;

    /**
     * 连接建立超时时间（秒）
     * TCP 连接建立的最大等待时间
     * @var int
     */
    public $ConnectionTimeout;

    /**
     * 读取响应超时时间（秒）
     * 从服务器读取响应数据的最大等待时间
     * @var int
     */
    public $ReadTimeout;

    /**
     * 最大重试次数
     * 发生网络错误或 5xx 服务器错误时的重试次数
     * 建议值：2-3次
     * @var int
     */
    public $RetryMaxAttempts;

    /**
     * 重试基础延迟时间（秒）
     * 第一次重试前的等待时间
     * @var int
     */
    public $RetryBaseDelay;

    /**
     * 重试最大延迟时间（秒）
     * 重试等待时间的上限（使用指数退避策略）
     * @var int
     */
    public $RetryMaxDelay;

    /**
     * 是否启用日志记录
     * 开启后会记录请求和响应的详细信息
     * @var bool
     */
    public $EnableLogging;

    /**
     * 是否记录请求体
     * 开启后会在日志中输出完整的请求体（注意：可能包含敏感信息）
     * @var bool
     */
    public $LogRequestBody;

    /**
     * 是否记录响应体
     * 开启后会在日志中输出完整的响应体
     * @var bool
     */
    public $LogResponseBody;

    /**
     * 创建默认配置
     * @return HTTPClientConfig
     */
    public static function NewDefaultConfig()
    {
        $config = new self();
        $config->SDKVersion = Variables::getVersion();
        $config->BaseURL = Consts::PROD_BASE_URL; // 正式环境地址
        $config->MaxConnections = 50; // 最大 50 个连接
        $config->MaxConnectionsPerHost = 10; // 每个主机最多 10 个连接
        $config->KeepAliveTimeout = 30; // 连接保活 30 秒
        $config->RequestTimeout = Consts::DEFAULT_TIMEOUT; // 请求总超时 60 秒
        $config->ConnectionTimeout = Consts::DEFAULT_CONNECT_TIMEOUT; // 连接超时 30 秒
        $config->ReadTimeout = Consts::DEFAULT_TIMEOUT; // 读取超时 60 秒
        $config->RetryMaxAttempts = Consts::DEFAULT_RETRY_ATTEMPTS; // 最多重试 3 次
        $config->RetryBaseDelay = Consts::DEFAULT_RETRY_BASE_DELAY; // 首次重试延迟 1 秒
        $config->RetryMaxDelay = Consts::DEFAULT_RETRY_MAX_DELAY; // 最大重试延迟 30 秒
        $config->EnableLogging = true; // 启用日志
        $config->LogRequestBody = false; // 不记录请求体（避免敏感信息泄露）
        $config->LogResponseBody = false; // 不记录响应体
        return $config;
    }

    /**
     * 验证配置有效性
     * @return bool
     * @throws ConfigValidationError
     */
    public function Validate()
    {
        if (empty($this->BaseURL)) {
            throw new ConfigValidationError("BaseURL", "API 基础地址不能为空");
        }

        if (empty($this->APIKey)) {
            throw new ConfigValidationError("APIKey", "应用密钥（app_key）不能为空");
        }

        if (empty($this->APISecret)) {
            throw new ConfigValidationError("APISecret", "应用密钥（app_secret）不能为空");
        }

        if ($this->MaxConnections <= 0) {
            throw new ConfigValidationError("MaxConnections", "最大连接数必须大于 0");
        }

        if ($this->RequestTimeout <= 0) {
            throw new ConfigValidationError("RequestTimeout", "请求超时时间必须大于 0");
        }

        if ($this->RetryMaxAttempts < 0) {
            throw new ConfigValidationError("RetryMaxAttempts", "最大重试次数不能为负数");
        }

        return true;
    }
}

/**
 * 配置构建器（Builder 模式）
 * 提供链式调用的配置构建方式，使用更加灵活和直观
 */
class ConfigBuilder
{
    /**
     * @var HTTPClientConfig
     */
    private $config;

    /**
     * 创建新的配置构建器
     */
    public function __construct()
    {
        $this->config = HTTPClientConfig::NewDefaultConfig();
    }

    /**
     * 创建新的配置构建器
     * @return ConfigBuilder
     */
    public static function NewConfigBuilder()
    {
        return new self();
    }

    /**
     * 设置 API 基础地址
     * @param string $url API 基础地址
     * @return ConfigBuilder
     */
    public function BaseURL($url)
    {
        $this->config->BaseURL = $url;
        return $this;
    }

    /**
     * 设置应用密钥（app_key）
     * @param string $key 应用密钥
     * @return ConfigBuilder
     */
    public function APIKey($key)
    {
        $this->config->APIKey = $key;
        return $this;
    }

    /**
     * 设置应用密钥（app_secret）
     * @param string $secret 应用密钥
     * @return ConfigBuilder
     */
    public function APISecret($secret)
    {
        $this->config->APISecret = $secret;
        return $this;
    }

    /**
     * 设置最大连接数
     * @param int $max 最大连接数
     * @return ConfigBuilder
     */
    public function MaxConnections($max)
    {
        $this->config->MaxConnections = $max;
        return $this;
    }

    /**
     * 设置请求超时时间
     * @param int $timeout 请求超时时间（秒）
     * @return ConfigBuilder
     */
    public function RequestTimeout($timeout)
    {
        $this->config->RequestTimeout = $timeout;
        return $this;
    }

    /**
     * 设置最大重试次数
     * @param int $attempts 最大重试次数
     * @return ConfigBuilder
     */
    public function RetryMaxAttempts($attempts)
    {
        $this->config->RetryMaxAttempts = $attempts;
        return $this;
    }

    /**
     * 设置是否启用日志记录
     * @param bool $enable 是否启用日志
     * @return ConfigBuilder
     */
    public function EnableLogging($enable)
    {
        $this->config->EnableLogging = $enable;
        return $this;
    }

    /**
     * 构建最终配置
     * @return HTTPClientConfig
     * @throws ConfigValidationError
     */
    public function Build()
    {
        if (empty($this->config->BaseURL)) {
            throw new ConfigValidationError("BaseURL", "BaseURL 不能为空，请使用 BaseURL() 方法设置 API 基础地址");
        }

        if (empty($this->config->APIKey)) {
            throw new ConfigValidationError("APIKey", "APIKey 不能为空，请使用 APIKey() 方法设置应用密钥");
        }

        if (empty($this->config->APISecret)) {
            throw new ConfigValidationError("APISecret", "APISecret 不能为空，请使用 APISecret() 方法设置应用密钥");
        }

        $this->config->Validate();
        return $this->config;
    }
}

/**
 * 配置验证错误类
 * 包含出错的字段名和错误描述信息
 */
class ConfigValidationError extends \Exception
{
    /**
     * 出错的字段名
     * @var string
     */
    public $Field;

    /**
     * 错误描述信息
     * @var string
     */
    public $Message;

    /**
     * ConfigValidationError 构造函数
     * @param string $field 字段名
     * @param string $message 错误描述
     */
    public function __construct($field, $message)
    {
        $this->Field = $field;
        $this->Message = $message;
        parent::__construct("配置验证失败 [{$field}]: {$message}");
    }
}
