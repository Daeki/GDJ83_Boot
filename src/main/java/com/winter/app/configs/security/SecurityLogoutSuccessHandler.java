package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.winter.app.members.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		
		
		//AccessToken : 사용자의 AccessToken
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("==== {} ==== ", memberVO);
		
		if(memberVO.getSns() == null) {
			response.sendRedirect("/");
			return;
		}
		
		if(memberVO.getSns().equals("kakao")) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer "+memberVO.getAccessToken());
			//headers.setBearerAuth("Bearer "+memberVO.getAccessToken());
			
			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers);
			
			ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
			log.info("로그아웃 ID : {}", res.getBody());
			
			response.sendRedirect("/");
			return;
		}
	}

}
