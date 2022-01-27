package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
