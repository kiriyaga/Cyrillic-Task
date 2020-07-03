package com.task.beans;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.task.beans.rbac.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter @Getter @NoArgsConstructor
public abstract class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String firtName;

	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String salt;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Role role;

	public User(String username, String password, String firtName, String lastName, String salt) {
		
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setSalt(salt);
		this.setFirtName(firtName);
		this.setLastName(lastName);

	}
	
}
