package com.task.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.beans.Account;
import com.task.repositories.AccountRepository;
import com.task.services.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class CustomerAccountsTest {
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@MockBean
	AccountRepository accountRepository;
	
	private List<Account> accounts = new ArrayList<Account>();
	
	private String customerUsername = "Test Username";

	@BeforeEach
	void setUp() throws Exception {
		
		accounts.add(new Account());
		accounts.add(new Account());
		
		Mockito.when(accountRepository.findCustomerAccounts(customerUsername)).thenReturn(accounts);
	}

	@Test
	void getAccountsFromCustomer_ValidUsername_ReturnAccounts() {
		
		List<Account> testAccounts = customerService.getAccountsFromCustomer(customerUsername);
		assertEquals(testAccounts.size(), accounts.size());
	}
	
	@Test
	void getAccountsFromCustomer_NullUsername_throwExeption() {
		
		assertThrows(NullPointerException.class, () -> customerService.getAccountsFromCustomer(null)); 
	}

}
