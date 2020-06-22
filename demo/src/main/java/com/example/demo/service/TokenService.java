package com.example.demo.service;

public interface TokenService {
	
	// 생성: createToken --> String(JSON Stringify)
	String createToken(String id) throws Exception;
	
	// 검증: validateToken --> boolean
	boolean validateToken(String token) throws Exception;
	
}
