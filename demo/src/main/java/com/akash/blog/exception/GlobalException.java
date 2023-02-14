package com.akash.blog.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akash.blog.payloads.ApiResonse;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResonse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
          String msg=  ex.getMessage();
          ApiResonse apiResponnse = new ApiResonse(msg,false);
          return new ResponseEntity<ApiResonse>(apiResponnse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname=((FieldError)(error)).getField();
			String msg = error.getDefaultMessage();
			
			resp.put(fieldname, msg);
		});
		
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
		
	}

	

}
