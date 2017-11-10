package com.example.model.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.example.repository.UserRepository;

/**
 *
 * User Repository Implementation
 *
 * @author: kameshr
 */
@Repository
public class UserRepositoryImpl  implements UserRepository {
    private static Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);
   
    @Override
    public User findByEmail(String email) {
        return new User();// (User) sessionFactorys.getCurrentSession().createQuery("from User u where u.email = :email")
           //     .setParameter("email", email).uniqueResult();
    }


}
