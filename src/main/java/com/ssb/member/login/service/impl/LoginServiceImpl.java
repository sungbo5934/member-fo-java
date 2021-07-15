package com.ssb.member.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ssb.member.comm.helper.DynamoDbHelper;
import com.ssb.member.login.model.MeberVO;
import com.ssb.member.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Value("${aws.dynamodb.memberTbl}")
	private String memberTbl;
	
	@Autowired
	private DynamoDbHelper dynamoDbHelper;

	@Override
	public MeberVO loginChk(MeberVO meberVo) throws Exception {
		
		Map<String,AttributeValue> key = new HashMap<String,AttributeValue>();
		key.put("memberId", new AttributeValue().withS(meberVo.getMemberId()));
		key.put("pwd", new AttributeValue().withS(meberVo.getPwd()));
		
		Map<String,AttributeValue> result = dynamoDbHelper.getItem(memberTbl, key);
		
		return null;
	}

}
