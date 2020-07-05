package com.task.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.beans.User;
import com.task.dtos.UserLoginRequest;
import com.task.dtos.UserLoginResponse;
import com.task.exceptions.UserCreditalsException;
import com.task.repositories.UserRepository;
import com.task.services.UserServiceImpl;
import com.task.utils.TokenUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UserAuthentificationTest {
	
	@Autowired
	UserServiceImpl userService;
	
	@MockBean
	TokenUtils tokenUtils;
	
	@MockBean
	PasswordEncoder bcript;
	
	@MockBean
	UserRepository userRepository;

	/**
	 * Data for testing
	 */
	
	private User user = new User("Test Username", "Test Password", "Test FirstName", "Test LastName", "Test salt");
	
	private String fullPasswordValid = "Test PasswordTest saltW!ra4^ma@lsa1DS4";
	
	private String fullPasswordInvalid = "Invalid PasswordTest saltW!ra4^ma@lsa1DS4";
	
	private UserLoginResponse userLoginResponse = new UserLoginResponse("Test Username", "Test FirstName", "Test LastName", "Test Token");

	private UserLoginRequest userRequestValid = new UserLoginRequest("Test Username", "Test Password");
	
	private UserLoginRequest userRequestInvalidUsername = new UserLoginRequest("Invalid Username", "Test Password");
	
	private UserLoginRequest userRequestInvalidPassword = new UserLoginRequest("Test Username", "Invalid Password");
	
	private UserLoginRequest userRequestInvalidPasswordAndUsername = new UserLoginRequest("Invalid Username", "Invalid Password");
	
	private UserLoginRequest userRequestNullUsername = new UserLoginRequest(null, "Invalid Password");
	
	private UserLoginRequest userRequestNullPassword = new UserLoginRequest("Test Username", null);
	
	private UserLoginRequest userRequestNullUsernameAndPassword= new UserLoginRequest(null, null);
	
		
	@BeforeEach
	void setUp() throws Exception {
		
		Mockito.when(userRepository.findByUsername("Test Username")).thenReturn(user);
		Mockito.when(tokenUtils.generateToken(user)).thenReturn("Test Token");
		Mockito.when(bcript.matches(fullPasswordValid, "Test Password")).thenReturn(true);
		Mockito.when(bcript.matches(fullPasswordInvalid, "Test Password")).thenReturn(false);
	}

	@Test
	void login_SuccessfullyLogin_ReturnUser() {
		
		UserLoginResponse testUser = userService.login(userRequestValid);
		assertEquals(testUser, userLoginResponse);
	}
	
	@Test()
	void login_InvalidLoginWrongUsername_throwExpection() {
		
		assertThrows(NullPointerException.class, () -> userService.login(userRequestInvalidUsername));
	}
	
	@Test()
	void login_InvalidLoginWrongPassword_throwExpection() {
		
		assertThrows(UserCreditalsException.class, () -> userService.login(userRequestInvalidPassword));
	}
	
	@Test()
	void login_InvalidLoginWrongUsernameAndPassword_throwExpection() {
		
		assertThrows(NullPointerException.class, () -> userService.login(userRequestInvalidPasswordAndUsername));
	}
	
	@Test()
	void login_InvalidLoginNullUsername_throwExpection() {
		
		assertThrows(NullPointerException.class, () -> userService.login(userRequestNullUsername));
	}
	
	@Test()
	void login_InvalidLoginNullPassword_throwExpection() {
		
		assertThrows(UserCreditalsException.class, () -> userService.login(userRequestNullPassword));
	}
	
	@Test()
	void login_InvalidLoginNullUsernameAndPassword_throwExpection() {
		
		assertThrows(NullPointerException.class, () -> userService.login(userRequestNullUsernameAndPassword));
	}

}
