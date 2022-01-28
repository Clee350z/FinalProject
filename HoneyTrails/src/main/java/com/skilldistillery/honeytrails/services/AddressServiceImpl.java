package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.Address;
import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.AddressRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private UserRepository userRepo;

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
	public Address addAddress(Address address, String username, int userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			return addressRepo.saveAndFlush(address);
		}
		return null;
	}

	@Override
	public Address updateAddressById(Address address, int addressId, String username) {
		Address updatedAddress = addressRepo.findById(addressId).get();
		if (updatedAddress == null) {
			return null;
		}

		if (address.getCity() != null) {
			updatedAddress.setCity(address.getCity());
		}
		if (address.getState() != null) {
			updatedAddress.setState(address.getState());
		}
		updatedAddress.setStreet(address.getStreet());
		updatedAddress.setZipcode(address.getZipcode());

		addressRepo.saveAndFlush(updatedAddress);

		return updatedAddress;
	}

}
