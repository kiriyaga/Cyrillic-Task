package com.task.services;

import org.springframework.validation.annotation.Validated;

import com.task.dtos.UserLoginRequest;
import com.task.dtos.UserLoginResponse;

public interface UserService {

	UserLoginResponse login(UserLoginRequest userLoginRequest);
}
