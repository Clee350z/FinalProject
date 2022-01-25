package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.services.AuthService;

@RestController
@CrossOrigin({"*", "http://locoalhots:4300"})
public class AuthController {

	@Autowired
	private AuthService authSer;
	
	//Temp, Delete!
	@GetMapping("usertest")
	public User userTest() {
		return authSer.findUserByName("admin");
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user, HttpServletResponse res) {

	    if (user == null) {
	        res.setStatus(400);
	    }

	    user = authSer.register(user);

	    return user;
	}

	@GetMapping("/authenticate")
	public User authenticate(Principal principal) {
	    return authSer.findUserByName(principal.getName());
	}
}
