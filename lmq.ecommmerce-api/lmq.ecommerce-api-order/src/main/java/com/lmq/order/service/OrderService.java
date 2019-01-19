package com.lmq.order.service;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmq.common.base.BaseResponse;



@RequestMapping("/order")
public interface OrderService {

	@RequestMapping("/updateOrderIdInfo")
	BaseResponse updateOrderIdInfo(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
			@RequestParam("orderNumber") String orderNumber);
}
