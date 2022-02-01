package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.entities.User;

public interface GroupHikeService {
	
	List<GroupHike> getAllGroupHikes();
	
//	List<User> getUsersByGroupHikeId(int groupHikeId);
	
	GroupHike getGroupHikeById(int groupHikeId);
	
	GroupHike getGroupHikeByEventName(String groupHikeEventName);
	
	GroupHike addGroupHike(GroupHike groupHike, String username, int trailId);
	
	GroupHike updateGroupHikeById(GroupHike groupHike, int groupHikeId, String username);
	
	GroupHike hideGroupHikeById(GroupHike groupHike, int groupHikeId, String username);
	
	void deleteGroupHikeById(int groupHikeId, String username);

	GroupHike addUsersToGroupHike(int groupHikeId, String username, int trailId);

	Set<GroupHike> findGroupHikeByTrailId(int trailId);


}
