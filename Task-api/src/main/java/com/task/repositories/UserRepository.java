package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

	@Query(value = "SELECT * FROM user WHERE USERNAME =?1 ", nativeQuery = true)
	User findByUsername(String username);
	
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT * FROM user WHERE user.username =?1) THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END", nativeQuery = true)
	Boolean checkifUserExist(String username);
}
