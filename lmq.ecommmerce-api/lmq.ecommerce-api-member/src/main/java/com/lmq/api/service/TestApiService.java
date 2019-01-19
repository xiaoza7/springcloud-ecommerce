package com.lmq.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.lmq.common.base.BaseResponse;

@RequestMapping("/member")
public interface TestApiService {
	
	@RequestMapping("/test")
	public Map<String,Object>test(Integer id,String name);
	
	@RequestMapping("/testResponse")
	public BaseResponse testResponse();
	
	@RequestMapping("/setRedisTest")
	public BaseResponse setRedisTest(String key, String value);

	@RequestMapping("/getRedis")
	public BaseResponse getRedis(String key);


}
