package com.example.service;

import com.example.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public boolean ifExistEmail(String string);

}
