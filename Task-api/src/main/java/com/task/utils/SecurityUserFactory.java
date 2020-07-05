package com.task.utils;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.task.beans.User;
import com.task.beans.security.SecurityUser;

public class SecurityUserFactory {

	public static SecurityUser create(@Valid User user) {

		Collection<? extends GrantedAuthority> authorities;
		try {
			authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getName());
		} catch (Exception e) {
			authorities = null;
		}
		return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), authorities);
	}
}
