package com.lmq.pay.service;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/pay")
public interface PayOrderService {

	@RequestMapping("/payOrder")
	public String payOrder(@RequestParam("orderId") String orderId,@RequestParam("temp") int temp);

}
