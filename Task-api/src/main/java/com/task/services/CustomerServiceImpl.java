package com.task.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.task.beans.Account;
import com.task.beans.Customer;
import com.task.beans.Farm;
import com.task.repositories.AccountRepository;
import com.task.repositories.CustomerRepository;
import com.task.repositories.FarmRepository;

import lombok.NonNull;

@Service
public class CustomerServiceImpl implements CustomerService, CrudService<Customer> {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	FarmRepository farmRepository;

	@Override
	public List<Account> getAccountsFromCustomer(@NonNull String username) {

		List<Account> accounts = accountRepository.findCustomerAccounts(username);
		return accounts;
	}

	@Override
	public List<Farm> getFarmsFromCustomerAccount(@NonNull String username, @NonNull Long accountId) {

		List<Farm> farms = farmRepository.findCustomerFarmsForAccount(username, accountId);
		return farms;
	}

	@Override
	public List<Customer> getAll(Specification<Customer> specifications) {

		return customerRepository.findAll(Specification.where(specifications));
	}
}
