package com.ssb.member.comm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseEntity {

	private int resultCode;
	
	private String resultMsg;
	
	private Object data;
	
}
