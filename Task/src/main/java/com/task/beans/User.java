package com.task.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.task.beans.rbac.Role;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "User")
@DiscriminatorValue("User")
@Setter
@Getter
@NoArgsConstructor
public class User {
	
	private static final Logger logger = LogManager.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String salt;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private Role role;

	public User(String username, String password, String firtName, String lastName, String salt) {

		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setSalt(salt);
		this.setFirstName(firtName);
		this.setLastName(lastName);
		logger.info(Messages.getLoggerMessage(OperationEnum.Created, User.class));
	}

}
