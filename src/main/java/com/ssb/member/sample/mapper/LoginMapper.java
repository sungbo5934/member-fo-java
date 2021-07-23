package com.ssb.member.sample.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ssb.member.comm.helper.DynamoDbHelper;
import com.ssb.member.login.model.MemberVO;

@Repository
public class LoginMapper {
	
	
	@Autowired
	private DynamoDbHelper dynamoDbHelper;

	public Map<String, AttributeValue> getMmber(MemberVO meberVo) throws Exception{
		Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		key.put("member_key", new AttributeValue().withS(meberVo.getMemberId()));
		
		Map<String, AttributeValue> result = dynamoDbHelper.getItem(key);
		
		return result;
	}

}
