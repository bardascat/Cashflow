package com.lab.cashflow.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lab.cashflow.domain.User;

public class SecurityUser extends User implements UserDetails{

	private String username;

	private String password;
	private Collection<GrantedAuthority> roles;
	
	public SecurityUser() {
		// TODO Auto-generated constructor stub
	}
	
	public SecurityUser(User user){
		this.username=user.getEmail();
		this.setEmail(user.getEmail());
		this.setFirstname(user.getFirstname());
		this.setLastname(user.getLastname());
		this.setId_user(user.getId_user());
		this.setPassword(user.getPassword());
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}
	
	public void setAuthorities(Collection<GrantedAuthority> auth) {
		this.roles=auth;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SecurityUser [username=" + username + ", password=" + password
				+ ", roles=" + roles + "]";
	}
	
	

}
