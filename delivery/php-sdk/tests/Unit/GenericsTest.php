<?php

namespace Tests\Unit;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\PhpSdk\Generics\TypeSafeContainer;
use Maiyatian\Delivery\PhpSdk\Generics\TypeSafeList;
use Maiyatian\Delivery\PhpSdk\Generics\Result;

/**
 * 泛型容器测试
 * 测试PHP中Go泛型T的等价实现
 */
class GenericsTest extends TestCase
{
    /**
     * 测试TypeSafeContainer基本操作
     */
    public function testTypeSafeContainerBasics()
    {
        $container = TypeSafeContainer::of('test value', 'string');
        
        $this->assertTrue($container->hasValue());
        $this->assertFalse($container->isEmpty());
        $this->assertEquals('test value', $container->get());
        $this->assertEquals('test value', $container->getOrDefault('default'));
        $this->assertEquals('test value', $container->getOrThrow());
    }

    /**
     * 测试TypeSafeContainer空容器
     */
    public function testTypeSafeContainerEmpty()
    {
        $container = TypeSafeContainer::empty('string');
        
        $this->assertFalse($container->hasValue());
        $this->assertTrue($container->isEmpty());
        $this->assertNull($container->get());
        $this->assertEquals('default', $container->getOrDefault('default'));
    }

    /**
     * 测试TypeSafeContainer空容器抛出异常
     */
    public function testTypeSafeContainerEmptyThrow()
    {
        $this->expectException(\RuntimeException::class);
        $this->expectExceptionMessage('容器为空，无法获取数据');
        
        $container = TypeSafeContainer::empty();
        $container->getOrThrow();
    }

    /**
     * 测试TypeSafeContainer类型约束
     */
    public function testTypeSafeContainerTypeConstraints()
    {
        // 正确的类型
        $intContainer = TypeSafeContainer::of(42, 'int');
        $this->assertEquals(42, $intContainer->get());
        
        $stringContainer = TypeSafeContainer::of('hello', 'string');
        $this->assertEquals('hello', $stringContainer->get());
        
        $arrayContainer = TypeSafeContainer::of([1, 2, 3], 'array');
        $this->assertEquals([1, 2, 3], $arrayContainer->get());
    }

    /**
     * 测试TypeSafeContainer类型约束失败
     */
    public function testTypeSafeContainerTypeConstraintFailure()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('数据类型不匹配');
        
