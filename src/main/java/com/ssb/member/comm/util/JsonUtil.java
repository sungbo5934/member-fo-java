package com.ssb.member.comm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static final ObjectMapper objMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static String toJson(Object obj) throws JsonProcessingException {
		return objMapper.writeValueAsString(obj);
	}
	
	public static <T> T toObj(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		return objMapper.readValue(json, clazz);
	}
	
}
