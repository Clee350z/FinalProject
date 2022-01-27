package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.repositories.AddressRepository;

public class AddressServiceImpl {

	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public List<Address> index() {
		return addressRepo.findAll();
	}
	
}
