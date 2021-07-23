package com.ssb.member.join.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssb.comm.util.EncryptUtil;
import com.ssb.member.comm.constant.MemberCommConstant;
import com.ssb.member.join.mapper.JoinMapper;
import com.ssb.member.join.model.MemberJoinVO;
import com.ssb.member.join.service.JoinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JoinServiceImpl implements JoinService{
	
	@Autowired
	private JoinMapper joinMapper;
	
	@Override
	public List<MemberJoinVO> selectMember(MemberJoinVO memberVo) throws Exception {
		List<MemberJoinVO> memberList = joinMapper.selectMember(memberVo);
		return memberList;
		
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
