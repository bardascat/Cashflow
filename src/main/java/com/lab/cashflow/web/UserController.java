package com.lab.cashflow.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab.cashflow.domain.User;
import com.lab.cashflow.service.IUserService;

@Controller
public class UserController {


	@Autowired
	IUserService userService;
	
	private static final Logger logger =  Logger.getLogger(UserController.class);

	@RequestMapping("/public/addDummyUser")
	@ResponseBody
	public User addDummyUser(){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = new User();
		user.setEmail("catalin@asd.ro");
		user.setFirstname("Catalin");
		user.setLastname("Bardas");
		user.setPassword(encoder.encode("demo"));
		this.userService.saveUser(user);
		
		return user;
		
	}
	
	@RequestMapping("/public/getUser")
	@ResponseBody
	public User getObject(){
		
		
		User user = new User();
		user.setEmail("catalin@asd.ro");
		user.setFirstname("Catalin");
		user.setLastname("Bardas");
		
		
		return user;
		
	}
	
	
	
	
}
