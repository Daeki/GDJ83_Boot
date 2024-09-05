package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//설정 class , XML

//WebMVCConfigure 구현
@Configuration
@Slf4j
public class FileConfig implements WebMvcConfigurer {
	@Value("${app.url.path}")
	private String url;
	@Value("${app.upload.location}")
	private String file;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<resources mapping="/resources/**" location="/resources/" />
		log.info("=====================================");
		log.info(url);
		log.info(file);
		
		//<resources mapping="/files/**" location="D:/upload/" />
		registry.addResourceHandler(url)
				.addResourceLocations(file);
	}
	

}
