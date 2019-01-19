package com.lmq.api.service;



import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmq.api.entity.UserEntity;
import com.lmq.common.base.BaseResponse;

/**
 * 
 * @author lmq
 *
 */

@RequestMapping("/member")
public interface MemberService {

	// 使用userId查找用户信息
	@RequestMapping("/findUserById")
	BaseResponse findUserById(Long userId);
	
	@RequestMapping("/regUser")
	BaseResponse regUser(@RequestBody UserEntity user);
	
	// 用户登录
		@RequestMapping("/login")
		BaseResponse login(@RequestBody UserEntity user);
	    // 使用token进行登录
		@RequestMapping("/findByTokenUser")
		BaseResponse findByTokenUser(@RequestParam("token")String token);
		 //使用openid查找用户信息
		@RequestMapping("/findByOpenIdUser")
		BaseResponse findByOpenIdUser(@RequestParam("openid") String openid);
		// 用户登录
		@RequestMapping("/qqLogin")
		BaseResponse qqLogin(@RequestBody UserEntity user);
}
