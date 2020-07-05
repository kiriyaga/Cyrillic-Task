package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.task.beans.User;

public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

	@Query(value = "SELECT * FROM user WHERE USERNAME =?1 ", nativeQuery = true)
	User findByUsername(String username);
}
