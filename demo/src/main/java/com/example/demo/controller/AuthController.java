package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberInfo;
import com.example.demo.entity.MemberWithToken;
import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@PostMapping("/")
	public MemberWithToken generate(@RequestBody Member member) throws Exception{
		System.out.println(member.toString());
		// 로그인 한 뒤 성공하면 토큰을 생성한 뒤 추가해서 반환
		MemberInfo info = memberRepo.login(member);
		if(info == null) throw new ElementNotFoundException("로그인 실패");
		
		String access_token = tokenService.createToken(info.getMember_id());
		
		return MemberWithToken.builder()
					.member_id(info.getMember_id())
					.member_nick(info.getMember_nickname())
					.access_token(access_token)
				.build();
	}
	
	@GetMapping("/")
	public ResponseEntity<String> validate(
			@RequestHeader(value = "Authorization") String access_token) throws Exception{
		
		// 토큰은 Authorization: Bearer {token} 형태로 전송되어진다.
		// 1. Bearer로 시작하는지 검사
		// 2. 뒷부분을 분리하여 유효한 토큰인지 확인
		log.info("token = {}", access_token);
		
		if(!access_token.startsWith("Bearer "))
			ResponseEntity.badRequest().body("요청 형식이 맞지 않습니다");
		
		access_token = access_token.substring("Bearer ".length());
		if(tokenService.validateToken(access_token)) { // 인증 성공
			return ResponseEntity.ok("Success");
			// return ResponseEntity.status(HttpStatus.OK).body("Success");
		} 
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
}
