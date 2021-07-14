package com.ssb.member.login.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> loginMember(@RequestBody @Valid MeberVO meberVo, BindingResult bindingResult) throws Exception{
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_GATEWAY);
		}
		
		MeberVO loginMember = loginService.loginChk(meberVo);
		if(loginMember == null) {
			bindingResult.addError(new ObjectError("login", messageSource.getMessage("member.login.fail")));
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_GATEWAY);
		}
		
		return new ResponseEntity<>(meberVo, HttpStatus.OK);
	}
	

}
