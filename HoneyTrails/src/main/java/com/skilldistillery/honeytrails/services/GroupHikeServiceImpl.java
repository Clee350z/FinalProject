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
	public GroupHike getGroupHikeByTitle(String groupHikeTitle) {
		return ghRepo.findByEventName(groupHikeTitle);
	}

	@Override
	public GroupHike addGroupHike(GroupHike groupHike) {
		return ghRepo.save(groupHike);
	}

	@Override
	public GroupHike updateGroupHikeById(int groupHikeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGroupHikeById(int groupHikeId) {
		ghRepo.deleteById(groupHikeId);
	}

}
