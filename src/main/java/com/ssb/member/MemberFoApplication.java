package com.ssb.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MemberFoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberFoApplication.class, args);
	}

}
