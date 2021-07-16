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
	
	@Value("${aws.dynamodb.memberPartition}")
	private String memberPartition;
	
	@Autowired
	private DynamoDbHelper dynamoDbHelper;

	@Override
	public MeberVO loginChk(MeberVO meberVo) throws Exception {
		
		Map<String,AttributeValue> key = new HashMap<String,AttributeValue>();
		key.put("member_partition", new AttributeValue().withS(memberPartition));
		key.put("member_key", new AttributeValue().withS(meberVo.getMemberId()));
		
		Map<String,AttributeValue> result = dynamoDbHelper.getItem(memberTbl, key);
		meberVo.setPwd(result.get("pwd").getS());
		
		return meberVo;
	}

}
