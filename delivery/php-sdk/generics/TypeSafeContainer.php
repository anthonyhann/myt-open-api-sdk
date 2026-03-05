<?php

namespace Maiyatian\Delivery\PhpSdk\Generics;

/**
 * 类型安全容器
 * 实现Go SDK中泛型T的高级用法在PHP中的等价功能
 * 使用PHP反射和运行时类型检查模拟编译时类型安全
 * 
 * @template T
 * @author SDK Generator
 * @version 1.0.0
 * @since 1.0.0
 */
class TypeSafeContainer
{
    /**
     * 容器数据
     * @var mixed
     */
    private $data;
    
    /**
     * 类型约束
     * @var string|null
     */
    private $typeConstraint;
    
    /**
     * 类型验证器
     * @var callable|null
     */
    private $typeValidator;
    
    /**
     * 构造函数
     * 
     * @param mixed $data 初始数据
     * @param string|null $typeConstraint 类型约束
     * @param callable|null $typeValidator 自定义类型验证器
     */
    public function __construct($data = null, ?string $typeConstraint = null, ?callable $typeValidator = null)
    {
        $this->typeConstraint = $typeConstraint;
        $this->typeValidator = $typeValidator;
        
        if ($data !== null) {
            $this->set($data);
        }
    }
    
    /**
     * 设置数据（类型安全）
     * 
     * @param mixed $data 数据
     * @return self
     * @throws \InvalidArgumentException
     */
    public function set($data): self
    {
        $this->validateType($data);
        $this->data = $data;
        return $this;
    }
    
    /**
     * 获取数据
     * 
     * @return mixed
     */
    public function get()
    {
        return $this->data;
    }
    
    /**
     * 检查是否为空
     * 
     * @return bool
     */
    public function isEmpty(): bool
    {
        return $this->data === null;
    }
    
    /**
     * 检查是否有数据
     * 
     * @return bool
     */
    public function hasValue(): bool
    {
        return $this->data !== null;
    }
    
    /**
     * 获取数据或返回默认值
     * 
     * @param mixed $defaultValue 默认值
     * @return mixed
     */
    public function getOrDefault($defaultValue)
    {
        return $this->hasValue() ? $this->data : $defaultValue;
    }
    
    /**
     * 获取数据或抛出异常
     * 
     * @return mixed
     * @throws \RuntimeException
     */
    public function getOrThrow()
    {
        if (!$this->hasValue()) {
            throw new \RuntimeException('容器为空，无法获取数据');
        }
        return $this->data;
    }
    
    /**
     * 映射转换（函数式编程）
     * 对应Go SDK中的泛型转换功能
     * 
     * @template U
     * @param callable $mapper 转换函数
     * @param string|null $targetType 目标类型
     * @return TypeSafeContainer<U>
     */
    public function map(callable $mapper, ?string $targetType = null): self
    {
        if (!$this->hasValue()) {
            return new self(null, $targetType);
        }
        
        try {
            $mappedValue = $mapper($this->data);
            return new self($mappedValue, $targetType);
        } catch (\Exception $e) {
            throw new \RuntimeException('数据映射失败: ' . $e->getMessage(), 0, $e);
        }
    }
    
    /**
     * 过滤数据
     * 
     * @param callable $predicate 过滤条件
     * @return TypeSafeContainer<T>
     */
    public function filter(callable $predicate): self
    {
        if (!$this->hasValue()) {
            return $this;
        }
        
        try {
            if ($predicate($this->data)) {
                return $this;
            } else {
                return new self(null, $this->typeConstraint, $this->typeValidator);
            }
        } catch (\Exception $e) {
            throw new \RuntimeException('数据过滤失败: ' . $e->getMessage(), 0, $e);
        }
    }
    
    /**
     * 平坦映射（处理嵌套容器）
     * 
     * @template U
     * @param callable $mapper 映射函数，返回TypeSafeContainer
     * @return TypeSafeContainer<U>
     */
    public function flatMap(callable $mapper): self
    {
        if (!$this->hasValue()) {
            return new self();
        }
        
        $result = $mapper($this->data);
        
        if (!($result instanceof self)) {
            throw new \InvalidArgumentException('flatMap的映射函数必须返回TypeSafeContainer实例');
        }
        
        return $result;
    }
    
    /**
     * 链式操作（流式API）
     * 
     * @param callable $operation 操作函数
     * @return self
     */
    public function pipe(callable $operation): self
    {
        return $operation($this);
    }
    
    /**
     * 合并两个容器
     * 
     * @param TypeSafeContainer $other 另一个容器
     * @param callable $combiner 合并函数
     * @return TypeSafeContainer
     */
    public function combine(self $other, callable $combiner): self
    {
        if (!$this->hasValue() || !$other->hasValue()) {
            return new self();
        }
        
        $combined = $combiner($this->data, $other->data);
        return new self($combined);
    }
    
