package com.ssb.member.login.service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ssb.member.login.model.MemberVO;

public interface LoginService {

	MemberVO loginChk(MemberVO meberVo, HttpServletResponse response) throws Exception;

}
