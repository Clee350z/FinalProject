package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.User;


public interface UserService {
	
public List<User> getAllUsers();
	
	public User getUserById(int userId);
	
	public User addUser(User user);
	
	public User updateUser(int trailId, User user);
	
	public boolean deleteUser(int userId);

}
