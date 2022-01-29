package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class UserController {

	@Autowired
	private UserService uServ;

	@GetMapping("users")
	public List<User> getUsers(HttpServletResponse res) {
		List<User> users = uServ.getAllUsers();
		if (users.size() <= 0) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return users;
	}

	@GetMapping("users/{userId}")
	public User getUser(@PathVariable int userId, HttpServletResponse res) {
		User user = uServ.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return user;
	}

	@PutMapping("users/{userid}")
	public User updateUser(@PathVariable int userid, @RequestBody User user, HttpServletResponse res,
			Principal principal) {
		try {
			if (uServ.getUserById(userid) != null) {
				uServ.updateUser(userid, user, principal.getName());
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return user;
	}

	@DeleteMapping("users/{userId}")
	public void removeUser(@PathVariable int userId) {
		uServ.deleteUser(userId);

	}

}