    /**
     * 类型验证
     * 
     * @param mixed $data 待验证数据
     * @throws \InvalidArgumentException
     */
    private function validateType($data): void
    {
        // 自定义验证器优先
        if ($this->typeValidator !== null) {
            if (!($this->typeValidator)($data)) {
                throw new \InvalidArgumentException(sprintf(
                    '数据不满足自定义类型约束，实际类型: %s',
                    $this->getActualType($data)
                ));
            }
            return;
        }
        
        // 类型约束检查
        if ($this->typeConstraint !== null && !$this->matchesTypeConstraint($data, $this->typeConstraint)) {
            throw new \InvalidArgumentException(sprintf(
                '数据类型不匹配，期望: %s，实际: %s',
                $this->typeConstraint,
                $this->getActualType($data)
            ));
        }
    }
    
    /**
     * 检查类型约束匹配
     * 
     * @param mixed $data 数据
     * @param string $constraint 类型约束
     * @return bool
     */
    private function matchesTypeConstraint($data, string $constraint): bool
    {
        switch ($constraint) {
            case 'int':
            case 'integer':
                return is_int($data);
            case 'float':
            case 'double':
                return is_float($data);
            case 'string':
                return is_string($data);
            case 'bool':
            case 'boolean':
                return is_bool($data);
            case 'array':
                return is_array($data);
            case 'object':
                return is_object($data);
            case 'callable':
                return is_callable($data);
            case 'resource':
                return is_resource($data);
            case 'null':
                return is_null($data);
            default:
                // 检查类或接口
                if (class_exists($constraint) || interface_exists($constraint)) {
                    return $data instanceof $constraint;
                }
                return true; // 未知类型，允许通过
        }
    }
    
    /**
     * 获取实际类型
     * 
     * @param mixed $data 数据
     * @return string
     */
    private function getActualType($data): string
    {
        if (is_object($data)) {
            return get_class($data);
        }
        return gettype($data);
    }
    
    /**
     * 创建空容器
     * 
     * @template U
     * @param string|null $typeConstraint 类型约束
     * @return TypeSafeContainer<U>
     */
    public static function empty(?string $typeConstraint = null): self
    {
        return new self(null, $typeConstraint);
    }
    
    /**
     * 创建包含值的容器
     * 
     * @template U
     * @param mixed $value 值
     * @param string|null $typeConstraint 类型约束
     * @return TypeSafeContainer<U>
     */
    public static function of($value, ?string $typeConstraint = null): self
    {
        return new self($value, $typeConstraint);
    }
    
    /**
     * 创建可空容器
     * 
     * @template U
     * @param mixed $value 可能为null的值
     * @param string|null $typeConstraint 类型约束
     * @return TypeSafeContainer<U>
     */
    public static function ofNullable($value, ?string $typeConstraint = null): self
    {
        return new self($value, $typeConstraint);
    }
    
    /**
     * 字符串表示
     * 
     * @return string
     */
    public function __toString(): string
    {
        return sprintf(
            'TypeSafeContainer{type=%s, hasValue=%s, value=%s}',
            $this->typeConstraint ?? 'any',
            $this->hasValue() ? 'true' : 'false',
            $this->hasValue() ? $this->formatValue($this->data) : 'null'
        );
    }
    
    /**
     * 格式化值用于显示
     * 
     * @param mixed $value 值
     * @return string
     */
    private function formatValue($value): string
    {
        if (is_string($value)) {
            return '"' . $value . '"';
        }
        if (is_array($value)) {
            return 'array(' . count($value) . ')';
        }
        if (is_object($value)) {
            return get_class($value) . '@' . spl_object_hash($value);
        }
        return var_export($value, true);
    }
}

/**
 * 泛型结果容器
 * 模拟Go SDK中的Result[T, E]模式
 * 
 * @template T 成功类型
 * @template E 错误类型
 */
class Result
{
    /**
     * 成功值
     * @var mixed
     */
    private $value;
    
    /**
     * 错误值
     * @var mixed
     */
    private $error;
    
    /**
     * 是否为成功状态
     * @var bool
     */
    private $isSuccess;
    
    /**
     * 私有构造函数
     * 
     * @param mixed $value 值
     * @param mixed $error 错误
     * @param bool $isSuccess 是否成功
     */
    private function __construct($value, $error, bool $isSuccess)
    {
        $this->value = $value;
        $this->error = $error;
        $this->isSuccess = $isSuccess;
    }
    
    /**
     * 创建成功结果
     * 
     * @template U
     * @param mixed $value 成功值
     * @return Result<U, mixed>
     */
    public static function success($value): self
    {
        return new self($value, null, true);
    }
    
