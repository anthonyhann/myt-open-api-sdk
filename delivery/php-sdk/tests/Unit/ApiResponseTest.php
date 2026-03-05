<?php

namespace Tests\Unit;

use PHPUnit\Framework\TestCase;
use Maiyatian\Delivery\PhpSdk\Client\ApiResponse;
use Maiyatian\Delivery\PhpSdk\Client\HTTPResponse;
use Maiyatian\Delivery\PhpSdk\Client\ApiException;
use Maiyatian\Delivery\PhpSdk\Consts\Consts;

/**
 * API响应包装器测试
 * 测试类型安全的API响应处理功能
 */
class ApiResponseTest extends TestCase
{
    /**
     * 测试成功响应创建
     */
    public function testSuccessResponseCreation()
    {
        $data = ['order_id' => 'ORDER_001', 'status' => 'success'];
        $response = ApiResponse::success($data);
        
        $this->assertInstanceOf(ApiResponse::class, $response);
        $this->assertEquals(Consts::SUCCESS_CODE, $response->getCode());
        $this->assertEquals('ok', $response->getMessage());
        $this->assertEquals($data, $response->getData());
        $this->assertTrue($response->isSuccess());
        $this->assertFalse($response->isError());
        $this->assertTrue($response->hasData());
    }

    /**
     * 测试自定义成功消息
     */
    public function testSuccessResponseWithCustomMessage()
    {
        $data = ['result' => 'completed'];
        $message = 'Operation completed successfully';
        $response = ApiResponse::success($data, $message);
        
        $this->assertEquals($message, $response->getMessage());
        $this->assertEquals($data, $response->getData());
    }

    /**
     * 测试错误响应创建
     */
    public function testErrorResponseCreation()
    {
        $code = 400;
        $message = 'Bad Request';
        $response = ApiResponse::error($code, $message);
        
        $this->assertEquals($code, $response->getCode());
        $this->assertEquals($message, $response->getMessage());
        $this->assertNull($response->getData());
        $this->assertFalse($response->isSuccess());
        $this->assertTrue($response->isError());
        $this->assertFalse($response->hasData());
    }

    /**
     * 测试从HTTPResponse转换 - 成功案例
     */
    public function testFromHttpResponseSuccess()
    {
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 200;
        $httpResponse->Code = Consts::SUCCESS_CODE;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = json_encode(['order_id' => 'ORDER_123']);
        
        $apiResponse = ApiResponse::from($httpResponse);
        
        $this->assertTrue($apiResponse->isSuccess());
        $this->assertEquals(Consts::SUCCESS_CODE, $apiResponse->getCode());
        $this->assertEquals('ok', $apiResponse->getMessage());
        
        $data = $apiResponse->getData();
        $this->assertIsArray($data);
        $this->assertEquals('ORDER_123', $data['order_id']);
    }

    /**
     * 测试从HTTPResponse转换 - 错误案例
     */
    public function testFromHttpResponseError()
    {
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 400;
        $httpResponse->Code = 400;
        $httpResponse->Message = 'Invalid parameters';
        $httpResponse->Data = '';
        
        $apiResponse = ApiResponse::from($httpResponse);
        
        $this->assertTrue($apiResponse->isError());
        $this->assertEquals(400, $apiResponse->getCode());
        $this->assertEquals('Invalid parameters', $apiResponse->getMessage());
        $this->assertNull($apiResponse->getData());
    }

    /**
     * 测试指定数据类型转换
     */
    public function testFromHttpResponseWithDataClass()
    {
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 200;
        $httpResponse->Code = Consts::SUCCESS_CODE;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = json_encode([
            'name' => 'Test Item',
            'price' => 99.99,
            'quantity' => 2
        ]);
        
        // 使用类名进行转换
        $apiResponse = ApiResponse::from($httpResponse, \stdClass::class);
        
        $this->assertTrue($apiResponse->isSuccess());
        $data = $apiResponse->getData();
        $this->assertInstanceOf(\stdClass::class, $data);
    }

    /**
     * 测试自定义转换函数
     */
    public function testFromHttpResponseWithCustomConverter()
    {
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 200;
        $httpResponse->Code = Consts::SUCCESS_CODE;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = json_encode(['amount' => '123.45']);
        
        // 使用自定义转换函数
        $converter = function($data) {
            return ['amount_float' => (float)$data['amount']];
        };
        
        $apiResponse = ApiResponse::from($httpResponse, $converter);
        
        $this->assertTrue($apiResponse->isSuccess());
        $data = $apiResponse->getData();
        $this->assertEquals(['amount_float' => 123.45], $data);
    }

