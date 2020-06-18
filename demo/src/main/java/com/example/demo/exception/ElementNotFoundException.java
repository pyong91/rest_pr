package com.example.demo.exception;

// 예외를 직접 구현할 때는 Exception을 상속받는다
// - 만약 RuntimeException을 상속 받으면 예외 처리를 선택할 수 있다.
public class ElementNotFoundException extends RuntimeException{

	public ElementNotFoundException() {
		super();
	}

	public ElementNotFoundException(String message) {
		super(message);
	}
	
}
