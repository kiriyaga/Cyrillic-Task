package com.task.services;

import java.util.List;

import com.task.beans.Account;
import com.task.beans.Farm;

public interface CustomerService {
	
	List<Account> getAccountsFromCustomer(String username);
	List<Farm> getFarmsFromCustomerAccount(String username, Long accountId);	
}
