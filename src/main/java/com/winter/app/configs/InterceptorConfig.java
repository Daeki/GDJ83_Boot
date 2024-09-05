package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.home.interceptors.AdminCheckInterceptor;
import com.winter.app.home.interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//어떤 URL이 왔을 때 어떤 Interceptor를 실행 할것인가??
		// /qan/list  -> LoginIntercptor
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/qna/list");
		
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/admin/*");
		
	}
	
	

}
