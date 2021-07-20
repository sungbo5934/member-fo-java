package com.ssb.member.comm.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum MemberCommConstant {
	
	PWD_ENCRYPT_ALGOTIZM("SHA-256");
	
	@Getter
	private String value;

}
