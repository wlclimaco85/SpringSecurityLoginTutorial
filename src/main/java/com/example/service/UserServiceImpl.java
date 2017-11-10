package com.example.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.framework.data.BaseJPAServiceImpl;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl extends BaseJPAServiceImpl<User, Long> implements UserService {

	  private @Autowired UserRepository userRepository;

	    @PostConstruct
	    public void setupService() {
	        this.baseJpaRepository = userRepository;
	        this.entityClass = User.class;
	        this.baseJpaRepository.setupEntityClass(User.class);
	    }





	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.insert(user);
	}
	
	@Override
    public boolean isValidPass(User user, String rawPass) {
        return User.doesPasswordMatch(rawPass, user.getPassword());
    }
	
	@Override
    public User loginUser(final User user, final HttpServletRequest request) {
        user.setLastLoginAt(user.getCurrentLoginAt());
        user.setLastLoginIp(user.getCurrentLoginIp());
        user.setCurrentLoginAt(new Date());
        user.setCurrentLoginIp(request.getRemoteHost());
        user.setLoginCount(user.getLoginCount() + 1);
        user.setUpdatedAt(new Date());

        return userRepository.update(user);
    }

}
