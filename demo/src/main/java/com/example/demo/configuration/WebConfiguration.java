package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 운영 설정(옛날 web.xml에서 하던 설정)
@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// registry가 설정 저장소이므로 이곳에 설정을 추가
		registry.addMapping("/**").allowedMethods("DELETE");
	}
}
