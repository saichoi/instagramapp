package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;

@RestController
@ControllerAdvice // 예외처리 
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // 모든 예외를 처리 
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
}
