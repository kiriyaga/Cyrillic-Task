package com.task.exceptions.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.task.dtos.messages.ResponseMessage;
import com.task.exceptions.UserCreditalsException;

@ControllerAdvice
@Controller
public class ResponseEntityExceptionHandler
		extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>(
				new ResponseMessage(ex.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {
		
		return new ResponseEntity<Object>(new ResponseMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<Object> handleNullPointerException(NullPointerException ex,
			WebRequest request) {

		return new ResponseEntity<Object>(new ResponseMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserCreditalsException.class)
	public final ResponseEntity<Object> handleUserCreditalsException(UserCreditalsException ex,
			WebRequest request) {

		return new ResponseEntity<Object>(new ResponseMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
