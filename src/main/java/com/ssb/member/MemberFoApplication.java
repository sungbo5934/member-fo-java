package com.ssb.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@ComponentScan({"com.ssb.member.*","com.ssb.comm.*"})
public class MemberFoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberFoApplication.class, args);
	}

}
