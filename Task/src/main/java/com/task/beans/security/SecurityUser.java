package com.task.beans.security;


import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SecurityUser implements UserDetails {

	private Long id;

	private String username;

	private String password;

	private String email;

	private Date lastPasswordReset;

	private Collection<? extends GrantedAuthority> authorities;

	private Boolean accountNonExpired = true;

	private Boolean accountNonLocked = true;

	private Boolean credentialsNonExpired = true;

	private Boolean enabled = true;

	public SecurityUser(Long id, 
						String username, 
						String password, 
						Collection<? extends GrantedAuthority> authorities) {

		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setAuthorities(authorities);
		
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.authorities;

	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {

		this.authorities = authorities;

	}

	@Override
	public boolean isAccountNonExpired() {
		
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {

		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return false;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
