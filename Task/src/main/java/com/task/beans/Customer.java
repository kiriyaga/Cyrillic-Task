package com.task.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends User {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account> accounts;

	public Customer(String username, String password, String firtName, String lastName, String salt) {
		
		super(username, password, firtName, lastName, salt);
		
	}
	
}