package com.lab.cashflow.service.impl;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.User;
import com.lab.cashflow.repository.UserRepository;
import com.lab.cashflow.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService implements IUserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(int id_user) {
        return userRepository.getUser(id_user);

    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.saveUser(user);
    }


    @Override
    public SecurityUser findUserByEmail(String email) {
        logger.info("findUserByEmail - " + email);
        SecurityUser user = this.userRepository.getUserByEmail(email);
        return user;
    }


}
