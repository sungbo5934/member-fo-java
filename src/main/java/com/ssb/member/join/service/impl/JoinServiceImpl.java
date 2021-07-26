package com.ssb.member.join.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.ItemConverter;
import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssb.comm.util.EncryptUtil;
import com.ssb.member.comm.constant.MemberCommConstant;
import com.ssb.member.comm.helper.DynamoDbHelper;
import com.ssb.member.comm.util.JsonUtil;
import com.ssb.member.join.mapper.JoinMapper;
import com.ssb.member.join.model.MemberJoinVO;
import com.ssb.member.join.service.JoinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JoinServiceImpl implements JoinService{
	
	@Autowired
	private JoinMapper joinMapper;
	
	@Autowired
	private DynamoDbHelper ddbHelper;
	
	@Override
	public List<MemberJoinVO> selectMember(MemberJoinVO memberVo) throws Exception {
		List<MemberJoinVO> memberList = new ArrayList<MemberJoinVO>();
		List<Map<String, Object>> resultList = joinMapper.selectMember(memberVo);
		return JsonUtil.toObj(JsonUtil.toJson(resultList), memberList.getClass());
		
	}

	@Override
	public void joinMember(MemberJoinVO memberVo) throws Exception {
		memberVo.setPwd(EncryptUtil.encrypt(memberVo.getPwd(), MemberCommConstant.PWD_ENCRYPT_ALGOTIZM.getValue()));
		joinMapper.joinMember(memberVo);
		
	}

	@Override
	public void updateMember(MemberJoinVO memberVo) throws Exception {
		memberVo.setPwd(EncryptUtil.encrypt(memberVo.getPwd(), MemberCommConstant.PWD_ENCRYPT_ALGOTIZM.getValue()));
		joinMapper.updateMember(memberVo);
		
	}

}
