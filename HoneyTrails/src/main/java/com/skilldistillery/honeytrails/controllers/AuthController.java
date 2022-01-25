package com.skilldistillery.honeytrails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.services.AuthService;

@RestController
//@RequestMapping("api")
public class AuthController {

	@Autowired
	private AuthService authSvc;
	
	//Temp, Delete!
	@GetMapping("usertest")
	public User userTest() {
		return authSvc.findUserByName("admin");
	}
}
