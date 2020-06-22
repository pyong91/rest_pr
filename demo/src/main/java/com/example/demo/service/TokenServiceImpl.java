package com.example.demo.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService{
	
	// 1. 토큰을 생성하기 위해서는 나만이 알고 있는 Secret key가 필요(외부에 노출되면 끝장)
	private final String secretKey = "KHacademy%";
	
	@Override
	public String createToken(String id) throws Exception{
		// 2. 토큰을 생성하기 위한 정보 설정
		// - 생성 알고리즘
		// - 만료 시간 : 짧을 수록 보안 수준이 올라가고 길 수록 사용자가 편해짐(일반적으로 30분을 경계로 함)
		// - 헤더 정보
		// - 저장 데이터
		
		// 알고리즘 생성
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
		
		// 만료 시간(java.util.Date)
		Calendar expire = Calendar.getInstance();
		expire.add(Calendar.MINUTE, 30); // 30분 뒤
		
		// 헤더 정보
		Map<String, Object> header = new HashMap<>();
		header.put("type", "jwt");
//		header.put("algorithm", "HS256"); // 없어도 됨.. alg라는 이름으로 자동첨부
		
		// 바디 정보 : 원하는 만큼
		Map<String, Object> body = new HashMap<>();
		body.put("id", id);
		
		return Jwts.builder()
					.setHeader(header)
					.setClaims(body)				
					.setExpiration(expire.getTime())
					.signWith(algorithm, secretKey.getBytes()) // 이 방식은 비추..(bite[])
				.compact();
	}
	
	@Override
	public boolean validateToken(String token) throws Exception{
		// 토큰 인증을 하다가 다음의 예외가 발생할 수 있음
		// - ExpriedJwtException : 토큰 만료로 인한 예외(토큰은 유효하지만 시간 초과)
		//	- 토큰을 갱신할 수 있는 신호를 클라이언트에게 전송(갱신 토큰, Refresh Token)
		// - JwtException : 토큰이 변조된 경우
		
		// 토큰 분석
		Claims claims = Jwts.parser()
							.setSigningKey(secretKey.getBytes())
							.parseClaimsJws(token)
							.getBody();
		// Claims를 이용한 정보 출력 및 검증
		String member_id = (String)claims.get("id");
		log.info("member_id = {}", member_id);
		// 추가 검증을 수행해서 true나 false로 반환하는 것들이 가능
		return true;
	}
	
}
