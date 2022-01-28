package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.services.AddressService;

@RestController
@RequestMapping("api")
public class AddressController {

	@Autowired
	private AddressService addressSer;
	
	@GetMapping("addresses")
	public List<Address> index(){
		return addressSer.allAddresses();
	}
	
	
	
}
