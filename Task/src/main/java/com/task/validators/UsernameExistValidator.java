package com.task.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.task.beans.User;
import com.task.repositories.UserRepository;
import com.task.validators.interfaces.UsernameExist;

public class UsernameExistValidator implements ConstraintValidator<UsernameExist, String> {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null)
			return false;
		User user = userRepository.findByUsername(value);
		if(user == null)
			return true;
		return false;
			
	}

}
