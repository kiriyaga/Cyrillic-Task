package com.task.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserLoginResponse {

	@NotNull(message = "Username is required!")
	private String username;
	private String firstName;
	private String lastName;
	@NotNull(message = "Token is required!")
	private String token;
}
