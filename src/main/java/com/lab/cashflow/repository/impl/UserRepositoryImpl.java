package com.lab.cashflow.repository.impl;

import com.lab.cashflow.config.security.SecurityUser;
import com.lab.cashflow.domain.User;
import com.lab.cashflow.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Transactional
@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {

    private static Logger logger = Logger.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;

    @Override
    public User getUser(int id_user) {
        User user = this.em.find(User.class, id_user);

        return user;

    }

    @Override
    public User saveUser(User user) {
        this.em.persist(user);

        return user;
    }

    @Override
    public SecurityUser getUserByEmail(String email) throws NoResultException {
        try {
            TypedQuery<User> query = this.em.createQuery("select u from user u where u.email=:email", User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();

            return new SecurityUser(user);
        } catch (Exception e) {
            logger.info("User with email: " + email + " was not found");
            return null;
        }
    }


}
