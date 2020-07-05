package com.task.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Customer")
@Getter
@NoArgsConstructor
@Setter
public class Customer extends User {
	
	private static final Logger logger = LogManager.getLogger(Customer.class);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account> accounts;

	public Customer(String username, String password, String firtName, String lastName, String salt) {

		super(username, password, firtName, lastName, salt);
		logger.info(Messages.getLoggerMessage(OperationEnum.Created, Customer.class));
	}

}