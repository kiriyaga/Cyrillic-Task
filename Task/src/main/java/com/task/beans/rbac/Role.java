package com.task.beans.rbac;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.task.beans.Account;
import com.task.beans.Farm;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
	
	private static final Logger logger = LogManager.getLogger(Role.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false)
	protected String name;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	protected List<PrivilegeEnum> privileges;

	public Role(String name) {
		super();
		this.name = name;
		logger.info(Messages.getLoggerMessage(OperationEnum.Created, Role.class));
	}

}
