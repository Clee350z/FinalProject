package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.services.AddressService;

@RestController
@RequestMapping("api")
public class AddressController {

	@Autowired
	private AddressService addressSer;
	
	//Probably dont need this but if an admin need to get all addresses or something
	@GetMapping("addresses")
	public List<Address> index(){
		return addressSer.allAddresses();
	}
	
	@GetMapping("addresses/{addressId}")
	public Address findAddress(@PathVariable int addressId, HttpServletResponse res){
		Address address = addressSer.getAddressById(addressId);
		if(address == null) {
			res.setStatus(404);
		}
		return address;
	}
	
	@PostMapping("address")
	public Address createAddress(@PathVariable int userId, @RequestBody Address address, HttpServletResponse res, Principal principal) {
		Address newAddress = addressSer.addAddress(address, principal.getName(), userId);
		if(newAddress == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return newAddress;
	}
	
	
	
}
