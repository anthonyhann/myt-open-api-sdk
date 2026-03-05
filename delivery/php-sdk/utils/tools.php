<?php
/**
 * @Author Hanqiang
 * @Date 2025/12/9
 * @PackageName: utils
 * @ClassName: tools
 * @Description: 麦芽田配送开放平台SDK - 工具类
 * 提供JSON编解码、数据转换、签名生成等通用功能
 * @Version 1.0
 */

namespace Maiyatian\Delivery\SDK\utils;

class Tools
{
    /**
     * 将对象转换为数组
     *
     * @param mixed $obj 对象或数组
     * @return array
     */
    public static function toArray($obj): array
    {
        if (is_array($obj)) {
            return $obj;
        }

        if (is_object($obj)) {
            return json_decode(json_encode($obj), true);
        }

        return [$obj];
    }

    /**
     * 将数组转换为对象
     *
     * @param array $array 数组
     * @return object
     */
    public static function toObject(array $array): object
    {
        return json_decode(json_encode($array));
    }

    /**
     * JSON编码
     *
     * @param mixed $data 要编码的数据
     * @param int $options JSON编码选项
     * @return string
     */
    public static function jsonEncode($data, int $options = JSON_UNESCAPED_UNICODE): string
    {
        $json = json_encode($data, $options);
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \RuntimeException('JSON encode error: ' . json_last_error_msg());
        }
        return $json;
    }

    /**
     * JSON解码
     *
     * @param string $json JSON字符串
     * @param bool $assoc 是否返回关联数组
     * @return mixed
     */
    public static function jsonDecode(string $json, bool $assoc = false)
    {
        $data = json_decode($json, $assoc);
        if (json_last_error() !== JSON_ERROR_NONE) {
            throw new \RuntimeException('JSON decode error: ' . json_last_error_msg());
        }
        return $data;
    }

    /**
     * 生成随机字符串
     *
     * @param int $length 字符串长度
     * @param string $chars 字符集
     * @return string
     */
    public static function randomString(int $length = 16, string $chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'): string
    {
        $result = '';
        $charsLength = strlen($chars);
        for ($i = 0; $i < $length; $i++) {
            $result .= $chars[random_int(0, $charsLength - 1)];
        }
        return $result;
    }

    /**
     * 生成UUID
     *
     * @return string
     */
    public static function uuid(): string
    {
        $data = random_bytes(16);
        $data[6] = chr(ord($data[6]) & 0x0f | 0x40); // Version 4
        $data[8] = chr(ord($data[8]) & 0x3f | 0x80); // Variant 1

        return vsprintf('%s%s-%s-%s-%s-%s%s%s', str_split(bin2hex($data), 4));
    }

    /**
     * URL安全的Base64编码
     *
     * @param string $data 要编码的数据
     * @return string
     */
    public static function base64UrlEncode(string $data): string
    {
        $base64 = base64_encode($data);
        return str_replace(['+', '/', '='], ['-', '_', ''], $base64);
    }

    /**
     * URL安全的Base64解码
     *
     * @param string $data 要解码的数据
     * @return string
     */
    public static function base64UrlDecode(string $data): string
    {
        $base64 = str_replace(['-', '_'], ['+', '/'], $data);
        $padding = strlen($base64) % 4;
        if ($padding !== 0) {
            $base64 .= str_repeat('=', 4 - $padding);
        }
        return base64_decode($base64);
    }

    /**
     * 生成HmacSHA256签名
     *
     * @param string $data 要签名的数据
     * @param string $secret 密钥
     * @return string
     */
    public static function hmacSha256(string $data, string $secret): string
    {
        return hash_hmac('sha256', $data, $secret, true);
    }

    /**
     * 生成HmacSHA256签名的Base64编码
     *
     * @param string $data 要签名的数据
     * @param string $secret 密钥
     * @return string
     */
    public static function hmacSha256Base64(string $data, string $secret): string
    {
        $hmac = self::hmacSha256($data, $secret);
        return self::base64UrlEncode($hmac);
    }

    /**
     * 验证签名
     *
     * @param string $data 要验证的数据
     * @param string $secret 密钥
     * @param string $signature 签名值
     * @return bool
     */
    public static function verifySignature(string $data, string $secret, string $signature): bool
    {
        $expected = self::hmacSha256Base64($data, $secret);
        return hash_equals($expected, $signature);
    }

    /**
     * 获取当前时间戳（秒）
     *
     * @return int
     */
    public static function getTimestamp(): int
    {
        return time();
    }

    /**
     * 获取当前时间戳（毫秒）
     *
     * @return int
     */
    public static function getMillisecondTimestamp(): int
    {
        return (int)(microtime(true) * 1000);
    }

    /**
     * 格式化日期时间
     *
     * @param int|null $timestamp 时间戳，默认当前时间
     * @param string $format 格式化字符串，默认'Y-m-d H:i:s'
     * @return string
     */
    public static function formatDateTime(?int $timestamp = null, string $format = 'Y-m-d H:i:s'): string
    {
        if ($timestamp === null) {
            $timestamp = time();
        }
        return date($format, $timestamp);
    }

    /**
     * 检查字符串是否为JSON格式
     *
     * @param string $string 字符串
     * @return bool
     */
    public static function isJson(string $string): bool
    {
        json_decode($string);
        return json_last_error() === JSON_ERROR_NONE;
    }

    /**
     * 驼峰命名转下划线命名
     *
     * @param string $str 驼峰命名字符串
     * @return string
     */
    public static function camelToSnake(string $str): string
    {
        return strtolower(preg_replace('/(?<!^)[A-Z]/', '_$0', $str));
    }

    /**
     * 下划线命名转驼峰命名
     *
     * @param string $str 下划线命名字符串
     * @param bool $ucfirst 是否首字母大写，默认false
     * @return string
     */
    public static function snakeToCamel(string $str, bool $ucfirst = false): string
    {
        $str = lcfirst(str_replace('_', '', ucwords($str, '_')));
        if ($ucfirst) {
            $str = ucfirst($str);
        }
        return $str;
    }

    /**
     * 递归转换数组键名为驼峰命名
     *
     * @param array $array 数组
     * @param bool $ucfirst 是否首字母大写，默认false
     * @return array
     */
    public static function arrayKeysToCamel(array $array, bool $ucfirst = false): array
    {
        $result = [];
        foreach ($array as $key => $value) {
            $newKey = self::snakeToCamel($key, $ucfirst);
            $result[$newKey] = is_array($value) ? self::arrayKeysToCamel($value, $ucfirst) : $value;
        }
        return $result;
    }

    /**
     * 递归转换数组键名为下划线命名
     *
     * @param array $array 数组
     * @return array
     */
    public static function arrayKeysToSnake(array $array): array
    {
        $result = [];
        foreach ($array as $key => $value) {
            $newKey = self::camelToSnake($key);
            $result[$newKey] = is_array($value) ? self::arrayKeysToSnake($value) : $value;
        }
        return $result;
    }
}
