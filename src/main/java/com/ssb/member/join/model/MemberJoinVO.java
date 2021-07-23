package com.ssb.member.join.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberJoinVO {
	
	private String memberNo;
	
	@NotEmpty(message = "{member.join.empty.id}")
	private String memberId;
	
	@NotEmpty(message = "{member.join.empty.pwd}")
	private String pwd;
	
	private String memberNm;
	
	private String email;
	
	@NotEmpty(message = "{member.join.empty.phone}")
	private String phone;
	
	private String address;
	
}
