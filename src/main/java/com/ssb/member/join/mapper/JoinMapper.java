package com.ssb.member.join.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssb.member.comm.helper.DynamoDbHelper;
import com.ssb.member.join.model.MemberJoinVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class JoinMapper {
	
	@Autowired
	private DynamoDbHelper ddbHelper;

	public List<Map<String, Object>> selectMember(MemberJoinVO memberVo) throws Exception{
		List<MemberJoinVO> memberList = new ArrayList<MemberJoinVO>();
		
		Map<String,Condition> keyCondition = new HashMap<String,Condition>();
		//memberNm
		keyCondition.put("memberNm", new Condition().withAttributeValueList(new AttributeValue().withS(memberVo.getMemberNm())).withComparisonOperator(ComparisonOperator.EQ));
		List<Map<String, AttributeValue>> resultList = ddbHelper.queryItemsGsi(keyCondition, "member_partition-memberNm-index");

		return resultList.stream().map(data -> ItemUtils.toSimpleMapValue(data)).collect(Collectors.toList());
	}

	public void joinMember(MemberJoinVO memberVo) throws Exception{
		
		Map<String, AttributeValue> joinMap = new HashMap<String, AttributeValue>();
		joinMap.put("member_key", new AttributeValue().withS(memberVo.getMemberId()));
		joinMap.put("pwd", new AttributeValue().withS(memberVo.getPwd()));
		joinMap.put("memberNm", new AttributeValue().withS(memberVo.getMemberNm()));
		joinMap.put("email", new AttributeValue().withS(memberVo.getEmail()));
		joinMap.put("address", new AttributeValue().withS(memberVo.getAddress()));
		joinMap.put("phone", new AttributeValue().withS(memberVo.getPhone()));
		ddbHelper.insertItem(joinMap);
		
	}

	public void updateMember(MemberJoinVO memberVo) throws Exception{
		Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		key.put("member_key", new AttributeValue().withS(memberVo.getMemberId()));
		
		Map<String, AttributeValueUpdate> updateItem = new  HashMap<String, AttributeValueUpdate>();
		updateItem.put("pwd", new AttributeValueUpdate(new AttributeValue(memberVo.getPwd()), AttributeAction.PUT));
		updateItem.put("memberNm",  new AttributeValueUpdate(new AttributeValue(memberVo.getMemberNm()), AttributeAction.PUT));
		updateItem.put("email",  new AttributeValueUpdate(new AttributeValue(memberVo.getEmail()), AttributeAction.PUT));
		updateItem.put("address",  new AttributeValueUpdate(new AttributeValue(memberVo.getAddress()), AttributeAction.PUT));
		updateItem.put("phone",  new AttributeValueUpdate(new AttributeValue(memberVo.getPhone()), AttributeAction.PUT));
		ddbHelper.updateItem(key, updateItem);
	}

}
