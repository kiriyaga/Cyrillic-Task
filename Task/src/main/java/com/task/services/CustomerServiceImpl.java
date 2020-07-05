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

import lombok.NonNull;

@Service
public class CustomerServiceImpl implements CustomerService, CrudService<Customer> {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Account> getAccountsFromCustomer(String username) {

		@NonNull
		Customer customer = customerRepository.findByUsername(username);
		return customer.getAccounts();
	}

	@Override
	public List<Farm> getFarmsFromCustomerAccount(String username, Long accountId) {

		@NonNull
		Customer customer = customerRepository.findByUsername(username);
		@NonNull
		Account account = accountRepository.getOne(accountId);

		if (customer.getAccounts().contains(account))
			return account.getFarms();

		return null;
	}

	@Override
	public List<Customer> getAll(Specification<Customer> specs) {

		return customerRepository.findAll(Specification.where(specs));
	}
}
