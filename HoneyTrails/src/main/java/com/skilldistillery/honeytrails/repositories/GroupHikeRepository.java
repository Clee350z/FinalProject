package com.skilldistillery.honeytrails.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.GroupHike;

public interface GroupHikeRepository extends JpaRepository<GroupHike, Integer> {
	
	GroupHike findByEventName(String eventName);
	
	Set<GroupHike> findByTrail_id(int id);
	
	

}
