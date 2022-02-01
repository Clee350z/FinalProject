package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.entities.Trail;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.GroupHikeRepository;
import com.skilldistillery.honeytrails.repositories.TrailRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class GroupHikeServiceImpl implements GroupHikeService {

	@Autowired
	private GroupHikeRepository ghRepo;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private TrailRepository tRepo;

	@Override
	public List<GroupHike> getAllGroupHikes() {
		return ghRepo.findAll();
	}
	
//	@Override
//	public List<User> getUsersByGroupHikeId(int groupHikeId) {
//		return ghRepo.findByGroupHike_Id(groupHikeId);
//	}

	@Override
	public GroupHike getGroupHikeById(int groupHikeId) {
		Optional<GroupHike> groupOpt = ghRepo.findById(groupHikeId);
		if (groupOpt.isPresent()) {
			return groupOpt.get();
		}
		return null;
	}

	@Override
	public GroupHike getGroupHikeByEventName(String groupHikeEventName) {
		return ghRepo.findByEventName(groupHikeEventName);
	}

	@Override
	public GroupHike addGroupHike(GroupHike groupHike, String username, int trailId) {
		User user = uRepo.findByUsername(username);
		groupHike.setCreatedByUser(user);
		groupHike.setHidden(false);
		groupHike.addUser(user);
		Optional<Trail> trail = tRepo.findById(trailId);
		if (trail.isPresent()) {
			groupHike.setTrail(trail.get());
			return ghRepo.saveAndFlush(groupHike);
		}
		return null;
	}
	
	@Override
	public GroupHike addUsersToGroupHike(GroupHike groupHike, String username, int trailId) {
		User user = uRepo.findByUsername(username);
		groupHike.addUser(user);
		Optional<Trail> trail = tRepo.findById(trailId);
		if(trail.isPresent()) {
			groupHike.setTrail(trail.get());
			return ghRepo.saveAndFlush(groupHike);
		}
		return null;
	}
	

	@Override
	public GroupHike updateGroupHikeById(GroupHike groupHike, int groupHikeId, String username) {
		GroupHike updatedGroupHike = ghRepo.findById(groupHikeId).get();
		if (updatedGroupHike == null) {
			return null;
		}
		User user = uRepo.findByUsername(username);
		if (user == updatedGroupHike.getCreatedByUser()) {

			if (groupHike.getTrail() != null) {
				updatedGroupHike.setTrail(groupHike.getTrail());
			}
			if (groupHike.getUsers() != null) {
				updatedGroupHike.setUsers(groupHike.getUsers());
			}
			updatedGroupHike.setEventName(groupHike.getEventName());
			updatedGroupHike.setMeetupDate(groupHike.getMeetupDate());

			ghRepo.saveAndFlush(updatedGroupHike);

		}
		return updatedGroupHike;
	}
	
	@Override
	public GroupHike hideGroupHikeById(GroupHike groupHike, int groupHikeId, String username) {
		GroupHike hiddenGroupHike = ghRepo.findById(groupHikeId).get();
		if (hiddenGroupHike == null) {
			return null;
		}
		User user = uRepo.findByUsername(username);
		if (user == hiddenGroupHike.getCreatedByUser()) {

			if (groupHike.getTrail() != null) {
				hiddenGroupHike.setTrail(groupHike.getTrail());
			}
			if (groupHike.getUsers() != null) {
				hiddenGroupHike.setUsers(groupHike.getUsers());
			}
			hiddenGroupHike.setEventName(groupHike.getEventName());
			hiddenGroupHike.setMeetupDate(groupHike.getMeetupDate());
			hiddenGroupHike.setHidden(true);

			ghRepo.saveAndFlush(hiddenGroupHike);

		}
		return hiddenGroupHike;
	}

	@Override
	public void deleteGroupHikeById(int groupHikeId, String username) {
		User user = uRepo.findByUsername(username);
		if (user == ghRepo.findById(groupHikeId).get().getCreatedByUser()) {
			ghRepo.deleteById(groupHikeId);
		}
	}
	
	@Override
	public Set<GroupHike> findGroupHikeByTrailId(int trailId){
		return ghRepo.findByTrail_id(trailId);
	}

	

}
