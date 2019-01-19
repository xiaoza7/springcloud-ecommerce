package com.lmq.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmq.common.constants.Constants;

/**
 * 
 * @author lmq
 *
 */

@Component
public class BaseApiService {
	
	@Autowired
	protected BaseRedisService baseRedisService;
	
	// 返回成功 ,data值为null
		public BaseResponse setResultSuccess() {
			return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
		}
		// 返回成功 ,data可传
		public BaseResponse setResultSuccess(Object data) {
			return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
		}
	
		// 返回错误，可以传msg
		public BaseResponse setResultError(String msg) {
			return setResult(Constants.HTTP_RES_CODE_500, msg, null);
		}
		public BaseResponse setResultError(Integer code,String msg) {
			return setResult(code, msg, null);
		}
	
	public BaseResponse setResult(int rtnCode, String msg, Object obj) {
		return new BaseResponse( rtnCode,  msg, obj);
	}

}
