package com.task.services;

import com.task.dtos.UserLoginRequest;
import com.task.dtos.UserLoginResponse;

public interface UserService {

	UserLoginResponse login(UserLoginRequest userLoginRequest);
}
