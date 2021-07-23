package com.ssb.member.login.model;

import javax.validation.constraints.NotEmpty;

import com.ssb.comm.model.CommBaseModel;

import lombok.Data;

@Data
public class MemberVO extends CommBaseModel{
	
	private String memberNo;
	
	@NotEmpty(message = "{member.login.empty.id}")
	private String memberId;
	
	@NotEmpty(message = "{member.login.empty.pwd}")
	private String pwd;
	
	private String memberNm;
	
	private String email;
	
	private String phone;
	
	private String address;

}
