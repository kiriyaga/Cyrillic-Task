package com.task.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Farm {
	
	private static final Logger logger = LogManager.getLogger(Farm.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private Account account;

	@Column(nullable = false)
	private String name;

	private String description;

	public Farm(Account account, String name, String description) {

		super();
		this.setAccount(account);
		this.setName(name);
		this.setDescription(description);
		logger.info(Messages.getLoggerMessage(OperationEnum.Created, Farm.class));
	}

}
