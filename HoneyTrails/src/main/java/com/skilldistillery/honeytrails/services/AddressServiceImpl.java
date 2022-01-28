package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	//@Autowired
	//private UserRepository userRepo;

	// Probably dont need but if an admin needs or something
	@Override
	public List<Address> allAddresses() {
		return addressRepo.findAll();
	}

	@Override
	public Address getAddressById(int addressId) {
		Optional<Address> addressOpt = addressRepo.findById(addressId);
		if (addressOpt.isPresent()) {
			return addressOpt.get();
		}
		return null;
	}

	@Override
	public Address addAddress(Address address, String username) {
			
		return addressRepo.saveAndFlush(address);
	}



}
