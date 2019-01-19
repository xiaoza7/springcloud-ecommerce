package com.lmq.pay.service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmq.common.base.BaseResponse;
import com.lmq.pay.entity.PaymentInfo;



@RequestMapping("/pay")
public interface PayService {
    //创建支付令牌
	@RequestMapping("/createPayToken")
	public BaseResponse createToken(@RequestBody PaymentInfo PaymentInfo);
	// 使用支付令牌查找支付信息
	@RequestMapping("/findPayToken")
	public BaseResponse findPayToken (@RequestParam("payToken") String  payToken);

}