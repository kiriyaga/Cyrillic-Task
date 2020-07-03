package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
