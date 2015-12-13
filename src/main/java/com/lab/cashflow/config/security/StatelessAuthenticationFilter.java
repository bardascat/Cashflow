package com.lab.cashflow.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.lab.cashflow.service.impl.TokenAuthService;

public class StatelessAuthenticationFilter extends GenericFilterBean {

	private TokenAuthService authService;
	private static final Logger logger =  Logger.getLogger(StatelessAuthenticationFilter.class);
	
	public StatelessAuthenticationFilter(TokenAuthService authService){
		this.authService=authService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("doFilter - Running StatelessAuthenticationFilter ");
		
		SecurityContextHolder.getContext().setAuthentication(this.authService.getAuthentication((HttpServletRequest)request));
		chain.doFilter(request, response);
		
	}

}
