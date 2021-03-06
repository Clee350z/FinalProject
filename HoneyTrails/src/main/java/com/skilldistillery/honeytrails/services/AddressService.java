package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.Address;

public interface AddressService {

	List<Address> allAddresses();

	Address getAddressById(int addressId);

	Address addAddress(Address address, String username);

	
}
