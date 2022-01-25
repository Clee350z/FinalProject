package com.skilldistillery.honeytrails.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
