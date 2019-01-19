package com.lmq.feignservice;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import com.lmq.pay.service.PayCallBackService;


@Component
@FeignClient("pay")
public interface CallBackServiceFegin extends PayCallBackService {

}
