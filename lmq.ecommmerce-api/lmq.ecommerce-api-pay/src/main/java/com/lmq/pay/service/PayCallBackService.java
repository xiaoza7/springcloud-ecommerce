package com.lmq.pay.service;



import java.util.Map;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmq.common.base.BaseResponse;



@RequestMapping("/callBackService")
public interface PayCallBackService {
	// // 同步回调
	@RequestMapping(value="/synCallBack",method = RequestMethod.GET)
	public BaseResponse synCallBack(@RequestParam Map<String, String> map,@RequestParam("haha")String haha);

	// // 异步回调
	@RequestMapping(value="/asynCallBack",method = RequestMethod.GET)
	public String asynCallBack(@RequestParam Map<String, String> map );
}