        new TypeSafeContainer('string value', 'int');
    }

    /**
     * 测试TypeSafeContainer类型验证
     */
    public function testTypeSafeContainerTypeValidation()
    {
        // 测试各种基本类型
        $this->assertInstanceOf(TypeSafeContainer::class, TypeSafeContainer::of(123, 'int'));
        $this->assertInstanceOf(TypeSafeContainer::class, TypeSafeContainer::of(12.34, 'float'));
        $this->assertInstanceOf(TypeSafeContainer::class, TypeSafeContainer::of(true, 'bool'));
        $this->assertInstanceOf(TypeSafeContainer::class, TypeSafeContainer::of([], 'array'));
        $this->assertInstanceOf(TypeSafeContainer::class, TypeSafeContainer::of(new \stdClass(), 'object'));
        $this->assertInstanceOf(TypeSafeContainer::class, TypeSafeContainer::of(null, 'null'));
    }

    /**
     * 测试TypeSafeContainer类或接口类型验证
     */
    public function testTypeSafeContainerClassTypeValidation()
    {
        $object = new \stdClass();
        $container = TypeSafeContainer::of($object, \stdClass::class);
        
        $this->assertSame($object, $container->get());
    }

    /**
     * 测试TypeSafeContainer映射操作
     */
    public function testTypeSafeContainerMap()
    {
        $container = TypeSafeContainer::of(5, 'int');
        
        $mappedContainer = $container->map(fn($x) => $x * 2, 'int');
        
        $this->assertEquals(10, $mappedContainer->get());
    }

    /**
     * 测试TypeSafeContainer空容器映射
     */
    public function testTypeSafeContainerMapEmpty()
    {
        $container = TypeSafeContainer::empty('int');
        
        $mappedContainer = $container->map(fn($x) => $x * 2, 'int');
        
        $this->assertTrue($mappedContainer->isEmpty());
    }

    /**
     * 测试TypeSafeContainer映射异常
     */
    public function testTypeSafeContainerMapException()
    {
        $this->expectException(\RuntimeException::class);
        $this->expectExceptionMessage('数据映射失败');
        
        $container = TypeSafeContainer::of(5, 'int');
        $container->map(function($x) {
            throw new \Exception('Test exception');
        });
    }

    /**
     * 测试TypeSafeContainer过滤操作
     */
    public function testTypeSafeContainerFilter()
    {
        $container = TypeSafeContainer::of(10, 'int');
        
        // 满足条件
        $filteredContainer = $container->filter(fn($x) => $x > 5);
        $this->assertTrue($filteredContainer->hasValue());
        $this->assertEquals(10, $filteredContainer->get());
        
        // 不满足条件
        $filteredContainer2 = $container->filter(fn($x) => $x > 15);
        $this->assertTrue($filteredContainer2->isEmpty());
    }

    /**
     * 测试TypeSafeContainer平坦映射
     */
    public function testTypeSafeContainerFlatMap()
    {
        $container = TypeSafeContainer::of(5, 'int');
        
        $flatMappedContainer = $container->flatMap(function($x) {
            return TypeSafeContainer::of($x * 3, 'int');
        });
        
        $this->assertEquals(15, $flatMappedContainer->get());
    }

    /**
     * 测试TypeSafeContainer平坦映射类型错误
     */
    public function testTypeSafeContainerFlatMapTypeError()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('flatMap的映射函数必须返回TypeSafeContainer实例');
        
        $container = TypeSafeContainer::of(5, 'int');
        $container->flatMap(fn($x) => $x * 2); // 返回int而不是Container
    }

    /**
     * 测试TypeSafeContainer管道操作
     */
    public function testTypeSafeContainerPipe()
    {
        $container = TypeSafeContainer::of(5, 'int');
        
        $result = $container->pipe(function($container) {
            return $container->map(fn($x) => $x + 10);
        });
        
        $this->assertEquals(15, $result->get());
    }

    /**
     * 测试TypeSafeContainer合并操作
     */
    public function testTypeSafeContainerCombine()
    {
        $container1 = TypeSafeContainer::of(5, 'int');
        $container2 = TypeSafeContainer::of(3, 'int');
        
        $combined = $container1->combine($container2, fn($a, $b) => $a + $b);
        
        $this->assertEquals(8, $combined->get());
    }

    /**
     * 测试TypeSafeContainer合并空容器
     */
    public function testTypeSafeContainerCombineEmpty()
    {
        $container1 = TypeSafeContainer::of(5, 'int');
        $container2 = TypeSafeContainer::empty('int');
        
        $combined = $container1->combine($container2, fn($a, $b) => $a + $b);
        
        $this->assertTrue($combined->isEmpty());
    }

    /**
     * 测试Result成功创建
     */
    public function testResultSuccess()
    {
        $result = Result::success('success data');
        
        $this->assertTrue($result->isSuccess());
        $this->assertFalse($result->isError());
        $this->assertEquals('success data', $result->getValue());
        $this->assertEquals('success data', $result->getValueOrDefault('default'));
    }

    /**
     * 测试Result错误创建
     */
    public function testResultError()
    {
        $result = Result::error('error message');
        
        $this->assertTrue($result->isError());
        $this->assertFalse($result->isSuccess());
        $this->assertEquals('error message', $result->getError());
        $this->assertEquals('default', $result->getValueOrDefault('default'));
    }

    /**
     * 测试Result获取值异常
     */
    public function testResultGetValueException()
    {
        $this->expectException(\RuntimeException::class);
        $this->expectExceptionMessage('结果为错误状态，无法获取成功值');
        
        $result = Result::error('error');
        $result->getValue();
    }

    /**
     * 测试Result获取错误异常
     */
    public function testResultGetErrorException()
    {
        $this->expectException(\RuntimeException::class);
        $this->expectExceptionMessage('结果为成功状态，无法获取错误值');
        
        $result = Result::success('success');
        $result->getError();
    }

    /**
     * 测试Result映射操作
     */
    public function testResultMap()
    {
        $result = Result::success(5);
        $mappedResult = $result->map(fn($x) => $x * 2);
        
        $this->assertTrue($mappedResult->isSuccess());
        $this->assertEquals(10, $mappedResult->getValue());
        
        // 错误情况下映射不执行
        $errorResult = Result::error('error');
        $mappedErrorResult = $errorResult->map(fn($x) => $x * 2);
        
        $this->assertTrue($mappedErrorResult->isError());
        $this->assertEquals('error', $mappedErrorResult->getError());
    }

    /**
     * 测试Result映射错误
     */
    public function testResultMapError()
    {
        $result = Result::error('original error');
        $mappedResult = $result->mapError(fn($err) => "Processed: {$err}");
        
        $this->assertTrue($mappedResult->isError());
        $this->assertEquals('Processed: original error', $mappedResult->getError());
    }

    /**
     * 测试Result恢复操作
     */
    public function testResultRecover()
    {
        $result = Result::error('error');
        $recoveredResult = $result->recover(fn($err) => "recovered from {$err}");
        
        $this->assertTrue($recoveredResult->isSuccess());
        $this->assertEquals('recovered from error', $recoveredResult->getValue());
        
        // 成功状态不需要恢复
        $successResult = Result::success('success');
        $notRecovered = $successResult->recover(fn($err) => 'should not execute');
        
        $this->assertTrue($notRecovered->isSuccess());
        $this->assertEquals('success', $notRecovered->getValue());
    }

    /**
     * 测试Result副作用操作
     */
    public function testResultEffect()
    {
        $successExecuted = false;
        $errorExecuted = false;
        
        $successResult = Result::success('data');
        $successResult->effect(
            function($data) use (&$successExecuted) {
                $successExecuted = true;
            },
            function($error) use (&$errorExecuted) {
                $errorExecuted = true;
            }
        );
        
        $this->assertTrue($successExecuted);
        $this->assertFalse($errorExecuted);
        
        $successExecuted = false;
        $errorExecuted = false;
        
        $errorResult = Result::error('error');
        $errorResult->effect(
            function($data) use (&$successExecuted) {
                $successExecuted = true;
            },
            function($error) use (&$errorExecuted) {
                $errorExecuted = true;
            }
        );
        
        $this->assertFalse($successExecuted);
        $this->assertTrue($errorExecuted);
    }

    /**
     * 测试TypeSafeList基本操作
     */
    public function testTypeSafeListBasics()
    {
        $list = new TypeSafeList([1, 2, 3], 'int');
        
        $this->assertEquals(3, $list->count());
        $this->assertEquals(3, $list->size());
        $this->assertFalse($list->isEmpty());
        $this->assertEquals(2, $list->get(1));
    }

    /**
     * 测试TypeSafeList添加和移除
     */
    public function testTypeSafeListAddRemove()
    {
        $list = new TypeSafeList([], 'string');
        
        $list->add('first');
        $list->add('second');
        
        $this->assertEquals(2, $list->count());
        $this->assertEquals('first', $list->get(0));
        $this->assertEquals('second', $list->get(1));
        
        $list->remove(0);
        $this->assertEquals(1, $list->count());
        $this->assertEquals('second', $list->get(0));
    }

    /**
     * 测试TypeSafeList类型约束
     */
    public function testTypeSafeListTypeConstraint()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('项目类型不匹配');
        
        $list = new TypeSafeList([], 'int');
        $list->add('string value'); // 应该失败
    }

    /**
     * 测试TypeSafeList映射操作
     */
    public function testTypeSafeListMap()
    {
        $list = new TypeSafeList([1, 2, 3], 'int');
        $mappedList = $list->map(fn($x) => $x * 2, 'int');
        
        $this->assertEquals([2, 4, 6], $mappedList->toArray());
    }

    /**
     * 测试TypeSafeList过滤操作
     */
    public function testTypeSafeListFilter()
    {
        $list = new TypeSafeList([1, 2, 3, 4, 5], 'int');
        $filteredList = $list->filter(fn($x) => $x % 2 === 0);
        
        $this->assertEquals([2, 4], $filteredList->toArray());
    }

    /**
     * 测试TypeSafeList查找操作
     */
    public function testTypeSafeListFindFirst()
    {
        $list = new TypeSafeList(['apple', 'banana', 'cherry'], 'string');
        
        $found = $list->findFirst(fn($x) => strlen($x) > 5);
        $this->assertTrue($found->hasValue());
        $this->assertEquals('banana', $found->get());
        
        $notFound = $list->findFirst(fn($x) => strlen($x) > 10);
        $this->assertTrue($notFound->isEmpty());
    }

    /**
     * 测试TypeSafeList折叠操作
     */
    public function testTypeSafeListFold()
    {
        $list = new TypeSafeList([1, 2, 3, 4], 'int');
        
        $sum = $list->fold(0, fn($acc, $x) => $acc + $x);
        $this->assertEquals(10, $sum);
        
        $product = $list->fold(1, fn($acc, $x) => $acc * $x);
        $this->assertEquals(24, $product);
    }

    /**
     * 测试TypeSafeList迭代器接口
     */
    public function testTypeSafeListIterator()
    {
        $list = new TypeSafeList(['a', 'b', 'c'], 'string');
        
        $result = [];
        foreach ($list as $index => $value) {
            $result[$index] = $value;
        }
        
        $this->assertEquals([0 => 'a', 1 => 'b', 2 => 'c'], $result);
    }

    /**
     * 测试TypeSafeList数组访问接口
     */
    public function testTypeSafeListArrayAccess()
    {
        $list = new TypeSafeList(['x', 'y'], 'string');
        
        // 读取
        $this->assertEquals('x', $list[0]);
        $this->assertEquals('y', $list[1]);
        
        // 写入
        $list[0] = 'modified';
        $this->assertEquals('modified', $list[0]);
        
        // 追加
        $list[] = 'appended';
        $this->assertEquals('appended', $list[2]);
        
        // 检查存在
        $this->assertTrue(isset($list[0]));
        $this->assertFalse(isset($list[10]));
        
        // 删除
        unset($list[1]);
        $this->assertFalse(isset($list[1]));
    }

    /**
     * 测试自定义类型验证器
     */
    public function testCustomTypeValidator()
    {
        $validator = function($data) {
            return is_int($data) && $data > 0;
        };
        
        $container = new TypeSafeContainer(5, null, $validator);
        $this->assertEquals(5, $container->get());
        
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('数据不满足自定义类型约束');
        
        new TypeSafeContainer(-5, null, $validator);
    }

    /**
     * 测试容器字符串表示
     */
    public function testContainerToString()
    {
        $container = TypeSafeContainer::of('test', 'string');
        $string = (string)$container;
        
        $this->assertStringContainsString('TypeSafeContainer');
        $this->assertStringContainsString('type=string');
        $this->assertStringContainsString('hasValue=true');
        $this->assertStringContainsString('"test"');
    }
}