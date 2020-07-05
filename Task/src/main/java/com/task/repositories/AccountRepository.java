package com.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.task.beans.Account;

public interface AccountRepository extends JpaRepository<Account, Long>,JpaSpecificationExecutor<Account> {
	
	@Query(value = "SELECT * FROM account LEFT JOIN customer ON account.customer_id = customer.id LEFT JOIN user ON customer.id = user.id where user.username =?1", nativeQuery = true)
	List<Account> findCustomerAccount(String username);	
}
