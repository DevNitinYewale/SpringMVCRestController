package com.example.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "id not found")
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomException(String t) {
		// TODO Auto-generated constructor stub
		super(t);
	}
	/*
	public CustomException(Throwable t) {
		// TODO Auto-generated constructor stub
		super(t);
	}
	*/

}