    /**
     * 创建错误结果
     * 
     * @template F
     * @param mixed $error 错误值
     * @return Result<mixed, F>
     */
    public static function error($error): self
    {
        return new self(null, $error, false);
    }
    
    /**
     * 检查是否成功
     * 
     * @return bool
     */
    public function isSuccess(): bool
    {
        return $this->isSuccess;
    }
    
    /**
     * 检查是否错误
     * 
     * @return bool
     */
    public function isError(): bool
    {
        return !$this->isSuccess;
    }
    
    /**
     * 获取成功值
     * 
     * @return mixed
     * @throws \RuntimeException
     */
    public function getValue()
    {
        if (!$this->isSuccess) {
            throw new \RuntimeException('结果为错误状态，无法获取成功值');
        }
        return $this->value;
    }
    
    /**
     * 获取错误值
     * 
     * @return mixed
     * @throws \RuntimeException
     */
    public function getError()
    {
        if ($this->isSuccess) {
            throw new \RuntimeException('结果为成功状态，无法获取错误值');
        }
        return $this->error;
    }
    
    /**
     * 获取值或默认值
     * 
     * @param mixed $defaultValue 默认值
     * @return mixed
     */
    public function getValueOrDefault($defaultValue)
    {
        return $this->isSuccess ? $this->value : $defaultValue;
    }
    
    /**
     * 映射成功值
     * 
     * @template U
     * @param callable $mapper 映射函数
     * @return Result<U, E>
     */
    public function map(callable $mapper): self
    {
        if (!$this->isSuccess) {
            return $this;
        }
        
        try {
            $mappedValue = $mapper($this->value);
            return self::success($mappedValue);
        } catch (\Exception $e) {
            return self::error($e);
        }
    }
    
    /**
     * 映射错误值
     * 
     * @template F
     * @param callable $mapper 映射函数
     * @return Result<T, F>
     */
    public function mapError(callable $mapper): self
    {
        if ($this->isSuccess) {
            return $this;
        }
        
        try {
            $mappedError = $mapper($this->error);
            return self::error($mappedError);
        } catch (\Exception $e) {
            return self::error($e);
        }
    }
    
    /**
     * 平坦映射
     * 
     * @template U
     * @param callable $mapper 映射函数，返回Result
     * @return Result<U, E>
     */
    public function flatMap(callable $mapper): self
    {
        if (!$this->isSuccess) {
            return $this;
        }
        
        $result = $mapper($this->value);
        
        if (!($result instanceof self)) {
            throw new \InvalidArgumentException('flatMap的映射函数必须返回Result实例');
        }
        
        return $result;
    }
    
    /**
     * 恢复错误（错误时执行操作）
     * 
     * @param callable $recovery 恢复函数
     * @return Result<T, E>
     */
    public function recover(callable $recovery): self
    {
        if ($this->isSuccess) {
            return $this;
        }
        
        try {
            $recoveredValue = $recovery($this->error);
            return self::success($recoveredValue);
        } catch (\Exception $e) {
            return self::error($e);
        }
    }
    
    /**
     * 执行副作用操作
     * 
     * @param callable $onSuccess 成功时执行
     * @param callable $onError 错误时执行
     * @return self
     */
    public function effect(callable $onSuccess, callable $onError): self
    {
        if ($this->isSuccess) {
            $onSuccess($this->value);
        } else {
            $onError($this->error);
        }
        
        return $this;
    }
}

/**
 * 泛型列表容器
 * 模拟Go SDK中的切片泛型功能
 * 
 * @template T
 */
class TypeSafeList implements \Iterator, \Countable, \ArrayAccess
{
    /**
     * 数据存储
     * @var array
     */
    private $items = [];
    
    /**
     * 类型约束
     * @var string|null
     */
    private $typeConstraint;
    
    /**
     * 迭代器位置
     * @var int
     */
    private $position = 0;
    
    /**
     * 构造函数
     * 
     * @param array $items 初始项目
     * @param string|null $typeConstraint 类型约束
     */
    public function __construct(array $items = [], ?string $typeConstraint = null)
    {
        $this->typeConstraint = $typeConstraint;
        foreach ($items as $item) {
            $this->add($item);
        }
    }
    
    /**
     * 添加项目
     * 
     * @param mixed $item 项目
     * @return self
     */
    public function add($item): self
    {
        $this->validateType($item);
        $this->items[] = $item;
        return $this;
    }
    
    /**
     * 获取项目
     * 
     * @param int $index 索引
     * @return mixed
     * @throws \OutOfBoundsException
     */
    public function get(int $index)
    {
        if (!isset($this->items[$index])) {
            throw new \OutOfBoundsException("索引 {$index} 超出范围");
        }
        return $this->items[$index];
    }
    
