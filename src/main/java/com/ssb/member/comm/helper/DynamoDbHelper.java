package com.ssb.member.comm.helper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DynamoDbHelper {

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	public void query() {
		
	}
}
