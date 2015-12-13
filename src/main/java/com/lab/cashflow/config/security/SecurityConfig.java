package com.lab.cashflow.config.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lab.cashflow.service.impl.TokenAuthService;
import com.lab.cashflow.service.impl.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger =  Logger.getLogger(SecurityConfig.class);
	
	@Autowired
	TokenAuthService tokenAuthService;
	
	@Autowired
	AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	AuthFailedHandler authFailedHandler;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthProvider authenticationProvider;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
		.headers().frameOptions().disable()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(new HttpAuthenticationEntryPoint())
		.and()
		.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/public/**").permitAll()
		.antMatchers("/ui/**").permitAll()
		.antMatchers("/ui/*/*").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST,"/api/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.addFilterBefore(new StatelessLoginFilter("/api/login",authenticationManager(),authSuccessHandler,authFailedHandler, tokenAuthService), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class);
		

	}

	
	@Override
	public void configure(AuthenticationManagerBuilder manager) throws Exception{
		
		//manager.authenticationProvider(authenticationProvider);
		
		manager.userDetailsService(this.userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	
	

}
