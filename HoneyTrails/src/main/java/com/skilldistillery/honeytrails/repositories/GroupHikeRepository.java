package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.GroupHike;

public interface GroupHikeRepository extends JpaRepository<GroupHike, Integer> {
	
	GroupHike findByEventName(String eventName);
	
	

}
