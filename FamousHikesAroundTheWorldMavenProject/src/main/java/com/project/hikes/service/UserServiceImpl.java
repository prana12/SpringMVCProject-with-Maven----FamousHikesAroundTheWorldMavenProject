package com.project.hikes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hikes.dao.UserDao;
import com.project.hikes.entity.HikeUser;
import com.project.hikes.entity.Role;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	private PasswordEncoder passwordEncoder;
	
	
	private int DEFAULT_USER_ROLE_ID = 2;
	
	
	@Override
	@Transactional
	public HikeUser findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	
	@Override
	@Transactional
	public void saveUser(HikeUser user) {
		//password hashing
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//associate user registration with USER role
		Role userRole = findUserRole(this.DEFAULT_USER_ROLE_ID);
		user.addUserRole(userRole);
		userDao.saveUser(user);
	}


	@Override
	public Role findUserRole(int id) {
		return userDao.findUserRole(id);
	}

}
