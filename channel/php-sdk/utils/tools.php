<?php

namespace Maiyatian\Channel\PhpSdk\Utils;

/**
 * 通用工具函数类
 * 包含结构体转Map、字符串处理等常用工具
 */
class Tools
{
    /**
     * 将对象转换为关联数组
     * 使用反射机制提取对象字段及其值
     * 
     * @param object $in 待转换的对象
     * @param string $tagName 使用的标签名称（如 "json"），为空则使用字段名
     * @return array 转换后的关联数组
     * @throws \InvalidArgumentException 如果输入不是对象
     */
    public static function ToMap($in, $tagName = '')
    {
        // 验证输入类型
        if (!is_object($in)) {
            throw new \InvalidArgumentException('ToMap 仅接受对象类型，实际类型：' . gettype($in));
        }

        $out = [];
        $reflection = new \ReflectionObject($in);
        $properties = $reflection->getProperties();

        foreach ($properties as $property) {
            // 获取属性名称
            $propertyName = $property->getName();
            
            // 确保属性可访问
            $property->setAccessible(true);
            
            // 获取属性值
            $value = $property->getValue($in);
            
            // 使用标签名称作为键（如果指定）
            $key = $propertyName;
            if (!empty($tagName)) {
                $tagValue = $property->getDocComment();
                if (!empty($tagValue)) {
                    // 从注释中提取标签值
                    preg_match(sprintf('/@%s\s+([^\s]+)/', $tagName), $tagValue, $matches);
                    if (isset($matches[1])) {
                        $key = $matches[1];
                    }
                }
            }
            
            // 将值添加到结果数组
            $out[$key] = $value;
        }

        return $out;
    }

    /**
     * 生成随机字符串
     * @param int $length 字符串长度
     * @param string $charset 字符集
     * @return string 随机字符串
     */
    public static function GenerateRandomString($length = 16, $charset = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ')
    {
        $charsetLength = strlen($charset);
        $result = '';
        for ($i = 0; $i < $length; $i++) {
            $result .= $charset[random_int(0, $charsetLength - 1)];
        }
        return $result;
    }

    /**
     * 格式化时间戳为字符串
     * @param int $timestamp 时间戳
     * @param string $format 格式
     * @return string 格式化后的时间字符串
     */
    public static function FormatTimestamp($timestamp, $format = 'Y-m-d H:i:s')
    {
        return date($format, $timestamp);
    }

    /**
     * 验证UUID格式
     * @param string $uuid UUID字符串
     * @return bool 是否为有效的UUID
     */
    public static function IsValidUUID($uuid)
    {
        return preg_match('/^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i', $uuid) === 1;
    }

    /**
     * 验证手机号格式
     * @param string $phone 手机号
     * @return bool 是否为有效的手机号
     */
    public static function IsValidPhone($phone)
    {
        return preg_match('/^1[3-9]\d{9}$/', $phone) === 1;
    }
}
