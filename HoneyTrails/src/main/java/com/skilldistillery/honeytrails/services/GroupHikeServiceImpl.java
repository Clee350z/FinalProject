package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.repositories.GroupHikeRepository;

@Service
public class GroupHikeServiceImpl implements GroupHikeService {
	
	@Autowired
	private GroupHikeRepository ghRepo;


	@Override
	public List<GroupHike> getAllGroupHikes() {
		return ghRepo.findAll();
	}

	@Override
	public GroupHike getGroupHikeById(int groupHikeId) {
		Optional<GroupHike> groupOpt = ghRepo.findById(groupHikeId);
		if(groupOpt.isPresent()) {
			return groupOpt.get();
		}
		return null;
	}

	@Override
	public GroupHike getGroupHikeByEventName(String groupHikeEventName) {
		return ghRepo.findByEventName(groupHikeEventName);
	}

	@Override
	public GroupHike addGroupHike(GroupHike groupHike) {
		return ghRepo.save(groupHike);
	}

	@Override
	public GroupHike updateGroupHikeById(GroupHike groupHike, int groupHikeId) {
		Optional<GroupHike> groupOpt = ghRepo.findById(groupHikeId);
		GroupHike managed = null;
		if(groupOpt.isPresent()) {
			managed = groupOpt.get();
			if(groupHike.getTrail() !=null) {
			managed.setTrail(groupHike.getTrail());
			}
			if(groupHike.getUsers() !=null) {
			managed.setUsers(groupHike.getUsers());
			}
			managed.setEventName(groupHike.getEventName());
			managed.setMeetupDate(groupHike.getMeetupDate());
		}
		ghRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public void deleteGroupHikeById(int groupHikeId) {
		ghRepo.deleteById(groupHikeId);
	}

}
