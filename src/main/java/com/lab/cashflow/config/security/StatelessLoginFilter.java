package com.lab.cashflow.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.cashflow.domain.User;
import com.lab.cashflow.service.impl.TokenAuthService;


public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter{

	private static final Logger logger =  Logger.getLogger(StatelessLoginFilter.class);

	TokenAuthService tokenAuthService;

	protected StatelessLoginFilter(String defaultFilterProcessesUrl,AuthenticationManager manager,AuthSuccessHandler successHandler, AuthFailedHandler failureHandler, TokenAuthService authService) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(manager);
		setAuthenticationSuccessHandler(successHandler);
		setAuthenticationFailureHandler(failureHandler);
		
		this.tokenAuthService=authService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		// TODO Auto-generated method stub
		
		User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

		logger.info("attemptAuthentication - " + user.toString());
		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		return getAuthenticationManager().authenticate(authentication);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		SecurityUser user = (SecurityUser) authResult.getPrincipal();
		logger.info("successfulAuthentication : "+user.toString());
		
		this.tokenAuthService.addAuthentication(response, authResult);
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	

	
	
}
