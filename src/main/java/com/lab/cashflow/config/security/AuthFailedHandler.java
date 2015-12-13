package com.lab.cashflow.config.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailedHandler extends SimpleUrlAuthenticationFailureHandler{

	private static final Logger logger = Logger.getLogger(AuthFailedHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		logger.info("onAuthenticationFailure - auth failed");
		response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
		response.getWriter().write(exception.getMessage());
		
		response.getWriter().flush();
	}
	
	
}
