package com.task.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter @NoArgsConstructor
public class Account {
	
	private static final Logger logger = LogManager.getLogger(Account.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Farm> farms;
	
	@Column(nullable = false)
	private String bankKey;
	
	@Column(nullable = false)
	private String bankName;
	
	private CurrencyEnum currency;

	public Account(Customer customer, List<Farm> farms, String bankKey, String bankName, CurrencyEnum currency) {
		
		super();
		this.setCustomer(customer);
		this.setFarms(farms);
		this.setBankKey(bankKey);
		this.setBankName(bankName);
		this.setCurrency(currency);
		logger.info(Messages.getLoggerMessage(OperationEnum.Created, Account.class));
		
	}

}
