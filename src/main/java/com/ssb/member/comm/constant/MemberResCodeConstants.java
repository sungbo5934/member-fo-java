package com.ssb.member.comm.constant;

import lombok.Getter;

public enum MemberResCodeConstants {
	
	SUCCESS(200, "Success"),
	ERROR(400, "Error");
	
	@Getter
	private int resultCode;
	@Getter
	private String resultMsg;
	
	MemberResCodeConstants(int resultCode, String resultMsg){
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

}
