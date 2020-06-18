package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

//RestController 에서 발생하는 예외에 간섭하기 위한 객체(AOP 객체)
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ErrorController {
	
	// ElementNotFoundException이 발생하면 404 에러를 사용자에게 전송
	//  - ResponseEntity는 스프링에서 제공하는 응답 Wrapper 객체(메시지 + 상태 설정)
	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<String> errorHandler(Exception e) {
		log.error("예외 발생", e); // 서버에 로그 남기기
		return ResponseEntity.notFound().build();
	}
	
}