    /**
     * 测试JSON解析错误处理
     */
    public function testFromHttpResponseInvalidJson()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('JSON解析失败');
        
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 200;
        $httpResponse->Code = Consts::SUCCESS_CODE;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = 'invalid json {';
        
        ApiResponse::from($httpResponse);
    }

    /**
     * 测试数据映射转换
     */
    public function testMapOperation()
    {
        $data = ['price' => '99.99', 'currency' => 'USD'];
        $response = ApiResponse::success($data);
        
        // 映射价格为浮点数
        $mappedResponse = $response->map(function($data) {
            return (float)$data['price'];
        });
        
        $this->assertTrue($mappedResponse->isSuccess());
        $this->assertEquals(99.99, $mappedResponse->getData());
        $this->assertIsFloat($mappedResponse->getData());
    }

    /**
     * 测试错误响应的映射操作
     */
    public function testMapOnErrorResponse()
    {
        $response = ApiResponse::error(500, 'Server Error');
        
        $mappedResponse = $response->map(function($data) {
            return 'This should not be called';
        });
        
        // 错误响应的映射应该返回相同的错误响应
        $this->assertTrue($mappedResponse->isError());
        $this->assertEquals(500, $mappedResponse->getCode());
        $this->assertNull($mappedResponse->getData());
    }

    /**
     * 测试映射操作异常处理
     */
    public function testMapOperationWithException()
    {
        $data = ['value' => 'test'];
        $response = ApiResponse::success($data);
        
        $mappedResponse = $response->map(function($data) {
            throw new \Exception('Mapping failed');
        });
        
        $this->assertTrue($mappedResponse->isError());
        $this->assertEquals(500, $mappedResponse->getCode());
        $this->assertStringContainsString('数据转换失败', $mappedResponse->getMessage());
    }

    /**
     * 测试过滤操作
     */
    public function testFilterOperation()
    {
        $data = ['status' => 'active', 'count' => 5];
        $response = ApiResponse::success($data);
        
        // 过滤条件：status为active
        $filteredResponse = $response->filter(function($response) {
            $data = $response->getData();
            return $data['status'] === 'active';
        });
        
        $this->assertTrue($filteredResponse->isSuccess());
        $this->assertEquals($data, $filteredResponse->getData());
    }

    /**
     * 测试过滤操作 - 不满足条件
     */
    public function testFilterOperationFailed()
    {
        $data = ['status' => 'inactive', 'count' => 5];
        $response = ApiResponse::success($data);
        
        $filteredResponse = $response->filter(function($response) {
            $data = $response->getData();
            return $data['status'] === 'active';
        });
        
        $this->assertTrue($filteredResponse->isError());
        $this->assertEquals(400, $filteredResponse->getCode());
    }

    /**
     * 测试获取数据或默认值
     */
    public function testGetDataOrDefault()
    {
        // 成功响应
        $successResponse = ApiResponse::success(['value' => 'test']);
        $this->assertEquals(['value' => 'test'], $successResponse->getDataOrDefault([]));
        
        // 错误响应
        $errorResponse = ApiResponse::error(404, 'Not Found');
        $this->assertEquals([], $errorResponse->getDataOrDefault([]));
        
        // null数据
        $nullResponse = ApiResponse::success(null);
        $this->assertEquals('default', $nullResponse->getDataOrDefault('default'));
    }

    /**
     * 测试获取数据或抛出异常
     */
    public function testGetDataOrThrow()
    {
        // 成功响应
        $successResponse = ApiResponse::success(['value' => 'test']);
        $this->assertEquals(['value' => 'test'], $successResponse->getDataOrThrow());
        
        // 错误响应应该抛出异常
        $this->expectException(ApiException::class);
        $this->expectExceptionMessage('API错误 [404]: Not Found');
        
        $errorResponse = ApiResponse::error(404, 'Not Found');
        $errorResponse->getDataOrThrow();
    }

    /**
     * 测试配送业务特定方法
     */
    public function testDeliverySpecificMethods()
    {
        // 配送成功响应
        $deliveryData = ['status' => 2, 'driver' => ['name' => 'John']];
        $response = ApiResponse::success($deliveryData);
        
        $this->assertTrue($response->isDeliverySuccess());
        $this->assertEquals(2, $response->getDeliveryStatus());
        $this->assertFalse($response->isLocationChange());
        
        // 位置变更响应
        $locationData = ['latitude' => 39.9042, 'longitude' => 116.4074];
        $locationResponse = ApiResponse::success($locationData);
        
        $this->assertTrue($locationResponse->isLocationChange());
    }

    /**
     * 测试配送状态获取 - 不同字段名
     */
    public function testGetDeliveryStatusVariableFields()
    {
        // 使用status字段
        $response1 = ApiResponse::success(['status' => 3]);
        $this->assertEquals(3, $response1->getDeliveryStatus());
        
        // 使用delivery_status字段
        $response2 = ApiResponse::success(['delivery_status' => 1]);
        $this->assertEquals(1, $response2->getDeliveryStatus());
        
        // 没有状态字段
        $response3 = ApiResponse::success(['other_field' => 'value']);
        $this->assertNull($response3->getDeliveryStatus());
        
        // 错误响应
        $errorResponse = ApiResponse::error(500, 'Error');
        $this->assertNull($errorResponse->getDeliveryStatus());
    }

    /**
     * 测试字符串表示
     */
    public function testToString()
    {
        $response = ApiResponse::success(['key' => 'value']);
        $string = (string)$response;
        
        $this->assertStringContainsString('ApiResponse');
        $this->assertStringContainsString('code=200');
        $this->assertStringContainsString('message="ok"');
        $this->assertStringContainsString('hasData=true');
    }

    /**
     * 测试转换为数组
     */
    public function testToArray()
    {
        $data = ['order_id' => 'ORDER_001', 'latitude' => 39.9042, 'longitude' => 116.4074];
        $response = ApiResponse::success($data);
        
        $array = $response->toArray();
        
        $this->assertIsArray($array);
        $this->assertEquals(Consts::SUCCESS_CODE, $array['code']);
        $this->assertEquals('ok', $array['message']);
        $this->assertEquals($data, $array['data']);
        $this->assertTrue($array['hasData']);
        $this->assertTrue($array['isSuccess']);
        $this->assertTrue($array['isDeliverySuccess']);
        $this->assertTrue($array['isLocationChange']);
        $this->assertNull($array['deliveryStatus']); // 因为没有正确的status字段
    }

    /**
     * 测试原始响应访问
     */
    public function testGetRawResponse()
    {
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 200;
        $httpResponse->Code = Consts::SUCCESS_CODE;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = '{}';
        
        $apiResponse = ApiResponse::from($httpResponse);
        
        $this->assertSame($httpResponse, $apiResponse->getRawResponse());
    }

    /**
     * 测试数据类型信息
     */
    public function testGetDataType()
    {
        // 数组数据
        $arrayResponse = ApiResponse::success(['key' => 'value']);
        $this->assertEquals('array', $arrayResponse->getDataType());
        
        // 字符串数据
        $stringResponse = ApiResponse::success('string value');
        $this->assertEquals('string', $stringResponse->getDataType());
        
        // 对象数据
        $objectResponse = ApiResponse::success(new \stdClass());
        $this->assertEquals('stdClass', $objectResponse->getDataType());
        
        // null数据
        $nullResponse = ApiResponse::success(null);
        $this->assertEquals('NULL', $nullResponse->getDataType());
    }

    /**
     * 测试实例化对象的私有方法
     */
    public function testInstantiateFromArray()
    {
        // 创建一个测试类
        $testClass = new class {
            public $publicProp;
            private $privateProp;
            
            public function setPrivateProp($value) {
                $this->privateProp = $value;
            }
            
            public function getPrivateProp() {
                return $this->privateProp;
            }
        };
        
        $className = get_class($testClass);
        
        $httpResponse = new HTTPResponse();
        $httpResponse->StatusCode = 200;
        $httpResponse->Code = Consts::SUCCESS_CODE;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = json_encode([
            'publicProp' => 'public value',
            'privateProp' => 'private value'
        ]);
        
        $apiResponse = ApiResponse::from($httpResponse, $className);
        
        $this->assertTrue($apiResponse->isSuccess());
        $this->assertInstanceOf($className, $apiResponse->getData());
    }
}