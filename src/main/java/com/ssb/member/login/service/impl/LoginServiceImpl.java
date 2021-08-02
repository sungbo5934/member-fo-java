package com.ssb.member.login.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ssb.comm.constant.CommJwtConstant;
import com.ssb.comm.helper.JwtHelper;
import com.ssb.comm.helper.RedisHelper;
import com.ssb.comm.util.EncryptUtil;
import com.ssb.comm.util.HttpServletUtil;
import com.ssb.member.comm.constant.MemberCommConstant;
import com.ssb.member.comm.helper.DynamoDbHelper;
import com.ssb.member.login.exception.LoginFailException;
import com.ssb.member.login.model.MemberVO;
import com.ssb.member.login.service.LoginService;
import com.ssb.member.sample.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private HttpServletUtil httpServletUtil;
	
	@Autowired
	private RedisHelper redisHelper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Value("${aws.dynamodb.memberPartition}")
	private String memberPartition;
	
	@Value("${redis.key.member}")
	private String redisKey;

	@Autowired
	private MessageSourceAccessor messageSource;
	
	@Override
	public MemberVO loginChk(MemberVO meberVo, HttpServletResponse response) throws Exception {
		
		Map<String, AttributeValue> memberInfo = loginMapper.getMmber(meberVo);
		
		String password = EncryptUtil.encrypt(meberVo.getPwd(), MemberCommConstant.PWD_ENCRYPT_ALGOTIZM.getValue());

		if(StringUtils.equals(memberInfo.get("pwd").getS(), password)) {
			
			redisHelper.setHash(redisKey, meberVo.getMemberId(), ItemUtils.toSimpleMapValue(memberInfo));
			
			httpServletUtil.setResLoginToken(response, Collections.singletonMap(memberPartition, ItemUtils.toSimpleMapValue(memberInfo)));
			
		}else {
			
			log.info(" 비밀번호 다름 ");
			throw new LoginFailException(messageSource.getMessage("member.login.fail"));
			
		}
		
		return meberVo;
	}

}
