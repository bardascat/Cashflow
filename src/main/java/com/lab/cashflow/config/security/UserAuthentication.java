package com.lab.cashflow.config.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication{

	private final SecurityUser securityUser;
	private boolean authenticated=true;
	
	 public UserAuthentication(SecurityUser securityUser) {
		this.securityUser=securityUser;
	}
	
	@Override
	public String getName() {
		return securityUser.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return securityUser.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return this.securityUser.getPassword();
	}

	@Override
	public Object getDetails() {
		return this.securityUser;
	}

	@Override
	public Object getPrincipal() {
		return this.securityUser.getEmail();
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		this.authenticated=isAuthenticated;
		
	}

}
