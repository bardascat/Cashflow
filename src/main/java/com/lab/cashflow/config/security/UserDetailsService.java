package com.lab.cashflow.config.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lab.cashflow.service.impl.UserService;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	private static Logger logger = Logger.getLogger(UserDetailsService.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		logger.info("loadUserByUsername - searching for : "+userName);
		
		SecurityUser user = this.userService.findUserByEmail(userName);
		
		
				
		if(user==null){
			logger.info("loadUserByUsername - User "+userName+" wast not found");
			throw new UsernameNotFoundException("User "+userName + " was not found");
		}else{
			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			
			auths.add(new SimpleGrantedAuthority("ROLE_USER"));
			user.setAuthorities(auths);

			logger.info("loadUserByUsername - User was found:"+user.toString());
			return user;
		}
	}
}
