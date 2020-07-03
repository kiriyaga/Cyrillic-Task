package com.task.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

@Controller
@RequestMapping("users")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@PreAuthorize("@securityService.hasProtectedAccess('ADD_USER')")
	@RequestMapping(method = RequestMethod.GET,value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser() {	
		
		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>("TEST", HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUseres() {	
		
		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>("TEST", HttpStatus.OK);
	}
	
	

}