    /**
     * 设置项目
     * 
     * @param int $index 索引
     * @param mixed $item 项目
     * @return self
     */
    public function set(int $index, $item): self
    {
        $this->validateType($item);
        $this->items[$index] = $item;
        return $this;
    }
    
    /**
     * 移除项目
     * 
     * @param int $index 索引
     * @return self
     */
    public function remove(int $index): self
    {
        if (isset($this->items[$index])) {
            array_splice($this->items, $index, 1);
        }
        return $this;
    }
    
    /**
     * 清空列表
     * 
     * @return self
     */
    public function clear(): self
    {
        $this->items = [];
        $this->position = 0;
        return $this;
    }
    
    /**
     * 检查是否为空
     * 
     * @return bool
     */
    public function isEmpty(): bool
    {
        return empty($this->items);
    }
    
    /**
     * 获取大小
     * 
     * @return int
     */
    public function size(): int
    {
        return count($this->items);
    }
    
    /**
     * 映射转换
     * 
     * @template U
     * @param callable $mapper 映射函数
     * @param string|null $targetType 目标类型
     * @return TypeSafeList<U>
     */
    public function map(callable $mapper, ?string $targetType = null): self
    {
        $newList = new self([], $targetType);
        foreach ($this->items as $item) {
            $newList->add($mapper($item));
        }
        return $newList;
    }
    
    /**
     * 过滤项目
     * 
     * @param callable $predicate 过滤条件
     * @return TypeSafeList<T>
     */
    public function filter(callable $predicate): self
    {
        $newList = new self([], $this->typeConstraint);
        foreach ($this->items as $item) {
            if ($predicate($item)) {
                $newList->add($item);
            }
        }
        return $newList;
    }
    
    /**
     * 查找第一个匹配项
     * 
     * @param callable $predicate 查找条件
     * @return TypeSafeContainer<T>
     */
    public function findFirst(callable $predicate): TypeSafeContainer
    {
        foreach ($this->items as $item) {
            if ($predicate($item)) {
                return TypeSafeContainer::of($item, $this->typeConstraint);
            }
        }
        return TypeSafeContainer::empty($this->typeConstraint);
    }
    
    /**
     * 折叠操作（reduce）
     * 
     * @template U
     * @param mixed $initial 初始值
     * @param callable $reducer 折叠函数
     * @return U
     */
    public function fold($initial, callable $reducer)
    {
        $accumulator = $initial;
        foreach ($this->items as $item) {
            $accumulator = $reducer($accumulator, $item);
        }
        return $accumulator;
    }
    
    /**
     * 转换为数组
     * 
     * @return array
     */
    public function toArray(): array
    {
        return $this->items;
    }
    
    /**
     * 类型验证
     * 
     * @param mixed $item 项目
     * @throws \InvalidArgumentException
     */
    private function validateType($item): void
    {
        if ($this->typeConstraint === null) {
            return;
        }
        
        if (!$this->matchesTypeConstraint($item, $this->typeConstraint)) {
            throw new \InvalidArgumentException(sprintf(
                '项目类型不匹配，期望: %s，实际: %s',
                $this->typeConstraint,
                $this->getActualType($item)
            ));
        }
    }
    
    /**
     * 检查类型约束匹配
     * 
     * @param mixed $item 项目
     * @param string $constraint 类型约束
     * @return bool
     */
    private function matchesTypeConstraint($item, string $constraint): bool
    {
        // 复用TypeSafeContainer的逻辑
        $container = new TypeSafeContainer();
        $method = new \ReflectionMethod($container, 'matchesTypeConstraint');
        $method->setAccessible(true);
        return $method->invoke($container, $item, $constraint);
    }
    
    /**
     * 获取实际类型
     * 
     * @param mixed $item 项目
     * @return string
     */
    private function getActualType($item): string
    {
        return is_object($item) ? get_class($item) : gettype($item);
    }
    
    // Iterator接口实现
    public function rewind(): void { $this->position = 0; }
    #[\ReturnTypeWillChange]
    public function current() { return $this->items[$this->position]; }
    #[\ReturnTypeWillChange]
    public function key() { return $this->position; }
    public function next(): void { ++$this->position; }
    public function valid(): bool { return isset($this->items[$this->position]); }
    
    // Countable接口实现
    public function count(): int { return count($this->items); }
    
    // ArrayAccess接口实现
    public function offsetExists($offset): bool { return isset($this->items[$offset]); }
    #[\ReturnTypeWillChange]
    public function offsetGet($offset) { return $this->items[$offset]; }
    public function offsetSet($offset, $value): void { 
        $this->validateType($value);
        if ($offset === null) {
            $this->items[] = $value;
        } else {
            $this->items[$offset] = $value;
        }
    }
    public function offsetUnset($offset): void { unset($this->items[$offset]); }
}