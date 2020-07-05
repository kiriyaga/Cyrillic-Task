package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.task.beans.Account;

public interface AccountRepository extends JpaRepository<Account, Long>,JpaSpecificationExecutor<Account> {

}
