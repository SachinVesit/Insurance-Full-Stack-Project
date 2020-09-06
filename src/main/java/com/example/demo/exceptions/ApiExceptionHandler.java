package com.example.demo.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		//create payload containing exceptin details
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		
		ApiException apiException = new ApiException(e.getMessage()
				,e
				,badRequest
				,ZonedDateTime.now(ZoneId.of("z")));
		
		//return response entity
		return new ResponseEntity<>(apiException, badRequest);
	}
}
