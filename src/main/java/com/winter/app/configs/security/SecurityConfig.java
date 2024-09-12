package com.winter.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.members.MemberService;
import com.winter.app.members.MemberUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//Security 에서 무시할 것들
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**")
						
						;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
		String message = URLEncoder.encode("로그인실패", "UTF-8");
		
		
		security.
				cors()
				.and()
				.csrf()
				.disable();
		
		security
			//권한에 관련된 설정
			.authorizeHttpRequests(
					(authorizeRequest)->
						authorizeRequest
							.requestMatchers("/").permitAll()
							.requestMatchers("/notice/list", "/notice/detail").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN")
							.requestMatchers("/manager/*").hasAnyRole("MANAGER", "ADMIN")
							.requestMatchers("/member/add", "/member/login", "/member/check").permitAll()
							.requestMatchers("/member/*").authenticated()
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/*").authenticated()
							
							.anyRequest().permitAll()
					
			 )//authorizeHttpRequests 끝부분
			
			//form login 관련 설정
			.formLogin(
					login -> 
						login
							//개발자가 만든 로그인 페이지 사용
							.loginPage("/member/login")
							//.defaultSuccessUrl("/")
							.successHandler(handler)
							//.failureUrl("/member/login?message="+message)
							.failureHandler(failHandler)
							//파라미터이름이 username이 아니고 'id'로 사용한 경우
							//.usernameParameter("id")
							//파라미터이름이 password가 아니고 'pw'로 사용한 경우
							//.passwordParameter("pw")
							.permitAll()
			)
			//logout 설정
			.logout(
					logout -> 
						logout
							//RequestMatcher("url), 로그아웃 URL 지정
							.logoutUrl("/member/logout") // 로그아웃 URL 지정
							//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
							.logoutSuccessUrl("/")
							.invalidateHttpSession(true) //true session 만료, false session 만료 X
							//.deleteCookies("")         //cookie 삭제
							
			)
			
			//remember me 
			.rememberMe(
					remember ->
						remember
							//Parameter 이름
							.rememberMeParameter("rememberMe")
							//token 유효시간
							.tokenValiditySeconds(60)
							//token에 생성시 값, 필수값, 개발자가 값 설정
							.key("rememberme")
							//인증절차(로그인)진행할 UserDetailService
							.userDetailsService(memberUserService)
							//로그인이 성공했을 경우 진행할 Handler
							.authenticationSuccessHandler(handler)
							.useSecureCookie(false)
			)
			//동시 세션
			.sessionManagement(
					sessionManager ->
						sessionManager
							//최대 허용 갯수, -1이면 무한대
							.maximumSessions(1)
							//false 기존사용자 만료, true 새로운사용자 인증 실패
							.maxSessionsPreventsLogin(false)
							//session이 만료되었을 경우 리다이렉트할 URL
							.expiredUrl("/member/check")
							
							
			)
			
			//Social Login
			.oauth2Login(
					oauth2 ->
						oauth2.userInfoEndpoint(
								user -> user.userService(memberUserService)
						)
			)
			
			
		;
		
		return security.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder(); 
	}

}
