package com.ssb.member.comm.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DynamoDbHelper {

	@Autowired
	private AmazonDynamoDB ddb;
	
	@Value("${aws.dynamodb.memberTbl}")
	private String memberTbl;
	
	@Value("${aws.dynamodb.memberPartition}")
	private String memberPartition;
	
	public void initPartition(Map<String, AttributeValue> item) {
		item.put("member_partition", new AttributeValue().withS(memberPartition));
	}
	
	public void initQuery(Map<String, Condition> condition) {
		condition.put("member_partition", new Condition().withAttributeValueList(new AttributeValue().withS(memberPartition)).withComparisonOperator(ComparisonOperator.EQ));
	}

	public Map<String,AttributeValue> getItem(Map<String, AttributeValue> item) throws Exception{
		initPartition(item);
		GetItemRequest getItemRequest = null;
		getItemRequest = new GetItemRequest()
				.withTableName(memberTbl)
				.withKey(item);
		
		Map<String,AttributeValue> resultItem = ddb.getItem(getItemRequest).getItem();
		
		return resultItem;
				
	}
	
	public void insertItem(Map<String, AttributeValue> item) throws Exception{
		initPartition(item);
		ddb.putItem(memberTbl, item);
	}
	
	public void updateItem(Map<String, AttributeValue> key, Map<String, AttributeValueUpdate> updateItem) throws Exception{
		initPartition(key);
		ddb.updateItem(memberTbl, key, updateItem);
	}
	
	/** 사용 가능 오퍼레이션 : EQ | LE | LT | GE | GT | BEGINS_WITH | BETWEEN **/
	public List<Map<String, AttributeValue>> queryItems(Map<String,Condition> keyCondition) throws Exception{
		initQuery(keyCondition);
		QueryRequest queryRequest = new QueryRequest()
								.withTableName(memberTbl)
								.withKeyConditions(keyCondition)
								.withIndexName("member_partition-memberNm-index");
		QueryResult result = ddb.query(queryRequest);
		return result.getItems();
	}
	
	/** 사용 가능 오퍼레이션 : EQ | LE | LT | GE | GT | BEGINS_WITH | BETWEEN **/
	public List<Map<String, AttributeValue>> queryItemsGsi(Map<String,Condition> keyCondition, String indexNm) throws Exception{
		initQuery(keyCondition);
		QueryRequest queryRequest = new QueryRequest()
								.withTableName(memberTbl)
								.withKeyConditions(keyCondition)
								.withIndexName(indexNm);
		QueryResult result = ddb.query(queryRequest);
		return result.getItems();
	}
	
	
	
}
