package com.ssb.member.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class SampleController {
	
	@GetMapping("/sample/data")
	public ResponseEntity<?> getSampleData(){
		Map<String, Object> sampleMap = new HashMap<String, Object>();
		sampleMap.put("sample", "sample Data");
		return new ResponseEntity<>(sampleMap, HttpStatus.OK);
	}

}
