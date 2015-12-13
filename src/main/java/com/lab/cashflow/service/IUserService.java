package com.lab.cashflow.service;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.User;

public interface IUserService {

    User getUser(int id_user);

    User saveUser(User user);

    SecurityUser findUserByEmail(String email) throws RuntimeException;

}
