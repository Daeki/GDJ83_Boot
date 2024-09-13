package com.winter.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class MemberServiceTest {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void passwordUpdateTest()throws Exception{
		//admin, admin => 1234
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("admin");
		memberVO.setPassword("admin");
		String newpassword="1234";
		
		
		
		MemberVO check = memberMapper.detail(memberVO);
		
		log.info("MemberVO : {} ", memberVO);
		log.info("Check : {} ", check);
		
		if(passwordEncoder.matches(memberVO.getPassword(), check.getPassword())) {
			log.info("일치 합니다");
		}
		
		assertEquals(check.getPassword(), memberVO.getPassword());
		
	}

	//@Test
	void test() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user");
		memberVO.setPassword(passwordEncoder.encode("user"));
		int result = memberMapper.pwUpdate(memberVO);
		
		assertEquals(1, result);
	}

}
