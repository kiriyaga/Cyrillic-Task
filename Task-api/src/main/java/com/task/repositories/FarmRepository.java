package com.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.task.beans.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>,JpaSpecificationExecutor<Farm> {
	
	@Query(value = "SELECT * FROM farm LEFT JOIN account ON farm.account_id = account.id LEFT JOIN customer ON account.customer_id = customer.id LEFT JOIN user ON customer.id = user.id where user.username =?1 AND account.id=?2", nativeQuery = true)
	List<Farm> findCustomerFarmsForAccount(String username, Long accountId);

}
