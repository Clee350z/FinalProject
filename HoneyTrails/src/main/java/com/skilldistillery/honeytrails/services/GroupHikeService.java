package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.GroupHike;

public interface GroupHikeService {
	
	List<GroupHike> getAllGroupHikes();
	
	GroupHike getGroupHikeById(int groupHikeId);
	
	GroupHike getGroupHikeByEventName(String groupHikeEventName);
	
	GroupHike addGroupHike(GroupHike groupHike);
	
	GroupHike updateGroupHikeById(GroupHike groupHike, int groupHikeId);
	
	void deleteGroupHikeById(int groupHikeId);

}
