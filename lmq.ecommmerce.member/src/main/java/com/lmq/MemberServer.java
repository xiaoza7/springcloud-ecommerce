package com.lmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * @author lmq
 *
 */


@SpringBootApplication
@EnableEurekaClient
public class MemberServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SpringApplication.run(MemberServer.class, args);
	}

}
