package com.ssb.member.join.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssb.comm.constant.CommResponseConstant;
import com.ssb.member.comm.model.MemberResponseEntity;
import com.ssb.member.join.model.MemberJoinVO;
import com.ssb.member.join.service.JoinService;

@RestController
@RequestMapping("/member")
public class JoinController {

	@Autowired
	private JoinService	joinService;
	
	@GetMapping("/join")
	public ResponseEntity<?> selectMember(MemberJoinVO memberVo) throws Exception{
		
		List<MemberJoinVO> memberList = joinService.selectMember(memberVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(new MemberResponseEntity(CommResponseConstant.SUCCESS.getResultCode(), CommResponseConstant.SUCCESS.getResultMsg(), memberList));
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> joinMember(@RequestBody @Valid MemberJoinVO memberVo, BindingResult bindingResult ) throws Exception{
		
		joinService.joinMember(memberVo);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/join")
	public ResponseEntity<?> updateMember(@RequestBody @Valid MemberJoinVO memberVo, BindingResult bindingResult ) throws Exception{
		
		joinService.updateMember(memberVo);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
