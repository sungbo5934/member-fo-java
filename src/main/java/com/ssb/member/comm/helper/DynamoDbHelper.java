package com.ssb.member.comm.helper;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DynamoDbHelper {

	@Autowired
	private AmazonDynamoDB ddb;

	public Map<String,AttributeValue> getItem(String tableName, Map<String, AttributeValue> key) {

		GetItemRequest getItemRequest = null;
		getItemRequest = new GetItemRequest()
				.withTableName(tableName)
				.withKey(key);
		
		Map<String,AttributeValue> resultItem = ddb.getItem(getItemRequest).getItem();
		
		return resultItem;
				
	}
}
