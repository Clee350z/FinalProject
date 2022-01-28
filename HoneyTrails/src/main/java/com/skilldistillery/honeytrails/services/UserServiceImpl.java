package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository uRepo;

	@Override
	public List<User> getAllUsers() {
		return uRepo.findAll();
	}

	@Override
	public User getUserById(int userId) {
		return uRepo.queryById(userId);
	}

	@Override
	public User updateUser(int userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int userId) {
		uRepo.deleteById(userId);
	}

}
