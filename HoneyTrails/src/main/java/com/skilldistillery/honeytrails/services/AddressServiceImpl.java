package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.repositories.AddressRepository;

public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepo;
	
	
	//Probably dont need but if an admin needs or something
	@Override
	public List<Address> allAddresses() {
		return addressRepo.findAll();
	}
	
	
	
}
