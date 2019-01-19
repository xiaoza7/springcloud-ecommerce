package com.lmq.pay.feignservice;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import com.lmq.order.service.OrderService;



@Component
@FeignClient("order")
public interface OrderServiceFegin extends OrderService {

}
