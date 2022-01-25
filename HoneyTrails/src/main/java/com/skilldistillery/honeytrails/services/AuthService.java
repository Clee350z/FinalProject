package com.skilldistillery.honeytrails.services;

import com.skilldistillery.honeytrails.entities.User;

public interface AuthService {

	User register(User user);

	User findUserByName(String username);
	
}
