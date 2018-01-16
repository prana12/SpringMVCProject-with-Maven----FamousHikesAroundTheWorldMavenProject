package com.project.hikes.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.project.hikes.entity.HikeUser;
import com.project.hikes.entity.Role;

public interface UserService {

	//public User findUserByEmail(String email);
	public HikeUser findUserByEmail(String email);
	public void saveUser(HikeUser user);
	public Role findUserRole(int id);
}
