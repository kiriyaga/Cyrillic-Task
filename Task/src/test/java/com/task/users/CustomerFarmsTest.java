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
import com.task.beans.Farm;
import com.task.repositories.FarmRepository;
import com.task.services.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class CustomerFarmsTest {

	@Autowired
	CustomerServiceImpl customerService;
	
	@MockBean
	FarmRepository farmRepository;
	
	private List<Farm> farms = new ArrayList<Farm>();
	
	private String customerUsername = "Test Username";
	
	private Long accountId = 1001L;

	@BeforeEach
	void setUp() throws Exception {
		
		farms.add(new Farm());
		farms.add(new Farm());
		farms.add(new Farm());
		
		Mockito.when(farmRepository.findCustomerFarmsForAccount(customerUsername, accountId)).thenReturn(farms);
	}

	@Test
	void getFarmsFromCustomerAccount_ValidUsernameAndAccountId_ReturnFarms() {
		
		List<Farm> testFarms = customerService.getFarmsFromCustomerAccount(customerUsername, accountId);
		assertEquals(testFarms.size(), farms.size());
	}
	
	@Test
	void getFarmsFromCustomerAccount_NullUsername_throwExeption() {
		
		assertThrows(NullPointerException.class, () -> customerService.getFarmsFromCustomerAccount(null, accountId)); 
	}
	
	@Test
	void getFarmsFromCustomerAccount_NullAccountId_throwExeption() {
		
		assertThrows(NullPointerException.class, () -> customerService.getFarmsFromCustomerAccount(customerUsername, null)); 
	}
	
	@Test
	void getFarmsFromCustomerAccount_NullUsernameAndAccountId_throwExeption() {
		
		assertThrows(NullPointerException.class, () -> customerService.getFarmsFromCustomerAccount(null, null)); 
	}
}
