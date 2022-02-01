package com.skilldistillery.honeytrails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.entities.User;

public interface GroupHikeRepository extends JpaRepository<GroupHike, Integer> {
	
	GroupHike findByEventName(String eventName);
	
//	List<User> findByGroupHike_Id(int groupHikeId);
	
	

}
