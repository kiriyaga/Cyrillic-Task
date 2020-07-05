package com.task.services.security;

import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.task.beans.rbac.Role;
import com.task.repositories.RoleRepository;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	private static final Logger logger = LogManager.getLogger(SecurityServiceImpl.class);

	@Autowired
	RoleRepository roleRepo;

	@Override
	public Boolean hasProtectedAccess(String privilegeForAuthorization) {
		

		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(privilegeForAuthorization);
		Collection<? extends GrantedAuthority> authorities = null;
		String roleFromUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		String sub = roleFromUser.substring(1, roleFromUser.length() - 1);
		logger.info("User role: " + sub);
		Role role = roleRepo.findByName(sub);
		logger.info(role);
		authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(getPrivileges(role));
		logger.info(authorities);

		if (authorities == null)
			return false;

		return authorities.contains(simpleGrantedAuthority);
	}

	private String getPrivileges(Role role) {

		if (role == null)
			return null;

		String privileges = "";
		for (int i = 0; i < role.getPrivileges().size(); i++) {
			privileges += role.getPrivileges().get(i).toString();
			if (i != role.getPrivileges().size() - 1)
				privileges += ",";
		}
		return privileges;

	}

}
