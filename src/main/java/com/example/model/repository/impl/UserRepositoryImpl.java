package com.example.model.repository.impl;

import java.util.Collection;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.framework.data.BaseHibernateJPARepository;
import com.example.model.User;
import com.example.repository.UserRepository;

/**
 *
 * User Repository Implementation
 *
 * @author: kameshr
 */
@Repository
public class UserRepositoryImpl extends BaseHibernateJPARepository<User, Long> implements UserRepository {
    private static Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);
   
    @Override
    public User findByEmail(String email) {
        return new User();// (User) sessionFactorys.getCurrentSession().createQuery("from User u where u.email = :email")
           //     .setParameter("email", email).uniqueResult();
    }

	@Override
	public User insert(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User insertOrUpdate(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> findAllByPage(int pageNum, int countPerPage, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
