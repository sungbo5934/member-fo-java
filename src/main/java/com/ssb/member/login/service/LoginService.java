package com.ssb.member.login.service;

import javax.validation.Valid;

import com.ssb.member.login.model.MeberVO;

public interface LoginService {

	MeberVO loginChk(MeberVO meberVo) throws Exception;

}
