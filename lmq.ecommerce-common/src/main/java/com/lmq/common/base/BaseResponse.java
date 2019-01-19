package com.lmq.common.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse {
	private Integer rtnCode;
	
	public BaseResponse() {
		
	}
	
	public BaseResponse(int rtnCode, String msg, Object obj) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.obj = obj;
	}

	private String msg;
	private Object obj;

}
