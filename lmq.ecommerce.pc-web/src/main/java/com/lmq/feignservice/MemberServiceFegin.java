package com.lmq.feignservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;


import com.lmq.api.service.MemberService;

/**
 * 
 * @author lmq
 *
 */

@Component
@FeignClient("member")
public interface MemberServiceFegin extends MemberService {

}
