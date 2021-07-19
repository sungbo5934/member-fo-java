package com.ssb.member.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssb.member.comm.constant.MemberResCodeConstants;
import com.ssb.member.comm.model.MemberResponseEntity;
import com.ssb.member.login.exception.LoginFailException;
import com.ssb.member.login.model.MeberVO;
import com.ssb.member.login.service.LoginService;

@RestController
@RequestMapping("/member")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MessageSourceAccessor messageSource;

	
	@PostMapping("/login")
	public ResponseEntity<?> loginMember(@RequestBody @Valid MeberVO meberVo, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		if(bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.body(new MemberResponseEntity(MemberResCodeConstants.ERROR.getResultCode(),MemberResCodeConstants.ERROR.getResultMsg(), bindingResult.getAllErrors()));
		}
		
		MeberVO loginMember = loginService.loginChk(meberVo, response);
		
		//jwtHelper.createToken(ApiGatewayConstant.TOKEN_LOGIN_TYPE.getValue(), null)

		return ResponseEntity.status(HttpStatus.OK)
				.body(new MemberResponseEntity(MemberResCodeConstants.SUCCESS.getResultCode(), MemberResCodeConstants.SUCCESS.getResultMsg(), loginMember));
	}
	
	@ExceptionHandler({LoginFailException.class})
	public ResponseEntity<?> loginFailException(final LoginFailException ex, BindingResult bindingResult){
		
		bindingResult.addError(new ObjectError("login", messageSource.getMessage("member.login.fail")));
		
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(new MemberResponseEntity(MemberResCodeConstants.ERROR.getResultCode(), ex.getMessage(), bindingResult.getAllErrors()));
	}

}
