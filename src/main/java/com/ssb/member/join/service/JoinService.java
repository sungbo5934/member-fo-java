package com.ssb.member.join.service;

import java.util.List;

import com.ssb.member.join.model.MemberJoinVO;

public interface JoinService {

	List<MemberJoinVO> selectMember(MemberJoinVO memberVo) throws Exception;

	void joinMember(MemberJoinVO memberVo) throws Exception;
	
	void updateMember(MemberJoinVO memberVo) throws Exception;


}
