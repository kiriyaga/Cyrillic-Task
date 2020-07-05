package com.task.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sipios.springsearch.anotation.SearchSpec;
import com.task.beans.User;
import com.task.dtos.UserLoginRequest;
import com.task.services.UserServiceImpl;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

@Controller
@RequestMapping("users")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	UserServiceImpl userService;

	/**
	 * 
	 * A end-point used to receive inquiries concerning users. 
	 * It is possible to send requests that will filter the return result. 
	 * Example of a query:
	 * /users?search=firstName:'Admin' AND username:'admin'
	 * @param specs - search criteria
	 * @return list of filtered users
	 */
	@PreAuthorize("@securityService.hasProtectedAccess('USER_WHO_CAN_GET_ALL_USERS')")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUseres(@SearchSpec Specification<User> specs) {

		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>(userService.getAll(specs), HttpStatus.OK);	
	}
	
	/**
	 * A end-point used to authentificate users. 
	 * @param userRequest - username and password
	 * @return user data
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userAuthentification(@Validated @RequestBody UserLoginRequest userRequest) {

		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>(userService.login(userRequest), HttpStatus.OK);	
	}

}
