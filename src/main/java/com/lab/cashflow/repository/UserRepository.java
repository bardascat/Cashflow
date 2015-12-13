package com.lab.cashflow.repository;

import javax.persistence.NoResultException;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.User;

public interface UserRepository {

	public User getUser(int id_user);
	
	public User saveUser(User user);
	public SecurityUser getUserByEmail(String locatin);
}
