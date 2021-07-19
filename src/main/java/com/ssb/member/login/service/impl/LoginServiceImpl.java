package com.ssb.member.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ssb.comm.constant.CommConstant;
import com.ssb.comm.helper.JwtHelper;
import com.ssb.comm.util.EncryptUtil;
import com.ssb.member.comm.helper.DynamoDbHelper;
import com.ssb.member.login.exception.LoginFailException;
import com.ssb.member.login.model.MeberVO;
import com.ssb.member.login.service.LoginService;
import com.ssb.member.sample.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Value("${aws.dynamodb.memberPartition}")
	private String memberPartition;
	
	private final String encryptAlgorizm = "SHA-256";

	@Override
	public MeberVO loginChk(MeberVO meberVo, HttpServletResponse response) throws Exception {
		
		
		Map<String, AttributeValue> memberInfo = loginMapper.getMmber(meberVo);
		
		String password = EncryptUtil.encrypt(meberVo.getPwd(), encryptAlgorizm);

		if(StringUtils.equals(memberInfo.get("pwd").getS(), password)) {
			
			Map<String, Object> tokenMap = new HashMap<String, Object>();
			meberVo.setPwd(StringUtils.EMPTY);
			tokenMap.put(memberPartition, meberVo);
			jwtHelper.setResLoginToken(response, tokenMap);
			
		}else {
			
			throw new LoginFailException("비밀번호 다름");
			
		}
		
		return meberVo;
	}

}
