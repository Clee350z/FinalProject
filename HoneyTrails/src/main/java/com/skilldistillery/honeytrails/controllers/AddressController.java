package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.entities.HikeReport;
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
	
	@GetMapping("addresses/{addressId}")
	public Address findAddress(@PathVariable int addressId, HttpServletResponse res){
		Address address = addressSer.getAddressById(addressId);
		if(address == null) {
			res.setStatus(404);
		}
		
		return address;
	}
	
	
	
	
}
