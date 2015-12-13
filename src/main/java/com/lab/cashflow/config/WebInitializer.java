package com.lab.cashflow.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(Config.class);
		context.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(context));
		
		 AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
	        dispatcherServlet.register(SpringMvcConfig.class);
	        
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
