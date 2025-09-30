package com.carrot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(requests -> 
			requests.
			requestMatchers("/api/users/join", "/api/users/login").permitAll() // 접근 허용
			.anyRequest().authenticated() // 나머지는 인증 필요
			)
	            
	        
	        .formLogin(login ->
	        	login
	        		.disable()); // 기본 로그인 폼 끄기
		
		return http.build();
		
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
