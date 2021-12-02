package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.Service.AuthService;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할 때 
@Controller
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;

	// 로그인 페이지 이동
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	// 회원가입 페이지 이동
	@GetMapping("/auth/signup")
	public String signupForm() {  
		return "auth/signup";
	}

	// 회원가입 하기 
	@PostMapping("/auth/signup")
	public @ResponseBody String signup(@Valid SignupDto dto, BindingResult bindingResult) { // 1.에러가 터지면 BindingResult에 담아온다.

		// 2.담아온 에러들을 모두 Map에 담아준다.
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>(); 
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패", errorMap); // 3.내가 만든 파일로 예외처리를 한다. 
		}else {
			User user = dto.toEntity();
			User UserEntity = authService.회원가입(user);
			System.out.println(UserEntity);
			return "auth/signin"; 
		}
		
	}

}
