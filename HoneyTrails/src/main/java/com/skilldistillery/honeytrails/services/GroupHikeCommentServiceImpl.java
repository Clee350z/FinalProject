package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.entities.GroupHikeComment;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.GroupHikeCommentRepository;
import com.skilldistillery.honeytrails.repositories.GroupHikeRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class GroupHikeCommentServiceImpl implements GroupHikeCommentService {

	@Autowired
	private GroupHikeCommentRepository ghcRepo;

	@Autowired
	private GroupHikeRepository ghRepo;

	@Autowired
	private UserRepository uRepo; 

	@Override
	public List<GroupHikeComment> getAllGroupHikeComments() {
		return ghcRepo.findAll();
	}

	@Override
	public GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId) {
		if (ghcRepo.existsById(groupHikeCommentId)) {
			return ghcRepo.findById(groupHikeCommentId).get();
		}
		return null;
	}

	@Override
	public GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment, String username, int groupHikeId) {
		GroupHike grpHike = ghRepo.findById(groupHikeId).get();
		User user = uRepo.findByUsername(username);
		groupHikeComment.setUser(user);
		groupHikeComment.setGroupHike(grpHike);
		return ghcRepo.saveAndFlush(groupHikeComment);
	}

	@Override
	public GroupHikeComment updateGroupHikeCommentById(GroupHikeComment groupHikeComment, int groupHikeCommentId,
			String username, int groupHikeId) {
		GroupHike grpHike = ghRepo.findById(groupHikeId).get();
		User user = uRepo.findByUsername(username);
		if (username.equals(groupHikeComment.getUser().getUsername())) {
			if (ghcRepo.existsById(groupHikeCommentId)) {
				groupHikeComment.setUser(user);
				groupHikeComment.setGroupHike(grpHike);
				return ghcRepo.save(groupHikeComment);
			}
		}
		return null;
	}

	@Override
	public void deleteGroupHikeCommentById(int groupHikeCommentId, String username) {
		User user = uRepo.findByUsername(username);
		if (user == ghcRepo.findById(groupHikeCommentId).get().getUser()) {
			ghcRepo.deleteById(groupHikeCommentId);
		}
	}

}
