//package com.finance.tracker.errorhandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//
//@RestControllerAdvice
//public class AppErrorHandler {
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex)
//	{
//		Map<String,String> errormap = new HashMap<>();
//		ex.getBindingResult().getFieldErrors().forEach(error->{
//			errormap.put(error.getField(), error.getDefaultMessage());
//		});
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errormap);
//
//	}
//
//
//}
