package com.task.dtos;

import javax.validation.constraints.NotNull;

import com.task.validators.interfaces.UsernameExist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

	@NotNull(message = "Username is required!")
	@UsernameExist
	private String username;

	@NotNull(message = "Password is required!")
	private String password;
}
