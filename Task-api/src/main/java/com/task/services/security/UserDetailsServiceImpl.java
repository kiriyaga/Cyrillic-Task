package com.task.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.beans.User;
import com.task.repositories.UserRepository;
import com.task.utils.SecurityUserFactory;

import lombok.NonNull;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		@NonNull User user = this.userRepository.findByUsername(username);
		return SecurityUserFactory.create(user);
	}
}
