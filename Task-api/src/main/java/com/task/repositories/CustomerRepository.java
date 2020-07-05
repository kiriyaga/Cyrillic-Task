package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.task.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>,JpaSpecificationExecutor<Customer> {
	
	@Query(value = "SELECT * FROM user LEFT JOIN customer ON user.id = customer.id where user.username =?1", nativeQuery = true)
	Customer findByUsername(String username);
}
