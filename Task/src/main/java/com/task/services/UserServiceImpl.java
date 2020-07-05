package com.task.services;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.task.beans.User;
import com.task.dtos.UserLoginRequest;
import com.task.dtos.UserLoginResponse;
import com.task.exceptions.UserCreditalsException;
import com.task.repositories.UserRepository;
import com.task.utils.TokenUtils;
import com.task.validators.interfaces.UsernameExist;

import lombok.NonNull;

@Service
public class UserServiceImpl implements UserService, CrudService<User> {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Value("${security.pepper}")
	private String pepper;

	@Autowired
	private PasswordEncoder bcript;

	@Autowired
	TokenUtils tokenUtils;

	@Override
	public List<User> getAll(Specification<User> specifications) {

		return userRepository.findAll(Specification.where(specifications));
	}

	@Override
	@Validated
	public UserLoginResponse login(@NonNull UserLoginRequest userLoginRequest) {
	
		User user = userRepository.findByUsername(userLoginRequest.getUsername());

		String fullPassword = userLoginRequest.getPassword() + user.getSalt() + pepper;

		if (bcript.matches(fullPassword, user.getPassword())) {
			String token = this.tokenUtils.generateToken(user);
			logger.info(String.format("Sucessfully login '%s'.", user.getUsername()));
			return new UserLoginResponse(user.getUsername(), user.getFirstName(), user.getLastName(), token);
		}
		
		logger.warn(String.format("Invalid login '%s'.", user.getUsername()));
		throw new UserCreditalsException(String.format("Wrong password '%s'.", user.getUsername()));
	}
}
