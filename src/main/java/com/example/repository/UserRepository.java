package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.framework.data.BaseJPARepository;
import com.example.model.User;

@Repository("userRepository")
public interface UserRepository extends BaseJPARepository<User, Long> {
	 User findByEmail(String email);
}
