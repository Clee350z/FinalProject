package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.services.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService uServ;
	
	@GetMapping("users")
	public List<User> getUsers(HttpServletResponse res) {
		List<User> users = uServ.getAllUsers();
		if(users.size() <= 0) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return users;
	}
	
	@GetMapping("users/{userid}")
	public User getUser(@PathVariable int userid, HttpServletResponse res){
		User user = uServ.getUserById(userid);
		if(user == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return user;
	}

}
