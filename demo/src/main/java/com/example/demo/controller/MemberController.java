package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberInfo;
import com.example.demo.repository.MemberRepository;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@PostMapping("/")
	public MemberInfo regist(@RequestBody Member member) {
//		Member result = memberRepo.save(member);
//		result.setMember_pw(null);
//		return result;
		
		Member result = memberRepo.save(member);
		return memberRepo.get(result);
	}
	
	// REST에서는 HttpSession을 쓰지 않는다(무상태 서버, stateless server)
	// - 로그인 시도할 경우 해당하는 정보의 회원을 조회한 뒤 정보를 반환한다.
	// - 프론트엔드에서 이를 받아서 프론트엔드에서 관리하는 세션에 저장한다.
	// - 로그인 이후부터는 요청 헤더에 로그인과 관련된 값이 첨부되어 넘어온다.
	@PostMapping("/login")
	public MemberInfo login(@RequestBody Member member) {
		return memberRepo.login(member);
	}
	
}
