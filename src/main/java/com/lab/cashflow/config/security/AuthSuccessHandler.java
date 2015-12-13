package com.lab.cashflow.config.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private static final Logger logger = Logger.getLogger(AuthSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("onAuthenticationSuccess - Success");
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		
		response.getWriter().print(
	            "{\"login\": \"SUCCESS\"}");
	    response.getWriter().flush();
	    
	   // super.onAuthenticationSuccess(request, response, authentication);
	    
	}

}
