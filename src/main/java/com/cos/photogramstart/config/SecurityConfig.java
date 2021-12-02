package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // Ioc
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super 삭제 - 기존 시큐리티가 가지고 있는 기능을 모두 비활성화.
		http.csrf().disable(); // csrf 토큰 비활성화
		http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**")
				.authenticated() // 이 주소는 인증이 필요한 페이지다.
				.anyRequest().permitAll() // 그 외 나머지 페이지는 인증이 필요 없다.
				.and().formLogin().loginPage("/auth/signin") // 인증이 필요한 페이지로 이동하면 자동으로 이동하는 페이지(로그인 페이지)
				.defaultSuccessUrl("/"); // 로그인이 정상적으로 이루어지면 이동하는 페이지
	}

}