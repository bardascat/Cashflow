package com.lab.cashflow.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lab.cashflow.service.impl.UserService;

@Component
public class AuthProvider implements AuthenticationProvider{

	private static Logger logger = Logger.getLogger(AuthProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		logger.info("authenticate - "+authentication.getName());
		logger.info("authenticate - "+authentication.getCredentials().toString());
		
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        //TODO move the authorities some where else
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		//check if user is valid
		
		UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), grantedAuths);
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
}
