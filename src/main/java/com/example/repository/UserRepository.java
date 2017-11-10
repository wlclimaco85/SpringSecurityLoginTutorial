package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository("userRepository")
public interface UserRepository {
	 User findByEmail(String email);
}
