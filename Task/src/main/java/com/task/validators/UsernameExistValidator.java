package com.task.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.task.repositories.UserRepository;
import com.task.validators.interfaces.UsernameExist;

import lombok.NonNull;

public class UsernameExistValidator implements ConstraintValidator<UsernameExist, String> {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(@NonNull String value, ConstraintValidatorContext context) {

		return userRepository.checkifUserExist(value);
	}
}
