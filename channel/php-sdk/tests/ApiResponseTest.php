<?php

namespace Maiyatian\Channel\PhpSdk\Tests;

use PHPUnit\Framework\TestCase;
use Maiyatian\Channel\PhpSdk\Client\ApiResponse;
use Maiyatian\Channel\PhpSdk\Client\HTTPResponse;
use Maiyatian\Channel\PhpSdk\Client\ApiException;

/**
 * ApiResponse 类单元测试
 * 测试类型安全的API响应包装器功能
 */
class ApiResponseTest extends TestCase
{
    /**
     * 测试成功响应创建
     */
    public function testSuccessResponseCreation()
    {
        $data = ['test' => 'value', 'number' => 42];
        $response = ApiResponse::success($data, 'success message');
        
        $this->assertTrue($response->isSuccess());
        $this->assertFalse($response->isError());
        $this->assertEquals(200, $response->getCode());
        $this->assertEquals('success message', $response->getMessage());
        $this->assertEquals($data, $response->getData());
        $this->assertTrue($response->hasData());
    }
    
    /**
     * 测试错误响应创建
     */
    public function testErrorResponseCreation()
    {
        $response = ApiResponse::error(400, 'bad request');
        
        $this->assertFalse($response->isSuccess());
        $this->assertTrue($response->isError());
        $this->assertEquals(400, $response->getCode());
        $this->assertEquals('bad request', $response->getMessage());
        $this->assertNull($response->getData());
        $this->assertFalse($response->hasData());
    }
    
    /**
     * 测试从HTTPResponse转换
     */
    public function testFromHttpResponse()
    {
        // 创建模拟HTTPResponse
        $httpResponse = new HTTPResponse();
        $httpResponse->Code = 200;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = '{"shop_id":"123","shop_name":"测试门店"}';
        
        $apiResponse = ApiResponse::from($httpResponse);
        
        $this->assertTrue($apiResponse->isSuccess());
        $this->assertEquals(200, $apiResponse->getCode());
        $this->assertEquals('ok', $apiResponse->getMessage());
        
        $data = $apiResponse->getData();
        $this->assertIsArray($data);
        $this->assertEquals('123', $data['shop_id']);
        $this->assertEquals('测试门店', $data['shop_name']);
    }
    
    /**
     * 测试从HTTPResponse转换时JSON解析失败
     */
    public function testFromHttpResponseWithInvalidJson()
    {
        $this->expectException(\InvalidArgumentException::class);
        $this->expectExceptionMessage('JSON解析失败');
        
        $httpResponse = new HTTPResponse();
        $httpResponse->Code = 200;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = 'invalid json{';
        
        ApiResponse::from($httpResponse);
    }
    
    /**
     * 测试使用自定义数据类转换
     */
    public function testFromHttpResponseWithCustomClass()
    {
        // 创建测试用的数据类
        class TestShopData {
            public $shop_id;
            public $shop_name;
        }
        
        $httpResponse = new HTTPResponse();
        $httpResponse->Code = 200;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = '{"shop_id":"456","shop_name":"自定义门店"}';
        
        $apiResponse = ApiResponse::from($httpResponse, TestShopData::class);
        
        $this->assertTrue($apiResponse->isSuccess());
        $data = $apiResponse->getData();
        $this->assertInstanceOf(TestShopData::class, $data);
        $this->assertEquals('456', $data->shop_id);
        $this->assertEquals('自定义门店', $data->shop_name);
    }
    
    /**
     * 测试使用转换函数
     */
    public function testFromHttpResponseWithCallable()
    {
        $httpResponse = new HTTPResponse();
        $httpResponse->Code = 200;
        $httpResponse->Message = 'ok';
        $httpResponse->Data = '{"value":10}';
        
        $converter = function($data) {
            return ['doubled_value' => $data['value'] * 2];
        };
        
        $apiResponse = ApiResponse::from($httpResponse, $converter);
        
        $this->assertTrue($apiResponse->isSuccess());
        $data = $apiResponse->getData();
        $this->assertEquals(['doubled_value' => 20], $data);
    }
    
    /**
     * 测试map函数式操作
     */
    public function testMapOperation()
    {
        $data = ['count' => 5];
        $response = ApiResponse::success($data);
        
        $mappedResponse = $response->map(function($data) {
            return ['count' => $data['count'], 'double_count' => $data['count'] * 2];
        });
        
        $this->assertTrue($mappedResponse->isSuccess());
        $mappedData = $mappedResponse->getData();
        $this->assertEquals(5, $mappedData['count']);
        $this->assertEquals(10, $mappedData['double_count']);
    }
    
