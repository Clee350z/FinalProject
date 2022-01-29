package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.User;


public interface UserService {
	
public List<User> getAllUsers();
	
	public User getUserById(int userId);
	
	public User updateUser(int userId, User user, String username);
	
	public void deleteUser(int userId);

}
