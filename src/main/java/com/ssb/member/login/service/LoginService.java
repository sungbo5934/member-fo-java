package com.ssb.member.login.service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ssb.member.login.model.MeberVO;

public interface LoginService {

	MeberVO loginChk(MeberVO meberVo, HttpServletResponse response) throws Exception;

}
