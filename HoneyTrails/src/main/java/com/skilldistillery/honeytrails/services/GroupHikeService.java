package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.honeytrails.entities.GroupHike;

public interface GroupHikeService {
	
	List<GroupHike> getAllGroupHikes();
	
	GroupHike getGroupHikeById(int groupHikeId);
	
	GroupHike getGroupHikeByEventName(String groupHikeEventName);
	
	GroupHike addGroupHike(GroupHike groupHike, String username, int trailId);
	
	GroupHike updateGroupHikeById(GroupHike groupHike, int groupHikeId, String username);
	
	GroupHike hideGroupHikeById(GroupHike groupHike, int groupHikeId, String username);
	
	void deleteGroupHikeById(int groupHikeId, String username);

	Set<GroupHike> findGroupHikeByTrailId(int trailId);

}
