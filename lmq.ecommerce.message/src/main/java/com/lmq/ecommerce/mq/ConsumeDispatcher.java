package com.lmq.ecommerce.mq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lmq.common.constants.Constants;
import com.lmq.ecommerce.adapter.MessageAdapter;
import com.lmq.ecommerce.email.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumeDispatcher {
	
	@Autowired
	private EmailService emailService;
	private MessageAdapter messageAdapter;
   
	// 监听消息
   @JmsListener(destination = "messages_queue")
	public void dispatch(String json) {
	   log.info("#####消息服务平台接受消息内容:{}#####", json);
		if (StringUtils.isEmpty(json)) {
			return;
		}
		
//		JSONObject js1=new JSONObject().parseObject(json);
//		JSONObject js2=js1.getJSONObject("header");
//		js2.getJSONObject("interfaceTYpe")
		JSONObject rootJSON = new JSONObject().parseObject(json);
		JSONObject header = rootJSON.getJSONObject("header");
		String interfaceType = header.getString("interfaceType");

		if (StringUtils.isEmpty(interfaceType)) {
			return;
		}
		// 判断接口类型是否为发邮件
		if (interfaceType.equals(Constants.MSG_EMAIL)) {
			messageAdapter = emailService;
		}
		if (messageAdapter == null) {
			return;
		}
		JSONObject contentJson = rootJSON.getJSONObject("content");
		messageAdapter.sendMsg(contentJson);
   }
}
