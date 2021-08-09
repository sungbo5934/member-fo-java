package com.ssb.member.login.controller;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssb.member.login.model.MemberVO;

import lombok.RequiredArgsConstructor;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active:local")
@RequiredArgsConstructor
public class LoginControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void joinMemberTest() throws Exception{
		MemberVO member = new MemberVO();
		member.setMemberId("sungbo8034");
		member.setPwd("pwd123!");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/member/login")
				.content(objectMapper.writeValueAsString(member))
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding(StandardCharsets.UTF_8.displayName());
		
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse();
		
	}

}
