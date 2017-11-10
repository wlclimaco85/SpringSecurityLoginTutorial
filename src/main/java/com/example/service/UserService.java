package com.example.service;

import javax.servlet.http.HttpServletRequest;

import com.example.framework.data.BaseService;
import com.example.model.User;



public interface UserService extends BaseService<User, Long> {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	boolean isValidPass(User user, String rawPass);
	User loginUser(User user, HttpServletRequest request);
}
