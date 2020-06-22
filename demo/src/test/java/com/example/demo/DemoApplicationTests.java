package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	void contextLoads() {
//		Member member = new Member();
//		member.setMember_id("test");
//		member.setMember_pw("test");
//		assertNotNull(memberRepo.login(member));
		
		System.out.println(memberRepo.count());
	}

}
