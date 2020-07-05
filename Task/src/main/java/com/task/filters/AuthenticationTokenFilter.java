package com.task.filters;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.task.beans.security.SecurityUser;
import com.task.services.security.UserDetailsServiceImpl;
import com.task.utils.TokenUtils;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		if (username != null) {
			SecurityUser userDetails = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
			if (this.tokenUtils.validateToken(authToken, userDetails)) {
				Collection<? extends GrantedAuthority> authorities;
				String role = this.tokenUtils.getRole(authToken);
				authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), authToken, authorities);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
}