    /**
     * 测试错误响应的map操作
     */
    public function testMapOnErrorResponse()
    {
        $response = ApiResponse::error(500, 'server error');
        
        $mappedResponse = $response->map(function($data) {
            return ['transformed' => true];
        });
        
        $this->assertTrue($mappedResponse->isError());
        $this->assertEquals(500, $mappedResponse->getCode());
        $this->assertNull($mappedResponse->getData());
    }
    
    /**
     * 测试map操作中的异常处理
     */
    public function testMapWithException()
    {
        $response = ApiResponse::success(['value' => 1]);
        
        $mappedResponse = $response->map(function($data) {
            throw new \Exception('mapping failed');
        });
        
        $this->assertTrue($mappedResponse->isError());
        $this->assertEquals(500, $mappedResponse->getCode());
        $this->assertStringContains('数据转换失败', $mappedResponse->getMessage());
    }
    
    /**
     * 测试filter过滤操作
     */
    public function testFilterOperation()
    {
        $response = ApiResponse::success(['status' => 'active']);
        
        $filteredResponse = $response->filter(function($response) {
            $data = $response->getData();
            return $data['status'] === 'active';
        });
        
        $this->assertTrue($filteredResponse->isSuccess());
        $this->assertEquals($response->getData(), $filteredResponse->getData());
    }
    
    /**
     * 测试filter操作失败
     */
    public function testFilterOperationFails()
    {
        $response = ApiResponse::success(['status' => 'inactive']);
        
        $filteredResponse = $response->filter(function($response) {
            $data = $response->getData();
            return $data['status'] === 'active';
        });
        
        $this->assertTrue($filteredResponse->isError());
        $this->assertEquals(400, $filteredResponse->getCode());
    }
    
    /**
     * 测试getDataOrDefault方法
     */
    public function testGetDataOrDefault()
    {
        // 成功响应
        $successResponse = ApiResponse::success(['value' => 'test']);
        $this->assertEquals(['value' => 'test'], $successResponse->getDataOrDefault([]));
        
        // 错误响应
        $errorResponse = ApiResponse::error(404, 'not found');
        $this->assertEquals(['default' => true], $errorResponse->getDataOrDefault(['default' => true]));
        
        // 空数据响应
        $emptyResponse = ApiResponse::success(null);
        $this->assertEquals('default', $emptyResponse->getDataOrDefault('default'));
    }
    
    /**
     * 测试getDataOrThrow方法
     */
    public function testGetDataOrThrow()
    {
        // 成功响应
        $successResponse = ApiResponse::success(['value' => 'test']);
        $this->assertEquals(['value' => 'test'], $successResponse->getDataOrThrow());
        
        // 错误响应应该抛出异常
        $errorResponse = ApiResponse::error(404, 'not found');
        $this->expectException(ApiException::class);
        $this->expectExceptionMessage('API错误 [404]: not found');
        $errorResponse->getDataOrThrow();
    }
    
    /**
     * 测试响应的字符串表示
     */
    public function testToString()
    {
        $response = ApiResponse::success(['test' => 'data']);
        $string = (string) $response;
        
        $this->assertStringContains('code=200', $string);
        $this->assertStringContains('message="ok"', $string);
        $this->assertStringContains('hasData=true', $string);
        $this->assertStringContains('dataType=array', $string);
    }
    
    /**
     * 测试toArray方法
     */
    public function testToArray()
    {
        $data = ['test' => 'value'];
        $response = ApiResponse::success($data, 'test message');
        $array = $response->toArray();
        
        $this->assertEquals(200, $array['code']);
        $this->assertEquals('test message', $array['message']);
        $this->assertEquals($data, $array['data']);
        $this->assertTrue($array['hasData']);
        $this->assertTrue($array['isSuccess']);
    }
    
    /**
     * 测试数据类型信息
     */
    public function testDataType()
    {
        // 数组类型
        $arrayResponse = ApiResponse::success(['test' => 'array']);
        $this->assertEquals('array', $arrayResponse->getDataType());
        
        // 字符串类型
        $stringResponse = ApiResponse::success('test string');
        $this->assertEquals('string', $stringResponse->getDataType());
        
        // 对象类型
        $object = new \stdClass();
        $object->test = 'object';
        $objectResponse = ApiResponse::success($object);
        $this->assertEquals('stdClass', $objectResponse->getDataType());
        
        // 空数据
        $nullResponse = ApiResponse::success(null);
        $this->assertEquals('NULL', $nullResponse->getDataType());
    }
}