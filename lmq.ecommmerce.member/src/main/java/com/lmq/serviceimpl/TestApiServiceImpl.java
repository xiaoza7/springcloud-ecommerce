package com.lmq.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.api.service.TestApiService;
import com.lmq.common.base.BaseApiService;
import com.lmq.common.base.BaseResponse;

/**
 * 
 * @author lmq
 *
 */

@RestController
public class TestApiServiceImpl extends BaseApiService implements TestApiService{
	
	@Override
	public Map<String, Object> test(Integer id, String name) {
		// TODO Auto-generated method stub
		Map<String,Object>map= new HashMap<String,Object>();
		map.put("errcode", "200");
		map.put("msg", "success");
		map.put("data", "id: "+id+","+"name: "+name);
		return map;
	}

	@Override
	public BaseResponse testResponse() {
		// TODO Auto-generated method stub
		return setResultSuccess();
	}

	@Override
	public BaseResponse setRedisTest(String key, String value) {
		// TODO Auto-generated method stub
		baseRedisService.setString(key, value);
		return setResultSuccess();
	}

	@Override
	public BaseResponse getRedis(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
